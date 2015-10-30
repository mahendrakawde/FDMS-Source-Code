package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FinancialInformationForm extends FinancialInformationFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3702742030340197873L;

	private String priceListVersion;

	private int vitalsId;

	private java.lang.String directive;

	private int chapelLocation;

	private java.lang.String postedContract; // original state of
												// sent-to-accounting flag

	private java.lang.String removeline;
	
	private boolean saveNeeded = false;

	// fields below added for deposit information in financials section
	private String voidedContract = "";

	private String voidedDeposit = "";

	private String depositId; // TransHistId column in transactionhistory
								// table

	private String componentId; // PaymentComponent column in transactionhistory
								// table

	private String paymentSource; // Description column in transactionhistory
									// table

	private String amountPaid; // AmountOfTran column in transactionhistory
								// table

	private String dateOfDeposit; // DateOfTran column in transactionhistory
									// table

	private String dateOfPayment; // DatePaid column in transactionhistory
									// table

	private String receiptNumber; // ReceiptNumber column in
									// transactionhistory table

	private String manualReceiptNumber; // ManualReceiptNumber column in
										// transactionhistory table

	private String nonCashAdjustment; // GLAcct column in transactionhistory
										// table

	private String methodOfPayment; // PaymentMethod column in
									// transactionhistory table

	private boolean showFinancingSection = false;

	
	private boolean showServicePlan = false;
	
	private String salesDescCDID;  // ID for the combodata table.
	
	private boolean postedDeposit;
	
	private boolean saleTaxBalance;
	
	private int orderByContLine;

	

	public int getOrderByContLine() {
		return orderByContLine;
	}

	public void setOrderByContLine(int orderByContLine) {
		this.orderByContLine = orderByContLine;
	}

	/**
	 * @return the postedDeposit
	 */
	public boolean isPostedDeposit() {
		return postedDeposit;
	}

	/**
	 * @param postedDeposit the postedDeposit to set
	 */
	public void setPostedDeposit(boolean postedDeposit) {
		this.postedDeposit = postedDeposit;
	}

	/**
	 * @return the salesDescCDID
	 */
	public String getSalesDescCDID() {
		return salesDescCDID;
	}

	/**
	 * @param salesDescCDID the salesDescCDID to set
	 */
	public void setSalesDescCDID(String salesDescCDID) {
		this.salesDescCDID = salesDescCDID;
	}

	/**
	 * Insert the method's description here. Creation date: (4/11/2002 10:43:28
	 * AM)
	 * 
	 * @return int
	 */
	public int getChapelLocation() {
		return chapelLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (4/3/2002 2:13:22
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDirective() {
		return directive;
	}

	/**
	 * Insert the method's description here. Creation date: (11/28/2002 10:03:17
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPostedContract() {
		return postedContract;
	}

	public String getPriceListVersion() {
		return priceListVersion;
	}

	/**
	 * Insert the method's description here. Creation date: (6/23/2003 3:40:10
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getRemoveline() {
		return removeline;
	}

	/**
	 * Insert the method's description here. Creation date: (4/3/2002 2:10:23
	 * PM)
	 * 
	 * @return int
	 */
	public int getVitalsId() {
		return vitalsId;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
		setApplyFinanceCharges(false);
	}

	/**
	 * Insert the method's description here. Creation date: (4/11/2002 10:43:28
	 * AM)
	 * 
	 * @param newChapelLocation
	 *            int
	 */
	public void setChapelLocation(int newChapelLocation) {
		chapelLocation = newChapelLocation;
	}

	/**
	 * Insert the method's description here. Creation date: (4/3/2002 2:13:22
	 * PM)
	 * 
	 * @param newDirective
	 *            java.lang.String
	 */
	public void setDirective(java.lang.String newDirective) {
		directive = newDirective;
	}

	/**
	 * Insert the method's description here. Creation date: (11/28/2002 10:03:17
	 * PM)
	 * 
	 * @param newPostedContract
	 *            java.lang.String
	 */
	public void setPostedContract(java.lang.String newPostedContract) {
		postedContract = newPostedContract;
	}

	public void setPriceListVersion(String in) {
		priceListVersion = in;
	}

	/**
	 * Insert the method's description here. Creation date: (6/23/2003 3:40:10
	 * PM)
	 * 
	 * @param newRemoveline
	 *            java.lang.String
	 */
	public void setRemoveline(java.lang.String newRemoveline) {
		removeline = newRemoveline;
	}

	/**
	 * Insert the method's description here. Creation date: (4/3/2002 2:10:23
	 * PM)
	 * 
	 * @param newVitalsId
	 *            int
	 */
	public void setVitalsId(int newVitalsId) {
		vitalsId = newVitalsId;
	}

	public void setVoidedContract(String voidedContract) {
		this.voidedContract = voidedContract;
	}

	public String getVoidedContract() {
		return voidedContract;
	}

	public void setVoidedDeposit(String voidedDeposit) {
		this.voidedDeposit = voidedDeposit;
	}

	public String getVoidedDeposit() {
		return voidedDeposit;
	}

	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}

	public String getDepositId() {
		return depositId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setPaymentSource(String paymentSource) {
		this.paymentSource = paymentSource;
	}

	public String getPaymentSource() {
		return paymentSource;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setDateOfDeposit(String dateOfDeposit) {
		this.dateOfDeposit = dateOfDeposit;
	}

	public String getDateOfDeposit() {
		return dateOfDeposit;
	}

	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public String getDateOfPayment() {
		return dateOfPayment;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setManualReceiptNumber(String manualReceiptNumber) {
		this.manualReceiptNumber = manualReceiptNumber;
	}

	public String getManualReceiptNumber() {
		return manualReceiptNumber;
	}

	public void setNonCashAdjustment(String nonCashAdjustment) {
		this.nonCashAdjustment = nonCashAdjustment;
	}

	public String getNonCashAdjustment() {
		return nonCashAdjustment;
	}

	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	public String getMethodOfPayment() {
		return methodOfPayment;
	}

	/**
	 * @return the showFinancingSection
	 */
	public boolean isShowFinancingSection() {
		return showFinancingSection;
	}

	/**
	 * @param showFinancingSection
	 *            the showFinancingSection to set
	 */
	public void setShowFinancingSection(boolean showFinancingSection) {
		this.showFinancingSection = showFinancingSection;
	}

	public boolean isShowServicePlan() {
		return showServicePlan;
	}

	public void setShowServicePlan(boolean showServicePlan) {
		this.showServicePlan = showServicePlan;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	/**
	 * @return the dueDateAvailable
	 */
	public boolean isDueDateAvailable() {
	if(getDueDate() == null || getDueDate().trim().length() <= 0){
			return false;
		}else
		{
			return true;
		}
	}

	/**
	 * @return the saveNeeded
	 */
	public boolean isSaveNeeded() {
		return saveNeeded;
	}

	/**
	 * @param saveNeeded the saveNeeded to set
	 */
	public void setSaveNeeded(boolean saveNeeded) {
		this.saveNeeded = saveNeeded;
	}

	/**
	 * @return the saleTaxBalance
	 */
	public boolean isSaleTaxBalance() {
		return saleTaxBalance;
	}

	/**
	 * @param saleTaxBalance the saleTaxBalance to set
	 */
	public void setSaleTaxBalance(boolean saleTaxBalance) {
		this.saleTaxBalance = saleTaxBalance;
	}
	
	
}
