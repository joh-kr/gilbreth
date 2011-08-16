package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

public class MathPiperConnector {
	
	private static final boolean PRINT_RESULT = false;
	
	private org.mathpiper.interpreters.Interpreter interpreter;
	private org.mathpiper.interpreters.EvaluationResponse currentResponse;
	
	public MathPiperConnector(){
		 interpreter = org.mathpiper.interpreters.Interpreters.newSynchronousInterpreter();
		 currentResponse = null;
	}

	public void evaluate(String term){
		currentResponse = interpreter.evaluate(term);
		
	}
	
	public String getResult(){
		if(PRINT_RESULT) System.out.println("------------------------");	
		if(PRINT_RESULT) System.out.println(currentResponse.getSideEffects());
		return currentResponse.getResult();
	}
	
	public double[] getResultAsArray(){
		String result = getResult();
		result = result.replace("{", "").replace("}", "");
		String[] split = result.split(",");
		double[] values = new double[split.length];
		for(int i = 0; i < split.length; i++){
			values[i] = Double.parseDouble(split[i]);
			if(PRINT_RESULT) System.out.println(values[i]);
		}
		return values;
	}
}
