package de.uni_leipzig.iwi.gibreth.vbpo.test.testdata;

import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

public class GeneratedTestDataFactory implements ITestDataFactory {
	
	private static String B = "b";
	private static String KAPPA = "k";	
	private static String OPEN_BRACKET  = "(";
	private static String CLOSE_BRACKET = ")";
	private static String MINUS = "-";
	private static String TIMES = "*";
	private static String PLUS = "+";	
	
	private double kappa = 0.3;
	
	private int numberOfSegments = 2;
	private int numberofProducts = 5;
	private int numberOfAssets   = 7;
	
	//private org.mathpiper.interpreters.Interpreter interpreter;
	
	public GeneratedTestDataFactory(){
		//interpreter = org.mathpiper.interpreters.Interpreters.newSynchronousInterpreter();
	}
	
	@Override
	public VbpoProblemDescription create() {
		/*
		VbpoProblemDescription problem = new VbpoProblemDescription(
				createCustomerDescription(),
				createFirmDescription(), 
				createCompetitionDescription(), 
				100);
		*/
		return null;
	}
	
	public String profitEquation(int n) throws Exception{
		StringBuilder equation = new StringBuilder();
		equation.append(sumContributionMargin(1, n)).append(MINUS).append(sumFixedCost(1, n));
		
		return equation.toString();
	}
	
	private StringBuilder sumContributionMargin(int start, int end) throws Exception{
		StringBuilder term = new StringBuilder();
		term.append(OPEN_BRACKET);
		for(int j = start; j <= end; j++){
			term.append(end - j)
				.append(TIMES)
				.append(OPEN_BRACKET)
				.append(price(j))
				.append(MINUS)
				.append(KAPPA)
				.append(TIMES)
				.append("2/" + 3 * (end - j))
				.append(TIMES)
				.append(B)
				.append(j)
				.append("^")
				.append("2")
				.append(CLOSE_BRACKET);
			if(j != end){
				term.append(PLUS);
			}
		}
		term.append(CLOSE_BRACKET);
		return term;
	}
	
	private StringBuilder sumFixedCost(int start, int end){
		StringBuilder term = new StringBuilder();
		
		term.append("1/3")
			.append(TIMES)
			.append(KAPPA)
			.append(TIMES)
			.append(OPEN_BRACKET);
		for(int j = start; j <= end; j++){
			term.append(OPEN_BRACKET)
				.append(B)
				.append(j)
				.append("^")
				.append("2")
				.append(CLOSE_BRACKET);
			if(j != end){
				term.append(PLUS);
			}
		}
		term.append(CLOSE_BRACKET);
		return term;
	}
	
	private StringBuilder price(int j) throws Exception{
		StringBuilder p_jm1 = new StringBuilder();
		StringBuilder term = new StringBuilder();
		System.out.println("Recursion depth: " + j);
		
		if(j == 0) throw new Exception("Unsupported depth " + j);
		
		if(j == 1){
			term.append(B).append(j);
		}else{
			if(j == 2){
				p_jm1.append("0");
			}else{
				p_jm1.append(price(j-1));
			}
			term.append(OPEN_BRACKET);
			term.append(j)
				 .append(TIMES)
				 .append(OPEN_BRACKET)
				 .append(B)
				 .append(j)
				 .append(MINUS)
				 .append(B)
				 .append(j - 1)
				 .append(CLOSE_BRACKET)
				 .append(PLUS)
				 .append(p_jm1);
			term.append(CLOSE_BRACKET);
			
		}
		
	
		return term;
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
