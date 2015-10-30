package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;

/**
 * DbInvoice - This class represents the information for one invoice Creation
 * date: (12/27/2002 2:42:35 PM)
 * 
 * @author:
 */
public class DbInvoice extends Persistent {
	static private final DbInvoicePeer peer = new DbInvoicePeer();

	public static final int FREQ_HOURLY = 1;
	public static final int FREQ_DAILY = 2;
	public static final int FREQ_WEEKLY = 3;
	public static final int FREQ_BIWEEKLY_24 = 4;
	public static final int FREQ_MONTHLY = 5;
	public static final int FREQ_ANNUALY = 6;

	public static final int CHECK_STATUS_PRINT_CHECK_NOW = 1;
	public static final int CHECK_STATUS_CHECK_GEN_MANUAL = 2;
	public static final int CHECK_STATUS_SAVE_INVOICE = 3;

	private int vendorID;
	private int locationID;
	private String invoiceNumber;
	private Date invoiceDate;
	private Date invoiceDueDate;
	private boolean invoicePaid;
	private String glcategory;
	private double amountOfInvoice;
	private String description;
	private int invoice1099Type;
	private double invoice1099Amount;
	private boolean discountFlag;
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
	private boolean checkCreated;
	private String invoiceStatus;
	private int apMasterID;

	protected PersistentPeer getPersistentPeer() {
	return peer;
	}

	public boolean isLocked() {
	return false;
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object restores
	 * from database to hashtable.
	 */
	public void restore(Transaction t, Hashtable data)
			throws PersistenceException {

	setId(Integer.parseInt(data.get(DbInvoicePeer.INVOICEID).toString()));
	vendorID = Integer.parseInt(data.get(DbInvoicePeer.VENDORID).toString());
	invoiceNumber = data.get(DbInvoicePeer.INVOICENUMBER).toString();
	try {
		invoiceDate = (Date) data.get(DbInvoicePeer.INVOICEDATE);
	} catch (ClassCastException e) {
		invoiceDate = null;
	}
	try {
		invoiceDueDate = (Date) data.get(DbInvoicePeer.INVOICEDUEDATE);
	} catch (ClassCastException e) {
		invoiceDueDate = null;
	}
	invoicePaid = Boolean.parseBoolean(data.get(DbInvoicePeer.INVOICEPAID)
			.toString());
	glcategory = data.get(DbInvoicePeer.GLCATEGORY).toString();
	amountOfInvoice = Double.parseDouble(data.get(DbInvoicePeer.AMOUNTOFINVOICE)
			.toString());
	description = data.get(DbInvoicePeer.DESCRIPTION).toString();
	invoice1099Type = Integer.parseInt(data.get(DbInvoicePeer.TYPE1099)
			.toString());
	invoice1099Amount = Double.parseDouble(data.get(DbInvoicePeer.AMOUNT1099)
			.toString());
	discountFlag = Boolean.parseBoolean(data.get(DbInvoicePeer.DISCOUNTFLAG)
			.toString());
	discountAmount = Double.parseDouble(data.get(DbInvoicePeer.DISCOUNTAMOUNT)
			.toString());
	discountSubjectAmount = Double.parseDouble(data.get(
			DbInvoicePeer.DISCOUNTSUBJECTAMOUNT).toString());
	discountPercent = Double.parseDouble(data.get(DbInvoicePeer.DISCOUNTPERCENT)
			.toString());
	discountDollars = Double.parseDouble(data.get(DbInvoicePeer.DISCOUNTDOLLARS)
			.toString());
	discountDue = Integer
			.parseInt(data.get(DbInvoicePeer.DISCOUNTDUE).toString());
	discountDueFreq = Integer.parseInt(data
			.get(DbInvoicePeer.DISCOUNTDUEFREQCODE).toString());

	try {
		discountDueDate = (Date) data.get(DbInvoicePeer.DISCOUNTDUEDATE);
	} catch (ClassCastException e) {
		discountDueDate = null;
	}

	recurringFlag = Boolean.parseBoolean(data.get(DbInvoicePeer.RECURRINGFLAG)
			.toString());
	recurringCount = Integer.parseInt(data.get(DbInvoicePeer.RECURRINGCOUNT)
			.toString());
	recurringFreq = Integer.parseInt(data.get(DbInvoicePeer.RECURRINGFREQ)
			.toString());
	recurringFreqCode = Integer.parseInt(data
			.get(DbInvoicePeer.RECURRINGFREQCODE).toString());
	checkingAccount = Integer.parseInt(data.get(DbInvoicePeer.CHECKINGACCOUNT)
			.toString());
	checkingStatus = Integer.parseInt(data.get(DbInvoicePeer.CHECKINGSTATUS)
			.toString());
	locationID = Integer.parseInt(data.get(DbInvoicePeer.LOCATIONID).toString());
	checkCreated = Boolean.parseBoolean(data.get(DbInvoicePeer.CHECKCREATED)
			.toString());
	invoiceStatus = data.get(DbInvoicePeer.INVOICESTATUS).toString();
	apMasterID = Integer.parseInt(data.get(DbInvoicePeer.APMASTERID).toString());
	}

	public void setId(Hashtable h) {
	modify();
	setId(((Integer) h.get(DbInvoicePeer.INVOICEID)).intValue());
	}

	public int getVendorID() {
	return vendorID;
	}

	public void setVendorID(int vendorID) {
	modify();
	this.vendorID = vendorID;
	}

	public String getInvoiceNumber() {
	return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
	modify();
	this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
	return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
	modify();
	this.invoiceDate = invoiceDate;
	}

	public Date getInvoiceDueDate() {
	return invoiceDueDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
	modify();
	this.invoiceDueDate = invoiceDueDate;
	}

	public boolean getInvoicePaid() {
	return invoicePaid;
	}

	public void setInvoicePaid(boolean invoicePaid) {
	modify();
	this.invoicePaid = invoicePaid;
	}

	public String getGlcategory() {
	return glcategory;
	}

	public void setGlcategory(String glcategory) {
	modify();
	this.glcategory = glcategory;
	}

	public double getAmountOfInvoice() {
	return amountOfInvoice;
	}

	public void setAmountOfInvoice(double amountOfInvoice) {
	modify();
	this.amountOfInvoice = amountOfInvoice;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	modify();
	this.description = description;
	}

	public int getInvoice1099Type() {
	return invoice1099Type;
	}

	public void setInvoice1099Type(int invoice1099Type) {
	modify();
	this.invoice1099Type = invoice1099Type;
	}

	public boolean getDiscountFlag() {
	return discountFlag;
	}

	public void setDiscountFlag(boolean discountFlag) {
	modify();
	this.discountFlag = discountFlag;
	}

	public double getInvoice1099Amount() {
	return invoice1099Amount;
	}

	public void setInvoice1099Amount(double invoice1099Amount) {
	modify();
	this.invoice1099Amount = invoice1099Amount;
	}

	public double getDiscountAmount() {
	return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
	modify();
	this.discountAmount = discountAmount;
	}

	public double getDiscountSubjectAmount() {
	return discountSubjectAmount;
	}

	public void setDiscountSubjectAmount(double discountSubjectAmount) {
	modify();
	this.discountSubjectAmount = discountSubjectAmount;
	}

	public double getDiscountPercent() {
	return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
	modify();
	this.discountPercent = discountPercent;
	}

	public double getDiscountDollars() {
	return discountDollars;
	}

	public void setDiscountDollars(double discountDollars) {
	modify();
	this.discountDollars = discountDollars;
	}

	public int getDiscountDue() {
	return discountDue;
	}

	public void setDiscountDue(int discountDue) {
	modify();
	this.discountDue = discountDue;
	}

	public int getDiscountDueFreq() {
	return discountDueFreq;
	}

	public void setDiscountDueFreq(int discountDueFreq) {
	modify();
	this.discountDueFreq = discountDueFreq;
	}

	public Date getDiscountDueDate() {
	return discountDueDate;
	}

	public void setDiscountDueDate(Date discountDueDate) {
	modify();
	this.discountDueDate = discountDueDate;
	}

	public boolean getRecurringFlag() {
	return recurringFlag;
	}

	public void setRecurringFlag(boolean recurringFlag) {
	modify();
	this.recurringFlag = recurringFlag;
	}

	public int getRecurringCount() {
	return recurringCount;
	}

	public void setRecurringCount(int recurringCount) {
	modify();
	this.recurringCount = recurringCount;
	}

	public int getRecurringFreq() {
	return recurringFreq;
	}

	public void setRecurringFreq(int recurringFreq) {
	modify();
	this.recurringFreq = recurringFreq;
	}

	public int getRecurringFreqCode() {
	return recurringFreqCode;
	}

	public void setRecurringFreqCode(int recurringFreqCode) {
	modify();
	this.recurringFreqCode = recurringFreqCode;
	}

	public int getCheckingAccount() {
	return checkingAccount;
	}

	public void setCheckingAccount(int checkingAccount) {
	modify();
	this.checkingAccount = checkingAccount;
	}

	public int getCheckingStatus() {
	return checkingStatus;
	}

	public void setCheckingStatus(int checkingStatus) {
	modify();
	this.checkingStatus = checkingStatus;
	}

	public int getLocationID() {
	return locationID;
	}

	public void setLocationID(int locationID) {
	modify();
	this.locationID = locationID;
	}

	public boolean getCheckCreated() {
	return checkCreated;
	}

	public void setCheckCreated(boolean checkCreated) {
	modify();
	this.checkCreated = checkCreated;
	}

	/**
	 * @return the invoiceStatus
	 */
	public String getInvoiceStatus() {
	return invoiceStatus;
	}

	/**
	 * @param invoiceStatus
	 *          the invoiceStatus to set
	 */
	public void setInvoiceStatus(String invoiceStatus) {
	modify();
	this.invoiceStatus = invoiceStatus;
	}

	public int getApMasterID() {
		return apMasterID;
	}

	public void setApMasterID(int apMasterID) {
		modify();
		this.apMasterID = apMasterID;
	}
}
