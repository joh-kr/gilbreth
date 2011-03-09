import org.apache.commons.math.linear.RealMatrix;
import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;


public class PairsUtilityStageTest extends UnitTest {
	
	private Interviewee testuser;
	private Interview interview;
	private PairsUtilityStage pairsUtilityStage;
	private Result result;
	private walkStages walkStages;
	
	private Attribute payment;
	private Attribute forum;
	private Level forumPresent;
	private Level forumAbsent;
	private Level paymentPresent;
	private Level paymentAbsent;
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("data.yml");
        
		testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);        
		pairsUtilityStage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
        result = pairsUtilityStage.getResult();
        walkStages = new walkStages();
        
		LevelRatingStage levelRatingStage = (LevelRatingStage) interview.getStage("AttributeRatingStage");
        AttributeWeightingStage attributeWeightingStage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
        
        // first stage
        walkStages.walkLevelRateStage(levelRatingStage);
        // second stage
        walkStages.walkAttributeWeightingStage(attributeWeightingStage);
        
    	payment = Attribute.find("byName", "Payment with Fraud Detection").first();
    	forum = Attribute.find("byName", "Forum").first();
    	
		forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();
		paymentPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		paymentAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();        
    }
    
    @Test
    public void testComputedPairs() throws Exception
    {
    	PairsUtilityStage.LevelsPair pair = pairsUtilityStage.computeLevelsPair(2);
    	List<Level> lhs = pair.getLHS();
    	List<Level> rhs = pair.getRHS();
		
		assertTrue(lhs.contains(forumPresent) && lhs.contains(paymentPresent));
		assertTrue(rhs.contains(forumAbsent) && rhs.contains(paymentAbsent));
		
		List<Long> lhsIds = new ArrayList();
		List<Long> rhsIds = new ArrayList();
		
		for (Level lvl : lhs) {
			lhsIds.add(lvl.id);
		}
		for (Level lvl : rhs) {
			rhsIds.add(lvl.id);
		}
		
		//RealMatrix m = result.getMatrix();
		
		// prefer lhs with both present
		if(lhs.contains(forumPresent)) {
			pairsUtilityStage.saveNewObservation(lhsIds, rhsIds, -4.0);
		} else {
			pairsUtilityStage.saveNewObservation(lhsIds, rhsIds,  4.0);		
		}  
		
		pair = pairsUtilityStage.computeLevelsPair(2);
    	lhs = pair.getLHS();
    	rhs = pair.getRHS();
    	
		lhsIds = new ArrayList();
		rhsIds = new ArrayList();
		
		for (Level lvl : lhs) {
			lhsIds.add(lvl.id);
		}
		for (Level lvl : rhs) {
			rhsIds.add(lvl.id);
		}    	
    	
		// do not repeat same pair
    	assertTrue(lhs.contains(forumAbsent) ^ lhs.contains(paymentAbsent));
		
		if(lhs.contains(forumPresent)) {
			pairsUtilityStage.saveNewObservation(lhsIds, rhsIds, -2.0);
		} else {
			pairsUtilityStage.saveNewObservation(lhsIds, rhsIds,  2.0);		
		}    	
    	
		pair = pairsUtilityStage.computeLevelsPair(2);
		assertNull(pair);
		assertTrue(pairsUtilityStage.isFinished());
		//m = result.getMatrix();
    }
    
	@Test
	public void singularMatrix() throws Exception {

		RealMatrix m = result.getMatrix();
		
		//stage.calculateR2();
	}
	
}
