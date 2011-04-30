package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math.stat.regression.SimpleRegression;

public class PriceEstimationStage extends Stage {
	Random r = new Random();
	
	private PriceSettings priceSettings;
	private Utility utility;
	
	private double deltaUtility = 1;
	private BigDecimal deltaPrice = new BigDecimal(25);
	
	public int iteration;

	public enum Action {
		buy,
		noBuy
	}
	
	public Action lastAction = null; 
	

	boolean finished = false;
	
	public PriceEstimationStage(Interview interview, Result result, PriceSettings priceSettings) {
		super(interview, result);
		this.priceSettings = priceSettings;
		this.utility = new Utility(result);
		iteration = 1;
	}
	
	// initialize pricePerUtility
	public void initializePricePerUtility() throws Exception {
		BigDecimal middlePrice = priceSettings.maximumPrice.add(priceSettings.minimumPrice).divide(new BigDecimal(2));
		
		result.price1 = middlePrice;
		result.price2 = result.price1;
		
		result.randomConcepts = getRandomConcepts();
		
		result.utility1 = result.randomConcepts.get((int) (result.randomConcepts.size() * 0.75)).getUtility();
		result.utility2 = result.randomConcepts.get((int) (result.randomConcepts.size() * 0.25)).getUtility();
		
		result.save();
	}
	
	public double getPrice(Concept c) throws Exception
	{
		if(Double.isNaN(result.PEintercept) || Double.isNaN(result.PEslope)) {
			throw new Exception("Price Estimation Stage not complete");
		}
		
		double price = result.PEintercept + c.getUtility() * result.PEslope;
		// Adjust to minimum price
		price = Math.max(price, priceSettings.minimumPrice.doubleValue());
			
		// Adjust to maximum price
		price = Math.min(price, priceSettings.maximumPrice.doubleValue());
			
		price = Math.round(price);
			
		return price;
	}
	
	private double getUtility(Concept c) throws Exception {
		return utility.computeCalibratedUtilityFor(c.getLevels());
	}
	
	public PricedConcept getPricedConcept() throws Exception{
		double referenceUtility = result.utility1;
		if(iteration == 2) {
			referenceUtility = result.utility2;
		} 
		int i = 0;
		/*
		 * search concept with utility close to needed utility
		 */
		
		
		
		while(i < result.randomConcepts.size() && result.randomConcepts.get(i).getUtility() <= referenceUtility) {
			i++;
		}
		
		i = Math.max(0, i - 1);
		
		Concept concept = result.randomConcepts.get(i);
		PricedConcept pricedConcept = new PricedConcept();
		// TODO Maybe add some copy constructor
		for(Level l : concept.getLevels()) {
			pricedConcept.addLevel(l);
		}
		
		//remove used concept
		result.randomConcepts.remove(i);
		result.save();
		
		if(iteration == 1) {
			pricedConcept.setPrice(result.price1);
		} else {
			pricedConcept.setPrice(result.price2);
		}
		
		pricedConcept.setUtility(getUtility(concept));

		return pricedConcept;
	}
	
	/*
	 * Increase price per utility
	 */
	public void BuyConcept() throws Exception {		
		if(iteration == 1) {
			result.utility1 -= deltaUtility;
			result.price1 = result.price1.add(deltaPrice);
		} else {
			result.utility2 -= deltaUtility;
			result.price2 = result.price2.add(deltaPrice);
		}
		
		result.save();
		checkIteration(Action.buy);
	}

	/*
	 * Decrease price per utility
	 */
	public void DoNotBuyConcept() throws Exception {
		if(iteration == 1) {
			result.utility1 += deltaUtility;
			result.price1 = result.price1.subtract(deltaPrice);
		} else {
			result.utility2 += deltaUtility;
			result.price2 = result.price2.subtract(deltaPrice);
		}
		
		result.save();
		checkIteration(Action.noBuy);
	}
	
	private void checkIteration(Action action) throws Exception
	{
		if(lastAction != null && action != lastAction) {
			//use a new set of random concepts
			result.randomConcepts = getRandomConcepts();
			iteration++;
			lastAction = null;
		} else {
			lastAction = action;
		}
		if(iteration > 2) {
			setFinished(action);
		}
	}
	
	/*
	 * Finish state after point of wtp is crossed
	 */
	private void setFinished(Action action) {
		SimpleRegression regression = new SimpleRegression();
		
		regression.addData(result.utility1, result.price1.doubleValue());
		regression.addData(result.utility2, result.price2.doubleValue());
		
		result.PEintercept = regression.getIntercept();
		result.PEslope = regression.getSlope();
		
		result.save();
		
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	private Concept getRandomConcept() throws Exception {
		Random r = new Random();
		Concept concept = new Concept();
		List<Attribute> attributes = Attribute.findAll();
		
		for(Attribute a : attributes){
			List<Level> levels = a.getLevels(result.excludedLevels);
			int n = r.nextInt(levels.size());
			concept.addLevel(levels.get(n));
		}

		return concept;
	}
	
	private List<Concept> getRandomConcepts() throws Exception {
		List<Concept> concepts = new ArrayList<Concept>();
		
		int count = 0;
		
		while(count++ < 10000 && concepts.size() < 200) {
			Concept concept = getRandomConcept();
			if(concept.isValid()) {
				concept.setUtility(utility.computeCalibratedUtilityFor(concept.getLevels()));
				concept.save();
				concepts.add(concept);
			}
		}
		
		Collections.sort(concepts);
		
		return concepts;
	}
	
}
