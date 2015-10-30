package com.aldorassist.webfdms.model;

import java.sql.Date;

public class InvoiceDTO {
	
	public static final int FREQ_HOURLY = 1;
	public static final int FREQ_DAILY = 2;
	public static final int FREQ_WEEKLY = 3;
	public static final int FREQ_BIWEEKLY_24 = 4;
	public static final int FREQ_MONTHLY = 5;
	public static final int FREQ_ANNUALY = 6;
	
	public static final int CHECK_STATUS_PRINT_CHECK_NOW = 1;
	public static final int CHECK_STATUS_CHECK_GEN_MANUAL = 2;
	public static final int CHECK_STATUS_SAVE_INVOICE = 3;
	
		// Create is assigned to an invoice status when it is created
	public static final String INVOICE_CREATE = 	"C";
	  // Submit is assigned to an invoice when the invoice is submitted for approval or denial
	public static final String INVOICE_SUBMIT = 	"S";
		// Deny is assigned to an invoice when the invoice is denied from the accountant.  From
	  // a deny status the next is delete.
	public static final String INVOICE_DENY =   	"d";
		// Delete is assigned to an invoice that was denied
	public static final String INVOICE_DELETE = 	"D";
		// Approved is assigned to an invoice after it has been submitted and after an accountant approves it
		// for check printing.
	public static final String INVOICE_APPROVE = 	"A";
		// Print is assigned to an invoice after it has been printed.
	public static final String INVOICE_PRINT = 		"P";
		// Void is assigned to an invoice after a check has been printed and then voided.
	public static final String INVOICE_VOID =			"V";
	
	private int invoiceID;

	private int vendorID;

	private String invoiceNumber;

	private Date invoiceDate;

	private Date invoiceDueDate;

	private boolean invoicePaid;

	private String glcategory;

	private double amountOfInvoice;

	private String description;

	private int invoice1099Type;
	
	private boolean discountFlag;

	private double invoice1099Amount;

	private double discountAmount;
	
	private double discountSubjectAmount;

	private double discountPercent;

	private double discountDollars;

	private int discountDue;

	private int discountDueFreq;

	private Date discountDueDate;

	private boolean recurringFlag;

	private int recurringCount;

	private int recurringFreq;
	
	private int recurringFreqCode;

	private int checkingAccount;

	private int checkingStatus;
	
	private int locationID;
	
	private boolean checkCreated;
	
	private double netInvoice;
	
	private String invoiceStatus;
	
	private boolean submitInvoice;
	
	private boolean approveInvoice;
	
	private boolean deleteInvoice;
	
	private boolean updateInvoice;
	
	private boolean printInvoice;
	
	private long checkNumber;
	
	private int apMasterID;
	
	public int getApMasterID() {
		return apMasterID;
	}

	public void setApMasterID(int apMasterID) {
		this.apMasterID = apMasterID;
	}

	/**
	 * @return the checkNumber
	 */
	public long getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(long checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @return the printedInvoice
	 */
	public boolean isPrintInvoice() {
		return printInvoice;
	}

	/**
	 * @param printedInvoice the printedInvoice to set
	 */
	public void setPrintInvoice(boolean printInvoice) {
		this.printInvoice = printInvoice;
	}

	/**
	 * @return the updateInvoice
	 */
	public boolean isUpdateInvoice() {
		return updateInvoice;
	}

	/**
	 * @param updateInvoice the updateInvoice to set
	 */
	public void setUpdateInvoice(boolean updateInvoice) {
		this.updateInvoice = updateInvoice;
	}

	/**
	 * @return the deleteInvoice
	 */
	public boolean isDeleteInvoice() {
		return deleteInvoice;
	}

	/**
	 * @param deleteInvoice the deleteInvoice to set
	 */
	public void setDeleteInvoice(boolean deleteInvoice) {
		this.deleteInvoice = deleteInvoice;
	}

	/**
	 * @return the approveInvoice
	 */
	public boolean isApproveInvoice() {
		return approveInvoice;
	}

	/**
	 * @param approveInvoice the approveInvoice to set
	 */
	public void setApproveInvoice(boolean approveInvoice) {
		this.approveInvoice = approveInvoice;
	}

	/**
	 * @return the invoiceStatus
	 */
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	/**
	 * @param invoiceStatus the invoiceStatus to set
	 */
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	/**
	 * @return the netInvoice
	 */
	public double getNetInvoice() {
		return netInvoice;
	}

	/**
	 * @param netInvoice the netInvoice to set
	 */
	public void setNetInvoice(double netInvoice) {
		this.netInvoice = netInvoice;
	}

	/**
	 * @return the amountOfInvoice
	 */
	public double getAmountOfInvoice() {
		return amountOfInvoice;
	}

	/**
	 * @param amountOfInvoice
	 *            the amountOfInvoice to set
	 */
	public void setAmountOfInvoice(double amountOfInvoice) {
		this.amountOfInvoice = amountOfInvoice;
	}

	/**
	 * @return the checkingAccount
	 */
	public int getCheckingAccount() {
		return checkingAccount;
	}

	/**
	 * @param checkingAccount
	 *            the checkingAccount to set
	 */
	public void setCheckingAccount(int checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	/**
	 * @return the checkingStatus
	 */
	public int getCheckingStatus() {
		return checkingStatus;
	}

	/**
	 * @param checkingStatus
	 *            the checkingStatus to set
	 */
	public void setCheckingStatus(int checkingStatus) {
		this.checkingStatus = checkingStatus;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the discountAmount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount
	 *            the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the discountDollars
	 */
	public double getDiscountDollars() {
		return discountDollars;
	}

	/**
	 * @param discountDollars
	 *            the discountDollars to set
	 */
	public void setDiscountDollars(double discountDollars) {
		this.discountDollars = discountDollars;
	}

	/**
	 * @return the discountDue
	 */
	public int getDiscountDue() {
		return discountDue;
	}

	/**
	 * @param discountDue
	 *            the discountDue to set
	 */
	public void setDiscountDue(int discountDue) {
		this.discountDue = discountDue;
	}

	/**
	 * @return the discountDueDate
	 */
	public Date getDiscountDueDate() {
		return discountDueDate;
	}

	/**
	 * @param discountDueDate
	 *            the discountDueDate to set
	 */
	public void setDiscountDueDate(Date discountDueDate) {
		this.discountDueDate = discountDueDate;
	}

	/**
	 * @return the discountDueFreq
	 */
	public int getDiscountDueFreq() {
		return discountDueFreq;
	}

	/**
	 * @param discountDueFreq
	 *            the discountDueFreq to set
	 */
	public void setDiscountDueFreq(int discountDueFreq) {
		this.discountDueFreq = discountDueFreq;
	}

	/**
	 * @return the discountPercent
	 */
	public double getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * @param discountPercent
	 *            the discountPercent to set
	 */
	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * @return the glcategory
	 */
	public String getGlcategory() {
		return glcategory;
	}

	/**
	 * @param glcategory
	 *            the glcategory to set
	 */
	public void setGlcategory(String glcategory) {
		this.glcategory = glcategory;
	}

	/**
	 * @return the invoice1099Amount
	 */
	public double getInvoice1099Amount() {
		return invoice1099Amount;
	}

	/**
	 * @param invoice1099Amount
	 *            the invoice1099Amount to set
	 */
	public void setInvoice1099Amount(double invoice1099Amount) {
		this.invoice1099Amount = invoice1099Amount;
	}

	/**
	 * @return the invoice1099Type
	 */
	public int getInvoice1099Type() {
		return invoice1099Type;
	}

	/**
	 * @param invoice1099Type
	 *            the invoice1099Type to set
	 */
	public void setInvoice1099Type(int invoice1099Type) {
		this.invoice1099Type = invoice1099Type;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate
	 *            the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the invoiceDueDate
	 */
	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}

	/**
	 * @param invoiceDueDate
	 *            the invoiceDueDate to set
	 */
	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	/**
	 * @return the invoiceID
	 */
	public int getInvoiceID() {
		return invoiceID;
	}

	/**
	 * @param invoiceID
	 *            the invoiceID to set
	 */
	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *            the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the invoicePaid
	 */
	public boolean isInvoicePaid() {
		return invoicePaid;
	}

	/**
	 * @param invoicePaid
	 *            the invoicePaid to set
	 */
	public void setInvoicePaid(boolean invoicePaid) {
		this.invoicePaid = invoicePaid;
	}

	/**
	 * @return the recurringCount
	 */
	public int getRecurringCount() {
		return recurringCount;
	}

	/**
	 * @param recurringCount
	 *            the recurringCount to set
	 */
	public void setRecurringCount(int recurringCount) {
		this.recurringCount = recurringCount;
	}

	/**
	 * @return the recurringFlag
	 */
	public boolean isRecurringFlag() {
		return recurringFlag;
	}

	/**
	 * @param recurringFlag
	 *            the recurringFlag to set
	 */
	public void setRecurringFlag(boolean recurringFlag) {
		this.recurringFlag = recurringFlag;
	}

	/**
	 * @return the recurringFreqCode
	 */
	public int getRecurringFreqCode() {
		return recurringFreqCode;
	}

	/**
	 * @param recurringFreqCode
	 *            the recurringFreqCode to set
	 */
	public void setRecurringFreqCode(int recurringFreqCode) {
		this.recurringFreqCode = recurringFreqCode;
	}

	/**
	 * @return the vendorID
	 */
	public int getVendorID() {
		return vendorID;
	}

	/**
	 * @param vendorID
	 *            the vendorID to set
	 */
	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	/**
	 * @return the locationID
	 */
	public int getLocationID() {
		return locationID;
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	/**
	 * @return the recurringFreq
	 */
	public int getRecurringFreq() {
		return recurringFreq;
	}

	/**
	 * @param recurringFreq the recurringFreq to set
	 */
	public void setRecurringFreq(int recurringFreq) {
		this.recurringFreq = recurringFreq;
	}

	/**
	 * @return the discountSubjectAmount
	 */
	public double getDiscountSubjectAmount() {
		return discountSubjectAmount;
	}

	/**
	 * @param discountSubjectAmount the discountSubjectAmount to set
	 */
	public void setDiscountSubjectAmount(double discountSubjectAmount) {
		this.discountSubjectAmount = discountSubjectAmount;
	}

	public boolean isDiscountFlag() {
		return discountFlag;
	}

	public void setDiscountFlag(boolean discountFlag) {
		this.discountFlag = discountFlag;
	}

	/**
	 * @return the checkCreated
	 */
	public boolean isCheckCreated() {
		return checkCreated;
	}

	/**
	 * @param checkCreated the checkCreated to set
	 */
	public void setCheckCreated(boolean checkCreated) {
		this.checkCreated = checkCreated;
	}

	/**
	 * @return the submitInvoice
	 */
	public boolean isSubmitInvoice() {
		return submitInvoice;
	}

	/**
	 * @param submitInvoice the submitInvoice to set
	 */
	public void setSubmitInvoice(boolean submitInvoice) {
		this.submitInvoice = submitInvoice;
	}
	
}