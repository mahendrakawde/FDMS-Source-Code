/*
 * CemCaseSetDAO.java
 *
 */

package fdms.ui.caseset.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbCemSpcCase;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * 
 * @author Guadalupe Garcia
 */
public class CemSpcCaseSetDAO extends DAO {

	private static Logger logger = Logger.getLogger(CaseSetDAO.class.getName());

	private String dbLookup = null;

	/**
	 * Creates a new instance of CemCaseSetDAO
	 * 
	 * @param dbLookup
	 * @throws Exception
	 */
	public CemSpcCaseSetDAO(String dbLookup) throws Exception {
		this.dbLookup = dbLookup;
	}

	/**
	 * 
	 * @return arraylist
	 */
	public ArrayList getCaseList(Character spaceType) {

		ArrayList cases = new ArrayList();

		try {
			makeConnection(dbLookup);

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SpaceID,");
			sql.append("    SpcType,");
			sql.append("    SpcTypeNumber,");
			sql.append("    SpcParentID,");
			sql.append("    SpcTypeName,");
			sql.append("    SpcDesc ");
			sql.append("FROM cem_space ");
			// sql.append("WHERE Type = ");
			// sql.append("\'"+ spaceType + "\'");

			statement = conn.prepareStatement(sql.toString());
			int col = 0;

			// statement.setInt(++col, active);

			int intSPID;
			intSPID = 0;
			int intTYN;
			intTYN = 0;
			int intPID;
			intPID = 0;

			rs = statement.executeQuery();
			while (rs.next()) {
				col = 0;
				DbCemSpcCase dbCemSpcCase = new DbCemSpcCase();
				intSPID = rs.getInt(++col);
				Integer intSpaceID = new Integer(intSPID);
				dbCemSpcCase.setSpaceID(intSpaceID);
				dbCemSpcCase.setType(rs.getString(++col));
				intTYN = rs.getInt(++col);
				Integer intTypeNum = new Integer(intTYN);
				dbCemSpcCase.setTypeNumber(intTypeNum);
				intPID = rs.getInt(++col);
				Integer intParentID = new Integer(intPID);
				dbCemSpcCase.setTypeNumber(intParentID);
				dbCemSpcCase.setTypeName(rs.getString(++col));
				dbCemSpcCase.setDesc(rs.getString(++col));

				cases.add(dbCemSpcCase);
			}

		} catch (SQLException e) {
			logger.error("CemSpcCaseSetDao SQL Exception in getCaseList() : ", e);
		} catch (Exception e) {
			logger.error("CemSpcCaseSetDao Exception in getCaseList() : ", e);
		} finally {
			closeConnection();
		}

		return cases;
	}

}
