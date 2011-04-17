package models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class PriceEstimationStage extends Stage {
	Random r = new Random();
	
	private PriceSettings priceSettings;
	private Utility utility;
	
	private enum Action {
		buy,
		noBuy
	}
	private Action lastAction = null;
	boolean finished = false;
	
	public PriceEstimationStage(Interview interview, Result result, PriceSettings priceSettings) {
		super(interview, result);
		this.priceSettings = priceSettings;
		this.utility = new Utility(result);
	}
	
	// initialize pricePerUtility
	public void initializePricePerUtility() throws Exception {
		PricedConcept concept = getPricedConcept();
		BigDecimal middlePrice = priceSettings.maximumPrice.add(priceSettings.minimumPrice).divide(new BigDecimal(2));
		
		result.pricePerUtilityUnit = middlePrice.doubleValue() / concept.getUtility();
		result.save();
	}
	
	private BigDecimal getPrice(Concept c) throws Exception
	{
		//int randomAdd = r.nextInt(priceSettings.maximumPrice.subtract(priceSettings.minimumPrice).intValue());
		double price = getUtility(c) * result.pricePerUtilityUnit;
		
		// Adjust to minimum price
		price = Math.max(price, priceSettings.minimumPrice.doubleValue());
		
		// Adjust to maximum price
		price = Math.min(price, priceSettings.maximumPrice.doubleValue());
		
		price = Math.round(price);
		
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
	 * Increase PricePerUtilityUnit by 0.5
	 */
	public void BuyConcept() {
		// increase by 5%
		result.pricePerUtilityUnit += 0.5;
		result.save();
		setFinished(Action.buy);
	}

	/*
	 * Decrease PricePerUtilityUnit by 0.5
	 */
	public void DoNotBuyConcept() {
		result.pricePerUtilityUnit -= 0.5;
		result.save();
		setFinished(Action.noBuy);
	}	
	
	/*
	 * Finish state after point of wtp is crossed
	 */
	private void setFinished(Action action) {
		if(lastAction != null && action != lastAction) {
			// choose middle between two last points
			if(action == Action.buy) {
				result.pricePerUtilityUnit -= 0.25;
			} else {
				result.pricePerUtilityUnit += 0.25;
			}
			finished = true;
		}
		lastAction = action;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
}
