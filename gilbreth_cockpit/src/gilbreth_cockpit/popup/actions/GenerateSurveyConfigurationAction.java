package gilbreth_cockpit.popup.actions;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitorAdapter;
import org.eclipse.emf.mwe.core.resources.ResourceLoaderFactory;
import org.eclipse.emf.mwe.ui.workflow.util.ProjectIncludingResourceLoader;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class GenerateSurveyConfigurationAction implements IObjectActionDelegate {

	private Shell shell;
	
	private PrintStream stdOut;
	private PrintStream stdErr;
	
	private ISelection selection;
	
	
	
	/**
	 * Constructor for Action1.
	 */
	public GenerateSurveyConfigurationAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {


		if (selection instanceof IStructuredSelection) {
			
			Object first = ((IStructuredSelection) selection).getFirstElement();
			final IResource resource = (IResource) first;
			final IProject project = resource.getProject();
			try {
				PlatformUI.getWorkbench().getProgressService().run(true, true,
						new IRunnableWithProgress() {
							public void run(IProgressMonitor monitor)
									throws InvocationTargetException,
									InterruptedException {
								try {
									
									runGenerator(resource, project, monitor);
									
								} catch (CoreException e) {
									System.out.println(e.getMessage());
								} catch (IOException e) {
									System.out.println(e.getMessage());
								}
							}
						});
			} catch (InvocationTargetException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

		
		
		
/*
	    // Start WF here
		WorkflowRunner runner = new WorkflowRunner();
		System.out.println(workflowFilePath);
		ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(new Shell());
		IProgressMonitor progressMonitor = progressDialog.getProgressMonitor();
		progressDialog.open();
		runner.run(workflowFilePath, new ProgressMonitorAdapter(progressMonitor), parameters, externalSlotValues);
		progressDialog.close();
		
		MessageDialog.openInformation(
			shell,
			"Gilbreth Cockpit",
			"The Survey Configuration was generated.");
*/
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
	
	private IFile getFile() {
		return (IFile)
		        ((IStructuredSelection)selection)
		            .getFirstElement();
	}
	
	/**
	 * Starts the workflow engine with given parameters in a single thread.
	 * 
	 * @param resource
	 * @param project
	 * @param monitor
	 * @throws CoreException
	 * @throws IOException 
	 */
	public void runGenerator(IResource resource, IProject project,
			IProgressMonitor monitor) throws CoreException, IOException {
		
	    IFile file = getFile();
	    IPath osPath = file.getLocation();
	    
	    
		monitor.beginTask("Generating ACAPE survey.", 100);
		monitor.worked(10);
		monitor.subTask("Loading workflow environment.");
		
		String workflowFilePath = FileLocator.resolve(new URL("platform:/plugin/gilbreth_cockpit/template/workflow.mwe")).getPath();	
		String faMaMetaModelFile = FileLocator.resolve(new URL("platform:/plugin/gilbreth_cockpit/model/feature-model-schema.xsd")).getPath();
		String targetDir =  FileLocator.resolve(new URL(getURI(resource.getParent().getParent()).toString())).getPath() + "/src-gen/";
		String runtimeProject =  resource.getLocationURI().toString();
		//resource.getParent()resource.getParent().getFullPath().toOSString() + "/src-gen";

		System.out.println(osPath.toOSString());
		System.out.println(workflowFilePath);
		System.out.println(faMaMetaModelFile);
		System.out.println(targetDir);
		System.out.println(runtimeProject);
		
		monitor.subTask("Starting Workflow File " + workflowFilePath);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("featureModelPath", osPath.toOSString());
		properties.put("famaMetamodelPath", "platform:/plugin/gilbreth_cockpit/model/feature-model-schema.xsd");
		properties.put("targetDir", targetDir);
		properties.put("runtimeProject", runtimeProject);
		
		Map<String, Object> externalSlotValues = new HashMap<String, Object>();
		
		setConsole();
		//org.apache.log4j.BasicConfigurator.configure();
		
		System.out.println("Console switched.");
		  
		// Run the workflow
		ClassLoader oldcl = Thread.currentThread().getContextClassLoader();

		try {
			ProjectIncludingResourceLoader resourceLoader = new ProjectIncludingResourceLoader(project);
			ResourceLoaderFactory.setCurrentThreadResourceLoader(resourceLoader);
			MyLog.registerToLogFactory();
			
			
			//
			
			
			WorkflowRunner runner = new WorkflowRunner();
			monitor.worked(25);
			
			Thread.currentThread().setContextClassLoader(WorkflowRunner.class.getClassLoader());
			
			System.out.println("Workflow file: " + workflowFilePath);
			System.out.println(
			"Run was successfully: " + 
			runner.run(workflowFilePath, new ProgressMonitorAdapter(monitor), properties, externalSlotValues));
			
			// 
			
			
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		} finally {
			ResourceLoaderFactory.setCurrentThreadResourceLoader(null);
			// Refresh workspace
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
			// reset the classloader/log
			Thread.currentThread().setContextClassLoader(oldcl);
			MyLog.unregisterFromLogFactory();
			
			resetConsole();
		}

		monitor.worked(90);

		monitor.done();
		
	}
	private void setConsole() {				
		boolean consoleAlreadyCreated = false;
		
		stdOut = System.out;
		stdErr = System.err;
		
		// Check if output of WorkflowRunner is already redirected to local
		// console		
		IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager()
				.getConsoles();
		for (IConsole console : consoles) {
			if (console.getName().equals("Generating ACAPE Survey")) {
				consoleAlreadyCreated = true;
				
				ConsolePlugin.getDefault().getConsoleManager().showConsoleView(
						console);
				
				MessageConsole messageConsole = (MessageConsole)console;
				MessageConsoleStream stream = messageConsole.newMessageStream();
				System.setOut(new PrintStream(stream));
				System.setErr(new PrintStream(stream));
				break;
			}
		}		

		if (consoleAlreadyCreated == false) {
			// Create new console
			MessageConsole console = new MessageConsole("Generating ACAPE Survey",
					null);
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(
					new IConsole[] { console });
			ConsolePlugin.getDefault().getConsoleManager().showConsoleView(
					console);

			// Set new print streams
			MessageConsoleStream stream = console.newMessageStream();
			System.setOut(new PrintStream(stream));
			System.setErr(new PrintStream(stream));
		}		
	}
	
	private void resetConsole(){
		System.setOut(stdOut);
		System.setErr(stdErr);
	}
	
	private URI getURI(IResource resource) {
		return URI.createPlatformResourceURI(resource.getFullPath().toString(),
				true);
	}

}
