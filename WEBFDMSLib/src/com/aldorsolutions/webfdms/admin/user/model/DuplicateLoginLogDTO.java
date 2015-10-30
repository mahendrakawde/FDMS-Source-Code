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

public class DuplicateLoginLogDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5209220611984118898L;

	private int logID;
	
	private String userName;

	private int userID;

	private long loginTime;


	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the logID
	 */
	public int getLogID() {
		return logID;
	}

	/**
	 * @param logID the logID to set
	 */
	public void setLogID(int logID) {
		this.logID = logID;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the loginTime
	 */
	public long getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	

}
