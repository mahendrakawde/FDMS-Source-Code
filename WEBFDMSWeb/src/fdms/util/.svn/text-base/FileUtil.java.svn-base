package fdms.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author Parth
 * 
 * This class will contains all the utility methods related to Files.
 * 
 */

public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileUtil.class.getName());

	/**
	 * 
	 * This method will generate path for file to upload on NFS directory. Path
	 * will be generated based on some conventions. 
	 * 
	 * The convention here is:
	 * <b>
	 * NFS_DIRECTORY/<companyId>/<localeId>/<locationId (chapelId)>/<vitalId>/images/<fileName>
	 * </b>
	 * 
	 * @param sessionUser
	 * @return pathForFileUpload
	 * 
	 */
	public static String pathForFileUpload(DbUserSession sessionUser) {
		StringBuffer path = new StringBuffer();

		int companyId = sessionUser.getCompanyID();

		int vitalId = sessionUser.getCurrentCaseID();

		DatabaseTransaction t =null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			DbCase currentCase = FdmsDb.getInstance().getCase(t, vitalId);

			path.append(companyId).append("/");

			path.append(currentCase.getLocale()).append("/");

			path.append(currentCase.getChapelNumber()).append("/");

			path.append(vitalId).append("/images/");
		} catch (Exception ex) {
			logger.error("Error at FileUtil:pathForFileUpload(): ", ex);
		}finally{
			if(t!=null){
				t.closeConnection();
				t=null;
			}
		}

		return path.toString();
	}

	/**
	 * Returns path for presentation images to be stored based on theme name.
	 * There will be different directories for each themes & respective theme
	 * images will be stored in that directory.
	 * 
	 * @param themeName
	 * @return
	 */
	public static String pathForPresentationImages(String themeName) {
		StringBuffer path = new StringBuffer();
		
		path.append("common/presentationThemes/").append(themeName);
		path.append("/images/");
		
		return path.toString();
	}

	/**
	 * Returns path for default place holder image.
	 * 
	 * @return
	 */
	public static String pathForDefaultPlaceHolder() {
		String path = "";
		
		path += "common/defaultPlaceHolder/";
		
		return path;
	}

	/**
	 * Returns byte[], which represents image of size newWidth & newHeight. This
	 * method will be used to resize image.
	 * 
	 * @param imageData
	 * @param newWidth
	 * @param newHeight
	 * @return
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static byte[] resizeImage(byte[] imageData, int newWidth, int newHeight) throws ImageFormatException, IOException {
		ImageIcon imageIcon = new ImageIcon(imageData);
		int width = imageIcon.getIconWidth();
		int height = imageIcon.getIconHeight();
		
		System.out.println("imageIcon width: " + width + "  height: " + height);

		// Create a new empty image buffer to "draw" the resized image into
		BufferedImage bufferedResizedImage = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_RGB);

		// Create a Graphics object to do the "drawing"
		Graphics2D g2d = bufferedResizedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		// Draw the resized image
		g2d.drawImage(imageIcon.getImage(), 0, 0, newWidth, newHeight, null);
		g2d.dispose();
		
		// Now our buffered image is ready
		// Encode it as a JPEG
		ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec
				.createJPEGEncoder(encoderOutputStream);
		encoder.encode(bufferedResizedImage);
		
		byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
		
		return resizedImageByteArray;
	}

	/**
	 * Returns path for QRCode, for particular case & company.
	 * 
	 * @param sessionUser
	 * @return
	 */
	public static String getPathForQRCode(DbUserSession sessionUser) {
		StringBuffer path = new StringBuffer();

		int companyId = sessionUser.getCompanyID();

		int vitalId = sessionUser.getCurrentCaseID();
		DatabaseTransaction t = null;
		try {
			t= (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			DbCase currentCase = FdmsDb.getInstance().getCase(t, vitalId);

			path.append("qrcode/");
			path.append(companyId).append("/");

			path.append(currentCase.getLocale()).append("/");

			path.append(currentCase.getChapelNumber()).append("/");

			path.append(vitalId).append("/");
		} catch (Exception ex) {
			logger.error("Error at FileUtil:getPathForQRCode(): ", ex);
		}finally{
			if(t!=null){
				t.closeConnection();
				t=null;
			}
		}
		
		return path.toString();
	}

}
