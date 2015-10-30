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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinanceChargeForm;

public class ShowFinanceChargeFrom extends Action {

private Logger logger = Logger.getLogger(ShowFinanceChargeFrom.class.getName());

/**
* This action is a global entry point that begins the finance charge calculation process.
* It prepares the form to show FinanceChargeFrom.JSP by getting the
* date finance charges were last run.
*/
public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
{
	//AppLog.trace("ShowFinanceChargeForm beginning.");
	ActionErrors errors = new ActionErrors();
	FinanceChargeForm fromForm = new FinanceChargeForm();
	LocaleDTO alocale = null;
   	HttpSession session = request.getSession();
	DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
		return mapping.findForward("FinanceChargeFromJsp");
	}

	try {
		alocale	= FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
		fromForm.setFromDate( FormatDate.convertDateToMM_DD_YYYY(alocale.getLastFinChgDate()));
		fromForm.setThruDate( FormatDate.getCurrentDateFormatedMMDDYYYY());
	}
	catch(Exception pe) { 
		logger.error("Exception in ShowFinanceChargeFrom. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
    }
        
	// Check for any errors so far
	if( !errors.isEmpty() )   {
		saveErrors(request, errors );
   	}
	session.setAttribute("FinanceChargeForm",fromForm);	  
	return mapping.findForward("FinanceChargeFromJsp");
}    
}
