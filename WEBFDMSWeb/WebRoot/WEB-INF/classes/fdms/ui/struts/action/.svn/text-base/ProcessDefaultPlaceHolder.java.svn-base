package fdms.ui.struts.action;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.dao.PresentationImageDAO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.ERegisterThemeForm;
import fdms.util.FileUtil;

public class ProcessDefaultPlaceHolder extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ERegisterThemeForm fileUploadForm = (ERegisterThemeForm) form;
		FormFile formFile = null;

        FileOutputStream fileOutputStream = null;

        MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String nfsDirectory = resources.getMessage("nfs.server.directory");
		int thumbnailDim = Integer.parseInt(resources.getMessage("thumbnail.image.width"));
		
		int guestBookImageHeight = Integer.parseInt(resources.getMessage("guestbook.image.height"));
		int guestBookImageWidth = Integer.parseInt(resources.getMessage("guestbook.image.width"));
		
        try {
        	formFile = fileUploadForm.getFile();
        	
        	String pathOfPlaceHolder = FileUtil.pathForDefaultPlaceHolder();
            
            String placeHolderName = "eregister-placeholder.jpg";
            
            File originalFileDirectory = new File(nfsDirectory + pathOfPlaceHolder + "originalFile/");
			File thumbnailDirectory = new File(nfsDirectory + pathOfPlaceHolder + "thumbnail/");
			File guestBookImageDirectory = new File(nfsDirectory + pathOfPlaceHolder + "guestBook/");
			
			if(!originalFileDirectory.exists()) {
				originalFileDirectory.mkdirs();
			}
			if(!thumbnailDirectory.exists()) {
				thumbnailDirectory.mkdirs();
			}
			if(!guestBookImageDirectory.exists()) {
				guestBookImageDirectory.mkdirs();
			}
			
			byte[] fileContent = formFile.getFileData();
	
			File outputImageFile = new File(originalFileDirectory, placeHolderName);
			File thumbnailImageFile = new File(thumbnailDirectory, placeHolderName);
			File guestBookImageFile = new File(guestBookImageDirectory, placeHolderName);
			
			if(outputImageFile.exists()) {
				outputImageFile.delete();
			}
			if(thumbnailImageFile.exists()) {
				outputImageFile.delete();
			}
			if(guestBookImageFile.exists()) {
				outputImageFile.delete();
			}

			fileOutputStream = new FileOutputStream(outputImageFile);

			// Write cropped image to NFS.
			fileOutputStream.write(fileContent);

			fileOutputStream.close();
			
			fileOutputStream = new FileOutputStream(thumbnailImageFile);
			fileOutputStream.write(FileUtil.resizeImage(fileContent, thumbnailDim, thumbnailDim));
			fileOutputStream.close();
			
			fileOutputStream = new FileOutputStream(guestBookImageFile);
			fileOutputStream.write(FileUtil.resizeImage(fileContent, guestBookImageWidth, guestBookImageHeight));
			fileOutputStream.close();

            PresentationImageDAO imageDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
            
            imageDao.addPlaceHolder(placeHolderName, pathOfPlaceHolder);

        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
        	if(fileOutputStream != null) {
        		fileOutputStream.close();
        	}
        }
        
		return mapping.findForward("showERegisterTheme");
	}

}
