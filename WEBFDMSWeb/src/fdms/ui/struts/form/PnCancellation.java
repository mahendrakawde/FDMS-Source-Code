package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PnCancellation extends PnCancellationBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7229806975306488690L;

	private String fullName;

	private String contractCode;

	/**
	 * Insert the method's description here. Creation date: (1/19/2003 4:56:01
	 * PM)
	 * 
	 * @return String
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/19/2003 4:55:40
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
		this.setAcknowledgement(false);
	}

	/**
	 * Insert the method's description here. Creation date: (1/19/2003 4:56:01
	 * PM)
	 * 
	 * @param newContractCode
	 *            String
	 */
	public void setContractCode(String newContractCode) {
		contractCode = newContractCode;
	}

	/**
	 * Insert the method's description here. Creation date: (1/19/2003 4:55:40
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
