package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class ObituaryForm extends ObituaryFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8559327253055551588L;

	private java.lang.String deceasedFullName;

	private java.lang.String form;
	private java.lang.String website;
	
	private String deceasedFirstName;
	private String deceasedLastName;
	
	private String obitConnectionUrl;
	
/**
 * Insert the method's description here.
 * Creation date: (3/21/2002 4:33:30 PM)
 * @return java.lang.String
 */
public java.lang.String getWebsite() {
	return website;
}

/**
 * Insert the method's description here.
 * Creation date: (3/21/2002 4:33:30 PM)
 * @return java.lang.String
 */
public void setWebsite(String newWebsite) {
	website = newWebsite;
}

	/**
	 * Insert the method's description here. Creation date: (3/21/2002 4:33:30
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDeceasedFullName() {
		return deceasedFullName;
	}

	/**
	 * Insert the method's description here. Creation date: (3/29/2002 12:09:51
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getForm() {
		return form;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		this.setSendToAsimas(false);
		
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (3/21/2002 4:33:30
	 * PM)
	 * 
	 * @param newDeceasedFullName
	 *            java.lang.String
	 */
	public void setDeceasedFullName(java.lang.String newDeceasedFullName) {
		deceasedFullName = newDeceasedFullName;
	}

	/**
	 * Insert the method's description here. Creation date: (3/29/2002 12:09:51
	 * PM)
	 * 
	 * @param newForm
	 *            java.lang.String
	 */
	public void setForm(java.lang.String newForm) {
		form = newForm;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	public String getDeceasedFirstName() {
		return deceasedFirstName;
	}
	public void setDeceasedFirstName(String deceasedFirstName) {
		this.deceasedFirstName = deceasedFirstName;
	}

	public String getDeceasedLastName() {
		return deceasedLastName;
	}
	public void setDeceasedLastName(String deceasedLastName) {
		this.deceasedLastName = deceasedLastName;
	}

	public String getObitConnectionUrl() {
		return obitConnectionUrl;
	}
	public void setObitConnectionUrl(String obitConnectionUrl) {
		this.obitConnectionUrl = obitConnectionUrl;
	}
}
