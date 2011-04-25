package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

/*
 * Inflexible class but for now easy to persist
 */
@Entity
public class UsedLevelPair extends Model {
	private Level lhs1;
	private Level lhs2;
	private Level rhs1;
	private Level rhs2;
	
	public UsedLevelPair(List<Long> lhsIds, List<Long> rhsIds)
	{
		if(lhsIds.size() > 2 || rhsIds.size() > 2) {
			throw new IllegalArgumentException("Use this class only for LevelPairs of max two levels each");
		}
		
		lhs1 = Level.findById(lhsIds.get(0));
		if(lhsIds.size() > 1) {
			lhs2 = Level.findById(lhsIds.get(1));
		}
		rhs1 = Level.findById(rhsIds.get(0));
		if(rhsIds.size() > 1) {
			rhs2 = Level.findById(rhsIds.get(1));
		}
	}
	
	public List<Level> getLHS() {
		List<Level> result = new ArrayList<Level>();
		result.add(lhs1);
		result.add(lhs2);
		return result;
	}
	
	public List<Level> getRHS() {
		List<Level> result = new ArrayList<Level>();
		result.add(rhs1);
		result.add(rhs2);
		return result;
	}
	
	public boolean equalsLevelsPair(LevelsPair lp) {
		return lp.getLHS().containsAll(getLHS()) && lp.getRHS().containsAll(getRHS()) 
			   || (lp.getLHS().containsAll(getRHS()) && lp.getRHS().containsAll(getLHS()));
	}
	
	public boolean includesLevel(Level l) {
		return (l == lhs1 || l == lhs2 || l == rhs1 || l == rhs2);
	}
}
