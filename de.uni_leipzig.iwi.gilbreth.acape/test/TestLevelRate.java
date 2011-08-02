import org.apache.commons.math.linear.RealMatrix;
import org.junit.*;

import java.util.*;
import java.util.logging.Logger;

import play.test.*;
import models.*;


public class TestLevelRate extends UnitTest {
	private Interviewee testuser;
	private Interview interview;
	private LevelRatingStage levelRating;
	
	private Result result;
	
	private walkStages walkStages;
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");

    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
        
        walkStages = new walkStages();
        
		testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);
        
		levelRating = (LevelRatingStage) interview.getStage("AttributeRatingStage");
        result = levelRating.getResult();
        walkStages.walkLevelRateStage(levelRating);
    }
	
    public boolean matrixIsEmpty() {
    	boolean isEmpty = true;
    	
    	RealMatrix m = result.getMatrix();
    	
    	for(int i = 0; i < m.getRowDimension(); i++) {
    		for(int j = 0; j < m.getColumnDimension(); j++) {
    			isEmpty = isEmpty && (m.getEntry(i,j) == 0);
    		}
    	}
  
    	return isEmpty;
    }
    
    @Test
    public void testSomeRatings() throws Exception {
		Attribute forum = Attribute.find("byName", "Forum").first();
		Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		
		Level forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		Level forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();		
		Level paymentPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		Level paymentAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();		
		
		assertTrue("Present: " + result.getRateFor(paymentPresent) + " Absent: " + result.getRateFor(paymentAbsent),
				result.getRateFor(paymentPresent) > result.getRateFor(paymentAbsent));
		assertTrue(result.getRateFor(forumPresent) > result.getRateFor(forumAbsent));
    }
    
    @Test
    public void testAllLevelsAreRated() throws Exception {
    	List<Level> allLevels = Level.findAll();
    	allLevels.removeAll(result.excludedLevels);
    	for(Level l : allLevels) {
    		assertNotNull(result.getRateFor(l));
    	}
    }
}
