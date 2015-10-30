package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class MarqueeFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -1508304599917352194L;
private int x ;  
  private String marqueeText ;  
  private int y ;
  private String submitButton;
  public String getMarqueeText () {
	return marqueeText ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (10/11/2002 5:41:56 PM)
 * @return java.lang.String
 */
public java.lang.String getSubmitButton() {
	return submitButton;
}
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
  public void setMarqueeText (String in) {
	marqueeText = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (10/11/2002 5:41:56 PM)
 * @param newSubmitButton java.lang.String
 */
public void setSubmitButton(java.lang.String newSubmitButton) {
	submitButton = newSubmitButton;
}
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
}
