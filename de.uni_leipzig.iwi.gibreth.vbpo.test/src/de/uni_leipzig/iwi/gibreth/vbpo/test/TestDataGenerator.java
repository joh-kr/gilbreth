package de.uni_leipzig.iwi.gibreth.vbpo.test;


import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLProblemDescription;

public class TestDataGenerator {
	

	public static SPLProblemDescription generateProblemDescription(){

		SPLProblemDescription problem = new SPLProblemDescription(createCustomerDescription(),
				createFirmDescription(), createCompetitionDescription(), 100);
		
		return problem;
	}
	
	private static SPLProblemDescription.Customer createCustomerDescription() {
		int[] q =  { 10, 30, 50 };
		double[][] wtp = { { 0.0, 1.0, 3.0, 4.0, 5.0 }, { 0.0, 4.0, 1.0, 2.0, 1.0 }, { 0.0,
		2.0, 2.3, 4.5, 2.3 } };

		return new SPLProblemDescription.Customer(q, wtp);
	}

	private static SPLProblemDescription.Firm createFirmDescription() {
		double[] cv = { 0.0, 0.1, 0.4, 0.5, 0.01 };
		double[] cf = { 0.0, 5.0, 6.0, 1.0, 2.0 };

		// Products/Assets
		boolean[][] a = { { true, false, true, true, true, false }, { false, true, false,
		  false, true, false }, { true, true, false, false, false, false }, {
		  false, false, false, false, false, true }, { false, false, false,
		  false, false, false } };
		 

		// Cost per asset
		double[] ca = { 4.0, 3.0, 2.0, 4.0, 5.0, 7.0 };

	
		return new SPLProblemDescription.Firm(cv, cf, ca, a);
	}

	private static SPLProblemDescription.Competition createCompetitionDescription() {
		double[] w = { 0.0, 0.0, 0.0 };
		// Here we need  some calculations
		// its the best competitor for each segments. That has to be calculated
		// before.

		return new SPLProblemDescription.Competition(w);
	}
}
