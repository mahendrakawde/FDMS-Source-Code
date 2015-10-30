package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbFormsAvailable represents the possible forms that a user may print.
 * The forms can be categorized by locale allowing different sets of
 * available forms per region. 
 * A ZERO or null locale means that from is available in all locales.
 * Category can be: 0 = forms for one specific case
 *                  1 = At-Need reports
 *                  2 = Pre-Need reports
 *                  3 = Financial reports
 *                  4 = Donation reports
 *                  5 = Price list reports
 *                  6 = Inventory reports
 *					7 = Obituary reports
 *					8 = AP Demand Check
 *                  9 = Receipt for payment on account
 * 					10= Payment history report
 * The peer class provides the class name and SQL for restoring.
 * Creation date: (12/16/2002 11:14:33 AM)
 * @author: 
 */
public class DbDefaultFormsAvailable extends com.aldorsolutions.webfdms.database.Persistent {
	final static DbDefaultFormsAvailablePeer peer= new DbDefaultFormsAvailablePeer();
	public final static int ATNEED_TYPE 	= 1;
	public final static int PRENEED_TYPE 	= 2;
	public final static int FINANCIAL_TYPE 	= 3;
	public final static int DONATION_TYPE	= 4;
	public final static int PRICELIST_TYPE	= 5;
	public final static int INVENTORY_TYPE	= 6;
	public final static int OBITUARY_TYPE	= 7;
	public final static int CHECK_TYPE		= 8;
	public final static int PMTRECEIPT_TYPE	= 9;
	public final static int PMTHISTORY_TYPE	= 10;
	private int localeNumber;
	private int formId;
	private java.lang.String description;
	private java.lang.String reportName;
	private java.lang.String selectionFormula;
	private int category;
	private java.lang.String exportType;
	private int marginLeft;
	private int marginRight;
	private int marginTop;
	private int marginBottom;
/**
 * Insert the method's description here.
 * Creation date: (2/19/2002 7:28:46 PM)
 * @return int
 */
public int getCategory() {
	return category;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 10:19:27 AM)
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Insert the method's description here.
 * Creation date: (8/12/2002 10:06:22 AM)
 * @return java.lang.String
 */
public java.lang.String getExportType() {
	return exportType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 10:19:16 AM)
 * @return int
 */
public int getFormId() {
	return formId;
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2002 7:51:20 PM)
 * @return int
 */
public int getLocaleNumber() {
	return localeNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 4:00:06 PM)
 * @return int
 */
public int getMarginBottom() {
	return marginBottom;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 3:58:58 PM)
 * @return int
 */
public int getMarginLeft() {
	return marginLeft;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 3:59:49 PM)
 * @return int
 */
public int getMarginRight() {
	return marginRight;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 3:59:58 PM)
 * @return int
 */
public int getMarginTop() {
	return marginTop;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 10:19:40 AM)
 * @return java.lang.String
 */
public java.lang.String getReportName() {
	return reportName;
}
/**
 * Insert the method's description here.
 * Creation date: (2/18/2002 12:26:40 PM)
 * @return java.lang.String
 */
public java.lang.String getSelectionFormula() {
	return selectionFormula;
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
	setId(				FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.IDENTITY).toString()));
	formId 				= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.IDENTITY).toString());
	localeNumber		= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.LOCALE).toString());
	description			= (String)data.get(DbDefaultFormsAvailablePeer.DESCRIPTION);
	reportName			= (String)data.get(DbDefaultFormsAvailablePeer.REPORTNAME);
	selectionFormula	= (String)data.get(DbDefaultFormsAvailablePeer.SELFORMULA);
	category			= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.CATEGORY).toString());
	exportType			= data.get(DbDefaultFormsAvailablePeer.EXPORTTYPE).toString();
	marginLeft			= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.MARGINLEFT).toString());
	marginRight			= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.MARGINRIGHT).toString());
	marginTop			= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.MARGINTOP).toString());
	marginBottom		= FormatNumber.parseInteger(data.get(DbDefaultFormsAvailablePeer.MARGINBOTTOM).toString());
}
/**
 * Insert the method's description here.
 * Creation date: (2/19/2002 7:28:46 PM)
 * @param newCategory int
 */
public void setCategory(int newCategory) {
	category = newCategory;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 10:19:27 AM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	modify();
	description = newDescription;
}
/**
 * Insert the method's description here.
 * Creation date: (8/12/2002 10:06:22 AM)
 * @param newExportType java.lang.String
 */
public void setExportType(java.lang.String newExportType) {
	exportType = newExportType;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 10:19:16 AM)
 * @param newFormId int
 */
public void setFormId(int newFormId) {
	formId = newFormId;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbDefaultFormsAvailablePeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbDefaultFormsAvailablePeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2002 7:51:20 PM)
 * @param newLocaleNumber int
 */
public void setLocaleNumber(int newLocaleNumber) {
	modify();
	localeNumber = newLocaleNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 4:00:06 PM)
 * @param newMarginBottom int
 */
public void setMarginBottom(int newMarginBottom) {
	marginBottom = newMarginBottom;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 3:58:58 PM)
 * @param newMarginLeft int
 */
public void setMarginLeft(int newMarginLeft) {
	marginLeft = newMarginLeft;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 3:59:49 PM)
 * @param newMarginRight int
 */
public void setMarginRight(int newMarginRight) {
	marginRight = newMarginRight;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (8/26/2002 3:59:58 PM)
 * @param newMarginTop int
 */
public void setMarginTop(int newMarginTop) {
	marginTop = newMarginTop;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 10:19:40 AM)
 * @param newReportName java.lang.String
 */
public void setReportName(java.lang.String newReportName) {
	modify();
	reportName = newReportName;
}
/**
 * Insert the method's description here.
 * Creation date: (2/18/2002 12:26:40 PM)
 * @param newSelectionFormula java.lang.String
 */
public void setSelectionFormula(java.lang.String newSelectionFormula) {
	selectionFormula = newSelectionFormula;
}
}
