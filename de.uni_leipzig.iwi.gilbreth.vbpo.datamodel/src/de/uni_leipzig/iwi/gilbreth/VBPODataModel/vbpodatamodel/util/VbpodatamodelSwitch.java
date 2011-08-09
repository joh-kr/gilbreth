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
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.util;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage
 * @generated
 */
public class VbpodatamodelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static VbpodatamodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VbpodatamodelSwitch() {
		if (modelPackage == null) {
			modelPackage = VbpodatamodelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case VbpodatamodelPackage.PRODUCT: {
				Product product = (Product)theEObject;
				T result = caseProduct(product);
				if (result == null) result = caseIdentifiableEntity(product);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.FEATURE: {
				Feature feature = (Feature)theEObject;
				T result = caseFeature(feature);
				if (result == null) result = caseIdentifiableEntity(feature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.ASSET: {
				Asset asset = (Asset)theEObject;
				T result = caseAsset(asset);
				if (result == null) result = caseIdentifiableEntity(asset);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.FIRM: {
				Firm firm = (Firm)theEObject;
				T result = caseFirm(firm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.COMPETITOR: {
				Competitor competitor = (Competitor)theEObject;
				T result = caseCompetitor(competitor);
				if (result == null) result = caseIdentifiableEntity(competitor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.CUSTOMER_SEGMENT: {
				CustomerSegment customerSegment = (CustomerSegment)theEObject;
				T result = caseCustomerSegment(customerSegment);
				if (result == null) result = caseIdentifiableEntity(customerSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.PRICE: {
				Price price = (Price)theEObject;
				T result = casePrice(price);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.SPL: {
				SPL spl = (SPL)theEObject;
				T result = caseSPL(spl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.VBPO_DATA_MODEL: {
				VBPODataModel vbpoDataModel = (VBPODataModel)theEObject;
				T result = caseVBPODataModel(vbpoDataModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.SSF: {
				SSF ssf = (SSF)theEObject;
				T result = caseSSF(ssf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.SYSTEM: {
				de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System system = (de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System)theEObject;
				T result = caseSystem(system);
				if (result == null) result = caseIdentifiableEntity(system);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.IDENTIFIABLE_ENTITY: {
				IdentifiableEntity identifiableEntity = (IdentifiableEntity)theEObject;
				T result = caseIdentifiableEntity(identifiableEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.COMPETITION: {
				Competition competition = (Competition)theEObject;
				T result = caseCompetition(competition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.CUSTOMERS: {
				Customers customers = (Customers)theEObject;
				T result = caseCustomers(customers);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VbpodatamodelPackage.WTP: {
				WTP wtp = (WTP)theEObject;
				T result = caseWTP(wtp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduct(Product object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeature(Feature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Asset</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Asset</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsset(Asset object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Firm</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Firm</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFirm(Firm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Competitor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Competitor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompetitor(Competitor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Customer Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Customer Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomerSegment(CustomerSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Price</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Price</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrice(Price object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SPL</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SPL</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSPL(SPL object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>VBPO Data Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>VBPO Data Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVBPODataModel(VBPODataModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SSF</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SSF</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSSF(SSF object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystem(de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifiable Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifiable Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifiableEntity(IdentifiableEntity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Competition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Competition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompetition(Competition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Customers</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Customers</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomers(Customers object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>WTP</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>WTP</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWTP(WTP object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //VbpodatamodelSwitch
