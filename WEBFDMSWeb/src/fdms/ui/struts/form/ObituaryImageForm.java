package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ObituaryImageForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1565590168178074916L;
	private String vitalsId;
	private String deceasedId;
	private FormFile file;
	private String submitButton;
	private String hasImage = "N";
	private String obitImageUrl;
	
	public String getDeceasedId() {
		return deceasedId;
	}
	public void setDeceasedId(String deceasedId) {
		this.deceasedId = deceasedId;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getVitalsId() {
		return vitalsId;
	}
	public void setVitalsId(String vitalsId) {
		this.vitalsId = vitalsId;
	}
	public String getSubmitButton() {
		return submitButton;
	}
	public void setSubmitButton(String submitButton) {
		this.submitButton = submitButton;
	}
	public String getHasImage() {
		return hasImage;
	}
	public void setHasImage(String hasImage) {
		this.hasImage = hasImage;
	}	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.hasImage = "N";
	}
	public String getObitImageUrl() {
		return obitImageUrl;
	}
	public void setObitImageUrl(String obitImageUrl) {
		this.obitImageUrl = obitImageUrl;
	}
	
	
	
}
