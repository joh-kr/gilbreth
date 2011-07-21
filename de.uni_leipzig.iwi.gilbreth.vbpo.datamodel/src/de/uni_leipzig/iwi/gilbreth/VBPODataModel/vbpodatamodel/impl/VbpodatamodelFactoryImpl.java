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
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelFactory;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelPackage;
import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VbpodatamodelFactoryImpl extends EFactoryImpl implements VbpodatamodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VbpodatamodelFactory init() {
		try {
			VbpodatamodelFactory theVbpodatamodelFactory = (VbpodatamodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://vbpodatamodel/1.0"); 
			if (theVbpodatamodelFactory != null) {
				return theVbpodatamodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new VbpodatamodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VbpodatamodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case VbpodatamodelPackage.PRODUCT: return createProduct();
			case VbpodatamodelPackage.FEATURE: return createFeature();
			case VbpodatamodelPackage.ASSET: return createAsset();
			case VbpodatamodelPackage.FIRM: return createFirm();
			case VbpodatamodelPackage.COMPETITOR: return createCompetitor();
			case VbpodatamodelPackage.CUSTOMER_SEGMENT: return createCustomerSegment();
			case VbpodatamodelPackage.PRICE: return createPrice();
			case VbpodatamodelPackage.SPL: return createSPL();
			case VbpodatamodelPackage.VBPO_DATA_MODEL: return createVBPODataModel();
			case VbpodatamodelPackage.SSF: return createSSF();
			case VbpodatamodelPackage.SYSTEM: return createSystem();
			case VbpodatamodelPackage.COMPETITION: return createCompetition();
			case VbpodatamodelPackage.CUSTOMERS: return createCustomers();
			case VbpodatamodelPackage.WTP: return createWTP();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature createFeature() {
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Asset createAsset() {
		AssetImpl asset = new AssetImpl();
		return asset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Firm createFirm() {
		FirmImpl firm = new FirmImpl();
		return firm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Competitor createCompetitor() {
		CompetitorImpl competitor = new CompetitorImpl();
		return competitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomerSegment createCustomerSegment() {
		CustomerSegmentImpl customerSegment = new CustomerSegmentImpl();
		return customerSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Price createPrice() {
		PriceImpl price = new PriceImpl();
		return price;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPL createSPL() {
		SPLImpl spl = new SPLImpl();
		return spl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VBPODataModel createVBPODataModel() {
		VBPODataModelImpl vbpoDataModel = new VBPODataModelImpl();
		return vbpoDataModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SSF createSSF() {
		SSFImpl ssf = new SSFImpl();
		return ssf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System createSystem() {
		SystemImpl system = new SystemImpl();
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Competition createCompetition() {
		CompetitionImpl competition = new CompetitionImpl();
		return competition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customers createCustomers() {
		CustomersImpl customers = new CustomersImpl();
		return customers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WTP createWTP() {
		WTPImpl wtp = new WTPImpl();
		return wtp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VbpodatamodelPackage getVbpodatamodelPackage() {
		return (VbpodatamodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static VbpodatamodelPackage getPackage() {
		return VbpodatamodelPackage.eINSTANCE;
	}

} //VbpodatamodelFactoryImpl
