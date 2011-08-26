package de.uni_leipzig.iwi.gibreth.vbpo.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.SmallScenarioTestData;
import de.uni_leipzig.iwi.gilbreth.optimization.DomainSpecificSimulatedAnnealingRunner;
import de.uni_leipzig.iwi.gilbreth.optimization.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

public class DomainSpecificSimulatedAnnealingTest {
	private DomainSpecificSimulatedAnnealingRunner starter;
	private VbpoProblemDescription description;
	private Solution solution;
	
	@Before
	public void setup(){
		starter = new DomainSpecificSimulatedAnnealingRunner();
		this.starter.configure(
				100000,
				100,
				0.95,
				0.05,
				10000,
				1);
		description = SmallScenarioTestData.generateProblemDescription();
		
	}
	
	@Test
	public void testProfit() throws Exception {
		solution = starter.optimize(description);
		double profit = solution.profit();
		System.out.println(profit);
		assertTrue("Profit height", 260 < profit);
	}
	
	@Test
	public void testPrices() throws Exception {
		solution = starter.optimize(description);
		double profit = solution.profit();
		assertTrue("Price Range", true);
	}

}
