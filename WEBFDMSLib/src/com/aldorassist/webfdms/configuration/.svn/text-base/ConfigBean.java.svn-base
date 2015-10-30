/**
 * 
 */
package com.aldorassist.webfdms.configuration;

import java.util.Collection;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ConfigDTO;
import com.aldorassist.webfdms.model.ConfigValueDTO;

/**
 * @author David Rollins
 * Sep 7, 2007
 * ConfigBean.java
 *
 */
public class ConfigBean {
	
	private Logger logger = Logger.getLogger(ConfigBean.class.getName());
	private ConfigDTO config = null;
	private Hashtable<String, ConfigValueDTO> configValues = new Hashtable<String, ConfigValueDTO> ();
	
	ConfigBean (ConfigDTO config) {
		this.config = config;
	}
	
	ConfigBean (ConfigDTO config, Collection <ConfigValueDTO> valueList) {
		this (config);
		loadConfigValues(valueList);
	}
	
	void loadConfigValues (Collection <ConfigValueDTO> valueList) {
		
		logger.debug("valueList: " + valueList);
		
		for ( ConfigValueDTO value : valueList ) {
			logger.debug("value: " + value);
			
			logger.debug("value ConfigID: " + value.getConfigID() );
			logger.debug("value ConfigCode: " + value.getConfigCode() );
			logger.debug("value Value: " + value.getValue() );
			
			configValues.put(value.getConfigCode(), value);
		}
	}
	
	public ConfigDTO getConfig () {
		return ( config );
	}

	public long getConfigID () {
		return ( config.getConfigID() );
	}
	
	public String getName () {
		return ( config.getName() );
	}
	
	public String getValue ( String code ) {
		ConfigValueDTO value = configValues.get(code);
		
		if ( value != null ) {
			return ( value.getValue() );
		}
		
		return ( null );
	}
	
	public ConfigValueDTO getValueDTO ( String code ) {
		return ( configValues.get(code) );
	}
	
	public Hashtable<String, ConfigValueDTO> getConfigValues() {
		return (configValues);
	}
}