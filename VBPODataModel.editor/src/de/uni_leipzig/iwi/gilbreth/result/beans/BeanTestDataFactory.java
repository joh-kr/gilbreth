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

package de.uni_leipzig.iwi.gilbreth.result.beans;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Generates test data. Mainly used externally by the iReport report editor
 * to display some test data.
 * 
 * @author Johannes MŸller
 *
 */
@Deprecated
public class BeanTestDataFactory {
	
	
	public static Collection createResultBean(){
		ResultBean result = new ResultBean();
		result.setProductFeatures(createProductFeatures());
		result.setProductSegmentAssignments(createProductSegmentAssignments());
	
		result.setNumberOfAssets(10);
		result.setNumberOfFeatures(10);
		result.setNumberOfCustomerSegments(20);
		result.setNumberOfProducts(10);
		result.setNumberOfSystems(30);
		result.setMaxProfit(100000.00d);
		
		result.setFeatureImpacts(createFeatureImpact());
		
		result.setAssetImpacts(createAssetImpact());
		
		Collection<ResultBean> map = new ArrayList<ResultBean>();
		map.add(result);
		return map;
	}
	
	
	public static Collection createProductSegmentAssignments(){
		Collection<ProductSegmentAssignment> map = new ArrayList<ProductSegmentAssignment>();
		
		map.add(new ProductSegmentAssignment("p1", "s1", 200.00d));
		map.add(new ProductSegmentAssignment("p2", "s1", 210.00d));
		map.add(new ProductSegmentAssignment("p3", "s1", 220.00d));
		map.add(new ProductSegmentAssignment("p4", "s1", 230.00d));
		
		return map;
	}
	
	public static Collection createProductFeatures(){
		Collection<ProductFeatures> map = new ArrayList<ProductFeatures>();
		String[] features = {"m1", "m2", "m3"};
		
		map.add(new ProductFeatures("p1", features, 230.00d));
		map.add(new ProductFeatures("p2", features, 240.00d));
		map.add(new ProductFeatures("p3", features, 250.00d));
		map.add(new ProductFeatures("p4", features, 260.00d));
		
		return map;
	}
	
	public static Collection createFeatureImpact(){
		Collection<FeatureImpact> map = new ArrayList<FeatureImpact>();

		
		map.add(new FeatureImpact("F1", 100.00d));
		map.add(new FeatureImpact("F2", 120.00d));
		map.add(new FeatureImpact("F3", 130.00d));
		map.add(new FeatureImpact("F4", 140.00d));
		map.add(new FeatureImpact("F5", 110.00d));
		map.add(new FeatureImpact("F6", 100.00d));
		map.add(new FeatureImpact("F7", 30.00d));
		map.add(new FeatureImpact("F8", 100.00d));
	
		return map;
	}

	public static Collection createAssetImpact(){
		Collection<AssetImpact> map = new ArrayList<AssetImpact>();

		
		map.add(new AssetImpact("A1", 100.00d));
		map.add(new AssetImpact("A2", 90.00d));
		map.add(new AssetImpact("A3", 10.00d));
		map.add(new AssetImpact("A4", 50.00d));
		map.add(new AssetImpact("A5", 20.00d));
		map.add(new AssetImpact("A6", 50.00d));

	
		return map;
	}
	
}

