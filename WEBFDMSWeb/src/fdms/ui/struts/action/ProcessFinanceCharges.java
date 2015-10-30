package fdms.ui.struts.action;

import java.util.ArrayList;

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

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.FinanceCharge;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinanceChargeSelect;

public class ProcessFinanceCharges extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFinanceCharges.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ProcessFinanceCharges beginning. processing thru:"+form.getThruDate()+" action:"+form.getDirective());
        
        FinanceChargeSelect form = (FinanceChargeSelect) actionForm;
        DatabaseTransaction t 			= null;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        LocaleDTO alocale = null;
        ArrayList fcList = null;
        FinanceCharge aCharge = null;
        java.sql.Date thruDate=null;
        
        // Bail out if cancel button clicked
        if (form.getDirective().compareToIgnoreCase("cancel")==0){
            return mapping.findForward("financial");
        }
        
        try {
            thruDate = new java.sql.Date ( FormatDate.convertToDate(form.getThruDate()).getTime() );
        }
        catch(Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.invaliddate"));
        }        
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            // AppLog.info("ShowFinanceChargeSelect Invoking forward mapping getInput().");
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        try {
            fcList = form.getChargesBunch().getFcList();
            java.util.Iterator myIterator = fcList.iterator();
            int fcamt;
            while (myIterator.hasNext() ){
                aCharge  = (FinanceCharge)myIterator.next();
                //AppLog.trace("Processing1: "+aCharge.isSelected()+" "+aCharge.getVitalsId()+" "+aCharge.getDeceasedName()+" "+aCharge.getFinanceChargeString());
                try {
                    fcamt = FormatCurrency.convertToCurrency(aCharge.getFinanceChargeString());
                    aCharge.setFinanceChargeAmount(fcamt);
                }
                catch (Exception e){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.fc.invalidamount",aCharge.getDeceasedName()));
                }
            }
            if (!errors.isEmpty()){
                saveErrors(request, errors);
                return (new ActionForward(mapping.getInput()));
            }
            // Loop thru finance charges again to update data file
            myIterator = fcList.iterator();
            while (myIterator.hasNext() ){
                aCharge  = (FinanceCharge)myIterator.next();
                // AppLog.trace("Processing2: "+aCharge.isSelected()+" "+aCharge.getVitalsId()+" "+aCharge.getDeceasedName()+" "+aCharge.getFinanceChargeString());
                try {
                    fcamt = FormatCurrency.convertToCurrency(aCharge.getFinanceChargeString());
                    aCharge.setFinanceChargeAmount(fcamt);
                    if (aCharge.isSelected() && fcamt > 0){
                        FdmsDb.financeChargesApply((DbUser)sessionUser, aCharge, thruDate);
                        aCharge.setSelected(false);  // can't remove so flag as processed, in case we go back to input jsp page
                        aCharge.setFinanceChargeAmount(0);
                        aCharge.setFinanceChargeString("0.00");
                    }
                }
                catch (Exception e){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.fc.invalidamount",aCharge.getDeceasedName()));
                }
            }
            
            // Update "Last Run" date for this location
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(),sessionUser.getRegion());
            alocale.setLastFinChgDate(thruDate);
            
            LocaleDAO localeDAO = new LocaleDAO();
            localeDAO.updateLocale(alocale, sessionUser.getDbLookup());
            
            // AppLog.trace("Updating locale#"+sessionUser.getRegion()+" "+alocale.getName()+" with last finance charge. date:"+thruDate);
            t.save();
        }  catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in ShowFinanceChargeSelect. "+pe);
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch (Exception e) {
            logger.error("Error : ", e);
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
        
        if (!errors.isEmpty()){
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        return mapping.findForward("financial");
    }
}
