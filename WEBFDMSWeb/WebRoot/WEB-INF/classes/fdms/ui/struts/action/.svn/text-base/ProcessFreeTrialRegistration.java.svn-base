package fdms.ui.struts.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FreeTrialRegistration;


public class ProcessFreeTrialRegistration extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFreeTrialRegistration.class.getName());
    
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
        
        try {
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
            
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
            //AppLog.warning("ProcessUserAcceptance.checkLocationExists Persistence Exception:"+e);
            logger.error("Error in checkLocationExists() : " + e);
            return homeExists;
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
    /**
     * Called from the FreeTrialRegistration.jsp, this action handles the
     * add submit button.
     * @return ActionForward
     * Created 08/28/02 5:38PM
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.info("ProcessFreeTrialRegistration Do Perform" + mapping);
        ActionErrors errors = new ActionErrors();
        FreeTrialRegistration form = (FreeTrialRegistration) actionForm;
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        ActionForward actionForward = new ActionForward(mapping.getInput());
        
        String submitType = form.getSubmitButton();
        
        // First, if the user didn't chose to 'save', then exit back to login.jsp
        if ((submitType == null) || (!submitType.equals("save"))) {
            actionForward = mapping.findForward("logon");
            //AppLog.trace("ProcessFreeTrialRegistration exiting to forwardLogon");
            return actionForward;
        }
        
        // Make sure the registration form User Information data is valid before proceeding.
        validateUserInfo(form, errors);
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            //AppLog.trace("ProcessFreeTrialRegistration failed User Information validation, return to getInput().");
            return actionForward;
        }
        
        // Make sure the registration form Funeral Home Information data is valid before proceeding.
        validateFuneralHomeInfo(form, errors, sessionUser);
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            //AppLog.trace("ProcessFreeTrialRegistration failed Funeral Home Information validation, return to getInput().");
            return actionForward;
        }
        
        //Send an email to the representative who should contact them to set up webFDMS
        sendEmail(form, request, errors);
        
        //Action Forward Logic
        form.setInitials("");
        form.setSubmitButton("");
        actionForward = mapping.findForward("showFreeTrialAcceptance");
        return actionForward;
    }
    
    /**
     * Called from ProcessFreeTrialRegistration, this Method sends the
     * Funeral Home Information data from the FreeTrialRegistration form via email to a rep.
     * If a validation error occurs, the error is stored in the errors collection.
     * Creation date: (3/18/2004 5:16:34 PM)
     */
    public void sendEmail(fdms.ui.struts.form.FreeTrialRegistration form, HttpServletRequest request, ActionErrors errors) {
        String messagebody = new String();
        String subject = new String();
        
        subject = "WebFDMS Free Trial Registration";
        messagebody = "First Name :" + form.getFirstName() + "\n";
        messagebody = messagebody + "Last Name :" + form.getLastName() + "\n";
        messagebody = messagebody + "User Email :" + form.getUserEmail() + "\n";
        messagebody = messagebody + "Funeral Home :" + form.getFuneralHomeName() + "\n";
        messagebody = messagebody + "Address1 :" + form.getAddr1() + "\n";
        messagebody = messagebody + "Address2 :" + form.getAddr2() + "\n";
        messagebody = messagebody + "City :" + form.getCity() + "\n";
        messagebody = messagebody + "State :" + form.getState() + "\n";
        messagebody = messagebody + "Zip :" + form.getZipCode() + "\n";
        messagebody = messagebody + "Phone number :" + form.getPhoneNumber() + "\n";
        messagebody = messagebody + "Other Phone :" + form.getOtherPhone() + "\n";
        messagebody = messagebody + "License Number :" + form.getLicenseNumber() + "\n";
        //AppLog.trace("In ProcessFreeTrialRegistration.sendemail." + subject + messagebody);
        
        // Setup mail server in Application.properties file in web-inf/ui/struts dir
        //You can change the values there, but you have to restart resin.
        
        MessageResources messages = this.getResources(request);
        String from = messages.getMessage("mail.freetrial.from");
        String to = messages.getMessage("mail.freetrial.to");
        String host = messages.getMessage("mail.smtp.host");
        
        
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            
            //AppLog.trace("host : " + host + " from :  "  + from + " to : "  + to);
            
            // Get session
            Session session = Session.getDefaultInstance(props, null);
            
            // Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(messagebody);
            
            // Send message
            Transport.send(message);
        }
        catch (Exception e) {
            logger.error("Catching errors in ProcessFreeTrialRegistration.sendEmail. ", e);
        }
    }
    
    
    /**
     * Called from ProcessFreeTrialRegistration, this Method validates the
     * Funeral Home Information data from the FreeTrialRegistration form.
     * If a validation error occurs, the error is stored in the errors collection.
     * Creation date: (8/28/2002 5:16:34 PM)
     */
    public void validateFuneralHomeInfo(FreeTrialRegistration form, ActionErrors errors, DbUserSession sessionUser) {
        
        String checkString = new String();
        
        try {
            
            //AppLog.trace("In ProcessFreeTrialRegistration.validateFuneralHomeInfo.");
            
            // Funeral Home Name is required.
            if (form.getFuneralHomeName() == null || form.getFuneralHomeName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFuneralHomeName"));
            } else {
                com.aldorsolutions.webfdms.beans.DbLocation chkLocation = new com.aldorsolutions.webfdms.beans.DbLocation();
                chkLocation.setId(0);
                chkLocation.setName(form.getFuneralHomeName());
                if (checkLocationExists(chkLocation, sessionUser)) {
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
            if (form.getPhoneNumber() != null && form.getPhoneNumber().trim().length() > 0) {
                checkString = form.getPhoneNumber().trim();
                checkString = checkString.replace('.','1');
                checkString = checkString.replace('-','1');
                checkString = checkString.replace('(','1');
                checkString = checkString.replace(')','1');
                checkString = checkString.replace(' ','1');
                try {
                    Double.parseDouble(checkString);
                } catch (Exception e) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidPhoneNumber"));
                }
            }
            
            // Other Phone must be numeric
            if (form.getOtherPhone() != null && form.getOtherPhone().trim().length() > 0) {
                checkString = form.getOtherPhone();
                checkString = checkString.replace('.','0');
                checkString = checkString.replace('-','0');
                checkString = checkString.replace('(','0');
                checkString = checkString.replace(')','0');
                checkString = checkString.replace(' ','0');
                try {
                    Double.parseDouble(checkString);
                } catch (Exception e) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.invalidOtherPhone"));
                }
            }
            
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
            logger.error("Catching errors in ProcessFreeTrialRegistration.validateFuneralHomeInfo. ", e);
        }
    }
    /**
     * Called from ProcessFreeTrialRegistration, this Method validates the User Information
     * data from the FreeTrialRegistration form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (8/28/2002 5:16:34 PM)
     */
    public void validateUserInfo(fdms.ui.struts.form.FreeTrialRegistration form, ActionErrors errors) {
        
        try {
            
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
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessFreeTrialRegistration.validateUserInfo. ", e);
        }
    }
}
