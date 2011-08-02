package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.math.stat.regression.SimpleRegression;

/**
 * Stage for comparing concepts
 * 
 * @author Max Lillack
 *
 */
public class ConceptComparisonStage extends Stage{

	private Utility utility;
	
	public ConceptComparisonStage(Interview interview, Result result) {
		super(interview, result);
		utility = new Utility(result);
	}
	/**
	 * Generates 3 concepts for comparison
	 * @return Three concepts with utility set
	 * @throws Exception
	 */
	public List<Concept> calculateConcepts() throws Exception{
		Random r = new Random();
		List<Concept> concepts = new ArrayList<Concept>();
		
		while(concepts.size() < 3) {
			Concept concept = new Concept();
			List<Attribute> attributes = Attribute.findAll();
			for(Attribute a : attributes){
				List<Level> levels = a.getLevels(result.excludedLevels);
				int n = r.nextInt(levels.size());
				
				concept.addLevel(levels.get(n));
			}
			if(concept.isValid()) {
				concept.setUtility(utility.getFinalUtility(concept.getLevels()));
				concepts.add(concept);
			}
			
		}
		
		return concepts;
	}
	
	public void saveObservation(List<Double> finalUtilities, List<Integer> buyingProbabilities) {
		SimpleRegression regression = utility.getCalibratedUtilitiesRegression(finalUtilities, buyingProbabilities);
		
		result.calibratedUtilityIntercept = regression.getIntercept();
		result.calibratedUtilitySlope = regression.getSlope();
		result.save();
		
	}
	
}
