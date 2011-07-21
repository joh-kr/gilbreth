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

import org.eclipse.jface.viewers.Viewer;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * A content provider for the JasperReport viewer. Is not related to the eclipse API.
 * 
 * @author Johannes MŸller
 * @version 0.1
 *
 */
public class ReportContentProvider {

	JasperPrint myJPrint;
	Viewer viewer;

	public JasperPrint getContent() {
		return myJPrint;
	}

	/**
	 * sets new content. Forces the view to refresh and present the new
	 * content.
	 * @param content
	 */
	public void setContent(JasperPrint content) {
		this.myJPrint = content;
		if(viewer != null) viewer.refresh();
	}
	
	/**
	 * registers the one listener to the content. I.e. the JasperReportView.
	 * @param viewer
	 */
	public void registerListener(Viewer viewer){
		this.viewer = viewer;
	}
	
	/**
	 * removes the listener
	 */
	public void deregisterListener(){
		viewer = null;
	}
	
}
/*EOF*/