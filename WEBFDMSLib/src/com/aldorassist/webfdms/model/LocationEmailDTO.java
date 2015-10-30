/**
 * 
 */
package com.aldorassist.webfdms.model;

/**
 * @author David Rollins 
 * Date: May 8, 2007 
 * File: LocationEmailDTO.java
 * 
 */
public class LocationEmailDTO {

	private long locationEmailID = 0;

	private long companyID = 0;

	private long locationID = 0;
	
	private long localeID = 0;

	private long emailType = 0;
	
	private String emailAddress;
	
	public static long EMAIL_TYPE_TO = 1;
	public static long EMAIL_TYPE_CC = 2;
	public static long EMAIL_TYPE_BCC = 3;

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the emailType
	 */
	public long getEmailType() {
		return emailType;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}

	/**
	 * @return the locationEmailID
	 */
	public long getLocationEmailID() {
		return locationEmailID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @param emailType the emailType to set
	 */
	public void setEmailType(long emailType) {
		this.emailType = emailType;
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	/**
	 * @param locationEmailID the locationEmailID to set
	 */
	public void setLocationEmailID(long locationEmailID) {
		this.locationEmailID = locationEmailID;
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

	public String getEmailTypeDesc() {
		if ( emailType == LocationEmailDTO.EMAIL_TYPE_TO ) {
			return ( "TO:" );
		} 
		else if ( emailType == LocationEmailDTO.EMAIL_TYPE_CC ) {
			return ( "CC:" );
		}
		else if ( emailType == LocationEmailDTO.EMAIL_TYPE_BCC ) {
			return ( "BCC:" );
		}
		else {
			return ( "" );
		}
	}
}
