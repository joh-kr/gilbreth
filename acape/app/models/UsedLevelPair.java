package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Class used to persist a level pair already used during the pairs utility stage
 * 
 * @TODO Inflexible class as it is fixed to two levels on each side but for now easy to persist
 * @author Max Lillack
 * 
 */
@Entity
public class UsedLevelPair extends Model {
	@ManyToOne
	@JoinColumn(name="LHS1_ID")
	private Level lhs1;
	@ManyToOne
	@JoinColumn(name="LHS2_ID")
	private Level lhs2;
	@ManyToOne
	@JoinColumn(name="RHS1_ID")
	private Level rhs1;
	@ManyToOne
	@JoinColumn(name="RHS2_ID")
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
