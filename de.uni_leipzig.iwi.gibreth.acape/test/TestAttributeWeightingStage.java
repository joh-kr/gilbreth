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

	private Level forumPresent;
	private Level forumAbsent;
	
	private Attribute forum;
	
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
        stage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
        
        walkStages.walkLevelRateStage(levelRating);
        walkStages.walkAttributeWeightingStage(stage);
        
        forum = Attribute.find("byName", "Forum").first();
	
		forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();

    }
    
    @Test
    public void testStage() throws Exception
    {	
    	assertTrue(stage.hasAttribute(0));
    	
    	int i = 0;
    	while(stage.getCurrentAttribute(0) != forum) {
    		stage.hasAttribute(++i);
    	}

    	Attribute attribute = stage.getCurrentAttribute(0);
    	assertTrue(attribute.name, attribute == forum);
    		
    	Level best  = stage.getBestRatedLevel(0);
    	Level worst = stage.getWorstRatedLevel(0);
    		
    	assertTrue(best == forumPresent);
    	assertTrue(worst == forumAbsent);
    }
}
