package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class TableListFormBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6803017715737851692L;

	private int x;

	private String speedDataCategory;

	private int y;

	private String locationId = "0";

	public String getSpeedDataCategory() {
		return speedDataCategory;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setSpeedDataCategory(String in) {
		speedDataCategory = in;
	}

	public void setX(int in) {
		x = in;
	}

	public void setY(int in) {
		y = in;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

}
