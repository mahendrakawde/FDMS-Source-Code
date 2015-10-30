/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Locale;

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
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDisplayDTO;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.UtilSingleton;



import fdms.ui.struts.form.VendorEditForm;


/** 
 * MyEclipse Struts
 * Creation date: 06-25-2007, 030309
 * 
 * XDoclet definition:
 * @struts.action path="/acctEditVendors" name="acctEditVendorsForm" input="/acct/acctEditVendors.jsp" scope="request" validate="true"
 * @struts.action-forward name="listVendors" path="/acct/acctListVendors.jsp" redirect="true"
 */
public class ShowVendorEditDisplay extends Action {
	
	private Logger logger = Logger.getLogger(ShowVendorEditDisplay.class.getName());
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
		VendorEditForm vendorEditForm = (VendorEditForm) form;
		
	       if ( form == null ) {
	        	form = new VendorEditForm();
	        }

		HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        
		ApVendorDAO vendorDao = new ApVendorDAO(user);
		
		String vendorIDStr = (String) session.getAttribute("sessionVendorID");
		setLocaleLocation(vendorEditForm,  user, session);
		if ( vendorIDStr != null ) {
			request.setAttribute("vendorID", vendorIDStr);
			session.removeAttribute("sessionVendorID");
		} else {
			vendorIDStr = request.getParameter("vendorID");
		}		
		
		ActionErrors errors = new ActionErrors();
		long vendorID = FormatNumber.parseLong(vendorIDStr);	
		
		if ( vendorID < 0 ) {
			String missingLoc = getResources(request).getMessage("error.acct.vendor.missingVendor");
			
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", missingLoc) );
			saveErrors(request, errors);

			return ( mapping.findForward("ShowVendorListJsp") );
		} 
		else if (vendorID == 0)
		{	
			
			  SecurityManagerBean smBean = new SecurityManagerBean();
			  SecurityConfigDTO security = smBean.getSecurityConfig(user.getCompanyID());
			  vendorEditForm.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
			  
			  
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
		        	
		        	//display.setDefaultAcct( myVendorLoc.getDefaultAcct() );
		        	DbApAccount		anAcct = null;
		        	anAcct = getAnAccount( user,  myVendorLoc.getDefaultAcctID(),  errors);
		        	display.setDefaultAcct(anAcct.getAccountNumber());		        	
		        	
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
				
				apAcctID = vendor.getDefaultAcctID();			
				
				vendorEditForm.setApAccountID(apAcctID);	 		        
		        
		        vendorEditForm.setType1099Values(get1099Types());
	
		        
	            ArrayList <UserLocationDTO> userLocations = uMgr.getLocationsOfUser(
	            		user.getDbLookup(), 
	            		(int) user.getId(), user.getCompanyID(),  user.getRegion());	        
		        //session.setAttribute("ADMIN_LOCATIONS", locations);
		        session.setAttribute("ADMIN_LOCATIONS", userLocations);
//		        session.setAttribute("ADMIN_LOCALES", rUserLocales);
		        
		        ArrayList <UserLocaleDTO> userLocales = uMgr.getLocalesOfUser(user.getDbLookup(), (int) user.getId(), 
		        		user.getCompanyID(), user.getRegion());
		        
		        session.setAttribute("ADMIN_LOCALES", userLocales);
		        
		        session.setAttribute("ADMIN_VENDOR_LOCATIONS", vendorDisplayLocs);
		    	
		    	String js = uMgr.createLocaleJavascript ( locations, vendorEditForm.getLocationIds() );
		    	vendorEditForm.setLocaleMapJavascript(js);	
		  
					        
			  
		}
		
		else 
		{
			
			SecurityManagerBean smBean = new SecurityManagerBean();
			SecurityConfigDTO security = smBean.getSecurityConfig(user.getCompanyID());
			vendorEditForm.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
			  	
			
		  ApVendorDTO vendor = vendorDao.getApVendor(vendorID);
   
		vendorEditForm.setVendorCode(vendor.getVendorCode());
		vendorEditForm.setAddr1(vendor.getAddr1());
		vendorEditForm.setAddr2(vendor.getAddr2());
		vendorEditForm.setCityState(vendor.getCityState());
		vendorEditForm.setVendorState(vendor.getVendorState());	
		vendorEditForm.setVendorCountry(vendor.getVendorCountry());		
		vendorEditForm.setContactName(vendor.getContactName());
		vendorEditForm.setDeleteCode(vendor.getDeleteCode());
		vendorEditForm.setEmailAddr(vendor.getEmailAddr());
		vendorEditForm.setName(vendor.getName());
		vendorEditForm.setNotes(vendor.getNotes());
		vendorEditForm.setPhone(vendor.getPhone());
		vendorEditForm.setPhone2(vendor.getPhone2());		
		vendorEditForm.setPostalCode(vendor.getPostalCode());
//		vendorEditForm.setDefaultAcct(vendor.getDefaultAcct());
		
		vendorEditForm.setDefaultAcct("XXX");
		
		vendorEditForm.setLocaleID(vendor.getLocaleID());
		vendorEditForm.setLocationID(vendor.getLocationID());
		vendorEditForm.setVendorID(vendor.getVendorID());
		vendorEditForm.setInternalVendor( Boolean.toString(vendor.isInternalVendor()) );	
		
		vendorEditForm.setFax(vendor.getFax());
		vendorEditForm.setAccountNumber(vendor.getAccountNumber());
		vendorEditForm.setDiscountPercentage(vendor.getDiscountPercentage());		
		vendorEditForm.setDiscountIfInDays(vendor.getDiscountIfInDays());
		vendorEditForm.setDiscountDueInDays(vendor.getDiscountDueInDays());
		vendorEditForm.setVendor1099Type(vendor.getVendor1099Type());
		vendorEditForm.setVendor1099Payment(vendor.getVendor1099Payment());
		vendorEditForm.setTaxID(vendor.getTaxID());
		vendorEditForm.setApprovedVendor(vendor.getApprovedVendor());
		vendorEditForm.setCreditLimit(vendor.getCreditLimit());	
		
	       UserManagerBean uMgr = new UserManagerBean();
	        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO(user);
	        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByVendorID(vendor.getVendorID());
	        ArrayList <ApVendorLocationDisplayDTO> vendorDisplayLocs = new ArrayList <ApVendorLocationDisplayDTO> ();
	        
	        ArrayList <UserLocationDTO> locations = uMgr.getLocations( user.getDbLookup(), (int) user.getId(), 
					user.getCompanyID(), user.getRegion() ); 
	        ArrayList <UserLocaleDTO> locales = uMgr.getLocales( user.getDbLookup(), (int) user.getId(), 
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
	        for ( ApVendorLocationDTO myVendorLoc : vendorLocs ) {
	        	ApVendorLocationDisplayDTO display = new ApVendorLocationDisplayDTO();
	        	
	        	display.setVendorLocationID( myVendorLoc.getVendorLocationID() );
	        	
	        	
	        	//change this;
	        	display.setDefaultAcct( String.valueOf(myVendorLoc.getDefaultAcctID()) );
	        	

					DbApAccount		anAcct = getAnAccount( user,  myVendorLoc.getDefaultAcctID(),  errors);
					if (anAcct == null) {
						display.setDefaultAcct("");
						display.setDefaultAcctDesc("");
					}
					else {
						display.setDefaultAcct(anAcct.getAccountNumber());
						display.setDefaultAcctDesc(anAcct.getDescription());
					}
			
	        	
	        	
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
			apAcctID = vendor.getDefaultAcctID();
			
			//now set the apacctid to the right account.
			DbApAccount		anAcct = null;
			DbApAccount[] alist	= null;
			DatabaseTransaction t = null;
			int newAcctId = 0;
			try {
		        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		        	anAcct = FdmsDb.getInstance().getApAccount(t,(int) apAcctID);
		        	if (anAcct != null) {
			        	alist = FdmsDb.getInstance().getApAccounts(t,anAcct.getAccountNumber(),"");
			        	for (int i=0; i < alist.length; i++){
			        		if (alist[i].getLocaleID()== user.getRegion()){
			        			newAcctId = alist[i].getId();
			        			break;
			        		}
			        	}
			        	if (newAcctId == 0){
			        		for (int i=0; i < alist.length; i++){
				        		if (alist[i].getLocaleID()== 0){
				        			newAcctId = alist[i].getId();
				        			break;
				        		}
				        	}
			        	}
		        	}
		        	
			} catch(PersistenceException pe) {
	            logger.error("Persistence Exception in ShowVendorEditDisplay.doPerform. " + pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	        } catch(Exception pe) {
	            logger.error("Exception in  ShowVendorEditDisplay.doPerform. ", pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	        } finally {
	            if (t != null) t.closeConnection();
	        }
			
			
			
			//vendorEditForm.setApAccountID(apAcctID);	   
	        vendorEditForm.setApAccountID(newAcctId);
	        vendorEditForm.setType1099Values(get1099Types());	
	        
	        
	        
            ArrayList <UserLocationDTO> userLocations = uMgr.getLocationsOfUser(
            		user.getDbLookup(), 
            		(int) user.getId(), user.getCompanyID(),  user.getRegion());	        
	        //session.setAttribute("ADMIN_LOCATIONS", locations);
	        session.setAttribute("ADMIN_LOCATIONS", userLocations);
//	        session.setAttribute("ADMIN_LOCALES", rUserLocales);
//	        session.setAttribute("ADMIN_LOCATIONS", userLocations);
	        ArrayList <UserLocaleDTO> userLocales = uMgr.getLocalesOfUser(user.getDbLookup(), (int) user.getId(), 
	        		user.getCompanyID(), user.getRegion());
	        
	        session.setAttribute("ADMIN_LOCALES", userLocales);
	        
	        
	        session.setAttribute("ADMIN_VENDOR_LOCATIONS", vendorDisplayLocs);
	    	
	    	String js = uMgr.createLocaleJavascript ( locations, vendorEditForm.getLocationIds() );
	    	vendorEditForm.setLocaleMapJavascript(js);	
		
 
		}
		
		return mapping.findForward("ShowVendorEditDisplayJsp");
	}
	
	private ArrayList <LabelValueBean> get1099Types( ) {
		
		ArrayList <LabelValueBean> array = new ArrayList <LabelValueBean> ();

		array.add ( new LabelValueBean("1099-A",Integer.toString(Constants.TYPE_1099_A) ) );
		array.add ( new LabelValueBean("1099-B",Integer.toString(Constants.TYPE_1099_B) ) );
		array.add ( new LabelValueBean("1099-C",Integer.toString(Constants.TYPE_1099_C) ) );
		array.add ( new LabelValueBean("1099-CAP",Integer.toString(Constants.TYPE_1099_CAP) ) );
		array.add ( new LabelValueBean("1099-DIV",Integer.toString(Constants.TYPE_1099_DIV) ) );
		array.add ( new LabelValueBean("1099-G",Integer.toString(Constants.TYPE_1099_G) ) );
		array.add ( new LabelValueBean("1099-H",Integer.toString(Constants.TYPE_1099_H) ) );
		array.add ( new LabelValueBean("1099-INT",Integer.toString(Constants.TYPE_1099_INT) ) );
		array.add ( new LabelValueBean("1099-LTC",Integer.toString(Constants.TYPE_1099_LTC) ) );
		array.add ( new LabelValueBean("1099-MISC",Integer.toString(Constants.TYPE_1099_MISC) ) );
		array.add ( new LabelValueBean("1099-OID", Integer.toString(Constants.TYPE_1099_OID) ) );
		array.add ( new LabelValueBean("1099-PART",Integer.toString(Constants.TYPE_1099_PART) ) );
		array.add ( new LabelValueBean("1099-Q",Integer.toString(Constants.TYPE_1099_Q) ) );
		array.add ( new LabelValueBean("1099-R",Integer.toString(Constants.TYPE_1099_R) ) );
		array.add ( new LabelValueBean("1099-S",Integer.toString(Constants.TYPE_1099_S) ) );
		array.add ( new LabelValueBean("1099-SA",Integer.toString(Constants.TYPE_1099_SA) ) );
		
		return ( array );		
	}	
	
	private DbApAccount getAnAccount(DbUserSession user, int recid, ActionErrors errors) {
		 DbApAccount		anAcct = null;
		 DatabaseTransaction t = null;
	     
		 try {
	         t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
	         anAcct = FdmsDb.getInstance().getApAccount(t, recid);
		 } catch(Exception pe) {
	            logger.error("Exception in  ShowVendorEditDisplay.do ", pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	        } finally {
	            if (t != null) t.closeConnection();
	        }
		 return anAcct;
	}
	

	private void setLocaleLocation(VendorEditForm vendorEditForm, DbUserSession sessionUser, HttpSession session) {

		DbUser user = (DbUser) sessionUser;
		DatabaseTransaction trans = null;
		String[] locationIds = (String[]) session.getAttribute(Constants.USER_LOCATION_IDS);
		DbLocation[] dbLocations = FdmsDb.getInstance().getLocationsForRegion(trans, user.getRegion());
		//ArrayList <DbLocation> userLocaleLocs = new ArrayList <DbLocation>();
		vendorEditForm.setDef_localeID(String.valueOf(user.getRegion()));
		vendorEditForm.setDef_locationID("");
		
		if (user.getLocationId()> 0){
			vendorEditForm.setDef_locationID(String.valueOf(user.getLocationId()));
		}else {
			for (int x = 0; x < locationIds.length; x++) {
				int aLocID = Integer.parseInt(locationIds[x]);
				vendorEditForm.setDef_locationID(String.valueOf(aLocID));
			}
		}			

	}
}