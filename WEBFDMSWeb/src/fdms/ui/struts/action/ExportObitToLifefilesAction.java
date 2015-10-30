package fdms.ui.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
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
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

public class ExportObitToLifefilesAction extends Action {
	
	private Logger logger = Logger.getLogger(ExportObitToLifefilesAction.class.getName());
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// ExportToLifefilesForm lifeFilesForm = (ExportToLifefilesForm) actionForm;
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		
		DatabaseTransaction t = null;
		
		int vitalsId = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		
		PrintWriter pw = response.getWriter();
		
		try {
			
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			
			int companyId = sessionUser.getCompanyID();
			
			LifeFilesDAO lifeFilesDAO = new LifeFilesDAO(sessionUser);
			LifeFilesDTO lifeFilesData = lifeFilesDAO.getLifeFilesRecords(vitalsId,companyId);
			
			SecurityManagerBean smBean = new SecurityManagerBean();
	        SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
	        
	        if(lifeFilesData == null) {
				lifeFilesData = new LifeFilesDTO();
			}
			
			lifeFilesData.setDateofBirth(FormatDate.convertMMddyyyyToYYYY_MM_DD(lifeFilesData.getDateofBirth()));
			lifeFilesData.setDateofDied(FormatDate.convertMMddyyyyToYYYY_MM_DD(lifeFilesData.getDateofDied()));
			
			lifeFilesDAO = new LifeFilesDAO(DAO.DB_LIFE_FILES);
			
			String lifeFilesUserName = security.getLifefilesUserName();
			
			if(lifeFilesUserName == null) {
				pw.write("Lifefiles not configured from Admin. Please contact Fdms Admin.|false");
				
				return actionMapping.findForward(null);
			}
			
			long lifeFilesClientId = lifeFilesDAO.getLifefilesClientId(lifeFilesUserName);
			
			if(lifeFilesClientId == 0) {
				pw.write("Your Lifefiles account is not configured yet.|false");
				
				return actionMapping.findForward(null);
			}
			
			lifeFilesData.setClientId(lifeFilesClientId);
			
			ERegisterImageMappingDAO imageMappingDao = new ERegisterImageMappingDAO(DAO.DB_FDMSSHARE);
			
			List<ERegisterImageMappingDTO> imageMappingDtoList = imageMappingDao.getImagesByCaseAndCategory(vitalsId, companyId, Constants.IMAGE_TYPE_DECEDENT);
			
			String message = "";
			
			if(imageMappingDtoList == null) {
				message = "No eRegister Book entry for this case. Obituary Exported.";
			}
			
			lifeFilesData.setFdmsCompanyId(companyId);
			
			if(imageMappingDtoList != null) {
				for(ERegisterImageMappingDTO imageMappingDto : imageMappingDtoList) {
					
					lifeFilesData.setDecedentId(imageMappingDto.getCaseId());
					
					if(imageMappingDto.isUploadedImageFlag()) {
						// find image from upload table
						UploadedFileDAO uploadedFileDao = new UploadedFileDAO(DAO.DB_FDMSSHARE);
						
						UploadedFileDTO uploadedFile = uploadedFileDao.getFileById(imageMappingDto.getImageId());
						
						uploadImageToLifefiles(uploadedFile, imageMappingDto, lifeFilesData, lifeFilesUserName);
					} else {
						PresentationImageDAO presentationDao = new PresentationImageDAO(DAO.DB_FDMSCOMMON);
						
						PresentationImageDTO presentationImageDto = presentationDao.getPresentationImage(imageMappingDto.getImageId());
						
						uploadImageToLifefiles(presentationImageDto, imageMappingDto, lifeFilesData, lifeFilesUserName);
						// get another connection with commondata database & get image from presentation images. 
					}
				}
				
				message = "Exported Successfully.";
			}
			
			int broadcastsId;
			
			if(lifeFilesDAO.checkAddOrUpdate(vitalsId, companyId)) {
				broadcastsId = lifeFilesDAO.updateLifeFiles(lifeFilesData);
			}else{
				broadcastsId = lifeFilesDAO.addLifeFiles(lifeFilesData);
			}
			
			String obitConnectionURL = security.getObitConnectURL() + broadcastsId;
			
			pw.write(message + "|true|" + obitConnectionURL);
			
		} catch (PersistenceException e) {
			logger.error("PersistenceException in ExportObitToLifefilesAction:execute() : ", e);
			
			pw.write("Export Failed.|false");
		} catch (Exception ex) {
			logger.error("Exception in ExportObitToLifefilesAction:execute() : ", ex);
			
			pw.write("Export Failed.|false");
		} finally {
			if (t != null)
				t.closeConnection();
		}
		
		return actionMapping.findForward(null);
	}
	
	private void uploadImageToLifefiles(Object imageDto, ERegisterImageMappingDTO imageMappingDto, LifeFilesDTO lifeFilesData, String lifeFilesClientName) {
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");

		String sourceNfsDir = resources.getMessage("nfs.server.directory");
		String destinationNFsDirectory = resources.getMessage("nfs.lifefiles.images.directory");
		
		String photoFullFileName = "";
		String originalFullPhotoFileName = "";
		
		try {
			
			FileInputStream photoFileContentStream;
			FileInputStream originalPhotoFileContentStream;
			
			FileOutputStream fileToWrite;
			
			PresentationImageDTO presentationDto;
			UploadedFileDTO uploadedFileDto;
			
			byte[] photoContent;
			byte[] originalPhotoContent;
			
			String[] photoFileName;
			String[] originalPhotoFileName;
			
			File photoFile;
			File originalPhotoFile;
			
			if(imageDto instanceof PresentationImageDTO) {
				presentationDto = (PresentationImageDTO) imageDto;
				
				photoFile = new File(sourceNfsDir + presentationDto.getFilePath() + "guestBook/" + presentationDto.getFileName());
				originalPhotoFile = new File(sourceNfsDir + presentationDto.getFilePath() + "originalFile/" + presentationDto.getFileName());
				
				photoFileContentStream = new FileInputStream(photoFile);
				originalPhotoFileContentStream = new FileInputStream(originalPhotoFile);
				
				photoFileName = presentationDto.getFileName().split("[/.]");
				originalPhotoFileName = presentationDto.getFileName().split("[/.]");
			} else {
				uploadedFileDto = (UploadedFileDTO) imageDto;
				
				photoFile = new File(sourceNfsDir + uploadedFileDto.getFilePath() + "guestBook/" + uploadedFileDto.getFileName());
				originalPhotoFile = new File(sourceNfsDir + uploadedFileDto.getFilePath() + "originalFile/" + uploadedFileDto.getFileName());
				
				photoFileContentStream = new FileInputStream(photoFile);
				originalPhotoFileContentStream = new FileInputStream(originalPhotoFile);
				
				photoFileName = uploadedFileDto.getFileName().split("[/.]");
				originalPhotoFileName = uploadedFileDto.getFileName().split("[/.]");
			}
			
			photoFileName[0] = "lifefiles" + "-decedent-" + imageMappingDto.getCaseId();
			originalPhotoFileName[0] = "lifefiles" + "-original-" + imageMappingDto.getCaseId();
			
			photoContent = new byte[(int) photoFile.length()];
			originalPhotoContent = new byte[(int) originalPhotoFile.length()];
			
			photoFileContentStream.read(photoContent);
			originalPhotoFileContentStream.read(originalPhotoContent);
			
			File destinationDir = new File(destinationNFsDirectory + lifeFilesClientName + "/");
			
			if(!destinationDir.exists()) {
				destinationDir.mkdirs();
			}
			
			photoFullFileName = StringUtils.join(photoFileName, ".");
			
			fileToWrite = new FileOutputStream(new File(destinationDir, photoFullFileName));
			
			fileToWrite.write(photoContent);
			
			fileToWrite.flush();
			fileToWrite.close();
			
			originalFullPhotoFileName = StringUtils.join(originalPhotoFileName, ".");
			
			fileToWrite = new FileOutputStream(new File(destinationDir, originalFullPhotoFileName));
			
			fileToWrite.write(originalPhotoContent);
			
			fileToWrite.flush();
			fileToWrite.close();
			
			lifeFilesData.setPhotoName(photoFullFileName);
			lifeFilesData.setOriginalPhoto(originalFullPhotoFileName);
			
		} catch (Exception ex) {
			logger.error("Error at ExportObitToLifefilesAction:uploadImageToLifefiles(): ", ex);
		}
	}

}
