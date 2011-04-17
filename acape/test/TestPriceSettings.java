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

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class TestPriceSettings extends UnitTest {
	private walkStages walkStages;
	private PriceEstimationStage stage;
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
        
        stage = (PriceEstimationStage) interview.getStage("PriceEstimationStage");
        stage.initializePricePerUtility();
        concept = stage.getPricedConcept();
    }
    
    @Test
    public void testPriceMinimum()
    {
    	assertTrue("Price " + concept.getPrice() + " >= " + settings.minimumPrice, 
    			concept.getPrice().compareTo(settings.minimumPrice) >= 0);
    }
    
    @Test
    public void testPriceMaximum()
    {
    	assertTrue("Price " + concept.getPrice() + " <= " + settings.maximumPrice,
    			concept.getPrice().compareTo(settings.maximumPrice) <= 0);
    }
    
    @Test
    public void testValidLevels() throws Exception
    {
    	List<Level> levels = concept.getLevels();
    	for(Level l : levels) {
    		for(int i : result.getColumnsFor(l)) {
    			assertTrue(i >= 0);
    		}
    	}
    }
    
    @Test
    public void testCorrectPriceSetting() throws Exception
    {
    	double expectedPrice = result.pricePerUtilityUnit * utility.computeUtilityFor(concept.getLevels());
    	expectedPrice = Math.max(expectedPrice, settings.minimumPrice.doubleValue());
    	expectedPrice = Math.min(expectedPrice, settings.maximumPrice.doubleValue());
    	expectedPrice = Math.round(expectedPrice);
    	assertEquals(expectedPrice, concept.getPrice().doubleValue(), 0.001);
    }
    
    
    @Test
    public void testBuy() throws Exception {
    	double oldPricePerUtilityUnit = result.pricePerUtilityUnit;
    	stage.BuyConcept();
    	assertTrue(oldPricePerUtilityUnit < result.pricePerUtilityUnit);
    }
    
    @Test
    public void testDoNotBuy() throws Exception {
    	double oldPricePerUtilityUnit = result.pricePerUtilityUnit;
    	stage.DoNotBuyConcept();
    	assertTrue(oldPricePerUtilityUnit > result.pricePerUtilityUnit);
    }
    
    @Test
    public void afterBuyIncreaseAboveMinimumPrice() throws Exception {
    	double oldPricePerUtilityUnit = result.pricePerUtilityUnit;
    	if(concept.getPrice().compareTo(settings.minimumPrice) == 0) {
        	stage.BuyConcept();
        	concept = stage.getPricedConcept();
    		assertTrue(concept.getPrice().compareTo(settings.minimumPrice) > 0);
    	}
    }
    
    @Test
    public void testCompleteStage() throws Exception {
    	// secret price per utility to be estimated by survey
    	double secretPU = 35;
    	do {
    		/*jlog.log(java.util.logging.Level.INFO, 
    			"utility " + concept.getUtility() + 
    			" price: " + concept.getPrice() + 
    			" current P/U " + result.pricePerUtilityUnit);*/
    		if(concept.getUtility() * secretPU >= concept.getPrice().doubleValue()) {
    			stage.BuyConcept();
    			//jlog.log(java.util.logging.Level.INFO, "Buy");
    		} else {
    			stage.DoNotBuyConcept();
    			//jlog.log(java.util.logging.Level.INFO, "Do Not Buy");
    		}
    		if(!stage.isFinished()) {
    			concept = stage.getPricedConcept();
    		}
    	} while(!stage.isFinished());
    	assertEquals(secretPU, result.pricePerUtilityUnit, 0.5);
    }
}
