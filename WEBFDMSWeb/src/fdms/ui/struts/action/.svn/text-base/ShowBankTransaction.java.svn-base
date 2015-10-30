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

import fdms.ui.struts.form.BankingTransactionForm;

public class ShowBankTransaction extends Action {

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
	    form.setHasLocationId("false");
	    if (user.getLocationId() > 0) {
	    	 form.setHasLocationId("true");
	    	BankAccountDAO checkingDao = new BankAccountDAO(user);
			BankAccountDTO checkingDto = new BankAccountDTO();
			checkingDto = checkingDao.getBankAccountByLocationId(user.getLocationId());
			if (checkingDto != null) {
				
				form.setBankName(checkingDto.getBankName());
				form.setRoutingNumber(checkingDto.getRoutingNumber());
				form.setAccountNumber(checkingDto.getAccountNumber());
				form.setLocationName(user.getLocationName());
				form.setAvailableBalance(FormatCurrency.toCurrency(checkingDto.getBalance()));
				try {
					t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
//					DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, user.getLocationId());
//					form.setAvailableBalance(FormatCurrency.toCurrency(dbLocation.getCashBalance()));
//					t.removePersistent(dbLocation);
						String tranIdStr = request.getParameter("tranId");
						if (tranIdStr != null){
							int tranId = Integer.parseInt(tranIdStr);
							DbHistory bankingTran = FdmsDb.getInstance().getHistory(t,tranId);
							form.setMemo(bankingTran.getComment());
							if (bankingTran.getIHistType() == DbHistory.BANK_DEPOSIT) {
								form.setType("D");
								form.setAmount(FormatCurrency.toCurrency(bankingTran.getLHistAmount()));
							}else {
								form.setType("F");
								form.setAmount(FormatCurrency.toCurrency(bankingTran.getLHistAmount() * (-1)));
							}
							
							form.setTranDate(FormatDate.convertDateToMM_DD_YYYY(bankingTran.getCHistDate()));
							form.setTranId(tranIdStr);
							t.removePersistent(bankingTran);
						}
					
				 } catch(PersistenceException pe) {
			            logger.error("Persistence Exception in ShowBankTransaction.doPerform. " + pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
			     } catch(Exception pe) {
			            logger.error("Exception in  ShowBankTransaction.doPerform. ", pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
			     } finally {
			            if (t != null) t.closeConnection();
			     }
				
			}

		} 
		
	    ActionForward actionForward = mapping.findForward("operate");
		return actionForward;
	}


}
