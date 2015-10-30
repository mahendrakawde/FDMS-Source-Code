package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class FreeTrialRegistrationBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -2625464088299687338L;
private int y;
  private int x;
  private String submitButton;
  private String firstName;
  private String lastName;
  private String userEmail;
  private String userLogin;
  private String userPassword2;
  private String userPassword1;

  private String funeralHomeName;
  private String addr1;
  private String addr2;
  private String addr3;
  private String city;
  private String state;
  private String zipCode;
  private String phoneNumber;
  private String otherPhone;
  private String licenseNumber;

  private String initials;
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:08 PM)
 * @return java.lang.String
 */
public java.lang.String getAddr1() {
	return addr1;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:19 PM)
 * @return java.lang.String
 */
public java.lang.String getAddr2() {
	return addr2;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:29 PM)
 * @return java.lang.String
 */
public java.lang.String getAddr3() {
	return addr3;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:40 PM)
 * @return java.lang.String
 */
public java.lang.String getCity() {
	return city;
}
/**
 * Insert the method's description here.
 * Creation date: (8/28/2002 4:49:49 PM)
 * @return java.lang.String
 */
public java.lang.String getFirstName() {
	return firstName;
}
/**
 * Insert the method's description here.
 * Creation date: (8/28/2002 4:50:24 PM)
 * @return java.lang.String
 */
public java.lang.String getFuneralHomeName() {
	return funeralHomeName;
}
/**
 * Insert the method's description here.
 * Creation date: (8/30/2002 2:48:09 PM)
 * @return java.lang.String
 */
public java.lang.String getInitials() {
	return initials;
}
/**
 * Insert the method's description here.
 * Creation date: (8/28/2002 4:50:06 PM)
 * @return java.lang.String
 */
public java.lang.String getLastName() {
	return lastName;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:12:44 PM)
 * @return java.lang.String
 */
public java.lang.String getLicenseNumber() {
	return licenseNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String getOtherPhone() {
	return otherPhone;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:09:12 PM)
 * @return java.lang.String
 */
public java.lang.String getPhoneNumber() {
	return phoneNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:51 PM)
 * @return java.lang.String
 */
public java.lang.String getState() {
	return state;
}
  public String getSubmitButton () {
	return submitButton ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 2:05:37 PM)
 * @return java.lang.String
 */
public java.lang.String getUserEmail() {
	return userEmail;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 12:47:14 PM)
 * @return java.lang.String
 */
public java.lang.String getUserLogin() {
	return userLogin;
}
  public String getUserName () {
	if (firstName != null) {
	   return firstName.trim() +" " +lastName;
	} else {
	   return lastName;
	} 
  }  
  public String getUserPassword1 () {
	return userPassword1 ;
  }  
  public String getUserPassword2 () {
	return userPassword2 ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:09:02 PM)
 * @return java.lang.String
 */
public java.lang.String getZipCode() {
	return zipCode;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:08 PM)
 * @param newAddr1 java.lang.String
 */
public void setAddr1(java.lang.String newAddr1) {
	addr1 = newAddr1;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:19 PM)
 * @param newAddr2 java.lang.String
 */
public void setAddr2(java.lang.String newAddr2) {
	addr2 = newAddr2;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:29 PM)
 * @param newAddr3 java.lang.String
 */
public void setAddr3(java.lang.String newAddr3) {
	addr3 = newAddr3;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:40 PM)
 * @param newCity java.lang.String
 */
public void setCity(java.lang.String newCity) {
	city = newCity;
}
/**
 * Insert the method's description here.
 * Creation date: (8/28/2002 4:49:49 PM)
 * @param newFirstName java.lang.String
 */
public void setFirstName(java.lang.String newFirstName) {
	firstName = newFirstName;
}
/**
 * Insert the method's description here.
 * Creation date: (8/28/2002 4:50:24 PM)
 * @param newFuneralHomeName java.lang.String
 */
public void setFuneralHomeName(java.lang.String newFuneralHomeName) {
	funeralHomeName = newFuneralHomeName;
}
/**
 * Insert the method's description here.
 * Creation date: (8/30/2002 2:48:09 PM)
 * @param newInitials java.lang.String
 */
public void setInitials(java.lang.String newInitials) {
	initials = newInitials;
}
/**
 * Insert the method's description here.
 * Creation date: (8/28/2002 4:50:06 PM)
 * @param newLastName java.lang.String
 */
public void setLastName(java.lang.String newLastName) {
	lastName = newLastName;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:12:44 PM)
 * @param newLicenseNumber java.lang.String
 */
public void setLicenseNumber(java.lang.String newLicenseNumber) {
	licenseNumber = newLicenseNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:09:22 PM)
 * @param newOtherPhone java.lang.String
 */
public void setOtherPhone(java.lang.String newOtherPhone) {
	otherPhone = newOtherPhone;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:09:12 PM)
 * @param newPhoneNumber java.lang.String
 */
public void setPhoneNumber(java.lang.String newPhoneNumber) {
	phoneNumber = newPhoneNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:08:51 PM)
 * @param newState java.lang.String
 */
public void setState(java.lang.String newState) {
	state = newState;
}
  public void setSubmitButton (String in) {
	submitButton = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 2:05:37 PM)
 * @param newUserEmail java.lang.String
 */
public void setUserEmail(java.lang.String newUserEmail) {
	userEmail = newUserEmail;
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 12:47:14 PM)
 * @param newUserLogin java.lang.String
 */
public void setUserLogin(java.lang.String newUserLogin) {
	userLogin = newUserLogin;
}
  public void setUserPassword1 (String in) {
	userPassword1 = in;
  }  
  public void setUserPassword2 (String in) {
	userPassword2 = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/29/2002 1:09:02 PM)
 * @param newZipCode java.lang.String
 */
public void setZipCode(java.lang.String newZipCode) {
	zipCode = newZipCode;
}
}
