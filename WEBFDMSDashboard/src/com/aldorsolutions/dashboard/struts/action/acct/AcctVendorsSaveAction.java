/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 * CJongs
 */
package com.aldorsolutions.dashboard.struts.action.acct;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
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
public class AcctVendorsSaveAction extends Action {
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
		
		AcctEditVendorsForm acctEditVendorsForm = (AcctEditVendorsForm) form;
		HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        ActionMessages errors = new ActionMessages();
		if ( "Cancel".equals(acctEditVendorsForm.getSubmitType() ) ) {
			//session.removeAttribute("acctListVendorsSearchForm");
			//ActionForward fwd = mapping.findForward("vendorListSearch");
			//return new ActionForward(mapping.getInput());	
			return mapping.findForward("vendorListSearch");	
		} else if ( "Delete".equals(acctEditVendorsForm.getSubmitType()) || "Inactive".equals(acctEditVendorsForm.getSubmitType()) ) {
			long vendorID = acctEditVendorsForm.getVendorID();
			if ( vendorID <=0 ) {
				
				String missingLoc = getResources(request).getMessage("error.acct.vendor.missingVendor");
				
				errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", missingLoc) );
				saveErrors(request, errors);
				
				return ( mapping.findForward("vendorEdit") );
			}
			else {
				ApVendorDAO vendorDao = new ApVendorDAO(user);
				 ApVendorDTO vendor = new ApVendorDTO();
				 
				 vendor = vendorDao.getApVendor(acctEditVendorsForm.getVendorID());
				 vendor.setDeleteCode("D");
				 vendorDao.updateApVendor(vendor);
				 
				
			}
			session.removeAttribute("acctListVendorsSearchForm");
			session.removeAttribute("acctEditVendorsForm");
			return mapping.findForward("vendorListSearch");
		}
		
		
		
		
		if ( "Add Location".equals(acctEditVendorsForm.getSubmitType()) ) {
			
			try {	
				saveLocation ( acctEditVendorsForm, user, session, request, errors ); 
			}
			 catch ( Exception e ) {
					logger.debug(e.getMessage(), e);
			}
			return mapping.getInputForward();
		}
		
		if ( "Remove Location".equals(acctEditVendorsForm.getDirection()) ) {
			
			try {	
				String vendorLocationIDStr = acctEditVendorsForm.getRemoveVendorLocationID();
				long vendorLocID = FormatNumber.parseLong(vendorLocationIDStr);
				removeLocation ( acctEditVendorsForm, user, session, request, errors, vendorLocID ); 
			}
			 catch ( Exception e ) {
					logger.debug(e.getMessage(), e);
			}
			//return mapping.getInputForward();
			
			ActionRedirect forward = new ActionRedirect (mapping.findForward("showVendorEdit")); 	
			forward.addParameter("vendorID", String.valueOf(acctEditVendorsForm.getVendorID()) );
			return ( forward );
		}
		
		//ApVendorDAO vendorDao = new ApVendorDAO(user);
		long vendorID = acctEditVendorsForm.getVendorID();
		
		
		if ( vendorID <0 ) {
			String missingLoc = getResources(request).getMessage("error.acct.vendor.missingVendor");
			
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", missingLoc) );
			saveErrors(request, errors);
			
			return ( mapping.findForward("vendorEdit") );
		}
		    
		if (vendorID == 0) {
			try {	
				saveLocation ( acctEditVendorsForm, user, session, request, errors ); 
			}
			 catch ( Exception e ) {
					logger.debug(e.getMessage(), e);
			}
		}
		else {
			try {
				saveVendor(acctEditVendorsForm, user, session, request, errors);
			}
			 catch ( Exception e ) {
					logger.debug(e.getMessage(), e);
			}
		}
		if (!errors.isEmpty()){
			//return ( mapping.findForward("vendorEdit") );
			ActionForward fwd = mapping.findForward("vendorEdit");
			return new ActionForward(mapping.getInput());
		}
		
		session.removeAttribute("acctListVendorsSearchForm");
		session.removeAttribute("acctEditVendorsForm");
		return mapping.findForward("vendorListSearch");
		//ActionForward fwd = mapping.findForward("vendorListSearch");
		//return new ActionForward(mapping.getInput());
	}
	
	private void saveVendor (AcctEditVendorsForm acctEditVendorsForm, DbUserSession user, 
			HttpSession session, HttpServletRequest request,ActionMessages errors  ) throws Exception {
		
		ApVendorDAO vendorDao = new ApVendorDAO(user);
		//long vendorID = acctEditVendorsForm.getVendorID();	
		
		boolean duplicateCode = false;
		duplicateCode = checkVendorCode(user, acctEditVendorsForm);
		boolean containQuote = false;
	    containQuote = (acctEditVendorsForm.getName().contains("\'") || acctEditVendorsForm.getName().contains("\""));
	    if ( !duplicateCode && !containQuote) {
	    	

	    ApVendorDTO vendor = new ApVendorDTO();
	    
		  vendor.setVendorCode(acctEditVendorsForm.getVendorCode());
			vendor.setAddr1(acctEditVendorsForm.getAddr1());
			vendor.setAddr2(acctEditVendorsForm.getAddr2());
			vendor.setCityState(acctEditVendorsForm.getCityState());
			vendor.setVendorState(acctEditVendorsForm.getVendorState());	
			vendor.setVendorCountry(acctEditVendorsForm.getVendorCountry());			
			vendor.setContactName(acctEditVendorsForm.getContactName());
			vendor.setDeleteCode(acctEditVendorsForm.getDeleteCode());
			vendor.setEmailAddr(acctEditVendorsForm.getEmailAddr());
			vendor.setName(acctEditVendorsForm.getName());
			vendor.setNotes(acctEditVendorsForm.getNotes());
			vendor.setPhone(acctEditVendorsForm.getPhone());
			vendor.setPhone2(acctEditVendorsForm.getPhone2());		
			vendor.setPostalCode(acctEditVendorsForm.getPostalCode());
			//vendor.setDefaultAcct(FormatNumber.parseInteger(acctEditVendorsForm.getDefaultAcct()));
			vendor.setLocaleID(acctEditVendorsForm.getLocaleID());
			vendor.setLocationID(acctEditVendorsForm.getLocationID());
		
			
			vendor.setVendorID(acctEditVendorsForm.getVendorID());
			vendor.setInternalVendor( Boolean.parseBoolean(acctEditVendorsForm.getInternalVendor()) );
			
			vendor.setFax(acctEditVendorsForm.getFax());
			vendor.setAccountNumber(acctEditVendorsForm.getAccountNumber());
			vendor.setDiscountPercentage(acctEditVendorsForm.getDiscountPercentage());
			//vendor.setDiscountIfInDays(acctEditVendorsForm.getDiscountIfInDays());
			//vendor.setDiscountDueInDays(acctEditVendorsForm.getDiscountDueInDays());
			vendor.setDiscountIfInDays(0);
			vendor.setDiscountDueInDays(0);
			vendor.setVendor1099Type(acctEditVendorsForm.getVendor1099Type());
			//vendor.setVendor1099Payment(acctEditVendorsForm.getVendor1099Payment());
			float initValue = 0;
			vendor.setVendor1099Payment(initValue);
			vendor.setTaxID(acctEditVendorsForm.getTaxID());
			vendor.setApprovedVendor(acctEditVendorsForm.getApprovedVendor());
			vendor.setCreditLimit(acctEditVendorsForm.getCreditLimit());
			vendor.setDefaultAcctID((int) acctEditVendorsForm.getApAccountID());


		    if (vendor.getVendorID() == 0){
				long localeID = 0;
				long locationID = 0;		
				if ( acctEditVendorsForm.getLocaleIds().length > 0 ) {
					localeID = FormatNumber.parseLong(acctEditVendorsForm.getLocaleIds()[0]);
				}
				
				if ( acctEditVendorsForm.getLocationIds().length > 0 ) {
					locationID = FormatNumber.parseLong(acctEditVendorsForm.getLocationIds()[0]);
				}			
				vendor.setLocaleID(localeID);
				vendor.setLocationID(locationID);			    	
		       vendorDao.addApVendor(vendor);	
		    }
		    else {
				vendorDao.updateApVendor(vendor);	
				
				// update each apvendorlocations to have the same defAcctID
		        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO(user);
		        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByVendorID(vendor.getVendorID());
		        for (ApVendorLocationDTO vendorLocForUser : vendorLocs) {
		        	vendorLocForUser.setDefaultAcctID(vendor.getDefaultAcctID());
		        	vendorLocDAO.updateApVendorLocation(vendorLocForUser);
		    	  }	  
		    }	
		    
	    } else {
			
	    	if (duplicateCode) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.acct.vendor.DuplicateCode", "") );
			}
			if (containQuote) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.acct.vendor.Name"));
			}
			saveErrors(request, errors);	
	}       
		    
	}
	
	private void saveLocation (AcctEditVendorsForm acctEditVendorsForm, DbUserSession user, 
			HttpSession session, HttpServletRequest request, ActionMessages errors ) throws Exception {
		
		ArrayList vendorLocs = (ArrayList) session.getAttribute("ADMIN_VENDOR_LOCATIONS");
		ArrayList <UserLocaleDTO> locales = (ArrayList) session.getAttribute("ADMIN_LOCALES");
		ArrayList <UserLocationDTO> locations = (ArrayList) session.getAttribute("ADMIN_LOCATIONS");	
	    	
		long localeID = -1;
		long locationID = -1;	
		
		//long defaultAcctID = FormatNumber.parseLong(acctEditVendorsForm.getNewDefaultAcctNum());
		
		if ( acctEditVendorsForm.getLocaleIds().length > 0 ) {
			localeID = FormatNumber.parseLong(acctEditVendorsForm.getLocaleIds()[0]);
		}
		
		if ( acctEditVendorsForm.getLocationIds().length > 0 ) {
			locationID = FormatNumber.parseLong(acctEditVendorsForm.getLocationIds()[0]);
		}
		
		ApVendorLocationDTO vendorLoc = new ApVendorLocationDTO();
		
		ApVendorLocationDAO vendorLocDao = new ApVendorLocationDAO(user);
		ArrayList<ApVendorLocationDTO> locationlist = new ArrayList <ApVendorLocationDTO> (); 
			locationlist = vendorLocDao.getApVendorLocationByVendorID(acctEditVendorsForm.getVendorID());
		
		boolean hasSet = false;
		if (acctEditVendorsForm.getVendorID() != 0){
			for (ApVendorLocationDTO location: locationlist ) {
				if (location.getLocaleID() == localeID && location.getLocationID() == locationID) {
					hasSet = true;
					break;
				}
			}
		}
		
		boolean duplicateCode = false;
		duplicateCode = checkVendorCode(user, acctEditVendorsForm);
		boolean containQuote = false;
	    containQuote = (acctEditVendorsForm.getName().contains("\'") || acctEditVendorsForm.getName().contains("\""));
	        
		

		//vendorLoc.setDefaultAcct(vendorLocEditForm.getDefaultAcct());
		// if it is not duplicate locale, location or code.
		if (!hasSet && !duplicateCode && !containQuote) {		
		
			ApVendorDAO vendorDao = new ApVendorDAO(user);
			if (acctEditVendorsForm.getVendorID() == 0){
				
				//save the vendor either new vendor or edit it.
				try {
					saveVendor(acctEditVendorsForm, user, session, request, errors);
				}
				 catch ( Exception e ) {
						logger.debug(e.getMessage(), e);
				}				
				//ArrayList <ApVendorDTO> vendors = vendorDao.getApVendorByName(true,acctEditVendorsForm.getName());
				ArrayList <ApVendorDTO> vendors = vendorDao.getApVendorByCode(true,acctEditVendorsForm.getVendorCode());
				long vendorID = 0;
				for (ApVendorDTO vendor : vendors) {
					vendorID = vendor.getVendorID();
				}
				vendorLoc.setVendorID(vendorID);
			}
			else {
			        vendorLoc.setVendorID(acctEditVendorsForm.getVendorID());	
			}	
		
	        vendorLoc.setDefaultAcctID((int) acctEditVendorsForm.getApAccountID());
	        vendorLoc.setLocaleID(localeID);
	        vendorLoc.setLocationID(locationID);
	        vendorLoc.setVendorLocationID(0);
	    
			ApVendorLocationDisplayDTO display = new ApVendorLocationDisplayDTO();
	    	display.setVendorLocationID( vendorLoc.getVendorLocationID() );
	    	
	    	InvoiceManager imvMgr = new InvoiceManager();
	    	DbApAccount account = imvMgr.getAccount(request, vendorLoc.getDefaultAcctID());
	    	
	    	if (account == null) {
	    		display.setDefaultAcct( "");
	    	}else {
	    		display.setDefaultAcct( account.getAccountNumber() );
	    	}
	
	    	display.setLocaleName( String.valueOf(vendorLoc.getLocaleID()) );
	    	display.setLocationName( String.valueOf(vendorLoc.getLocationID()) );
	    	  	
	    	for ( UserLocationDTO loc : locations ) {
	    		long locID = loc.getLocationId();
	    		if ( locID == vendorLoc.getLocationID() ) {
	    			display.setLocationName(loc.getName());
	    			break;
	    		}
	    	}
	    	
	    	for ( UserLocaleDTO loc : locales ) {
	    		long locID = FormatNumber.parseLong(loc.getLocaleId());
	    		if ( locID == vendorLoc.getLocaleID() ) {
	    			display.setLocaleName(loc.getName());
	    			break;
	    		}
	    	}
	    	
	    	//This is for preset the default account Number to the default of vendor for all location
			try {
				//InvoiceManager imvMgr = new InvoiceManager();
				
				account = imvMgr.getAccount(request, acctEditVendorsForm.getApAccountID());
				vendorLoc.setDefaultAcctID((int) acctEditVendorsForm.getApAccountID());
				if (account == null) {
					display.setDefaultAcct("");
				}
				else {
					display.setDefaultAcct(account.getAccountNumber());
				}
			
			} catch ( Exception e ) {
				logger.debug("Exception: ", e);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
				saveErrors(request, errors);
			}       	
	    	
	        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO (user);
	        vendorLocDAO.addApVendorLocation(vendorLoc);  
	        ApVendorLocationDTO  vendorLocation = vendorLocDAO.getApVendorLocationByVendorIDLocaleIDLocationID(vendorLoc.getVendorID(),vendorLoc.getLocaleID(),vendorLoc.getLocationID());
	        display.setVendorLocationID(vendorLocation.getVendorLocationID());
	        vendorLocs.add(display);
	        acctEditVendorsForm.setNewDefaultAcctNum("");
		}
		else {
			if (hasSet) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.acct.vendor.DuplicateLocation", "") );
			}
			if (duplicateCode) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.acct.vendor.DuplicateCode", "") );
			}
			if (containQuote) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.acct.vendor.Name"));
			}
			saveErrors(request, errors);	
		}
	}
	
	private boolean checkVendorCode(DbUserSession user,AcctEditVendorsForm acctEditVendorsForm){
		boolean duplicateCode = false;
		ApVendorDAO vendorDaoC = new ApVendorDAO(user);
		ArrayList<ApVendorDTO> vendorlistC = new ArrayList <ApVendorDTO>();
		vendorlistC =	vendorDaoC.getApVendorByCode(true,acctEditVendorsForm.getVendorCode());
		if (vendorlistC.size() > 0) {
			if (acctEditVendorsForm.getVendorID() == 0) {
				duplicateCode = true;
			}else {
				for (ApVendorDTO vendor : vendorlistC) {
					if(vendor.getVendorID() == acctEditVendorsForm.getVendorID() ) {
						continue;
					} else {
						if(vendor.getVendorCode().compareToIgnoreCase(acctEditVendorsForm.getVendorCode())== 0 ){
							duplicateCode = true;
						}
					}
				}
			}	
		}
		return duplicateCode;
	}
	
	private void removeLocation (AcctEditVendorsForm acctEditVendorsForm, DbUserSession user, 
			HttpSession session, HttpServletRequest request, ActionMessages errors, long vendorLocID ) throws Exception {

		ApVendorLocationDAO vendorLocDao = new ApVendorLocationDAO(user);

		if (vendorLocID <= 0) {
			String missingLoc = getResources(request).getMessage("error.acct.vendor.missingVendor");

			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", missingLoc));
			saveErrors(request, errors);	
		} 
			ApVendorLocationDTO vendorLoc = vendorLocDao.getApVendorLocation(vendorLocID);
			
			ArrayList<ApVendorLocationDTO> locationlist = new ArrayList <ApVendorLocationDTO>(); 
			locationlist = vendorLocDao.getApVendorLocationByVendorID(vendorLoc.getVendorID());
			if (locationlist.size() > 1) {
				vendorLocDao.deleteApVendorLocation(vendorLocID);
			}
			else {
				String cannotRemove = getResources(request).getMessage("error.acct.vendor.RemoveLastLocation");
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", cannotRemove));			
				saveErrors(request, errors);			
			}
	}
	
}