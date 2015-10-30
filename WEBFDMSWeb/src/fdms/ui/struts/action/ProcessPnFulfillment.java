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

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PnFulfillment;

public class ProcessPnFulfillment extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPnFulfillment.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("PnFulfillment beginning: "+form.getSubmitButton()+":"+form.getVitalsId()+":"+form.getContractId());
        
        PnFulfillment form = (PnFulfillment) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        int contractid = FormatNumber.parseInteger(form.getContractId());
        SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        
        // default next page to display
        ActionForward nextAction = mapping.findForward("showPnStatus");
        // check for cancel action
        if (form.getSubmitButton().equalsIgnoreCase("cancel")){
            return nextAction;
        }
        
        DatabaseTransaction t = null;
        DbPnContract	acontract = null;
        DbPreneed dbPreNeed = null;
        DbVitalsDeceased	bene = null;
        DbCase 				caseinfo = null;
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
            bene	  = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            caseinfo  = FdmsDb.getInstance().getCase(t, vitalsid);
            if (acontract==null || dbPreNeed==null || bene==null || caseinfo==null){
                //AppLog.error("ProcessPnFulfillment null db object:"+" "+acontract+" "+dbPreNeed+" "+bene+" "+caseinfo);
                return nextAction;
            }
            if (form.getSubmitButton().equalsIgnoreCase("save")){
                dbPreNeed.setStatus(DbPreneed.SERVICED);
                acontract.setFulfillmentContactName(	form.getContactName());
                acontract.setFulfillmentContactPhone(FormatString.formatPhone(form.getContactPhone()));                
                bene.setDateOfDeath(					FormatDate.convertToDateMMDDYYYY(form.getDeathDate()));
                caseinfo.setDeathDate(FormatDate.convertToDateYYYYMMDD(form.getDeathDate()));
                t.save();
            }

        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnFulfillment. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        }
        catch(Exception pe) {
            logger.error("Exception in  ProcessPnFulfillment. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        return nextAction;
    }
}
