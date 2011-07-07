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
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl#getFirm <em>Firm</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl#getCompetition <em>Competition</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl#getCustomers <em>Customers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VBPODataModelImpl extends EObjectImpl implements VBPODataModel {
	/**
	 * The cached value of the '{@link #getFirm() <em>Firm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirm()
	 * @generated
	 * @ordered
	 */
	protected Firm firm;

	/**
	 * The cached value of the '{@link #getCompetition() <em>Competition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompetition()
	 * @generated
	 * @ordered
	 */
	protected Competition competition;

	/**
	 * The cached value of the '{@link #getCustomers() <em>Customers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomers()
	 * @generated
	 * @ordered
	 */
	protected Customers customers;

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
	public Firm getFirm() {
		return firm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFirm(Firm newFirm, NotificationChain msgs) {
		Firm oldFirm = firm;
		firm = newFirm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM, oldFirm, newFirm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirm(Firm newFirm) {
		if (newFirm != firm) {
			NotificationChain msgs = null;
			if (firm != null)
				msgs = ((InternalEObject)firm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM, null, msgs);
			if (newFirm != null)
				msgs = ((InternalEObject)newFirm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM, null, msgs);
			msgs = basicSetFirm(newFirm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM, newFirm, newFirm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Competition getCompetition() {
		return competition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompetition(Competition newCompetition, NotificationChain msgs) {
		Competition oldCompetition = competition;
		competition = newCompetition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION, oldCompetition, newCompetition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompetition(Competition newCompetition) {
		if (newCompetition != competition) {
			NotificationChain msgs = null;
			if (competition != null)
				msgs = ((InternalEObject)competition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION, null, msgs);
			if (newCompetition != null)
				msgs = ((InternalEObject)newCompetition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION, null, msgs);
			msgs = basicSetCompetition(newCompetition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION, newCompetition, newCompetition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customers getCustomers() {
		return customers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomers(Customers newCustomers, NotificationChain msgs) {
		Customers oldCustomers = customers;
		customers = newCustomers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS, oldCustomers, newCustomers);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomers(Customers newCustomers) {
		if (newCustomers != customers) {
			NotificationChain msgs = null;
			if (customers != null)
				msgs = ((InternalEObject)customers).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS, null, msgs);
			if (newCustomers != null)
				msgs = ((InternalEObject)newCustomers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS, null, msgs);
			msgs = basicSetCustomers(newCustomers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS, newCustomers, newCustomers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM:
				return basicSetFirm(null, msgs);
			case VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION:
				return basicSetCompetition(null, msgs);
			case VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS:
				return basicSetCustomers(null, msgs);
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM:
				return getFirm();
			case VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION:
				return getCompetition();
			case VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS:
				return getCustomers();
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM:
				setFirm((Firm)newValue);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION:
				setCompetition((Competition)newValue);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS:
				setCustomers((Customers)newValue);
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM:
				setFirm((Firm)null);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION:
				setCompetition((Competition)null);
				return;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS:
				setCustomers((Customers)null);
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
			case VbpodatamodelPackage.VBPO_DATA_MODEL__FIRM:
				return firm != null;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__COMPETITION:
				return competition != null;
			case VbpodatamodelPackage.VBPO_DATA_MODEL__CUSTOMERS:
				return customers != null;
		}
		return super.eIsSet(featureID);
	}

} //VBPODataModelImpl
