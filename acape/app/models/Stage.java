package models;

import java.util.logging.Logger;

/**
 * A stage represents a class of step in the survey
 * @author Johannes Müller
 *
 */
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
	
	//allow access to result for unit test
	public Result getResult()
	{
		return result;
	}
}
