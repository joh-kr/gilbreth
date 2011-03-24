package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math.util.MathUtils;



import sun.security.krb5.RealmException;

/**
 * In that stage, iteratively 2 concepts are compared to to each other.
 * 
 * @author Johannes Müller
 *
 */
public class PairsUtilityStage extends Stage {
	
	private Boolean stageFinished;
	
	public PairsUtilityStage(Interview interview, Result result){
		super(interview, result);
		stageFinished = false;
	}
	
	public LevelsPair computeLevelsPair(int numberOfAttributes) throws Exception{
		List<AttributeFrequency> attributeFrequencies = sortAttributes();
		if(attributeFrequencies.size() < numberOfAttributes)
			throw new Exception("More attributes asked than available. Asked " + numberOfAttributes + " Available " + attributeFrequencies.size());
		
		List<List<LevelFrequency>> listOfListOfLevelFrequencies = new ArrayList();
		
		LevelsPair initLevelPair = new LevelsPair();


		// Put the levels of each attribute with the lowest frequency into the lhs
		for(int i = 0; i < numberOfAttributes; i++){
			listOfListOfLevelFrequencies.add(sortLevels(attributeFrequencies.get(i).getAttribute()));
		}
		
		jlog.log(java.util.logging.Level.INFO, "Construct list of sorted levels with " + listOfListOfLevelFrequencies.size() + " elements.");
		
		for(List<LevelFrequency> lf : listOfListOfLevelFrequencies){
			initLevelPair.addToLHS(lf.get(0).getLevel(), lf.get(lf.size() <= 1 ? 0 : 1).getLevel());
		}
		
		// now we have the levels less often used in a concept
		// next we try to find a lhs and rhs with utility as equal as possible
		List<LevelsPair> levelsPairs = new ArrayList();
		levelsPairs.add(initLevelPair);
		
		LevelsPair lp = null;
		
		for(int i = 0; i < initLevelPair.size(); i++){
			for(int j = i; j < initLevelPair.size(); j++){
				lp = new LevelsPair(levelsPairs.get(i));
				lp.swapLevels(j);
				lp.calculateUtilities();
				levelsPairs.add(lp);
			}
		}
		
		Collections.sort(levelsPairs);
		
		
		// find a corresponding rhs with a utility similar to the utility of rhs
		// Begin to start calculating the utility if you have enough degrees of freedom
		
		Boolean newPairFound = false;
		int i = 0;
		
		while(i < levelsPairs.size() && !newPairFound) {
			if(!observationExists(levelsPairs.get(i))) {
				newPairFound = true;
			} else {
				i++;
			}
		}
		
		if(newPairFound) {
			return levelsPairs.get(i);
		} else {
			stageFinished = true;
			return null;
		}

	}
	
	/**
	 * 
	 * @return the attributes in an ascending order, ordered according to their frequencies
	 * in the matrix
	 * @throws Exception
	 */
	public List<AttributeFrequency> sortAttributes() throws Exception{
		List<AttributeFrequency> frequencies = new ArrayList<AttributeFrequency>();
		List<Attribute> attributes = Attribute.findAll();
		AttributeFrequency frequency;
		for(Attribute attribute : attributes){
			frequency = new AttributeFrequency();
			frequency.setAttribute(attribute);
			frequency.setFrequency(result.getAttributeFrequency(attribute));
			frequencies.add(frequency);
		}
		// Sorts in ascending order
		Collections.sort(frequencies);
		return frequencies;
	}
	
	
	public List<LevelFrequency> sortLevels(Attribute attribute) throws Exception{
		List<LevelFrequency> levelFrequencies = new ArrayList();
		LevelFrequency frequency = null;

		for(Level l : attribute.getLevels(result.excludedLevels)){
			frequency = new LevelFrequency();
			frequency.setFrequency(result.getLevelFrequency(l));
			frequency.setLevel(l);
			levelFrequencies.add(frequency);
		}
		Collections.sort(levelFrequencies);

		return levelFrequencies;
	}
	
	public boolean isFinished(){
		return result.rowCount >= result.getMatrix().getRowDimension() || stageFinished;// || result.getR2Difference() < 0.0001d;// Calc of R2 seems to be broken
	}
	
	// data manipulation section
	
	/** 
	 * add new row for each pair comparison
	 * element  0 for a level not part of the comparison
	 * element -1 for a level on the left side
	 * element  1 for a level on the right side
	 */
	public void saveNewObservation(List<Long> lhsIds, List<Long> rhsIds, double preference) throws Exception{
		double[] x = buildObservationRow(lhsIds, rhsIds, preference);
		
		result.addNewRow(x);
		
		result.setR2(calculateR2());
	}
	
	/*
	 * Wrapper for function saveNewObservation accepting lists of levels instead of level IDs
	 */
	public void saveNewObservationByLevels(List<Level> lhs, List<Level> rhs, double preference) throws Exception {
		List<Long> lhsIds = new ArrayList<Long>();
		List<Long> rhsIds = new ArrayList<Long>();
		for(Level l : lhs) {
			lhsIds.add(l.id);
		}
		for(Level l : rhs) {
			rhsIds.add(l.id);
		}
		saveNewObservation(lhsIds, rhsIds, preference);
	}

	private double[] buildObservationRow(List<Long> lhsIds, List<Long> rhsIds,
			double preference) throws Exception {
		double[] x = new double[result.getNrOfColumns()];
		populate(x, lhsIds, -1.0d);
		populate(x, rhsIds,  1.0d);
		x[x.length - 1] = preference;
		return x;
	}
	
	private double[] buildObservationRowFromLevels(List<Level> lhs, List<Level> rhs, double preference) throws Exception {
		List<Long> lhsIds = new ArrayList<Long>();
		List<Long> rhsIds = new ArrayList<Long>();
		//create list of Level IDs
		// use to loops in case lhs and rhs are of differenz size
		for(int i = 0; i < lhs.size(); i++) {
			lhsIds.add(lhs.get(i).id);
		}
		for(int i = 0; i < rhs.size(); i++) {
			rhsIds.add(rhs.get(i).id);
		}
		return buildObservationRow(lhsIds, rhsIds, preference);
	}
	
	private Boolean observationExists(LevelsPair lp) throws Exception {
		//build row with dummy preference
		RealMatrix m = result.getMatrix();
		//sub matrix without preference in last column
		m = m.getSubMatrix(0, m.getRowDimension() - 1, 0, m.getColumnDimension() - 2);
		double[] observationRow = buildObservationRowFromLevels(lp.getLHS(), lp.getRHS(), 0.0);
		double[] compareRow = new double[observationRow.length - 1];
		
		//also compare if lhs and rhs are switched
		double[] observationRowSwitched = buildObservationRowFromLevels(lp.getRHS(), lp.getLHS(),0.0);
		double[] compareRowSwitched = new double[observationRowSwitched.length - 1];
		
		System.arraycopy(observationRow, 0, compareRow, 0, observationRow.length - 1);
		System.arraycopy(observationRowSwitched, 0, compareRowSwitched, 0, observationRowSwitched.length - 1);
		
		Boolean observationExists = false;
		for(int i = 0; i < m.getRowDimension(); i++) {
			if(Arrays.equals(compareRow, m.getRow(i)) || Arrays.equals(compareRowSwitched, m.getRow(i))) {
				observationExists = true;
			}
		}
		return observationExists;
	}
	
	private class MyOLSMultipleLinearRegression extends OLSMultipleLinearRegression{
		public double calculateYVariance(){
			//return super.calculateYVariance();
			return 1;
		}
	}
	
	/**
	 * Calculate R2 as a measure of the difference between expected results and
	 * the actual responses
	 * @return r2 value
	 */
	public double calculateR2(){
		
		MyOLSMultipleLinearRegression regression = new MyOLSMultipleLinearRegression();
		
		double[] y = result.getDependentVariableValues();
		double[][] x = result.getIndeptendentVariableValues();
		
		//@TODO
		//regression.newSampleData(y, x);
		
		//double y_var = regression.estimateRegressandVariance();
		//double y_var = regression.calculateYVariance();
		//double res_var = StatUtils.variance(regression.estimateResiduals());
		
		//double r2 = 1 - res_var/y_var;
		//jlog.log(java.util.logging.Level.INFO, "R2 is calculated as " + r2 + " with var y: " + y_var + " and var residuals " + res_var);
		//@TODO
		//return r2;
		return 1;
	}
	
	private void populate(double[] x, List<Long> list, double factor) throws Exception{
		Integer[] columns;
		Level level;
		for(Long id : list){
			level = Level.findById(id);
			columns = result.getColumnsFor(level);
			for(int i : columns){
				x[i] = factor;
			}
		}
	}
	
	
	// Nested Classes section
	
	public class AttributeFrequency implements Comparable{
		Attribute attribute;
		int frequency;
		
		public Attribute getAttribute() {
			return attribute;
		}
		public void setAttribute(Attribute attribute) {
			this.attribute = attribute;
		}
		public int getFrequency() {
			return frequency;
		}
		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}
		public int compareTo(Object o) {
			if(o instanceof AttributeFrequency){
				AttributeFrequency af = (AttributeFrequency) o;
				return new Integer(this.frequency).compareTo(af.frequency);
			}
			return 0;
		}
		
	}
	
	public class LevelFrequency  implements Comparable{
		public Level getLevel() {
			return level;
		}

		public void setLevel(Level level) {
			this.level = level;
		}

		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		Level level;
		int frequency;
		
		public int compareTo(Object o) {
			if(o instanceof LevelFrequency){
				LevelFrequency af = (LevelFrequency) o;
				return new Integer(this.frequency).compareTo(af.frequency);
			}
			return 0;
		}
		
	}
	
	public class LevelsPair implements Comparable{
		private List<Level> lhs;
		private List<Level> rhs;
		private double utility_lhs;
		private double utility_rhs;
		
		public LevelsPair(){
			lhs = new ArrayList();
			rhs = new ArrayList();
		}
		
		public LevelsPair(LevelsPair levelsPair){
			this();
			for(int i = 0; i < levelsPair.size(); i++){
				this.lhs.add(levelsPair.getLHS().get(i)); 
				this.rhs.add(levelsPair.getRHS().get(i)); 
			}
			jlog.log(java.util.logging.Level.INFO, "Created a LevelsPair with size " + lhs.size());
		}
		
		public List<Level> getLHS(){
			return lhs;
		}
		
		public List<Level> getRHS(){
			return rhs;
		}
		
		public void addToLHS(Level lhs, Level rhs){
			this.lhs.add(lhs);
			this.rhs.add(rhs);
		}
		
		
		public int size(){
			return lhs.size();
		}
		
		public void swapLevels(int index){
			if(index >= lhs.size())
				throw new IllegalArgumentException("The index is out of range. Index is " + index + " but range is 0 - " + (lhs.size() - 1));
			jlog.log(java.util.logging.Level.INFO, "Swap element at " + index);
			jlog.log(java.util.logging.Level.INFO, "Size of levels is" + lhs.size());
			
			Level swap;
			swap = lhs.get(index);
			lhs.set(index, rhs.get(index));
			rhs.set(index, swap);
		}
		
		
		public void calculateUtilities() throws Exception{
			utility_lhs = computeUtilityFor(lhs);
			utility_rhs = computeUtilityFor(rhs);
		}
		
		private double computeUtilityFor(List<Level> levels) throws Exception{		
			OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
			
			double[] y = result.getDependentVariableValues();
			double[][] x = result.getIndeptendentVariableValues();
			
			regression.newSampleData(y, x);
			
			jlog.log(java.util.logging.Level.INFO, "Transformed Matrix before regression: ");
			
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
		
		public double getUtilityForLhs()
		{
			return utility_lhs;
		}
		public double getUtilityForRhs()
		{
			return utility_rhs;
		}
		
		public double getUtilityDifference(){
			return Math.abs(utility_lhs - utility_rhs);
		}

		public int compareTo(Object o) {
			if(o instanceof LevelsPair){
				LevelsPair af = (LevelsPair) o;
				return new Double(this.getUtilityDifference()).compareTo(af.getUtilityDifference());
			}
			return 0;
		}
		
		
	}
}
