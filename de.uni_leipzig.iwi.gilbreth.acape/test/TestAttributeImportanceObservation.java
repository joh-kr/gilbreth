import java.util.List;
import java.util.logging.Logger;

import models.Attribute;
import models.Level;
import models.TestModels.AttributeImportanceObservation;
import models.TestModels.RatingObservation;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class TestAttributeImportanceObservation extends UnitTest {
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
    }
    
    @Test
    public void TestAllAttributesRated() {
    	List<Attribute> allAttributes = Attribute.findAll();
    	List<AttributeImportanceObservation> observersations = AttributeImportanceObservation.findAll();
    	
    	for(AttributeImportanceObservation obs : observersations) {
    		allAttributes.remove(obs.attribute);
    	}
    	
    	assertEquals(0, allAttributes.size());
    }
    
    @Test
    public void TestAllValidRatings() {
    	List<AttributeImportanceObservation> observersations = AttributeImportanceObservation.findAll();
    	
    	for(AttributeImportanceObservation obs : observersations) {
    		assertTrue(obs.importance >= 0);
    		assertTrue(obs.importance <= 7);
    	}
    }
}