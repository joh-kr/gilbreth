/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.acape;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getType <em>Type</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getLhs <em>Lhs</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getRhs <em>Rhs</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.uni_leipzig.iwi.gilbreth.acape.ConstraintType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ConstraintType
	 * @see #setType(ConstraintType)
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getConstraint_Type()
	 * @model
	 * @generated
	 */
	ConstraintType getType();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ConstraintType
	 * @see #getType()
	 * @generated
	 */
	void setType(ConstraintType value);

	/**
	 * Returns the value of the '<em><b>Lhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lhs</em>' reference.
	 * @see #setLhs(Entity)
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getConstraint_Lhs()
	 * @model
	 * @generated
	 */
	Entity getLhs();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getLhs <em>Lhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lhs</em>' reference.
	 * @see #getLhs()
	 * @generated
	 */
	void setLhs(Entity value);

	/**
	 * Returns the value of the '<em><b>Rhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rhs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs</em>' reference.
	 * @see #setRhs(Entity)
	 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapePackage#getConstraint_Rhs()
	 * @model
	 * @generated
	 */
	Entity getRhs();

	/**
	 * Sets the value of the '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getRhs <em>Rhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs</em>' reference.
	 * @see #getRhs()
	 * @generated
	 */
	void setRhs(Entity value);

} // Constraint
