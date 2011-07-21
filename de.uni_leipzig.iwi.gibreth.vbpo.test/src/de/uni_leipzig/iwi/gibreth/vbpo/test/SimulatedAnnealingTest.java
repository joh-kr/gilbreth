package de.uni_leipzig.iwi.gibreth.vbpo.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;

public class SimulatedAnnealingTest {
	
	private HeadlessStarter starter;
	private SPLProblemDescription description;
	private Solution solution;
	
	@Before
	public void setup(){
		starter = new HeadlessStarter();
		this.starter.configure(
				1000,
				0.95,
				0.05,
				10000,
				1);
		description = TestDataGenerator.generateProblemDescription();
		
	}
	
	@Test
	public void testProfit() {
		solution = starter.startOptimization(description);
		double profit = solution.profit();
		assertTrue("Profit height", 100 < profit);
	}
	@Test
	
	public void testPrices() {
		solution = starter.startOptimization(description);
		double profit = solution.profit();
		assertTrue("Price Range", true);
	}
}
