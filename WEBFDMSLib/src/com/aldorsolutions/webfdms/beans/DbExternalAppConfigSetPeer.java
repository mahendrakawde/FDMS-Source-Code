package com.aldorsolutions.webfdms.beans;

/**
 * @author Guadalupe Garcia
 * 
 */
public class DbExternalAppConfigSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {

	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbExternalAppConfig";
	}

	/**
	 * 
	 */
	public String getSql(java.util.Hashtable h) {

		return ("SELECT ExternalAppId, ExternalAppConfigId "
				   + "FROM externalappconfig");

	}
}
