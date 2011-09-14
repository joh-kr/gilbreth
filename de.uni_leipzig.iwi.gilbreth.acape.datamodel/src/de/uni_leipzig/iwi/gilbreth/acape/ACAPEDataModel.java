/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.acape;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ACAPE Data Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getName <em>Name</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getPriceSettings <em>Price Settings</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getACAPEDataModel()
 * @model
 * @generated
 */
public interface ACAPEDataModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.acape.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getACAPEDataModel_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

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
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getACAPEDataModel_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Price Settings</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price Settings</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price Settings</em>' containment reference.
	 * @see #setPriceSettings(PriceSettings)
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getACAPEDataModel_PriceSettings()
	 * @model containment="true" required="true"
	 * @generated
	 */
	PriceSettings getPriceSettings();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getPriceSettings <em>Price Settings</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Settings</em>' containment reference.
	 * @see #getPriceSettings()
	 * @generated
	 */
	void setPriceSettings(PriceSettings value);

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.uni_leipzig.iwi.gilbreth.acape.Constraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' containment reference list.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getACAPEDataModel_Constraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<Constraint> getConstraints();

} // ACAPEDataModel
