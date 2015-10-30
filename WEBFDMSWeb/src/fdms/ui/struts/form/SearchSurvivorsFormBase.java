package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class SearchSurvivorsFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4830272067138568083L;

	private boolean contractNo;

	private boolean preneed;

	private String maxLimit;

	private boolean survivors;

	private boolean informant;

	private String searchText;

	private boolean caseCode;

	private boolean deceased;

	private String locationId;

	private boolean fromSearch;
	
	private boolean checkWriterSearch;
	
	private String directive;
	
	public boolean getCaseCode() {
		return caseCode;
	}

	public boolean getContractNo() {
		return contractNo;
	}

	public boolean getDeceased() {
		return deceased;
	}

	public boolean getInformant() {
		return informant;
	}

	public String getMaxLimit() {
		return maxLimit;
	}

	public boolean getPreneed() {
		return preneed;
	}

	public String getSearchText() {
		return searchText;
	}

	public boolean getSurvivors() {
		return survivors;
	}

	/**
	 * Insert the method's description here. Creation date: (8/15/2002 12:05:52
	 * PM)
	 * 
	 * @return boolean
	 */
	public boolean isFromSearch() {
		return fromSearch;
	}

	public void setCaseCode(boolean in) {
		caseCode = in;
	}

	public void setContractNo(boolean in) {
		contractNo = in;
	}

	public void setDeceased(boolean in) {
		deceased = in;
	}

	/**
	 * Insert the method's description here. Creation date: (8/15/2002 12:05:52
	 * PM)
	 * 
	 * @param newFromSearch
	 *            boolean
	 */
	public void setFromSearch(boolean newFromSearch) {
		fromSearch = newFromSearch;
	}

	public void setInformant(boolean in) {
		informant = in;
	}

	public void setMaxLimit(String in) {
		maxLimit = in;
	}

	public void setPreneed(boolean in) {
		preneed = in;
	}

	public void setSearchText(String in) {
		searchText = in;
	}

	public void setSurvivors(boolean in) {
		survivors = in;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the checkWriterSearch
	 */
	public boolean isCheckWriterSearch() {
		return checkWriterSearch;
	}

	/**
	 * @param checkWriterSearch the checkWriterSearch to set
	 */
	public void setCheckWriterSearch(boolean checkWriterSearch) {
		this.checkWriterSearch = checkWriterSearch;
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

}