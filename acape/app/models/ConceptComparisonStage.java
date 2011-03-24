package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ConceptComparisonStage extends Stage{

	public ConceptComparisonStage(Interview interview, Result result) {
		super(interview, result);
	}
	
	public List<Concept> calculateConcepts(){
		Random r = new Random();
		List<Concept> concepts = new ArrayList<Concept>();
		
		while(concepts.size() < 3) {
			Concept concept = new Concept();
			List<Attribute> attributes = Attribute.findAll();
			for(Attribute a : attributes){
				List<Level> levels = a.getLevels(result.excludedLevels);
				int n = r.nextInt(levels.size());
				
				concept.addAttributeAndLevel(a, levels.get(n));
			}
			if(concept.isValid()) {
				concepts.add(concept);
			}
			
		}
		
		return concepts;
	}
	
}
