package fdms.ui.struts.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

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
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.beans.DbComponent;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PaymentAddChangeForm;

public class ProcessPaymentAddChange extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPaymentAddChange.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from paymentAdd.JSP, this action handles the submit button from its form.
     * The form has multiple submit buttons to handle
     * 1) save the payment.
     * 2) cancel and return to payment.jsp.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {                
        
        formErrors = new ArrayList();
        PaymentAddChangeForm form = (PaymentAddChangeForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        boolean subtractFromAmount=false;
        
        logger.debug("ProcessPaymentAddChange. Starting action:"+form.getDirective());      
        
        int vitalsid=0;        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            logger.debug("ProcessPaymentAddChange. Invalid VitalsID to process:"+vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        if (form.getDirective().compareTo("cancel")==0){
            return  mapping.findForward("showPayment");
        } 
        else if (form.getDirective().compareTo("help")==0){
            return mapping.findForward("usingHelp");
        }
        else if (form.getDirective().compareTo("selectcomponent")==0){
        	
			String paymentComponent = form.getPayment();
			
			if ( (paymentComponent != null) && (paymentComponent.length() > 0) ) 
			{
				try {
					logger.debug("ProcessPaymentAddChange prepare to post payment");
					t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
					
					int paymentComponentID = Integer.parseInt(paymentComponent); 
					
					DbComponent [] dbComponent = FdmsDb.getInstance().getComponentsForID(t, vitalsid);
					
					for (int i = 0; i < dbComponent.length; i++) {
						if ( paymentComponentID == dbComponent[i].getId() ) {
							form.setPaymentSource( dbComponent[i].getSource() );
						}
						
					}
					
				}
				catch(PersistenceException pe) {
		            logger.error("Persistence Exception in ProcessPayment do.Perform. " + pe);
		            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
		        } 
				finally {
					if ( t != null ) {
		        		t.closeConnection();
		        		t = null;
		        	}
				}
				
			}
			
			return (new ActionForward(mapping.getInput() ));
			
        }
        else if (form.getDirective().compareTo("save")!=0){
        	// then action must be to add a payment.
            logger.debug("ProcessPaymentAddChange unknown action.");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException","Unknown command"));
        }
        
        // Verify that a description was entered
        logger.debug("ProcessPaymentAddChange begin save processing");
        if (form.getPaymentSource().compareTo(" ") <= 0){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.emptydescr"));
            formErrors.add("paymentSource");
        }

        // get component to update
        int pmtcompID = 0;
        try {
            logger.debug("ProcessPaymentAddChange get component");
            pmtcompID = Integer.parseInt(form.getPayment());
            if (pmtcompID < 1){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.emptycomponent"));
                formErrors.add("payment");
            }
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.emptycomponent"));
            formErrors.add("payment");
        }
        // Verify deposit date
        logger.debug("ProcessPaymentAddChange verify deposit date");
        Date depositDate = null;
        
        try {
            FormatDate.convertToDateYYYYMMDD( form.getDateOfDeposit());
            
            depositDate = new Date (FormatDate.convertToDate( form.getDateOfDeposit() ).getTime());
            
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.depositdate"));
            formErrors.add("dateOfDeposit");
        }
        
        // Verify date paid
        logger.debug("ProcessPaymentAddChange verify date paid");
        Date datePaid = null;
        
        try {
            FormatDate.convertToDateYYYYMMDD( form.getDateOfPayment() );
            datePaid = new Date (FormatDate.convertToDate( form.getDateOfPayment() ).getTime());
            
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.paiddate"));
            formErrors.add("dateOfPayment");
        }
        
        // Verify amount paid
        logger.debug("ProcessPaymentAddChange verify amount paid");
        int amountpaid = 0;
        try {
            amountpaid = FormatCurrency.convertToCurrency(form.getAmountPaid());
            if (amountpaid ==0){
            	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.amount"));
                formErrors.add("amountPaid");
            }
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.amount"));
            formErrors.add("amountPaid");
        }
        // Verify GL account from payment type
        logger.debug("ProcessPaymentAddChange verify gl account");
        String glaccount = CsvTable.getField(form.getNonCashAdjustment(),2);
        if (glaccount.compareTo(" ")<=0){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.nca"));
            formErrors.add("nonCashAdjustment");
        }

        String subtractFromBalance = CsvTable.getField(form.getNonCashAdjustment(),3);
        if("N".equalsIgnoreCase(subtractFromBalance)) {
  				subtractFromAmount = true;
  			}

        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            logger.debug("ProcessPaymentAddChange Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput() ));
        }
        
        String operation = "";
        
        String hasSet = (String) session.getAttribute("paymentOperation");
        if (hasSet == null) {
        	hasSet = "new";
        }
        else {
        	hasSet = "set";
        }
        if (hasSet.compareToIgnoreCase("set")== 0) {
        	operation = session.getAttribute("paymentOperation").toString();
        }
        boolean errorSave = false;
        if ( operation.compareToIgnoreCase("processing")!=0 && operation.compareToIgnoreCase("error")!=0) {
        	session.setAttribute("paymentOperation", "processing");
        	
        
	        try{
	        	long createdTimestamp = System.currentTimeMillis();

	            logger.debug("ProcessPaymentAddChange prepare to post payment");
	            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	            // call method to post a payment
	            //AppLog.trace("ProcessPaymentAddChange post payment");
	            FdmsDb.getInstance().postPayment(
	                    t, 
	                    sessionUser, 
	                    vitalsid, 
	                    form.getPaymentSource(),
	                    amountpaid, 
	                    glaccount, 
	                    depositDate, 
	                    datePaid,
	                    form.getManualReceiptNumber(), 
	                    form.getMethodOfPayment(), 
	                    pmtcompID,
	                    (short) 0,
	                    subtractFromAmount);
	            
	        } catch(PersistenceException pe) {
	            logger.error("Persistence Exception in ProcessPayment do.Perform. " + pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	            errorSave = true;
	        } catch(Exception pe) {
	            logger.error("Exception in  ProcessPayment .doPerform. ", pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	            errorSave = true;
	        }
	        finally {
	        	if ( t != null ) {
	        		t.closeConnection();
	        		t = null;
//	        		try {
//	        			Thread.currentThread().sleep(60000); // wait for miliseconds 1000 millisecond = 1 second
//	        		} catch (Exception pe) {
//	        			
//	        		}
//	        		finally {
//	        			
//	        		}
	        	}
	        	
	            if (!errorSave) {
	            	session.removeAttribute("paymentOperation");
	            } 
	            else {
	            	session.setAttribute("paymentOperation","error");
	            }
	        	
	        }
	        
        } // end session for adding payment
        else {
        	// this will be operate if there is a payment processing so we throw the processing page let the user wait for a couple minutes.
        	
            session.removeAttribute("paymentAddChange");
            session.removeAttribute("paymentAddChangeDisplayList");
            session.removeAttribute("paytypes");
            session.removeAttribute("paymethods");
        	
        	return mapping.findForward("processingPayment");
        }
        
        ActionForward actionForward = mapping.findForward("showPayment");
        if (!errors.isEmpty()){
            logger.error("ProcessPayment Invoking forward mapping getInput()");
            saveErrors(request, errors);
            actionForward = new ActionForward(mapping.getInput());
            
            
        } else {
            //Remove Beans from session scope
            session.removeAttribute("paymentAddChange");
            session.removeAttribute("paymentAddChangeDisplayList");
            session.removeAttribute("paytypes");
            session.removeAttribute("paymethods");
            
        }
        session.removeAttribute("paymentOperation");
        return actionForward ;
    }
    
}
