package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.ServiceScreenThemeDAO;
import com.aldorassist.webfdms.model.ServiceScreenThemeDTO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.ERegisterThemeForm;

public class ProcessServiceTheme extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ERegisterThemeForm eregisterForm = (ERegisterThemeForm) form;
		
		ServiceScreenThemeDAO themeDao = new ServiceScreenThemeDAO(DAO.DB_FDMSCOMMON);
		
		if(eregisterForm.getOperation() != null && eregisterForm.getOperation().equalsIgnoreCase("add")) {
			ServiceScreenThemeDTO themeDto = new ServiceScreenThemeDTO();
			
			themeDto.setThemeDesc(eregisterForm.getServiceScreenThemeDesc());
			
			themeDao.addServiceScreenTheme(themeDto);
		}
		
		return mapping.findForward("showERegisterTheme");
	}
}
