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

    }

	@Test
	public void singularMatrix() throws Exception {

		RealMatrix m = result.getMatrix();
		
		//stage.calculateR2();
	}
	
}
