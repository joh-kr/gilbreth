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
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl#getSSFContainsSystem <em>SSF Contains System</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl#getSSFContainsAsset <em>SSF Contains Asset</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SSFImpl extends EObjectImpl implements SSF {
	/**
	 * The cached value of the '{@link #getSSFContainsSystem() <em>SSF Contains System</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSSFContainsSystem()
	 * @generated
	 * @ordered
	 */
	protected EList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System> sSFContainsSystem;

	/**
	 * The cached value of the '{@link #getSSFContainsAsset() <em>SSF Contains Asset</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSSFContainsAsset()
	 * @generated
	 * @ordered
	 */
	protected EList<Asset> sSFContainsAsset;

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
	public EList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System> getSSFContainsSystem() {
		if (sSFContainsSystem == null) {
			sSFContainsSystem = new EObjectContainmentEList<de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System>(de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System.class, this, VbpodatamodelPackage.SSF__SSF_CONTAINS_SYSTEM);
		}
		return sSFContainsSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Asset> getSSFContainsAsset() {
		if (sSFContainsAsset == null) {
			sSFContainsAsset = new EObjectContainmentEList<Asset>(Asset.class, this, VbpodatamodelPackage.SSF__SSF_CONTAINS_ASSET);
		}
		return sSFContainsAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_SYSTEM:
				return ((InternalEList<?>)getSSFContainsSystem()).basicRemove(otherEnd, msgs);
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_ASSET:
				return ((InternalEList<?>)getSSFContainsAsset()).basicRemove(otherEnd, msgs);
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
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_SYSTEM:
				return getSSFContainsSystem();
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_ASSET:
				return getSSFContainsAsset();
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
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_SYSTEM:
				getSSFContainsSystem().clear();
				getSSFContainsSystem().addAll((Collection<? extends de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System>)newValue);
				return;
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_ASSET:
				getSSFContainsAsset().clear();
				getSSFContainsAsset().addAll((Collection<? extends Asset>)newValue);
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
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_SYSTEM:
				getSSFContainsSystem().clear();
				return;
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_ASSET:
				getSSFContainsAsset().clear();
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
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_SYSTEM:
				return sSFContainsSystem != null && !sSFContainsSystem.isEmpty();
			case VbpodatamodelPackage.SSF__SSF_CONTAINS_ASSET:
				return sSFContainsAsset != null && !sSFContainsAsset.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SSFImpl
