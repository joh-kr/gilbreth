package models;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;

public class Utility {
	
	private Result result;
	
	public Utility(Result result) {
		this.result = result;
	}
	
	/*
	 * Use regression to calculate utility
	 */
	public double computeUtilityFor(List<Level> levels) throws Exception{		
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		
		double[] y = result.getDependentVariableValues();
		double[][] x = result.getIndeptendentVariableValues();
		
		// consider an intercept in the regression
		regression.setNoIntercept(false);
		regression.newSampleData(y, x);
		
		// jlog.log(java.util.logging.Level.INFO, "Transformed Matrix before regression: ");
		
		// Without dependent variable
		double[] x_predict = new double[result.getNrOfColumns() - 1];
		Integer[] columns;
		
		for(Level l : levels){
			columns = result.getColumnsFor(l);
			for(int i : columns){
				x_predict[i] = 1.0d;
			}
		}
		
		return predict(regression, x_predict);
	}
	
	public double computeUtilityFor(Level level) throws Exception {
		List<Level> levels = new ArrayList<Level>();
		levels.add(level);
		return computeUtilityFor(levels);
	}
	
	private double predict(OLSMultipleLinearRegression regression, double[] x){
		if(regression == null) {
			throw new IllegalArgumentException("Parameter regression must not be null");
		}
		double prediction = 0.0d;
		double[] beta = regression.estimateRegressionParameters();
		
		// intercept at beta[0]
		prediction = beta[0];
		for(int i = 1; i < beta.length; i++){
			prediction += beta[i] * x[i - 1];
		}
		
		return prediction;
	}
	
	
}
