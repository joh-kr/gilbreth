import org.apache.commons.math.linear.RealMatrix;
import org.junit.*;

import java.util.*;
import java.util.logging.Logger;

import play.test.*;
import models.*;

public class TestAttributeWeightingStage extends UnitTest {
	private Interviewee testuser;
	private Interview interview;
	private LevelRatingStage levelRating;
	private AttributeWeightingStage stage;
	
	private Level paymentPresent;
	private Level paymentAbsent;
	private Level forumPresent;
	private Level forumAbsent;
	
	private Attribute forum;
	private Attribute payment;
	
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
        stage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
        
        Map<Long, Double> levelIdsAndRates = new Hashtable();
        
        forum = Attribute.find("byName", "Forum").first();
		payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		
		paymentPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		paymentAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();	
		forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();
		
		levelIdsAndRates.put(forumAbsent.id, 2d);
		levelIdsAndRates.put(forumPresent.id, 7d); 
		
		levelRating.addLevelRates(levelIdsAndRates);
		levelIdsAndRates = new Hashtable();
		
		levelIdsAndRates.put(paymentPresent.id, 4d);
		levelIdsAndRates.put(paymentAbsent.id, 1d);
		
		levelRating.addLevelRates(levelIdsAndRates);		
    }
    
    @Test
    public void testStage() throws Exception
    {
		RealMatrix m;
		double row[];
		
		m = result.getMatrix();
		row = m.getRow(0);
		assertTrue(row[1] == 1.0d);
		assertTrue(row[4] == -0.5d);
		row = m.getRow(1);
		assertTrue(row[0] == 1.0d);
		assertTrue(row[4] == 0.5d);			
		
    	assertTrue(stage.hasAttribute(0));
    	assertTrue(stage.hasAttribute(1));
    	assertFalse(stage.hasAttribute(2));
    	
    	assertTrue(result.getRateFor(paymentPresent) > result.getRateFor(paymentAbsent));
    	assertTrue(result.getRateFor(forumAbsent) < result.getRateFor(forumPresent));
    	
    	if(stage.hasAttribute(0)) {
    		Attribute attribute = stage.getCurrentAttribute(0);
    		assertTrue(attribute == forum);
    		
    		Level best  = stage.getBestRatedLevel(0);
    		Level worst = stage.getWorstRatedLevel(0);
    		
    		
    		assertTrue(best == forumPresent);
    		assertTrue(worst == forumAbsent);
    		
    		stage.weightLevelsFor(attribute, 6);
    		
    		m = result.getMatrix();
    		row = m.getRow(0);
    		assertTrue(row[1] == 1.0d);
    		assertTrue(row[4] == -3.0d);
    		row = m.getRow(1);
    		assertTrue(row[0] == 1.0d);
    		assertTrue(row[4] == 3.0d);	
    	} else {
    		fail("missing attribute 0");
    	}
    	
    	if(stage.hasAttribute(1)) {
    		Attribute attribute = stage.getCurrentAttribute(1);
    		assertTrue(attribute == payment);
    		
    		Level best  = stage.getBestRatedLevel(1);
    		Level worst = stage.getWorstRatedLevel(1);
    		
    		m = result.getMatrix();
    		
    		assertTrue(best == paymentPresent);
    		assertTrue(worst == paymentAbsent);
    		
    		stage.weightLevelsFor(attribute, 6);
    		
    		m = result.getMatrix();
    		row = m.getRow(2);
    		assertTrue(row[3] == 1.0d);
    		assertTrue(row[4] == -3.0d);
    		row = m.getRow(3);
    		assertTrue(row[2] == 1.0d);
    		assertTrue(row[4] == 3.0d);	
    	} else {
    		fail("missing attribute 1");
    	}
		
    }
}
