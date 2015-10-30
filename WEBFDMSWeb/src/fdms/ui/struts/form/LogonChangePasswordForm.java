package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author drollins
 *
 */
public class LogonChangePasswordForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6333112851000985700L;

	private String username;
	
	private long userID;

	private boolean userLocked;

	private boolean changePassword;

	private String oldPassword;
	
	private String newPassword;
	
	private String confirmPassword;
	
	private String submitButton;
	
	/**
	 * @return Returns the changePassword.
	 */
	public boolean isChangePassword() {
		return changePassword;
	}

	/**
	 * @param changePassword The changePassword to set.
	 */
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	/**
	 * @return Returns the confirmPassword.
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword The confirmPassword to set.
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return Returns the newPassword.
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword The newPassword to set.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return Returns the oldPassword.
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword The oldPassword to set.
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return Returns the submitButton.
	 */
	public String getSubmitButton() {
		return submitButton;
	}

	/**
	 * @param submitButton The submitButton to set.
	 */
	public void setSubmitButton(String submitButton) {
		this.submitButton = submitButton;
	}

	/**
	 * @return Returns the userLocked.
	 */
	public boolean isUserLocked() {
		return userLocked;
	}

	/**
	 * @param userLocked The userLocked to set.
	 */
	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Returns the userID.
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * @param userID The userID to set.
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		username = null;
		userID = 0;
		userLocked = false;
		changePassword = false;
		oldPassword = null;
		newPassword = null;
		confirmPassword = null;
		submitButton = null;
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ArrayList formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        
//        MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
//        MessageResources resources = messageFactory.createResources("ApplicationResources");
        
		if (oldPassword == null || oldPassword.trim().equals("") ) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.password.required"));
			formErrors.add("oldPassword");
		}
		
		if (newPassword == null || newPassword.trim().equals("") ) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.password.required"));
			formErrors.add("newPassword");
		}

		if (confirmPassword == null || confirmPassword.trim().equals("") ) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.password2.required"));
			formErrors.add("confirmPassword");
		}

		if (confirmPassword != null && newPassword != null ) {
			
			if ( confirmPassword.equals(newPassword) == false )
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.password.match"));
			formErrors.add("newPassword");
		}
		        	
        if (formErrors.size() > 0) {
        	request.setAttribute("formErrors", formErrors);
        }
        
        return errors;
    }

}