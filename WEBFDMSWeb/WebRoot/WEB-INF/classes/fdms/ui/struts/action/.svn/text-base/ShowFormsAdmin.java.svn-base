package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.manager.FormsAvailableManager;

public class ShowFormsAdmin extends Action {

	private Logger logger = Logger.getLogger(ShowFormsAdmin.class.getName());

	/**
	 * Gobal Action, this action prepares to show
	 * WebFDMSFormsAdmin.jsp.
	 * The FormsAdminForm allows the operator to select the forms
	 * to be modified or click add or delete.
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
    ArrayList <OptionsList> formslist = new ArrayList <OptionsList> ();
    FormsAvailableManager faManager = new FormsAvailableManager();
    ActionErrors errors = new ActionErrors();
    DbUserSession sessionUser = SessionHelpers.getUserSession(request);
    HttpSession session = request.getSession();
            
    if (sessionUser == null)	{
      errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
    }
        
    //Database Access Logic
    try {
      formslist = faManager.getFormsAvailable(sessionUser);
    } catch(PersistenceException pe) {
      logger.error("Persistence Exception in ShowFormsAdmin.doPerform. " + pe);
      errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
    } catch(Exception pe) {
      logger.error("Exception in  ShowFormsAdmin.doPerform. ", pe);
      errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
    }
        
    // Check for any errors so far
    if (!errors.isEmpty()) {
      saveErrors(request, errors);
    }
    request.getSession().setAttribute("FDMS_SA", Boolean.TRUE);
    session.setAttribute("formslist",formslist);
    return mapping.findForward("formsAdmin");
  }
}
