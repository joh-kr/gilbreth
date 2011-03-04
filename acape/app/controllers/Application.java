package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * The application controller of the ACA-PE survey application. It handles the application flow.
 * It is organized as follows. Each stage of the Survey are two methods assigned. One for the 
 * get-Requests and one for the post- Request. The get opens the page, the post saves the results.
 * 
 * The application starts with the index, that opens the excludeLevel Stage.
 * 
 * @author Johannes Müller
 *
 */
public class Application extends Controller {
	
	// ----- Start the survey
	
	public static void index() throws Exception {
		Interviewee interviewee = Interviewee.createNewInterviewee("John Deere", "john.deere@gmail.com");
		Interview interview = Interview.createNewInterview(interviewee);
		
		long id = interview.id;
		excludeLevel(id, 0);
		
		//render(id);
	}
	
	// ------ Exclude Level Stage -------

	public static void excludeLevel(long interviewId, int page) throws Exception {	
		Interview interview = getInterview(interviewId);
		
		LevelExclusionStage stage = (LevelExclusionStage) interview.getStage("LevelExclusion");
		
		if(stage.hasAttribute(page)){		
			Attribute attribute = stage.getCurrentAttribute(page);
			render(interviewId, attribute, page);
		}else{
			levelRate(interviewId, 0);
			//index();
		}
	}

	public static void postExcludeLevel(long interviewId,
			List<Long> excludeLevelIds, int page) throws Exception {
		Interview interview = getInterview(interviewId);
		
		LevelExclusionStage stage = (LevelExclusionStage) interview.getStage("LevelExclusion");
	
		stage.excludeLevels(removeNullEntries(excludeLevelIds));
		
		excludeLevel(interview.id, ++page);
	}

	// ------ Rate Level Stage -------

	public static void levelRate(long interviewId, int page) throws Exception {

		Interview interview = getInterview(interviewId);
		LevelRatingStage stage = (LevelRatingStage) interview.getStage("AttributeRatingStage");
	
		if(stage.hasAttribute(page)){		
			Attribute attribute = stage.getCurrentAttribute(page);
			render(interviewId, attribute, page);
		}else{
			attributeImportance(interviewId, 0);
			//index();
		}
	}

	public static void postLevelRate(long interviewId, List<Long> levelIds,
			List<Double> levelRates, int page) throws Exception {

		Interview interview = getInterview(interviewId);
		
		if(levelIds.size() != levelRates.size()) 
			throw new Exception("An number of level IDs and level rates is unequal. Size of Level ID: " + levelIds.size() + ". Size of Level Rates: " + levelRates.size());
			// Ask the respondent to fill out all questions
		
		
		Map levelIdsAndRates = new Hashtable();
		for(int i = 0; i < levelIds.size(); i++){
			levelIdsAndRates.put(levelIds.get(i), levelRates.get(i));
		}
		
		LevelRatingStage stage = (LevelRatingStage) interview.getStage("AttributeRatingStage");
		
		stage.addLevelRates(levelIdsAndRates);
		
		levelRate(interview.id, ++page);
	}

	// ------ Attribute Importance Stage -------
	
	public static void attributeImportance(long interviewId, int page) throws Exception {

		Interview interview = getInterview(interviewId);
		AttributeWeightingStage stage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
	
		if(stage.hasAttribute(page)){		
			Attribute attribute = stage.getCurrentAttribute(page);
			
			Level best  = stage.getBestRatedLevel(page);
			Level worst = stage.getWorstRatedLevel(page);
			
			render(interviewId, attribute, best, worst, page);
			
		}else{
			pairsUtilities(interviewId);
		}
	}
	
	public static void postAttributeImportance(long interviewId, long attributeId, double importance, int page) throws Exception {
		Interview interview = getInterview(interviewId);
		Attribute attribute = Attribute.findById(attributeId);
		
		AttributeWeightingStage stage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
		stage.weightLevelsFor(attribute, importance);
		
		attributeImportance(interviewId, ++page);
	}
	
	// ------ Pairs Utility Stage -------
	
	public static void pairsUtilities(long interviewId) throws Exception{
		Interview interview = getInterview(interviewId);
		PairsUtilityStage stage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
		
		PairsUtilityStage.LevelsPair pair = stage.computeLevelsPair(2);
		if (!stage.isFinished()) {
			render(interviewId, pair);

		} else {
			conceptComparison(interviewId, false);
		}
			
	}
	
	public static void postPairsUtilities(long interviewId, List<Long> lhsIds, List<Long> rhsIds, double preference) throws Exception {
		Interview interview = getInterview(interviewId);
		PairsUtilityStage stage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");	
		
		stage.saveNewObservation(lhsIds, rhsIds, preference);
		
		pairsUtilities(interviewId);

	}
	
	// ------ Concept Comparison Stage -------
	
	public static void conceptComparison(long interviewId, boolean finished) throws Exception{
		Interview interview = getInterview(interviewId);
		ConceptComparisonStage stage = (ConceptComparisonStage) interview.getStage("ConceptComparisonStage");
		
		List<Concept> concepts = stage.calculateConcepts();

		if (!finished) {
			render(interviewId, concepts);

		} else {
			priceEstimation(interviewId, false);
		}
			
	}
	
	public static void postConceptComparison(long interviewId) throws Exception {
		Interview interview = getInterview(interviewId);
		
		conceptComparison(interviewId, true);

	}
	
	// ------ Price Estimation Stage -------
	
	public static void priceEstimation(long interviewId, boolean finished) throws Exception{
		Interview interview = getInterview(interviewId);
		PriceEstimationStage stage = (PriceEstimationStage) interview.getStage("PriceEstimationStage");
		
		PricedConcept concept = stage.getPricedConcept();

		if (!finished) {
			render(interviewId, concept);

		} else {
			index(); // Survey is finished becuase the other stages are not implemented yet
		}
			
	}
	
	public static void postPriceEstimation(long interviewId) throws Exception {
		Interview interview = getInterview(interviewId);
		
		conceptComparison(interviewId, true);

	}
	
	
	
	// ----- Helper ---------
	
	/**
	 * returns a interview object from the database 
	 * 
	 * @return a Interview object corresponding to the passed id
	 */
	private static Interview getInterview(long id) throws Exception{
		Interview interview = Interview.findById(id);
		if(interview == null) throw new Exception("For the passed interview ID " + id + " is no interview saved.");
		return interview;
	}
	
	/**
	 * removes null values from a list
	 * 
	 * @param list
	 * @return
	 */
	private static List<Long> removeNullEntries(List<Long> list){
		// If no Levels excluded the response params do not contain a variable
		// excludeLevelIds and play cannot map any value to the excludeLevelIds
		// parameter
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				
				// The array contains entries for all levels of an attribute
				// if one is not checked, it gets a null value
				if(list.get(i) == null){
					list.remove(i);
				}
			}
		}
		return list;
	}

}