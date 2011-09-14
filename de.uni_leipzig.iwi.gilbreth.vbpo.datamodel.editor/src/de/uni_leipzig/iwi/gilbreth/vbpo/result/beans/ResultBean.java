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

package de.uni_leipzig.iwi.gilbreth.vbpo.result.beans;

import java.util.Collection;

/**
 * Represents the result of the optimization run. Contains all data that is
 * extracted from the optimization run.
 * 
 * @author Johannes MŸller
 * @version 0.3
 *
 */
public class ResultBean {
	
	// --- Member Section

	private Collection<ProductFeatures> productFeatures;
	
	private Collection<ProductSegmentAssignment> productSegmentAssignments;
	
	private Collection<FeatureImpact> featureImpacts;
	
	private Collection<AssetImpact> assetImpacts;

	private Collection<SystemImpact> systemImpacts;
	
	private long numberOfProducts;
	
	private long numberOfAssets;
	
	private long numberOfSystems;
	
	private long numberOfCustomerSegments;
	
	private long numberOfFeatures;
	
	private double maxProfit;
	
	// --- Construction Section
	
	public ResultBean(Collection<ProductFeatures> productFeatures,
			Collection<ProductSegmentAssignment> productSegmentAssignment) {
		super();
		this.productFeatures = productFeatures;
		this.productSegmentAssignments = productSegmentAssignment;
	}

	public ResultBean(){}
	
	// --- Getter/Setter Section

	public Collection<ProductFeatures> getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(Collection<ProductFeatures> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public Collection<ProductSegmentAssignment> getProductSegmentAssignments() {
		return productSegmentAssignments;
	}
	
	public void setProductSegmentAssignments(
			Collection<ProductSegmentAssignment> productSegmentAssignments) {
		this.productSegmentAssignments = productSegmentAssignments;
	}

	public long getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(long numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public long getNumberOfAssets() {
		return numberOfAssets;
	}

	public void setNumberOfAssets(long numberOfAssets) {
		this.numberOfAssets = numberOfAssets;
	}

	public long getNumberOfSystems() {
		return numberOfSystems;
	}

	public void setNumberOfSystems(long numberOfSystems) {
		this.numberOfSystems = numberOfSystems;
	}

	public long getNumberOfCustomerSegments() {
		return numberOfCustomerSegments;
	}

	public void setNumberOfCustomerSegments(long numberOfCustomerSegments) {
		this.numberOfCustomerSegments = numberOfCustomerSegments;
	}

	public double getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(double maxProfit) {
		this.maxProfit = maxProfit;
	}

	public Collection<FeatureImpact> getFeatureImpacts() {
		return featureImpacts;
	}

	public void setFeatureImpacts(Collection<FeatureImpact> featureImpacts) {
		this.featureImpacts = featureImpacts;
	}

	public long getNumberOfFeatures() {
		return numberOfFeatures;
	}

	public void setNumberOfFeatures(long numberOfFeatures) {
		this.numberOfFeatures = numberOfFeatures;
	}

	public Collection<AssetImpact> getAssetImpacts() {
		return assetImpacts;
	}

	public void setAssetImpacts(Collection<AssetImpact> assetImpacts) {
		this.assetImpacts = assetImpacts;
	}

	public Collection<SystemImpact> getSystemImpacts() {
		return systemImpacts;
	}

	public void setSystemImpacts(Collection<SystemImpact> systemImpacts) {
		this.systemImpacts = systemImpacts;
	}
}
/*EOF*/
