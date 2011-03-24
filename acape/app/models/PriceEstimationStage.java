package models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class PriceEstimationStage extends Stage {
	Random r = new Random();
	
	private PriceSettings priceSettings;
	
	public PriceEstimationStage(Interview interview, Result result, PriceSettings priceSettings) {
		super(interview, result);
		this.priceSettings = priceSettings;
	}
	
	private BigDecimal getRandomPrice()
	{
		int randomAdd = r.nextInt(priceSettings.maximumPrice.subtract(priceSettings.minimumPrice).intValue());
		return priceSettings.minimumPrice.add(new BigDecimal(randomAdd));
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
		
		concept.setPrice(getRandomPrice());

		
		return concept;
	}

}
