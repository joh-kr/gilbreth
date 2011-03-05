import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;


public class PairsUtilityStageTest extends UnitTest {
	
    @Before
    public void setup() {
        Fixtures.deleteAll();
    }
	
	@Test
	public void singularMatrix() throws Exception {
		Fixtures.load("data.yml");
		Interviewee testuser = Interviewee.find("byName", "TestUser").first();
		Interview interview = Interview.createNewInterview(testuser);
		PairsUtilityStage stage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
		
		stage.calculateR2();
	}
}
