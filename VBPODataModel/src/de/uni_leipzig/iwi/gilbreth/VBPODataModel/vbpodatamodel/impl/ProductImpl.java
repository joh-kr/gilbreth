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
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;

import java.math.BigDecimal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl#getProductComprisesSystem <em>Product Comprises System</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl#getProductHasFeature <em>Product Has Feature</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl#getUnitCost <em>Unit Cost</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProductImpl extends IdentifiableEntityImpl implements Product {
	/**
	 * The cached value of the '{@link #getProductComprisesSystem() <em>Product Comprises System</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductComprisesSystem()
	 * @generated
	 * @ordered
	 */
	protected de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System productComprisesSystem;

	/**
	 * The cached value of the '{@link #getProductHasFeature() <em>Product Has Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductHasFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> productHasFeature;

	/**
	 * The default value of the '{@link #getUnitCost() <em>Unit Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal UNIT_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnitCost() <em>Unit Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnitCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal unitCost = UNIT_COST_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.PRODUCT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System getProductComprisesSystem() {
		if (productComprisesSystem != null && productComprisesSystem.eIsProxy()) {
			InternalEObject oldProductComprisesSystem = (InternalEObject)productComprisesSystem;
			productComprisesSystem = (de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System)eResolveProxy(oldProductComprisesSystem);
			if (productComprisesSystem != oldProductComprisesSystem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, VbpodatamodelPackage.PRODUCT__PRODUCT_COMPRISES_SYSTEM, oldProductComprisesSystem, productComprisesSystem));
			}
		}
		return productComprisesSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System basicGetProductComprisesSystem() {
		return productComprisesSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProductComprisesSystem(de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System newProductComprisesSystem) {
		de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System oldProductComprisesSystem = productComprisesSystem;
		productComprisesSystem = newProductComprisesSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.PRODUCT__PRODUCT_COMPRISES_SYSTEM, oldProductComprisesSystem, productComprisesSystem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getProductHasFeature() {
		if (productHasFeature == null) {
			productHasFeature = new EObjectResolvingEList<Feature>(Feature.class, this, VbpodatamodelPackage.PRODUCT__PRODUCT_HAS_FEATURE);
		}
		return productHasFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getUnitCost() {
		return unitCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnitCost(BigDecimal newUnitCost) {
		BigDecimal oldUnitCost = unitCost;
		unitCost = newUnitCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VbpodatamodelPackage.PRODUCT__UNIT_COST, oldUnitCost, unitCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VbpodatamodelPackage.PRODUCT__PRODUCT_COMPRISES_SYSTEM:
				if (resolve) return getProductComprisesSystem();
				return basicGetProductComprisesSystem();
			case VbpodatamodelPackage.PRODUCT__PRODUCT_HAS_FEATURE:
				return getProductHasFeature();
			case VbpodatamodelPackage.PRODUCT__UNIT_COST:
				return getUnitCost();
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
			case VbpodatamodelPackage.PRODUCT__PRODUCT_COMPRISES_SYSTEM:
				setProductComprisesSystem((de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System)newValue);
				return;
			case VbpodatamodelPackage.PRODUCT__PRODUCT_HAS_FEATURE:
				getProductHasFeature().clear();
				getProductHasFeature().addAll((Collection<? extends Feature>)newValue);
				return;
			case VbpodatamodelPackage.PRODUCT__UNIT_COST:
				setUnitCost((BigDecimal)newValue);
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
			case VbpodatamodelPackage.PRODUCT__PRODUCT_COMPRISES_SYSTEM:
				setProductComprisesSystem((de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System)null);
				return;
			case VbpodatamodelPackage.PRODUCT__PRODUCT_HAS_FEATURE:
				getProductHasFeature().clear();
				return;
			case VbpodatamodelPackage.PRODUCT__UNIT_COST:
				setUnitCost(UNIT_COST_EDEFAULT);
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
			case VbpodatamodelPackage.PRODUCT__PRODUCT_COMPRISES_SYSTEM:
				return productComprisesSystem != null;
			case VbpodatamodelPackage.PRODUCT__PRODUCT_HAS_FEATURE:
				return productHasFeature != null && !productHasFeature.isEmpty();
			case VbpodatamodelPackage.PRODUCT__UNIT_COST:
				return UNIT_COST_EDEFAULT == null ? unitCost != null : !UNIT_COST_EDEFAULT.equals(unitCost);
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
		result.append(" (unitCost: ");
		result.append(unitCost);
		result.append(')');
		return result.toString();
	}

} //ProductImpl
