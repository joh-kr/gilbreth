package de.uni_leipzig.iwi.gilbreth.acape.generator;

import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

public class WorkflowLogger extends AbstractWorkflowComponent{

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void checkConfiguration(Issues issues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		System.out.println("Log: " + message);
		
	}

	
}
