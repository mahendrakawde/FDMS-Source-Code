package com.aldorsolutions.dashboard.struts.form.chart;

import java.util.Calendar;

import org.apache.struts.action.ActionForm;

public class ChartCommonForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431426046641455733L;
	
	private String fromDate;
	private String toDate;
	private long localeID;
	
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the localeID
	 */
	public long getLocaleID() {
		return localeID;
	}

	/**
	 * @param localeID the localeID to set
	 */
	public void setLocaleID(long localeID) {
		this.localeID = localeID;
	}

	/**
	 * @return the currentYear
	 */
	public String getCurrentYear() {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		return Integer.toString(year);
	}

	/**
	 * @return the lastYear
	 */
	public String getLastYear() {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR)-1;
		return Integer.toString(year);
	}

}
