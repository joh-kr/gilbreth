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
}
