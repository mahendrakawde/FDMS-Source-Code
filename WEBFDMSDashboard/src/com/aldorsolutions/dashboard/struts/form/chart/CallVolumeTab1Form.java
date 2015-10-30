/**
 * 
 */
package com.aldorsolutions.dashboard.struts.form.chart;

import com.aldorsolutions.dashboard.utils.FDMSChartFX;

/**
 * @author Bhavesh
 *
 */
public class CallVolumeTab1Form extends BurialCremationTab2Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2268097490501633205L;
	
	private String saleType;

	
	private FDMSChartFX callVolumeChart = new FDMSChartFX();
	private FDMSChartFX totalRevenuChart = new FDMSChartFX();
	private FDMSChartFX averageRevenuChart = new FDMSChartFX();
	
	
	/**
	 * @return the callVolumeChart
	 */
	public FDMSChartFX getCallVolumeChart() {
		return callVolumeChart;
	}

	/**
	 * @param callVolumeChart the callVolumeChart to set
	 */
	public void setCallVolumeChart(FDMSChartFX callVolumeChart) {
		this.callVolumeChart = callVolumeChart;
	}

	/**
	 * @return the totalRevenuChart
	 */
	public FDMSChartFX getTotalRevenuChart() {
		return totalRevenuChart;
	}

	/**
	 * @param totalRevenuChart the totalRevenuChart to set
	 */
	public void setTotalRevenuChart(FDMSChartFX totalRevenuChart) {
		this.totalRevenuChart = totalRevenuChart;
	}

	/**
	 * @return the averageRevenuChart
	 */
	public FDMSChartFX getAverageRevenuChart() {
		return averageRevenuChart;
	}

	/**
	 * @param averageRevenuChart the averageRevenuChart to set
	 */
	public void setAverageRevenuChart(FDMSChartFX averageRevenuChart) {
		this.averageRevenuChart = averageRevenuChart;
	}

	/**
	 * @return the saleType
	 */
	public String getSaleType() {
		return saleType;
	}

	/**
	 * @param saleType the saleType to set
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
}
