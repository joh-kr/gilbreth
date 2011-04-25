import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import models.AttributeWeightingStage;
import models.Concept;
import models.ConceptComparisonStage;
import models.Interview;
import models.Interviewee;
import models.Level;
import models.LevelRatingStage;
import models.PairsUtilityStage;
import models.PriceEstimationStage;
import models.PriceSettings;
import models.PricedConcept;
import models.Result;
import models.Utility;

import org.apache.commons.math.stat.regression.SimpleRegression;
import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class Calibration extends UnitTest {
	private walkStages walkStages;
	private ConceptComparisonStage stage;
	private PriceSettings settings;
	
	private PricedConcept concept;
	private Result result;
	private Utility utility;
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");

    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
        
		Interviewee testuser = Interviewee.find("byName", "TestUser").first();
		Interview interview = Interview.createNewInterview(testuser);
		
		result = interview.result;
		utility = new Utility(result);
		
		settings = PriceSettings.all().first();
        
        walkStages = new walkStages();
        //first stage
        walkStages.walkLevelRateStage((LevelRatingStage) interview.getStage("AttributeRatingStage"));
        // second stage
        walkStages.walkAttributeWeightingStage((AttributeWeightingStage) interview.getStage("StageWeightAttributes"));
        // third stage
        walkStages.walkPairsUtilityStage((PairsUtilityStage) interview.getStage("PairsUtilityStage"), utility);
        
        stage = (ConceptComparisonStage) interview.getStage("ConceptComparisonStage");
    }
    
    @Test
    public void calibratedUtility()
    {
    	List<Double> finalUtilities = new ArrayList<Double>();
    	finalUtilities.add(4.0);
    	finalUtilities.add(5.0);
    	finalUtilities.add(6.0);
    	
    	List<Integer> buyingProbabilities = new ArrayList<Integer>();
    	buyingProbabilities.add(0);
    	buyingProbabilities.add(50);
    	buyingProbabilities.add(100);
    	
    	SimpleRegression regression = utility.getCalibratedUtilitiesRegression(finalUtilities, buyingProbabilities);
    	
    	assertFalse(Double.isNaN(regression.getSlope()));
    	assertFalse(Double.isNaN(regression.getIntercept()));
    }
    
    @Test
    public void utilityCalculation() throws Exception
    {
    	List<Concept> concepts = stage.calculateConcepts();
    	
    	List<Double> finalUtilities = new ArrayList<Double>();
    	finalUtilities.add(4.0);
    	finalUtilities.add(5.0);
    	finalUtilities.add(6.0);
    	
    	List<Integer> buyingProbabilities = new ArrayList<Integer>();
    	buyingProbabilities.add(0);
    	buyingProbabilities.add(50);
    	buyingProbabilities.add(100);
    	
    	stage.saveObservation(finalUtilities, buyingProbabilities);
    	
    	//@TODO add test
    	double calibratedUtility = utility.computeCalibratedUtilityFor(concepts.get(0).getLevels());
    	
    	
    }

}
