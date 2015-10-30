package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.checkwriter.model.CheckListDisplayDTO;

public class CheckEditListForm extends ActionForm {

	private ArrayList<CheckListDisplayDTO> checks = new ArrayList<CheckListDisplayDTO>();
	
	private String directive = null;
	
	private String checkNumber = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434883745484713369L;

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	/**
	 * @return the checks
	 */
	public ArrayList<CheckListDisplayDTO> getChecks() {
		return checks;
	}

	/**
	 * @param checks
	 *            the checks to set
	 */
	public void setChecks(ArrayList<CheckListDisplayDTO> checks) {
		this.checks = checks;
	}

	/**
	 * @return the directive
	 */
	public String getDirective() {
		return directive;
	}

	/**
	 * @param directive the directive to set
	 */
	public void setDirective(String directive) {
		this.directive = directive;
	}

	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	
}
