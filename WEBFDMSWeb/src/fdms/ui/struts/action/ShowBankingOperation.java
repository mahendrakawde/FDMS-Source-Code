package fdms.ui.struts.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeMap;

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

import com.aldorassist.webfdms.model.ShowBankingTransaction;
import com.aldorsolutions.webfdms.beans.DbApMaster;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
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
import fdms.ui.struts.form.BankAccountForm;

public class ShowBankingOperation extends Action {

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
	    BankingOperationForm form = new BankingOperationForm ();
	    DatabaseTransaction t = null;
	    form.setHasLocationId("false");
	    
	    if (user.getLocationId() <=0) {
	    	if(user.getRegion()> 0) {
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
			
			if (form.getLocationName() == null || form.getLocationName().length() <= 0) {
				form.setLocationName(user.getLocationName());
			}
			BankAccountDAO bankDao = new BankAccountDAO(user);
			BankAccountDTO bankDto = new BankAccountDTO();
			bankDto = bankDao.getBankAccountByLocationId(user.getLocationId());
			form.setHasBankAccount("false");
			if (bankDto == null) {
				form.setHasBankAccount("false");
			} else {
				if (bankDto.getStartDate() != null) {
				form.setHasBankAccount("true");
				form.setBankName(bankDto.getBankName());
				form.setAccountName(bankDto.getAccountName());
				form.setAccountNumber(bankDto.getAccountNumber());
				form.setRoutingNumber(bankDto.getRoutingNumber());
				form.setGlAcct(bankDto.getAccountingCode());
				form.setArAcct("");
				form.setLocationId(user.getLocationId());
				form.setLocaleId(user.getRegion());
				form.setAvailableBalance(FormatCurrency.toCurrency(bankDto.getBalance()));
				try {
					t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
//					DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, user.getLocationId());
//					form.setAvailableBalance(FormatCurrency.toCurrency(dbLocation.getCashBalance()));
//					t.removePersistent(dbLocation);
					
					//now we are going to show the transactioninfor for banking
					// get information from bankingtransaction
					TreeMap <Long, ShowBankingTransaction>tranInfo = new TreeMap <Long, ShowBankingTransaction>();
					java.util.Date fromDate = new java.util.Date();
					
					//get any transaction that is lease than 1 month.
					//fromDate = FormatDate.addToDate(fromDate,0,(-1));
					// Added by bhavesh #5593 Bank Deposit Screen / Check Writer issue (60 days instead of 30 days )
					fromDate = FormatDate.addToDate(fromDate,0,(-2));
					java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
					DbHistory [] bankingTrans = FdmsDb.getInstance().getHistoryForBank(t,user.getLocationId(),sqlFromDate,null);
					
					DbHistory bankingTranInfo = new DbHistory();
					for (int i = 0; i < bankingTrans.length; i++){
						bankingTranInfo = bankingTrans[i];
						ShowBankingTransaction showTran= new ShowBankingTransaction();
						
						showTran.setDescription(bankingTranInfo.getComment());
						showTran.setTranDate(FormatDate.convertDateToMM_DD_YYYY(bankingTranInfo.getCHistDate()));
						if (bankingTranInfo.getIHistType() == DbHistory.BANK_DEPOSIT) {
							showTran.setType("D");
							showTran.setAmount(FormatCurrency.toCurrency(bankingTranInfo.getLHistAmount()));
						}else {
							showTran.setType("F");
							showTran.setAmount(FormatCurrency.toCurrency(bankingTranInfo.getLHistAmount()*(-1)));
						}
						if (bankingTranInfo.getCHistDeleteTran() == 'V') {
							showTran.setStatus("V");
						}
						showTran.setCheckNumber("");
						if (bankingTranInfo.getCHistDeleteTran() == 'V'){
							showTran.setVoidTitle("");
						}else {
							showTran.setVoidTitle("void");
						}
						showTran.setTranId(String.valueOf(bankingTranInfo.getId()));
						
						Timestamp tmstmp = new Timestamp (bankingTranInfo.getCHistDate().getTime());
						long key = tmstmp.getTime();
						key = generateKey(key,bankingTranInfo.getId());
						tranInfo.put(key,showTran);
						
					}	
					
					// get information from checks
					
					DbApMaster [] checks = FdmsDb.getInstance().getApCheckSet(t, user.getRegion(),user.getLocationId(),0,true,sqlFromDate);
					for(DbApMaster check: checks){
						ShowBankingTransaction showTran= new ShowBankingTransaction();
						showTran.setAmount(FormatCurrency.toCurrency(check.getInvoiceTotal()-check.getDiscountAmount()));
						showTran.setDescription(check.getMemo());
						showTran.setTranDate(FormatDate.convertDateToMM_DD_YYYY(check.getCheckDate()));
						showTran.setType("C");
						showTran.setCheckNumber(String.valueOf(check.getCheckNumber()));
						showTran.setStatus(check.getVoidedCode());
						showTran.setVoidTitle("");
						showTran.setTranId(String.valueOf(0));
						showTran.setPayTo(check.getVendorName());
						
						Timestamp tmstmp = new Timestamp (check.getCheckDate().getTime());
						long key = tmstmp.getTime();
						key = generateKey(key,check.getId());
						tranInfo.put(key,showTran);
						
					}
					
					Iterator tranInfoIterator = tranInfo.values().iterator();
					ArrayList <ShowBankingTransaction> list = new ArrayList <ShowBankingTransaction>();
					
					while (tranInfoIterator.hasNext()) {
						ShowBankingTransaction showTran = (ShowBankingTransaction) tranInfoIterator.next();
						list.add(showTran);
					}
					
					form.setTranInfo(list);
					
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

		} 
		
		session.setAttribute("bankingOperationForm", form);
		
		ActionForward actionForward = mapping.findForward("operate");
		return actionForward;
	}
	private long generateKey(long timestamp, long identity) {
		
		long key = timestamp + identity;
		return key;
	}
	

}
