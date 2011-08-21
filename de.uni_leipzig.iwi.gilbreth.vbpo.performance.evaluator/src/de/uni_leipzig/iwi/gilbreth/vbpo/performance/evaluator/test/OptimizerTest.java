package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator.Optimizer;
import de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator.SimpleProblemTestDataFactory;
import de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator.TestDataFactory;

public class OptimizerTest {

	VbpoProblemDescription description;
	TestDataFactory testData = new SimpleProblemTestDataFactory();
	Optimizer optimizer;
	@Before
	public void setUp() throws Exception {
		optimizer = new Optimizer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void optimizeTest() throws Exception{
		int size = 5;
		double kappa = 0.1d;
		optimizer.configureAlgorithm(10000, 0.001d, 1000000, 0.995d, 50000, 1);
		description = testData.create(size, kappa);
		Solution s = optimizer.runOptimization(description, false);
		double maxProfit = testData.calculateMaxProfit(size, kappa); 
		System.out.println(s);
		System.out.println(maxProfit);
		Assert.assertTrue("Heuristic result is higher than max profit.", s.profit() <= maxProfit);

	}
}
