package de.uni_leipzig.iwi.gibreth.vbpo.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opt4j.core.Archive;
import org.opt4j.core.Individual;
import org.opt4j.optimizer.sa.CoolingSchedulesModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule.Type;
import org.opt4j.start.Opt4JTask;

import com.google.inject.Module;

import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.ITestDataFactory;
import de.uni_leipzig.iwi.gibreth.vbpo.test.testdata.PaperTestData;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoDecoder;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoGenotype;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoLinOpModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoSimulatedAnnealingModule;

public class LinOpSimulatedAnnealingTest {
	
	private Opt4JTask task;
	private Opt4JTaskFactory opt4JTaskFactory;
	private VbpoProblemDescription description;
	private ITestDataFactory testDataFactory;
	
	private static int MAX_ITERATIONS = 10000;
	private static int CHANGE_ITERATIONS = 10000;
	private static double DELTA = 0.1d;
	private static double ALPHA = 0.995d;
	private static int INITIAL_TEMP = 4000;
	private static int FINAL_TEMP = 1;
	
	@Before
	public void setup() throws Exception{	
		testDataFactory = new PaperTestData();
//		opt4JTaskFactory = new Opt4JTaskFactory(
//				MAX_ITERATIONS,
//				CHANGE_ITERATIONS,
//				DELTA,
//				ALPHA,
//				INITIAL_TEMP,
//				FINAL_TEMP,
//				testDataFactory.create());
//		
//		task = opt4JTaskFactory.create();
		
		description  = testDataFactory.create();
		
		VbpoSimulatedAnnealingModule annealingModule = new VbpoSimulatedAnnealingModule();
		annealingModule.setChangeIterations(CHANGE_ITERATIONS);
		annealingModule.setDelta(DELTA);
		annealingModule.setIterations(MAX_ITERATIONS);
		
		CoolingSchedulesModule coolingModules = new CoolingSchedulesModule();
		coolingModules.setAlpha(ALPHA);
		coolingModules.setInitialTemperature(INITIAL_TEMP);
		coolingModules.setFinalTemperature(FINAL_TEMP);
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
	
	
	@After
	public void tearDown(){
		task        = null;
		description = null;
	}
	
	
	@Test
	public void test() throws Exception{
		Solution solution = null;
		
		long start = System.currentTimeMillis();
		task.execute();
		long end = System.currentTimeMillis();

		System.out.println("Execution time was "+(end-start)+" ms.");
		
		
		
		Archive archive = task.getInstance(Archive.class);
		if(archive.size() > 0){
			Individual individual = archive.iterator().next();
			VbpoDecoder decoder = new VbpoDecoder(description);
			solution = decoder.decode((VbpoGenotype)individual.getGenotype());
			System.out.println("Solution:" + solution.toString());
		}
		task.close();
		
	}

}
