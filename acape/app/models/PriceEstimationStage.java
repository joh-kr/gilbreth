package models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class PriceEstimationStage extends Stage {
	Random r = new Random();
	public PriceEstimationStage(Interview interview, Result result) {
		super(interview, result);
	}
	
	public PricedConcept getPricedConcept(){
		
		PricedConcept concept = new PricedConcept();
		List<Attribute> attributes = Attribute.findAll();
		do {
			for(Attribute a : attributes){
				List<Level> levels = a.getLevels(result.excludedLevels);
				int n = r.nextInt(levels.size());
				concept.addAttributeAndLevel(a, levels.get(n));
			}
		} while(!concept.isValid());
		
		concept.setPrice(new BigDecimal(r.nextInt(1000)));

		
		return concept;
	}

}
