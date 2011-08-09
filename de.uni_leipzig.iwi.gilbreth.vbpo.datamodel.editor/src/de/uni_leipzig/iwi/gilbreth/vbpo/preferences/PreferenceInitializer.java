package de.uni_leipzig.iwi.gilbreth.vbpo.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor.presentation.VBPODataModelEditorPlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = VBPODataModelEditorPlugin.getPlugin().getPreferenceStore();
		
		store.setDefault(PreferenceConstants.P_MAX_ITERATIONS, 1000000);
		store.setDefault(PreferenceConstants.P_CHANGE_ITERATIONS, 10000);
		store.setDefault(PreferenceConstants.P_ALPHA, 0.995d);
		store.setDefault(PreferenceConstants.P_DELTA, 0.01d);
		store.setDefault(PreferenceConstants.P_INITIAL_TEMPERATURE, 4000);
		store.setDefault(PreferenceConstants.P_FINAL_TEMPERATURE, 1);

	}

}
