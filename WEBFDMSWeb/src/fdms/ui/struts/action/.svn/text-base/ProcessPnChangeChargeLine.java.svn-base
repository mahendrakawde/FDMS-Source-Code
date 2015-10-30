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

import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.TempPnCharges;

import fdms.ui.struts.form.PnChangeChargeLineForm;

public class ProcessPnChangeChargeLine extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPnChangeChargeLine.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        PnChangeChargeLineForm form = (PnChangeChargeLineForm) actionForm;
        //AppLog.trace("ProcessPnChangeChargeLine beginning."+form.getVitalsId()+":"+form.getContractId()+":"+form.getLineId());
        DatabaseTransaction t =null;
        DbPnContract		acontract = null;
        ActionErrors errors = new ActionErrors();
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        int contractid = FormatNumber.parseInteger(form.getContractId());
        
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        // make sure our session is valid
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            saveErrors(request, errors);
            return new ActionForward(mapping.getInput());
        }
        // Prepare to show PnCharges page
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            int chargeid = FormatNumber.parseInteger(form.getLineId());
            int seqno	 = FormatNumber.parseInteger(form.getSequenceNumber());
            int price	 = FormatCurrency.convertToCurrency(form.getPrice());
            int taxexempt= FormatCurrency.convertToCurrency(form.getTaxExempt());
            short typeno = FormatNumber.parseShort(form.getTypeCode());
            TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);
            charges.changeAcharge(chargeid, seqno, typeno, form.getDescription(),
            price, form.getTaxCode(), taxexempt);
            
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            try {
                charges.calculateSalesTax( acontract.getPriceListVersion());
            }
            catch (Exception e){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",e.getMessage()));
            }
            
            ProcessPnStatus.preparePnCharges(errors,request,vitalsid, contractid, (DbUser) sessionUser);
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnChangeCharge. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        }
        catch(Exception pe) {
            logger.error("Exception in ProcessPnChangeCharge. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        return mapping.findForward("ShowPnChargesJspGlobal");
    }
}
