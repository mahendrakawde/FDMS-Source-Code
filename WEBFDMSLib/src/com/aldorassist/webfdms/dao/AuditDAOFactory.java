package com.aldorassist.webfdms.dao;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.common.Constants;

public class AuditDAOFactory {

	static Logger logger = Logger.getLogger(AuditDAOFactory.class.getName());

	public AuditDAOFactory() {
	}

	public static AuditDAO getDAO() throws Exception {
		AuditDAO auditDAO = null;
		auditDAO = (AuditDAO) Class.forName(Constants.AUDIT_DAO_CLASS).newInstance();
		return auditDAO;
	}
}
