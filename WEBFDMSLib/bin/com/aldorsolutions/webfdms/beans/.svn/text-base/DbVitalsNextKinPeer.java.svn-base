package com.aldorsolutions.webfdms.beans;

/**
 * This class supplies constants and SQL for DbVitalsNextKin. Creation date:
 * (11/14/2001 4:13:09 PM)
 * 
 * @author:
 */
public class DbVitalsNextKinPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY = "VitalsMasterKey";

	static public final String CITY = "NextKinCity";

	static public final String FIRSTNAME = "NextKinFirstName";

	static public final String LASTNAME = "NextKinLastName";

	static public final String PHONE = "NextKinPhone";

	static public final String CELLPHONE = "NextKinCellPhone";

	static public final String RELATION = "NextKinRelation";

	static public final String ROAD2 = "NextKinRoad2";

	static public final String ROAD3 = "NextKinRoad3";

	static public final String SALUTATION = "NextKinSalutation";

	static public final String STATE = "NextKinState";

	static public final String STREET = "NextOfKinAddress";

	static public final String ZIP = "NextKinZip";

	static public final String SAMEINFORMANT = "NextKinSameAsInf";
	
	static public final String MIDDLENAME = "NextKinMiddleName";
	
	static public final String EMAIL= "NextKinEmail";
	
	static public final String PREFERCOMMUNICATE= "NextKinPreferCommunicate";

	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		throw new com.aldorsolutions.webfdms.database.PersistenceException(
				"DbVitalNextKinPeer.Insert NOT ALLOWED");
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		throw new com.aldorsolutions.webfdms.database.PersistenceException(
				"DbVitalsNextKinPeer. Delete NOT ALLOWED");
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT " + IDENTITY + "," + FIRSTNAME + "," + LASTNAME + ","
				+ STREET + "," + ROAD2 + "," + ROAD3 + "," + CITY + "," + STATE
				+ "," + ZIP + "," + PHONE + "," + CELLPHONE + "," + RELATION
				+ "," + SALUTATION + "," + SAMEINFORMANT + ", " + MIDDLENAME + ", " + EMAIL +", " +PREFERCOMMUNICATE +" "
				+ "from vitalstats WHERE VitalsMasterKey = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbVitalsNextKin vitals = (DbVitalsNextKin) p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection
					.prepareStatement("UPDATE vitalstats SET " + FIRSTNAME
							+ "=?," + LASTNAME + "=?," + STREET + "=?," + ROAD2
							+ "=?," + ROAD3 + "=?," + CITY + "=?," + STATE
							+ "=?," + ZIP + "=?," + PHONE + "=?," + CELLPHONE
							+ "=?," + RELATION + "=?," + SALUTATION + "=?,"
							+ SAMEINFORMANT + "=?, " + MIDDLENAME + "=?, "
							+ EMAIL + "=?, " + PREFERCOMMUNICATE + "=? "
							+ "WHERE VitalsMasterKey = ?");
			pstmt.setString(1, vitals.getFirstname());
			pstmt.setString(2, vitals.getLastname());
			pstmt.setString(3, vitals.getStreet());
			pstmt.setString(4, vitals.getRoad2());
			pstmt.setString(5, vitals.getRoad3());
			pstmt.setString(6, vitals.getCity());
			pstmt.setString(7, vitals.getState());
			pstmt.setString(8, vitals.getZip());
			pstmt.setString(9, vitals.getPhone());
			pstmt.setString(10, vitals.getCellPhone());
			pstmt.setString(11, vitals.getRelation());
			pstmt.setString(12, vitals.getSalutation());
			pstmt.setString(13, vitals.getSameAsInformant());
			pstmt.setString(14, vitals.getMiddleName());
			pstmt.setString(15, vitals.getEmail());
			pstmt.setString(16, vitals.getNextKinPreferCommunicate());
			pstmt.setInt(17, vitals.getId());
			
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbVitalsInformantPeer.Update:" + e.getMessage(), e);
		}
	}
}
