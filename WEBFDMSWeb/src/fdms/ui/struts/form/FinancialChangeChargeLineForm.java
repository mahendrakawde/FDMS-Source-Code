package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FinancialChangeChargeLineForm extends FinancialChangeChargeLineFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2173884130849129530L;

	private java.lang.String serialNumber;

	private java.lang.String stockType;

	private java.lang.String serialNumberModifiable;

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 4:44:40
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (11/11/2002 7:41:07
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSerialNumberModifiable() {
		return serialNumberModifiable;
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 4:58:02
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getStockType() {
		return stockType;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 4:44:40
	 * PM)
	 * 
	 * @param newSerialNumber
	 *            java.lang.String
	 */
	public void setSerialNumber(java.lang.String newSerialNumber) {
		serialNumber = newSerialNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (11/11/2002 7:41:07
	 * AM)
	 * 
	 * @param newSerialNumberModifiable
	 *            java.lang.String
	 */
	public void setSerialNumberModifiable(java.lang.String newSerialNumberModifiable) {
		serialNumberModifiable = newSerialNumberModifiable;
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 4:58:02
	 * PM)
	 * 
	 * @param newStockType
	 *            java.lang.String
	 */
	public void setStockType(java.lang.String newStockType) {
		stockType = newStockType;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
