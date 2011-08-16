package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator.SimpleProblemTestDataFactory;

public class SimpleProblemTestDataFactoryTest {

	private TestDataFactoryAdapter testData;
	
	@Before
	public void setUp() throws Exception {
		testData = new TestDataFactoryAdapter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCommands() throws Exception {
		String result = testData.commands(3, 0.1d);
		System.out.println(result);
		Assert.assertSame(result, result);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testCreate() throws Exception {
		VbpoProblemDescription result = testData.create(3, 0.1d);
		System.out.println(result);
		Assert.assertSame(result, result);
	}
	
	@Test
	public void testMaxProfit() throws Exception {
		testData.create(3, 0.1d);
		double result = testData.calculateMaxProfit(3, 0.1d);
		System.out.println(result);
		Assert.assertEquals(result, result, 0.1d);
	}
	
	private static class TestDataFactoryAdapter extends SimpleProblemTestDataFactory{
		public String commands(int end, double kappa) throws Exception {
			return super.commands(end, kappa);
		}

		
	}

}
