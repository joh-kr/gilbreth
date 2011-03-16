package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Transient;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Concept{
	@Transient
	protected static Logger jlog =  Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
	private HashMap<Attribute, Level> attributeLevelPairs = new HashMap<Attribute, Level>();
	
	public void addAttributeAndLevel(Attribute attribute, Level level){
		jlog.log(java.util.logging.Level.INFO, "Add: " + attribute.name + " " + level.name);
		attributeLevelPairs.put(attribute, level);
	}
	
	public List<Attribute> getAttributes(){
		List<Attribute> attributes= new ArrayList<Attribute>();
		for(Attribute a : attributeLevelPairs.keySet()){
			attributes.add(a);
		}
		return attributes;
	}
	
	public Level getLevelOf(Attribute attribute){
		return attributeLevelPairs.get(attribute);
	}
	
	public Boolean isValid() {
		Boolean isValid = true;
		
		
		List<Constraint> constraints = Constraint.findAll();
		List<Level> allLevels = Level.findAll();
		
		for(Constraint c : constraints) {
			
			List<Level> objectLevels = new ArrayList<Level>();
			
			for(Level l : allLevels) {
				Boolean levelContainsFeature = false;
				if(Arrays.asList(l.features.split(",")).contains(c.object)) {
					levelContainsFeature = true;
				}

				if(levelContainsFeature) {
					objectLevels.add(l);
				}
			}
			
			for(Level objectLevel : objectLevels) {
				if(attributeLevelPairs.containsValue(objectLevel)) {
					Boolean subjectLevelExists = false; 
					for(Level l : attributeLevelPairs.values()) {
						if(Arrays.asList(l.features.split(",")).contains(c.subject)) {
							subjectLevelExists = true;
						}
					}
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
