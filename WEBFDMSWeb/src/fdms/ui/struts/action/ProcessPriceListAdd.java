package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbDefaultPriceList;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PriceListAddForm;


public class ProcessPriceListAdd extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPriceListAdd.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from the ProcessPriceListsAdd.jsp, this action handles the
     * add submit button.
     * @return ActionForward
     * Created 08/16/02 11:38AM
     */
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        PriceListAddForm form = (PriceListAddForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbSpeedData dbSpeedData = null;
        DbPriceList priceList = null;
        DbDefaultPriceList[] dbDefaultPriceList = null;
        DbPriceList[] dbPriceList = null;
        
        // Get Form Directive
        String submitType = form.getSubmitbutton();
        //AppLog.trace("ProcessPriceListAdd.doPerform:Submit = " +submitType);
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("showPriceListsGlobal");
        
        if ((submitType == null) || (!submitType.equals("save"))) {
            //AppLog.trace("ProcessPriceListAdd exiting to ShowPriceListsGlobal");
            return actionForward;
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Make sure the PriceListVersion is unique
            validateData(t, sessionUser, form, errors);
            if (errors.isEmpty()) {
                //AppLog.trace("ProcessPriceListAdd.validateData is valid, adding speeddata");
                dbSpeedData = new DbSpeedData();
                dbSpeedData.setNew();
                dbSpeedData.setCategory("VERSIONS");
                dbSpeedData.setLocale(sessionUser.getRegion());
                dbSpeedData.setData(form.getPriceListName().trim() +"," +form.getPriceListDescription().trim());
                t.addPersistent(dbSpeedData);
                
                if (form.getPopulateListFrom().equals("basicPriceList")) {
                    dbDefaultPriceList = FdmsDb.getInstance().getDefaultPriceListRecords(t);
                    //AppLog.trace("DefaultPriceList name is " +form.getPopulateListFrom() + " size = " +dbDefaultPriceList.length);
                    for (int i=0; i < dbDefaultPriceList.length; i++) {
                        priceList = new DbPriceList();
                        priceList.setNew();
                        priceList.setActive(dbDefaultPriceList[i].getActive());
                        priceList.setCategory(dbDefaultPriceList[i].getCategory());
                        priceList.setContrDescr(dbDefaultPriceList[i].getContrDescr());
                        priceList.setContrLine(dbDefaultPriceList[i].getContrLine());
                        priceList.setGlAcctNo(dbDefaultPriceList[i].getGlAcctNo());
                        priceList.setGPLdescr(dbDefaultPriceList[i].getGPLdescr());
                        priceList.setGPLkey(dbDefaultPriceList[i].getGPLkey());
                        priceList.setGPLprint(dbDefaultPriceList[i].getGPLprint());
                        priceList.setGPLregulated(dbDefaultPriceList[i].getGPLregulated());
                        priceList.setGridRow(dbDefaultPriceList[i].getGridRow());
                        priceList.setIncludedDescr(dbDefaultPriceList[i].getIncludedDescr());
                        priceList.setInvoiceSeqNo(dbDefaultPriceList[i].getInvoiceSeqNo());
                        priceList.setMasterDescr(dbDefaultPriceList[i].getMasterDescr());
                        priceList.setNotInclDescr(dbDefaultPriceList[i].getNotInclDescr());
                        priceList.setPackage(dbDefaultPriceList[i].getPackage());
                        priceList.setPrice(dbDefaultPriceList[i].getPrice());
                        priceList.setPriceUnit(dbDefaultPriceList[i].getPriceUnit());
                        priceList.setTaxCode(dbDefaultPriceList[i].getTaxCode());
                        priceList.setTaxExempt(dbDefaultPriceList[i].getTaxExempt());
                        priceList.setToPrice(dbDefaultPriceList[i].getToPrice());
                        priceList.setVersion(form.getPriceListName().trim());
                        priceList.setLocaleID(sessionUser.getRegion());
                        t.addPersistent(priceList);
                    }
                } else {
                	PriceListManager plm = new PriceListManager();
                    dbPriceList = plm.getPriceListForVersion(t, form.getPopulateListFrom(), sessionUser.getRegion());
                    //AppLog.trace("dbPriceList name is " +form.getPopulateListFrom() + " size = " +dbPriceList.length);
                    for (int i=0; i < dbPriceList.length; i++) {
                        priceList = new DbPriceList();
                        priceList.setNew();
                        priceList.setActive(dbPriceList[i].getActive());
                        priceList.setCategory(dbPriceList[i].getCategory());
                        priceList.setContrDescr(dbPriceList[i].getContrDescr());
                        priceList.setContrLine(dbPriceList[i].getContrLine());
                        priceList.setGlAcctNo(dbPriceList[i].getGlAcctNo());
                        priceList.setGPLdescr(dbPriceList[i].getGPLdescr());
                        priceList.setGPLkey(dbPriceList[i].getGPLkey());
                        priceList.setGPLprint(dbPriceList[i].getGPLprint());
                        priceList.setGPLregulated(dbPriceList[i].getGPLregulated());
                        priceList.setGridRow(dbPriceList[i].getGridRow());
                        priceList.setIncludedDescr(dbPriceList[i].getIncludedDescr());
                        priceList.setInvoiceSeqNo(dbPriceList[i].getInvoiceSeqNo());
                        priceList.setMasterDescr(dbPriceList[i].getMasterDescr());
                        priceList.setNotInclDescr(dbPriceList[i].getNotInclDescr());
                        priceList.setPackage(dbPriceList[i].getPackage());
                        priceList.setPrice(dbPriceList[i].getPrice());
                        priceList.setPriceUnit(dbPriceList[i].getPriceUnit());
                        priceList.setTaxCode(dbPriceList[i].getTaxCode());
                        priceList.setTaxExempt(dbPriceList[i].getTaxExempt());
                        priceList.setToPrice(dbPriceList[i].getToPrice());
                        priceList.setVersion(form.getPriceListName().trim());
                        priceList.setLocaleID(sessionUser.getRegion());
                        t.addPersistent(priceList);
                    }
                }
                //AppLog.trace("ProcessPriceListAdd.doPerform commiting work.");
                t.save();
            }
            
            form.setSubmitbutton("");
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPriceListAdd.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ProcessPriceListAdd.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty() ) {
            //AppLog.info("ProcessPriceListAdd Invoking forward mapping getInput()");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        //AppLog.trace("Leaving ProcessPriceListAdd");
        return actionForward;
    }
    /**
     * Insert the method's description here.
     * Creation date: (8/23/2002 5:48:08 PM)
     */
    /**
     * Called from ProcessInventoryCatalog, this Method validates the data
     * from the InventoryCatalog form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (7/17/2002 10:44:10 AM)
     */
    
    public void validateData(
            DatabaseTransaction t, 
            DbUserSession sessionUser, 
            fdms.ui.struts.form.PriceListAddForm form, 
            ActionErrors errors) {
        
        try {            
            //AppLog.trace("In ProcessPriceListAdd.validateData");
            
            // PriceList Name is required
            if (form.getPriceListName() == null || form.getPriceListName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.priceListAdd.nullPriceListName"));
                formErrors.add("priceListName");
            } else {
                // Make sure PriceList name is unique
                DbSpeedData[] dbCheckSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "VERSIONS", form.getPriceListName().trim());
                if (dbCheckSpeedData.length > 0) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.priceListAdd.uniquePriceListName"));
                    formErrors.add("priceListName");
                }
            }
            
            // PriceList Description is required.
            if (form.getPriceListDescription() == null || form.getPriceListDescription().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.priceListAdd.nullPriceListDesc"));
                formErrors.add("priceListDescription");
            }
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessInventoryCatalog.do validateData. ", e);
        }
    }
}
