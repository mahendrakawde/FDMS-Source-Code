package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.reporting.filter.FilterInterface;

public class FormEditForm extends FormEditFormBase {
	
	private ArrayList <FilterInterface> filtersI = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2017395958795294300L;
	
	/**
	 * @return the filtersI
	 */
	public ArrayList<FilterInterface> getFiltersI() {
		return filtersI;
	}

	/**
	 * @param filtersI the filtersI to set
	 */
	public void setFiltersI(ArrayList<FilterInterface> filtersI) {
		this.filtersI = filtersI;
	}

	/**
	 * 
	 */
	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}
	
	/**
	 * 
	 */
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
