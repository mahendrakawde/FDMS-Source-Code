package com.aldorsolutions.webfdms.accounting.bean;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.common.Constants;

/**
 * Workfile: AccountingInterfaceFactory.java
 * Date: Nov 8, 2005 11:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceFactory {
	
	private final static Logger logger = Logger.getLogger(AccountingInterfaceFactory.class.getName());
	private final static String INTERFACE_PEACHTREE_CLASS = 
    	"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfacePeachtree";
	private final static String INTERFACE_BUSINESSWORKS_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceBusinessWorks";
	private final static String INTERFACE_QUICKBOOKS_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceQuickbooks";	
	private final static String INTERFACE_QUICKBOOKS_NEW_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceQuickbooksNew";		
	private final static String INTERFACE_PEACHTREE_XML_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfacePeachtreeXML";
	private final static String INTERFACE_QUICKBOOKS_XML_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceQuickbooksXML";
	private final static String INTERFACE_ACCPAC_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceACCPAC";
    private final static String INTERFACE_LAWSON_CLASS =
        "com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceLawson";	
    private final static String INTERFACE_GREATPLAINS = "com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceGreatPlains";
    private final static String INTERFACE_PEOPLESOFT_KEYSTONE = 
    	"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfacePeoplesoftKeystone";
    private final static String INTERFACE_BUSINESSWORKS_NEW_CLASS =
		"com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceBusinessWorksNew";
	
    
	/**
	 * 
	 * @param ACCOUNTING_INTERFACE
	 * @return
	 */
	public static AccountingInterface getAccountingInterface(int ACCOUNTING_INTERFACE) {
		AccountingInterface accountingInterface = null;
		
		try {
			String accountingInterfaceClassName = 
				getAccountingInterfaceClassName(ACCOUNTING_INTERFACE);
			accountingInterface = 
				(AccountingInterface) Class.forName(accountingInterfaceClassName).newInstance();			
		} catch (Exception e) {
			logger.error("Exception in getAccountingInterface() : ", e);
		}
		
		return accountingInterface;
	}	
	
	/**
	 * 
	 * @param ACCOUNTING_INTERFACE
	 * @return
	 */
	private static String getAccountingInterfaceClassName(int ACCOUNTING_INTERFACE) {
		String className = null;
		
		switch (ACCOUNTING_INTERFACE) {
			case Constants.INTERFACE_PEACHTREE:
				className = INTERFACE_PEACHTREE_CLASS;
				break;
			case Constants.INTERFACE_BUSINESSWORKS:
				className = INTERFACE_BUSINESSWORKS_CLASS;
				break;		
			case Constants.INTERFACE_QUICKBOOKS:
				className = INTERFACE_QUICKBOOKS_CLASS;
				break;				
			case Constants.INTERFACE_QUICKBOOKS_NEW:
				className = INTERFACE_QUICKBOOKS_NEW_CLASS;
				break;					
			case Constants.INTERFACE_PEACHTREE_XML:
				className = INTERFACE_PEACHTREE_XML_CLASS;
				break;		
			case Constants.INTERFACE_QUICKBOOKS_XML:
				className = INTERFACE_QUICKBOOKS_XML_CLASS;
				break;				
			case Constants.INTERFACE_ACCPAC:
				className = INTERFACE_ACCPAC_CLASS;	
				break;
            case Constants.INTERFACE_LAWSON:
                className = INTERFACE_LAWSON_CLASS;
                break;
            case Constants.INTERFACE_GREATPLAINS:
                className = INTERFACE_GREATPLAINS;
                break;    
            case Constants.INTERFACE_PEOPLESOFT_KEYSTONE:
                className = INTERFACE_PEOPLESOFT_KEYSTONE;
                break;  
            case Constants.INTERFACE_BUSINESSWORKS_NEW:
            	className = INTERFACE_BUSINESSWORKS_NEW_CLASS;
		}
		
		return className;
	}

}
