package com.aldorsolutions.webfdms.sms.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ReportSchedulingDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.sms.SMSTTVRecipientDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins Date: Jul 20, 2007 File: FormAvailableFilterDAO.java
 * 
 */
public class SMSTTVRecipientDAO extends DAO {

	private Logger logger = Logger.getLogger(SMSTTVRecipientDAO.class
			.getName());

	public SMSTTVRecipientDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public SMSTTVRecipientDAO(DbUserSession user) {
		super(user);
	}

	/**
	 * 
	 * @param companyID
	 * @param userId
	 */
	public SMSTTVRecipientDAO(long companyID, long userId) {
		super(companyID, userId);
	}

	/**
	 * 
	 * @param formFilterID
	 * @return
	 */
	public SMSTTVRecipientDTO getSMSTTVRecipient(int ID) {
		SMSTTVRecipientDTO recipient = null;

		try {
			String sql = "select Id, Firstname, Lastname, AreaCode, Phone, Greeting, Type, ModifyDate FROM smsttvrecipient "
					+ " WHERE Id = ?";

			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setLong(1, ID);

			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				recipient = new SMSTTVRecipientDTO();
				recipient.setId(rs.getInt(++col));
				recipient.setFirstname(rs.getString(++col));
				recipient.setLastname(rs.getString(++col));
				recipient.setAreacode(rs.getString(++col));
				recipient.setPhone(rs.getString(++col));
				recipient.setGreeting(rs.getString(++col));
				recipient.setType(rs.getString(++col));
				recipient.setModifyDate(rs.getLong(++col));
				
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSTTVRecipient() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSTTVRecipient() : ", e);
		} finally {
			closeConnection();
		}

		return recipient;
	}

	/**
	 * 
	 * @param formID
	 * @return
	 */
	public ArrayList<SMSTTVRecipientDTO> getSMSTTYRecipientByType(String type) {
		ArrayList<SMSTTVRecipientDTO> list = new ArrayList<SMSTTVRecipientDTO>();

		try {
			String sql = "select Id, Firstname, Lastname, AreaCode, Phone, Greeting, Type, ModifyDate FROM smsttvrecipient "
				+ " WHERE Type = ? ORDER BY Firstname, Lastname";
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setString(1, type);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				SMSTTVRecipientDTO recipient = new SMSTTVRecipientDTO();
				recipient.setId(rs.getInt(++col));
				recipient.setFirstname(rs.getString(++col));
				recipient.setLastname(rs.getString(++col));
				recipient.setAreacode(rs.getString(++col));
				recipient.setPhone(rs.getString(++col));
				recipient.setGreeting(rs.getString(++col));
				recipient.setType(rs.getString(++col));
				recipient.setModifyDate(rs.getLong(++col));
				list.add(recipient);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSTTYRecipientByType() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSTTYRecipientByType() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
	
	/**
	 * 
	 * @param formAvailableFilter
	 * @return
	 * @throws Exception
	 */
	public boolean addSMSTTVRecipient(SMSTTVRecipientDTO recipient)
			throws Exception {
		boolean added = false;

		try {
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			String sql = "insert into smsttvrecipient (  Firstname, Lastname,AreaCode, Phone, Greeting, Type, ModifyDate) "
					+ "VALUES ( ?,?,?,?,?,?,? )";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(++col, recipient.getFirstname());
			statement.setString(++col, recipient.getLastname());
			statement.setString(++col, recipient.getAreacode());
			statement.setString(++col, recipient.getPhone());
			statement.setString(++col, recipient.getGreeting());
			statement.setString(++col, recipient.getType());
			statement.setTimestamp(++col, new java.sql.Timestamp(recipient.getModifyDate()));
			statement.executeUpdate();
			added = true;

			if (added) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					recipient.setId(rs.getInt(1));
				}

//				insertAudit(reportScheduling);
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
	
	/**
	 * 
	 * @param formAvailableFilter
	 * @return
	 */
	public boolean updateSMSTTVRecipient(SMSTTVRecipientDTO recipient)
			throws SQLException, Exception {
		boolean updated = false;

		try {

			String sql = "UPDATE smsttvrecipient set Firstname = ?, Lastname = ?," +
					" AreaCode = ?, Phone = ?, Greeting = ?, Type = ?, ModifyDate = ? WHERE Id = ?";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setString(++col, recipient.getFirstname());
			statement.setString(++col, recipient.getLastname());
			statement.setString(++col, recipient.getAreacode());
			statement.setString(++col, recipient.getPhone());
			statement.setString(++col, recipient.getGreeting());
			statement.setString(++col, recipient.getType());
			statement.setTimestamp(++col, new java.sql.Timestamp(recipient.getModifyDate()));
			statement.setInt(++col, recipient.getId());
			statement.executeUpdate();

//			updateAudit(reportScheduling, oldComp);

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
	

	/**
	 * 
	 * @param formFilterID
	 * @return
	 */
	public boolean deleteSMSTTVRecipient(int recipientId) {
		boolean deleted = false;

		try {

			String sql = "DELETE FROM smsttvrecipient where Id = ?";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++col, recipientId);
			statement.executeUpdate();

//			deleteAudit(oldComp);

			deleted = true;
		} catch (SQLException e) {
			logger.error("SQLException in deleteSMSTTVRecipient() : ", e);
		} catch (Exception e) {
			logger.error("Exception in deleteSMSTTVRecipient() : ", e);
		} finally {
			closeConnection();
		}

		return deleted;
	}

}
