package de.uni_leipzig.iwi.gibreth.vbpo.test;

import java.util.ArrayList;
import java.util.Collection;

import org.opt4j.optimizer.sa.CoolingSchedulesModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule.Type;
import org.opt4j.start.Opt4JTask;

import com.google.inject.Module;

import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.ITestDataFactory;
import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.PaperTestData;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoLinOpModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoSimulatedAnnealingModule;



public class Opt4JTaskFactory {
	
	private Opt4JTask task;
	
	private VbpoProblemDescription description;
	private int    max_iterations = 10000;
	private int    change_iterations = 10000;
	private double delta = 0.1d;
	private double alpha = 0.995d;
	private int    initial_temp = 4000;
	private int    final_temp = 1;
	
	private boolean initialized = false;

	public Opt4JTaskFactory(){
		initialized = false;
	}
	
	public Opt4JTaskFactory(int max_iterations, int change_iterations, double delta,
			double alpha, int initial_temp, int final_temp, VbpoProblemDescription description) {
		init(max_iterations, 
			change_iterations, 
			delta,
			alpha, 
			initial_temp, 
			final_temp, 
			description);
	}
	
	public Opt4JTask create() throws Exception{	
		setup();
		if(!initialized) throw new Exception("Task was not configured properly.");
		return task;
	}

	private void setup(){		
		VbpoSimulatedAnnealingModule annealingModule = new VbpoSimulatedAnnealingModule();
		annealingModule.setChangeIterations(change_iterations);
		annealingModule.setDelta(delta);
		annealingModule.setIterations(max_iterations);
		
		CoolingSchedulesModule coolingModules = new CoolingSchedulesModule();
		coolingModules.setAlpha(alpha);
		coolingModules.setInitialTemperature(initial_temp);
		coolingModules.setFinalTemperature(final_temp);
		coolingModules.setType(Type.EXPONENTIAL);

		VbpoModule vbpoModule = new VbpoLinOpModule();
		vbpoModule.setProblemDescription(description);
		
		Collection<Module> modules = new ArrayList<Module>();
		modules.add(vbpoModule);
		modules.add(annealingModule);
		modules.add(coolingModules);

		task = new Opt4JTask(false);
		task.init(modules);
	}
	
	public void init(int max_iterations, int change_iterations,
			double delta, double alpha, int initial_temp, int final_temp, VbpoProblemDescription description) {
		this.max_iterations = max_iterations;
		this.change_iterations = change_iterations;
		this.delta = delta;
		this.alpha = alpha;
		this.initial_temp = initial_temp;
		this.final_temp = final_temp;
		this.description = description;
		
		initialized = true;
	}
}
