package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.data.validation.Required;

/**
 * A LevelsPair consists of a left hand side (lhs) and right hand side (rhs) 
 * each containing a set of levels
 * @author Johannes Müller
 * @author Max Lillack
 *
 */
public class LevelsPair implements Comparable{
	
	private List<Level> lhs;
	private List<Level> rhs;
	
	private double utility_lhs;
	private double utility_rhs;
	
	private Utility utility;
	
	public LevelsPair(Utility utility) {
		lhs = new ArrayList();
		rhs = new ArrayList();
		this.utility = utility;
	}
	
	public LevelsPair(LevelsPair levelsPair){
		lhs = new ArrayList();
		rhs = new ArrayList();
		for(int i = 0; i < levelsPair.size(); i++){
			this.lhs.add(levelsPair.getLHS().get(i)); 
			this.rhs.add(levelsPair.getRHS().get(i)); 
		}
		this.utility = levelsPair.utility;
		// jlog.log(java.util.logging.Level.INFO, "Created a LevelsPair with size " + lhs.size());
	}
	
	public List<Level> getLHS(){
		return lhs;
	}
	
	public List<Level> getRHS(){
		return rhs;
	}
	
	public void addToLHS(Level lhs, Level rhs){
		this.lhs.add(lhs);
		this.rhs.add(rhs);
	}
	
	
	public int size(){
		return lhs.size();
	}
	
	public void swapLevels(int index){
		if(index >= lhs.size())
			throw new IllegalArgumentException("The index is out of range. Index is " + index + " but range is 0 - " + (lhs.size() - 1));
		//jlog.log(java.util.logging.Level.INFO, "Swap element at " + index);
		//jlog.log(java.util.logging.Level.INFO, "Size of levels is" + lhs.size());
		
		Level swap;
		swap = lhs.get(index);
		lhs.set(index, rhs.get(index));
		rhs.set(index, swap);
	}
	
	
	public void calculateUtilities() throws Exception{
		utility_lhs = utility.getFinalUtility(lhs);
		utility_rhs = utility.getFinalUtility(rhs);
	}
	
	public double getUtilityForLhs()
	{
		return utility_lhs;
	}
	public double getUtilityForRhs()
	{
		return utility_rhs;
	}
	
	public double getUtilityDifference(){
		return Math.abs(utility_lhs - utility_rhs);
	}

	public int compareTo(Object o) {
		if(o instanceof LevelsPair){
			LevelsPair af = (LevelsPair) o;
			return new Double(this.getUtilityDifference()).compareTo(af.getUtilityDifference());
		}
		return 0;
	}
	
	
}