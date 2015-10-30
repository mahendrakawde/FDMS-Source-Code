package fdms.ui.struts.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPasswordLog;
import com.aldorsolutions.webfdms.beans.DbSecurityConfig;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.security.util.Encryptor;
import com.aldorsolutions.webfdms.util.LoginLogger;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.UserEditForm;


/**
 * ProcessUserEdit
 * This class processes the form for adding or changing user data
 * and stores the information to the DBMS.
 * Creation date: (5/13/2002 4:13:09 PM)
 * @author:
 */
public class ProcessUserEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessUserEdit.class.getName());
    private ArrayList <String> formErrors;
    
    /**
     * Called from WebFdmsManagementUserAdmin2.JSP, this action validates the form data,
     * stores it to the DBMS, and redisplays a new user list so the operator
     * can select another to modify.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.trace("ProcessUserEdit action doPerform: "+form.getSubmitbutton()+"-"+form.getAction());
        
        formErrors = new ArrayList <String> ();
        UserEditForm form = (UserEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        String usersLookup = UtilSingleton.getInstance().getUserDBLookup();
        DbUser auser = null;
        short checked = 1;
        short unchecked = 0;
        
        // Check if CANCEL button was clicked
        if (form.getSubmitbutton().equals("cancel")){
            return mapping.findForward("showUserListGlobal");
        }
        
        // Must select userId for Change or Delete
        if (form.getActionID() == UserEditForm.ACTION_DELETE || form.getActionID() == UserEditForm.ACTION_CHANGE) {
            if (form.getUserID() < 1) {                
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));                
                formErrors.add("userId");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                return (new ActionForward(mapping.getInput()));
            }
        }
        
        // Database Access
        try {
            t = new DatabaseTransaction(usersLookup, sessionUser.getId(), 
            			sessionUser.getCompanyID(), sessionUser.getRegion() );
            
            boolean passwordChanged = false;
            
            if (form.getActionID() == UserEditForm.ACTION_ADD) {
                auser = new DbUser();
                auser.setNew();
                auser.setRegion(sessionUser.getRegion());
                auser.setDataUrl(sessionUser.getDataUrl());
                auser.setDbLookup(sessionUser.getDbLookup());
                auser.setSqlUser(sessionUser.getSqlUser());
                auser.setSqlPassword(sessionUser.getSqlPassword());
                passwordChanged = true;
            } else {
                auser = (DbUser)FdmsDb.getInstance().getUser(t, form.getUserID());
            }
            
            if (form.getActionID() == UserEditForm.ACTION_DELETE) {
                auser.remove();
                t.save();
            } else {
                validateData(auser, form, errors, request);
                if (errors.isEmpty()) {
                    auser.setUserName(form.getName());
                    auser.setInitials(form.getUserinitials());
                    auser.setSecurityAdmin(form.getAdmin() ? checked:unchecked);
                    auser.setSecurityAcctsRec(form.getAr()? checked:unchecked);
                    auser.setSecurityAtneed(form.getAtneed()? checked:unchecked);
                    auser.setSecurityFinancial(form.getFinancial()? checked:unchecked);
                    auser.setSecurityForms(form.getForms()? checked:unchecked);
                    auser.setSecurityInventory(form.getInventory()? checked:unchecked);
                    auser.setSecurityPreneed(form.getPreneed()? checked:unchecked);
                    auser.setSecurityPayments(form.getPayment()? checked:unchecked);
                    auser.setSecurityViewOnly(form.getViewonly()? checked:unchecked);
                    auser.setSecurityAccountingInterface(form.getAccountingInterface()? checked:unchecked);
                    auser.setSecuritySpeedData(form.getSpeedData()? checked:unchecked);
                    auser.setSecurityArrangerManagement(form.getArrangerManager()? checked:unchecked);
                    auser.setSecurityFormsAvaialble(form.getFormsAvaialble()? checked:unchecked);
                    auser.setSecurityGLSalesAccount(form.getGlSalesAccount()? checked:unchecked);
                    auser.setSecurityEnableFinancialChange(form.getFinancialChange()? checked:unchecked);
                    auser.setSecurityDelete(form.getVoidcase()? checked:unchecked);
                    auser.setSecurityReports(form.getReports()? checked:unchecked);
                    auser.setSecurityCemetery(form.isViewCemetery()? checked:unchecked);
                    auser.setSecurityFuneral(form.isViewFuneralHome()? checked:unchecked);         
                    auser.setSqlUser(sessionUser.getSqlUser());
                    auser.setSqlPassword(sessionUser.getSqlPassword());
                    auser.setDbLookup(sessionUser.getDbLookup());
                    auser.setFirstName(form.getFirstName());
                    auser.setLastName(form.getLastName());
                    auser.setEmailAddr(form.getEmailAddr());
                    auser.setState(form.getState());
                    auser.setCompanyID(form.getCompanyID());
                    auser.setChangePassword(form.isChangePassword()? true:false);            
                    auser.setUserLocked(form.isUserLocked()? true:false);
                    
                    String password = form.getPassword();
                    
                    if (password != null) {
                    	if (  (password.trim().length() > 0) && 
                    		  (password.equals("***SAME***") == false) ) {
                    		
                    		password = Encryptor.encrypt(password);
                    		auser.setPassword(password);	
                    		auser.setPasswordTimestamp(new Timestamp(System.currentTimeMillis()));
                    		//auser.setChangePassword(true); //request to take out confirmation page if the password change by user.
                    		auser.setChangePassword(false);
                    		passwordChanged = true;
                    	}
                    }
                   
                    if ( form.isUserLockedState() != auser.isUserLocked() ) {
                    	
                    	if ( auser.isUserLocked() ) {
                    		auser.setLockedTimestamp(new Timestamp(System.currentTimeMillis()));
                    	} 
                    	else {
                    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        	Date date = sdf.parse("01/01/1980");
                        	
                        	/*
                        	 * Setting timestamp to null on MySQL will result in the 
                        	 * current time being set.  Instead I'm setting it to 1980 
                        	 * to ensure that the user will be allowed back in. 
                        	 */
                        	
                        	auser.setLockedTimestamp(new Timestamp(date.getTime()));
                        	
                        	LoginLogger loginLog = new LoginLogger(auser.getUserId(), 
                        			auser.getCompanyID(), auser.getRegion() );        
                            
                        	String ip = request.getRemoteAddr();
                    		String browser = null;
                            String userAgent = null;
                            
                            loginLog.logUser( auser.getUserName(), auser.getUserId(), 
                            		auser.getRegion(), System.currentTimeMillis(), ip, browser, 
                            		userAgent, "A" );
                    	}
                    	
                    	
                    }
                    
                    if (form.getActionID() == UserEditForm.ACTION_ADD) {
                        t.addPersistent(auser);
                    }
                    t.save();
            		
                    // add/update locationIds configured for this user
                    long userId = new Integer(auser.getId()).longValue();
                    UserManagerBean userManagerBean = new UserManagerBean();
                    
                    ArrayList locationList = form.getLocations();
                    ArrayList <UserLocationDTO> saveLocations = new ArrayList <UserLocationDTO> ();
                    String [] locationIds  = form.getLocationIds();
                    
                    for ( int i=0; i < locationList.size(); i++ ) {
                    	DbLocation location = (DbLocation) locationList.get(i);
                    	
                    	int locationID = location.getId();
                    	
                    	if ( locationIds != null ) {
	                    	for ( int x = 0; x < locationIds.length; x++ ) {
	                    		int aLocID = Integer.parseInt(locationIds[x]);
	                    		
	                    		if ( aLocID == locationID ) {
	                    			int localeID = location.getLocaleNumber();
	                            	
	                    			UserLocationDTO loc = new UserLocationDTO();
	                    			
	                    			loc.setLocationId( locationID );
	                    			loc.setRegionId( localeID );
	                    			
	                    			saveLocations.add(loc);
	                    		}
	                    		
	                    	}
                    	}
                    		
                    }
                    
                    userManagerBean.deleteUserLocations(userId);
                    userManagerBean.addUserLocations ( userId, saveLocations );
                    // if userlocation exists update
                    if(isExists(Long.parseLong(form.getUserDefaultLocation()), saveLocations)){
                    	userManagerBean.updateUserDefaultLocation(Integer.toString(auser.getId()),
        					form.getUserDefaultLocation());
                    }else{
                    	// if user location not exists insert the defaultuserlocation 
                    	UserLocationDTO userDefaultLocation = new UserLocationDTO();
                    	//for ALL scenario
                    	if("0".equals(form.getUserDefaultLocation())){
                    		userDefaultLocation.setLocationId(0);
                            userDefaultLocation.setRegionId(0);
                    	}else{
                    		// for specific location scenario.
                    	LocationDAO locationDAO = new LocationDAO(sessionUser);
                    	LocationDTO locationDTO = locationDAO.getLocation(Long.parseLong(form.getUserDefaultLocation()));
	                    	userDefaultLocation.setLocationId(Long.parseLong(form.getUserDefaultLocation()));
	                        userDefaultLocation.setRegionId(locationDTO.getLocaleID());
                    	}
                    	UserDAO userDAO = new UserDAO();
                        userDAO.addDefaultUserLocation(auser.getId(),userDefaultLocation);
                    }
                    if ( passwordChanged ) {
                    	DatabaseTransaction x = null;
                    	
                    	try {

                        	x = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

    						DbPasswordLog dbPasswordLog = new DbPasswordLog();
    						dbPasswordLog.setNew();
    						dbPasswordLog.setPassword(password);
    						dbPasswordLog.setUserID(auser.getId());
    						dbPasswordLog.setTmstmp(new Timestamp(System.currentTimeMillis()));
    						x.addPersistent(dbPasswordLog);

    						x.save();
    						
                		} finally {
                			if ( x != null ) {
                				x.closeConnection();
                			}
                		}
                		
                    }
            		
                }
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessApAccountEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ProcessApAccountEdit.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return new ActionForward(mapping.getInput());
        }
        
        return mapping.findForward("showUserListGlobal");        
    }
    
    /**
     * This method validates the WebFDMSManagementUserAdmin2.jsp form values.
     * Creation date: (9/25/2002 2:59:45 PM)
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param form fdms.ui.struts.form.UserEditForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void validateData(DbUser auser, UserEditForm form, ActionErrors errors, HttpServletRequest request) throws Exception {
        // Check User name
        if (form.getName() == null || form.getName().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.blankname"));
            formErrors.add("name");
        } else {
            if (form.getActionID() == UserEditForm.ACTION_CHANGE) {
                if (!form.getName().trim().equals(auser.getUserName().trim())) {
                    DbUserSession chkUser = FdmsDb.getInstance().getUser("Name", form.getName());
                    if (chkUser != null) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.dupname"));
                        formErrors.add("name");
                    }
                }
            }
            if (form.getActionID() == UserEditForm.ACTION_ADD) {
                DbUserSession chkUser = FdmsDb.getInstance().getUser("Name", form.getName());
                if (chkUser != null) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.dupname"));
                    formErrors.add("name");
                }
            }
        }
        
        // Check Passwords
        if (form.getActionID() == UserEditForm.ACTION_ADD) {
        	
            if (form.getPassword() == null || form.getPassword().trim().length() == 0 ||
                form.getConfirmPassword() == null || form.getConfirmPassword().trim().length() == 0) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.password"));
                formErrors.add("password");
                formErrors.add("confirmPassword");
            } else {
                if (!form.getPassword().trim().equals(form.getConfirmPassword().trim())) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.password"));
                    formErrors.add("password");
                    formErrors.add("confirmPassword");
                }
            }
            
            String password = form.getPassword();
            
            if ( password != null ) {
            	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
                
            	SecurityManagerBean mbSecurity = new SecurityManagerBean ();
                SecurityConfigDTO dtoSecurity = mbSecurity.getSecurityConfig(sessionUser.getCompanyID());
                
            	if ( mbSecurity.isPasswordComplexityValid(auser.getUserName(), password, dtoSecurity) == false ) {
            		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.complexity"));
            	}
            	
            }
            
        }
        
        if (form.getActionID() == UserEditForm.ACTION_CHANGE) {
            if (form.getPassword() != null || form.getPassword().trim().length() > 0) {
                if (form.getConfirmPassword() != null || form.getConfirmPassword().trim().length() > 0) {
                    if (!form.getPassword().equals(form.getConfirmPassword())) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.password"));
                        formErrors.add("password");
                        formErrors.add("confirmPassword");
                    }
                } else {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.password"));
                    formErrors.add("password");
                    formErrors.add("confirmPassword");
                }
            } else {
                if (form.getConfirmPassword() != null || form.getConfirmPassword().trim().length() > 0) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.user.password"));
                    formErrors.add("password");
                    formErrors.add("confirmPassword");
                }
            }
            
            String password = form.getPassword();
            
            if ( password != null ) {
            	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
                
            	if ( password.equals("***SAME***") == false ) {
            		
            		DatabaseTransaction t = null;
        		
            		try {
            			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            			DbSecurityConfig secureConfig = FdmsDb.getInstance().getSecurityConfig(t, sessionUser.getCompanyID());
                    
            			SecurityManagerBean mbSecurity = new SecurityManagerBean ();
            			String userID = Integer.toString ( form.getUserID() );
                
            			if (mbSecurity.isPasswordComplexityValid(auser.getUserName(), password, secureConfig) == false) {
            				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.complexity"));
            			}

            			if (secureConfig.isPasswordNotRepeated()) {
            				
            				DbPasswordLog[] passLog = FdmsDb.getInstance().getPasswordLogSet(t, userID, password);

            				if (passLog != null && passLog.length > 0) {
            					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.repeated"));
            				}
            			}

            		} finally {
            			if ( t != null ) {
            				t.closeConnection();
            			}
					}
                
            	}
            }

        	
        }
        
        
        // First Name is required.
        if (form.getFirstName() == null || form.getFirstName().trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFirstName"));
            formErrors.add("firstName");
        }
        
        // Last Name is required.
        if (form.getLastName() == null || form.getLastName().trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullLastName"));
            formErrors.add("lastName");
        }
        
        // User Email is required.
        if (form.getEmailAddr() == null || form.getEmailAddr().trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullEmail"));
            formErrors.add("emailAddr");
        } else {
            // Make sure they supplied a valid email address.
            if (form.getEmailAddr().indexOf('@') == -1) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidEmail"));
                formErrors.add("emailAddr");
            }
        }
        
    }
    /**
     * checks userlocation exists or not
     * @param locationId
     * @param saveLocations
     * @return true if exist else false
     */
    public boolean isExists(long locationId, ArrayList saveLocations){
    	boolean isExists = false;
    	for(int i=0; saveLocations != null && i< saveLocations.size();i++){
    		UserLocationDTO loc = (UserLocationDTO)saveLocations.get(i);
    		if(loc.getLocationId() == locationId){
    			isExists = true;
    			break;
    		}
    	}
    	return isExists;
    }
}
