package fdms.ui.struts.action;

import java.sql.Timestamp;

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

import com.aldorsolutions.webfdms.beans.DbPasswordLog;
import com.aldorsolutions.webfdms.beans.DbSecurityConfig;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.util.Encryptor;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.LogonChangePasswordForm;

public class ProcessLogonChangePassword extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLogonChangePassword.class.getName());

    /**
     * Called from login.jsp, this action either verifies a user has access to the system
     * or logs into demo database or allows setting up a free trial.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("*** Entering perform() in ProcessLogonChangePassword ***");
            
        LogonChangePasswordForm pForm = (LogonChangePasswordForm) form;
        logger.debug( "LogonChangePasswordForm: " + pForm );
        
        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();
        
        DbUserSession userSession = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        DbUser user = (DbUser) userSession;
        
        String submitType = new String(pForm.getSubmitButton());
        logger.debug("Submit type : " + submitType);
        
        if ( user == null ) {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
            saveErrors(request, errors);
            return (mapping.findForward("logon"));
        }
        
        // Validate the request parameters specified by the user
        String username = pForm.getUsername();
        String userID =   new Long ( pForm.getUserID() ).toString();
        String password = user.getPassword();
        String oldPassword = pForm.getOldPassword();
        String newPassword = pForm.getNewPassword();
        String confirmPassword = pForm.getConfirmPassword();

        // See if this user is already logged into the system.
        if (pForm.getSubmitButton().equals("change")) {            
        	
//        	password = Encryptor.encrypt(password); // TODO: Will already be encrypted
            oldPassword = Encryptor.encrypt(oldPassword);
            
        	if ( !oldPassword.equals(password) ) {
        		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
                saveErrors(request, errors);
                return (mapping.findForward("logon"));
        	}
        	
        	DatabaseTransaction t = null;
        	SecurityManagerBean mbSecurity = new SecurityManagerBean ();
            
            //Database Access Logic
            try {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(userSession);
                t.setReadOnly(false);
                
            	DbSecurityConfig secureConfig = FdmsDb.getInstance().getSecurityConfig(t, user.getCompanyID());
                
            	if ( !newPassword.equals(confirmPassword) ) {
            		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.match"));
                    saveErrors(request, errors);
                    return (new ActionForward(mapping.getInput()));
            	}
            	
            	if ( mbSecurity.isPasswordComplexityValid(username, newPassword, secureConfig) == false ) {
            		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.complexity"));
                    saveErrors(request, errors);
                    return (new ActionForward(mapping.getInput()));
            	}
            	
            	newPassword = Encryptor.encrypt(newPassword);
            	
            	if ( secureConfig.isPasswordNotRepeated() ) {
                
            		DbPasswordLog[] passLog = FdmsDb.getInstance().getPasswordLogSet(t, userID, newPassword);

					if (passLog != null && passLog.length > 0) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.repeated"));
						saveErrors(request, errors);
						return (new ActionForward(mapping.getInput()));
					}
            	}
                
	        } 
            catch(PersistenceException pe) {
	            logger.error("Persistence Exception in ProcessLogonChangePassword.doPerform. " + pe);
	        } 
            finally {
	        	if (t != null) t.closeConnection();
	        }
            
            updatePassword(userSession, user, newPassword, errors, false);
        	
            
        }
        else {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
            saveErrors(request, errors);
            return (new ActionForward("logon"));
        }
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            //AppLog.info("Invoking forward mapping getInput");
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        
        session.removeAttribute("passwordPatternDesc");
        
        return mapping.findForward("introduction");
    }
    

	private ActionErrors updatePassword ( DbUserSession sessionUser, DbUser user, String password, ActionErrors errors, 
			boolean isAdmin ) {
		
		DatabaseTransaction t = null;
		
		try {
			SecurityManagerBean smBean = new SecurityManagerBean();
			Timestamp tmstmp = smBean.insertPasswordLog ( sessionUser, password );
    		
            String userLookup = UtilSingleton.getInstance().getUserDBLookup();
            
    		//t = (DatabaseTransaction) DatabaseTransaction.getTransaction( DbUser.URL, dbProps, sessionUser);
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser, userLookup);
            t.setReadOnly(false);
    		
    		t.addPersistent(user);
    		user.setPassword(password);
    		user.setPasswordTimestamp(tmstmp);
    		user.setChangePassword(isAdmin);
    		
    		t.save();
		}
		catch(PersistenceException pe) {
            logger.error("Persistence Exception in SecurityManagerBean updatePassword " + pe, pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  SecurityManagerBean updatePassword ",  pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.GeneralException",pe.getMessage()));
        }
        finally {
        	if ( t != null ) {
        		t.closeConnection();
        	}
        }
		
		return ( errors );
	}

}
