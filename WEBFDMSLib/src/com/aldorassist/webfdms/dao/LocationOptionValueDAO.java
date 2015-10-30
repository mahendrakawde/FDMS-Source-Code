package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sun.security.action.GetLongAction;

import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.CompanyOptionsValueDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.LocationOptionValueDTO;
import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class LocationOptionValueDAO extends DAO {
	private Logger logger = Logger
			.getLogger(LocationOptionsDAO.class.getName());

	public void addLocationOptionValue(
			LocationOptionValueDTO locationOptionValueDTO) throws Exception {

		try {
			StringBuffer sql = new StringBuffer();

			sql.append("insert into locationoptionvalues (" + getSelectFields()
					+ ") " + "values (?, ?, ?, ?, ?)");

			int col = 0;
			makeConnection(DAO.DB_FDMSUSERS);
			statement = conn.prepareStatement(sql.toString());

			statement.setInt(++col, locationOptionValueDTO
					.getLocationOptionValue());
			statement.setInt(++col, locationOptionValueDTO.getCompanyID());
			statement.setInt(++col, locationOptionValueDTO.getUserID());
			statement.setInt(++col, locationOptionValueDTO.getLocationID());
			statement.setInt(++col, locationOptionValueDTO.getValue());

			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException in addLocation() : ", e);
			throw e;
		} catch (Exception e) {
			logger.error("Exception in addLocation() : ", e);
			throw e;
		} finally {
			closeConnection();
		}
	}

	private static String getSelectFields() {
		return "locationOptionValue,companyID,userID,locationID,value";
	}

	public ArrayList<LocationOptionValueDTO> getLocationOptionsValues(
			String companyId, String locationId) {
		ArrayList<LocationOptionValueDTO> list = new ArrayList<LocationOptionValueDTO>();

		try {
			StringBuffer sql = new StringBuffer();
			// sql.append("select "
			// +getSelectFields()+" from locationoptionvalues");
			sql.append("SELECT locationOptionValue,companyID,userID,locationID,VALUE,locationOptionValueID FROM locationoptionvalues WHERE companyID ="
							+ companyId + " AND locationID =" + locationId);
			makeConnection(DAO.DB_FDMSUSERS);
			statement = conn.prepareStatement(sql.toString());

			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;

				LocationOptionValueDTO locationOptionValueDTO = new LocationOptionValueDTO();

				locationOptionValueDTO.setLocationOptionValue(rs.getInt(++col));
				locationOptionValueDTO.setCompanyID(rs.getInt(++col));
				locationOptionValueDTO.setUserID(rs.getInt(++col));
				locationOptionValueDTO.setLocationID(rs.getInt(++col));
				locationOptionValueDTO.setValue(rs.getInt(++col));
				locationOptionValueDTO.setLocationOptionValueID(rs
						.getInt(++col));
				list.add(locationOptionValueDTO);

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

	public boolean updateLocationOptionValue(
			LocationOptionValueDTO locationOptionValueDTO) throws Exception {
		boolean updated = false;

		try {

			// CompanyOptionsValueDTO oldComp =
			// getCompanyOptionValue(companyOptionValue.getCompanyOptionValueID());

			StringBuffer sql = new StringBuffer();
			// sql.append("UPDATE companyoptionvalues set companyOptionID = ?, companyID = ?, value = ? "
			// +
			// "WHERE companyOptionValueID = ?");
			sql
					.append("UPDATE locationoptionvalues SET locationOptionValue = ?, companyID = ?, userID = ? ,locationID = ? ,VALUE = ? "
							+ "WHERE locationOptionValueID = ?");

			int col = 0;
			makeConnection(DAO.DB_FDMSUSERS);
			statement = conn.prepareStatement(sql.toString());

			statement.setInt(++col, locationOptionValueDTO
					.getLocationOptionValue());
			statement.setInt(++col, locationOptionValueDTO.getCompanyID());
			statement.setInt(++col, locationOptionValueDTO.getUserID());
			statement.setInt(++col, locationOptionValueDTO.getLocationID());
			statement.setInt(++col, locationOptionValueDTO.getValue());

			statement.setLong(++col, locationOptionValueDTO
					.getLocationOptionValueID());

			statement.executeUpdate();

			// updateAudit(companyOptionValue, oldComp);

			updated = true;
		} catch (SQLException e) {
			logger.error("SQLException in updateLocationOptionValue() : ", e);
		} catch (Exception e) {
			logger.error("Exception in updateLocationOptionValue() : ", e);
			throw e;
		} finally {
			closeConnection();
		}

		return updated;
	}

	// added by haranesh patel
	public boolean addCompanyOptionValue(
			LocationOptionValueDTO locationOptionValueDTO) throws Exception {
		boolean added = false;

		try {
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			StringBuffer sql = new StringBuffer();
			// sql.append("INSERT INTO companyoptionvalues " +
			// "(companyOptionID, companyID, value) ");
			// sql.append("VALUES ( ?, ?, ? )");

			sql
					.append("INSERT INTO locationoptionvalues (locationOptionValue,companyID,userID,locationID,value) ");
			sql.append("VALUES ( ?, ?, ?, ? ,?)");

			int col = 0;
			makeConnection(DAO.DB_FDMSUSERS);
			statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			statement.setInt(++col, locationOptionValueDTO
					.getLocationOptionValue());
			statement.setInt(++col, locationOptionValueDTO.getCompanyID());
			statement.setInt(++col, locationOptionValueDTO.getUserID());
			statement.setInt(++col, locationOptionValueDTO.getLocationID());
			statement.setInt(++col, locationOptionValueDTO.getValue());

			// statement.setLong(++col,
			// companyOptionValue.getCompanyOptionID());
			// statement.setLong(++col, companyOptionValue.getCompanyID());
			// statement.setInt(++col, companyOptionValue.getValue());

			statement.executeUpdate();
			added = true;

			if (added) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					// companyOptionValue.setCompanyOptionValueID(rs.getInt(1));
					locationOptionValueDTO.setLocationOptionValueID(rs
							.getInt(1));
				}

				// insertAudit(companyOptionValue);
			}

		} catch (SQLException e) {
			logger.error("SQLException in addCompanyOptionValue() : ", e);
			throw e;
		} catch (Exception e) {
			logger.error("Exception in addCompanyOptionValue() : ", e);
			throw e;
		} finally {
			closeConnection();
		}

		return added;
	}

}
