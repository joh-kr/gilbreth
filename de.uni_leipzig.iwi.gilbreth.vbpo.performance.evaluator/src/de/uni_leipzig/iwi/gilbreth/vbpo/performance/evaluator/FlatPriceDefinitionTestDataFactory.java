package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

public class FlatPriceDefinitionTestDataFactory extends SimpleProblemTestDataFactory {

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
		commands.append(priceDefinitions(end));
		
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
		//System.out.println(commands);
		return commands.toString();
	}
	
	private String priceDefinitions(int end){
		StringBuilder variables = new StringBuilder();
		
		String[] prices = new String[end];
		
		prices[0] = "b1";
		variables.append("p1").append(ASSIGNMENT).append(prices[0]).append(COMMAND_END).append(LINE_BREAK);
		for (int i = 1; i < end; i++) {
			prices[i] = "p" + i + "+" + u(i+1, i+1) + "-" + u(i+1, i);
			variables.append("p").append(i+1).append(ASSIGNMENT).
			append(prices[i]).append(COMMAND_END).append(LINE_BREAK);
		}

		return variables.toString();
	}
	
	protected String profitEquation(int end){
		StringBuilder term = new StringBuilder();
		//	term.append(OPEN_BRACKET);
			for (int j = 1; j < end; j++) {
				term
				.append(OPEN_BRACKET)		
					.append("p"+(j))
					.append(MINUS)
					.append(KAPPA)
					.append(TIMES).append(B).append(j).append("^").append("2")
				.append(CLOSE_BRACKET)
				.append(PLUS);
			}
			term
			.append(OPEN_BRACKET)
				.append("p"+(end))
				.append(MINUS)
				.append(KAPPA)
				.append(TIMES).append(B).append(end).append("^").append("2")
			.append(CLOSE_BRACKET);
			return term.toString();
		
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
		commands.append(priceDefinitions(end));
		commands.append(KAPPA + ":= " + kappa + ";\n");
		commands.append("equ").append(ASSIGNMENT)
		.append(profitEquation(end)).append(COMMAND_END)
		.append(LINE_BREAK);
		commands.append("equ").append(COMMAND_END);
		commands.append("]");
		
		//System.out.println(commands);
		
		mathPiperConnector.evaluate(commands.toString());
		String res = mathPiperConnector.getResult();
		
		return Double.parseDouble(res);
	}

}
