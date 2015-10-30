package com.aldorsolutions.dashboard.struts.form.chart;

import org.apache.struts.action.ActionForm;

public class LocaleTab5Form extends BurialCremationTab2Form {
		
	private double[] receivable; 
	private double[] arCurrentReceivables;
	private double[] arTotalRevenue;
	
	
	// set data from callvolume tab to locale tab
	private double currentYearBurialAvg;
	private double lastYearBurialAvg;
	private double currentYearCremAvg;
	private double lastYearCremAvg;
	
	
	/**
	 * @return the receivable
	 */
	public double[] getReceivable() {
		return receivable;
	}
	/**
	 * @param receivable the receivable to set
	 */
	public void setReceivable(double[] receivable) {
		this.receivable = receivable;
	}
	
	
	
	/**
	 * @return the arCurrentReceivables
	 */
	public double[] getArCurrentReceivables() {
		return arCurrentReceivables;
	}
	/**
	 * @param arCurrentReceivables the arCurrentReceivables to set
	 */
	public void setArCurrentReceivables(double[] arCurrentReceivables) {
		this.arCurrentReceivables = arCurrentReceivables;
	}
	/**
	 * @return the arTotalRevenue
	 */
	public double[] getArTotalRevenue() {
		return arTotalRevenue;
	}
	/**
	 * @param arTotalRevenue the arTotalRevenue to set
	 */
	public void setArTotalRevenue(double[] arTotalRevenue) {
		this.arTotalRevenue = arTotalRevenue;
	}
	/**
	 * @return the currentYearBurialAvg
	 */
	public double getCurrentYearBurialAvg() {
		return currentYearBurialAvg;
	}
	/**
	 * @param currentYearBurialAvg the currentYearBurialAvg to set
	 */
	public void setCurrentYearBurialAvg(double currentYearBurialAvg) {
		this.currentYearBurialAvg = currentYearBurialAvg;
	}
	/**
	 * @return the lastYearBurialAvg
	 */
	public double getLastYearBurialAvg() {
		return lastYearBurialAvg;
	}
	/**
	 * @param lastYearBurialAvg the lastYearBurialAvg to set
	 */
	public void setLastYearBurialAvg(double lastYearBurialAvg) {
		this.lastYearBurialAvg = lastYearBurialAvg;
	}
	/**
	 * @return the currentYearCremAvg
	 */
	public double getCurrentYearCremAvg() {
		return currentYearCremAvg;
	}
	/**
	 * @param currentYearCremAvg the currentYearCremAvg to set
	 */
	public void setCurrentYearCremAvg(double currentYearCremAvg) {
		this.currentYearCremAvg = currentYearCremAvg;
	}
	/**
	 * @return the lastYearCremAvg
	 */
	public double getLastYearCremAvg() {
		return lastYearCremAvg;
	}
	/**
	 * @param lastYearCremAvg the lastYearCremAvg to set
	 */
	public void setLastYearCremAvg(double lastYearCremAvg) {
		this.lastYearCremAvg = lastYearCremAvg;
	}

	
	

}
