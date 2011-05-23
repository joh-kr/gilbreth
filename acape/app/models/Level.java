package models;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Level extends Model{
	
	@Transient
	protected static Logger jlog =  Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
    @ManyToOne
    @Required
	public Attribute attribute;
    
	public Attribute getAttribute() {
		return attribute;
	}


	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}


	public void setName(String name) {
		this.name = name;
	}

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
		if(features == null) {
			return new String[0];
		} else {
			return features.split(",");
		}
	}
	
	public int getNrOfFeatures(){
		return getConstitutingFeaturesAsArray().length;
	}
	
	
	public static int nrOfAllFeatures(){
    	int nrOfFeatures = 0;
    	List<Level> levels = Level.all().fetch();
    	for(Level l : levels)
    		nrOfFeatures += l.getNrOfFeatures();
    	return nrOfFeatures;
	}
}
