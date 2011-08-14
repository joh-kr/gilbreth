package de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math.linear.RealVector.Entry;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;
import org.opt4j.common.random.Rand;
import org.opt4j.core.problem.Genotype;
import org.opt4j.operator.Apply;
import org.opt4j.operator.normalize.NormalizeDouble;

import com.google.inject.Inject;

import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoProblemDescription.Competition;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoProblemDescription.Customer;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.VbpoProblemDescription.Firm;

@Apply(VbpoGenotype.class)
public class VbpoLinOpNeighbor extends VbpoNeighbor {
	
	private static boolean PRINT_STEPS = false;
	
	private SimplexSolver solver;

	@Inject
	public VbpoLinOpNeighbor(VbpoProblemDescription problem, Rand random,
			NormalizeDouble normalize) {
		super(problem, random, normalize);
		// TODO Auto-generated constructor stub
		solver = new SimplexSolver();
	}
	/**
	 * 
	 */
	@Override
	public void neighbor(Genotype genotype) {
		VbpoGenotype splGenotype = (VbpoGenotype) genotype;
		// randomly create a neighbor in terms of product segment assignment
		neighborX(splGenotype);
		
		// find optimal prices for the product segment assignment
		neighborP(splGenotype);
		
	}

	/**
	 * @param genotype
	 */
	@Override
	protected void neighborP(VbpoGenotype genotype){
		LinearObjectiveFunction objective = createObjectiveFunction(genotype);
		
		Collection<LinearConstraint> constraints = createConstraints(genotype);
		
		if(PRINT_STEPS){
			System.out.println("-------------------------");
			printObjective(objective);
			printConstraints(constraints);
		}

	
		try {
			RealPointValuePair solution = solver.optimize(objective, constraints, GoalType.MAXIMIZE, false);
			
			for(int i = 0; i < genotype.getP().size(); i++){
				genotype.getP().set(i, solution.getPoint()[i]);
			}
			
		} catch (OptimizationException e) {		
			// If no solution can be found, the prices are set to 0.0d to prevent a high 
			// profit with a illegal solution
			for(int i = 0; i < genotype.getP().size(); i++){
				genotype.getP().set(i, 0.0d);
			}
			
			if(PRINT_STEPS) System.out.println(e.getLocalizedMessage());
		}
		
		if(PRINT_STEPS) System.out.println("-------------------------");

	}
	
	private LinearObjectiveFunction createObjectiveFunction(VbpoGenotype genotype){
		Customer customer = getDescription().getCustomer();
		Firm firm = getDescription().getFirm();
		
		double[] coeff = new double[firm.NumberOfProducts()];
		double buyers = 0.0d;
		
		for(int j = 0; j < firm.NumberOfProducts(); j++){
			for(int i = 0; i < customer.numberOfSegments(); i++){
				buyers += genotype.getXAsMatrix()[i][j] ? customer.getQ(i) : 0.0d;
			}
			coeff[j] = buyers;
			buyers = 0.0d;
		}
		
		return new LinearObjectiveFunction(coeff, 0);
	}
	
	private Collection<LinearConstraint> createConstraints(VbpoGenotype genotype){
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
		constraints.addAll(createMaxWellfareConstraints(genotype));
		constraints.addAll(createBestProductConstraints(genotype));
		constraints.addAll(createZeroProductConstraint());
		
		return constraints;
	}
	
	private Collection<LinearConstraint> createMaxWellfareConstraints(VbpoGenotype genotype){
		Customer customer = getDescription().getCustomer();
		Competition competition = getDescription().getCompetition();
		Firm firm = getDescription().getFirm();
		
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

		double[] coeff = null;
		double   value = 0.0d;
		
		for(int i = 0; i < customer.numberOfSegments(); i++){
			coeff = new double[firm.NumberOfProducts()];
			value = 0.0d;
			for(int j = 0; j < firm.NumberOfProducts(); j++){
				coeff[j] = genotype.getXAsMatrix()[i][j] ? 1.0d : 0.0d;		
				value   += genotype.getXAsMatrix()[i][j] ? customer.getWTP(i, j) : 0.0d;
			}		
			value -= competition.getW(i);
			
			constraints.add(new LinearConstraint(coeff, Relationship.LEQ, value));
		}
		
		return constraints;
	}
	
	private Collection<LinearConstraint> createBestProductConstraints(VbpoGenotype genotype){
		Customer customer = getDescription().getCustomer();
		Firm firm = getDescription().getFirm();
		
		SolutionAdapter adapter = new SolutionAdapter(null, null, getDescription());
		
		boolean[] y = adapter.determineY(genotype.getXAsMatrix());
		
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

		double[] coeff = null;
		double   value = 0.0d;
		
		for(int i = 0; i < customer.numberOfSegments(); i++){
			for(int j = 0; j < firm.NumberOfProducts(); j++){
				coeff = new double[firm.NumberOfProducts()];
				value = 0.0d;
				for(int s = 0; s < firm.NumberOfProducts(); s++){
					coeff[s] = genotype.getXAsMatrix()[i][s] ? 1.0d : 0.0d;
					value   += genotype.getXAsMatrix()[i][s] ? customer.getWTP(i, s) : 0.0d;
				}
				coeff[j] += y[j] ? -1.0d : 0.0d;
				value    += y[j] ? -customer.getWTP(i, j) : 0.0d;
				constraints.add(new LinearConstraint(coeff, Relationship.LEQ, value));
			}		
			
		}
		
		return constraints;
	}
	
	private Collection<LinearConstraint> createZeroProductConstraint(){
		Firm firm = getDescription().getFirm();
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
		
	}

}
