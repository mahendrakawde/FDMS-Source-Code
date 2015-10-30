package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.VendorEditListForm;

public class ShowVendorList extends Action {
    
    private Logger logger = Logger.getLogger(ShowVendorList.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
    	HttpSession session =  request.getSession();
    	
    	VendorEditListForm	form = (VendorEditListForm)session.getAttribute( "VendorEditListForm" );
    	
        formErrors = new ArrayList();
//        VendorEditListForm form = (VendorEditListForm) actionForm;
        
        if ( form == null ) {
        	form = new VendorEditListForm();
        }
        
        
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        
        
        // Check for any errors so far
		ApVendorDAO vendorDao = new ApVendorDAO(sessionUser);
		DatabaseTransaction t = null;
		if (form.getDirection()!= null && form.getDirection().compareToIgnoreCase("Search") == 0) {
			try {
				t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
				
				if ( (((form.getSearchVendorName()== null) || (form.getSearchVendorName().compareTo("") == 0))
						&& ((form.getVendorCode()== null) || (form.getVendorCode().compareTo("") == 0)) )
						&& ((form.getLocationID() == 0) && (form.getLocaleID() == 0))){
					
					form.setSearchVendorName("");

					if (form.getIncludeNoLocation() == null) {
						ArrayList <ApVendorDTO> vendorList = new ArrayList <ApVendorDTO>();
						// end getting vender for user				
						form.setVendors(vendorList);
					}else {
						if (form.getIncludeNoLocation().compareToIgnoreCase("Y")==0) {
							
							ArrayList <ApVendorDTO> vendorList = vendorDao.getUnAssignedLocationApVendor(form.getIncludeInactive(),true);
							for ( ApVendorDTO aVendor: vendorList) {
								DbApAccount anAcct = FdmsDb.getInstance().getApAccount(t, aVendor.getDefaultAcctID());
								if (anAcct == null) {
									aVendor.setDefaultAcct("");
									aVendor.setDefaultAcctDesc("");
								}
								else {
									aVendor.setDefaultAcct(anAcct.getAccountNumber());
									aVendor.setDefaultAcctDesc(anAcct.getDescription());
								}
							}
							form.setVendors(vendorList);
						}else {
							ArrayList <ApVendorDTO> vendorList = new ArrayList <ApVendorDTO>();
							// end getting vender for user				
							form.setVendors(vendorList);
						}
					}
				
				}
				else {
					//acctListVendorsSearchForm.setVendorName(acctListVendorsSearchForm.getVendorName());
					//acctListVendorsSearchForm.setLocationID(locationID)
					long locationID = form.getLocationID();
					long localeID = form.getLocaleID();			
					
					String searchVendorName ="";
					if (form.isExactName()){
						searchVendorName = form.getSearchVendorName();
					}
					else {
					    searchVendorName = "%"+form.getSearchVendorName()+"%";
					}
					String searchVendorCode = "";
					if (form.isExactCode()){
					    searchVendorCode = form.getVendorCode();
					}
					else {
						searchVendorCode = "%"+form.getVendorCode()+"%";
					}
					
					//ArrayList <ApVendorDTO> vendors = vendorDao.getApVendorByName(true,searchVendorName);
					ArrayList <Long> vendorIDs = getVendorIDs(sessionUser,locationID, localeID, form.getIncludeInactive(), form.getIncludeNoLocation());
					ArrayList <ApVendorDTO> vendorList = vendorDao.getApVendorByVendorIDsName(form.getIncludeInactive(),vendorIDs,searchVendorName,searchVendorCode);	 
					
					for ( ApVendorDTO aVendor: vendorList) {
						DbApAccount anAcct = FdmsDb.getInstance().getApAccount(t, aVendor.getDefaultAcctID());
						if (anAcct == null) {
							aVendor.setDefaultAcct("");
							aVendor.setDefaultAcctDesc("");
						}
						else {
							aVendor.setDefaultAcct(anAcct.getAccountNumber());
							aVendor.setDefaultAcctDesc(anAcct.getDescription());
						}
					}		
					
					
					form.setVendors(vendorList);

				}
				

				form.setSearchVendorName("");
				form.setVendorCode("");
				form.setIncludeInactive("A");
				form.setIncludeNoLocation("N");
				form.setLocationID(0);
				form.setLocaleID(0);	
				form.setExactCode(false);
				form.setExactName(false);
				
//				if ( ((form.getSearchVendorName()== null) || (form.getSearchVendorName().compareTo("") == 0)) && (form.getLocationID() == 0)){
//					form.setSearchVendorName("");
//					
//					ArrayList <ApVendorDTO> vendorList = new ArrayList <ApVendorDTO>();
//					// end getting vender for user				
//					form.setVendors(vendorList);
//				
//				}
//				else {
//					
//					long locationID = form.getLocationID();
//					
//					form.setSearchVendorName(form.getSearchVendorName());
//					
//					String searchVendorName = "%"+form.getSearchVendorName()+"%";
//					//ArrayList <ApVendorDTO> vendors = vendorDao.getApVendorByName(true,searchVendorName);
//					//ArrayList <Long> vendorIDs = getVendorIDs(sessionUser);
//					ArrayList <Long> vendorIDs = getVendorIDs(sessionUser,locationID);
//					ArrayList <ApVendorDTO> vendorList = vendorDao.getApVendorByVendorIDsName(true,vendorIDs,searchVendorName);	 
//					
//					for ( ApVendorDTO aVendor: vendorList) {
//						DbApAccount		anAcct = getAnAccount( sessionUser,  aVendor.getDefaultAcctID(),  errors);
//						if (anAcct == null) {
//							aVendor.setDefaultAcct("");
//							aVendor.setDefaultAcctDesc("");
//						}
//						else {
//							aVendor.setDefaultAcct(anAcct.getAccountNumber());
//							aVendor.setDefaultAcctDesc(anAcct.getDescription());
//						}
//					}			
//					
//				
//					form.setVendors(vendorList);
//					form.setSearchVendorName("");
//					form.setLocationID(0);	
//					
//				}       
//		        
//		        if( !errors.isEmpty() )   {
//		            saveErrors(request, errors );
//		        }
        
			} catch ( Exception e ) {
				logger.debug("Exception: ", e);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
				saveErrors(request, errors);
			}  finally {
	            if (t != null) {
	                try {
						t.closeConnection();
						t = null;
					}  catch (Exception e) {
	                    logger.error("Error in closeConnection() : ", e);
	                }
	            }
	        }
			form.setDirection("");
		}else {
			form.setLocationID(0); 
			form.setSearchVendorName("");
			form.setLocaleID(0);
			form.setVendorCode("");
			form.setIncludeInactive("A");
			form.setIncludeNoLocation("N");
			form.setExactCode(false);
			form.setExactName(false);
		}
        
        createLocaleLocationList( request);
        session.setAttribute("VendorEditListForm", form);
        return mapping.findForward("ShowVendorListJsp");
        
        // return mapping.findForward("ShowCheckEditDisplayJsp");
    }
    
//	private ArrayList <Long> getVendorIDs( DbUserSession user, long locationID ) {
//		
//		ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(user);
//		ArrayList<Long> locationIDs = new ArrayList<Long>();
//		if (locationID == 0) {
//			UserManagerBean uMgr = new UserManagerBean();	
//			String [] stringLocationIDs = uMgr.getUserLocationIds(user.getId());
//			for(int i=0;i<stringLocationIDs.length;i++){
//				locationIDs.add( Long.parseLong(stringLocationIDs[i]));
//			}
//		}
//		else {
//			locationIDs.add(locationID);
//		}
//		
//		ArrayList <ApVendorLocationDTO> apVendorLocationList = apVendorLocationDao.getApVendorLocationByLocationIDs(locationIDs);
//		ArrayList<Long> vendorIDs = new ArrayList<Long>();
//		for ( ApVendorLocationDTO apVendorLocation : apVendorLocationList ) {
//			vendorIDs.add(apVendorLocation.getVendorID());
//		}
//		return (vendorIDs);
//	}		
    
	private ArrayList <Long> getVendorIDs( DbUserSession user, long locationID, long localeID, String vendorStatus, String noLocation ) {
		
		ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(user);
		ArrayList<Long> locationIDs = new ArrayList<Long>();
		DatabaseTransaction t = null;
		if (locationID == 0) {
			if (localeID ==0) {
				UserManagerBean uMgr = new UserManagerBean();	
				String [] stringLocationIDs = uMgr.getUserLocationIds(user.getId());
				for(int i=0;i<stringLocationIDs.length;i++){
					locationIDs.add( Long.parseLong(stringLocationIDs[i]));
				}
			}else {
			try {
			    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
				DbLocation[] dbLocations = FdmsDb.getInstance().getLocationsForRegion(t, (int)localeID); 
				for (DbLocation dbLocation: dbLocations) {
					locationIDs.add((long)dbLocation.getId());
				}
			
			} catch(PersistenceException pe) {
	            // AppLog.criticalError("Persistence Exception in NewAtNeed.doPerform. "+pe.getCause());
	            
	            
	        } catch(Exception pe) {
	            // AppLog.criticalError("Exception in BilltoAddChange .doPerform. "+pe);
	            // pe.printStackTrace();
	           
	        } finally {
	            if (t != null) {
	                try {
						t.closeConnection();
						t = null;
					}  catch (Exception e) {
	                    logger.error("Error in closeConnection() : ", e);
	                }
	            }
	        }
			}
			
		}
		else {
			locationIDs.add(locationID);
		}
		
		ArrayList <ApVendorLocationDTO> apVendorLocationList = apVendorLocationDao.getApVendorLocationByLocationIDs(vendorStatus,locationIDs, noLocation);
		ArrayList<Long> vendorIDs = new ArrayList<Long>();
		for ( ApVendorLocationDTO apVendorLocation : apVendorLocationList ) {
			vendorIDs.add(apVendorLocation.getVendorID());
		}
		return (vendorIDs);
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
	
	private void createLocaleLocationList(HttpServletRequest request) {
        UserDAO userDAO = new UserDAO();
		HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	    UserManagerBean uMgr = new UserManagerBean();
//        ArrayList <UserLocationDTO> locations = uMgr.getLocations( user.getDbLookup(), (int) user.getId(), 
//				user.getCompanyID(), user.getRegion() ); 
//        ArrayList <UserLocationDTO> userLocations = userDAO.getUserLocations(user.getId());
//        ArrayList <UserLocationDTO> rUserLocations = new ArrayList<UserLocationDTO>();
//        for (UserLocationDTO userLocation: userLocations) {
//        	for (UserLocationDTO location: locations) {
//        		if (location.getLocationId() == userLocation.getLocationId()){
//        			rUserLocations.add(location);
//        			break;
//        		}
//        	}
//        }
//        session.setAttribute("ADMIN_LOCATIONS", rUserLocations);
        
        ArrayList <UserLocationDTO> userLocations = uMgr.getLocationsOfUser(
        		user.getDbLookup(), 
        		(int) user.getId(), user.getCompanyID(),  user.getRegion());	
        
        
        session.setAttribute("ADMIN_LOCATIONS", userLocations);
        
//        ArrayList <UserLocaleDTO> locales = uMgr.getLocalesOfUser( user.getDbLookup(), (int) user.getId(), 
//				user.getCompanyID(), user.getRegion());
//
//        ArrayList <UserLocaleDTO> userLocales = userDAO.getUserLocales(user.getId());
//        ArrayList <UserLocaleDTO> rUserLocales = new ArrayList<UserLocaleDTO>();
//        for (UserLocaleDTO locale: locales){
//        	for (UserLocaleDTO userLocale: userLocales ){
//        		  if ( userLocale.getLocaleId().equalsIgnoreCase(locale.getLocaleId()) ){
//        			  userLocale.setName(locale.getName());
//        			  userLocale.setCompanyId(locale.getCompanyId());
//        			  rUserLocales.add(userLocale);
//        			  break;
//        		  }
//        	}
//        }
        
//        session.setAttribute("ADMIN_LOCALES", rUserLocales);
        
        ArrayList <UserLocaleDTO> userLocales = uMgr.getLocalesOfUser(user.getDbLookup(), (int) user.getId(), 
        		user.getCompanyID(), user.getRegion());
        
        session.setAttribute("ADMIN_LOCALES", userLocales);
        
	}
	
    
}
