package models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class PriceEstimationStage extends Stage {
	Random r = new Random();
	
	private PriceSettings priceSettings;
	private Utility utility;
	
	public PriceEstimationStage(Interview interview, Result result, PriceSettings priceSettings) {
		super(interview, result);
		this.priceSettings = priceSettings;
		this.utility = new Utility(result);
	}
	
	private BigDecimal getPrice(Concept c) throws Exception
	{
		//int randomAdd = r.nextInt(priceSettings.maximumPrice.subtract(priceSettings.minimumPrice).intValue());
		double price = getUtility(c) * result.pricePerUtilityUnit;
		
		// Adjust to minimum price
		price = Math.max(price, priceSettings.minimumPrice.doubleValue());
		
		// Adjust to maximum price
		price = Math.min(price, priceSettings.maximumPrice.doubleValue());
		
		return new BigDecimal(price);
	}
	
	private double getUtility(Concept c) throws Exception {
		return Math.max(utility.computeUtilityFor(c.getLevels()), 1);
	}
	
	public PricedConcept getPricedConcept() throws Exception{
		
		PricedConcept concept = new PricedConcept();
		List<Attribute> attributes = Attribute.findAll();
		do {
			for(Attribute a : attributes){
				List<Level> levels = a.getLevels(result.excludedLevels);
				int n = r.nextInt(levels.size());
				concept.addAttributeAndLevel(a, levels.get(n));
			}
		} while(!concept.isValid());
		
		concept.setPrice(getPrice(concept));
		
		// allow only concept utility >= 1
		concept.setUtility(getUtility(concept));

		return concept;
	}
	
	/*
	 * Increase PricePerUtilityUnit by 5%
	 */
	public void BuyConcept(double utility) {
		if(utility * result.pricePerUtilityUnit < priceSettings.minimumPrice.doubleValue()) {
			// if concept would be prices below minimum, increase above minimum
			result.pricePerUtilityUnit = (priceSettings.minimumPrice.doubleValue() * 1.05) / result.pricePerUtilityUnit; 
		}else {
			// increase by 5%
			result.pricePerUtilityUnit *= 1.05;
		}
		result.save();
	}

	/*
	 * Decrease PricePerUtilityUnit by 1%
	 */
	public void DoNotBuyConcept(double utility) {
		result.pricePerUtilityUnit *= 0.99;
		result.save();
	}	
	
}
