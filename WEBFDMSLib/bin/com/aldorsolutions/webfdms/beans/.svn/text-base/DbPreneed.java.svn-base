package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbPreneed represents the preneed information one case.
 * Preneed Status - 'A'=Active, 'S'=Serviced, 'C'=Cancelled.
 * The peer class provides the class name and SQL for restoring.
 * Creation date: (1/21/2002 11:14:33 AM)
 * @author: 
 */
public class DbPreneed extends com.aldorsolutions.webfdms.database.Persistent {
	final static DbPreneedPeer peer= new DbPreneedPeer();
	static public final String ACTIVE = "A";
	static public final String SERVICED = "S";
	static public final String CANCELLED= "C";
	private java.lang.String buyerFirst;
	private java.lang.String buyerMiddle;
	private java.lang.String buyerLast;
	private java.lang.String buyerStreet;
	private java.lang.String buyerCity;
	private java.lang.String buyerState;
	private java.lang.String buyerZip;
	private java.lang.String cobuyer;
	private java.lang.String buyerSsNo;
	private java.lang.String counselor;
	private java.lang.String status;
	private java.lang.String fundType;
	private java.lang.String depository;
	private java.lang.String contractDate;
	private java.lang.String maturityDate;
	private java.lang.String fundAcctNo;
	private float interestRate;
	private java.lang.String accessable;
	private int contractAmt;
	private int paidYTD;
	private int paidTotal;
	private int interestYtd;
	private int interestTotal;
	private int mgmtFee;
	private int commission;
	private int faceValue;
	private int lastPmtAmt;
	private java.lang.String lastPmtDate;
	private java.lang.String source;
	private java.lang.String casketName;
	private java.lang.String vaultName;
	private java.lang.String urnName;
	private java.lang.String serviceType;
	private int serviceAmt;
	private int casketAmt;
	private int vaultAmt;
	private int urnAmt;
	private int gstAmt;
	private int otherAmt;
	private java.lang.String arrangeDate;
	private java.lang.String beneSameAsBuyer;
	private java.lang.String buyerTitle;
	private java.lang.String buyerAptNo;
	private String buyerPhone;
	private String fundsStreet;
	private String fundsCity;
	private String fundsState;
	private String fundsZip;
	private String fundsDepositedDate;
	public String getFundsStreet() {
		return fundsStreet;
	}
	public void setFundsStreet(String fundsStreet) {
		this.fundsStreet = fundsStreet;
	}
	public String getFundsCity() {
		return fundsCity;
	}
	public void setFundsCity(String fundsCity) {
		this.fundsCity = fundsCity;
	}
	public String getFundsState() {
		return fundsState;
	}
	public void setFundsState(String fundsState) {
		this.fundsState = fundsState;
	}
	public String getFundsZip() {
		return fundsZip;
	}
	public void setFundsZip(String fundsZip) {
		this.fundsZip = fundsZip;
	}
	public String getFundsDepositedDate() {
		return fundsDepositedDate;
	}
	public void setFundsDepositedDate(String fundsDepositedDate) {
		this.fundsDepositedDate = fundsDepositedDate;
	}
	/**
	 * @return Returns the buyerPhone.
	 */
	public String getBuyerPhone() {
		return buyerPhone;
	}
	/**
	 * @param buyerPhone The buyerPhone to set.
	 */
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
        private String comments;
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:01:55 AM)
 * @return char
 */
public String getAccessable() {
	return accessable;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:07:06 AM)
 * @return java.lang.String
 */
public java.lang.String getArrangeDate() {
	return arrangeDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/4/2003 2:21:31 PM)
 * @return java.lang.String
 */
public java.lang.String getBeneSameAsBuyer() {
	return beneSameAsBuyer;
}
/**
 * Insert the method's description here.
 * Creation date: (2/4/2003 2:26:29 PM)
 * @return java.lang.String
 */
public java.lang.String getBuyerAptNo() {
	return buyerAptNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:22 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerCity() {
	return buyerCity;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerFirst() {
	return buyerFirst;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:01 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerLast() {
	return buyerLast;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:48 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerMiddle() {
	return buyerMiddle;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:33 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerSsNo() {
	return buyerSsNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:32 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerState() {
	return buyerState;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:12 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerStreet() {
	return buyerStreet;
}
/**
 * Insert the method's description here.
 * Creation date: (2/4/2003 2:26:15 PM)
 * @return java.lang.String
 */
public java.lang.String getBuyerTitle() {
	return buyerTitle;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:50 AM)
 * @return java.lang.String
 */
public java.lang.String getBuyerZip() {
	return buyerZip;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:22 AM)
 * @return int
 */
public int getCasketAmt() {
	return casketAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:08 AM)
 * @return java.lang.String
 */
public java.lang.String getCasketName() {
	return casketName;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:12 AM)
 * @return java.lang.String
 */
public java.lang.String getCobuyer() {
	return cobuyer;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:35 AM)
 * @return int
 */
public int getCommission() {
	return commission;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:02:10 AM)
 * @return int
 */
public int getContractAmt() {
	return contractAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:59:23 AM)
 * @return java.lang.String
 */
public java.lang.String getContractDate() {
	return contractDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:46 AM)
 * @return java.lang.String
 */
public java.lang.String getCounselor() {
	return counselor;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:58:57 AM)
 * @return java.lang.String
 */
public java.lang.String getDepository() {
	return depository;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:44 AM)
 * @return int
 */
public int getFaceValue() {
	return faceValue;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:00:02 AM)
 * @return java.lang.String
 */
public java.lang.String getFundAcctNo() {
	return fundAcctNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:58:44 AM)
 * @return java.lang.String
 */
public java.lang.String getFundType() {
	return fundType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:01:18 AM)
 * @return float
 */
public float getInterestRate() {
	return interestRate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:09 AM)
 * @return int
 */
public int getInterestTotal() {
	return interestTotal;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:00 AM)
 * @return int
 */
public int getInterestYtd() {
	return interestYtd;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:04:15 AM)
 * @return int
 */
public int getLastPmtAmt() {
	return lastPmtAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:04:42 AM)
 * @return java.lang.String
 */
public java.lang.String getLastPmtDate() {
	return lastPmtDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:59:37 AM)
 * @return java.lang.String
 */
public java.lang.String getMaturityDate() {
	return maturityDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:24 AM)
 * @return int
 */
public int getMgmtFee() {
	return mgmtFee;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:52 AM)
 * @return int
 */
public int getOtherAmt() {
	return otherAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:02:33 AM)
 * @return int
 */
public int getPaidTotal() {
	return paidTotal;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:02:25 AM)
 * @return int
 */
public int getPaidYTD() {
	return paidYTD;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:06 AM)
 * @return int
 */
public int getServiceAmt() {
	return serviceAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:53 AM)
 * @return java.lang.String
 */
public java.lang.String getServiceType() {
	return serviceType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:04:58 AM)
 * @return java.lang.String
 */
public java.lang.String getSource() {
	return source;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:58 AM)
 * @return java.lang.String
 */
public String getStatus() {
	return status;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:44 AM)
 * @return int
 */
public int getUrnAmt() {
	return urnAmt;
}

public int getGSTAmt() {
	return gstAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:36 AM)
 * @return java.lang.String
 */
public java.lang.String getUrnName() {
	return urnName;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:36 AM)
 * @return int
 */
public int getVaultAmt() {
	return vaultAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:19 AM)
 * @return java.lang.String
 */
public java.lang.String getVaultName() {
	return vaultName;
}
/**
 * isLocked method comment.
 */
public boolean isLocked() {
	return false;
}

public String getComments() { return comments; }

/**
 * Move data from hashtable and copy into class variables
 * Peer object restores from database to hashtable.
 */
public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
	setId(				FormatNumber.parseInteger(data.get(DbPreneedPeer.IDENTITY).toString()));
	setBuyerFirst(		(String)data.get(DbPreneedPeer.BUYERFIRST));
	setBuyerMiddle(		(String)data.get(DbPreneedPeer.BUYERMIDDLE));
	setBuyerLast(		(String)data.get(DbPreneedPeer.BUYERLAST));
	setBuyerStreet(		(String)data.get(DbPreneedPeer.BUYERSTREET));
	setBuyerCity(		(String)data.get(DbPreneedPeer.BUYERCITY));
	setBuyerState(		(String)data.get(DbPreneedPeer.BUYERSTATE));
	setBuyerZip(		(String)data.get(DbPreneedPeer.BUYERZIP));
	setCobuyer(			(String)data.get(DbPreneedPeer.COBUYER));
	setBuyerSsNo(		(String)data.get(DbPreneedPeer.BUYERSSNO));
	setCounselor(		(String)data.get(DbPreneedPeer.COUNSELOR));
	setStatus(			(String)data.get(DbPreneedPeer.PNSTATUS));
	setFundType(		(String)data.get(DbPreneedPeer.FUNDTYPE));
	setDepository(		(String)data.get(DbPreneedPeer.DEPOSITORY));
	setFundsStreet(		(String)data.get(DbPreneedPeer.FUNDSSTREET));
	setFundsCity(		(String)data.get(DbPreneedPeer.FUNDSCITY));
	setFundsState(		(String)data.get(DbPreneedPeer.FUNDSSTATE));
	setFundsZip(		(String)data.get(DbPreneedPeer.FUNDSZIP));
	setFundsDepositedDate(		(String)data.get(DbPreneedPeer.FUNDSDEPOSITEDDATE));
	
	setContractDate(	(String)data.get(DbPreneedPeer.CONTRACTDATE));
	setMaturityDate(	(String)data.get(DbPreneedPeer.MATURITYDATE));
	setFundAcctNo(		(String)data.get(DbPreneedPeer.FUNDACCTNO));
	setInterestRate(	FormatNumber.parseFloat(data.get(DbPreneedPeer.INTERESTRATE).toString()));
	setAccessable(		(String)data.get(DbPreneedPeer.FUNDSACCESS));
	setContractAmt(		FormatNumber.parseInteger(data.get(DbPreneedPeer.CONTRACTAMT).toString()));
	setPaidYTD(			FormatNumber.parseInteger(data.get(DbPreneedPeer.PAIDYTD).toString()));
	setPaidTotal(		FormatNumber.parseInteger(data.get(DbPreneedPeer.PAIDTOTAL).toString()));
	setInterestYtd(		FormatNumber.parseInteger(data.get(DbPreneedPeer.YTDINT).toString()));
	setInterestTotal(	FormatNumber.parseInteger(data.get(DbPreneedPeer.TOTALINT).toString()));
	setMgmtFee(			FormatNumber.parseInteger(data.get(DbPreneedPeer.MGMTFEE).toString()));
	setCommission(		FormatNumber.parseInteger(data.get(DbPreneedPeer.COMMISSION).toString()));
	setFaceValue(		FormatNumber.parseInteger(data.get(DbPreneedPeer.FACEVALUE).toString()));
	setLastPmtAmt(		FormatNumber.parseInteger(data.get(DbPreneedPeer.LASTPMTAMT).toString()));
	setLastPmtDate(		(String)data.get(DbPreneedPeer.LASTPMTDATE));
	setSource(			(String)data.get(DbPreneedPeer.SOURCE));
	setCasketName(		(String)data.get(DbPreneedPeer.CASKETNAME));
	setVaultName(		(String)data.get(DbPreneedPeer.VAULTNAME));
	setUrnName(			(String)data.get(DbPreneedPeer.URNNAME));
	setServiceType(		(String)data.get(DbPreneedPeer.SERVICETYPE));
	setServiceAmt(		FormatNumber.parseInteger(data.get(DbPreneedPeer.SERVICEAMT).toString()));
	setCasketAmt(		FormatNumber.parseInteger(data.get(DbPreneedPeer.CASKETAMT).toString()));
	setVaultAmt(		FormatNumber.parseInteger(data.get(DbPreneedPeer.VAULTAMT).toString()));
	setUrnAmt(			FormatNumber.parseInteger(data.get(DbPreneedPeer.URNAMT).toString()));
	setGSTAmt(			FormatNumber.parseInteger(data.get(DbPreneedPeer.GSTAMT).toString()));
	setOtherAmt(		FormatNumber.parseInteger(data.get(DbPreneedPeer.OTHERAMT).toString()));
	arrangeDate			= data.get(DbPreneedPeer.ARRNGDATE).toString();
	beneSameAsBuyer		= data.get(DbPreneedPeer.BENESAME).toString();
	buyerTitle			= data.get(DbPreneedPeer.BUYERTITLE).toString();
	buyerAptNo			= data.get(DbPreneedPeer.BUYERAPT).toString();
    setComments((String) data.get(DbPreneedPeer.COMMENTS));       
    buyerPhone 			= data.get(DbPreneedPeer.BUYERPHONE).toString();
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:01:55 AM)
 * @param newAccessable char
 */
public void setAccessable(String newAccessable) {
	modify();
	accessable = newAccessable;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:07:06 AM)
 * @param newArrangeDate java.lang.String
 */
public void setArrangeDate(java.lang.String newArrangeDate) {
	modify();
	arrangeDate = newArrangeDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/4/2003 2:21:31 PM)
 * @param newBeneSameAsBuyer java.lang.String
 */
public void setBeneSameAsBuyer(java.lang.String newBeneSameAsBuyer) {
	beneSameAsBuyer = newBeneSameAsBuyer;
}
/**
 * Insert the method's description here.
 * Creation date: (2/4/2003 2:26:29 PM)
 * @param newBuyerAptNo java.lang.String
 */
public void setBuyerAptNo(java.lang.String newBuyerAptNo) {
	buyerAptNo = newBuyerAptNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:22 AM)
 * @param newBuyerCity java.lang.String
 */
public void setBuyerCity(java.lang.String newBuyerCity) {
	modify();
	buyerCity = newBuyerCity;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param newBuyerFirst java.lang.String
 */
public void setBuyerFirst(java.lang.String newBuyerFirst) {
	modify();
	buyerFirst = newBuyerFirst;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:01 AM)
 * @param newBuyerLast java.lang.String
 */
public void setBuyerLast(java.lang.String newBuyerLast) {
	modify();
	buyerLast = newBuyerLast;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:48 AM)
 * @param newBuyerMiddle java.lang.String
 */
public void setBuyerMiddle(java.lang.String newBuyerMiddle) {
	modify();
	buyerMiddle = newBuyerMiddle;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:33 AM)
 * @param newBuyerSsNo java.lang.String
 */
public void setBuyerSsNo(java.lang.String newBuyerSsNo) {
	modify();
	buyerSsNo = newBuyerSsNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:32 AM)
 * @param newBuyerState java.lang.String
 */
public void setBuyerState(java.lang.String newBuyerState) {
	modify();
	buyerState = newBuyerState;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:12 AM)
 * @param newBuyerStreet java.lang.String
 */
public void setBuyerStreet(java.lang.String newBuyerStreet) {
	modify();
	buyerStreet = newBuyerStreet;
}
/**
 * Insert the method's description here.
 * Creation date: (2/4/2003 2:26:15 PM)
 * @param newBuyerTitle java.lang.String
 */
public void setBuyerTitle(java.lang.String newBuyerTitle) {
	buyerTitle = newBuyerTitle;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:56:50 AM)
 * @param newBuyerZip java.lang.String
 */
public void setBuyerZip(java.lang.String newBuyerZip) {
	modify();
	buyerZip = newBuyerZip;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:22 AM)
 * @param newCasketAmt int
 */
public void setCasketAmt(int newCasketAmt) {
	modify();
	casketAmt = newCasketAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:08 AM)
 * @param newCasketName java.lang.String
 */
public void setCasketName(java.lang.String newCasketName) {
	modify();
	casketName = newCasketName;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:12 AM)
 * @param newCobuyer java.lang.String
 */
public void setCobuyer(java.lang.String newCobuyer) {
	modify();
	cobuyer = newCobuyer;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:35 AM)
 * @param newCommission int
 */
public void setCommission(int newCommission) {
	modify();
	commission = newCommission;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:02:10 AM)
 * @param newContractAmt int
 */
public void setContractAmt(int newContractAmt) {
	modify();
	contractAmt = newContractAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:59:23 AM)
 * @param newContractDate java.lang.String
 */
public void setContractDate(java.lang.String newContractDate) {
	modify();
	contractDate = newContractDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:46 AM)
 * @param newCounselor java.lang.String
 */
public void setCounselor(java.lang.String newCounselor) {
	modify();
	counselor = newCounselor;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:58:57 AM)
 * @param newDepository java.lang.String
 */
public void setDepository(java.lang.String newDepository) {
	modify();
	depository = newDepository;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:44 AM)
 * @param newFaceValue int
 */
public void setFaceValue(int newFaceValue) {
	modify();
	faceValue = newFaceValue;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:00:02 AM)
 * @param newFundAcctNo java.lang.String
 */
public void setFundAcctNo(java.lang.String newFundAcctNo) {
	modify();
	fundAcctNo = newFundAcctNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:58:44 AM)
 * @param newFundType java.lang.String
 */
public void setFundType(java.lang.String newFundType) {
	modify();
	fundType = newFundType;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbPreneedPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbPreneedPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:01:18 AM)
 * @param newInterestRate float
 */
public void setInterestRate(float newInterestRate) {
	modify();
	interestRate = newInterestRate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:09 AM)
 * @param newInterestTotal int
 */
public void setInterestTotal(int newInterestTotal) {
	modify();
	interestTotal = newInterestTotal;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:00 AM)
 * @param newInterestYtd int
 */
public void setInterestYtd(int newInterestYtd) {
	modify();
	interestYtd = newInterestYtd;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:04:15 AM)
 * @param newLastPmtAmt int
 */
public void setLastPmtAmt(int newLastPmtAmt) {
	modify();
	lastPmtAmt = newLastPmtAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:04:42 AM)
 * @param newLastPmtDate java.lang.String
 */
public void setLastPmtDate(java.lang.String newLastPmtDate) {
	modify();
	lastPmtDate = newLastPmtDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:59:37 AM)
 * @param newMaturityDate java.lang.String
 */
public void setMaturityDate(java.lang.String newMaturityDate) {
	modify();
	maturityDate = newMaturityDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:03:24 AM)
 * @param newMgmtFee int
 */
public void setMgmtFee(int newMgmtFee) {
	modify();
	mgmtFee = newMgmtFee;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:52 AM)
 * @param newOtherAmt int
 */
public void setOtherAmt(int newOtherAmt) {
	modify();
	otherAmt = newOtherAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:02:33 AM)
 * @param newPaidTotal int
 */
public void setPaidTotal(int newPaidTotal) {
	modify();
	paidTotal = newPaidTotal;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:02:25 AM)
 * @param newPaidYTD int
 */
public void setPaidYTD(int newPaidYTD) {
	modify();
	paidYTD = newPaidYTD;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:06 AM)
 * @param newServiceAmt int
 */
public void setServiceAmt(int newServiceAmt) {
	modify();
	serviceAmt = newServiceAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:53 AM)
 * @param newServiceType java.lang.String
 */
public void setServiceType(java.lang.String newServiceType) {
	modify();
	serviceType = newServiceType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:04:58 AM)
 * @param newSource java.lang.String
 */
public void setSource(java.lang.String newSource) {
	modify();
	source = newSource;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:57:58 AM)
 * @param newStatus java.lang.String
 */
public void setStatus(String newStatus) {
	modify();
	status = newStatus;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:44 AM)
 * @param newUrnAmt int
 */
public void setUrnAmt(int newUrnAmt) {
	modify();
	urnAmt = newUrnAmt;
}

public void setGSTAmt(int gstAmt) {
	modify();
	this.gstAmt = gstAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:36 AM)
 * @param newUrnName java.lang.String
 */
public void setUrnName(java.lang.String newUrnName) {
	modify();
	urnName = newUrnName;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:36 AM)
 * @param newVaultAmt int
 */
public void setVaultAmt(int newVaultAmt) {
	modify();
	vaultAmt = newVaultAmt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:05:19 AM)
 * @param newVaultName java.lang.String
 */
public void setVaultName(java.lang.String newVaultName) {
	modify();
	vaultName = newVaultName;
}

public void setComments(String comments) { this.comments = comments; }
}
