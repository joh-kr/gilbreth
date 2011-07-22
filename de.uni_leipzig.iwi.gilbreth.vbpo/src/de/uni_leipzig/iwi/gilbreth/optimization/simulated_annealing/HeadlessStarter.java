/**
 * Copyright 2011 Johannes Müller, University of Leipzig
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
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

package de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing;

import java.util.ArrayList;
import java.util.Collection;

import org.opt4j.benchmark.dtlz.DTLZModule;
import org.opt4j.config.Task;
import org.opt4j.config.TaskStateListener;
import org.opt4j.core.Archive;
import org.opt4j.core.Individual;
import org.opt4j.optimizer.ea.EvolutionaryAlgorithmModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule.Type;
import org.opt4j.start.Opt4JTask;
import org.opt4j.viewer.ViewerModule;

import com.google.inject.Module;

/**
 * Starts a Opt4J Optimization task for a given SPLProblemDescription.
 * 
 * @author Johannes Müller
 *
 */
public class HeadlessStarter {
	
	private Collection<IterationChangedListener> iterationChangedListener = new ArrayList<IterationChangedListener>();
	
	private Opt4JTask task;
	private VbpoProblemDescription description;
	
	private int maxIterations = 10000;
	private double delta = 0.1d;
	private double alpha = 0.995d;
	private int initialTemp = 4000;
	private int finalTemp = 1;
	private int workUnits = 100;
	private int eventInterval = maxIterations/workUnits;
	
	
	
	public Solution startOptimization(VbpoProblemDescription description){
		this.description = description;
		
		configureOpt4J();
		return runOptimization();
	}
	
	private void configureOpt4J() {
		
		VbpoSimulatedAnnealingModule annealingModule = new VbpoSimulatedAnnealingModule();
		annealingModule.setChangeIterations(1000);
		annealingModule.setDelta(delta);
		annealingModule.setIterations(maxIterations);
		
		CoolingSchedulesModule coolingModules = new CoolingSchedulesModule();
		coolingModules.setAlpha(0.995d);
		coolingModules.setInitialTemperature(initialTemp);
		coolingModules.setFinalTemperature(finalTemp);
		coolingModules.setType(Type.HYPERBOLIC);

		VbpoModule sPLModule = new VbpoModule();
		sPLModule.setProblemDescription(description);
		
		Collection<Module> modules = new ArrayList<Module>();
		modules.add(sPLModule);
		modules.add(annealingModule);
		modules.add(coolingModules);

		task = new Opt4JTask(false);
		task.init(modules);
	}

	/**
	 * 
	 * 
	 * @param maxIterations maximal number of iterations the algorithm shall be run
	 * @param alpha the alpha value
	 * @param delta the delta value its the difference in result the result must achieve within maxIterations to run algorithm 
	 * @param initialTemp the initial temperature (see Opt4J for details)
	 * @param finalTemp the final temperature (see Opt4J for details)
	 */
	public void configure(int maxIterations, double alpha, double delta, int initialTemp, int finalTemp){
		this.maxIterations = maxIterations;
		this.alpha = alpha;
		this.delta = delta;
		this.initialTemp = initialTemp;
		this.finalTemp = finalTemp;
		
	}
	
	private Solution runOptimization() {
		Solution solution = null;
		try {
			
			task.addStateListener(new TaskStateListener(){

				@Override
				public void stateChanged(Task task) {
					if(((Opt4JTask)task).getIteration()%eventInterval == 0){
						notifyIterationChangedListener(1);
					}

				}
			});
			
			task.execute();
			Archive archive = task.getInstance(Archive.class);
			if(archive.size() > 0){
				Individual individual = archive.iterator().next();
				VbpoDecoder decoder = new VbpoDecoder(description);
				solution = decoder.decode((VbpoGenotype)individual.getGenotype());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			task.close();
		}
		return solution;
	}
	
	public int getFullWorkUnits(){
		return workUnits;
	}
	
	public void addIterationChangedListener(IterationChangedListener listener){
		iterationChangedListener.add(listener);
	}
	public void removeIterationChangedListener(IterationChangedListener listener){
			iterationChangedListener.remove(listener);
	}
	
	private void notifyIterationChangedListener(int iteration){
		for(IterationChangedListener listener: iterationChangedListener){
			listener.iterationChanged(iteration);
		}
	}
}
