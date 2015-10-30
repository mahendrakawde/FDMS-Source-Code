package com.aldorassist.webfdms.model;

/**
 * 
 * @author David Rollins
 *
 */
public class ConfigValueDTO {
	
	private long configValueID = 0;
	
	private long configID = 0;
	
	private String configCode;
	
	private String value;

	/**
	 * @return the configCode
	 */
	public String getConfigCode() {
		return configCode;
	}

	/**
	 * @param configCode the configCode to set
	 */
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	/**
	 * @return the configID
	 */
	public long getConfigID() {
		return configID;
	}

	/**
	 * @param configID the configID to set
	 */
	public void setConfigID(long configID) {
		this.configID = configID;
	}

	/**
	 * @return the configValueID
	 */
	public long getConfigValueID() {
		return configValueID;
	}

	/**
	 * @param configValueID the configValueID to set
	 */
	public void setConfigValueID(long configValueID) {
		this.configValueID = configValueID;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}