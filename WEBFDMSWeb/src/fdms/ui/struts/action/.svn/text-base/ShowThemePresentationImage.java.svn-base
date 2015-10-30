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

import com.aldorassist.webfdms.dao.PresentationImageDAO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.PresentationImageForm;

public class ShowThemePresentationImage extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PresentationImageForm imageForm = (PresentationImageForm) form;
		
		Long themeId = null;
		
		try {
			themeId = Long.parseLong(request.getParameter("themeId"));
		} catch (NumberFormatException e) {
			if(e.getMessage().equals("null")) {
				themeId = imageForm.getThemeId();
			}
		}
		
		PresentationImageDAO presentationImageDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
		
		imageForm.setThemeId(themeId);

		imageForm.setPresentationImages(presentationImageDao.getPresentationImageByTheme(themeId));
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String imageAccessUrl = resources.getMessage("nfs.file.access.url");
		
		request.setAttribute("imageAccessUrl", imageAccessUrl);
		
		return mapping.findForward("showThemePresentationImages");
	}

}
