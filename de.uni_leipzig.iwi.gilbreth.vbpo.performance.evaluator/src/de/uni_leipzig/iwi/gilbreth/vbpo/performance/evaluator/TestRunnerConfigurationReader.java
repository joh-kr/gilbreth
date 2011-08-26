package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class TestRunnerConfigurationReader {

	private static final char COLUMN_SEPARATOR = '\t';
	
	public static TestConfiguration[] read(String path) throws IOException{
		CSVReader csvReader = new CSVReader(new FileReader(path), COLUMN_SEPARATOR);
		
		
		return transform(csvReader.readAll());
	}
	
	private static TestConfiguration[] transform(List<String[]> list){
		TestConfiguration[] configurations = new TestConfiguration[list.size()];
		
		TestConfiguration configuration;
		for(int i = 0; i < configurations.length; i++){
			configuration = new TestConfiguration(
					Integer.parseInt(list.get(i)[0]),
					Integer.parseInt(list.get(i)[1]),
					Double.parseDouble(list.get(i)[2]),
					Double.parseDouble(list.get(i)[3]),
					Double.parseDouble(list.get(i)[4]),
					Double.parseDouble(list.get(i)[5]),
					Integer.parseInt(list.get(i)[6]),
					Integer.parseInt(list.get(i)[7]));
			configurations[i] = configuration;
		}
		return configurations;
	}
}
