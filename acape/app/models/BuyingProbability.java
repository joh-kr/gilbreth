package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @Deprecated
 * @author Johannes Müller
 *
 */
@Entity
public class BuyingProbability extends Model{

    @OneToMany
    @Required
	public List<Level> levels;
    
    @ManyToOne
    @Required    
    public Respondent respondent;
   
    @Required
    public Double probability;
    
    public BuyingProbability(List<Level> levels, Respondent respondent, Double probability){
    	this.levels = levels;
    	this.respondent = respondent;
    	this.probability = probability;
    }
    
	
}
