package com.aldorassist.webfdms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.GlAccountTranslationDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class GlAccountTranslationDAO extends DAO {

	private Logger logger = Logger.getLogger(GlAccountTranslationDAO.class.getName());

	/**
	 * @param user
	 */
	public GlAccountTranslationDAO(DbUserSession user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param companyID
	 * @param userId
	 */
	public GlAccountTranslationDAO(long companyID, long userId) {
		super(companyID, userId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dbLookup
	 */
	public GlAccountTranslationDAO(String dbLookup) {
		super(dbLookup);
		// TODO Auto-generated constructor stub
	}


	public String getAssetGLAcctNumber(long accountDescCDID, long saleTypeCDID) {
		String glAcctNumber = null;
		int col = 0;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select assetAcctGL from glaccttranslation where accountDescCDID = ? and saleTypeCDID = ?");

			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, accountDescCDID);
			statement.setLong(++col, saleTypeCDID);
			rs = statement.executeQuery();

			if (rs.next()) {
				col=0;
				glAcctNumber = rs.getString(++col);
			} else {
				//glAcctNumber = "99998";
				glAcctNumber = "99999";
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCompanyOptions() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCompanyOptions() : ", e);
		} finally {
			closeConnection();
		}

		return glAcctNumber;
	}

	
	
	
	public String getCostOfGoodsSoldGLAcctNumber(long accountDescCDID, long saleTypeCDID) {
		String glAcctNumber = null;
		int col = 0;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select costOfGoodsSoldAcctGL from glaccttranslation where accountDescCDID = ? and saleTypeCDID = ?");
			                   
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, accountDescCDID);
			statement.setLong(++col, saleTypeCDID);
			rs = statement.executeQuery();

			if (rs.next()) {
				col=0;
				glAcctNumber = rs.getString(++col);
			} else {
				glAcctNumber = "99998";
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCompanyOptions() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCompanyOptions() : ", e);
		} finally {
			closeConnection();
		}

		return glAcctNumber;
	}

	
	
	
	public String getSalesGLAcctNumber(long accountDescCDID, long saleTypeCDID) {
		String glAcctNumber = null;
		int col = 0;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select salesAcctGL from glaccttranslation where accountDescCDID = ? and saleTypeCDID = ?");

			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, accountDescCDID);
			statement.setLong(++col, saleTypeCDID);
			rs = statement.executeQuery();

			if (rs.next()) {
				col=0;
				glAcctNumber = rs.getString(++col);
			} else {
				glAcctNumber = "99998";
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCompanyOptions() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCompanyOptions() : ", e);
		} finally {
			closeConnection();
		}

		return glAcctNumber;
	}
}
