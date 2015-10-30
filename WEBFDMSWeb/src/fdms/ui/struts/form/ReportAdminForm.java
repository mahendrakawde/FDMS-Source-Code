package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;


public class ReportAdminForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7641252215247080561L;

	private String formID;
	private String submitbutton;


	public String getSubmitbutton() {
		return submitbutton;
	}

	public void setSubmitbutton(String submitbutton) {
		this.submitbutton = submitbutton;
	}

	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}
}
