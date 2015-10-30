package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;

/**
 * A DbLocale represents one region location. The peer class provides the class
 * name and SQL for restoring Creation date: (11/26/2001 11:14:33 AM)
 * 
 * @author:
 */
public class DbLocale extends com.aldorsolutions.webfdms.database.Persistent {
	
//	private Logger logger = Logger.getLogger(DbLocale.class.getName());

	final static DbLocalePeer peer = new DbLocalePeer();

	private int localeNumber;

	private String name;

	private int nextContractNo;

	private int nextReceiptNo;

	private int nextNonCashNo;

	private java.util.Date lastFinanceChargeDate;

	private java.util.Date expirationDate;

	private int numberOfUsers;

	private String preneedLicense;

	private String atneedLicense;

	private int interfaceType;

	private String managerName;

	private int abbitID;

	private int bankID;

	private double commissionRate;

	private double refundRate;

	private int nextPreNeedNo;

	private double inflationRate;

	private int saleDateCode;

	private int daysBeforeDue;

	public final static int SALE_DATE_DEATHDATE = 0;

	public final static int SALE_DATE_ARRDATE = 1;

	public final static int SALE_DATE_SERVDATE = 2;

	private boolean useLocalizedSpeedData;

	private boolean autoFillDateOfDeath;

	private boolean autoFillArrangeDate;

	private int companyID;

	private String inactiveCode;

	private boolean flagAcctInterfaceShowDates;

	private boolean flagShowFinancing;

	private int localeType;

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:15
	 * PM)
	 * 
	 * @return int
	 */
	public int getAbbitID() {
		return abbitID;
	}

	/**
	 * Insert the method's description here. Creation date: (2/4/2003 7:48:44
	 * PM)
	 * 
	 * @return String
	 */
	public String getAtneedLicense() {
		return atneedLicense;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:32
	 * PM)
	 * 
	 * @return int
	 */
	public int getBankID() {
		return bankID;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:45
	 * PM)
	 * 
	 * @return double
	 */
	public double getCommissionRate() {
		return commissionRate;
	}

	/**
	 * Insert the method's description here. Creation date: (3/7/2003 1:37:06
	 * PM)
	 * 
	 * @return int
	 */
	public int getDaysBeforeDue() {
		return daysBeforeDue;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2002 2:58:50
	 * PM)
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 10:05:18
	 * AM)
	 * 
	 * @return double
	 */
	public double getInflationRate() {
		return inflationRate;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:22:56
	 * PM)
	 * 
	 * @return int
	 */
	public int getInterfaceType() {
		return interfaceType;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:47:26
	 * PM)
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getLastFinanceChargeDate() {
		return lastFinanceChargeDate;
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2002 7:51:20
	 * PM)
	 * 
	 * @return int
	 */
	public int getLocaleNumber() {
		return localeNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:07
	 * PM)
	 * 
	 * @return String
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2002 7:50:35
	 * PM)
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:45:54
	 * PM)
	 * 
	 * @return int
	 */
	public int getNextContractNo() {
		return nextContractNo;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:46:25
	 * PM)
	 * 
	 * @return int
	 */
	public int getNextNonCashNo() {
		return nextNonCashNo;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 5:22:44
	 * PM)
	 * 
	 * @return int
	 */
	public int getNextPreNeedNo() {
		return nextPreNeedNo;
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:46:15
	 * PM)
	 * 
	 * @return int
	 */
	public int getNextReceiptNo() {
		return nextReceiptNo;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2002 2:59:10
	 * PM)
	 * 
	 * @return int
	 */
	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (1/20/2003 3:56:37
	 * PM)
	 * 
	 * @return String
	 */
	public String getPreneedLicense() {
		return preneedLicense;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:24:00
	 * PM)
	 * 
	 * @return double
	 */
	public double getRefundRate() {
		return refundRate;
	}

	/**
	 * Insert the method's description here. Creation date: (3/7/2003 1:36:52
	 * PM)
	 * 
	 * @return int
	 */
	public int getSaleDateCode() {
		return saleDateCode;
	}

	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */

	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbLocalePeer.IDENTITY).toString()));
		localeNumber = FormatNumber.parseInteger(data.get(DbLocalePeer.IDENTITY).toString());
		name = data.get(DbLocalePeer.NAME).toString();
		nextContractNo = FormatNumber.parseInteger(data.get(DbLocalePeer.NEXTCONTRACT).toString());
		nextReceiptNo = FormatNumber.parseInteger(data.get(DbLocalePeer.NEXTRECEIPT).toString());
		nextNonCashNo = FormatNumber.parseInteger(data.get(DbLocalePeer.NEXTNONCASH).toString());
		Object testdate = data.get(DbLocalePeer.LASTFCDATE);
		Class testclass = testdate.getClass();
		java.sql.Date currdate = new java.sql.Date(new java.util.Date().getTime());

		if (testclass.isInstance(currdate)) {
			lastFinanceChargeDate = (java.util.Date) data.get(DbLocalePeer.LASTFCDATE);
		} else {
			lastFinanceChargeDate = currdate;
		}
		testdate = data.get(DbLocalePeer.EXPIREDATE);
		testclass = testdate.getClass();
		if (testclass.isInstance(currdate)) {
			expirationDate = (java.util.Date) data.get(DbLocalePeer.EXPIREDATE);
		} else {
			expirationDate = currdate;
		}
		numberOfUsers = FormatNumber.parseInteger(data.get(DbLocalePeer.NUMBERUSERS).toString());
		preneedLicense = data.get(DbLocalePeer.PRENEEDLIC).toString();
		atneedLicense = data.get(DbLocalePeer.ATNEEDLIC).toString();
		interfaceType = FormatNumber.parseInteger(data.get(DbLocalePeer.INTERFACE).toString());
		managerName = data.get(DbLocalePeer.MANAGER).toString();
		abbitID = FormatNumber.parseInteger(data.get(DbLocalePeer.ABBITID).toString());
		bankID = FormatNumber.parseInteger(data.get(DbLocalePeer.BANKID).toString());
		commissionRate = FormatNumber.parseDouble(data.get(DbLocalePeer.COMMISSION));
		refundRate = FormatNumber.parseDouble(data.get(DbLocalePeer.REFUNDRATE));
		nextPreNeedNo = FormatNumber.parseInteger(data.get(DbLocalePeer.NEXTPNNO).toString());
		inflationRate = FormatNumber.parseDouble(data.get(DbLocalePeer.INFLATION));
		saleDateCode = FormatNumber.parseInteger(data.get(DbLocalePeer.SALEDATECODE).toString());
		daysBeforeDue = FormatNumber.parseInteger(data.get(DbLocalePeer.DUEDATEDAYS).toString());
		useLocalizedSpeedData = FormatString.ynToBoolean(data.get(DbLocalePeer.USELOCALIZEDSPEEDDATA).toString());
		autoFillDateOfDeath = FormatNumber.parseInteger(data.get(DbLocalePeer.AUTO_FILL_DATE_OF_DEATH).toString()) == 1;
		autoFillArrangeDate = FormatNumber.parseInteger(data.get(DbLocalePeer.AUTO_FILL_ARRANGE_DATE).toString()) == 1;
		companyID = FormatNumber.parseInteger(data.get(DbLocalePeer.COMPANYID).toString());
		localeType = FormatNumber.parseInteger(data.get(DbLocalePeer.LOCALETYPE).toString());
		inactiveCode = data.get(DbLocalePeer.INACTIVECODE).toString();
		flagAcctInterfaceShowDates = FormatNumber.parseInteger(data.get(DbLocalePeer.FLAG_ACCT_INT_DATES).toString()) == 1;
		flagShowFinancing = FormatNumber.parseInteger(data.get(DbLocalePeer.FLAG_SHOW_FINANCING).toString()) == 1;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:15
	 * PM)
	 * 
	 * @param newAbbitID
	 *            int
	 */
	public void setAbbitID(int newAbbitID) {
		abbitID = newAbbitID;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (2/4/2003 7:48:44
	 * PM)
	 * 
	 * @param newAtneedLicense
	 *            String
	 */
	public void setAtneedLicense(String newAtneedLicense) {
		atneedLicense = newAtneedLicense;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:32
	 * PM)
	 * 
	 * @param newBankID
	 *            int
	 */
	public void setBankID(int newBankID) {
		bankID = newBankID;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:45
	 * PM)
	 * 
	 * @param newCommissionRate
	 *            double
	 */
	public void setCommissionRate(double newCommissionRate) {
		commissionRate = newCommissionRate;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (3/7/2003 1:37:06
	 * PM)
	 * 
	 * @param newDaysBeforeDue
	 *            int
	 */
	public void setDaysBeforeDue(int newDaysBeforeDue) {
		daysBeforeDue = newDaysBeforeDue;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2002 2:58:50
	 * PM)
	 * 
	 * @param newExpirationDate
	 *            java.util.Date
	 */
	public void setExpirationDate(java.util.Date newExpirationDate) {
		expirationDate = newExpirationDate;
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
		setId(((Integer) h.get(DbLocalePeer.IDENTITY)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (2/17/2003 10:05:18
	 * AM)
	 * 
	 * @param newInflationRate
	 *            double
	 */
	public void setInflationRate(double newInflationRate) {
		inflationRate = newInflationRate;
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:22:56
	 * PM)
	 * 
	 * @param newInterfaceType
	 *            int
	 */
	public void setInterfaceType(int newInterfaceType) {
		interfaceType = newInterfaceType;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:47:26
	 * PM)
	 * 
	 * @param newLastFinanceChargeDate
	 *            java.util.Date
	 */
	public void setLastFinanceChargeDate(java.util.Date newLastFinanceChargeDate) {
		lastFinanceChargeDate = newLastFinanceChargeDate;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2002 7:51:20
	 * PM)
	 * 
	 * @param newLocaleNumber
	 *            int
	 */
	void setLocaleNumber(int newLocaleNumber) {
		localeNumber = newLocaleNumber;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:23:07
	 * PM)
	 * 
	 * @param newManagerName
	 *            String
	 */
	public void setManagerName(String newManagerName) {
		managerName = newManagerName;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2002 7:50:35
	 * PM)
	 * 
	 * @param newName
	 *            String
	 */
	public void setName(String newName) {
		name = newName;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:45:54
	 * PM)
	 * 
	 * @param newNextContractNo
	 *            int
	 */
	public void setNextContractNo(int newNextContractNo) {
		nextContractNo = newNextContractNo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:46:25
	 * PM)
	 * 
	 * @param newNextNonCashNo
	 *            int
	 */
	public void setNextNonCashNo(int newNextNonCashNo) {
		nextNonCashNo = newNextNonCashNo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 5:22:44
	 * PM)
	 * 
	 * @param newNextPreNeedNo
	 *            int
	 */
	public void setNextPreNeedNo(int newNextPreNeedNo) {
		nextPreNeedNo = newNextPreNeedNo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (6/20/2002 4:46:15
	 * PM)
	 * 
	 * @param newNextReceiptNo
	 *            int
	 */
	public void setNextReceiptNo(int newNextReceiptNo) {
		nextReceiptNo = newNextReceiptNo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2002 2:59:10
	 * PM)
	 * 
	 * @param newNumberOfUsers
	 *            int
	 */
	public void setNumberOfUsers(int newNumberOfUsers) {
		numberOfUsers = newNumberOfUsers;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (1/20/2003 3:56:37
	 * PM)
	 * 
	 * @param newPreneedLicense
	 *            String
	 */
	public void setPreneedLicense(String newPreneedLicense) {
		preneedLicense = newPreneedLicense;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/2003 1:24:00
	 * PM)
	 * 
	 * @param newRefundRate
	 *            double
	 */
	public void setRefundRate(double newRefundRate) {
		refundRate = newRefundRate;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (3/7/2003 1:36:52
	 * PM)
	 * 
	 * @param newSaleDateCode
	 *            int
	 */
	public void setSaleDateCode(int newSaleDateCode) {
		saleDateCode = newSaleDateCode;
		modify();
	}

	public boolean getUseLocalizedSpeedData() {
		return useLocalizedSpeedData;
	}

	public void setUseLocalizedSpeedData(boolean useLocalizedSpeedData) {
		this.useLocalizedSpeedData = useLocalizedSpeedData;
	}

	public boolean getAutoFillDateOfDeath() {
		return autoFillDateOfDeath;
	}

	public boolean getAutoFillArrangeDate() {
		return autoFillArrangeDate;
	}

	public void setAutoFillDateOfDeath(boolean newAutoFillDateOfDeath) {
		autoFillDateOfDeath = newAutoFillDateOfDeath;
	}

	public void setAutoFillArrangeDate(boolean newAutoFillArrangeDate) {
		autoFillArrangeDate = newAutoFillArrangeDate;
	}

	/**
	 * @return Returns the comanyID.
	 */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * @param comanyID
	 *            The comanyID to set.
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
		modify();
	}

	/**
	 * @return Returns the inactiveCode.
	 */
	public String getInactiveCode() {
		return inactiveCode;
	}

	/**
	 * @param inactiveCode
	 *            The inactiveCode to set.
	 */
	public void setInactiveCode(String inactiveCode) {
		this.inactiveCode = inactiveCode;
		modify();
	}

	public boolean isFlagAcctInterfaceShowDates() {
		return flagAcctInterfaceShowDates;
	}

	public void setFlagAcctInterfaceShowDates(boolean flagAcctInterfaceShowDates) {
		this.flagAcctInterfaceShowDates = flagAcctInterfaceShowDates;
		modify();
	}

	public boolean isFlagShowFinancing() {
		return flagShowFinancing;
	}

	public void setFlagShowFinancing(boolean flagShowFinancing) {
		this.flagShowFinancing = flagShowFinancing;
		modify();
	}

	/**
	 * @return the localeType
	 */
	public int getLocaleType() {
		return localeType;
	}

	/**
	 * @param localeType the localeType to set
	 */
	public void setLocaleType(int localeType) {
		this.localeType = localeType;
		modify();
	}
	
}
