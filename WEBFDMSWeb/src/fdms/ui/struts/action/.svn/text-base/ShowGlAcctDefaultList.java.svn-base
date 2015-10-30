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
 * ShowGlAcctDefaultList Prepares form bean with GL default accounts from DBMS.
 * Creation date: (6/1/2003 4:13:09 PM)
 * @author: 
 */
public class ShowGlAcctDefaultList extends Action {

    private Logger logger = Logger.getLogger(ShowGlAcctDefaultList.class.getName()); 
    
/**
 * Gobal Action, this action prepares to show
 * glAcctDefaultList.JSP.
 * The form Allows the operator to select the account
 * to be modified or click add or delete.
 */
  public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	//AppLog.trace("ShowGlAccountDefaultList action doPerfrom");
   	ActionErrors errors = new ActionErrors();
   	HttpSession session = request.getSession();
	DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	try {
		// Set collections in session
		SessionHelpers.setGlAccountDefaultListInSession(request) ;
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowGlAccountDefaultList.doPerform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in  ShowGlAccountDefaultList.doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	}
	// Check for any errors so far
	if( !errors.isEmpty() )   {
		saveErrors(request, errors );
   	}
	  
	return mapping.findForward("showGlAcctDefaultListJsp");
  }        
}
