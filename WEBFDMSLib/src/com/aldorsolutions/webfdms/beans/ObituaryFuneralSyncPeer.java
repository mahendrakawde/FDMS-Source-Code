package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;


/**
 * DbLocaleConfigPeer supplies constants and SQL for Persistent Class. Creation
 * date: (11/14/2001 4:13:09 PM)
 * 
 * @author:
 */
public class ObituaryFuneralSyncPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {
	
	static public final String ID = "id"; 
	static public final String OBITID="ObitID"; 
	static public final String LOCATIONID="LocationID"; 
	static public final String DECTITLE="DecTitle"; 
	static public final String DECFIRSTNAME="DecFirstName"; 
	static public final String DECMIDDLENAME="DecMiddleName"; 
	static public final String DECLASTNAME="DecLastName"; 
	static public final String DECNICKNAME="DecNickName"; 
	static public final String DECMAIDENNAME="DecMaidenName"; 
	static public final String DATEOFBIRTH="DateOfBirth"; 
	static public final String PLACEOFBIRTH="PlaceOfBirth"; 
	static public final String DATEOFDEATH="DateOfDeath"; 
	static public final String PLACEOFDEATH="PlaceOfDeath"; 
	static public final String VIEWTYPE="ViewType"; 
	static public final String VIEWNOBODYPRESENT="ViewNoBodyPresent"; 
	static public final String VIEWDATE="ViewDate"; 
	static public final String VIEWTIME="ViewTime"; 
	static public final String VIEWATHOME="ViewAtHome"; 
	static public final String VIEWLOCATION="ViewLocation"; 
	static public final String OTHERVIEWATHOME="OtherViewAtHome"; 
	static public final String VIEWOTHERLOCATION="ViewOtherLocation"; 
	static public final String VIEWOTHERDATE="ViewOtherDate"; 
	static public final String VIEWOTHERTIME="ViewOtherTime"; 
	static public final String BURIALTYPE="BurialType"; 
	static public final String BURIALDATE="BurialDate"; 
	static public final String BURIALTIME="BurialTime"; 
	static public final String CEMETERYNAME="CemeteryName"; 
	static public final String SERVICETYPE="ServiceType"; 
	static public final String SERVICEDATE="ServiceDate"; 
	static public final String SERVICETIME="ServiceTime"; 
	static public final String SERVICEATHOME="ServiceAtHome"; 
	static public final String SERVICEOTHERLOCATION="ServiceOtherLocation"; 
	static public final String EXTRASERVICES="ExtraServices"; 
	static public final String FLOWERSTYPE="FlowersType"; 
	static public final String FLOWERSDATE="FlowersDate"; 
	static public final String FLOWERSTIME="FlowersTime"; 
	static public final String FLOWERSOTHERDATE="FlowersOtherDate"; 
	static public final String FLOWERSOTHERTIME="FlowersOtherTime"; 
	static public final String FLOWERSOTHERLOCATION="FlowersOtherLocation"; 
	static public final String CONTRIBUTIONNAME="ContributionName"; 
	static public final String EULOGY="Eulogy"; 
	static public final String SURVIVORS="Survivors"; 
	static public final String DATEENTERED="DateEntered"; 
	static public final String DATEMODIFIED="DateModified"; 
	static public final String ARCHIVEDATE="ArchiveDate"; 
	static public final String CLIENTID="ClientId"; 
	static public final String POSTEDYN="PostedYN"; 
	static public final String POSTEDDATETIME="PostedDateTime";
	
	static public final String LOCALEID  = "";//TODO rupali
	static public final String LOCALECFGTYPEID = "";//TODO rupali
	static public final String VALUE = "";//TODO rupali

	Logger logger = Logger.getLogger(ObituaryFuneralSyncPeer.class.getName());
	
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;

		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbLocaleConfig localeCfg = (DbLocaleConfig) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("INSERT INTO localeconfigs (" + 
					LOCALEID + "," + LOCALECFGTYPEID + "," + VALUE + " ) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, localeCfg.getLocaleID());
			pstmt.setInt(2, localeCfg.getLocaleConfigTypeID());
			pstmt.setInt(3, localeCfg.getValue());

		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigPeer.Insert:" + e.getMessage(), e);
		}

		return pstmt;
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		
		java.sql.PreparedStatement pstmt=null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM  localeconfigs WHERE localeconfigID=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigPeer.Remove",e);
		}

	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * from localeconfigs WHERE localeConfigID = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbLocaleConfig locale = (DbLocaleConfig) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("UPDATE localeconfigs SET " + 
					LOCALEID + " = ?," + LOCALECFGTYPEID + " = ?," + VALUE + " = ? " +
					"WHERE localeconfigID = ?");

			pstmt.setInt(1, locale.getLocaleID());
			pstmt.setInt(2, locale.getLocaleConfigTypeID());
			pstmt.setInt(3, locale.getValue());
			pstmt.setInt(4, locale.getId());

		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigPeer.Update:" + e.getMessage(), e);
		}

		return pstmt;
	}
}
