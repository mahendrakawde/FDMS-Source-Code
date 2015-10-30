package fdms.ui.struts.action;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import fdms.ui.struts.form.FileUploadForm;
import fdms.util.FileUtil;


/**
 * 
 * @author Parth
 * 
 * This action class will get the cropped image from UI & store it to
 * the NFS directory.
 *         
 * @since 2.9.7
 * 
 */

public class CropImageAction extends Action {
	
	private Logger logger = Logger.getLogger(CropImageAction.class.getName());

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FileUploadForm fileUploadForm = (FileUploadForm) form;

		FileOutputStream fileOutputStream = null;
		
		/*MessageResourcesFactory messageFactory = null;
    	messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		String nfsDirectory = resources.getMessage("nfs.server.directory");*/

		try {
			DbUserSession sessionUser = SessionHelpers.getUserSession(request);

			// Here we will access path from root of the application. Because
			// the path here will be later appended by the actual path from
			// where the file is being read.
			String pathToRead = "/";

			String absoluteFileReadPath = getServlet().getServletConfig()
					.getServletContext().getRealPath(pathToRead);

			// Input file to get file with original name
			File inputImageFile = new File(absoluteFileReadPath + 
					 fileUploadForm.getFileName());
			
			// String fileNameForFTP = "eregister-photo-" + ((fileUploadForm.getFileOrder() > 0) ? fileUploadForm.getFileOrder() : 0) + ".jpg";
			
			String pathToWrite = "/fdms/";
			//File directory = new File(nfsDirectory + FileUtil.pathForFileUpload(sessionUser));
			File directory = new File(getServlet().getServletConfig()
					.getServletContext().getRealPath(pathToWrite)
					+ "/" + FileUtil.pathForFileUpload(sessionUser));
			
			if(!directory.exists()) {
				directory.mkdirs();
			}

			FileInputStream fileInputStream = new FileInputStream(inputImageFile);

			byte[] fileContent = new byte[(int) inputImageFile.length()];

			fileInputStream.read(fileContent);

			fileInputStream.close();

			/*if(inputImageFile.delete()) {
				System.out.println("Temporary uploaded file deleted......");
			}*/
			
			// File outputImageFile = new File(directory, fileNameForFTP);
			
			File outputImageFile = new File(inputImageFile.getPath());

			fileOutputStream = new FileOutputStream(outputImageFile);

			// Write cropped image to local file system.
			
			byte[] croppedImage = resizeImageAsJPG(fileContent, fileUploadForm);
			fileOutputStream.write(croppedImage);

			fileOutputStream.close();
			
			if(!inputImageFile.exists()) {
				inputImageFile.delete();
			}

		} catch (Exception ex) {
			logger.error("Exception in CropImageAction:execute()", ex);
		}

		return super.execute(mapping, form, request, response);
	}

	/**
	 * This member function will take image as byte array & also takes FormBean
	 * to get values came from <b>HttpServletRequest</b> object. It will crop
	 * image as the dimension specified by the user when cropped.
	 * 
	 * @param pImageData
	 *            the image data.
	 * @param uploadForm
	 *            The form bean which contains Request data from the form.
	 * @return The resized JPG image.
	 * @throws IOException
	 *             If the image could not be manipulated correctly.
	 * 
	 */
	public byte[] resizeImageAsJPG(byte[] pImageData, FileUploadForm uploadForm)
			throws IOException {
		// Create an ImageIcon from image data
		ImageIcon imageIcon = new ImageIcon(pImageData);
		int width = imageIcon.getIconWidth();
		int height = imageIcon.getIconHeight();
		
		if(uploadForm.getWidth() <= 0) {
			uploadForm.setWidth(width);
			uploadForm.setHeight(height);
			uploadForm.setHorizontalStartPoint(0);
			uploadForm.setVerticalStartPoint(0);
			uploadForm.setVerticalEndPoint(height);
			uploadForm.setHorizontalEndPoint(width);
		}

		// Create a new empty image buffer to "draw" the resized image into
		BufferedImage bufferedResizedImage = new BufferedImage(
				uploadForm.getWidth(), uploadForm.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		// Create a Graphics object to do the "drawing"
		Graphics2D g2d = bufferedResizedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		// Draw the resized image
		g2d.drawImage(imageIcon.getImage(), 0, 0, uploadForm.getWidth(),
				uploadForm.getHeight(), uploadForm.getHorizontalStartPoint(),
				uploadForm.getVerticalStartPoint(),
				uploadForm.getHorizontalEndPoint(),
				uploadForm.getVerticalEndPoint(), null);
		g2d.dispose();

		// Now our buffered image is ready
		// Encode it as a JPEG
		ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
		encoder.encode(bufferedResizedImage);

		byte[] resizedImageByteArray = encoderOutputStream.toByteArray();

		return resizedImageByteArray;
	}

}
