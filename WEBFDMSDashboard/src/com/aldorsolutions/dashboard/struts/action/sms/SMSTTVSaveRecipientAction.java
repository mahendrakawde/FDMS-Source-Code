/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 * CJongs
 */
package com.aldorsolutions.dashboard.struts.action.sms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;

import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorsolutions.dashboard.struts.form.acct.AcctEditVendorsForm;
import com.aldorsolutions.dashboard.struts.form.acct.InvoiceEditDisplayForm;
import com.aldorsolutions.dashboard.struts.form.acct.VendorLocRemoveForm;
import com.aldorsolutions.dashboard.struts.form.sms.SMSTTVRecipientForm;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDisplayDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.sms.SMSTTVRecipientDTO;
import com.aldorsolutions.webfdms.sms.dao.SMSTTVRecipientDAO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


/** 
 * MyEclipse Struts
 * Creation date: 06-25-2007
 * 
 * XDoclet definition:
 * @struts.action path="/acctSaveVendors" name="acctEditVendorsForm" input="/acct/acctEditVendors.jsp" scope="request" validate="true"
 * @struts.action-forward name="vendorList" path="/acct/acctListVendors.jsp" redirect="true"
 */
public class SMSTTVSaveRecipientAction extends Action {
	/*
	 * Generated Methods
	 */

	private Logger logger = Logger.getLogger(SMSTTVSaveRecipientAction.class.getName());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		SMSTTVRecipientForm recipientForm = (SMSTTVRecipientForm) form;
		HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        ActionMessages errors = new ActionMessages();
        String type = recipientForm.getType();
        
		if ( "Add".equals(recipientForm.getDirection() ) ) {

			if ((recipientForm.getFirstname().trim().length()>0)
				&&	(recipientForm.getLastname().trim().length()>0)
				&&	(recipientForm.getAreacode().trim().length()==3)
				&&	(recipientForm.getPhone().trim().length()==7)){
						SMSTTVRecipientDTO recipient = new SMSTTVRecipientDTO();
						recipient.setFirstname(recipientForm.getFirstname());
						recipient.setLastname(recipientForm.getLastname());
						recipient.setAreacode(recipientForm.getAreacode());
						recipient.setPhone(recipientForm.getPhone());
						recipient.setGreeting(recipientForm.getGreeting());
						recipient.setType(type);
						SMSTTVRecipientDAO recipientDao = new SMSTTVRecipientDAO(user);
						try {
							recipientDao.addSMSTTVRecipient(recipient);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
			}
			
		} else if ("Remove".equals(recipientForm.getDirection() )) {
			int id = Integer.valueOf(recipientForm.getId());
			if (id > 0){
				SMSTTVRecipientDAO recipientDao = new SMSTTVRecipientDAO(user);
				recipientDao.deleteSMSTTVRecipient(id);
			}else{
                SecurityManagerBean smBean = new SecurityManagerBean();
                SecurityConfigDTO security = smBean.getSecurityConfig(user.getCompanyID());
                boolean success = false;
                
            	if ("sms".compareToIgnoreCase(type)==0){
            		security.setSmsFirstname("");
            		security.setSmsLastname("");
            		security.setSmsAreaCode("");
            		security.setSmsPhone("");
            		security.setSmsGreeting("");
            		success = smBean.updateSecurityConfig(security, 0);
            	}else {
            		security.setTtvFirstname("");
            		security.setTtvLastname("");
            		security.setTtvAreaCode("");
            		security.setTtvPhone("");
            		security.setTtvGreeting("");
            		success = smBean.updateSecurityConfig(security, 0);
            	}
			}
		}
		
		
		
		
		session.removeAttribute("smsTTVRecipientForm");
		if ("ttv".equalsIgnoreCase(type)){
			return mapping.findForward("showSMSTTVRecipientEditForTTV");
		}else {
			return mapping.findForward("showSMSTTVRecipientEditForSMS");
		}
		
	}
	
}