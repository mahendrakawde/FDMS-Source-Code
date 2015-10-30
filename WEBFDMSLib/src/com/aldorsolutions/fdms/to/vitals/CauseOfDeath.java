package com.aldorsolutions.fdms.to.vitals;

import java.util.ArrayList;
import java.util.List;

public class CauseOfDeath {
	
	private List<Cause> causes = new ArrayList<Cause>(5);
	private String tobaccoUser = "";
	private String pregnantAtDeath = "";
	private String otherCondition = "";
	private String typeOfDeath = "";
	private boolean bodyFoundMore24Hr;
	private boolean anAutopsyPerformed;
	private String autopsyFindingsAvailablePriorToCauseOfDeath;
	private String hospiceStatus;
	
	public CauseOfDeath() {}
	
	public List<Cause> getCauses(){
		return causes;
	}
	
	public void addCause(String dueTo, String interval){
		causes.add(new Cause(dueTo, interval));
	}
	
	public void clearCauses(){
		causes.clear();
	}
	
	public String getTobaccoUser() {
		return tobaccoUser;
	}

	public void setTobaccoUser(String tobaccoUser) {
		this.tobaccoUser = tobaccoUser;
	}

	public String getPregnantAtDeath() {
		return pregnantAtDeath;
	}

	public void setPregnantAtDeath(String pregnantAtDeath) {
		this.pregnantAtDeath = pregnantAtDeath;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getTypeOfDeath() {
		return typeOfDeath;
	}

	public void setTypeOfDeath(String typeOfDeath) {
		this.typeOfDeath = typeOfDeath;
	}

	public boolean wasBodyFoundMore24Hr() {
		return bodyFoundMore24Hr;
	}

	public void setBodyFoundMore24Hr(boolean bodyFoundMore24Hr) {
		this.bodyFoundMore24Hr = bodyFoundMore24Hr;
	}

	public boolean wasAnAutopsyPerformed() {
		return anAutopsyPerformed;
	}

	public void setAnAutopsyPerformed(boolean anAutopsyPerformed) {
		this.anAutopsyPerformed = anAutopsyPerformed;
	}

	public String wereAutopsyFindingsAvailablePriorToCauseOfDeath() {
		return autopsyFindingsAvailablePriorToCauseOfDeath;
	}

	public void setAutopsyFindingsAvailablePriorToCauseOfDeath(
			String autopsyFindingsAvailablePriorToCauseOfDeath) {
		this.autopsyFindingsAvailablePriorToCauseOfDeath = autopsyFindingsAvailablePriorToCauseOfDeath;
	}

	public String isHospiceStatus() {
		return hospiceStatus;
	}

	public void setHospiceStatus(String hospiceStatus) {
		this.hospiceStatus = hospiceStatus;
	}
	
	public class Cause{
		String dueTo;
		String interval;
		
		public Cause(String dueTo, String interval) {
			super();
			this.dueTo = dueTo;
			this.interval = interval;
		}

		public String getDueTo() {
			return dueTo;
		}

		public String getInterval() {
			return interval;
		}
		
	}
}
