package models;

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
		for(Level l : attribute.getLevels()){
			if(level == null || result.getRateFor(l) > result.getRateFor(level))
				level = l;
		}
		return level;
	}
	
	public Level getWorstRatedLevel(int page) throws Exception{
		Attribute attribute = getCurrentAttribute(page);
		// Check whether a level in levels exist
		Level level = null;
		for(Level l : attribute.getLevels()){
			if(level == null || result.getRateFor(l) < result.getRateFor(level))
				level = l;
		}
		return level;
	}
	
	public void weightLevelsFor(Attribute attribute, double weight) throws Exception{
		List<Level> levels = attribute.getLevels();
		RealMatrix matrix = result.getMatrix();
		int colIndex;
		double[] column;
		double rate;
		int match;
		
		for(Level l : levels){
			colIndex = result.getColumnFor(l.getConstitutingFeatures());
			column = matrix.getColumn(colIndex);
			match = -1;
			for(int i = 0; i < column.length; i++){
				if(column[i] != 0){
					match = i;
					break;
				}
			}
			rate = matrix.getEntry(match, matrix.getColumnDimension() - 1);
			matrix.setEntry(match, matrix.getColumnDimension() - 1, rate * weight);
		}
		result.setMatrix(matrix);
		result.save();
		//System.out.println("Weighted Matrix: " + result.getMatrix());
	}

}
