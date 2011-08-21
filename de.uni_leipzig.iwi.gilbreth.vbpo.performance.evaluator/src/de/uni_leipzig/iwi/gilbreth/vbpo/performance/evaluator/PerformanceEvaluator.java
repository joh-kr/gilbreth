package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;




public class PerformanceEvaluator {


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SimpleProblemTestDataFactory testData = new SimpleProblemTestDataFactory();
		Optimizer optimizer = new Optimizer();
		licenceText();
		
		if(args.length < 3){ 
			help();
		}else{
			int        min    = Integer.parseInt(args[0]);
			int        max    = Integer.parseInt(args[1]);
			double     kappa  = Double.parseDouble(args[2]);
			
			TestRunner runner = new TestRunner("./result.csv", optimizer, testData, min, max, kappa);
	
			long start = System.currentTimeMillis();
			runner.start();
			long end = System.currentTimeMillis();
		
			System.out.println("The evaluation is finished now.\n" +
				"It took " +(end - start) +"ms to perform the evaluation.");
		}
		
	}
	
	private static void help(){
		System.out.println("\nUsage:\n\n"
						  +"java PerformanceEvaluator <start> <end> <kappa>\n"
						  +"\n"
						  +"start: the minimum size of the evaluated problems\n"
						  +"end  : the maximum size of the evaluated problems\n"
						  +"kappa: a adjustment parameter 0<=k<=1 that controls the cost\n");
	}
	
	
	private static void licenceText(){
		System.out.println(
				">>>> Performance Evaluation Tool <<<<\n\n"
				+"------------------------------------------------------------------------\n" 
				+"Copyright 2011 Johannes MŸller, IWi University of Leipzig\n"
				+"Licensed under the Apache License, Version 2.0 (the \"License\");\n"
				+"you may not use this file except in compliance with the License.\n"
				+"You may obtain a copy of the License at\n"
				+"\n"
				+"http://www.apache.org/licenses/LICENSE-2.0\n"
				+"\n"
				+"Unless required by applicable law or agreed to in writing, software\n"
				+"distributed under the License is distributed on an \"AS IS\" BASIS,\n"
				+"WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
				+"See the License for the specific language governing permissions and\n"
				+"limitations under the License.\n\n"
				+"------------------------------------------------------------------------\n"
				+"\n\n");
	}
	
	public static void writeFinish(){
		
	}
}

