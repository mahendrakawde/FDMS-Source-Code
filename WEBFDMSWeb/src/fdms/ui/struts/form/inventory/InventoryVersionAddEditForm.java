package fdms.ui.struts.form.inventory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.util.FormatDate;



/**
 * @struts.form name="inventoryVersionAddEditForm"
 * 
 */
public class InventoryVersionAddEditForm extends ActionForm {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5434327600119644848L;

	private String directive;
	
	private String invVersionID;

	private String priceListID;

	private String companyID;

	private String name;

	private String description;

	private String invFromDate;

	private String invToDate;

	private boolean active;

	private ArrayList locations;
	
	private ArrayList priceLists;

	private String[] locationIDs;

	private ArrayList locales;

	private String[] localeIDs;
	
	private String localeMapJavascript;

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the companyID
	 */
	public String getCompanyID() {
		return companyID;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the directive
	 */
	public String getDirective() {
		return directive;
	}

	/**
	 * @return the invFromDate
	 */
	public String getInvFromDate() {
		return invFromDate;
	}

	/**
	 * @return the invToDate
	 */
	public String getInvToDate() {
		return invToDate;
	}

	/**
	 * @return the localeIDs
	 */
	public String[] getLocaleIDs() {
		return localeIDs;
	}

	/**
	 * @return the locales
	 */
	public ArrayList getLocales() {
		return locales;
	}
	
	/**
	 * @return the locales.size()
	 */
	public int getLocalesSize() {
		if ( locales != null ) {
			return ( locales.size() );
		}
		
		return (1);
	}

	/**
	 * @return the locationIDs
	 */
	public String[] getLocationIDs() {
		return locationIDs;
	}

	/**
	 * @return the locations
	 */
	public ArrayList getLocations() {
		return locations;
	}
	
	/**
	 * @return the locations.size()
	 */
	public int getLocationsSize() {
		if ( locations != null ) {
			return ( locations.size() );
		}
		
		return (1);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the priceListID
	 */
	public String getPriceListID() {
		return priceListID;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = Long.toString(companyID);
	}
	
	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param directive
	 *            the directive to set
	 */
	public void setDirective(String directive) {
		this.directive = directive;
	}

	/**
	 * @param invFromDate
	 *            the invFromDate to set
	 */
	public void setInvFromDate(String invFromDate) {
		this.invFromDate = invFromDate;
	}

	/**
	 * @param invFromDate
	 *            the invFromDate to set
	 */
	public void setInvFromDate(java.sql.Date invFromDate) {
		this.invFromDate = FormatDate.convertDateToMM_DD_YYYY(invFromDate);
	}

	/**
	 * @param invToDate
	 *            the invToDate to set
	 */
	public void setInvToDate(String invToDate) {
		this.invToDate = invToDate;
	}

	/**
	 * @param invToDate
	 *            the invToDate to set
	 */
	public void setInvToDate(java.sql.Date invToDate) {
		this.invToDate = FormatDate.convertDateToMM_DD_YYYY(invToDate);;
	}
	
	/**
	 * @param localeIDs
	 *            the localeIDs to set
	 */
	public void setLocaleIDs(String[] localeIDs) {
		this.localeIDs = localeIDs;
	}

	/**
	 * @param locales
	 *            the locales to set
	 */
	public void setLocales(ArrayList locales) {
		this.locales = locales;
	}

	/**
	 * @param locationIDs
	 *            the locationIDs to set
	 */
	public void setLocationIDs(String[] locationIDs) {
		this.locationIDs = locationIDs;
	}

	/**
	 * @param locations
	 *            the locations to set
	 */
	public void setLocations(ArrayList locations) {
		this.locations = locations;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param priceListID
	 *            the priceListID to set
	 */
	public void setPriceListID(String priceListID) {
		this.priceListID = priceListID;
	}
	
	/**
	 * @param priceListID
	 *            the priceListID to set
	 */
	public void setPriceListID(long priceListID) {
		this.priceListID = Long.toString(priceListID);
	}

	/**
	 * @return the invVersionID
	 */
	public String getInvVersionID() {
		return invVersionID;
	}

	/**
	 * @param invVersionID the invVersionID to set
	 */
	public void setInvVersionID(String invVersionID) {
		this.invVersionID = invVersionID;
	}

	/**
	 * @return the localeMapJavascript
	 */
	public String getLocaleMapJavascript() {
		return localeMapJavascript;
	}

	/**
	 * @param localeMapJavascript the localeMapJavascript to set
	 */
	public void setLocaleMapJavascript(String localeMapJavascript) {
		this.localeMapJavascript = localeMapJavascript;
	}

	/**
	 * @return the priceLists
	 */
	public ArrayList getPriceLists() {
		return priceLists;
	}

	/**
	 * @param priceLists the priceLists to set
	 */
	public void setPriceLists(ArrayList priceLists) {
		this.priceLists = priceLists;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ArrayList formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        
//        MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
//        MessageResources resources = messageFactory.createResources("ApplicationResources");
        
        logger.debug("InvVerserion Name: " + name);
        
        if (name == null || name.trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.isRequired", "Version Name"));
            formErrors.add("name");
        }
        
        if (priceListID == null || priceListID.trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.isRequired", "Price List"));
            formErrors.add("priceListID");
        }
        
        if (companyID == null || companyID.trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.isRequired", "Company"));
            formErrors.add("companyID");
        }
        	
        if (formErrors.size() > 0) {
        	request.setAttribute("formErrors", formErrors);
        }
        
        return errors;
    }
}