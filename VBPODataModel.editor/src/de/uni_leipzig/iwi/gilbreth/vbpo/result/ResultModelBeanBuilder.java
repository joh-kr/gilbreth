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

package de.uni_leipzig.iwi.gilbreth.vbpo.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;
import de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor.optimization.Lookup;
import de.uni_leipzig.iwi.gilbreth.vbpo.result.beans.AssetImpact;
import de.uni_leipzig.iwi.gilbreth.vbpo.result.beans.FeatureImpact;
import de.uni_leipzig.iwi.gilbreth.vbpo.result.beans.ProductFeatures;
import de.uni_leipzig.iwi.gilbreth.vbpo.result.beans.ProductSegmentAssignment;
import de.uni_leipzig.iwi.gilbreth.vbpo.result.beans.ResultBean;

/**
 * Is responsible for building a ResultBean on the basis of a given VBPODataModel and
 * a de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution
 * 
 * @author Johannes MŸller
 * @version 0.1
 *
 */
public class ResultModelBeanBuilder {
	private  VBPODataModel root;
	private Solution solution;
	private Lookup lookup;
	
	/**
	 * Creates a ResultModelBeanBuilder with the given sources.
	 * 
	 * @param root the VBPODataModel
	 * @param solution the de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution as
	 * result of a optimization run
	 * @param lookup aLookup that helps identifying the positions of Assets, Products and Segments in arrays 
	 * of the solution.
	 */
	public ResultModelBeanBuilder(VBPODataModel root, Solution solution, Lookup lookup){
		this.root = root;
		this.solution = solution;
		this.lookup = lookup;
	}
	
	/**
	 * builds a ResultBean on the basis of the sources provided at the construction of the ResultBeanBuilder
	 * 
	 * @return a ResultBean representing the results of a optimization run
	 */
	public ResultBean buildResultBean(){
		// check solution for null
		
		ResultBean result = new ResultBean();

		result.setNumberOfAssets(numberOfAssets());
		result.setNumberOfFeatures(numberOfFeatures());
		result.setNumberOfProducts(numberOfProducts());
		result.setNumberOfSystems(numberOfSystems());
		result.setNumberOfCustomerSegments(numberOfCustomerSegments());
		
		result.setMaxProfit(profit());
		
		result.setProductFeatures(productFeatures());
		result.setProductSegmentAssignments(createProductSegmentAssignments());
		result.setAssetImpacts(assetImpacts());
		result.setFeatureImpacts(featureImpacts(result.getAssetImpacts()));
		
		return result;
	}
	
	
	/**
	 * calculates the number of assets in the model.
	 * 
	 * @return number of assets.
	 */
	private int numberOfAssets(){
		return root.getFirm().getSSF().getContainedAssets().size();
	}
	
	/**
	 * calculates the number of features in the model.
	 * 
	 * @return number of features.
	 */
	private int numberOfFeatures(){
		return root.getFirm().getSPL().getContainedFeatures().size();
	}

	/**
	 * calculates the number of products in the model.
	 * 
	 * @return number of products.
	 */
	private int numberOfProducts(){
		return root.getFirm().getSPL().getContainedProducts().size();
	}
	
	/**
	 * calculates the number of systems in the model.
	 * 
	 * @return number of systems.
	 */
	private int numberOfSystems(){
		return root.getFirm().getSSF().getContainedSystems().size();
	}
	
	/**
	 * calculates the number of customer segments in the model.
	 * 
	 * @return number of customer segments.
	 */
	private int numberOfCustomerSegments(){
		return root.getCustomers().getCustomerSegments().size();
	}	
	
	/**
	 * 
	 * 
	 * @return the maximal profit identified in the optimization run.
	 */
	private double profit(){
		return solution.profit();
	}
	
	/**
	 * 
	 * @return a collection of ProductFeaturesBeans containing the products, their features and the calculated prices.
	 */
	private  Collection<ProductFeatures> productFeatures(){
		Collection<ProductFeatures> map = new ArrayList<ProductFeatures>();
		
		Product product = null;
		String[] features = null;
		double price = 0.0d;
		ProductFeatures productFeatures = null;
		
		for(Iterator iter = root.getFirm().getSPL().
				getContainedProducts().iterator();iter.hasNext();){
			
			product = (Product)iter.next();
			features = new String[product.getFeatures().size()];
			for(int i = 0; i < product.getFeatures().size(); i++){
				features[i] = product.getFeatures().get(i).getName();
			}
			
			price = solution.getP()[lookup.getProductLookup().get(product)];
			
			productFeatures = new ProductFeatures(product.getName(), features, price);
			map.add(productFeatures);
			
		}

		return map;
	}
	
	
	/**
	 * 
	 * @return a collection of ProductSegmentAssignment-Beans. Contains the the assignments 
	 * of products to segments and the possible prices for the products.
	 */
	public  Collection<ProductSegmentAssignment> createProductSegmentAssignments(){
		Collection<ProductSegmentAssignment> map = new ArrayList<ProductSegmentAssignment>();
		
		ProductSegmentAssignment assignment = null;
		Product product = null;
		CustomerSegment segment = null;
		double price = 0.0d;
		for(Iterator iter = root.getCustomers().getCustomerSegments().iterator(); iter.hasNext();){
			
			segment = (CustomerSegment) iter.next();	
			for(int i = 0; i < root.getFirm().getSPL().getContainedProducts().size(); i++){
				if(solution.getX()[lookup.getSegmentLookup().get(segment)][i]){
					product = root.getFirm().getSPL().getContainedProducts().get(i);
					price = solution.getP()[lookup.getProductLookup().get(product)];
					map.add(new ProductSegmentAssignment(product.getName(), segment.getName(), price));
					break;
				}
			}
		}
				
		return map;
	}
	
	/**
	 * 
	 * @param assetImpacts a collection of assetImpacts
	 * @return a collection of FeatureImpacts containing the impact of features to the profit. I.e. how important is it
	 * to have  a feature in a product line with respect to profit.
	 */
	public Collection<FeatureImpact> featureImpacts(Collection<AssetImpact> assetImpacts){
		Collection<FeatureImpact> map = new ArrayList<FeatureImpact>();

		Feature feature = null;
		Asset featureAsset = null;
		AssetImpact assetImpact = null;
		double impact = 0.0d;
		int counter = 0;
		boolean stop = false;
		
		for(Iterator iter = root.getFirm().getSPL().getContainedFeatures().iterator(); iter.hasNext();){
			feature = (Feature) iter.next();
			impact = 0.0d;
			counter = 0;
			stop = false;
			
			for(Iterator assetIter = assetImpacts.iterator(); assetIter.hasNext();){
				assetImpact = (AssetImpact) assetIter.next();
				for(Iterator featureAssetIter = feature.getRealizingAssets().iterator(); featureAssetIter.hasNext();){
					featureAsset = (Asset) featureAssetIter.next();
					if(featureAsset.getName().equals(assetImpact.getAssetName())){
						
						impact += assetImpact.getImpact();
						
						// If one of the assets related to a feature has an impact of 0, than this asset is not used in 
						// any of the products planned to be fielded. Hence the feature is not included in any od the fielded
						// products as well. So the feature has an impact of 0 as well.	
						if(assetImpact.getImpact() == 0.0d){
							impact = 0.0d;
							stop = true;
							break;
						}
						
						counter++;
					}
				}
				if(stop) break;
			}
			// Because we want to normalize the impact of the features
			// as per cent values as the asset impacts as well.
			impact = counter == 0 ? 0.0d : impact / counter;
			
			map.add(new FeatureImpact(feature.getName(), impact));
		}
		
		return map;
		
	}
	
	/**
	 * 
	 * 
	 * @return a collection of AssetImpact-Beans containing the impact of assets on profit.
	 * @see de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution
	 */
	public Collection<AssetImpact> assetImpacts(){
		Collection<AssetImpact> map = new ArrayList<AssetImpact>();
		Collection<Solution.AssetContainer> assetContainers = solution.calculateAssetImportance();
		
		Solution.AssetContainer assetContainer = null;
		Asset asset = null;
		for(Iterator iter = assetContainers.iterator();iter.hasNext();){
			assetContainer = (Solution.AssetContainer) iter.next();
			asset = root.getFirm().getSSF().getContainedAssets().get(assetContainer.asset);
			
			map.add(new AssetImpact(asset.getName(), assetContainer.delta_profit));
		}
		
		return map;
	}

}
/*EOF*/
