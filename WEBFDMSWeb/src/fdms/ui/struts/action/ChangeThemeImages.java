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

import fdms.ui.struts.form.ERegisterBookForm;

public class ChangeThemeImages extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ERegisterBookForm eRegisterForm = (ERegisterBookForm) form;
		
		PresentationImageDAO imageDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
		
		int noOfImagesReq = 6;
		
		eRegisterForm.setImageLibrary(imageDao.getRandomImages(eRegisterForm.getThemeId(), noOfImagesReq));
		
		eRegisterForm.setLogoMap(null);
		eRegisterForm.setDecedentMap(null);
		eRegisterForm.setPresentationMap(null);
		
		eRegisterForm.setPresentationImage(null);
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String imageAccessUrl = resources.getMessage("nfs.file.access.url");
		
		request.setAttribute("imageAccessUrl", imageAccessUrl);
		
		return mapping.findForward("showImages");
	}
}
