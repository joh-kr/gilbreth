package models.TestModels;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.Attribute;
import models.AttributeWeightingStage;
import models.Level;
import models.LevelRatingStage;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Class used only for testing. Allows the persistence of attribute importance observation.
 * @author Max Lillack
 *
 */
@Entity
public class AttributeImportanceObservation extends Model {
    @ManyToOne
    @Required
    public Attribute attribute;
    
    
    @Required
    public Double importance;
    
    public AttributeImportanceObservation(Attribute attribute, Double importance) {
    	this.attribute = attribute;
    	this.importance = importance;
    }
    
    public static void addObservations(AttributeWeightingStage stage, List<AttributeImportanceObservation> observations) throws Exception {		
		for(AttributeImportanceObservation obs : observations) {
			stage.weightLevelsFor(obs.attribute, obs.importance);
		}
    }
}
