/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.acape.impl;

import de.uni_leipzig.iwi.gilbreth.acape.ACAPEDataModel;
import de.uni_leipzig.iwi.gilbreth.acape.AcapePackage;
import de.uni_leipzig.iwi.gilbreth.acape.Attribute;

import de.uni_leipzig.iwi.gilbreth.acape.Constraint;
import de.uni_leipzig.iwi.gilbreth.acape.PriceSettings;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ACAPE Data Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl#getPriceSettings <em>Price Settings</em>}</li>
 *   <li>{@link de.uni_leipzig.iwi.gilbreth.acape.impl.ACAPEDataModelImpl#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ACAPEDataModelImpl extends EObjectImpl implements ACAPEDataModel {
	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attributes;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPriceSettings() <em>Price Settings</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriceSettings()
	 * @generated
	 * @ordered
	 */
	protected PriceSettings priceSettings;

	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> constraints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ACAPEDataModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AcapePackage.Literals.ACAPE_DATA_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, AcapePackage.ACAPE_DATA_MODEL__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AcapePackage.ACAPE_DATA_MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriceSettings getPriceSettings() {
		return priceSettings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPriceSettings(PriceSettings newPriceSettings, NotificationChain msgs) {
		PriceSettings oldPriceSettings = priceSettings;
		priceSettings = newPriceSettings;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS, oldPriceSettings, newPriceSettings);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriceSettings(PriceSettings newPriceSettings) {
		if (newPriceSettings != priceSettings) {
			NotificationChain msgs = null;
			if (priceSettings != null)
				msgs = ((InternalEObject)priceSettings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS, null, msgs);
			if (newPriceSettings != null)
				msgs = ((InternalEObject)newPriceSettings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS, null, msgs);
			msgs = basicSetPriceSettings(newPriceSettings, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS, newPriceSettings, newPriceSettings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Constraint> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, AcapePackage.ACAPE_DATA_MODEL__CONSTRAINTS);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AcapePackage.ACAPE_DATA_MODEL__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS:
				return basicSetPriceSettings(null, msgs);
			case AcapePackage.ACAPE_DATA_MODEL__CONSTRAINTS:
				return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
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
			case AcapePackage.ACAPE_DATA_MODEL__ATTRIBUTES:
				return getAttributes();
			case AcapePackage.ACAPE_DATA_MODEL__NAME:
				return getName();
			case AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS:
				return getPriceSettings();
			case AcapePackage.ACAPE_DATA_MODEL__CONSTRAINTS:
				return getConstraints();
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
			case AcapePackage.ACAPE_DATA_MODEL__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
				return;
			case AcapePackage.ACAPE_DATA_MODEL__NAME:
				setName((String)newValue);
				return;
			case AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS:
				setPriceSettings((PriceSettings)newValue);
				return;
			case AcapePackage.ACAPE_DATA_MODEL__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection<? extends Constraint>)newValue);
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
			case AcapePackage.ACAPE_DATA_MODEL__ATTRIBUTES:
				getAttributes().clear();
				return;
			case AcapePackage.ACAPE_DATA_MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS:
				setPriceSettings((PriceSettings)null);
				return;
			case AcapePackage.ACAPE_DATA_MODEL__CONSTRAINTS:
				getConstraints().clear();
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
			case AcapePackage.ACAPE_DATA_MODEL__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case AcapePackage.ACAPE_DATA_MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AcapePackage.ACAPE_DATA_MODEL__PRICE_SETTINGS:
				return priceSettings != null;
			case AcapePackage.ACAPE_DATA_MODEL__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ACAPEDataModelImpl
