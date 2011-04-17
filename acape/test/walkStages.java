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
	
	private int getUtilityComparisionRating(double ratingLhs, double ratingRhs) {
    	int rating;
		if(ratingLhs == ratingRhs) {
    		rating = 0;
    	} else if(ratingLhs > ratingRhs) {
    		rating = (int) Math.max(-4, Math.round(ratingRhs - ratingLhs));
    	} else {
    		rating = (int) Math.min(4, Math.round(ratingLhs - ratingRhs));
    	}
    	return rating;
	}
	
	/*
	 * answer pairs utility stage consistent by using expected utility
	 */
	public void walkPairsUtilityStage(PairsUtilityStage stage, Utility utility) throws Exception
	{
		while(!stage.isFinished()) {
	    	PairsUtilityStage.LevelsPair pair = stage.computeLevelsPair(2);
	    	if(pair != null) {
		    	List<Level> lhs = pair.getLHS();
		    	List<Level> rhs = pair.getRHS();
		    	
		    	double utilityLhs = utility.computeUtilityFor(lhs);
		    	double utilityRhs = utility.computeUtilityFor(rhs);
		    	
		    	int rating = getUtilityComparisionRating(utilityLhs, utilityRhs);

		    	stage.saveNewObservationByLevels(lhs, rhs, rating);	    		
	    	}
		}
	}
}
