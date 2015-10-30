package com.aldorassist.webfdms.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.AuditDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class AuditDAOSQL extends DAO implements AuditDAO {

	static final Logger logger = Logger.getLogger(AuditDAOSQL.class
			.getName());
	
	public void record(AuditDTO auditDto) throws Exception {
		try {

			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("INSERT INTO audit_log ");
			sqlBuf.append("(COMPANY_ID, LOCALE_ID, USER_ID, ");
			sqlBuf.append("MESSAGE) ");
			sqlBuf.append("VALUES (?,?,?,?)");
			

			makeConnection(DAO.DB_AUDIT);
			statement = conn.prepareStatement(sqlBuf.toString());
			int idx = 1;
			statement.setInt(idx++, auditDto.getCompanyId());
			statement.setInt(idx++, auditDto.getLocaleId());
			statement.setInt(idx++, auditDto.getUserId());
			statement.setString(idx++, auditDto.getMessage());

			statement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e);
			throw new Exception(e);
		} finally {
			closeConnection();
		}
	}

}
