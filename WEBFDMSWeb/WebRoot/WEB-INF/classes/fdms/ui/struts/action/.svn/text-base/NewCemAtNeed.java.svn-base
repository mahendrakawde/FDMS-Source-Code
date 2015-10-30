package fdms.ui.struts.action;

// Cloned and modified by JO - QPRIME - SOW: F030601A Cemetery Management System

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

import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.CemAnStatus;

public class NewCemAtNeed extends Action {
    
    private Logger logger = Logger.getLogger(NewCemAtNeed.class.getName());    
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        CemAnStatus cemAnStatus;  
        DatabaseTransaction t = null;
        
        sessionUser.setCurrentCaseID(0);   
        
        try {
            cemAnStatus = new CemAnStatus();
            // Get the next contract number from locale record.
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            cemAnStatus.setDirective("add");
            // Add form to session
            request.setAttribute("cemAnStatus",cemAnStatus);
            
            DbLocation[] dbLocations = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());            
            request.setAttribute("chapels", dbLocations);            
        } catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in NewAtNeed.doPerform. "+pe.getCause());
            
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
            logger.error("PersistenceException in doPerform() : " + pe);
        } catch(Exception pe) {
            // AppLog.criticalError("Exception in BilltoAddChange .doPerform. "+pe);
            // pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
					t.closeConnection();
					t = null;
				}  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
//        JOptionPane.showMessageDialog(null, "newCemAtneed ");
        ActionForward actionForward = mapping.findForward("newAtNeedToFirstCallJsp");
        if( !errors.isEmpty() ) {
            // AppLog.info("NewAtNeed Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );            
        }
        
        return  actionForward;
    }
}
