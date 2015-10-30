/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.form.acct;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import com.aldorassist.webfdms.model.LocationEmailDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;

/**
 * MyEclipse Struts Creation date: 05-07-2007
 * 
 * XDoclet definition:
 * 
 * @struts.form name="acctLocationEditForm"
 */
public class AcctLocationEditForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3603737332136340239L;

	private long locationID;

	private String locationName;

	private String address1;

	private String address2;

	private String address3;

	private String city;

	private String state;

	private String zipcode;

	private long accountantUserID;

	private ArrayList<UserDTO> accountantUsers;

	private ArrayList<LocationEmailDTO> locationEmails;
	
	private String newEmailAddress;
	
	private long newEmailType;
	
	private String [] locEmailIDs;
	
	private String [] locEmailAddress;
	
	private String [] locEmailType;
	
	private ArrayList <LabelValueBean> emailOptions;
	
	private String externalVendorLimit = "$0.00";
	
	private String internalVendorLimit = "$0.00";
	
	public AcctLocationEditForm () {
		super();
		emailOptions = new ArrayList <LabelValueBean>();
		
		emailOptions.add( new LabelValueBean ("TO:", String.valueOf(LocationEmailDTO.EMAIL_TYPE_TO)) );
		emailOptions.add( new LabelValueBean ("CC:", String.valueOf(LocationEmailDTO.EMAIL_TYPE_CC)) );
		emailOptions.add( new LabelValueBean ("BCC:", String.valueOf(LocationEmailDTO.EMAIL_TYPE_BCC)) );
	}
	
	/*
	 * Generated Methods
	 */

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if ( locationID == 0 ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.int", "Location ID") );
		}
		
		if ( locationName == null ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.required", "Location Name") );
		}
		
		if ( address1 == null ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.required", "Address") );
		}

		if ( city == null ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.required", "City") );
		}

		if ( state == null ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.required", "State") );
		}

		if ( zipcode == null ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.required", "Zipcode") );
		}
		
		if ( accountantUserID == 0 ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.int", "Accountant") );
		}
		
		return errors;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}
	
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @param address3
	 *            the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param locationID
	 *            the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	/**
	 * @param locationName
	 *            the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the accountantUserID
	 */
	public long getAccountantUserID() {
		return accountantUserID;
	}

	/**
	 * @return the accountantUsers
	 */
	public ArrayList<UserDTO> getAccountantUsers() {
		return accountantUsers;
	}

	/**
	 * @param accountantUserID the accountantUserID to set
	 */
	public void setAccountantUserID(long accountantUserID) {
		this.accountantUserID = accountantUserID;
	}

	/**
	 * @param accountantUsers the accountantUsers to set
	 */
	public void setAccountantUsers(ArrayList<UserDTO> accountantUsers) {
		this.accountantUsers = accountantUsers;
	}

	/**
	 * @return the locationEmails
	 */
	public ArrayList<LocationEmailDTO> getLocationEmails() {
		return locationEmails;
	}

	/**
	 * @param locationEmails the locationEmails to set
	 */
	public void setLocationEmails(ArrayList<LocationEmailDTO> locationEmails) {
		this.locationEmails = locationEmails;
	}

	/**
	 * @return the newEmailAddress
	 */
	public String getNewEmailAddress() {
		return newEmailAddress;
	}

	/**
	 * @return the newEmailType
	 */
	public long getNewEmailType() {
		return newEmailType;
	}

	/**
	 * @param newEmailAddress the newEmailAddress to set
	 */
	public void setNewEmailAddress(String newEmailAddress) {
		this.newEmailAddress = newEmailAddress;
	}

	/**
	 * @param newEmailType the newEmailType to set
	 */
	public void setNewEmailType(long newEmailType) {
		this.newEmailType = newEmailType;
	}
	
	public ArrayList <LabelValueBean> getEmailTypeOptionCollection() {
		return ( emailOptions );		
	}

	/**
	 * @return the locEmailAddress
	 */
	public String[] getLocEmailAddress() {
		return locEmailAddress;
	}

	/**
	 * @return the locEmailIDs
	 */
	public String[] getLocEmailIDs() {
		return locEmailIDs;
	}

	/**
	 * @return the locEmailType
	 */
	public String[] getLocEmailType() {
		return locEmailType;
	}

	/**
	 * @param locEmailAddress the locEmailAddress to set
	 */
	public void setLocEmailAddress(String[] locEmailAddress) {
		this.locEmailAddress = locEmailAddress;
	}

	/**
	 * @param locEmailIDs the locEmailIDs to set
	 */
	public void setLocEmailIDs(String[] locEmailIDs) {
		this.locEmailIDs = locEmailIDs;
	}

	/**
	 * @param locEmailType the locEmailType to set
	 */
	public void setLocEmailType(String[] locEmailType) {
		this.locEmailType = locEmailType;
	}

	/**
	 * @return the externalVendorLimit
	 */
	public String getExternalVendorLimit() {
		return externalVendorLimit;
	}

	/**
	 * @return the internalVendorLimit
	 */
	public String getInternalVendorLimit() {
		return internalVendorLimit;
	}

	/**
	 * @param externalVendorLimit the externalVendorLimit to set
	 */
	public void setExternalVendorLimit(String externalVendorLimit) {
		this.externalVendorLimit = externalVendorLimit;
	}

	/**
	 * @param internalVendorLimit the internalVendorLimit to set
	 */
	public void setInternalVendorLimit(String internalVendorLimit) {
		this.internalVendorLimit = internalVendorLimit;
	}
	

	/**
	 * @param externalVendorLimit the externalVendorLimit to set
	 */
	public void setExternalVendorLimit(double externalVendorLimit) {
		
		try { 
			DecimalFormat df = new DecimalFormat ("$#,##0.00");
			this.externalVendorLimit = df.format(externalVendorLimit);
		} catch ( NumberFormatException nfe ) {
			nfe.printStackTrace();
			this.externalVendorLimit = "$0.00";
		}
		
	}

	/**
	 * @param internalVendorLimit the internalVendorLimit to set
	 */
	public void setInternalVendorLimit(double internalVendorLimit) {
		
		try { 
			DecimalFormat df = new DecimalFormat ("$#,##0.00");
			this.internalVendorLimit = df.format(internalVendorLimit);
		} catch ( NumberFormatException nfe ) {
			nfe.printStackTrace();
			this.internalVendorLimit = "$0.00";
		}
	}

	
}