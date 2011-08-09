import org.apache.commons.math.linear.RealMatrix;
import org.hibernate.mapping.Collection;
import org.junit.*;

import java.util.*;
import java.util.logging.Logger;

import play.test.*;
import models.*;
import models.TestModels.AttributeImportanceObservation;
import models.TestModels.RatingObservation;


public class walkStages {

	public void walkLevelRateStage(LevelRatingStage stage) throws Exception
	{
		Result result = stage.getResult();
		result.validConcepts = Concept.getValidConcepts(result.excludedLevels);
		List<RatingObservation> testObservations = RatingObservation.findAll();
		RatingObservation.addObservations(stage, testObservations);
	}
	
	public void walkAttributeWeightingStage(AttributeWeightingStage stage) throws Exception
	{ 	
		List<AttributeImportanceObservation> testObservations = AttributeImportanceObservation.findAll();
		AttributeImportanceObservation.addObservations(stage, testObservations);
	}
	
	private int getUtilityComparisionRating(double ratingLhs, double ratingRhs) {
    	int rating;
		if(ratingLhs == ratingRhs) {
    		rating = 0;
    	} else if(ratingLhs > ratingRhs) {
    		rating = (int) Math.max(-4, Math.round(ratingRhs - ratingLhs));
    	} else {
    		rating = (int) Math.min(4, Math.abs(Math.round(ratingLhs - ratingRhs)));
    	}
    	return rating;
	}
	
	/*
	 * answer pairs utility stage consistent by using expected utility
	 */
	public void walkPairsUtilityStage(PairsUtilityStage stage, Utility utility) throws Exception
	{
		while(!stage.isFinished()) {
	    	LevelsPair pair = stage.computeLevelsPair(2);
	    	if(pair != null) {
		    	List<Level> lhs = pair.getLHS();
		    	List<Level> rhs = pair.getRHS();
		    	
		    	double utilityLhs = utility.getFinalUtility(lhs);
		    	double utilityRhs = utility.getFinalUtility(rhs);
		    	
		    	int rating = getUtilityComparisionRating(utilityLhs, utilityRhs);

		    	stage.saveNewObservationByLevels(lhs, rhs, rating);	    		
	    	}
		}
	}
	
	public void walkCalibrationStage(ConceptComparisonStage stage, Utility utility) throws Exception
	{
		List<Concept> concepts = stage.calculateConcepts();
		
		List<Double> finalUtilities = new ArrayList<Double>();
		List<Integer> buyingProbabilities = new ArrayList<Integer>();
		
		for(Concept c : concepts) {
			finalUtilities.add(utility.getFinalUtility(c.getLevels()));
		}
		
		Collections.sort(finalUtilities);
		
		buyingProbabilities.add(10);
		buyingProbabilities.add(50);
		buyingProbabilities.add(90);
		
		stage.saveObservation(finalUtilities, buyingProbabilities);
		
	}
}
