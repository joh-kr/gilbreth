package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * @Depricated
 * @author Johannes Müller
 *
 */
@Entity
public class AttributePairingFrequency extends Model{
	
    @ManyToOne
    @Required    
    public Respondent respondent;
   
    @Required 
    Attribute lhs;
    
    @Required 
    Attribute rhs;
    
    @Required 
    long frequency;
    
    public AttributePairingFrequency(Respondent respondent, Attribute lhs, Attribute rhs){
    	this.respondent = respondent;
    	this.frequency = 0l;
    	this.lhs = lhs;
    	this.rhs = rhs;
    }
    
    public static boolean pairExist(Respondent respondent, Attribute lhs, Attribute rhs){
    	List<AttributePairingFrequency> l = AttributePairingFrequency.find("respondent = ?1 AND lhs = ?2 AND rhs = ?3" , respondent, lhs, rhs).fetch();
    	return l.size() > 0;
    }
	
    public static AttributePairingFrequency getFrequencyFor(Respondent respondent, Attribute lhs, Attribute rhs){
    	
    	List<AttributePairingFrequency> l = AttributePairingFrequency.find("respondent = ?1 AND lhs = ?2 AND rhs = ?3" , respondent, lhs, rhs).fetch();
    	if(l.size() > 0) return l.get(0);
    	
    	AttributePairingFrequency freq = new AttributePairingFrequency(respondent, lhs, rhs).save();
    	respondent.attributePairingFrequencies.add(freq);
    	respondent.save();
    	
    	return freq;
    }
    
    public static long getPairingFrequencyFor(Respondent respondent, Attribute lhs, Attribute rhs){
    	List<AttributePairingFrequency> l = AttributePairingFrequency.find("respondent = ?1 AND lhs = ?2 AND rhs = ?3" , respondent, lhs, rhs).fetch();
    	if(l.size() > 0) return l.get(0).frequency;
    	
    	return 0;
    }
    
    public static void setFrequencyFor(Respondent respondent, Attribute lhs, Attribute rhs, long frequency){
    	AttributePairingFrequency f = getFrequencyFor(respondent, lhs, rhs);
    	f.frequency = frequency;
    	f.save();
    }
    
}
