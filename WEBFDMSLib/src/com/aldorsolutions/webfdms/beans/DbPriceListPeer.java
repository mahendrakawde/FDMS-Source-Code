package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.FormatString;

/**
 * The DbPriceListPeer handles database access for the DbPriceList business
 * object. The DatabasePeer that it extends specifically prescribes for it
 * methods that allow it to create SQL statements.
 */
public class DbPriceListPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	private Logger logger = Logger.getLogger(DbPriceListPeer.class.getName());

	static public final String VERSION = "PriceListVersion";

	static public final String GPLKEY = "GPLKey";

	static public final String MASTERDESCR = "MasterDescription";

	static public final String CONTRACTDESCR = "ContractDescription";

	static public final String PRICE = "Price";

	static public final String TOPRICE = "ToPrice";

	static public final String ITEMCATEGORY = "ItemCategory";

	static public final String GPLDESCRIPTION = "GPLDescription";

	static public final String ACTIVE = "Active";

	static public final String GRIDROWNUMBER = "GridRowNumber";

	static public final String PRICEUNITS = "GPLPriceExtension";

	static public final String CONTRACTLINE = "ContractLineNumber";

	static public final String REGULATED = "GPLRegulated";

	static public final String GPLPRINTCODE = "GPLPrintCode";

	static public final String TAXCODE = "TaxCode";

	static public final String TAXEXEMPTAMT = "TaxExemptAmount";

	static public final String GLACCTNO = "GLAcctNo";

	static public final String PACKAGE = "PackageItem";

	static public final String IDENTITY = "PriceListID";

	static public final String SEQUENCENO = "InvoiceSeqNo";

	static public final String INCLUDEDDESCR = "IncludedDescr";

	static public final String NOTINCLUDEDDESCR = "NotIncludedDescr";

	static public final String LOCALEID = "LocaleID";
	
	static public final String ACCOUNTDESCCDID = "AccountDescCDID";
	static public final String COMMISSIONABLE = "Commissionable";


	/**
	 * Insert the method's description here. Creation date: (10/29/2001 7:17:27
	 * PM)
	 */
	public DbPriceListPeer() {
		super();
	}

	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbPriceList rec = (DbPriceList) p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO pricelist (" + VERSION + ","
							+ GPLKEY + "," + MASTERDESCR + "," + CONTRACTDESCR
							+ "," + PRICE + "," + TOPRICE + "," + ITEMCATEGORY
							+ "," + GPLDESCRIPTION + "," + ACTIVE + ","
							+ GRIDROWNUMBER + "," + PRICEUNITS + ","
							+ CONTRACTLINE + "," + REGULATED + ","
							+ GPLPRINTCODE + "," + TAXCODE + "," + TAXEXEMPTAMT
							+ "," + GLACCTNO + "," + PACKAGE + "," + SEQUENCENO
							+ "," + INCLUDEDDESCR + "," + NOTINCLUDEDDESCR
							+ "," + LOCALEID + "," + ACCOUNTDESCCDID + ", "+ COMMISSIONABLE +" "
							+ ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? )", Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, rec.getVersion());
			pstmt.setString(2, rec.getGPLkey());
			pstmt.setString(3, rec.getMasterDescr());
			pstmt.setString(4, rec.getContrDescr());
			pstmt.setInt(5, rec.getPrice());
			pstmt.setInt(6, rec.getToPrice());
			pstmt.setString(7, rec.getCategory());
			pstmt.setString(8, rec.getGPLdescr());
			pstmt.setString(9, FormatString.charToString(rec.getActive()));
			pstmt.setShort(10, rec.getGridRow());
			pstmt.setString(11, rec.getPriceUnit());
			pstmt.setShort(12, rec.getContrLine());
			pstmt.setString(13, FormatString
					.charToString(rec.getGPLregulated()));
			pstmt.setString(14, FormatString.charToString(rec.getGPLprint()));
			pstmt.setString(15, rec.getTaxCode());
			pstmt.setInt(16, rec.getTaxExempt());
			pstmt.setString(17, rec.getGlAcctNo());
			pstmt.setString(18, FormatString.charToString(rec.getPackage()));
			pstmt.setInt(19, rec.getInvoiceSeqNo());
			pstmt.setString(20, rec.getIncludedDescr());
			pstmt.setString(21, rec.getNotInclDescr());
			pstmt.setInt(22, rec.getLocaleID());
			pstmt.setLong(23, rec.getAccountDescCDID());
			pstmt.setString(24, rec.getCommissionable());
			return pstmt;
		} catch (java.sql.SQLException e) {
			logger.error("DbPriceList Insert SQL Exception:", e);
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPriceListPeer.Insert", e);
		}
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.PreparedStatement pstmt = null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection
					.prepareStatement("DELETE FROM pricelist WHERE PriceListID=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPriceListPeer.Remove", e);
		}
	}

	/**
	 * Provides the SQL used to restore a price list entry from the database
	 * 
	 * @param p
	 *            the Customer being restored (only the ID field is filled in)
	 * @return the SQL used to restore that customer
	 */
	public String getRestoreSql(Persistent p) {
		return "SELECT * from pricelist WHERE PriceListID = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbPriceList rec = (DbPriceList) p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection
					.prepareStatement("UPDATE pricelist  SET " + VERSION
							+ "=?," + GPLKEY + "=?," + MASTERDESCR + "=?,"
							+ CONTRACTDESCR + "=?," + PRICE + "=?," + TOPRICE
							+ "=?," + ITEMCATEGORY + "=?," + GPLDESCRIPTION
							+ "=?," + ACTIVE + "=?," + GRIDROWNUMBER + "=?,"
							+ PRICEUNITS + "=?," + CONTRACTLINE + "=?,"
							+ REGULATED + "=?," + GPLPRINTCODE + "=?,"
							+ TAXCODE + "=?," + TAXEXEMPTAMT + "=?," + GLACCTNO
							+ "=?," + PACKAGE + "=?," + SEQUENCENO + "=?,"
							+ INCLUDEDDESCR + "=?," + NOTINCLUDEDDESCR + "=?,"
							+ LOCALEID + "=?," + ACCOUNTDESCCDID + "=?, " + COMMISSIONABLE + "=? " 
							+ " WHERE " + IDENTITY + " = ?");
			pstmt.setString(1, rec.getVersion());
			pstmt.setString(2, rec.getGPLkey());
			pstmt.setString(3, rec.getMasterDescr());
			pstmt.setString(4, rec.getContrDescr());
			pstmt.setInt(5, rec.getPrice());
			pstmt.setInt(6, rec.getToPrice());
			pstmt.setString(7, rec.getCategory());
			pstmt.setString(8, rec.getGPLdescr());
			pstmt.setString(9, FormatString.charToString(rec.getActive()));
			pstmt.setShort(10, rec.getGridRow());
			pstmt.setString(11, rec.getPriceUnit());
			pstmt.setShort(12, rec.getContrLine());
			pstmt.setString(13, FormatString
					.charToString(rec.getGPLregulated()));
			pstmt.setString(14, FormatString.charToString(rec.getGPLprint()));
			pstmt.setString(15, rec.getTaxCode());
			pstmt.setInt(16, rec.getTaxExempt());
			pstmt.setString(17, rec.getGlAcctNo());
			pstmt.setString(18, FormatString.charToString(rec.getPackage()));
			pstmt.setInt(19, rec.getInvoiceSeqNo());
			pstmt.setString(20, rec.getIncludedDescr());
			pstmt.setString(21, rec.getNotInclDescr());
			pstmt.setInt(22, rec.getLocaleID());
			pstmt.setLong(23, rec.getAccountDescCDID());
			pstmt.setString(24, rec.getCommissionable());
			pstmt.setInt(25, rec.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPriceListPeer.Update", e);
		}
	}
}