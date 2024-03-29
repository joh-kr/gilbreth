package models;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import models.LevelsPair;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

import com.sun.org.apache.bcel.internal.generic.NEW;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Result saves all intermediate and final results of the interview
 * @author Johannes Müller
 * @author Max Lillack
 *
 */
@Entity
public class Result extends Model {

	@Transient
	private static Logger jlog = Logger
			.getLogger("de.iwi.uni_leipzig.gilbreth");

	@Lob
	public byte[] matrix;

	@Lob
	private byte[] featureColumnMatching;

	@Required
	public int matrixRowCount;

	/*
	 * Results from Price Estimation Stage
	 */
	@Column(nullable=true)
	public Double PEslope = null;
	@Column(nullable=true)
	public Double PEintercept = null;
	
	public double R2Difference;

	public double R2;
	
	@Required
	/*
	 * Intercept of the regression of the calibrated utility after stage concept comparision
	 */
	@Column(nullable = true)
	public Double calibratedUtilityIntercept;
	
	@Required
	/*
	 * Slope of the regression of the calibrated utility after stage concept comparision
	 */
	@Column(nullable = true)
	public Double calibratedUtilitySlope;

	/**
	 * The levels explicitly excluded in the first stage
	 */
	@ManyToMany
	public List<Level> excludedLevels;
	
	@ManyToMany
	public List<UsedLevelPair> usedLevelPairs;
	
	/*
	 * Number of pairs questions
	 */
	public int numberOfPairs = 0;
	
	/*
	 * PE Stage Variables
	 */
	public double utility1;
	public BigDecimal price1;
	public double utility2;
	public BigDecimal price2;
	
	@ManyToMany
	public List<Concept> validConcepts = null;
	
	@ManyToMany
	@JoinTable(name="Result_usedConcepts")
	public List<Concept> usedConcepts;
	
	public Result() {
		excludedLevels = new ArrayList<Level>();
		usedLevelPairs = new ArrayList<UsedLevelPair>();
		usedConcepts = new ArrayList<Concept>();
		
		matrixRowCount = -1;
		R2Difference = 1.0d;
		R2 = 0.0d;
		
		calibratedUtilityIntercept = null;
		calibratedUtilitySlope = null;
	}

	/*
	 * Creates a map with: key is a string representing a single feature value
	 * is the column index in the matrix
	 */
	private void initializeFeatureColumnMatching() {
		int column = 0;
		Map<String, Integer> map = new Hashtable();
		List<Level> levels = Level.all().fetch();
		for (Level l : levels) {
			for (String f : l.getConstitutingFeaturesAsArray()) {
				if (!map.containsKey(f)) {
					map.put(f, column++);
				}
			}
			// add column for feature interaction parameter
			if(l.getNrOfFeatures() > 1) {
				if(!map.containsKey(l.getConstitutingFeatures())) {
					map.put(l.getConstitutingFeatures(), column++);
				}
			}
		}
		this.setFeatureColumnMatching(map);
		/*jlog.log(java.util.logging.Level.INFO,
				"FeatureColumnMatching initialized with " + map.size()
						+ " entries.");*/
	}

	/**
	 * The suggestion is to ask 3*(N - n - 1) -N pairs S. 73 Breidert where N is
	 * the nr of levels and n is the number of attributes
	 * 
	 * We modify this definition and use not the number of levels. Instead we
	 * use the nr of features consituting the levels. That is due to the fact
	 * that we want to know the part worths of the features and not the levels.
	 * Since we expect more features than levels, the number of questions must
	 * be higher with features than with levels.
	 * 
	 * Additionally we need rows for the initial utility from the self
	 * explicated stqges. + number of levels.
	 * 
	 * @return the number of rows of the estimation matrix
	 */
	private int calculateNrOfRows() {

		int nrOfFeatures = Level.nrOfAllFeatures();
		int nrOfAttributes = Attribute.all().fetch().size();
		int nrOfLevels = Level.findAll().size();
		int numberOfColumns = nrOfFeatures + nrOfLevels;

		// The suggestion is to ask 3*(N - n - 1) -N pairs S. 73 Breidert
		int suggestedValue = 3 * (numberOfColumns - nrOfAttributes - 1)
				- numberOfColumns;
		int full = suggestedValue + numberOfColumns;
		// jlog.log(java.util.logging.Level.INFO,
		// "Number of rows for the matrix are " + full + ".");

		return full;
	}

	/**
	 * the number of all columns in the matrix
	 * 
	 * @return
	 */
	private int calculateNrOfColumns() {
		return this.getFeatureColumnMatching().size() + 1;
	}

	private void initializeMatrix() {
		// jlog.log(java.util.logging.Level.INFO, "Number of Columns: " +
		// this.calculateNrOfColumns());
		setMatrix(new Array2DRowRealMatrix(this.calculateNrOfRows(),
				this.calculateNrOfColumns()));
		// jlog.log(java.util.logging.Level.INFO, "Matrix is initialized.");
	}

	public void addExcludedLevel(Level level) {
		excludedLevels.add(level);
		this.save();
		jlog.log(java.util.logging.Level.INFO, "Level " + level.getName()
				+ " is excluded from the interview.");
	}

	public void addNewRow(double[] row) {
		RealMatrix m = this.getMatrix();
		m.setRow(++matrixRowCount, row);
		this.setMatrix(m);
		this.save();
		// jlog.log(java.util.logging.Level.INFO, "At row " + rowCount +
		// " new values are written.");
	}

	public int getNrOfColumns() {
		return getMatrix().getColumnDimension();
	}

	public double getR2Difference() {
		return this.R2Difference;
	}

	/**
	 * Sets the difference between old r2 and new r2 given by the parameter. The
	 * difference R2Difference can be used as a measure to decide when level
	 * comparison stage can be ended.
	 * 
	 * @param value
	 *            new r2 value
	 */
	public void setR2(double value) {
		this.R2Difference = value - this.R2;
		this.R2 = value;
		save();
		jlog.log(java.util.logging.Level.INFO, "R2 difference is " + this.R2Difference);
	}

	public RealMatrix getCurrentSubMatrix() {
		RealMatrix m = getMatrix();
		return m.getSubMatrix(0, getRowCount(), 0, m.getColumnDimension() - 1);
	}

	public double[][] getIndeptendentVariableValues() {
		RealMatrix m = getCurrentSubMatrix();
		return m.getSubMatrix(0, m.getRowDimension() - 1, 0,
				m.getColumnDimension() - 2).getData();
	}

	public double[] getDependentVariableValues() {
		RealMatrix m = getCurrentSubMatrix();
		return m.getColumn(m.getColumnDimension() - 1);
	}

	public RealMatrix getMatrix() {
		return (RealMatrix) deserializeObject(this.matrix);
	}

	public void setMatrix(RealMatrix matrix) {
		this.matrix = serializeObject(matrix);
	}

	public Map<String, Integer> getFeatureColumnMatching() {
		return (Map<String, Integer>) deserializeObject(this.featureColumnMatching);
	}

	public void setFeatureColumnMatching(Map<String, Integer> map) {
		this.featureColumnMatching = serializeObject(map);
	}

	public int getRowCount() {
		return this.matrixRowCount;
	}
	
	/*
	 * Returns a set of features (string) which are included in at least one level of the attribute 
	 */
	private HashSet<String> getFeaturesOfAttribute(Attribute a)
	{
		HashSet<String> featureSet = new HashSet<String>();
		
		for(Level l : a.getLevels(excludedLevels)) {
			for(String feature : l.getConstitutingFeaturesAsArray()) {
				featureSet.add(feature);
			}
		}
		
		return featureSet;
	}
	
	/*
	 * Returns a set of columns which represent features present in some levels of the attribute
	 */
	private HashSet<Integer> getColumnsOfAttribute(Attribute a) throws Exception
	{
		HashSet<Integer> columns = new HashSet<Integer>();
		for(String feature : getFeaturesOfAttribute(a)) {
			columns.add(getColumnFor(feature));
		}
		return columns;
	}
	
	public String getNameForColumn(Integer column) throws Exception
	{
		Map<String, Integer> map = getFeatureColumnMatching();
		if(!map.containsValue(column)) {
			throw new Exception(
					"The matrix does not contain any entry for value " + column);
		}
		String name = null;
		Boolean found = false;
		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() == column) {
				name = entry.getKey();
				found = true;
			}
		}
		if(!found) {
			throw new Exception("Value not found");
		}
		return name;
	}

	/*
	 * Return the column in the result matrix representing the given feature
	 */
	public int getColumnFor(String feature) throws Exception {
		Map<String, Integer> map = getFeatureColumnMatching();
		if (!map.containsKey(feature))
			throw new Exception(
					"The matrix does not contain any entry for key " + feature);
		return map.get(feature).intValue();
	}

	public Integer[] getColumnsFor(Level level) throws Exception {
		List<Integer> result = new ArrayList();

		for (String s : level.getConstitutingFeaturesAsArray()) {
			result.add(getColumnFor(s));
		}
		
		// feature interaction parameter
		if(level.getNrOfFeatures() > 1) {
			result.add(getColumnFor(level.getConstitutingFeatures()));
		}

		return result.toArray(new Integer[0]);
	}

	/*
	 * Return rating 0 if level has no features
	 */
	public double getRateFor(Level level) throws Exception {
		RealMatrix matrix = getMatrix();
		// Check whether matrix is initialized
		double rating = 0;

		String[] features = level.getConstitutingFeaturesAsArray();
		int[] affectedColumns = new int[features.length];
		
		// check if level has features
		if(features.length > 0) {
			for (int i = 0; i < features.length; i++) {
				affectedColumns[i] = getColumnFor(features[i]);
			}
	
			boolean found = false;
			boolean featureInRow = false;
			
			for (int i = 0; i < matrix.getRowDimension(); i++) {
				featureInRow = false;
				for (int j = 0; j < affectedColumns.length; j++) {
					if (matrix.getEntry(i, affectedColumns[j]) != 0) {
						featureInRow = true;
						found = true;
					}
				}
				if (featureInRow) {
					rating += matrix.getEntry(i, matrix.getColumnDimension() - 1);
				}
			}
			if (!found) {
				throw new Exception("No Entry for Level " + level.getName()
						+ " found.");
			}
		}

		return rating;
	}

	/*
	 * Level frequency is the count of observations containing all the features
	 * of the level
	 */
	public int getLevelFrequency(Level level) throws Exception {
		RealMatrix m = getMatrix();
		
		//Saves the columns with the needed features
		int[] columns = new int[level.getConstitutingFeaturesAsArray().length];

		int c = 0;
		for (String f : level.getConstitutingFeaturesAsArray()) {
			columns[c++] = getColumnFor(f);
		}
		
		//Get set of all columns of this attribute i.e. all features of levels of the same attribute
		HashSet<Integer> columnsOfUnwantedFeatures = getColumnsOfAttribute(level.attribute);
		// Remove features of the selected level leaving only unwanted features
		for(int col : columns) {
			columnsOfUnwantedFeatures.remove(col);
		}
		
		int count = 0;

		for (int i = 0; i <= getRowCount(); i++) {
			double[] row = m.getRow(i);
			boolean rowContainsAllFeatures = true;
			
			// call with +1.0 and -1.0 to check existence on left side as well as right side
			rowContainsAllFeatures = rowContainsLevel(columns, columnsOfUnwantedFeatures, row, 1.0)
								  || rowContainsLevel(columns, columnsOfUnwantedFeatures, row, -1.0);
			
			if (rowContainsAllFeatures) {
				count++;
			}
		}

		return count;
	}

	/*
	 * flag = -1 if feature is searched on left side, +1 else
	 */
	private boolean rowContainsLevel(int[] columns,
									 HashSet<Integer> columnsOfUnwantedFeatures,
									 double[] row, double flag) {
		
		Boolean rowContainsAllFeatures = true;
		
		for (int j = 0; j < columns.length; j++) {
			if (row[columns[j]] != flag) {
				rowContainsAllFeatures = false;
			}
		}
		
		// Check for unwanted features
		if(rowContainsAllFeatures) {
			for(int j : columnsOfUnwantedFeatures) {
				if (row[j] == flag) {
					rowContainsAllFeatures = false;
				}
			}
		}
		return rowContainsAllFeatures;
	}
	

	/*
	 * Attribute frequency is the sum of the frequencies of all levels of the
	 * attribute
	 */
	public int getAttributeFrequency(Attribute attribute) throws Exception {
		List<Level> levels = attribute.getLevels(excludedLevels);
		int c = 0;
		for (Level l : levels) {
			c += getLevelFrequency(l);
		}
		return c;
	}

	private byte[] serializeObject(Object obj) {
		byte[] buf = null;
		try {
			// Serialize to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			out.close();

			// Get the bytes of the serialized object
			buf = bos.toByteArray();

		} catch (IOException e) {
			// Err
		}
		return buf;
	}

	private Object deserializeObject(byte[] serializedObject) {
		// http://www.exampledepot.com/egs/java.io/DeserializeObj.html
		Object obj = null;
		try {
			// Deserialize from a byte array
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(serializedObject));
			obj = in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		return obj;
	}
	
	public static Result createNewResult() {
		Result result = new Result();
		result.initializeFeatureColumnMatching();
		result.initializeMatrix();
		result.save();
		return result;
	}
	
}
