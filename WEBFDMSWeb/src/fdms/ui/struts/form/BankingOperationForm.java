package fdms.ui.struts.form;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.model.ShowBankingTransaction;

public class BankingOperationForm extends ActionForm {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4420032962949977739L;
	private String bankName;
	private String accountName;
	private String accountNumber;
	private String hasLocationId;
	private String locationName;
	private String directive;
	private String availableBalance;
	private String type;
	private String amount;
	private String memo;
	private String tranDate;
	private ArrayList <ShowBankingTransaction> tranInfo = new ArrayList <ShowBankingTransaction>();
	private String voidMemo;
	private String routingNumber;
	private int localeId;
	private int locationId;
	private String arAcct;
	private String glAcct;
	private String hasBankAccount;
	
	
	public String getHasBankAccount() {
		return hasBankAccount;
	}
	public void setHasBankAccount(String hasBankAccount) {
		this.hasBankAccount = hasBankAccount;
	}
	public String getArAcct() {
		return arAcct;
	}
	public void setArAcct(String arAcct) {
		this.arAcct = arAcct;
	}
	public String getGlAcct() {
		return glAcct;
	}
	public void setGlAcct(String glAcct) {
		this.glAcct = glAcct;
	}
	public int getLocaleId() {
		return localeId;
	}
	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getVoidMemo() {
		return voidMemo;
	}
	public void setVoidMemo(String voidMemo) {
		this.voidMemo = voidMemo;
	}
	public ArrayList<ShowBankingTransaction> getTranInfo() {
		return tranInfo;
	}
	public void setTranInfo(ArrayList<ShowBankingTransaction> tranInfo) {
		this.tranInfo = tranInfo;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getDirective() {
		return directive;
	}
	public void setDirective(String directive) {
		this.directive = directive;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getHasLocationId() {
		return hasLocationId;
	}
	public void setHasLocationId(String hasLocationId) {
		this.hasLocationId = hasLocationId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
