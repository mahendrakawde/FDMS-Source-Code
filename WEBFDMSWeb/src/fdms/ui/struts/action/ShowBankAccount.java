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

import fdms.ui.struts.form.BankAccountForm;
import fdms.ui.struts.form.VerifyFinancialForm;

public class ShowBankAccount extends Action{
	
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
	    BankAccountForm form = (BankAccountForm) actionForm;
	    form.setHasLocationId("false");
	    if (user.getLocationId() <=0) {
	    	if(user.getRegion()> 0) {
	    		DatabaseTransaction t = null;
	    		try {
	    			DbLocation[] chapels = null;
	    			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
	    			chapels = FdmsDb.getInstance().getLocationsForRegion(t, user.getRegion());
	    			if (chapels != null && chapels.length == 1) {
	                    user.setLocationId(chapels[0].getId());
	                    form.setLocationName(chapels[0].getName());
	                    form.setHasLocationId("true");
	                    form.setLocationId(chapels[0].getId());
	                    form.setLocaleId(user.getRegion());
	    			}    
	    		} catch(PersistenceException pe) {
		            logger.error("Persistence Exception in ShowBankAccount.doPerform. " + pe);
		            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
			     } catch(Exception pe) {
			            logger.error("Exception in  ShowBankAccount.doPerform. ", pe);
			            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
			     } finally {
			            if (t != null) t.closeConnection();
			     }		
	    	}
	    }
	    
	    
		if (user.getLocationId() > 0 || form.getHasLocationId().compareToIgnoreCase("true") == 0) {
			form.setHasLocationId("true");
//			DatabaseTransaction t = null;
			try {
				
			
				if (form.getLocationName() == null || form.getLocationName().length() <= 0) {
					form.setLocationName(user.getLocationName());
				}
				form.setLocaleId(user.getRegion());
				form.setLocationId(user.getLocationId());
				BankAccountDAO bankDao = new BankAccountDAO(user);
				BankAccountDTO bankDto = new BankAccountDTO();
				bankDto = bankDao.getBankAccountByLocationId(user.getLocationId());
				if (bankDto == null) {
//					t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
//					DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, user.getLocationId());
//					form.setCurrentBalance(FormatCurrency.toCurrency(dbLocation.getCashBalance()));
//					t.removePersistent(dbLocation);
				} else {
					form.setBankName(bankDto.getBankName());
					form.setAccountName(bankDto.getAccountName());
					form.setRoutingNumber(bankDto.getRoutingNumber());
					form.setAccountNumber(bankDto.getAccountNumber());
					form.setStreet(bankDto.getStreet());
					form.setCity(bankDto.getCity());
					form.setState(bankDto.getState());
					form.setZip(bankDto.getZip());
					form.setPhone1(bankDto.getPhone());
					form.setPhone2(bankDto.getOtherPhone());
					form.setAccountingCode(bankDto.getAccountingCode());
					if (bankDto.getStartDate() == null) {
						form.setInitBalance("");
					}else {
						form.setStartDate(FormatDate.convertDateToMM_DD_YYYY(bankDto.getStartDate()));
						form.setInitBalance(FormatCurrency.toCurrency(bankDto.getInitialBalance()));
					}
				}
//			} catch(PersistenceException pe) {
//		            logger.error("Persistence Exception in ShowBankAccount.doPerform. " + pe);
//		            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
		     } catch(Exception pe) {
		            logger.error("Exception in  ShowBankAccount.doPerform. ", pe);
		            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
		     } finally {
//		            if (t != null) t.closeConnection();
		     }		
		} 
		
	    
	    ActionForward actionForward = mapping.findForward("bankAccount");
		return actionForward;
	}
}
