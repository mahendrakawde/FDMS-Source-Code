package com.aldorsolutions.dashboard.struts.form.chart;

public class PSR1Tab6Form extends ChartCommonForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8699323285507366744L;
	
	private double[] salesTotal;
	private double[] salesBurial;
	private double[] salesCrem;
	
	/**
	 * @return the salesBurial
	 */
	public double[] getSalesBurial() {
		return salesBurial;
	}
	/**
	 * @param salesBurial the salesBurial to set
	 */
	public void setSalesBurial(double[] salesBurial) {
		this.salesBurial = salesBurial;
	}
	/**
	 * @return the salesCrem
	 */
	public double[] getSalesCrem() {
		return salesCrem;
	}
	/**
	 * @param salesCrem the salesCrem to set
	 */
	public void setSalesCrem(double[] salesCrem) {
		this.salesCrem = salesCrem;
	}
	/**
	 * @return the salesTotal
	 */
	public double[] getSalesTotal() {
		return salesTotal;
	}
	/**
	 * @param salesTotal the salesTotal to set
	 */
	public void setSalesTotal(double[] salesTotal) {
		this.salesTotal = salesTotal;
	}
}
