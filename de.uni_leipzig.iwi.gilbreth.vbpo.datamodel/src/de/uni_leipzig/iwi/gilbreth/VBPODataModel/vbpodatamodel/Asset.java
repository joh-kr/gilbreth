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

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Asset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getReuseCost <em>Reuse Cost</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getSetupCost <em>Setup Cost</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getAsset()
 * @model
 * @generated
 */
public interface Asset extends IdentifiableEntity {
	/**
	 * Returns the value of the '<em><b>Reuse Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reuse Cost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reuse Cost</em>' attribute.
	 * @see #setReuseCost(BigDecimal)
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getAsset_ReuseCost()
	 * @model
	 * @generated
	 */
	BigDecimal getReuseCost();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getReuseCost <em>Reuse Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reuse Cost</em>' attribute.
	 * @see #getReuseCost()
	 * @generated
	 */
	void setReuseCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Setup Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setup Cost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Setup Cost</em>' attribute.
	 * @see #setSetupCost(BigDecimal)
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getAsset_SetupCost()
	 * @model
	 * @generated
	 */
	BigDecimal getSetupCost();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getSetupCost <em>Setup Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Setup Cost</em>' attribute.
	 * @see #getSetupCost()
	 * @generated
	 */
	void setSetupCost(BigDecimal value);

} // Asset
