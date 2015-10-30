package com.aldorsolutions.webfdms.beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * Class representing the financial, non repetitive, details for one contract.
 * Creation date: (11/14/2001 3:55:17 PM)
 * 
 * @author:
 */
public class DbFinancialSummary extends com.aldorsolutions.webfdms.database.Persistent {

	private Logger logger = Logger.getLogger(DbFinancialSummary.class.getName());

	static private final DbFinancialSummaryPeer peer = new DbFinancialSummaryPeer();

	private String cFinSaleType; // Converted from char[25]

	private short iFinServiceChargesBox;

	private float fFinServiceChargeRate;

	private String cFinSaleDate; // Converted from char[8]

	private String cFinDueDate; // Converted from char[8]

	private String cFinDateInvoiceSent; // Converted from char[8]

	private int lAmtFinanced;

	private int lInterest;

	private short sTerm;

	private int lPaymentAmt;

	private int lLastFinancedPmtAmt;

	private String cFirstPmtDueDate; // Converted from char[8]

	private int lTotalPaidToDate;

	private String FCscheduleDate; // Converted from char[8]

	private String FClastRunDate; // Converted from char[8]

	private String cApplNo; // Converted from char[7]

	private String cAuthCode; // Converted from char[5]

	private String cLastPmtResp; // Converted from char[30]

	private String cLastPmtMemo; // Converted from char[30]

	private int lLastPmtAmount;

	private String cLastPmtDate; // Converted from char[8]

	private short iARsentBox;

	private int lFinTotalCharges;

	private int lLastFinanCharge;

	private int lLastReceiptNo;

	private short iVersion;

	private String cFinMiscNotes; // Converted from char[180]

	private String cPriceListTable; // Converted from char[15]

	private String cPackageTable; // Converted from char[15]

	private String cFamilyPreviouslyServed;

	private String cPreviousFhUsed; // Converted from char[26]

	private String cAdvertisingSource; // Converted from char[26]
	
	private long salesDescCDID;	// This is the sale description combodata ID
	
	private String finChargePreviousCalc;
	private int previousFinanceCharge;

	// private BTRIEVE_DATE DateModified;

	// private BTRIEVE_TIME TimeModified;

	/**
	 * @return the salesDescCDID
	 */
	public long getSalesDescCDID() {
		return salesDescCDID;
	}

	/**
	 * @param salesDescCDID the salesDescCDID to set
	 */
	public void setSalesDescCDID(long salesDescCDID) {
		this.salesDescCDID = salesDescCDID;
	}

	/**
	 * Calculate the case balance and return floating point number Creation
	 * date: (6/29/00 3:40:34 PM)
	 * 
	 * @return float
	 */
	public float getBalance() {
		long bal = (getLFinTotalCharges() - getLTotalPaidToDate());
		if (bal > 0)
			bal -= (getLAmtFinanced() + getLInterest());
		return ((float) bal / 100);
	}

	public String getCAdvertisingSource() {
		return cAdvertisingSource;
	}

	public String getCApplNo() {
		return cApplNo;
	}

	public String getCAuthCode() {
		return cAuthCode;
	}

	public String getCFamilyPreviouslyServed() {
		return cFamilyPreviouslyServed;
	}

	public String getCFinDateInvoiceSent() {
		return cFinDateInvoiceSent;
	}

	/**
	 * Return the due date from persistent storage. Use
	 * getCFinDueDate(DatabaseTransaction t) to get a default due date in case
	 * the due date is presently empty.
	 */
	public String getCFinDueDate() {
		return cFinDueDate;
	}

	/**
	 * If the due date is present, it is returned. If not present a default due
	 * date is constructed based on the sale date.
	 */
	public String getCFinDueDate(DatabaseTransaction t) {
		try {
			if (cFinDueDate == null || cFinDueDate.compareTo("00000000") <= 0) {
				// Make default due date based on sale date
				SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
				java.util.Date saledate = formatter.parse(getCFinSaleDate(t)); // gets
																				// default
																				// sale
																				// date
																				// if
																				// not
																				// already
																				// present
				// calcualte sale date + 1 month
				Calendar cal = Calendar.getInstance();
				cal.setTime(saledate);
				cal.add(Calendar.MONTH, 1);
				cFinDueDate = formatter.format(cal.getTime());
				// also make default sale date based on deceased.burialDate()
				// or just make additional methods for returning a default sale
				// date.
			}
		} catch (java.text.ParseException e) {
			logger.error("DbFinancialSummary.getCFinDueDate parse exception on sale date:", e);
		}
		return cFinDueDate;
	}

	public String getCFinMiscNotes() {
		return cFinMiscNotes;
	}

	/**
	 * Return the sale date. Use getCFinSaleDate(DatabaseTransaction t) to get a
	 * default sale date in case sale date has not been entered yet.
	 */
	public String getCFinSaleDate() {
		return cFinSaleDate;
	}

	/**
	 * If the sale date from persistence storage is empty, create a default sale
	 * date based on the date-of-service. If not empty, return the sale date
	 * stored.
	 */
	public String getCFinSaleDate(DatabaseTransaction t) {
		DbVitalsDeceased deceased = null;

		if (cFinSaleDate == null || cFinSaleDate.compareTo("00000000") <= 0) {
			// get the deceased object for the date of service
			deceased = FdmsDb.getInstance().getVitalsDeceased(t, getId());
			if (deceased != null) {
				cFinSaleDate = deceased.getDateOfBurial();
				if (cFinSaleDate == null || cFinSaleDate.compareTo("00000000") <= 0) {
					cFinSaleDate = com.aldorsolutions.webfdms.util.FormatDate.getCurrentDateFormatedMMDDYYYY();
				}
			}
		}
		return cFinSaleDate;
	}

	public String getCFinSaleType() {
		return cFinSaleType;
	}

	public String getCFirstPmtDueDate() {
		return cFirstPmtDueDate;
	}

	public String getCLastPmtDate() {
		return cLastPmtDate;
	}

	public String getCLastPmtMemo() {
		return cLastPmtMemo;
	}

	public String getCLastPmtResp() {
		return cLastPmtResp;
	}

	public String getCPackageTable() {
		return cPackageTable;
	}

	public String getCPreviousFhUsed() {
		return cPreviousFhUsed;
	}

	public String getCPriceListTable() {
		return cPriceListTable;
	}

	public String getFClastRunDate() {
		return FClastRunDate;
	}

	public String getFCscheduleDate() {
		return FCscheduleDate;
	}

	public float getFFinServiceChargeRate() {
		return fFinServiceChargeRate;
	}

	public short getIARsentBox() {
		return iARsentBox;
	}

	public short getIFinServiceChargesBox() {
		return iFinServiceChargesBox;
	}

	public short getIVersion() {
		return iVersion;
	}

	public int getLAmtFinanced() {
		return lAmtFinanced;
	}

	public int getLFinTotalCharges() {
		return lFinTotalCharges;
	}

	public int getLInterest() {
		return lInterest;
	}

	public int getLLastFinancedPmtAmt() {
		return lLastFinancedPmtAmt;
	}

	public int getLLastFinanCharge() {
		return lLastFinanCharge;
	}

	public int getLLastPmtAmount() {
		return lLastPmtAmount;
	}

	public int getLLastReceiptNo() {
		return lLastReceiptNo;
	}

	public int getLPaymentAmt() {
		return lPaymentAmt;
	}

	public int getLTotalPaidToDate() {
		return lTotalPaidToDate;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * This method checks the case's price list version for a value. If the case
	 * has a price list established, it is returned. If case's price list is not
	 * specified, a default price list is returned Creation date: (2/7/01
	 * 11:07:22 AM)
	 * 
	 * @return java.lang.String
	 */
	public String getPriceListVersion(DatabaseTransaction t) {

		if (getCPriceListTable().length() > 0)
			return getCPriceListTable();
		
		PriceListManager plm = new PriceListManager();

		return plm.getDefaultPriceList(t, getId());

		// If not price list specified for this case, determine the default
		// version
		/*
		 * DbCase vitals = FdmsDb.getInstance().getCase(t,getId()); // If can't
		 * open vitals then just return the default price list for this
		 * installation if (vitals.getBtrvStatus() != 0) return
		 * getDefaultPriceList();
		 * 
		 * vitals.retrieve( getId() ); if (vitals.getBtrvStatus() != 0) return
		 * getDefaultPriceList(); // Now we have the chapel for this case in
		 * vitals.getCVitLOC() // Search chapel.tab for this case's chapel to
		 * retrieve the price list for this chapel location fdms.util.CsvTable
		 * chapelTable = new fdms.util.CsvTable("chapel.tab"); int line =
		 * chapelTable.search(vitals.getCVitLOC(), 1); if (line <0 ) return
		 * getDefaultPriceList();
		 * 
		 * return chapelTable.getFieldOnLine(line, 4);
		 */
	}

	public short getSTerm() {
		return sTerm;
	}

	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return false;
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		setId(Integer.parseInt(data.get(DbFinancialSummaryPeer.CASE_ID).toString()));
		cAdvertisingSource = data.get(DbFinancialSummaryPeer.ADVSOURCE).toString();
		cApplNo = data.get(DbFinancialSummaryPeer.EAGLEAPPLNO).toString();
		cAuthCode = data.get(DbFinancialSummaryPeer.EAGLEAUTHCODE).toString();
		cFamilyPreviouslyServed = data.get(DbFinancialSummaryPeer.FAMILYUSEDBEFORE).toString();
		cFinDateInvoiceSent = data.get(DbFinancialSummaryPeer.DATEINVOICED).toString();
		cFinDueDate = data.get(DbFinancialSummaryPeer.DUEDATE).toString();
		cFinMiscNotes = data.get(DbFinancialSummaryPeer.NOTES).toString();
		cFinSaleDate = data.get(DbFinancialSummaryPeer.SALEDATE).toString();
		cFinSaleType = data.get(DbFinancialSummaryPeer.SALETYPE).toString();
		cFirstPmtDueDate = data.get(DbFinancialSummaryPeer.DUEDATE).toString();
		cLastPmtDate = data.get(DbFinancialSummaryPeer.LASTPMTDATE).toString();
		cLastPmtMemo = data.get(DbFinancialSummaryPeer.LASTPMTMEMO).toString();
		cLastPmtResp = data.get(DbFinancialSummaryPeer.LASTPMTRESP).toString();
		cPackageTable = data.get(DbFinancialSummaryPeer.PKGLISTTABLE).toString();
		cPreviousFhUsed = data.get(DbFinancialSummaryPeer.FNRLHOMEPREVUSED).toString();
		cPriceListTable = data.get(DbFinancialSummaryPeer.PRICELISTTABLE).toString();
		FClastRunDate = data.get(DbFinancialSummaryPeer.FINCHARGELASTCALC).toString();
		FCscheduleDate = data.get(DbFinancialSummaryPeer.FINCHARGESCHEDULE).toString();
		fFinServiceChargeRate = FormatNumber.parseFloat(data.get(DbFinancialSummaryPeer.FINCHARGERATE).toString());
		iARsentBox = FormatNumber.parseShort(data.get(DbFinancialSummaryPeer.SENTTOACCOUNTING).toString());
		iFinServiceChargesBox = FormatNumber.parseShort(data.get(DbFinancialSummaryPeer.CALCFINCHARGE).toString());
		iVersion = FormatNumber.parseShort(data.get(DbFinancialSummaryPeer.FILEVERSION).toString());
		lAmtFinanced = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.AMOUNTFINANCED).toString());
		lFinTotalCharges = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.TOTALCHARGES).toString());
		lInterest = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.INTERESTTOTAL).toString());
		lLastFinancedPmtAmt = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.LASTFINANCEDPMT).toString());
		lLastFinanCharge = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.LASTFINCHARGE).toString());
		lLastPmtAmount = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.LASTFINANCEDPMT).toString());
		lLastReceiptNo = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.LASTRECEIPTNO).toString());
		lPaymentAmt = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.MONTHLYPMTAMOUNT).toString());
		lTotalPaidToDate = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.TOTALPAID).toString());
		sTerm = FormatNumber.parseShort(data.get(DbFinancialSummaryPeer.TERM).toString());
		salesDescCDID = FormatNumber.parseLong(data.get(DbFinancialSummaryPeer.SALESDESCCDID).toString());
		finChargePreviousCalc = data.get(DbFinancialSummaryPeer.FINCHARGEPREVIOUSCALC).toString();
		previousFinanceCharge = FormatNumber.parseInteger(data.get(DbFinancialSummaryPeer.PREVIOUSFINANCECHARGE).toString());
	}

	public void setCAdvertisingSource(String lcl_arg0) {
		cAdvertisingSource = lcl_arg0;
		modify();
	}

	public void setCApplNo(String lcl_arg0) {
		cApplNo = lcl_arg0;
		modify();
	}

	public void setCAuthCode(String lcl_arg0) {
		cAuthCode = lcl_arg0;
		modify();
	}

	public void setCFamilyPreviouslyServed(String yesno) {
		if (yesno != null && yesno.length() > 0) {
			// use just first character
			modify();
			cFamilyPreviouslyServed = yesno.substring(0, 1);
		}
	}

	public void setCFinDateInvoiceSent(String lcl_arg0) {
		cFinDateInvoiceSent = lcl_arg0;
		modify();
	}

	public void setCFinDueDate(String lcl_arg0) {
		cFinDueDate = lcl_arg0;
		modify();
	}

	public void setCFinMiscNotes(String lcl_arg0) {
		cFinMiscNotes = lcl_arg0;
		modify();
	}

	public void setCFinSaleDate(String lcl_arg0) {
		cFinSaleDate = lcl_arg0;
		modify();
	}

	public void setCFinSaleType(String lcl_arg0) {
		cFinSaleType = lcl_arg0;
		modify();
	}

	public void setCFirstPmtDueDate(String lcl_arg0) {
		cFirstPmtDueDate = lcl_arg0;
		modify();
	}

	public void setCLastPmtDate(String lcl_arg0) {
		cLastPmtDate = lcl_arg0;
		modify();
	}

	public void setCLastPmtMemo(String lcl_arg0) {
		cLastPmtMemo = lcl_arg0;
		modify();
	}

	public void setCLastPmtResp(String lcl_arg0) {
		cLastPmtResp = lcl_arg0;
		modify();
	}

	public void setCPackageTable(String lcl_arg0) {
		cPackageTable = lcl_arg0;
		modify();
	}

	public void setCPreviousFhUsed(String lcl_arg0) {
		cPreviousFhUsed = lcl_arg0;
		modify();
	}

	public void setCPriceListTable(String lcl_arg0) {
		cPriceListTable = lcl_arg0;
		modify();
	}

	public void setFClastRunDate(String lcl_arg0) {
		modify();
		FClastRunDate = lcl_arg0;
	}

	public void setFCscheduleDate(String lcl_arg0) {
		FCscheduleDate = lcl_arg0;
		modify();
	}

	public void setFFinServiceChargeRate(float lcl_arg0) {
		fFinServiceChargeRate = lcl_arg0;
		modify();
	}

	public void setIARsentBox(short lcl_arg0) {
		iARsentBox = lcl_arg0;
		modify();
	}

	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		// if (h.containsKey(peer.NAME)){
		// getPersistentPeer().restore(this, t);
		// }
		setId(((Integer) h.get(DbFinancialSummaryPeer.CASE_ID)).intValue());
	}

	public void setIFinServiceChargesBox(short lcl_arg0) {
		modify();
		iFinServiceChargesBox = lcl_arg0;
	}

	public void setIVersion(short lcl_arg0) {
		modify();
		iVersion = lcl_arg0;
	}

	public void setLAmtFinanced(int lcl_arg0) {
		lAmtFinanced = lcl_arg0;
		modify();
	}

	public void setLFinTotalCharges(int lcl_arg0) {
		lFinTotalCharges = lcl_arg0;
		modify();
	}

	public void setLInterest(int lcl_arg0) {
		lInterest = lcl_arg0;
		modify();
	}

	public void setLLastFinancedPmtAmt(int lcl_arg0) {
		lLastFinancedPmtAmt = lcl_arg0;
		modify();
	}

	public void setLLastFinanCharge(int lcl_arg0) {
		lLastFinanCharge = lcl_arg0;
		modify();
	}

	public void setLLastPmtAmount(int lcl_arg0) {
		lLastPmtAmount = lcl_arg0;
		modify();
	}

	public void setLLastReceiptNo(int lcl_arg0) {
		lLastReceiptNo = lcl_arg0;
		modify();
	}

	public void setLPaymentAmt(int lcl_arg0) {
		lPaymentAmt = lcl_arg0;
		modify();
	}

	public void setLTotalPaidToDate(int lcl_arg0) {
		lTotalPaidToDate = lcl_arg0;
		modify();
	}

	public void setSTerm(short lcl_arg0) {
		sTerm = lcl_arg0;
		modify();
	}

	public String getFinChargePreviousCalc() {
		return finChargePreviousCalc;
	}

	public void setFinChargePreviousCalc(String finChargePreviousCalc) {
		this.finChargePreviousCalc = finChargePreviousCalc;
	}

	public int getPreviousFinanceCharge() {
		return previousFinanceCharge;
	}

	public void setPreviousFinanceCharge(int previousFinanceCharge) {
		this.previousFinanceCharge = previousFinanceCharge;
		modify();
	}
	
}
