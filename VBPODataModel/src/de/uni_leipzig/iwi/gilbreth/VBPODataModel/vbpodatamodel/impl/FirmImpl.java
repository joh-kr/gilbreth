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

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Firm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl#getFirmHasSPL <em>Firm Has SPL</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl#getFirmHasSSF <em>Firm Has SSF</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FirmImpl extends EObjectImpl implements Firm {
	/**
	 * The cached value of the '{@link #getFirmHasSPL() <em>Firm Has SPL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirmHasSPL()
	 * @generated
	 * @ordered
	 */
	protected SPL firmHasSPL;

	/**
	 * The cached value of the '{@link #getFirmHasSSF() <em>Firm Has SSF</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirmHasSSF()
	 * @generated
	 * @ordered
	 */
	protected SSF firmHasSSF;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FirmImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.FIRM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPL getFirmHasSPL() {
		return firmHasSPL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFirmHasSPL(SPL newFirmHasSPL, NotificationChain msgs) {
		SPL oldFirmHasSPL = firmHasSPL;
		firmHasSPL = newFirmHasSPL;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__FIRM_HAS_SPL, oldFirmHasSPL, newFirmHasSPL);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirmHasSPL(SPL newFirmHasSPL) {
		if (newFirmHasSPL != firmHasSPL) {
			NotificationChain msgs = null;
			if (firmHasSPL != null)
				msgs = ((InternalEObject)firmHasSPL).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__FIRM_HAS_SPL, null, msgs);
			if (newFirmHasSPL != null)
				msgs = ((InternalEObject)newFirmHasSPL).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__FIRM_HAS_SPL, null, msgs);
			msgs = basicSetFirmHasSPL(newFirmHasSPL, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__FIRM_HAS_SPL, newFirmHasSPL, newFirmHasSPL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SSF getFirmHasSSF() {
		return firmHasSSF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFirmHasSSF(SSF newFirmHasSSF, NotificationChain msgs) {
		SSF oldFirmHasSSF = firmHasSSF;
		firmHasSSF = newFirmHasSSF;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__FIRM_HAS_SSF, oldFirmHasSSF, newFirmHasSSF);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirmHasSSF(SSF newFirmHasSSF) {
		if (newFirmHasSSF != firmHasSSF) {
			NotificationChain msgs = null;
			if (firmHasSSF != null)
				msgs = ((InternalEObject)firmHasSSF).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__FIRM_HAS_SSF, null, msgs);
			if (newFirmHasSSF != null)
				msgs = ((InternalEObject)newFirmHasSSF).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__FIRM_HAS_SSF, null, msgs);
			msgs = basicSetFirmHasSSF(newFirmHasSSF, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__FIRM_HAS_SSF, newFirmHasSSF, newFirmHasSSF));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SPL:
				return basicSetFirmHasSPL(null, msgs);
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SSF:
				return basicSetFirmHasSSF(null, msgs);
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
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SPL:
				return getFirmHasSPL();
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SSF:
				return getFirmHasSSF();
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
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SPL:
				setFirmHasSPL((SPL)newValue);
				return;
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SSF:
				setFirmHasSSF((SSF)newValue);
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
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SPL:
				setFirmHasSPL((SPL)null);
				return;
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SSF:
				setFirmHasSSF((SSF)null);
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
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SPL:
				return firmHasSPL != null;
			case VbpodatamodelPackage.FIRM__FIRM_HAS_SSF:
				return firmHasSSF != null;
		}
		return super.eIsSet(featureID);
	}

} //FirmImpl
