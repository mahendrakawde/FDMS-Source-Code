package com.aldorsolutions.webfdms.reporting.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.CreditcardDTO;
import com.aldorassist.webfdms.model.ReportSchedulingDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins Date: Jul 20, 2007 File: FormAvailableFilterDAO.java
 * 
 */
public class CreditcardDAO extends DAO {

	private Logger logger = Logger.getLogger(CreditcardDAO.class
			.getName());

	public CreditcardDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public CreditcardDAO(DbUserSession user) {
		super(user);
	}

	/**
	 * 
	 * @param companyID
	 * @param userId
	 */
	public CreditcardDAO(long companyID, long userId) {
		super(companyID, userId);
	}

	public CreditcardDTO getCreditcard(int Id) {
		CreditcardDTO creditcard = null;

		try {
			String sql = "SELECT Id, Passkey, Fristname, Lastname, CardNumber, ExpirationDate, CVV,	Address, City, State, Zip, ReferanceId, " +
					" Amount, RecNo, TranDate, MerchandiseId, VitalsId ,ResponseCard ,ErrorDetail ,ApprovalCode FROM creditcard  WHERE SchedulingId = ?";

			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setLong(1, Id);

			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				creditcard = new CreditcardDTO();
				creditcard.setId(rs.getInt(++col));
				creditcard.setPassKey(rs.getString(++col));
				creditcard.setFirstname(rs.getString(++col));
				creditcard.setLastname(rs.getString(++col));
				creditcard.setCardNumber(rs.getString(++col));
				creditcard.setExpirationDate(rs.getString(++col));
				creditcard.setCvv(rs.getString(++col));
				creditcard.setAddress(rs.getString(++col));
				creditcard.setCity(rs.getString(++col));
				creditcard.setState(rs.getString(++col));
				creditcard.setZip(rs.getString(++col));
				creditcard.setReferanceId(rs.getString(++col));
				creditcard.setAmount(rs.getString(++col));
				creditcard.setRecNo(rs.getString(++col));
				creditcard.setTranDate(rs.getLong(++col));
				creditcard.setMerchandiseId(rs.getInt(++col));
				creditcard.setVitalsId(rs.getInt(++col));
				creditcard.setResponseCard(rs.getString(++col));
				creditcard.setErrorDetail(rs.getString(++col));
				creditcard.setApprovalCode(rs.getString(++col));
			}

		} catch (SQLException e) {
			logger.error("SQLException in getCreditcard() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCreditcard() : ", e);
		} finally {
			closeConnection();
		}

		return creditcard;
	}

	public boolean addCreditcard(CreditcardDTO creditcard)	throws Exception {
	boolean added = false;
	
	try {
		logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
		String sql = "insert into creditcard ( Passkey, Fristname, Lastname, CardNumber, ExpirationDate, " +
				"CVV,	Address, City, State, Zip, ReferanceId, Amount, RecNo, TranDate, MerchandiseId, VitalsId ,ResponseCard ,ErrorDetail ,ApprovalCode) "
				+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
	
		int col = 0;
		makeConnection(getDbLookup());
		statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		statement.setString(++col, creditcard.getPassKey());
		statement.setString(++col, creditcard.getFirstname());
		statement.setString(++col, creditcard.getLastname());
		statement.setString(++col, creditcard.getCardNumber());
		statement.setString(++col, creditcard.getExpirationDate());
		statement.setString(++col, creditcard.getCvv());
		statement.setString(++col, creditcard.getAddress());
		statement.setString(++col, creditcard.getCity());
		statement.setString(++col, creditcard.getState());
		statement.setString(++col, creditcard.getZip());
		statement.setString(++col, creditcard.getReferanceId());
		statement.setString(++col, creditcard.getAmount());
		statement.setString(++col, creditcard.getRecNo());
		statement.setTimestamp(++col, new java.sql.Timestamp(
				creditcard.getTranDate()));
		statement.setInt(++col,creditcard.getMerchandiseId());
		statement.setInt(++col,creditcard.getVitalsId());
		statement.setString(++col, creditcard.getResponseCard());
		statement.setString(++col, creditcard.getErrorDetail());
		statement.setString(++col, creditcard.getApprovalCode());
		statement.executeUpdate();
		added = true;
	
		if (added) {
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				creditcard.setId(rs.getInt(1));
			}
	
	//		insertAudit(reportScheduling);
		}
	
	} catch (SQLException e) {
		throw e;
	} catch (Exception e) {
		throw e;
	} finally {
		closeConnection();
	}
	
	return added;
	}

	public boolean updateCreditcard(CreditcardDTO creditcard) throws SQLException, Exception {
	boolean updated = false;
	
	try {
	
		//SMSSchedulingDTO oldComp = getSMSScheduling(smsScheduling.getSchedulingID());
	
		String sql = "UPDATE creditcard set Passkey = ?, Fristname = ?, Lastname = ?, CardNumber =?, ExpirationDate =?, CVV =? , " +
				" Address =?, City =?, State =?, Zip =?, ReferanceId =?, Amount =?, RecNo =?, TranDate =?, MerchandiseId =?, VitalsId = ? " +
				" ,ResponseCard = ?, ErrorDetail = ?, ApprovalCode = ? WHERE Id = ?";
	
		int col = 0;
		makeConnection(getDbLookup());
		statement = conn.prepareStatement(sql);

		statement.setString(++col, creditcard.getPassKey());
		statement.setString(++col, creditcard.getFirstname());
		statement.setString(++col, creditcard.getLastname());
		statement.setString(++col, creditcard.getCardNumber());
		statement.setString(++col, creditcard.getExpirationDate());
		statement.setString(++col, creditcard.getCvv());
		statement.setString(++col, creditcard.getAddress());
		statement.setString(++col, creditcard.getCity());
		statement.setString(++col, creditcard.getState());
		statement.setString(++col, creditcard.getZip());
		statement.setString(++col, creditcard.getReferanceId());
		statement.setString(++col, creditcard.getAmount());
		statement.setString(++col, creditcard.getRecNo());
		statement.setTimestamp(++col, new java.sql.Timestamp(
				creditcard.getTranDate()));
		statement.setInt(++col,creditcard.getMerchandiseId());
		statement.setInt(++col,creditcard.getVitalsId());
		statement.setString(++col, creditcard.getResponseCard());
		statement.setString(++col, creditcard.getErrorDetail());
		statement.setString(++col, creditcard.getApprovalCode());
		statement.setInt(++col, creditcard.getId());
		statement.executeUpdate();
	
	//	updateAudit(reportScheduling, oldComp);
	
		updated = true;
	} catch (SQLException e) {
		throw e;
	} catch (Exception e) {
		throw e;
	} finally {
		closeConnection();
	}
	
	return updated;
	}	
	
	public boolean deleteCreditcard(int Id) {
		boolean deleted = false;

		try {
			//SMSSchedulingDTO oldComp = getSMSScheduling(schedulingID);

			String sql = "DELETE FROM creditcard where Id = ?";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++col, Id);
			statement.executeUpdate();

//			deleteAudit(oldComp);

			deleted = true;
		} catch (SQLException e) {
			logger.error("SQLException in deleteCreditcard() : ", e);
		} catch (Exception e) {
			logger.error("Exception in deleteCreditcard() : ", e);
		} finally {
			closeConnection();
		}

		return deleted;
	}
}
