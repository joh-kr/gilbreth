package models;

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
public class LevelRate extends Model{

    @ManyToOne
    @Required
	public Level level;
    
    @ManyToOne
    @Required    
    public Respondent respondent;
   
    @Required
    public double rate;
    
    public LevelRate(Respondent respondent, Level level, double rate){
    	this.level = level;
    	this.respondent = respondent;
    	this.rate = rate;
    }
    
    public void updateLevelRate(double rate){
    	this.rate = rate;
    	this.save();
    }
    
	
}
