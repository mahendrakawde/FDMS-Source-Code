package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class MiscCashReceiptsForm extends MiscCashReceiptsFormBase {

  /**
	 * 
	 */
	private static final long serialVersionUID = -3355629035321830999L;
public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
  }  
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
