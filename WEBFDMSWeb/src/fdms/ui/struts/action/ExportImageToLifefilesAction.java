package fdms.ui.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.dao.ERegisterImageMappingDAO;
import com.aldorassist.webfdms.dao.LifeFilesDAO;
import com.aldorassist.webfdms.dao.PresentationImageDAO;
import com.aldorassist.webfdms.dao.UploadedFileDAO;
import com.aldorassist.webfdms.model.ERegisterImageMappingDTO;
import com.aldorassist.webfdms.model.LifeFilesDTO;
import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorassist.webfdms.model.UploadedFileDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.SessionHelpers;

public class ExportImageToLifefilesAction extends Action {
	
	private Logger logger = Logger.getLogger(ExportImageToLifefilesAction.class.getName());
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// ExportToLifefilesForm lifeFilesForm = (ExportToLifefilesForm) form;
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		
		DatabaseTransaction t = null;
		
		int vitalsId = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		
		PrintWriter pw = response.getWriter();
		
		try {
			
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			int companyId = sessionUser.getCompanyID();
			
			LifeFilesDTO lifeFilesData;
			
			LifeFilesDAO lifeFilesDAO = new LifeFilesDAO(DAO.DB_LIFE_FILES);
			
			SecurityManagerBean smBean = new SecurityManagerBean();
	        SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
			
			String lifeFilesUserName = security.getLifefilesUserName();
			
			if(lifeFilesUserName == null) {
				pw.write("Lifefiles not configured from Admin. Please contact Fdms Admin.|false");
				
				return super.execute(mapping, form, request, response);
			}
			
			long lifeFilesClientId = lifeFilesDAO.getLifefilesClientId(lifeFilesUserName);
			
			if(lifeFilesClientId == 0) {
				pw.write("Your Lifefiles account is not configured yet.|false");
				
				return super.execute(mapping, form, request, response);
			}
			
			
			
			ERegisterImageMappingDAO imageMappingDao = new ERegisterImageMappingDAO(DAO.DB_FDMSSHARE);
			
			List<ERegisterImageMappingDTO> imageMappingDtoList = imageMappingDao.getImagesByCaseAndCategory(vitalsId, companyId, Constants.IMAGE_TYPE_PRESENTATION);
			
			if(imageMappingDtoList == null) {
				pw.write("Please save eRegister Book first.|false");
				
				return super.execute(mapping, form, request, response);
			}
			
			for(ERegisterImageMappingDTO imageMappingDto : imageMappingDtoList) {
				lifeFilesData = new LifeFilesDTO();
				
				lifeFilesData.setClientId(lifeFilesClientId);
				lifeFilesData.setFdmsCompanyId(imageMappingDto.getCompanyId());
				lifeFilesData.setDecedentId(imageMappingDto.getCaseId());
				lifeFilesData.setImageOrder(imageMappingDto.getImageOrder());
				lifeFilesData.setCaption(null);
				
				String fileFullName;
				
				if(imageMappingDto.isUploadedImageFlag()) {
					// find image from upload table
					UploadedFileDAO uploadedFileDao = new UploadedFileDAO(DAO.DB_FDMSSHARE);
					
					UploadedFileDTO uploadedFile = uploadedFileDao.getFileById(imageMappingDto.getImageId());
					
					fileFullName = uploadImageToLifefiles(uploadedFile, imageMappingDto, lifeFilesUserName);
				} else {
					PresentationImageDAO presentationDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
					
					PresentationImageDTO presentationImageDto = presentationDao.getPresentationImage(imageMappingDto.getImageId());
					
					fileFullName = uploadImageToLifefiles(presentationImageDto, imageMappingDto, lifeFilesUserName);
					// get another connection with commondata database & get image from presentation images. 
				}
				
				lifeFilesData.setPhotoName(fileFullName);
				
				if(lifeFilesDAO.isImageAlreadyExist(companyId, lifeFilesData.getDecedentId(), lifeFilesData.getImageOrder())) {
					lifeFilesDAO.updateImageToLifeFiles(lifeFilesData);
				} else {
					lifeFilesDAO.addImageToLifeFiles(lifeFilesData);
				}
			}
			
			pw.write("Exported Successfully.|true");
			
		} catch (PersistenceException e) {
			logger.error("PersistenceException in ExportImageToLifefilesAction:execute() : ", e);
			
			pw.write("Export Failed.|false");
		} catch (Exception ex) {
			logger.error("Exception in ExportImageToLifefilesAction:execute() : ", ex);
			
			pw.write("Export Failed.|false");
		} finally {
			if (t != null)
				t.closeConnection();
		}
		
		return super.execute(mapping, form, request, response);
	}
	
	private String uploadImageToLifefiles(Object imageDto, ERegisterImageMappingDTO imageMappingDto, String lifeFilesClientName) {
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");

		String sourceNfsDir = resources.getMessage("nfs.server.directory");
		String destinationNFsDirectory = resources.getMessage("nfs.lifefiles.images.directory");
		
		String fullFileName = "";
		
		try {
			
			FileInputStream fileContentStream;
			
			PresentationImageDTO presentationDto;
			UploadedFileDTO uploadedFileDto;
			
			byte[] fileContent;
			String[] fileName;
			
			File sourceFile;
			
			if(imageDto instanceof PresentationImageDTO) {
				presentationDto = (PresentationImageDTO) imageDto;
				
				sourceFile = new File(sourceNfsDir + presentationDto.getFilePath() + "guestBook/" + presentationDto.getFileName());
				
				fileContentStream = new FileInputStream(sourceFile);
				
				fileName = presentationDto.getFileName().split("[/.]");
			} else {
				uploadedFileDto = (UploadedFileDTO) imageDto;
				
				sourceFile = new File(sourceNfsDir + uploadedFileDto.getFilePath() + "guestBook/" + uploadedFileDto.getFileName());
				
				fileContentStream = new FileInputStream(sourceFile);
				
				fileName = uploadedFileDto.getFileName().split("[/.]");
			}
			
			fileName[0] = "lifefiles" + "-" + imageMappingDto.getCaseId() + "-" + imageMappingDto.getImageOrder();
			
			fileContent = new byte[(int) sourceFile.length()];
			
			fileContentStream.read(fileContent);
			
			File destinationDir = new File(destinationNFsDirectory + lifeFilesClientName + "/");
			
			if(!destinationDir.exists()) {
				destinationDir.mkdirs();
			}
			
			fullFileName = StringUtils.join(fileName, ".");
			
			FileOutputStream fileToWrite = new FileOutputStream(new File(destinationDir, fullFileName));
			
			fileToWrite.write(fileContent);
			
			fileToWrite.flush();
			fileToWrite.close();
			
		} catch (Exception ex) {
			logger.error("Error at ExportImageToLifefilesAction:uploadImageToLifefiles(): ", ex);
		}
		
		return fullFileName;
	}

}
