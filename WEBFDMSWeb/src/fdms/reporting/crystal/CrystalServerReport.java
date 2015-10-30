package fdms.reporting.crystal;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axis.AxisEngine;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.soap.SOAPConstants;
import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.model.CrystalReportCustomColDTO;
import com.aldorsolutions.webfdms.util.UtilSingleton;


public class CrystalServerReport {
	
	private Logger logger = Logger.getLogger(CrystalServerReport.class.getName());
	private static String reportTempDir = null;
	private ExportReport exportReport = null;
	long configID;
	
	public CrystalServerReport(long configID) { 
		exportReport = new ExportReport();
		this.configID = configID;
		
		if (reportTempDir == null) {
			try {
					reportTempDir = UtilSingleton.getInstance().getProperty(configID, "ApplicationDirectory") 
						+ UtilSingleton.getInstance().getProperty(configID, "ReportTempDirectory")
						+ File.separator;
			} catch (Exception e) {
				logger.error("Exception in instantiating CrystalServerReport : ", e);
			}
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param formID
	 * @param fromDate
	 * @param toDate
	 * @param customValues
	 * @param location
	 * @return
	 */
	public String printReport(
    		DbUserSession user, 
    		int formID,
			String fromDate, 
			String toDate, 
			ArrayList customValues,
			String location,
			String recId) {
		
		DbFormsAvailable form;
		DatabaseTransaction t = null;

		// Get form data object fromm FormsAvailable table
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			form = FdmsDb.getInstance().getFormsAvailable(t, formID);
			if (form == null) {
				logger.error("ExportReport: Invalid Form ID for user  : "
						+ user.getUserName());
				return "error";
			}
		} catch (PersistenceException e) {
			logger.error("ExportReport PersistenceException:", e);
			return "error";
		} finally {
			if ( t != null ) {
            	t.closeConnection();
            	t = null;
            }
		}		
		
		// file name for generated report
		String finalFormName = exportReport.makeFormName(form.getReportName(), user);
		logger.debug("finalFormName : " + finalFormName);
		
		String exportFormat = form.getExportType();
		logger.debug("Export type : " + form.getExportType());				
		
		String fileName = getDestinationFileName(finalFormName, Integer.toString(user.getId()), exportFormat);
		logger.debug("fileName : " + fileName);
		
		fileName = getFileURL(
				user,
				formID,
				fromDate,
				toDate,
				recId,
				customValues,
				location,
				fileName,
				form);
		
		return fileName;		
	}

    /**
     * 
     * @param formName
     * @param userCode
     * @param exportFormat
     * @return
     */
    public String getDestinationFileName(String formName, String userCode, String exportFormat) {
        StringBuffer nameBuff;

        if (exportFormat.equalsIgnoreCase("WORD")){
        	exportFormat = "doc";
        } else if (exportFormat.equals("default")) {
        	exportFormat = "pdf";
        }
        
        int pos = formName.lastIndexOf(".");
        if (pos < 0) pos = 0;
        
        nameBuff = new StringBuffer();
        //nameBuff.append("/");
        nameBuff.append(formName.substring(0,pos));
        // append userID
        nameBuff.append(".");
        nameBuff.append(userCode);
        // append date/time stamp to make totaly unique name
        nameBuff.append(".");
        java.text.SimpleDateFormat timestamp = new java.text.SimpleDateFormat("MMddhhmmss");
        timestamp.setLenient(false);
        nameBuff.append(timestamp.format(new java.util.Date()));
        // append extension (doc, pdf, html, etc.)
        nameBuff.append(".");
        nameBuff.append(exportFormat.toLowerCase());

        return nameBuff.toString();
    }    
    
    /**
     * 
     * @param user
     * @param form
     * @param fromDate
     * @param toDate
     * @param recordId
     * @param additionalValues
     * @return
     */
    private String makeSelectionFormula(
    		DbUserSession user, 
    		DbFormsAvailable form, 
    		String fromDate, 
    		String toDate,
    		String recordId,
    		ArrayList additionalValues) {
        String formula = form.getSelectionFormula();
        if (formula.compareTo(" ")<=0){
            return " "+formula;
        }
        int subpos;
        StringBuffer formbuff;
        // Replace VITALS ID
        if (0<(subpos= formula.indexOf("%vitalid%")) ){
            formbuff = new StringBuffer();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( Integer.toString(user.getCurrentCaseID()));
            if (formula.length()> subpos+8){
                formbuff.append( formula.substring(subpos+9) );
            }
            formula = formbuff.toString();
        }
        // Replace Locale
        if (0<(subpos= formula.indexOf("%locale%")) ){
            formbuff = new StringBuffer();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( Integer.toString(user.getRegion()));
            if (formula.length()> subpos+7){
                formbuff.append( formula.substring(subpos+8) );
            }
            formula = formbuff.toString();
        }
        // Replace From Date
        if (0<(subpos= formula.indexOf("%fromdate%")) ){
            formbuff = new StringBuffer();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( fromDate );
            if (formula.length()> subpos+9){
                formbuff.append( formula.substring(subpos+10) );
            }
            formula = formbuff.toString();
        }
        // Replace to Date
        if (0<(subpos= formula.indexOf("%todate%")) ){
            formbuff = new StringBuffer();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( toDate );
            if (formula.length()> subpos+7){
                formbuff.append( formula.substring(subpos+8) );
            }
            formula = formbuff.toString();
        }
        // Replace RecordID
        if (0<(subpos= formula.indexOf("%recid%")) ){
            formbuff = new StringBuffer();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append(recordId);
            if (formula.length()> subpos+6){
                formbuff.append( formula.substring(subpos+7) );
            }
            formula = formbuff.toString();
        }
        
        if ((additionalValues != null) && (additionalValues.size() > 0)) {
        	Iterator it = additionalValues.iterator();
        	
        	while (it.hasNext()) {
        		CrystalReportCustomColDTO crCustomColDTO = (CrystalReportCustomColDTO) it.next();
        		
        		if ((crCustomColDTO.getColValue() != null) 
        				&& (!"ALL".equalsIgnoreCase(crCustomColDTO.getColValue()))) {

	                // Replace Custom Value
	                if (0<(subpos= formula.indexOf("%" + crCustomColDTO.getFormulaValue() + "%")) ){
	                    formbuff = new StringBuffer();
	                    formbuff.append(formula.substring(0,subpos));
	                    formbuff.append(crCustomColDTO.getColValue());
	                    if (formula.length()> subpos+(crCustomColDTO.getFormulaValue().length() + 1)){
	                        formbuff.append( 
	                        		formula.substring(subpos+(crCustomColDTO.getFormulaValue().length() + 2)));
	                    }
	                    formula = formbuff.toString();
	                }    
        		} else formula = "";
        		
        	}
        }
        
        logger.error("Formula : " + formula);

        return formula;
    }    
    
    /**
     * 
     * @param user
     * @param formID
     * @param fromDate
     * @param toDate
     * @param recId
     * @param customValues
     * @param location
     * @param fileName
     * @param form
     * @return
     */
    private String getFileURL(
    		DbUserSession user, 
			int formID,
			String fromDate, 
			String toDate, 
			String recId,
			ArrayList customValues,
			String location,
			String fileName,
			DbFormsAvailable form) {
    	
    	String fileURL = "";
    	
    	try {
	
			String formula = makeSelectionFormula(
					user, 
					form, 
					fromDate, 
					toDate,
					recId,
					customValues);
					
			String CRYSTAL_SERVER_DSN = UtilSingleton.getInstance().getProperty(configID, "CrystalServer.dsn");
			//String CRYSTAL_SERVER_IP = 	UtilSingleton.getInstance().getProperty(configID, "CrystalServer.IP");
			String CRYSTAL_REPORTGENERATOR_IP = 	UtilSingleton.getInstance().getProperty(configID,"CrystalServer.ReportGenerator.IP");
			String CRYSTAL_REPORTSERVER_IP = 	UtilSingleton.getInstance().getProperty(configID,"CrystalServer.ReportServer.IP");
			String CONFIG_CODE_CR_WEBSERVICE_NAMESPACE = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.WebService.NameSpace");
			
			//String BODY_NAMESPACE_VALUE = "http://" + CRYSTAL_SERVER_IP + "/WebfdmsReporting/";
			String BODY_NAMESPACE_VALUE = "http://" + CONFIG_CODE_CR_WEBSERVICE_NAMESPACE + "/WebfdmsReporting/";
			String BASE_FILENAME_SERVER_VALUE = "http://" + CRYSTAL_REPORTSERVER_IP + "/WebfdmsReporting/";
			
			String SERVICE_METHOD = "GenerateReport";
		    String NS_XSD = "http://www.w3.org/2001/XMLSchema";			
		
			//String endpoint = "http://" + CRYSTAL_SERVER_IP + "/WebfdmsReporting/ReportService.asmx";
			String endpoint = "http://" + CRYSTAL_REPORTGENERATOR_IP + "/WebfdmsReporting/ReportService.asmx";
			
			Service service = new Service();
			service.setCacheWSDL(true);
			Call call = (Call) service.createCall();	
		    call.setUseSOAPAction(true);
		    call.setSOAPActionURI(BODY_NAMESPACE_VALUE + SERVICE_METHOD);
		    call.setEncodingStyle(null);
		    call.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
		    call.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		    call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
		    call.setOperationName(new QName(BODY_NAMESPACE_VALUE, SERVICE_METHOD));
				
		    call.setTargetEndpointAddress(new URL(endpoint));	
		    
		    OperationDesc operation = new OperationDesc();
		    operation.setName(SERVICE_METHOD);
		    int i = 0;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "location"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "locale"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "rptFile"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "fileName"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "db"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "dsn"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);		  
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "selFormula"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "fromDate"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "toDate"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "marginL"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "marginR"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "marginT"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "marginR"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;

		    operation.setReturnType(new QName(NS_XSD, "string"));
		    operation.setReturnClass(java.lang.String.class);
		    operation.setReturnQName(new QName(BODY_NAMESPACE_VALUE, "CreateStringResult"));
		    operation.setStyle(Style.WRAPPED);
		    operation.setUse(Use.LITERAL);  
		    
		    call.setOperation(operation);
		    			
			String[] params = new String[i];
			i = 0;
			params[i++] = location;
			params[i++] = Integer.toString(user.getRegion());
			params[i++] = form.getReportName();
			params[i++] = fileName;
			params[i++] = exportReport.getDataSource(user);
			params[i++] = CRYSTAL_SERVER_DSN;
			params[i++] = formula;
			params[i++] = fromDate;
			params[i++] = toDate;
			params[i++] = Integer.toString(form.getMarginLeft());
			params[i++] = Integer.toString(form.getMarginRight());
			params[i++] = Integer.toString(form.getMarginTop());
			params[i++] = Integer.toString(form.getMarginBottom());
				
			fileURL = (String) call.invoke((Object[])params);						
			
			if ((fileURL != null) && (!"error".equals(fileURL))) 
				//fileURL = BODY_NAMESPACE_VALUE + fileURL;
				fileURL = BASE_FILENAME_SERVER_VALUE + fileURL;
			else
				fileURL = UtilSingleton.getInstance().getProperty(configID, "CrystalServer.errorPg");
			
			logger.debug("FileURL : " + fileURL);
			
    	} catch (Exception e) {
    		logger.error("Exception in getFileURL() : ", e);
    	}
			
		return fileURL;
    }

}
