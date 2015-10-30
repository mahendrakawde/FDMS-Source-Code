package com.aldorsolutions.webfdms.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.UtilSingleton;

/**
 * This class represents the data and methods for adding ONE set of GL journal entries
 * to a specified external disk file for interfacing with various general accounting software.
 * A static method allows creatint a standard disk file
 * name that can be passed to each instance of this class that may be needed for multiple
 * journal entries to the same disk file.
 * 6/26/03 - Added QuickBooks interface (v1.15)
 *
 * Creation date: (1/28/2003 7:18:10 AM)
 * @author:
 */
public class InterfaceAccounting {
    
    private static Logger logger = Logger.getLogger(InterfaceAccounting.class.getName());
    
    private int tranCount;
    private int groupTotal;
    private int caseID;
    private DatabaseTransaction t;
    private DbUser ourUser;
    private int interfaceType;
    private int locationSelected;
    private String diskFileName;
    private java.util.Date tranDate;
    public final static int CarriageReturn = 13;
    public final static int LineFeed = 10;
    private int summaryTotal;
    private int groupID;
    private int numDistr;
    private String transactionReference;
    private String arAcct;
    private java.util.ArrayList salesTranList;
    private int contractNumber;
    private String caseCode;
    
    /**
     * InterfaceAccounting constructor comment.
     */
    public InterfaceAccounting(
            DbUser aUser, 
            int aType, 
            int aLocation, 
            String aFileName, 
            int vitalsID) {
        super();
        t = null;
        ourUser = aUser;
        interfaceType = aType;
        locationSelected = aLocation;
        diskFileName =  aFileName;
        caseID = vitalsID;
        transactionReference = null;
        arAcct = null;
        salesTranList = new ArrayList();
    }
    
    /**
     * Add the GL transaction from input parameter to specified disk file.
     * Creation date: (1/28/2003 4:34:44 PM)
     * @param intFile java.io.File
     * @param gltran String
     */
    public static boolean addTranToDiskFile(String intFile, String gltran) {
        boolean success = false;
        FileOutputStream fileStream = null;
        java.io.PrintWriter printStream = null;
        
        try {
            fileStream = new FileOutputStream(intFile, true);  // append
            printStream = new java.io.PrintWriter(fileStream, true);  // autoflush
            printStream.println(gltran);
            success = true;
        } catch (java.io.IOException e) {
            logger.error("InterfaceAccounting.addTranToDiskFile  IOException: ", e);
            logger.error("Trying to open file named " + intFile);
        } finally {
            try {
                if (printStream != null) printStream.close();
                if (fileStream != null) fileStream.close();                
            } catch (IOException e) {
                logger.error("IO Exception in closing out streams : ", e);
            }
        }
        
        return success;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:25:49 AM)
     * @return String
     */
    public String getArAcct() {
        return arAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/21/2003 12:20:23 PM)
     * @return String
     */
    public String getCaseCode() {
        return caseCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:22:30 AM)
     * @return int
     */
    public int getCaseID() {
        return caseID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/21/2003 12:20:09 PM)
     * @return int
     */
    public int getContractNumber() {
        return contractNumber;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:37:54 AM)
     * @return String
     */
    public String getDiskFileName() {
        return diskFileName;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:04:13 AM)
     * @return int
     */
    public int getGroupID() {
        return groupID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:21:54 AM)
     * @return int
     */
    public int getGroupTotal() {
        return groupTotal;
    }
    /**
     * Access the config file for the path to an interface file relative to the
     * JSP server's context path.
     * Creation date: (1/30/2003 5:13:43 PM)
     * @return String
     * @param user DbUser
     */
    public static String getHttpBaseDir(DbUser user) {
        StringBuffer filepath = new StringBuffer();
        
        // context for webapp
        String propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "WebAppContext");
        if (propspath == null){
            logger.debug("InterfaceAccounting.getFileBaseDir WebAppContext key not found in webfdms.properties");
            filepath.append("/");
        } else {
            filepath.append(propspath);
            filepath.append("/");
        }
        // subdirectory for interface files
        propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "InterfaceLocation");
        if (propspath == null){
            logger.debug("InterfaceAccounting.getFileBaseDir IntefaceLocation key not found in webfdms.properties");
        } else {
            filepath.append(propspath);
            filepath.append("/");
        }  
        
        // Construct the directory for this file: dbname/localeID
        int pos = user.getDataUrl().lastIndexOf("/");
        filepath.append( user.getDataUrl().substring(pos+1)); // just the DB name
        filepath.append("/");
        filepath.append(user.getRegion());
        filepath.append("/");
                
        return filepath.toString();
    }
    
    public static String getEdenHttpBaseDir(DbUser user, int localeId, int locationId) {
       StringBuffer filepath = new StringBuffer();
        
        // context for webapp
        String propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "WebAppContext");
        if (propspath == null){
            logger.debug("InterfaceAccounting.getFileBaseDir WebAppContext key not found in webfdms.properties");
            filepath.append("/");
        } else {
            filepath.append(propspath);
            filepath.append("/");
        }
        // subdirectory for interface files
        propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "EdenLocation");
        if (propspath == null){
            logger.debug("InterfaceAccounting.getEdenFileBaseDir IntefaceLocation key not found in webfdms.properties");
        } else {
            filepath.append(propspath);
            filepath.append("/");
        }  
        
        // Construct the directory for this file: dbname/localeID
        int pos = user.getDataUrl().lastIndexOf("/");
        filepath.append( user.getDataUrl().substring(pos+1)); // just the DB name
        filepath.append(File.separator);
        filepath.append(localeId);
        filepath.append(File.separator);
        filepath.append(locationId);
        filepath.append(File.separator);
                
        return filepath.toString();
    }   
    /**
     * Access the PC file system for a list of interface files in this user's directory.
     * Creation date: (1/30/2003 5:20:39 PM)
     * @return String[] list of file names within the base interface directory
     * @param user DbUser
     */
    public static String[] getInterfaceFileList(DbUser user) {
        
        // get a FILE class for the base interface file directory for this user
        File interfacePath = new File( AccountingInterfaceUtil.getFileBaseDir(user) );
        if (!interfacePath.exists()){
            return null; // emtpy list
        }
        if (!interfacePath.isDirectory()){
            return null; // emtpy list
        }
        
        // Get list of files in this directory
        String[] filelist = interfacePath.list();
        
        return filelist;
    }
    
   public static String[] getEdenFileList(DbUser user, int localeId, int locationId) {
        
	   // get a FILE class for the base interface file directory for this user
       File interfacePath = new File( AccountingInterfaceUtil.getEdenFileBaseDir(user, localeId, locationId) );
       if (!interfacePath.exists()){
           return null; // emtpy list
       }
       if (!interfacePath.isDirectory()){
           return null; // emtpy list
       }
       
       // Get list of files in this directory
       String[] filelist = interfacePath.list();
       
       return filelist;
    }
    /**
     * Fabricate a new CSV file name in the format YYYYMMDD_HHMMSS_location.locationID.type.CSV.
     * No directory is used -- just the file name.
     * Directory for this file will be the dbname\localeID -
     * but the directory name can be either the disk system root or the
     * JSP server root depending on the caller's needs.
     * type  - Specifies which Accounting interface file is to be created.
     * locale - Company for which interface is created.
     * locationSelected - If one location is to be selected, otherwise 0 for all locations.
     * Creation date: (1/23/2003 4:54:18 PM)
     * @return String inteface file name created.
     */
    static public  String getInterfaceFileName(int interfaceType, int locationSelected, DbUser user) {
        StringBuffer filename = new StringBuffer();
        StringBuffer filepath = new StringBuffer();
        DatabaseTransaction t = null;
        DbLocation  location = null;
        // Get root directory for GL transaction file
        //filepath.append( getFileBaseDir(user));
        
        // Construct file name
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd_Hmmss");
        filename.append( formatter.format(new Date()));
        filename.append("_");
        // add location name to file name
        try {
            if (locationSelected > 0){
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
                location = FdmsDb.getInstance().getLocation(t, locationSelected);
                if (location != null){
                    String loc = location.getName();
                    loc.replace(' ','_');
                    loc.replace('"','_');
                    loc.replace('\'','_');
                    filename.append(loc);
                } else {
                    filename.append("notfound");
                }
            } else {
                filename.append("ALL");
            }
        } catch (PersistenceException e){
            filename.append(e.toString());
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        filename.append(".");
        filename.append(locationSelected);
        filename.append(".");
        filename.append(interfaceType);
        if (interfaceType==Constants.INTERFACE_QUICKBOOKS || interfaceType == Constants.INTERFACE_QUICKBOOKS_NEW){
            filename.append(".iif");
        } else if (interfaceType == Constants.INTERFACE_PEACHTREE_XML
        		|| interfaceType == Constants.INTERFACE_QUICKBOOKS_XML
        		|| interfaceType == Constants.INTERFACE_ACCPAC) {
            filename.append(".xml");
        } else filename.append(".csv");
        
        // Construct the file FILE
        filepath.append( filename );
        
        return filepath.toString();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:32:07 AM)
     * @return int
     */
    public int getInterfaceType() {
        return interfaceType;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:32:27 AM)
     * @return int
     */
    public int getLocationSelected() {
        return locationSelected;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:04:58 AM)
     * @return int
     */
    public int getNumDistr() {
        return numDistr;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:30:35 AM)
     * @return DbUser
     */
    public DbUser getOurUser() {
        return ourUser;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:02:57 AM)
     * @return int
     */
    public int getSummaryTotal() {
        return summaryTotal;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:23:59 AM)
     * @return DatabaseTransaction
     */
    public DatabaseTransaction getT() {
        return t;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:21:25 AM)
     * @return int
     */
    public int getTranCount() {
        return tranCount;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 10:37:47 AM)
     * @return java.util.Date
     */
    public java.util.Date getTranDate() {
        return tranDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:05:59 AM)
     * @return String
     */
    public String getTransactionReference() {
        return transactionReference;
    }
    /**
     * If anything needs to be written to the accounting interface file, put it here.
     * Creation date: (6/26/2003 4:34:44 PM)
     * @param intFile java.io.File
     * @param gltran String
     */
    public static boolean initializeExport(String intFile, int intType) {
        boolean result = true;
        StringBuffer os1 = new StringBuffer();
                        
        if (intType == Constants.INTERFACE_QUICKBOOKS || intType == Constants.INTERFACE_QUICKBOOKS_NEW){
            // build string for TRNSaction line
            os1.append("!TRNS" + "\t");
            os1.append("TRNSID" +"\t");
            os1.append("TRNSTYPE" +"\t");
            os1.append("DATE" +"\t");
            os1.append("ACCNT" +"\t");
            os1.append("NAME" +"\t");
            os1.append("AMOUNT" +"\t");
            os1.append("DOCNUM" +"\t");
            os1.append("MEMO" +"\t");
            os1.append("CLEAR" +"\t");
            os1.append("TOPRINT" +"\t");
            os1.append("ADDR1" +"\t");
            os1.append("ADDR2" +"\t");
            os1.append("ADDR3" +"\t");
            os1.append("ADDR4" +"\t");
            os1.append("DUEDATE" +"\t");

            if (intType == Constants.INTERFACE_QUICKBOOKS_NEW)
                os1.append("CLASS" + "\t");

            result = addTranToDiskFile(intFile, os1.toString());

            if (!result) return result;
            
            os1 = new StringBuffer();
            // build string for SPLit line
            os1.append("!SPL" +"\t");
            os1.append("SPLID" +"\t");
            os1.append("TRNSTYPE" +"\t");
            os1.append("DATE" +"\t");
            os1.append("ACCNT" +"\t");
            os1.append("NAME" +"\t");
            os1.append("AMOUNT" +"\t");
            os1.append("DOCNUM" +"\t");
            os1.append("MEMO" +"\t");
            os1.append("CLEAR" +"\t");
            os1.append("QNTY" +"\t");
            os1.append("PRICE" +"\t");
            os1.append("INVITEM" +"\t");
            os1.append("TAXABLE" +"\t");
            os1.append("EXTRA" +"\t");
            
            if (intType == Constants.INTERFACE_QUICKBOOKS_NEW)
                os1.append("CLASS" + "\t");
            
            result = addTranToDiskFile(intFile, os1.toString()) ;
            
        } else if (intType == Constants.INTERFACE_PEACHTREE_XML) {
         
        	result = addTranToDiskFile(intFile, "<transactions>\n");
        
        } else if (intType == Constants.INTERFACE_QUICKBOOKS_XML) {
            
            os1.append("<transactions>");
            result = addTranToDiskFile(intFile, "<transactions>\n");
                        
        }
        
        return result;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:25:49 AM)
     * @param newArAcct String
     */
    public void setArAcct(String newArAcct) {
        arAcct = newArAcct;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/21/2003 12:20:23 PM)
     * @param newCaseCode String
     */
    public void setCaseCode(String newCaseCode) {
        caseCode = newCaseCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:22:30 AM)
     * @param newCaseID int
     */
    public void setCaseID(int newCaseID) {
        caseID = newCaseID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/21/2003 12:20:09 PM)
     * @param newContractNumber int
     */
    public void setContractNumber(int newContractNumber) {
        contractNumber = newContractNumber;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:37:54 AM)
     * @param newDiskFileName String
     */
    public void setDiskFileName(String newDiskFileName) {
        diskFileName = newDiskFileName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:04:13 AM)
     * @param newGroupID int
     */
    public void setGroupID(int newGroupID) {
        groupID = newGroupID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:21:54 AM)
     * @param newGroupTotal int
     */
    public void setGroupTotal(int newGroupTotal) {
        groupTotal = newGroupTotal;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:32:07 AM)
     * @param newInterfaceType int
     */
    public void setInterfaceType(int newInterfaceType) {
        interfaceType = newInterfaceType;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:32:27 AM)
     * @param newLocationSelected int
     */
    public void setLocationSelected(int newLocationSelected) {
        locationSelected = newLocationSelected;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:04:58 AM)
     * @param newNumDistr int
     */
    public void setNumDistr(int newNumDistr) {
        numDistr = newNumDistr;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:30:35 AM)
     * @param newOurUser DbUser
     */
    public void setOurUser(DbUser newOurUser) {
        ourUser = newOurUser;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:02:57 AM)
     * @param newSummaryTotal int
     */
    public void setSummaryTotal(int newSummaryTotal) {
        summaryTotal = newSummaryTotal;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:23:59 AM)
     * @param newT DatabaseTransaction
     */
    public void setT(DatabaseTransaction newT) {
        t = newT;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 7:21:25 AM)
     * @param newTranCount int
     */
    public void setTranCount(int newTranCount) {
        tranCount = newTranCount;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 10:37:47 AM)
     * @param newTranDate java.util.Date
     */
    public void setTranDate(java.util.Date newTranDate) {
        tranDate = newTranDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/28/2003 11:05:59 AM)
     * @param newTransactionReference String
     */
    public void setTransactionReference(String newTransactionReference) {
        transactionReference = newTransactionReference;
    }
    /**
     * Write both an adjustment transaction with an offsetting transaction to A/R.
     * Creation date: (1/28/2003 6:57:11 PM)
     * @return boolean
     * @param hist DbHistory
     * @param csvFileName String
     * @param interfaceType int
     */
    public static boolean writeAdjustment(DbHistory hist, String csvFileName, int interfaceType, DbUser user) {
        DbLocation location = null;
        DbCase  caseinfo = null;
        DbVitalsDeceased vitals = null;
        String  refcode=null;
        boolean success = true;
        DatabaseTransaction t = null;
        String  aracct = null;
        DbVitalsInformant inform = null;
        String taxable = null;
        String extra = null;
        String custname = null;
        int chapelId = 0;
        int transactionID = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            // get AR account from location
            caseinfo = FdmsDb.getInstance().getCase(t, hist.getLMainKey());
            chapelId = caseinfo.getChapelNumber();
            location = FdmsDb.getInstance().getLocation(t, chapelId);
            inform = FdmsDb.getInstance().getVitalsInformant(t, hist.getLMainKey());
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, hist.getLMainKey());
            
            if (vitals!=null && inform !=null){
                custname = vitals.getDecFullName();
            } else {
                custname = "CASEID:"+String.valueOf(caseinfo.getCaseCode());
                success=false;
                logger.debug("InterfaceAccounting: unable to fetch vitals for: " + 
                		hist.getLMainKey() + " success;" + success);
            }
            // make reference code
            // use either case code or contract code
            if (caseinfo!=null && caseinfo.getCaseCode().compareTo(" ")>0){
                refcode = caseinfo.getCaseCode();
            } else if (caseinfo!=null){
                refcode = caseinfo.getContractCode();
            } else refcode = "CASEID"+String.valueOf(hist.getLMainKey());
            
            // Determine A/R account
            if (location!=null){
                aracct = location.getArAcct();
            } else{
                aracct = "BadLoc#";
            }
            
            //setTranDate( hist.getCHistDate());
            // -----------------------------------------------------------------------------------------------
            if (interfaceType==Constants.INTERFACE_PEACHTREE) {
                // Write credit to sales account
                writePeachtreeTran(
                        csvFileName, 
                        hist.getCHistDate(), 
                        refcode, 
                        hist.getCHistGLAcct(), 
                        hist.getCHistDesc(), 
                        -hist.getLHistAmount(), 
                        hist.getLMainKey(), 
                        2);
                
                // Write debit side unless this is a "C" or "I" transaction type
                if (hist.getCHistSPF()!='C' && hist.getCHistSPF()!='I'){
                    writePeachtreeTran(
                            csvFileName, 
                            hist.getCHistDate(), 
                            refcode, 
                            aracct, 
                            hist.getCHistDesc(), 
                            hist.getLHistAmount(), 
                            hist.getLMainKey(), 
                            2);
                }
            }
            
            if (interfaceType == Constants.INTERFACE_PEACHTREE_XML) {
                // Write credit to sales account
                writeXMLTran(
                        csvFileName, 
                        hist.getCHistDate(), 
                        refcode, 
                        hist.getCHistGLAcct(), 
                        hist.getCHistDesc(), 
                        -hist.getLHistAmount(), 
                        hist.getLMainKey(), 
                        2);
                
                // Write debit side unless this is a "C" or "I" transaction type
                if (hist.getCHistSPF()!='C' && hist.getCHistSPF()!='I'){
                    writeXMLTran(
                            csvFileName, 
                            hist.getCHistDate(), 
                            refcode, 
                            aracct, 
                            hist.getCHistDesc(), 
                            hist.getLHistAmount(), 
                            hist.getLMainKey(), 
                            2);
                }                
            }
            
            // -----------------------------------------------------------------------------------------------
            if (interfaceType==Constants.INTERFACE_BUSINESSWORKS) {
                String cdescr = hist.getCHistDesc()+" "+Integer.toString(hist.getId());
                // write credit to sale, reverse sign on amount
                writeBusWorksTran(
                        csvFileName, 
                        hist.getCHistDate(),  
                        hist.getId(), 
                        hist.getCHistGLAcct(), 
                        cdescr, 
                        -hist.getLHistAmount(), 
                        refcode);
                // Write debit side unless this is a "C" or "I" transaction type
                if (hist.getCHistSPF()!='C' && hist.getCHistSPF()!='I'){
                    writeBusWorksTran(
                            csvFileName, 
                            hist.getCHistDate(),  
                            hist.getId(), 
                            aracct, 
                            cdescr, 
                            hist.getLHistAmount(), 
                            refcode);
                }
            }
            // -----------------------------------------------------------------------------------------------
            if (interfaceType==Constants.INTERFACE_QUICKBOOKS 
            		|| interfaceType == Constants.INTERFACE_QUICKBOOKS_NEW 
            		|| interfaceType == Constants.INTERFACE_QUICKBOOKS_XML){
                
                // For QuickBooks, we need to determine what kind of transaction since they
                // are handled differently
                if (hist.getCHistSPF()=='S' || hist.getCHistSPF()=='D' || hist.getCHistSPF()=='F'){
                    
                    // Write an INVOICE adjustment
                    if (hist.getCHistTaxCode().compareTo("   ") > 0) taxable = "Y";
                    else taxable = "N";
                    
                    // Check if this is a TAX charge
                    if (hist.getIHistType()==99 || hist.getIHistType()==98
                            || (hist.getCHistDesc().indexOf("Tax")>0) || (hist.getCHistDesc().indexOf("tax")>0)){
                        extra = "AUTOSTAX";
                    } else {
                        extra = " ";
                    }
                    
                    transactionID = hist.getId() * 10;
                    if (interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                    
	                    writeQbInvcTrnsTranXml(
	                            csvFileName,
	                            transactionID,
	                            hist.getCHistDate(),
	                            refcode,
	                            aracct,
	                            hist.getCHistDesc(),
	                            hist.getLHistAmount(),
	                            custname,
	                            2,
	                            inform,
	                            hist.getCHistDate(),
	                            chapelId,
	                            interfaceType
	                            );
	                    writeQbInvcSplTranXml(
	                            csvFileName,
	                            transactionID+1,
	                            hist.getCHistDate(),
	                            refcode,hist.getCHistGLAcct(),
	                            hist.getCHistDesc(),
	                            hist.getLHistAmount(),
	                            custname,
	                            hist.getCHistInvItemName(),
	                            taxable,
	                            extra,
	                            chapelId,
	                            interfaceType
	                            );
	                 //   writeQbEndTrnsXml(csvFileName);
	                    
                    }
                    else {
                    	
                    	writeQbInvcTrnsTran(
	                            csvFileName,
	                            transactionID,
	                            hist.getCHistDate(),
	                            refcode,
	                            aracct,
	                            hist.getCHistDesc(),
	                            hist.getLHistAmount(),
	                            custname,
	                            2,
	                            inform,
	                            hist.getCHistDate(),
	                            chapelId,
	                            interfaceType
	                            );
	                    writeQbInvcSplTran(
	                            csvFileName,
	                            transactionID+1,
	                            hist.getCHistDate(),
	                            refcode,hist.getCHistGLAcct(),
	                            hist.getCHistDesc(),
	                            hist.getLHistAmount(),
	                            custname,
	                            hist.getCHistInvItemName(),
	                            taxable,
	                            extra,
	                            chapelId,
	                            interfaceType
	                            );
	                 	                               	
                    }
                
                
                } else if (hist.getCHistSPF()=='C'){
                    // For "Cost of sale" record, write 1/2 of a journal entry with TRNS record
                    // "I" transaction must be next for the last half SPL record
                    transactionID = hist.getId()*10;
                    if (interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                    
	                    writeQbIIFTrnsXml(
	                         csvFileName,
	                         transactionID,
	                         "TRNS",
	                         "General Journal",
	                         hist.getCHistDate(),
	                         refcode,
	                         hist.getCHistGLAcct(),
	                         hist.getCHistDesc(),
	                         -hist.getLHistAmount(),
	                         custname,
	                         " ",
	                         " ",
	                         " ",
	                         " ",
	                         chapelId,
	                         interfaceType);
	                 //   writeQbEndTrnsXml(csvFileName);
                    
                    } else {
                    
	                    writeQbIIFTrns(	
	                    	 csvFileName,
	                         transactionID,
	                         "TRNS",
	                         "General Journal",
	                         hist.getCHistDate(),
	                         refcode,
	                         hist.getCHistGLAcct(),
	                         hist.getCHistDesc(),
	                         -hist.getLHistAmount(),
	                         custname,
	                         " ",
	                         " ",
	                         " ",
	                         " ",
	                         chapelId,
	                         interfaceType);	
	                    writeQbEndTrns(csvFileName);
                    }
                    
                } else if (hist.getCHistSPF()=='I'){
                    transactionID = hist.getId()*10;
                    
                    if (interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                    	
	                    writeQbIIFTrnsXml(
	                            csvFileName,
	                            transactionID,
	                            "SPL",
	                            "General Journal",
	                            hist.getCHistDate(),
	                            refcode,
	                            hist.getCHistGLAcct(),
	                            hist.getCHistDesc(),
	                            -hist.getLHistAmount(),
	                            custname,
	                            " ",
	                            " ",
	                            " ",
	                            " ",
	                            chapelId,
	                            interfaceType);
	               //     writeQbEndTrnsXml(csvFileName);
                    
                    }
                    else {
                    	
	                    writeQbIIFTrns(
	                            csvFileName,
	                            transactionID,
	                            "SPL",
	                            "General Journal",
	                            hist.getCHistDate(),
	                            refcode,
	                            hist.getCHistGLAcct(),
	                            hist.getCHistDesc(),
	                            -hist.getLHistAmount(),
	                            custname,
	                            " ",
	                            " ",
	                            " ",
	                            " ",
	                            chapelId,
	                            interfaceType);
	                    
	                    writeQbEndTrns(csvFileName);
                    }

                } else if (hist.getCHistSPF()=='P'){
                    transactionID = hist.getId()*10;
                                        
                    if (interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                    	
	                    writeQbIIFTrnsXml(
	                            csvFileName,
	                            transactionID,
	                            "TRNS",
	                            "Payment",
	                            hist.getCHistDate(),
	                            refcode,
	                            hist.getCHistGLAcct(),
	                            hist.getCHistDesc(),
	                            -hist.getLHistAmount(),
	                            custname,
	                            " ",
	                            " ",
	                            " ",
	                            " ",
	                            chapelId,
	                            interfaceType);
	                    writeQbIIFTrnsXml(
	                            csvFileName,
	                            transactionID+1,
	                            "SPL",
	                            "Payment",
	                            hist.getCHistDate(),
	                            refcode,
	                            hist.getCHistARacct(),
	                            hist.getCHistDesc(),
	                            hist.getLHistAmount(),
	                            custname,
	                            " ",
	                            " ",
	                            " ",
	                            " ",
	                            chapelId,
	                            interfaceType);
	                //    writeQbEndTrns(csvFileName);
                    }    
                    else {
                    	
	                    writeQbIIFTrns(
	                            csvFileName,
	                            transactionID,
	                            "TRNS",
	                            "Payment",
	                            hist.getCHistDate(),
	                            refcode,
	                            hist.getCHistGLAcct(),
	                            hist.getCHistDesc(),
	                            -hist.getLHistAmount(),
	                            custname,
	                            " ",
	                            " ",
	                            " ",
	                            " ",
	                            chapelId,
	                            interfaceType);
	                    writeQbIIFTrns(
	                            csvFileName,
	                            transactionID+1,
	                            "SPL",
	                            "Payment",
	                            hist.getCHistDate(),
	                            refcode,
	                            hist.getCHistARacct(),
	                            hist.getCHistDesc(),
	                            hist.getLHistAmount(),
	                            custname,
	                            " ",
	                            " ",
	                            " ",
	                            " ",
	                            chapelId,
	                            interfaceType);
	                            writeQbEndTrns(csvFileName);
	                    
	                    }

                }
                // skip anything else
            }
            // -----------------------------------------------------------------------------------------------
        } catch (PersistenceException e){
            success=false;
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        return false;
    }
    /**
     * Write both GL transactions for a vendor check with an offsetting transaction to CASH.
     * Creation date: (1/28/2003 6:57:11 PM)
     * @return boolean
     * @param hist DbHistory
     * @param csvFileName String
     * @param interfaceType int
     */
    public static boolean writeApExpense(
            DbApDistribution check,
            String csvFileName,
            int interfaceType,
            DbUser user) {
        
        DbLocation location = null;
        DbApMaster apmaster = null;
        DbApVendor vendor = null;
        DbApAccount acct = null;
        boolean success = true;
        DatabaseTransaction t = null;
        String cashacct = null;
        String expacct = null;
        String vendorName = null;
        int chapelId = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            // get check master
            apmaster = FdmsDb.getInstance().getApCheck(t, check.getMasterID());
            if (apmaster == null){
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Ap Master ID for detail transaction#"+check.getId());
                return false;
            }
            
            // get vendor
            vendor = FdmsDb.getInstance().getApVendor(t, apmaster.getVendorID());
            if (vendor==null){
                vendorName = "vendor ID not found:"+apmaster.getVendorID();
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Vendor ID for detail transaction#"+check.getId());
            } else {
                vendorName = vendor.getName();
            }
            
            // get CASH account from location
            location = FdmsDb.getInstance().getLocation(t, apmaster.getLocationID());
            // Save case account
            if (location !=null){
                cashacct = location.getCashAcct();
                chapelId = location.getId();
            } else {
                cashacct ="BadLoc#";
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Location ID for detail transaction#"+check.getId());
            }
            
            // get expense account record
            acct = FdmsDb.getInstance().getApAccount(t, check.getAccountID());
            if (acct != null){
                expacct = acct.getAccountNumber();
            } else {
                expacct = "BadAcct#";
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Acct ID for detail transaction#"+check.getId());
            }
            
            if (interfaceType==Constants.INTERFACE_PEACHTREE) {
                // Write debit to expense account
                writePeachtreeTran(
                        csvFileName, 
                        apmaster.getCheckDate(), 
                        String.valueOf(apmaster.getCheckNumber()), 
                        expacct, 
                        check.getMemo(), 
                        check.getAmount(), 
                        apmaster.getLocationID(), 
                        2);
                // Write credit side to cash account
                writePeachtreeTran(
                        csvFileName, 
                        apmaster.getCheckDate(), 
                        String.valueOf(apmaster.getCheckNumber()), 
                        cashacct, 
                        apmaster.getMemo(), 
                        -check.getAmount(), 
                        apmaster.getLocationID(), 
                        2);
            }
            
            if (interfaceType==Constants.INTERFACE_PEACHTREE_XML) {
                // Write debit to expense account
                writeXMLTran(
                        csvFileName, 
                        apmaster.getCheckDate(), 
                        String.valueOf(apmaster.getCheckNumber()), 
                        expacct, 
                        check.getMemo(), 
                        check.getAmount(), 
                        apmaster.getLocationID(), 
                        2);
                // Write credit side to cash account
                writeXMLTran(
                        csvFileName, 
                        apmaster.getCheckDate(), 
                        String.valueOf(apmaster.getCheckNumber()), 
                        cashacct, 
                        apmaster.getMemo(), 
                        -check.getAmount(), 
                        apmaster.getLocationID(), 
                        2);                
            }
            
            if (interfaceType==Constants.INTERFACE_BUSINESSWORKS) {
                String refcode = Long.toString(apmaster.getCheckNumber());
                // write debit to expense account
                writeBusWorksTran(
                        csvFileName, 
                        apmaster.getCheckDate(),  
                        apmaster.getCheckNumber(), 
                        expacct, 
                        check.getMemo(), 
                        check.getAmount(), 
                        refcode);
                // Write credit side to cash account
                writeBusWorksTran(
                        csvFileName, 
                        apmaster.getCheckDate(),  
                        apmaster.getCheckNumber(), 
                        cashacct, 
                        apmaster.getMemo(), 
                        -check.getAmount(), 
                        refcode);
            }
            
            if (interfaceType==Constants.INTERFACE_QUICKBOOKS || interfaceType==Constants.INTERFACE_QUICKBOOKS_NEW || interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                
                String refcode = Long.toString(apmaster.getCheckNumber());
                long transid = apmaster.getCheckNumber()*10;
                // write debit to expense account
                if (interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                	
                	  writeQbIIFTrnsXml(
	                        csvFileName,
	                        transid,
	                        "TRNS",
	                        "Billpmt",
	                        apmaster.getCheckDate(),
	                        refcode,
	                        expacct,
	                        check.getMemo(),
	                        check.getAmount(),
	                        vendorName,
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        chapelId,
	                        interfaceType);
	                // Write credit side to cash account
	                writeQbIIFTrnsXml(
	                        csvFileName,
	                        transid,
	                        "SPL",
	                        "Billpmt",
	                        apmaster.getCheckDate(),
	                        refcode,
	                        cashacct,
	                        apmaster.getMemo(),
	                        -check.getAmount(),
	                        vendorName,
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        chapelId,
	                        interfaceType);
	                	
                }
                else {
                	
	                writeQbIIFTrns(
	                        csvFileName,
	                        transid,
	                        "TRNS",
	                        "Billpmt",
	                        apmaster.getCheckDate(),
	                        refcode,
	                        expacct,
	                        check.getMemo(),
	                        check.getAmount(),
	                        vendorName,
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        chapelId,
	                        interfaceType);
	                // Write credit side to cash account
	                writeQbIIFTrns(
	                        csvFileName,
	                        transid,
	                        "SPL",
	                        "Billpmt",
	                        apmaster.getCheckDate(),
	                        refcode,
	                        cashacct,
	                        apmaster.getMemo(),
	                        -check.getAmount(),
	                        vendorName,
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        chapelId,
	                        interfaceType);
	                }
            }
        } catch (PersistenceException e){
            success=false;
            logger.debug("Success " + success, e);
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        return false;
    }
    /**
     * Generic format for BusinessWorks CSV file.
     *  BWFormat[]="/%ld,%s/%s/%s,0,\"%s\",\"%s\",%s,%10.2f%c%c";
     * Creation date: (2/21/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeBusWorksTran(
            String intfile, 
            java.util.Date sDate, 
            long sequenceno, 
            String sGlacct, 
            String sDescr, 
            int sAmount, 
            String journalRef) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0);
        
        // Optional slash at beginning of line which BW converts to # if successfully imported
        gltran.append("/");
        // Reference (contract# or vitals ID)
        gltran.append(  Long.toString(sequenceno));
        gltran.append(    ",");
        //Date of transaction
        gltran.append(  FormatDate.convertDateToMM_DD_YY(sDate));
        gltran.append(    ",");
        // code "Zero" means General Journal
        gltran.append("0,");
        // Journal Reference (manually entered case code) -- quoted
        gltran.append(  "\"");
        gltran.append(  journalRef);
        gltran.append(    "\",");
        // Description -- quoted
        gltran.append(  "\"");
        gltran.append(  sDescr);
        gltran.append(    "\",");
        // GL Account#
        gltran.append(  sGlacct);
        gltran.append(    ",");
        // Amount
        gltran.append(  amount);
        
        
        // Transaction Number (not used on import)
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }
    /**
     * Write both an adjustment transaction with an offsetting transaction to CASH for a cash sale.
     * Creation date: (1/28/2003 6:57:11 PM)
     * @return boolean
     * @param hist DbHistory
     * @param csvFileName String
     * @param interfaceType int
     */
    public static boolean writeMiscCashReceipt(
            DbHistory hist,
            String csvFileName,
            int interfaceType,
            DbUser user) {
        
        DbLocation location = null;
        String refcode = null;
        boolean success = true;
        DatabaseTransaction t = null;
        String cashacct = null;
        int transactionID = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            // get AR account from location
            location = FdmsDb.getInstance().getLocation(t, hist.getLocationId());
            if (location != null){
                cashacct = location.getCashAcct();
            } else {
                logger.debug("InterfaceAccounting.writeMiscCashReceipt, Invalid Location ID for hist transaction#"+hist.getId());
                cashacct = "BadLoc#";
            }
            
            // make reference code using receipt number
            refcode = Integer.toString(hist.getLHistReceiptNo() );
            
            if (interfaceType==Constants.INTERFACE_PEACHTREE) {
                // Write credit to sales account
                writePeachtreeTran(
                        csvFileName, 
                        hist.getCHistDate(), 
                        refcode, 
                        hist.getCHistGLAcct(), 
                        hist.getCHistDesc(), 
                        -hist.getLHistAmount(), 
                        hist.getLocationId(), 
                        2);
                // Write debit side to cash account
                writePeachtreeTran(
                        csvFileName, 
                        hist.getCHistDate(), 
                        refcode, 
                        cashacct, 
                        hist.getCHistDesc(), 
                        hist.getLHistAmount(), 
                        hist.getLocationId(), 
                        2);
                
            } else if (interfaceType==Constants.INTERFACE_PEACHTREE_XML) {
                // Write credit to sales account
                writeXMLTran(
                        csvFileName, 
                        hist.getCHistDate(), 
                        refcode, 
                        hist.getCHistGLAcct(), 
                        hist.getCHistDesc(), 
                        -hist.getLHistAmount(), 
                        hist.getLocationId(), 
                        2);
                // Write debit side to cash account
                writeXMLTran(
                        csvFileName, 
                        hist.getCHistDate(), 
                        refcode, 
                        cashacct, 
                        hist.getCHistDesc(), 
                        hist.getLHistAmount(), 
                        hist.getLocationId(), 
                        2);                
            } else if (interfaceType==Constants.INTERFACE_BUSINESSWORKS) {
                String cdescr = hist.getCHistDesc()+" "+Integer.toString(hist.getId());
                // write credit to sale, reverse sign on amount
                writeBusWorksTran(
                        csvFileName, 
                        hist.getCHistDate(),  
                        hist.getId(), 
                        hist.getCHistGLAcct(), 
                        cdescr, 
                        -hist.getLHistAmount(), 
                        refcode);
                // Write debit side to cash account
                writeBusWorksTran(
                        csvFileName, 
                        hist.getCHistDate(),  
                        hist.getId(), 
                        cashacct, 
                        cdescr, 
                        hist.getLHistAmount(), 
                        refcode);
            }
            // -----------------------------------------------------------------------------------------------
            else if (interfaceType==Constants.INTERFACE_QUICKBOOKS || interfaceType == Constants.INTERFACE_QUICKBOOKS_NEW || interfaceType == Constants.INTERFACE_QUICKBOOKS_XML ){
                // Write an INVOICE adjustment
                transactionID = hist.getId()*10;
                if (interfaceType == Constants.INTERFACE_QUICKBOOKS_XML) {
                	
                	writeQbIIFTrnsXml(
	                        csvFileName,
	                        transactionID,
	                        "TRNS",
	                        "General Journal",
	                        hist.getCHistDate(),
	                        refcode,
	                        cashacct,
	                        hist.getCHistDesc(),
	                        hist.getLHistAmount(),
	                        "Misc Cash Receipt",
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        hist.getLocationId(),
	                        interfaceType);
	                
	                writeQbIIFTrnsXml(
	                        csvFileName,
	                        transactionID+1,
	                        "SPL",
	                        "General Journal",
	                        hist.getCHistDate(),
	                        refcode,
	                        hist.getCHistGLAcct(),
	                        hist.getCHistDesc(),
	                        -hist.getLHistAmount(),
	                        "Misc Cash Receipt",
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        hist.getLocationId(),
	                        interfaceType);
	                
	                //writeQbEndTrns(csvFileName);
                }
                else { 
                	
                	writeQbIIFTrns(
	                        csvFileName,
	                        transactionID,
	                        "TRNS",
	                        "General Journal",
	                        hist.getCHistDate(),
	                        refcode,
	                        cashacct,
	                        hist.getCHistDesc(),
	                        hist.getLHistAmount(),
	                        "Misc Cash Receipt",
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        hist.getLocationId(),
	                        interfaceType);
	                
	                writeQbIIFTrns(
	                        csvFileName,
	                        transactionID+1,
	                        "SPL",
	                        "General Journal",
	                        hist.getCHistDate(),
	                        refcode,
	                        hist.getCHistGLAcct(),
	                        hist.getCHistDesc(),
	                        -hist.getLHistAmount(),
	                        "Misc Cash Receipt",
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        hist.getLocationId(),
	                        interfaceType);
	                
	                writeQbEndTrns(csvFileName);
	            }
            }	        
            
        } 
        catch (PersistenceException e){
            success=false;
            logger.debug("Success " + success, e);
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        return false;
    }
    /**
     * Generic format for PeachTree's CSV file.
     * Creation date: (1/28/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writePeachtreeTran(
            String intfile, 
            java.util.Date sDate, 
            String sRef, 
            String sGlacct, 
            String sDescr, 
            int sAmount, 
            int sTranID, 
            int sNumDist) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0);
        
        // Transaction Date
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append(",");
        // Reference
        gltran.append(sRef);
        gltran.append(",");
        //Date clear in bank rec (not used)
        gltran.append(",");
        //Number of ditributions
        gltran.append(sNumDist);
        gltran.append(",");
        // GL account
        gltran.append(sGlacct);
        gltran.append(",");
        // Description
        gltran.append(sDescr);
        gltran.append(",");
        // Tran amount
        gltran.append(amount);
        gltran.append(",");
        // Job ID
        gltran.append(",");
        // Reimburable Expense
        gltran.append(",");
        // Transaction period
        gltran.append(",");
        // Transaction Number (not used on import)
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }
    /**
     * QuickBook's IIF file ENDTRNS record.
     * Creation date: (7/2/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeQbEndTrns(String intfile) {
        
        // Write to disk file
        addTranToDiskFile(intfile, "ENDTRNS\t");
        return true;
    }
    /**
     * SPL split record format for QuickBooks's IIF file.
     * Creation date: (7/1/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeQbIIFTrns(
            String intfile,
            long transactionID,
            String iifType,
            String journal,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            String quantity,
            int chapelId,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        // Record Identifier
        gltran.append(iifType);
        gltran.append("\t");
        // transaction ID
        gltran.append(transactionID);
        gltran.append("\t");
        // "General Journal" or "Invoice" or "Payment" etc.
        gltran.append(journal);
        gltran.append("\t");
        // Transaction Date
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(  sGlacct);
        gltran.append("\t");
        // Name
        gltran.append(  custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(  amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(  sRef);
        gltran.append("\t");
        // Memo
        gltran.append(  sDescr);
        gltran.append("\t");
        // Clear
        gltran.append(" \t");
        // Quantity
        gltran.append(quantity);
        gltran.append("\t");
        // Price
        gltran.append(  price);
        gltran.append("\t");
        // Item name
        gltran.append( itemname );
        gltran.append("\t");
        // Taxable
        gltran.append( taxable );
        gltran.append("\t");
        // Extra
        gltran.append( extratax );
        gltran.append("\t");
        // due date not used in SPL
        gltran.append(" \t");
        if (qbVersion == Constants.INTERFACE_QUICKBOOKS_NEW) {
            // chapel number
            gltran.append(chapelId);
            gltran.append("\t");
        }
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }

    public static boolean writeQbIIFTrnsXml(
            String intfile,
            long transactionID,
            String iifType,
            String journal,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            String quantity,
            int chapelId,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        // Record Identifier
        gltran.append("<transaction>\n");
        
        gltran.append("    <iiftype>");
        gltran.append(iifType);
        gltran.append("</iiftype>");
        gltran.append("\n");
        
        // transaction ID
        gltran.append("    <transactionid>");
        gltran.append(transactionID);
        gltran.append("</transactionid>");
        gltran.append("\n");
        
        // "General Journal" or "Invoice" or "Payment" etc.
        gltran.append("    <journal>");
        gltran.append(journal);
        gltran.append("</journal>");
        gltran.append("\n");
        
        // Transaction Date
        gltran.append("    <transactiondate>");
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("</transactiondate>");
        gltran.append("\n");
        
        // GL account
        gltran.append("    <glaccount>");
        gltran.append(  sGlacct);
        gltran.append("</glaccount>");
        gltran.append("\n");
        
        // Name
        gltran.append("    <customername>");
        gltran.append(  custname);
        gltran.append("</customername>");
        gltran.append("\n");
        
        // Tran amount
        gltran.append("    <transactionamount>");
        gltran.append(  amount);
        gltran.append("</transactionamount>");
        gltran.append("\n");
        
        // Doc Num
        gltran.append("    <documentnumber>");
        gltran.append(  sRef);
        gltran.append("</documentnumber>");
        gltran.append("\n");
        
        // Memo
        gltran.append("    <memo>");
        gltran.append(sDescr.replaceAll("&","&amp;"));
        gltran.append("</memo>");
        gltran.append("\n");
        
        // Clear
        //gltran.append(" \t");
        
        // Quantity
        gltran.append("    <quantity>");
        gltran.append(quantity);
        gltran.append("</quantity>");
        gltran.append("\n");
        
        // Price
        gltran.append("    <price>");
        gltran.append(  price);
        gltran.append("</price>");
        gltran.append("\n");
        
        // Item name
        gltran.append("    <itemname>");
        gltran.append( itemname );
        gltran.append("</itemname>");
        gltran.append("\n");
        
        // Taxable
        gltran.append("    <taxable>");
        gltran.append( taxable );
        gltran.append("</taxable>");
        gltran.append("\n");
        
        // Extra
        gltran.append("    <extratax>");
        gltran.append( extratax );
        gltran.append("</extratax>");
        gltran.append("\n");
        
        // due date not used in SPL
        //gltran.append(" \t");
        
        // chapel number
        gltran.append("    <chapelid>");
        gltran.append(chapelId);
        gltran.append("</chapelid>");
        gltran.append("\n");
        
        gltran.append("</transaction>");
               
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }
    
    /**
     * SPL split record format for QuickBooks's IIF file.
     * Creation date: (7/1/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeQbInvcSplTran(
            String intfile,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            int chapelId,
            int qbVersion) {

        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)-sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        // Record Identifier
        gltran.append("SPL\t");
        // Trans ID
        gltran.append(transactionID);
        gltran.append("\t");
        // Trans type
        gltran.append("Invoice\t");
        // Transaction Date
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(  sGlacct);
        gltran.append("\t");
        // Name
        gltran.append(  custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(  amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(  sRef);
        gltran.append("\t");
        // Memo
        gltran.append(  sDescr);
        gltran.append("\t");
        // Clear
        gltran.append(" \t");
        // Quantity
        gltran.append("-1\t");
        // Price
        gltran.append(  price);
        gltran.append("\t");
        // Item name
        gltran.append( itemname );
        gltran.append("\t");
        // Taxable
        gltran.append( taxable );
        gltran.append("\t");
        // Extra
        gltran.append( extratax );
        gltran.append("\t");
        // due date not used in SPL
        gltran.append(" \t");
        if (qbVersion == Constants.INTERFACE_QUICKBOOKS_NEW || qbVersion == Constants.INTERFACE_QUICKBOOKS_XML) {
            // chapel number
            gltran.append(chapelId);
            gltran.append("\t");
        }
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }
    
    
    
    /**
     * SPL split record format for QuickBooks's XML IIF file.
     * Creation date: (7/1/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeXmlQbInvcSplTran(
            String intfile,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            int chapelId,
            int qbVersion) {
            	
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)-sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        
        gltran.append("<transaction>\n");
        
        // Record Identifier
        gltran.append("    <spl>");
        gltran.append("SPL");
        gltran.append("</spl>\n");
        
        // Trans ID
        gltran.append("    <transactionid>");
        gltran.append(transactionID);
        gltran.append("</transactionid>\n");
        
        
        // Trans type
        gltran.append("    <transactionid>");
        gltran.append("Invoice");
        gltran.append("</transactionid>\n");
        
        // Transaction Date
        gltran.append("    <transactiondate>");
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("</transactiondate>\n");
        
        
        // GL account
        gltran.append("    <glaccount>");
        gltran.append(  sGlacct);
        gltran.append("</glaccount>\n");
       
        
        // Name
        gltran.append("    <customername>");
        gltran.append(  custname);
        gltran.append("</customername>\n");
        
        // Tran amount
        gltran.append("    <transactionamount>");
        gltran.append(  amount);
        gltran.append("</transactionamount>\n");
        
        // Doc Num
        gltran.append("    <documentnumber>");
        gltran.append(  sRef);
        gltran.append("</documentnumber>\n");
        
        // Memo
        gltran.append("    <memo>");
        gltran.append(  sDescr);
        gltran.append("</memo>\n");
        
        // Clear
        //gltran.append(" \t");
        
        // Quantity
        gltran.append("    <quantity>");
        gltran.append("-1");
        gltran.append("</quantity>\n");
        
        // Price
        gltran.append("    <price>");
        gltran.append(  price);
        gltran.append("</price>\n");
        
        // Item name
        gltran.append("    <itemname>");
        gltran.append( itemname );
        gltran.append("</itemname>\n");
        
        // Taxable
        gltran.append("    <taxable>");
        gltran.append( taxable );
        gltran.append("</taxable>\n");
        
        // Extra
        gltran.append("    <extratax>");
        gltran.append( extratax );
        gltran.append("</extratax>\n");
        
        // due date not used in SPL
        //gltran.append(" \t");
        
        // chapel number
        gltran.append("    <chapelnumber>");
        gltran.append(chapelId);
        gltran.append("</chapelnumber>\n");
        
        gltran.append("</transaction>");
        
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }
    
    
    
    
    /**
     * SPL split record format for QuickBooks's IIF file.
     * Creation date: (7/1/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeQbInvcSplTranXml(
            String intfile,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            int chapelId,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)-sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        // Record Identifier
        gltran.append("SPL\t");
        // Trans ID
        gltran.append(transactionID);
        gltran.append("\t");
        // Trans type
        gltran.append("Invoice\t");
        // Transaction Date
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(  sGlacct);
        gltran.append("\t");
        // Name
        gltran.append(  custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(  amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(  sRef);
        gltran.append("\t");
        // Memo
        gltran.append(  sDescr);
        gltran.append("\t");
        // Clear
        gltran.append(" \t");
        // Quantity
        gltran.append("-1\t");
        // Price
        gltran.append(  price);
        gltran.append("\t");
        // Item name
        gltran.append( itemname );
        gltran.append("\t");
        // Taxable
        gltran.append( taxable );
        gltran.append("\t");
        // Extra
        gltran.append( extratax );
        gltran.append("\t");
        // due date not used in SPL
        gltran.append(" \t");
        if (qbVersion == Constants.INTERFACE_QUICKBOOKS_NEW || qbVersion == Constants.INTERFACE_QUICKBOOKS_XML) {
            // chapel number
            gltran.append(chapelId);
            gltran.append("\t");
        }
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }


    /**
     * TRNS record format for QuickBook's IIF file.
     * Creation date: (7/1/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeQbInvcTrnsTran(
            String intfile,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            int sNumDist,
            DbVitalsInformant inform,
            Date duedate,
            int chapelId,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        
        String informName = "C/O "+inform.getFname()+" "+inform.getLname();
        String informCity = inform.getCity()+", "+inform.getState()+" "+inform.getZip();
        
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat) numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double) sAmount / 100.0);
        // Identifiers
        gltran.append("TRNS\t");
        // Trans ID
        gltran.append(transactionID);
        gltran.append("\t");
        // Trans type
        gltran.append("Invoice\t");
        // Transaction Date
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(sGlacct);
        gltran.append("\t");
        // Deceased Name (Customer Name)
        gltran.append(custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(sRef);
        gltran.append("\t");
        // memo
        gltran.append(sDescr);
        gltran.append("\t");
        //Clear
        gltran.append(" \t");
        //to print
        gltran.append("N\t");
        // addr1
        gltran.append(informName);
        gltran.append("\t");
        // addr2
        gltran.append(inform.getStreet());
        gltran.append("\t");
        // addr3
        gltran.append(" \t");
        // addr4
        gltran.append(informCity);
        gltran.append("\t");
        // due date
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(duedate));
        gltran.append("\t");
        if (qbVersion == Constants.INTERFACE_QUICKBOOKS_NEW || qbVersion == Constants.INTERFACE_QUICKBOOKS_XML) {
            // chapel number
            gltran.append(chapelId);
            gltran.append("\t");
        }
        
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile(intfile, gltran.toString());
        return true;
    }

    /**
     * TRNS record format for QuickBook's XML iif file.
     * Creation date: (7/1/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeQbInvcTrnsTranXml(
            String intfile,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            int sNumDist,
            DbVitalsInformant inform,
            Date duedate,
            int chapelId,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        
        String informName = "C/O "+inform.getFname()+" "+inform.getLname();
        String informCity = inform.getCity()+", "+inform.getState()+" "+inform.getZip();
        
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat) numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double) sAmount / 100.0);
        // Identifiers
        gltran.append("<transaction>\n");
        
        gltran.append("    <trns>");
        gltran.append("TRNS");
        gltran.append("</trns>");
        gltran.append("\n");
        
        // Trans ID
        gltran.append("    <transactionid>");
        gltran.append(transactionID);
        gltran.append("</transactionid>");
        gltran.append("\n");
        
        // Trans type
        gltran.append("    <invoice>");
        gltran.append("Invoice");
        gltran.append("</invoice>");
        gltran.append("\n");
        
        // Transaction Date
        gltran.append("    <transactiondate>");
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("</transactiondate>");
        gltran.append("\n");
        
        // GL account
        gltran.append("    <glaccount>");
        gltran.append(sGlacct);
        gltran.append("</glaccount>");
        gltran.append("\n");
        
        // Deceased Name (Customer Name)
        gltran.append("    <customername>");
        gltran.append(custname);
        gltran.append("</customername>");
        gltran.append("\n");
        
        // Tran amount
        gltran.append("    <transactionamount>");
        gltran.append(amount);
        gltran.append("</transactionamount>");
        gltran.append("\n");
        
        
        // Doc Num
        gltran.append("    <documentnumber>");
        gltran.append(sRef);
        gltran.append("</documentnumber>");
        gltran.append("\n");
        
        
        // memo
        gltran.append("    <memo>");
        gltran.append(sDescr);
        gltran.append("</memo>");
        gltran.append("\n");
       
        
        //Clear
        //gltran.append(" \t");
 
        
        //to print
        gltran.append("    <toprint>");
        gltran.append("N");
        gltran.append("</toprint>");
        gltran.append("\n");
        
        // addr1
        gltran.append("    <addr1>");
        gltran.append(informName);
        gltran.append("</addr1>");
        gltran.append("\n");
        
        
        // addr2
        gltran.append("    <address2>");
        gltran.append(inform.getStreet());
        gltran.append("</address2>");
        gltran.append("\n");
        
        
        // addr3
        //gltran.append(" \t");
        
        
        // addr4
        gltran.append("    <addr4>");
        gltran.append(informCity);
        gltran.append("</addr4>");
        gltran.append("\n");
        
        // due date
        gltran.append("    <duedate>");
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(duedate));
        gltran.append("</duedate>");
        gltran.append("\n");
               
        /// chapel number
        gltran.append("    <chapelnumber>");
        gltran.append(chapelId);
        gltran.append("</chapelnumber>");
        gltran.append("\n");
        
        gltran.append("</transaction>");
        
                
        // Add END-OF-LINE character
        // not needed since using println() which adds an end-of-line automatically
        //gltran.append(  CarriageReturn);
        //gltran.append(  LineFeed);
        
        // Write to disk file
        addTranToDiskFile(intfile, gltran.toString());
        return true;
    }
    
    /**
     * Construct a GL journal entry to credit sales.
     * Creation date: (1/28/2003 4:58:10 PM)
     * @return boolean
     * @param hist DbHistory
     * @param interfaceType int
     */
    public boolean writeSalesTran(DbHistory hist, int interfaceType, String intfile) {
        DbCase caseinfo = null;
        DbLocation location = null;
        DbCase histcase = null;
        boolean success = true;
                
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getOurUser());
            // check if need to get AR account from location
            if (getArAcct() == null){
                histcase = FdmsDb.getInstance().getCase(t, hist.getLMainKey());
                location = FdmsDb.getInstance().getLocation(t, histcase.getChapelNumber());
                // DO NOT PROCESS THIS TRANSACTION if case or location not found -- invalid data
                if (histcase == null || location == null){
                    //AppLog.error("InterfaceAccounting.WriteSalesTran found invalid data at hist#"+hist.getId());
                    return false;
                }
                setArAcct(location.getArAcct());
            }
            
            // check if need to save reference
            if (getTransactionReference()==null){
                // use either case code or contract code
                caseinfo = FdmsDb.getInstance().getCase(t, getCaseID());
                int icontractno = FormatNumber.parseInteger(caseinfo.getContractCode());
                if (icontractno < 1) icontractno = caseinfo.getId();
                setContractNumber(  icontractno);
                setCaseCode(    caseinfo.getCaseCode());
                
                if (caseinfo!=null && caseinfo.getCaseCode().compareTo(" ")>0){
                    setTransactionReference( caseinfo.getCaseCode());
                } else if (caseinfo!=null){
                    setTransactionReference( caseinfo.getContractCode());
                } else setTransactionReference( "CASEID"+String.valueOf(getCaseID()));
            }
            // Check if need to save transaction date
            if (getTranDate()==null){
                setTranDate( hist.getCHistDate());
            }
            
        } catch (PersistenceException e){
            logger.error("InterfaceAccounting.WriteSalesTran found invalid data at hist#"+hist.getId()+". Persistence Exception:", e);
            success=false;
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        if (getInterfaceType()==Constants.INTERFACE_PEACHTREE
                || getInterfaceType()==Constants.INTERFACE_QUICKBOOKS
                || getInterfaceType()==Constants.INTERFACE_QUICKBOOKS_NEW
                || getInterfaceType()==Constants.INTERFACE_QUICKBOOKS_XML
                || getInterfaceType()==Constants.INTERFACE_PEACHTREE_XML){
            // Accumulate details because need to write header record first then these detail transactions.
            salesTranList.add(hist);
            setSummaryTotal(hist.getLHistAmount() + getSummaryTotal());
            //writePeachtreeTran(intfile, getTranDate(), getTransactionReference(), getArAcct(), hist.getCHistDesc(), , getGroupID(), getNumDistr());
        }
        
        if (getInterfaceType()==Constants.INTERFACE_BUSINESSWORKS) {
            String cdescr = hist.getCHistDesc()+" "+Integer.toString(hist.getId());
            writeBusWorksTran(
                    intfile, 
                    getTranDate(),  
                    getContractNumber(), 
                    hist.getCHistGLAcct(), 
                    cdescr, 
                    -hist.getLHistAmount(), 
                    getTransactionReference());
            setSummaryTotal(hist.getLHistAmount() + getSummaryTotal());            
        }
        
        return success;
    }
    /**
     * Add an accounting interface summary transaction as required by
     * the specific accounting interface type.
     * Creation date: (1/28/2003 10:31:57 AM)
     * @return boolean
     */
    public boolean writeSummaryTran(String intfile) {
        DbVitalsDeceased vitals = null;
        DbVitalsInformant inform = null;
        String description = null;
        DbHistory hist = null;
        String taxable = null;
        String extra = null;
        boolean success = true;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getOurUser());
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, getCaseID());
            inform = FdmsDb.getInstance().getVitalsInformant(t, getCaseID());
            if (vitals!=null && inform !=null){
                description = vitals.getDecFullName();
            } else {
                description = "CASEID"+String.valueOf(getCaseID());
                success=false;
                //AppLog.error("InterfaceAccounting: unable to fetch vitals for: "+getCaseID());
            }
        } catch (PersistenceException e){
            description = e.toString();
            logger.debug("Success " + success, e);
            success=false;
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        if (getInterfaceType()==Constants.INTERFACE_PEACHTREE){
            int numTrans =  salesTranList.size() + 1; // plus one for summary transaction
            writePeachtreeTran(
                    intfile, 
                    getTranDate(), 
                    getTransactionReference(), 
                    getArAcct(), 
                    description, 
                    getSummaryTotal(), 
                    getGroupID(), 
                    numTrans);
            
            java.util.Iterator mylist = salesTranList.iterator();
            while (mylist.hasNext()){
                hist = (DbHistory)mylist.next();
                writePeachtreeTran(
                        intfile, 
                        getTranDate(), 
                        getTransactionReference(), 
                        hist.getCHistGLAcct(), 
                        hist.getCHistDesc(), 
                        -hist.getLHistAmount(), 
                        getGroupID(),
                        numTrans);
            }
            
        } else if (getInterfaceType() == Constants.INTERFACE_PEACHTREE_XML) {
            int numTrans =  salesTranList.size() + 1; // plus one for summary transaction
            writeXMLTran(
                    intfile, 
                    getTranDate(), 
                    getTransactionReference(), 
                    getArAcct(), 
                    description, 
                    getSummaryTotal(), 
                    getGroupID(), 
                    numTrans);
            
            java.util.Iterator mylist = salesTranList.iterator();
            while (mylist.hasNext()){
                hist = (DbHistory)mylist.next();
                writeXMLTran(
                        intfile, 
                        getTranDate(), 
                        getTransactionReference(), 
                        hist.getCHistGLAcct(), 
                        hist.getCHistDesc(), 
                        -hist.getLHistAmount(), 
                        getGroupID(),
                        numTrans);
            }            
        } else if (getInterfaceType()==Constants.INTERFACE_BUSINESSWORKS) {
            writeBusWorksTran(
                    intfile, 
                    getTranDate(),  
                    getContractNumber(), 
                    getArAcct(), 
                    description, 
                    getSummaryTotal(),
                    getTransactionReference());
            
        } else if (getInterfaceType() == Constants.INTERFACE_QUICKBOOKS || 
        		   getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_NEW || getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_XML){
            
            ArrayList cogs = new ArrayList();
            int numTrans =  salesTranList.size() + 1; // plus one for summary transaction
            int transid = 0;
            boolean firstCycle = true;
            java.util.Iterator mylist = salesTranList.iterator();
            
            while (mylist.hasNext()){
                hist = (DbHistory)mylist.next();
                
                if (firstCycle){
                    // write debit to A/R group header record
                    transid = (hist.getId()*10)-1;

                    if (getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_XML) {
                    	
                    	writeQbInvcTrnsTranXml(
                                intfile,
                                transid,
                                getTranDate(),
                                getTransactionReference(),
                                getArAcct(),
                                description,
                                getSummaryTotal(),
                                description,
                                numTrans,
                                inform,
                                getTranDate(),
                                hist.getLocationId(),
                                interfaceType);
                            firstCycle=false;
                    
                    }
                    else {
                                        
                        writeQbInvcTrnsTran(
                            intfile,
                            transid,
                            getTranDate(),
                            getTransactionReference(),
                            getArAcct(),
                            description,
                            getSummaryTotal(),
                            description,
                            numTrans,
                            inform,
                            getTranDate(),
                            hist.getLocationId(),
                            interfaceType);
                        firstCycle=false;
                    
                    }
                }
                // COGS and Inventory transactions must be handled separately
                if (hist.getCHistSPF()=='C' || hist.getCHistSPF()=='I'){
                    cogs.add(hist);
                } else {
                    // handle sale transactions
                    if (hist.getCHistTaxCode().compareTo("   ") > 0){
                        taxable = "Y";
                    } else {
                        taxable = "N";
                    }
                    
                    // Check if this is a TAX charge
                    if (hist.getIHistType()==99 || hist.getIHistType()==98
                            || (hist.getCHistDesc().indexOf("Tax")>0) || (hist.getCHistDesc().indexOf("tax")>0)){
                        extra = "AUTOSTAX";
                    } else {
                        extra = " ";
                    }
                    transid = hist.getId()*10;
                    
                    if (getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_XML) {
                    	
                    	writeXmlQbInvcSplTran(
                                intfile,
                                transid,
                                getTranDate(),
                                getTransactionReference(),
                                hist.getCHistGLAcct(),
                                hist.getCHistDesc(),
                                hist.getLHistAmount(),
                                description,
                                hist.getCHistInvItemName(),
                                taxable,
                                extra,
                                hist.getLocationId(),
                                interfaceType);
                                            
                    }
                    else {
                    	
                        writeQbInvcSplTran(
                            intfile,
                            transid,
                            getTranDate(),
                            getTransactionReference(),
                            hist.getCHistGLAcct(),
                            hist.getCHistDesc(),
                            hist.getLHistAmount(),
                            description,
                            hist.getCHistInvItemName(),
                            taxable,
                            extra,
                            hist.getLocationId(),
                            interfaceType);
                    
                    }
                }
            }
            
            if (getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_XML) {
            
            	/// do nothing
            
            }
            else {
            	
                writeQbEndTrns(intfile);
                
            }
            // Now write General Journal entries for Inventory transactions
            mylist = cogs.iterator();
            while (mylist.hasNext()){
                hist = (DbHistory)mylist.next();
                if (hist.getCHistSPF()=='C'){
                    // For "Cost of sale" record, write 1/2 of a journal entry with TRNS record
                    // "I" transaction must be next for the last half SPL record
                    transid = hist.getId()*10;
                    writeQbIIFTrns(
                            intfile,
                            transid,
                            "TRNS",
                            "General Journal",
                            hist.getCHistDate(),
                            getTransactionReference(),
                            hist.getCHistGLAcct(),
                            hist.getCHistDesc(),
                            -hist.getLHistAmount(),
                            description,
                            " ",
                            " ",
                            " ",
                            " ",
                            hist.getLocationId(),
                            interfaceType);
                } else if (hist.getCHistSPF()=='I'){
                    transid = hist.getId()*10;
                    writeQbIIFTrns(
                            intfile,
                            transid,
                            "SPL",
                            "General Journal",
                            hist.getCHistDate(),
                            getTransactionReference(),
                            hist.getCHistGLAcct(),
                            hist.getCHistDesc(),
                            -hist.getLHistAmount(),
                            description,
                            " ",
                            " ",
                            " ",
                            " ",
                            hist.getLocationId(),
                            interfaceType);
                    writeQbEndTrns(intfile);
                }
            }
        }
        
        return false;
    }
    
    
    /*
     * Writes summary for quicken xml reports
     * This method is a partial copy of the writesummarytran method above
     */
    public boolean writeXmlSummaryTran(String intfile) {
    	
    	
            DbVitalsDeceased vitals = null;
            DbVitalsInformant inform = null;
            String description = null;
            DbHistory hist = null;
            String taxable = null;
            String extra = null;
            boolean success = true;
            
            try {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getOurUser());
                vitals = FdmsDb.getInstance().getVitalsDeceased(t, getCaseID());
                inform = FdmsDb.getInstance().getVitalsInformant(t, getCaseID());
                if (vitals!=null && inform !=null){
                    description = vitals.getDecFullName();
                } else {
                    description = "CASEID"+String.valueOf(getCaseID());
                    success=false;
                    //AppLog.error("InterfaceAccounting: unable to fetch vitals for: "+getCaseID());
                }
            } catch (PersistenceException e){
                description = e.toString();
                success=false;
                logger.debug("Success " + success, e);
            } finally {
    			if ( t != null ) {
    				t.closeConnection();
    				t = null;
    			}
    		}
                
                ArrayList cogs = new ArrayList();
                int numTrans =  salesTranList.size() + 1; // plus one for summary transaction
                int transid = 0;
                boolean firstCycle = true;
                java.util.Iterator mylist = salesTranList.iterator();
                
                while (mylist.hasNext()){
                    hist = (DbHistory)mylist.next();
                    
                    if (firstCycle){
                        // write debit to A/R group header record
                        transid = (hist.getId()*10)-1;
                        writeQbInvcTrnsTranXml(
                                intfile,
                                transid,
                                getTranDate(),
                                getTransactionReference(),
                                getArAcct(),
                                description,
                                getSummaryTotal(),
                                description,
                                numTrans,
                                inform,
                                getTranDate(),
                                hist.getLocationId(),
                                interfaceType);
                        firstCycle=false;
                    }
                    // COGS and Inventory transactions must be handled separately
                    if (hist.getCHistSPF()=='C' || hist.getCHistSPF()=='I'){
                        cogs.add(hist);
                    } else {
                        // handle sale transactions
                        if (hist.getCHistTaxCode().compareTo("   ") > 0){
                            taxable = "Y";
                        } else {
                            taxable = "N";
                        }
                        
                        // Check if this is a TAX charge
                        if (hist.getIHistType()==99 || hist.getIHistType()==98
                                || (hist.getCHistDesc().indexOf("Tax")>0) || (hist.getCHistDesc().indexOf("tax")>0)){
                            extra = "AUTOSTAX";
                        } else {
                            extra = " ";
                        }
                        transid = hist.getId()*10;
                        writeQbInvcSplTranXml(
                                intfile,
                                transid,
                                getTranDate(),
                                getTransactionReference(),
                                hist.getCHistGLAcct(),
                                hist.getCHistDesc(),
                                hist.getLHistAmount(),
                                description,
                                hist.getCHistInvItemName(),
                                taxable,
                                extra,
                                hist.getLocationId(),
                                interfaceType);
                    }
                }
                //writeQbEndTrns(intfile);
                // Now write General Journal entries for Inventory transactions
                mylist = cogs.iterator();
                while (mylist.hasNext()){
                    hist = (DbHistory)mylist.next();
                    if (hist.getCHistSPF()=='C'){
                        // For "Cost of sale" record, write 1/2 of a journal entry with TRNS record
                        // "I" transaction must be next for the last half SPL record
                        transid = hist.getId()*10;
                        writeQbIIFTrnsXml(
                                intfile,
                                transid,
                                "TRNS",
                                "General Journal",
                                hist.getCHistDate(),
                                getTransactionReference(),
                                hist.getCHistGLAcct(),
                                hist.getCHistDesc(),
                                -hist.getLHistAmount(),
                                description,
                                " ",
                                " ",
                                " ",
                                " ",
                                hist.getLocationId(),
                                interfaceType);
                    } else if (hist.getCHistSPF()=='I'){
                        transid = hist.getId()*10;
                        writeQbIIFTrnsXml(
                                intfile,
                                transid,
                                "SPL",
                                "General Journal",
                                hist.getCHistDate(),
                                getTransactionReference(),
                                hist.getCHistGLAcct(),
                                hist.getCHistDesc(),
                                -hist.getLHistAmount(),
                                description,
                                " ",
                                " ",
                                " ",
                                " ",
                                hist.getLocationId(),
                                interfaceType);
                       // writeQbEndTrns(intfile);
                    }
                }
                        
            return false;
        }
  
    /**
     * Generic format for PeachTree's CSV file.
     * Creation date: (1/28/2003 10:45:15 AM)
     * @return boolean  TRUE for successful
     */
    public static boolean writeXMLTran(
            String intfile, 
            java.util.Date sDate, 
            String sRef, 
            String sGlacct, 
            String sDescr, 
            int sAmount, 
            int sTranID, 
            int sNumDist) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0);
        
        gltran.append("<transaction>\n");
        
        //Transaction Date
        
        // sale date
        gltran.append("    <saleDate>");
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("</saleDate>\n");
        // Reference
        gltran.append("    <reference>");
        gltran.append(sRef);
        gltran.append("</reference>\n");
        //Date clear in bank rec (not used)
        
        
        //Number of ditributions
        gltran.append("    <distributions>");
        gltran.append(sNumDist);
        gltran.append("</distributions>\n");
        
        // GL account
        gltran.append("    <glaccount>");
        gltran.append(sGlacct);
        gltran.append("</glaccount>\n");
        
        // Description
        gltran.append("    <description>");
        gltran.append(sDescr);
        gltran.append("</description>\n");
        
        // Tran amount
        gltran.append("    <amount>");
        gltran.append(amount);
        gltran.append("</amount>\n");
        
        // Job ID
        
        // Reimburable Expense
        
       
        // Transaction period
       
        // Transaction Number (not used on import)
        
        gltran.append("</transaction>\n");
        
        // Write to disk file
        addTranToDiskFile( intfile, gltran.toString() );
        
        return true;
    }    
    

    
    /* Method to print invoices to quickbooks xml report
     * 
     */
    public static void writeQuickBooksXmlInvoices(String csvFileName,StringBuffer sb) {
    
    	        // write data to file
        addTranToDiskFile( csvFileName, sb.toString() );
    
    }
    
    
    
    public static boolean closeXML(String fileName) {
        addTranToDiskFile(fileName, "</transactions>");        
        return true;
    }

}
