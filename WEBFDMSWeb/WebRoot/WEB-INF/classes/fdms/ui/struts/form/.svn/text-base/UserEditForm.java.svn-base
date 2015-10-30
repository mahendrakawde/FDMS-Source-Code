package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class UserEditForm extends UserEditFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1871274794106502392L;

	private int userID;

	private int actionID;
	private String userDefaultLocation;

	private java.lang.String action;

	public final static int ACTION_ADD = 1;

	public final static int ACTION_CHANGE = 2;

	public final static int ACTION_DELETE = 3;

	/**
	 * Insert the method's description here. Creation date: (5/16/2002 11:20:24
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getAction() {
		return action;
	}

	/**
	 * Insert the method's description here. Creation date: (5/16/2002 11:20:14
	 * AM)
	 * 
	 * @return int
	 */
	public int getActionID() {
		return actionID;
	}

	/**
	 * @return int
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * 
	 */
	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		super.reset(actionMapping, request);
		setAdmin(false);
		setAr(false);
		setAtneed(false);
		setFinancial(false);
		setForms(false);
		setInventory(false);
		setPreneed(false);
		setPayment(false);
		setViewonly(false);
		setAccountingInterface(false);
		setEasyPayment(false);
		setSpeedData(false);
		setFinancialChange(false);
		setArrangerManager(false);
		setFormsAvaialble(false);
		setGlSalesAccount(false);
		setVoidcase(false);
		setReports(false);
		setUserLocked(false);
		setViewCemetery(false);
		setViewFuneralHome(false);
		setCompanyID(0);
	}

	/**
	 * @param newAction
	 *            java.lang.String
	 */
	public void setAction(java.lang.String newAction) {
		action = newAction;
	}

	/**
	 * @param newActionID
	 *            int
	 */
	public void setActionID(int newActionID) {
		actionID = newActionID;
	}

	/**
	 * @param newUserID
	 *            int
	 */
	public void setUserID(int newUserID) {
		userID = newUserID;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
	
	/**
	 * @return the userDefaultLocation
	 */
	public String getUserDefaultLocation() {
		return userDefaultLocation;
	}

	/**
	 * @param userDefaultLocation the userDefaultLocation to set
	 */
	public void setUserDefaultLocation(String userDefaultLocation) {
		this.userDefaultLocation = userDefaultLocation;
	}
}
