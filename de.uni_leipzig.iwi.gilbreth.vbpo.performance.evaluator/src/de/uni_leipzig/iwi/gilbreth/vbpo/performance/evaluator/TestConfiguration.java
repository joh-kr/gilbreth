package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

public class TestConfiguration {

	private int max_iteration = 0;
	private int change_iterations = 0;
	private double delta = 0;
	private double alpha = 0;
	private double initial_temp = 0;
	private double final_temp = 0;
	private int size = 0;
	private int type = 0;
	
	
	public TestConfiguration(int max_iteration, int change_iterations,
			double delta, double alpha, double initial_temp, double final_temp,
			int size, int type) {
		super();
		this.max_iteration = max_iteration;
		this.change_iterations = change_iterations;
		this.delta = delta;
		this.alpha = alpha;
		this.initial_temp = initial_temp;
		this.final_temp = final_temp;
		this.size = size;
		this.type = type;
		
	}
	
	public int getMax_iteration() {
		return max_iteration;
	}
	public int getChange_iterations() {
		return change_iterations;
	}
	public double getDelta() {
		return delta;
	}
	public double getAlpha() {
		return alpha;
	}
	public double getInitial_temp() {
		return initial_temp;
	}
	public double getFinal_temp() {
		return final_temp;
	}
	public int getSize() {
		return size;
	}

	public int getType() {
		return type;
	}
}
