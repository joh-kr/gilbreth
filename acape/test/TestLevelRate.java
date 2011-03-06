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
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");

    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("data.yml");
        
		testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);        
		levelRating = (LevelRatingStage) interview.getStage("AttributeRatingStage");
        result = levelRating.getResult();
		Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		Level excludedLevel = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();
		result.addExcludedLevel(excludedLevel);
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
    
    public void rateLevelForum() throws Exception
    {
    	Attribute forum = Attribute.find("byName", "Forum").first();
    	Map<Long, Double> levelIdsAndRates = new Hashtable();
		Level forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		Level forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();
		levelIdsAndRates.put(forumAbsent.id, 2d);
		levelIdsAndRates.put(forumPresent.id, 7d);
		levelRating.addLevelRates(levelIdsAndRates);
		
		RealMatrix m = result.getMatrix();
		
		double row[] = m.getRow(0);
		assertTrue(row[1] == 1.0d);
		assertTrue(row[4] == -0.5d);
		row = m.getRow(1);
		assertTrue(row[0] == 1.0d);
		assertTrue(row[4] == 0.5d);	
		
		assertTrue(result.getRateFor(forumAbsent) < result.getRateFor(forumPresent));
    }
    
    public void rateLevelPayment() throws Exception
    {
    	Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
    	Map<Long, Double> levelIdsAndRates = new Hashtable();
		Level paymentPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		Level paymentAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();	

		levelIdsAndRates.put(paymentPresent.id, 4d);
		levelIdsAndRates.put(paymentAbsent.id, 1d);
		levelRating.addLevelRates(levelIdsAndRates);
		
		RealMatrix m = result.getMatrix();
		
		double row[] = m.getRow(2);
		assertTrue(row[3] == 1.0d);
		assertTrue(row[4] == -0.5d);
		row = m.getRow(3);
		assertTrue(row[2] == 1.0d);
		assertTrue(row[4] == 0.5d);
		
		assertTrue(result.getRateFor(paymentPresent) > result.getRateFor(paymentAbsent));
    }
    
    @Test
    public void testStage() throws Exception {
    	//matrix is empty at start
    	assertTrue(matrixIsEmpty());
    	
		assertTrue(levelRating.hasAttribute(0));
		
		Attribute forum = Attribute.find("byName", "Forum").first();
		Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		
		assertTrue(levelRating.getCurrentAttribute(0) == forum);
	
		assertTrue(levelRating.hasAttribute(1));
		
		assertTrue(levelRating.getCurrentAttribute(1) == payment);
	
		rateLevelForum();
		rateLevelPayment();
	
		assertFalse(levelRating.hasAttribute(2));
		
    
    }
}
