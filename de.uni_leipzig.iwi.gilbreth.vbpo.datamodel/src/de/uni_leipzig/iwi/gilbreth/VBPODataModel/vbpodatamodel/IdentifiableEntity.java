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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifiable Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getName <em>Name</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getIdentifiableEntity()
 * @model abstract="true"
 * @generated
 */
public interface IdentifiableEntity extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getIdentifiableEntity_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage#getIdentifiableEntity_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // IdentifiableEntity
