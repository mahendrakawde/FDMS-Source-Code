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

import com.aldorassist.webfdms.dao.InvoiceDAO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApDistribution;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbApMaster;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbBankAccount;
import com.aldorsolutions.webfdms.beans.DbInvoice;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.BankAccountDAO;
import com.aldorsolutions.webfdms.checkwriter.model.BankAccountDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.checks.CheckPrinter;

import fdms.ui.struts.form.CheckEditDisplayForm;

public class ProcessCheckEditDisplay extends Action {
    
    private Logger logger = Logger.getLogger(ProcessCheckEditDisplay.class.getName());

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        CheckEditDisplayForm form = (CheckEditDisplayForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbApMaster apMaster = null;
        DbApVendor vendor = null;
        DbApAccount account = null;
        
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        String directive = form.getDirective();
        // AppLog.trace("ProcessCheckEdit. action:"+directive+" checkID: "+form.getApmasterID()).;
        try {
            if( directive.equals("cancel") )	{
                // AppLog.trace("ProcessCheckEdit. CANCEL");
                return mapping.findForward("ShowCheckEditJsp");
            }
            
            if( directive.compareToIgnoreCase("save")!=0 && 
            		directive.compareToIgnoreCase("void")!=0 &&
            		directive.compareToIgnoreCase("print")!=0 ){
                return mapping.findForward("ShowCheckEditJsp");
            }
            
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            int apMasterID = FormatNumber.parseInteger(form.getApmasterID());
            apMaster 		= FdmsDb.getInstance().getApCheck(t, apMasterID);
            if (apMaster==null){
                return mapping.findForward("ShowCheckEditJsp");
            }

            if( directive.equals("print") )	{

            	CheckPrinter cp = new CheckPrinter ();
            	String pageName = cp.printCheckForCheckWriter(request, response, servlet.getServletContext(), 
            			sessionUser, errors, apMasterID);
            	
            	logger.debug("Check Printer Page: " + pageName);
                form.setPreviewFile(pageName);
                // AppLog.trace("ProcessCheckEdit. CANCEL");
                return mapping.findForward("checkEditDisplay");
            }
            
            // are we voiding?
            if (directive.compareToIgnoreCase("void")==0){
            	
	            vendor = FdmsDb.getInstance().getApVendor(t, apMaster.getVendorID());
	            DbApDistributionHistory[] expenses = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getDistributionsHist(t, apMaster.getId());
	            DbApDistributionHistory expenseHistoryItem = null;
        		for (DbApDistributionHistory expense : expenses) {
        			expenseHistoryItem = new DbApDistributionHistory();
        			expenseHistoryItem.setNew();
        			expenseHistoryItem.setApMasterID(apMaster.getId());
        			expenseHistoryItem.setApAccountNumber(expense.getApAccountNumber());
        			expenseHistoryItem.setDiscountAmount(0);
        			expenseHistoryItem.setType(expense.getType());
        			expenseHistoryItem.setMemo(expense.getMemo());
        			expenseHistoryItem.setAmount(expense.getAmount()*(-1));
        			t.addPersistent(expenseHistoryItem);
        		}

	        		// set the invoice table to be voided status
	        		InvoiceDAO invoiceDao = new InvoiceDAO(sessionUser);
	        		ArrayList <InvoiceDTO> invoicesList = new  ArrayList <InvoiceDTO>();
	        		invoicesList = invoiceDao.getInvoicesByMasterID(String.valueOf(apMaster.getId()));
	        		for(InvoiceDTO apInvoicesForCheckDTO : invoicesList) {
	        			DbInvoice dbInvoice = FdmsDb.getInstance().getInvoice(t,apInvoicesForCheckDTO.getInvoiceID());
	        			dbInvoice.setCheckCreated(true);
						dbInvoice.setInvoicePaid(false);
	        			dbInvoice.setInvoiceStatus(InvoiceDTO.INVOICE_VOID);
	        			t.addPersistent(dbInvoice);
	        		}        	
	        		
	        		 apMaster.remove();
	        		 
	        		 int locationID = 0;
	                 try {locationID = apMaster.getLocationID() ;}
	                 catch (Exception e){}
	                 if (locationID < 1){
	                     errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocation()));
	                 }
	                 
	                 // set balance on location
	        		 DbLocation aloc = FdmsDb.getInstance().getLocation(t, locationID);
	                 if (aloc == null){
	                     errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocation()));
	                 }
	                 int checkamount = apMaster.getInvoiceTotal()-apMaster.getDiscountAmount();
	                 aloc.setCashBalance(    aloc.getCashBalance() + checkamount);
	                 t.addPersistent(aloc);
	                 
	                 //set balance on bankaccount
	                 BankAccountDAO bankDao = new BankAccountDAO(sessionUser);
					 BankAccountDTO bankDto = new BankAccountDTO();
					 bankDto = bankDao.getBankAccountByLocationId(locationID);
					
					 if (bankDto != null) {
						 DbBankAccount bankAccount = FdmsDb.getInstance().getBankAccount(t, bankDto.getBankAccountId());
		    	         int bankBalance = bankAccount.getBalance();
		    	         bankBalance = bankBalance + checkamount;
						 bankAccount.setBalance(bankBalance);
						 t.addPersistent(bankAccount);
					 }
            	
             } else {
                // Verify we have a valid vendor ID
                int vendorid = FormatNumber.parseInteger( form.getVendor() )	;
                if (vendorid < 1){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.novendor",form.getVendor()));
                }
                // Verify we have a valid check date
                java.util.Date checkDate = null;
                try {checkDate = FormatDate.convertToDate( form.getCheckDate() );}
                catch (Exception e){}
                if (checkDate == null){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.date"));
                }
                // Verify the check number is valid
                long checknumber = 0;
                try {checknumber = Long.parseLong( form.getCheckNumber());}
                catch(Exception e){}
                if (checknumber < 1){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.number"));
                }
                // Verify we have a valid location
                int locationID = 0;
                try {locationID = Integer.parseInt( form.getLocation() );}
                catch (Exception e){}
                if (locationID < 1){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocation()));
                }
                // return if errors
                if (!errors.isEmpty()){
                    saveErrors(request, errors);
                    return new ActionForward(mapping.getInput());
                }
                
                apMaster.setLocationID(	locationID);
                apMaster.setCheckNumber(	checknumber);
                apMaster.setCheckDate(	checkDate);
                apMaster.setVendorID(		vendorid);
                apMaster.setMemo(			form.getMemo());
                
            }
            apMaster.setVoidedComment(form.getEditMemo());
            t.addPersistent(apMaster);
            // clean up
            t.save();
        } catch(PersistenceException pe) {
        	pe.printStackTrace();
            // AppLog.criticalError("Persistence Exception in ProcessApVendor.doPerform. "+pe.getMessage());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            // AppLog.criticalError("Exception in ProcessApVendor .doPerform. "+pe);
             pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                    t = null;
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        if( !errors.isEmpty() )  {
            saveErrors(request, errors );
        }
        return mapping.findForward("ShowCheckEditJsp");
    }
}
