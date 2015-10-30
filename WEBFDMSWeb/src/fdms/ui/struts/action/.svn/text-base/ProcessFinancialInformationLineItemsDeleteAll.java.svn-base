package fdms.ui.struts.action;

import java.util.Iterator;
import java.util.TreeMap;

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
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialInformationForm;


public class ProcessFinancialInformationLineItemsDeleteAll extends Action {

    private Logger logger = Logger.getLogger(ProcessFinancialInformationLineItemsDeleteAll.class.getName());

    /**
     * A LINK on Financial Information page forwards to this action.
     * A parameter on the link gives the sequence number of the line to be deleted.
     * The specified line is simply removed from the charges collection and
     * the financial information page is redisplayed.
     */
    public ActionForward execute(
        ActionMapping mapping,     
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)        
        throws javax.servlet.ServletException, java.io.IOException {
                
        logger.debug("*** Entering ProcessFinancialInformationLineItemDelete ***");

        FinancialInformationForm form = (FinancialInformationForm) actionForm;
        ActionErrors errors = new ActionErrors();
        FinancialInformationLineItem fiLineItem = null;
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid = 0;
        
        HttpSession session = request.getSession();
        FinancialInformationForm financialInfo =
            (FinancialInformationForm) session.getAttribute("financialInformation");
        vitalsid = financialInfo.getVitalsId();
        
        TreeMap items = (TreeMap)financialInfo.getLineItems();
        Iterator it = items.values().iterator();
        
        while (it.hasNext()) {
            fiLineItem = (FinancialInformationLineItem) it.next();
            fiLineItem.setItemDeletion(1);
        }
        
        financialInfo.setSaveNeeded(true);

        // Calculate sales tax amount
        try {
            FdmsDb.getInstance().calculateSalesTax(
                sessionUser,
                items,
                financialInfo.getPriceListVersion(),
                vitalsid);

        } catch(Exception pe) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
            logger.error("Error in doPerform() : ", pe);
            return mapping.findForward("financialInformation");
        }

        // Sum new total of all charges in collection
        int totalcharges = SessionHelpers.sumCharges(items);
        
        logger.debug("Process Delete: old total: " + financialInfo.getTotalCharge());
        logger.debug("Process Delete: function:"+totalcharges);
        financialInfo.setTotalCharge(FormatCurrency.toCurrency((long)totalcharges));
        logger.debug("Process Delete: new total:"+financialInfo.getTotalCharge());

        return mapping.findForward("financialInformation");

    }
}
