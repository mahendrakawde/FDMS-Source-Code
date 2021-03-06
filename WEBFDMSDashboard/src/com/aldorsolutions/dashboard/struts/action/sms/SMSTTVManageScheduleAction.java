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
import com.aldorsolutions.dashboard.struts.form.sms.SMSTTVListForm;
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
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.sms.SMSTTVRecipientDTO;
import com.aldorsolutions.webfdms.sms.dao.SMSTTVRecipientDAO;
import com.aldorsolutions.webfdms.util.DAO;
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
public class SMSTTVManageScheduleAction extends Action {
	/*
	 * Generated Methods
	 */

	private Logger logger = Logger.getLogger(SMSTTVManageScheduleAction.class.getName());
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
		
		SMSTTVListForm listForm = (SMSTTVListForm) form;
		HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        ActionMessages errors = new ActionMessages();
        
        
		if ( "Add".equals(listForm.getDirection() ) ) {
			ActionRedirect forward = new ActionRedirect (mapping.findForward("smsTTVEdit")); 	
			forward.addParameter("type", listForm.getSendType() );
			forward.addParameter("schedulingID", "0" );
			return ( forward );
//			return mapping.findForward("smsTTVEdit");

			
		} else if ("Remove".equals(listForm.getDirection() )) {

			SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
			smsScheDAo.deleteSMSScheduling(listForm.getSchedulingID());
			SecurityManagerBean smBean = new SecurityManagerBean();
	        SecurityConfigDTO security = smBean.getSecurityConfig(user.getCompanyID());
	        if (listForm.getSendType().compareToIgnoreCase("sms")==0) {
	        	security.setSmsNumberOfTime(security.getSmsNumberOfTime() + 1);
	        }else {
	        	security.setTtvNumberOfTime(security.getTtvNumberOfTime() + 1);
	        }
	        smBean.updateSecurityConfig(security, 0);
		}

		session.removeAttribute("smsTTVRecipientForm");
		if ("ttv".equalsIgnoreCase(listForm.getSendType())){
			return mapping.findForward("showSMSTTVListForTTV");
		}else {
			return mapping.findForward("showSMSTTVListForSMS");
		}
		
	}
	
}