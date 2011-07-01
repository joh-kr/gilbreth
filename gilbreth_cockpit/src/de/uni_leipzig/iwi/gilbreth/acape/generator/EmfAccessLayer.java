package de.uni_leipzig.iwi.gilbreth.acape.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Handles the low level access to the emf model of fama feature model.
 * 
 * 
 * @author Johannes MŸller
 *
 */
public class EmfAccessLayer {
	
	private static final String SET_RELATION_TYPE = "SetRelationType";
	
	private static final String GENERAL_FEATURE = "GeneralFeature";
	
	private static final String ATTRIBUTE_NAME = "name";
	
	private static final String ATTRIBUTE_MAX = "max";
	
	private static final String ATTRIBUTE_MIN = "min";
	
	private static final String REFERENCE_CARDINALITY = "cardinality";
	
	/**
	 * References the whole feature model with the feature tree,
	 * the requires constraints and the exclude constraints
	 */
	private EObject featureModel;
	
	/**
	 * references the feature tree
	 */
	private EObject featureTree;
	
	private List<EObject> emfRequiresConstraints;
	
	private List<EObject> emfExcludeConstraints;
	
	
	public EmfAccessLayer(EObject featureModel){
		this.featureModel = featureModel;
		setupTopLevelStructures(featureModel);
	}


	private void setupTopLevelStructures(EObject featureModel){
		// filter the complete feature model for the feature tree and constraints
		// these are the first level object in the model
		featureTree = filter(featureModel, GENERAL_FEATURE).get(0);
		emfRequiresConstraints = filter(featureModel, "RequiresType");
		emfExcludeConstraints = filter(featureModel, "ExcludeType");
	}
	
	public EObject findGeneralFeatureInCompleteTreeByName(String name){
		// Check whether the list is null or empty
		return findGeneralFeatureByName(findAllGeneralFeatures(), name).get(0);
	}
	
	public List<EObject> findAllGeneralFeatures(){
		return filterAll(featureTree, GENERAL_FEATURE);
	}
	
	public boolean isGrouped(String featureName){
		
		// That is a hack because we do not want to consider
		// The concept node as attribute, but cannot exclude it
		// somewhere else
		if(featureName.equals(getName(featureTree))) return true;
		
		//TODO check for appropriate size
		EObject feature = findGeneralFeatureByName(filterAll(featureTree, GENERAL_FEATURE), featureName).get(0);
		
		
		// The special case before the general case
		EObject relation = feature.eContainer();
		System.out.println("Relation " +  relation.eClass().getName() + "/" + featureName + (relation.eClass().getName().equals(SET_RELATION_TYPE)));
		return relation.eClass().getName().equals(SET_RELATION_TYPE);
	}
	
	public boolean isOptional(String featureName){
		
		//TODO check for appropriate size
		EObject feature = findGeneralFeatureByName(filterAll(featureTree, GENERAL_FEATURE), featureName).get(0);
		return isOptional(feature);
	}
	
	public boolean isOptional(EObject feature){
		// The special case before the general case
		EObject relation = feature.eContainer();
		String relationTypeName = relation.eClass().getName();		
		EObject cardinality = null;
		
		// SetRelations contain references to a list of cardinalities
		// Binary relations not, so we have to distinct between both cases
		if(relationTypeName.equals(SET_RELATION_TYPE)){
			// TODO check size
			cardinality = getCardinalityList(relation).get(0);
			
		}else{
			cardinality = getCardinality(relation);
		}
		return getMin(cardinality) < getMax(cardinality);
	}
	
	public boolean isTerminal(String featureName){
		//TODO check for appropriate size
		
		// That is a hack because we do not want to consider
		// The concept node as attribute, but cannot exclude it
		// somewhere else
		if(featureName.equals(getName(featureTree))) return true;
		
		EObject feature = findGeneralFeatureByName(filterAll(featureTree, GENERAL_FEATURE), featureName).get(0);
		return feature.eContents().size() == 0;
	}
	
	// ------ Model Accessing Methods Section -----
	
	/**
	 * finds those EObjects in the list source that have an attribute name that is set to the
	 * value name.
	 * 
	 * @param source the source list
	 * @param name the name of the object that is searched
	 * @return
	 */
	public List<EObject> findGeneralFeatureByName(List<EObject> source, String name){
		List<EObject> list = new ArrayList<EObject>();
		String currentName;
		
		for(EObject o : source){
			// TODO you have also to check whether o's type is GeneralFeature
			currentName = getName(o);
			if(currentName.equals(name)){
				list.add(o);
			}
		}
		
		return list;
	}
	
	public List<EObject> filterForGeneralFeature(EObject o){
		return filter(o, GENERAL_FEATURE);
	}
	
	/**
	 * Returns the content of an attribute "name" of an EObject
	 * @param o
	 * @return
	 */
	public String getName(EObject o){
		//Maybe check whether o is of type GeneralFeature
		return (String)getAttributeValue(o, ATTRIBUTE_NAME);
	}
	
	/**
	 * 
	 * @param o
	 * @return the max value of an cardinalityType object
	 */
	public int getMax(EObject o){
		return Integer.parseInt((String) getAttributeValue(o, ATTRIBUTE_MAX));
	}
	
	/**
	 * 
	 * @param o
	 * @return the min value of an cardinalityType object
	 */
	public int getMin(EObject o){
		return Integer.parseInt((String) getAttributeValue(o, ATTRIBUTE_MIN));
	}	
	
	
	/**
	 * 
	 * @param o
	 * @return the cardinalityType object of an relation-object
	 */
	public EObject getCardinality(EObject o){	
		return (EObject)o.eGet(findReference(o, "cardinality"));
	}

	/**
	 * 
	 * @param o
	 * @return the cardinalityType object of an relation-object
	 */
	public List<EObject> getCardinalityList(EObject o){	
		return (List<EObject>) o.eGet(findReference(o, REFERENCE_CARDINALITY));
	}
	
	/**
	 * 
	 * @param o
	 * @param attributeName
	 * @return the EStructuralFeature-Object representing a attribute of a given EObject o
	 */
	private Object getAttributeValue(EObject o, String attributeName){
		return o.eGet(findAttribute(o, attributeName));
	}
	
	/**
	 * finds for a given EObject an attribute of the corresponding type with the given name or null,
	 * if the attribute is not present in the type
	 * 
	 * @param o the E
	 * @param name the name of the attribute that is searched for
	 * @return
	 */
	private EAttribute findAttribute(EObject o, String name){
		EAttribute attribute = null;
		for(EAttribute a: o.eClass().getEAllAttributes()){
			if(a.getName().equals(name)){
				attribute = a;
				break;
			}
		}
		
		return attribute;
	}
	
	private EReference findReference(EObject o, String name){
		EReference reference = null;
		for(EReference a: o.eClass().getEAllReferences()){
			if(a.getName().equals(name)){
				reference = a;
				break;
			}
		}
		
		return reference;
	}
	
	private List<EReference> findReferenceList(EObject o, String name){
		List<EReference> references = new ArrayList<EReference>();
		for(EReference a: o.eClass().getEAllReferences()){
			if(a.getName().equals(name)){
				references.add(a);
			}
		}
		
		return references;
	}
	
	
	/**
	 * filters all direct and indirect objects in below parent by its typeName
	 * 
	 * @param parent
	 * @param typeName
	 * @return
	 */
	private List<EObject> filterAll(EObject parent, String typeName){
		List<EObject> list = new ArrayList<EObject>();
		EObject o = null;
		TreeIterator<EObject> iter = featureTree.eAllContents();
		while(iter.hasNext()){
			o = iter.next();
			if(o.eClass().getName().equals(typeName)){
				list.add(o);
			}
		}
		
		return list;
	}
	
	/**
	 * filters all direct EObjects below parent by its typeName
	 * @param parent
	 * @param typeName
	 * @return
	 */
	public List<EObject> filter(EObject parent, String typeName){
		List<EObject> list = new ArrayList<EObject>();
		
		for(EObject o : parent.eContents()){
			if(o.eClass().getName().equals(typeName)){
				list.add(o);
			}
		}
		
		return list;
	}
}
