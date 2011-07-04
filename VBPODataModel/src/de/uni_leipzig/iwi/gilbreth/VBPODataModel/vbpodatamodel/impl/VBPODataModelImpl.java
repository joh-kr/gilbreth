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
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>VBPO Data Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl#getHasAFirm <em>Has AFirm</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl#getHasCompetition <em>Has Competition</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl#getHasCustomers <em>Has Customers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VBPODataModelImpl extends EObjectImpl implements VBPODataModel {
	/**
	 * The cached value of the '{@link #getHasAFirm() <em>Has AFirm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasAFirm()
	 * @generated
	 * @ordered
	 */
	protected Firm hasAFirm;

	/**
	 * The cached value of the '{@link #getHasCompetition() <em>Has Competition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasCompetition()
	 * @generated
	 * @ordered
	 */
	protected Competition hasCompetition;

	/**
	 * The cached value of the '{@link #getHasCustomers() <em>Has Customers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasCustomers()
	 * @generated
	 * @ordered
	 */
	protected Customers hasCustomers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VBPODataModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.VBPO_DATA_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Firm getHasAFirm() {
		return hasAFirm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHasAFirm(Firm newHasAFirm, NotificationChain msgs) {
		Firm oldHasAFirm = hasAFirm;
		hasAFirm = newHasAFirm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM, oldHasAFirm, newHasAFirm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasAFirm(Firm newHasAFirm) {
		if (newHasAFirm != hasAFirm) {
			NotificationChain msgs = null;
			if (hasAFirm != null)
				msgs = ((InternalEObject)hasAFirm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM, null, msgs);
			if (newHasAFirm != null)
				msgs = ((InternalEObject)newHasAFirm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM, null, msgs);
			msgs = basicSetHasAFirm(newHasAFirm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM, newHasAFirm, newHasAFirm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Competition getHasCompetition() {
		return hasCompetition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHasCompetition(Competition newHasCompetition, NotificationChain msgs) {
		Competition oldHasCompetition = hasCompetition;
		hasCompetition = newHasCompetition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION, oldHasCompetition, newHasCompetition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasCompetition(Competition newHasCompetition) {
		if (newHasCompetition != hasCompetition) {
			NotificationChain msgs = null;
			if (hasCompetition != null)
				msgs = ((InternalEObject)hasCompetition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION, null, msgs);
			if (newHasCompetition != null)
				msgs = ((InternalEObject)newHasCompetition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION, null, msgs);
			msgs = basicSetHasCompetition(newHasCompetition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION, newHasCompetition, newHasCompetition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customers getHasCustomers() {
		return hasCustomers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHasCustomers(Customers newHasCustomers, NotificationChain msgs) {
		Customers oldHasCustomers = hasCustomers;
		hasCustomers = newHasCustomers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS, oldHasCustomers, newHasCustomers);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasCustomers(Customers newHasCustomers) {
		if (newHasCustomers != hasCustomers) {
			NotificationChain msgs = null;
			if (hasCustomers != null)
				msgs = ((InternalEObject)hasCustomers).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS, null, msgs);
			if (newHasCustomers != null)
				msgs = ((InternalEObject)newHasCustomers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS, null, msgs);
			msgs = basicSetHasCustomers(newHasCustomers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS, newHasCustomers, newHasCustomers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM:
				return basicSetHasAFirm(null, msgs);
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION:
				return basicSetHasCompetition(null, msgs);
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS:
				return basicSetHasCustomers(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM:
				return getHasAFirm();
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION:
				return getHasCompetition();
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS:
				return getHasCustomers();
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM:
				setHasAFirm((Firm)newValue);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION:
				setHasCompetition((Competition)newValue);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS:
				setHasCustomers((Customers)newValue);
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM:
				setHasAFirm((Firm)null);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION:
				setHasCompetition((Competition)null);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS:
				setHasCustomers((Customers)null);
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_AFIRM:
				return hasAFirm != null;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_COMPETITION:
				return hasCompetition != null;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__HAS_CUSTOMERS:
				return hasCustomers != null;
		}
		return super.eIsSet(featureID);
	}

} //VBPODataModelImpl
