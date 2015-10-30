package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

public class DbColumn {
	private static Logger logger = Logger.getLogger(DbColumn.class.getName());
	
	protected boolean required;
	protected String datatype;
	protected int datasize;
	protected int digits;
	protected String name;
	
	public DbColumn(String columnName, String datatype, int datasize, int digits, boolean nullable) {
		this.name = columnName;
		this.datatype = datatype;
		this.datasize = datasize;
		this.digits = digits;
		this.required = !nullable;
	}
	public DbColumn ( String aName, String aDatatype, boolean isRequired) {
		this.name = aName;
		this.datatype = aDatatype;
		this.required = isRequired;
	}
	/**
	 * @return the datasize
	 */
	public int getDatasize() {
		return datasize;
	}
	/**
	 * @param datasize the datasize to set
	 */
	public void setDatasize(int datasize) {
		this.datasize = datasize;
	}
	/**
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}
	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	/**
	 * @return the digits
	 */
	public int getDigits() {
		return digits;
	}
	/**
	 * @param digits the digits to set
	 */
	public void setDigits(int digits) {
		this.digits = digits;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
}
