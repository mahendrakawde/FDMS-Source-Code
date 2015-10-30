package fdms.ui.struts.action;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.ui.struts.form.FileUploadForm;
import fdms.util.ImageUtil;

public class ImageEditorAction extends Action {
	
	private Logger logger = Logger.getLogger(ImageEditorAction.class.getName());
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FileUploadForm fileUploadForm = (FileUploadForm) form;
		
		/*MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String nfsDirectory = resources.getMessage("nfs.server.directory");*/

		try {
			// Here we will access path from root of the application. Because
			// the path here will be later appended by the actual path from
			// where the file is being read.
			String pathToRead = "/";

			String absoluteFileReadPath = getServlet().getServletConfig()
					.getServletContext().getRealPath(pathToRead);

			// Input file to get file with original name
			File inputImageFile = new File(absoluteFileReadPath + 
					 fileUploadForm.getFileName());
			
			String fileName = inputImageFile.getName();
			
			String[] splittedFileName = fileName.split("_____");
			
			String newFileName;
			
			if(splittedFileName.length > 1) {
				String[] intermediateSplittedFileName = splittedFileName[splittedFileName.length - 1].split("\\.");
				
				int counter = Integer.parseInt(intermediateSplittedFileName[intermediateSplittedFileName.length - 2]);
				
				intermediateSplittedFileName[intermediateSplittedFileName.length - 2] = "" + (++counter);
				
				splittedFileName[splittedFileName.length - 1] = StringUtils.join(intermediateSplittedFileName, ".");
				
				newFileName = StringUtils.join(splittedFileName, "_____");
			} else {
				splittedFileName = fileName.split("\\.");
				splittedFileName[splittedFileName.length - 2] = splittedFileName[splittedFileName.length - 2] + "_____1";
				
				newFileName = StringUtils.join(splittedFileName, ".");
			}

			splittedFileName = fileUploadForm.getFileName().split("/");
			splittedFileName[splittedFileName.length - 1] = newFileName;
			
			String newFilePath = StringUtils.join(splittedFileName, "/");
			
			File outputFile = new File(absoluteFileReadPath + newFilePath);
			
			BufferedImage originalImage = ImageIO.read(inputImageFile);
			
			BufferedImage processedImage = null;

			if(fileUploadForm.getImageOperation().equalsIgnoreCase("horizontalFlip")) {
				processedImage = ImageUtil.horizontalflip(originalImage);
				
				ImageIO.write(processedImage, "jpg", outputFile);
			} else if(fileUploadForm.getImageOperation().equalsIgnoreCase("verticalFlip")) {
				processedImage = ImageUtil.verticalflip(originalImage);
				
				ImageIO.write(processedImage, "jpg", outputFile);
			} else if(fileUploadForm.getImageOperation().equalsIgnoreCase("clockwiseRotation")) {
				processedImage  = ImageUtil.rotateImage(originalImage, 90.0);

	            ImageIO.write(processedImage, "jpg", outputFile);
			} else if(fileUploadForm.getImageOperation().equalsIgnoreCase("counterClockwiseRotation")) {
				processedImage  = ImageUtil.rotateImage(originalImage, -90.0);

	            ImageIO.write(processedImage, "jpg", outputFile);
			} else if(fileUploadForm.getImageOperation().equalsIgnoreCase("resize")) {
				//processedImage = ImageUtil.resizeImage(originalImage, fileUploadForm.getResizeHeight(), fileUploadForm.getResizeWidth());
				
				processedImage = ImageUtil.resizeImage(originalImage, fileUploadForm.getResizePercent());

	            ImageIO.write(processedImage, "jpg", outputFile);
			}

			if(inputImageFile.exists())
				inputImageFile.delete();
			
			request.setAttribute("uploadedPath", newFilePath);
			
			request.setAttribute("callingId", fileUploadForm.getCallingId());
			
			//fileUploadForm.setFileName(fileUploadForm.getFileName());

		} catch (Exception ex) {
			logger.error("Exception in FlipImageAction:execute()", ex);
		}
		
		return mapping.findForward("displayEdited");
	}

}
