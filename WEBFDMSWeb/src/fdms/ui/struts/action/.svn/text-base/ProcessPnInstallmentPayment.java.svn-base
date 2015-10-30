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
import com.aldorsolutions.webfdms.beans.DbPnPayment;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.PnInstallmentPayment;

public class ProcessPnInstallmentPayment extends Action {

    private Logger logger = Logger.getLogger(ProcessPnInstallmentPayment.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(
        ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {                
        
        PnInstallmentPayment form = (PnInstallmentPayment) actionForm;
        formErrors = new ArrayList();
        DbPnContract acontract = null;
        //DbVitalsDeceased  bene = null;
        DatabaseTransaction t = null;
        
        logger.debug("ProcessPnInstallmentPayment beginning: "+form.getSubmitButton()+":"+form.getVitalsId()+":"+form.getContractId());

        ActionErrors errors = new ActionErrors();
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        int contractid = FormatNumber.parseInteger(form.getContractId());

        DbUserSession sessionUser = SessionHelpers.getUserSession(request);

        // default next page to display
        ActionForward nextAction = mapping.findForward("showPnStatus");
        // clear report preview
        form.setPreviewFile("");

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            //bene    = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
            //if (bene == null){
            //  errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            //  return nextAction;
            //}
            if (acontract == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
                return nextAction;
            }
            //form.setContractCode( Integer.toString(acontract.getContractNumber()));
            //form.setFullName(   bene.getDecFName()+" "+bene.getDecLName());
            // save  payment information in case user changed it
            int numpaymentsmade = FormatNumber.parseInteger(form.getNumberPaymentsMade());
            if (numpaymentsmade != acontract.getNumberPmtsMade()){
                acontract.setNumberPmtsMade(numpaymentsmade);
                t.save();
            }
            //acontract.setLastPmtAmount();
            //acontract.setLastPmtCommAmount();
            //acontract.setLastPmtCommBox();
            //acontract.setLastPmtDate();
            //acontract.setLastPmtFundsForCode();
            //acontract.setLastPmtMemo();

            if (form.getSubmitButton().equalsIgnoreCase("save")){
                
            } else if (form.getSubmitButton().equalsIgnoreCase("printdeposit")){
                logger.debug("ProcessPnInstallmentPayment handling printdeposit form:"+form.getFormName()+" payment:"+form.getPaymentHistorySelected());
                
                processPrintForm(form, errors, request, response, vitalsid, contractid);
                nextAction = mapping.findForward("redisplayPnInstallmentPaymentJsp");
                request.setAttribute("PnInstallmentPayment",form);
            } else if (form.getSubmitButton().equalsIgnoreCase("addpayment")){
                logger.debug("ProcessPnInstallmentPayment handling addpayment");
                
                processAddPayment(form, errors, request, vitalsid, contractid);
                nextAction = mapping.findForward("redisplayPnInstallmentPaymentJsp");                
            } else {
                // not one of the above commands, so assume "exit"
            }
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnPayment. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        } catch (Exception pe) {
            logger.error("Exception in ProcessPnPayment. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe));
        } finally {
            if (t != null) t.closeConnection();
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            nextAction = mapping.findForward("redisplayPnInstallmentPaymentJsp");
            request.setAttribute("PnInstallmentPayment",form);
            request.setAttribute("formErrors", formErrors);            
        }
        
        SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        
        return nextAction;
    }
    
    /**
     * Add payment to database, update totals on page and redisplay the page.
     * Creation date: (1/20/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public void processAddPayment(
            fdms.ui.struts.form.PnInstallmentPayment form,
            ActionErrors errors, 
            HttpServletRequest request, 
            int vitalsid, 
            int contractid) throws PersistenceException {
        
        logger.debug("Entering processAddPayment");

        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbPnContract acontract = null;
        DbPnPayment apayment = null;

        // Verify data for payment
        int fundsfor = 0;
        try {
            fundsfor = Integer.parseInt(form.getFundsFor());
            if (fundsfor < 1) throw new Exception();
        }  catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.fundsfor"));
            formErrors.add("fundsFor");
        }
        
        int amount = 0;
        try {
            amount = FormatCurrency.convertToCurrency(form.getTotalCheckAmount());
            if (fundsfor == 1 && amount==0) throw new Exception();
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.checkamount"));
            formErrors.add("totalCheckAmount");
        }
        
        int commamount = 0;
        try {
            commamount = FormatCurrency.convertToCurrency(form.getCommissionAmount());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commamount"));
            formErrors.add("commissionAmount");
        }
        
        try {
            FormatCurrency.convertToCurrency(form.getEscrowAmount());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.escrowamount"));
            formErrors.add("escrowAmount");
        }
        
        java.util.Date pmtdate = null;
        try {
            pmtdate = FormatDate.convertToDate(form.getCheckDate());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.checkdate"));
            formErrors.add("checkDate");
        }
        
        if (commamount != 0
            && (form.getCommSentRetained()==null
                || (form.getCommSentRetained().compareToIgnoreCase("S")!=0
                && form.getCommSentRetained().compareToIgnoreCase("R")!=0))
             ){
            
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commsent"));
            formErrors.add("commSentRetained");
        }
        
        
		try {

	        // Save Payment information
	        logger.debug("Getting db connection");
	        t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	        // but bail out if errors.
	        if (!errors.isEmpty()) {
	            logger.debug("ActionErrors found in processAddPayment()");
	            ProcessPnStatus.getPaymentOptionList(t, vitalsid, contractid, form);
	            return;
	        }
	        
	        acontract = FdmsDb.getInstance().getPnContract(t, contractid);
	        if (acontract==null){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
	            return;
	        }
	        
	        apayment = new DbPnPayment();
	        apayment.setNew();
	        t.addPersistent(apayment) ;
	        apayment.setContractID( contractid );
	        apayment.setAmount( amount );
	        apayment.setCommissionAmount( commamount );
	        apayment.setCommissionSentBox(form.getCommSentRetained() );
	        apayment.setDate( pmtdate );
	        apayment.setFundsForCode( form.getFundsFor() );
	        apayment.setMemo(     form.getMemo());

	        acontract.setLastPmtAmount( amount );
	        acontract.setLastPmtCommAmount( commamount );
	        acontract.setLastPmtCommBox(  form.getCommSentRetained());
	        acontract.setLastPmtDate(   pmtdate );
	        acontract.setLastPmtFundsForCode(form.getFundsFor() );
	        acontract.setLastPmtMemo(   form.getMemo());
	        acontract.setTotalPaidToDate( acontract.getTotalPaidToDate()+amount );
	        acontract.setNumberPmtsMade(  acontract.getNumberPmtsMade()+1);
	        t.save();

	        // Fill form
	        ProcessPnStatus.preparePnInstallmentPayment(errors, request, vitalsid, contractid, sessionUser);
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
		

/*  form.setLastPaymentAmount( form.getTotalCheckAmount());
        form.setLastPaymentDate(  form.getCheckDate());
        form.setCommissionPercent(  Double.toString(acontract.getCommissionRate()*100));
        form.setTotalContractAmount(FormatCurrency.toCurrency((long)acontract.getTotalCharges()));
        long totalsaleprice = (long)(acontract.getMonthlyPmtAmount()*acontract.getContractNumberPayments())+acontract.getDownpayment();
        if (totalsaleprice ==0) totalsaleprice = (long)acontract.getTotalCharges();
        form.setTotalFinancedAmount(FormatCurrency.toCurrency( totalsaleprice ));
        form.setTotalPaidToDate(  FormatCurrency.toCurrency((long)acontract.getTotalPaidToDate()));
        form.setNumberPaymentsMade( Integer.toString(acontract.getNumberPmtsMade()));

        form.setTotalCheckAmount("");
        form.setCheckDate(  FormatDate.getCurrentDateFormatedMMDDYYYY());
        form.setEscrowAmount("");
        form.setCommissionAmount("");
        form.setFundsFor("0");
        form.setMemo("")  ;
        // get payment history
        t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        ProcessPnStatus.getPaymentOptionList(t, vitalsid, contractid, form);
        // Caluclate payoff amount
        double timeinyears = (double)(acontract.getContractNumberPayments() - acontract.getNumberPmtsMade())/12;
        PresentValue P = new PresentValue((double)acontract.getMonthlyPmtAmount()/100.0, acontract.getInterestRate()*100, 12, timeinyears);
        form.setPayoffAmount(   FormatCurrency.toCurrency((long)P.getPresentValue()));  ;
        t.closeConnection();
        request.setAttribute("PnInstallmentPayment",form);

 */
    }
    
    /**
     * Print the selected form and prepare to redisplay the installment payment page.
     * Creation date: (1/19/2003 4:03:56 PM)
     * @param errors org.apache.struts.action.ActionErrors
     * @param request javax.servlet.http.HttpServletRequest
     * @param vitalsid int
     */
    public void processPrintForm(
            fdms.ui.struts.form.PnInstallmentPayment form,
            ActionErrors errors, 
            HttpServletRequest request, 
            HttpServletResponse response, 
            int vitalsid, 
            int contractid) {

        DbUserSession sessionUser = SessionHelpers.getUserSession(request);

        // verify we have a form selected
        int formid = FormatNumber.parseInteger(form.getFormName());
        if (formid < 1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.print.noselect"));
            formErrors.add("formName");
        }
        
        int paymentid = FormatNumber.parseInteger( form.getPaymentHistorySelected() );
        if (paymentid < 1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("paymentHistorySelected");            
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return;
        }
        
        //Database Access Logic
        try{

            String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");
            String pageName = null;
            
            if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());                
                pageName = crystalServerReport.printReport(sessionUser, formid, "", "", null, "", Integer.toString(paymentid), true);
            }
            else
            {
            	ExportReport crystal = new ExportReport();
            	crystal.setRecordIdSelParam( paymentid ); // selection parameter
                pageName = crystal.printForm(sessionUser, formid, "", "", null, "", request, response, servlet.getServletContext());
            }
            
            form.setPreviewFile(pageName);
        } catch(Exception pe) {
            logger.error("Exception in PnInstallmentPayment .doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        }

    }
}