import java.util.logging.Logger;

import models.RequiresConstraint;
import models.Result;

import org.junit.*;

import play.test.*;


public class TestExcludeLevel extends UnitTest {
	private Result result;
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
    }
    
    @Test
    public void testIfExcluded() throws Exception {    	
    	result = Result.all().first();
    	assertNotNull(result);
    	assertTrue(result.excludedLevels.size() > 0);
    	//jlog.log(java.util.logging.Level.INFO, "First excluded Level " + result.excludedLevels.get(0).name);
    	assertEquals(result.excludedLevels.get(0).name, "Debit Card and Credit Card");
    }
}
