import java.util.List;
import java.util.logging.Logger;

import models.AttributeWeightingStage;
import models.Concept;
import models.ConceptComparisonStage;
import models.Interview;
import models.Interviewee;
import models.LevelRatingStage;
import models.PairsUtilityStage;
import models.PriceEstimationStage;
import models.PriceSettings;
import models.PricedConcept;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class TestPriceSettings extends UnitTest {
	private walkStages walkStages;
	private PriceEstimationStage stage;
	private PriceSettings settings;
	
	private PricedConcept concept;
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");

    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
        
		Interviewee testuser = Interviewee.find("byName", "TestUser").first();
		Interview interview = Interview.createNewInterview(testuser);
		
		settings = PriceSettings.all().first();
        
        walkStages = new walkStages();
        //first stage
        walkStages.walkLevelRateStage((LevelRatingStage) interview.getStage("AttributeRatingStage"));
        // second stage
        walkStages.walkAttributeWeightingStage((AttributeWeightingStage) interview.getStage("StageWeightAttributes"));
        // third stage
        walkStages.walkPairsUtilityStage((PairsUtilityStage) interview.getStage("PairsUtilityStage"));
        
        stage = (PriceEstimationStage) interview.getStage("PriceEstimationStage");
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
    			concept.getPrice().compareTo(settings.maximumPrice) < 0);
    }    
}
