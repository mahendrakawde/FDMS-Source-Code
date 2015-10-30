package com.aldorsolutions.webfdms.pricelist.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbPriceListPeer;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentI;


public class PriceListManager {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * Create a set of Price List entries for one GPL KEY Creation date:
	 * (12/26/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPriceList item or null if no entry for given key
	 *         found
	 */
	public DbPriceList getPriceListForKey(DatabaseTransaction t, String version, String gplkey) {
		Hashtable h = new Hashtable();
		com.aldorsolutions.webfdms.beans.DbPriceList pricelistitem = null;
		try {
			com.aldorsolutions.webfdms.beans.DbPriceListSet plSet = new com.aldorsolutions.webfdms.beans.DbPriceListSet();
			h.put(DbPriceListPeer.VERSION, version);
			h.put(DbPriceListPeer.GPLKEY, gplkey);
			plSet.restore(t, h);
			PersistentI[] obs = plSet.getPersistents();
			// Should only be one price list item for given version and GPL Key
			if (obs.length > 0) {
				pricelistitem = (com.aldorsolutions.webfdms.beans.DbPriceList) obs[0];
			} else if (obs.length > 1) {
				//AppLog.warning("DbPriceList.getPriceListForKey found more
				// than one row for:"+version+"/"+gplkey );
			}
			return pricelistitem;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPriceListForKey Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a set of Price List entries for one version Creation date:
	 * (12/26/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPriceList[]
	 */
	public DbPriceList[] getPriceListForVersion(DatabaseTransaction t, String version, int localeid) {
		Hashtable h = new Hashtable();
		com.aldorsolutions.webfdms.beans.DbPriceList[] pricelist;
		try {
			com.aldorsolutions.webfdms.beans.DbPriceListSet plSet = new com.aldorsolutions.webfdms.beans.DbPriceListSet();
			h.put(DbPriceListPeer.VERSION, version);
			h.put(DbPriceListPeer.LOCALEID, new Integer(localeid));
			plSet.restore(t, h);
			PersistentI[] obs = plSet.getPersistents();
			pricelist = new com.aldorsolutions.webfdms.beans.DbPriceList[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				pricelist[i] = (com.aldorsolutions.webfdms.beans.DbPriceList) obs[i];
			}
			return pricelist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getPriceListForVersion Persistence
			// Exception:",e);
			return null;
		}
	}
	
	/**
	 * Get one price list item by record ID Creation date: (12/24/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPriceList
	 */
	public DbPriceList getPriceListItem(DatabaseTransaction t, int id) {
		DbPriceList p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbPriceList) Persistent.getPersistent(t, id, "com.aldorsolutions.webfdms.beans.DbPriceList");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getPriceListItem Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of price list versions used in the price list table.
	 * Creation date: (1/21/02 9:11:17 AM)
	 * 
	 * @return String[] version names
	 */
	public ArrayList getPriceListVersionsByCompanyID(DatabaseTransaction t, long companyID) {
		ArrayList versions = new ArrayList();
		ResultSet rs;
		PreparedStatement stmt;
		Connection connection;
		try {
			connection = ((DatabaseTransaction) t).getConnection();
			
			String sql = "select DISTINCT PriceListVersion from pricelist where " +
						 "localeID in ( select localeID from locales where companyid = ? ) " +
						 "order by PriceListVersion DESC";
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, companyID);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String version = rs.getString("PriceListVersion");
				
				if ( version != null && version.length() > 0 ) {
					versions.add(rs.getString("PriceListVersion"));
				}
			}

			return versions;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getPriceListVersions SQLException:" + e.getMessage());
			return null;
		} catch (SQLException e) {
			logger.error("FdmsDb::getPriceListVersions SQLException:" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Create a list of price list versions used in the price list table.
	 * Creation date: (1/21/02 9:11:17 AM)
	 * 
	 * @return String[] version names
	 */
	public ArrayList getPriceListVersions(DatabaseTransaction t, int localeid) {
		ArrayList versions = new ArrayList();
		ResultSet rs;
		Statement stmt;
		Connection connection;
		try {
			connection = ((DatabaseTransaction) t).getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT PriceListVersion FROM pricelist WHERE LocaleID=" + localeid
					+ " order by PriceListVersion DESC");
			while (rs.next()) {
				versions.add(rs.getString("PriceListVersion"));
			}

			return versions;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getPriceListVersions SQLException:",e);
			return null;
		} catch (SQLException e) {
			// logger.error("FdmsDb::getPriceListVersions SQLException:",e);
			return null;
		}
	}
	

	/**
	 * Determine the default package price list for this case-ID by retrieving
	 * the price list for the case's location. If the location is not present,
	 * return the first package price list version in MULTICMP.TAB table.
	 * Creation date: (2/7/01 11:16:35 AM)
	 * 
	 * @return java.lang.String
	 */
	public String getDefaultPackagePriceList(DatabaseTransaction t, int vitalsID) {
		String verdefault = "none";
		DbLocation chapel = null;
		DbCase currCase = FdmsDb.getInstance().getCase(t, vitalsID);
		if (currCase != null) {
			t.removePersistent(currCase);
			chapel = FdmsDb.getInstance().getLocation(t, currCase.getChapelNumber());
			if (chapel != null) {
				t.removePersistent(chapel);
				return chapel.getPackageVersion();
			}
		}
		// Can't get price list for case so just pick the first one.
		ArrayList versions = getPriceListVersions(t, currCase.getLocale());
		Iterator verloop = versions.iterator();
		if (verloop.hasNext()) {
			verdefault = verloop.next().toString();
		}
		return verdefault;
	}

	/**
	 * Determine the default price list for this case-ID by retrieving the price
	 * list for the case's location. If the location is not present, return the
	 * first price list version in MULTICMP.TAB table. Creation date: (2/7/01
	 * 11:16:35 AM)
	 * 
	 * @return java.lang.String
	 */
	public String getDefaultPriceList(DatabaseTransaction t, int vitalsID) {
		String verdefault = "none";
		DbLocation chapel = null;
		DbCase currCase = FdmsDb.getInstance().getCase(t, vitalsID);
		if (currCase != null) {
			t.removePersistent(currCase);
			chapel = FdmsDb.getInstance().getLocation(t, currCase.getChapelNumber());
			if (chapel != null) {
				t.removePersistent(chapel);
				return chapel.getPriceListVersion();
			}
		}
		// Can't get price list for case so just pick the first one.
		ArrayList versions = getPriceListVersions(t, currCase.getLocale());
		Iterator verloop = versions.iterator();
		if (verloop.hasNext()) {
			verdefault = verloop.next().toString();
		}
		return verdefault;
	}
	
}
