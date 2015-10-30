package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class VeteranFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8020247999959658762L;

	private String vet;

	public String getVet() {
		return vet;
	}

	public void setVet(String in) {
		vet = in;
	}
}
