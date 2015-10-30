package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class ReportsPrintFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -4308860975833039139L;
private String x ;  
  private String category ;  
  private String directive ;  
  private String y ;
  
  public String getCategory () {
	return category ;
  }  
  public String getDirective () {
	return directive ;
  }  
  public String getX () {
	return x ;
  }  
  public String getY () {
	return y ;
  }  
  public void setCategory (String in) {
	category = in;
  }  
  public void setDirective (String in) {
	directive = in;
  }  
  public void setX (String in) {
	x = in;
  }  
  public void setY (String in) {
	y = in;
  }  
}
