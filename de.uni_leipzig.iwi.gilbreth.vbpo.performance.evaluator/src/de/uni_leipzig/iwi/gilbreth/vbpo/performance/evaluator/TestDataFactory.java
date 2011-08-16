package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoProblemDescription;

public interface TestDataFactory {


	/**
	 * creates a VbpoProblemDescription with a given size u and a adjustment parameter kappa (k)
	 * The problem is designed in a way that allows to calculate the optimal configuration
	 * analytically. So these problemDescriptions can be used to assess the performance of the
	 * heuristic algorithms.
	 * 
	 * @param u the number of products and the number of segments
	 * @param k a adjustment parameter 0 <= k <= 1
	 * @return a VbpoProblemDescription
	 * @throws Exception 
	 */
	public VbpoProblemDescription create(int u, double k) throws Exception;

	/**
	 * calculates the max profit in a analytical way on the basis of a vbpoProblem
	 * generated before. So the method must not called before a Problem is generated. 
	 * 
	 * @param end
	 * @param kappa
	 * @return
	 * @throws Exception
	 */
	public double calculateMaxProfit(int end, double kappa) throws Exception;
	
}
