package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author drollins
 *
 */
public class LogonPasswordExpirationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -20329164165355788L;

	private String username;
	
	private long userID;

	private long expiresInDays;
	
	private String submitButton;

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

	/**
	 * @return Returns the expiresInDays.
	 */
	public long getExpiresInDays() {
		return expiresInDays;
	}

	/**
	 * @param expiresInDays The expiresInDays to set.
	 */
	public void setExpiresInDays(long expiresInDays) {
		this.expiresInDays = expiresInDays;
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

}