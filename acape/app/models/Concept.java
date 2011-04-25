package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import play.db.jpa.Model;

import com.sun.org.apache.xpath.internal.operations.Bool;

@Entity
public class Concept extends Model implements Comparable<Concept> {
	@Transient
	protected static Logger jlog =  Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
	@ManyToMany
	private List<Level> levels = new ArrayList<Level>();
	
	private double utility;
	
	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public void addLevel(Level level){
		// jlog.log(java.util.logging.Level.INFO, "Add: " + attribute.name + " " + level.name);
		levels.add(level);
	}
	
	public HashSet<Attribute> getAttributes(){		
		HashSet<Attribute> attributes = new HashSet<Attribute>();
		
		for(Level l : levels){
			attributes.add(l.attribute);
		}
		return attributes;
	}
	
	/*
	 * Returns a unsorted list of all levels in the concept
	 */
	public List<Level> getLevels()
	{
		return levels;
	}
	
	public Level getLevelOf(Attribute attribute) throws Exception{
		Level level = null;
		boolean found = false;
		for(Level l : levels) {
			if(l.attribute == attribute) {
				found = true;
				level = l;
			}
		}
		if(!found) {
			throw new Exception("Concept does not contain attribute");
		}
		
		return level;
	}
	
	/*
	 * Checks all contraints
	 */
	public Boolean isValid() {
		Boolean isValid = true;
		
		
		List<Constraint> constraints = Constraint.findAll();
		List<Level> allLevels = Level.findAll();
		
		for(Constraint c : constraints) {
			
			List<Level> objectLevels = new ArrayList<Level>();
			/*
			 * Select all levels containing the object feature
			 */
			for(Level l : allLevels) {
				Boolean levelContainsFeature = false;
				if(Arrays.asList(l.features.split(",")).contains(c.object)) {
					levelContainsFeature = true;
				}

				if(levelContainsFeature) {
					objectLevels.add(l);
				}
			}
			/*
			 * if a level with an object feature is present check if
			 * a level with the corresponding subject feature is present
			 */
			for(Level objectLevel : objectLevels) {
				if(levels.contains(objectLevel)) {
					Boolean subjectLevelExists = false; 
					for(Level l : levels) {
						if(Arrays.asList(l.features.split(",")).contains(c.subject)) {
							subjectLevelExists = true;
						}
					}
					/*
					 * the validity depends on the type of the constraint
					 */
					if(subjectLevelExists && c instanceof ExcludesConstraint) {
						isValid = false;
					}else if(!subjectLevelExists && c instanceof RequiresConstraint) {
						isValid = false;
					}
				}
			}
		}
		return isValid;
	}

	@Override
	public int compareTo(Concept c) {
		if(utility > c.utility) {
			return 1;
		} else if(utility < c.utility) {
			return -1;
		} else {
			return 0;
		}
	}
	
} 
