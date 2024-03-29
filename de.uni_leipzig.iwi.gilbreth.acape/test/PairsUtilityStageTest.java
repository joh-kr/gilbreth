import org.apache.commons.math.linear.RealMatrix;
import org.codehaus.groovy.ant.UberCompileTask;
import org.junit.*;

import java.util.*;
import java.util.logging.Logger;

import play.test.*;
import models.*;


public class PairsUtilityStageTest extends UnitTest {
	
	private Interviewee testuser;
	private Interview interview;
	private PairsUtilityStage pairsUtilityStage;
	private Result result;
	private Utility utility;
	private walkStages walkStages;

	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
        
		testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);        
		pairsUtilityStage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
        result = pairsUtilityStage.getResult();
        walkStages = new walkStages();
        utility = new Utility(result);
        
		LevelRatingStage levelRatingStage = (LevelRatingStage) interview.getStage("AttributeRatingStage");
        AttributeWeightingStage attributeWeightingStage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
        
        // first stage
        walkStages.walkLevelRateStage(levelRatingStage);
        // second stage
        walkStages.walkAttributeWeightingStage(attributeWeightingStage);     
    }
    
    @Test
    public void matrixValidForRegression()
    {
    	double[] dependent = result.getDependentVariableValues();
    	double[][] independent = result.getIndeptendentVariableValues();
    	
    	int levelCount = Level.findAll().size();
    	
    	List<Level> allLevels = Level.findAll();
    	HashSet<String> features = new HashSet();
    	for(Level l : allLevels) {
    		if(l.features != null) {
    			for(String feature : Arrays.asList(l.features.split(","))) {
    				/*jlog.log(java.util.logging.Level.INFO, 
    					"Feature " + feature);*/
    				features.add(feature);
    			}
    		}
    	}
    	int featureCount = features.size();
    	
    	assertTrue(dependent.length == levelCount);
    	assertTrue("FeatureCount: " + featureCount + " IndependentVariables: " + independent[0].length,
    			independent[0].length == featureCount);
    	assertTrue(dependent.length == independent.length);
    	assertTrue(independent.length >= independent[0].length);	
    }
    
    @Test
    public void improveObservationsChangeUtility() throws Exception
    {
    	LevelsPair pair = pairsUtilityStage.computeLevelsPair(2);
    	List<Level> lhs = pair.getLHS();
    	List<Level> rhs = pair.getRHS();
    	
		List<Long> lhsIds = new ArrayList();
		List<Long> rhsIds = new ArrayList();
		
		for (Level lvl : lhs) {
			lhsIds.add(lvl.id);
		}
		for (Level lvl : rhs) {
			rhsIds.add(lvl.id);
		}
		
		pair.calculateUtilities();
		
		double oldUtilityLhs = pair.getUtilityForLhs();
		double oldUtilityRhs = pair.getUtilityForRhs();
		
		//choose best rating for lhs
		pairsUtilityStage.saveNewObservation(lhsIds, rhsIds, -4.0);
		
		pair.calculateUtilities();
		
		double utilityLhs = pair.getUtilityForLhs();
		double utilityRhs = pair.getUtilityForRhs();
		
		//improve lhs compared to rhs
		assertTrue((oldUtilityLhs - oldUtilityRhs) < (utilityLhs - utilityRhs));
    }
	
	@Test
	public void fillOutStage() throws Exception {
		walkStages.walkPairsUtilityStage(pairsUtilityStage, utility);
		assertTrue(pairsUtilityStage.isFinished());
	}
	
	@Test
	public void testAttributeFrequencies() throws Exception
	{
		List<Attribute> attributes = Attribute.findAll();
		for(Attribute a : attributes) {
			assertEquals("Frequency of " + a.name + " = " + result.getAttributeFrequency(a), a.getLevels(result.excludedLevels).size(), result.getAttributeFrequency(a), 0.01);
		}
	}
	
	@Test
	public void testAttributeFrequenciesInPairs() throws Exception
	{
		LevelsPair pair = pairsUtilityStage.computeLevelsPair(2);
		
		int freq1 = result.getLevelFrequency(pair.getLHS().get(0));
		
		pairsUtilityStage.saveNewObservationByLevels(pair.getLHS(), pair.getRHS(), 1.0);
		
		assertEquals(freq1 + 1, result.getLevelFrequency(pair.getLHS().get(0)));
		
		
	}
}
