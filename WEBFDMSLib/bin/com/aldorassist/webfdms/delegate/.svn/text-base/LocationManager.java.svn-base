package com.aldorassist.webfdms.delegate;

import java.util.ArrayList;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.dao.LocationEmailDAO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.LocationEmailDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserVacationDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.MailUtil;

public class LocationManager {
	private Logger logger = Logger.getLogger(LocationManager.class.getName());
    
	public void sendLocationEmails (DbUserSession user, long locationID, String subject, String message) throws Exception {
		
		UserManagerBean userMgr = new UserManagerBean();
		
		ArrayList <String> emailTo = new ArrayList <String> ();
		ArrayList <String> emailCC = new ArrayList <String> ();
		ArrayList <String> emailBCC = new ArrayList <String> ();
 		
		LocationDAO locDAO = new LocationDAO(user);
		LocationEmailDAO locEmailDAO = new LocationEmailDAO(user);
		LocationDTO location = locDAO.getLocation(locationID);
		ArrayList <LocationEmailDTO> locEmails = locEmailDAO.getLocationEmailByLocation(locationID);
		
		long acctUserID = location.getAccountantUserID();
		
		if ( acctUserID > 0 ) {
			UserDTO acctUser = userMgr.getUser(acctUserID);
			emailTo.add( acctUser.getEmail() );
		}
		
		ArrayList <UserVacationDTO> userVacations = userMgr.getUserVacations(acctUserID);
		
		if ( userVacations != null && userVacations.size() > 0 ) {
			
			for ( UserVacationDTO uVac: userVacations ) {
				UserDTO vacUser = userMgr.getUser(uVac.getNotifyUserID());
				emailTo.add( vacUser.getEmail() );
			}
		}
		
		if ( locEmails != null && locEmails.size() > 0 ) {
			
			for ( LocationEmailDTO locEmail: locEmails ) {
				
				long emailType = locEmail.getEmailType();
				
				if ( emailType == LocationEmailDTO.EMAIL_TYPE_TO ) {
					emailTo.add(locEmail.getEmailAddress());
				} else if ( emailType == LocationEmailDTO.EMAIL_TYPE_CC ) {
					emailCC.add(locEmail.getEmailAddress());
				} else if ( emailType == LocationEmailDTO.EMAIL_TYPE_BCC ) {
					emailBCC.add(locEmail.getEmailAddress());
				}
				
			}
		}
		
		//emailBCC.add("cjongs@aldorsolutions.com");
		emailBCC.add("bshah@aldorsolutions.com");
		
		try {
			MailUtil.sendEmail(user, user.getConfigID(), emailTo, emailCC, emailBCC, subject, message);
		} catch ( MessagingException me ) {
			logger.info ( "Problem with emails; Email To:" + emailTo.toArray() + 
					" Email CC: " + emailCC.toArray() +
					" Email BCC: " + emailBCC.toArray(), me );
		}
		
	}
	
	public void sendLocationEmailsByInvoiceStatus (DbUserSession user, long locationID, String subject, String message, String invoiceStatus) throws Exception {
		
//		UserManagerBean userMgr = new UserManagerBean();
		
		ArrayList <String> emailTo = new ArrayList <String> ();
		ArrayList <String> emailCC = new ArrayList <String> ();
		ArrayList <String> emailBCC = new ArrayList <String> ();
		InvoiceDTO invoice = new InvoiceDTO();
		// this invoice got submited so send the email to accountant by using locationemail table
		
		if (invoiceStatus.compareToIgnoreCase(invoice.INVOICE_SUBMIT)==0 ){ 
//			LocationDAO locDAO = new LocationDAO(user);
			LocationEmailDAO locEmailDAO = new LocationEmailDAO(user);
//			LocationDTO location = locDAO.getLocation(locationID);
			ArrayList <LocationEmailDTO> locEmails = locEmailDAO.getLocationEmailByLocation(locationID);
			
//			long acctUserID = location.getAccountantUserID();
//			
//			if ( acctUserID > 0 ) {
//				UserDTO acctUser = userMgr.getUser(acctUserID);
//				emailTo.add( acctUser.getEmail() );
//			}
//			
//			ArrayList <UserVacationDTO> userVacations = userMgr.getUserVacations(acctUserID);
//			
//			if ( userVacations != null && userVacations.size() > 0 ) {
//				
//				for ( UserVacationDTO uVac: userVacations ) {
//					UserDTO vacUser = userMgr.getUser(uVac.getNotifyUserID());
//					emailTo.add( vacUser.getEmail() );
//				}
//			}
			
			if ( locEmails != null && locEmails.size() > 0 ) {
				
				for ( LocationEmailDTO locEmail: locEmails ) {
					
					long emailType = locEmail.getEmailType();
					
					if ( emailType == LocationEmailDTO.EMAIL_TYPE_TO ) {
						emailTo.add(locEmail.getEmailAddress());
					} else if ( emailType == LocationEmailDTO.EMAIL_TYPE_CC ) {
						emailCC.add(locEmail.getEmailAddress());
					} else if ( emailType == LocationEmailDTO.EMAIL_TYPE_BCC ) {
						emailBCC.add(locEmail.getEmailAddress());
					}
					
				}
			}
			
			//emailBCC.add("cjongs@aldorsolutions.com");
		
		}
		else { // this for sending the email to user for other status (Approved, Denied) 
			//emailTo.add("cjongs@aldorsolutions.com");
			
	        //ArrayList <UserLocationDTO> usersByLocation = new ArrayList <UserLocationDTO> ();
            UserDAO userDao = new UserDAO();
            ArrayList  <UserLocationDTO> usersByLocation  = userDao.getUsersByLocation(locationID);
            
			ArrayList <UserDTO> users = userDao.getUsersByRoleAssigned(1 , user.getCompanyID(), 2);		
			
			for ( UserDTO aUser : users) {
				for ( UserLocationDTO userByLocation : usersByLocation) {
					if (aUser.getUserId() == userByLocation.getUser_id()){
						if (aUser.getEmail().length()>6){
							emailTo.add(aUser.getEmail());
						}
					}
				}	
			}
			
		}
		
		try {
			MailUtil.sendEmail(user, user.getConfigID(), emailTo, emailCC, emailBCC, subject, message);
		} catch ( MessagingException me ) {
			logger.info ( "Problem with emails; Email To:" + emailTo.toArray() + 
					" Email CC: " + emailCC.toArray() +
					" Email BCC: " + emailBCC.toArray(), me );
		}
		
	}
}
