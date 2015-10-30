package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PnFulfillmentBase extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7911054772936667318L;

	private String contactPhone;

	private int x;

	private String contractId;

	private String vitalsId;

	private String submitButton;

	private String deathDate;

	private String contactName;

	private int y;

	public String getContactName() {
		return contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getContractId() {
		return contractId;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public String getSubmitButton() {
		return submitButton;
	}

	public String getVitalsId() {
		return vitalsId;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setContactName(String in) {
		contactName = in;
	}

	public void setContactPhone(String in) {
		contactPhone = in;
	}

	public void setContractId(String in) {
		contractId = in;
	}

	public void setDeathDate(String in) {
		deathDate = in;
	}

	public void setSubmitButton(String in) {
		submitButton = in;
	}

	public void setVitalsId(String in) {
		vitalsId = in;
	}

	public void setX(int in) {
		x = in;
	}

	public void setY(int in) {
		y = in;
	}
}
