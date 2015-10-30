package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
/**
 * DefaultPriceList Class represents one record in the.
 * DefaultPriceList table.
 * Creation date: (8/26/02 11:50:40 AM)
 * @author: C. McDiarmid
 */
public class DbDefaultPriceList extends com.aldorsolutions.webfdms.database.Persistent
implements java.lang.Comparable, java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1879995636452431260L;

	static private final DbDefaultPriceListPeer peer = new DbDefaultPriceListPeer();
	
	private String			Version;
	public static final int VERSION_LENGTH = 12;
	private String			GPLkey;
	public static final int GPLKEY_LENGTH = 8;
	private String			MasterDescr;
	public static final int MASTERDESCR_LENGTH=39;
	private String			ContrDescr;
	public static final int CONTRDESCR_LENGTH=39;
	private int				Price;
	private int				ToPrice;
	private String			Category;
	private String			GPLdescr;
	public static final int GPLDESCR_LENGTH = 150;
	private char			Active;
	private short			GridRow;
	private String			PriceUnit;
	public static final int PRICEUNIT_LENGTH = 15; 
	private short			ContrLine;
	private char			GPLregulated;
	private char			GPLprint;
	private String			TaxCode;
	public static final int TAXCODE_LENGTH = 5;
	private int				TaxExempt;
	private String			GlAcctNo;
	public static final int GLACCTNO_LENGTH = 10;
	private char			Package;
	private String			IncludedDescr;
	public static final int INCLUDEDDESCR_LENGTH=253; 
	private String			NotInclDescr;
	public static final int NOTINCDLDESCR_LENGTH=253;
	private int recID;
	private int invoiceSeqNo;
	private int localeID;
/**
 * Order for charges is based on string comparison using toString()
 * Creation date: (8/26/02 11:50:40 AM)
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
 * Creation date: (8/26/02 11:50:40 AM)
 * @return int
 */
public int getInvoiceSeqNo() {
	return invoiceSeqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/02 11:50:40 AM)
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
 * Creation date: (8/26/02 11:50:40 AM)
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
	setId(FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.IDENTITY).toString()));
	recID			= FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.IDENTITY).toString());
	Version			= data.get(DbDefaultPriceListPeer.VERSION).toString();
	GPLkey			= data.get(DbDefaultPriceListPeer.GPLKEY).toString();
	MasterDescr		= data.get(DbDefaultPriceListPeer.MASTERDESCR).toString();
	ContrDescr		= data.get(DbDefaultPriceListPeer.CONTRACTDESCR).toString();
	Price			= FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.PRICE).toString());
	ToPrice			= FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.TOPRICE).toString());
	Category		= data.get(DbDefaultPriceListPeer.ITEMCATEGORY).toString();
	GPLdescr		= data.get(DbDefaultPriceListPeer.GPLDESCRIPTION).toString();
	Active			= FormatString.getFirstChar(data.get(DbDefaultPriceListPeer.ACTIVE).toString());
	GridRow			= FormatNumber.parseShort(data.get(DbDefaultPriceListPeer.GRIDROWNUMBER).toString());
	PriceUnit		= data.get(DbDefaultPriceListPeer.PRICEUNITS).toString();
	ContrLine		= FormatNumber.parseShort(data.get(DbDefaultPriceListPeer.CONTRACTLINE).toString());
	GPLregulated	= FormatString.getFirstChar(data.get(DbDefaultPriceListPeer.REGULATED).toString());
	GPLprint		= FormatString.getFirstChar(data.get(DbDefaultPriceListPeer.GPLPRINTCODE).toString());
	TaxCode			= data.get(DbDefaultPriceListPeer.TAXCODE).toString();
	TaxExempt		= FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.TAXEXEMPTAMT).toString());
	GlAcctNo		= data.get(DbDefaultPriceListPeer.GLACCTNO).toString();
	Package			= FormatString.getFirstChar(data.get(DbDefaultPriceListPeer.PACKAGE).toString());
	invoiceSeqNo	= FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.SEQUENCENO).toString());
	localeID		= FormatNumber.parseInteger(data.get(DbDefaultPriceListPeer.LOCALEID).toString());
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
	//if (h.containsKey(peer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbDefaultPriceListPeer.IDENTITY)).intValue());
}
	public void setIncludedDescr(String lcl_arg0)
	{
	IncludedDescr = lcl_arg0;
	modify();
	}
/**
 * Insert the method's description here.
 * Creation date: (8/26/02 11:50:40 AM)
 * @param newInvoiceSeqNo int
 */
public void setInvoiceSeqNo(int newInvoiceSeqNo) {
	invoiceSeqNo = newInvoiceSeqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/02 11:50:40 AM)
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
 * Creation date: (8/26/02 11:50:40 AM)
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
}
