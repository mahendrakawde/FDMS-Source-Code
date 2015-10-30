package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.dao.ERegisterBackgroundThemeDAO;
import com.aldorassist.webfdms.dao.ERegisterThemeDAO;
import com.aldorassist.webfdms.dao.PresentationImageDAO;
import com.aldorassist.webfdms.dao.ServiceScreenThemeDAO;
import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.ERegisterThemeForm;

/**
 * Action to display list of themes of ERegister.
 * 
 * @author Parth
 *
 */
public class ShowERegisterTheme extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ERegisterThemeForm themeForm = (ERegisterThemeForm) form;
		
		ERegisterThemeDAO themeDAO = new ERegisterThemeDAO(DAO.DB_FDMSCOMMON);
		
		PresentationImageDAO presentaionImageDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
		
		PresentationImageDTO defaultPaceHolder = presentaionImageDao.getDefaultPlaceHolder();
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String fileAccessUrl = resources.getMessage("nfs.file.access.url");

		if(defaultPaceHolder == null) {
			themeForm.setDefaultPlaceHolderAvailable(false);
		} else {
			themeForm.setDefaultPlaceHolderAvailable(true);
			themeForm.setThemes(themeDAO.getAllThemes());
			themeForm.setDefaultPlaceHolder(defaultPaceHolder);
		}
		
		ERegisterBackgroundThemeDAO backgroundThemeDao = new ERegisterBackgroundThemeDAO(DAO.DB_FDMSCOMMON);
		
		themeForm.setBackgroundThemes(backgroundThemeDao.getAllBackgroundThemes());
		
		ServiceScreenThemeDAO serviceScreenDao = new ServiceScreenThemeDAO(DAO.DB_FDMSCOMMON);
		
		themeForm.setServiceScreenThemes(serviceScreenDao.getAllServiceScreenThemes());
		
		request.setAttribute("imageAccessUrl", fileAccessUrl);

		return mapping.findForward("showERegisterTheme");
	}

}
