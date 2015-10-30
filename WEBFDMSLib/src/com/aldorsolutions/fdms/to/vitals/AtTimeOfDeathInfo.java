package com.aldorsolutions.fdms.to.vitals;

import java.util.Date;

public class AtTimeOfDeathInfo {
	
	private String actualOrPresumedTimeOfDeath = "";
	private Date pronouncedDeadOn ;
	private String timePronounced	= "";
	private boolean medicalExaminerContacted ;
	private String placeOfDeath = "";
	private HospitalDetails hospitalDetails;
	
	public AtTimeOfDeathInfo() {}
	
	
	public String getActualOrPresumedTimeOfDeath() {
		return actualOrPresumedTimeOfDeath;
	}


	public void setActualOrPresumedTimeOfDeath(String actualOrPresumedTimeOfDeath) {
		this.actualOrPresumedTimeOfDeath = actualOrPresumedTimeOfDeath;
	}


	public Date getPronouncedDeadOn() {
		return pronouncedDeadOn;
	}


	public void setPronouncedDeadOn(Date pronouncedDeadOn) {
		this.pronouncedDeadOn = pronouncedDeadOn;
	}


	public String getTimePronounced() {
		return timePronounced;
	}


	public void setTimePronounced(String timePronounced) {
		this.timePronounced = timePronounced;
	}


	public boolean isMedicalExaminerContacted() {
		return medicalExaminerContacted;
	}


	public void setMedicalExaminerContacted(boolean medicalExaminerContacted) {
		this.medicalExaminerContacted = medicalExaminerContacted;
	}

	public String getPlaceOfDeath() {
		return placeOfDeath;
	}
	
	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}
	
	public boolean hasHospitalDetails() {
		return hospitalDetails != null;
	}

	public void setHospitalDetails(String hospitalAccessType, String caseNumber, String attendingPhysician) {
		
		this.hospitalDetails = new HospitalDetails(hospitalAccessType, caseNumber, attendingPhysician);
	}
	
	public void clearHospitalDetails() {
		
		this.hospitalDetails = null;
	}
	
	public String getHospitalAccessType(){
		return hospitalDetails != null ? hospitalDetails.hospitalAccessType : "";
	}

	public String getCaseNumber() {
		return hospitalDetails != null ? hospitalDetails.caseNumber : "";
	}

	public String getAttendingPhysician() {
		return hospitalDetails != null ? hospitalDetails.attendingPhysician : "";
	}

	private class HospitalDetails {
		String hospitalAccessType;
		String caseNumber;
		String attendingPhysician;
		
		HospitalDetails(String hospitalAccessType, String caseNumber,
				String attendingPhysician) {
			super();
			this.hospitalAccessType = hospitalAccessType;
			this.caseNumber = caseNumber;
			this.attendingPhysician = attendingPhysician;
		}
		
	}
}
