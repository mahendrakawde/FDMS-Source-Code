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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.MiscCashReceiptsForm;

public class ShowMiscCashReceipts extends Action {
    
    private Logger logger = Logger.getLogger(ShowMiscCashReceipts.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbSpeedData[] dbSpeedData = null;
        DbLocation[] dbLocation = null;
        LocaleDTO userlocale = null;
        
        MiscCashReceiptsForm miscCashReceipts = new MiscCashReceiptsForm();
        java.util.ArrayList locationList = new java.util.ArrayList();
        java.util.ArrayList glDescriptionList = new java.util.ArrayList();
        java.util.ArrayList cashAcctList = new java.util.ArrayList();
        java.util.ArrayList payMethodList = new java.util.ArrayList();
        java.util.ArrayList pleaseSelect = new java.util.ArrayList();
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Set the list of receipt types in session.
            SessionHelpers.setReceiptTypesInSession(request);
            
            //Populate the locationList collection
            dbLocation = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
            //AppLog.trace("DbLocation list length = " +dbLocation.length);
            for (int i=0; i < dbLocation.length; i++) {
                String listValue = String.valueOf(dbLocation[i].getId());
                String listLabel = dbLocation[i].getName();
                locationList.add(new OptionsList(listValue, listLabel));
            }
            
            //Populate the glDescriptionList collection
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "REVTYPE");
            //AppLog.trace("DbSpeedData for REVTYPE list length = " +dbSpeedData.length);
            for (int i=0; i < dbSpeedData.length; i++) {
                String listValue = CsvTable.getField(dbSpeedData[i].getData(), 2);
                String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
                glDescriptionList.add(new OptionsList(listValue, listLabel));
            }
            
            //Populate the cashAcctList collection
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "CASHTYPE");
            //AppLog.trace("DbSpeedData for CASHTYPE list length = " +dbSpeedData.length);
            for (int i=0; i < dbSpeedData.length; i++) {
                String listValue = CsvTable.getField(dbSpeedData[i].getData(), 2);
                String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
                cashAcctList.add(new OptionsList(listValue, listLabel));
            }
            
            //Populate the payMethodList", payMethodList)
            userlocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "PAYMETHOD");
            //AppLog.trace("DbSpeedData for PAYMETHOD list length = " +dbSpeedData.length);
            for (int i=0; i < dbSpeedData.length; i++) {
                if (dbSpeedData[i].getData() !=null && dbSpeedData[i].getData().length()>1){
                    String listValue = dbSpeedData[i].getData().substring(0,2);
                    String listLabel = dbSpeedData[i].getData();
                    payMethodList.add(new OptionsList(listValue, listLabel));
                }
            }
            
            // Form Defaults
            miscCashReceipts.setSubmitButton("");
            miscCashReceipts.setAmountOfTran("0");
            miscCashReceipts.setDateOfTran(FormatDate.getCurrentDateFormatedMMDDYYYY());
            miscCashReceipts.setFormId("None");
            miscCashReceipts.setReceiptNumber(String.valueOf(userlocale.getNextReceiptNo()));
            
            pleaseSelect.add(new OptionsList("","--Select--"));
            //AppLog.trace("Finished setting miscCashReceipts form bean");
            
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ShowMiscCashReceipts.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch (Exception pe) {
            logger.error("Exception in ShowMiscCashReceipts.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        //Set Form Bean Into Scope
        session.setAttribute("miscCashReceipts", miscCashReceipts);
        session.setAttribute("locationList", locationList);
        session.setAttribute("glDescriptionList", glDescriptionList);
        session.setAttribute("cashAcctList", cashAcctList);
        session.setAttribute("payMethodList", payMethodList);
        session.setAttribute("pleaseSelect", pleaseSelect);
        //AppLog.trace("Setting miscCashReceipts form bean and collection arrays into session scope.");
        
        ActionForward actionForward = mapping.findForward("miscCashReceipts");
        
        if (!errors.isEmpty()) {
            //AppLog.info("ShowMiscCahsReceipts invoking forward mapping getInput().");
            saveErrors(request, errors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        return  actionForward;
        
    }
}
