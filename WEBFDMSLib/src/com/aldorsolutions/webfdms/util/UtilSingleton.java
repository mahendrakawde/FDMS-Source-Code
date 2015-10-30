package com.aldorsolutions.webfdms.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.configuration.ConfigBean;
import com.aldorassist.webfdms.configuration.ConfigCache;
import com.aldorassist.webfdms.model.ConfigValueDTO;
import com.aldorassist.webfdms.model.ReportSchedulingDTO;
import com.aldorsolutions.webfdms.reporting.ReportGenerator;
import com.aldorsolutions.webfdms.reporting.dao.ReportSchedulingDAO;



/**
 * Workfile: UtilSingleton.java
 * Date: Nov 7, 2005 6:18:32 PM
 * Author: Guadalupe Garcia
 * update: March 03 09
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class UtilSingleton {

	public static UtilSingleton instance;
	private Properties props;
//    public static String LOCALE = null;
    private DataSourceManager dsMgr = null;
    private String userDBLookup = null;
    private String fdmsDBLookup = null;
    private String auditDBLookup = null;
    private String commonDBLookup = null;
    private String funeralSyncDBLookup = null;
    private int RRNumber = 0;
    private int RRMaxNumber = 0;
    
    private int RRDashboardNumber = 0;
    private int RRDashboardMaxNumber = 0;
    
    private static final Logger logger = Logger.getLogger(UtilSingleton.class.getName());
	
	static {
		UtilSingleton.instance = new UtilSingleton();
	}

	public UtilSingleton() {
		loadProps();
		dsMgr = new DataSourceManager();
		
		String usersLookup = getProperty("db.users.jndi");
		String fdmsLookup = getProperty("db.fdmsdata1.jndi");
		String auditLookup = getProperty("db.audit.jndi");
		String commonLookup = getProperty("db.common.jndi");
		String funeralSyncLookup = getProperty("db.funeralsync.jndi");
		
		userDBLookup = usersLookup;
	    fdmsDBLookup = fdmsLookup;
	    auditDBLookup = auditLookup;
	    commonDBLookup = commonLookup;
	    funeralSyncDBLookup = funeralSyncLookup;
		
		try {
			dsMgr.getDataSource ( usersLookup );
			dsMgr.getDataSource ( fdmsLookup );
			dsMgr.getDataSource ( auditLookup );
			dsMgr.getDataSource ( commonLookup );
			dsMgr.getDataSource (funeralSyncLookup);
		} catch ( Exception e ) {
			logger.error("Unable to create initial lookups.", e);
		}
				
	}
	
	private void loadProps() {
        InputStream in = null;
        
//        if (LOCALE == null) {
            try {
                in = this.getClass().getResourceAsStream("/webfdms.properties");
                if (in != null) {
                	props = new Properties();
                	props.load(in);
//                    LOCALE = props.getProperty("webapp.locale");
                }
            } catch (Exception e) {
                logger.error("Exception in loading property : ", e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {
                        logger.error("Exception in closing inputstream : ", e);
                    }
                }
            }
//        }
        
//        if (LOCALE == null) LOCALE = Constants.LOCALE_US;		
	}
	
	public static UtilSingleton getInstance() {
		return UtilSingleton.instance;
	}
	
	private String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public String getProperty ( long configID, String code ) {
		return ConfigCache.getInstance().getConfigCode(configID, code);
	}
	
	public String getRRProperty ( long configID, String code ) {
		ArrayList RRGeneratorIPs = new ArrayList();
		String RRGeneratorIP = "";
		String key ="";
		String search = code.toUpperCase();
		String name = "";
		String nameUp = "";
		String value = "";
		
		ConfigBean config = ConfigCache.getInstance().getConfig(configID);

		for ( ConfigValueDTO cValue : config.getConfigValues().values() ) {
			name = cValue.getConfigCode();
			 nameUp = name.toUpperCase();
			    int possition = nameUp.indexOf(search);
			    if ( possition != -1) {
			    	value = cValue.getValue();
			    	RRGeneratorIPs.add(value);
			    }
		}
		RRMaxNumber = RRGeneratorIPs.size();
		RRGeneratorIP = (String) RRGeneratorIPs.get(RRNumber);
		if (RRNumber == (RRMaxNumber-1)) {
			RRNumber = 0;
		}
		else {
			RRNumber = RRNumber +1;
		}
		return RRGeneratorIP;
	}
	
	public String getRRDashboardProperty ( long configID, String code ) {
		ArrayList RRGeneratorIPs = new ArrayList();
		String RRGeneratorIP = "";
		String key ="";
		String search = code.toUpperCase();
		String name = "";
		String nameUp = "";
		String value = "";
		
		ConfigBean config = ConfigCache.getInstance().getConfig(configID);

		for ( ConfigValueDTO cValue : config.getConfigValues().values() ) {
			name = cValue.getConfigCode();
			 nameUp = name.toUpperCase();
			    int possition = nameUp.indexOf(search);
			    if ( possition != -1) {
			    	value = cValue.getValue();
			    	RRGeneratorIPs.add(value);
			    }
		}
		RRDashboardMaxNumber = RRGeneratorIPs.size();
		RRGeneratorIP = (String) RRGeneratorIPs.get(RRDashboardNumber);
		if (RRDashboardNumber == (RRDashboardMaxNumber-1)) {
			RRDashboardNumber = 0;
		}
		else {
			RRDashboardNumber = RRDashboardNumber +1;
		}
		return RRGeneratorIP;
	}
	
	
	
	public Connection getConnectionFromCache(String jndiLookup) throws SQLException {
		return ( dsMgr.createConnection(jndiLookup) );
	}

	/**
	 * @return the auditDBLookup
	 */
	public String getAuditDBLookup() {
		return auditDBLookup;
	}

	/**
	 * @return the commonDBLookup
	 */
	public String getCommonDBLookup() {
		return commonDBLookup;
	}

	/**
	 * @return the fdmsDBLookup
	 */
	public String getFdmsDBLookup() {
		return fdmsDBLookup;
	}

	/**
	 * @return the userDBLookup
	 */
	public String getUserDBLookup() {
		return userDBLookup;
	}

	/**
	 * @return the funeralSyncDBLookup
	 */
	public String getFuneralSyncDBLookup() {
		return funeralSyncDBLookup;
	}

	
	/**
	 * @return the rRMaxNumber
	 */
	public int getRRMaxNumber() {
		return RRMaxNumber;
	}

	/**
	 * @param maxNumber the rRMaxNumber to set
	 */
	public void setRRMaxNumber(int maxNumber) {
		RRMaxNumber = maxNumber;
	}
	
	/**
	 * @return the rRMaxNumber
	 */
	public int getRRDashboardMaxNumber() {
		return RRDashboardMaxNumber;
	}

	/**
	 * @param maxNumber the rRMaxNumber to set
	 */
	public void setRRDashboardMaxNumber(int maxNumber) {
		RRDashboardMaxNumber = maxNumber;
	}

}