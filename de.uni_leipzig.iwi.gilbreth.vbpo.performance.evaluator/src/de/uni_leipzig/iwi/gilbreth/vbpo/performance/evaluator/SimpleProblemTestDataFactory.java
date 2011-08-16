package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

/**
 * A TestDataGenerator that generates TestData that can also be solved analytically.
 * 
 * @author Johannes MŸller
 * @version 0.2
 *
 */
public class SimpleProblemTestDataFactory extends AbstractTestDataFactory{


	/**
	 * creates a profit equation for the transformed SPPD with the substituted variables b_j.
	 * Does not contain a fixed cost term.
	 * @param n number of b
	 * @return a string with the profit equation.
	 * @throws Exception
	 */
	protected String profitEquation(int n) throws Exception {
		StringBuilder equation = new StringBuilder();
		equation.append(sumContributionMarginWOFixedCost(1, n));

		return equation.toString();
	}

	/**
	 * creates the sum of the contributation margins of all products in mathpiper syntax in
	 * the case no fixed cost is considered.
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	private StringBuilder sumContributionMarginWOFixedCost(int start, int end)
			throws Exception {
		StringBuilder term = new StringBuilder();
	//	term.append(OPEN_BRACKET);
		for (int j = start; j < end; j++) {
			term
			.append(OPEN_BRACKET)
				.append(p(j))
				.append(MINUS)
				.append(KAPPA)
				.append(TIMES).append(B).append(j).append("^").append("2")
			.append(CLOSE_BRACKET)
			.append(PLUS);
		}
		term
	//	.append(OPEN_BRACKET)
			.append(p(end))
			.append(MINUS)
			.append(KAPPA)
			.append(TIMES).append(B).append(end).append("^").append("2");
	//	.append(CLOSE_BRACKET);
		
	//	term.append(CLOSE_BRACKET);
		return term;
	}
	
	


	// ------- VbpoProblemDescription Helper ------------
	

	
	protected int[] qs(int m){
		int[] qs = new int[m+1];
		for(int i = 0; i < qs.length; i++){
			qs[i] = 1;
		}
		return qs;
	}
	
	protected double[] cv(int n, double k, double[] b){
		double[] cv = new double[n + 1];
		cv[0] = 0.0d;
		for(int j = 1; j <= n; j++){
			cv[j] = k*b[j - 1]*b[j - 1];
		}
		return cv;
	}
	
	protected double[] cf(int n, double k, double[] b){
		double[] cf = new double[n + 1];
		cf[0] = 0.0d; // zero product
		for(int j = 1; j < cf.length; j++){
			cf[j] = 0.0d;
		}
		return cf;
	}
	
	protected boolean[][] a(int n){
		boolean[][] a = new boolean[n+1][n+1];
		for(int j = 0; j < n+1; j++){
			for(int l = 0; l < n+1; l++){
				a[l][j] = l == j;
			}
		}
		return a;
	}
	
	protected double[] ca(int o){
		double[] ca = new double[o+1];
		ca[0] = 0.0d; //zero product
		for(int j = 0; j <= o; j++){
			ca[j] = 0.0d;
		}
		return ca;
	}
	
	protected double[] w(int m){
		double[] w = new double[m+1];// because of the symmetry of products and customer segments
		for(int i = 0; i < m; i++){
			w[i] = 0.0d;
		}
		return w;
	}
	


}
