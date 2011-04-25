package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math.stat.regression.SimpleRegression;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Utility {
	
	private Result result;
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
	public Utility(Result result) {
		this.result = result;
	}
	
	public SimpleRegression getCalibratedUtilitiesRegression(List<Double> finalUtilities, List<Integer> buyingProbabilities)
	{
		SimpleRegression regression = new SimpleRegression();
		
		double p;
		double x;
		double y;
		
		for(int i = 0; i < buyingProbabilities.size(); i++) {
			p = buyingProbabilities.get(i);
			
			// trim values as given in:
			// Sawtooth Software, The ACA/Web v6.0 Technical Paper, 2007
			p = Math.min(95, p);
			p = Math.max(5, p);
			
			x = finalUtilities.get(i);
			y = Math.log(p / (100 - p));
			
			regression.addData(x, y);
		}
		
		if(regression.getSlope() < 0) {
			jlog.log(java.util.logging.Level.INFO,"Faulty Survey");
		}
		
		return regression;		
	}
	
	public double computeCalibratedUtilityFor(List<Level> levels) throws Exception 
	{
		if(Double.isNaN(result.calibratedUtilityIntercept) || Double.isNaN(result.calibratedUtilitySlope)) {
			throw new Exception("Utility not calibrated");
		}
		
		double utilities[] = new double[levels.size()];
		Level level;
		for(int i = 0; i < levels.size(); i++) {
			level = levels.get(i);
			utilities[i] = getFinalUtility(level) * result.calibratedUtilitySlope;
			utilities[i] += result.calibratedUtilityIntercept / level.attribute.levels.size();
		}

		double calibratedUtility = 0;
		for(double u : utilities) {
			calibratedUtility += u;
		}
		
		return calibratedUtility;
	}
	
	public double getPriorUtility(Level level) throws Exception {
		Integer[] columns;
		columns = result.getColumnsFor(level);
		
		double[] searchedRow = new double[result.getNrOfColumns() - 1];
		for(int i : columns){
			searchedRow[i] = 1.0d;
		}
		
		RealMatrix m = result.getMatrix();
		m = m.getSubMatrix(0, (int) Level.count(), 0, m.getColumnDimension() - 2);
		
		int i = 0;
		
		while(!Arrays.equals(m.getRow(i), searchedRow)) {
			i++;
		}
		
		return result.getDependentVariableValues()[i];
	}
	
	/*
	 * @TODO refactor this method!
	 */
	public double getPairsUtilities(List<Level> levels) throws Exception {
		
		RealMatrix m = result.getMatrix();
		m = m.getSubMatrix((int) Level.count(), m.getRowDimension() - 1, 0, m.getColumnDimension() - 1);
		
		double[] emptyRow = new double[m.getColumnDimension()];
		
		int nonEmptyRows = 0;
		for(int i = 0; i < m.getRowDimension(); i++) {
			if(!Arrays.equals(emptyRow, m.getRow(i))) {
				nonEmptyRows++;
			}
		}
				
		RealMatrix x = new Array2DRowRealMatrix(nonEmptyRows + m.getColumnDimension() - 1, m.getColumnDimension() - 1);
		
		for(int i = 0; i < nonEmptyRows; i++) {
			double[] newRow = Arrays.copyOf( m.getRow(i), x.getColumnDimension());
			x.setRow(i, newRow);
		}
		
		for(int i = nonEmptyRows; i < nonEmptyRows + m.getColumnDimension() - 1; i++) {
			double[] newRow = new double[m.getColumnDimension() - 1];
			newRow[i - nonEmptyRows] = 1.0;
			x.setRow(i, newRow);
		}
		
		double[] y = new double[x.getRowDimension()];
		for(int i = 0; i < nonEmptyRows; i++) {
			y[i] = m.getEntry(i, m.getColumnDimension() -1 );
		}
		
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.setNoIntercept(true);
		regression.newSampleData(y, x.getData());
		
		double[] utilities = regression.estimateRegressionParameters();
		
		double utility = 0;
		Integer[] columns;
		for(Level l : levels) {
			columns = result.getColumnsFor(l);
			for(int i : columns) {
				utility += utilities[i];
			}
		}
		
		return utility;	
	}
	
	public double getFinalUtility(List<Level> levels) throws Exception {
		double utility = 0;
		for(Level l : levels) {
			utility += getFinalUtility(l);
		}
		return utility;
		
	}
	
	public double getFinalUtility(Level level) throws Exception {
		List<Level> levels = new ArrayList<Level>();
		levels.add(level);
		
		double n = Level.count() - result.excludedLevels.size();
		double t = result.numberOfPairs;		
		
		double priorUtility = getPriorUtility(level);
		double pairsUtility = 0;
		
		boolean levelUsedInPairs = false;
		List<UsedLevelPair> usedPairs = UsedLevelPair.findAll();
		for(UsedLevelPair pair : usedPairs) {
			if(pair.includesLevel(level)) {
				levelUsedInPairs = true;
			}
		}
		
		if(t > 0) {
			pairsUtility = getPairsUtilities(levels);
			if(levelUsedInPairs) {
				pairsUtility *= t/(n+t);
			}
		}
		
		priorUtility *= n/(n+t);
		
		
		return priorUtility + pairsUtility;
	}
	
	
}
