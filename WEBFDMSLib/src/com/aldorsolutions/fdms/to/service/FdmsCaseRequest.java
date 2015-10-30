package com.aldorsolutions.fdms.to.service;

import com.aldorsolutions.fdms.to.fdmscase.*;

public class FdmsCaseRequest {
	
	private int localeId;
	private int locationId;
	private int directorId;
	private int vitalsId;
	
	private CaseInfo caseInfo ;
	
    
	public int getVitalsId() {
		return vitalsId;
	}

	public void setVitalsId(int vitalsId) {
		this.vitalsId = vitalsId;
	}

	public int getLocaleId() {
		return localeId;
	}
	
	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public CaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}
	
}
