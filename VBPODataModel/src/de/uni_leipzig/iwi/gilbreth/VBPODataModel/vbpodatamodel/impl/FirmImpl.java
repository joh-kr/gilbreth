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
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl#getSPL <em>SPL</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl#getSSF <em>SSF</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FirmImpl extends EObjectImpl implements Firm {
	/**
	 * The cached value of the '{@link #getSPL() <em>SPL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSPL()
	 * @generated
	 * @ordered
	 */
	protected SPL sPL;

	/**
	 * The cached value of the '{@link #getSSF() <em>SSF</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSSF()
	 * @generated
	 * @ordered
	 */
	protected SSF sSF;

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
	public SPL getSPL() {
		return sPL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSPL(SPL newSPL, NotificationChain msgs) {
		SPL oldSPL = sPL;
		sPL = newSPL;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__SPL, oldSPL, newSPL);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSPL(SPL newSPL) {
		if (newSPL != sPL) {
			NotificationChain msgs = null;
			if (sPL != null)
				msgs = ((InternalEObject)sPL).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__SPL, null, msgs);
			if (newSPL != null)
				msgs = ((InternalEObject)newSPL).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__SPL, null, msgs);
			msgs = basicSetSPL(newSPL, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__SPL, newSPL, newSPL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SSF getSSF() {
		return sSF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSSF(SSF newSSF, NotificationChain msgs) {
		SSF oldSSF = sSF;
		sSF = newSSF;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__SSF, oldSSF, newSSF);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSSF(SSF newSSF) {
		if (newSSF != sSF) {
			NotificationChain msgs = null;
			if (sSF != null)
				msgs = ((InternalEObject)sSF).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__SSF, null, msgs);
			if (newSSF != null)
				msgs = ((InternalEObject)newSSF).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VbpodatamodelPackage.FIRM__SSF, null, msgs);
			msgs = basicSetSSF(newSSF, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.FIRM__SSF, newSSF, newSSF));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.FIRM__SPL:
				return basicSetSPL(null, msgs);
			case VbpodatamodelPackage.FIRM__SSF:
				return basicSetSSF(null, msgs);
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
			case VbpodatamodelPackage.FIRM__SPL:
				return getSPL();
			case VbpodatamodelPackage.FIRM__SSF:
				return getSSF();
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
			case VbpodatamodelPackage.FIRM__SPL:
				setSPL((SPL)newValue);
				return;
			case VbpodatamodelPackage.FIRM__SSF:
				setSSF((SSF)newValue);
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
			case VbpodatamodelPackage.FIRM__SPL:
				setSPL((SPL)null);
				return;
			case VbpodatamodelPackage.FIRM__SSF:
				setSSF((SSF)null);
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
			case VbpodatamodelPackage.FIRM__SPL:
				return sPL != null;
			case VbpodatamodelPackage.FIRM__SSF:
				return sSF != null;
		}
		return super.eIsSet(featureID);
	}

} //FirmImpl
