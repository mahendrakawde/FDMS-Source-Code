package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.UserRegistration;


public class ProcessUserRegistration extends Action {
    
    private Logger logger = Logger.getLogger(ProcessUserRegistration.class.getName());
    
    /**
     * Checks for the existance of this Location by passing a DbLocation instance
     * with pre-set values to check against.  If returns true, the location already
     * exists in the database.
     * Creation date: (9/04/02 10:54:17 AM)
     * @return boolean
     */
    public boolean checkLocationExists(com.aldorsolutions.webfdms.beans.DbLocation dbLocation, DbUserSession sessionUser) {
        
        boolean homeExists = false;
        DatabaseTransaction t = null;
        com.aldorsolutions.webfdms.beans.DbLocation[] list = null;
        String jndiLookup = UtilSingleton.getInstance().getFdmsDBLookup();
        
        try {
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser,jndiLookup);
            if (dbLocation.getState() != null && dbLocation.getLicenseNumber() != null) {
                com.aldorsolutions.webfdms.beans.DbLocationSet dbLocations = new com.aldorsolutions.webfdms.beans.DbLocationSet();
                java.util.Hashtable h = new java.util.Hashtable();
                h.put("State",dbLocation.getState());
                h.put("LicenseNumber", dbLocation.getLicenseNumber());
                dbLocations.restore(t,h);
                PersistentI[] obs = dbLocations.getPersistents();
                list = new com.aldorsolutions.webfdms.beans.DbLocation[obs.length];
                // This trick is needed to make a Persistent[] into a DbLocation[]
                for (int i=0; i < obs.length; i++) {
                    list[i] = (com.aldorsolutions.webfdms.beans.DbLocation)obs[i];
                    if (list[i].getId() != dbLocation.getId()) {
                        homeExists = true;
                    }
                }
            }
            
            if (dbLocation.getName() != null) {
                com.aldorsolutions.webfdms.beans.DbLocationSet dbLocations = new com.aldorsolutions.webfdms.beans.DbLocationSet();
                java.util.Hashtable h = new java.util.Hashtable();
                h.put("Name", dbLocation.getName());
                dbLocations.restore(t,h);
                PersistentI[] obs = dbLocations.getPersistents();
                list = new com.aldorsolutions.webfdms.beans.DbLocation[obs.length];
                // This trick is needed to make a Persistent[] into a DbLocation[]
                for (int i=0; i < obs.length; i++) {
                    list[i] = (com.aldorsolutions.webfdms.beans.DbLocation)obs[i];
                    if (list[i].getId() != dbLocation.getId()) {
                        homeExists = true;
                    }
                }
            }
            
            return homeExists;
        }
        catch (PersistenceException e){
            logger.error("ProcessUserAcceptance.checkLocationExists Persistence Exception:", e);
            return homeExists;
        } finally {
            if (t != null) t.closeConnection();
        }
    }
    /**
     * Called from the UserRegistration.jsp, this action handles the
     * add submit button.
     * @return ActionForward
     * Created 08/28/02 5:38PM
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        UserRegistration form = (UserRegistration) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        ActionForward actionForward = new ActionForward(mapping.getInput());
        String submitType = form.getSubmitButton();
        //AppLog.info("ProcessUserRegistration.doPerform:Submit = " +submitType);
        
        // Extract attributes we will need
        
        // First, if the user didn't chose to 'save', then exit back to login.jsp
        if ((submitType == null) || (!submitType.equals("save"))) {
            actionForward = mapping.findForward("logon");
            //AppLog.trace("ProcessUserRegistration exiting to forwardLogon");
            return actionForward;
        }
        
        // Make sure the registration form User Information data is valid before proceeding.
        validateUserInfo(form, errors);
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            //AppLog.trace("ProcessUserRegistration failed User Information validation, return to getInput().");
            return actionForward;
        }
        
        // Make sure the registration form Funeral Home Information data is valid before proceeding.
        validateFuneralHomeInfo(form, errors, sessionUser);
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            //AppLog.trace("ProcessUserRegistration failed Funeral Home Information validation, return to getInput().");
            return actionForward;
        }
        
        //Action Forward Logic
        form.setInitials("");
        form.setSubmitButton("");
        actionForward = mapping.findForward("showUserAcceptance");
        return actionForward;
    }
    /**
     * Called from ProcessUserRegistration, this Method validates the
     * Funeral Home Information data from the UserRegistration form.
     * If a validation error occurs, the error is stored in the errors collection.
     * Creation date: (8/28/2002 5:16:34 PM)
     */
    public void validateFuneralHomeInfo(UserRegistration form, ActionErrors errors, DbUserSession sessionUser) {
        
        try {
            
            //AppLog.trace("In ProcessUserRegistration.validateFuneralHomeInfo.");
            
            // Funeral Home Name is required.
            if (form.getFuneralHomeName() == null || form.getFuneralHomeName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFuneralHomeName"));
            } else {
                com.aldorsolutions.webfdms.beans.DbLocation chkLocation = new com.aldorsolutions.webfdms.beans.DbLocation();
                chkLocation.setId(0);
                chkLocation.setName(form.getFuneralHomeName());
                if (checkLocationExists(chkLocation,sessionUser)) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.homeExists"));
                    return;
                }
            }
            
            // Address Line 1 is required.
            if (form.getAddr1() == null || form.getAddr1().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullAddr1"));
            }
            // City is required.
            if (form.getCity() == null || form.getCity().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullCity"));
            }
            // State is required.
            if (form.getState() == null || form.getState().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullState"));
            }
            // Zip Code is required.
            if (form.getZipCode() == null || form.getZipCode().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullZipCode"));
            }
            /*** Zip code editing needs to accept foreign postal codes, such as Canadian
             * else {
             * checkString = form.getZipCode().trim();
             * checkString = checkString.replace(' ','1');
             * checkString = checkString.replace('.','1');
             * checkString = checkString.replace('-','1');
             * try {
             * checkint = Double.parseDouble(checkString);
             * } catch (Exception e) {
             * errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidZipCode"));
             * }
             * }
             */
            // Phone number must be numeric
            /*
            if (form.getPhoneNumber() != null && form.getPhoneNumber().trim().length() > 0) {
                checkString = form.getPhoneNumber().trim();
                checkString = checkString.replace('.','1');
                checkString = checkString.replace('-','1');
                checkString = checkString.replace('(','1');
                checkString = checkString.replace(')','1');
                checkString = checkString.replace(' ','1');
                try {
                    checkint = Double.parseDouble(checkString);
                } catch (Exception e) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidPhoneNumber"));
                }
            }
             */
            
            // Other Phone must be numeric
            /*
            if (form.getOtherPhone() != null && form.getOtherPhone().trim().length() > 0) {
                checkString = form.getOtherPhone();
                checkString = checkString.replace('.','0');
                checkString = checkString.replace('-','0');
                checkString = checkString.replace('(','0');
                checkString = checkString.replace(')','0');
                checkString = checkString.replace(' ','0');
                try {
                    checkint = Double.parseDouble(checkString);
                } catch (Exception e) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidOtherPhone"));
                }
            }
             */
            
          /*if (form.getLicenseNumber() == null || form.getLicenseNumber().trim().equals("")) {
             errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullLicense"));
          } else {
                 com.aldorsolutions.webfdms.beans.DbLocation chkLocation = new com.aldorsolutions.webfdms.beans.DbLocation();
                 chkLocation.setId(0);
                 chkLocation.setLicenseNumber(form.getLicenseNumber());
                 chkLocation.setState(form.getState());
                 if (checkLocationExists(chkLocation)) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.homeExists"));
                        return;
                 }
          }*/
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessUserRegistration.validateFuneralHomeInfo. ", e);
        }
    }
    /**
     * Called from ProcessUserRegistration, this Method validates the User Information
     * data from the UserRegistration form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (8/28/2002 5:16:34 PM)
     */
    public void validateUserInfo(fdms.ui.struts.form.UserRegistration form, ActionErrors errors) {
        
        try {
            
            //AppLog.trace("In ProcessUserRegistration.validateUserInfo");
            
            // First Name is required.
            if (form.getFirstName() == null || form.getFirstName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFirstName"));
            }
            
            // Last Name is required.
            if (form.getLastName() == null || form.getLastName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullLastName"));
            }
            
            // User Email is required.
            if (form.getUserEmail() == null || form.getUserEmail().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullEmail"));
            } else {
                // Make sure they supplied a valid email address.
                if (form.getUserEmail().indexOf('@') == -1) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidEmail"));
                }
            }
            
            // Login Name is required.
            if (form.getUserLogin() == null || form.getUserLogin().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullLogin"));
            }
            
            // Password1 and Password2 are required and must match.
            if (form.getUserPassword1() == null || form.getUserPassword1().trim().equals("") ||
            form.getUserPassword2() == null || form.getUserPassword2().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullUserPassword"));
            } else {
                if (!form.getUserPassword1().trim().equals(form.getUserPassword2().trim())) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidUserPassword"));
                }
            }
            
            // Verify user doesn't already exist.
            if (form.getUserEmail() != null && form.getUserEmail().trim().length() > 0) {
                DbUserSession dbUserSession = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getUser("EmailAddr", form.getUserEmail());
                if (dbUserSession != null) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.userExists"));
                    return;
                }
            }
            if (form.getUserLogin() != null && form.getUserLogin().trim().length() > 0) {
                DbUserSession dbUserSession = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getUser("Name", form.getUserLogin());
                if (dbUserSession != null) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.loginExists"));
                }
            }
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessUserRegistration.validateUserInfo. ", e);
        }
    }
}
