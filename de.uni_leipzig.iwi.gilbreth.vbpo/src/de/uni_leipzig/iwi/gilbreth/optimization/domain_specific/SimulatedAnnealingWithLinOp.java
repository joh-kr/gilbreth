package de.uni_leipzig.iwi.gilbreth.optimization.domain_specific;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.math.linear.RealVector.Entry;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

import de.uni_leipzig.iwi.gilbreth.optimization.Runner;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription.Competition;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription.Customer;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription.Firm;

public class SimulatedAnnealingWithLinOp extends Runner{
	
	private static final boolean PRINT_STEPS = false;
	
	protected VbpoProblemDescription description;
	
	protected long cooling_steps;
	protected Random random = new Random();
	protected SimplexSolver solver;
	
	protected Individual individual_n;
	protected Individual individual_c;	
	protected Individual individual_b;
	protected double temp;
	protected long time;
	protected double improvement;
	protected long improvement_counter;

	
	public Solution optimize(VbpoProblemDescription description) throws Exception{
		if(!configured) throw new Exception("Algorithm must be configured first.");
		this.description = description;
		solver = new SimplexSolver();
		solver.setMaxIterations(1000);
		
		initialize();
		loop();
		
		if(f(individual_b) < 0.0d){
			return unsuccsessfulResult();
		}else{
			return individual_b.getS();
		}
		
	}
		
	private Solution unsuccsessfulResult(){
		double[] ap = new double[individual_b.getP().length];
		ap[0] = 0.0d;
		for(int i = 1; i < ap.length; i++){
			ap[i] = -1.0d;
		}		
		Individual ind = new Individual(individual_b.getX(), ap, description);
		
		return ind.getS();
	}
	
	private void initialize(){
		individual_n = new Individual(description);
		individual_b = new Individual(individual_n);
		individual_c = new Individual(individual_n);
		
		temp                = initialTemp;
		time                = 0;
		improvement         = 0.0d;
		improvement_counter = super.changeIterations;
	}
	
	
	private void loop(){
		while(!terminationCriterion()){
			improvement = f(individual_n) - f(individual_c);
			if(improvement >= 0.0d){
				individual_c = new Individual(individual_n);
				setImprovementCounter(improvement);
				if(individual_c.getProfit() > individual_b.getProfit()){
					individual_b = new Individual(individual_c);
				}
			}else{
				temp = getTemperature();
				improvement_counter--;
				if(switchIndividuals()){
					individual_c = new Individual(individual_n);
				}
			}
			individual_n = neighbor(individual_c);
			time = time + 1;
		}
	}
	
	private void setImprovementCounter(double improvement){
		if(individual_c.getProfit() < 0.0d) return;
		
		double frac_imp = improvement/individual_c.getProfit();	
		if(frac_imp > delta){
			improvement_counter = super.changeIterations;
		}
		
	}

	protected double f(Individual individual){
		double _profit = 0.0d;

		int suboptimalSegments = countSegmentsWithSuboptimalProductsAssigned(individual);
		int segmentsBuyingFromComp = countSegmentsBuyingFromCompetitor(individual);

		if (segmentsBuyingFromComp == 0 && suboptimalSegments == 0) {
			_profit = individual.getProfit();
		} else {
			_profit = calculatePenaltyProfit(individual.getProfit(),suboptimalSegments, segmentsBuyingFromComp);
		}
		
		return _profit;
	}
	
	private int countSegmentsBuyingFromCompetitor(Individual individual) {
		boolean[][] x = individual.getX();
		double[] p = individual.getP();
		VbpoProblemDescription.Customer c = description.getCustomer();
		VbpoProblemDescription.Firm f = description.getFirm();
		VbpoProblemDescription.Competition co = description.getCompetition();
		double value = 0.0d;
		int count = 0;
		for (int i = 0; i < c.numberOfSegments(); i++) {
			value = 0.0d;
			for (int j = 0; j < f.NumberOfProducts(); j++) {
				if (x[i][j]) {
					value += c.getWTP(i, j) - p[j];
				}
			}
			if (value < co.getW(i))
				count++;
		}

		return count;
	}

	private int countSegmentsWithSuboptimalProductsAssigned(Individual individual) {
		boolean[][] x = individual.getX();
		boolean[] y = individual.getS().determineY();
		double[] p = individual.getP();
		VbpoProblemDescription.Customer c = description.getCustomer();
		VbpoProblemDescription.Firm     f = description.getFirm();
		double welfare = 0.0d;
		int count = 0;

		for (int i = 0; i < c.numberOfSegments(); i++) {
			welfare = 0.0d;
			for (int s = 0; s < f.NumberOfProducts(); s++) {
				if (x[i][s])
					welfare = c.getWTP(i, s) - p[s];
			}
			for (int j = 0; j < f.NumberOfProducts(); j++) {
				if (y[j] && welfare < (c.getWTP(i, j) - p[j]))
					count++;
			}
		}
		return count;
	}
	
	private double calculatePenaltyProfit(double profit, int suboptimalSegments, int segmentsBuyingFromComp){	
		double _profit = 0.0d;
		_profit = profit < 0.0d ? profit : (-1*profit) * (suboptimalSegments + segmentsBuyingFromComp);
				
		return _profit;
	}
	
	protected boolean terminationCriterion(){
		return improvement_counter == 0 || time == maxIterations;
	}
	
	protected double getTemperature(){
		if(time == maxIterations) return 0.0d;
		
		return initialTemp * alpha *(time/maxIterations);
	}
	
	protected boolean switchIndividuals(){
		double rand = random.nextDouble();
		return rand < Math.exp(-1*(improvement/temp));
	}
	
	protected Individual neighbor(Individual individual){
		Individual newIndividual = new Individual(individual);
		neighborX(newIndividual);
		neighborP(newIndividual);
		
		return newIndividual;
	}
	
	protected void neighborX(Individual individual){
		
		int i      = random.nextInt(individual.getX().length);
		int change = random.nextInt(individual.getX()[0].length);
		
		for(int j = 0; j < description.getFirm().NumberOfProducts(); j++){
			individual.getX()[i][j] = j == change;
		}	
	}

	/**
	 * @param genotype
	 */
	protected void neighborP(Individual individual){
		LinearObjectiveFunction objective = createObjectiveFunction(individual);
		
		Collection<LinearConstraint> constraints = createConstraints(individual);
		
		if(PRINT_STEPS){
			System.out.println("-------------------------");
			printObjective(objective);
			printConstraints(constraints);
		}

	
		try {
			RealPointValuePair solution = solver.optimize(objective, constraints, GoalType.MAXIMIZE, false);
			
			for(int i = 0; i < individual.getP().length; i++){
				individual.getP()[i] = solution.getPoint()[i];
			}
			
		} catch (OptimizationException e) {		
			// If no solution can be found, the prices are set to 0.0d to prevent a high 
			// profit with a illegal solution
			for(int i = 0; i < individual.getP().length; i++){
				individual.getP()[i] = 0.0d;
			}
			
			if(PRINT_STEPS) System.out.println(e.getLocalizedMessage());
		}
		
		if(PRINT_STEPS) System.out.println("-------------------------");

	}
	
	private LinearObjectiveFunction createObjectiveFunction(Individual individual){
		Customer customer = description.getCustomer();
		Firm firm 		  = description.getFirm();
		
		double[] coeff = new double[firm.NumberOfProducts()];
		double buyers = 0.0d;
		
		for(int j = 0; j < firm.NumberOfProducts(); j++){
			for(int i = 0; i < customer.numberOfSegments(); i++){
				buyers += individual.getX()[i][j] ? customer.getQ(i) : 0.0d;
			}
			coeff[j] = buyers;
			buyers = 0.0d;
		}
		
		return new LinearObjectiveFunction(coeff, 0);
	}
	
	private Collection<LinearConstraint> createConstraints(Individual individual){
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
		constraints.addAll(createMaxWellfareConstraints(individual));
		constraints.addAll(createBestProductConstraints(individual));
		constraints.addAll(createZeroProductConstraint());
		
		return constraints;
	}
	
	private Collection<LinearConstraint> createMaxWellfareConstraints(Individual individual){
		Customer customer 		= description.getCustomer();
		Competition competition = description.getCompetition();
		Firm firm 				= description.getFirm();
		
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

		double[] coeff = null;
		double   value = 0.0d;
		
		for(int i = 0; i < customer.numberOfSegments(); i++){
			coeff = new double[firm.NumberOfProducts()];
			value = 0.0d;
			for(int j = 0; j < firm.NumberOfProducts(); j++){
				coeff[j] = individual.getX()[i][j] ? 1.0d : 0.0d;		
				value   += individual.getX()[i][j] ? customer.getWTP(i, j) : 0.0d;
			}		
			value -= competition.getW(i);
			
			constraints.add(new LinearConstraint(coeff, Relationship.LEQ, value));
		}
		
		return constraints;
	}
	
	private Collection<LinearConstraint> createBestProductConstraints(Individual individual){
		Customer customer = description.getCustomer();
		Firm firm 		  = description.getFirm();
		
		SolutionAdapter adapter = new SolutionAdapter(null, null, description);
		
		boolean[] y = adapter.determineY(individual.getX());
		
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

		double[] coeff = null;
		double   value = 0.0d;
		
		for(int i = 0; i < customer.numberOfSegments(); i++){
			for(int j = 0; j < firm.NumberOfProducts(); j++){
				coeff = new double[firm.NumberOfProducts()];
				value = 0.0d;
				for(int s = 0; s < firm.NumberOfProducts(); s++){
					coeff[s] = individual.getX()[i][s] ? 1.0d : 0.0d;
					value   += individual.getX()[i][s] ? customer.getWTP(i, s) : 0.0d;
				}
				coeff[j] += y[j] ? -1.0d : 0.0d;
				value    += y[j] ? -customer.getWTP(i, j) : 0.0d;
				constraints.add(new LinearConstraint(coeff, Relationship.LEQ, value));
			}		
			
		}
		
		return constraints;
	}
	
	private Collection<LinearConstraint> createZeroProductConstraint(){
		Firm firm = description.getFirm();
		double[] coeff = new double[firm.NumberOfProducts()];
		coeff[0] = 1.0;
		double value = 0.0d;
		
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
		constraints.add(new LinearConstraint(coeff, Relationship.EQ, value));
		
		return constraints;
	}
	
	private void printConstraints(Collection<LinearConstraint> constraints){
		for(LinearConstraint l : constraints){
			Iterator<Entry> iter = l.getCoefficients().iterator();
			Entry e = null;
			while(iter.hasNext()){
				e = iter.next();
				System.out.print(e.getValue() + "p_"+e.getIndex() + " + ");
			}
			System.out.println(l.getRelationship().toString() + l.getValue());
		}
	}
	
	private void printObjective(LinearObjectiveFunction objective){
		Iterator<Entry> iter = objective.getCoefficients().iterator();
		Entry e = null;
		while(iter.hasNext()){
			e = iter.next();
			System.out.print(e.getValue() + "p_"+e.getIndex() + " + ");
		}
		System.out.println();
	}
	
	
	/**
	 * Helps accessing the protected implementations of Solution. We are espacially interested
	 * in the determineY(boolean[][]) implementation.
	 * 
	 * @author Johannes Müller
	 *
	 */
	private static class SolutionAdapter extends Solution{

		public SolutionAdapter(boolean[][] x, double[] p,
				VbpoProblemDescription problemDescription) {
			super(x, p, problemDescription);
			// TODO Auto-generated constructor stub
		}
		
		public boolean[] determineY(boolean[][] x){
			return super.determineY(x);
		}
		
	}
	

}

