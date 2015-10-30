package com.aldorsolutions.webfdms.reporting.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ReportSchedulingDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins Date: Jul 20, 2007 File: FormAvailableFilterDAO.java
 * 
 */
public class SMSSchedulingDAO extends DAO {

	private Logger logger = Logger.getLogger(SMSSchedulingDAO.class
			.getName());

	public SMSSchedulingDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public SMSSchedulingDAO(DbUserSession user) {
		super(user);
	}

	/**
	 * 
	 * @param companyID
	 * @param userId
	 */
	public SMSSchedulingDAO(long companyID, long userId) {
		super(companyID, userId);
	}

	/**
	 * 
	 * @param formFilterID
	 * @return
	 */
	public SMSSchedulingDTO getSMSScheduling(int schedulingID) {
		SMSSchedulingDTO smsScheduling = null;

		try {
			String sql = "select SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
					" RepeatType, RepeatNumber, DATETIME, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
					" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage FROM smsscheduling "
					+ " WHERE SchedulingId = ?";

			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setLong(1, schedulingID);

			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return smsScheduling;
	}

	/**
	 * 
	 * @param formFilterID
	 * @return
	 */
	public SMSSchedulingDTO getSMSCommonScheduling(int schedulingID) {
		SMSSchedulingDTO smsScheduling = null;

		try {
			String sql = "select SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
					" RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
					" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage, CompanyId, " +
					"DataURL, dbLookup, CompanyName, SMSSqlId, SendType FROM smsscheduling "
					+ " WHERE SchedulingId = ?";

			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setLong(1, schedulingID);

			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
				smsScheduling.setCompanyId(rs.getInt(++col));
				smsScheduling.setDataURL(rs.getString(++col));
				smsScheduling.setDbLookup(rs.getString(++col));
				smsScheduling.setCompanyName(rs.getString(++col));
				smsScheduling.setSMSSqlId(rs.getInt(++col));
				smsScheduling.setSendType(rs.getString(++col));
				
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return smsScheduling;
	}	
	
	/**
	 * 
	 * @param formID
	 * @return
	 */
	public ArrayList<ReportSchedulingDTO> getReportScheduling(String reportType) {
		ArrayList<ReportSchedulingDTO> list = new ArrayList<ReportSchedulingDTO>();

		try {
			String sql = "select SchedulingID, FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM,  EmailTo, EmailCC, "
					+ "RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, ReportName, Status, ReportType, UserId from reportscheduling where Status <> 'D' and ReportType = '"+reportType+"'";

			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);

			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				reportScheduling.setSchedulingID(rs.getInt(++col));
				reportScheduling.setFormID(rs.getInt(++col));
				reportScheduling.setLocale(rs.getString(++col));
				reportScheduling.setLocation(rs.getString(++col));
				reportScheduling.setFromDate(rs.getDate(++col));
				reportScheduling.setToDate(rs.getDate(++col));
				reportScheduling.setRunDate(rs.getDate(++col));
				reportScheduling.setRunTimeHH(rs.getInt(++col));
				reportScheduling.setRunTimeMM(rs.getInt(++col));
				reportScheduling.setEmailTo(rs.getString(++col));
				reportScheduling.setEmailCC(rs.getString(++col));
				reportScheduling.setRepeatType(rs.getString(++col));
				reportScheduling.setRepeatNumber(rs.getInt(++col));
				reportScheduling.setDatetime(rs.getLong(++col));
				reportScheduling.setStartDateTime(rs.getLong(++col));
				reportScheduling.setStopDateTime(rs.getLong(++col));
				reportScheduling.setReportName(rs.getString(++col));
				reportScheduling.setStatus(rs.getString(++col));
				reportScheduling.setReportType(rs.getString(++col));
				reportScheduling.setUserId(rs.getInt(++col));
				list.add(reportScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
	
	public ArrayList<ReportSchedulingDTO> getSuccessReportScheduling(int userId) {
		ArrayList<ReportSchedulingDTO> list = new ArrayList<ReportSchedulingDTO>();

		try {
			String sql = "select SchedulingID, FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM,  EmailTo, EmailCC, "
					+ "RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, ReportName, Status, ReportType, UserId from reportscheduling where UserId = ? and Status = 'S' ";

			int i = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++i, userId );
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				reportScheduling.setSchedulingID(rs.getInt(++col));
				reportScheduling.setFormID(rs.getInt(++col));
				reportScheduling.setLocale(rs.getString(++col));
				reportScheduling.setLocation(rs.getString(++col));
				reportScheduling.setFromDate(rs.getDate(++col));
				reportScheduling.setToDate(rs.getDate(++col));
				reportScheduling.setRunDate(rs.getDate(++col));
				reportScheduling.setRunTimeHH(rs.getInt(++col));
				reportScheduling.setRunTimeMM(rs.getInt(++col));
				reportScheduling.setEmailTo(rs.getString(++col));
				reportScheduling.setEmailCC(rs.getString(++col));
				reportScheduling.setRepeatType(rs.getString(++col));
				reportScheduling.setRepeatNumber(rs.getInt(++col));
				reportScheduling.setDatetime(rs.getLong(++col));
				reportScheduling.setStartDateTime(rs.getLong(++col));
				reportScheduling.setStopDateTime(rs.getLong(++col));
				reportScheduling.setReportName(rs.getString(++col));
				reportScheduling.setStatus(rs.getString(++col));
				reportScheduling.setReportType(rs.getString(++col));
				reportScheduling.setUserId(rs.getInt(++col));
				list.add(reportScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
	
	public ArrayList<ReportSchedulingDTO> getReportSchedulingList(int userId) {
		ArrayList<ReportSchedulingDTO> list = new ArrayList<ReportSchedulingDTO>();

		try {
			String sql = "select SchedulingID, FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM,  EmailTo, EmailCC, "
					+ " RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, ReportName, Status, ReportType, UserId from reportscheduling where UserId = ? and Status = 'S' and ReportType = 'S' "
					+ " ORDER BY RunDate DESC";

			int i = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++i, userId );
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				reportScheduling.setSchedulingID(rs.getInt(++col));
				reportScheduling.setFormID(rs.getInt(++col));
				reportScheduling.setLocale(rs.getString(++col));
				reportScheduling.setLocation(rs.getString(++col));
				reportScheduling.setFromDate(rs.getDate(++col));
				reportScheduling.setToDate(rs.getDate(++col));
				reportScheduling.setRunDate(rs.getDate(++col));
				reportScheduling.setRunTimeHH(rs.getInt(++col));
				reportScheduling.setRunTimeMM(rs.getInt(++col));
				reportScheduling.setEmailTo(rs.getString(++col));
				reportScheduling.setEmailCC(rs.getString(++col));
				reportScheduling.setRepeatType(rs.getString(++col));
				reportScheduling.setRepeatNumber(rs.getInt(++col));
				reportScheduling.setDatetime(rs.getLong(++col));
				reportScheduling.setStartDateTime(rs.getLong(++col));
				reportScheduling.setStopDateTime(rs.getLong(++col));
				reportScheduling.setReportName(rs.getString(++col));
				reportScheduling.setStatus(rs.getString(++col));
				reportScheduling.setReportType(rs.getString(++col));
				reportScheduling.setUserId(rs.getInt(++col));
				list.add(reportScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}	
	
	public ArrayList<ReportSchedulingDTO> getReportSchedulingQ() {
		ArrayList<ReportSchedulingDTO> list = new ArrayList<ReportSchedulingDTO>();

		try {
			String sql = "select SchedulingID, FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM, EmailTo, EmailCC, "
					+ "RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, ReportName, Status, ReportType, UserId from reportscheduling where Status = 'Q' and ReportType='S'";

			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);

			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				reportScheduling.setSchedulingID(rs.getInt(++col));
				reportScheduling.setFormID(rs.getInt(++col));
				reportScheduling.setLocale(rs.getString(++col));
				reportScheduling.setLocation(rs.getString(++col));
				reportScheduling.setFromDate(rs.getDate(++col));
				reportScheduling.setToDate(rs.getDate(++col));
				reportScheduling.setRunDate(rs.getDate(++col));
				reportScheduling.setRunTimeHH(rs.getInt(++col));
				reportScheduling.setRunTimeMM(rs.getInt(++col));
				reportScheduling.setEmailTo(rs.getString(++col));
				reportScheduling.setEmailCC(rs.getString(++col));
				reportScheduling.setRepeatType(rs.getString(++col));
				reportScheduling.setRepeatNumber(rs.getInt(++col));
				reportScheduling.setDatetime(rs.getLong(++col));
				reportScheduling.setStartDateTime(rs.getLong(++col));
				reportScheduling.setStopDateTime(rs.getLong(++col));
				reportScheduling.setReportName(rs.getString(++col));
				reportScheduling.setStatus(rs.getString(++col));
				reportScheduling.setReportType(rs.getString(++col));
				reportScheduling.setUserId(rs.getInt(++col));
				list.add(reportScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
	
	public ArrayList<ReportSchedulingDTO> getReportSchedulingQ(int userId) {
		ArrayList<ReportSchedulingDTO> list = new ArrayList<ReportSchedulingDTO>();

		try {
			String sql = "select SchedulingID, FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM, EmailTo, EmailCC, "
					+ " RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, ReportName, Status, ReportType, UserId from reportscheduling where Status in ('Q','R') and ReportType in ('S','R') "
					+ " and UserId = ? ORDER BY RunDate";

			int i = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++i, userId);

			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				reportScheduling.setSchedulingID(rs.getInt(++col));
				reportScheduling.setFormID(rs.getInt(++col));
				reportScheduling.setLocale(rs.getString(++col));
				reportScheduling.setLocation(rs.getString(++col));
				reportScheduling.setFromDate(rs.getDate(++col));
				reportScheduling.setToDate(rs.getDate(++col));
				reportScheduling.setRunDate(rs.getDate(++col));
				reportScheduling.setRunTimeHH(rs.getInt(++col));
				reportScheduling.setRunTimeMM(rs.getInt(++col));
				reportScheduling.setEmailTo(rs.getString(++col));
				reportScheduling.setEmailCC(rs.getString(++col));
				reportScheduling.setRepeatType(rs.getString(++col));
				reportScheduling.setRepeatNumber(rs.getInt(++col));
				reportScheduling.setDatetime(rs.getLong(++col));
				reportScheduling.setStartDateTime(rs.getLong(++col));
				reportScheduling.setStopDateTime(rs.getLong(++col));
				reportScheduling.setReportName(rs.getString(++col));
				reportScheduling.setStatus(rs.getString(++col));
				reportScheduling.setReportType(rs.getString(++col));
				reportScheduling.setUserId(rs.getInt(++col));
				list.add(reportScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
	
	public ArrayList<SMSSchedulingDTO> getReportSchedulingQByCompanyId(int companyId, String sendType) {
		ArrayList<SMSSchedulingDTO> list = new ArrayList<SMSSchedulingDTO>();

		try {
			String sql = "SELECT SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
			" RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
			" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage, " +
			"CompanyId, DataURL,dbLookup, CompanyName, SMSSqlId, SendType " +
			     "	FROM smsscheduling " +
			     "	WHERE CompanyId = ? AND SendType = ? AND Status IN ('Q','R') " + 
			     " AND RunType IN ('S', 'R')";

			int i = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++i, companyId);
			statement.setString(++i, sendType);

			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				SMSSchedulingDTO smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
				smsScheduling.setCompanyId(rs.getInt(++col));
				smsScheduling.setDataURL(rs.getString(++col));
				smsScheduling.setDbLookup(rs.getString(++col));
				smsScheduling.setCompanyName(rs.getString(++col));
				smsScheduling.setSMSSqlId(rs.getInt(++col));
				smsScheduling.setSendType(rs.getString(++col));
				list.add(smsScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportSchedulingQByCompanyId() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportSchedulingQByCompanyId() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}	
	
	
	public ArrayList<SMSSchedulingDTO> getSMSSchedulingCommonQ() {
		ArrayList<SMSSchedulingDTO> list = new ArrayList<SMSSchedulingDTO>();

		logger.info("SMSSchedulingDAO.getSMSSchedulingCommonQ(start, end) scheduleDate:  scheduledHour : ; intial : "  );
		try {
			String sql = "SELECT SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
					" RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
					" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage, " +
					"CompanyId, DataURL,dbLookup, CompanyName, SMSSqlId, SendType " +
					     "	FROM smsscheduling " +
					     "	WHERE Status IN ('Q','R') " + 
					     " AND RunType IN ('S', 'R')";
			makeConnection(DAO.DB_FDMSCOMMON);
			statement = conn.prepareStatement(sql);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				SMSSchedulingDTO smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
				smsScheduling.setCompanyId(rs.getInt(++col));
				smsScheduling.setDataURL(rs.getString(++col));
				smsScheduling.setDbLookup(rs.getString(++col));
				smsScheduling.setCompanyName(rs.getString(++col));
				smsScheduling.setSMSSqlId(rs.getInt(++col));
				smsScheduling.setSendType(rs.getString(++col));
				list.add(smsScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}
		logger.info("SMSSchedulingDAO.getSMSSchedulingCommonQ(start, end) list size : " + list.size());
		return list;
	}
	
	public ArrayList<SMSSchedulingDTO> getSMSSchedulingCommonQ(Date scheduleDate, int scheduledHour, boolean intial, String dbLookup, String sendType) {
		ArrayList<SMSSchedulingDTO> list = new ArrayList<SMSSchedulingDTO>();

		logger.info("SMSSchedulingDAO.getSMSSchedulingQ(start, end) scheduleDate: "+ scheduleDate + "; scheduledHour : " + scheduledHour + " ; intial : " + intial  );
		try {
			String sql = "SELECT SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
					" RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
					" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage, " +
					"CompanyId, DataURL,dbLookup, CompanyName, SMSSqlId, SendType " +
					"	FROM smsscheduling " +
				     "	WHERE Status IN ('Q'"  + (intial? ",'R') " : ") ") +
				     " AND RunType IN ('S'" + (intial? ", 'R') " : ") " )+
				     " AND SendType = ? " +
				     "	AND ((Rundate < ?) " +
				     	"	OR " +
				     	"	(RunTimeHH <  ? AND RunDate = ?)) " +
				     "	ORDER BY RunDate,RunTimeHH";
		int i = 0;
			
		makeConnection(DAO.DB_FDMSCOMMON);
		statement = conn.prepareStatement(sql);
		statement.setString(++i, sendType);
		statement.setDate(++i, scheduleDate );
		statement.setInt(++i, scheduledHour);
		statement.setDate(++i, scheduleDate);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				SMSSchedulingDTO smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
				smsScheduling.setCompanyId(rs.getInt(++col));
				smsScheduling.setDataURL(rs.getString(++col));
				smsScheduling.setDbLookup(rs.getString(++col));
				smsScheduling.setCompanyName(rs.getString(++col));
				smsScheduling.setSMSSqlId(rs.getInt(++col));
				smsScheduling.setSendType(rs.getString(++col));
				list.add(smsScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}
		logger.info("SMSSchedulingDAO.getSMSSchedulingCommonQ(start, end) list size : " + list.size());
		return list;
	}
	
	
	public ArrayList<SMSSchedulingDTO> getSMSSchedulingQ() {
		ArrayList<SMSSchedulingDTO> list = new ArrayList<SMSSchedulingDTO>();

		logger.info("SMSSchedulingDAO.getSMSSchedulingQ(start, end) scheduleDate:  scheduledHour : ; intial : "  );
		try {
			String sql = "SELECT SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
					" RepeatType, RepeatNumber, DATETIME, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
					" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage " +
					     "	FROM smsscheduling " +
					     "	WHERE Status IN ('Q','R') " + 
					     " AND RunType IN ('S', 'R')";
			int i = 0;
			makeConnection(getDbLookup());
			if (getDbLookup().contains(("jdbc/FDMS_Key2db"))) {
				int a = 1;
			}
			statement = conn.prepareStatement(sql);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				SMSSchedulingDTO smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
				list.add(smsScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}
		logger.info("SMSSchedulingDAO.getSMSSchedulingQ(start, end) list size : " + list.size());
		return list;
	}

	public ArrayList<SMSSchedulingDTO> getSMSSchedulingQ(Date scheduleDate, int scheduledHour, boolean intial) {
		ArrayList<SMSSchedulingDTO> list = new ArrayList<SMSSchedulingDTO>();

		logger.info("SMSSchedulingDAO.getSMSSchedulingQ(start, end) scheduleDate: "+ scheduleDate + "; scheduledHour : " + scheduledHour + " ; intial : " + intial  );
		try {
			String sql = "SELECT SchedulingID, LocaleId, LocationId, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM," +
					" RepeatType, RepeatNumber, DATETIME, StartDateTime, StopDateTime, CountryCode, AreaCode, Phone," +
					" SQLScript, StartMessage, EndMessage, Status, RunType,	UserId, Firstname, Lastname, SentMessage " +
					     "	FROM smsscheduling " +
					     "	WHERE Status IN ('Q'"  + (intial? ",'R') " : ") ") 
					     + " AND RunType IN ('S'" + (intial? ", 'R') " : ") " )+
					     "	AND ((Rundate < ?) " +
					     	"	OR " +
					     	"	(RunTimeHH <  ? AND RunDate = ?)) " +
					     "	ORDER BY RunDate,RunTimeHH";
			int i = 0;
			makeConnection(getDbLookup());
			if (getDbLookup().contains(("jdbc/FDMS_Key2db"))) {
				int a = 1;
			}
			statement = conn.prepareStatement(sql);
			statement.setDate(++i, scheduleDate );
			statement.setInt(++i, scheduledHour);
			statement.setDate(++i, scheduleDate);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				SMSSchedulingDTO smsScheduling = new SMSSchedulingDTO();
				smsScheduling.setSchedulingID(rs.getInt(++col));
				smsScheduling.setLocaleId(rs.getInt(++col));
				smsScheduling.setLocationId(rs.getInt(++col));
				smsScheduling.setFromDate(rs.getDate(++col));
				smsScheduling.setToDate(rs.getDate(++col));
				smsScheduling.setRunDate(rs.getDate(++col));
				smsScheduling.setRunTimeHH(rs.getInt(++col));
				smsScheduling.setRunTimeMM(rs.getInt(++col));
				smsScheduling.setRepeatType(rs.getString(++col));
				smsScheduling.setRepeatNumber(rs.getInt(++col));
				smsScheduling.setDatetime(rs.getLong(++col));
				smsScheduling.setStartDateTime(rs.getLong(++col));
				smsScheduling.setStopDateTime(rs.getLong(++col));
				smsScheduling.setCountryCode(rs.getString(++col));
				smsScheduling.setAreaCode(rs.getString(++col));
				smsScheduling.setPhone(rs.getString(++col));
				smsScheduling.setSqlScript(rs.getString(++col));
				smsScheduling.setStartMessage(rs.getString(++col));
				smsScheduling.setEndMessage(rs.getString(++col));
				smsScheduling.setStatus(rs.getString(++col));
				smsScheduling.setRunType(rs.getString(++col));
				smsScheduling.setUserId(rs.getInt(++col));
				smsScheduling.setFirstname(rs.getString(++col));
				smsScheduling.setLastname(rs.getString(++col));
				smsScheduling.setSentMessage(rs.getString(++col));
				list.add(smsScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}
		logger.info("SMSSchedulingDAO.getSMSSchedulingQ(start, end) list size : " + list.size());
		return list;
	}

	public ArrayList<ReportSchedulingDTO> getReportSchedulingQ(Date startDate, int startHour, Date endDate, int endHour) {
		logger.info("ReportSchedulingDAO.getReportSchedulingQ(start, end) startDate: "+ startDate + "; startHour : " + startHour + " ; endDate : " + endDate + " ; endHour : " + endHour);
		ArrayList<ReportSchedulingDTO> list = new ArrayList<ReportSchedulingDTO>();
		try {
			
			StringBuffer sql = new StringBuffer(512);
			sql.append("SELECT SchedulingID, FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM, EmailTo, EmailCC," )
			    .append(" RepeatType, RepeatNumber, DATETIME, StartDateTime, StopDateTime, ReportName, STATUS, ReportType, UserId ") 
				.append(" FROM reportscheduling ") 
				.append(" WHERE STATUS IN ('Q') AND ReportType='S'  ");
			if(endDate.after(startDate)){
				sql.append(" AND (( RunTimeHH >= ? AND RunDate = ?)" ) 
				   .append(" OR ") 
				   .append("(RunTimeHH < ? AND RunDate = ? ))");
			}else {
				sql.append(" AND (RuntimeHH >= ? AND RuntimeHH < ? AND RunDate = ? )");
			}				
			logger.info("ReportSchedulingDAO.getReportSchedulingQ(start, end) SQL:  " +sql.toString());
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			int index =1;
			if(endDate.after(startDate)){
				statement.setInt(index++, startHour);
				statement.setDate(index++, startDate);
				statement.setInt(index++, endHour);
				statement.setDate(index++, endDate);
			}else {
				statement.setInt(index++, startHour);
				statement.setInt(index++, endHour);
				statement.setDate(index++, startDate);
			}

			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				reportScheduling.setSchedulingID(rs.getInt(++col));
				reportScheduling.setFormID(rs.getInt(++col));
				reportScheduling.setLocale(rs.getString(++col));
				reportScheduling.setLocation(rs.getString(++col));
				reportScheduling.setFromDate(rs.getDate(++col));
				reportScheduling.setToDate(rs.getDate(++col));
				reportScheduling.setRunDate(rs.getDate(++col));
				reportScheduling.setRunTimeHH(rs.getInt(++col));
				reportScheduling.setRunTimeMM(rs.getInt(++col));
				reportScheduling.setEmailTo(rs.getString(++col));
				reportScheduling.setEmailCC(rs.getString(++col));
				reportScheduling.setRepeatType(rs.getString(++col));
				reportScheduling.setRepeatNumber(rs.getInt(++col));
				reportScheduling.setDatetime(rs.getLong(++col));
				reportScheduling.setStartDateTime(rs.getLong(++col));
				reportScheduling.setStopDateTime(rs.getLong(++col));
				reportScheduling.setReportName(rs.getString(++col));
				reportScheduling.setStatus(rs.getString(++col));
				reportScheduling.setReportType(rs.getString(++col));
				reportScheduling.setUserId(rs.getInt(++col));
				list.add(reportScheduling);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getReportScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getReportScheduling() : ", e);
		} finally {
			closeConnection();
		}
		logger.info("ReportSchedulingDAO.getReportSchedulingQ(start, end) list size : " + list.size());
		return list;
	}
	
	
	/**
	 * 
	 * @param formAvailableFilter
	 * @return
	 * @throws Exception
	 */
	public boolean addReportScheduling(ReportSchedulingDTO reportScheduling)
			throws Exception {
		boolean added = false;

		try {
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			String sql = "insert into reportscheduling ( "
					+ "FormID, Locale, Location, FromDate, ToDate, RunDate, RunTimeHH, RunTimeMM, EmailTo, EmailCC, "
					+ "RepeatType, RepeatNumber, Datetime, StartDateTime, StopDateTime, ReportName, Status, ReportType, UserId) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// statement.setInt(++col, reportScheduling.getSchedulingID());
			statement.setInt(++col, reportScheduling.getFormID());
			statement.setString(++col, reportScheduling.getLocale());
			statement.setString(++col, reportScheduling.getLocation());
			statement.setDate(++col, reportScheduling.getFromDate());
			statement.setDate(++col, reportScheduling.getToDate());
			statement.setDate(++col, reportScheduling.getRunDate());
			statement.setInt(++col,reportScheduling.getRunTimeHH());
			statement.setInt(++col, reportScheduling.getRunTimeMM());
			statement.setString(++col, reportScheduling.getEmailTo());
			statement.setString(++col, reportScheduling.getEmailCC());
			statement.setString(++col, reportScheduling.getRepeatType());
			statement.setInt(++col, reportScheduling.getRepeatNumber());
			// statement.setLong(++col, reportScheduling.getDateTime());
			statement.setTimestamp(++col, new java.sql.Timestamp(
					reportScheduling.getDatetime()));
			statement.setTimestamp(++col, new java.sql.Timestamp(
					reportScheduling.getStartDateTime()));
			statement.setTimestamp(++col, new java.sql.Timestamp(
					reportScheduling.getStopDateTime()));
			statement.setString(++col, reportScheduling.getReportName());
			statement.setString(++col, reportScheduling.getStatus());
			statement.setString(++col, reportScheduling.getReportType());
			statement.setInt(++col,reportScheduling.getUserId());
			statement.executeUpdate();
			added = true;

			if (added) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					reportScheduling.setSchedulingID(rs.getInt(1));
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
	
	public boolean addSMSCommonScheduling(SMSSchedulingDTO sms)
	throws Exception {
	boolean added = false;
	
	try {
		logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
		String sql = "INSERT INTO smsscheduling (LocaleId, LocationId, FromDate, ToDate, RunDate, " +
				"RunTimeHH,	RunTimeMM, RepeatType, RepeatNumber, Datetime, StartDateTime,StopDateTime, CountryCode, " +
				"AreaCode, Phone, SQLScript, StartMessage, EndMessage, Status, RunType, UserId, Firstname, Lastname, " +
				"SentMessage, CompanyId, DataURL, dbLookup, CompanyName, SMSSqlId, SendType)" +
				"VALUES	(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	 
		int col = 0;
		makeConnection(getDbLookup());
		statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		// statement.setInt(++col, reportScheduling.getSchedulingID());
		statement.setInt(++col, sms.getLocaleId());
		statement.setInt(++col, sms.getLocationId());
		statement.setDate(++col, sms.getFromDate());
		statement.setDate(++col, sms.getToDate());
		statement.setDate(++col, sms.getRunDate());
		statement.setInt(++col,sms.getRunTimeHH());
		statement.setInt(++col, sms.getRunTimeMM());
		statement.setString(++col, sms.getRepeatType());
		statement.setInt(++col, sms.getRepeatNumber());
		statement.setTimestamp(++col, new java.sql.Timestamp(sms.getDatetime()));
		statement.setTimestamp(++col, new java.sql.Timestamp(sms.getStartDateTime()));
		statement.setTimestamp(++col, new java.sql.Timestamp(sms.getStopDateTime()));
		statement.setString(++col, sms.getCountryCode());
		statement.setString(++col, sms.getAreaCode());
		statement.setString(++col, sms.getPhone());
		statement.setString(++col,sms.getSqlScript());
		statement.setString(++col, sms.getStartMessage());
		statement.setString(++col, sms.getEndMessage());
		statement.setString(++col, sms.getStatus());
		statement.setString(++col,sms.getRunType());
		statement.setInt(++col, sms.getUserId());
		statement.setString(++col, sms.getFirstname());
		statement.setString(++col, sms.getLastname());
		statement.setString(++col,sms.getSentMessage());
		statement.setInt(++col, sms.getCompanyId());
		statement.setString(++col, sms.getDataURL());
		statement.setString(++col, sms.getDbLookup());
		statement.setString(++col,sms.getCompanyName());
		statement.setInt(++col,sms.getSMSSqlId());
		statement.setString(++col,sms.getSendType());
		statement.executeUpdate();
		added = true;
	
		if (added) {
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				sms.setSchedulingID(rs.getInt(1));
			}
	
	//		insertAudit(sms);
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
	public boolean updateSMSScheduling(SMSSchedulingDTO smsScheduling)
			throws SQLException, Exception {
		boolean updated = false;

		try {

			SMSSchedulingDTO oldComp = getSMSScheduling(smsScheduling
					.getSchedulingID());

			String sql = "UPDATE smsscheduling set 	LocaleId = ?, LocationId = ?, FromDate = ?," +
					" ToDate = ?, RunDate = ?, RunTimeHH = ?, RunTimeMM = ?, RepeatType = ?, RepeatNumber = ?," +
					" Datetime = ?, StartDateTime = ?, StopDateTime = ?, CountryCode = ?, AreaCode = ?," +
					" Phone = ?, SQLScript = ?, StartMessage = ?, EndMessage = ?, Status = ?, RunType = ?, " +
					" UserId = ?, Firstname = ?, Lastname = ?, SentMessage = ? WHERE SchedulingID = ?";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++col, smsScheduling.getLocaleId());
			statement.setInt(++col, smsScheduling.getLocationId());
			statement.setDate(++col, smsScheduling.getFromDate());
			statement.setDate(++col, smsScheduling.getToDate());
			statement.setDate(++col, smsScheduling.getRunDate());
			statement.setInt(++col,smsScheduling.getRunTimeHH());
			statement.setInt(++col, smsScheduling.getRunTimeMM());
			statement.setString(++col, smsScheduling.getRepeatType());
			statement.setInt(++col, smsScheduling.getRepeatNumber());
			// statement.setLong(++col, reportScheduling.getDateTime());
			statement.setTimestamp(++col, new java.sql.Timestamp(
					smsScheduling.getDatetime()));
			statement.setTimestamp(++col, new java.sql.Timestamp(
					smsScheduling.getStartDateTime()));
			statement.setTimestamp(++col, new java.sql.Timestamp(
					smsScheduling.getStopDateTime()));
			statement.setString(++col, smsScheduling.getCountryCode());
			statement.setString(++col, smsScheduling.getAreaCode());
			statement.setString(++col, smsScheduling.getPhone());
			statement.setString(++col, smsScheduling.getSqlScript());
			statement.setString(++col, smsScheduling.getStartMessage());
			statement.setString(++col, smsScheduling.getEndMessage());
			statement.setString(++col, smsScheduling.getStatus());
			statement.setString(++col, smsScheduling.getRunType());
			statement.setInt(++col, smsScheduling.getUserId());
			statement.setString(++col, smsScheduling.getFirstname());
			statement.setString(++col, smsScheduling.getLastname());
			statement.setString(++col, smsScheduling.getSentMessage());
			statement.setInt(++col, smsScheduling.getSchedulingID());
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
	public boolean updateSMSCommonScheduling(SMSSchedulingDTO smsScheduling)
	throws SQLException, Exception {
boolean updated = false;

try {

	SMSSchedulingDTO oldComp = getSMSScheduling(smsScheduling
			.getSchedulingID());

	String sql = "UPDATE smsscheduling set 	LocaleId = ?, LocationId = ?, FromDate = ?," +
			" ToDate = ?, RunDate = ?, RunTimeHH = ?, RunTimeMM = ?, RepeatType = ?, RepeatNumber = ?," +
			" Datetime = ?, StartDateTime = ?, StopDateTime = ?, CountryCode = ?, AreaCode = ?," +
			" Phone = ?, SQLScript = ?, StartMessage = ?, EndMessage = ?, Status = ?, RunType = ?, " +
			" UserId = ?, Firstname = ?, Lastname = ?, SentMessage = ?, CompanyId = ?, DataURL = ?, dbLookup = ?, CompanyName = ?, SendType = ? WHERE SchedulingID = ?";

	int col = 0;
	makeConnection(getDbLookup());
	statement = conn.prepareStatement(sql);
	statement.setInt(++col, smsScheduling.getLocaleId());
	statement.setInt(++col, smsScheduling.getLocationId());
	statement.setDate(++col, smsScheduling.getFromDate());
	statement.setDate(++col, smsScheduling.getToDate());
	statement.setDate(++col, smsScheduling.getRunDate());
	statement.setInt(++col,smsScheduling.getRunTimeHH());
	statement.setInt(++col, smsScheduling.getRunTimeMM());
	statement.setString(++col, smsScheduling.getRepeatType());
	statement.setInt(++col, smsScheduling.getRepeatNumber());
	// statement.setLong(++col, reportScheduling.getDateTime());
	statement.setTimestamp(++col, new java.sql.Timestamp(
			smsScheduling.getDatetime()));
	statement.setTimestamp(++col, new java.sql.Timestamp(
			smsScheduling.getStartDateTime()));
	statement.setTimestamp(++col, new java.sql.Timestamp(
			smsScheduling.getStopDateTime()));
	statement.setString(++col, smsScheduling.getCountryCode());
	statement.setString(++col, smsScheduling.getAreaCode());
	statement.setString(++col, smsScheduling.getPhone());
	statement.setString(++col, smsScheduling.getSqlScript());
	statement.setString(++col, smsScheduling.getStartMessage());
	statement.setString(++col, smsScheduling.getEndMessage());
	statement.setString(++col, smsScheduling.getStatus());
	statement.setString(++col, smsScheduling.getRunType());
	statement.setInt(++col, smsScheduling.getUserId());
	statement.setString(++col, smsScheduling.getFirstname());
	statement.setString(++col, smsScheduling.getLastname());
	statement.setString(++col, smsScheduling.getSentMessage());
	statement.setInt(++col, smsScheduling.getCompanyId());
	statement.setString(++col, smsScheduling.getDataURL());
	statement.setString(++col, smsScheduling.getDbLookup());
	statement.setString(++col, smsScheduling.getCompanyName());
	statement.setInt(++col, smsScheduling.getSchedulingID());
	statement.setString(++col, smsScheduling.getSendType());
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
	/**
	 * 
	 * @param formFilterID
	 * @return
	 */
	public boolean deleteSMSScheduling(int schedulingID) {
		boolean deleted = false;

		try {
			SMSSchedulingDTO oldComp = getSMSScheduling(schedulingID);

			String sql = "DELETE FROM smsscheduling where SchedulingID = ?";

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setInt(++col, schedulingID);
			statement.executeUpdate();

//			deleteAudit(oldComp);

			deleted = true;
		} catch (SQLException e) {
			logger.error("SQLException in deleteSMSScheduling() : ", e);
		} catch (Exception e) {
			logger.error("Exception in deleteSMSScheduling() : ", e);
		} finally {
			closeConnection();
		}

		return deleted;
	}

	public String getSQLMessage(String sqlScript) {
		String msg = "";
		
		try {
			String sql = sqlScript;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);

			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				msg = rs.getString(++col);
				
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSQLMessage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSQLMessage() : ", e);
		} finally {
			closeConnection();
		}

		return msg;
	}
	public String getSQLMessage(String sqlScript, String dbLookup) {
		String msg = "";
		
		try {
			String sql = sqlScript;
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql);

			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				msg = rs.getString(++col);
				
			}

		} catch (SQLException e) {
			logger.error("SQLException in getSQLMessage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSQLMessage() : ", e);
		} finally {
			closeConnection();
		}

		return msg;
	}
}
