package models.TestModels;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;

/*
 * Model used to store observation in the level rating stage for unit tests
 * 
 */

@Entity
public class RatingObservation extends Model {
    @ManyToOne
    @Required
    public Level level;
    
    @Required
    public Double rating;
    
    public RatingObservation(Level level, Double rating) {
    	this.level = level;
    	this.rating = rating;
    }
    
    public static void addObservations(LevelRatingStage stage, List<RatingObservation> observations) throws Exception {
		Map<Long, Double> levelIdsAndRates = new Hashtable();
		
		for(RatingObservation obs : observations) {
			levelIdsAndRates.put(obs.level.id, obs.rating);
		}
		
		stage.addLevelRates(levelIdsAndRates);
    }
}