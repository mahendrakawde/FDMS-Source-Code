package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PnInstallmentPaymentBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2943188436948932100L;

	private String submitButton;

	private String paymentHistorySelected;

	private String totalCheckAmount;

	private String numberPaymentsMade;

	private String vitalsId;

	private String totalPaidToDate;

	private String totalContractAmount;

	private String escrowAmount;

	private String checkDate;

	private String commissionPercent;

	private String fundsFor;

	private int y;

	private int x;

	private String lastPaymentAmount;

	private String formName;

	private String contractId;

	private String lastPaymentDate;

	private String commissionAmount;

	private java.lang.String memo;

	private java.lang.String totalFinancedAmount;

	private java.lang.String commSentRetained;

	private java.lang.String payoffAmount;

	public String getCheckDate() {
		return checkDate;
	}

	public String getCommissionAmount() {
		return commissionAmount;
	}

	public String getCommissionPercent() {
		return commissionPercent;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 2:57:12
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCommSentRetained() {
		return commSentRetained;
	}

	public String getContractId() {
		return contractId;
	}

	public String getEscrowAmount() {
		return escrowAmount;
	}

	public String getFormName() {
		return formName;
	}

	public String getFundsFor() {
		return fundsFor;
	}

	public String getLastPaymentAmount() {
		return lastPaymentAmount;
	}

	public String getLastPaymentDate() {
		return lastPaymentDate;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 1:48:18
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getMemo() {
		return memo;
	}

	public String getNumberPaymentsMade() {
		return numberPaymentsMade;
	}

	public String getPaymentHistorySelected() {
		return paymentHistorySelected;
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2003 8:07:47
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPayoffAmount() {
		return payoffAmount;
	}

	public String getSubmitButton() {
		return submitButton;
	}

	public String getTotalCheckAmount() {
		return totalCheckAmount;
	}

	public String getTotalContractAmount() {
		return totalContractAmount;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 2:12:58
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTotalFinancedAmount() {
		return totalFinancedAmount;
	}

	public String getTotalPaidToDate() {
		return totalPaidToDate;
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

	public void setCheckDate(String in) {
		checkDate = in;
	}

	public void setCommissionAmount(String in) {
		commissionAmount = in;
	}

	public void setCommissionPercent(String in) {
		commissionPercent = in;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 2:57:12
	 * PM)
	 * 
	 * @param newCommSentRetained
	 *            java.lang.String
	 */
	public void setCommSentRetained(java.lang.String newCommSentRetained) {
		commSentRetained = newCommSentRetained;
	}

	public void setContractId(String in) {
		contractId = in;
	}

	public void setEscrowAmount(String in) {
		escrowAmount = in;
	}

	public void setFormName(String in) {
		formName = in;
	}

	public void setFundsFor(String in) {
		fundsFor = in;
	}

	public void setLastPaymentAmount(String in) {
		lastPaymentAmount = in;
	}

	public void setLastPaymentDate(String in) {
		lastPaymentDate = in;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 1:48:18
	 * PM)
	 * 
	 * @param newMemo
	 *            java.lang.String
	 */
	public void setMemo(java.lang.String newMemo) {
		memo = newMemo;
	}

	public void setNumberPaymentsMade(String in) {
		numberPaymentsMade = in;
	}

	public void setPaymentHistorySelected(String in) {
		paymentHistorySelected = in;
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2003 8:07:47
	 * AM)
	 * 
	 * @param newPayoffAmount
	 *            java.lang.String
	 */
	public void setPayoffAmount(java.lang.String newPayoffAmount) {
		payoffAmount = newPayoffAmount;
	}

	public void setSubmitButton(String in) {
		submitButton = in;
	}

	public void setTotalCheckAmount(String in) {
		totalCheckAmount = in;
	}

	public void setTotalContractAmount(String in) {
		totalContractAmount = in;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 2:12:58
	 * PM)
	 * 
	 * @param newTotalFinancedAmount
	 *            java.lang.String
	 */
	public void setTotalFinancedAmount(java.lang.String newTotalFinancedAmount) {
		totalFinancedAmount = newTotalFinancedAmount;
	}

	public void setTotalPaidToDate(String in) {
		totalPaidToDate = in;
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
