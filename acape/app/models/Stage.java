package models;

import java.util.logging.Logger;

public class Stage {
	Result result;
	Interview interview;
	
	protected static Logger jlog =  Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
	AttributeIterator attributeIterator = new AttributeIterator();
	
	public Stage(Interview interview, Result result){
		this.interview = interview;
		this.result = result;
	}
	
	public boolean hasAttribute(int index){
		return attributeIterator.hasAttribute(index);
	}
	
	public Attribute getCurrentAttribute(int index){
		Attribute a = attributeIterator.getCurrentAttribute(index);
		
		// remove excluded Levels
		a.levels.removeAll(result.excludedLevels);
		
		return a;
	}
}
