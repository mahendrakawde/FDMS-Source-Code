package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.ERegisterBackgroundThemeDAO;
import com.aldorassist.webfdms.model.ERegisterBackgroundThemeDTO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.ERegisterThemeForm;

public class ProcessBackgroundTheme extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ERegisterThemeForm eregisterForm = (ERegisterThemeForm) form;
		
		ERegisterBackgroundThemeDAO themeDao = new ERegisterBackgroundThemeDAO(DAO.DB_FDMSCOMMON);
		
		if(eregisterForm.getOperation() != null && eregisterForm.getOperation().equalsIgnoreCase("add")) {
			ERegisterBackgroundThemeDTO themeDto = new ERegisterBackgroundThemeDTO();
			
			themeDto.setThemeDesc(eregisterForm.getBackgroundThemeDesc());
			
			themeDao.addBackgroundTheme(themeDto);
		}

		return mapping.findForward("showERegisterTheme");
	}

}
