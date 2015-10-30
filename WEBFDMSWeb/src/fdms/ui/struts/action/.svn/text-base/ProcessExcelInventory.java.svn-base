package fdms.ui.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.aldorsolutions.webfdms.beans.LoadDataDTO;
import com.aldorsolutions.webfdms.common.Constants;


public class ProcessExcelInventory extends Action{

	private Logger logger = Logger.getLogger(ProcessExcelInventory.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entered ProcessExcelInventory Action");
		
		DynaActionForm form = (DynaActionForm) actionForm;
		
		
		
		
		
		
		
		
		
		
		
		return mapping.findForward("excelinventorydetails");
	}
	
	/**
	 * Checks validation errors
	 * 
	 * @param errors
	 *            list of errors
	 * @param form
	 *            loaddataform
	 */
	public void validate(ActionErrors errors, DynaActionForm form) {
		FormFile file = (FormFile) form.get("file");
		String fileName = file.getFileName();
		if (fileName != null) {
			fileName = fileName.toUpperCase();
			fileName = fileName.trim();
		}
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		Boolean identityRequired = (Boolean) form.get("identityRequired");
		String identityStart = (String) form.get("identityStart");
		
		String databaseName = dto.getDataBaseName();
		if (databaseName != null) {
			databaseName = databaseName.trim();
		}
		if (databaseName == null || "".equals(databaseName)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, "DATA BASE"));
		}

		if (identityRequired.booleanValue() && identityStart == null || "".equals(identityStart)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR,
					"If Identity Required is True, identityStart can not be empty"));
		}
		
		if (fileName == null || "".equals(fileName)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, "DATA_FILE"));
		} else {
			if (!fileName.endsWith(".TXT") && !fileName.endsWith(".CSV")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.SUFFIX_ERROR, "DATA_FILE",
						".xls"));
			}
		}
	}

}
