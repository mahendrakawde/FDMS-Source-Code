package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class PnSelectPackageForm extends PnSelectPackageFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5639861545838357858L;
	private java.lang.String fullName;
	private java.lang.String contractCode;
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 11:51:13 AM)
 * @return java.lang.String
 */
public java.lang.String getContractCode() {
	return contractCode;
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 11:51:01 AM)
 * @return java.lang.String
 */
public java.lang.String getFullName() {
	return fullName;
}
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
  }  
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 11:51:13 AM)
 * @param newContractCode java.lang.String
 */
public void setContractCode(java.lang.String newContractCode) {
	contractCode = newContractCode;
}
/**
 * Insert the method's description here.
 * Creation date: (1/13/2003 11:51:01 AM)
 * @param newFullName java.lang.String
 */
public void setFullName(java.lang.String newFullName) {
	fullName = newFullName;
}
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
