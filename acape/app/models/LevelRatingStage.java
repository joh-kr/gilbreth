package models;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.StatUtils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import play.Logger;

/**
 * In that stage, the levels of all attributes are rated in comparison to each other. 
 * Each level gets a rate assigned (e.g. 0-9). However, not the absolute ratings are 
 * important but the relative ratings of the levels. For that reason, the ratings are
 * centered and unitized. The worst level than is a negative value between -1 and 0. The
 * best level has a positive sign with a value between 0 and 1.
 * 
 * @author Johannes Müller
 * @version 0.1
 */
public class LevelRatingStage extends Stage {
	
	public LevelRatingStage(Interview interview, Result result){
		super(interview, result);	
	}
	/*
	 * @TODO Ensure only levels of the same attribute are added at the same time
	 */
	public void addLevelRates(Map<Long, Double> idAndRates) throws Exception{	
		Level level = null;
		
		idAndRates = unitize(center(idAndRates));
		
		for(Long id: idAndRates.keySet()){
			if(idAndRates.get(id).isInfinite() || idAndRates.get(id).isNaN())
				throw new Exception("Post contained ilegal numbers as rates: " + idAndRates.get(id));
			
			level = Level.findById(id);
			double[] row = new double[result.getNrOfColumns()];
			
			Boolean rowEmpty = true;
			
			/*jlog.log(java.util.logging.Level.INFO, "Save rates of Level: " + level.getName());*/
			
			// For each feature constituting the level a entry is added to the matrix
			for(String f : level.getConstitutingFeaturesAsArray()){
				/*jlog.log(java.util.logging.Level.INFO, 
						"Add entry at " + result.getColumnFor(f) + " for feature " + f);*/
				row[result.getColumnFor(f)] = 1;
				rowEmpty = false;
			}

			// Save the rate as the dependent variable in the matrix
			/*jlog.log(java.util.logging.Level.INFO, 
					"Add entry at " + (row.length - 1) + " for rate " + idAndRates.get(id));*/			
			row[row.length - 1] = idAndRates.get(id);
			
			if(!rowEmpty) {
				result.addNewRow(row);
			}
		}
	}
	
	private Double avg(Collection<Double> values){
    	double sumAvg = 0.0d;
    	int countAvg = 0;
    	
    	for(Double value : values){
    		sumAvg += value;
    		countAvg++;
    	}
    	return sumAvg / countAvg;
	}
	
	private Map<Long, Double> center(Map<Long, Double> values){
		Double avg = avg(values.values());
		
		for(Long key : values.keySet()){
    		values.put(key, values.get(key) - avg);
    	}
    	return values;
	}
	
	private Map<Long, Double> unitize(Map<Long, Double> values) throws Exception{
		double[] val = new double[values.size()];
		int i = 0;
		for(double d : values.values()){
			val[i++] = d;
		}
		double max = StatUtils.max(val);
		double min = StatUtils.min(val);
		double range = Math.abs(max) + Math.abs(min);

		// added case for range of zero
		// TODO review algorithm
		for(Long key : values.keySet()){
			if(range == 0.0) {
				values.put(key, 0.0);
			} else { 
				values.put(key, values.get(key)/range);
			}
		}
		return values;
	}
	
	/*
	private Map<Long, Double> standardize(Map<Long, Double> values) throws Exception{
	   	double sumVar = 0.0d;
    	int countVar = 0;
    	double avg = avg(values.values());
    	
    	for(Long key : values.keySet()){
    		sumVar += Math.pow(values.get(key) - avg, 2);
    		countVar++;
    	}

    	double variance = sumVar / countVar;
    	double standardDev = Math.sqrt(variance);
    	
    	if(standardDev == 0.0d) throw new Exception("Deviation of the distribution is 0. Cannot standardize the variable!");
    	
    	for(Long key : values.keySet()){
    		values.put(key, values.get(key) / standardDev);
    	}
    	return values;
		
	}
	*/

}
