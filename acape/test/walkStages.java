import org.apache.commons.math.linear.RealMatrix;
import org.junit.*;

import java.util.*;
import java.util.logging.Logger;

import play.test.*;
import models.*;


public class walkStages {
	public void walkLevelRateStage(LevelRatingStage stage) throws Exception
	{
    	Attribute forum = Attribute.find("byName", "Forum").first();
    	Map<Long, Double> levelIdsAndRates = new Hashtable();
		Level forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		Level forumAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Absent").first();
		levelIdsAndRates.put(forumAbsent.id, 2d);
		levelIdsAndRates.put(forumPresent.id, 7d);
		stage.addLevelRates(levelIdsAndRates);
		
    	Attribute payment = Attribute.find("byName", "Payment with Fraud Detection").first();
    	levelIdsAndRates = new Hashtable();
		Level paymentPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Present").first();
		Level paymentAbsent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", payment, "Absent").first();	

		levelIdsAndRates.put(paymentPresent.id, 4d);
		levelIdsAndRates.put(paymentAbsent.id, 1d);
		stage.addLevelRates(levelIdsAndRates);
		
	}
}
