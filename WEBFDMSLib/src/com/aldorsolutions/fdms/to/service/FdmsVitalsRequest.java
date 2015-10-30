package com.aldorsolutions.fdms.to.service;

import com.aldorsolutions.fdms.to.common.*;
import com.aldorsolutions.fdms.to.fdmscase.*;
import com.aldorsolutions.fdms.to.vitals.*;

public class FdmsVitalsRequest {
	
	private int vitalsid;
	private String idTagNumber = "";
	private DeceasedPerson deceased;
	private Education decedentsEducation;
	private Ethnicity ethnicity;
	private OccupationalHistory occupationalHistory;
	private Name survivingSpouseName;
	private Person father;
	private Person mother;
	private RelatedPerson informantInfo;
	private DispositionInfo dispositionInfo;
	private CertificationInfo certificationInfo;
	private CauseOfDeath causeOfDeath;
	private MedicalExaminerReport injuryReport;
	
	public FdmsVitalsRequest(){}

	public int getVitalsid() {
		return vitalsid;
	}

	public void setVitalsid(int vitalsid) {
		this.vitalsid = vitalsid;
	}

	public String getIdTagNumber() {
		return idTagNumber;
	}

	public void setIdTagNumber(String idTagNumber) {
		this.idTagNumber = idTagNumber;
	}

	public DeceasedPerson getDeceased() {
		return deceased;
	}

	public void setDeceased(DeceasedPerson deceased) {
		this.deceased = deceased;
	}

	public Education getDecedentsEducation() {
		return decedentsEducation;
	}

	public void setDecedentsEducation(Education decedentsEducation) {
		this.decedentsEducation = decedentsEducation;
	}

	public Ethnicity getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public OccupationalHistory getOccupationalHistory() {
		return occupationalHistory;
	}

	public void setOccupationalHistory(OccupationalHistory occupationalHistory) {
		this.occupationalHistory = occupationalHistory;
	}

	public Name getSurvivingSpouseName() {
		return survivingSpouseName;
	}

	public void setSurvivingSpouseName(Name survivingSpouseName) {
		this.survivingSpouseName = survivingSpouseName;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public RelatedPerson getInformantInfo() {
		return informantInfo;
	}

	public void setInformantInfo(RelatedPerson informantInfo) {
		this.informantInfo = informantInfo;
	}

	public DispositionInfo getDispositionInfo() {
		return dispositionInfo;
	}

	public void setDispositionInfo(DispositionInfo dispositionInfo) {
		this.dispositionInfo = dispositionInfo;
	}

	public CertificationInfo getCertificationInfo() {
		return certificationInfo;
	}

	public void setCertificationInfo(CertificationInfo certificationInfo) {
		this.certificationInfo = certificationInfo;
	}

	public CauseOfDeath getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(CauseOfDeath causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public MedicalExaminerReport getInjuryReport() {
		return injuryReport;
	}

	public void setInjuryReport(MedicalExaminerReport injuryReport) {
		this.injuryReport = injuryReport;
	}
	
}
