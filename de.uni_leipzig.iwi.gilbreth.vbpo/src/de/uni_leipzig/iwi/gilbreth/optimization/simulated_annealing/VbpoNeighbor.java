/*   
 * Copyright 2011 Johannes Müller, University of Leipzig
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing;

import org.opt4j.common.random.Rand;
import org.opt4j.core.problem.Genotype;
import org.opt4j.operator.Apply;
import org.opt4j.operator.neighbor.Neighbor;
import org.opt4j.operator.neighbor.NeighborBoolean;
import org.opt4j.operator.neighbor.NeighborDouble;
import org.opt4j.operator.normalize.NormalizeDouble;
import com.google.inject.Inject;


/**
 * Generates Neighbors to a given genotype uses domain knowledge about the SPL problem
 * and generates only such neighbors that satisfy the constraints.
 * 
 * @author Johannes Müller
 *
 */
@Apply(VbpoGenotype.class)
public class VbpoNeighbor implements Neighbor<Genotype>{

	private VbpoProblemDescription description;
	private Rand random;
	protected final NormalizeDouble normalize;
	protected final NeighborDouble doubleNeighbor;
	protected final NeighborBoolean booleanNeighbor;
	
	/**
	 * creates a new neighbor creator.
	 * 
	 * @param problem
	 * @param random
	 * @param normalize
	 */
	@Inject
	public VbpoNeighbor(VbpoProblemDescription problem, Rand random, NormalizeDouble normalize){
		
		this.description = problem;
		this.random = random;
		this.normalize = normalize;
		this.doubleNeighbor = new NeighborDouble(normalize, random);
		this.booleanNeighbor = new NeighborBoolean(random);
	}
	
	/**
	 * Creates on the basis of a given genotype a new genotype in the neighborhood
	 * of the old genotype. It ensures that the new genotype met the constraints that
	 * - each segment gets exactly one product assigned
	 * - price is within range
	 */
	public void neighbor(Genotype genotype) {
		VbpoGenotype splGenotype = (VbpoGenotype) genotype;
		
		// Randomly decide which part of the genotype to alter
		if(random.nextBoolean()){
			neighborX(splGenotype);
		}else{
			neighborP(splGenotype);
		}
	}

	/**
	 * Select a price that satisfies the constraint of the SPL portfolio
	 * optimization problem.
	 * 
	 * @param genotype
	 */
	protected void neighborP(VbpoGenotype genotype){
		// Dont change the initital price of the 0 product
		int r = 1 + random.nextInt(genotype.getP().size() - 1);
		double value = 0.0d;
		value = genotype.getP().get(r) + random.nextDouble() * description.priceStep(r);
		
//		if(value > genotype.getP().getUpperBound(r)){
//			value = genotype.getP().getLowerBound(r);
//		}
		genotype.getP().set(r, value);

		normalize.normalize(genotype.getP());
	}
	
	protected void neighborX(VbpoGenotype genotype){
			
		int i      = random.nextInt(description.getCustomer().numberOfSegments());
		int change = random.nextInt(description.getFirm().NumberOfProducts());
		
		for(int j = 0; j < description.getFirm().NumberOfProducts(); j++){
			genotype.setX(i, j, j == change);
			//genotype.getX().set(i*offset + j, j == change);
		}	
	}
}

// EOF