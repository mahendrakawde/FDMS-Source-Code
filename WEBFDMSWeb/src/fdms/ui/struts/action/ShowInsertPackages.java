package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;



public class ShowInsertPackages extends Action {
    
    private Logger logger = Logger.getLogger(ShowInsertPackages.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.trace("ShowInsertPackages.doPerform");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbPriceList[] dbPriceList = null;
        fdms.ui.struts.form.FinancialSelectPackagesForm financialSelectPackages = new fdms.ui.struts.form.FinancialSelectPackagesForm();
        java.util.ArrayList financialPackagesList = new java.util.ArrayList();
        String priceListVersion = null;
        
        try {
            priceListVersion = request.getParameter("priceListVersion");
        } catch (Exception e) {
            logger.debug("Exception : ", e);
        }
        
        if (priceListVersion == null || priceListVersion.trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.finan.priceListVersion"));
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            PriceListManager plm = new PriceListManager();
            dbPriceList = plm.getPriceListForVersion(t, priceListVersion, sessionUser.getRegion());
            
            //Populate the financialPackagesList collection
            for (int i=0; i < dbPriceList.length; i++) {
                if ( (dbPriceList[i].getPackage() == 'Y') && (dbPriceList[i].getActive() != 'N')) {
                    financialPackagesList.add(new OptionsList(String.valueOf(dbPriceList[i].getId()), dbPriceList[i].getContrDescr()));
                }
            }
            
            if (financialPackagesList.size() == 0) {
                financialPackagesList.add(new OptionsList(" ", "No Packages Available"));
            }
            
            // Set the form variables.
            financialSelectPackages.setPriceListVersion(priceListVersion);
            financialSelectPackages.setVitalsId(String.valueOf(SessionHelpers.getVitalsIdFromSession(request, sessionUser)));
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in FinancialSelectPackages.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in FinancialSelectPackages.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        request.setAttribute("financialPackagesList",financialPackagesList);
        request.setAttribute("financialSelectPackages", financialSelectPackages);
        return mapping.findForward("showInsertPackages");
        
    }
}
