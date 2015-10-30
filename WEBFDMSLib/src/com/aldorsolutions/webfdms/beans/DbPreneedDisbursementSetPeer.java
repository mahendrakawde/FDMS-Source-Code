package com.aldorsolutions.webfdms.beans;

/**
 * Workfile: DbPreneedDisbursementSetPeer.java
 * Date: Nov 15, 2005 10:10:38 AM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class DbPreneedDisbursementSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbPreneedDisbursement";
	}

	public String getSql(java.util.Hashtable h) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM preneeddisbursement ");
		sql.append("WHERE (VitalsMasterKey = " + h.get(DbPreneedDisbursementPeer.VITALSMASTERKEY) + ")");
		
		return sql.toString();
	}	

}
