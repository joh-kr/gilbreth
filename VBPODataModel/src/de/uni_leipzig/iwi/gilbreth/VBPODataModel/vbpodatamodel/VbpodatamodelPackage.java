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
	 * The feature id for the '<em><b>Comprising System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__COMPRISING_SYSTEM = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__FEATURES = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Realizing Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__REALIZING_ASSETS = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>SPL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIRM__SPL = 0;

	/**
	 * The feature id for the '<em><b>SSF</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIRM__SSF = 1;

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
	 * The feature id for the '<em><b>Prices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITOR__PRICES = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>WT Ps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_SEGMENT__WT_PS = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE__PRODUCT = 1;

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
	 * The feature id for the '<em><b>Contained Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__CONTAINED_PRODUCTS = 0;

	/**
	 * The feature id for the '<em><b>Contained Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__CONTAINED_FEATURES = 1;

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
	 * The feature id for the '<em><b>Firm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL__FIRM = 0;

	/**
	 * The feature id for the '<em><b>Competition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL__COMPETITION = 1;

	/**
	 * The feature id for the '<em><b>Customers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VBPO_DATA_MODEL__CUSTOMERS = 2;

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
	 * The feature id for the '<em><b>Contained Systems</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SSF__CONTAINED_SYSTEMS = 0;

	/**
	 * The feature id for the '<em><b>Contained Assets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SSF__CONTAINED_ASSETS = 1;

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
	 * The feature id for the '<em><b>Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ASSETS = IDENTIFIABLE_ENTITY_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Competitors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPETITION__COMPETITORS = 0;

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
	 * The feature id for the '<em><b>Customer Segments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMERS__CUSTOMER_SEGMENTS = 0;

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
	 * The feature id for the '<em><b>Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP__PRICE = 0;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP__PRODUCT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP__NAME = 2;

	/**
	 * The number of structural features of the '<em>WTP</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WTP_FEATURE_COUNT = 3;


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
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getComprisingSystem <em>Comprising System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Comprising System</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getComprisingSystem()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ComprisingSystem();

	/**
	 * Returns the meta object for the reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Features</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product#getFeatures()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Features();

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
	 * Returns the meta object for the reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature#getRealizingAssets <em>Realizing Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realizing Assets</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature#getRealizingAssets()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_RealizingAssets();

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
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getSPL <em>SPL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>SPL</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getSPL()
	 * @see #getFirm()
	 * @generated
	 */
	EReference getFirm_SPL();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getSSF <em>SSF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>SSF</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm#getSSF()
	 * @see #getFirm()
	 * @generated
	 */
	EReference getFirm_SSF();

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
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor#getPrices <em>Prices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Prices</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor#getPrices()
	 * @see #getCompetitor()
	 * @generated
	 */
	EReference getCompetitor_Prices();

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
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment#getWTPs <em>WT Ps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>WT Ps</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment#getWTPs()
	 * @see #getCustomerSegment()
	 * @generated
	 */
	EReference getCustomerSegment_WTPs();

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
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price#getProduct()
	 * @see #getPrice()
	 * @generated
	 */
	EReference getPrice_Product();

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
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getContainedProducts <em>Contained Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Products</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getContainedProducts()
	 * @see #getSPL()
	 * @generated
	 */
	EReference getSPL_ContainedProducts();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getContainedFeatures <em>Contained Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Features</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL#getContainedFeatures()
	 * @see #getSPL()
	 * @generated
	 */
	EReference getSPL_ContainedFeatures();

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
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getFirm <em>Firm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Firm</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getFirm()
	 * @see #getVBPODataModel()
	 * @generated
	 */
	EReference getVBPODataModel_Firm();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getCompetition <em>Competition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Competition</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getCompetition()
	 * @see #getVBPODataModel()
	 * @generated
	 */
	EReference getVBPODataModel_Competition();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getCustomers <em>Customers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Customers</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel#getCustomers()
	 * @see #getVBPODataModel()
	 * @generated
	 */
	EReference getVBPODataModel_Customers();

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
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getContainedSystems <em>Contained Systems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Systems</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getContainedSystems()
	 * @see #getSSF()
	 * @generated
	 */
	EReference getSSF_ContainedSystems();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getContainedAssets <em>Contained Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Assets</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF#getContainedAssets()
	 * @see #getSSF()
	 * @generated
	 */
	EReference getSSF_ContainedAssets();

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
	 * Returns the meta object for the reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System#getAssets <em>Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Assets</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System#getAssets()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_Assets();

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
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition#getCompetitors <em>Competitors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Competitors</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition#getCompetitors()
	 * @see #getCompetition()
	 * @generated
	 */
	EReference getCompetition_Competitors();

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
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers#getCustomerSegments <em>Customer Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Customer Segments</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers#getCustomerSegments()
	 * @see #getCustomers()
	 * @generated
	 */
	EReference getCustomers_CustomerSegments();

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
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getPrice <em>Price</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getPrice()
	 * @see #getWTP()
	 * @generated
	 */
	EAttribute getWTP_Price();

	/**
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getProduct()
	 * @see #getWTP()
	 * @generated
	 */
	EReference getWTP_Product();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP#getName()
	 * @see #getWTP()
	 * @generated
	 */
	EAttribute getWTP_Name();

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
		 * The meta object literal for the '<em><b>Comprising System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__COMPRISING_SYSTEM = eINSTANCE.getProduct_ComprisingSystem();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__FEATURES = eINSTANCE.getProduct_Features();

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
		 * The meta object literal for the '<em><b>Realizing Assets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__REALIZING_ASSETS = eINSTANCE.getFeature_RealizingAssets();

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
		 * The meta object literal for the '<em><b>SPL</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIRM__SPL = eINSTANCE.getFirm_SPL();

		/**
		 * The meta object literal for the '<em><b>SSF</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIRM__SSF = eINSTANCE.getFirm_SSF();

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
		 * The meta object literal for the '<em><b>Prices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPETITOR__PRICES = eINSTANCE.getCompetitor_Prices();

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
		 * The meta object literal for the '<em><b>WT Ps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOMER_SEGMENT__WT_PS = eINSTANCE.getCustomerSegment_WTPs();

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
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRICE__PRODUCT = eINSTANCE.getPrice_Product();

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
		 * The meta object literal for the '<em><b>Contained Products</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPL__CONTAINED_PRODUCTS = eINSTANCE.getSPL_ContainedProducts();

		/**
		 * The meta object literal for the '<em><b>Contained Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPL__CONTAINED_FEATURES = eINSTANCE.getSPL_ContainedFeatures();

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
		 * The meta object literal for the '<em><b>Firm</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VBPO_DATA_MODEL__FIRM = eINSTANCE.getVBPODataModel_Firm();

		/**
		 * The meta object literal for the '<em><b>Competition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VBPO_DATA_MODEL__COMPETITION = eINSTANCE.getVBPODataModel_Competition();

		/**
		 * The meta object literal for the '<em><b>Customers</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VBPO_DATA_MODEL__CUSTOMERS = eINSTANCE.getVBPODataModel_Customers();

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
		 * The meta object literal for the '<em><b>Contained Systems</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SSF__CONTAINED_SYSTEMS = eINSTANCE.getSSF_ContainedSystems();

		/**
		 * The meta object literal for the '<em><b>Contained Assets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SSF__CONTAINED_ASSETS = eINSTANCE.getSSF_ContainedAssets();

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
		 * The meta object literal for the '<em><b>Assets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__ASSETS = eINSTANCE.getSystem_Assets();

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
		 * The meta object literal for the '<em><b>Competitors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPETITION__COMPETITORS = eINSTANCE.getCompetition_Competitors();

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
		 * The meta object literal for the '<em><b>Customer Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOMERS__CUSTOMER_SEGMENTS = eINSTANCE.getCustomers_CustomerSegments();

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
		 * The meta object literal for the '<em><b>Price</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WTP__PRICE = eINSTANCE.getWTP_Price();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WTP__PRODUCT = eINSTANCE.getWTP_Product();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WTP__NAME = eINSTANCE.getWTP_Name();

	}

} //VbpodatamodelPackage
