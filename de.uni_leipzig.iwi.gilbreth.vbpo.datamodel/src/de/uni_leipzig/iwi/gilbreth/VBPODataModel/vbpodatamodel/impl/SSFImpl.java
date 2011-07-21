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
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SSF</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl#getContainedSystems <em>Contained Systems</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl#getContainedAssets <em>Contained Assets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SSFImpl extends EObjectImpl implements SSF {
	/**
	 * The cached value of the '{@link #getContainedSystems() <em>Contained Systems</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedSystems()
	 * @generated
	 * @ordered
	 */
	protected EList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System> containedSystems;

	/**
	 * The cached value of the '{@link #getContainedAssets() <em>Contained Assets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedAssets()
	 * @generated
	 * @ordered
	 */
	protected EList<Asset> containedAssets;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SSFImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.SSF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System> getContainedSystems() {
		if (containedSystems == null) {
			containedSystems = new EObjectContainmentEList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System>(de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System.class, this, VbpodatamodelPackage.SSF__CONTAINED_SYSTEMS);
		}
		return containedSystems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Asset> getContainedAssets() {
		if (containedAssets == null) {
			containedAssets = new EObjectContainmentEList<Asset>(Asset.class, this, VbpodatamodelPackage.SSF__CONTAINED_ASSETS);
		}
		return containedAssets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.SSF__CONTAINED_SYSTEMS:
				return ((InternalEList<?>)getContainedSystems()).basicRemove(otherEnd, msgs);
			case VbpodatamodelPackage.SSF__CONTAINED_ASSETS:
				return ((InternalEList<?>)getContainedAssets()).basicRemove(otherEnd, msgs);
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
			case VbpodatamodelPackage.SSF__CONTAINED_SYSTEMS:
				return getContainedSystems();
			case VbpodatamodelPackage.SSF__CONTAINED_ASSETS:
				return getContainedAssets();
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
			case VbpodatamodelPackage.SSF__CONTAINED_SYSTEMS:
				getContainedSystems().clear();
				getContainedSystems().addAll((Collection<? extends de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System>)newValue);
				return;
			case VbpodatamodelPackage.SSF__CONTAINED_ASSETS:
				getContainedAssets().clear();
				getContainedAssets().addAll((Collection<? extends Asset>)newValue);
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
			case VbpodatamodelPackage.SSF__CONTAINED_SYSTEMS:
				getContainedSystems().clear();
				return;
			case VbpodatamodelPackage.SSF__CONTAINED_ASSETS:
				getContainedAssets().clear();
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
			case VbpodatamodelPackage.SSF__CONTAINED_SYSTEMS:
				return containedSystems != null && !containedSystems.isEmpty();
			case VbpodatamodelPackage.SSF__CONTAINED_ASSETS:
				return containedAssets != null && !containedAssets.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SSFImpl
