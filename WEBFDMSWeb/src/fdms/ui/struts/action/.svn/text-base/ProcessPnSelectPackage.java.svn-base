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
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.TempPnCharges;

import fdms.ui.struts.form.PnSelectPackageForm;

public class ProcessPnSelectPackage extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPnSelectPackage.class.getName());
    
    
    /**
     * Called from the PnSelectPackage.jsp, this action handles the
     * submit buttons.  The submit buttons are as follows:
     * 1) select: adds the items in the package to the charge collection
     * 2) done:   Returns to PnCharges.jsp
     *
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        //AppLog.trace("ProcessPnSelectPackage beginning."+form.getVitalsId()+":"+form.getContractId());
        
        PnSelectPackageForm form = (PnSelectPackageForm) actionForm;
        ActionErrors errors = new ActionErrors();
        com.aldorsolutions.webfdms.beans.DbPackageList[] dbPackageList = null;
        com.aldorsolutions.webfdms.beans.DbPriceList 		priceList = null;
        DatabaseTransaction t = null;
        DbPnContract		acontract = null;
        
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        int contractid = FormatNumber.parseInteger(form.getContractId());
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbPackageList = FdmsDb.getInstance().getPackageListForKey(t, form.getPriceListVersion(), FormatNumber.parseInteger(form.getPackageName()));
            TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);
            // Process the selected package
            for (int i=0; i < dbPackageList.length; i++) {
                //AppLog.trace("Processing Insert PnPackage items:" +dbPackageList[i].getPriceListId());
                int priceId = dbPackageList[i].getPriceListId();
                //Retreive the pricelist record for this item
                priceList = new PriceListManager().getPriceListItem(t, priceId );
                if (priceList == null) {
                    //AppLog.error("Inserting PnPackage Items : Received invalid price list table ID: " +priceId);
                } else {
                    // Add price list item to Temp-pn-Charges
                    charges.addAcharge(priceList.getInvoiceSeqNo() ,priceList.getContrLine(), priceList.getContrDescr(), priceList.getPrice(),
                    "S", priceList.getId(),0, String.valueOf(priceList.getCategory()), priceList.getTaxCode(), priceList.getTaxExempt());
                }
            }
            // Update package name in contract table
            acontract = FdmsDb.getInstance().getPnContract(t,contractid);
            acontract.setPackageID( FormatNumber.parseInteger(form.getPackageName()) );
            // Calculate sales tax
            try {
                charges.calculateSalesTax( acontract.getPriceListVersion());
            }
            catch (Exception e){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",e.getMessage()));
            }
            // Save all updates in transaction
            t.save();

            // Prepare to show PnCharges page
            ProcessPnStatus.preparePnCharges(errors,request,vitalsid, contractid, (DbUser)sessionUser);
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnSelectPackage do.Perform. " + pe);
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
