package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PnAddServicesForm extends PnAddServicesFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2434262862376236769L;

	private String fullName;

	private String contractCode;

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 10:11:54
	 * AM)
	 * 
	 * @return String
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 10:11:39
	 * AM)
	 * 
	 * @return String
	 */
	public String getFullName() {
		return fullName;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 10:11:54
	 * AM)
	 * 
	 * @param newContractCode
	 *            String
	 */
	public void setContractCode(String newContractCode) {
		contractCode = newContractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 10:11:39
	 * AM)
	 * 
	 * @param newFullName
	 *            String
	 */
	public void setFullName(String newFullName) {
		fullName = newFullName;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
