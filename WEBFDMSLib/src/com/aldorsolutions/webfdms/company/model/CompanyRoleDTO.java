package com.aldorsolutions.webfdms.company.model;

public class CompanyRoleDTO {

	private long companyRoleID = 0;

	private long companyID = 0;

	private long roleID = 0;

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @return the companyRoleID
	 */
	public long getCompanyRoleID() {
		return companyRoleID;
	}

	/**
	 * @return the roleID
	 */
	public long getRoleID() {
		return roleID;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	/**
	 * @param companyRoleID
	 *            the companyRoleID to set
	 */
	public void setCompanyRoleID(long companyRoleID) {
		this.companyRoleID = companyRoleID;
	}

	/**
	 * @param roleID
	 *            the roleID to set
	 */
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

}