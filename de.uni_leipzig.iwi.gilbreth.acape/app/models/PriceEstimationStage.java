package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math.stat.regression.SimpleRegression;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.hamcrest.core.IsEqual;

/**
 * Implementation of the last stage of acape
 * 
 * The interviewee is presented with some priced concepts and decides if she would buy or not.
 * The algorithm estimates two points in the price utility space and uses linear regression to
 * estimate a linear function to estimate the willingness to pay for any utility value.
 * 
 * @author Max Lillack
 *
 */
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
	
	/**
	 * Initialize list of valid concepts and initial utility and prices
	 * @throws Exception
	 */
	public void initializePricePerUtility() throws Exception {
		BigDecimal middlePrice = priceSettings.maximumPrice.add(priceSettings.minimumPrice).divide(new BigDecimal(2));
		
		result.price1 = middlePrice;
		result.price2 = result.price1;
		
		/*
		 * Set utility for every valid concept
		 */
		for(Concept concept : result.validConcepts) {
			concept.setUtility(utility.computeCalibratedUtilityFor(concept.getLevels()));
		}
		Collections.sort(result.validConcepts);
		
		// get concept with utility above median 
		result.utility1 = result.validConcepts.get((int) (result.validConcepts.size() * 0.75)).getUtility();
		// get concept with utility below median
		result.utility2 = result.validConcepts.get((int) (result.validConcepts.size() * 0.25)).getUtility();
		
		result.save();
	}
	
	public double getPrice(Concept c) throws Exception
	{
		if(result.PEintercept == null || result.PEslope == null) {
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
	
	private Boolean conceptIsUsed(Concept concept) {
		Boolean isUsed = false;
		for (Concept c : result.usedConcepts) {
			isUsed = isUsed || c.id == concept.id;
		}
		return isUsed;
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
		
		while(i < result.validConcepts.size() && result.validConcepts.get(i).getUtility() <= referenceUtility) {
			i++;
		}
		
		i = Math.max(0, i - 1);
		
		Concept concept = result.validConcepts.get(i);
		
		int offset = 1;
		// Do not use one of the last concepts again
		while(conceptIsUsed(concept)) {
			int index = Math.min(i + offset, result.validConcepts.size() - 1);
			index = Math.max(index, 0);
			
			concept = result.validConcepts.get(index);
			
			offset = (offset > 0) ? -offset : -(offset - 1);
		}
		
		// remove last item from used concepts
		// @TODO remove size of list to config
		if(result.usedConcepts.size() > 4) {
			result.usedConcepts.remove(0);
		}
		// add current concept to used concepts
		result.usedConcepts.add(concept);
		
		result.save();
		
		PricedConcept pricedConcept = new PricedConcept();
		// TODO Maybe add some copy constructor
		for(Level l : concept.getLevels()) {
			pricedConcept.addLevel(l);
		}
	
		if(iteration == 1) {
			pricedConcept.setPrice(result.price1);
		} else {
			pricedConcept.setPrice(result.price2);
		}
		
		pricedConcept.setUtility(getUtility(concept));

		return pricedConcept;
	}
	
	/**
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

	/**
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
			iteration++;
			lastAction = null;
		} else {
			lastAction = action;
		}
		if(iteration > 2) {
			setFinished(action);
		}
	}
	
	/**
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
	
}
