package de.uni_leipzig.iwi.gilbreth.optimization.domain_specific;

import java.util.Collection;

import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;

public class SimulatedAnnealingPure extends SimulatedAnnealingWithLinOp {

	protected Individual neighbor(Individual individual){
		Individual newIndividual = new Individual(individual);
		
		if(random.nextBoolean()){
			neighborX(newIndividual);
		}else{
			neighborP(newIndividual);
		}

		return newIndividual;
	}
	

	/**
	 * @param genotype
	 */
	protected void neighborP(Individual individual){
		// Dont change the initital price of the 0 product
		int r = 1;
	
		boolean isSet = false;
		while(!isSet){
			r = 1 + (individual.getP().length > 1 ? random.nextInt(individual.getP().length - 1) : 0);
			for(int j = 0; j < individual.getX()[0].length; j++){
				isSet = individual.getX()[r][j];
				if(isSet) break;
			}
			if(isSet) break;
		}
		double value = 0.0d;
		value = individual.getP()[r] + random.nextDouble() * description.priceStep(r);
		
		individual.getP()[r] = 
			value >= description.lowerPriceBound(r) && 
			value <= description.upperPriceBound(r) ? value : description.lowerPriceBound(r);
	}
}
