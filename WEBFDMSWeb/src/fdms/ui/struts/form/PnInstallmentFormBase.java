package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PnInstallmentFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6639653533406654832L;

	private String inflationRate;

	private String contractId;

	private String totalCharges;

	private String submitButton;

	private String amountFinanced;

	private String financeCharge;

	private String numberOfPayments;

	private int y;

	private int x;

	private String totalSalePrice;

	private String futureValue;

	private String totalPayments;

	private String firstPaymentDate;

	private String vitalsId;

	private String downPayment;

	private String interestRate;

	private java.lang.String paymentAmt;

	public String getAmountFinanced() {
		return amountFinanced;
	}

	public String getContractId() {
		return contractId;
	}

	public String getDownPayment() {
		return downPayment;
	}

	public String getFinanceCharge() {
		return financeCharge;
	}

	public String getFirstPaymentDate() {
		return firstPaymentDate;
	}

	public String getFutureValue() {
		return futureValue;
	}

	public String getInflationRate() {
		return inflationRate;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public String getNumberOfPayments() {
		return numberOfPayments;
	}

	/**
	 * Insert the method's description here. Creation date: (1/14/2003 7:12:24
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPaymentAmt() {
		return paymentAmt;
	}

	public String getSubmitButton() {
		return submitButton;
	}

	public String getTotalCharges() {
		return totalCharges;
	}

	public String getTotalPayments() {
		return totalPayments;
	}

	public String getTotalSalePrice() {
		return totalSalePrice;
	}

	public String getVitalsId() {
		return vitalsId;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setAmountFinanced(String in) {
		amountFinanced = in;
	}

	public void setContractId(String in) {
		contractId = in;
	}

	public void setDownPayment(String in) {
		downPayment = in;
	}

	public void setFinanceCharge(String in) {
		financeCharge = in;
	}

	public void setFirstPaymentDate(String in) {
		firstPaymentDate = in;
	}

	public void setFutureValue(String in) {
		futureValue = in;
	}

	public void setInflationRate(String in) {
		inflationRate = in;
	}

	public void setInterestRate(String in) {
		interestRate = in;
	}

	public void setNumberOfPayments(String in) {
		numberOfPayments = in;
	}

	/**
	 * Insert the method's description here. Creation date: (1/14/2003 7:12:24
	 * AM)
	 * 
	 * @param newPaymentAmt
	 *            java.lang.String
	 */
	public void setPaymentAmt(java.lang.String newPaymentAmt) {
		paymentAmt = newPaymentAmt;
	}

	public void setSubmitButton(String in) {
		submitButton = in;
	}

	public void setTotalCharges(String in) {
		totalCharges = in;
	}

	public void setTotalPayments(String in) {
		totalPayments = in;
	}

	public void setTotalSalePrice(String in) {
		totalSalePrice = in;
	}

	public void setVitalsId(String in) {
		vitalsId = in;
	}

	public void setX(int in) {
		x = in;
	}

	public void setY(int in) {
		y = in;
	}
}
