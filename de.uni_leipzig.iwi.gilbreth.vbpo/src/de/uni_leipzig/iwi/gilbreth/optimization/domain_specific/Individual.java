package de.uni_leipzig.iwi.gilbreth.optimization.domain_specific;

import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;


public class Individual{
	private boolean[][] x;
	private double[] p;
	private Solution s;
	private boolean individualChanged = true;
	private VbpoProblemDescription description;
	
	public Individual(VbpoProblemDescription description){
		this.description =description;
		
		int i = description.getCustomer().numberOfSegments();
		int j = description.getFirm().NumberOfProducts();
		
		this.x = new boolean[i][j];
		this.p = new double[j];
		
		for(int il = 0; il < i; il++){
			for(int jl = 0; jl < j; jl++){
				x[il][jl] = il == jl;
			}
		}			
		for(int jl = 0; jl < j; jl++){
			p[jl] = description.getFirm().getCv(jl);
		}
	}
	
	public Individual(boolean[][] x, double[] p, VbpoProblemDescription description){
		this.x = x;
		this.p = p;
		this.description = description;
	}
	
	public Individual(Individual individual){
		this.x = new boolean[individual.getX().length][individual.getX()[0].length];
		this.p = new double[individual.getP().length];
		
		for(int i = 0; i < individual.getX().length; i++){
			for(int j = 0; j < individual.getX()[0].length; j++){
				this.x[i][j] = individual.getX()[i][j];
			}
		}
		for(int j = 0; j < individual.getP().length; j++){
			this.p[j] = individual.getP()[j];
		}
		this.description = individual.description;
	}

	public boolean[][] getX() {
		return x;
	}

	public void setX(boolean[][] x) {
		this.x = x;
	}

	public double[] getP() {
		return p;
	}

	public void setP(double[] p) {
		this.p = p;
	}

	public Solution getS() {
		if(individualChanged){
			s = new Solution(x, p, description);
		}
		return s;
	}
	
	public double getProfit(){
		return getS().profit();
	}

}

