package com.aldorsolutions.fdms.to.common;

public class Ethnicity {
	private String race = "";
	private String ancestry = "";
	private String hispanicOrigin;
	private String citizenship = "";
	private Tribal tribal;
	
	public Ethnicity(){}
	
	
	public String getRace() {
		return race;
	}


	public void setRace(String race) {
		this.race = race;
	}


	public String getAncestry() {
		return ancestry;
	}


	public void setAncestry(String ancestry) {
		this.ancestry = ancestry;
	}


	public String isHispanicOrigin() {
		return hispanicOrigin;
	}


	public void setHispanicOrigin(String hispanicOrigin) {
		this.hispanicOrigin = hispanicOrigin;
	}


	public String getCitizenship() {
		return citizenship;
	}


	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	
	public String getTribalMember() {
		return tribal != null ? tribal.member: "";
	}
	
	public String getTribalName() {
		return tribal != null ? tribal.name: "";
	}
	
	public boolean hasTribal() {
		return tribal != null;
	}

	public void setTribal(String member, String name) {
		this.tribal = new Tribal(member, name);
	}
	
	public void clearTribal() {
		this.tribal = null;
	}

	private class Tribal{
		String member ;
		String name;
		Tribal(String member, String name){
			this.member = member;
			this.name = name;
		}
	}
}
