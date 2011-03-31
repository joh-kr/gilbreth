package de.uni_leipzig.iwi.gilbreth.acape.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.DirectoryCleaner;

import acape.AcapeFactory;
import acape.Attribute;
import acape.impl.AcapePackageImpl;

public class FeatureModelToACAPEComponent extends AbstractWorkflowComponent {

	private static final Log LOG = LogFactory.getLog(DirectoryCleaner.class);
	
	private String outputModelSlot;

	private String inputModelSlot;

	private String featureModelPath;

	// the resulting model
	private acape.ACAPEDataModel aCAPEDataModel;

	private FaMaFeatureModelHandling famaHandling;
	
	private EmfFeatureModelHandling emfHandling;

	public String getOutputModelSlot() {
		return outputModelSlot;
	}

	// ---- Getter/Setter section
	public String getInputModelSlot() {
		return inputModelSlot;
	}

	public void setInputModelSlot(String inputModelSlot) {
		this.inputModelSlot = inputModelSlot;
	}
	
	public void setOutputModelSlot(String outputModelSlot) {
		this.outputModelSlot = outputModelSlot;
	}

	public String getFeatureModelPath() {
		return featureModelPath;
	}

	public void setFeatureModelPath(String featureModelPath) {
		this.featureModelPath = featureModelPath;
	}

	// ---- Workflow management
	@Override
	public void checkConfiguration(Issues issues) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {

		LOG.info("Begin transforming Feature Model to ACAPE Survey ... ");
		// Create the Handler for FaMa and EMF
		famaHandling = new FaMaFeatureModelHandling(featureModelPath);
		// pass the dynamically with emf loaded fama feature model from the 
		// workflow context to the member field
		emfHandling = new EmfFeatureModelHandling((EObject) ctx.get(inputModelSlot));
		
		
		instanciateACAPEModel();
		ctx.set(outputModelSlot, aCAPEDataModel);
		
		LOG.info("Feature Model to ACAPE Survey finished ");
	}
	
	
	
	// ---- ACAPE Model Handling
	private void instanciateACAPEModel(){
		AcapePackageImpl.init();
		AcapeFactory factory = AcapeFactory.eINSTANCE;
		aCAPEDataModel = factory.createACAPEDataModel();
		aCAPEDataModel.setName("MyModel");
		
		List<String> attributeNamesvarIFSelection = famaHandling.selectAttributeFeatures();
		List<String> attributeFeatureNames = new ArrayList<String>();
		
		for(String name : attributeNamesvarIFSelection){
			if(!emfHandling.isTerminal(name) ||
				(emfHandling.isTerminal(name) && !emfHandling.isGrouped(name) && emfHandling.isOptional(name))){
				attributeFeatureNames.add(name);
			}
		}
		
		for(String name : attributeFeatureNames){
			System.out.println("Attribute: " + name);
			createAttribute(aCAPEDataModel, factory, name);
		}
		
		
		
	}
	

	
	private void createAttribute(acape.ACAPEDataModel rootModel,AcapeFactory factory, String featureName){
		Attribute attribute = factory.createAttribute();
		attribute.setName(featureName);
		attribute.setDescription("Desc");
		createLevel(attribute, factory, "1", "1");
			
		rootModel.getAttributes().add(attribute);
	}
	
	private void createLevel(acape.Attribute attribute, AcapeFactory factory, String name, String description){
		/*
		Level l = factory.createLevel();
		l.setDescription(description);
		l.setName(name);
		*/
		List<acape.Level> levels = emfHandling.createLevelsFor(factory, attribute.getName());
		for(acape.Level l : levels){
			System.out.println(l.getName());
			attribute.getLevels().add(l);
		}
		
		
	}
	
}
