package models;

import java.util.List;


/**
 * The attribute iterator realizes a paging mechanism over the list of all attributes.
 * The iterated list of attributes is sorted by name ascending.
 * 
 * @author Johannes Müller
 *
 */
public class AttributeIterator {
	
	Attribute currentAttribute;
	private boolean hasNext = false;
	
	public boolean hasAttribute(int index){
		List<Attribute> attributes = Attribute.find("order by name asc")
		.from(index).fetch(1);

		if (attributes.isEmpty()) {
			hasNext = false;
		} else{
			currentAttribute = attributes.get(0);
			hasNext = true;
		}
		return hasNext;
	}
	
	public Attribute getCurrentAttribute(int index){
		if(hasNext) return currentAttribute;
		else throw new IllegalStateException("The current attribute is requested, although no attribute is present. Probably the hasNext() is not called before or there is no next attribute.");
	}

}
