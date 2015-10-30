package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.sql.Time;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatTime;

/**
 * DbInvMaster - This class represents one merchandise item that a site may have
 * available for sale at some point in time. Creation date: (12/27/2001 2:42:35
 * PM)
 * 
 * @author:
 */
public class DbInvMaster extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbInvMasterPeer peer = new DbInvMasterPeer();

	static public final String STOCK_TYPE_SERIAL = "#";

	static public final String STOCK_TYPE_STOCKED = "S";

	static public final String STOCK_TYPE_NONSTOCKED = "N";

	private String cItemName; // Converted from char[15]

	private String cDescription; // Converted from char[80]

	private String cStockType;

	private String cProductLine; // Converted from char[15]

	private String cShowRoom;

	private String cSupplierCode; // Converted from char[30]

	private int lCost;

	private int lPrice;

	private short iMinimumOnHand;

	private int lReorderQuantity;

	private String cGLsalesAcct; // Converted from char[10]

	private String cGLassetAcct; // Converted from char[10]

	private String cGLcostAcct; // Converted from char[10]

	private float fTaxExempt;

	private String cTaxCode; // Converted from char[5]

	private short iFileVersion;

	private String cDeleteCode;

	private String CasketType;

	private String CasketUse;

	private String Interior; // Converted from char[51]

	private String Exterior; // Converted from char[51]

	private String ExtCode; // Converted from char[3]

	private String Notes; // Converted from char[256]

	private Date DateModified;

	private Time TimeModified;

	private int locale;

	private String glAcctCode;

	private int sequenceNo;

	private short contractLineNo;

	private java.lang.String imageUrl;

	private int invVersionID;
	
	private long accountDescCDID;
	
	private String commissionable;

	/**
	 * @return the accountDescCDID
	 */
	public long getAccountDescCDID() {
		return accountDescCDID;
	}

	/**
	 * @param accountDescCDID the accountDescCDID to set
	 */
	public void setAccountDescCDID(long accountDescCDID) {
		this.accountDescCDID = accountDescCDID;
	}

	public String getCasketType() {
		return CasketType;
	}

	public String getCasketUse() {
		return CasketUse;
	}

	public String getCDeleteCode() {
		return cDeleteCode;
	}

	public String getCDescription() {
		return cDescription;
	}

	public String getCGLassetAcct() {
		return cGLassetAcct;
	}

	public String getCGLcostAcct() {
		return cGLcostAcct;
	}

	public String getCGLsalesAcct() {
		return cGLsalesAcct;
	}

	public String getCItemName() {
		return cItemName;
	}

	/**
	 * Insert the method's description here. Creation date: (4/10/2002 2:33:44
	 * PM)
	 * 
	 * @return short
	 */
	public short getContractLineNo() {
		return contractLineNo;
	}

	public String getCProductLine() {
		return cProductLine;
	}

	public String getCShowRoom() {
		return cShowRoom;
	}

	public String getCStockType() {
		return cStockType;
	}

	public String getCSupplierCode() {
		return cSupplierCode;
	}

	public String getCTaxCode() {
		return cTaxCode;
	}

	public Date getDateModified() {
		return DateModified;
	}

	public String getExtCode() {
		return ExtCode;
	}

	public String getExterior() {
		return Exterior;
	}

	public float getFTaxExempt() {
		return fTaxExempt;
	}

	/**
	 * Insert the method's description here. Creation date: (12/28/2001 7:30:49
	 * AM)
	 * 
	 * @return String
	 */
	public String getGlAcctCode() {
		return glAcctCode;
	}

	public short getIFileVersion() {
		return iFileVersion;
	}

	/**
	 * Location of JPG or GIF picture of this item. Creation date: (7/11/2002
	 * 12:18:24 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getImageUrl() {
		return imageUrl;
	}

	public short getIMinimumOnHand() {
		return iMinimumOnHand;
	}

	public String getInterior() {
		return Interior;
	}

	public int getLCost() {
		return lCost;
	}

	/**
	 * Insert the method's description here. Creation date: (12/28/2001 7:16:17
	 * AM)
	 * 
	 * @return int
	 */
	public int getLocale() {
		return locale;
	}

	public int getLPrice() {
		return lPrice;
	}

	public int getLReorderQuantity() {
		return lReorderQuantity;
	}

	public String getNotes() {
		return Notes;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (4/10/2002 2:33:28
	 * PM)
	 * 
	 * @return int
	 */
	public int getSequenceNo() {
		return sequenceNo;
	}

	public Time getTimeModified() {
		return TimeModified;
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
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbInvMasterPeer.IDENTITY).toString()));
		cItemName = (String) data.get(DbInvMasterPeer.ITEMNAME);
		cDescription = (String) data.get(DbInvMasterPeer.DESCRIPTION);
		cStockType = data.get(DbInvMasterPeer.STOCKTYPE).toString();
		cProductLine = (String) data.get(DbInvMasterPeer.PRODUCTLINE);
		cShowRoom = data.get(DbInvMasterPeer.INSHOWROOM).toString();
		cSupplierCode = (String) data.get(DbInvMasterPeer.SUPPLIERNAME);
		lCost = FormatNumber.parseInteger(data.get(DbInvMasterPeer.COST).toString());
		lPrice = FormatNumber.parseInteger(data.get(DbInvMasterPeer.PRICE).toString());
		iMinimumOnHand = FormatNumber.parseShort(data.get(DbInvMasterPeer.MINIMUMQTY).toString());
		lReorderQuantity = FormatNumber.parseInteger(data.get(DbInvMasterPeer.REORDERQTY).toString());
		fTaxExempt = FormatNumber.parseFloat(data.get(DbInvMasterPeer.TAXEXEMPTAMT).toString());
		cTaxCode = (String) data.get(DbInvMasterPeer.TAXCODE);
		iFileVersion = FormatNumber.parseShort(data.get(DbInvMasterPeer.RECORDVERSION).toString());
		cDeleteCode = data.get(DbInvMasterPeer.DELETECODE).toString();
		CasketType = data.get(DbInvMasterPeer.CASKETTYPE).toString();
		CasketUse = data.get(DbInvMasterPeer.CASKETUSE).toString();
		Interior = (String) data.get(DbInvMasterPeer.INTERIORDESC);
		Exterior = (String) data.get(DbInvMasterPeer.EXTERIORDESC);
		ExtCode = (String) data.get(DbInvMasterPeer.EXTERIORCODE);
		Notes = (String) data.get(DbInvMasterPeer.NOTES);
		glAcctCode = data.get(DbInvMasterPeer.GLCATEGORY).toString();
		locale = FormatNumber.parseInteger(data.get(DbInvMasterPeer.LOCALE).toString());
		sequenceNo = FormatNumber.parseInteger(data.get(DbInvMasterPeer.SEQUENCENO).toString());
		contractLineNo = FormatNumber.parseShort(data.get(DbInvMasterPeer.CONTRACTLINE).toString());
		imageUrl = data.get(DbInvMasterPeer.IMAGEURL).toString();
		cGLsalesAcct = data.get(DbInvMasterPeer.GLSALES).toString();
		cGLcostAcct = data.get(DbInvMasterPeer.GLCOST).toString();
		cGLassetAcct = data.get(DbInvMasterPeer.GLASSET).toString();
		invVersionID = FormatNumber.parseInteger(data.get(DbInvMasterPeer.INVVERSIONID).toString());
		DateModified = FormatDate.parseDate(data.get(DbInvMasterPeer.DATEMODIFIED));
		TimeModified = FormatTime.parseTime(data.get(DbInvMasterPeer.TIMEMODIFIED));
		accountDescCDID = FormatNumber.parseInteger(data.get(DbInvMasterPeer.ACCOUNTDESCCDID).toString()); 
		commissionable = data.get(DbInvMasterPeer.COMMISSIONABLE).toString();
	}

	public void setCasketType(String lcl_arg0) {
		if (lcl_arg0 != null && lcl_arg0.length() > 1) {
			lcl_arg0 = lcl_arg0.substring(0, 1);
		}
		CasketType = lcl_arg0;
		modify();
	}

	public void setCasketUse(String lcl_arg0) {
		if (lcl_arg0 != null && lcl_arg0.length() > 1) {
			lcl_arg0 = lcl_arg0.substring(0, 1);
		}
		CasketUse = lcl_arg0;
		modify();
	}

	public void setCDeleteCode(String lcl_arg0) {
		cDeleteCode = lcl_arg0;
		modify();
	}

	public void setCDescription(String lcl_arg0) {
		cDescription = lcl_arg0;
		modify();
	}

	public void setCGLassetAcct(String lcl_arg0) {
		cGLassetAcct = lcl_arg0;
		modify();
	}

	public void setCGLcostAcct(String lcl_arg0) {
		cGLcostAcct = lcl_arg0;
		modify();
	}

	public void setCGLsalesAcct(String lcl_arg0) {
		cGLsalesAcct = lcl_arg0;
		modify();
	}

	public void setCItemName(String lcl_arg0) {
		cItemName = lcl_arg0;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (4/10/2002 2:33:44
	 * PM)
	 * 
	 * @param newContractLineNo
	 *            short
	 */
	public void setContractLineNo(short newContractLineNo) {
		contractLineNo = newContractLineNo;
	}

	public void setCProductLine(String lcl_arg0) {
		cProductLine = lcl_arg0;
		modify();
	}

	public void setCShowRoom(String lcl_arg0) {
		cShowRoom = lcl_arg0;
		modify();
	}

	public void setCStockType(String lcl_arg0) {
		cStockType = lcl_arg0;
		modify();
	}

	public void setCSupplierCode(String lcl_arg0) {
		cSupplierCode = lcl_arg0;
		modify();
	}

	public void setCTaxCode(String lcl_arg0) {
		cTaxCode = lcl_arg0;
		modify();
	}

	public void setDateModified(Date lcl_arg0) {
		DateModified = lcl_arg0;
		modify();
	}

	public void setExtCode(String lcl_arg0) {
		ExtCode = lcl_arg0;
		modify();
	}

	public void setExterior(String lcl_arg0) {
		Exterior = lcl_arg0;
		modify();
	}

	public void setFTaxExempt(float lcl_arg0) {
		fTaxExempt = lcl_arg0;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (12/28/2001 7:30:49
	 * AM)
	 * 
	 * @param newGlAcctCode
	 *            char
	 */
	public void setGlAcctCode(String newGlAcctCode) {
		glAcctCode = newGlAcctCode;
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
		setId(((Integer) h.get(DbInvMasterPeer.IDENTITY)).intValue());
	}

	public void setIFileVersion(short lcl_arg0) {
		iFileVersion = lcl_arg0;
		modify();
	}

	/**
	 * Set location of JPG or GIF picture of this item. Creation date:
	 * (7/11/2002 12:18:24 PM)
	 * 
	 * @param newImageUrl
	 *            java.lang.String
	 */
	public void setImageUrl(java.lang.String newImageUrl) {
		imageUrl = newImageUrl;
		modify();
	}

	public void setIMinimumOnHand(short lcl_arg0) {
		iMinimumOnHand = lcl_arg0;
		modify();
	}

	public void setInterior(String lcl_arg0) {
		Interior = lcl_arg0;
		modify();
	}

	public void setLCost(int lcl_arg0) {
		lCost = lcl_arg0;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (12/28/2001 7:16:17
	 * AM)
	 * 
	 * @param newLocale
	 *            int
	 */
	public void setLocale(int newLocale) {
		locale = newLocale;
		modify();
	}

	public void setLPrice(int lcl_arg0) {
		lPrice = lcl_arg0;
		modify();
	}

	public void setLReorderQuantity(int lcl_arg0) {
		lReorderQuantity = lcl_arg0;
		modify();
	}

	public void setNotes(String lcl_arg0) {
		Notes = lcl_arg0;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (4/10/2002 2:33:28
	 * PM)
	 * 
	 * @param newSequenceNo
	 *            int
	 */
	public void setSequenceNo(int newSequenceNo) {
		sequenceNo = newSequenceNo;
	}

	public void setTimeModified(Time lcl_arg0) {
		TimeModified = lcl_arg0;
		modify();
	}

	/**
	 * @return the invVersionID
	 */
	public int getInvVersionID() {
		return invVersionID;
	}

	/**
	 * @param invVersionID the invVersionID to set
	 */
	public void setInvVersionID(int invVersionID) {
		this.invVersionID = invVersionID;
		modify();
	}
	
	
	
	public String getCommissionable() {
		return commissionable;
	}

	public void setCommissionable(String commissionable) {
		this.commissionable = commissionable;
		modify();
	}

	public String toOutString () {
		

		return ( "cItemName; " + cItemName + "\n" + 
				"cDescription; " + cDescription + "\n" + 
				"cStockType; " + cStockType + "\n" + 
				"cProductLine; " + cProductLine + "\n" + 
				"cShowRoom; " + cShowRoom + "\n" + 
				"cSupplierCode; " + cSupplierCode + "\n" + 
				"lCost; " + lCost + "\n" + 
				"lPrice; " + lPrice + "\n" + 
				"iMinimumOnHand; " + iMinimumOnHand + "\n" + 
				"lReorderQuantity; " + lReorderQuantity + "\n" + 
				"cGLsalesAcct" + cGLsalesAcct + "\n" + 
				"cGLassetAcct; " + cGLassetAcct + "\n" + 
				"cGLcostAcct; " + cGLcostAcct + "\n" + 
				"fTaxExempt; " + fTaxExempt + "\n" +
				"cTaxCode; " + cTaxCode + "\n" + 
				"iFileVersion; " + iFileVersion + "\n" + 
				"cDeleteCode; " + cDeleteCode + "\n" + 
				"CasketType; " + CasketType + "\n" + 
				"CasketUse; " + CasketUse + "\n" +
				"Interior; " + Interior + "\n" + 
				"Exterior; " + Exterior + "\n" + 
				"ExtCode; " + ExtCode + "\n" + 
				"Notes; " + Notes + "\n" +
				"DateModified; " + DateModified + "\n" + 
				"TimeModified; " + TimeModified + "\n" + 
				"locale; " + locale + "\n" + 
				"glAcctCode; " + glAcctCode + "\n" +
				"sequenceNo; " + sequenceNo + "\n" + 
				"contractLineNo; " + contractLineNo + "\n" +
				"imageUrl; " + imageUrl + "\n" + 
				"invVersionID; " + invVersionID + "\n" +
				"commissionable; " + commissionable + "\n" );
	}
	
}