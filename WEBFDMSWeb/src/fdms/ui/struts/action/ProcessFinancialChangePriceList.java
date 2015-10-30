package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.ui.struts.form.FinancialChangePriceListForm;


public class ProcessFinancialChangePriceList extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFinancialChangePriceList.class.getName());

    /**
     * Called from FinancialChangePriceList.jsp. This action takes the selected
     * price list version from the JSP and updates the FinancialInformationForm
     * property where the current version is kept for this session.
     * When the financial information page is saved, this version is stored in
     * the financial table.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        ActionErrors errors = new ActionErrors();
        // AppLog.trace("ProcessPriceListChange. price list:"+form.getPriceChange());
        HttpSession session = request.getSession();
        FinancialChangePriceListForm form = (FinancialChangePriceListForm) actionForm;
        
        // Make sure PriceChange was selected.
        if (form.getPriceChange() == null || form.getPriceChange().trim().length() == 0) {
            // AppLog.trace("ProcessFinancialChangePriceList canceling price change, returning to FinancialInformation.");
            return mapping.findForward("financialInformation");
        }
        
        fdms.ui.struts.form.FinancialInformationForm financialInfo = (fdms.ui.struts.form.FinancialInformationForm) session.getAttribute("financialInformation");
        if (form.getPriceChange().compareTo(" ")> 0){
            financialInfo.setPriceListVersion( form.getPriceChange() );
        }
        return mapping.findForward("financialInformation");
    }
}
