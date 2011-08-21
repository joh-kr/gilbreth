/*   
 * Copyright 2011 Johannes Müller, University of Leipzig
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.uni_leipzig.iwi.gilbreth.optimization;

import de.uni_leipzig.iwi.gilbreth.optimization.helper.Helper;

/**
 * Contains the values that describe the SPL Optimization problem.
 * 
 * @author Johannes Müller
 * 
 */
public class VbpoProblemDescription implements java.io.Serializable{

	// ---- Data Member Section ----

	/**
	 * 
	 */
	private static final long serialVersionUID = -7181164848196429106L;

	public static class Competition implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 3936873322900673027L;
		private double[] w;

		public Competition(double[] w) {
			this.w = w;
		}

		public double[] getW() {
			return w;
		}

		public double getW(int i) {
			return w[i];
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("Competition with following characteristics: \n");
			sb.append("W: \n");
			sb.append(Helper.toString(w));

			return sb.toString();
		}

	}

	public static class Customer implements java.io.Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7431287357357783966L;

		private int[] q;

		// willingness to pay of segment i for product j
		private double[][] wtp;

		public Customer(int[] q, double[][] wtp) {
			this.q = q;
			this.wtp = wtp;
		}

		public int getQ(int i) {
			return q[i];
		}

		public double getWTP(int i, int j) {
			return wtp[i][j];
		}

		public int numberOfSegments() {
			return wtp.length;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("Customer with following characteristics: \n");
			sb.append("Q: \n");
			sb.append(Helper.toString(q));
			
			sb.append("WTP: \n");
			sb.append(Helper.toString(wtp));			
			
			
			return sb.toString();
		}
	}

	public static class Firm implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6963030573889670413L;

		private double[] cv;

		private double[] cf;

		private double[] ca;

		private boolean[][] a;

		public Firm(double[] cv, double[] cf, double[] ca, boolean[][] a) {
			if (cv.length != cf.length)
				throw new IllegalArgumentException(
						"Variable and fixed cost not equal. Must be equal because each product owns its cv and cf.");
			if (a[0].length != ca.length)
				throw new IllegalArgumentException(
						"The cost array of asset cost is unequal to the number of assets in the problem description.");
			if (a.length != cf.length)
				throw new IllegalArgumentException(
						"The number of products in array a is unequal to the number in cf.");

			this.cv = cv;
			this.ca = ca;
			this.cf = cf;
			this.a = a;
		}

		public boolean getA(int j, int k) {
			return a[j][k];
		}

		public double getCa(int i) {
			return ca[i];
		}

		public double getCf(int i) {
			return cf[i];
		}

		public double getCv(int i) {
			return cv[i];
		}

		public int NumberOfAssets() {
			return a[0].length;
		}

		public int NumberOfProducts() {
			return cv.length;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("Firm with following characteristics: \n");
			sb.append("A: \n");
			sb.append(Helper.toString(a));
			
			sb.append("Ca: \n");
			sb.append(Helper.toString(ca));			

			sb.append("Cf: \n");
			sb.append(Helper.toString(cf));	
			
			sb.append("Cv: \n");
			sb.append(Helper.toString(cv));	
			
			return sb.toString();
		}
		
	}

	private Competition competition;

	// ---- Constructor Section ----

	private Customer customer;

	// ---- Getter/Setter Section

	private Firm firm;

	private int price_steps;

	public VbpoProblemDescription(){} 
	
	public VbpoProblemDescription(Customer customer, Firm firm,
			Competition competition, int price_steps) {
		this.customer = customer;
		this.firm = firm;
		this.competition = competition;
		this.price_steps = price_steps;
	}

	// ---- Method Section ----

	public Competition getCompetition() {
		return competition;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Firm getFirm() {
		return firm;
	}

	public double lowerPriceBound(int j) {
		return firm.getCv(j);

	}

	// --- internal class section ----

	public double priceRange(int i) {
		return upperPriceBound(i) - lowerPriceBound(i);
	}

	public double priceStep(int i) {
		return priceRange(i) / price_steps;
	}

	public double upperPriceBound(int j) {
		if (customer.numberOfSegments() <= 0)
			throw new IllegalArgumentException(
					"To calculate a upper bound for the price at least one customer segment has to be specified! ");

		double max = customer.getWTP(0, j);

		for (int i = 1; i < customer.numberOfSegments(); i++) {
			max = max < customer.getWTP(i, j) ? customer.getWTP(i, j) : max;
		}

		return max;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName() + " with following characteristics: \n");
		sb.append(competition.toString());
		sb.append(customer.toString());
		sb.append(firm.toString());
		return sb.toString();
	}

}
