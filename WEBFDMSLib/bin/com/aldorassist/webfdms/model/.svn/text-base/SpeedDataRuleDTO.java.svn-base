package com.aldorassist.webfdms.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Workfile: SpeedDataRuleDTO.java
 * Date: Nov 17, 2005 6:31:41 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class SpeedDataRuleDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6314295295579605079L;
	
	private String tabCategory;
	private ArrayList <String> labels;
	private ArrayList values;
	private String lockForAdmin;
	
	public SpeedDataRuleDTO() { }
	
	public SpeedDataRuleDTO(
			String tabCategory,
			ArrayList <String> labels, 
			String lockForAdmin) {
		
		this.tabCategory = tabCategory;
		this.labels = labels;
		this.lockForAdmin = lockForAdmin;
	}

	public ArrayList <String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList <String> labels) {
		this.labels = labels;
	}

	public String getTabCategory() {
		return tabCategory;
	}

	public void setTabCategory(String tabCategory) {
		this.tabCategory = tabCategory;
	}

	public ArrayList getValues() {
		return values;
	}

	public void setValues(ArrayList values) {
		this.values = values;
	}

	public String getLockForAdmin() {
		return lockForAdmin;
	}

	public void setLockForAdmin(String lockForAdmin) {
		this.lockForAdmin = lockForAdmin;
	}

	
}
