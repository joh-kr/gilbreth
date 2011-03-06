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
        Fixtures.load("data.yml");
        
        walkStages = new walkStages();
        
		testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);        
		levelRating = (LevelRatingStage) interview.getStage("AttributeRatingStage");
        result = levelRating.getResult();

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
    public void testStage() throws Exception {
    	//matrix is empty at start
    	assertTrue(matrixIsEmpty());
    	
		assertTrue(levelRating.hasAttribute(0));
		
		Attribute forum = Attribute.find("byName", "Forum").first();
		Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		
		Level forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		Level forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();		
		Level paymentPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		Level paymentAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();		
		
		assertTrue(levelRating.getCurrentAttribute(0) == forum);
	
		assertTrue(levelRating.hasAttribute(1));
		
		assertTrue(levelRating.getCurrentAttribute(1) == payment);
	
		walkStages.walkLevelRateStage(levelRating);
		
		//@TODO check entries
		
		assertTrue(result.getRateFor(paymentPresent) > result.getRateFor(paymentAbsent));
		assertTrue(result.getRateFor(forumAbsent) < result.getRateFor(forumPresent));
	
		assertFalse(levelRating.hasAttribute(2));
    }
}
