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

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;

/**
 * Provides lookup facilities to relate assets, products, and segments to positions
 * in the arrays of the solution.
 * 
 * @author Johannes MŸller
 * @version 0.2
 *
 */
public class Lookup {

	
	private Hashtable<Product, Integer> productLookup = new Hashtable<Product, Integer>();
	private Hashtable<Asset, Integer>   assetLookup   = new Hashtable<Asset, Integer>();
	private Hashtable<CustomerSegment, Integer> segmentLookup = new Hashtable<CustomerSegment, Integer>();
	
	public Hashtable<Product, Integer> getProductLookup() {
		return productLookup;
	}
	public void setProductLookup(Hashtable<Product, Integer> productLookup) {
		this.productLookup = productLookup;
	}
	public Hashtable<Asset, Integer> getAssetLookup() {
		return assetLookup;
	}
	public void setAssetLookup(Hashtable<Asset, Integer> assetLookup) {
		this.assetLookup = assetLookup;
	}
	public Hashtable<CustomerSegment, Integer> getSegmentLookup() {
		return segmentLookup;
	}
	public void setSegmentLookup(Hashtable<CustomerSegment, Integer> segmentLookup) {
		this.segmentLookup = segmentLookup;
	}
	
}
/*EOF*/