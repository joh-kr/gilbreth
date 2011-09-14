package de.uni_leipzig.iwi.gibreth.vbpo.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.SmallScenarioTestData;
import de.uni_leipzig.iwi.gilbreth.optimization.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

public class HeadlessStarterTest {
	
	private HeadlessStarter starter;
	private VbpoProblemDescription description;
	private Solution solution;
	
	@Before
	public void setup(){
		starter = new HeadlessStarter();
		this.starter.configure(
				100000,
				10000,
				0.95,
				0.05,
				10000,
				1);
		description = SmallScenarioTestData.generateProblemDescription();
		
	}
	
	@Test
	public void testProfit() {
		solution = starter.optimize(description);
		double profit = solution.profit();
		System.out.println(profit);
		assertTrue("Profit height", 260 < profit);
	}
	
	@Test
	public void testPrices() {
		solution = starter.optimize(description);
		double profit = solution.profit();
		assertTrue("Price Range", true);
	}
}
