package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class TableContentsFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4616148623343892600L;

	private String locationId;

	private String[] speedDataContents;

	public String[] getSpeedDataContents() {
		return speedDataContents;
	}

	public void setSpeedDataContents(String[] in) {
		speedDataContents = in;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
}
