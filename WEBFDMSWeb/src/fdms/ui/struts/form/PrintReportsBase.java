package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PrintReportsBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -912810338007133793L;
private String x ;  
  private String toDate ;  
  private String selectLocation ;  
  private String fromDate ;  
  private String selectReport ;  
  private String y ;
  private String category;
  private String selectItemCategory;
  
	private java.lang.String generateButton;
  public String getFromDate () {
	return fromDate ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (10/14/2002 3:39:43 PM)
 * @return java.lang.String
 */
public java.lang.String getGenerateButton() {
	return generateButton;
}
  public String getSelectLocation () {
	return selectLocation ;
  }  
  public String getSelectReport () {
	return selectReport ;
  }  
  public String getToDate () {
	return toDate ;
  }  
  public String getX () {
	return x ;
  }  
  public String getY () {
	return y ;
  }  
  public String getCategory() {
      return category;
  }
  public void setFromDate (String in) {
	fromDate = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (10/14/2002 3:39:43 PM)
 * @param newGenerateButton java.lang.String
 */
public void setGenerateButton(java.lang.String newGenerateButton) {
	generateButton = newGenerateButton;
}
  public void setSelectLocation (String in) {
	selectLocation = in;
  }  
  public void setSelectReport (String in) {
	selectReport = in;
  }  
  public void setToDate (String in) {
	toDate = in;
  }  
  public void setX (String in) {
	x = in;
  }  
  public void setY (String in) {
	y = in;
  }  
  public void setCategory(String category) {
      this.category = category;
  }
public String getSelectItemCategory() {
	return selectItemCategory;
}
public void setSelectItemCategory(String selectItemCategory) {
	this.selectItemCategory = selectItemCategory;
}
  

}
