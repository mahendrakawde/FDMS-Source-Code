package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class SurvivorsAddChangeForm extends SurvivorsAddChangeFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3925591386302092313L;

	private int survivorId;

	/**
	 * Insert the method's description here. Creation date: (3/21/2002 2:18:32
	 * PM)
	 * 
	 * @return int
	 */
	public int getSurvivorId() {
		return survivorId;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (3/21/2002 2:18:32
	 * PM)
	 * 
	 * @param newSurvivorId
	 *            int
	 */
	public void setSurvivorId(int newSurvivorId) {
		survivorId = newSurvivorId;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
