package models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class PriceEstimationStage extends Stage {
	Random r = new Random();
	public PriceEstimationStage(Interview interview, Result result) {
		super(interview, result);
		// TODO Auto-generated constructor stub
	}
	
	public PricedConcept getPricedConcept(){
		
		PricedConcept concept = new PricedConcept();
		List<Attribute> attributes = Attribute.findAll();
		for(Attribute a : attributes){
			List<Level> levels = a.getLevels();
			int n = r.nextInt(levels.size());
			concept.addAttributeAndLevel(a, levels.get(n));
		}
		
		concept.setPrice(new BigDecimal(r.nextInt(1000)));

		
		return concept;
	}

}
