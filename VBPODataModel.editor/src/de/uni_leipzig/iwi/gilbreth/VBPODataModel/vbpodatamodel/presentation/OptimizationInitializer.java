/**
 * Copyright 2011 Johannes MŸller, University of Leipzig
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.presentation;

import java.util.Hashtable;
import java.util.Iterator;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;

/**
 * Initializes a SPLProblemDescription for a VBPODataModel opended in the VBPODataModelEditor
 * and delegates the Opt4J-configuration to the HeadlessStarter.
 * 
 * @author Johannes MŸller
 * @version 0.2
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
	
	/**
	 * the lookup holds hashtables which relate e.g. a product to a specific 
	 * position in an array of the solution.
	 */
	private Lookup lookup;

	private SPLProblemDescription description;
	
	
	public OptimizationInitializer(){
		
	}
	
	/**
	 * starts the optimization.
	 * 
	 * @param root the root of the domain model VBPODataModel
	 */
	public void optimize(VBPODataModel root){
		init(root);
		createProblemDescription();
		
		HeadlessStarter starter = new HeadlessStarter();
		solution = starter.startOptimization(description);
	}
	
	/**
	 * 
	 * @return the solution after the optimization run.
	 * @throws Exception if the solution is not initialized. I.e. the optimization is not finished yet
	 */
	public Solution getSolution() throws Exception{
		if(solution == null) throw new Exception("Optimization is not finished yet, solution is null.");
		return solution;
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

		
		Hashtable<Product, Integer> productLookup = new Hashtable<Product, Integer>();
		Hashtable<Asset, Integer>   assetLookup   = new Hashtable<Asset, Integer>();
		Hashtable<CustomerSegment, Integer>   customerLookup   = new Hashtable<CustomerSegment, Integer>();
		
		for (int i = 0; i < numberOfProducts; i++) {
			productLookup.put(root.getHasAFirm().getFirmHasSPL()
					.getSPLContainsProduct().get(i), i);
		}

		for (int i = 0; i < numberOfAssets; i++) {
			assetLookup.put(root.getHasAFirm().getFirmHasSSF()
					.getSSFContainsAsset().get(i), i);
		}
		
		for (int i = 0; i < numberOfCustomerSegments; i++) {
			customerLookup.put(root.getHasCustomers().getCustomersConsistsOfCustomerSegments().get(i), i);
		}

		lookup = new Lookup();
		lookup.setAssetLookup(assetLookup);
		lookup.setProductLookup(productLookup);
		lookup.setSegmentLookup(customerLookup);
		
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
				

				
				int j = lookup.getProductLookup().get(wtpObject.getWTPForProduct());
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
					a[i][j] = j == lookup.getAssetLookup().get(asset);
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

	public Lookup getLookup() {
		return lookup;
	}
	

	// set EMF resource to read the model

	// start initiailization
	// - 1. read model
	// - 2. create problem configuration
	// - 3. configure Opt4J
	// - 4. start configuration
	// - 5. return solution

}
