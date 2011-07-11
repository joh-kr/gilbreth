package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.presentation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.ui.IEditorPart;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.VBPODataModel;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.IterationChangedListener;
import de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing.Solution;
import de.uni_leipzig.iwi.gilbreth.result.beans.JasperPrintCreator;
import de.uni_leipzig.iwi.gilbreth.result.beans.ResultModelBeanBuilder;

public class OptimizeAction extends ValidateAction{
	
	
	private IEditorPart activeEditorPart;
	
	public OptimizeAction(IEditorPart activeEditorPart){
		super.setText(VBPODataModelEditorPlugin.INSTANCE.getString("_UI_StartOptimization_menu_item"));
		this.activeEditorPart = activeEditorPart;
	}
	
	@Override
	public void run() {
		super.run();
		
		// Start Optimization here
		Job job = new Job("Optimize Portfolio") { 

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				
				VbpodatamodelEditor editor = (VbpodatamodelEditor) activeEditorPart;
								
		        // get the document of the master editor
		        EObject root = getRootElement(getActiveEditingPartResource());
				
				// validate Model
				Diagnostic diagnostic = Diagnostician.INSTANCE.validate(root);
				if(diagnostic.getSeverity() != Diagnostic.OK){
					// do not start optimization until model is valid.
					
					return Status.CANCEL_STATUS;
				}
				
		        // Initialize and start optimization
		        OptimizationInitializer optimizer = new OptimizationInitializer();
		        monitor.beginTask("The Portfolio is optimized according to the given information ...", optimizer.getFullWorkUnits()); 
		 

		        
		        optimizer.addIterationChangedListener(new IterationChangedListener(){

					@Override
					public void iterationChanged(int iteration) {
						monitor.worked(iteration);
						VBPODataModelEditorPlugin.INSTANCE.log(iteration);
					}
		        	
		        });
		        
		        optimizer.optimize((VBPODataModel)root);
		        
		        Solution solution = null;
				try {
					solution = optimizer.getSolution();
				} catch (Exception e1) {
					VBPODataModelEditorPlugin.INSTANCE.log(e1.getLocalizedMessage());
				}

		        ResultModelBeanBuilder builder = new ResultModelBeanBuilder((VBPODataModel)root, solution, optimizer.getLookup());
		        
		        
		        
		        editor.getReportContentProvider().setContent(JasperPrintCreator.createPrint(builder.buildResultBean()));
		       
				VBPODataModelEditorPlugin.INSTANCE.log("Optimization is finished succesfully!");
				
				editor.setResultPageActive();

		        monitor.done(); 
		        return Status.OK_STATUS; 
			} 
		}; 
		job.schedule();
		//VBPODataModelEditorPlugin.INSTANCE.log(exception);
	}
	
	private Resource getActiveEditingPartResource(){
        URI resourceURI = EditUIUtil.getURI(activeEditorPart.getEditorInput());
		Resource resource = null;
		
		EditingDomain editingDomain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();
		try {
			// Load the resource through the editing domain.
			//
			resource = editingDomain.getResourceSet().getResource(resourceURI, true);
		}
		catch (Exception e) {
		
			resource = editingDomain.getResourceSet().getResource(resourceURI, false);
		}
		return resource;
	}
	
	private EObject getRootElement(Resource resource){
		return resource.getContents().get(0);
		
	}

}
