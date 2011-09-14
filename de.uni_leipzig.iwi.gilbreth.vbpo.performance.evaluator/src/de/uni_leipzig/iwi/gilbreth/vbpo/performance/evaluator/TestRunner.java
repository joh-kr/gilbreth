package de.uni_leipzig.iwi.gilbreth.vbpo.performance.evaluator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import de.uni_leipzig.iwi.gilbreth.optimization.Runner;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Runs a set of optimization runs and saves the results in a csv file.
 * 
 * @author jo
 *
 */
public class TestRunner {
	
	private static final char COLUMN_SEPARATOR = '\t';
	
	private static final String MAP_FILE_PATH = "map.dat";
	private static final String MAX_PROFIT_FILE_PATH = "pmap.dat";
	
	private CSVWriter csvWriter;
	private CSVReader csvReader;
	private Map<MaxProfitKey, VbpoProblemDescription> problems;
	private Map<MaxProfitKey, Double> maxProfits;
	private String csvFilePath;
	private Runner[] runners;
	private SimpleProblemTestDataFactory testData;
	private double kappa;
	
	private TestConfiguration[] configurations;
	
	private int[] sizes;
	private int tries;
	
	public TestRunner(String csvFilePath,
			SimpleProblemTestDataFactory testData, double kappa) throws IOException {
		super();
		this.csvFilePath = csvFilePath;
		this.testData = testData;
		this.kappa = kappa;
		
	}
	
	public void start() throws Exception{
		Result result = new Result();
		Solution solution = null;
		VbpoProblemDescription description;
		long start = 0;
		long end = 0;
		writeHeader();
		
		for(int c = 0; c < configurations.length; c++){
				
				System.out.println("Iteration " + c + " for problem with size " + configurations[c].getSize());
				description = getVbpoProblemDescription(configurations[c].getSize(), kappa);
				runners[configurations[c].getType()].configure(
						configurations[c].getMax_iteration(), 
						configurations[c].getChange_iterations(), 
						configurations[c].getAlpha(), 
						configurations[c].getDelta(), 
						(int)configurations[c].getInitial_temp(), 
						(int)configurations[c].getFinal_temp());
				
				
				// Run optimizer with linop
				start = System.currentTimeMillis();
				solution = runners[configurations[c].getType()].optimize(description);
				end = System.currentTimeMillis();	

				setResults(solution, (end - start), configurations[c].getType(), getMaxProfit(configurations[c].getSize(), kappa), configurations[c].getSize(), result);

				writeResult(result);
				System.out.println(result.toString());

		}
		

	}
	
	private void setResults(Solution solution, long time, int type, double maxProfit, int size, Result result){
		int builtProducts = 0;
		boolean[] y = solution.determineY();
		for(boolean built : y) builtProducts += built ? 1 : 0;
		
		result.setNr_of_products(builtProducts);
		result.setProfit(solution.profit());
		result.setTime(time);
		
		result.setProfit_max(maxProfit);
		result.setSize(size);
	}
	
	private void writeHeader() throws IOException{
		openWriter();
		csvWriter.writeNext(Result.ENTRIES);
		closeWriter();
	}
	
	private void writeResult(Result result) throws IOException{
		List<String[]> entries = readCSVFile();
		openWriter();
		// After each iteration the writer flushes the results an closes the strean
		// If the writer is opened the next time, it has to rewrite the whole content
		// because it overwrites it otherwise.
		// In case the testrunner cannot finish the whole tests the result can be used nevertheless.
		csvWriter.writeAll(entries);
		
		csvWriter.writeNext(result.asStringArray());
		
		closeWriter();
	}
	
	private List<String[]> readCSVFile() throws IOException{
		csvReader = new CSVReader(new FileReader(csvFilePath), COLUMN_SEPARATOR);
		return csvReader.readAll();
	}
	
	private void openWriter() throws IOException{
		this.csvWriter = new CSVWriter(new FileWriter(csvFilePath), COLUMN_SEPARATOR);
	}
	
	private void closeWriter() throws IOException{
		csvWriter.close();
	}
	
	private VbpoProblemDescription getVbpoProblemDescription(int i, double kappa) throws Exception{
		Map<MaxProfitKey, VbpoProblemDescription> map = getProblemsMap();
		
		MaxProfitKey key = new MaxProfitKey();
		key.setKappa(kappa);
		key.setSize(i);
		
		if(!map.containsKey(key)){
			map.put(key, testData.create(i, kappa));
			writeFile(MAP_FILE_PATH, map);
		}
		
		return map.get(key);
	}
	
	private double getMaxProfit(int i, double kappa) throws Exception{
		Map<MaxProfitKey, Double> map = getMaxProfitMap();
		
		MaxProfitKey key = new MaxProfitKey();
		key.setKappa(kappa);
		key.setSize(i);
		
		if(!map.containsKey(i)){
			map.put(key, testData.calculateMaxProfit(i, kappa));
			writeFile(MAX_PROFIT_FILE_PATH, map);
		}
		
		return map.get(key);
	}
	
	private Map<MaxProfitKey, VbpoProblemDescription> getProblemsMap(){
		if(problems != null){
			return problems;
		}else{
			problems = readGeneratedDescriptions();
			if(problems == null) problems = new Hashtable<MaxProfitKey	, VbpoProblemDescription>();
			return problems;
		}
	}

	private Map<MaxProfitKey, Double> getMaxProfitMap(){
		if(maxProfits != null){
			return maxProfits;
		}else{
			maxProfits = readMaxProfits();
			if(maxProfits == null) maxProfits = new Hashtable<MaxProfitKey, Double>();
			return maxProfits;
		}
	}
	
	private Map<MaxProfitKey, VbpoProblemDescription> readGeneratedDescriptions(){
		return (Map<MaxProfitKey, VbpoProblemDescription>) readFile(MAP_FILE_PATH);
	}
	
	private Map<MaxProfitKey, Double> readMaxProfits(){
		return (Map<MaxProfitKey, Double>) readFile(MAX_PROFIT_FILE_PATH);
	}	
	
	private Object readFile(String path){
		Object map = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(path);
			in = new ObjectInputStream(fis);
			map = in.readObject();
			in.close();
		} catch (IOException ex) {
			
		} catch (ClassNotFoundException ex) {
			
		}
		return map;
	}
	
	private void writeFile(String path, Object map){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(path);
			out = new ObjectOutputStream(fos);
			out.writeObject(map);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private static class MaxProfitKey implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -5106796792022373553L;
		private double kappa;
		private int size;
		public void setKappa(double kappa) {
			this.kappa = kappa;
		}
		public double getKappa() {
			return kappa;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getSize() {
			return size;
		}
	}
	
	private static class Result{
		
		public static final int ATTRIBUTES = 6;
		public static final String[] ENTRIES = {
			"size", "time", "profit_max",
			"profit", "nr_of_products", "type"};
		
		double size;
		
		long time;
		
		double profit_max;
		double profit;
		
		int nr_of_products;
		
		int type;
		
		public long getTime() {
			return time;
		}
		public void setTime(long time) {
			this.time = time;
		}
		public double getProfit() {
			return profit;
		}
		public void setProfit(double profit) {
			this.profit = profit;
		}
		public double getProfit_max() {
			return profit_max;
		}
		public void setProfit_max(double profit_max) {
			this.profit_max = profit_max;
		}
		public int getNr_of_products() {
			return nr_of_products;
		}
		public void setNr_of_products(int nr_of_products) {
			this.nr_of_products = nr_of_products;
		}
		public double getSize() {
			return size;
		}
		public void setSize(double size) {
			this.size = size;
		}
		
		public String[] asStringArray(){
			String[] entries = new String[Result.ATTRIBUTES];
			
			entries[0] = ""+this.getSize();
			entries[1] = ""+this.getTime();
		
			entries[2] = ""+this.getProfit_max();
			entries[3] = ""+this.getProfit();
			
			entries[4] = ""+this.getNr_of_products();
			entries[5] = ""+this.getType();
			
			return entries;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for(String s : ENTRIES) sb.append(s + "\t");
			sb.append("\n");
			String header = sb.toString();
			for(int i = 0; i < header.length(); i++) sb.append("-");
			sb.append("\n");
			String[] res = asStringArray();
			for(String s : res) sb.append(s + "\t");
			sb.append("\n");
			for(int i = 0; i < header.length(); i++) sb.append("_");
			sb.append("\n");
			return sb.toString();
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
	}

	public int[] getSizes() {
		return sizes;
	}

	public void setSizes(int[] sizes) {
		this.sizes = sizes;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public TestConfiguration[] getConfigurations() {
		return configurations;
	}

	public void setConfigurations(TestConfiguration[] configurations) {
		this.configurations = configurations;
	}

	public Runner[] getRunners() {
		return runners;
	}

	public void setRunners(Runner[] runners) {
		this.runners = runners;
	}
	

}
