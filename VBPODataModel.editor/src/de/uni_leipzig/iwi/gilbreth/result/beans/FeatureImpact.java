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
 * The impact of features to profit.
 * 
 * @author Johannes MŸller
 * @version 0.1
 */
public class FeatureImpact {
	public FeatureImpact(String featureName, double impact) {
		super();
		this.featureName = featureName;
		this.impact = impact;
	}
	
	public FeatureImpact(){}
	
	private String featureName;
	private double impact;
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public double getImpact() {
		return impact;
	}
	public void setImpact(double impact) {
		this.impact = impact;
	}

}
/*EOF*/