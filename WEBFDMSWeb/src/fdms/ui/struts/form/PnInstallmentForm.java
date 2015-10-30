package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PnInstallmentForm extends PnInstallmentFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7447768438642506075L;

	private String fullName;

	private String contractCode;

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 4:45:08
	 * PM)
	 * 
	 * @return String
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 4:44:43
	 * PM)
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
	 * Insert the method's description here. Creation date: (1/13/2003 4:45:08
	 * PM)
	 * 
	 * @param newContractCode
	 *            String
	 */
	public void setContractCode(String newContractCode) {
		contractCode = newContractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/13/2003 4:44:43
	 * PM)
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
