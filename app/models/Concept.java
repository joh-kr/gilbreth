package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Concept{
	
	private HashMap<Attribute, Level> attributeLevelPairs = new HashMap<Attribute, Level>();
	
	public void addAttributeAndLevel(Attribute attribute, Level level){
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
	
} 
