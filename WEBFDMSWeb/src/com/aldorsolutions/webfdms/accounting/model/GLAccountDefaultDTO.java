package com.aldorsolutions.webfdms.accounting.model;

public class GLAccountDefaultDTO {

	private int glAcctID;

	private String deleteCode;

	private int locationID;

	private String category;

	private String disposition;

	private String saleType;

	private String revenueAcct;

	private String invAssetAcct;

	private String invCogsAcct;

	private int locale;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the deleteCode
	 */
	public String getDeleteCode() {
		return deleteCode;
	}

	/**
	 * @param deleteCode
	 *            the deleteCode to set
	 */
	public void setDeleteCode(String deleteCode) {
		this.deleteCode = deleteCode;
	}

	/**
	 * @return the disposition
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * @param disposition
	 *            the disposition to set
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	/**
	 * @return the invAssetAcct
	 */
	public String getInvAssetAcct() {
		return invAssetAcct;
	}

	/**
	 * @param invAssetAcct
	 *            the invAssetAcct to set
	 */
	public void setInvAssetAcct(String invAssetAcct) {
		this.invAssetAcct = invAssetAcct;
	}

	/**
	 * @return the invCogsAcct
	 */
	public String getInvCogsAcct() {
		return invCogsAcct;
	}

	/**
	 * @param invCogsAcct
	 *            the invCogsAcct to set
	 */
	public void setInvCogsAcct(String invCogsAcct) {
		this.invCogsAcct = invCogsAcct;
	}

	/**
	 * @return the locale
	 */
	public int getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(int locale) {
		this.locale = locale;
	}

	/**
	 * @return the locationID
	 */
	public int getLocationID() {
		return locationID;
	}

	/**
	 * @param locationID
	 *            the locationID to set
	 */
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	/**
	 * @return the revenueAcct
	 */
	public String getRevenueAcct() {
		return revenueAcct;
	}

	/**
	 * @param revenueAcct
	 *            the revenueAcct to set
	 */
	public void setRevenueAcct(String revenueAcct) {
		this.revenueAcct = revenueAcct;
	}

	/**
	 * @return the saleType
	 */
	public String getSaleType() {
		return saleType;
	}

	/**
	 * @param saleType
	 *            the saleType to set
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	/**
	 * @return the glAcctID
	 */
	public int getGlAcctID() {
		return glAcctID;
	}

	/**
	 * @param glAcctID
	 *            the glAcctID to set
	 */
	public void setGlAcctID(int glAcctID) {
		this.glAcctID = glAcctID;
	}

}
