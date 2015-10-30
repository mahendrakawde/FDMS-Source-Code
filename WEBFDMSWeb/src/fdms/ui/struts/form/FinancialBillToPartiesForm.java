package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FinancialBillToPartiesForm extends FinancialBillToPartiesFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4119883836143180719L;

	private java.lang.String submitbutton;

	/**
	 * Insert the method's description here. Creation date: (4/22/2002 2:31:54
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSubmitbutton() {
		return submitbutton;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (4/22/2002 2:31:54
	 * PM)
	 * 
	 * @param newSubmitbutton
	 *            java.lang.String
	 */
	public void setSubmitbutton(java.lang.String newSubmitbutton) {
		submitbutton = newSubmitbutton;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
