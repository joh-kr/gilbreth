package de.uni_leipzig.iwi.gilbreth.optimization;

import java.util.ArrayList;
import java.util.Collection;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.IterationChangedListener;

public abstract class Runner {

	private Collection<IterationChangedListener> iterationChangedListener = new ArrayList<IterationChangedListener>();
	protected VbpoProblemDescription description;
	protected int maxIterations = 10000;
	protected int changeIterations = 10000;
	protected double delta = 0.1d;
	protected double alpha = 0.995d;
	protected int initialTemp = 4000;
	protected int finalTemp = 1;
	private int workUnits = 100;
	protected int eventInterval = maxIterations/workUnits;
	protected boolean configured = false;

	
	
	public abstract Solution optimize(VbpoProblemDescription description) throws Exception;
	
	
	/**
	 * 
	 * 
	 * @param maxIterations maximal number of iterations the algorithm shall be run
	 * @param alpha the alpha value
	 * @param delta the delta value its the difference in result the result must achieve within maxIterations to run algorithm 
	 * @param initialTemp the initial temperature (see Opt4J for details)
	 * @param finalTemp the final temperature (see Opt4J for details)
	 */
	public void configure(int maxIterations, int changeIterations, double alpha,
			double delta, int initialTemp, int finalTemp) {
				this.maxIterations = maxIterations;
				this.changeIterations = changeIterations;
				this.alpha = alpha;
				this.delta = delta;
				this.initialTemp = initialTemp;
				this.finalTemp = finalTemp;
				
				this.configured = true;
				
			}

	
	
	
	public int getFullWorkUnits() {
		return workUnits;
	}

	public void addIterationChangedListener(IterationChangedListener listener) {
		iterationChangedListener.add(listener);
	}

	public void removeIterationChangedListener(IterationChangedListener listener) {
			iterationChangedListener.remove(listener);
	}

	protected void notifyIterationChangedListener(int iteration) {
		for(IterationChangedListener listener: iterationChangedListener){
			listener.iterationChanged(iteration);
		}
	}

}
