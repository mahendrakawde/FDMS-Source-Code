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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PriceListForm;


public class ProcessPriceList extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPriceList.class.getName());
    private ArrayList formErrors;
    
    /**
     * This method is called from ProcessPriceList.do.  It
     * clears the Edit Price List Item section field values.
     *
     * Creation date: (8/1/2002 3:35:49 PM)
     */
    public void clearForm(PriceListForm form) {
        //AppLog.trace("Clearing form values in ProcessPriceList.clearForm");
        form.setContractDescription("");
        form.setPrice("");
        form.setPerUnit("");
        form.setItemCategory("");
        form.setAccountDescID("");
        form.setToPrice("");
        form.setPackagedItem(false);
        form.setTaxCode("");
        form.setTaxExempt("");
        form.setActive(false);
        form.setInvoiceSeqNo("");
        form.setContractLineNumber("");
        form.setGplPrintCode(false);
        form.setGplDescription("");
        form.setGlAcct("");
        form.setCommissionable("N");
    }
    /**
     * Called from the WebFDMSManagementEditPriceList.jsp, this action handles the
     * Price List Item submit buttons and directives.  The submit buttons are as follows:
     *   1)  save:  saves the pricelist adds or changes.
     *   2)  exit:  returns to the calling screen (WebFDMSManagementEditPriceLists.jsp).
     *
     * The directives are as follows:
     *   1)  redisplay:  redisplays the form with the selected Price List Item values
     *        set in the Edit Price List Item section of the screen.  It sets the
     *        directive to 'change'.
     *   2)  statusDisplay:  redisplays the screen with a subset of the pricelist items
     *        drop-down list based on the selected status.
     *   3)  add:  displays default values in the Edit Price List Item
     *          section of the screen for this new item and allows entry and designates
     *          saving a new record.
     *   4)  change: designates saving changes to an existing record.
     *
     * @return ActionForward
     * Created 07/30/02 16:41PM
     */
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        PriceListForm form = (PriceListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        java.util.ArrayList selectList = new java.util.ArrayList();
        
        // Get Form Submit Button and Directive
        String submitType = form.getSubmitbutton();
        String directive = form.getDirective();
        //AppLog.trace("ProcessPriceList submit = " +submitType +" directive = " +directive);
        
        if (submitType.equals("exit")) {
            //AppLog.info("ProcessPriceList exiting to ShowPriceLists.do");
            ActionForward actionForward = mapping.findForward("showPriceListsGlobal");
            return actionForward;
        }
        
        // Verify that the user selected a PriceListItem to edit
        if (submitType.equals("save") && form.getPriceListId() == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("priceListId");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            form.setSubmitbutton("");
            return (new ActionForward(mapping.getInput()));
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // statusDisplay
            if (directive.equals("statusDisplay")) {
                statusDisplay(t, session, sessionUser, form);
                clearForm(form);
                selectList.add(new OptionsList(" ", " "));
                form.setDirective("");
                t.closeConnection();
                session.setAttribute("selectList",selectList);
                return (new ActionForward(mapping.getInput()));
            }
            
            // redisplay
            if (directive.equals("redisplay")) {
                clearForm(form);
                reDisplay(t, form);
                selectList.add(new OptionsList(" ", " "));
                t.closeConnection();
                session.setAttribute("selectList",selectList);
                return (new ActionForward(mapping.getInput()));
            }
            
            if (submitType.equals("") && directive.equals("add")) {
                // Set addItem defaults
                clearForm(form);
                form.setPriceListId("0");
                form.setPrice("$0");
                form.setToPrice("$0");
                form.setPackagedItem(false);
                form.setTaxExempt("$0");
                form.setActive(true);
                form.setInvoiceSeqNo("0");
                form.setContractLineNumber("0");
                form.setGplPrintCode(true);
                form.setGlAcct("");
                selectList.add(new OptionsList("--Select--","--Select--"));
                t.closeConnection();
                session.setAttribute("selectList",selectList);
                return (new ActionForward(mapping.getInput()));
            }
            
            DbPriceList dbPriceList = null;
            if (directive.equals("add")) {
                dbPriceList = new DbPriceList();
                dbPriceList.setNew();
                dbPriceList.setLocaleID(sessionUser.getRegion());
            } else {
            	PriceListManager plm = new PriceListManager();
                dbPriceList = plm.getPriceListItem(t, Integer.parseInt(form.getPriceListId()));
            }
            
            validateData(form, errors, sessionUser);
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                form.setSubmitbutton("");
                t.closeConnection();
                return (new ActionForward(mapping.getInput()));
            }
            
            setPriceList(directive, dbPriceList, form, errors);
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
                request.setAttribute("formErrors", formErrors);
                form.setSubmitbutton("");
                t.closeConnection();
                return (new ActionForward(mapping.getInput()));
            }
            
            if (directive.equals("add")) {
                t.addPersistent(dbPriceList);
            }
            t.save();
            t.closeConnection();
            //AppLog.trace("Saved record id " +dbPriceList.getId());
            clearForm(form);
            form.setSubmitbutton("");
            form.setDirective("");
            
            selectList.add(new OptionsList(" "," "));
            session.setAttribute("selectList",selectList);
            
            // New transaction
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            statusDisplay(t, session, sessionUser, form);
            form.setPriceListId(String.valueOf(dbPriceList.getId()));
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPriceList.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ProcessPriceList.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        //Action Forward Logic
        ActionForward actionForward = new ActionForward(mapping.getInput());
        
        if (!errors.isEmpty() ) {
            //AppLog.info("ProcessPriceList Invoking forward mapping getInput()");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
        }
        
        //AppLog.trace("Leaving ProcessPriceList");
        return actionForward;
    }
    /**
     * This method sets the Edit Price List Section values
     * to selected Price List Item.
     * Creation date: (8/1/2002 2:32:34 PM)
     */
    public void reDisplay(DatabaseTransaction t, PriceListForm form) {
        
        //AppLog.trace("Setting Edit Price List Item section in ProcessPriceList.reDisplay.");
    	PriceListManager plm = new PriceListManager();
        DbPriceList dbPriceList = plm.getPriceListItem(t, Integer.parseInt(form.getPriceListId()));
        form.setContractDescription(dbPriceList.getContrDescr());
        form.setPrice(FormatCurrency.toCurrency(dbPriceList.getPrice()));
        form.setPerUnit(dbPriceList.getPriceUnit());
        form.setItemCategory(String.valueOf(dbPriceList.getCategory()));
        form.setAccountDescID(String.valueOf(dbPriceList.getAccountDescCDID()));
        form.setToPrice(FormatCurrency.toCurrency(dbPriceList.getToPrice()));
        form.setGlAcct(	dbPriceList.getGlAcctNo());
        //AppLog.trace("dbPriceList.getPackage = " +dbPriceList.getPackage());
        if (dbPriceList.getPackage() == 'Y') {
           // AppLog.trace("dbPriceList.getPackage evaluated to true,");
            form.setPackagedItem(true);
        } else {
           // AppLog.trace("dbPriceList.getPackage evaluated to false,");
            form.setPackagedItem(false);
        }
        form.setTaxCode(dbPriceList.getTaxCode());
        form.setTaxExempt(FormatCurrency.toCurrency(dbPriceList.getTaxExempt()));
        //AppLog.trace("dbPriceList.getActive = " +dbPriceList.getActive());
        if (dbPriceList.getActive() == 'Y') {
            //AppLog.trace("dbPriceList.getActive evaluated to true,");
            form.setActive(true);       
        } else {
            //AppLog.trace("dbPriceList.getActive evaluated to false,");
            form.setActive(false);
        }
        form.setInvoiceSeqNo(String.valueOf(dbPriceList.getInvoiceSeqNo()));
        form.setContractLineNumber(String.valueOf(dbPriceList.getContrLine()));
        //AppLog.trace("dbPriceList.getGPLprint = " +dbPriceList.getGPLprint());
        if (dbPriceList.getGPLprint() == 'Y') {
            //AppLog.trace("dbPriceList.getGPLprint evaluated to true,");
            form.setGplPrintCode(true);
        } else {
            //AppLog.trace("dbPriceList.getGPLprint evaluated to true,");
            form.setGplPrintCode(false);
        }
        form.setGplDescription(dbPriceList.getGPLdescr());
        form.setDirective("change");
        form.setCommissionable(dbPriceList.getCommissionable());
    }
    /**
     * Called from ProcessPriceList, this Method sets the priceList record
     * from the PriceList form values.  If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (08/01/2002 4:27:04 PM)
     */
    
    public void setPriceList(String directive, DbPriceList dbPriceList, PriceListForm form, ActionErrors errors) {
        
        String errorField = new String();
        String errorString = "";
        
        try {
            //AppLog.trace("SetPriceList.directive = " +directive);
            
            // Populate priceList fields from the PriceListForm form values
            errorField = "PriceListVersion";
            errorString = "priceListVersion";
            dbPriceList.setVersion(form.getPriceListVersion());
            
            // For Add only:
            if (directive.equals("add")) {
                errorField = "GPLKey";
                errorString = "";
                dbPriceList.setGPLkey(" ");
                errorField = "MasterDescription";
                errorString = "contractDescription";
                dbPriceList.setMasterDescr(form.getContractDescription());
            }
            
            errorString = "glAcct";
            dbPriceList.setGlAcctNo(form.getGlAcct());
            errorField = "ContractDescription";
            dbPriceList.setContrDescr(form.getContractDescription());
            
            errorField = "Price";
            errorString = "price";
            dbPriceList.setPrice(FormatCurrency.convertToCurrency(form.getPrice()));
            
            errorField = "ToPrice";
            errorString = "toPrice";
            dbPriceList.setToPrice(FormatCurrency.convertToCurrency(form.getToPrice()));
            
            errorField = "ItemCategory";
            errorString = "itemCategory";
            dbPriceList.setCategory(form.getItemCategory());
            
            errorField = "AccountDescCDID";
            errorString = "accountDescCDID";
            dbPriceList.setAccountDescCDID(FormatNumber.parseLong(form.getAccountDescID()));
            
            errorField = "GplDescription";
            errorString = "gplDescription";
            dbPriceList.setGPLdescr(form.getGplDescription());
            
            // Active - set Boolean
            errorField = "Active";
            errorString = "";
            dbPriceList.setActive('N');
            //AppLog.trace("form Active = " +form.getActive());
            if (form.getActive() == true) {
                //AppLog.trace("form Active evaluated to true.");
                dbPriceList.setActive('Y');
            }
            
            // GplPriceExtension - remove "--Select--"
            errorField = "GplPriceExtension";
            errorString = "perUnit";            
            if (form.getPerUnit() == null || form.getPerUnit().trim().equals("--Select--")) {
                dbPriceList.setPriceUnit("");
            } else {
                dbPriceList.setPriceUnit(form.getPerUnit());
            }
            
            errorField = "ContractLineNumber";
            errorString = "contractLineNumber";
            dbPriceList.setContrLine(FormatNumber.parseShort(form.getContractLineNumber()));
            
            // GplPrintCode - set Boolean
            errorField = "GplPrintCode";
            errorString = "gplPrintCode";
            dbPriceList.setGPLprint('N');
            //AppLog.trace("form PrintCode = " +form.getGplPrintCode());
            if (form.getGplPrintCode() == true) {
                //AppLog.trace("form PrintCode evaluated to true.");
                dbPriceList.setGPLprint('Y');
            }
            
            // TaxCode - remove "--Select--"
            errorField = "TaxCode";
            errorString = "taxCode";
            if (form.getTaxCode() == null || form.getTaxCode().trim().equals("--Select--")) {
                dbPriceList.setTaxCode("");
            } else {
                dbPriceList.setTaxCode(form.getTaxCode());
            }
            
            errorField = "TaxExemptAmount";
            errorString = "taxExempt";
            dbPriceList.setTaxExempt(FormatCurrency.convertToCurrency(form.getTaxExempt()));
            
            // PackagedItem - set Boolean
            errorField = "PackageItem";
            errorString = "packagedItem";
            dbPriceList.setPackage('N');
            //AppLog.trace("form PackagedItem = " +form.getPackagedItem());
            if (form.getPackagedItem() == true) {
                //AppLog.trace("form PackagedItem evaluated to true.");
                dbPriceList.setPackage('Y');
            } else {
                
            }
            
            // PackagedItem - set Boolean
            errorField = "InvoiceSeqNo";
            errorString = "invoiceSeqNo";
            dbPriceList.setInvoiceSeqNo(FormatNumber.parseInteger(form.getInvoiceSeqNo()));
            dbPriceList.setCommissionable(form.getCommissionable());
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.set" +errorField));
            if (errorString != "") formErrors.add(errorString);
        }
        
        return;
        
    }
    /**
     * This method is called from ProcessPriceList.  It recreates the
     * priceListItems collections and pushes it into the session.
     * Creation date: (8/1/2002 2:22:11 PM)
     * @param form fdms.ui.struts.form.PriceListForm
     * @param request javax.servlet.http.HttpServletRequest
     */
    public void statusDisplay(DatabaseTransaction t, HttpSession session, DbUserSession sessionUser, PriceListForm form) {
        
        // Set Price List Items Array
        //AppLog.trace("Resetting PriceListItems array in ProcessPriceList.statusDisplay.");
        //AppLog.trace("Version = " +form.getPriceListVersion());
    	PriceListManager plm = new PriceListManager();
        DbPriceList[] dbPriceList = plm.getPriceListForVersion(t, form.getPriceListVersion(), sessionUser.getRegion());
        //AppLog.trace("DbPriceList size = " +dbPriceList.length);
        java.util.ArrayList priceListItems = new java.util.ArrayList();
        
        for (int i=0; i < dbPriceList.length; i++) {
            String itemStatus = form.getStatusList();
            if (itemStatus.equals("A")) {
                if (dbPriceList[i].getActive() == 'Y') {
                    priceListItems.add(new OptionsList(String.valueOf(dbPriceList[i].getId()),dbPriceList[i].getContrDescr()));
                }
            } else {
                if (itemStatus.equals("N")) {
                    if (dbPriceList[i].getActive() == 'N') {
                        priceListItems.add(new OptionsList(String.valueOf(dbPriceList[i].getId()),dbPriceList[i].getContrDescr()));
                    }
                } else {
                    priceListItems.add(new OptionsList(String.valueOf(dbPriceList[i].getId()),dbPriceList[i].getContrDescr()));
                }
            }
        }
        
        if (priceListItems.size() == 0) {
            priceListItems.add(new OptionsList("", "No Price List Items Found"));
        }
        //AppLog.trace("PriceListItems size = " +priceListItems.size());
        session.setAttribute("priceListItems",priceListItems);
        
    }
    /**
     * Called from ProcessPriceList, this Method validates the data
     * from the PriceList form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (8/01/2002 3:51:10 PM)
     */
    
    public void validateData(PriceListForm form, ActionErrors errors, DbUserSession sessionUser) {
    	
        try {
            
            /*** Required Data ***/
            
            // ContractDescription is required
            if (form.getContractDescription() == null || form.getContractDescription().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.nullContractDescription"));
                formErrors.add("contractDescription");
            }
            
            // ItemCategory is required
            if (form.getItemCategory() == null ||
            form.getItemCategory().trim().equals("--Select--") ||
            form.getItemCategory().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.nullItemCategory"));
                formErrors.add("itemCategory");
            }
            
            // InvoiceSeqNo is required
            try {
                if (form.getInvoiceSeqNo() == null || form.getInvoiceSeqNo().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.nullInvoiceSeqNo"));
                    formErrors.add("invoiceSeqNo");
                } else {
                    if (FormatNumber.parseInteger(form.getInvoiceSeqNo()) == 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidInvoiceSeqNo"));
                        formErrors.add("invoiceSeqNo");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidInvoiceSeqNo"));
                formErrors.add("invoiceSeqNo");
            }
             
            // Account Description is required
            try {
              // Account Description might be required.
              CompanyOptionsManager coMgr = new CompanyOptionsManager ();
	            	// Now check to see if this options is turned on for this customer
	            int acctDescription = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_ACCOUNT_DESCRIPTIONS);
	            	// If account descriptions are turned on then load them
	            if (acctDescription == 1) {
	            	// is Required
                if (form.getAccountDescID() == null || form.getAccountDescID().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidAccountDescription"));
                    formErrors.add("accountDescID");
                } else {
                    if (FormatNumber.parseInteger(form.getAccountDescID()) == 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidAccountDescription"));
                        formErrors.add("accountDescID");
                    }
                }
	            }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidAccountDescription"));
                formErrors.add("accountDescID");
            }
            
            // ContractLineNumber is required
            try {
                if (form.getContractLineNumber() == null || form.getContractLineNumber().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.nullContractLineNumber"));
                    formErrors.add("contractLineNumber");
                } else {
                    if (FormatNumber.parseShort(form.getContractLineNumber()) == 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidContractLineNumber"));
                        formErrors.add("contractLineNumber");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pricelist.invalidContractLineNumber"));
                formErrors.add("contractLineNumber");
            }
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessPriceList.validateData. ", e);
        }
    }
}
