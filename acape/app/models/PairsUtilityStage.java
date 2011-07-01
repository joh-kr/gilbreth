package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math.util.MathUtils;

import play.data.validation.Required;
import play.db.jpa.Model;


/**
 * In that stage, iteratively 2 concepts are compared to to each other.
 * 
 * @author Johannes Müller
 *
 */
public class PairsUtilityStage extends Stage {
	
	private Boolean stageFinished;
	private Utility utility;
	
	public PairsUtilityStage(Interview interview, Result result){
		super(interview, result);
		stageFinished = false;
		this.utility = new Utility(result);
	}
	
	public LevelsPair computeLevelsPair(int numberOfAttributes) throws Exception{
		List<AttributeFrequency> attributeFrequencies = sortAttributes();
		if(attributeFrequencies.size() < numberOfAttributes)
			throw new Exception("More attributes asked than available. Asked " + numberOfAttributes + " Available " + attributeFrequencies.size());
		
		List<List<LevelFrequency>> listOfListOfLevelFrequencies = new ArrayList();
		
		LevelsPair initLevelPair = new LevelsPair(utility);


		// Put the levels of each attribute with the lowest frequency into the lhs
		for(int i = 0; i < numberOfAttributes; i++){
			listOfListOfLevelFrequencies.add(sortLevels(attributeFrequencies.get(i).getAttribute()));
		}
		
		//jlog.log(java.util.logging.Level.INFO, "Construct list of sorted levels with " + listOfListOfLevelFrequencies.size() + " elements.");
		
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
		return result.matrixRowCount >= result.getMatrix().getRowDimension() || 
			   stageFinished || 
			   Math.abs(result.getR2Difference()) < 0.001d;// Calc of R2 seems to be broken
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
		
		UsedLevelPair usedPair = new UsedLevelPair(lhsIds, rhsIds);
		usedPair.save();
		result.usedLevelPairs.add(usedPair);
		result.numberOfPairs++;
		
		result.addNewRow(x);
		
		//jlog.log(java.util.logging.Level.INFO, "Add new Row" + Arrays.toString(x));
		
		result.setR2(calculateR2());
		
		result.save();
	}
	
	/**
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
	
	private Boolean observationExists(LevelsPair lp) throws Exception {
		
		Boolean observationExists = false;
		
		for(UsedLevelPair usedPair : result.usedLevelPairs) {
			if(usedPair.equalsLevelsPair(lp)) {
				observationExists = true;
			}
		}
		
		return observationExists;
	}
	
	/**
	 * Calculate R2 as a measure of the difference between expected results and
	 * the actual responses
	 * @return r2 value
	 */
	private double calculateR2(){
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		
		double[] y = result.getDependentVariableValues();
		double[][] x = result.getIndeptendentVariableValues();

		regression.newSampleData(y, x);
		
		return regression.calculateRSquared();
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
	

}
