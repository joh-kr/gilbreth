package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

import java.util.ArrayList;
import java.util.Collection;

import org.opt4j.core.Archive;
import org.opt4j.core.Individual;
import org.opt4j.optimizer.sa.CoolingSchedulesModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule.Type;
import org.opt4j.start.Opt4JTask;

import com.google.inject.Module;

import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoDecoder;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoGenotype;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoLinOpModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoSimulatedAnnealingModule;

public class Optimizer {

	private static final boolean PRINT_RESULT = false;
	
	private int change_iterations = 0;
	private double delta = 0;
	private int max_iteration = 0;
	private double alpha = 0;
	private double initial_temp = 0;
	private double final_temp = 0;
		
	private  Opt4JTask task;
	
	public void configureAlgorithm(int change_iterations, double delta, int max_iteration,
			double alpha, double initial_temp, double final_temp) {
		this.change_iterations = change_iterations;
		this.delta = delta;
		this.max_iteration = max_iteration;
		this.alpha = alpha;
		this.initial_temp = initial_temp;
		this.final_temp = final_temp;
	}
	
	public  Solution runOptimization(VbpoProblemDescription description, boolean useLinOp) throws Exception{		
		Solution solution;
		
		VbpoSimulatedAnnealingModule annealingModule = new VbpoSimulatedAnnealingModule();
		annealingModule.setChangeIterations(change_iterations);
		annealingModule.setDelta(delta);
		annealingModule.setIterations(max_iteration);
		
		CoolingSchedulesModule coolingModules = new CoolingSchedulesModule();
		coolingModules.setAlpha(alpha);
		coolingModules.setInitialTemperature(initial_temp);
		coolingModules.setFinalTemperature(final_temp);
		coolingModules.setType(Type.EXPONENTIAL);

		VbpoModule vbpoModule = useLinOp ? new VbpoLinOpModule() : new VbpoModule();
		vbpoModule.setProblemDescription(description);
		
		Collection<Module> modules = new ArrayList<Module>();
		modules.add(vbpoModule);
		modules.add(annealingModule);
		modules.add(coolingModules);

		task = new Opt4JTask(false);
		task.init(modules);
		task.execute();
		
		Archive archive = task.getInstance(Archive.class);
		if(archive.size() > 0){
			Individual individual = archive.iterator().next();
			VbpoDecoder decoder = new VbpoDecoder(description);
			solution = decoder.decode((VbpoGenotype)individual.getGenotype());	
			if(PRINT_RESULT) System.out.println("Solution:" + solution.toString());
			return solution;
		}
		task.close();
		
		return null;
	}

}
