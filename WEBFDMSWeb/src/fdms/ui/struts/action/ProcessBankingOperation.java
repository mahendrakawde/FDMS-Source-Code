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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.model.ShowBankingTransaction;
import com.aldorsolutions.webfdms.beans.DbBankAccount;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.BankAccountDAO;
import com.aldorsolutions.webfdms.checkwriter.model.BankAccountDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.BankingOperationForm;

public class ProcessBankingOperation extends Action {

	private Logger logger = Logger.getLogger(ProcessApCheckWriter.class.getName());
    private ArrayList <String> formErrors;

    /**
     * Called from ApCheckWriter.JSP, this action is
     * the destination for the form. Multiple submit buttons are handled.
     * Depending on the action, the same JSP is redisplayed for entering
     * the next check to print.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
    	ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    DbUserSession user = SessionHelpers.getUserSession(request);
	    BankingOperationForm form = (BankingOperationForm) actionForm;
	    DatabaseTransaction t = null;
	    if (user.getLocationId() > 0) {
	    			try {
					t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
					
					
					if (form.getDirective()!= null && form.getDirective().compareToIgnoreCase("save") == 0){
						
						if(form.getAmount()==null || form.getAmount().equals("")){
							  errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionError("error.GeneralException","Amount required and must be greater then zero"));
						}
						if(form.getTranDate() == null  || form.getTranDate().equals("") ){
							errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionError("error.GeneralException","Transaction date must have valid date"));
							
						}
						
						if (FormatCurrency.convertToCurrency(form.getAmount().trim()) > 0){
							
							setHistory(t,user,form,errors );
							
							t.save();
						}
						
					}
					
				 } catch(PersistenceException pe) {
			            logger.error("Persistence Exception in ShowLocationEdit.doPerform. " + pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
			     } catch(Exception pe) {
			            logger.error("Exception in  ShowLocationEdit.doPerform. ", pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
			     } finally {
			            if (t != null) t.closeConnection();
			     }
				
				

		} 

	    
	    ActionForward actionForward = mapping.findForward("showBankingTransaction");
	//    session.removeAttribute("bankingOperationForm");
        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }else{
        	session.removeAttribute("bankingOperationForm");
        }

		return actionForward;
    }

    public void setHistory(DatabaseTransaction t, DbUserSession user, BankingOperationForm form,
    	    ActionErrors errors) {

    	DbHistory dbHistory = new DbHistory();
        dbHistory.setNew();
    	        String errorField = new String();
    	        String formField = null;

    	        try {

    	            errorField = "VitalsMasterKey";            
    	            dbHistory.setLMainKey(0);

    	            errorField = "ARAcct";
    	            formField = "arAcct";
    	            dbHistory.setCHistARacct(form.getArAcct());

    	            errorField = "Date";
    	            formField = "dateOfTran";
    	            
    	            java.sql.Date tranDate = null;
					try {
						tranDate = FormatDate.convertToSQLDate( form.getTranDate() );
						dbHistory.setCHistDate(tranDate);
			        } catch (Exception e){
			            logger.error("Error in handleSave() : ", e);
			        }
					
    	            errorField = "Description";
    	            formField = "description";
    	            
    	            int newAmount = FormatCurrency.convertToCurrency(form.getAmount().trim());
    	            if (form.getType().compareToIgnoreCase(ShowBankingTransaction.DEPOSIT) == 0) {
    	            	dbHistory.setCHistDesc("Bank Deposit");
    	            	dbHistory.setLHistAmount(newAmount);
    	            	dbHistory.setIHistType(DbHistory.BANK_DEPOSIT);
					} else {
						// this is for bank fee
						dbHistory.setCHistDesc("Bank Fee");
						newAmount = newAmount * (-1);
						dbHistory.setLHistAmount(newAmount);
						dbHistory.setIHistType(DbHistory.BANK_FEE);
					}
    	            
    	            
    	            
    	            errorField = "GLAcct";
    	            formField = "";
    	            dbHistory.setCHistGLAcct(form.getGlAcct());


    	            errorField = "OriginalPosting";
    	            formField = "";
    	            dbHistory.setCHistOriginalPosting('N');

    	            errorField = "Posted";
    	            formField = "";
    	            dbHistory.setCHistPosted('N');

    	            errorField = "SPF";
    	            formField = "";
    	            dbHistory.setCHistSPF(DbHistory.HIST_SPF_BANK.charAt(0));

    	            errorField = "LocationId";
    	            dbHistory.setLocationId(form.getLocationId());
    	            
    	            errorField ="memo";
    	            dbHistory.setComment(form.getMemo());
    	            
    	            long createdTimestamp = System.currentTimeMillis();
    	            dbHistory.setDatetimeOfTrans(createdTimestamp);
    	            
    	            dbHistory.setCHistDeleteTran(' ');
    	            
    	            t.addPersistent(dbHistory);

    	            // set the balance of location
    	            DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, form.getLocationId());
    	            int balance = dbLocation.getCashBalance();
					balance = balance + newAmount;
					dbLocation.setCashBalance(balance);
					t.addPersistent(dbLocation);
    	            
					//update the bank account
					BankAccountDAO bankDao = new BankAccountDAO(user);
					BankAccountDTO bankDto = new BankAccountDTO();
					bankDto = bankDao.getBankAccountByLocationId(user.getLocationId());
					
					DbBankAccount bankAccount = FdmsDb.getInstance().getBankAccount(t, bankDto.getBankAccountId());
    	            int bankBalance = bankAccount.getBalance();
    	            bankBalance = bankBalance + newAmount;
					bankAccount.setBalance(bankBalance);
					t.addPersistent(bankAccount);
					
    	        } catch (Exception e) {
    	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.receipts.set" +errorField));
    	            formErrors.add(formField);
    	        }

    	        return;

    	    }
}
