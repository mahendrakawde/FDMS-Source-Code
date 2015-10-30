package com.aldorassist.webfdms.model;

import java.sql.Timestamp;

import com.aldorsolutions.webfdms.util.BaseDTO;

public class AuditDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -932407747502994903L;
	
	private int companyId;
	private int localeId;
	private int userId;
	private String message;

	public AuditDTO() {}
	
	public AuditDTO(Timestamp createdDate, long createdBy, Timestamp modifiedDate, long modifiedBy, String message) {
		super(createdDate, createdBy, modifiedDate, modifiedBy);
		this.message = message;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
