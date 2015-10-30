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

import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.LocationEditForm;


public class ProcessLocationEdit extends Action {

    private Logger logger = Logger.getLogger(ProcessLocationEdit.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {

        formErrors = new ArrayList();
        LocationEditForm form = (LocationEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbLocation dbLocation = null;
        String submitType = form.getSubmitbutton();

        if (!submitType.equals("save")) {
            return mapping.findForward("showLocationAdminGlobal");
        }

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

            // Process the Location add/change/or delete
            if (form.getDirective().equals("add")) {
                dbLocation = new DbLocation();
                dbLocation.setNew();
                dbLocation.setLocaleNumber( Integer.parseInt(form.getLocaleId()) );
                t.addPersistent(dbLocation);
            } else {
                dbLocation = FdmsDb.getInstance().getLocation(t, Integer.parseInt(form.getLocationID()));
            }

            if (form.getDirective().equals("delete")) {
                dbLocation.setInactiveCode("D");
                t.save();
            } else {
                validateFuneralHomeData(sessionUser, form, dbLocation, errors);
                if (errors.isEmpty()) {
                    setLocation(form, dbLocation, errors);

                    if (errors.isEmpty()) {
                       t.addPersistent(dbLocation);
                       t.save();
                    }
                }
            }

        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessLocationEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessLocationEdits.doPerform. ", pe);
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

        //Action Forward Logic

        ActionForward actionForward = mapping.findForward("showLocationAdminGlobal");

        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }

        return actionForward;

    }
    /**
     * Called from ProcessLocationEdit, this Method sets the Location record
     * from the WebFDMSLocationEdit.jsp values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/6/2002 10:14:50 AM)
     */
    public void setLocation(fdms.ui.struts.form.LocationEditForm form, DbLocation dbLocation, ActionErrors errors) {
        String errorField = new String();
        String errorStr = ""; 
        
        try {
            errorField = "FuneralHomeName";
            errorStr = "funeralHomeName";
            dbLocation.setName(form.getFuneralHomeName());

            errorField = "CompanyNumber";
            errorStr = "";
            dbLocation.setCompanyNumber(form.getCompanyID());

            errorField = "Division";
            errorStr = "division";
            dbLocation.setDivision(form.getDivision());
            
            errorField = "LocationNumber";
            errorStr = "locationNumber";
            dbLocation.setLocationNumber(form.getLocationNumber());
            
            errorField = "Addr1";
            errorStr = "addr1";
            dbLocation.setAddr1(form.getAddr1());

            errorField = "Addr2";
            errorStr = "addr2";
            dbLocation.setAddr2(form.getAddr2());

            errorField = "Addr3";
            errorStr = "addr3";
            dbLocation.setAddr3(form.getAddr3());

            errorField = "City";
            errorStr = "city";
            dbLocation.setCity(form.getCity());

            errorField = "State";
            errorStr = "state";
            dbLocation.setState(form.getState());

            errorField = "ZipCode";
            errorStr = "zipCode";
            dbLocation.setZip(form.getZipCode());
            
            dbLocation.setCounty(form.getCounty());

            errorField = "LicenseNumber";
            errorStr = "licenseNumber";
            dbLocation.setLicenseNumber(form.getLicenseNumber());

            errorField = "Website";
            errorStr = "website";
            dbLocation.setWebsite(form.getWebsite());

            errorField = "Email";
            errorStr = "email";
            dbLocation.setEmail(form.getEmail());
            
            errorField = "PhoneNumber";
            errorStr = "phoneNumber";
            dbLocation.setPhone(FormatString.formatPhone(form.getPhoneNumber()));

            errorField = "OtherPhone";
            errorStr = "otherPhone";
            dbLocation.setPhoneAlternate(FormatString.formatPhone(form.getOtherPhone()));

            errorField = "ApAcct";
            errorStr = "apGlAcct";
            dbLocation.setApAcct(form.getApGlAcct());

            errorField = "ArAcct";
            errorStr = "arGlAcct";
            dbLocation.setArAcct(form.getArGlAcct());

            errorField = "CashAcct";
            errorStr = "cashGlAcct";
            dbLocation.setCashAcct(form.getCashGlAcct());

            errorField = "CashBalance";
            errorStr = "checkingAcctBalance";
            dbLocation.setCashBalance(FormatCurrency.convertToCurrency(form.getCheckingAcctBalance().trim()));

            errorField = "DiscountAcct";
            errorStr = "discountGlAcct";
            dbLocation.setDiscountAcct(form.getDiscountGlAcct());

            errorField = "FinanceChargeAcct";
            errorStr = "finChrgGlAcct";
            dbLocation.setFinanceChargeAcct(form.getFinChrgGlAcct());

            errorField = "StdServiceCharge";
            errorStr = "standardServiceFee";
            dbLocation.setStdServiceCharge(FormatCurrency.convertToCurrency(form.getStandardServiceFee()));

            errorField = "NextCheckNumber";
            errorStr = "nextCheckNumber";
            if (form.getNextCheckNumber().trim().equals(""))
                form.setNextCheckNumber("0");
            dbLocation.setNextCheckNumber(Integer.parseInt(form.getNextCheckNumber().trim()));

            errorField = "PriceListVersion";
            errorStr = "priceListVersion";
            dbLocation.setPriceListVersion(form.getPriceListVersion());

            errorField = "";
            errorStr = "genericeVitals";
            dbLocation.setPreferenceGenericVitals( SessionHelpers.convertBoolToYn(form.getGenericVitals()));
            dbLocation.setManagerName(form.getManagerName());

            errorField = "UndepositedFundsAcct";
            dbLocation.setUndepositedFundsAcct(form.getUndepositedFundsAcct());

            errorField = "UseUndepositedFundsAcct";
            dbLocation.setUseUndepositedFundsAcct(form.getUseUndepositedFundsAcct());
            
            errorField = "LocaleId";
            errorStr = "localeId";
            dbLocation.setLocaleNumber(Integer.parseInt(form.getLocaleId().trim()));

            errorField = "oneTimeVendorCode";
            errorStr = "OneTimeVendorCode";
            dbLocation.setOneTimeVendorCode(form.getOneTimeVendorCode());
            
            errorField = "funeralSyncLocationId";
            errorStr = "funeralSyncLocationId";
            dbLocation.setFuneralSyncLocationId(form.getFuneralSyncLocationId());
            
            errorField = "merchandiseId";
            errorStr = "merchandiseId";
            dbLocation.setMerchandiseId(form.getMerchandiseId());
            
            errorField = "turnOnApplyFinanceCharge";
            errorStr = "turnOnApplyFinanceCharge";
            dbLocation.setTurnOnApplyFinanceCharge( SessionHelpers.convertBoolToYn(form.isTurnOnApplyFinanceCharge()));
            
            errorField = "monthlyInterestRate";
            errorStr = "monthlyInterestRate";
            dbLocation.setMonthlyInterestRate(form.getMonthlyInterestRate());
            
            errorField = "eRegisterPage";
            errorStr = "eRegisterPage";
            dbLocation.setERegisterPage(form.getRegisterPage());
            
            errorField = "eRegisterTargetPage";
            errorStr = "eRegisterTargetPage";
            dbLocation.setERegisterTargetPage(form.getRegisterTargetPage());
            
        } catch (Exception e) {
            logger.error("Exception in setLocation() : ", e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.set" +errorField));
        }

        return;

    }
    /**
     * Called from ProcessLocationEdit, this Method validates the Funeral Home
     * (location) data from WebFDMSLocationEdit.jsp. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/6/2002 10:14:32 AM)
     */
    public void validateFuneralHomeData(DbUserSession sessionUser, fdms.ui.struts.form.LocationEditForm form, DbLocation dbLocation, ActionErrors errors) {
        int checkInt = 0;

        if (form.getLocationID().equals("0")) return;
        
        try {

            // Funeral Home Name is required.
            if (form.getFuneralHomeName() == null || form.getFuneralHomeName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFuneralHomeName"));
                formErrors.add("funeralHomeName");
            } else {
                if (form.getDirective().equals("add") || (form.getDirective().equals("change") && (!form.getFuneralHomeName().trim().equals(dbLocation.getName())))) {
                    // Verify Location doesn't already exist using the Funeral Home Name.
                    com.aldorsolutions.webfdms.beans.DbLocation chkLocation = new com.aldorsolutions.webfdms.beans.DbLocation();
                    chkLocation.setId(Integer.parseInt(form.getLocationID()));
                    chkLocation.setLocaleNumber(Integer.parseInt(form.getLocaleId()));
                    chkLocation.setName(form.getFuneralHomeName());

                    if (com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocationExists(sessionUser, chkLocation)) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.homeExists"));
                        formErrors.add("funeralHomeName");
                        return;
                    }
                }
            }

            // Address Line 1 is required.
            if (form.getAddr1() == null || form.getAddr1().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullAddr1"));
                formErrors.add("addr1");
            }
            // City is required.
            if (form.getCity() == null || form.getCity().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullCity"));
                formErrors.add("city");
            }
            // State is required.
            if (form.getState() == null || form.getState().trim().equals("") || form.getState().trim().equals("--Select--")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullState"));
                formErrors.add("state");
            }

            // Zip Code is required and must be numeric
            if (form.getZipCode() == null || form.getZipCode().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullZipCode"));
                formErrors.add("zipCode");
            }


            // AP Acct is required
            if (form.getApGlAcct() == null || form.getApGlAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullAPAcct"));
                formErrors.add("apGlAcct");
            }

            // AR Acct is required
            if (form.getArGlAcct() == null || form.getArGlAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullARAcct"));
                formErrors.add("arGlAcct");
            }

            // Cash Acct is required
            if (form.getCashGlAcct() == null || form.getCashGlAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullCashAcct"));
                formErrors.add("cashGlAcct");
            }

            // Discount Acct is required
            if (form.getDiscountGlAcct() == null || form.getDiscountGlAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullDiscountAcct"));
                formErrors.add("discountGlAcct");
            }

            // Finance Charge Acct is required
            if (form.getFinChrgGlAcct() == null || form.getFinChrgGlAcct().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullFinChrgAcct"));
                formErrors.add("finChrgGlAcct");
            }

            // Next Check Number is required and must be numeric
            try {
                if (form.getNextCheckNumber() == null || form.getNextCheckNumber().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullNextCheckNumber"));
                    formErrors.add("nextCheckNumber");
                } else {
                    checkInt = Integer.parseInt(form.getNextCheckNumber());
                    if (checkInt < 1) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.invalidNextCheckNumber"));
                        formErrors.add("nextCheckNumber");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.invalidNextCheckNumber"));
                formErrors.add("nextCheckNumber");
            }

            // Checking Acct Balance is required and must be numeric
            try {
                if (form.getCheckingAcctBalance() == null || form.getCheckingAcctBalance().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullCheckingAcctBalance"));
                    formErrors.add("checkingAcctBalance");
                } else {
                    checkInt = FormatCurrency.convertToCurrency(form.getCheckingAcctBalance());
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.invalidCheckingAcctBalance"));
                formErrors.add("checkingAcctBalance");
            }

            // Standard Service Fee is required
            try {
                if (form.getStandardServiceFee() == null || form.getStandardServiceFee().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.nullStandardServiceFee"));
                    formErrors.add("standardServiceFee");
                } else {
                    checkInt = FormatCurrency.convertToCurrency(form.getStandardServiceFee());
                    if (checkInt < 1) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.invalidStandardServiceFee"));
                        formErrors.add("standardServiceFee");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.invalidStandardServiceFee"));
                formErrors.add("standardServiceFee");
            }
            // Price List Version is required
            if (form.getPriceListVersion() == null || form.getPriceListVersion().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.location.nullPriceListVersion"));
                formErrors.add("priceListVersion");
            }

        } catch (Exception e) {
            logger.error("Catching errors in ProcessLocationEdit.validateFuneralHomeData. ", e);
        }
    }
}
