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

/*
 * Log
 * Version 0.2
 * asset product assignment in method createFirmDescription was broken -> fixed
 */

package de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor.optimization;

import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.jface.preference.IPreferenceStore;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.IterationChangedListener;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.SPLProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;
import de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor.presentation.VBPODataModelEditorPlugin;
import de.uni_leipzig.iwi.gilbreth.vbpo.preferences.PreferenceConstants;

/**
 * Initializes a SPLProblemDescription for a VBPODataModel opended in the
 * VBPODataModelEditor and delegates the Opt4J-configuration to the
 * HeadlessStarter.
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

	private HeadlessStarter starter;
	/**
	 * the lookup holds hashtables which relate e.g. a product to a specific
	 * position in an array of the solution.
	 */
	private Lookup lookup;

	private SPLProblemDescription description;

	public OptimizationInitializer() {
		this.starter = new HeadlessStarter();

		// Configure the starter with the values of the preference page.
		IPreferenceStore store = VBPODataModelEditorPlugin.getPlugin()
				.getPreferenceStore();
		this.starter.configure(
				store.getInt(PreferenceConstants.P_MAX_ITERATIONS),
				store.getDouble(PreferenceConstants.P_ALPHA),
				store.getDouble(PreferenceConstants.P_DELTA),
				store.getInt(PreferenceConstants.P_INITIAL_TEMPERATURE),
				store.getInt(PreferenceConstants.P_FINAL_TEMPERATURE));

	}

	/**
	 * starts the optimization.
	 * 
	 * @param root
	 *            the root of the domain model VBPODataModel
	 */
	public void optimize(VBPODataModel root) {
		init(root);
		createProblemDescription();

		solution = starter.startOptimization(description);
	}

	/**
	 * 
	 * @return the solution after the optimization run.
	 * @throws Exception
	 *             if the solution is not initialized. I.e. the optimization is
	 *             not finished yet
	 */
	public Solution getSolution() throws Exception {
		if (solution == null)
			throw new Exception(
					"Optimization is not finished yet, solution is null.");
		return solution;
	}

	private void init(VBPODataModel root) {
		this.root = (VBPODataModel) root;
	}

	private void createProblemDescription() {

		numberOfProducts = root.getFirm().getSPL().getContainedProducts()
				.size();
		numberOfAssets = root.getFirm().getSSF().getContainedAssets().size();
		numberOfCompetitors = root.getCompetition().getCompetitors().size();
		numberOfCustomerSegments = root.getCustomers().eContents().size();

		Hashtable<Product, Integer> productLookup = new Hashtable<Product, Integer>();
		Hashtable<Asset, Integer> assetLookup = new Hashtable<Asset, Integer>();
		Hashtable<CustomerSegment, Integer> customerLookup = new Hashtable<CustomerSegment, Integer>();

		for (int i = 0; i < numberOfProducts; i++) {
			productLookup.put(root.getFirm().getSPL().getContainedProducts()
					.get(i), i);
		}

		for (int i = 0; i < numberOfAssets; i++) {
			assetLookup.put(
					root.getFirm().getSSF().getContainedAssets().get(i), i);
		}

		for (int i = 0; i < numberOfCustomerSegments; i++) {
			customerLookup.put(
					root.getCustomers().getCustomerSegments().get(i), i);
		}

		lookup = new Lookup();
		lookup.setAssetLookup(assetLookup);
		lookup.setProductLookup(productLookup);
		lookup.setSegmentLookup(customerLookup);

		description = new SPLProblemDescription(createCustomerDescription(),
				createFirmDescription(), createCompetitionDescription(), 100);
	}

	private SPLProblemDescription.Customer createCustomerDescription() {
		int numberOfSegments = root.getCustomers().eContents().size();
		int[] q = new int[numberOfSegments]; // { 10, 30, 50 };
		double[][] wtp = new double[numberOfSegments][numberOfProducts];
		/*
		 * { { 0.0, 1.0, 3.0, 4.0, 5.0 }, { 0.0, 4.0, 1.0, 2.0, 1.0 }, { 0.0,
		 * 2.0, 2.3, 4.5, 2.3 } };
		 */

		for (int i = 0; i < numberOfCustomerSegments; i++) {
			q[i] = root.getCustomers().getCustomerSegments().get(i).getSize();
		}
		for (int i = 0; i < numberOfCustomerSegments; i++) {
			for (Iterator iter = root.getCustomers().getCustomerSegments()
					.get(i).getWTPs().iterator(); iter.hasNext();) {
				WTP wtpObject = (WTP) iter.next();

				int j = lookup.getProductLookup().get(wtpObject.getProduct());
				wtp[i][j] = wtpObject.getPrice().doubleValue();
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
			cf[i] = root.getFirm().getSPL().getContainedProducts().get(i)
					.getComprisingSystem().getImplementationCost()
					.doubleValue();
			cv[i] = root.getFirm().getSPL().getContainedProducts().get(i)
					.getUnitCost().doubleValue();
		}

		for (int i = 0; i < numberOfAssets; i++) {
			ca[i] = root.getFirm().getSSF().getContainedAssets().get(i)
					.getSetupCost().doubleValue();
		}

		for (int i = 0; i < numberOfProducts; i++) {
			for (int j = 0; j < numberOfAssets; j++) {
				// set whether the System of the Product contains the asset 
				a[i][j] = root
						.getFirm()
						.getSPL()
						.getContainedProducts()
						.get(i)
						.getComprisingSystem()
						.getAssets()
						.contains(
								root.getFirm().getSSF().getContainedAssets()
										.get(j));
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
			w[i] = bestCompetitiveOfferingFor(root.getCustomers()
					.getCustomerSegments().get(i));
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
		for (Iterator iterWTP = segment.getWTPs().iterator(); iterWTP.hasNext();) {
			for (Iterator iterCompetitor = root.getCompetition()
					.getCompetitors().iterator(); iterCompetitor.hasNext();) {
				Competitor competitor = (Competitor) iterCompetitor.next();
				for (Iterator iterPrice = competitor.getPrices().iterator(); iterPrice
						.hasNext();) {
					Price competitorPrice = (Price) iterPrice.next();
					WTP wtp = (WTP) iterWTP.next();
					if (competitorPrice.getProduct() == wtp.getProduct()) {
						tempSurplus = wtp.getPrice().doubleValue()
								- competitorPrice.getPrice().doubleValue();
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

	public int getFullWorkUnits() {
		return starter.getFullWorkUnits();
	}

	public void addIterationChangedListener(IterationChangedListener listener) {
		starter.addIterationChangedListener(listener);
	}

	public void removeIterationChangedListener(IterationChangedListener listener) {
		starter.removeIterationChangedListener(listener);
	}

	// set EMF resource to read the model

	// start initiailization
	// - 1. read model
	// - 2. create problem configuration
	// - 3. configure Opt4J
	// - 4. start configuration
	// - 5. return solution

}
 