package de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing;

import java.util.ArrayList;
import java.util.Collection;

import org.opt4j.benchmark.dtlz.DTLZModule;
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
 * @author jo
 *
 */
public class HeadlessStarter {
	
	private Opt4JTask task;
	private SPLProblemDescription description;
	public Solution startOptimization(SPLProblemDescription description){
		this.description = description;
		
		configureOpt4J();
		return runOptimization();
	}
	
	private void configureOpt4J() {
		
		SPLSimulatedAnnealingModule annealingModule = new SPLSimulatedAnnealingModule();
		annealingModule.setChangeIterations(1000);
		annealingModule.setDelta(0.7);
		annealingModule.setIterations(10000);
		
		CoolingSchedulesModule coolingModules = new CoolingSchedulesModule();
		coolingModules.setAlpha(0.995d);
		coolingModules.setInitialTemperature(4000.00d);
		coolingModules.setFinalTemperature(1.00d);
		coolingModules.setType(Type.HYPERBOLIC);

		SPLModule sPLModule = new SPLModule();
		sPLModule.setProblemDescription(description);
		
		
		ViewerModule viewer = new ViewerModule();
		viewer.setCloseOnStop(true);
		
		
		Collection<Module> modules = new ArrayList<Module>();
		modules.add(sPLModule);
		modules.add(annealingModule);
		modules.add(coolingModules);
		
		//modules.add(viewer);

		task = new Opt4JTask(false);
		task.init(modules);
		

	}

	private Solution runOptimization() {
		Solution solution = null;
		try {
			task.execute();
			Archive archive = task.getInstance(Archive.class);
			if(archive.size() > 0){
				Individual individual = archive.iterator().next();
				SPLDecoder decoder = new SPLDecoder(description);
				solution = decoder.decode((SPLGenotype)individual.getGenotype());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			task.close();
			return solution;
		}

	}
}
