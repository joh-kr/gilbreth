/**
 * Copyright 2011 Johannes M�ller, University of Leipzig
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

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers;
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
 * An implementation of the model object '<em><b>Customers</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomersImpl#getCustomerSegments <em>Customer Segments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomersImpl extends EObjectImpl implements Customers {
	/**
	 * The cached value of the '{@link #getCustomerSegments() <em>Customer Segments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerSegments()
	 * @generated
	 * @ordered
	 */
	protected EList<CustomerSegment> customerSegments;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomersImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VbpodatamodelPackage.Literals.CUSTOMERS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CustomerSegment> getCustomerSegments() {
		if (customerSegments == null) {
			customerSegments = new EObjectContainmentEList<CustomerSegment>(CustomerSegment.class, this, VbpodatamodelPackage.CUSTOMERS__CUSTOMER_SEGMENTS);
		}
		return customerSegments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VbpodatamodelPackage.CUSTOMERS__CUSTOMER_SEGMENTS:
				return ((InternalEList<?>)getCustomerSegments()).basicRemove(otherEnd, msgs);
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
			case VbpodatamodelPackage.CUSTOMERS__CUSTOMER_SEGMENTS:
				return getCustomerSegments();
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
			case VbpodatamodelPackage.CUSTOMERS__CUSTOMER_SEGMENTS:
				getCustomerSegments().clear();
				getCustomerSegments().addAll((Collection<? extends CustomerSegment>)newValue);
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
			case VbpodatamodelPackage.CUSTOMERS__CUSTOMER_SEGMENTS:
				getCustomerSegments().clear();
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
			case VbpodatamodelPackage.CUSTOMERS__CUSTOMER_SEGMENTS:
				return customerSegments != null && !customerSegments.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CustomersImpl
