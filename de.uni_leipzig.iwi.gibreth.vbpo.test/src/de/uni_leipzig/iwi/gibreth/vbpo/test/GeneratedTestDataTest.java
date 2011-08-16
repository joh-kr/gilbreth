package de.uni_leipzig.iwi.gibreth.vbpo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.GeneratedTestDataFactory;

public class GeneratedTestDataTest {
	private GeneratedTestDataFactory testData = new GeneratedTestDataFactory();
	
	@Before
	public void setup() throws Exception{	

	}
	
	
	@After
	public void tearDown(){

	}
	
	
	@Test
	public void test() throws Exception{
		System.out.print(testData.profitEquation(250));
	}

}
