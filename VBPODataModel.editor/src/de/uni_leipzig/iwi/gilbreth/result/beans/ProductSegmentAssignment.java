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

/**
 * The Assigment of Products to segments and the possible prices for the products
 * in that segment.
 * 
 * @author Johannes MŸller
 * @version 0.1
 *
 */
public class ProductSegmentAssignment {
	
	
	private String product;
	private String segment;
	private double price;
	
	public ProductSegmentAssignment(){
		
	}
	

	public ProductSegmentAssignment(String product, String segment, double price) {
		super();
		this.product = product;
		this.segment = segment;
		this.price = price;
	}

	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	

}
