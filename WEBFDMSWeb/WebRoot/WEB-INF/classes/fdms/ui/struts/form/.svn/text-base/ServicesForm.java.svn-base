package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class ServicesForm extends ServicesFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2619280237299059790L;

	private java.lang.String deceasedFullName;

	/**
	 * Insert the method's description here. Creation date: (3/19/2002 2:17:16
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDeceasedFullName() {
		return deceasedFullName;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
		this.setFlowerDelivery(false);
		this.setRestoration(false);
		this.setFlowerPickup(false);
		this.setCemeteryOpen(false);
		this.setCemeterySetAndSeal(false);
		this.setCemeteryTent(false);
	}

	/**
	 * Insert the method's description here. Creation date: (3/19/2002 2:17:16
	 * PM)
	 * 
	 * @param newDeceasedFullName
	 *            java.lang.String
	 */
	public void setDeceasedFullName(java.lang.String newDeceasedFullName) {
		deceasedFullName = newDeceasedFullName;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
