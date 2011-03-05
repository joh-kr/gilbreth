package models;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.regression.MultipleLinearRegression;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;

import play.data.validation.Required;
import play.db.jpa.Model;

import Jama.Matrix;

/**
 * Respondent is the main class in the data model and manages the survey proceeding, collects the data
 * and facades the statistical calculations.
 * 
 * @author jo
 */
@Entity
public class Respondent extends Model{
	
	/**
	 * The levels explicitly excluded in the first stage
	 */
    @ManyToMany
    public List<Level> excludedLevels;

    /**
     * The pairing frequency of the attributes
     * Required to calculate the next product in the concept comparison
     */
    @OneToMany(mappedBy="respondent", cascade=CascadeType.ALL)
    public List<AttributePairingFrequency> attributePairingFrequencies;
    
    /**
     * Saves the frequencies of the pairing of levels
     */
    @OneToMany(mappedBy="respondent", cascade=CascadeType.ALL)
    public List<LevelPairingFrequency> levelPairingFrequencies;
    
    @OneToMany(mappedBy="respondent", cascade=CascadeType.ALL)
    public List<LevelRate> levelRates;
    
    @Lob
    public byte[] matrix;
    
    
    public int comparisonCount;
     
  
    public Respondent(){
    	excludedLevels = new ArrayList<Level>();
    	levelRates     = new ArrayList<LevelRate>();
    	attributePairingFrequencies = new ArrayList<AttributePairingFrequency>();
    	levelPairingFrequencies = new ArrayList<LevelPairingFrequency>();
    	comparisonCount = 0;
    }
    
    public Respondent addExcludedLevel(Level level){
    	excludedLevels.add(level);
    	this.save();
    	return this;
    }
    
    
    public Respondent addLevelRate(Level level, double rate){	
    	LevelRate levelRate = level.getRatingOf(this);
    	if(levelRate == null)
    		levelRate = new LevelRate(this, level, rate);
    	levelRate.rate = rate;
    	levelRate.save();
    	this.levelRates.add(levelRate);
    	this.save();
        return this;
    }
    
 
    
    public void normalizeAndUnitizeLevelUtilityOf(Attribute attribute){
    	// http://www.wirtschaftsinformatik-24.de/statistik/aritmetische-mittel-varianz-standardabweichung.php
    	
    	double sumAvg = 0.0d;
    	int countAvg = 0;
    	
    	for(Level level : attribute.levels){
    		sumAvg += level.getRatingOf(this).rate;
    		countAvg++;
    	}
    	double avg = sumAvg / countAvg;
    	
    	double sumVar = 0.0d;
    	int countVar = 0;
    	for(Level level : attribute.levels){
    		sumVar += Math.pow(level.getRatingOf(this).rate - avg, 2);
    		countVar++;
    	}    	
    	
    	double variance = sumVar / countVar;
    	double standardDev = Math.sqrt(variance);
    	
    	
    	LevelRate rate;
    	for(Level level : attribute.levels){
    		rate = level.getRatingOf(this);
    		rate.rate = (rate.rate - avg) / standardDev;// todo: check if problematic can be not correct if worst is not 0 1?)
    		rate.save();
    	}
    }
    
    public void weightLevelUtilityOf(Attribute attribute, Double weight){
    	
    	// assure that weight is scaled to 1 - 4

		// use the initial importance rating as a first part worth
		// Check literature whether approach is appropriate
		
		// Normalize Attribute Importance to 1 - 4 
		// Use ai to weight attribute levels (level * attribute importance)
		// Save it as levelRate
    	
    	LevelRate rate;
    	for(Level level : attribute.levels){
    		rate = level.getRatingOf(this);
    		rate.rate = rate.rate * weight;
    		rate.save();
    	}
    }
    
	public Level getHighestRatedLevelOf(Attribute attribute){
		
		Level best = null;
		// check if there are already some rated levels for the respondent
		List<Level> levels = Level
				.find("SELECT l "
						+ "FROM Respondent r JOIN r.levelRates lr JOIN lr.level l JOIN l.attribute a 	"
						+ "WHERE r = ?1 AND a = ?2",
						this, attribute).fetch(); // AND lr.rate >= (SELECT MAX(lr.rate) FROM lr)
		
		if (levels != null && levels.size() > 0) {
			best = levels.get(0);
		}
		
		for(int i = 0; levels != null && i < levels.size(); i++){
			if(best.getRatingOf(this).rate < levels.get(i).getRatingOf(this).rate)
				best = levels.get(i);
		}
		
		
		return best;
	}
	
	public Level getLowestRatedLevelOf(Attribute attribute){
		
		Level worst = null;
		
		List<Level> levels = Level
		.find("SELECT l "
				+ "FROM Respondent r JOIN r.levelRates lr JOIN lr.level l JOIN l.attribute a 	"
				+ "WHERE r = ?1 AND a = ?2",
				this, attribute).fetch(); // AND lr.rate <= (SELECT MIN(lr.rate) FROM lr)
		
		if(levels != null && levels.size() > 0) {
			worst = levels.get(0);
		}
		
		for(int i = 0; levels != null && i < levels.size(); i++){
			if(worst.getRatingOf(this).rate > levels.get(i).getRatingOf(this).rate)
				worst = levels.get(i);
		}

		
		return worst;
	}
	
	
	
	public RealMatrix createInitialUtilitiesMatrix(){
		
		RealMatrix m;

		int initialRows = this.levelRates.size();
		// The suggestion is to ask 3*(N - n - 1) -N pairs S. 73 Breidert
		// Move to an extra method
		int rows        = (3*(this.levelRates.size() - Attribute.all().fetch().size() - 1))	 - this.levelRates.size();
		if(rows <= 0) rows = this.levelRates.size();
		rows += initialRows;
		int columns     = this.levelRates.size() + 1; // independent variables plus result
		
		m = createMatrix(rows, columns);
		
		for(int i = 0; i < initialRows; i++){
			for(int j = 0; j < columns - 1; j++){
				if( i == j)
					m.addToEntry(i, j, 1.0d);
				else
					m.addToEntry(i, j, 0.0d);
			}
			m.addToEntry(i, columns - 1, this.levelRates.get(i).rate); // The result is the prior utility	
		}
		this.comparisonCount = initialRows - 1;
		
		
		this.matrix = serializeMatrix(m);
		
		return m;
	}
	
	
	public RealMatrix createMatrix(int rows, int columns){
		// Check whether rows and columns > 0
		RealMatrix m = new Array2DRowRealMatrix(rows, columns);
		
		return m;
	}
	
	private byte[] serializeMatrix(RealMatrix matrix){
		byte[] buf = null;
		try {
		    // Serialize to a byte array
		    ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
		    ObjectOutputStream out = new ObjectOutputStream(bos) ;
		    out.writeObject(matrix);
		    out.close();

		    // Get the bytes of the serialized object
		    buf = bos.toByteArray();
		  
		} catch (IOException e) {
			//Err
		}
		 return buf;
	}
	
	private RealMatrix deserializeMatrix(byte[] serializedMatrix){
		//http://www.exampledepot.com/egs/java.io/DeserializeObj.html
		RealMatrix matrix = null;
		try {
		    // Deserialize from a byte array
		    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(serializedMatrix));
		    matrix = (RealMatrix) in.readObject();
		    in.close();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		return matrix;
	}
	
	public List<Attribute> determineAttributePairs(int nrOfAttributes){
		List<Attribute> allAttributes = Attribute.all().fetch();
		List<Attribute> selectedAttributes = new ArrayList<Attribute>();
		
		selectedAttributes.add(assembleAttributeList(allAttributes, allAttributes, Long.MAX_VALUE));
		
		// Check if the number of attributes in the db is enough 
		
		for(int i = 0; i < nrOfAttributes - 1; i++){
			selectedAttributes.add(assembleAttributeList(selectedAttributes, allAttributes, Long.MAX_VALUE));
		}
		
		return selectedAttributes;
	}
	
	public void setPairCompairisonResult(List<Level> leftLevels, List<Level> rightLevels, double rate){
		RealMatrix m = deserializeMatrix(matrix);
		
		for(int i = 0; i < this.levelRates.size(); i++){
			for(int j = 0; j < leftLevels.size(); j++){
				if(leftLevels.get(j).equals(this.levelRates.get(i).level))
				{
					m.setEntry(this.comparisonCount, i, -1);
				}
				if(rightLevels.get(j).equals(this.levelRates.get(i).level))
				{
					m.setEntry(this.comparisonCount, i, 1);
				}
			}
		}
		m.setEntry(this.comparisonCount, m.getColumnDimension() - 1, rate);
		matrix = serializeMatrix(m);
		this.comparisonCount++;
		this.save();
	}
	
	public void updateLevelUtilities(){
		RealMatrix m = deserializeMatrix(matrix);
		RealMatrix subM;
		
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		
		double[] y = m.getColumn(m.getColumnDimension() - 1);
		subM = m.getSubMatrix(0, m.getRowDimension() - 1, 0, m.getColumnDimension() - 2); // without y
		double[][] x = subM.getData();
		regression.newSampleData(y, x);
		
		double[] beta = regression.estimateRegressionParameters(); 
	
		
		// Ceck whether levelRates and beta equal in length
		for(int i = 0; i < beta.length; i++){
			System.out.println("levelRate: " + this.levelRates.get(i));
			System.out.println("New Beta: " + beta[i]);
			this.levelRates.get(i).updateLevelRate(beta[i]);
			System.out.println();
		}
		
	}
	
	public boolean pairComparisonFinished(){
		// either the number of comparisons are reached or the goodness of fit (R^2) is sufficiently enough
		// Compare increase of R^2 Breidert S. 72
		return this.comparisonCount == deserializeMatrix(matrix).getRowDimension() - 1;
	}
	
	private Attribute assembleAttributeList(List<Attribute> list1, List<Attribute> list2, long frequency){
		Attribute[] candidatePair = new Attribute[2];
		for(Attribute lhs : list1){
			candidatePair[0] = lhs;
			for(Attribute rhs : list2){
				if(!lhs.equals(rhs) && AttributePairingFrequency.getPairingFrequencyFor(this, lhs, rhs) < frequency){
					frequency =  AttributePairingFrequency.getPairingFrequencyFor(this, lhs, rhs);
					candidatePair[1] = rhs;
				
				}
			}
		}
		AttributePairingFrequency.setFrequencyFor(this, candidatePair[0], candidatePair[1], ++frequency);
		
		return candidatePair[1];
	}
	
	public List<Level[]> determineLevelPairs(List<Attribute> attributeList){
		
		// incorporate the utility measure of different concpets Breidert S. 72
		List<Level[]> l = new ArrayList<Level[]>();
		Level[] candidate;
		long frequency;
		for(Attribute a : attributeList){
			frequency = Long.MAX_VALUE;
			candidate = new Level[2];
			for(Level lhs : a.levels){
				for(Level rhs : a.levels){
					if(LevelPairingFrequency.getPairingFrequencyFor(this, lhs, rhs) < frequency){
						frequency = LevelPairingFrequency.getPairingFrequencyFor(this, lhs, rhs);
						candidate[0] = lhs;
						candidate[1] = rhs;
					}
				}
			}
			l.add(candidate);
			LevelPairingFrequency.setFrequencyFor(this, candidate[0], candidate[1], ++frequency);
		}
		
		return l;
	}
    
}

