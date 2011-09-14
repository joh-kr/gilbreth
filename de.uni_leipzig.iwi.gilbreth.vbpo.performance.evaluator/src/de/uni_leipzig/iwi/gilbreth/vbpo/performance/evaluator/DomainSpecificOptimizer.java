package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

import de.uni_leipzig.iwi.gilbreth.optimization.DomainSpecificPureSimulatedAnnealingRunner;
import de.uni_leipzig.iwi.gilbreth.optimization.DomainSpecificSimulatedAnnealingRunner;
import de.uni_leipzig.iwi.gilbreth.optimization.Runner;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

public class DomainSpecificOptimizer extends Optimizer {
	Runner runner = new DomainSpecificPureSimulatedAnnealingRunner();
	
	public void configureAlgorithm(int change_iterations, double delta, int max_iteration,
			double alpha, double initial_temp, double final_temp) {
		
		runner.configure(max_iteration, change_iterations, alpha, delta, (int)initial_temp, (int)final_temp);

	}
	
	public  Solution runOptimization(VbpoProblemDescription description, boolean useLinOp) throws Exception{		
		return runner.optimize(description);
	}
}
