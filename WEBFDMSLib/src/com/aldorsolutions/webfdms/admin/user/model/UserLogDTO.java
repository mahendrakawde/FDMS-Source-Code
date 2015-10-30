/*
 * UseLogDTO.java
 *
 * Created on February 4, 2005, 8:54 PM
 */

package com.aldorsolutions.webfdms.admin.user.model;

/**
 * 
 * @author David Rollins
 */

import java.io.Serializable;

public class UserLogDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5209220611984118898L;

	private String username;

	private int userId;
	
	private int regionId;

	private long time;

	private String ip;

	private String browser;

	private String userAgent;

	private String valid;

	/**
	 * @return Returns the browser.
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser
	 *            The browser to set.
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * @return Returns the ip.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            The ip to set.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return Returns the regionId.
	 */
	public int getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId
	 *            The regionId to set.
	 */
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return Returns the time.
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            The time to set.
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return Returns the userAgent.
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent
	 *            The userAgent to set.
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return Returns the userId.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Returns the valid.
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            The valid to set.
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}

}
