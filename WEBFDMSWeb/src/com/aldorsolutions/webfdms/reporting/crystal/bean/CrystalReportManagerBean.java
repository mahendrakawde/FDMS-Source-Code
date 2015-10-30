/**
 * Workfile: CrystalReportManagerBean.java
 * Date: Oct 15, 2005 7:11:01 PM
 * Author: Guadalupe Garcia, CJongs
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */
package com.aldorsolutions.webfdms.reporting.crystal.bean;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.model.CrystalReportCustomColDTO;
import com.aldorsolutions.webfdms.util.MailUtil;
import com.aldorsolutions.webfdms.util.UtilSingleton;

public class CrystalReportManagerBean {
	
	private Logger logger = Logger.getLogger(CrystalReportManagerBean.class.getName());
	
	private static String reportTempDir = null;
	private static boolean debug = true;
	long configID;
	
	public CrystalReportManagerBean(long configID) { 
		this.configID = configID;
		
		if (reportTempDir == null) {
			try {
				reportTempDir = UtilSingleton.getInstance().getProperty(configID,"ApplicationDirectory") 
					+ UtilSingleton.getInstance().getProperty(configID,"ReportTempDirectory")
					+ File.separator;
			} catch (Exception e) {
				logger.debug("Exception in instantiating CrystalServerReport : " + e.getMessage(), e );
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
			String recId, 
			boolean displayURL) {
		
		String fileName = printReport ( user, formID, fromDate, toDate, 
				customValues, location, recId );
		
		if ( displayURL ) {
			fileName = "displayReport.jsp?reportURL=" + fileName; 
		}
		
		return fileName;		
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
	private String printReport(
    		DbUserSession user, 
    		int formID,
			String fromDate, 
			String toDate, 
			ArrayList customValues,
			String location,
			String recId) {
		
		DbFormsAvailable form;
		DatabaseTransaction t = null;

		// Get form data object from FormsAvailable table
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			form = FdmsDb.getInstance().getFormsAvailable(t, formID);
			if (form == null) {
				logger.debug("ExportReport: Invalid Form ID for user  : " + user.getUserName());
				return "error";
			}
		} catch (PersistenceException e) {
			logger.debug("ExportReport PersistenceException:" + e.getMessage(), e);
			return "error";
		} finally {
        	if (t != null) {
        		t.closeConnection();
				t = null;
        	}
        }
		
		// file name for generated report
		String finalFormName = makeFormName(form.getReportName(), user);
		
		if ( debug ) {
			logger.debug("finalFormName : " + finalFormName);
		}
		//store the finalformname back to the reportname since we have some report name set as Name??.rpt.
		form.setReportName(finalFormName);
		
		String exportFormat = form.getExportType();
		if ( debug ) {
			logger.debug("Export type : " + form.getExportType());
		}
		
		String fileName = getDestinationFileName(finalFormName, Integer.toString(user.getId()), exportFormat);
		if ( debug ) {
			logger.debug("fileName : " + fileName);
		}
		
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
        String extension = "";

        if (exportFormat.equalsIgnoreCase("WORD")){
        	extension = "doc";
        } else if (exportFormat.equals("default")) {
        	extension = "pdf";
        } else if (exportFormat.equals("HTML32") || exportFormat.equals("HTML40")) {
        	extension = "htm";
        } else {
        	extension = exportFormat;
        }
        
        int pos = formName.lastIndexOf(".");
        if (pos < 0) pos = 0;
        
        nameBuff = new StringBuffer();
        nameBuff.append(formName.substring(0,pos));
        nameBuff.append(".");
        nameBuff.append(userCode);
        nameBuff.append(".");
        java.text.SimpleDateFormat timestamp = new java.text.SimpleDateFormat("MMddhhmmss");
        timestamp.setLenient(false);
        nameBuff.append(timestamp.format(new java.util.Date()));
        nameBuff.append(".");
        nameBuff.append(extension.toLowerCase());

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
        
        if ( debug ) {
			logger.debug("Formula : " + formula);
		}

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
			
					
			//String RR_CRYSTAL_REPORTGENERATOR_IP = 	UtilSingleton.getInstance().getRRProperty(configID,"RR.CrystalServer.ReportGenerator.IP");
			String CRYSTAL_REPORTGENERATOR_IP = 	UtilSingleton.getInstance().getRRProperty(configID,"RR.CrystalServer.ReportGenerator.IP");
			System.out.println("CRYSTAL_REPORTGENERATOR_IP : "+CRYSTAL_REPORTGENERATOR_IP);
			int RRMaxNumber = UtilSingleton.getInstance().getRRMaxNumber();
			
			
			String CRYSTAL_SERVER_DSN = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.dsn");
			//String CRYSTAL_SERVER_IP = 	UtilSingleton.getInstance().getProperty(configID,"CrystalServer.IP");
			//String CRYSTAL_REPORTGENERATOR_IP = 	UtilSingleton.getInstance().getProperty(configID,"CrystalServer.ReportGenerator.IP");
			String CRYSTAL_REPORTSERVER_IP = 	UtilSingleton.getInstance().getProperty(configID,"CrystalServer.ReportServer.IP");
			System.out.println("CRYSTAL_REPORTSERVER_IP : "+CRYSTAL_REPORTSERVER_IP);
			String CONFIG_CODE_CR_WEBSERVICE_NAMESPACE = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.WebService.NameSpace");
			System.out.println("CONFIG_CODE_CR_WEBSERVICE_NAMESPACE : "+CONFIG_CODE_CR_WEBSERVICE_NAMESPACE);
			String CRYSTAL_SERVERTIMEOUT = 	UtilSingleton.getInstance().getProperty(configID,"CrystalServer.Timeout");

			
			String FDMS_USERS_SCHEMA = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.userSchema");
			String FDMS_AUDIT_SCHEMA = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.auditSchema");
			String SERVICE_METHOD = "GenerateReport";
			String NS_XSD = "s";
		    //String NS_XSD = "http://www.w3.org/2001/XMLSchema";			
		
		    String BODY_NAMESPACE_VALUE = null; 
		    String BASE_FILENAME_SERVER_VALUE = null;
		    String endpoint = null;
			 CompanyOptionsManager coMgr = new CompanyOptionsManager ();
		        int newReportServerOption = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_NEWREPORTSERVER);
		        
		        
		    boolean isFillinForm = false;
		    String search = "DSFD";
		    String reportName = form.getReportName().toUpperCase();
		    int possition = reportName.indexOf(search);
		    if ( possition != -1) {
		    	isFillinForm = true;
		    }	  		    
		    int fillinReportFromAsreports = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_FILLIN_REPORT_FROM_ASREPORTS);  
			
		    boolean useAsreport = false;
		    
		    if ((newReportServerOption == 0)|| (fillinReportFromAsreports == 1 && isFillinForm) ){
		    	useAsreport = true;
		    	CONFIG_CODE_CR_WEBSERVICE_NAMESPACE = "52.27.74.179";//Edited By Rupali
		    	CRYSTAL_REPORTSERVER_IP  = "52.27.74.179";//Edited By Rupali
		    	CRYSTAL_REPORTGENERATOR_IP  = "52.27.74.179";//Edited By Rupali
		    }
    
		    
		    if (useAsreport) {
				BODY_NAMESPACE_VALUE = "http://" + CONFIG_CODE_CR_WEBSERVICE_NAMESPACE + "/WebfdmsReporting/";
				BASE_FILENAME_SERVER_VALUE = "http://" + CRYSTAL_REPORTSERVER_IP + "/WebfdmsReporting/";
				endpoint = "http://" + CRYSTAL_REPORTGENERATOR_IP + "/WebfdmsReporting/ReportService.asmx";
	        }
		    else {//xen3win3
		    
				BODY_NAMESPACE_VALUE = "http://" + CONFIG_CODE_CR_WEBSERVICE_NAMESPACE + "/CrystalDecisionsWebService/";
				BASE_FILENAME_SERVER_VALUE = "http://" + CRYSTAL_REPORTSERVER_IP + "/CrystalDecisionsWebService/";
				endpoint = "http://" + CRYSTAL_REPORTGENERATOR_IP + "/CrystalDecisionsWebService/ReportService.asmx  ";
		    }

			
			
			
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
		    //call.setTimeout(new Integer(300*1000));
		    call.setTimeout(new Integer(CRYSTAL_SERVERTIMEOUT));
		    
		    OperationDesc operation = new OperationDesc();
		    operation.setName(SERVICE_METHOD);
		    int i = 0;
		    
		    logger.info("ping");
		    if (useAsreport) {
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
		    		new QName(BODY_NAMESPACE_VALUE, "fileDir"), 
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
		    		new QName(BODY_NAMESPACE_VALUE, "userSchema"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "auditSchema"), 
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
		    		new QName(BODY_NAMESPACE_VALUE, "marginB"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    logger.info("inside useAsreport");
		    }
		    else { //xen3win3
		    
		    	logger.info("inside xen3win3");
				    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "LocationString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "LocaleString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "RptFileString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;	    
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "FileNameString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "FileDirString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;			    
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "DbString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "DsnString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);		  
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "SelFormulaString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "FromDateString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "ToDateString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "UserSchemaString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "AuditSchemaString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "MarginLInteger"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "MarginRInteger"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "MarginTInteger"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "MarginBInteger"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;	
		    //new additional
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "FormIdInteger"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "UserIdInteger"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);	
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "ExportTypeString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "DataPullString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "StoredProcNameString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;
		    operation.addParameter(
		    		new QName(BODY_NAMESPACE_VALUE, "XmlFileNameString"), 
		    		new QName(NS_XSD, "string"), 
		    		String.class, 
		    		ParameterDesc.IN, 
		    		false, 
		    		false);
		    i++;	       

		    }

		    operation.setReturnType(new QName(NS_XSD, "string"));
		    operation.setReturnClass(java.lang.String.class);
		    operation.setReturnQName(new QName(BODY_NAMESPACE_VALUE, "CreateStringResult"));
		    operation.setStyle( Style.WRAPPED  );
		    operation.setUse(Use.LITERAL); 
		    
		    call.setOperation(operation);
		    
			String[] params = new String[i];
			i = 0;
			
			params[i++] = location;
			params[i++] = Integer.toString(user.getRegion());
			params[i++] = form.getReportName();
			params[i++] = fileName;
			params[i++] = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.reportsDir");
			params[i++] = getDataSource(user);
			params[i++] = CRYSTAL_SERVER_DSN;
			params[i++] = formula;
			params[i++] = fromDate;
			params[i++] = toDate;
			params[i++] = FDMS_USERS_SCHEMA;
			params[i++] = FDMS_AUDIT_SCHEMA;
			params[i++] = Integer.toString(form.getMarginLeft());
			params[i++] = Integer.toString(form.getMarginRight());
			params[i++] = Integer.toString(form.getMarginTop());
			params[i++] = Integer.toString(form.getMarginBottom());
			
			if (!useAsreport) {

			params[i++] = Integer.toString(form.getFormId());
			params[i++] = Integer.toString(user.getId());
			params[i++] = form.getExportType();
			params[i++] = form.getDatapull();
			params[i++] = form.getStoredProc();
			params[i++] = form.getXmlFile();

			}
	
	        if ( debug ) {
	        
	        	if (useAsreport) {	
				logger.debug("params[i++] = location; \n"+
						"params[i++] = Integer.toString(user.getRegion()); \n"+
						"params[i++] = form.getReportName(); \n"+
						"params[i++] = fileName; \n"+
						"params[i++] = UtilSingleton.getInstance().getProperty(\"CrystalServer.reportsDir\"); \n"+
						"params[i++] = getDataSource(user); \n"+
						"params[i++] = CRYSTAL_SERVER_DSN; \n"+
						"params[i++] = formula; \n"+
						"params[i++] = fromDate; \n"+
						"params[i++] = toDate; \n"+
						"params[i++] = FDMS_USERS_SCHEMA; \n"+
						"params[i++] = FDMS_AUDIT_SCHEMA; \n"+
						"params[i++] = Integer.toString(form.getMarginLeft()); \n"+
						"params[i++] = Integer.toString(form.getMarginRight()); \n"+
						"params[i++] = Integer.toString(form.getMarginTop()); \n"+
						"params[i++] = Integer.toString(form.getMarginBottom());");	        	
	        	}
	        	else { //xen3win3

				logger.debug("params[i++] = location; \n"+
						"params[i++] = Integer.toString(user.getRegion()); \n"+
						"params[i++] = form.getReportName(); \n"+
						"params[i++] = fileName; \n"+
						"params[i++] = UtilSingleton.getInstance().getProperty(\"CrystalServer.reportsDir\"); \n"+
						"params[i++] = getDataSource(user); \n"+
						"params[i++] = CRYSTAL_SERVER_DSN; \n"+
						"params[i++] = formula; \n"+
						"params[i++] = fromDate; \n"+
						"params[i++] = toDate; \n"+
						"params[i++] = FDMS_USERS_SCHEMA; \n"+
						"params[i++] = FDMS_AUDIT_SCHEMA; \n"+
						"params[i++] = Integer.toString(form.getMarginLeft()); \n"+
						"params[i++] = Integer.toString(form.getMarginRight()); \n"+
						"params[i++] = Integer.toString(form.getMarginTop()); \n"+
						"params[i++] = Integer.toString(form.getMarginBottom()); \n"+
						"params[i++] = Integer.toString(form.getFormId()); \n"+
						"params[i++] = Integer.toString(user.getId()); \n"+
						"params[i++] = form.getExportType(); \n"+
						"params[i++] = form.getDatapull(); \n"+
						"params[i++] = form.getStoredProc(); \n"+
						"params[i++] = form.getXmlFile();");

	        	}
	        	
				for ( int x = 0; x < params.length; x++ ) {
					logger.debug( "params[" + x + "] = " +  params[x] );
				}

				
			}
		
			
	        for (int a = 1; a<= RRMaxNumber; a++) {
	        	try {
	        		fileURL = (String) call.invoke((Object[])params);
	        		logger.info("parameters = " + params);
	        		logger.info("file URL = " + fileURL);
	        	} catch (Exception e){
	        		//server not found you send out the server down.
	        		//put the process of email here;
	        		logger.error("From getFileUrl() : " + e);
	        		if (a == RRMaxNumber) {
						fileURL = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.errorPg");
						break;
					}
	        		else {
	        			
	        			emailAdmin(  user,  CRYSTAL_REPORTGENERATOR_IP );
	        			
	        			CRYSTAL_REPORTGENERATOR_IP = 	UtilSingleton.getInstance().getRRProperty(configID,"RR.CrystalServer.ReportGenerator.IP");
	        			
	        			
	        		    if ((newReportServerOption == 0)|| (fillinReportFromAsreports == 1 && isFillinForm) ){
	        		    	useAsreport = true;
	        		    	CONFIG_CODE_CR_WEBSERVICE_NAMESPACE = "52.27.74.179";//Edited By Rupali
	        		    	CRYSTAL_REPORTSERVER_IP  = "52.27.74.179";//Edited By Rupali
	        		    	CRYSTAL_REPORTGENERATOR_IP  = "52.27.74.179";//Edited By Rupali
	        		    }
	            
	        		    
	        		    if (useAsreport) {
	        				BODY_NAMESPACE_VALUE = "http://" + CONFIG_CODE_CR_WEBSERVICE_NAMESPACE + "/WebfdmsReporting/";
	        				BASE_FILENAME_SERVER_VALUE = "http://" + CRYSTAL_REPORTSERVER_IP + "/WebfdmsReporting/";
	        				endpoint = "http://" + CRYSTAL_REPORTGENERATOR_IP + "/WebfdmsReporting/ReportService.asmx";
	        	        }
	        		    else {//xen3win3
	        		    
	        				BODY_NAMESPACE_VALUE = "http://" + CONFIG_CODE_CR_WEBSERVICE_NAMESPACE + "/CrystalDecisionsWebService/";
	        				BASE_FILENAME_SERVER_VALUE = "http://" + CRYSTAL_REPORTSERVER_IP + "/CrystalDecisionsWebService/";
	        				endpoint = "http://" + CRYSTAL_REPORTGENERATOR_IP + "/CrystalDecisionsWebService/ReportService.asmx";
	        		    }
	        			
	        		    operation.setReturnQName(new QName(BODY_NAMESPACE_VALUE, "CreateStringResult"));
	        		    call.setOperation(operation);
	        		    call.setTargetEndpointAddress(new URL(endpoint));

	        		    
	        			continue;
	        			
	        		}
	        	}
				
				if ((fileURL != null) && (!"error".equals(fileURL)) && (fileURL.length()>0)) {
					if (useAsreport) {
						fileURL = BASE_FILENAME_SERVER_VALUE + fileURL;		
						
					}
					break;
				}
				else {

					fileURL = UtilSingleton.getInstance().getProperty(configID,"CrystalServer.errorPg");
					break;
				}
				
	        }
			
    	} catch (Exception e) {
    		logger.debug("Exception in getFileURL() : " + e.getMessage());
    	}
		return fileURL;
    }
    
	protected void emailAdmin( DbUserSession sessionUser, String errorServer ) {
    	
    	
    	DatabaseTransaction t = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			
    	
    	  	
	    	ArrayList <String> emailArray = new ArrayList <String> ();
			//emailArray.add("cjongs@aldorsolutions.com");
	    	emailArray.add("bshah@aldorsolutions.com");
	    	//emailArray.add("reportingservice@aldorsolutions.com");
	    	emailArray.add("systems@aldorsolutions.com");
			String subject ="A problem on print server: " + errorServer;	
			StringBuffer message = new StringBuffer();
			String endLine = "\r\n";
			message.append(endLine + endLine);
			message.append("Automatic email: Something has happened on a print server, cannot get connection, please fix " + endLine + endLine);
			message.append("Print Server : "+ errorServer + endLine + endLine );

			message.append(endLine + endLine);
			
			message.append("Thank You. ");
			
	    	MailUtil.sendEmail(sessionUser, sessionUser.getConfigID(), emailArray, null, null, subject, message.toString());
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in report printing. " + pe);
			
		} catch (Exception pe) {
			logger.error("Exception in  report printing. ", pe);
			
		} finally {
			if (t != null)
				t.closeConnection();
		}
		
	}
    
    public String getDataSource(DbUserSession user) {
        String datasource=null;
        int pos = user.getDataUrl().lastIndexOf("/");
        datasource = user.getDataUrl().substring(pos+1);
        return datasource;
    }
    
    public String makeFormName(String formName, DbUserSession user) {
        StringBuffer newFormName= new StringBuffer();
        int ipos = formName.indexOf("??");
        if (ipos<1){
            // no substitution
            return formName;
        }
        // get state of death from DbVitalsDeceased
        DatabaseTransaction t=null;
        DbVitalsDeceased deceasedInfo=null;
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            deceasedInfo = FdmsDb.getInstance().getVitalsDeceased(t, user.getCurrentCaseID());
            if (deceasedInfo==null){
                //AppLog.error("ExportReport.makeFormName: Invalid case-ID:"+user.getCurrentCaseID());
                return formName;
            }
        } catch (PersistenceException e){
            logger.debug("ExportReport.makeFormName PersistenceException:" + e.getMessage(), e);
            return formName;
        } finally {
        	if (t != null) {
        		t.closeConnection();
				t = null;
        	}
        }

        newFormName.append(formName.substring(0,ipos));
        newFormName.append(deceasedInfo.getStateOfDeath());
        newFormName.append(formName.substring(ipos+2));

        return newFormName.toString();
    }    

}
