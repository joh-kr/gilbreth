package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @Deprecated
 * @author Johannes Müller
 *
 */
@Entity
public class LevelPairingFrequency extends Model{
	
    @ManyToOne
    @Required    
    public Respondent respondent;
   
    @Required 
    Level lhs;
    
    @Required 
    Level rhs;
    
    @Required 
    long frequency;
    
    public LevelPairingFrequency(Respondent respondent, Level lhs, Level rhs){
    	this.respondent = respondent;
    	this.frequency = 0l;
    	this.lhs = lhs;
    	this.rhs = rhs;
    }
    
    public static boolean pairExist(Respondent respondent, Attribute lhs, Attribute rhs){
    	List<LevelPairingFrequency> l = LevelPairingFrequency.find("respondent = ?1 AND lhs = ?2 AND rhs = ?3" , respondent, lhs, rhs).fetch();
    	return l.size() > 0;
    }
	
    public static LevelPairingFrequency getFrequencyFor(Respondent respondent, Level lhs, Level rhs){
    	
    	List<LevelPairingFrequency> l = LevelPairingFrequency.find("respondent = ?1 AND lhs = ?2 AND rhs = ?3" , respondent, lhs, rhs).fetch();
    	if(l.size() > 0) return l.get(0);
    	
    	LevelPairingFrequency freq = new LevelPairingFrequency(respondent, lhs, rhs).save();
    	respondent.levelPairingFrequencies.add(freq);
    	respondent.save();
    	
    	return freq;
    }
    
    public static long getPairingFrequencyFor(Respondent respondent, Level lhs, Level rhs){
    	List<LevelPairingFrequency> l = LevelPairingFrequency.find("respondent = ?1 AND lhs = ?2 AND rhs = ?3" , respondent, lhs, rhs).fetch();
    	if(l.size() > 0) return l.get(0).frequency;
    	
    	return 0;
    }
    
    public static void setFrequencyFor(Respondent respondent, Level lhs, Level rhs, long frequency){
    	LevelPairingFrequency f = getFrequencyFor(respondent, lhs, rhs);
    	f.frequency = frequency;
    	f.save();
    }
    
}
