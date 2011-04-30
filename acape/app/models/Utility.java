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
		
		/*
		 * Slope should not be negative
		 * @TODO handle error
		 */
		if(regression.getSlope() < 0) {
			jlog.log(java.util.logging.Level.INFO,"Faulty Survey");
		}
		
		return regression;		
	}
	
	/*
	 * Calibrated utility can be calculated after concept comparison stage
	 */
	public double computeCalibratedUtilityFor(List<Level> levels) throws Exception 
	{
		// Assure concept comparison stage is finished and there calibration parameters exist
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
	
	public double computeCalibratedUtilityFor(Level level) throws Exception 
	{
		List<Level> levels = new ArrayList<Level>();
		levels.add(level);
		return computeCalibratedUtilityFor(levels);
	}
	/*
	 * Utility based on the level rating and attribute importance
	 */
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
	 * Utility based on the results from pair comparison stage
	 * Is used to selected next pairs in the interview
	 * @TODO Refactor this method!
	 */
	public double getPairsUtilities(List<Level> levels) throws Exception {
		
		RealMatrix m = result.getMatrix();
		// get dependent variables matrix
		m = m.getSubMatrix((int) Level.count(), m.getRowDimension() - 1, 0, m.getColumnDimension() - 1);
		
		double[] emptyRow = new double[m.getColumnDimension()];
		
		// search non empty rows i.e. at least one entry is not zero 
		int nonEmptyRows = 0;
		for(int i = 0; i < m.getRowDimension(); i++) {
			if(!Arrays.equals(emptyRow, m.getRow(i))) {
				nonEmptyRows++;
			}
		}
		
		/*
		 * Create matrix x to store non empty rows and with enough extra rows for identity matrix
		 */
		RealMatrix x = new Array2DRowRealMatrix(nonEmptyRows + m.getColumnDimension() - 1, m.getColumnDimension() - 1);
		
		for(int i = 0; i < nonEmptyRows; i++) {
			double[] newRow = Arrays.copyOf( m.getRow(i), x.getColumnDimension());
			x.setRow(i, newRow);
		}
		
		/*
		 * Add identity matrix to x
		 */
		for(int i = nonEmptyRows; i < nonEmptyRows + m.getColumnDimension() - 1; i++) {
			double[] newRow = new double[m.getColumnDimension() - 1];
			newRow[i - nonEmptyRows] = 1.0;
			x.setRow(i, newRow);
		}
		
		/*
		 * Get dependent variables and store in vector y
		 */
		double[] y = new double[x.getRowDimension()];
		for(int i = 0; i < nonEmptyRows; i++) {
			y[i] = m.getEntry(i, m.getColumnDimension() -1 );
		}
		
		/*
		 * Regression of matrix x and vector y
		 */
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.setNoIntercept(true);
		regression.newSampleData(y, x.getData());
		
		/*
		 * Vector to save regression results
		 */
		double[] utilities = regression.estimateRegressionParameters();
		
		/*
		 * Calculate utility of given levels by adding up the utility of all containing features of the levels
		 */
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
	
	/*
	 * Final utility is the combined utility of prior and pairs utility
	 */
	public double getFinalUtility(List<Level> levels) throws Exception {
		double utility = 0;
		for(Level l : levels) {
			utility += getFinalUtility(l);
		}
		return utility;
		
	}
	
	/*
	 * Final utility is the combined utility of prior and pairs utility
	 */	
	public double getFinalUtility(Level level) throws Exception {
		List<Level> levels = new ArrayList<Level>();
		levels.add(level);
		
		double n = Level.count() - result.excludedLevels.size();
		double t = result.numberOfPairs;		
		
		/*
		 * Get Prior Utility
		 */
		double priorUtility = getPriorUtility(level);
		double pairsUtility = 0;
		
		/*
		 * Get Pairs Utility
		 */
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
		
		/*
		 * Weight prior Utility
		 */
		priorUtility *= n/(n+t);
		
		/*
		 * Final utility is the sum of weightes prior utility and pairs utility
		 */
		return priorUtility + pairsUtility;
	}
	
	
}
