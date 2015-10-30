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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.MiscCashReceiptsForm;

public class ProcessMiscCashReceipts extends Action {

    private Logger logger = Logger.getLogger(ProcessMiscCashReceipts.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {

        formErrors = new ArrayList();
        MiscCashReceiptsForm form = (MiscCashReceiptsForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbHistory dbHistory = null;
        
        if (form.getSubmitButton() != null && form.getSubmitButton().equals("exit")) {
            ActionForward actionForward = mapping.findForward("financial");
            return actionForward;
        }

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            //AppLog.trace("ProcessMiscCashReceipts submit ="+form.getSubmitButton());

            // --- HANDLE PRINTING A RECEIPT ---
            if (form.getFormId() != null && form.getFormId().trim().length() > 0 && (!form.getFormId().equals("None"))) {
                //AppLog.trace("ProcessMiscCashReceipts printing receipt form: "+form.getFormId());
                if (FormatNumber.parseInteger(form.getFormId()) < 1) {
                    //AppLog.error("ProcessMiscCashReceipts - No receipt type selected.");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                    formErrors.add("formId");
                    t.closeConnection();
                    return(new ActionForward(mapping.getInput()));
                }
            }

            if (form.getSubmitButton().equals("save")) {
                dbHistory = new DbHistory();
                dbHistory.setNew();
                validateData(form, errors);
                if (errors.isEmpty()) {
                    setHistory(t, sessionUser, dbHistory, form, errors);
                    if (errors.isEmpty()) {
                        t.save();
                    } else {
                        //AppLog.criticalError("Exception in ProcessMiscCashReceipts.setHistory.");
                    }
                } else {
                    //AppLog.trace("Validation Errors in ProcessMiscCashReceipts; returning to MiscCashReceipts form.");
                }
            }

            // --- HANDLE PRINTING A RECEIPT ---
            if (errors.isEmpty() && FormatNumber.parseInteger(form.getFormId()) > 0) {
                
                String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");
                String pageName = null;
                
                if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                    CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());                
                    pageName = crystalServerReport.printReport(sessionUser, FormatNumber.parseInteger(form.getFormId()), "", "", null, "", Integer.toString(dbHistory.getId()), true);
                }
                else
                {
                	ExportReport crystal = new ExportReport();
                	crystal.setRecordIdSelParam(dbHistory.getId()); // selection parameter
                    pageName = crystal.printForm(sessionUser, FormatNumber.parseInteger(form.getFormId()), "", "", null, "", request, response, servlet.getServletContext());
                }
                
                
                form = new MiscCashReceiptsForm();
                setNewForm(request, sessionUser, session, form, errors);
                form.setPreviewFile(pageName);
                //AppLog.trace("Setting miscCashReceipts collection arrays into session scope.");
                session.setAttribute("miscCashReceipts", form);
                ActionForward actionForward = mapping.findForward("showMiscCashReceipts");
                return actionForward;
            }

        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessMiscCashReceipts.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessMiscCashReceipts.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

        //Action Forward Logic

        ActionForward actionForward = mapping.findForward("showMiscCashReceiptsGlobal");

        if (!errors.isEmpty()) {
            //AppLog.info("ProcessMiscCashReceipts Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }

        logger.debug("Leaving ProcessMiscCashReceipts.");
        return actionForward;

    }
    /**
     * Called from ProcessMiscCashReceipts, this Method sets the History record
     * from the MiscCashReceipts form values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (8/13/2002 3:41:43 PM)
     */

    public void setHistory(DatabaseTransaction t, DbUserSession sessionUser, DbHistory dbHistory, fdms.ui.struts.form.MiscCashReceiptsForm form,
    ActionErrors errors) {

        String errorField = new String();
        String formField = null;
        DbLocale userlocale = null;
        DatabaseTransaction tlocale = null;
        int receiptNumber = 0;

        try {

            errorField = "VitalsMasterKey";            
            dbHistory.setLMainKey(0);

            errorField = "ARAcct";
            formField = "arAcct";
            dbHistory.setCHistARacct(form.getArAcct());

            errorField = "Date";
            formField = "dateOfTran";
            dbHistory.setCHistDate(new java.sql.Date(FormatDate.convertToDate(form.getDateOfTran()).getTime()));

            errorField = "Description";
            formField = "description";
            dbHistory.setCHistDesc(form.getDescription());

            errorField = "GLAcct";
            formField = "glAcct";
            dbHistory.setCHistGLAcct(form.getGlAcct());

            errorField = "ManualReceipt";
            formField = "manualReceiptNo";
            dbHistory.setCHistManualReceipt(form.getManualReceiptNo());

            errorField = "OriginalPosting";
            formField = "";
            dbHistory.setCHistOriginalPosting('N');

            errorField = "PayMethod";
            formField = "getPayMethod";
            dbHistory.setCHistPayMethod(form.getPayMethod());

            errorField = "Posted";
            formField = "";
            dbHistory.setCHistPosted('N');

            errorField = "SPF";
            formField = "";
            dbHistory.setCHistSPF('R');

            // Set Receipt Number from locale
            try {
                tlocale = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                userlocale = FdmsDb.getInstance().getLocaleWithLock(tlocale,sessionUser.getRegion());
                if (userlocale == null) {
                    throw new java.sql.SQLException("No locale for user region.");
                }
                receiptNumber = userlocale.getNextReceiptNo();
                userlocale.setNextReceiptNo(receiptNumber + 1);
                tlocale.addPersistent(userlocale);
                tlocale.save();
                //AppLog.trace("ProcessMiscCashReceipts: next receipt number = " +receiptNumber);

            } catch (java.sql.SQLException e) {
                if (e.getErrorCode()== 1205){
                    //AppLog.warning("ProcessMiscCashReceipts - DbLocale locked from updating." +sessionUser.getRegion());
                    throw new PersistenceException("User locale temporarily locked. Try again.");
                }
                //AppLog.warning("ProcessMiscCashReceipts - invalid region for user " +sessionUser.getUserName());
                throw new PersistenceException("Invalid region for user",e);
            } finally {
    			if ( t != null ) {
    				t.closeConnection();
    			}
    		}

            errorField = "ReceiptNo";
            dbHistory.setLHistReceiptNo(receiptNumber);

            errorField = "Amount";
            dbHistory.setLHistAmount(FormatNumber.parseInteger(String.valueOf(FormatCurrency.convertToCurrency(form.getAmountOfTran()))));

            errorField = "LocationId";
            dbHistory.setLocationId(Integer.parseInt(form.getLocationId()));

            t.addPersistent(dbHistory);

        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.set" +errorField));
            formErrors.add(formField);
        }

        return;

    }
    /**
     * Insert the method's description here.
     * Creation date: (11/22/2002 9:31:43 AM)
     */
    public void setNewForm(HttpServletRequest request, DbUserSession sessionUser, HttpSession session, fdms.ui.struts.form.MiscCashReceiptsForm form, ActionErrors errors) {

        DatabaseTransaction t = null;
        DbSpeedData[] dbSpeedData = null;
        DbLocation[] dbLocation = null;
        LocaleDTO userlocale = null;
        ArrayList locationList = new ArrayList();
        ArrayList glDescriptionList = new ArrayList();
        ArrayList cashAcctList = new ArrayList();
        ArrayList payMethodList = new ArrayList();
        ArrayList pleaseSelect = new ArrayList();

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
                String listValue = dbSpeedData[i].getData().substring(0,2);
                String listLabel = dbSpeedData[i].getData();
                payMethodList.add(new OptionsList(listValue, listLabel));
            }

            // Form Defaults
            form.setSubmitButton("");
            form.setAmountOfTran("0");
            form.setDateOfTran(FormatDate.getCurrentDateFormatedMMDDYYYY());
            form.setFormId("None");
            form.setReceiptNumber(String.valueOf(userlocale.getNextReceiptNo()));

            pleaseSelect.add(new OptionsList("","--Select--"));
            //AppLog.trace("Finished setting miscCashReceipts form bean");

        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessMiscCashReceipts.setNewForm. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch (Exception pe) {
            logger.error("Exception in ProcessMiscCashReceipts.setNewForm. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }

        //Set Form Bean Into Scope
        session.setAttribute("locationList", locationList);
        session.setAttribute("glDescriptionList", glDescriptionList);
        session.setAttribute("cashAcctList", cashAcctList);
        session.setAttribute("payMethodList", payMethodList);
        session.setAttribute("pleaseSelect", pleaseSelect);
        //AppLog.trace("Setting miscCashReceipts collection arrays into session scope.");

    }
    /**
     * Called from ProcessMiscCashReceipts, this Method validates the data
     * from the MiscCashReceipts form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (8/13/2002 3:41:28 PM)
     */

    public void validateData(fdms.ui.struts.form.MiscCashReceiptsForm form, ActionErrors errors) {

        try {

            // AmountOfTran is Required
            try {
                if (form.getAmountOfTran() == null || form.getAmountOfTran().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullAmountOfTran"));
                    formErrors.add("amountOfTran");
                } else {
                    if (FormatCurrency.convertToCurrency(form.getAmountOfTran()) == 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.invalidAmountOfTran"));
                        formErrors.add("amountOfTran");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.invalidAmountOfTran"));
                formErrors.add("amountOfTran");
            }

            // ArAcct
            if (form.getArAcct() == null || form.getArAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullArAcct"));
                formErrors.add("arAcct");
            }

            // DateOfTran
            if (form.getDateOfTran() == null || form.getDateOfTran().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullDateOfTran"));
                formErrors.add("dateOfTran");
            } else {
                try {
                    FormatDate.convertToDate(form.getDateOfTran());
                } catch (Exception de) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.invalidDateOfTran"));
                    formErrors.add("dateOfTran");
                }
            }

            // Description
            if (form.getDescription() == null || form.getDescription().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullDescription"));
                formErrors.add("description");
            }

            // GlAcct
            if (form.getGlAcct() == null || form.getGlAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullGlAcct"));
                formErrors.add("glAcct");
            }

            // LocationId
            if (form.getLocationId() == null || form.getLocationId().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullLocationId"));
                formErrors.add("locationId");
            }

            // PayMethod
            if (form.getPayMethod() == null || form.getPayMethod().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.nullPayMethod"));
                formErrors.add("payMethod");
            }

            // ReceiptNo is not required but must be numeric
            try {
                if (form.getReceiptNumber() != null && (!form.getReceiptNumber().trim().equals(""))) {
                    Integer.parseInt(form.getReceiptNumber());
                }
            } catch (Exception ne) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.invalidReceiptNumber"));
                formErrors.add("receiptNumber");
            }

        } catch (Exception e) {
            logger.error("Catching errors in ProcessMiscCashReceipts.validateData. ", e);
        }
    }
}
