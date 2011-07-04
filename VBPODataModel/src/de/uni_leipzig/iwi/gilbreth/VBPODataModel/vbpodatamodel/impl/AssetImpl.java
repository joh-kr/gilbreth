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

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;

import java.math.BigDecimal;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Asset</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.AssetImpl#getReuseCost <em>Reuse Cost</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.AssetImpl#getSetupCost <em>Setup Cost</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssetImpl extends IdentifiableEntityImpl implements Asset {
	/**
	 * The default value of the '{@link #getReuseCost() <em>Reuse Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReuseCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal REUSE_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReuseCost() <em>Reuse Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReuseCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal reuseCost = REUSE_COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getSetupCost() <em>Setup Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetupCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal SETUP_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSetupCost() <em>Setup Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetupCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal setupCost = SETUP_COST_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.ASSET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getReuseCost() {
		return reuseCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReuseCost(BigDecimal newReuseCost) {
		BigDecimal oldReuseCost = reuseCost;
		reuseCost = newReuseCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.ASSET__REUSE_COST, oldReuseCost, reuseCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getSetupCost() {
		return setupCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSetupCost(BigDecimal newSetupCost) {
		BigDecimal oldSetupCost = setupCost;
		setupCost = newSetupCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.ASSET__SETUP_COST, oldSetupCost, setupCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VbpodatamodelPackage.ASSET__REUSE_COST:
				return getReuseCost();
			case VbpodatamodelPackage.ASSET__SETUP_COST:
				return getSetupCost();
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
			case VbpodatamodelPackage.ASSET__REUSE_COST:
				setReuseCost((BigDecimal)newValue);
				return;
			case VbpodatamodelPackage.ASSET__SETUP_COST:
				setSetupCost((BigDecimal)newValue);
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
			case VbpodatamodelPackage.ASSET__REUSE_COST:
				setReuseCost(REUSE_COST_EDEFAULT);
				return;
			case VbpodatamodelPackage.ASSET__SETUP_COST:
				setSetupCost(SETUP_COST_EDEFAULT);
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
			case VbpodatamodelPackage.ASSET__REUSE_COST:
				return REUSE_COST_EDEFAULT == null ? reuseCost != null : !REUSE_COST_EDEFAULT.equals(reuseCost);
			case VbpodatamodelPackage.ASSET__SETUP_COST:
				return SETUP_COST_EDEFAULT == null ? setupCost != null : !SETUP_COST_EDEFAULT.equals(setupCost);
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
		result.append(" (reuseCost: ");
		result.append(reuseCost);
		result.append(", setupCost: ");
		result.append(setupCost);
		result.append(')');
		return result.toString();
	}

} //AssetImpl
