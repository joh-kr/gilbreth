/**
 * Copyright 2011 Johannes M�ller, University of Leipzig
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

package de.uni_leipzig.iwi.gilbreth.result.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.presentation.Lookup;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;

/**
 * Is responsible for building a ResultBean on the basis of a given VBPODataModel and
 * a de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution
 * 
 * @author Johannes M�ller
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
		return root.getHasAFirm().getFirmHasSSF().getSSFContainsAsset().size();
	}
	
	/**
	 * calculates the number of features in the model.
	 * 
	 * @return number of features.
	 */
	private int numberOfFeatures(){
		return root.getHasAFirm().getFirmHasSPL().getSPLComprisesofFeature().size();
	}

	/**
	 * calculates the number of products in the model.
	 * 
	 * @return number of products.
	 */
	private int numberOfProducts(){
		return root.getHasAFirm().getFirmHasSPL().getSPLContainsProduct().size();
	}
	
	/**
	 * calculates the number of systems in the model.
	 * 
	 * @return number of systems.
	 */
	private int numberOfSystems(){
		return root.getHasAFirm().getFirmHasSSF().getSSFContainsSystem().size();
	}
	
	/**
	 * calculates the number of customer segments in the model.
	 * 
	 * @return number of customer segments.
	 */
	private int numberOfCustomerSegments(){
		return root.getHasCustomers().getCustomersConsistsOfCustomerSegments().size();
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
		
		for(Iterator iter = root.getHasAFirm().getFirmHasSPL().
				getSPLContainsProduct().iterator();iter.hasNext();){
			
			product = (Product)iter.next();
			features = new String[product.getProductHasFeature().size()];
			for(int i = 0; i < product.getProductHasFeature().size(); i++){
				features[i] = product.getProductHasFeature().get(i).getName();
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
		for(Iterator iter = root.getHasCustomers().getCustomersConsistsOfCustomerSegments().iterator(); iter.hasNext();){
			
			segment = (CustomerSegment) iter.next();	
			for(int i = 0; i < root.getHasAFirm().getFirmHasSPL().getSPLContainsProduct().size(); i++){
				if(solution.getX()[lookup.getSegmentLookup().get(segment)][i]){
					product = root.getHasAFirm().getFirmHasSPL().getSPLContainsProduct().get(i);
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
		
		for(Iterator iter = root.getHasAFirm().getFirmHasSPL().getSPLComprisesofFeature().iterator(); iter.hasNext();){
			feature = (Feature) iter.next();
			impact = 0.0d;
			for(Iterator assetIter = assetImpacts.iterator(); assetIter.hasNext();){
				assetImpact = (AssetImpact) assetIter.next();
				for(Iterator featureAssetIter = feature.getFeatureRealizedByAsset().iterator(); featureAssetIter.hasNext();){
					featureAsset = (Asset) featureAssetIter.next();
					if(featureAsset.getName().equals(assetImpact.getAssetName())){
						impact += assetImpact.getImpact();
					}
				}
			}
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
			asset = root.getHasAFirm().getFirmHasSSF().getSSFContainsAsset().get(assetContainer.asset);
			
			map.add(new AssetImpact(asset.getName(), assetContainer.delta_profit));
		}
		
		return map;
	}

}
/*EOF*/
