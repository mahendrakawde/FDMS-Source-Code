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

import com.aldorassist.webfdms.dao.ComboDataDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.ComboDataDTO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PriceListAddForm;
import fdms.ui.struts.form.PriceListForm;
import fdms.ui.struts.form.PriceListsForm;


public class ProcessPriceLists extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPriceLists.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from the WebFDMSManagementEditPriceLists.jsp, this action handles the
     * Edit submit button or selection double click.  It creates a list of existing
     * Price List items for the selected Price List Version and allows the user to
     * select an item from that list to edit.
     * @return ActionForward
     *
     * Created 07/30/02 16:41PM
     */
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        PriceListsForm form = (PriceListsForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        PriceListForm priceList = new PriceListForm();
        DbPriceList[] dbPriceList = null;
        DbSpeedData[] dbSpeedData = null;
        ArrayList priceListItems = new ArrayList();
        ArrayList categoryList = new ArrayList();
        ArrayList taxCodeList = new ArrayList();
        ArrayList selectList = new ArrayList();
    		ArrayList accountDescList = null;

        
        // Get Form Directive
        String directive = form.getDirective();
        
        if (directive.equals("exit")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            //AppLog.info("ProcessPriceLists exiting to WebFDMSManagement");
            return actionForward;
        }
        
        // Verify that the user selected a PriceListVersion to edit
        if (directive.equals("edit") && form.getPriceListVersion() == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("priceListVersion");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }
        
        // New Option - add
        if (directive.equals("add")) {
            ActionForward actionForward = mapping.findForward("ShowPriceListAdd");
            PriceListAddForm priceListAdd = new PriceListAddForm();
            priceListAdd.setPopulateListFrom("basicPriceList");
            session.setAttribute("priceListAdd", priceListAdd);
            return actionForward;
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Set Price List Items Array
            priceList.setPriceListVersion(form.getPriceListVersion());
            priceList.setPriceListDescription(form.getPriceListDescription());
            //AppLog.trace("Setting PriceListVersion to " +form.getPriceListVersion());
            PriceListManager plm = new PriceListManager();
            dbPriceList = plm.getPriceListForVersion(t, priceList.getPriceListVersion(), sessionUser.getRegion());
            //AppLog.trace("dbPriceList length is " +dbPriceList.length);
            for (int i=0; i < dbPriceList.length; i++) {
                // Default screen entry is for active items only
                priceList.setStatusList("A");
                if (dbPriceList[i].getActive() == 'Y') {
                    priceListItems.add(new OptionsList(String.valueOf(dbPriceList[i].getId()),dbPriceList[i].getContrDescr()));
                }
            }
            if (priceListItems.size() == 0) {
                priceListItems.add(new OptionsList("0", "No Price List Items Found"));
            }
            //AppLog.trace("Set priceListItems array size = " +priceListItems.size());
            
        	  // Populate the Account Description List object
        	  accountDescList = new ArrayList();
        	  
            CompanyOptionsManager coMgr = new CompanyOptionsManager ();
            	// Now check to see if this options is turned on for this customer
            int acctDescription = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_ACCOUNT_DESCRIPTIONS);
            	// If account descriptions are turned on then load them
            if (acctDescription == 1) {
            	session.setAttribute("accoutDescDisplay", "display");
            	
//          		accountDescList.add(new OptionsList("","--Select--"));
        	  	if (directive.equals("add")) {
        	  		priceList.setAccountDescID("");
        	  	}
        	  	
        	  	ComboDataDAO comboDataDAO = new ComboDataDAO(sessionUser);
        	  	ArrayList accountDescriptions = comboDataDAO.getComboDataOptions(ComboDataDTO.COMBODATA_LOCAL_DEFULT, 
        	  				ComboDataDTO.COMBODATA_TYPE_ACCOUNT_DESCRIPTION);
        	  		
        	  		// Now go thru the elements and 
        	  	for (int i=0; i < accountDescriptions.size(); i++) {
        	  		ComboDataDTO comboData = (ComboDataDTO)accountDescriptions.get(i);
        	  		accountDescList.add(new OptionsList(comboData.getValue(), comboData.getName()));
        	  	}
            } else {
            	session.removeAttribute("accoutDescDisplay");
            }

            // Set Category List Array
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "PLCATGRY");
            //AppLog.trace("dbSpeedData.PLCATGRY length is " +dbSpeedData.length);
            for (int i=0; i < dbSpeedData.length; i++) {
                String listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
                String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 2);
                categoryList.add(new OptionsList(listValue,listLabel));
            }
            //AppLog.trace("Set categoryList array size = " +categoryList.size());
            
            // Set TaxCode List Array
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "TaxCode");
            //AppLog.trace("dbSpeedData.TaxCode length is " +dbSpeedData.length);
            for (int i=0; i < dbSpeedData.length; i++) {
                String listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
                String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 2);
                taxCodeList.add(new OptionsList(listValue,listLabel));
            }
            //AppLog.trace("Set taxCodeList array size = " +taxCodeList.size());
            
            // SelectList should display a blank at entry
            selectList.add(new OptionsList(" "," "));
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPriceLists.doPerform. " + pe);
        }
        catch(Exception pe) {
            logger.error("Exception in ProcessPriceLists.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        //Place Objects In Scope
        session.setAttribute("priceList", priceList);
        session.setAttribute("priceListItems", priceListItems);
        session.setAttribute("categoryList", categoryList);
        session.setAttribute("taxCodeList", taxCodeList);
        session.setAttribute("selectList", selectList);
      	session.setAttribute("accountDescList", accountDescList);
        //AppLog.trace("Setting priceList form and arrays into scope session.");
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("ShowPriceList");
        
        if (!errors.isEmpty() ) {
            //AppLog.info("ProcessPriceLists Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        //AppLog.trace("Leaving ProcessPriceLists");
        return  actionForward;
    }
}
