package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class LocationEditForm extends LocationEditFormBase {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = -4553481924560483063L;
private java.lang.String website;				// url for website accessable from obit page
  

  public java.lang.String getWebsite() {
  return website;
  }
  
  public void setWebsite (java.lang.String newWebsite) {
  website = newWebsite;
  }
  
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
	setGenericVitals(false);
	setUseUndepositedFundsAcct(false);
	setAutoFillDateOfDeath(false);
	setAutoFillArrangeDate(false);
	setTurnOnApplyFinanceCharge(false);
  }
  
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }      
}
