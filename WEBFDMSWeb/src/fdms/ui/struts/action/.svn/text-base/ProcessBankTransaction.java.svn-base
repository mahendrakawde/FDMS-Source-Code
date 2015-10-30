package fdms.ui.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
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

import com.aldorsolutions.webfdms.beans.DbBankAccount;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.BankAccountDAO;
import com.aldorsolutions.webfdms.checkwriter.model.BankAccountDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.BankingTransactionForm;

public class ProcessBankTransaction extends Action {

	private Logger logger = Logger.getLogger(ShowBankAccount.class.getName());
	public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
	   	ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    DbUserSession user = SessionHelpers.getUserSession(request);
	    BankingTransactionForm form = (BankingTransactionForm) actionForm;
	    DatabaseTransaction t = null;
	    if (user.getLocationId() > 0) {
				try {
					t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
					DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, user.getLocationId());
					
						String tranIdStr = form.getTranId();
						if (tranIdStr != null){
							// do the void
							int tranId = Integer.parseInt(tranIdStr);
							DbHistory bankingTran = FdmsDb.getInstance().getHistory(t,tranId);
							bankingTran.setCHistDeleteTran('V');
							bankingTran.setComment(form.getMemo());
							int amount = bankingTran.getLHistAmount();
							// since it is voided so we are going to convert it then added to the balance of location.
							int newAmount = amount*(-1);
							
							//set the new balance to location
							int balance = dbLocation.getCashBalance();
							balance = balance + newAmount;
							dbLocation.setCashBalance(balance);
							t.addPersistent(dbLocation);
							t.addPersistent(bankingTran);
							
							//set the new balance to bankaccount
							BankAccountDAO bankDao = new BankAccountDAO(user);
							BankAccountDTO bankDto = new BankAccountDTO();
							bankDto = bankDao.getBankAccountByLocationId(bankingTran.getLocationId());
							
							DbBankAccount bankAccount = FdmsDb.getInstance().getBankAccount(t, bankDto.getBankAccountId());
		    	            int bankBalance = bankAccount.getBalance();
		    	            bankBalance = bankBalance + newAmount;
							bankAccount.setBalance(bankBalance);
							t.addPersistent(bankAccount);
							
							t.save();
						}
					
				 } catch(PersistenceException pe) {
			            logger.error("Persistence Exception in ProcessBankTransaction.doPerform. " + pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
			     } catch(Exception pe) {
			            logger.error("Exception in  ProcessBankTransaction.doPerform. ", pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
			     } finally {
			            if (t != null) t.closeConnection();
			     }
				
				

		} 
		
	    ActionForward actionForward = mapping.findForward("showTransaction");
	    session.removeAttribute("bankingOperationForm");
		return actionForward;
	}


}
