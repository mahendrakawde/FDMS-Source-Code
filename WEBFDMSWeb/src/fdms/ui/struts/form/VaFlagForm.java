package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class VaFlagForm extends VaFlagFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 449006491388024986L;

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
		this.setArmyBox(false);
		this.setNavyBox(false);
		this.setAirForceBox(false);
		this.setMarineBox(false);
		this.setCoastGuardBox(false);
		this.setOtherBranchBox(false);
		this.setSpanAmerBox(false);
		this.setWwiBox(false);
		this.setWwiiBox(false);
		this.setKoreanBox(false);
		this.setAfter55Box(false);
		this.setVietnamBox(false);
		this.setOtherServBox(false);
		this.setCondition1Box(false);
		this.setCondition2Box(false);
		this.setCondition3Box(false);
		this.setCondition4Box(false);
		/* new form */
		this.setSelReserveBox(false);
		this.setGulfBox(false);
		this.setCondition5Box(false);

	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		this.setArmyBox(false);
		this.setNavyBox(false);
		this.setAirForceBox(false);
		this.setMarineBox(false);
		this.setCoastGuardBox(false);
		this.setOtherBranchBox(false);
		this.setSpanAmerBox(false);
		this.setWwiBox(false);
		this.setWwiiBox(false);
		this.setKoreanBox(false);
		this.setAfter55Box(false);
		this.setVietnamBox(false);
		this.setOtherServBox(false);
		this.setCondition1Box(false);
		this.setCondition2Box(false);
		this.setCondition3Box(false);
		this.setCondition4Box(false);
		/* new form */
		this.setSelReserveBox(false);
		this.setGulfBox(false);
		this.setCondition5Box(false);

		return super.validate(actionMapping, request);
	}
}
