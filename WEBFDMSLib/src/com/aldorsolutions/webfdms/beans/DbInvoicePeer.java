package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;

/**
 * DbApAccountPeer supplies constants and SQL for Persistent Class.
 * Creation date: (4/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbInvoicePeer extends DatabasePeer {
	static public final String INVOICEID = "invoiceID";
	static public final String VENDORID = "vendorID";
	static public final String LOCATIONID = "locationID";
	static public final String INVOICENUMBER = "invoiceNumber";
	static public final String INVOICEDATE = "invoiceDate";
	static public final String INVOICEDUEDATE = "invoiceDueDate";
	static public final String INVOICEPAID = "invoicePaid";
	static public final String GLCATEGORY = "glcategory";
	static public final String AMOUNTOFINVOICE = "amountOfInvoice";
	static public final String DESCRIPTION = "description";
	static public final String TYPE1099 = "1099Type";
	static public final String AMOUNT1099 = "1099Amount";
	static public final String DISCOUNTFLAG = "discountFlag";
	static public final String DISCOUNTAMOUNT = "discountAmount";
	static public final String DISCOUNTSUBJECTAMOUNT = "discountSubjectAmount";
	static public final String DISCOUNTPERCENT = "discountPercent";
	static public final String DISCOUNTDOLLARS = "discountDollars";
	static public final String DISCOUNTDUE = "discountDue";
	static public final String DISCOUNTDUEFREQCODE = "discountDueFreqCode";
	static public final String DISCOUNTDUEDATE = "discountDueDate";
	static public final String RECURRINGFLAG = "recurringFlag";
	static public final String RECURRINGCOUNT = "recurringCount";
	static public final String RECURRINGFREQ = "recurringFreq";
	static public final String RECURRINGFREQCODE = "recurringFreqCode";
	static public final String CHECKINGACCOUNT = "checkingAccount";
	static public final String CHECKINGSTATUS = "checkingStatus";
	static public final String CHECKCREATED = "checkCreated";
	static public final String INVOICESTATUS = "invoiceStatus";
	static public final String APMASTERID = "apMasterID";

	Logger logger = Logger.getLogger(DbInvoicePeer.class.getName());
	
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbInvoice rec = (DbInvoice) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("INSERT INTO invoice (" + 
					VENDORID + "," + 
					LOCATIONID + "," + 
					INVOICENUMBER + "," + 
					INVOICEDATE + "," + 
					INVOICEDUEDATE + "," + 
					INVOICEPAID + "," + 
					GLCATEGORY + "," + 
					AMOUNTOFINVOICE + "," + 
					DESCRIPTION + "," + 
					TYPE1099 + "," + 
					AMOUNT1099 + "," + 
					DISCOUNTFLAG + "," + 
					DISCOUNTAMOUNT + "," + 
					DISCOUNTSUBJECTAMOUNT + "," + 
					DISCOUNTPERCENT + "," + 
					DISCOUNTDOLLARS + ", " + 
					DISCOUNTDUE + ", " + 
					DISCOUNTDUEFREQCODE + ", " + 
					DISCOUNTDUEDATE + ", " + 
					RECURRINGFLAG + ", " + 
					RECURRINGCOUNT + ", " + 
					RECURRINGFREQ + ", " + 
					RECURRINGFREQCODE + ", " + 
					CHECKINGACCOUNT + ", " + 
					CHECKINGSTATUS + ", " + 
					CHECKCREATED + ", " + 
					INVOICESTATUS + ", " +
					APMASTERID + " "+
					") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, rec.getVendorID());
			pstmt.setInt(2, rec.getLocationID());
			pstmt.setString(3, rec.getInvoiceNumber());
			if (rec.getInvoiceDate() != null) {
				pstmt.setDate(4, new java.sql.Date(rec.getInvoiceDate().getTime()));
			} else {
				pstmt.setDate(4, null);
			}
			if (rec.getInvoiceDueDate() != null) {
				pstmt.setDate(5, new java.sql.Date(rec.getInvoiceDueDate().getTime()));
			} else {
				pstmt.setDate(5, null);
			}
			pstmt.setBoolean(6, rec.getInvoicePaid());
			pstmt.setString(7, rec.getGlcategory());
			pstmt.setDouble(8, rec.getAmountOfInvoice());
			pstmt.setString(9, rec.getDescription());
			pstmt.setInt(10, rec.getInvoice1099Type());
			pstmt.setDouble(11, rec.getInvoice1099Amount());
			pstmt.setBoolean(12, rec.getDiscountFlag());
			pstmt.setDouble(13, rec.getDiscountAmount());
			pstmt.setDouble(14, rec.getDiscountSubjectAmount());
			pstmt.setDouble(15, rec.getDiscountPercent());
			pstmt.setDouble(16, rec.getDiscountDollars());
			pstmt.setInt(17, rec.getDiscountDue());
			pstmt.setInt(18, rec.getDiscountDueFreq());
			if (rec.getDiscountDueDate() != null) {
				pstmt.setDate(19, new java.sql.Date(rec.getDiscountDueDate().getTime()));
			} else {
				pstmt.setDate(19, null);
			}
			pstmt.setBoolean(20, rec.getRecurringFlag());
			pstmt.setInt(21, rec.getRecurringCount());
			pstmt.setInt(22, rec.getRecurringFreq());
			pstmt.setInt(23, rec.getRecurringFreqCode());
			pstmt.setInt(24, rec.getCheckingAccount());
			pstmt.setInt(25, rec.getCheckingStatus());
			pstmt.setBoolean(26, rec.getCheckCreated());
			pstmt.setString(27, rec.getInvoiceStatus());
			pstmt.setInt(28, rec.getApMasterID());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbInvoicePeer.Insert:" + e.getMessage(), e);
		}
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.PreparedStatement pstmt = null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM invoice WHERE invoiceID=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbInvoicePeer.Remove", e);
		}
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(Persistent p) {
		return "SELECT * FROM invoice WHERE invoiceID = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbInvoice rec = (DbInvoice) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("UPDATE invoice SET " +
					VENDORID + "=?," + 
					LOCATIONID + "=?," + 
					INVOICENUMBER + "=?," + 
					INVOICEDATE + "=?," + 
					INVOICEDUEDATE + "=?," + 
					INVOICEPAID + "=?," + 
					GLCATEGORY + "=?," + 
					AMOUNTOFINVOICE + "=?," + 
					DESCRIPTION + "=?," + 
					TYPE1099 + "=?," + 
					AMOUNT1099 + "=?," + 
					DISCOUNTFLAG + "=?," + 
					DISCOUNTAMOUNT + "=?," + 
					DISCOUNTSUBJECTAMOUNT + "=?," + 
					DISCOUNTPERCENT + "=?," + 
					DISCOUNTDOLLARS + "=?," + 
					DISCOUNTDUE + "=?," + 
					DISCOUNTDUEFREQCODE + "=?," + 
					DISCOUNTDUEDATE + "=?," + 
					RECURRINGFLAG + "=?," + 
					RECURRINGCOUNT + "=?," + 
					RECURRINGFREQ + "=?," + 
					RECURRINGFREQCODE + "=?," + 
					CHECKINGACCOUNT + "=?," + 
					CHECKINGSTATUS + "=?," + 
					CHECKCREATED + "=?," + 
					INVOICESTATUS + "=?, " + 
					APMASTERID + "=? " +
					" WHERE " + INVOICEID + " = ?");
			pstmt.setInt(1, rec.getVendorID());
			pstmt.setInt(2, rec.getLocationID());
			pstmt.setString(3, rec.getInvoiceNumber());
			pstmt.setDate(4, rec.getInvoiceDate());
			if (rec.getInvoiceDueDate() != null) {
				pstmt.setDate(5, new java.sql.Date(rec.getInvoiceDueDate().getTime()));
			} else {
				pstmt.setDate(5, null);
			}
			pstmt.setBoolean(6, rec.getInvoicePaid());
			pstmt.setString(7, rec.getGlcategory());
			pstmt.setDouble(8, rec.getAmountOfInvoice());
			pstmt.setString(9, rec.getDescription());
			pstmt.setInt(10, rec.getInvoice1099Type());
			pstmt.setDouble(11, rec.getInvoice1099Amount());
			pstmt.setBoolean(12, rec.getDiscountFlag());
			pstmt.setDouble(13, rec.getDiscountAmount());
			pstmt.setDouble(14, rec.getDiscountSubjectAmount());
			pstmt.setDouble(15, rec.getDiscountPercent());
			pstmt.setDouble(16, rec.getDiscountDollars());
			pstmt.setInt(17, rec.getDiscountDue());
			pstmt.setInt(18, rec.getDiscountDueFreq());
			if (rec.getDiscountDueDate() != null) {
				pstmt.setDate(19, new java.sql.Date(rec.getDiscountDueDate().getTime()));
			} else {
				pstmt.setDate(19, null);
			}
			pstmt.setBoolean(20, rec.getRecurringFlag());
			pstmt.setInt(21, rec.getRecurringCount());
			pstmt.setInt(22, rec.getRecurringFreq());
			pstmt.setInt(23, rec.getRecurringFreqCode());
			pstmt.setInt(24, rec.getCheckingAccount());
			pstmt.setInt(25, rec.getCheckingStatus());
			pstmt.setBoolean(26, rec.getCheckCreated());
			pstmt.setString(27, rec.getInvoiceStatus());
			pstmt.setInt(28, rec.getApMasterID());
			pstmt.setInt(29, rec.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbInvoicePeer.Update:" + e.getMessage(), e);
		}
	}
}
