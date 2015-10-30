package fdms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class CommonUtil {
	
	private static Logger logger = Logger.getLogger(CommonUtil.class.getName());
	
	public static boolean hasAnyUploadedImage(String[] presentationImageDetails) {
		boolean flag = false;
		
		String[] details;
		
		for(String imageDetail : presentationImageDetails) {
			details = imageDetail.split("\\|");
			
			if(details.length > 2) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	public static long getThemeId(List<Object> images) {
		long themeId = 0L;
		
		for(Object image : images ) {
			if(image instanceof PresentationImageDTO) {
				PresentationImageDTO imageDto = (PresentationImageDTO) image;
				
				themeId = imageDto.getThemeId();
				
				break;
			}
		}
		
		return themeId;
	}

	/**
	 * Generate QR code for the case Id passed to this method. It will bind
	 * front end url of eRegister Book.
	 * 
	 * @param sessionUser
	 * @param caseId
	 * @param targetUrl
	 * @return
	 */
	public static String generateQrCode(DbUserSession sessionUser, int caseId) {
		
		MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String nfsDirectory = resources.getMessage("nfs.server.directory");
		String eregisterMobiUrl = resources.getMessage("eregister.mobi.url");
		
		int thumbnailDim = Integer.parseInt(resources.getMessage("thumbnail.image.width"));
		// int originalDim = Integer.parseInt(resources.getMessage("qrcode.image.size"));
		
		String pathToWrite = FileUtil.getPathForQRCode(sessionUser);
		
		String qrCodeName = "qrcode.jpg";
		
		String text = eregisterMobiUrl + "?CompanyId=" + sessionUser.getCompanyID() + "&CaseId=" + caseId;
		
		// Image type can be jpeg, gif, tiff
		String imageFormat = "png";
		
		try {
			File thumbnailDirectory = new File(nfsDirectory + pathToWrite);
			
			if(!thumbnailDirectory.exists()) {
				thumbnailDirectory.mkdirs();
			}
			
			File thumbnailImageFile = new File(thumbnailDirectory, qrCodeName);
			
			if(thumbnailImageFile.exists()) {
				thumbnailImageFile.delete();
			}
	        
	        FileOutputStream thumbnailImage = new FileOutputStream(thumbnailImageFile);

	        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, thumbnailDim, thumbnailDim);
	        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, thumbnailImage);

	        thumbnailImage.close();
		} catch (Exception ex) {
			logger.error("Error at CommonUtil:generateQrCode(): ", ex);
		}
		
		return pathToWrite;
	}

}
