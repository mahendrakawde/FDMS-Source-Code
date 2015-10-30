package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * ShowApAccountList Prepares form bean with ApAccounts from DBMS.
 * Creation date: (5/13/2002 4:13:09 PM)
 * @author:
 */
public class ShowApAccountList extends Action {
    
    private Logger logger = Logger.getLogger(ShowApAccountList.class.getName());
    
    /**
     * Gobal Action, this action prepares to show
     * ApAccountList.JSP.
     * The ApAccountListForm Allows the operator to select the account
     * to be modified or click add or delete.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.trace("ShowApAccountList action doPerfrom");
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        try {
            // Set collections in session
            SessionHelpers.setApAccountListInSession(request,0,true) ;
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowApAccountList.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowApAccountList.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        }
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        
        return mapping.findForward("showApAccountListJsp");
    }
}
