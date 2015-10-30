package com.aldorsolutions.webfdms.ums.model;

public class OperationsDTO {
	
	private long operationID;

	private String name;

	private String token;
	
	private long resourceID;

	private boolean status;

	/**
	 * 
	 */
	public OperationsDTO() {
		super();
	}

	/**
	 * @param operationID
	 * @param name
	 * @param token
	 * @param resourceID
	 * @param status
	 */
	public OperationsDTO(long operationID, String name, String token, long resourceID, boolean status) {
		super();
		this.operationID = operationID;
		this.name = name;
		this.token = token;
		this.resourceID = resourceID;
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the operationID
	 */
	public long getOperationID() {
		return operationID;
	}

	/**
	 * @return the resourceID
	 */
	public long getResourceID() {
		return resourceID;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param operationID the operationID to set
	 */
	public void setOperationID(long operationID) {
		this.operationID = operationID;
	}

	/**
	 * @param resourceID the resourceID to set
	 */
	public void setResourceID(long resourceID) {
		this.resourceID = resourceID;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	

}