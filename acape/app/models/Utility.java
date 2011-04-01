package models;

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
		
		regression.setNoIntercept(true);
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
	
	private double predict(OLSMultipleLinearRegression regression, double[] x){
		if(regression == null) {
			throw new IllegalArgumentException("Parameter regression must not be null");
		}
		double prediction = 0.0d;
		double[] beta = regression.estimateRegressionParameters();
		if(beta.length != x.length) 
			throw new IllegalArgumentException("The number of predictor variables is different to the number of regression parameters. " + x.length + "!=" + beta.length);
		
		for(int i = 0; i < beta.length; i++){
			prediction += beta[i] * x[i];
		}
		
		
		return prediction;
	}
}
