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

import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinanceChargeForm;
import fdms.ui.struts.form.FinanceChargeSelect;

public class ShowFinanceChargeSelect extends Action {
    
    private Logger logger = Logger.getLogger(ShowFinanceChargeSelect.class.getName());
    private ArrayList formErrors;
    
    /**
     * This action is reached from FinanceChargeFrom.JSP supplying the
     * "through" date for calculation of finance charges.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        FinanceChargeForm form = (FinanceChargeForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        com.aldorsolutions.webfdms.util.TempFinanceCharges fcList = null;
        
        logger.debug("ShowFinanceChargeSelect beginning. "+form.getDirective());

        // Check for "Exit" button
        if (form.getDirective().compareToIgnoreCase("exit")==0){
            return mapping.findForward("financial");
        }
        
        // Get FROM date from form and validate
        java.sql.Date fromDate = null;
        if (form.getFromDate() == null || form.getFromDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.interestfrom"));
            formErrors.add("fromDate");
        } else {
            try {
                fromDate = new java.sql.Date(FormatDate.convertToDate(form.getFromDate()).getTime());
            } catch(Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.interestfrom"));
                formErrors.add("fromDate");
            }
        }
        // Get THROUGH date from form and validate
        java.sql.Date thruDate = null;
        if (form.getThruDate() == null || form.getThruDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.invaliddate"));
            formErrors.add("thruDate");
        } else {
            try {
                thruDate = new java.sql.Date( FormatDate.convertToDate(form.getThruDate()).getTime() );
            } catch(Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.invaliddate"));
                formErrors.add("thruDate");
            }
        }
        
        if (!errors.isEmpty()) {
            logger.debug("ShowFinanceChargeSelect Invoking forward mapping getInput().");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }
        
        // Now that we have a valid through date
        // proceed to calculate potential finance charges
        FinanceChargeSelect selectForm = new FinanceChargeSelect();
        selectForm.setFromDate(form.getFromDate());
        selectForm.setThruDate(form.getThruDate());
        try {
            fcList = FdmsDb.financeChargesCandidates(fromDate, thruDate, (DbUser)sessionUser);
            logger.debug("Finance Charges List size = " + fcList.getFcList().size());
            selectForm.setChargesBunch( fcList );
        } catch(PersistenceException pe) {            
            logger.error("Persistence Exception in ShowFinanceChargeSelect. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
            saveErrors(request, errors);
            return new ActionForward(mapping.getInput());
        }
                
        session.setAttribute("FinanceChargeSelect", selectForm);
        
        return mapping.findForward("FinanceChargeSelectJsp");
    }
}
