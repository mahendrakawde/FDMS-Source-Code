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
import org.apache.struts.util.LabelValueBean;

import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorsolutions.dashboard.struts.form.acct.AcctEditVendorsForm;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
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
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

/** 
 * MyEclipse Struts
 * Creation date: 06-25-2007
 * 
 * XDoclet definition:
 * @struts.action path="/acctEditVendors" name="acctEditVendorsForm" input="/acct/acctEditVendors.jsp" scope="request" validate="true"
 * @struts.action-forward name="listVendors" path="/acct/acctListVendors.jsp" redirect="true"
 */
public class AcctVendorsEditAction extends Action {
	
	private Logger logger = Logger.getLogger(AcctVendorsEditAction.class.getName());
	/*
	 * Generated Methods
	 */

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
		//session.setAttribute("DEF_LOCALEID", String.valueOf(user.getRegion()));
		//session.setAttribute("DEF_LOCATIIONID", String.valueOf(user.getLocationId()));
        //DbApAccountSetPeer peer= new DbApAccountSetPeer();
        
        acctEditVendorsForm.setDef_localeID(String.valueOf(user.getRegion()));
        acctEditVendorsForm.setDef_locationID(String.valueOf(user.getLocationId()));
		ApVendorDAO vendorDao = new ApVendorDAO(user);
		String vendorIDStr = request.getParameter("vendorID");
		
		ActionMessages errors = new ActionMessages();	
		long vendorID = FormatNumber.parseLong(vendorIDStr);	

		if ( vendorID < 0 ) {
			String missingLoc = getResources(request).getMessage("error.acct.vendor.missingVendor");
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", missingLoc) );
			saveErrors(request, errors);
			
			String errorLog = request.getParameter("errorMesg");
			if (errorLog != null || errorLog.length() != 0) {
				errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", errorLog) );
			}
	
			return ( mapping.findForward("vendorListSearch") );
		} 
		else if (vendorID == 0)
		{	
			SecurityManagerBean smBean = new SecurityManagerBean();
			SecurityConfigDTO security = smBean.getSecurityConfig(user.getCompanyID());
			acctEditVendorsForm.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
			
			  ApVendorDTO vendor = new ApVendorDTO();
			  
		       UserManagerBean uMgr = new UserManagerBean();
		        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO(user);
//		        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByVendorID(vendor.getVendorID());
		        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByVendorID(-1);
		        ArrayList <ApVendorLocationDisplayDTO> vendorDisplayLocs = new ArrayList <ApVendorLocationDisplayDTO> ();
		        ArrayList <UserLocationDTO> locations = uMgr.getLocations( user.getDbLookup(), (int) user.getId(), 
						user.getCompanyID(), user.getRegion() ); 
		        ArrayList <UserLocaleDTO> locales = uMgr.getLocales( user.getDbLookup(), (int) user.getId(), 
						user.getCompanyID(), user.getRegion());	 
		          
		        long localeID = 0;		        
		        for ( ApVendorLocationDTO myVendorLoc : vendorLocs ) {
		        	ApVendorLocationDisplayDTO display = new ApVendorLocationDisplayDTO();
		        	
		        	display.setVendorLocationID( myVendorLoc.getVendorLocationID() );
		        	
					DbApAccount		anAcct = getAnAccount( user,  myVendorLoc.getDefaultAcctID(),  errors);
					if (anAcct == null) {
						display.setDefaultAcct("");
						display.setDefaultAcctDesc("");
					}
					else {
						display.setDefaultAcct(anAcct.getAccountNumber());
						display.setDefaultAcctDesc(anAcct.getDescription());
					}
		        	
		        	
		        	//display.setDefaultAcct( myVendorLoc.getDefaultAcct() );
		        	display.setLocaleName( String.valueOf(myVendorLoc.getLocaleID()) );
		        	display.setLocationName( String.valueOf(myVendorLoc.getLocationID()) );
			        
		        
		        	
		        	for ( UserLocationDTO loc : locations ) {
		        		long locID = loc.getLocationId();
		        		if ( locID == myVendorLoc.getLocationID() ) {
		        			display.setLocationName(loc.getName());
		        			break;
		        		}
		        	}
		        	
		        	for ( UserLocaleDTO loc : locales ) {
		        		long locID = FormatNumber.parseLong(loc.getLocaleId());
		        		if ( locID == myVendorLoc.getLocaleID() ) {
		        			localeID = locID;		        			
		        			display.setLocaleName(loc.getName());
		        			break;
		        		}
		        	}
		        	
		        	vendorDisplayLocs.add(display);
		        	
		        }
		        try {
		        	SessionHelpers.setApAccountListInSession(request, user.getLocationId());
		        	
				} catch ( Exception e ) {
					logger.debug("Exception: ", e);
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
					saveErrors(request, errors);
				}
				long apAcctID = 0;
				try {
					InvoiceManager imvMgr = new InvoiceManager();
					//DbApAccount account = imvMgr.getAccount(request, acctEditVendorsForm.getApAccountID());
					ArrayList <DbApAccount> accounts = imvMgr.getAccountList(request, user.getLocationId(),localeID);
					//vendor.setDefaultAcct(FormatNumber.parseInteger(account.getAccountNumber()));
					//private long apAccountID;

					for ( DbApAccount account : accounts ) {
		        		String Id = account.getAccountNumber();
		        		
		        		if (vendor.getDefaultAcct() == null) {
		        			break;
		        		} else {
		        			String IdDefaultAcct = vendor.getDefaultAcct();
			        		if ( IdDefaultAcct.compareToIgnoreCase(Id) == 0  ) {
			        			apAcctID = account.getId();
			        			break;
			        		}
		        		}
		        	}
					
				} catch ( Exception e ) {
					logger.debug("Exception: ", e);
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
					saveErrors(request, errors);
				}			
				

				acctEditVendorsForm.setApAccountID(apAcctID);		        

		        acctEditVendorsForm.setType1099Values(get1099Types());
		        

//	            UserDAO userDAO = new UserDAO();
//	            ArrayList <UserLocaleDTO> userLocales = userDAO.getUserLocales(user.getId());
//	            ArrayList <UserLocaleDTO> rUserLocales = new ArrayList<UserLocaleDTO>();
//	            for (UserLocaleDTO locale: locales){
//	            	for (UserLocaleDTO userLocale: userLocales ){
//	            		  if ( userLocale.getLocaleId().equalsIgnoreCase(locale.getLocaleId()) ){
//	            			  userLocale.setName(locale.getName());
//	            			  userLocale.setCompanyId(locale.getCompanyId());
//	            			  rUserLocales.add(userLocale);
//	            		  }
//	            	}
//	            }
  	            
	            ArrayList <UserLocationDTO> userLocations = uMgr.getLocationsOfUser(
	            		user.getDbLookup(), 
	            		(int) user.getId(), user.getCompanyID(),  user.getRegion());
	            
//		        session.setAttribute("ADMIN_LOCATIONS", userLocations);
//		        session.setAttribute("ADMIN_LOCALES", rUserLocales);
		        session.setAttribute("ADMIN_LOCATIONS", userLocations);
		        ArrayList <UserLocaleDTO> userLocales = uMgr.getLocalesOfUser(user.getDbLookup(), (int) user.getId(), 
		        		user.getCompanyID(), user.getRegion());
		        
		        session.setAttribute("ADMIN_LOCALES", userLocales);
		        
		        session.setAttribute("ADMIN_VENDOR_LOCATIONS", vendorDisplayLocs);
		    	String js = uMgr.createLocaleJavascript ( locations, acctEditVendorsForm.getLocationIds() );
		    	acctEditVendorsForm.setLocaleMapJavascript(js);	
				  
		}
		
		else 
		{					
			
			SecurityManagerBean smBean = new SecurityManagerBean();
			SecurityConfigDTO security = smBean.getSecurityConfig(user.getCompanyID());
			acctEditVendorsForm.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
			
		  ApVendorDTO vendor = vendorDao.getApVendor(vendorID);
   
		acctEditVendorsForm.setVendorCode(vendor.getVendorCode());
		acctEditVendorsForm.setAddr1(vendor.getAddr1());
		acctEditVendorsForm.setAddr2(vendor.getAddr2());
		acctEditVendorsForm.setCityState(vendor.getCityState());
		acctEditVendorsForm.setVendorState(vendor.getVendorState());	
		acctEditVendorsForm.setVendorCountry(vendor.getVendorCountry());		
		acctEditVendorsForm.setContactName(vendor.getContactName());
		acctEditVendorsForm.setDeleteCode(vendor.getDeleteCode());
		acctEditVendorsForm.setEmailAddr(vendor.getEmailAddr());
		acctEditVendorsForm.setName(vendor.getName());
		acctEditVendorsForm.setNotes(vendor.getNotes());
		acctEditVendorsForm.setPhone(vendor.getPhone());
		acctEditVendorsForm.setPhone2(vendor.getPhone2());		
		acctEditVendorsForm.setPostalCode(vendor.getPostalCode());
		//acctEditVendorsForm.setDefaultAcct(vendor.getDefaultAcct());
		acctEditVendorsForm.setApAccountID(vendor.getDefaultAcctID());
		acctEditVendorsForm.setLocaleID(vendor.getLocaleID());
		acctEditVendorsForm.setLocationID(vendor.getLocationID());
		acctEditVendorsForm.setVendorID(vendor.getVendorID());
		acctEditVendorsForm.setInternalVendor( Boolean.toString(vendor.isInternalVendor()) );
		
		acctEditVendorsForm.setFax(vendor.getFax());
		acctEditVendorsForm.setAccountNumber(vendor.getAccountNumber());
		acctEditVendorsForm.setDiscountPercentage(vendor.getDiscountPercentage());		
		acctEditVendorsForm.setDiscountIfInDays(vendor.getDiscountIfInDays());
		acctEditVendorsForm.setDiscountDueInDays(vendor.getDiscountDueInDays());
		acctEditVendorsForm.setVendor1099Type(vendor.getVendor1099Type());
		acctEditVendorsForm.setVendor1099Payment(vendor.getVendor1099Payment());
		acctEditVendorsForm.setTaxID(vendor.getTaxID());
		acctEditVendorsForm.setApprovedVendor(vendor.getApprovedVendor());
		acctEditVendorsForm.setCreditLimit(vendor.getCreditLimit());	
		
	       UserManagerBean uMgr = new UserManagerBean();
	        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO(user);
	        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByVendorID(vendor.getVendorID());
	        ArrayList <ApVendorLocationDisplayDTO> vendorDisplayLocs = new ArrayList <ApVendorLocationDisplayDTO> ();
	        //ArrayList <UserLocationDTO> locations = uMgr.getLocations( user.getDbLookup(), (int) user.getId(), 
			//		user.getCompanyID(), user.getRegion() ); 
	        //ArrayList <UserLocaleDTO> locales = uMgr.getLocales( user.getDbLookup(), (int) user.getId(), 
			//		user.getCompanyID(), user.getRegion());
	      ArrayList <UserLocationDTO> locations = uMgr.getLocationsOfUser( user.getDbLookup(), (int) user.getId(), 
					user.getCompanyID(), user.getRegion() ); 
	      ArrayList <UserLocaleDTO> locales = uMgr.getLocalesOfUser( user.getDbLookup(), (int) user.getId(), 
					user.getCompanyID(), user.getRegion());
	      
	      //get just only the locale and location for a user
	      ArrayList <ApVendorLocationDTO> vendorLocsForuser = new ArrayList <ApVendorLocationDTO> (); 
	      for (UserLocationDTO location: locations ) {
	    	  for (ApVendorLocationDTO vendorLocForUser : vendorLocs) {
	    		  if (location.getLocationId() == vendorLocForUser.getLocationID()) {
	    			  vendorLocsForuser.add(vendorLocForUser);
	    		  }
	    	  }	  
	      }

	        long localeID = 0;
	        //for ( ApVendorLocationDTO myVendorLoc : vendorLocs ) {
	        for ( ApVendorLocationDTO myVendorLoc : vendorLocsForuser ) {	
	        	ApVendorLocationDisplayDTO display = new ApVendorLocationDisplayDTO();
	        	
	        	display.setVendorLocationID( myVendorLoc.getVendorLocationID() );
	        	
				DbApAccount		anAcct = getAnAccount( user,  myVendorLoc.getDefaultAcctID(),  errors);
				if (anAcct == null) {
					display.setDefaultAcct("");
					display.setDefaultAcctDesc("");
				}
				else {
					display.setDefaultAcct(anAcct.getAccountNumber());
					display.setDefaultAcctDesc(anAcct.getDescription());
				}
	        	
	        	
	        	//display.setDefaultAcct( myVendorLoc.getDefaultAcct() );
	        	display.setLocaleName( String.valueOf(myVendorLoc.getLocaleID()) );
	        	display.setLocationName( String.valueOf(myVendorLoc.getLocationID()) );
	        	
	        	
	        	for ( UserLocationDTO loc : locations ) {
	        		long locID = loc.getLocationId();
	        		if ( locID == myVendorLoc.getLocationID() ) {
	        			display.setLocationName(loc.getName());
	        			break;
	        		}
	        	}
	        	
	        	for ( UserLocaleDTO loc : locales ) {
	        		long locID = FormatNumber.parseLong(loc.getLocaleId());
	        		if ( locID == myVendorLoc.getLocaleID() ) {
	        			localeID = locID;
	        			display.setLocaleName(loc.getName());
	        			break;
	        		}
	        	}
	        	
	        	vendorDisplayLocs.add(display);
	        	
	        }
	        
	        //SessionHelpers.setApAccountListInSession(request, invoiceEditForm.getLocationID());
	        
	        
	        try {
	        	SessionHelpers.setApAccountListInSession(request, user.getLocationId());
	        	
			} catch ( Exception e ) {
				logger.debug("Exception: ", e);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
				saveErrors(request, errors);
			}
//			long apAcctID = 0;
//			try {
//				InvoiceManager imvMgr = new InvoiceManager();
//				//DbApAccount account = imvMgr.getAccount(request, acctEditVendorsForm.getApAccountID());
//				ArrayList <DbApAccount> accounts = imvMgr.getAccountList(request, user.getLocationId(),localeID);
//				//vendor.setDefaultAcct(FormatNumber.parseInteger(account.getAccountNumber()));
//				//private long apAccountID;
//				for ( DbApAccount account : accounts ) {
//	        		String Id = account.getAccountNumber();
//	        		if (vendor.getDefaultAcct() == null) {
//	        			break;
//	        		} else {
//		        		String IdDefaultAcct = vendor.getDefaultAcct();
//		        		if ( IdDefaultAcct.compareToIgnoreCase(Id) == 0  ) {
//		        			apAcctID = account.getId();
//		        			break;
//		        		}
//	        		}
//	        	}
//				
//			} catch ( Exception e ) {
//				logger.debug("Exception: ", e);
//				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
//				saveErrors(request, errors);
//			}			
			
			
//			acctEditVendorsForm.setApAccountID(apAcctID);
	        acctEditVendorsForm.setType1099Values(get1099Types());
	        
//            UserDAO userDAO = new UserDAO();
//            ArrayList <UserLocaleDTO> userLocales = userDAO.getUserLocales(user.getId());
//            ArrayList <UserLocaleDTO> rUserLocales = new ArrayList<UserLocaleDTO>();
//            for (UserLocaleDTO locale: locales){
//            	for (UserLocaleDTO userLocale: userLocales ){
//            		  if ( userLocale.getLocaleId().equalsIgnoreCase(locale.getLocaleId()) ){
//            			  userLocale.setName(locale.getName());
//            			  userLocale.setCompanyId(locale.getCompanyId());
//            			  rUserLocales.add(userLocale);
//            			  break;
//            		  }
//            	}
//            }
	            
	        
//	        session.setAttribute("ADMIN_LOCATIONS", locations);
//	        session.setAttribute("ADMIN_LOCALES", rUserLocales);
	        
	           ArrayList <UserLocationDTO> userLocations = uMgr.getLocationsOfUser(
	            		user.getDbLookup(), 
	            		(int) user.getId(), user.getCompanyID(),  user.getRegion());	        

		        session.setAttribute("ADMIN_LOCATIONS", userLocations);
		        ArrayList <UserLocaleDTO> userLocales = uMgr.getLocalesOfUser(user.getDbLookup(), (int) user.getId(), 
		        		user.getCompanyID(), user.getRegion());
		        
		        session.setAttribute("ADMIN_LOCALES", userLocales);
	        
	        
	        
	        session.setAttribute("ADMIN_VENDOR_LOCATIONS", vendorDisplayLocs);
	    	
	    	String js = uMgr.createLocaleJavascript ( locations, acctEditVendorsForm.getLocationIds() );
	    	acctEditVendorsForm.setLocaleMapJavascript(js);	
		
		}
		
		
		return mapping.findForward("vendorEdit");
	}	
	
	private ArrayList <LabelValueBean> get1099Types( ) {
	
	ArrayList <LabelValueBean> array = new ArrayList <LabelValueBean> ();

//	array.add ( new LabelValueBean("1099-A",Integer.toString(Constants.TYPE_1099_A) ) );
//	array.add ( new LabelValueBean("1099-B",Integer.toString(Constants.TYPE_1099_B) ) );
//	array.add ( new LabelValueBean("1099-C",Integer.toString(Constants.TYPE_1099_C) ) );
//	array.add ( new LabelValueBean("1099-CAP",Integer.toString(Constants.TYPE_1099_CAP) ) );
//	array.add ( new LabelValueBean("1099-DIV",Integer.toString(Constants.TYPE_1099_DIV) ) );
//	array.add ( new LabelValueBean("1099-G",Integer.toString(Constants.TYPE_1099_G) ) );
//	array.add ( new LabelValueBean("1099-H",Integer.toString(Constants.TYPE_1099_H) ) );
//	array.add ( new LabelValueBean("1099-INT",Integer.toString(Constants.TYPE_1099_INT) ) );
//	array.add ( new LabelValueBean("1099-LTC",Integer.toString(Constants.TYPE_1099_LTC) ) );
	array.add ( new LabelValueBean("1099-MISC",Integer.toString(Constants.TYPE_1099_MISC) ) );
//	array.add ( new LabelValueBean("1099-OID", Integer.toString(Constants.TYPE_1099_OID) ) );
//	array.add ( new LabelValueBean("1099-PART",Integer.toString(Constants.TYPE_1099_PART) ) );
//	array.add ( new LabelValueBean("1099-Q",Integer.toString(Constants.TYPE_1099_Q) ) );
//	array.add ( new LabelValueBean("1099-R",Integer.toString(Constants.TYPE_1099_R) ) );
//	array.add ( new LabelValueBean("1099-S",Integer.toString(Constants.TYPE_1099_S) ) );
//	array.add ( new LabelValueBean("1099-SA",Integer.toString(Constants.TYPE_1099_SA) ) );
	
	return ( array );		
}
	private DbApAccount getAnAccount(DbUserSession user, int recid, ActionMessages errors) {
		 DbApAccount		anAcct = null;
		 DatabaseTransaction t = null;
	     
		 try {
	         t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
	         anAcct = FdmsDb.getInstance().getApAccount(t, recid);
		 } catch(Exception pe) {
	    		logger.debug("Exception: ", pe);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", pe.getMessage()) );
	            
	        } finally {
	            if (t != null) t.closeConnection();
	     }
		 return anAcct;
	}
	
	
}