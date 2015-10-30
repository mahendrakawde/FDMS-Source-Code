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

import com.aldorsolutions.webfdms.beans.DbPackageList;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PackagePriceListForm;
import fdms.ui.struts.form.PackagePriceListsForm;


public class ProcessPackagePriceLists extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPackagePriceLists.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from the WebFDMSManagementEditPackagePriceLists.jsp, this action handles the
     * submit buttons which are as follows:
     * 1) redisplay:  Displays the list of packages for the selected pricelist.
     * 2) edit:  Calls ProcessPackagePriceList to setup edit screen for
     *           the selected package and pricelist.
     * 3) cancel:  Returns to WebFDMSManagement.jsp.
     *
     * @return ActionForward
     * Created 07/30/02 16:41PM
     */
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        formErrors = new ArrayList();
        PackagePriceListsForm form = (PackagePriceListsForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        PackagePriceListForm packagePriceList = new PackagePriceListForm();
        DbPriceList[] dbPriceList = null;
        DbPackageList[] dbPackageList = null;
        java.util.ArrayList packageItemsList = new java.util.ArrayList();
        java.util.ArrayList serviceIncludedList = new java.util.ArrayList();
        java.util.ArrayList serviceNotIncludedList = new java.util.ArrayList();
        java.util.ArrayList serviceIncludedNoData = new java.util.ArrayList();
        java.util.ArrayList serviceNotIncludedNoData = new java.util.ArrayList();
        
        // Get Form Directive
        String submitType = form.getSubmitbutton();
        String directive = form.getDirective();
        //AppLog.trace("Submit = " +submitType +" Directive = " +directive);
        
        if (submitType.equals("cancel")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            //AppLog.info("ProcessPackagePriceLists exiting to WebFDMSManagement");
            return actionForward;
        }
        
        // Verify that the user selected a Package List Item to edit.
        if (submitType.equals("edit")) {
            if (form.getPkgPriceListId() == null || form.getPkgPriceListId().trim().equals("")) {
                //AppLog.trace("Returning, to PackagePriceLists - no data was selected.");
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                formErrors.add("pkgPriceListId");
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                form.setSubmitbutton("");
                return (new ActionForward(mapping.getInput()));
            }
        }
        
        if (directive.equals("redisplay") && (form.getPriceListVersion() == null || form.getPriceListVersion().equals(""))) {
            return (new ActionForward(mapping.getInput()));
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Redisplay
            if (submitType.equals("") && directive.equals("redisplay")) {
                //AppLog.trace("Running ProcessPackagePriceLists redisplay logic.");
            	PriceListManager plm = new PriceListManager();
                dbPriceList = plm.getPriceListForVersion(t, form.getPriceListVersion(), sessionUser.getRegion());
                //AppLog.trace("dbPriceList length is " +dbPriceList.length);
                
                for (int i=0; i < dbPriceList.length; i++) {
                    // Grab pricelist records where PackagedItem = 'Y'
                    if (dbPriceList[i].getPackage() == 'Y') {
                        packageItemsList.add(new OptionsList(String.valueOf(dbPriceList[i].getId()),dbPriceList[i].getContrDescr()));
                    }
                }
                if (packageItemsList.size() == 0) {
                    packageItemsList.add(new OptionsList("", "No Package Items Found."));
                }
                //AppLog.trace("Set packageItemsList array size = " +packageItemsList.size());
                session.setAttribute("packageItemsList", packageItemsList);
                //AppLog.trace("Setting packageItemsList array into scope session.");
                t.closeConnection();
                return (new ActionForward(mapping.getInput()));
            }
            
            // Setup the PackagePriceList form values
            if (submitType.equals("edit")) {
                //AppLog.trace("Running ProcessPackagePriceLists logic to setup next screen.");
                dbPackageList = FdmsDb.getInstance().getPackageListForKey(t, form.getPriceListVersion(), Integer.parseInt(form.getPkgPriceListId()));
                //AppLog.trace("dbPackageList length = " +dbPackageList.length);
                for (int i=0; i < dbPackageList.length; i++) {
                    if (dbPackageList[i].getIncludedCode() == 'I') {
                        serviceIncludedList.add(new OptionsList(String.valueOf(dbPackageList[i].getPriceListId()), dbPackageList[i].getExtraDescr()));
                    } else {
                        serviceNotIncludedList.add(new OptionsList(String.valueOf(dbPackageList[i].getPriceListId()), dbPackageList[i].getExtraDescr()));
                    }
                }
                if (serviceIncludedList.size() == 0) {
                    serviceIncludedNoData.add(new OptionsList("","No Included Items found for this package."));
                    packagePriceList.setServiceIncluded("disabled");
                }
                if (serviceNotIncludedList.size() == 0) {
                    serviceNotIncludedNoData.add(new OptionsList("","No Excluded Items found for this package."));
                    packagePriceList.setServiceNotIncluded("disabled");
                }
                packagePriceList.setPriceListVersion(form.getPriceListVersion());
                packagePriceList.setPkgPriceListId(form.getPkgPriceListId());
            }
            
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPackagePriceLists.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ProcessPackagePriceLists.doPerform. ", pe);
        }
        finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        // Place Objects into scope
        session.setAttribute("packagePriceList", packagePriceList);
        session.setAttribute("serviceIncludedList", serviceIncludedList);
        //AppLog.trace("serviceIncludedList length = " +serviceIncludedList.size());
        session.setAttribute("serviceNotIncludedList", serviceNotIncludedList);
        //AppLog.trace("serviceNotIncludedList length = " +serviceNotIncludedList.size());
        session.setAttribute("serviceIncludedNoData", serviceIncludedNoData);
        session.setAttribute("serviceNotIncludedNoData", serviceNotIncludedNoData);
        //AppLog.trace("setting packagePriceList form and servicelists arrays into scope session");
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("ShowPackagePriceList");
        
        if (!errors.isEmpty() ) {
            //AppLog.info("ProcessPackagePriceLists Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        //AppLog.trace("Leaving ProcessPackagePriceLists");
        return actionForward;
    }
}
