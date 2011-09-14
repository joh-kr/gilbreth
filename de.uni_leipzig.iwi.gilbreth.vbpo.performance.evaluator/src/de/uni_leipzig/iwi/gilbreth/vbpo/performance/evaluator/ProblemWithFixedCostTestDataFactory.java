package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

/**
 * A TestDataGenerator that generates TestData that can also be solved analytically.
 * 
 * @author Johannes MŸller
 * @version 0.1
 *
 */
public class ProblemWithFixedCostTestDataFactory extends AbstractTestDataFactory{

	
	

	
	
	
	/**
	 * creates a profit equation for the transformed SPPD with the substituted variables b_j.
	 * Contains a fixed cost term.
	 * @param n number of b
	 * @return a string with the profit equation.
	 * @throws Exception
	 */
	protected String profitEquation(int n) throws Exception {
		StringBuilder equation = new StringBuilder();
		equation.append(sumContributionMargin(1, n)).append(MINUS)
				.append(sumFixedCost(1, n));

		return equation.toString();
	}

	/**
	 * creates the sum of the contributation margins of all products in mathpiper syntax.
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	private StringBuilder sumContributionMargin(int start, int end)
			throws Exception {
		StringBuilder term = new StringBuilder();
		term.append(OPEN_BRACKET);
		for (int j = start; j <= end; j++) {
			term.append(q(j, end));
			term.append(TIMES).append(OPEN_BRACKET).append(p(j)).append(MINUS)
					.append(KAPPA).append(TIMES).append("2/" + 3 * (end - j))
					.append(TIMES).append(B).append(j).append("^").append("2")
					.append(CLOSE_BRACKET);
			if (j != end) {
				term.append(PLUS);
			}
		}
		term.append(CLOSE_BRACKET);
		return term;
	}

	/**
	 * creates a string in mathpiper syntax that represents the sum of the fixed cost
	 * of the products.
	 * @param start
	 * @param end
	 * @return
	 */
	private StringBuilder sumFixedCost(int start, int end) {
		StringBuilder term = new StringBuilder();

		term.append("1/3").append(TIMES).append(KAPPA).append(TIMES)
				.append(OPEN_BRACKET);
		for (int j = start; j <= end; j++) {
			term.append(OPEN_BRACKET).append(B).append(j).append("^")
					.append("2").append(CLOSE_BRACKET);
			if (j != end) {
				term.append(PLUS);
			}
		}
		term.append(CLOSE_BRACKET);
		return term;
	}


	// ------- VbpoProblemDescription Helper ------------
	
	protected int[] qs(int m){
		int[] qs = new int[m];
		for(int i = 0; i < m; i++){
			qs[i] = q(i, m);
		}
		return qs;
	}
	
	protected double[] cv(int n, double k, double[] b){
		double[] cv = new double[n];
		for(int j = 0; j < n; j++){
			cv[j] = 2/3*(k/(n-j))*b[j]*b[j];
		}
		return cv;
	}
	
	protected double[] cf(int n, double k, double[] b){
		double[] cf = new double[n];
		for(int j = 0; j < n; j++){
			cf[j] = 1/3*k*b[j]*b[j];
		}
		return cf;
	}
	
	protected boolean[][] a(int n){
		boolean[][] a = new boolean[n][n];
		for(int j = 0; j < n; j++){
			for(int l = 0; l < n; l++){
				a[l][j] = l == j;
			}
		}
		return a;
	}
	
	protected double[] ca(int o){
		double[] ca = new double[o];
		for(int j = 0; j < o; j++){
			ca[j] = 0.0d;
		}
		return ca;
	}
	
	protected double[] w(int m){
		double[] w = new double[m];
		for(int i = 0; i < m; i++){
			w[i] = 0.0d;
		}
		return w;
	}

}
