package com.aldorsolutions.fdms.to.common;

import java.util.ArrayList;
import java.util.List;

public class RelatedPerson extends Person {
	private String relation = "";
	private String cellPhone = "";
	private List<String> phones = new ArrayList<String> ();
	private String email = "";
	private boolean billToParty;
	
	public RelatedPerson() {}
	
	private static RelatedPerson defaultRelatedPerson = new RelatedPerson();
	public static RelatedPerson getDefault(){
		return defaultRelatedPerson;
	}
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public void addPhone(String phone){
		if(phone != null){
			phones.add(phone);
		}
	}
	public boolean isBillToParty() {
		return billToParty;
	}
	public void setBillToParty(boolean billToParty) {
		this.billToParty = billToParty;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
