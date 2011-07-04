package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.opt4j.core.Archive;
import org.opt4j.core.Individual;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.optimizer.sa.CoolingScheduleModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule;
import org.opt4j.optimizer.sa.CoolingSchedulesModule.Type;
import org.opt4j.start.Opt4JTask;
import org.opt4j.viewer.ViewerModule;

import com.google.inject.Module;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLSimulatedAnnealing;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLSimulatedAnnealingModule;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;

/**
 * Initializes a SPLProblemDescription for a VBPODataModel opended in the VBPODataModelEditor
 * and delegates the Opt4J-configuration to the HeadlessStarter.
 * 
 * @author jo
 *
 */
public class OptimizationInitializer {

	private Solution solution;

	private VBPODataModel root;
	// Collect the sizes globally to initialize the matrixes and vectors
	// properly
	private int numberOfProducts;
	private int numberOfCustomerSegments;
	private int numberOfAssets;
	private int numberOfCompetitors;

	private Hashtable<Product, Integer> productLookup = new Hashtable<Product, Integer>();
	private Hashtable<Asset, Integer> assetLookup = new Hashtable<Asset, Integer>();

	private SPLProblemDescription description;
	
	
	public OptimizationInitializer(){
		
	}
	
	public void optimize(VBPODataModel root){
		init(root);
		createProblemDescription();
		
		HeadlessStarter starter = new HeadlessStarter();
		solution = starter.startOptimization(description);
	}
	
	
	private void init(VBPODataModel root) {
		this.root = (VBPODataModel) root;
	}

	private void createProblemDescription() {

		numberOfProducts = root.getHasAFirm().getFirmHasSPL()
				.getSPLContainsProduct().size();
		numberOfAssets = root.getHasAFirm().getFirmHasSSF()
				.getSSFContainsAsset().size();
		numberOfCompetitors = root.getHasCompetition().getConsistsOf().size();
		numberOfCustomerSegments = root.getHasCustomers().eContents().size();

		for (int i = 0; i < numberOfProducts; i++) {
			productLookup.put(root.getHasAFirm().getFirmHasSPL()
					.getSPLContainsProduct().get(i), i);
		}

		for (int i = 0; i < numberOfAssets; i++) {
			assetLookup.put(root.getHasAFirm().getFirmHasSSF()
					.getSSFContainsAsset().get(i), i);
		}

		description = new SPLProblemDescription(createCustomerDescription(),
				createFirmDescription(), createCompetitionDescription(), 100);
	}

	private SPLProblemDescription.Customer createCustomerDescription() {
		int numberOfSegments = root.getHasCustomers().eContents().size();
		int[] q = new int[numberOfSegments]; // { 10, 30, 50 };
		double[][] wtp = new double[numberOfSegments][numberOfProducts];
		/*
		 * { { 0.0, 1.0, 3.0, 4.0, 5.0 }, { 0.0, 4.0, 1.0, 2.0, 1.0 }, { 0.0,
		 * 2.0, 2.3, 4.5, 2.3 } };
		 */

		for (int i = 0; i < numberOfCustomerSegments; i++) {
			q[i] = root.getHasCustomers()
					.getCustomersConsistsOfCustomerSegments().get(i).getSize();
		}
		for (int i = 0; i < numberOfCustomerSegments; i++) {
			for (Iterator iter = root.getHasCustomers()
					.getCustomersConsistsOfCustomerSegments().get(i)
					.getSegmentHasWTP().iterator(); iter.hasNext();) {
				WTP wtpObject = (WTP) iter.next();
				
				VBPODataModelEditorPlugin.INSTANCE.log("WTP: " + wtpObject.getValue());
				VBPODataModelEditorPlugin.INSTANCE.log("prodcutLookup null?: " + productLookup == null);
				
				int j = productLookup.get(wtpObject.getWTPForProduct());
				wtp[i][j] = wtpObject.getValue().doubleValue();
			}

		}
		return new SPLProblemDescription.Customer(q, wtp);
	}

	private SPLProblemDescription.Firm createFirmDescription() {
		double[] cv = new double[numberOfProducts]; // { 0.0, 0.1, 0.4, 0.5,
													// 0.01 };
		double[] cf = new double[numberOfProducts]; // { 0.0, 5.0, 6.0, 1.0, 2.0
													// };

		// Products/Assets
		boolean[][] a = new boolean[numberOfProducts][numberOfAssets];

		/*
		 * { { true, false, true, true, true, false }, { false, true, false,
		 * false, true, false }, { true, true, false, false, false, false }, {
		 * false, false, false, false, false, true }, { false, false, false,
		 * false, false, false } };
		 */

		// Cost per asset
		double[] ca = new double[numberOfAssets];// { 4.0, 3.0, 2.0, 4.0, 5.0,
													// 7.0 };

		for (int i = 0; i < numberOfProducts; i++) {
			cf[i] = root.getHasAFirm().getFirmHasSPL().getSPLContainsProduct()
					.get(i).getProductComprisesSystem().getImplementationCost()
					.doubleValue();
			cv[i] = root.getHasAFirm().getFirmHasSPL().getSPLContainsProduct()
					.get(i).getUnitCost().doubleValue();
		}

		for (int i = 0; i < numberOfAssets; i++) {
			ca[i] = root.getHasAFirm().getFirmHasSSF().getSSFContainsAsset()
					.get(i).getSetupCost().doubleValue();
		}

		for (int i = 0; i < numberOfProducts; i++) {
			for (int j = 0; j < numberOfAssets; j++) {
				for (Iterator iter = root.getHasAFirm().getFirmHasSPL()
						.getSPLContainsProduct().get(i)
						.getProductComprisesSystem().getSystemUsesAsset()
						.iterator(); iter.hasNext();) {
					Asset asset = (Asset) iter.next();
					a[i][j] = j == assetLookup.get(asset);
				}
			}
		}

		return new SPLProblemDescription.Firm(cv, cf, ca, a);
	}

	private SPLProblemDescription.Competition createCompetitionDescription() {
		double[] w = new double[numberOfCustomerSegments];// { 0.0, 0.0, 0.0
															// };// Here we need
															// some calculations
		// its the best competitor for each segments. That has to be calculated
		// before.

		for (int i = 0; i < numberOfCompetitors; i++) {
			w[i] = bestCompetitiveOfferingFor(root.getHasCustomers()
					.getCustomersConsistsOfCustomerSegments().get(i));
		}

		return new SPLProblemDescription.Competition(w);
	}

	/**
	 * calculates the best competitive Offering for each segment. Iterates over
	 * all competitive offerings and calculates the customer surplus. The
	 * highest customer surplus is returned as the best competitive offering.
	 * 
	 * @param segment
	 * @return
	 */
	private double bestCompetitiveOfferingFor(CustomerSegment segment) {
		double customerSurplus = -Double.MAX_VALUE;
		double tempSurplus;
		for (Iterator iterWTP = segment.getSegmentHasWTP().iterator(); iterWTP
				.hasNext();) {
			for (Iterator iterCompetitor = root.getHasCompetition()
					.getConsistsOf().iterator(); iterCompetitor.hasNext();) {
				Competitor competitor = (Competitor) iterCompetitor.next();
				for (Iterator iterPrice = competitor.getCompetitorHasPrice().iterator(); iterPrice
						.hasNext();) {
					Price competitorPrice = (Price) iterPrice.next();
					WTP wtp = (WTP) iterWTP.next();
					if (competitorPrice.getPriceForProduct() == wtp
							.getWTPForProduct()) {
						tempSurplus = wtp.getValue().doubleValue()
								- competitorPrice.getValue().doubleValue();
						customerSurplus = customerSurplus < tempSurplus ? tempSurplus
								: customerSurplus;
					}
				}
			}
		}

		return customerSurplus;
	}
	

	// set EMF resource to read the model

	// start initiailization
	// - 1. read model
	// - 2. create problem configuration
	// - 3. configure Opt4J
	// - 4. start configuration
	// - 5. return solution

}
