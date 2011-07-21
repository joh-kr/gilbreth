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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPL</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getContainedProducts <em>Contained Products</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getContainedFeatures <em>Contained Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getSPL()
 * @model
 * @generated
 */
public interface SPL extends EObject {
	/**
	 * Returns the value of the '<em><b>Contained Products</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SPL Contains Product</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Products</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getSPL_ContainedProducts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Product> getContainedProducts();

	/**
	 * Returns the value of the '<em><b>Contained Features</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SPL Comprisesof Feature</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Features</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getSPL_ContainedFeatures()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Feature> getContainedFeatures();

} // SPL
