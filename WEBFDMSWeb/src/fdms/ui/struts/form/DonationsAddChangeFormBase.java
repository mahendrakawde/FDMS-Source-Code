package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class DonationsAddChangeFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 3470729071158759569L;
private String deceasedFullName;  
  private String directive;  
  private String donatorId;
  private String donatorFirstName ;  
  private String donations ;  
  private String donatorZipCode ;  
  private String donatorCity ;  
  private String donatorAddress2 ;  
  private String donationLetter ;  
  private String donatorTitle ;  
  private String donatorPhoneNumber ;  
  private String donatorLastName ;  
  private String donationAmount ;  
  private String donationOrganization ;  
  private String donatorState ;  
  private String donatorAddress3 ;  
  private String donatorAddress ;  
  private String donatorPhoneNumber2 ;  
  private String donationPaymentType ;
  
  
  public String getDeceasedFullName()
  {
	  return deceasedFullName;
  }  
  public String getDirective()
  {
	  return directive;
  }  
  public String getDonationAmount () {
	return donationAmount ;
  }  
  public String getDonationLetter () {
	return donationLetter ;
  }  
  public String getDonationOrganization () {
	return donationOrganization ;
  }  
  public String getDonationPaymentType () {
	return donationPaymentType ;
  }  
  public String getDonations () {
	return donations ;
  }  
  public String getDonatorAddress () {
	return donatorAddress ;
  }  
  public String getDonatorAddress2 () {
	return donatorAddress2 ;
  }  
  public String getDonatorAddress3 () {
	return donatorAddress3 ;
  }  
  public String getDonatorCity () {
	return donatorCity ;
  }  
  public String getDonatorFirstName () {
	return donatorFirstName ;
  }  
  public String getDonatorId() 
  {
	  return donatorId;
  }  
  public String getDonatorLastName () {
	return donatorLastName ;
  }  
  public String getDonatorPhoneNumber () {
	return donatorPhoneNumber ;
  }  
  public String getDonatorPhoneNumber2 () {
	return donatorPhoneNumber2 ;
  }  
  public String getDonatorState () {
	return donatorState ;
  }  
  public String getDonatorTitle() {
	return donatorTitle ;
  }    
  public String getDonatorZipCode () {
	return donatorZipCode ;
  }  
  public void setDeceasedFullName(String in )
  {
	  deceasedFullName = in;
  }  
  public void setDirective(String in )
  {
	  directive = in;
  }  
  public void setDonationAmount (String in) {
	donationAmount = in;
  }  
  public void setDonationLetter (String in) {
	donationLetter = in;
  }  
  public void setDonationOrganization (String in) {
	donationOrganization = in;
  }  
  public void setDonationPaymentType (String in) {
	donationPaymentType = in;
  }  
  public void setDonations (String in) {
	donations = in;
  }  
  public void setDonatorAddress (String in) {
	donatorAddress = in;
  }  
  public void setDonatorAddress2 (String in) {
	donatorAddress2 = in;
  }  
  public void setDonatorAddress3 (String in) {
	donatorAddress3 = in;
  }  
  public void setDonatorCity (String in) {
	donatorCity = in;
  }  
  public void setDonatorFirstName (String in) {
	donatorFirstName = in;
  }  
  public void setDonatorId( String in )
  {
	  donatorId = in ;
  }  
  public void setDonatorLastName (String in) {
	donatorLastName = in;
  }  
  public void setDonatorPhoneNumber (String in) {
	donatorPhoneNumber = in;
  }  
  public void setDonatorPhoneNumber2 (String in) {
	donatorPhoneNumber2 = in;
  }  
  public void setDonatorState (String in) {
	donatorState = in;
  }  
  public void setDonatorTitle (String in) {
	donatorTitle= in;
  }    
  public void setDonatorZipCode (String in) {
	donatorZipCode = in;
  }  
}
