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

package de.uni_leipzig.iwi.gilbreth.vbpo.result.presentation;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

import com.jasperassistant.designer.viewer.ViewerComposite;

import de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor.presentation.VBPODataModelEditorPlugin;

/**
 * A wrapper for the SWTJasperViewer component. Is mainly responsible to wire
 * the ReportContentProvider with the SWTJAsperViewer
 * 
 * @author jo
 *
 */
public class ReportViewer extends Viewer {

	/**
	 * Component to view JasperReport prints.
	 */
	ViewerComposite viewerComposite;
	
	/**
	 * Underlying data model.
	 */
	ReportContentProvider content;

	/**
	 * constructs a ReportViewer and registers the ReportViewer as 
	 * listener at the given ReportContentProvider.
	 * 
	 * @param composite the parent view component that is to be including the JasperReportView
	 * @param content the ReportContentProvider
	 */
	public ReportViewer(ViewerComposite composite, ReportContentProvider content) {
		this.viewerComposite = composite;
		this.content = content;
		this.content.registerListener(this);

		refresh();
	}

	@Override
	public Control getControl() {
		return viewerComposite;
	}

	@Override
	public Object getInput() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Refreshs the view asynchrony.
	 */
	@Override
	public void refresh() {

		// Guard
		if (content == null || content.getContent() == null)
			return;

		if (!content.getContent().getPages().isEmpty()) {

			// http://www.tutorials.de/java/208390-invalid-thread-access.html
			Runnable r = new Runnable() {
				public void run() {
					viewerComposite.getReportViewer().setDocument(
							content.getContent());
					viewerComposite.pack();
					viewerComposite.setEnabled(true);
				}
			};

			if (!viewerComposite.isDisposed()) {
				viewerComposite.getDisplay().asyncExec(r);
			} else {
				// TODO
			}

		} else {
			VBPODataModelEditorPlugin.INSTANCE.log("report has no pages!");
		}

	}

	@Override
	public void setInput(Object input) {
		if (input instanceof ReportContentProvider)
			content = (ReportContentProvider) input;
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		// TODO Auto-generated method stub

	}

}
/*EOF*/
