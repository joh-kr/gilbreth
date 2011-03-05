package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ConceptComparisonStage extends Stage{

	public ConceptComparisonStage(Interview interview, Result result) {
		super(interview, result);
		// TODO Auto-generated constructor stub
	}
	
	public List<Concept> calculateConcepts(){
		Random r = new Random();
		List<Concept> concepts = new ArrayList<Concept>();
		
		for(int i = 0; i < 3; i++){
			Concept concept = new Concept();
			List<Attribute> attributes = Attribute.findAll();
			for(Attribute a : attributes){
				List<Level> levels = a.getLevels(result.excludedLevels);
				int n = r.nextInt(levels.size());
				concept.addAttributeAndLevel(a, levels.get(n));
			}
			concepts.add(concept);
			
		}
		
		return concepts;
	}
	
}
