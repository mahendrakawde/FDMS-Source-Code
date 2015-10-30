package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.ERegisterThemeDAO;
import com.aldorassist.webfdms.model.ERegisterThemeDTO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.ERegisterThemeForm;

public class ProcessERegisterTheme extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ERegisterThemeForm themeForm = (ERegisterThemeForm) form;
		
		ERegisterThemeDTO themeDto = new ERegisterThemeDTO();
		
		themeDto.setThemeDesc(themeForm.getThemeDesc());
		
		ERegisterThemeDAO themeDao = new ERegisterThemeDAO(DAO.DB_FDMSCOMMON);
		
		if(themeDao.addTheme(themeDto)) {
			themeForm.setThemeDesc("");
			// lead to success
		} else {
			// lead to failure
		}
		
		return mapping.findForward("showERegisterTheme");
	}

}
