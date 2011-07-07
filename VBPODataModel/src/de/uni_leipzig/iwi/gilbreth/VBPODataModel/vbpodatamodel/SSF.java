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
 * A representation of the model object '<em><b>SSF</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getContainedSystems <em>Contained Systems</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getContainedAssets <em>Contained Assets</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getSSF()
 * @model
 * @generated
 */
public interface SSF extends EObject {
	/**
	 * Returns the value of the '<em><b>Contained Systems</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SSF Contains System</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Systems</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getSSF_ContainedSystems()
	 * @model containment="true"
	 * @generated
	 */
	EList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System> getContainedSystems();

	/**
	 * Returns the value of the '<em><b>Contained Assets</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SSF Contains Asset</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Assets</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getSSF_ContainedAssets()
	 * @model containment="true"
	 * @generated
	 */
	EList<Asset> getContainedAssets();

} // SSF
