import org.apache.commons.math.linear.RealMatrix;
import org.junit.*;

import java.util.*;
import java.util.logging.Logger;

import play.test.*;
import models.*;
import models.TestModels.AttributeImportanceObservation;
import models.TestModels.RatingObservation;


public class walkStages {

	public void walkLevelRateStage(LevelRatingStage stage) throws Exception
	{
		List<RatingObservation> testObservations = RatingObservation.findAll();
		RatingObservation.addObservations(stage, testObservations);
	}
	
	public void walkAttributeWeightingStage(AttributeWeightingStage stage) throws Exception
	{ 	
		List<AttributeImportanceObservation> testObservations = AttributeImportanceObservation.findAll();
		AttributeImportanceObservation.addObservations(stage, testObservations);
	}
	
	public void walkPairsUtilityStage(PairsUtilityStage stage) throws Exception
	{
		while(!stage.isFinished()) {
	    	PairsUtilityStage.LevelsPair pair = stage.computeLevelsPair(2);
	    	if(pair != null) {
		    	List<Level> lhs = pair.getLHS();
		    	List<Level> rhs = pair.getRHS();
		    	
		    	// get test ratings from previous observations
		    	double ratingLhs = 0;
		    	double ratingRhs = 0;
		    	
		    	for(Level l : lhs) {
		    		RatingObservation obs = RatingObservation.find("select obs from RatingObservation obs where obs.level = ?", l).first();
		    		ratingLhs += obs.rating;
		    	}
		    	for(Level l : rhs) {
		    		RatingObservation obs = RatingObservation.find("select obs from RatingObservation obs where obs.level = ?", l).first();
		    		ratingRhs += obs.rating;
		    	}
		    	
		    	int rating;
		    	
		    	if(ratingLhs == ratingRhs) {
		    		rating = 0;
		    	} else if(ratingLhs > ratingRhs) {
		    		rating = (int) Math.max(-4, Math.round(ratingRhs - ratingLhs));
		    	} else {
		    		rating = (int) Math.min(4, Math.round(ratingLhs - ratingRhs));
		    	}
		    	stage.saveNewObservationByLevels(lhs, rhs, rating);	    		
	    	}
		}
	}
}
