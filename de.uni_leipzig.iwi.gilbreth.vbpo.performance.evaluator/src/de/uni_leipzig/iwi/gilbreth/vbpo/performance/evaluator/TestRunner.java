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
	private Optimizer optimizer;
	private SimpleProblemTestDataFactory testData;
	private int startSize;
	private int maxSize;
	private double kappa;
	
	public TestRunner(String csvFilePath, Optimizer optimizer,
			SimpleProblemTestDataFactory testData, int startSize, int maxSize, double kappa) throws IOException {
		super();
		this.csvFilePath = csvFilePath;
		this.optimizer = optimizer;
		this.testData = testData;
		this.startSize = startSize;
		this.maxSize = maxSize;
		this.kappa = kappa;
		
	}
	
	public void start() throws Exception{
		Result result = new Result();
		Solution solution = null;
		VbpoProblemDescription description;
		long start = 0;
		long end = 0;
		writeHeader();
		for(int i = startSize; i < maxSize; i++){
			description = getVbpoProblemDescription(i, kappa);
			optimizer.configureAlgorithm(100 + i, 0.01d, (i*10) + 10000, 0.995d, (i*5) + 1000, 1);
			
			// Run optimizer with linop
			start = System.currentTimeMillis();
			solution = optimizer.runOptimization(description, true);
			end = System.currentTimeMillis();	
			setLinOpResults(solution, (end-start), result);
			
			// Run optimizer with pure simulated Annealing
			start = System.currentTimeMillis();
			solution = optimizer.runOptimization(description, false);
			end = System.currentTimeMillis();
			setOrdinaryResults(solution, (end-start), result);
			
			result.setProfit_max(getMaxProfit(i, kappa));
			result.setSize(i);
			
			writeResult(result);
			System.out.println("Iteration " + i);
			System.out.println(result.toString());
		}
	}
	
	private void setLinOpResults(Solution solution, long time, Result result){
		int builtProducts = 0;
		boolean[] y = solution.determineY();
		for(boolean built : y) builtProducts += built ? 1 : 0;
		
		result.setNr_of_built_products_linOp(builtProducts);
		result.setProfit_linOp(solution.profit());
		result.setTime_linOp(time);
	}
	
	private void setOrdinaryResults(Solution solution, long time, Result result){
		int builtProducts = 0;
		boolean[] y = solution.determineY();
		for(boolean built : y) builtProducts += built ? 1 : 0;
		
		result.setNr_of_built_products_ordinary(builtProducts);
		result.setProfit_ordinary(solution.profit());
		result.setTime_ordinary(time);
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
		
		public static final int ATTRIBUTES = 8;
		public static final String[] ENTRIES = {
			"size", "time_linPp", "time_ordinary","profit_max",
			"profit_linop","profit_ordinary","nr_of_products_linOp",
			"nr_of_products_ordinary"};
		
		double size;
		
		long time_linOp;
		long time_ordinary;
		
		double profit_max;
		double profit_linOp;
		double profit_ordinary;
		
		
		int nr_of_built_products_linOp;
		int nr_of_built_products_ordinary;
		public long getTime_linOp() {
			return time_linOp;
		}
		public void setTime_linOp(long time_linOp) {
			this.time_linOp = time_linOp;
		}
		public long getTime_ordinary() {
			return time_ordinary;
		}
		public void setTime_ordinary(long time_ordinary) {
			this.time_ordinary = time_ordinary;
		}
		public double getProfit_linOp() {
			return profit_linOp;
		}
		public void setProfit_linOp(double profit_linOp) {
			this.profit_linOp = profit_linOp;
		}
		public double getProfit_ordinary() {
			return profit_ordinary;
		}
		public void setProfit_ordinary(double profit_ordinary) {
			this.profit_ordinary = profit_ordinary;
		}
		public double getProfit_max() {
			return profit_max;
		}
		public void setProfit_max(double profit_max) {
			this.profit_max = profit_max;
		}
		public int getNr_of_built_products_linOp() {
			return nr_of_built_products_linOp;
		}
		public void setNr_of_built_products_linOp(int nr_of_built_products_linOp) {
			this.nr_of_built_products_linOp = nr_of_built_products_linOp;
		}
		public int getNr_of_built_products_ordinary() {
			return nr_of_built_products_ordinary;
		}
		public void setNr_of_built_products_ordinary(int nr_of_built_products_ordinary) {
			this.nr_of_built_products_ordinary = nr_of_built_products_ordinary;
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
			entries[1] = ""+this.getTime_linOp();
			entries[2] = ""+this.getTime_ordinary();
		
			entries[3] = ""+this.getProfit_max();
			entries[4] = ""+this.getProfit_linOp();
			entries[5] = ""+this.getProfit_ordinary();
			
			entries[6] = ""+this.getNr_of_built_products_linOp();
			entries[7] = ""+this.getNr_of_built_products_ordinary();
			
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
	}
	

}
