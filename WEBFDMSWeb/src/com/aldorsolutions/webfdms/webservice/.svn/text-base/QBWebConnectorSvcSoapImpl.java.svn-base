/**
 * QBWebConnectorSvcSoapImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.aldorsolutions.webfdms.webservice;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.MessageContext;
import org.apache.axis.session.Session;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.aldorsolutions.webfdms.accounting.bean.AccountingInterface;
import com.aldorsolutions.webfdms.accounting.bean.AccountingInterfaceManagerBean;
import com.aldorsolutions.webfdms.accounting.dao.AccountingInterfaceDAO;
import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;

public class QBWebConnectorSvcSoapImpl implements com.aldorsolutions.webfdms.webservice.QBWebConnectorSvcSoap{
    
	Logger logger = Logger.getLogger(QBWebConnectorSvcSoapImpl.class);
		
	public java.lang.String clientVersion(java.lang.String qbVersion) throws java.rmi.RemoteException {

		StringBuilder evLogTxt= new StringBuilder();
			
		evLogTxt.append("WebMethod: clientVersion() has been called "); 
		evLogTxt.append("by QBWebConnectorSvcSoapImpl" + "\r\n\r\n");
		evLogTxt.append("Parameters received:\r\n");
		evLogTxt.append("string qbVersion = " + qbVersion + "\r\n");
		evLogTxt.append("\r\n");

		String retVal = null;
		
		try {
			double recommendedVersion = 1.5;
			double supportedMinVersion = 1.5;
			double suppliedVersion = Double.parseDouble(parseForVersion(qbVersion));
			
			if (suppliedVersion < recommendedVersion) {
				retVal = "W:We recommend that you upgrade your QBWebConnector";
			} else if (suppliedVersion < supportedMinVersion) {
				retVal = "E:You need to upgrade your QBWebConnector";
			}
			
			evLogTxt.append("QBWebConnector version = " + qbVersion + "\r\n");
			evLogTxt.append("Recommended Version = " + recommendedVersion + "\r\n");
			evLogTxt.append("Supported Minimum Version = " + supportedMinVersion + "\r\n");
			evLogTxt.append("SuppliedVersion = " + suppliedVersion + "\r\n");
			
		} catch ( PatternSyntaxException pse ) {
			retVal = "W:Unknown version of QB Wb Connector; Version provided: " + qbVersion;
		}
		
		logger.debug("evLogTxt: " + evLogTxt );
		
		return retVal;
    }
	
	public java.lang.String[] authenticate(java.lang.String strUserName, java.lang.String strPassword) throws java.rmi.RemoteException {
        String[] asRtn = new String[2];
	    asRtn[0] = "invalid"; //Ticket Number;
	    asRtn[1] = "nvu";

	    // asRtn[1] = ""; // use currently open company
	    // asRtn[1] = "none"; // no data to transmit
	    // asRtn[1] = "nvu"; // non valid user
	    
	    logger.debug("userName: " + strUserName + ";  password: " + strPassword );
	    
	    DbUser dbUser = null;
		dbUser = DbUser.findUserByUserName(strUserName);
		
		if ( dbUser != null ) {
			boolean validPassword = SecurityManagerBean.isValidPassword(strPassword, dbUser.getPassword());
	    	
			if (validPassword == false) {
				dbUser = null;
			}	
		}
		
		if ( dbUser == null ) {
			asRtn[1] = "nvu";
			return asRtn;
		}
		
	    asRtn[0] = strUserName + Calendar.getInstance().getTimeInMillis();
	    asRtn[1] = "";
	    
	    Session session = MessageContext.getCurrentContext().getSession();
	    session.set( asRtn[0], new QBSessionDTO (dbUser) );
	    
	    logger.debug("In authenticate as[0] = " + asRtn[0]);
	    logger.debug("In authenticate as[1] = " + asRtn[1]);
	    return asRtn;
    }

    public java.lang.String sendRequestXML(java.lang.String ticket, java.lang.String strHCPResponse, java.lang.String strCompanyFileName, java.lang.String qbXMLCountry, int qbXMLMajorVers, int qbXMLMinorVers) throws java.rmi.RemoteException {
    	Session session = MessageContext.getCurrentContext().getSession();
    	QBSessionDTO sessionDTO = (QBSessionDTO) session.get(ticket);
    	
    	if ( sessionDTO == null ) {
    		return ( null );
    	}
    	
    	AccountingInterfaceDTO aiDTO = sessionDTO.getAccountingInterfaceDTO();
    	AccountingInterfaceManagerBean aiMB = new AccountingInterfaceManagerBean();
	    
    	if ( aiDTO == null ) {
    	    aiDTO = aiMB.createAccountingInterfaceDTO( Constants.INTERFACE_QUICKBOOKS_XML, sessionDTO.getUser(), 0, 
    	    		sessionDTO.getErrors(), null, null, null, true);
    	    
    	    AccountingInterface ai = aiDTO.getAccountingInterface();
    	    ai.initializeExport(aiDTO.getFileName(), aiDTO.getInterfaceType());
    	    
    	    ArrayList <Integer> integers = aiMB.createAccountingInterfaceQBWebConnectXML(aiDTO, aiDTO.getFileName());
    	    sessionDTO.setVitalsIDs(integers);
    	    sessionDTO.setAccountingInterfaceDTO(aiDTO);
    	}
    	
    	QBVitalsRecordInfo vitalsRecord = sessionDTO.getVitalsRecord();
    	
    	if ( vitalsRecord == null || (vitalsRecord != null && vitalsRecord.hasDataToSend() == false) ) {
    		vitalsRecord = null;
        	
        	while ( vitalsRecord == null ) {
        		
        		try {
        			Integer vitalsID = sessionDTO.getNextVitalsID();
            	
        			if ( vitalsID == null ) {
        				return null;
        			}

        			vitalsRecord = aiMB.processVitalsRecordQBWebConnectXML(aiDTO, aiDTO.getFileName(), vitalsID.intValue());
        			
        		} catch ( Exception e ) {
        			logger.debug(e.getMessage(), e);
        		}
        		
        	}
        	
    		sessionDTO.setVitalsRecord(vitalsRecord);
    	} 
    	
    	String response = null;
    	
   		if ( vitalsRecord.isCustomerDataSent() == false ) {
   			response = vitalsRecord.getCustomerDataToSend();
   			vitalsRecord.setCustomerDataSent(true);
   		} else if ( vitalsRecord.isInvoiceDataSent() == false )  {
   			response = vitalsRecord.getInvoiceDataToSend();
   			vitalsRecord.setInvoiceDataSent(true);
   		} else if ( vitalsRecord.isPaymentDataSent() == false )  {
   			response = vitalsRecord.getPaymentsDataToSend();
   			vitalsRecord.setPaymentDataSent(true);
   		}
   		
	    logger.debug( "Session user: sendRequestXML; " + sessionDTO );
	    
		StringBuilder evLogTxt = new StringBuilder();
			
		evLogTxt.append("WebMethod: sendRequestXML() has been called by QBWebconnector" + "\r\n\r\n");
		evLogTxt.append("Parameters received:\r\n");
		evLogTxt.append("string ticket = " + ticket + "\r\n");
		evLogTxt.append("string strHCPResponse = " + strHCPResponse + "\r\n");
		evLogTxt.append("string strCompanyFileName = " + strCompanyFileName + "\r\n");
		evLogTxt.append("string qbXMLCountry = " + qbXMLCountry + "\r\n");
		evLogTxt.append("int qbXMLMajorVers = " + qbXMLMajorVers + "\r\n");
		evLogTxt.append("int qbXMLMinorVers = " + qbXMLMinorVers + "\r\n");
		evLogTxt.append("string MyResponse = " + response + "\r\n");
		evLogTxt.append("\r\n");
		logger.debug(evLogTxt);
		return (response);
    }

    public int receiveResponseXML(java.lang.String ticket, java.lang.String response, java.lang.String hresult, java.lang.String message) throws java.rmi.RemoteException {
    	
    	Session session = MessageContext.getCurrentContext().getSession();
    	QBSessionDTO sessionDTO = (QBSessionDTO) session.get(ticket);
    	
    	if ( sessionDTO == null ) {
    		return ( -121 );
    	}
    	
    	String evLogTxt="WebMethod: receiveResponseXML() has been called by QBWebconnector" + "\r\n\r\n";
		evLogTxt=evLogTxt+"Parameters received:\r\n";
		evLogTxt=evLogTxt+"string ticket = " + ticket + "\r\n";
		evLogTxt=evLogTxt+"string response = " + response + "\r\n";
		evLogTxt=evLogTxt+"string hresult = " + hresult + "\r\n";
		evLogTxt=evLogTxt+"string message = " + message + "\r\n";
		evLogTxt=evLogTxt+"\r\n";
		
	    AccountingInterfaceDTO aiDTO = sessionDTO.getAccountingInterfaceDTO();
	    String responseFileName = aiDTO.getFileName() + ".response.xml";

	    AccountingInterfaceUtil.addTranToDiskFile(responseFileName, response);
	    ArrayList <String> errors = null;
		int retVal=0;
		
	    try {
	    	errors = parseResponse(response);
		    sessionDTO.addQbErrors(errors);
	    } catch ( Exception e ) {
	    	retVal = -102;
	    	logger.debug("Parse Exception:", e);
	    	return ( retVal );
	    }
	    
		if(!hresult.toString().equals("")){
			// if there is an error with response received, web service could also return a -ve int		
			evLogTxt=evLogTxt+ "HRESULT = " + hresult + "\r\n";
			evLogTxt=evLogTxt+ "Message = " + message + "\r\n";
			retVal=-101;
		}
		
		evLogTxt=evLogTxt+ "Length of response received = " + response.length() + "\r\n";
		retVal=sessionDTO.getPercentageOfVitalsComplete();
		
		if ( errors != null && errors.size() > 0 ) {
		    retVal = -101;
		}
		else {
			
			QBVitalsRecordInfo vitalsRec = sessionDTO.getVitalsRecord();
			
			if ( vitalsRec.isCustomerDataSent() && vitalsRec.isCustomerDataProcessed() == false ) {
				vitalsRec.setCustomerDataProcessed(true);
			} 
			
			if ( vitalsRec.isInvoiceDataSent() && vitalsRec.isInvoiceDataProcessed() == false ) {
				AccountingInterfaceDAO aiDAO = new AccountingInterfaceDAO ();
				DbUser user = aiDTO.getDbUser();
				ArrayList <Integer> transIDs = vitalsRec.getInvoiceTransactionIds();
				
				for ( Integer tranhistID : transIDs ) {
					aiDAO.postTransaction( tranhistID.intValue(), user.getDbLookup() );
				}
				
				vitalsRec.setInvoiceDataProcessed(true);
			} 
			
			if ( vitalsRec.isPaymentDataSent() && vitalsRec.isPaymentDataProcessed() == false ) {
				AccountingInterfaceDAO aiDAO = new AccountingInterfaceDAO ();
				DbUser user = aiDTO.getDbUser();
				ArrayList <Integer> transIDs = vitalsRec.getPaymentTransactionIds();
				
				for ( Integer tranhistID : transIDs ) {
					aiDAO.postTransaction( tranhistID.intValue(), user.getDbLookup() );
				}
				
				vitalsRec.setPaymentDataProcessed(true);
			}
			
//			ArrayList <Integer> transIDs = aiDTO.getTransactionIDsToPost();
//			
//			AccountingInterfaceDAO aiDAO = new AccountingInterfaceDAO ();
//			DbUser user = aiDTO.getDbUser();
//				
//			for ( Integer tranhistID : transIDs ) {
//				aiDAO.postTransaction( tranhistID.intValue(), user.getDbLookup() );
//			}
//			
//			aiDTO.clearTransactionIDsToPost();
		}
		
		logger.debug(evLogTxt);
		return retVal;
    }

    public java.lang.String connectionError(java.lang.String ticket, java.lang.String hresult, java.lang.String message) throws java.rmi.RemoteException {
        Session session = MessageContext.getCurrentContext().getSession();
        
        if ( session.get("ce_counter") == null ) {
        	session.set("ce_counter", 0);
        }

		String evLogTxt="WebMethod: connectionError() has been called by QBWebconnector" + "\r\n\r\n";
		evLogTxt=evLogTxt+"Parameters received:\r\n";
		evLogTxt=evLogTxt+"string ticket = " + ticket + "\r\n";
		evLogTxt=evLogTxt+"string hresult = " + hresult + "\r\n";
		evLogTxt=evLogTxt+"string message = " + message + "\r\n";
		evLogTxt=evLogTxt+"\r\n";
		
		String retVal=null;
		// 0x80040400 - QuickBooks found an error when parsing the provided XML text stream. 
		final String QB_ERROR_WHEN_PARSING="0x80040400"; 
		// 0x80040401 - Could not access QuickBooks.  
		final String QB_COULDNT_ACCESS_QB="0x80040401";
		// 0x80040402 - Unexpected error. Check the qbsdklog.txt file for possible, additional information. 
		final String QB_UNEXPECTED_ERROR="0x80040402";
		// Add more as you need...

		if(hresult.trim().equals(QB_ERROR_WHEN_PARSING)){
			evLogTxt=evLogTxt+ "HRESULT = " + hresult + "\r\n";
			evLogTxt=evLogTxt+ "Message = " + message + "\r\n";
			retVal = "DONE";
		}
		else if(hresult.trim().equals(QB_COULDNT_ACCESS_QB)){
			evLogTxt=evLogTxt+ "HRESULT = " + hresult + "\r\n";
			evLogTxt=evLogTxt+ "Message = " + message + "\r\n";
			retVal = "DONE";
		}
		else if(hresult.trim().equals(QB_UNEXPECTED_ERROR)){
			evLogTxt=evLogTxt+ "HRESULT = " + hresult + "\r\n";
			evLogTxt=evLogTxt+ "Message = " + message + "\r\n";
			retVal = "DONE";
		}
		else { 
			// Depending on various hresults return different value 
//			if((int)session.get("ce_counter")==0){
//				// Try again with this company file
//				evLogTxt=evLogTxt+ "HRESULT = " + hresult + "\r\n";
//				evLogTxt=evLogTxt+ "Message = " + message + "\r\n";
//				evLogTxt=evLogTxt+ "Sending empty company file to try again.";
//				retVal = "";
//			}
//			else{
//				evLogTxt=evLogTxt+ "HRESULT = " + hresult + "\r\n";
//				evLogTxt=evLogTxt+ "Message = " + message + "\r\n";
//				evLogTxt=evLogTxt+ "Sending DONE to stop.";
//				retVal = "DONE";
//			}
		}
		evLogTxt=evLogTxt+"\r\n";
		evLogTxt=evLogTxt+"Return values: " + "\r\n";
		evLogTxt=evLogTxt+"string retVal = " + retVal + "\r\n";
		session.set("ce_counter", ((Integer) session.get("ce_counter")) + 1);
		logger.debug(evLogTxt);
		return retVal;
    }

    public java.lang.String getLastError(java.lang.String ticket) throws java.rmi.RemoteException {
    	String evLogTxt="WebMethod: getLastError() has been called by QBWebconnector" + "\r\n\r\n";
		evLogTxt=evLogTxt+"Parameters received:\r\n";
		evLogTxt=evLogTxt+"string ticket = " + ticket + "\r\n";
		evLogTxt=evLogTxt+"\r\n";
		
		StringBuilder retVal=new StringBuilder();
		Session session = MessageContext.getCurrentContext().getSession();
    	QBSessionDTO sessionDTO = (QBSessionDTO) session.get(ticket);
    	
    	if ( sessionDTO == null ) {
    		retVal.append("Session is invalid!"); 
			return ( retVal.toString() );
    	}
    	
		int errorCode=0;
		if(errorCode==-101){
			retVal.append("QuickBooks was not running!"); 
			return ( retVal.toString() );
			// This is just an example of custom user errors
		}
		
		if ( sessionDTO.hasErrors() || sessionDTO.hasQBErrors() ) {
			MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
	        MessageResources resources = messageFactory.createResources("ApplicationResources");

	        if ( sessionDTO.hasQBErrors() ) {
	        	ArrayList <String> errors = sessionDTO.getQbErrors();
	        	
	        	for ( String error : errors ) {
	        		retVal.append ( error );
					retVal.append ( "\r\n" );
	        	}
	        }
	        
	        if ( sessionDTO.hasErrors() ) {
	        	retVal.append ( "Not All Data Was Exported\r\n" );
	        	ActionErrors errors = sessionDTO.getErrors();
				Iterator index = errors.get();
				
				while ( index.hasNext() ) {
					ActionError error = (ActionError) index.next();
					retVal.append ( resources.getMessage(error.getKey(), error.getValues()) );
					retVal.append ( "\r\n" );
				}
	        }
	        
			
		}
		else{
			retVal.append("Error!");
		}
		
		evLogTxt=evLogTxt+"\r\n";
		evLogTxt=evLogTxt+"Return values: " + "\r\n";
		evLogTxt=evLogTxt+"string retVal= " + retVal + "\r\n";
		logger.debug(evLogTxt);
		return retVal.toString();
    }

    public java.lang.String closeConnection(java.lang.String ticket) throws java.rmi.RemoteException {
    	String evLogTxt="WebMethod: closeConnection() has been called by QBWebconnector" + "\r\n\r\n";
		evLogTxt=evLogTxt+"Parameters received:\r\n";
		evLogTxt=evLogTxt+"string ticket = " + ticket + "\r\n";
		evLogTxt=evLogTxt+"\r\n";
		String retVal=null;

		retVal="OK";

		evLogTxt=evLogTxt+"\r\n";
		evLogTxt=evLogTxt+"Return values: " + "\r\n";
		evLogTxt=evLogTxt+"string retVal= " + retVal + "\r\n";
		logger.debug(evLogTxt);
		Session session = MessageContext.getCurrentContext().getSession();
    	session.set(ticket, null);
    	
		return retVal;
    }
    
    private ArrayList <String> parseResponse (String response) throws Exception {
    	    	
    	ArrayList <String> errors = new ArrayList <String> ();
    	
   		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		// responseFile = new File(responseFileName);
		
		Document doc = docBuilder.parse(new InputSource(new StringReader(response)));
		Element docEle = doc.getDocumentElement();

		// get a nodelist of elements
		NodeList invoiceNodes = docEle.getElementsByTagName("InvoiceAddRs");
		NodeList paymentNodes = docEle.getElementsByTagName("ReceivePaymentAddRs");
		// NodeList customerNodes = docEle.getElementsByTagName("CustomerAddRs");

		// errors.addAll( getErrors(customerNodes) );
		errors.addAll(getErrors(invoiceNodes));
		errors.addAll(getErrors(paymentNodes));
    	return ( errors );
    }
    
    /**
	 * I take an employee element and read the values in, create an Employee
	 * object and return it
	 */
	private ArrayList <String> getErrors(NodeList sections) {
		int numSections = sections.getLength();
		ArrayList <String> errors = new ArrayList <String> ();
		
		for (int i = 0; i < numSections; i++) {
			Element section = (Element) sections.item(i); 
			NamedNodeMap nnm = section.getAttributes();
			Node statusCode = nnm.getNamedItem("statusCode");
			
			int statusError = getIntValue(statusCode.getNodeValue());
			
			if ( statusError > 0 ) {
				
//				if ( statusError != 3140 ) {
					Node statusMessage = nnm.getNamedItem("statusMessage");
					errors.add(statusMessage.getNodeValue());
//				}
			}
		}
		
		return ( errors );
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * returns a int value
	 */
	private int getIntValue(String value) {
		
		int intValue = 0;
		
		try {
			if ( value != null && value.length() > 0 ) {
				intValue = Integer.parseInt(value);
			}
		} catch ( NumberFormatException nfe ) {
			nfe.printStackTrace();
		}
		
		return ( intValue );
	}

	private String parseForVersion(String input){
		// This method is created just to parse the first two version components
		// out of the standard four component version number:
		// <Major>.<Minor>.<Release>.<Build>
		// 
		// As long as you get the version in right format, you could use
		// any algorithm here.
		// 
		String retVal="";
		Pattern pattern = Pattern.compile("^[0-9]+\\.[0-9]+");
		Matcher match = pattern.matcher(input);
		
		boolean found = false;
		while (match.find()) {
			found = true;
			retVal += match.group();
		}
		
		if ( found == false ) {
			retVal=input;
		}
		
		return retVal;
	}
	
}