import org.apache.commons.math.linear.RealMatrix;
import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;


public class PairsUtilityStageTest extends UnitTest {
	
	private Interviewee testuser;
	private Interview interview;
	private PairsUtilityStage pairsUtility;
	private Result result;
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("data.yml");
        
		testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);        
		pairsUtility = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
        result = pairsUtility.getResult();
		Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		Level excludedLevel = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();
		result.addExcludedLevel(excludedLevel);
    }
	
    @Test
    public void testLevelIsExluded() throws Exception {
    	Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
		Level excludedLevel = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();
		Level includedLevel = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		
		assertNotNull(excludedLevel);
		assertNotNull(includedLevel);
		//check that correct Level was excluded
		assertTrue(result.excludedLevels.contains(excludedLevel));
		//check that correct Level is included
		assertFalse(result.excludedLevels.contains(includedLevel));		
    }
    /*
	@Test
	public void singularMatrix() throws Exception {

		RealMatrix m = result.getMatrix();
		
		//stage.calculateR2();
	}
	*/
}
