package com.aldorsolutions.fdms.to.fdmscase;

import java.util.Date;

import com.aldorsolutions.fdms.to.common.FdmsLocation;

public class FirstCall {
	
	private Date arrangeDate;
	private String arrangeTime = "";
	private Date originalPreneedDate;
	private FdmsLocation funeralHome;
	private FdmsLocation facility;
	private String source = "";
	private String embalming = "";
	
	public FirstCall(){}

	public Date getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(Date arrangeDate) {
		this.arrangeDate = arrangeDate;
	}

	public String getArrangeTime() {
		return arrangeTime;
	}

	public void setArrangeTime(String arrangeTime) {
		this.arrangeTime = arrangeTime;
	}

	public Date getOriginalPreneedDate() {
		return originalPreneedDate;
	}

	public void setOriginalPreneedDate(Date originalPreneedDate) {
		this.originalPreneedDate = originalPreneedDate;
	}

	public FdmsLocation getFuneralHome() {
		return funeralHome;
	}

	public void setFuneralHome(FdmsLocation funeralHome) {
		this.funeralHome = funeralHome;
	}

	public FdmsLocation getFacility() {
		return facility;
	}

	public void setFacility(FdmsLocation facility) {
		this.facility = facility;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEmbalming() {
		return embalming;
	}

	public void setEmbalming(String embalming) {
		this.embalming = embalming;
	}
	
	
}
