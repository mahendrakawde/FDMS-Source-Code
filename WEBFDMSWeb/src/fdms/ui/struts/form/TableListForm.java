package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class TableListForm extends TableListFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 307370862700176062L;

	private String submitbutton;

	/**
	 * Insert the method's description here. Creation date: (3/27/2002 11:26:32
	 * AM)
	 * 
	 * @return String
	 */
	public String getSubmitbutton() {
		return submitbutton;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (3/27/2002 11:26:32
	 * AM)
	 * 
	 * @param newSubmitbutton
	 *            String
	 */
	public void setSubmitbutton(String newSubmitbutton) {
		submitbutton = newSubmitbutton;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
