/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package fdms.ui.struts.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-28-2007
 * 
 * XDoclet definition:
 * @struts.form name="vendorLoctEditForm"
 */
public class VendorLoctEditForm extends ActionForm {

	private long vendorLocationID;
	
	private long defaultAcct;
	
	private long vendorID;
	
	private long locationID;
	
	private long localeID;
	
	private String submitType;
	
	private String localeMapJavascript;
	
	private String direction;
	
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the defaultAcct
	 */
	public long getDefaultAcct() {
		return defaultAcct;
	}

	/**
	 * @return the localeID
	 */
	public long getLocaleID() {
		return localeID;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}

	/**
	 * @return the vendorID
	 */
	public long getVendorID() {
		return vendorID;
	}

	/**
	 * @return the vendorLocationID
	 */
	public long getVendorLocationID() {
		return vendorLocationID;
	}

	/**
	 * @param defaultAcct the defaultAcct to set
	 */
	public void setDefaultAcct(long defaultAcct) {
		this.defaultAcct = defaultAcct;
	}

	/**
	 * @param localeID the localeID to set
	 */
	public void setLocaleID(long localeID) {
		this.localeID = localeID;
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	/**
	 * @param vendorID the vendorID to set
	 */
	public void setVendorID(long vendorID) {
		this.vendorID = vendorID;
	}

	/**
	 * @param vendorLocationID the vendorLocationID to set
	 */
	public void setVendorLocationID(long vendorLocationID) {
		this.vendorLocationID = vendorLocationID;
	}
	
	/**
	 * @return the submitType
	 */
	public String getSubmitType() {
		return submitType;
	}

	/**
	 * @param submitType the submitType to set
	 */
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	
	/**
	 * @return the localeMapJavascript
	 */
	public String getLocaleMapJavascript() {
		return localeMapJavascript;
	}

	/**
	 * @param localeMapJavascript the localeMapJavascript to set
	 */
	public void setLocaleMapJavascript(String localeMapJavascript) {
		this.localeMapJavascript = localeMapJavascript;
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
}