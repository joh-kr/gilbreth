package de.uni_leipzig.iwi.gilbreth.vbpo.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor.presentation.VBPODataModelEditorPlugin;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class GilbrethPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {
	
	private StringFieldEditor alpha;
	
	private StringFieldEditor delta;

	public GilbrethPreferencePage() {
		super(GRID);
		setPreferenceStore(VBPODataModelEditorPlugin.INSTANCE.getPlugin().getPreferenceStore());
		setDescription("Configuration of the simulated annealing algorithm.");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {

		addField(new IntegerFieldEditor(
				PreferenceConstants.P_MAX_ITERATIONS, 
				"&Maximal Iterations:", 
				getFieldEditorParent()));
		
		addField(new IntegerFieldEditor(
				PreferenceConstants.P_CHANGE_ITERATIONS, 
				"&Iterations within changes must occur:", 
				getFieldEditorParent()));
		
		alpha = new StringFieldEditor(
				PreferenceConstants.P_ALPHA, 
				"&Alpha:", 
				getFieldEditorParent());
		addField(alpha);
		delta = new StringFieldEditor(
				PreferenceConstants.P_DELTA, 
				"&Delta:", 
				getFieldEditorParent());
		addField(delta);
		addField(new IntegerFieldEditor(
				PreferenceConstants.P_INITIAL_TEMPERATURE, 
				"&Initial Temperature:", 
				getFieldEditorParent()));
		addField(new IntegerFieldEditor(
				PreferenceConstants.P_FINAL_TEMPERATURE, 
				"&Final Temperature:", 
				getFieldEditorParent()));
		addField(new RadioGroupFieldEditor(
				PreferenceConstants.P_ALGORITHM,
				 "Algorithm",
				 1,
				new String[][] { { "&Plain Simulated Annealing", PreferenceConstants.P_ALGORITHM_PLAIN_SIMAN }, {
				"&Linear Optimized Prices", PreferenceConstants.P_ALGORITHM_LINOP_SIMAN }
				}, getFieldEditorParent()));
	}
	
	protected void checkState() {
        super.checkState();
        if(alpha.getStringValue()!= null && alpha.getStringValue().matches("[0-9]*\\.?[0-9]+")){
                  setErrorMessage(null);
              setValid(true);
        }else{
              setErrorMessage("Alpha has to be a double value!");
              setValid(false);
        }
        if(delta.getStringValue()!= null && delta.getStringValue().matches("[0-9]*\\.?[0-9]+")){
            setErrorMessage(null);
        setValid(true);
  }else{
        setErrorMessage("Delta has to be a double value!");
        setValid(false);
  }
}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
	/*
	 * 		addField(
				new DirectoryFieldEditor(PreferenceConstants.P_PATH, 
				"&Directory preference:", getFieldEditorParent()));
		addField(
				new BooleanFieldEditor(
				PreferenceConstants.P_BOOLEAN,
				"&An example of a boolean preference",
				getFieldEditorParent()));

		addField(
				new RadioGroupFieldEditor(
				PreferenceConstants.P_CHOICE,
			"An example of a multiple-choice preference",
			1,
			new String[][] { { "&Choice 1", "choice1" }, {
				"C&hoice 2", "choice2" }
		}, getFieldEditorParent()));
		addField(
			new StringFieldEditor(PreferenceConstants.P_STRING, "A &text preference:", getFieldEditorParent())
			);
	 * 
	 * */
	
	
}