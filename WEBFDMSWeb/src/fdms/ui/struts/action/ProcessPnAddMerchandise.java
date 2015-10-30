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

import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.TempPnCharges;

import fdms.ui.struts.form.PnAddMerchandiseForm;

public class ProcessPnAddMerchandise extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPnAddMerchandise.class.getName());
    
    /**
     * Called from PnCharges.JSP, this action Takes the selected
     * merchandise items and adds them to the charge collection.
     * PnCharges JSP is redisplayed with the added items.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
                
        //AppLog.trace("ProcessPnAddMerchandise beginning."+form.getVitalsId());
        
        PnAddMerchandiseForm form = (PnAddMerchandiseForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DatabaseTransaction t =null;
        DbPnContract		acontract = null;
        DbInvMaster 		invitem = null;
        
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
        // Get Inventory items to add to temp-charges
        try {
            // check if anything selected
            if (form.getSubmitButton().compareToIgnoreCase("save")==0 && (null != form.getListValue()))  {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);
                for(int i =0; i < (form.getListValue()).length ; i++) 	{
                    int itemId = FormatNumber.parseInteger((form.getListValue())[i]);
                    //Retreive A PriceList Record
                    invitem = FdmsDb.getInstance().getInvMaster(t,itemId);
                    if (invitem==null){
                        //AppLog.error("ProcessPnAddMerchandise: Received invalid Inv table ID:"+itemId);
                    }
                    else {
                        //AppLog.trace("ProcessPnAddMerchanise: adding:"+itemId+":"+invitem.getCDescription());
                        //Construct a new DbChargeItem object for this DbPriceList
                        int taxexempt = (int)((invitem.getFTaxExempt()*100)+.5);
                        charges.addAcharge(invitem.getSequenceNo() ,invitem.getContractLineNo(), 
                        		invitem.getCDescription(), invitem.getLPrice(), "S", 0,
                        		invitem.getId(), invitem.getCProductLine(), invitem.getCTaxCode(), 
                        		taxexempt);
                        
                    }
                }
                acontract = FdmsDb.getInstance().getPnContract(t, contractid);
                try {
                    charges.calculateSalesTax( acontract.getPriceListVersion());
                }
                catch (Exception e){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",e.getMessage()));
                }
            }
            ProcessPnStatus.preparePnCharges(errors,request,vitalsid, contractid, (DbUser)sessionUser);
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnAddMerchandise do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        } finally {
            if (t != null) t.closeConnection();
        }
                
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        return mapping.findForward("ShowPnChargesJspGlobal");
    }
}
