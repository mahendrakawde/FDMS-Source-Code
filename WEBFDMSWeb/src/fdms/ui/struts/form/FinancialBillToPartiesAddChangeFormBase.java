package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class FinancialBillToPartiesAddChangeFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5685710066463488425L;

	private String directive;

	private String billToPartyId;

	private boolean billToPartyContractSigner;

	private String billToPartySocialSecurityNumber;

	private String billToPartyFirstName;

	private boolean billToPartyCashSale;

	private String billToPartyEMailAddress;

	private boolean billToPartyRefused;

	private String billToPartyZipCode;

	private String billToPartyRelation;

	private String billToPartyCity;

	private boolean billToPartyReceiveInvoice;

	private String billToPartyWorkPhone;

	private String billToPartyLastName;

	private String billToPartyLanguage;

	private String billToPartyTitle;

	private String billToPartyAddress4;

	private String billToPartyAddress3;

	private String billToPartyAddress2;

	private String billToPartyHomePhone;
	
	private String billToPartyCellPhone;

	private String billToPartyAddress1;

	private String billToPartyWorkPhoneExt;

	private String billToPartyState;
	
	private boolean showCashSale = false;
	
	private boolean showRefused = false;
	

	public String getBillToPartyAddress1() {
		return billToPartyAddress1;
	}

	public String getBillToPartyAddress2() {
		return billToPartyAddress2;
	}

	public String getBillToPartyAddress3() {
		return billToPartyAddress3;
	}

	public String getBillToPartyAddress4() {
		return billToPartyAddress4;
	}

	public boolean getBillToPartyCashSale() {
		return billToPartyCashSale;
	}

	public String getBillToPartyCity() {
		return billToPartyCity;
	}

	public boolean getBillToPartyContractSigner() {
		return billToPartyContractSigner;
	}

	public String getBillToPartyEMailAddress() {
		return billToPartyEMailAddress;
	}

	public String getBillToPartyFirstName() {
		return billToPartyFirstName;
	}

	public String getBillToPartyHomePhone() {
		return billToPartyHomePhone;
	}

	public String getBillToPartyId() {
		return billToPartyId;
	}

	public String getBillToPartyLanguage() {
		return billToPartyLanguage;
	}

	public String getBillToPartyLastName() {
		return billToPartyLastName;
	}

	public boolean getBillToPartyReceiveInvoice() {
		return billToPartyReceiveInvoice;
	}

	public boolean getBillToPartyRefused() {
		return billToPartyRefused;
	}

	public String getBillToPartyRelation() {
		return billToPartyRelation;
	}

	public String getBillToPartySocialSecurityNumber() {
		return billToPartySocialSecurityNumber;
	}

	public String getBillToPartyState() {
		return billToPartyState;
	}

	public String getBillToPartyTitle() {
		return billToPartyTitle;
	}

	public String getBillToPartyWorkPhone() {
		return billToPartyWorkPhone;
	}

	public String getBillToPartyWorkPhoneExt() {
		return billToPartyWorkPhoneExt;
	}

	public String getBillToPartyZipCode() {
		return billToPartyZipCode;
	}

	public String getDirective() {
		return directive;
	}

	public void setBillToPartyAddress1(String in) {
		billToPartyAddress1 = in;
	}

	public void setBillToPartyAddress2(String in) {
		billToPartyAddress2 = in;
	}

	public void setBillToPartyAddress3(String in) {
		billToPartyAddress3 = in;
	}

	public void setBillToPartyAddress4(String in) {
		billToPartyAddress4 = in;
	}

	public void setBillToPartyCashSale(boolean in) {
		billToPartyCashSale = in;
	}

	public void setBillToPartyCity(String in) {
		billToPartyCity = in;
	}

	public void setBillToPartyContractSigner(boolean in) {
		billToPartyContractSigner = in;
	}

	public void setBillToPartyEMailAddress(String in) {
		billToPartyEMailAddress = in;
	}

	public void setBillToPartyFirstName(String in) {
		billToPartyFirstName = in;
	}

	public void setBillToPartyHomePhone(String in) {
		billToPartyHomePhone = in;
	}

	public void setBillToPartyId(String in) {
		billToPartyId = in;
	}

	public void setBillToPartyLanguage(String in) {
		billToPartyLanguage = in;
	}

	public void setBillToPartyLastName(String in) {
		billToPartyLastName = in;
	}

	public void setBillToPartyReceiveInvoice(boolean in) {
		billToPartyReceiveInvoice = in;
	}

	public void setBillToPartyRefused(boolean in) {
		billToPartyRefused = in;
	}

	public void setBillToPartyRelation(String in) {
		billToPartyRelation = in;
	}

	public void setBillToPartySocialSecurityNumber(String in) {
		billToPartySocialSecurityNumber = in;
	}

	public void setBillToPartyState(String in) {
		billToPartyState = in;
	}

	public void setBillToPartyTitle(String in) {
		billToPartyTitle = in;
	}

	public void setBillToPartyWorkPhone(String in) {
		billToPartyWorkPhone = in;
	}

	public void setBillToPartyWorkPhoneExt(String in) {
		billToPartyWorkPhoneExt = in;
	}

	public void setBillToPartyZipCode(String in) {
		billToPartyZipCode = in;
	}

	public void setDirective(String in) {
		directive = in;
	}

	public String getBillToPartyCellPhone() {
		return billToPartyCellPhone;
	}

	public void setBillToPartyCellPhone(String billToPartyCellPhone) {
		this.billToPartyCellPhone = billToPartyCellPhone;
	}

	public boolean isShowCashSale() {
		return showCashSale;
	}

	public boolean isShowRefused() {
		return showRefused;
	}

	public void setShowCashSale(boolean showCashSale) {
		this.showCashSale = showCashSale;
	}

	public void setShowRefused(boolean showRefused) {
		this.showRefused = showRefused;
	}
	
}