package de.uni_leipzig.iwi.gibreth.vbpo.test.testdata;

import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

public class PaperTestData implements ITestDataFactory {

	@Override
	public VbpoProblemDescription create() {
		VbpoProblemDescription problem = new VbpoProblemDescription(
				createCustomerDescription(),
				createFirmDescription(), 
				createCompetitionDescription(), 
				100);
		
		return problem;
	}
	
	
	private static VbpoProblemDescription.Customer createCustomerDescription() {
		int[] q = {	23, 
					60};
		double[][] wtp = {{ 0.0, 1.8, 0.7, 2.5, 3.0 }, 
				          { 0.0, 0.7, 0.4, 3.0, 3.2 }};

		return new VbpoProblemDescription.Customer(q, wtp);
	}

	private static VbpoProblemDescription.Firm createFirmDescription() {
		double[] cv = { 0.0, 0.0, 0.0, 0.0, 0.0 };
		double[] cf = { 0.0, 3.5, 3.0, 4.5, 7.0 };

		// Products/Assets  0-As
		boolean[][] a = { { true , false, false, false, false}, 
				          { false, true , false, false, true}, 
				          { false, false, false, true , true}, 
				          { false, true , true , false, true}, 
				          { false, true , true , true , true} };
		 

		// Cost per asset
		double[] ca = { 0.0, 30.0, 40.0, 10.0, 100.0};

	
		return new VbpoProblemDescription.Firm(cv, cf, ca, a);
	}

	private static VbpoProblemDescription.Competition createCompetitionDescription() {
		double[] w = { 0.3, 0.0};
		// Here we need  some calculations
		// its the best competitor for each segments. That has to be calculated
		// before.

		return new VbpoProblemDescription.Competition(w);
	}

	
}
