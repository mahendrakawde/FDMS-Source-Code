package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbPnContractPeer;
import com.aldorsolutions.webfdms.beans.DbPnContractSet;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PnCancellation;

public class ProcessPnCancellation extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPnCancellation.class.getName());
    private ArrayList formErrors = null;
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        PnCancellation form = (PnCancellation) actionForm;        
        formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        logger.debug("** ProcessPnCancellation beginning ** \n "+form.getSubmitButton()+":"+form.getVitalsId()+":"+form.getContractId());
        
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        int contractid = FormatNumber.parseInteger(form.getContractId());
        // default next page to display
        ActionForward nextAction = mapping.findForward("showPnStatus");
        SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        
        if (form.getSubmitButton().equalsIgnoreCase("cancel")){
            return nextAction;
        }
        // Verify data was entered
        java.util.Date canceldate = null;
        try {
            canceldate = FormatDate.convertToDate(form.getCancellationDate());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.canceldate"));
            formErrors.add("cancellationDate");
        }
        // verify type of action
        if (form.getCancelType() == null || form.getCancelType().compareTo("A") < 0){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.canceltype"));
            formErrors.add("cancelType");
        }
        // verfiy withdrawal amount
        int withdrawalamount=0;
        try {
            withdrawalamount = FormatCurrency.convertToCurrency(form.getWithdrawalAmount());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.withdrawalamount"));
            formErrors.add("withdrawalAmount");
        }
        if (form.getCancelType() == null || form.getCancelType().compareToIgnoreCase("P")==0 && withdrawalamount < 1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.withdrawalamount"));
            formErrors.add("withdrawalAmount");
        }
        // verify forward funds to is not empty
        if (form.getForwardName()== null || form.getForwardName().compareTo(" ") <= 0){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.forwardname"));
            formErrors.add("forwardName");
        }
        // but bail out if errors.
        if (!errors.isEmpty()) {
            logger.debug("Errors found in ProcessPnCancellation");
            request.setAttribute("PnCancellation",form);
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }

        DatabaseTransaction t = null;
        DbPreneed dbPreNeed = null;
        DbPnContract	acontract = null;
        DbPnContractSet set = new DbPnContractSet();
        java.util.Hashtable h = new java.util.Hashtable();
        DbPnContract	controfset = null;
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
            if (acontract==null || dbPreNeed==null){
                return nextAction;
            }
            if (form.getSubmitButton().equalsIgnoreCase("save")){
                logger.debug("Saving cancellation");
                // If this is a partial withdrawal then we do not
                // want to mark the contract as cancelled
                if (form.getCancelType().compareTo(DbPnContract.CANCEL_PARTIAL_WITHDRAWAL)!=0){
                    // Change pre-need case status to cancelled
                    // only if ALL contracts for this case are cancelled.
                    // If any contracts are still active, then do not mark case as cancelled
                    h.put(DbPnContractPeer.VITALSID, new Integer(vitalsid));
                    set.restore(t,h);
                    PersistentI[] obs = set.getPersistents();
                    boolean foundActive = false;
                    for(int i=0; i<obs.length; i++) {
                        controfset = (com.aldorsolutions.webfdms.beans.DbPnContract)obs[i];
                        // make sure we are looking at contracts other than the one currently being cancelled
                        if (controfset.getId() != acontract.getId() &&
                        controfset.getStatus(t)==DbPnContract.CONTRACT_STATUS_ACTIVE){
                            foundActive=true;
                        }
                    }
                    if (!foundActive){
                        dbPreNeed.setStatus(DbPreneed.CANCELLED);
                    }
                }
                // these updates are done regardless of cancel type
                acontract.setCancellationDate(canceldate);
                acontract.setCancellationType(	form.getCancelType());
                acontract.setCancellationFundsName(	form.getForwardName());
                acontract.setCancellationFundsAddr(	form.getForwardAddress());
                acontract.setCancellationFundsCity(	form.getForwardCity());
                acontract.setCancellationFundsState(form.getForwardState());
                acontract.setCancellationFundsZip(	form.getForwardZipCode());
                acontract.setPartialWithdrawalAmount(withdrawalamount);
                String ack = "N";
                if (form.isAcknowledgement()) ack="Y";
                acontract.setCancellationAcknowledge( ack );
                t.save();
            }
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnCancel. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        }
        catch(Exception pe) {
            logger.error("Exception in  ProcessPnCancel. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            nextAction = new ActionForward(mapping.getInput());
        }
        
        return nextAction;
    }
}
