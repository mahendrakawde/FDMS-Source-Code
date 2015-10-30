package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class TableRowFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 5372749785476502235L;
private String listValue ;  
  public String getListValue () {
	return listValue ;
  }  
  public void setListValue (String in) {
	listValue = in;
  }  
}
