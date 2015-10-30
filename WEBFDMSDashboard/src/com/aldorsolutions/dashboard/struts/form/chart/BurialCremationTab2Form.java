package com.aldorsolutions.dashboard.struts.form.chart;


public class BurialCremationTab2Form extends ChartCommonForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8235953196516521071L;
	
	private double[] CBTCallVolume;
	private double[] CBTRevnueVolume;
	private double[] CBTAverageVolume;

	
	private int maxSize;

	/**
	 * @return the cBTCallVolume
	 */
	public double[] getCBTCallVolume() {
		return CBTCallVolume;
	}

	/**
	 * @param cBTCallVolume the cBTCallVolume to set
	 */
	public void setCBTCallVolume(double[] cBTCallVolume) {
		CBTCallVolume = cBTCallVolume;
	}

	/**
	 * @return the cBTRevnueVolume
	 */
	public double[] getCBTRevnueVolume() {
		return CBTRevnueVolume;
	}

	/**
	 * @param cBTRevnueVolume the cBTRevnueVolume to set
	 */
	public void setCBTRevnueVolume(double[] cBTRevnueVolume) {
		CBTRevnueVolume = cBTRevnueVolume;
	}

	/**
	 * @return the cBTAverageVolume
	 */
	public double[] getCBTAverageVolume() {
		return CBTAverageVolume;
	}

	/**
	 * @param cBTAverageVolume the cBTAverageVolume to set
	 */
	public void setCBTAverageVolume(double[] cBTAverageVolume) {
		CBTAverageVolume = cBTAverageVolume;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public double getMaxValue(double currentValue,double pastValue, double raiseBy){
		double maxValue=0.0;
		try{
			if(currentValue<pastValue){
				maxValue=  pastValue+ pastValue*raiseBy;
			}else{
				maxValue=  currentValue+ currentValue*raiseBy;
			}
		}catch(Exception ex){
			return maxValue;
		}
		return Math.ceil(maxValue);
	}
}
