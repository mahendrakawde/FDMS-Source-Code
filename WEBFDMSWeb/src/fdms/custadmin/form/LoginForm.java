/*
 * Created on Dec 21, 2005
 */
package fdms.custadmin.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.database.framework.DBFrameworkForm;
import com.aldorsolutions.webfdms.database.framework.Database;
import com.aldorsolutions.webfdms.database.framework.Table;

/**
 * @author Ranando
 * 
 * The form bean for customer administrative logins
 */
public class LoginForm extends ActionForm implements DBFrameworkForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8651498118428448004L;

	private Logger logger = Logger.getLogger(LoginForm.class.getName());

	private String username;

	private String password;

	public Database getDatabase() {
		return null;
	}

	public Table getTable() {
		return null;
	}

	public void setfdmsName(String uname) {
		username = uname;
		logger.debug("LoginForm.setAdminName(\"" + uname + "\");");
	}

	public void setfdmsPassword(String pword) {
		password = pword;
		logger.debug("LoginForm.setAdminPassword(\"" + pword + "\");");
	}

	public String getfdmsName() {
		return username;
	}

	public String getfdmsPassword() {
		return password;
	}

	public LoginForm() {
		super();
	}

	public void finalize() throws Throwable {
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		username = null;
		password = null;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		String u = username;
		u = "1";

		logger.debug("Called validate(). username = " + username);
		if ((u == null) || (u.trim().length() < 1)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.username.required"));
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.exception.message", String.valueOf(u.trim()
					.length())));
		}

		if ((password == null) || (password.trim().length() < 1)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.required"));
		}

		return errors;
	}
}
