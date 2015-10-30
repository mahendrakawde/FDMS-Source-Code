package com.aldorsolutions.webfdms.ums.model;

public class RolesOperationsDTO {
	
	private long roleID;
	
	private long operationID;

	/**
	 * 
	 */
	public RolesOperationsDTO() {
		super();
	}
	
	/**
	 * @param roleID
	 * @param operationID
	 */
	public RolesOperationsDTO(long roleID, long operationID) {
		super();
		this.roleID = roleID;
		this.operationID = operationID;
	}

	/**
	 * @return the operationID
	 */
	public long getOperationID() {
		return operationID;
	}

	/**
	 * @return the roleID
	 */
	public long getRoleID() {
		return roleID;
	}

	/**
	 * @param operationID the operationID to set
	 */
	public void setOperationID(long operationID) {
		this.operationID = operationID;
	}

	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	
}