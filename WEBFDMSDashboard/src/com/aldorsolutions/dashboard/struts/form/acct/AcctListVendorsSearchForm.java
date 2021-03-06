/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 * CJongs
 */
package com.aldorsolutions.dashboard.struts.form.acct;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;

/** 
 * MyEclipse Struts
 * Creation date: 02-16-09
 * 
 * XDoclet definition:
 * @struts.form name="acctListVendorsForm"
 */
public class AcctListVendorsSearchForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	private String vendorName;
	private long locationID;
	private long localeID;
	private String includeInactive;
	private String includeNoLocation;
	private String vendorCode;
	private ArrayList <ApVendorDTO> vendors = new ArrayList <ApVendorDTO>();
	private String direction;
	private boolean exactCode;
	private boolean exactName;


	public boolean isExactName() {
		return exactName;
	}

	public void setExactName(boolean exactName) {
		this.exactName = exactName;
	}

	public boolean isExactCode() {
		return exactCode;
	}

	public void setExactCode(boolean exactCode) {
		this.exactCode = exactCode;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	/**
	 * @return the vendors
	 */
	public ArrayList<ApVendorDTO> getVendors() {
		return vendors;
	}

	/**
	 * @param vendors the vendors to set
	 */
	public void setVendors(ArrayList<ApVendorDTO> vendors) {
		this.vendors = vendors;
	}

	/**
	 * @return the includeInactive
	 */
	public String getIncludeInactive() {
		return includeInactive;
	}

	/**
	 * @param includeInactive the includeInactive to set
	 */
	public void setIncludeInactive(String includeInactive) {
		this.includeInactive = includeInactive;
	}

	/**
	 * @return the includeNoLocation
	 */
	public String getIncludeNoLocation() {
		return includeNoLocation;
	}

	/**
	 * @param includeNoLocation the includeNoLocation to set
	 */
	public void setIncludeNoLocation(String includeNoLocation) {
		this.includeNoLocation = includeNoLocation;
	}

	/**
	 * @return the localeID
	 */
	public long getLocaleID() {
		return localeID;
	}

	/**
	 * @param localeID the localeID to set
	 */
	public void setLocaleID(long localeID) {
		this.localeID = localeID;
	}

	/**
	 * @return the vendorCode
	 */
	public String getVendorCode() {
		return vendorCode;
	}

	/**
	 * @param vendorCode the vendorCode to set
	 */
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}



	
	
	
}