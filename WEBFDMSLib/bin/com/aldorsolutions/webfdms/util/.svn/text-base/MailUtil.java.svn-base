package com.aldorsolutions.webfdms.util;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;

public class MailUtil {

	public static void sendEmail (DbUserSession user,long configID, String emailTo, String subject, String message) throws MessagingException {
		sendEmail (user, configID, emailTo, subject, message, null);
	}
	
	public static void sendEmail (DbUserSession user,long configID, String emailTo, String subject, String message, String fromAddr) throws MessagingException {
		ArrayList <String> emailArray = new ArrayList <String> ();
		emailArray.add(emailTo);
		
		sendEmail (user, configID, emailArray, null, null, subject, message, fromAddr);
	}
	
	public static void sendEmail (DbUserSession user,long configID, ArrayList <String> emailTo, String subject, String message) throws MessagingException {
		sendEmail (user, configID, emailTo, null, null, subject, message, null);
	}
	
	public static void sendEmail (DbUserSession user,long configID, ArrayList <String> emailTo, String subject, String message, String fromAddr) throws MessagingException {
		sendEmail (user, configID, emailTo, subject, message, fromAddr);
	}
	
	public static void sendEmail (DbUserSession user,long configID, ArrayList <String> emailTo, ArrayList <String> emailCC, ArrayList <String> emailBCC, 
			String subject, String message) throws MessagingException {
		sendEmail ( user, configID, emailTo, emailCC, emailBCC, subject, message, null);
	}
	
	public static void sendEmail (DbUserSession user, long configID, ArrayList <String> emailTo, ArrayList <String> emailCC, ArrayList <String> emailBCC, 
			String subject, String message, String fromAddr) throws MessagingException {
		
		String mailHost = UtilSingleton.getInstance().getProperty(configID,"smtp.mail.host");
		String mailFrom = UtilSingleton.getInstance().getProperty(configID,"smtp.mail.from");
		
		if ( fromAddr != null ) {
			mailFrom = fromAddr;
		}
		long startTime = System.currentTimeMillis();
		MailUtil.postMail(emailTo, emailCC, emailBCC, mailHost, subject, message, mailFrom);
		
		//log it when we send out the email
		CompanyOptionsManager coMgr = new CompanyOptionsManager ();
        int emailLogOption = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_EMAIL_LOG);
        if (emailLogOption == 1) {
			EmailLogDTO emailData = new EmailLogDTO();
			EmailLogger emailLoger = new EmailLogger(user);
//			emailData = emailLoger.getLog(logids,emailName);
			long stopTime = System.currentTimeMillis();
			emailData.setStartTime(startTime);
			emailData.setStopTime(stopTime);
			emailData.setExecuteTime((int) (stopTime-startTime));
			String emails = "";
			if (emailTo != null) {
				for (int x=0; x < emailTo.size(); x++) {
					emails += emailTo.get(x);
		        	
		        	if (x+1 < emailTo.size()) {
		        		emails += ";";
		        	}
		        }
				emailData.setEmailTo(emails);
			}else {
				emailData.setEmailTo("");
			}
			emails = "";
			if (emailCC != null) {
				for (int x=0; x < emailCC.size(); x++) {
					emails += emailCC.get(x);
		        	
		        	if (x+1 < emailCC.size()) {
		        		emails += ";";
		        	}
		        }
				emailData.setEmailCC(emails);
			}else {
				emailData.setEmailCC("");
			}
			emails = "";
			if (emailBCC != null) {
				for (int x=0; x < emailBCC.size(); x++) {
					emails += emailBCC.get(x);
		        	
		        	if (x+1 < emailBCC.size()) {
		        		emails += ";";
		        	}
		        }
				emailData.setEmailBCC(emails);
			}else {
				emailData.setEmailBCC("");
			}
			emailData.setEmailSubject(subject);
			emailData.setEmailBody(message);
			emailData.setClassName("MailUtil");
			emailLoger.emailLogAll( emailData );
        }
		
	}
	
	private static void ArrayListToString(ArrayList<String> emailList, String emails){
		for (int x=0; x < emailList.size(); x++) {
			emails += emailList.get(x);
        	
        	if (x+1 < emailList.size()) {
        		emails += ";";
        	}
        }
	}
	
	private static void postMail(ArrayList <String> recipientsTo, ArrayList <String> recipientsCC, ArrayList <String> recipientsBCC, 
			String mailHost, String subject, String message, String from) throws MessagingException {
		
		boolean debug = false;

		// Set the host smtp address
		Properties props = new Properties();
		
		if ( mailHost == null ) {
			throw new MessagingException ( "mailHost is not defined" );
		}
		
		
		props.put("mail.smtp.host", mailHost);
		
		// create some properties and get the default Session
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		
		if ( recipientsTo != null ) {
			addRecipients (Message.RecipientType.TO, recipientsTo, msg);
		}
		
		if ( recipientsCC != null ) {
			addRecipients (Message.RecipientType.CC, recipientsCC, msg);
		}
		
		if ( recipientsBCC != null ) {
			addRecipients (Message.RecipientType.BCC, recipientsBCC, msg);
		}
		
		// Optional : You can also set your custom headers in the Email if you
		// Want
		// msg.addHeader("MyHeaderName", "myHeaderValue");

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
	
	private static void addRecipients (RecipientType messageType, ArrayList <String> recipients, Message msg) throws MessagingException {
		
		InternetAddress[] addressTo = new InternetAddress[recipients.size()];
		for (int i = 0; i < recipients.size(); i++) {
			if (recipients.get(i) != null && recipients.get(i).length() > 0) {
				addressTo[i] = new InternetAddress(recipients.get(i));
			}
		}
		
		msg.addRecipients(messageType, addressTo);
	}
	
}
