package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;

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

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.UserEditForm;
import fdms.ui.struts.form.UserListForm;


/**
 * ShowUserEdit Prepares form bean with data from selected user
 * Creation date: (5/16/2002 4:13:09 PM)
 * @author: CJongs
 */
public class ShowUserEdit extends Action {
    
    private Logger logger = Logger.getLogger(ShowUserEdit.class.getName());
    private ArrayList formErrors;
    
    static {
    	final Logger logTmp = Logger.getLogger(ShowUserEdit.class.getName());
    	logTmp.debug("ShowUserEdit Loaded");
    }
    
    /**
     * Called from WebFdmsManagementUserAdmin.JSP, this action prepares to show
     * WebFdmsManagementAdmin2.JSP.
     * The UserListForm is used to determine if adding or which
     * user is being updated.
     * Then the UserEdit form is created for the JSP to use.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {    	
    	
        logger.debug("Entering ShowUserEdit action doPerfrom");
        
        formErrors = new ArrayList();
        UserListForm form = (UserListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        UserEditForm editform = new UserEditForm();
        DbUserSession auser = null;
        LocaleDTO alocale = null;
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        CompanyDTO company = SessionHelpers.getCompanySession(request);
        int recid = 0;
        
        if (form.getSubmitbutton().equals("exit")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            return actionForward;
        }
        
        DatabaseTransaction t = null;
        DatabaseTransaction tdata = null;
        
        try {
            // fetch account from DBMS
            String userLookup = UtilSingleton.getInstance().getUserDBLookup();
            
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser, userLookup);
        	tdata = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        	alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
        	
            if ( form.getSubmitbutton().equals("add")){   
                // prepare empty form for addition
                editform.setUserID(0);
                editform.setCompanyID(company.getCompanyID());
                editform.setActionID(UserEditForm.ACTION_ADD);
                editform.setAction("Add");
            } else {
                // must be "change" or "delete"
                recid = FormatNumber.parseInteger( form.getUserID());
                if (recid < 1){
                    //AppLog.trace("ShowUserEdit- Change/delete with no selection.");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                    formErrors.add("userID");
                    saveErrors(request, errors);
                    request.setAttribute("formErrors", formErrors);
                    return (new ActionForward(mapping.getInput()));
                }
                                                
                auser = FdmsDb.getInstance().getUser(t, recid);
                
                if (auser==null){
                    //AppLog.error("ShowUserEdit. Attempted update of recid:"+recid+" but record not found in DBMS");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Account record could not be accessed."));
                } else {
                    // Load form with DB data
                    editform.setUserID(auser.getId());
                    editform.setCompanyID(auser.getCompanyID());
                    editform.setChangePassword(auser.isChangePassword());
                    editform.setUserLocked(auser.isUserLocked());
                    editform.setLockedTimestamp(auser.getLockedTimestamp());
					editform.setName(auser.getUserName());
					editform.setUserinitials(auser.getInitials());
					editform.setAdmin(0 != auser.getSecurityAdmin());
					editform.setPreneed(0 != auser.getSecurityPreneed());
					editform.setChangePassword( auser.isChangePassword());
					editform.setLockedTimestamp( auser.getLockedTimestamp());
					editform.setUserLocked( auser.isUserLocked());
					editform.setPassword( "***SAME***" );
					editform.setConfirmPassword( "***SAME***" );
					editform.setUserLockedState( auser.isUserLocked() );
					editform.setDbLookup( auser.getDbLookup());
					
                    // the following are available only if licensed for at-Need
                    if (alocale.getAtneedLicense().compareToIgnoreCase("N")!=0){
                        editform.setAr(0 != auser.getSecurityAcctsRec());
						editform.setAtneed(0 != auser.getSecurityAtneed());
						editform.setFinancial(0 != auser.getSecurityFinancial());
						editform.setForms(0 != auser.getSecurityForms());
						editform.setInventory(0 != auser.getSecurityInventory());
						editform.setPayment(0 != auser.getSecurityPayments());
						editform.setViewonly(0 != auser.getSecurityViewOnly());
						editform.setVoidcase(0 != auser.getSecurityDelete());
						editform.setReports(0 != auser.getSecurityReports());
						editform.setViewCemetery(0 != auser.getSecurityCemetery());
						editform.setViewFuneralHome(0 != auser.getSecurityFuneral());
						editform.setAccountingInterface(0 != auser.getSecurityAccountingInterface());
						editform.setSpeedData(0 != auser.getSecuritySpeedData());
						editform.setArrangerManager(0 != auser.getSecurityArrangerManagement());
						editform.setFormsAvaialble(0 != auser.getSecurityFormsAvaialble());
						editform.setGlSalesAccount(0 != auser.getSecurityGLSalesAccount());
						editform.setEasyPayment(0 != auser.getSecurityEasyPayment());
						editform.setFinancialChange(0 != auser.getSecurityEnableFinancialChange());
                    }
                    
                    editform.setAtneedLicense(alocale.getAtneedLicense());
					editform.setFirstName(auser.getFirstName());
					editform.setLastName(auser.getLastName());
					editform.setEmailAddr(auser.getEmailAddr());
					editform.setState(auser.getState());
                    
                    if (form.getSubmitbutton().equals("delete")){
                        editform.setAction("Delete");
                        editform.setActionID(UserEditForm.ACTION_DELETE);
                    } else {
                        editform.setAction("Change");
                        editform.setActionID(UserEditForm.ACTION_CHANGE);
                    }
                }
                
            }
            //chai comment - this has to be change not getting the states list form webfdmsdata1 but the data itself
            ArrayList stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList();
//            ArrayList stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList(sessionUser.getDbLookup());
            session.setAttribute("stateList", stateList);
            
            UserManagerBean userManagerBean = new UserManagerBean();

            DbLocation dbLocations [] = FdmsDb.getInstance().getLocationsForCompany(tdata, (int) sessionUser.getCompanyID());
            
//            LocationDAO locationDao = new LocationDAO(sessionUser);
//            ArrayList<LocationDTO> dbLocations = new ArrayList<LocationDTO>();
//        	try {
//        		dbLocations = locationDao.getLocations(sessionUser.getCompanyID(), false);
//        	} catch (Exception e) {
//        		logger.debug(e.getMessage(), e);
//        		errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//        				"error.exception", e.getMessage()));
//        		saveMessages(request, errors);
//        	}
            
            
            
            
            logger.debug("Dblocations is null : " + dbLocations );
            
            ArrayList locationList = new ArrayList();
            
            //Populate the Location List
            for (int i=0; i < dbLocations.length; i++) {
                DbLocation location = (DbLocation) dbLocations[i];
                locationList.add(location);
            }
         
            
            
            editform.setLocations(locationList);
            
//            ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(sessionUser.getDbLookup(),
//					(int) sessionUser.getCompanyID());
            
            
            ArrayList <LocaleDTO> locales = new ArrayList <LocaleDTO>();
            LocaleDAO localeDAO = new LocaleDAO();
			locales = localeDAO.getLocales(true, (int) sessionUser.getCompanyID(), sessionUser.getDbLookup(), true);
            
            
	        ArrayList localeList = new ArrayList();
	        
	        // Populate the Locale List
	        
	        for ( LocaleDTO locale : locales ) {
	        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
	        }
            
            editform.setLocales(localeList);
            
           
            long userID = new Integer(recid).longValue();
            
            editform.setLocationIds( userManagerBean.getUserLocationIds( userID ) );
            editform.setLocaleIds( userManagerBean.getUserLocaleIds( userID ) );
            
            logger.debug( "LocaleIds: " + editform.getLocaleIds() );
            logger.debug( "LocationIds: " + editform.getLocationIds() );
            
        	String js = createLocaleJavascript ( dbLocations, editform.getLocationIds() );
        	
        	editform.setLocaleMapJavascript(js);
        	            
            // Set collections in session
            session.setAttribute("userEditForm",editform);
            session.setAttribute("locationsSize", new Integer(locationList.size()) );
            session.setAttribute("localesSize", new Integer(localeList.size()) );
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowApAccountEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowApAccountEdit.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
            
            if ( tdata != null ) {
            	tdata.closeConnection();
            	tdata = null;
            }
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            return  new ActionForward(mapping.getInput());
        }
        UserManagerBean userManagerBean = new UserManagerBean();
        HashMap map = userManagerBean.getUserLocations(recid);
        String userLocation = "";
        if(map!= null){
         userLocation = (String)map.get("defaultLocation");
        }
        editform.setUserDefaultLocation(userLocation);
		
        return mapping.findForward("showUserEditJsp");
    }
    
    private String createLocaleJavascript ( DbLocation [] dbLocations, String [] locationIds ) {
    	
    	String js = "\n";
		
    	js += "\tlocList = new Array(" + dbLocations.length + ");\n";
    	
    	for ( int j = 0; j < dbLocations.length; j++ ) {
    		DbLocation dbLocation = (DbLocation) dbLocations[j];
    		
    		int localeID = dbLocation.getLocaleNumber();
    		int locationID = dbLocation.getId();
    		String locName = dbLocation.getName();
    		boolean selected = false;
    		
    		if ( locationIds != null ) {
    			for ( int x = 0; x < locationIds.length; x++ ) {
    				int aLocID = Integer.parseInt( locationIds[x] );
				
    				if ( aLocID == locationID ) {
    					selected = true;
    				}
    			}
    		}
    		
			js += "\tlocList[" + j + "] = new ua_location("+ localeID + ", "+ 
				locationID + ", \"" + locName + "\", " + selected +");\n";
			
			
    	}
    	
    	return ( js );
			
	}
}
