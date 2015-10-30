package com.aldorsolutions.fdms.to.vitals;

import java.util.Date;

public class CertificationInfo {
	
	public enum CertifierType { PHYSICIAN, MEDICALEXAMINER, NURSEPRACTITIONER }
	
	private boolean notificationOfExaminerRequired;
	private CertifierType certifierType;
	private Date certifierDateSigned;
	private String licenseNumber = "";
	private AtTimeOfDeathInfo atTimeOfDeathInfo;
	private CertifyingPhysician certifyingPhysician;
	private Date registrarFileDate;
	
	public CertificationInfo() {}
	
	
	public boolean isNotificationOfExaminerRequired() {
		return notificationOfExaminerRequired;
	}


	public void setNotificationOfExaminerRequired(
			boolean notificationOfExaminerRequired) {
		this.notificationOfExaminerRequired = notificationOfExaminerRequired;
	}
	
	public boolean hasCertifierType() {
		return certifierType != null;
	}
	
	public CertifierType getCertifierType() {
		return certifierType;
	}

	public void setCertifierType(CertifierType certifierType) {
		this.certifierType = certifierType;
	}


	public Date getCertifierDateSigned() {
		return certifierDateSigned;
	}


	public void setCertifierDateSigned(Date certifierDateSigned) {
		this.certifierDateSigned = certifierDateSigned;
	}


	public String getLicenseNumber() {
		return licenseNumber;
	}


	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public AtTimeOfDeathInfo getAtTimeOfDeathInfo() {
		return atTimeOfDeathInfo;
	}


	public void setAtTimeOfDeathInfo(AtTimeOfDeathInfo atTimeOfDeathInfo) {
		this.atTimeOfDeathInfo = atTimeOfDeathInfo;
	}


	public CertifyingPhysician getCertifyingPhysician() {
		return certifyingPhysician;
	}


	public void setCertifyingPhysician(CertifyingPhysician certifyingPhysician) {
		this.certifyingPhysician = certifyingPhysician;
	}


	public Date getRegistrarFileDate() {
		return registrarFileDate;
	}


	public void setRegistrarFileDate(Date registrarFileDate) {
		this.registrarFileDate = registrarFileDate;
	}
	
}
