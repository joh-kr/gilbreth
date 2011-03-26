import java.util.logging.Logger;

import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.junit.*;

import play.test.*;

public class TestUtilityCalculations extends UnitTest {
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
    
	/*
	 * copied current implementation of utility calculation from pairs utility stage
	 */
    private double calculateUtility(double[] y, double[][] x, int searchedIndex) {
    	OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
    	
    	regression.setNoIntercept(true);
    	regression.newSampleData(y, x);
    	
    	double[] x_predict = new double[x[0].length];
    	for(int i = 0; i < x_predict.length; i++) {
    		if(i == searchedIndex) {
    			x_predict[i] = 1.0;
    		} else {
    			x_predict[i] = 0.0;
    		}
    	}
    	
		double prediction = 0.0d;
		double[] beta = regression.estimateRegressionParameters();

		for(int i = 0; i < beta.length; i++){
			prediction += beta[i] * x_predict[i];
		}
		return prediction;
    }
    
    @Test
    public void improveUtilityByObservation() {
    	
		double[] y = {1.0, 2.0, 0.0};
		double[][] x = {{1.0, 0.0},{0.0, 1.0},{0.0, 0.0}};
		
		//one observation concerning x1, utility of x1 is 1
		assertEquals(1.0, calculateUtility(y, x, 0), 0.001);
		assertEquals(2.0, calculateUtility(y, x, 1), 0.001);
		
		//add second observation, again only concerning x1
		y = new double[] {1.0, 2.0, 2.0};
		x = new double[][] {{1.0, 0.0},{0.0, 1.0},{1.0, 0.0}};
		
		//new observation with higher rating improves utility of x1
		assertEquals(1.5, calculateUtility(y, x, 0), 0.001);
		assertEquals(2.0, calculateUtility(y, x, 1), 0.001);
		
		//x2 has higher rating and is preferred to x1
		y = new double[] {1.0, 2.0, -2.0};
		x = new double[][] {{1.0, 0.0},{0.0, 1.0},{1.0, -1.0}};
		
		double utilityX1 = calculateUtility(y, x, 0);
		double utilityX2 = calculateUtility(y, x, 1);
		
		assertTrue(utilityX2 > utilityX1);
		
		// introduce new x3 with utility of 1
		y = new double[] {1.0, 2.0, -2.0, 1.0};
		x = new double[][] {{1.0, 0.0, 0.0},
				            {0.0, 1.0, 0.0},
				            {1.0, -1.0, 0.0},
				            {0.0, 0.0, 1.0}};
		assertEquals(1.0, calculateUtility(y, x, 2), 0.001);
		
		// add observation showing x2 is preferred to x3
		y = new double[] {1.0, 2.0, -2.0, 1.0, -1.5};
		x = new double[][] {{1.0, 0.0, 0.0},
				            {0.0, 1.0, 0.0},
				            {1.0, -1.0, 0.0}, 
				            {0.0, 0.0, 1.0}, 
				            {0.0, -1.0, 1.0}};
		
		// utility of x2 should not decrease with this observation
		// fails
		assertTrue("old utility of x2: " + utilityX2 + " new utility: " + calculateUtility(y, x, 1), 
				utilityX2 <= calculateUtility(y, x, 1));
		
    }
}
