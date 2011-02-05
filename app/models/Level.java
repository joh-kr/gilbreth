package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Level extends Model{
	
    @ManyToOne
    @Required
	public Attribute attribute;
    
	@Required
    public String name;
	
	@Required
	@Lob
    public String description;
	
	/**
	 * The features as comma separated list, constituting the level
	 */
	@Required
	public String features;
	
	public Level(Attribute attribute, String name, String description, String features){
		this.attribute = attribute;
		this.name = name;
		this.description = description;
		this.features = features;
	}
	
	
	public String getConstitutingFeatures(){
		return features;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return the names of the features constituting the level
	 */
	public String[] getConstitutingFeaturesAsArray(){
		return features.split(",");
	}
	
	public int getNrOfFeatures(){
		return getConstitutingFeaturesAsArray().length;
	}
	
	
	public LevelRate getRatingOf(Respondent respondent){
		List<LevelRate> levelRates = this.find("SELECT lr "
				+ "FROM LevelRate lr JOIN lr.level l JOIN lr.respondent r "
				+ "WHERE l = ?1 AND r = ?2",this, respondent).fetch();
		
		if(levelRates != null && levelRates.size() > 0)
			return levelRates.get(0);
		return null;

	}
	
	public static int nrOfAllFeatures(){
    	int nrOfFeatures = 0;
    	List<Level> levels = Level.all().fetch();
    	for(Level l : levels)
    		nrOfFeatures += l.getNrOfFeatures();
    	return nrOfFeatures;
	}
}
