package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class EditVerseFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -5334979785826142821L;
private int x ;  
  private String memorialId ;  
  private String vitalsId ;  
  private String description ;  
  private String directive ;  
  private int y ;
  
	private java.lang.String textValue;
  public String getDescription () {
	return description ;
  }  
  public String getDirective () {
	return directive ;
  }  
  public String getMemorialId () {
	return memorialId ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (10/8/2002 5:57:43 PM)
 * @return java.lang.String
 */
public java.lang.String getTextValue() {
	return textValue;
}
  public String getVitalsId () {
	return vitalsId ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
  public void setDescription (String in) {
	description = in;
  }  
  public void setDirective (String in) {
	directive = in;
  }  
  public void setMemorialId (String in) {
	memorialId = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (10/8/2002 5:57:43 PM)
 * @param newVerseText java.lang.String
 */
public void setTextValue(java.lang.String newTextValue) {
	textValue = newTextValue;
}
  public void setVitalsId (String in) {
	vitalsId = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
}
