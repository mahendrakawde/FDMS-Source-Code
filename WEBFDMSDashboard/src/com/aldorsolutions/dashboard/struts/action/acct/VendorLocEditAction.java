/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.action.acct;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorsolutions.dashboard.struts.form.acct.VendorLocEditForm;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


/** 
 * MyEclipse Struts
 * Creation date: 06-28-2007
 * 
 * XDoclet definition:
 * @struts.action path="/vendorLocEdit" name="vendorLocEditForm" input="/acct/vendorLocEdit.jsp" scope="request" validate="true"
 */
public class VendorLocEditAction extends Action {
	/*
	 * Generated Methods
	 */

	private Logger logger = Logger.getLogger(AcctVendorsEditAction.class.getName());	
	
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
		VendorLocEditForm vendorLocEditForm = (VendorLocEditForm) form;
		
		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);

		ApVendorLocationDAO vendorLocDao = new ApVendorLocationDAO(user);
		String vendorLocationIDStr = request.getParameter("vendorLocationID");
		ActionMessages errors = new ActionMessages();

		long vendorLocID = FormatNumber.parseLong(vendorLocationIDStr);

		if (vendorLocID <= 0) {
			String missingLoc = getResources(request).getMessage("error.acct.vendor.missingVendor");

			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", missingLoc));
			saveErrors(request, errors);

			return (mapping.findForward("showVendorEdit"));
		}
		
		
		ApVendorLocationDTO vendorLoc = vendorLocDao.getApVendorLocation(vendorLocID);
		ArrayList <UserLocationDTO> locations = (ArrayList) session.getAttribute("ADMIN_LOCATIONS");
		UserManagerBean uMgr = new UserManagerBean();
		
		long apAcctID = 0;
		try {
			InvoiceManager imvMgr = new InvoiceManager();
			//DbApAccount account = imvMgr.getAccount(request, acctEditVendorsForm.getApAccountID());
			ArrayList <DbApAccount> accounts = imvMgr.getAccountList(request, user.getLocationId(),vendorLoc.getLocaleID());
			//vendor.setDefaultAcct(FormatNumber.parseInteger(account.getAccountNumber()));
			//private long apAccountID;

//			for ( DbApAccount account : accounts ) {
//        		String Id = account.getAccountNumber();
//        		String IdDefaultAcct = vendorLoc.getDefaultAcct();
//        		if ( IdDefaultAcct.compareToIgnoreCase(Id) == 0  ) {
//        			apAcctID = account.getId();
//        			break;
//        		}
//        	}
			
		} catch ( Exception e ) {
			logger.debug("Exception: ", e);
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
			saveErrors(request, errors);
		}			
		
		
		//acctEditVendorsForm.setApAccountID(apAcctID);			
		//vendorLocEditForm.setDefaultAcct(vendorLoc.getDefaultAcct());
		//vendorLocEditForm.setDefaultAcct(apAcctID);
		//vendorLocEditForm.setDefaultAcctID(vendorLoc.getDefaultAcctID());
		vendorLocEditForm.setLocaleID(vendorLoc.getLocaleID());
		vendorLocEditForm.setLocationID(vendorLoc.getLocationID());
		vendorLocEditForm.setVendorID(vendorLoc.getVendorID());
		vendorLocEditForm.setVendorLocationID(vendorLoc.getVendorLocationID());
		
		String [] locationIDs = { String.valueOf(vendorLocEditForm.getLocationID()) }; 
			
    	String js = uMgr.createLocaleJavascript ( locations, locationIDs );
    	vendorLocEditForm.setLocaleMapJavascript(js);
    	
		return ( mapping.findForward("showLocEdit") );
	}
}