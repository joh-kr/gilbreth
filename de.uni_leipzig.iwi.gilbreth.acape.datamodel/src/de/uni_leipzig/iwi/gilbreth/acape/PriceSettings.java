/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.acape;

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Price Settings</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getPriceSettings()
 * @model
 * @generated
 */
public interface PriceSettings extends EObject {
	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(BigDecimal)
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getPriceSettings_LowerBound()
	 * @model
	 * @generated
	 */
	BigDecimal getLowerBound();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(BigDecimal)
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getPriceSettings_UpperBound()
	 * @model
	 * @generated
	 */
	BigDecimal getUpperBound();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(BigDecimal value);

} // PriceSettings
