package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Transient;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Concept{
	@Transient
	protected static Logger jlog =  Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
	private HashMap<Attribute, Level> attributeLevelPairs = new HashMap<Attribute, Level>();
	
	double utility;
	
	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public void addAttributeAndLevel(Attribute attribute, Level level){
		// jlog.log(java.util.logging.Level.INFO, "Add: " + attribute.name + " " + level.name);
		attributeLevelPairs.put(attribute, level);
	}
	
	public List<Attribute> getAttributes(){
		List<Attribute> attributes= new ArrayList<Attribute>();
		for(Attribute a : attributeLevelPairs.keySet()){
			attributes.add(a);
		}
		return attributes;
	}
	
	/*
	 * Returns a unsorted list of all levels in the concept
	 */
	public List<Level> getLevels()
	{
		return new ArrayList(attributeLevelPairs.values());
	}
	
	public Level getLevelOf(Attribute attribute){
		return attributeLevelPairs.get(attribute);
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
				if(attributeLevelPairs.containsValue(objectLevel)) {
					Boolean subjectLevelExists = false; 
					for(Level l : attributeLevelPairs.values()) {
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
	
} 
