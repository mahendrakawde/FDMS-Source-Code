package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class OpenCemSpcCaseFormBase extends ActionForm {
  
  private String id ;
  
  public String getId () {
	return id ;
  }  
  public void setId (String in) {
	id = in;
  }  
}
