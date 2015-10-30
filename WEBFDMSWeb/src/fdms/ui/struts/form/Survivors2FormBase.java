package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class Survivors2FormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 845112760490291585L;
private String informantLastName ;  
  private String stateOfDeath ;  
  private String relationship ;  
  private String informantFirstName ;  
  private String informantStreet ;  
  private String cityOfDeath ;  
  private String informantAlternatePhone ;  
  private String zipCodeOfDeath ;  
  private String informantPhone ;
  
  public String getCityOfDeath () {
	return cityOfDeath ;
  }  
  public String getInformantAlternatePhone () {
	return informantAlternatePhone ;
  }  
  public String getInformantFirstName () {
	return informantFirstName ;
  }  
  public String getInformantLastName () {
	return informantLastName ;
  }  
  public String getInformantPhone () {
	return informantPhone ;
  }  
  public String getInformantStreet () {
	return informantStreet ;
  }  
  public String getRelationship () {
	return relationship ;
  }  
  public String getStateOfDeath () {
	return stateOfDeath ;
  }  
  public String getZipCodeOfDeath () {
	return zipCodeOfDeath ;
  }  
  public void setCityOfDeath (String in) {
	cityOfDeath = in;
  }  
  public void setInformantAlternatePhone (String in) {
	informantAlternatePhone = in;
  }  
  public void setInformantFirstName (String in) {
	informantFirstName = in;
  }  
  public void setInformantLastName (String in) {
	informantLastName = in;
  }  
  public void setInformantPhone (String in) {
	informantPhone = in;
  }  
  public void setInformantStreet (String in) {
	informantStreet = in;
  }  
  public void setRelationship (String in) {
	relationship = in;
  }  
  public void setStateOfDeath (String in) {
	stateOfDeath = in;
  }  
  public void setZipCodeOfDeath (String in) {
	zipCodeOfDeath = in;
  }  
}
