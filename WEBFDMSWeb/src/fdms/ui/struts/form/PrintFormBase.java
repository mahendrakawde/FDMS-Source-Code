package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PrintFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3485873676104242924L;

	private String formName;

	private java.lang.String vitalsId;

	private java.lang.String memorialId;

	public String getFormName() {
		return formName;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/2002 10:08:23
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getMemorialId() {
		return memorialId;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/2002 10:08:10
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVitalsId() {
		return vitalsId;
	}

	public void setFormName(String in) {
		formName = in;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/2002 10:08:23
	 * AM)
	 * 
	 * @param newMemorialId
	 *            java.lang.String
	 */
	public void setMemorialId(java.lang.String newMemorialId) {
		memorialId = newMemorialId;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/2002 10:08:10
	 * AM)
	 * 
	 * @param newVitalsId
	 *            java.lang.String
	 */
	public void setVitalsId(java.lang.String newVitalsId) {
		vitalsId = newVitalsId;
	}
}
