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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SystemImpl#getImplementationCost <em>Implementation Cost</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SystemImpl#getSystemUsesAsset <em>System Uses Asset</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemImpl extends IdentifiableEntityImpl implements de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System {
	/**
	 * The default value of the '{@link #getImplementationCost() <em>Implementation Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal IMPLEMENTATION_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationCost() <em>Implementation Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal implementationCost = IMPLEMENTATION_COST_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSystemUsesAsset() <em>System Uses Asset</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemUsesAsset()
	 * @generated
	 * @ordered
	 */
	protected EList<Asset> systemUsesAsset;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getImplementationCost() {
		return implementationCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementationCost(BigDecimal newImplementationCost) {
		BigDecimal oldImplementationCost = implementationCost;
		implementationCost = newImplementationCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.SYSTEM__IMPLEMENTATION_COST, oldImplementationCost, implementationCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Asset> getSystemUsesAsset() {
		if (systemUsesAsset == null) {
			systemUsesAsset = new EObjectResolvingEList<Asset>(Asset.class, this, VbpodatamodelPackage.SYSTEM__SYSTEM_USES_ASSET);
		}
		return systemUsesAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VbpodatamodelPackage.SYSTEM__IMPLEMENTATION_COST:
				return getImplementationCost();
			case VbpodatamodelPackage.SYSTEM__SYSTEM_USES_ASSET:
				return getSystemUsesAsset();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case VbpodatamodelPackage.SYSTEM__IMPLEMENTATION_COST:
				setImplementationCost((BigDecimal)newValue);
				return;
			case VbpodatamodelPackage.SYSTEM__SYSTEM_USES_ASSET:
				getSystemUsesAsset().clear();
				getSystemUsesAsset().addAll((Collection<? extends Asset>)newValue);
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
			case VbpodatamodelPackage.SYSTEM__IMPLEMENTATION_COST:
				setImplementationCost(IMPLEMENTATION_COST_EDEFAULT);
				return;
			case VbpodatamodelPackage.SYSTEM__SYSTEM_USES_ASSET:
				getSystemUsesAsset().clear();
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
			case VbpodatamodelPackage.SYSTEM__IMPLEMENTATION_COST:
				return IMPLEMENTATION_COST_EDEFAULT == null ? implementationCost != null : !IMPLEMENTATION_COST_EDEFAULT.equals(implementationCost);
			case VbpodatamodelPackage.SYSTEM__SYSTEM_USES_ASSET:
				return systemUsesAsset != null && !systemUsesAsset.isEmpty();
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
		result.append(" (implementationCost: ");
		result.append(implementationCost);
		result.append(')');
		return result.toString();
	}

} //SystemImpl
