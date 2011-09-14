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

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL;
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
 * An implementation of the model object '<em><b>SPL</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SPLImpl#getContainedProducts <em>Contained Products</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SPLImpl#getContainedFeatures <em>Contained Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPLImpl extends EObjectImpl implements SPL {
	/**
	 * The cached value of the '{@link #getContainedProducts() <em>Contained Products</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> containedProducts;

	/**
	 * The cached value of the '{@link #getContainedFeatures() <em>Contained Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> containedFeatures;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SPLImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.SPL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Product> getContainedProducts() {
		if (containedProducts == null) {
			containedProducts = new EObjectContainmentEList<Product>(Product.class, this, VbpodatamodelPackage.SPL__CONTAINED_PRODUCTS);
		}
		return containedProducts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getContainedFeatures() {
		if (containedFeatures == null) {
			containedFeatures = new EObjectContainmentEList<Feature>(Feature.class, this, VbpodatamodelPackage.SPL__CONTAINED_FEATURES);
		}
		return containedFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.SPL__CONTAINED_PRODUCTS:
				return ((InternalEList<?>)getContainedProducts()).basicRemove(otherEnd, msgs);
			case VbpodatamodelPackage.SPL__CONTAINED_FEATURES:
				return ((InternalEList<?>)getContainedFeatures()).basicRemove(otherEnd, msgs);
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
			case VbpodatamodelPackage.SPL__CONTAINED_PRODUCTS:
				return getContainedProducts();
			case VbpodatamodelPackage.SPL__CONTAINED_FEATURES:
				return getContainedFeatures();
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
			case VbpodatamodelPackage.SPL__CONTAINED_PRODUCTS:
				getContainedProducts().clear();
				getContainedProducts().addAll((Collection<? extends Product>)newValue);
				return;
			case VbpodatamodelPackage.SPL__CONTAINED_FEATURES:
				getContainedFeatures().clear();
				getContainedFeatures().addAll((Collection<? extends Feature>)newValue);
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
			case VbpodatamodelPackage.SPL__CONTAINED_PRODUCTS:
				getContainedProducts().clear();
				return;
			case VbpodatamodelPackage.SPL__CONTAINED_FEATURES:
				getContainedFeatures().clear();
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
			case VbpodatamodelPackage.SPL__CONTAINED_PRODUCTS:
				return containedProducts != null && !containedProducts.isEmpty();
			case VbpodatamodelPackage.SPL__CONTAINED_FEATURES:
				return containedFeatures != null && !containedFeatures.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SPLImpl
