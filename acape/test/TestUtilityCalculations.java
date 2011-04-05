import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.junit.*;

import play.test.*;

public class TestUtilityCalculations extends UnitTest {
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
    
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
}
