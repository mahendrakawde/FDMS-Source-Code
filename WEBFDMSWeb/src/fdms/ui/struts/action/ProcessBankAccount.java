package fdms.ui.struts.action;

import java.sql.Date;
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

import com.aldorsolutions.webfdms.beans.DbApMaster;
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

public class ProcessBankAccount extends Action {

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
	    BankAccountForm form = (BankAccountForm) actionForm;
	    
	    
	    	
	    	BankAccountDAO bankDao = new BankAccountDAO(user);
		    BankAccountDTO bankDto = bankDao.getBankAccountByLocationId(form.getLocationId());
		    
		   
		    
	    	if (bankDto == null){
	    		bankDto = new BankAccountDTO();
	    		setBankInfo(bankDto,form,user);
	    		setBalance(bankDto,form,user);
	    		bankDao.addBankAccount(bankDto);
	    		
	    	}else {
	    		setBankInfo(bankDto,form,user);
	    		if (bankDto.getStartDate() == null){
	    			setBalance(bankDto,form,user);
	    		}
	    		bankDao.updateBankAccount(bankDto);
	    	}
	    	
	   
		
	    ActionForward actionForward = mapping.findForward("webFDMSManagement");
		return actionForward;
    }

	private void setBankInfo(BankAccountDTO bankDto,
			BankAccountForm form, DbUserSession user) {
		bankDto.setBankName(form.getBankName());
		bankDto.setAccountName(form.getAccountName());
		bankDto.setRoutingNumber(form.getRoutingNumber());
		bankDto.setAccountNumber(form.getAccountNumber());
	    bankDto.setStreet(form.getStreet());
	    bankDto.setCity(form.getCity());
	    bankDto.setState(form.getState());
	    bankDto.setZip(form.getZip());
	    bankDto.setPhone(form.getPhone1());
	    bankDto.setOtherPhone(form.getPhone2());
	    bankDto.setLocationId(form.getLocationId());
	    bankDto.setLocaleId(form.getLocaleId());
		bankDto.setAccountingCode(form.getAccountingCode());
	}
	private void setBalance(BankAccountDTO bankDto,
			BankAccountForm form, DbUserSession user) {
		
		if (form.getStartDate() != null && form.getStartDate().length() > 0) {
			java.sql.Date startDate = null;
			try {
				startDate = FormatDate.convertToSQLDate( form.getStartDate() );
				bankDto.setStartDate(startDate);
				
		        DatabaseTransaction t = null;
				try {
						int newAmount = FormatCurrency.convertToCurrency(form.getInitBalance().trim());
						bankDto.setInitialBalance(newAmount);
						t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
						
						int checkAmount = 0;
						
						checkAmount = getCheckAmount(user, startDate, t);
						int currentBalance = newAmount + checkAmount;
						
						DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, user.getLocationId());
						dbLocation.setCashBalance(currentBalance);
						t.addPersistent(dbLocation);
						t.save();
						bankDto.setBalance(currentBalance);
						
				} catch(PersistenceException pe) {
		            logger.error("Persistence Exception in ProcessCheckingAccount.doPerform. " + pe);
				} catch(Exception pe) {
		            logger.error("Exception in  ProcessCheckingAccount.doPerform. ", pe);
			     } finally {
			            if (t != null) t.closeConnection();
			     }
		     
			 } catch (Exception e){
		            logger.error("Error in handleSave() : ", e);
		     }
		}
	}
	private int getCheckAmount(DbUserSession user, Date fromDate, DatabaseTransaction t){
		int amount = 0;
		DbApMaster [] checks = FdmsDb.getInstance().getApCheckSet(t, user.getRegion(),user.getLocationId(),0,true,fromDate);
		for(DbApMaster check: checks){
			int checkAmount = check.getInvoiceTotal()-check.getDiscountAmount();
			if (check.getVoidedCode().compareToIgnoreCase("V")== 0) {
				//do nothing.
			}else {
				checkAmount = checkAmount * (-1);
			}
			amount = amount + checkAmount;
		}
		return amount;
	}
        

}
