import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javassist.bytecode.StackMapTable.Walker;

import models.Attribute;
import models.AttributeWeightingStage;
import models.Interview;
import models.Interviewee;
import models.Level;
import models.LevelRatingStage;
import models.PairsUtilityStage;
import models.Result;
import models.Utility;

import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math.stat.regression.SimpleRegression;
import org.junit.*;

import play.test.*;

public class TestUtilityCalculations extends UnitTest {
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
    
	private Utility utility;
	private Interview interview;
	
	private Attribute forum;
	private Attribute fraudDetection;
	private Attribute paymentMethod;
	private Level forumPresent;
	private Level forumAbsent;
	private Level fdPresent;
	private Level fdAbsent;
	private Level creditCard; 
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
        
		Interviewee testuser = Interviewee.find("byName", "TestUser").first();
		interview = Interview.createNewInterview(testuser);
		
		Result result = interview.result;
		utility = new Utility(result);
		
        walkStages walkStages = new walkStages();
        
		LevelRatingStage levelRatingStage = (LevelRatingStage) interview.getStage("AttributeRatingStage");
        AttributeWeightingStage attributeWeightingStage = (AttributeWeightingStage) interview.getStage("StageWeightAttributes");
        
        // first stage
        walkStages.walkLevelRateStage(levelRatingStage);
        // second stage
        walkStages.walkAttributeWeightingStage(attributeWeightingStage);
        
		forum = Attribute.find("byName", "Forum").first();
		forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();
		fraudDetection = Attribute.find("byName", "Payment with Fraud Detection").first();
		fdPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", fraudDetection, "Present").first();
		fdAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", fraudDetection, "Absent").first();
		paymentMethod = Attribute.find("byName", "Payment Method").first();
		creditCard = Level.find("select l from Level l where l.attribute = ? and l.name = ?", paymentMethod, "Credit Card").first(); 
    } 
       	
	
	/*
	 * copied current implementation of utility calculation from pairs utility stage
	 */
    private double calculateUtility(double[] y, double[][] x, double[] x_predict) {
    	OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
    	
    	regression.setNoIntercept(false);
    	regression.newSampleData(y, x);
    	
		double prediction = 0.0d;
		double[] beta = regression.estimateRegressionParameters();

		prediction = beta[0];
		for(int i = 1; i < beta.length; i++){
			prediction += beta[i] * x_predict[i - 1];
		}
		return prediction;
    }
       
    
    @Test
    public void improveUtilityByObservation() {
    	
		double[] y = {1.0, 2.0, 0.0};
		double[][] x = {{1.0, 0.0},{0.0, 1.0},{0.0, 0.0}};
		
		//one observation concerning x1, utility of x1 is 1
		assertEquals(1.0, calculateUtility(y, x, new double[] {1, 0}), 0.001);
		assertEquals(2.0, calculateUtility(y, x, new double[] {0, 1}), 0.001);
		assertEquals(0.0, calculateUtility(y, x, new double[] {0, 0}), 0.001);	
		
		//add second observation, again only concerning x1
		y = new double[] {1.0, 2.0, 2.0};
		x = new double[][] {{1.0, 0.0},{0.0, 1.0},{1.0, 0.0}};	
		
		//new observation with higher rating improves utility of x1
		assertEquals(4.0, calculateUtility(y, x, new double[] {1, 0}), 0.001);
		assertEquals(2.0, calculateUtility(y, x, new double[] {0, 1}), 0.001);
		
		//x2 has higher rating and is preferred to x1
		y = new double[] {1.0, 2.0, -2.0};
		x = new double[][] {{1.0, 0.0},{0.0, 1.0},{1.0, -1.0}};
		
		double utilityX1 = calculateUtility(y, x, new double[] {1, 0});
		double utilityX2 = calculateUtility(y, x, new double[] {0, 1});
		
		assertTrue(utilityX2 > utilityX1);
		
		// introduce new x3 with utility of 1
		y = new double[] {1.0, 2.0, -2.0, 1.0};
		x = new double[][] {{1.0, 0.0, 0.0},
				            {0.0, 1.0, 0.0},
				            {1.0, -1.0, 0.0},
				            {0.0, 0.0, 1.0}};
		assertEquals(1.0, calculateUtility(y, x, new double[] {0, 0, 1}), 0.001);
		
		// add observation showing x2 is preferred to x3
		y = new double[] {1.0, 2.0, -2.0, 1.0, -1.5};
		x = new double[][] {{1.0, 0.0, 0.0},
				            {0.0, 1.0, 0.0},
				            {1.0, -1.0, 0.0}, 
				            {0.0, 0.0, 1.0}, 
				            {0.0, -1.0, 1.0}};
		
		// utility of x2 should not decrease with this observation
		assertTrue("old utility of x2: " + utilityX2 + " new utility: " + calculateUtility(y, x, new double[] {0, 1, 0}), 
				Math.abs(calculateUtility(y, x, new double[] {0, 1, 0}) - utilityX2) <= 0.01);
    }
    
    @Test
    public void testPairSample()
    {
		double[] y = {2.5, -1.0, 1.0, -1.0, 1.0};
		double[][] x = {{1.0, 0.0, 1.0, 0.0},
					    {0.0, 0.0, 0.0, 1.0},
						{0.0, 0.0, 1.0, 0.0},
						{0.0, 1.0, 0.0, 0.0},
						{1.0, 0.0, 0.0, 0.0}};
		
		double OldutilityX1 = calculateUtility(y, x, new double[] {1, 0, 0, 0});
		double OldutilityX3 = calculateUtility(y, x, new double[] {0, 0, 1, 0});
		
		//jlog.log(java.util.logging.Level.INFO, "old utility X1 " + OldutilityX1);
		//jlog.log(java.util.logging.Level.INFO, "old utility X3 " + OldutilityX3);
		
		y = new double[] {2.5, -1.0, 1.0, -1.0, 1.0, 4.0};
		x = new double[][] {{1.0, 0.0, 1.0, 0.0},
					    {0.0, 0.0, 0.0, 1.0},
						{0.0, 0.0, 1.0, 0.0},
						{0.0, 1.0, 0.0, 0.0},
						{1.0, 0.0, 0.0, 0.0},
						{1.0, -1.0, 1.0, -1.0}};		
		
		double utilityX1 = calculateUtility(y, x, new double[] {1, 0, 0, 0});
		double utilityX3 = calculateUtility(y, x, new double[] {0, 0, 1, 0});
		
		//jlog.log(java.util.logging.Level.INFO, "utility X1 " + utilityX1);
		//jlog.log(java.util.logging.Level.INFO, "utility X3 " + utilityX3);	
    }
    
    @Test
    public void testOLSoneObservation()
    {
		double[] y = {4.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		double[][] x = {{1.0, -1.0, 0.0, 1.0, -1.0},
					    {1.0, 0.0, 0.0, 0.0, 0.0},
						{0.0, 1.0, 0.0, 0.0, 0.0},
						{0.0, 0.0, 1.0, 0.0, 0.0},
						{0.0, 0.0, 0.0, 1.0, 0.0},
						{0.0, 0.0, 0.0, 0.0, 1.0}};
		
    	OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
    	
    	regression.setNoIntercept(true);
    	regression.newSampleData(y, x);
    	
		double[] beta = regression.estimateRegressionParameters();
		
		assertEquals(beta[0], 0.8, 0.01);
		assertEquals(beta[1], -0.8, 0.01);
		assertEquals(beta[2], 0.0, 0.01);
		assertEquals(beta[3], 0.8, 0.01);
		assertEquals(beta[4], -0.8, 0.01);
    }
    
    @Test
    public void testOLStwoObservation()
    {
		double[] y = {4.0, -2.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		double[][] x = {{1.0, -1.0, 0.0, 1.0, -1.0},
						{-1.0, 0.0, 1.0, -1.0, 0.0},
					    {1.0, 0.0, 0.0, 0.0, 0.0},
						{0.0, 1.0, 0.0, 0.0, 0.0},
						{0.0, 0.0, 1.0, 0.0, 0.0},
						{0.0, 0.0, 0.0, 1.0, 0.0},
						{0.0, 0.0, 0.0, 0.0, 1.0}};
		
    	OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
    	
    	regression.setNoIntercept(true);
    	regression.newSampleData(y, x);
    	
		double[] beta = regression.estimateRegressionParameters();
		
		assertEquals(beta[0], 0.875, 0.01);
		assertEquals(beta[1], -0.75, 0.01);
		assertEquals(beta[2], -0.125, 0.01);
		assertEquals(beta[3], 0.875, 0.01);	
		assertEquals(beta[4], -0.75, 0.01);
    }    
    
    @Test
    public void testPriorUtilities() throws Exception
    {    			
		assertEquals(1.0, utility.getPriorUtility(forumPresent), 0.001);
		assertEquals(-1.0, utility.getPriorUtility(forumAbsent), 0.001);
    }
    
    @Test
    public void testPairsUtilities() throws Exception
    {
    	PairsUtilityStage pairsUtilityStage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
    	
    	List<Level> lhs = new ArrayList<Level>();
    	lhs.add(fdAbsent);
    	lhs.add(forumAbsent);
    	List<Level> rhs = new ArrayList<Level>();
    	rhs.add(fdPresent);
    	rhs.add(forumPresent);
    	List<Level> cc = new ArrayList<Level>();
    	cc.add(creditCard);
    	
    	pairsUtilityStage.saveNewObservationByLevels(lhs, rhs, 4.0);
    	
    	assertEquals(1.6, utility.getPairsUtilities(rhs), 0.01);
    	assertEquals(-1.6, utility.getPairsUtilities(lhs), 0.01);
    	assertEquals(0.0, utility.getPairsUtilities(cc), 0.01);
    	   	
    	pairsUtilityStage.saveNewObservationByLevels(rhs, cc, -2.0);
    	
    	assertEquals(1.75, utility.getPairsUtilities(rhs), 0.01);
    	assertEquals(-1.5, utility.getPairsUtilities(lhs), 0.01);
    	assertEquals(-0.125, utility.getPairsUtilities(cc), 0.01);
    }
    
    @Test
    public void testFinalUtilities() throws Exception
    {
    	PairsUtilityStage pairsUtilityStage = (PairsUtilityStage) interview.getStage("PairsUtilityStage");
    	
    	List<Level> lhs = new ArrayList<Level>();
    	lhs.add(fdAbsent);
    	lhs.add(forumAbsent);
    	List<Level> rhs = new ArrayList<Level>();
    	rhs.add(fdPresent);
    	rhs.add(forumPresent);
    	List<Level> cc = new ArrayList<Level>();
    	cc.add(creditCard);
    	
    	pairsUtilityStage.saveNewObservationByLevels(lhs, rhs, 4.0);
    	pairsUtilityStage.saveNewObservationByLevels(rhs, cc, -2.0);
    	// @TODO calculate exact values
    	assertTrue(utility.getFinalUtility(forumPresent) > utility.getFinalUtility(forumAbsent));
    }
    

    
    

}
