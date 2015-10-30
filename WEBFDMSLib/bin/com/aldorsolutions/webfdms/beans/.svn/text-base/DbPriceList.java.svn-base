package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
/**
 * Services Price List Class represents one price list item.
 * Creation date: (4/18/01 1:43:40 PM)
 * @author: R Davidson
 */
public class DbPriceList extends com.aldorsolutions.webfdms.database.Persistent
implements java.lang.Comparable, java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2866410316669593645L;

	static private final DbPriceListPeer peer = new DbPriceListPeer();
	
	private String			Version;	//  Converted from char[13]
	public static final int VERSION_LENGTH = 12;
	private String			GPLkey;	//  Converted from char[9]
	public static final int GPLKEY_LENGTH = 8;
	private String			MasterDescr;	//  Converted from char[40]
	public static final int MASTERDESCR_LENGTH=39;
	private String			ContrDescr;	//  Converted from char[40]
	public static final int CONTRDESCR_LENGTH=39;
	private int				Price;
	private int				ToPrice;
	private String			Category;
	private String			GPLdescr;	//  Converted from char[151]
	public static final int GPLDESCR_LENGTH = 150;
	private char			Active;
	private short			GridRow;
	private String			PriceUnit;	//  Converted from char[16]
	public static final int PRICEUNIT_LENGTH = 15; 
	private short			ContrLine;
	private char			GPLregulated;
	private char			GPLprint;
	private String			TaxCode;	//  Converted from char[6]
	public static final int TAXCODE_LENGTH = 5;
	private int				TaxExempt;
	private String			GlAcctNo;
	public static final int GLACCTNO_LENGTH = 30;
	private char			Package;
	private String			IncludedDescr;	//  Converted from char[5000]
	public static final int INCLUDEDDESCR_LENGTH=253; 
	private String			NotInclDescr;	//  Converted from char[5000]
	public static final int NOTINCDLDESCR_LENGTH=253;
	private int recID;
	private int invoiceSeqNo;
	private int localeID;
	
	private long accountDescCDID;
	private String commissionable;

	
	
	
	
	/**
 * Order for charges is based on string comparison using toString()
 * Creation date: (2/1/01 2:52:15 PM)
 * @return int negative, zero, positive 
 * @param o1 java.lang.Object
 * @param o2 java.lang.Object
 */
public int compareTo(Object o1) {

	return toString().compareTo(o1.toString());
	
}
	public char getActive()
	{
	return Active;
	}
	public String getCategory()
	{
	return Category;
	}
	public String getContrDescr()
	{
	return ContrDescr;
	}
	public short getContrLine()
	{
	return ContrLine;
	}
	public String getGlAcctNo()
	{
	return GlAcctNo;
	}
	public String getGPLdescr()
	{
	return GPLdescr;
	}
	public String getGPLkey()
	{
	return GPLkey;
	}
	public char getGPLprint()
	{
	return GPLprint;
	}
	public char getGPLregulated()
	{
	return GPLregulated;
	}
	public short getGridRow()
	{
	return GridRow;
	}
	public String getIncludedDescr()
	{
	return IncludedDescr;
	}
/**
 * Insert the method's description here.
 * Creation date: (4/8/2002 1:54:19 PM)
 * @return int
 */
public int getInvoiceSeqNo() {
	return invoiceSeqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (8/14/2002 2:59:37 PM)
 * @return int
 */
public int getLocaleID() {
	return localeID;
}
	public String getMasterDescr()
	{
	return MasterDescr;
	}
	public String getNotInclDescr()
	{
	return NotInclDescr;
	}
	public char getPackage()
	{
	return Package;
	}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
	public int getPrice()
	{
	return Price;
	}
	public String getPriceUnit()
	{
	return PriceUnit;
	}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 10:44:25 AM)
 * @return int
 */
public int getRecID() {
	return recID;
}
	public String getTaxCode()
	{
	return TaxCode;
	}
	public int getTaxExempt()
	{
	return TaxExempt;
	}
	public int getToPrice()
	{
	return ToPrice;
	}
	public String getVersion()
	{
	return Version;
	}
/**
 * Generates a hash code for the receiver.
 * This method is supported primarily for
 * hash tables, such as those provided in java.util.
 * @return an integer hash code for the receiver
 * @see java.util.Hashtable
 */
public int hashCode() {
	// Insert code to generate a hash code for the receiver here.
	// This implementation forwards the message to super.  You may replace or supplement this.
	// NOTE: if two objects are equal (equals(Object) returns true) they must have the same hash code
	//return super.hashCode();
	return (getRecID());
}
/**
 * isLocked method comment.
 */
public boolean isLocked() {
	return false;
}
/**
 * Move data from hashtable and copy into class variables
 * Peer object restores from database to hashtable.
 */
public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
	setId(				FormatNumber.parseInteger(data.get(DbPriceListPeer.IDENTITY).toString()));
	recID			= FormatNumber.parseInteger(data.get(DbPriceListPeer.IDENTITY).toString());
	Version			= data.get(DbPriceListPeer.VERSION).toString();
	GPLkey			= data.get(DbPriceListPeer.GPLKEY).toString();
	MasterDescr		= data.get(DbPriceListPeer.MASTERDESCR).toString();
	ContrDescr		= data.get(DbPriceListPeer.CONTRACTDESCR).toString();
	Price			= FormatNumber.parseInteger(data.get(DbPriceListPeer.PRICE).toString());
	ToPrice			= FormatNumber.parseInteger(data.get(DbPriceListPeer.TOPRICE).toString());
	Category		= data.get(DbPriceListPeer.ITEMCATEGORY).toString();
	GPLdescr		= data.get(DbPriceListPeer.GPLDESCRIPTION).toString();
	Active			= FormatString.getFirstChar(data.get(DbPriceListPeer.ACTIVE).toString());
	GridRow			= FormatNumber.parseShort(data.get(DbPriceListPeer.GRIDROWNUMBER).toString());
	PriceUnit		= data.get(DbPriceListPeer.PRICEUNITS).toString();
	ContrLine		= FormatNumber.parseShort(data.get(DbPriceListPeer.CONTRACTLINE).toString());
	GPLregulated	= FormatString.getFirstChar(data.get(DbPriceListPeer.REGULATED).toString());
	GPLprint		= FormatString.getFirstChar(data.get(DbPriceListPeer.GPLPRINTCODE).toString());
	TaxCode			= data.get(DbPriceListPeer.TAXCODE).toString();
	TaxExempt		= FormatNumber.parseInteger(data.get(DbPriceListPeer.TAXEXEMPTAMT).toString());
	GlAcctNo		= data.get(DbPriceListPeer.GLACCTNO).toString();
	Package			= FormatString.getFirstChar(data.get(DbPriceListPeer.PACKAGE).toString());
	invoiceSeqNo	= FormatNumber.parseInteger(data.get(DbPriceListPeer.SEQUENCENO).toString());
	localeID		= FormatNumber.parseInteger(data.get(DbPriceListPeer.LOCALEID).toString());
	accountDescCDID = FormatNumber.parseInteger(data.get(DbInvMasterPeer.ACCOUNTDESCCDID).toString()); 
	commissionable		= data.get(DbPriceListPeer.COMMISSIONABLE).toString();
}
	public void setActive(char lcl_arg0)
	{
	Active = lcl_arg0;
	modify();
	}
	public void setCategory(String lcl_arg0)
	{
	Category = lcl_arg0;
	modify();
	}
	public void setContrDescr(String lcl_arg0)
	{
	ContrDescr = lcl_arg0;
	modify();
	}
	public void setContrLine(short lcl_arg0)
	{
	ContrLine = lcl_arg0;
	modify();
	}
	public void setGlAcctNo(String lcl_arg0)
	{
	GlAcctNo = lcl_arg0;
	modify();
	}
	public void setGPLdescr(String lcl_arg0)
	{
	modify();
	GPLdescr = lcl_arg0;
	}
	public void setGPLkey(String lcl_arg0)
	{
	GPLkey = lcl_arg0;
	modify();
	}
	public void setGPLprint(char lcl_arg0)
	{
	GPLprint = lcl_arg0;
	modify();
	}
	public void setGPLregulated(char lcl_arg0)
	{
	GPLregulated = lcl_arg0;
	modify();
	}
	public void setGridRow(short lcl_arg0)
	{
	GridRow = lcl_arg0;
	modify();
	}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbPriceListPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbPriceListPeer.IDENTITY)).intValue());
}
	public void setIncludedDescr(String lcl_arg0)
	{
	IncludedDescr = lcl_arg0;
	modify();
	}
/**
 * Insert the method's description here.
 * Creation date: (4/8/2002 1:54:19 PM)
 * @param newInvoiceSeqNo int
 */
public void setInvoiceSeqNo(int newInvoiceSeqNo) {
	invoiceSeqNo = newInvoiceSeqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (8/14/2002 2:59:37 PM)
 * @param newLocaleID int
 */
public void setLocaleID(int newLocaleID) {
	localeID = newLocaleID;
	modify();
}
	public void setMasterDescr(String lcl_arg0)
	{
	MasterDescr = lcl_arg0;
		modify();
}
	public void setNotInclDescr(String lcl_arg0)
	{
	NotInclDescr = lcl_arg0;
	modify();
	}
	public void setPackage(char lcl_arg0)
	{
	Package = lcl_arg0;
	modify();
	}
	public void setPrice(int aPrice)
	{
	Price = aPrice;
	modify();
	}
	public void setPriceUnit(String lcl_arg0)
	{
	PriceUnit = lcl_arg0;
	modify();
	}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 10:44:25 AM)
 * @param newRecID int
 */
protected void setRecID(int newRecID) {
	recID = newRecID;
	modify();
}
	public void setTaxCode(String lcl_arg0)
	{
	TaxCode = lcl_arg0;
	modify();
	}
	public void setTaxExempt(int aExempt)
	{
	TaxExempt = aExempt;
	modify();
	}
	public void setToPrice(int aToPrice)
	{
	ToPrice = aToPrice;
	modify();
	}
	public void setVersion(String lcl_arg0)
	{
	Version = lcl_arg0;
	modify();
	}
/**
 * Returns a String that represents the value of this object.
 * @return a string representation of the receiver
 */
public String toString() {
	// Insert code to print the receiver here.
	// This implementation forwards the message to super. You may replace or supplement this.
	//return super.toString();
	return Integer.toString(hashCode());
}
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
public String getCommissionable() {
	return commissionable;
}
public void setCommissionable(String commissionable) {
	this.commissionable = commissionable;
}

}
