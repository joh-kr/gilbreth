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
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VbpodatamodelFactory
 * @model kind="package"
 * @generated
 */
public interface VbpodatamodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "vbpodatamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://vbpodatamodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "vbpodatamodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	VbpodatamodelPackage eINSTANCE = de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.IdentifiableEntityImpl <em>Identifiable Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.IdentifiableEntityImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getIdentifiableEntity()
	 * @generated
	 */
	int IDENTIFIABLE_ENTITY = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_ENTITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_ENTITY__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Identifiable Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_ENTITY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = IDENTIFIABLE_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = IDENTIFIABLE_ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Product Comprises System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_COMPRISES_SYSTEM = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Product Has Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_HAS_FEATURE = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Unit Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__UNIT_COST = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FeatureImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = IDENTIFIABLE_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__DESCRIPTION = IDENTIFIABLE_ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Feature Realized By Asset</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_REALIZED_BY_ASSET = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.AssetImpl <em>Asset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.AssetImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getAsset()
	 * @generated
	 */
	int ASSET = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSET__NAME = IDENTIFIABLE_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSET__DESCRIPTION = IDENTIFIABLE_ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Reuse Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSET__REUSE_COST = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Setup Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSET__SETUP_COST = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSET_FEATURE_COUNT = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl <em>Firm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getFirm()
	 * @generated
	 */
	int FIRM = 3;

	/**
	 * The feature id for the '<em><b>Firm Has SPL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIRM__FIRM_HAS_SPL = 0;

	/**
	 * The feature id for the '<em><b>Firm Has SSF</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIRM__FIRM_HAS_SSF = 1;

	/**
	 * The number of structural features of the '<em>Firm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIRM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitorImpl <em>Competitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitorImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCompetitor()
	 * @generated
	 */
	int COMPETITOR = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITOR__NAME = IDENTIFIABLE_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITOR__DESCRIPTION = IDENTIFIABLE_ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Competitor Has Price</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITOR__COMPETITOR_HAS_PRICE = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Competitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITOR_FEATURE_COUNT = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomerSegmentImpl <em>Customer Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomerSegmentImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCustomerSegment()
	 * @generated
	 */
	int CUSTOMER_SEGMENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_SEGMENT__NAME = IDENTIFIABLE_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_SEGMENT__DESCRIPTION = IDENTIFIABLE_ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Segment Has WTP</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_SEGMENT__SEGMENT_HAS_WTP = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_SEGMENT__SIZE = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Customer Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_SEGMENT_FEATURE_COUNT = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.PriceImpl <em>Price</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.PriceImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getPrice()
	 * @generated
	 */
	int PRICE = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Price For Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE__PRICE_FOR_PRODUCT = 1;

	/**
	 * The number of structural features of the '<em>Price</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SPLImpl <em>SPL</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SPLImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getSPL()
	 * @generated
	 */
	int SPL = 7;

	/**
	 * The feature id for the '<em><b>SPL Contains Product</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__SPL_CONTAINS_PRODUCT = 0;

	/**
	 * The feature id for the '<em><b>SPL Comprisesof Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__SPL_COMPRISESOF_FEATURE = 1;

	/**
	 * The number of structural features of the '<em>SPL</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl <em>VBPO Data Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getVBPODataModel()
	 * @generated
	 */
	int VBPO_DATA_MODEL = 8;

	/**
	 * The feature id for the '<em><b>Has AFirm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL__HAS_AFIRM = 0;

	/**
	 * The feature id for the '<em><b>Has Competition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL__HAS_COMPETITION = 1;

	/**
	 * The feature id for the '<em><b>Has Customers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL__HAS_CUSTOMERS = 2;

	/**
	 * The number of structural features of the '<em>VBPO Data Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl <em>SSF</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getSSF()
	 * @generated
	 */
	int SSF = 9;

	/**
	 * The feature id for the '<em><b>SSF Contains System</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SSF__SSF_CONTAINS_SYSTEM = 0;

	/**
	 * The feature id for the '<em><b>SSF Contains Asset</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SSF__SSF_CONTAINS_ASSET = 1;

	/**
	 * The number of structural features of the '<em>SSF</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SSF_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SystemImpl <em>System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SystemImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getSystem()
	 * @generated
	 */
	int SYSTEM = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__NAME = IDENTIFIABLE_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__DESCRIPTION = IDENTIFIABLE_ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Implementation Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__IMPLEMENTATION_COST = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System Uses Asset</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SYSTEM_USES_ASSET = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FEATURE_COUNT = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitionImpl <em>Competition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitionImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCompetition()
	 * @generated
	 */
	int COMPETITION = 12;

	/**
	 * The feature id for the '<em><b>Consists Of</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITION__CONSISTS_OF = 0;

	/**
	 * The number of structural features of the '<em>Competition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomersImpl <em>Customers</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomersImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCustomers()
	 * @generated
	 */
	int CUSTOMERS = 13;

	/**
	 * The feature id for the '<em><b>Customers Consists Of Customer Segments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMERS__CUSTOMERS_CONSISTS_OF_CUSTOMER_SEGMENTS = 0;

	/**
	 * The number of structural features of the '<em>Customers</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMERS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.WTPImpl <em>WTP</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.WTPImpl
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getWTP()
	 * @generated
	 */
	int WTP = 14;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP__VALUE = 0;

	/**
	 * The feature id for the '<em><b>WTP For Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP__WTP_FOR_PRODUCT = 1;

	/**
	 * The number of structural features of the '<em>WTP</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getProductComprisesSystem <em>Product Comprises System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product Comprises System</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getProductComprisesSystem()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductComprisesSystem();

	/**
	 * Returns the meta object for the reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getProductHasFeature <em>Product Has Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Product Has Feature</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getProductHasFeature()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductHasFeature();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getUnitCost <em>Unit Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit Cost</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getUnitCost()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_UnitCost();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature#getFeatureRealizedByAsset <em>Feature Realized By Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Feature Realized By Asset</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature#getFeatureRealizedByAsset()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureRealizedByAsset();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset <em>Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Asset</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset
	 * @generated
	 */
	EClass getAsset();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getReuseCost <em>Reuse Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reuse Cost</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getReuseCost()
	 * @see #getAsset()
	 * @generated
	 */
	EAttribute getAsset_ReuseCost();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getSetupCost <em>Setup Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Setup Cost</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset#getSetupCost()
	 * @see #getAsset()
	 * @generated
	 */
	EAttribute getAsset_SetupCost();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm <em>Firm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Firm</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm
	 * @generated
	 */
	EClass getFirm();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getFirmHasSPL <em>Firm Has SPL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Firm Has SPL</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getFirmHasSPL()
	 * @see #getFirm()
	 * @generated
	 */
	EReference getFirm_FirmHasSPL();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getFirmHasSSF <em>Firm Has SSF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Firm Has SSF</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getFirmHasSSF()
	 * @see #getFirm()
	 * @generated
	 */
	EReference getFirm_FirmHasSSF();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor <em>Competitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Competitor</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor
	 * @generated
	 */
	EClass getCompetitor();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor#getCompetitorHasPrice <em>Competitor Has Price</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Competitor Has Price</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor#getCompetitorHasPrice()
	 * @see #getCompetitor()
	 * @generated
	 */
	EReference getCompetitor_CompetitorHasPrice();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment <em>Customer Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer Segment</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment
	 * @generated
	 */
	EClass getCustomerSegment();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment#getSegmentHasWTP <em>Segment Has WTP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Segment Has WTP</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment#getSegmentHasWTP()
	 * @see #getCustomerSegment()
	 * @generated
	 */
	EReference getCustomerSegment_SegmentHasWTP();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment#getSize()
	 * @see #getCustomerSegment()
	 * @generated
	 */
	EAttribute getCustomerSegment_Size();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price <em>Price</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Price</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price
	 * @generated
	 */
	EClass getPrice();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price#getValue()
	 * @see #getPrice()
	 * @generated
	 */
	EAttribute getPrice_Value();

	/**
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price#getPriceForProduct <em>Price For Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Price For Product</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price#getPriceForProduct()
	 * @see #getPrice()
	 * @generated
	 */
	EReference getPrice_PriceForProduct();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL <em>SPL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SPL</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL
	 * @generated
	 */
	EClass getSPL();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getSPLContainsProduct <em>SPL Contains Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>SPL Contains Product</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getSPLContainsProduct()
	 * @see #getSPL()
	 * @generated
	 */
	EReference getSPL_SPLContainsProduct();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getSPLComprisesofFeature <em>SPL Comprisesof Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>SPL Comprisesof Feature</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getSPLComprisesofFeature()
	 * @see #getSPL()
	 * @generated
	 */
	EReference getSPL_SPLComprisesofFeature();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel <em>VBPO Data Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>VBPO Data Model</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel
	 * @generated
	 */
	EClass getVBPODataModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getHasAFirm <em>Has AFirm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Has AFirm</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getHasAFirm()
	 * @see #getVBPODataModel()
	 * @generated
	 */
	EReference getVBPODataModel_HasAFirm();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getHasCompetition <em>Has Competition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Has Competition</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getHasCompetition()
	 * @see #getVBPODataModel()
	 * @generated
	 */
	EReference getVBPODataModel_HasCompetition();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getHasCustomers <em>Has Customers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Has Customers</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getHasCustomers()
	 * @see #getVBPODataModel()
	 * @generated
	 */
	EReference getVBPODataModel_HasCustomers();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF <em>SSF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SSF</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF
	 * @generated
	 */
	EClass getSSF();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getSSFContainsSystem <em>SSF Contains System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>SSF Contains System</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getSSFContainsSystem()
	 * @see #getSSF()
	 * @generated
	 */
	EReference getSSF_SSFContainsSystem();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getSSFContainsAsset <em>SSF Contains Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>SSF Contains Asset</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getSSFContainsAsset()
	 * @see #getSSF()
	 * @generated
	 */
	EReference getSSF_SSFContainsAsset();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System
	 * @generated
	 */
	EClass getSystem();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System#getImplementationCost <em>Implementation Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Cost</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System#getImplementationCost()
	 * @see #getSystem()
	 * @generated
	 */
	EAttribute getSystem_ImplementationCost();

	/**
	 * Returns the meta object for the reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System#getSystemUsesAsset <em>System Uses Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>System Uses Asset</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System#getSystemUsesAsset()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_SystemUsesAsset();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity <em>Identifiable Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable Entity</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity
	 * @generated
	 */
	EClass getIdentifiableEntity();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getName()
	 * @see #getIdentifiableEntity()
	 * @generated
	 */
	EAttribute getIdentifiableEntity_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.IdentifiableEntity#getDescription()
	 * @see #getIdentifiableEntity()
	 * @generated
	 */
	EAttribute getIdentifiableEntity_Description();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition <em>Competition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Competition</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition
	 * @generated
	 */
	EClass getCompetition();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition#getConsistsOf <em>Consists Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Consists Of</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition#getConsistsOf()
	 * @see #getCompetition()
	 * @generated
	 */
	EReference getCompetition_ConsistsOf();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers <em>Customers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customers</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers
	 * @generated
	 */
	EClass getCustomers();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers#getCustomersConsistsOfCustomerSegments <em>Customers Consists Of Customer Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Customers Consists Of Customer Segments</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers#getCustomersConsistsOfCustomerSegments()
	 * @see #getCustomers()
	 * @generated
	 */
	EReference getCustomers_CustomersConsistsOfCustomerSegments();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP <em>WTP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>WTP</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP
	 * @generated
	 */
	EClass getWTP();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getValue()
	 * @see #getWTP()
	 * @generated
	 */
	EAttribute getWTP_Value();

	/**
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getWTPForProduct <em>WTP For Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>WTP For Product</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getWTPForProduct()
	 * @see #getWTP()
	 * @generated
	 */
	EReference getWTP_WTPForProduct();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	VbpodatamodelFactory getVbpodatamodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.ProductImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '<em><b>Product Comprises System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__PRODUCT_COMPRISES_SYSTEM = eINSTANCE.getProduct_ProductComprisesSystem();

		/**
		 * The meta object literal for the '<em><b>Product Has Feature</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__PRODUCT_HAS_FEATURE = eINSTANCE.getProduct_ProductHasFeature();

		/**
		 * The meta object literal for the '<em><b>Unit Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__UNIT_COST = eINSTANCE.getProduct_UnitCost();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FeatureImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Feature Realized By Asset</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__FEATURE_REALIZED_BY_ASSET = eINSTANCE.getFeature_FeatureRealizedByAsset();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.AssetImpl <em>Asset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.AssetImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getAsset()
		 * @generated
		 */
		EClass ASSET = eINSTANCE.getAsset();

		/**
		 * The meta object literal for the '<em><b>Reuse Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSET__REUSE_COST = eINSTANCE.getAsset_ReuseCost();

		/**
		 * The meta object literal for the '<em><b>Setup Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSET__SETUP_COST = eINSTANCE.getAsset_SetupCost();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl <em>Firm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.FirmImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getFirm()
		 * @generated
		 */
		EClass FIRM = eINSTANCE.getFirm();

		/**
		 * The meta object literal for the '<em><b>Firm Has SPL</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIRM__FIRM_HAS_SPL = eINSTANCE.getFirm_FirmHasSPL();

		/**
		 * The meta object literal for the '<em><b>Firm Has SSF</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIRM__FIRM_HAS_SSF = eINSTANCE.getFirm_FirmHasSSF();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitorImpl <em>Competitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitorImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCompetitor()
		 * @generated
		 */
		EClass COMPETITOR = eINSTANCE.getCompetitor();

		/**
		 * The meta object literal for the '<em><b>Competitor Has Price</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPETITOR__COMPETITOR_HAS_PRICE = eINSTANCE.getCompetitor_CompetitorHasPrice();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomerSegmentImpl <em>Customer Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomerSegmentImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCustomerSegment()
		 * @generated
		 */
		EClass CUSTOMER_SEGMENT = eINSTANCE.getCustomerSegment();

		/**
		 * The meta object literal for the '<em><b>Segment Has WTP</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOMER_SEGMENT__SEGMENT_HAS_WTP = eINSTANCE.getCustomerSegment_SegmentHasWTP();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER_SEGMENT__SIZE = eINSTANCE.getCustomerSegment_Size();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.PriceImpl <em>Price</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.PriceImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getPrice()
		 * @generated
		 */
		EClass PRICE = eINSTANCE.getPrice();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRICE__VALUE = eINSTANCE.getPrice_Value();

		/**
		 * The meta object literal for the '<em><b>Price For Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRICE__PRICE_FOR_PRODUCT = eINSTANCE.getPrice_PriceForProduct();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SPLImpl <em>SPL</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SPLImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getSPL()
		 * @generated
		 */
		EClass SPL = eINSTANCE.getSPL();

		/**
		 * The meta object literal for the '<em><b>SPL Contains Product</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPL__SPL_CONTAINS_PRODUCT = eINSTANCE.getSPL_SPLContainsProduct();

		/**
		 * The meta object literal for the '<em><b>SPL Comprisesof Feature</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPL__SPL_COMPRISESOF_FEATURE = eINSTANCE.getSPL_SPLComprisesofFeature();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl <em>VBPO Data Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VBPODataModelImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getVBPODataModel()
		 * @generated
		 */
		EClass VBPO_DATA_MODEL = eINSTANCE.getVBPODataModel();

		/**
		 * The meta object literal for the '<em><b>Has AFirm</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VBPO_DATA_MODEL__HAS_AFIRM = eINSTANCE.getVBPODataModel_HasAFirm();

		/**
		 * The meta object literal for the '<em><b>Has Competition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VBPO_DATA_MODEL__HAS_COMPETITION = eINSTANCE.getVBPODataModel_HasCompetition();

		/**
		 * The meta object literal for the '<em><b>Has Customers</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VBPO_DATA_MODEL__HAS_CUSTOMERS = eINSTANCE.getVBPODataModel_HasCustomers();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl <em>SSF</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SSFImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getSSF()
		 * @generated
		 */
		EClass SSF = eINSTANCE.getSSF();

		/**
		 * The meta object literal for the '<em><b>SSF Contains System</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SSF__SSF_CONTAINS_SYSTEM = eINSTANCE.getSSF_SSFContainsSystem();

		/**
		 * The meta object literal for the '<em><b>SSF Contains Asset</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SSF__SSF_CONTAINS_ASSET = eINSTANCE.getSSF_SSFContainsAsset();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SystemImpl <em>System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.SystemImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getSystem()
		 * @generated
		 */
		EClass SYSTEM = eINSTANCE.getSystem();

		/**
		 * The meta object literal for the '<em><b>Implementation Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM__IMPLEMENTATION_COST = eINSTANCE.getSystem_ImplementationCost();

		/**
		 * The meta object literal for the '<em><b>System Uses Asset</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__SYSTEM_USES_ASSET = eINSTANCE.getSystem_SystemUsesAsset();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.IdentifiableEntityImpl <em>Identifiable Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.IdentifiableEntityImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getIdentifiableEntity()
		 * @generated
		 */
		EClass IDENTIFIABLE_ENTITY = eINSTANCE.getIdentifiableEntity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE_ENTITY__NAME = eINSTANCE.getIdentifiableEntity_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE_ENTITY__DESCRIPTION = eINSTANCE.getIdentifiableEntity_Description();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitionImpl <em>Competition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CompetitionImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCompetition()
		 * @generated
		 */
		EClass COMPETITION = eINSTANCE.getCompetition();

		/**
		 * The meta object literal for the '<em><b>Consists Of</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPETITION__CONSISTS_OF = eINSTANCE.getCompetition_ConsistsOf();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomersImpl <em>Customers</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.CustomersImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getCustomers()
		 * @generated
		 */
		EClass CUSTOMERS = eINSTANCE.getCustomers();

		/**
		 * The meta object literal for the '<em><b>Customers Consists Of Customer Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOMERS__CUSTOMERS_CONSISTS_OF_CUSTOMER_SEGMENTS = eINSTANCE.getCustomers_CustomersConsistsOfCustomerSegments();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.WTPImpl <em>WTP</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.WTPImpl
		 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.impl.VbpodatamodelPackageImpl#getWTP()
		 * @generated
		 */
		EClass WTP = eINSTANCE.getWTP();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WTP__VALUE = eINSTANCE.getWTP_Value();

		/**
		 * The meta object literal for the '<em><b>WTP For Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WTP__WTP_FOR_PRODUCT = eINSTANCE.getWTP_WTPForProduct();

	}

} //VbpodatamodelPackage
