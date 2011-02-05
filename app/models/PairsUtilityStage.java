package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math.util.MathUtils;

/**
 * In that stage, iteratively 2 concepts are compared to to each other.
 * 
 * @author Johannes Müller
 *
 */
public class PairsUtilityStage extends Stage {

	public PairsUtilityStage(Interview interview, Result result){
		super(interview, result);
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
				levelsPairs.add(lp);
			}
		}
		
		Collections.sort(levelsPairs);
		
		
		// find a corresponding rhs with a utility similar to the utility of rhs
		// Begin to start calculating the utility if you have enough degrees of freedom

		
		return levelsPairs.get(0);
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

		for(Level l : attribute.getLevels()){
			frequency = new LevelFrequency();
			frequency.setFrequency(result.getLevelFrequency(l));
			frequency.setLevel(l);
			levelFrequencies.add(frequency);
		}
		Collections.sort(levelFrequencies);

		return levelFrequencies;
	}
	
	public boolean isFinished(){
		return result.rowCount >= result.getMatrix().getRowDimension();// || result.getR2Difference() < 0.0001d;// Calc of R2 seems to be broken
	}
	
	// data manipulation section
	
	public void saveNewObservation(List<Long> lhsIds, List<Long> rhsIds, double preference) throws Exception{
		double[] x = new double[result.getNrOfColumns()];
		populate(x, lhsIds, -1.0d);
		populate(x, rhsIds,  1.0d);
		x[x.length - 1] = preference;
		
		result.addNewRow(x);
		result.setR2(calculateR2());
		
	}
	
	private class MyOLSMultipleLinearRegression extends OLSMultipleLinearRegression{
		public double calculateYVariance(){
			return super.calculateYVariance();
		}
	}
	
	public double calculateR2(){
		
		MyOLSMultipleLinearRegression regression = new MyOLSMultipleLinearRegression();
		
		double[] y = result.getDependentVariableValues();
		double[][] x = result.getIndeptendentVariableValues();
		
		regression.newSampleData(y, x);
		
		//double y_var = regression.estimateRegressandVariance();
		double y_var = regression.calculateYVariance();
		double res_var = StatUtils.variance(regression.estimateResiduals());
		
		double r2 = 1 - res_var/y_var;
		jlog.log(java.util.logging.Level.INFO, "R2 is calculated as " + r2 + " with var y: " + y_var + " and var residuals " + res_var);
		return r2;
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
			double prediction = 0.0d;
			double[] beta = regression.estimateRegressionParameters();
			if(beta.length != x.length) 
				throw new IllegalArgumentException("The number of predictor variables is different to the number of regression parameters. " + x.length + "!=" + beta.length);
			
			for(int i = 0; i < beta.length; i++){
				prediction += beta[i] * x[i];
			}
			
			return prediction;
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
