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
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Competitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor#getPrices <em>Prices</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getCompetitor()
 * @model
 * @generated
 */
public interface Competitor extends IdentifiableEntity {
	/**
	 * Returns the value of the '<em><b>Prices</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Competitor Has Price</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prices</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getCompetitor_Prices()
	 * @model containment="true"
	 * @generated
	 */
	EList<Price> getPrices();

} // Competitor
