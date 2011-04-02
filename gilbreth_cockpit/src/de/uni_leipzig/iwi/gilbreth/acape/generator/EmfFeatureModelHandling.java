package de.uni_leipzig.iwi.gilbreth.acape.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.util.*;
import org.uncommons.maths.combinatorics.CombinationGenerator;

import acape.AcapeFactory;
import acape.Level;

/**
 * Provides the possible calculations and operations on the Emf Model.
 * Especially it provides the levels for a given set of attribute features.
 * 
 * @author Johannes MŸller
 */
public class EmfFeatureModelHandling {

	private EmfAccessLayer access;
	
	/**
	 * Creates an instance of a EmfFeatureModelHandling Object
	 * @param featureModel
	 */
	public EmfFeatureModelHandling(EObject featureModel){
		access = new EmfAccessLayer(featureModel);
	}

	/**
	 * calculates the list of levels for a given attribute feature
	 * 
	 * @param factory the acape factory that creates new instances of acape model elements
	 * @param attributeName the name of the attribute feature for which the levels are to be created
	 * @return a list of levels for the given attribute feature name
	 */
	public List<acape.Level> createLevelsFor(AcapeFactory factory, String attributeName){
		List<acape.Level> levels = new ArrayList<acape.Level>();
		
		EObject attributeFeature = access.findGeneralFeatureInCompleteTreeByName(attributeName);
		
		acape.Level level = createLevel("", "",new ArrayList<acape.Feature>(), null, factory);
	
		constructLevelFor(levels, null, attributeFeature, factory);
		
		for(acape.Level l : levels){
			System.out.println(l);
		}
		
		return levels;
	}
	
	private void addElementsTo(List<EObject> subBinaryRelations, List<EObject> mandatory, List<EObject> optional){
		for(EObject o : subBinaryRelations){
			EObject subfeature = access.filterForGeneralFeature(o).get(0);
			String name = access.getName(subfeature);
			
			if(access.isOptional(subfeature)){
				optional.add(subfeature);
			}else{
				mandatory.add(subfeature);
			}
		}
	}
	
	private List<acape.Level> combineMandatoryLevelAndOptionalLevels(acape.Level mandatory, List<acape.Level> optional, acape.AcapeFactory factory){
		// Add to each possible optional feature combination the mandatory level
		List<acape.Level> mandatoryOptionalLevels = new ArrayList<acape.Level>();
		acape.Level completeLevel = null;
		for(acape.Level l : optional){
			for(acape.Feature f : l.getFeatures()){
				completeLevel = createLevel(mandatory.getName() + l.getName(), "", 
						merge(mandatory.getFeatures(), l.getFeatures()), 
						null, factory);
				mandatoryOptionalLevels.add(completeLevel);
			}
		}
		return mandatoryOptionalLevels;
	}
	
	private List<List<acape.Level>> createGroupFeatureLevels(List<EObject> subSetRelations, acape.AcapeFactory factory){
		List<List<acape.Level>> groupedFeatureLevels = new ArrayList<List<acape.Level>>();
		for(EObject o : subSetRelations){
			List<EObject> subfeatures = access.filterForGeneralFeature(o);
			// TODO rework the min max determination: min and max are in the cardinalitytype object this is a referenced list in a setRelation
			EObject cardinality = access.getCardinalityList(o).get(0);
			groupedFeatureLevels.add(
					calculateGroupFeatureCombinations(
							subfeatures, access.getMin(cardinality), 
							access.getMax(cardinality), factory));
		}
		return groupedFeatureLevels;
	}
	
	private List<acape.Level> combineSolitaryAndGroupedFeatureLevels(List<acape.Level> mandatoryOptionalLevels, List<List<acape.Level>> groupedFeatureLevels, acape.AcapeFactory factory){
		List<acape.Level> resultList = new ArrayList<acape.Level>();
		for(int i = 0; i < mandatoryOptionalLevels.size(); i++){
			for(int j = 0; j < groupedFeatureLevels.size(); j++){
				for(int k = 0; k < groupedFeatureLevels.get(j).size(); k++){
					resultList.add(createLevel(
						mandatoryOptionalLevels.get(i).getName() + 
						groupedFeatureLevels.get(j).get(k).getName(), 
						"", 
						merge(mandatoryOptionalLevels.get(i).getFeatures(), 
								groupedFeatureLevels.get(j).get(k).getFeatures()), 
						null, 
						factory));
				}
			}
		}
		return resultList;
	}
	
	private void constructLevelFor(List<acape.Level> levels, acape.Level level, EObject feature, acape.AcapeFactory factory){
		List<EObject> subBinaryRelations = access.filter(feature, "BinaryRelationType");
		List<EObject> subSetRelations    = access.filter(feature, "SetRelationType");
		
		List<EObject> mandatory    = new ArrayList<EObject>();
		List<EObject> optional    = new ArrayList<EObject>();
		
		List<acape.Level> optionalFeatureLevelCombinations = null;
		
		boolean initRecursion = level == null;
		
		acape.Level newLevel = level;
		
		addElementsTo(subBinaryRelations, mandatory, optional);
		
		
		// Create one big level for all mandatory sub features of a feature
		acape.Level mandatoryLevel = createMandatoryLevel(mandatory, factory);
		
		// Combine all optional features in all possible ways
		optionalFeatureLevelCombinations = combineFeatures(optional, factory);
		
		// Add to each possible optional feature combination the mandatory level
		List<acape.Level> mandatoryOptionalLevels = combineMandatoryLevelAndOptionalLevels(mandatoryLevel, optionalFeatureLevelCombinations, factory);
		
		
		List<List<acape.Level>> groupedFeatureLevels = createGroupFeatureLevels(subSetRelations, factory);
		
		List<acape.Level> resultList = combineSolitaryAndGroupedFeatureLevels(mandatoryOptionalLevels, groupedFeatureLevels, factory);
			
		for(acape.Level l : resultList){
			for(acape.Feature f : l.getFeatures()){
				constructLevelFor(levels, l, access.findGeneralFeatureByName(feature.eContents(), f.getName()).get(0), factory);
			}
			if(subBinaryRelations.size() == 0 && !initRecursion) levels.add(newLevel);
		}
		
		//if(subBinaryRelations.size() == 0 && !initRecursion) levels.add(newLevel);
	}
	
	
	private List<acape.Level> calculateGroupFeatureCombinations(List<EObject> list, int min, int max, acape.AcapeFactory factory){
		List<acape.Level> result = new ArrayList<acape.Level>();
		
		String name = null;
		String temp_name = null;
		List<acape.Feature> features = null;
		for(int i = min; i < max; i++){
			name = null;
			temp_name = null;
			features = new ArrayList<acape.Feature>();
			for(int j = 0; j < i; j++){
				temp_name = access.getName(list.get(j));
				name += temp_name;
				features.add(createFeature(temp_name, factory));
			}
			result.add(createLevel(name, "",features, null, factory));
		}
		
		return result;
	}
	
	/**
	 * combines a list of optional features to every possible combination
	 * and returns a list of acape.Levels on the basis of these combinations.
	 * 
	 * @param list
	 * @param factory
	 * @return
	 */
	private List<acape.Level> combineFeatures(List<EObject> list, acape.AcapeFactory factory){
		List<acape.Level> result = new ArrayList<acape.Level>();
		List<EObject> temp = null;
		String name = null;
		List<acape.Feature> features = null;
		CombinationGenerator<EObject> generator = null;
		for(int i = 0; i < list.size(); i++){
			generator = new CombinationGenerator<EObject>(list, i);
			temp = generator.nextCombinationAsList();
			name = "";
			String temp_name = null;
			features = new ArrayList<acape.Feature>();
			for(int j = 0; j < temp.size(); j++){
				temp_name = access.getName(temp.get(j));
				name += temp_name;
				features.add(createFeature(temp_name, factory));
			}
			result.add(createLevel(name, "",features, null, factory));
		}
		
		return result;
	}
	
	private acape.Level createMandatoryLevel(List<EObject> list, acape.AcapeFactory factory){
		String name = "";
		String temp_name = null;
		List<acape.Feature> features = new ArrayList<acape.Feature>();
		for(int j = 0; j < list.size(); j++){
			temp_name = access.getName(list.get(j));
			name += temp_name;
			features.add(createFeature(temp_name, factory));
		}
		return createLevel(name, "",features, null, factory);
	}
	
	private List<acape.Feature> merge(List<acape.Feature> a, List<acape.Feature> b){
		List<acape.Feature> result = new ArrayList<acape.Feature>();
		for(acape.Feature f : a) result.add(f);
		for(acape.Feature f : b) result.add(f);
		
		return result;
		
	}
	
	/**
	 * initializes an empty level.
	 * @param factory
	 * @return
	 */
	private Level initializeLevel(acape.AcapeFactory factory){
		Level l = factory.createLevel();
		l.setDescription("");
		l.setName("");
		return l;
	}
	
	private acape.Feature createFeature(String name, acape.AcapeFactory factory){
		acape.Feature feature = factory.createFeature();
		feature.setName(name);
		return feature;
	}
	
	private Level createLevel(String name, String description, List<acape.Feature> features, acape.Feature feature, acape.AcapeFactory factory){
		acape.Level newLevel =factory.createLevel();
		newLevel.setName(name);
		newLevel.setDescription(description);
		// set Description if you have an idea where to place one
		
		for(acape.Feature f: features){
			newLevel.getFeatures().add(f);
		}
		if(feature != null) newLevel.getFeatures().add(feature);
		
		return newLevel;
	}
	
	
	// 
	public boolean isOptional(String name){
		return access.isOptional(name);
	}
	
	public boolean isTerminal(String name){
		return access.isTerminal(name);
	}
	
	public boolean isGrouped(String name){
		return access.isGrouped(name);
	}
	
}
