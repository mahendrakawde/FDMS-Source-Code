package fdms.ui.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorassist.webfdms.model.UploadedFileDTO;

import fdms.ui.struts.form.ERegisterBookForm;

public class ChangeImageOrder extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ERegisterBookForm registerForm = (ERegisterBookForm) form;
		
		System.out.println("Image Order Direction: " + registerForm.getOrderDirection());
		System.out.println("imageToReorder: " + registerForm.getImageToReorder());
		
		registerForm.setPresentationMap(registerForm.getPresentationMap());
		
		List<Object> imageLibrary = registerForm.getImageLibrary();
		
		Object imageBeingReorder = imageLibrary.get(registerForm.getImageToReorder());
		
		if(imageBeingReorder instanceof PresentationImageDTO) {
			PresentationImageDTO presentationDto = (PresentationImageDTO) imageBeingReorder;
			
			//logoImageMappingDto.setImageId(presentationDto.getFileId());
		} else if(imageBeingReorder instanceof UploadedFileDTO) {
			UploadedFileDTO uploadedFileDto = (UploadedFileDTO) imageBeingReorder;
			
			//logoImageMappingDto.setImageId(uploadedFileDto.getFileId());
		}
		
		int imageToReorder = registerForm.getImageToReorder();
		
		Object image = imageLibrary.get(imageToReorder);
		
		if(registerForm.getOrderDirection().equalsIgnoreCase("right")) {
			Object nextImage = imageLibrary.get(imageToReorder + 1);
			
			imageLibrary.set(imageToReorder, nextImage);
			imageLibrary.set(imageToReorder + 1, image);
		} else {
			Object previousImage = imageLibrary.get(imageToReorder - 1);
			
			imageLibrary.set(imageToReorder, previousImage);
			imageLibrary.set(imageToReorder - 1, image);
		}
		
		registerForm.setImageLibrary(imageLibrary);
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String imageAccessUrl = resources.getMessage("nfs.file.access.url");
		
		request.setAttribute("imageAccessUrl", imageAccessUrl);
		
		return mapping.findForward("reOrder");
	}

}
