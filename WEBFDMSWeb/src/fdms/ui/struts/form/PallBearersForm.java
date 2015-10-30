package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class PallBearersForm extends PallBearersFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7524511518884297555L;
	private java.lang.String deceasedFullName;
/**
 * Insert the method's description here.
 * Creation date: (3/20/2002 11:21:33 AM)
 * @return java.lang.String
 */
public java.lang.String getDeceasedFullName() {
	return deceasedFullName;
}
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
  }  
/**
 * Insert the method's description here.
 * Creation date: (3/20/2002 11:21:33 AM)
 * @param newDeceasedFullName java.lang.String
 */
public void setDeceasedFullName(java.lang.String newDeceasedFullName) {
	deceasedFullName = newDeceasedFullName;
}
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
