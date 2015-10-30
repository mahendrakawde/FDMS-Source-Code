package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class PnSummary extends PnSummaryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4384532214237413875L;
	private java.lang.String locationId;
/**
 * Insert the method's description here.
 * Creation date: (2/10/2003 5:36:08 PM)
 * @return java.lang.String
 */
public java.lang.String getLocationId() {
	return locationId;
}
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
	setIrrevocable(false);
	setYearlyStatements(false);
  }    
/**
 * Insert the method's description here.
 * Creation date: (2/10/2003 5:36:08 PM)
 * @param newLocationId java.lang.String
 */
public void setLocationId(java.lang.String newLocationId) {
	locationId = newLocationId;
}
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
