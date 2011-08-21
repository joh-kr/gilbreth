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

package de.uni_leipzig.iwi.gilbreth.optimization;

import java.util.ArrayList;
import java.util.Collection;

import org.opt4j.config.Task;
import org.opt4j.config.TaskStateListener;
import org.opt4j.core.Archive;
import org.opt4j.core.Individual;
import org.opt4j.optimizer.sa.CoolingSchedulesModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule.Type;
import org.opt4j.start.Opt4JTask;

import com.google.inject.Module;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoDecoder;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoGenotype;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoLinOpModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoSimulatedAnnealingModule;

/**
 * Starts a Opt4J Optimization task for a given SPLProblemDescription.
 * 
 * @author Johannes Müller
 *
 */
public class HeadlessStarter extends Runner{
	
	private Opt4JTask task;
	
	public Solution optimize(VbpoProblemDescription description){
		this.description = description;
		
		configureOpt4J();
		return runOptimization();
	}
	
	private void configureOpt4J() {
		
		VbpoSimulatedAnnealingModule annealingModule = new VbpoSimulatedAnnealingModule();
		annealingModule.setChangeIterations(changeIterations);
		annealingModule.setDelta(delta);
		annealingModule.setIterations(maxIterations);
		
		CoolingSchedulesModule coolingModules = new CoolingSchedulesModule();
		coolingModules.setAlpha(alpha);
		coolingModules.setInitialTemperature(initialTemp);
		coolingModules.setFinalTemperature(finalTemp);
		coolingModules.setType(Type.EXPONENTIAL);

		VbpoModule sPLModule = new VbpoLinOpModule();
		sPLModule.setProblemDescription(description);
		
		Collection<Module> modules = new ArrayList<Module>();
		modules.add(sPLModule);
		modules.add(annealingModule);
		modules.add(coolingModules);

		task = new Opt4JTask(false);
		task.init(modules);
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
		System.out.println(solution);
		return solution;
	}
}
