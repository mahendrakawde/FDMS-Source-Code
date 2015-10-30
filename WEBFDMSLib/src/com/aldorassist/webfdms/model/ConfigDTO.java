package com.aldorassist.webfdms.model;

/**
 * 
 * @author David Rollins
 * 
 */
public class ConfigDTO {

	private long configID = 0;

	private String name;
	
	private boolean inactive = false;

	/**
	 * @return the configID
	 */
	public long getConfigID() {
		return configID;
	}

	/**
	 * @param configID
	 *            the configID to set
	 */
	public void setConfigID(long configID) {
		this.configID = configID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the inactive
	 */
	public boolean isInactive() {
		return inactive;
	}

	/**
	 * @param inactive the inactive to set
	 */
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

}