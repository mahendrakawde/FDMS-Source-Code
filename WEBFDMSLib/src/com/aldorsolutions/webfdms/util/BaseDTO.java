package com.aldorsolutions.webfdms.util;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Josh Bosquez
 * 
 */
public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 435373992188015250L;

	private Timestamp createdDate;

	private long createdBy;

	private Timestamp modifiedDate;

	private long modifiedBy;

	public BaseDTO() {
	}

	/**
	 * @param createdDate
	 * @param createdBy
	 * @param modifiedDate
	 * @param modifiedBy
	 */
	public BaseDTO(Timestamp createdDate, long createdBy, Timestamp modifiedDate, long modifiedBy) {
		super();
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return Returns the createdBy.
	 */
	public long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            The createdBy to set.
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return Returns the createdDate.
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            The createdDate to set.
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return Returns the modifiedBy.
	 */
	public long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            The modifiedBy to set.
	 */
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return Returns the modifiedDate.
	 */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            The modifiedDate to set.
	 */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
