package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoProblemDescription;

public abstract class AbstractTestDataFactory implements TestDataFactory {
	protected static String B = "b";
	protected static String KAPPA = "k";
	protected static String OPEN_BRACKET = "(";
	protected static String CLOSE_BRACKET = ")";
	protected static String OPEN_LIST_BRACKET = "{";
	protected static String CLOSE_LIST_BRACKET = "}";
	protected static String MINUS = "-";
	protected static String TIMES = "*";
	protected static String PLUS = "+";
	protected static String ASSIGNMENT = ":=";
	protected static String COMMAND_END = ";";
	protected static String LINE_BREAK = "\n";
	
	private boolean problemCreated = false;
	
	protected double[] b;
	protected String deriveResult;
	
	protected MathPiperConnector mathPiperConnector = new MathPiperConnector();
	
	
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
	public VbpoProblemDescription create(int u, double k) throws Exception {
		
		String term = commands(u, k);
		mathPiperConnector.evaluate(term);
		deriveResult = mathPiperConnector.getResult();
		b = mathPiperConnector.getResultAsArray();
		
		VbpoProblemDescription problem = new VbpoProblemDescription(
				 createCustomerDescription(u, u, b), createFirmDescription(u, k, b),
				 createCompetitionDescription(u), 100);;
		 
		problemCreated = true;
		return problem;
	}
	
	/**
	 * calculates the max profit in a analytical way on the basis of a vbpoProblem
	 * generated before. So the method must not called before a Problem is generated. 
	 * 
	 * @param end
	 * @param kappa
	 * @return
	 * @throws Exception
	 */
	public double calculateMaxProfit(int end, double kappa) throws Exception{
		if(!problemCreated) throw new Exception("A problem must be created before.");

		StringBuilder commands = new StringBuilder();
		commands.append("[");
		commands.append("Unbind(*);\n");
		commands.append("b"+(0) + ":="+0+";\n");
		for(int i = 0; i < end;i++){
			commands.append("b"+(i+1) + ":="+b[i]+";\n");
		}
		commands.append(KAPPA + ":= " + kappa + ";\n");
		commands.append("result").append(ASSIGNMENT).append(deriveResult)
		.append(COMMAND_END).append(LINE_BREAK);
		commands.append("equ").append(ASSIGNMENT)
		.append(profitEquation(end)).append(COMMAND_END)
		.append(LINE_BREAK);
		commands.append("equ").append(COMMAND_END);
		commands.append("]");
		
		mathPiperConnector.evaluate(commands.toString());
		String res = mathPiperConnector.getResult();
		
		return Double.parseDouble(res);
	}
	
	/**
	 * creates a set of commands to calculate the derivatives of the introduced variables
	 * b_j
	 * 
	 * @param end the numvber of b
	 * @param kappa the adjustment parameter between cost and wtp
	 * @return a string with the compiled commands.
	 * @throws Exception
	 */
	protected String commands(int end, double kappa) throws Exception {
		String variables = variables(end);
		StringBuilder commands = new StringBuilder();
		commands.append("[");
		commands.append("Unbind(*);\n");
		commands.append("variables").append(ASSIGNMENT).append(variables)
				.append(COMMAND_END).append(LINE_BREAK);
		commands.append(KAPPA + ":= " + kappa + ";\n");
		commands.append("equ").append(ASSIGNMENT)
				.append(profitEquation(end)).append(COMMAND_END)
				.append(LINE_BREAK);
		commands.append("difResult := Differentiate(").append(variables)
				.append(") equ;").append(LINE_BREAK);
		commands.append("count := 1;\n"
				+ "solResult := {};\n"
				+ "While(count <=" + end + ")\n" + "[\n"
				+ "result:=Solve(difResult[count], variables[count]);\n"
				+ "rhs := EquationRight(result[1]);\n"
				+ "solResult := Append(solResult, rhs);\n" 
				+ "count++;\n"
				+ "];\n");
		commands.append("solResult;");
		commands.append("]");
		return commands.toString();
	}
	
	/**
	 * creates a list of the variables in mathpiper syntax.
	 * @param end
	 * @return
	 */
	private String variables(int end) {
		StringBuilder variables = new StringBuilder();

		variables.append(OPEN_LIST_BRACKET);
		for (int i = 1; i < end; i++) {
			variables.append(B).append(i).append(",");
		}
		variables.append(B).append((end));
		variables.append(CLOSE_LIST_BRACKET);

		return variables.toString();
	}
	
	/**
	 * creates a string that represents the prices with the substituted b_j in 
	 * mathpiper syntax.
	 * 
	 * @param j
	 * @return
	 * @throws Exception
	 */
	protected StringBuilder p(int j) throws Exception {
		StringBuilder term = new StringBuilder();
		if (j == 0) {
			return term.append("0");
		}
		term.append(OPEN_BRACKET);
		term.append(u(j, j))
			.append(MINUS)
			.append(OPEN_BRACKET)
			.append(u(j, j - 1))
			.append(MINUS)
			.append(p(j - 1))
			.append(CLOSE_BRACKET);
		term.append(CLOSE_BRACKET);
		return term;
	}
	
	protected int q(int j, int n) {
		return n - j + 1;
	}

	protected String u(int i, int j) {
		if(i == 1) 
			return "b" + j;
		return i + "*b" + j;
	}
	
	protected double[][] wtps(int m, int n, double[] b){
		double[][] result = new double[m+1][n+1];
		result[0][0] = 0.0d; // Zero Product
		for(int i = 1; i < m+1; i++){
			for(int j = 1; j < n+1; j++){
				result[i][j] = i*b[j-1];
			}
		}
		return result;
	}
	
	private  VbpoProblemDescription.Customer createCustomerDescription(int m, int n, double[] b) {
		int[] q = qs(m);
		double[][] wtp = wtps(m, n, b);

		return new VbpoProblemDescription.Customer(q, wtp);
	}

	private  VbpoProblemDescription.Firm createFirmDescription(int n, double k, double[] b) {
		double[] cv = cv(n, k, b);
		double[] cf = cf(n, k, b);

		// Products/Assets 0-As
		boolean[][] a = a(n);

		// Cost per asset
		double[] ca = ca(n);

		return new VbpoProblemDescription.Firm(cv, cf, ca, a);
	}

	private  VbpoProblemDescription.Competition createCompetitionDescription(int m) {
		double[] w = w(m);
		// Here we need some calculations
		// its the best competitor for each segments. That has to be calculated
		// before.

		return new VbpoProblemDescription.Competition(w);
	}
	
	/**
	 * creates a profit equation for the transformed SPPD with the substituted variables b_j.
	 * Does not contain a fixed cost term.
	 * @param n number of b
	 * @return a string with the profit equation.
	 * @throws Exception
	 */
	protected abstract String profitEquation(int n) throws Exception;
	
	/**
	 * calculates the size of the segments.
	 * @param m the number of segments
	 * @return an array of sizes of the segments
	 */
	protected abstract int[] qs(int m);
	
	/**
	 * calculates the variable cost
	 * 
	 * @param n the number of products
	 * @param k the kappa parameter
	 * @param b the calculated b parameter
	 * @return
	 */
	protected abstract double[] cv(int n, double k, double[] b);
	
	/**
	 * calculates the fixed cost for the products
	 * 
	 * @param n
	 * @return
	 */
	protected abstract double[] cf(int n, double k, double[] b);
	
	/**
	 * creates an array with assets product assignements.
	 * It is assumed that the number of assets and products is equal
	 * 
	 * @param n number of products
	 * @return
	 */
	protected abstract boolean[][] a(int n);
	
	/**
	 * calculates the cost of the assets
	 * @param o the number of assets
	 * @return
	 */
	protected abstract double[] ca(int o);
	
	/**
	 * the net utility if a segments buys from the competition.
	 * @param m the number of segments.
	 * @return
	 */
	protected abstract double[] w(int m);
	
}
