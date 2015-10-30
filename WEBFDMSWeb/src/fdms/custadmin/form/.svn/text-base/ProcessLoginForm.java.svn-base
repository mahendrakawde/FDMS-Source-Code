/*
 * Created on Dec 22, 2005
 */
package fdms.custadmin.form;

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

import com.aldorsolutions.webfdms.database.framework.Database;
import com.aldorsolutions.webfdms.database.framework.MySQLDatabase;
import com.aldorsolutions.webfdms.database.framework.MySQLDatabaseException;

import fdms.admin.user.action.ShowMain;

/**
 * @author Ranando
 *
 * This class actually handles attempts at a user log-in. If
 * everything goes OK, we'll go to summary.jsp. Otherwise, we 
 * fall back to login.jsp. 
 */
public class ProcessLoginForm extends Action {

    private Logger logger = Logger.getLogger(ShowMain.class.getName());
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
	 		HttpServletRequest request, HttpServletResponse response)
	 		  throws javax.servlet.ServletException, java.io.IOException {

        ActionErrors errors = new ActionErrors();
        LoginForm form = (LoginForm)actionForm;
 		Database db = null; 
	 	String forward = "bad_login";

        /* Normally, we wouldn't do this manually but... */
	 	try {
			db = new MySQLDatabase();
	 	
		 	if (db != null)
		 	{
		 		if (db.logIn(form.getfdmsName(), form.getfdmsPassword())) {
		 			HttpSession session = request.getSession();
		 			session.setAttribute("SiteUserName", form.getfdmsName());
		 			session.setAttribute("SitePassword", form.getfdmsPassword());
		 			
		 			forward = "summary";
		 		}
		 	}
	 	} catch (MySQLDatabaseException e) {
	 		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
	 	} catch (Throwable e) {
	 		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.exception.message", e.getMessage()));
	 	}

        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            //request.setAttribute("formErrors", formErrors);
	 	}

        logger.debug("Forward: "+forward);
        return mapping.findForward(forward);
	 }
}
