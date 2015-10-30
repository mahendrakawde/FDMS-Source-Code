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

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;



public class ShowArrangerAdmin extends Action {
    
    private Logger logger = Logger.getLogger(ShowArrangerAdmin.class.getName());
    
    /**
     * Gobal Action, this action prepares to show
     * WebFDMSArrangerAdmin.jsp.
     * The ArrangerAdminForm allows the operator to select the arranger
     * to be modified or click add or delete.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ShowArrangerAdmin.doPerform");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbArrangers[] dbArranger = null;
        java.util.ArrayList arrangerlist = new java.util.ArrayList();
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbArranger = FdmsDb.getInstance().getArrangers(t, sessionUser.getRegion());
            
            //Populate the Arranger List
            for (int i=0; dbArranger != null && i < dbArranger.length; i++) {
                String listValue = String.valueOf(dbArranger[i].getId());
                String listLabel = dbArranger[i].getName()+ ("D".equals(dbArranger[i].getDeleteCode())?" (InActive)":"");
                arrangerlist.add(new OptionsList(listValue, listLabel));
            }
            
            DbArrangers[] inactive = FdmsDb.getInstance().getInactiveArrangers(t, sessionUser.getRegion());
            for (int i=0;  inactive != null && i < inactive.length; i++) {
                String listValue = String.valueOf(inactive[i].getId());
                String listLabel = inactive[i].getName()+ ("D".equals(inactive[i].getDeleteCode())?" (InActive)":"");
                arrangerlist.add(new OptionsList(listValue, listLabel));
            }
            
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowArrangerAdmin.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ShowArrangerAdmin.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        session.setAttribute("arrangerlist",arrangerlist);
        
        return mapping.findForward("arrangerAdmin");
    }
}
