package com.aldorsolutions.webfdms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUser;


/**
 * Workfile: AccountingInterfaceUtil.java
 * Date: Nov 7, 2005 6:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceUtil {
	
	private final static Logger logger = Logger.getLogger(AccountingInterfaceUtil.class.getName());

	
    /**
     * Access the application's config file to get the DISK SYSTEM's base directory
     * needed for doing FILE I/O.
     * Creation date: (1/30/2003 5:13:43 PM)
     * @return java.lang.String
     * @param user com.aldorsolutions.webfdms.beans.DbUser
     */
    public static String getFileBaseDir(DbUser user) {
    	
    	logger.debug("Entering getFileBaseDir()");
    	
        StringBuffer filepath = new StringBuffer();
        
        // Get root directory for GL transaction file        
        String propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "ApplicationDirectory");
        if (propspath == null){
            logger.debug("InterfaceAccounting.getFileBaseDir Application Directory key not found in webfdms.properties");
            filepath.append("c:\\");
        } else {
            filepath.append(propspath);
        }
        // Add the Interface Accounting sub-directory
        propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "InterfaceLocation");
        if (propspath == null){
            logger.debug("InterfaceAccounting.getFileBaseDir InterfaceLocation key not found in webfdms.properties");
        } else {
            filepath.append(propspath);
            filepath.append( File.separator );
        }      
        
        // Construct the directory for this file: dbname/localeID
        int pos = user.getDataUrl().lastIndexOf("/");
        filepath.append( user.getDataUrl().substring(pos+1)); // just the DB name
        filepath.append(File.separator);
        filepath.append(user.getRegion());
        filepath.append(File.separator);
        
        // Make this directory if it does not exist
        File interfacePath = new File( filepath.toString());
        logger.debug("InterfaceAccounting: creating directory:"+filepath.toString());
        if ( !interfacePath.exists() ){
            if (interfacePath.mkdirs()){
                logger.debug("directory created");
            } else {
                logger.debug("InterfaceAccounting.getInterfaceFileName: could not make directory:"+filepath.toString());
                return null;
            }
        }
        
        return filepath.toString();
    }	
    
    public static String getEdenFileBaseDir(DbUser user, int localeId, int locationId) {
    	
        StringBuffer filepath = new StringBuffer();
        
        // Get root directory for GL transaction file        
        String propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "ApplicationDirectory");
        if (propspath == null){
//            logger.debug("getFileBaseDir Application Directory key not found in webfdms.properties");
            filepath.append("c:\\");
        } else {
            filepath.append(propspath);
        }
        // Add the Interface Accounting sub-directory
        propspath = UtilSingleton.getInstance().getProperty(user.getConfigID(), "EdenLocation");
        if (propspath == null){
//            logger.debug("getFileBaseDir InterfaceLocation key not found in webfdms.properties");
        } else {
            filepath.append(propspath);
            filepath.append( File.separator );
        }      
        
        // Construct the directory for this file: dbname/localeID
        int pos = user.getDataUrl().lastIndexOf("/");
        filepath.append( user.getDataUrl().substring(pos+1)); // just the DB name
        filepath.append(File.separator);
        filepath.append(localeId);
        filepath.append(File.separator);
        filepath.append(locationId);
        filepath.append(File.separator);
        
        // Make this directory if it does not exist
        File interfacePath = new File( filepath.toString());
//        logger.debug("InterfaceAccounting: creating directory:"+filepath.toString());
        if ( !interfacePath.exists() ){
            if (interfacePath.mkdirs()){
//                logger.debug("directory created");
            } else {
//                logger.debug("InterfaceAccounting.getInterfaceFileName: could not make directory:"+filepath.toString());
                return null;
            }
        }
        
        return filepath.toString();
    }   

    
    /**
     * Add the GL transaction from input parameter to specified disk file.
     * Creation date: (1/28/2003 4:34:44 PM)
     * @param intFile java.io.File
     * @param gltran java.lang.String
     */
    public static boolean addTranToDiskFile(String intFile, String gltran) {
        boolean success = false;
        FileOutputStream fileStream = null;
        PrintWriter printStream = null;
        
        try {
            fileStream = new FileOutputStream(intFile, true);  // append
            printStream = new PrintWriter(fileStream, true);  // autoflush
            printStream.println(gltran);
            success = true;
        } catch (IOException e) {
            logger.error("InterfaceAccounting.addTranToDiskFile  IOException: ", e);
            logger.error("Trying to open file named " + intFile);
        } finally {
            try {
                if (printStream != null) {
                	printStream.close();
                	printStream = null;
                }
                if (fileStream != null) {
                	fileStream.close();
                	fileStream = null;
                }
            } catch (IOException e) {
                logger.error("IO Exception in closing out streams : ", e);
            }
        }
        
        return success;
    }

    
    
    /**
		 * Add the GL transaction from input parameter to specified disk file. This
		 * routine does not include the line feed and it must be supplied from the
		 * calling routine
		 * 
		 * @param intFile
		 *          java.io.File
		 * @param gltran
		 *          java.lang.String
		 */
	public static boolean addTranToDisk(String intFile, String gltran) {
		boolean success = false;
		FileOutputStream fileStream = null;
		PrintWriter printStream = null;
	
		try {
			fileStream = new FileOutputStream(intFile, true); // append
			printStream = new PrintWriter(fileStream, true); // autoflush
			printStream.print(gltran);
			success = true;
		} catch (IOException e) {
			logger.error("InterfaceAccounting.addTranToDisk  IOException: ", e);
			logger.error("Trying to open file named " + intFile);
		} finally {
			try {
				if (printStream != null) {
					printStream.close();
					printStream = null;
				}
				if (fileStream != null) {
					fileStream.close();
					fileStream = null;
				}
			} catch (IOException e) {
				logger.error("IO Exception in closing out streams : ", e);
			}
	}

	return success;
	}

    public static String getInterfaceDescription (int fileFormat) {
    	
    	String description = "unknown";
    	
    	switch (fileFormat) {

    		case 1 :
    			description = "PeachTree";
    			break;
    		case 2 :
    			description = "BusinessWorks";
    			break;
    			
    		case 3 :
    			description = "Quick Books";
    			break;
    		case 4 :
    			description = "Quick Books New Edition";
    			break;		
    		case 5 :
    			description = "XML Format";
    			break;
    		case 6 :
    			description = "Quick Books Web Connector";
    			break;	
    		case 7 :
    			description = "ACCPAC XML";
    			break;
    		case 8 :
    			description = "Lawson";
    			break;	
    		case 9 :
    			description = "Great Plains - Memorial Estates";
    			break;
    		case 10 :
    			description = "Peoplesoft - Keystone";
    			break;
    		case 11 :
    			description = "BusinessWorks New Edition";
    			break;
    	}
    	
    	return ( description );
    }

}
