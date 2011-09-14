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

package de.uni_leipzig.iwi.gilbreth.vbpo.result;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.uni_leipzig.iwi.gilbreth.vbpo.result.beans.ResultBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Is responsible for creating a JasperPrint for a given result. Handles the
 * interaction with the JasperReport engine and selects the templates to 
 * generate the report.
 * 
 * @author Johannes MŸller
 * @version 0.3.1
 */
public class JasperPrintCreator {

	private static final String TEMPLATE_PATH = "plugin/de.uni_leipzig.iwi.gilbreth.vbpo.datamodel.editor/template";
	private static final String SUBREPORT_TEMPLATE_PATH = "template" + File.separator;
	private static final String TEMPLATE_NAME = "optimizationResult.jasper";
	private static final String LOGO_NAME = "gilbreth_logo.jpg";
	
	
	/**
	 * Creates a print for a given resultBean
	 * 
	 * @param resultBean the resultBean describing the result that has to be 
	 * presented in the report.
	 * @return a compiled report in the JasperPrint format
	 */
	public static JasperPrint createPrint(ResultBean resultBean){
		URL url;
		JasperPrint myJPrint = null;
		
		try {
		    url = new URL("platform:/"+TEMPLATE_PATH+"/"+TEMPLATE_NAME);
		    InputStream inputStream = url.openConnection().getInputStream();
		    
		    Collection<ResultBean> result =  new ArrayList<ResultBean>();
		    result.add(resultBean);
			
		    Map<String, Object> parameters = getParameters();
		    
		    JasperReport jasperReport = null;
		    jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
		   
		    
		    myJPrint = JasperFillManager.fillReport(jasperReport, parameters, 
		    		new JRBeanCollectionDataSource(result));
		    
		    //BeanTestDataFactory.createProductSegmentAssignmentsCollection()
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myJPrint;
	}
	
	/**
	 * Creates the Parameters specific for the report. The following parameters are
	 * determined and set:
	 * - SUBREPORT_DIR
	 * 
	 * @return a Map of Parameter key - parameter relations
	 * @throws IOException
	 * @author Johannes MŸller
	 */
	private static Map<String, Object> getParameters() throws IOException{
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("SUBREPORT_DIR", SUBREPORT_TEMPLATE_PATH);
	    parameters.put("IMG_DIR", SUBREPORT_TEMPLATE_PATH);
	    
	    URL logoUrl =  new URL("platform:/"+TEMPLATE_PATH+"/"+LOGO_NAME);
	    parameters.put("LOGO", logoUrl.openConnection().getInputStream());
	    
	    return parameters;
	}
}
/*
 * 0.3.1 changed constant TEMPLATE_PATH to match the new name of the plugin
 */
/*EOF*/
