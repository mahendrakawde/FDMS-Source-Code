package com.aldorsolutions.webfdms.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.model.CrystalReportCustomColDTO;


/**
 * Generate a report using Crystal Run Time Library.
 * Resulting report is exported to a PDF file.
 * The first line (up to first return) of the report selection formula
 * can be replaced with one provided from the DbFormsAvailable object.
 * Also, the following formulas get updated in the specified crystal report:
 *    FromDate
 *    ToDate
 *    LocationSelect
 *    locale
 * Creation date: (2/13/2002 10:39:13 AM)
 * @author:
 */
public class ExportReport {

    private Logger logger = Logger.getLogger(ExportReport.class.getName());
    private String appURL;
    private String reportSubDir;
    private String reportURLDir;
    private String reportLibrary;
    private int recordIdSelParam;
    private String exportFormat;
    private String appDirectory;
    /**
     * ViewReport constructor comment.
     */
    public ExportReport(long configID) {
        super();
        // Get root directory for GL transaction file
        UtilSingleton util = UtilSingleton.getInstance();
        reportSubDir = new String("reportTemp");
        reportURLDir = new String("webfdms/reportTemp");
        reportLibrary = new String("ReportLibrary");
        appURL = new String("/");
    
        String propsContext = util.getProperty(configID, Constants.CONFIG_CODE_APP_WEBCONTEXT );
        String propsPath = util.getProperty(configID, Constants.CONFIG_CODE_APP_DIRECTORY );
		String rptsLib = util.getProperty(configID, Constants.CONFIG_CODE_CR_REPORT_LIB);
		String rptsDir = util.getProperty(configID, Constants.CONFIG_CODE_CR_REPORT_DIR);
        
		
        if (propsContext == null){
            logger.error("WebAppContext key not found in webfdms.properties");
        }
        else {
        	reportURLDir = propsContext + "/" + reportSubDir;
        }
        
        if (propsPath == null){
            logger.error("InterfaceAccounting.getFileBaseDir Application Directory key not found in webfdms.properties");
            appDirectory = "c:\\";
        }
        else {
            appDirectory = propsPath;
        }
                    
        reportLibrary = getAppDirectory() + getReportLibrary();
        
        if (rptsLib == null){
            logger.error("Reports Library.getFileBaseDir Report Directory key not found in webfdms.properties");
            rptsLib = "c:\\reports\\";
        }
        
        if (rptsLib == null){
            logger.error("Reports Directory.getFileBaseDir Report Directory key not found in webfdms.properties");
            rptsLib += "US";
        }
        else {
        	rptsLib += rptsDir;
        }
        
        reportLibrary = rptsLib;
        
        recordIdSelParam=0;
        exportFormat = new String("default");
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/20/2002 4:37:08 PM)
     * @return String
     */
    public String getAppDirectory() {
        return appDirectory;
    }
    /**
     * Extract data source from User's data-url
     * Creation date: (2/21/2002 10:52:46 PM)
     * @return String
     * @param user DbUserSession
     */
    public String getDataSource(DbUserSession user) {
        String datasource=null;
        int pos = user.getDataUrl().lastIndexOf("/");
        datasource = user.getDataUrl().substring(pos+1);
        return datasource;
    }
    /**
     * Construct the appropriate HTML page name for an exported report.
     * Creation date: (2/14/2002 4:51:07 PM)
     * @return String form-name + ".HTML"
     * @param formName String
     * @param userCode String user identifier to make report file names unique by user
     */
    public String getDestinationFileName(String formName, String userCode) {
        StringBuilder nameBuff;
        String extension = exportFormat;
        if (extension.equalsIgnoreCase("WORD")){
            extension = "doc";
        } else if (extension.equals("default")) {
        	extension = "pdf";
        }
        
        int pos = formName.lastIndexOf(".");
        nameBuff = new StringBuilder();
        //nameBuff.append("/");
        
        if ( pos > 0 ) {
        	nameBuff.append(formName.substring(0,pos));
        }
        
        // append userID
        nameBuff.append(".");
        nameBuff.append(userCode);
        // append date/time stamp to make totaly unique name
        nameBuff.append(".");
        java.text.SimpleDateFormat timestamp = new java.text.SimpleDateFormat("MMddhhmmss");
        timestamp.setLenient(false);
        nameBuff.append(timestamp.format(new Date()));
        // append extension (doc, pdf, html, etc.)
        nameBuff.append(".");
        nameBuff.append(extension);

        return nameBuff.toString();
    }
    /**
     * Construct the appropriate path/file name for an exported report.
     * Creation date: (2/14/2002 4:51:07 PM)
     * @return String
     * @param pageName String
     */
    public String getDestinationPath(String pageName) {
        StringBuilder nameBuff;

        nameBuff = new StringBuilder();
        nameBuff.append(getAppDirectory());
        nameBuff.append(getReportSubDir());
        nameBuff.append("\\");
        nameBuff.append(pageName);

        return nameBuff.toString();
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/5/2002 4:46:06 PM)
     * @return String
     */
    public String getExportFormat() {
        return exportFormat;
    }
    /**
     * Insert the method's description here.
     * Creation date: (5/9/2002 1:43:00 PM)
     * @return int
     */
    public int getRecordIdSelParam() {
        return recordIdSelParam;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/18/2002 7:51:31 AM)
     * @return String
     */
    public String getReportLibrary() {
        return reportLibrary;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/18/2002 7:45:24 AM)
     * @return String
     */
    public String getReportSubDir() {
        return reportSubDir;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/13/2002 10:41:05 AM)
     * @param args String[]
     */
    public static void main(String[] args) {
        ExportReport report = new ExportReport(1);
        report.prepareReport("c:\\report1.rpt","","","","","Example Test Report","none","testreport.html");

    }
    /**
     * Look for mail-merge characters for replacement with case data.
     * Creation date: (2/25/2002 3:06:25 PM)
     * @return String
     * @param formName String
     */
    public String makeFormName(String formName, DbUserSession user) {
        StringBuilder newFormName= new StringBuilder();
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
        }
        catch (PersistenceException e){
            logger.error("ExportReport.makeFormName PersistenceException:", e);
            return formName;
        } finally {
			if ( t != null ) {
            	t.closeConnection();
            	t = null;
            }
		}

        newFormName.append(formName.substring(0,ipos));
        newFormName.append(deceasedInfo.getStateOfDeath());
        newFormName.append(formName.substring(ipos+2));
        //AppLog.trace("ExportReport.makeFormName: "+newFormName.toString());
        // Check if we have that state's form or not
        // Open the file RPT name to make sure it exists
        
        String formpath=new String(getReportLibrary() + File.separator + newFormName.toString());
        try {
        	File file = new File(formpath);
        	
        	if ((file == null) || (!file.exists())) {
        			throw new IOException("Report file does not exist");
        	}
        	
        }
        catch (IOException e) {
            logger.error("ExportReport.makeFormName form file does not exist : " + formpath);
            logger.error("makeFormName error : ", e);
            return "nodc.rpt";
        }

        return newFormName.toString();
    }
    /**
     * Construct a selection formula.
     * Get formula template from FormsAvailable object.
     * Look for % and replace with Vitals-ID of the case.
     * Creation date: (2/18/2002 12:17:35 PM)
     * @return String Selection Formula
     * @param user DbUserSession
     * @param form DbFormsAvailable
     * @SuppressWarnings = "true";
     */
    public String makeSelectionFormula(
    		DbUserSession user, 
    		DbFormsAvailable form, 
    		String fromDate, 
    		String toDate,
    		ArrayList additionalValues) {
        String formula = form.getSelectionFormula();
        if (formula.compareTo(" ")<=0){
            return " "+formula;
        }
        int subpos;
        StringBuilder formbuff;
        // Replace VITALS ID
        if (0<(subpos= formula.indexOf("%vitalid%")) ){
            formbuff = new StringBuilder();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( Integer.toString(user.getCurrentCaseID()));
            if (formula.length()> subpos+8){
                formbuff.append( formula.substring(subpos+9) );
            }
            formula = formbuff.toString();
        }
        // Replace Locale
        if (0<(subpos= formula.indexOf("%locale%")) ){
            formbuff = new StringBuilder();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( Integer.toString(user.getRegion()));
            if (formula.length()> subpos+7){
                formbuff.append( formula.substring(subpos+8) );
            }
            formula = formbuff.toString();
        }
        // Replace From Date
        if (0<(subpos= formula.indexOf("%fromdate%")) ){
            formbuff = new StringBuilder();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( fromDate );
            if (formula.length()> subpos+9){
                formbuff.append( formula.substring(subpos+10) );
            }
            formula = formbuff.toString();
        }
        // Replace to Date
        if (0<(subpos= formula.indexOf("%todate%")) ){
            formbuff = new StringBuilder();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( toDate );
            if (formula.length()> subpos+7){
                formbuff.append( formula.substring(subpos+8) );
            }
            formula = formbuff.toString();
        }
        // Replace RecordID
        if (0<(subpos= formula.indexOf("%recid%")) ){
            formbuff = new StringBuilder();
            formbuff.append( formula.substring(0,subpos));
            formbuff.append( recordIdSelParam );
            if (formula.length()> subpos+6){
                formbuff.append( formula.substring(subpos+7) );
            }
            formula = formbuff.toString();
        }
        
        if ((additionalValues != null) && (additionalValues.size() > 0)) {
        	Iterator it = additionalValues.iterator();
        	
        	while (it.hasNext()) {
        		CrystalReportCustomColDTO crCustomColDTO = (CrystalReportCustomColDTO) it.next();
        		
        		if (!"ALL".equalsIgnoreCase(crCustomColDTO.getColValue())) {

	                // Replace Custom Value
	                if (0<(subpos= formula.indexOf("%" + crCustomColDTO.getFormulaValue() + "%")) ){
	                    formbuff = new StringBuilder();
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
     * Call Native Method to generat a Crystal Report
     * Creation date: (2/13/2002 10:40:43 AM)
     * @param rptName String
     * @param server String
     * @param dbName String
     * @param userName String
     * @param password String
     * @param rptDescription String
     * @param selectionFormula String
     * @param exportFileName String
     */
    public void prepareReport(
    		String rptName, 
			String server, 
			String dbName, 
			String userName, 
			String password, 
			String rptDescr, 
			String selectionFormula, 
			String exportFileName){
    	
        try {
            NativeCall crpe = NativeCall.getInstance();
            crpe.previewReport(
            		rptName, 
					server, 
					dbName, 
					userName, 
					password, 
					rptDescr, 
					selectionFormula, 
					exportFileName);
        } catch (Exception e) {
            logger.error("Error in prepareReport() : ", e);
        }
    }
    /**
     * Determine path names for report name and export file name.
     * Then call JNI method to create and export report.
     * Creation date: (2/15/2002 8:24:59 PM)
     * @param formName String
     * @param description String
     * @param formula String First line of report selection formula
     * @param userCode String To make report names unique by user
     * @return String destination file name to forward to
     * @SuppressWarnings = "true";
     */
    public String printForm(
    		DbUserSession user, 
			int formID, 
			String fromDate, 
			String toDate, 
			ArrayList customValues,
			String location, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			ServletContext servletContext) {
    	
        DbFormsAvailable form;
        DatabaseTransaction t = null;
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            form = FdmsDb.getInstance().getFormsAvailable(t,formID);
            if (form==null){
                logger.error("ExportReport: Invalid Form ID for user  : " + user.getUserName());
                return "error";
            }
        } catch (PersistenceException e){
            logger.error("ExportReport PersistenceException:", e);
            return "error";
        } finally {
			if ( t != null ) {
            	t.closeConnection();
            	t = null;
            }
		}
        
        logger.debug("/********************************/");
        logger.debug("Username : " + user.getUserName());
        
        // Make user code
        String userCode = Integer.toString( user.getId() );
        
        logger.debug("Export type : " + form.getExportType());
        // Check if using default export type or if it has been specified by setExportFormat()
        if (exportFormat.compareToIgnoreCase("default")==0){
            exportFormat = form.getExportType();
        }
        // Replace substitutions in form name
        String finalFormName = makeFormName(form.getReportName(), user);
        logger.debug("Form name : " + finalFormName);
        // check for special case of the formName being a PDF file to simply serve up as-is
        if (finalFormName.indexOf(".pdf")>0 || finalFormName.indexOf(".PDF")>0){
            // just return the formName since we are serving this exact file name
            // from the reportLibrary directory
            return "/"+getReportLibrary()+"/"+finalFormName;
        }
        // Make export file name
        String pageName = getDestinationFileName(finalFormName, userCode);
        logger.debug("PageName : " + pageName);
        
        // Get export path for JNI and append export file name
        String formPath = getDestinationPath(pageName);
        logger.debug("Form path : " + formPath);
        
        // Get location for RPTs are stored and append RPT name to it.
        String formLibraryPath = new String(getReportLibrary()+"\\"+finalFormName);
        logger.debug("RPT path : " + formLibraryPath);
        
        // make sure fromDate, toDate, location have values
        // otherwist the strtok() in fdmscrpe.dll does not work as expected.
        if (fromDate.compareTo(" ")<=0) fromDate=" ";
        if (toDate.compareTo(" ")<=0)   toDate=" ";
        if (location.compareTo(" ")<=0) location=" ";
        // Make formula string of tokens to be unpacked in JNI dll.
        String formula = makeSelectionFormula(user, form, fromDate, toDate, customValues);
        StringBuilder formulaGroup = new StringBuilder();
        formulaGroup.append(formula);
        formulaGroup.append("|");
        formulaGroup.append(fromDate);
        formulaGroup.append("|");
        formulaGroup.append(toDate);
        formulaGroup.append("|");
        formulaGroup.append(location);
        formulaGroup.append("|");
        formulaGroup.append(user.getRegion());
        formulaGroup.append("|");
        formulaGroup.append(exportFormat);
        formulaGroup.append("|");
        formulaGroup.append(form.getMarginLeft());
        formulaGroup.append("|");
        formulaGroup.append(form.getMarginRight());
        formulaGroup.append("|");
        formulaGroup.append(form.getMarginTop());
        formulaGroup.append("|");
        formulaGroup.append(form.getMarginBottom());
        formula = formulaGroup.toString();
        
        logger.debug("Formula : " + formula);
        
        // Extract ODBC data source from URL of user
        String server= getDataSource(user);
        logger.debug("Database : " + server);

        prepareReport(
        		formLibraryPath, 
				"WebFdmsData1",
				server,
				user.getSqlUser(),
				user.getSqlPassword(),
				form.getDescription(),
				formula,
				formPath);
        
        logger.debug ( "reportURL: " + appURL + reportURLDir + "/" + pageName );
        
        return (appURL + reportURLDir + "/" + pageName);
    }
    

    /**
	 * Insert the method's description here. Creation date: (12/20/2002 4:37:08
	 * PM)
	 * 
	 * @param newAppDirectory
	 *            String
	 */
    public void setAppDirectory(String newAppDirectory) {
        appDirectory = newAppDirectory;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/5/2002 4:46:06 PM)
     * @param newExportFormat String
     */
    public void setExportFormat(String newExportFormat) {
        exportFormat = newExportFormat;
    }
    /**
     * Insert the method's description here.
     * Creation date: (5/9/2002 1:43:00 PM)
     * @param newRecordIdSelParam int
     */
    public void setRecordIdSelParam(int newRecordIdSelParam) {
        recordIdSelParam = newRecordIdSelParam;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/18/2002 7:51:31 AM)
     * @param newReportLibrary String
     */
    public void setReportLibrary(String newReportLibrary) {
        reportLibrary = newReportLibrary;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/18/2002 7:45:24 AM)
     * @param newReportSubDir String
     */
    public void setReportSubDir(String newReportSubDir) {
        reportSubDir = newReportSubDir;
    }
}
