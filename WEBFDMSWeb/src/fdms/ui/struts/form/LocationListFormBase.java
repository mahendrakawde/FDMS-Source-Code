package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class LocationListFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 5840071174616598259L;
private int x ;  
  private String locationID ;  
  private String submitbutton ;  
  private int y ;
  
  public String getLocationID () {
	return locationID ;
  }  
  public String getSubmitbutton () {
	return submitbutton ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
  public void setLocationID (String in) {
	locationID = in;
  }  
  public void setSubmitbutton (String in) {
	submitbutton = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
}
