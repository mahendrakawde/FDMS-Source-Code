package fdms.ui.struts.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.dao.ERegisterImageMappingDAO;
import com.aldorassist.webfdms.dao.ERegisterThemeDAO;
import com.aldorassist.webfdms.dao.PresentationImageDAO;
import com.aldorassist.webfdms.model.ERegisterThemeDTO;
import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.PresentationImageForm;
import fdms.util.FileUtil;

public class ProcessThemePresentationImage extends Action {
	
	private Logger logger = Logger.getLogger(ProcessThemePresentationImage.class.getName());
	
	// TODO Validation portion is still remaining. Needs to check for empty image.
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PresentationImageForm imageForm = (PresentationImageForm) form;

		try {
			if(imageForm.getOperation().equalsIgnoreCase("add")) {
				uploadThemeImage(imageForm);
			} else if (imageForm.getOperation().equalsIgnoreCase("delete")) {
				deleteThemeImage(imageForm);
			}

		} catch (Exception ex) {
			logger.error("Error at ProcessThemePresentationImage:execute(): ", ex);
		}
		
		return mapping.findForward("showThemePresentationImage");
	}
	
	private void uploadThemeImage(PresentationImageForm imageForm) {
		FileOutputStream fileOutputStream = null;
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");

		String nfsDirectory = resources.getMessage("nfs.server.directory");
		int thumbnailDim = Integer.parseInt(resources.getMessage("thumbnail.image.width"));
		
		int guestBookImageHeight = Integer.parseInt(resources.getMessage("guestbook.image.height"));
		int guestBookImageWidth = Integer.parseInt(resources.getMessage("guestbook.image.width"));
		
		try {
			ERegisterThemeDAO themeDao = new ERegisterThemeDAO(DAO.DB_FDMSCOMMON);

			ERegisterThemeDTO themeDto = themeDao.getThemeById(imageForm.getThemeId());
			
			PresentationImageDAO imageDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
			
			PresentationImageDTO imageDto = new PresentationImageDTO();
			
			long imageNumber = imageDao.getLatestImageNumberByTheme(imageForm.getThemeId());
			
			if(imageNumber >= 1) {
				imageNumber += 1;
			} else {
				imageNumber = 1;
			}

			String fileNameToUpload = "presentation-photo-" + themeDto.getThemeDesc() + "-" + imageNumber + ".jpg";
			
			String pathToWrite = FileUtil.pathForPresentationImages(themeDto.getThemeDesc());
			
			File originalFileDirectory = new File(nfsDirectory + pathToWrite + "originalFile/");
			File thumbnailDirectory = new File(nfsDirectory + pathToWrite + "thumbnail/");
			File guestBookImageDirectory = new File(nfsDirectory + pathToWrite + "guestBook/");
			
			if(!originalFileDirectory.exists()) {
				originalFileDirectory.mkdirs();
			}
			if(!thumbnailDirectory.exists()) {
				thumbnailDirectory.mkdirs();
			}
			if(!guestBookImageDirectory.exists()) {
				guestBookImageDirectory.mkdirs();
			}
			
			byte[] fileContent = imageForm.getFile().getFileData();
	
			File outputImageFile = new File(originalFileDirectory, fileNameToUpload);
			File thumbnailImageFile = new File(thumbnailDirectory, fileNameToUpload);
			File guestBookImageFile = new File(guestBookImageDirectory, fileNameToUpload);
			
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
			
			imageDto.setFileName(fileNameToUpload);
			imageDto.setFilePath(FileUtil.pathForPresentationImages(themeDto.getThemeDesc()));
			imageDto.setImageNumber(imageNumber);
			imageDto.setThemeId(imageForm.getThemeId());
			
			imageDao.addPresentationImage(imageDto);
		} catch (Exception ex) {
			logger.error("Error at ProcessThemePresentationImage:execute(): ", ex);
		}
	}
	
	private void deleteThemeImage(PresentationImageForm form) {
		ERegisterImageMappingDAO imageMappingDao = new ERegisterImageMappingDAO(DAO.DB_FDMSSHARE);
		
		PresentationImageDAO imageDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
		
		PresentationImageDTO placeHolderDto = imageDao.getDefaultPlaceHolder();
		
		List<Long> mappingIds = imageMappingDao.getMappingIdsByImageIdAndUploadFlag(form.getImageId(), false);
		
		imageMappingDao.updateImageIdsToDefaultPlaceHolderByMappingIds(mappingIds, placeHolderDto.getFileId());
		
		imageDao.deletePresentationImage(form.getImageId());
	}

}
