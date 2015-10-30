/**
 * 
 */
package com.aldorassist.webfdms.configuration;

import java.util.ArrayList;

import com.aldorassist.webfdms.dao.ConfigDAO;
import com.aldorassist.webfdms.dao.ConfigValuesDAO;
import com.aldorassist.webfdms.model.ConfigDTO;
import com.aldorassist.webfdms.model.ConfigValueDTO;

/**
 * @author David Rollins
 * Sep 7, 2007
 * ConfigCache.java
 *
 */
public class ConfigCache {
	
	private ArrayList <ConfigBean> configBeans = null;
	private static ConfigCache instance = null;
	
	static {
		instance = new ConfigCache();
	}
	
	private ConfigCache () {
		loadCache();
	}
	
	public static ConfigCache getInstance () {
		return ( instance );
	}
	
	public void loadCache() {
		ConfigDAO configDAO = new ConfigDAO();
		ConfigValuesDAO configValuesDAO = new ConfigValuesDAO();
		
		if ( configBeans == null ) {
			configBeans = new ArrayList<ConfigBean> ();
		}
		else {
			configBeans.clear();
		}
		
		ArrayList <ConfigDTO> configs = configDAO.getConfigs(true);
		
		for ( ConfigDTO config : configs ) {
			ConfigBean bean = new ConfigBean (config);
			
			ArrayList <ConfigValueDTO> configValues = null;
			configValues = configValuesDAO.getConfigValues(config.getConfigID());
			
			bean.loadConfigValues(configValues);
			configBeans.add(bean);
		}
	}
	
	public ArrayList <ConfigBean> getConfigs () {
		return ( configBeans );
	}
	
	public ConfigBean getConfig (long configID) {
		ConfigBean returnValue = null;
		
		for ( ConfigBean config : configBeans ) {
			
			if ( config.getConfigID() == configID ) {
				returnValue = config;
				break;
			}
		}
		
		return ( returnValue );
	}
	
	public String getConfigCode (long configID, String configCode) {
		ConfigBean config = getConfig(configID);
		String returnValue = null;
		
		if ( config != null ) {
			returnValue = config.getValue(configCode);
		}
		
		return ( returnValue );
	}
	
//	public String getRRConfigCode (long configID, String configCode) {
//		ConfigBean config = getConfig(configID);
//		String returnValue = null;
//		
//		if ( config != null ) {
//			returnValue = config.getValue(configCode);
//		}
//		
//		return ( returnValue );
//	}
	
	
}