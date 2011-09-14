/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.acape;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.uni_leipzig.iwi.gilbreth.acape.AcapeFactory
 * @model kind="package"
 * @generated
 */
public interface AcapePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "acape";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "de.uni_leipzig.iwi.gilbreth.acape.metamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "acape";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AcapePackage eINSTANCE = de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl.init();

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl <em>ACAPE Data Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getACAPEDataModel()
	 * @generated
	 */
	int ACAPE_DATA_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACAPE_DATA_MODEL__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACAPE_DATA_MODEL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Price Settings</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACAPE_DATA_MODEL__PRICE_SETTINGS = 2;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACAPE_DATA_MODEL__CONSTRAINTS = 3;

	/**
	 * The number of structural features of the '<em>ACAPE Data Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACAPE_DATA_MODEL_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.EntityImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AttributeImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DESCRIPTION = ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Levels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__LEVELS = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.LevelImpl <em>Level</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.LevelImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getLevel()
	 * @generated
	 */
	int LEVEL = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__NAME = ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__DESCRIPTION = ENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__FEATURES = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Level</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__LHS = 1;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__RHS = 2;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.FeatureImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.PriceSettingsImpl <em>Price Settings</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.PriceSettingsImpl
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getPriceSettings()
	 * @generated
	 */
	int PRICE_SETTINGS = 6;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_SETTINGS__LOWER_BOUND = 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_SETTINGS__UPPER_BOUND = 1;

	/**
	 * The number of structural features of the '<em>Price Settings</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_SETTINGS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.uni_leipzig.iwi.gilbreth.acape.ConstraintType <em>Constraint Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ConstraintType
	 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getConstraintType()
	 * @generated
	 */
	int CONSTRAINT_TYPE = 7;


	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel <em>ACAPE Data Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ACAPE Data Model</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel
	 * @generated
	 */
	EClass getACAPEDataModel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getAttributes()
	 * @see #getACAPEDataModel()
	 * @generated
	 */
	EReference getACAPEDataModel_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getName()
	 * @see #getACAPEDataModel()
	 * @generated
	 */
	EAttribute getACAPEDataModel_Name();

	/**
	 * Returns the meta object for the containment reference '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getPriceSettings <em>Price Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Price Settings</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getPriceSettings()
	 * @see #getACAPEDataModel()
	 * @generated
	 */
	EReference getACAPEDataModel_PriceSettings();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel#getConstraints()
	 * @see #getACAPEDataModel()
	 * @generated
	 */
	EReference getACAPEDataModel_Constraints();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.Entity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Entity#getName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.Entity#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Entity#getDescription()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Description();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.acape.Attribute#getLevels <em>Levels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Levels</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Attribute#getLevels()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Levels();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.Level <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Level</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Level
	 * @generated
	 */
	EClass getLevel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.uni_leipzig.iwi.gilbreth.acape.Level#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Level#getFeatures()
	 * @see #getLevel()
	 * @generated
	 */
	EReference getLevel_Features();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Constraint#getType()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Type();

	/**
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getLhs <em>Lhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Lhs</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Constraint#getLhs()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_Lhs();

	/**
	 * Returns the meta object for the reference '{@link de.uni_leipzig.iwi.gilbreth.acape.Constraint#getRhs <em>Rhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rhs</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Constraint#getRhs()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_Rhs();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.Feature#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.Feature#getName()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Name();

	/**
	 * Returns the meta object for class '{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings <em>Price Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Price Settings</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.PriceSettings
	 * @generated
	 */
	EClass getPriceSettings();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getLowerBound()
	 * @see #getPriceSettings()
	 * @generated
	 */
	EAttribute getPriceSettings_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.PriceSettings#getUpperBound()
	 * @see #getPriceSettings()
	 * @generated
	 */
	EAttribute getPriceSettings_UpperBound();

	/**
	 * Returns the meta object for enum '{@link de.uni_leipzig.iwi.gilbreth.acape.ConstraintType <em>Constraint Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Constraint Type</em>'.
	 * @see de.uni_leipzig.iwi.gilbreth.acape.ConstraintType
	 * @generated
	 */
	EEnum getConstraintType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AcapeFactory getAcapeFactory();

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
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl <em>ACAPE Data Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getACAPEDataModel()
		 * @generated
		 */
		EClass ACAPE_DATA_MODEL = eINSTANCE.getACAPEDataModel();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACAPE_DATA_MODEL__ATTRIBUTES = eINSTANCE.getACAPEDataModel_Attributes();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACAPE_DATA_MODEL__NAME = eINSTANCE.getACAPEDataModel_Name();

		/**
		 * The meta object literal for the '<em><b>Price Settings</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACAPE_DATA_MODEL__PRICE_SETTINGS = eINSTANCE.getACAPEDataModel_PriceSettings();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACAPE_DATA_MODEL__CONSTRAINTS = eINSTANCE.getACAPEDataModel_Constraints();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.EntityImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__DESCRIPTION = eINSTANCE.getEntity_Description();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AttributeImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Levels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE__LEVELS = eINSTANCE.getAttribute_Levels();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.LevelImpl <em>Level</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.LevelImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getLevel()
		 * @generated
		 */
		EClass LEVEL = eINSTANCE.getLevel();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LEVEL__FEATURES = eINSTANCE.getLevel_Features();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.ConstraintImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__TYPE = eINSTANCE.getConstraint_Type();

		/**
		 * The meta object literal for the '<em><b>Lhs</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__LHS = eINSTANCE.getConstraint_Lhs();

		/**
		 * The meta object literal for the '<em><b>Rhs</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__RHS = eINSTANCE.getConstraint_Rhs();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.FeatureImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.impl.PriceSettingsImpl <em>Price Settings</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.PriceSettingsImpl
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getPriceSettings()
		 * @generated
		 */
		EClass PRICE_SETTINGS = eINSTANCE.getPriceSettings();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRICE_SETTINGS__LOWER_BOUND = eINSTANCE.getPriceSettings_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRICE_SETTINGS__UPPER_BOUND = eINSTANCE.getPriceSettings_UpperBound();

		/**
		 * The meta object literal for the '{@link de.uni_leipzig.iwi.gilbreth.acape.ConstraintType <em>Constraint Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.uni_leipzig.iwi.gilbreth.acape.ConstraintType
		 * @see de.uni_leipzig.iwi.gilbreth.acape.impl.AcapePackageImpl#getConstraintType()
		 * @generated
		 */
		EEnum CONSTRAINT_TYPE = eINSTANCE.getConstraintType();

	}

} //AcapePackage
