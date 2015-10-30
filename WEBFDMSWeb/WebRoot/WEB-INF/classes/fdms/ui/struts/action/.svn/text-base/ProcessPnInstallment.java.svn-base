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

import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.Amortization;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FutureValue;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PnInstallmentForm;

public class ProcessPnInstallment extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPnInstallment.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {

        PnInstallmentForm form = (PnInstallmentForm) actionForm;
        formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        DatabaseTransaction t = null;
        DbPnContract acontract = null;
        DbVitalsDeceased bene = null;
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        logger.debug("ProcessPnInstallment beginning. "+form.getSubmitButton()+":"+form.getVitalsId()+":"+form.getContractId());
        
        ActionForward nextPage = mapping.findForward("showPnStatus"); // default to show status page next
        
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        int contractid = FormatNumber.parseInteger(form.getContractId());
        // Check for CANCEL action
        if (form.getSubmitButton().compareToIgnoreCase("cancel")==0){
            SessionHelpers.setVitalsIdInRequest(request,vitalsid);
            return nextPage;
        }
        
        int totalCharges = 0;        
        try {
            totalCharges = FormatCurrency.convertToCurrency(form.getTotalCharges())	;
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.amount"));
            formErrors.add("totalCharges");
        }
        
        int downPayment = 0;
        try {
            downPayment = FormatCurrency.convertToCurrency(form.getDownPayment());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.downpayment"));
            formErrors.add("downPayment");
        }
        
        int numPayments = 0;
        try {
            numPayments = FormatNumber.parseInteger(form.getNumberOfPayments());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.numpayments"));
            formErrors.add("numberOfPayments");
        }
        
        double intRate = 0d;
        try {
            intRate = FormatNumber.parseFloat(form.getInterestRate());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.intrate"));
            formErrors.add("interestRate");
        }
        
        double inflation = 0;
        try {
            inflation = FormatNumber.parseFloat(form.getInflationRate()) ;
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.inflation"));
            formErrors.add("inflationRate");
        }
        
        java.util.Date firstpmtdate = null;
        try {
            firstpmtdate = FormatDate.convertToDate(form.getFirstPaymentDate());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.firstpmtdate"));
            formErrors.add("firstPaymentDate");
        }
        
        int paymentAmount = 0;
        try {
            paymentAmount = FormatCurrency.convertToCurrency(form.getPaymentAmt());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.amount"));
            formErrors.add("paymentAmt");
        }
        
        int financeCharge = 0;
        try {
            financeCharge = FormatCurrency.convertToCurrency(form.getFinanceCharge() );
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.financecharge"));
            formErrors.add("financeCharge");
        }                
        
        double termYears = numPayments / 12.0;
        Amortization a = new Amortization(((totalCharges-downPayment)/100.0), intRate, 12.0, termYears);
        FutureValue F = new FutureValue((totalCharges/100.0), inflation, 12.0, termYears);
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            logger.debug("Getting pn contract info");
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            logger.debug("Getting vitals info");
            bene = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            
            if (bene == null){
                logger.debug("Errors found");
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
                saveErrors(request, errors);
                if (formErrors.size() > 0) request.setAttribute("formErrors", formErrors);              
                return nextPage;
            }
            
            if (acontract == null){
                logger.debug("Errors found");
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                saveErrors(request, errors);
                if (formErrors.size() > 0) request.setAttribute("formErrors", formErrors);
                return nextPage;
            }
            
            form.setContractCode(Integer.toString(acontract.getContractNumber()));
            form.setFullName(bene.getDecFName()+" "+bene.getDecLName());
            
            // check action
            if (form.getSubmitButton().compareToIgnoreCase("recalc")==0){
                logger.debug("Recalculating info");
                
                if (numPayments > 0 && intRate > 0){
                    form.setPaymentAmt(		FormatCurrency.toCurrency((long)a.getPaymentAmount()));
                    form.setFinanceCharge(	FormatCurrency.toCurrency((long)a.getTotalFinanceCharges()) );
                    form.setTotalPayments(	FormatCurrency.toCurrency((long)(a.getTotalAllPayments())));
                    form.setTotalSalePrice(	FormatCurrency.toCurrency((long)(a.getTotalAllPayments()+downPayment)));
                }
                
                form.setAmountFinanced(	FormatCurrency.toCurrency((long)(totalCharges - downPayment)));
                form.setFutureValue(	FormatCurrency.toCurrency((long) (F.getFutureValue())));
                request.setAttribute("PnInstallmentForm",form);
                nextPage = mapping.findForward("redisplayPnInstallmentJsp");
                
            } else if (form.getSubmitButton().compareToIgnoreCase("change")==0){
                logger.debug("Updating temp charges");
                
                acontract.setTotalCharges(totalCharges);
                acontract.setMonthlyPmtAmount(paymentAmount);
                acontract.setDownpayment(downPayment);
                acontract.setTotalFinanceCharge(financeCharge);
                acontract.setContractNumberPayments(numPayments);
                acontract.setDateFirstPmtDue(firstpmtdate);
                acontract.setInterestRate(intRate/100);
                t.save();
            } else {
                // Cancel assumed
            }
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnInstallment. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        } catch (Exception pe) {
            logger.error("Exception in ProcessPnInstallment. ",pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            nextPage = new ActionForward(mapping.getInput());
        }
        
        SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        
        logger.debug("Returning to : " + nextPage.getPath());
        
        return nextPage;
    }
}
