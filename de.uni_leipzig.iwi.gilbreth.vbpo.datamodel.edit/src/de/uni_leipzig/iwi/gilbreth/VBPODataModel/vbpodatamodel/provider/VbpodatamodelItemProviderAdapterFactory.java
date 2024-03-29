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
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.provider;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.util.VbpodatamodelAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VbpodatamodelItemProviderAdapterFactory extends VbpodatamodelAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VbpodatamodelItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductItemProvider productItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProductAdapter() {
		if (productItemProvider == null) {
			productItemProvider = new ProductItemProvider(this);
		}

		return productItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureItemProvider featureItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Feature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureAdapter() {
		if (featureItemProvider == null) {
			featureItemProvider = new FeatureItemProvider(this);
		}

		return featureItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssetItemProvider assetItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAssetAdapter() {
		if (assetItemProvider == null) {
			assetItemProvider = new AssetItemProvider(this);
		}

		return assetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FirmItemProvider firmItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Firm}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFirmAdapter() {
		if (firmItemProvider == null) {
			firmItemProvider = new FirmItemProvider(this);
		}

		return firmItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompetitorItemProvider competitorItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competitor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompetitorAdapter() {
		if (competitorItemProvider == null) {
			competitorItemProvider = new CompetitorItemProvider(this);
		}

		return competitorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomerSegmentItemProvider customerSegmentItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.CustomerSegment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCustomerSegmentAdapter() {
		if (customerSegmentItemProvider == null) {
			customerSegmentItemProvider = new CustomerSegmentItemProvider(this);
		}

		return customerSegmentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PriceItemProvider priceItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPriceAdapter() {
		if (priceItemProvider == null) {
			priceItemProvider = new PriceItemProvider(this);
		}

		return priceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SPLItemProvider splItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SPL}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSPLAdapter() {
		if (splItemProvider == null) {
			splItemProvider = new SPLItemProvider(this);
		}

		return splItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VBPODataModelItemProvider vbpoDataModelItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createVBPODataModelAdapter() {
		if (vbpoDataModelItemProvider == null) {
			vbpoDataModelItemProvider = new VBPODataModelItemProvider(this);
		}

		return vbpoDataModelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SSFItemProvider ssfItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.SSF}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSSFAdapter() {
		if (ssfItemProvider == null) {
			ssfItemProvider = new SSFItemProvider(this);
		}

		return ssfItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemItemProvider systemItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSystemAdapter() {
		if (systemItemProvider == null) {
			systemItemProvider = new SystemItemProvider(this);
		}

		return systemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompetitionItemProvider competitionItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Competition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompetitionAdapter() {
		if (competitionItemProvider == null) {
			competitionItemProvider = new CompetitionItemProvider(this);
		}

		return competitionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomersItemProvider customersItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Customers}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCustomersAdapter() {
		if (customersItemProvider == null) {
			customersItemProvider = new CustomersItemProvider(this);
		}

		return customersItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WTPItemProvider wtpItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.WTP}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWTPAdapter() {
		if (wtpItemProvider == null) {
			wtpItemProvider = new WTPItemProvider(this);
		}

		return wtpItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (productItemProvider != null) productItemProvider.dispose();
		if (featureItemProvider != null) featureItemProvider.dispose();
		if (assetItemProvider != null) assetItemProvider.dispose();
		if (firmItemProvider != null) firmItemProvider.dispose();
		if (competitorItemProvider != null) competitorItemProvider.dispose();
		if (customerSegmentItemProvider != null) customerSegmentItemProvider.dispose();
		if (priceItemProvider != null) priceItemProvider.dispose();
		if (splItemProvider != null) splItemProvider.dispose();
		if (vbpoDataModelItemProvider != null) vbpoDataModelItemProvider.dispose();
		if (ssfItemProvider != null) ssfItemProvider.dispose();
		if (systemItemProvider != null) systemItemProvider.dispose();
		if (competitionItemProvider != null) competitionItemProvider.dispose();
		if (customersItemProvider != null) customersItemProvider.dispose();
		if (wtpItemProvider != null) wtpItemProvider.dispose();
	}

}
