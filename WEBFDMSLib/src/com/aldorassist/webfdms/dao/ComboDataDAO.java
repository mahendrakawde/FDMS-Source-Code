/**
 * 
 */
package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ComboDataDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * @author Chad
 *
 */
public class ComboDataDAO extends DAO {

	private Logger logger = Logger.getLogger(ComboDataDAO.class.getName());

	/**
	 * 
	 */
	public ComboDataDAO() {
		super();
	}

	/**
	 * @param dbLookup
	 */
	public ComboDataDAO(String dbLookup) {
		super(dbLookup);
	}

	/**
	 * @param user
	 */
	public ComboDataDAO(DbUserSession user) {
		super(user);
	}

	/**
	 * @param companyID
	 * @param userId
	 */
	public ComboDataDAO(long companyID, long userId) {
		super(companyID, userId);
	}

	
	
	private ComboDataDTO processResultSet(ResultSet rs, ComboDataDTO comboData) 
		throws SQLException {
		int col = 0;
		comboData.setComboDataID(rs.getLong(++col));
		comboData.setLocal(rs.getLong(++col));
		comboData.setType(rs.getInt(++col));
		comboData.setName(rs.getString(++col));
		comboData.setValue(rs.getString(++col));
		comboData.setDescription(rs.getString(++col));
		comboData.setCreatedDTS(rs.getTimestamp(++col));
		comboData.setUpdatedDTS(rs.getTimestamp(++col));
		return comboData;
	}
	
	
	public ArrayList<ComboDataDTO> getComboDataOptions(int local, int type) {
		ArrayList<ComboDataDTO> list = new ArrayList<ComboDataDTO>();

		int col = 0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from combodata where local = ? and type = ?");

			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(++col, local);
			statement.setInt(++col, type);
			rs = statement.executeQuery();

			while (rs.next()) {
				ComboDataDTO comboData = new ComboDataDTO();
				list.add(processResultSet(rs, comboData));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCompanyOptions() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCompanyOptions() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
}
