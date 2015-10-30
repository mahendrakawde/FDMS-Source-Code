/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 * CJongs
 */
package com.aldorsolutions.webfdms.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.model.ReportSchedulingDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.reporting.dao.ReportSchedulingDAO;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.util.CrystalReportManagerBeanScheduling;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.UtilSingleton;
import com.eclerisy.primemessage.common.PMConstant;
import com.eclerisy.primemessage.sdk.PrimeMessage;

/**
 * MyEclipse Struts Creation date: 04-17-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="failed" path="/login.jsp"
 * @struts.action-forward name="success" path="/main.jsp"
 */

public class SMSCommonGenerator extends TimerTask {
	/*
	 * Generated Methods
	 */

	public static CompanyDTO COMPANYINFO;
	public static int ScheID;

	public SMSCommonGenerator(CompanyDTO company, int scheduleID) {
		super();
		COMPANYINFO = company;
		ScheID = scheduleID;
	}

	public void run() {
		String pageName = "";

		CompanyDTO company = COMPANYINFO;

		int scheduleID = ScheID;

		SMSSchedulingDTO smsSche = new SMSSchedulingDTO();
		SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
		smsSche = smsScheDAo.getSMSScheduling(scheduleID);

		if ((smsSche != null) && ( (smsSche.getStatus().compareToIgnoreCase("Q") == 0) || (smsSche.getStatus().compareToIgnoreCase("R") == 0)) ) {

			// get in to run the scheduling we need to update Q to R and set the starting time
			smsSche.setStatus("R");
			long startingTimestamp = System.currentTimeMillis();
			smsSche.setStartDateTime(startingTimestamp);
			try {
				smsScheDAo.updateSMSCommonScheduling(smsSche);
			}catch (Exception pe) {
					pe.printStackTrace();
			}
			//
//			   sendSMS(smsSche,company);
			 try{
			PrimeMessage pm = new PrimeMessage("pmgateway.net");
        	pm.setDeliveryType(0);
        	pm.setCustomerAbbrev("CJONGS");
        	String msg = "";
    		msg = smsScheDAo.getSQLMessage(smsSche.getSqlScript(), smsSche.getDbLookup());
    		
        	String message = msg;
        	message = smsSche.getStartMessage()+" "+message+" "+smsSche.getEndMessage();
        	pm.setMessageBody(message);
        	pm.addRecipient(smsSche.getFirstname(), smsSche.getLastname(), Integer.parseInt(smsSche.getCountryCode()), Integer.parseInt(smsSche.getAreaCode()), Integer.parseInt(smsSche.getPhone()));
        	
        	pm.sendMessage("cjongs", "cj0ng5");
        	int status = pm.getOperationStatus();
        	if (PMConstant.STATUS_SUCCESS != status){
        		String msg1 = pm.getOperationErrorMessage();
        	}
        	smsSche.setSentMessage(message);
			}catch (Exception e){
		        	
		    }
				//we finish up the task.
				
				smsSche.setStatus("S");
				long createdTimestamp = System.currentTimeMillis();
				smsSche.setStopDateTime(createdTimestamp);
				
					
					
			}else {
				smsSche.setStatus("E");
				long createdTimestamp = System.currentTimeMillis();
				smsSche.setStopDateTime(createdTimestamp);
			}
			
			try {
				smsScheDAo.updateSMSCommonScheduling(smsSche);
			}catch (Exception pe) {
	
			}
			
	}

	private void sendSMS(SMSSchedulingDTO smsSche, CompanyDTO company) {
	      try{
	        	PrimeMessage pm = new PrimeMessage("pmgateway.net");
	        	
	        	// we keep this one for the requirement. www.celltrust.com, login: cjongs, password: cj0ng5
//	        	pm.setDeliveryType(0); // 0 for SMS and 1 for email.
//	        	pm.setCustomerAbbrev("CJONGS"); // set in the celltrust on  mygateway, keyword management.-- This one is mean from whom sending sms.
//	        	pm.setMessageBody("Hello"); // message
//	        	pm.addRecipient("chai", "jongs", 1,940, 5942322); 
//	        	pm.sendMessage("cjongs", "cj0ng5"); // user and password
	        	
	        	
	        	pm.setDeliveryType(0);
	        	pm.setCustomerAbbrev("CJONGS");
	        	String message = getMessage(smsSche, company);
	        	message = smsSche.getStartMessage()+" "+message+" "+smsSche.getEndMessage();
	        	pm.setMessageBody(message);
	        	pm.addRecipient(smsSche.getFirstname(), smsSche.getLastname(), Integer.parseInt(smsSche.getCountryCode()), Integer.parseInt(smsSche.getAreaCode()), Integer.parseInt(smsSche.getPhone()));
	        	
	        	pm.sendMessage("cjongs", "cj0ng5");
	        	int status = pm.getOperationStatus();
	        	String msg = "";
	        	if (PMConstant.STATUS_SUCCESS != status){
	        		msg = pm.getOperationErrorMessage();
	        	}
	        	smsSche.setSentMessage(message);
	        }catch (Exception e){
	        	
	        }
		
	}

	private String getMessage(SMSSchedulingDTO smsSche, CompanyDTO company) {
		String msg = "";
		SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(company.getDbLookup());
		msg = smsScheDAo.getSQLMessage(smsSche.getSqlScript());
		
		return msg;
	}
			
	
}