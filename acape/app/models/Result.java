package models;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Result extends Model{
	
	@Transient
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
    @Lob
    public byte[] matrix;

    @Lob
    public byte[] featureColumnMatching;     
    
    @Required
    public int rowCount;
    
    
    public double R2Difference;
    
    public double R2;
    
	/**
	 * The levels explicitly excluded in the first stage
	 */
    @ManyToMany
    public List<Level> excludedLevels;
    
    
    public Result(){    	
    	excludedLevels = new ArrayList<Level>();
    	rowCount = -1;
    	R2Difference = 1.0d;
    	R2 = 0.0d;
    }
 
	private void initializeFeatureColumnMatching(){
		int c = 0;
		Map<String, Integer> map = new Hashtable();
    	List<Level> levels = Level.all().fetch();
    	for(Level l : levels){
    		for(String f : l.getConstitutingFeaturesAsArray()){
    			if(!map.containsKey(f)) map.put(f, c++);
    		}
    		if(l.getNrOfFeatures() > 1){
    			if(!map.containsKey(l.getConstitutingFeatures())) map.put(l.getConstitutingFeatures(), c++);
    		}
    	}
    	this.setFeatureColumnMatching(map);
    	jlog.log(java.util.logging.Level.INFO, "FeatureColumnMatching initialized with " + map.size() + " entries.");
	}
	
	/**
	 * The suggestion is to ask 3*(N - n - 1) -N pairs S. 73 Breidert
	 * where N is the nr of levels and n is the number of attributes
	 * 
	 * We modify this definition and use not the number of levels. Instead
	 * we use the nr of features consituting the levels. That is due to the fact
	 * that we want to know the part worths of the features and not the levels.
	 * Since we expect more features than levels, the number of questions must be higher
	 * with features than with levels.
	 * 
	 * Additionally we need rows for the initial utility from the self explicated stqges.
	 * + number of levels.
	 * 
	 * @return the number of rows of the estimation matrix
	 */
    private int calculateNrOfRows(){
    	
    	int nrOfFeatures   = Level.nrOfAllFeatures();
    	int nrOfAttributes = Attribute.all().fetch().size();
    	int nrOfLevels     = Level.findAll().size();
    	int numberOfColumns = nrOfFeatures + nrOfLevels;
    	
		// The suggestion is to ask 3*(N - n - 1) -N pairs S. 73 Breidert
    	int suggestedValue = 3 * (numberOfColumns  - nrOfAttributes - 1) - numberOfColumns ;
    	int full = suggestedValue + numberOfColumns;
    	jlog.log(java.util.logging.Level.INFO, "Number of rows for the matrix are " + full + ".");
    	
    	return full;
    }
    
    /**
     * the number of all features in the 	
     * @return
     */
    private  int calculateNrOfColumns(){
    	return this.getFeatureColumnMatching().size() + 1;
    }
    
    private void initializeMatrix(){ 	
    	setMatrix(new Array2DRowRealMatrix(this.calculateNrOfRows(), this.calculateNrOfColumns()));
    	jlog.log(java.util.logging.Level.INFO, "Matrix is initialized.");
    }
    
    
    public void addExcludedLevel(Level level){
    	excludedLevels.add(level);
    	this.save();
    	jlog.log(java.util.logging.Level.INFO, "Level " + level.getName() + " is excluded from the interview.");
    }
    
    public void addNewRow(double[] row){
    	RealMatrix m = this.getMatrix();
    	m.setRow(++rowCount, row);
    	this.setMatrix(m);
    	this.save();
    	jlog.log(java.util.logging.Level.INFO, "At row " + rowCount + " new values are written.");
    }
    
    public int getNrOfColumns(){
    	return getMatrix().getColumnDimension();
    }
    
    public double getR2Difference(){
    	return this.R2Difference;
    }
    
    /**
     * Sets the difference between old r2 and new r2 given by the parameter.
     * The difference R2Difference can be used as a measure to decide when
     * level comparison stage can be ended.
     * @param value new r2 value
     */
    public void setR2(double value){
    	this.R2Difference = value - this.R2;
    	this.R2 = value;
    	save();
    	jlog.log(java.util.logging.Level.INFO, "R2 difference is " + this.R2Difference);
    }
    
    public RealMatrix getCurrentSubMatrix(){
    	RealMatrix m = getMatrix( );
    	return m.getSubMatrix(0, getRowCount(), 0, m.getColumnDimension() - 1);
    }
    
    public double[][] getIndeptendentVariableValues(){
    	RealMatrix m = getCurrentSubMatrix( );
    	return m.getSubMatrix(0, m.getRowDimension() - 1, 0, m.getColumnDimension() - 2).getData();
    }
    
    public double[] getDependentVariableValues(){
    	RealMatrix m = getCurrentSubMatrix( );
		return m.getColumn(m.getColumnDimension() - 1);
    }
    
    public RealMatrix getMatrix(){
    	return (RealMatrix)deserializeObject(this.matrix);
    }
    
    public void setMatrix(RealMatrix matrix){
    	this.matrix = serializeObject(matrix);
    }
    
    public Map<String, Integer> getFeatureColumnMatching(){
    	return (Map<String, Integer>)deserializeObject(this.featureColumnMatching);
    }
    
    public void setFeatureColumnMatching(Map<String, Integer> map){
    	this.featureColumnMatching = serializeObject(map);
    }
    
    public int getRowCount(){
    	return this.rowCount;
    }
    
    public int getColumnFor(String name) throws Exception{
    	Map<String, Integer> map = getFeatureColumnMatching();
    	if(!map.containsKey(name))
    		throw new Exception("The matrix does not contain any entry for key " + name);
    	return map.get(name).intValue();
    }
    
    public Integer[] getColumnsFor(Level level) throws Exception{
		List<Integer> result = new ArrayList();
			for(String s : level.getConstitutingFeaturesAsArray()){
				result.add(getColumnFor(s));
			}
			// If more than one feature build the level, than we also have a interaction parameter
			// Its name consists of all features of the level in a comma separated list
			if(level.getConstitutingFeaturesAsArray().length > 1){
				result.add(getColumnFor(level.getConstitutingFeatures()));
			}
			return result.toArray(new Integer[1]);
    }
    
    public double getRateFor(Level level) throws Exception{
    	int column = getColumnFor(level.getConstitutingFeatures());
    	RealMatrix matrix = getMatrix();
    	// Check whether matrix is initialized
    	int row = 0;
    	boolean found = false;
    	for(int i = 0; i < matrix.getRowDimension(); i++){
    		if(matrix.getEntry(i, column) == 1.0d){
    			row = i;
    			found = true;
    			break;
    		}
    	}
    	if(!found) 
    		throw new Exception("No Entry for Level " + level.getName() + " found.");
    
    	return matrix.getEntry(row, matrix.getColumnDimension() - 1);
    }
    
    /*
     * Level frequency is the sum of ??
     * TODO: find out what level frequency is
     */
    public int getLevelFrequency(Level level) throws Exception{
    	RealMatrix m = getMatrix();
    	int columnIndex = getColumnFor(level.getConstitutingFeatures());
    	double[] column = m.getColumn(columnIndex);
    	int c = 0;
    	for(double entry : column){
    		if(entry != 0.0d) c++;
    	}
    	return c;
    }
    
    /*
     * Attribute frequency is the sum of the frequencies of all 
     * levels of the attribute
     */
    public int getAttributeFrequency(Attribute attribute) throws Exception{
    	List<Level> levels = attribute.getLevels(excludedLevels);
    	int c = 0;
    	for(Level l : levels){
    		c += getLevelFrequency(l);
    	}
    	return c;
    }
    
	private byte[] serializeObject(Object obj){
		byte[] buf = null;
		try {
		    // Serialize to a byte array
		    ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
		    ObjectOutputStream out = new ObjectOutputStream(bos) ;
		    out.writeObject(obj);
		    out.close();

		    // Get the bytes of the serialized object
		    buf = bos.toByteArray();
		  
		} catch (IOException e) {
			//Err
		}
		 return buf;
	}
	
	private Object deserializeObject(byte[] serializedObject){
		//http://www.exampledepot.com/egs/java.io/DeserializeObj.html
		Object obj = null;
		try {
		    // Deserialize from a byte array
		    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(serializedObject));
		    obj = in.readObject();
		    in.close();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		return obj;
	}
	
	
	
	public static Result createNewResult(){
		Result result = new Result();
		result.initializeFeatureColumnMatching();
		result.initializeMatrix();
		result.save();
		return result;
	}
}