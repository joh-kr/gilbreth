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

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.jface.preference.IPreferenceStore;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelFactory;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP;
import de.uni_leipzig.iwi.gilbreth.optimization.HeadlessStarter;
import de.uni_leipzig.iwi.gilbreth.optimization.Solution;
import de.uni_leipzig.iwi.gilbreth.optimization.VbpoProblemDescription;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.IterationChangedListener;
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

	// To handle the zero product
	private static String ZERO_PRODUCT = "ZeroProduct";
	
	private VbpodatamodelFactory factory;
	private Product zeroProduct;
	private de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System s;
	private Asset a;
	private Feature f;
	
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

	private VbpoProblemDescription description;

	public OptimizationInitializer() {
		this.starter = new HeadlessStarter();

		// Configure the starter with the values of the preference page.
		IPreferenceStore store = VBPODataModelEditorPlugin.getPlugin()
				.getPreferenceStore();
		this.starter.configure(
				store.getInt(PreferenceConstants.P_MAX_ITERATIONS),
				store.getInt(PreferenceConstants.P_CHANGE_ITERATIONS),
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
		// To include the zero product explicitly in the solution.
		addZeroProduct();
		createProblemDescription();
		solution = starter.optimize(description);
		// TODO temporarly removed because the result build crashes since the zero product 
		// is not in the list anymore.
		//removeZeroProduct();
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

	private void addZeroProduct(){
		Product p = null;
		boolean containsZeroProduct = false;
		for (Iterator iter = root.getFirm().getSPL().getContainedProducts()
				.iterator(); iter.hasNext();) {
			p = (Product) iter.next();
			containsZeroProduct = p.getName().equals(ZERO_PRODUCT);
		}
		if(!containsZeroProduct){
			factory = VbpodatamodelPackage.eINSTANCE.getVbpodatamodelFactory();
			
			a = factory.createAsset();
			a.setName(ZERO_PRODUCT);
			a.setReuseCost(new BigDecimal(0.0d));
			a.setSetupCost(new BigDecimal(0.0d));
			root.getFirm().getSSF().getContainedAssets().add(0, a);
			
			s = factory.createSystem();	
			s.setImplementationCost(new BigDecimal(0.0d));
			s.setName(ZERO_PRODUCT);
			s.getAssets().add(0, a);
			root.getFirm().getSSF().getContainedSystems().add(0, s);
			
			f = factory.createFeature();
			f.setName(ZERO_PRODUCT);
			f.getRealizingAssets().add(0, a);
			root.getFirm().getSPL().getContainedFeatures().add(0, f);
			
			zeroProduct = factory.createProduct();
			zeroProduct.setComprisingSystem(s);
			zeroProduct.setUnitCost(new BigDecimal(0.0d));
			zeroProduct.getFeatures().add(0, f);
			zeroProduct.setName(ZERO_PRODUCT);		
			root.getFirm().getSPL().getContainedProducts().add(0, zeroProduct);
		}
	}
	
	private void removeZeroProduct(){
		root.getFirm().getSPL().getContainedProducts().remove(zeroProduct);
		root.getFirm().getSPL().getContainedFeatures().remove(f);	
		root.getFirm().getSSF().getContainedSystems().remove(s);
		root.getFirm().getSSF().getContainedAssets().remove(a);
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

		description = new VbpoProblemDescription(
				createCustomerDescription(),
				createFirmDescription(), 
				createCompetitionDescription(), 10);
	}

	private VbpoProblemDescription.Customer createCustomerDescription() {
		int numberOfSegments = root.getCustomers().eContents().size();
		
		int[] q = new int[numberOfSegments];
		double[][] wtp = new double[numberOfSegments][numberOfProducts];


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
		return new VbpoProblemDescription.Customer(q, wtp);
	}

	private VbpoProblemDescription.Firm createFirmDescription() {
		double[] cv = new double[numberOfProducts];
		double[] cf = new double[numberOfProducts]; 
		double[] ca = new double[numberOfAssets];
		boolean[][] a = new boolean[numberOfProducts][numberOfAssets];

		for (int j = 0; j < numberOfProducts; j++) {
			cf[j] = root.getFirm().getSPL().getContainedProducts().get(j)
					.getComprisingSystem().getImplementationCost()
					.doubleValue();
			cv[j] = root.getFirm().getSPL().getContainedProducts().get(j)
					.getUnitCost().doubleValue();
		}

		for (int l = 0; l < numberOfAssets; l++) {
			ca[l] = root.getFirm().getSSF().getContainedAssets().get(l)
					.getSetupCost().doubleValue();
		}

		for (int j = 0; j < numberOfProducts; j++) {
			for (int l = 0; l < numberOfAssets; l++) {
				// set whether the System of the Product contains the asset 
				a[j][l] = root
						.getFirm()
						.getSPL()
						.getContainedProducts()
						.get(j)
						.getComprisingSystem()
						.getAssets()
						.contains(
								root.getFirm().getSSF().getContainedAssets()
										.get(l));
			}
		}

		return new VbpoProblemDescription.Firm(cv, cf, ca, a);
	}

	private VbpoProblemDescription.Competition createCompetitionDescription() {
		double[] w = new double[numberOfCustomerSegments];// { 0.0, 0.0, 0.0
															// };// Here we need
															// some calculations
		// its the best competitor for each segments. That has to be calculated
		// before.

		for (int i = 0; i < numberOfCompetitors; i++) {
			w[i] = bestCompetitiveOfferingFor(root.getCustomers()
					.getCustomerSegments().get(i));
		}

		return new VbpoProblemDescription.Competition(w);
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
		WTP wtp = null;
		Competitor competitor = null;
		Price competitorPrice = null;
		
		for (Iterator iterWTP = segment.getWTPs().iterator(); iterWTP.hasNext();) {
			wtp = (WTP) iterWTP.next();
			for (Iterator iterCompetitor = root.getCompetition()
					.getCompetitors().iterator(); iterCompetitor.hasNext();) {
				competitor = (Competitor) iterCompetitor.next();
				for (Iterator iterPrice = competitor.getPrices().iterator(); iterPrice
						.hasNext();) {
					competitorPrice = (Price) iterPrice.next();
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
 