package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class VendorEditListForm extends ActionForm {

	private long locationID;
	private ArrayList vendors = new ArrayList();
	
	private String directive = null;
	
	private String checkNumber = null;
	
	private String searchVendorName;
	private long localeID;
	private String vendorCode;
	private String includeInactive;
	private String includeNoLocation;
	private String direction;
	private boolean exactCode;
	private boolean exactName;
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
	 * @return the vendors
	 */
	public ArrayList getVendors() {
		return vendors;
	}

	/**
	 * @param vendors
	 *            the vendors to set
	 */
	public void setVendors(ArrayList vendors) {
		this.vendors = vendors;
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
	 * @return the searchVendorName
	 */
	public String getSearchVendorName() {
		return searchVendorName;
	}

	/**
	 * @param searchVendorName the searchVendorName to set
	 */
	public void setSearchVendorName(String searchVendorName) {
		this.searchVendorName = searchVendorName;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	public long getLocaleID() {
		return localeID;
	}

	public void setLocaleID(long localeID) {
		this.localeID = localeID;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getIncludeInactive() {
		return includeInactive;
	}

	public void setIncludeInactive(String includeInactive) {
		this.includeInactive = includeInactive;
	}

	public String getIncludeNoLocation() {
		return includeNoLocation;
	}

	public void setIncludeNoLocation(String includeNoLocation) {
		this.includeNoLocation = includeNoLocation;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isExactCode() {
		return exactCode;
	}

	public void setExactCode(boolean exactCode) {
		this.exactCode = exactCode;
	}

	public boolean isExactName() {
		return exactName;
	}

	public void setExactName(boolean exactName) {
		this.exactName = exactName;
	}


	
}
