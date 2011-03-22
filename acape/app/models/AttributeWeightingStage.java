package models;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.math.linear.RealMatrix;

/**
 * The Stage of an ACA-PE survey where the Attributes are weighted. That is done by asking how 
 * important the difference between the worst and the best level is to the respondent. As
 * higher the value as more important the attribute is.
 * 
 * @author Johannes Müller
 *
 */
public class AttributeWeightingStage extends Stage {
	
	public AttributeWeightingStage(Interview interview, Result result){
		super(interview, result);
		
	}
	
	public Level getBestRatedLevel(int page) throws Exception{
		Attribute attribute = getCurrentAttribute(page);
		// Check whether a level in levels exist
		Level level = null;
		for(Level l : attribute.getLevels(result.excludedLevels)){
			if(level == null || result.getRateFor(l) > result.getRateFor(level))
				level = l;
		}
		return level;
	}
	
	public Level getWorstRatedLevel(int page) throws Exception{
		Attribute attribute = getCurrentAttribute(page);
		// Check whether a level in levels exist
		Level level = null;
		for(Level l : attribute.getLevels(result.excludedLevels)){
			if(level == null || result.getRateFor(l) < result.getRateFor(level))
				level = l;
		}
		return level;
	}
	
	/*
	 * Applies the weight to the current rating of all observations containing
	 * a feature of the attribute
	 */
	public void weightLevelsFor(Attribute attribute, double weight) throws Exception{
		List<Level> levels = attribute.getLevels(result.excludedLevels);
		
		RealMatrix matrix = result.getMatrix();
		double rate;
		
		HashSet<String> features = new HashSet<String>();
		
		for(Level l : levels) {
			for(String feature : l.getConstitutingFeaturesAsArray()) {
				features.add(feature);
			}
		}
		
    	int[] affectedColumns = new int[features.size()];
    	
    	int j = 0;
		for(String feature : features) {
			affectedColumns[j++] = result.getColumnFor(feature);
		}
		
		boolean featureInRow = false;
		
    	for(int i = 0; i < matrix.getRowDimension(); i++) {
    		featureInRow = false;
    		for(int c = 0; c < affectedColumns.length; c++) {
	    		if(matrix.getEntry(i, affectedColumns[c]) != 0){
	    			featureInRow = true;
	    		}
    		}
    		//weight row as it contains a feature of the attribute
    		if(featureInRow) {
    			rate = matrix.getEntry(i, matrix.getColumnDimension() - 1);
    			matrix.setEntry(i, matrix.getColumnDimension() - 1, rate * weight);
    		}
    	}		

		result.setMatrix(matrix);
		result.save();
	}

}
