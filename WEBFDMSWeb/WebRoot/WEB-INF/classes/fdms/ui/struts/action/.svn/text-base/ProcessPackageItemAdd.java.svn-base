package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
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

import fdms.ui.struts.form.PackageItemAdd;
import fdms.ui.struts.form.PackagePriceListForm;


public class ProcessPackageItemAdd extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPackageItemAdd.class.getName());
    
    /**
     * Called from the WebFDMSManagementPackageAdd.jsp, this action handles the
     * submit buttons.  The submit buttons are as follows:
     * 1) select: saves the changes to the session attribute (packageListItems).
     * 2) exit:   Returns to WebFDMSManagementEditPackagePriceList.jsp
     *
     * @return ActionForward
     * Created 08/07/02 12:41PM
     */
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        PackageItemAdd form = (PackageItemAdd) actionForm;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbPriceList dbPriceList = null;
        PackagePriceListForm packagePriceList = (PackagePriceListForm)session.getAttribute("packagePriceList");
        String[] newItems = form.getPriceListId();
        java.util.ArrayList oldList = new java.util.ArrayList();
        java.util.ArrayList newList = new java.util.ArrayList();
        java.util.ArrayList noDataList = new java.util.ArrayList();
        int endIndex = 0;
        
        if (form.getServiceType().equals("Included")) {
            oldList = (java.util.ArrayList)session.getAttribute("serviceIncludedList");
        } else {
            oldList = (java.util.ArrayList)session.getAttribute("serviceNotIncludedList");
        }
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("ShowNewPackagePriceList");
        
        // Get Form Directive
        String submitType = form.getSubmitbutton();
        //AppLog.trace("ProcessPackageItemAdd Submit = " +submitType);
        
        if (submitType.equals("exit")) {
            //AppLog.trace("ProcessPackageItemAdd Invoking forwardShowNewPackagePriceList.");
            return actionForward;
        }
        
        //Database Access Logic
        
        if (submitType.trim().equals("save") && (newItems != null) && newItems.length > 0) {
                    
            try {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                endIndex = oldList.size();
                
                // Record(s) are to be added to the top or middle of the array.
                if (form.getAddWhere().equals("atCursor")) {
                    endIndex = packagePriceList.getOptionSelectedIndex();
                }
                if (endIndex == -1) {
                    endIndex = 0;
                }
                if (newList.addAll(oldList.subList(0, endIndex))) {
                    // AppLog.trace("newList = oldList(0 to " +endIndex +") size = " +newList.size());
                }
                
                for (int i=0; i < newItems.length; i++) {
                    String listValue = newItems[i];
                    dbPriceList = new PriceListManager().getPriceListItem(t, Integer.parseInt(listValue));
                    newList.add(new OptionsList(listValue, dbPriceList.getContrDescr()));
                }
                
                if (form.getAddWhere().equals("atCursor")) {
                    newList.addAll(oldList.subList(endIndex, oldList.size()));
                }
                
            } catch(PersistenceException pe) {
                logger.error("Persistence Exception in ProcessPackageItemAdd.doPerform. " + pe);
            } catch(Exception pe) {
                logger.error("Exception in ProcessPackageItemAdd.doPerform. " + pe);
            } finally {
                if (t != null) t.closeConnection();
            }
        }
        
        // Place Objects into scope
        
        if (form.getServiceType().equals("Included")) {
            session.setAttribute("serviceIncludedList", newList);
            if (newList.size() == 0) {
                noDataList.add(new OptionsList("","No Included Items found for this package."));
            }
            session.setAttribute("serviceIncludedNoData", noDataList);
            //AppLog.trace("setting serviceIncludedList and serviceIncludedNoData into scope session");
        } else {
            session.setAttribute("serviceNotIncludedList", newList);
            if (newList.size() == 0) {
                noDataList.add(new OptionsList("","No Excluded Items found for this package."));
            }
            session.setAttribute("serviceNotIncludedNoData", noDataList);
            //AppLog.trace("setting serviceNotIncludedList and serviceNotIncludedNoData into scope session");
        }       
        
        //AppLog.trace("ProcessPackageItemAdd Invoking forwardShowNewPackagePriceList.");
        return actionForward;
    }
}
