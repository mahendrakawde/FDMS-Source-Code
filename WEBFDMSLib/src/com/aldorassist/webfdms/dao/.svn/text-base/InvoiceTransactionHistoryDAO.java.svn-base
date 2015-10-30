package com.aldorassist.webfdms.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.InvoiceTransactionHistoryDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatString;

public class InvoiceTransactionHistoryDAO extends DAO {

	private Logger logger = Logger.getLogger(InvoiceTransactionHistoryDAO.class.getName());

	/**
	 * @param user
	 */
	public InvoiceTransactionHistoryDAO(DbUserSession user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param companyID
	 * @param userId
	 */
	public InvoiceTransactionHistoryDAO(long companyID, long userId) {
		super(companyID, userId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dbLookup
	 */
	public InvoiceTransactionHistoryDAO(String dbLookup) {
		super(dbLookup);
		// TODO Auto-generated constructor stub
	}

	
	// CHAD 5/15/08
	// I LEFT THIS IN FOR DOCUMENTATION PURPOSES ONLY.  WE SHOULD NEVER DO A INSERT IN THE DAO'S WITHOUT 
	// A TRANSACTION.  DOING THIS IS A BAD IDEA BECUASE IF MULTIPLE RECORDS NEEDS TO BE INSERTED THEN
	// WE RUN INTO A PROBLEM WHERE SOME OF THE RECORDS MAY OF BEEN INSERTED AND AN EXCEPTION OCCURS AND 
	// OTHER RECORDS ARE LEFT OUT, THERE BY CORRUPTING THE DATABASE.
//	public void addInvoiceHistory(InvoiceTransactionHistoryDTO invoiceTransHistory) throws Exception {
//
//		try {
//			String sql = "insert into invoicetranshist (invoiceID, locationID, type, description, "
//								 + "amount, glAccount, transactionDate, posted, createdDTS) VALUES (?,?,?,?,?,?,?,?, now())";
//
//			int col = 0;
//			makeConnection(getDbLookup());
//			statement = conn.prepareStatement(sql);
//
//			statement.setLong(++col, invoiceTransHistory.getInvoiceID());
//			statement.setLong(++col, invoiceTransHistory.getLocationID());
//			statement.setInt(++col, invoiceTransHistory.getType());
//			statement.setString(++col, invoiceTransHistory.getDescription());
//			statement.setDouble(++col, invoiceTransHistory.getAmount());
//			statement.setString(++col, invoiceTransHistory.getGlAccount());
//			statement.setDate(++col, invoiceTransHistory.getTransactionDate());
//			statement.setString(++col,FormatString.charToString(invoiceTransHistory.getPosted()));
//
//			statement.executeUpdate();
//		} catch (SQLException e) {
//			logger.error("SQLException in addInvoiceHistory() : ", e);
//			throw e;
//		} catch (Exception e) {
//			logger.error("Exception in addInvoiceHistory() : ", e);
//			throw e;
//		} finally {
//			closeConnection();
//		}
//	}
// CHAD 5/15/08
	
}
