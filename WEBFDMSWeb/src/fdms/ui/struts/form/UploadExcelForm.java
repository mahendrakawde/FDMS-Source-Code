package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadExcelForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private FormFile theFile;

	/**
	 * @return the theFile
	 */
	public FormFile getTheFile() {
		return theFile;
	}

	/**
	 * @param theFile the theFile to set
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	 
	 
}
