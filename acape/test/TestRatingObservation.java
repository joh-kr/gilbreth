import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.*;
import play.test.*;
import models.*;
import models.TestModels.RatingObservation;

public class TestRatingObservation extends UnitTest {
	private RatingObservation ro;
	private Attribute cm;
	private Level cmPresent;
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
        
        cm = Attribute.find("byName", "Customer Management").first();
        cmPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", cm, "Present").first();
        
        ro = RatingObservation.find("select obs from RatingObservation obs where obs.level = ?", cmPresent).first();
        
    }
    
    @Test
    public void observationExists() throws Exception {
    	assertNotNull(ro);
    	assertNotNull(cm);
    }
    
    @Test
    public void observationContainsRating() throws Exception {
		
 	
		assertTrue(ro.level == cmPresent);
		assertTrue(ro.rating == 6);
    }
    
    @Test
    public void allLevelsRated()
    {
    	List<RatingObservation> allObservations = RatingObservation.findAll();
    	List<Level> allLevels = Level.findAll();
    	
    	for(RatingObservation obs : allObservations) {
    		allLevels.remove(obs.level);
    	}
    	
    	assertEquals(0, allLevels.size());
    }
}
