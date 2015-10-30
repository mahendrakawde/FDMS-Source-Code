package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;



/**
 * ShowUserAdmin Prepares form bean with list of users.
 * Creation date: (5/13/2002 4:13:09 PM)
 * @author: 
 */
public class ShowUserAdmin extends Action {

    private Logger logger = Logger.getLogger(ShowUserAdmin.class.getName());
    
/**
 * Gobal Action, this action prepares to show
 * WebFDMSmanagementuseradmin.JSP.
 * The UserListForm Allows the operator to select the user
 * to be modified or click add or delete.
 */
  public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	logger.debug("ShowUserAdmin.doPerform");
   	ActionErrors errors = new ActionErrors();
   	HttpSession session = request.getSession();
	DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
	java.util.ArrayList userlist = new java.util.ArrayList();

	if (sessionUser == null)	{
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	
	try {
		// Set collections in session
		SessionHelpers.setUserListInSession(request);
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowUserAdmin.doPerform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in  ShowUserAdmin.doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	}
	
	// Check for any errors so far
	if (!errors.isEmpty()) {
	   saveErrors(request, errors);
   	}

	if (session.getAttribute("userList") == null) {
	   session.setAttribute("userList",userlist);
	}

	return mapping.findForward("UserAdminList");
  }  
}
