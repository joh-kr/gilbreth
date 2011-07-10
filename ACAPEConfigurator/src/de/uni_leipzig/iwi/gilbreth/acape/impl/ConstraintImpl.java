/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.acape.impl;

import de.uni_leipzig.iwi.gilbreth.acape.AcapePackage;
import de.uni_leipzig.iwi.gilbreth.acape.Constraint;
import de.uni_leipzig.iwi.gilbreth.acape.ConstraintType;
import de.uni_leipzig.iwi.gilbreth.acape.Entity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl#getLhs <em>Lhs</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl#getRhs <em>Rhs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintImpl extends EObjectImpl implements Constraint {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ConstraintType TYPE_EDEFAULT = ConstraintType.REQUIRE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ConstraintType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLhs() <em>Lhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLhs()
	 * @generated
	 * @ordered
	 */
	protected Entity lhs;

	/**
	 * The cached value of the '{@link #getRhs() <em>Rhs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRhs()
	 * @generated
	 * @ordered
	 */
	protected Entity rhs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AcapePackage.Literals.CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ConstraintType newType) {
		ConstraintType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AcapePackage.CONSTRAINT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getLhs() {
		if (lhs != null && lhs.eIsProxy()) {
			InternalEObject oldLhs = (InternalEObject)lhs;
			lhs = (Entity)eResolveProxy(oldLhs);
			if (lhs != oldLhs) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AcapePackage.CONSTRAINT__LHS, oldLhs, lhs));
			}
		}
		return lhs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetLhs() {
		return lhs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLhs(Entity newLhs) {
		Entity oldLhs = lhs;
		lhs = newLhs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AcapePackage.CONSTRAINT__LHS, oldLhs, lhs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getRhs() {
		if (rhs != null && rhs.eIsProxy()) {
			InternalEObject oldRhs = (InternalEObject)rhs;
			rhs = (Entity)eResolveProxy(oldRhs);
			if (rhs != oldRhs) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AcapePackage.CONSTRAINT__RHS, oldRhs, rhs));
			}
		}
		return rhs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetRhs() {
		return rhs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRhs(Entity newRhs) {
		Entity oldRhs = rhs;
		rhs = newRhs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AcapePackage.CONSTRAINT__RHS, oldRhs, rhs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AcapePackage.CONSTRAINT__TYPE:
				return getType();
			case AcapePackage.CONSTRAINT__LHS:
				if (resolve) return getLhs();
				return basicGetLhs();
			case AcapePackage.CONSTRAINT__RHS:
				if (resolve) return getRhs();
				return basicGetRhs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AcapePackage.CONSTRAINT__TYPE:
				setType((ConstraintType)newValue);
				return;
			case AcapePackage.CONSTRAINT__LHS:
				setLhs((Entity)newValue);
				return;
			case AcapePackage.CONSTRAINT__RHS:
				setRhs((Entity)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AcapePackage.CONSTRAINT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case AcapePackage.CONSTRAINT__LHS:
				setLhs((Entity)null);
				return;
			case AcapePackage.CONSTRAINT__RHS:
				setRhs((Entity)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AcapePackage.CONSTRAINT__TYPE:
				return type != TYPE_EDEFAULT;
			case AcapePackage.CONSTRAINT__LHS:
				return lhs != null;
			case AcapePackage.CONSTRAINT__RHS:
				return rhs != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //ConstraintImpl
