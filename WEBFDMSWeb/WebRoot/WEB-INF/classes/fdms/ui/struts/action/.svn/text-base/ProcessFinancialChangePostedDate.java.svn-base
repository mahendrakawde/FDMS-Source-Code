package fdms.ui.struts.action;

import java.util.ArrayList;
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

import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.TransactionhistoryDAO;
import com.aldorsolutions.webfdms.beans.TransactionhistoryDTO;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialAddServicesForm;
import fdms.ui.struts.form.FinancialChangePostedDateForm;
import fdms.ui.struts.form.FinancialInformationForm;


public class ProcessFinancialChangePostedDate extends Action {

    private Logger logger = Logger.getLogger(ProcessFinancialChangePostedDate.class.getName());
    private ArrayList formErrors;
    /**
     * Called from FinancialAddServices.JSP, this action Takes the selected
     * charge items and adds them to the charge collection.
     * Financial Information JSP is redisplayed with the added items.
     */
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request,
            HttpServletResponse response)        
        throws javax.servlet.ServletException, java.io.IOException {
                                            
        logger.debug("*** Entering ProcessFinancialAddServices ***");
       
        FinancialChangePostedDateForm form = (FinancialChangePostedDateForm) actionForm;        
        formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid = 0;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput()));
        }
        
        // check if anything selected
        if (form.getSubmitButton().equals("change") )  {
          	TransactionhistoryDTO tran = new TransactionhistoryDTO();
           	TransactionhistoryDAO tranDao = new TransactionhistoryDAO(sessionUser);
           	
           	boolean success = false;
           	try {
	           	java.sql.Date newDate = FormatDate.convertToSQLDate(form.getToPostedDate());
	           	success = tranDao.updatePostedDate(vitalsid, newDate);
	           	String invNewDate = FormatDate.convertDateToMMDDYYYY(new java.util.Date(newDate.getTime()));
	           	success = tranDao.updateInvhistoryTrnasactionDate(vitalsid, invNewDate);
	           	
           	} catch(Exception e){
            	
            		errors.add(ActionErrors.GLOBAL_ERROR, 
            				new ActionError("error.ui.financial.toposteddate"));
                    formErrors.add("toPostedDate");
//                    return mapping.findForward("financialChangePostedDate");
            }
           	
           	
        }
        
        if( !errors.isEmpty()) {
            saveErrors(request, errors );
        }
        
        return mapping.findForward("showFinancialInformation");
        //return mapping.findForward("qtyFinancialInformation");
    }
}
