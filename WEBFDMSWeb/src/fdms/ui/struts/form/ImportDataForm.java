package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ImportDataForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private FormFile file;
	
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
}
