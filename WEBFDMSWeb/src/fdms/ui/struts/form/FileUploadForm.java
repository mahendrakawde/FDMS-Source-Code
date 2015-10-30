package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * @author Parth
 *
 */
public class FileUploadForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private FormFile file;
    private String pathOnServer;
    private String fileName;
    private int fileOrder;
    
    private int horizontalStartPoint;
    private int verticalStartPoint;
    private int horizontalEndPoint;
    private int verticalEndPoint;
    private int height;
    private int width;
    
    private String imageOperation;
    private String callingId;
    
	private int resizeHeight;
	private int resizeWidth;
	private int resizePercent;
	
    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	public String getPathOnServer() {
		return pathOnServer;
	}
	public void setPathOnServer(String pathOnServer) {
		this.pathOnServer = pathOnServer;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getFileOrder() {
		return fileOrder;
	}
	public void setFileOrder(int fileOrder) {
		this.fileOrder = fileOrder;
	}

	public int getHorizontalStartPoint() {
		return horizontalStartPoint;
	}
	public void setHorizontalStartPoint(int horizontalStartPoint) {
		this.horizontalStartPoint = horizontalStartPoint;
	}
	
	public int getVerticalStartPoint() {
		return verticalStartPoint;
	}
	public void setVerticalStartPoint(int verticalStartPoint) {
		this.verticalStartPoint = verticalStartPoint;
	}
	
	public int getHorizontalEndPoint() {
		return horizontalEndPoint;
	}
	public void setHorizontalEndPoint(int horizontalEndPoint) {
		this.horizontalEndPoint = horizontalEndPoint;
	}
	
	public int getVerticalEndPoint() {
		return verticalEndPoint;
	}
	public void setVerticalEndPoint(int verticalEndPoint) {
		this.verticalEndPoint = verticalEndPoint;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public String getImageOperation() {
		return imageOperation;
	}
	public void setImageOperation(String imageOperation) {
		this.imageOperation = imageOperation;
	}

	public String getCallingId() {
		return callingId;
	}
	public void setCallingId(String callingId) {
		this.callingId = callingId;
	}

	public int getResizeHeight() {
		return resizeHeight;
	}
	public void setResizeHeight(int resizeHeight) {
		this.resizeHeight = resizeHeight;
	}

	public int getResizeWidth() {
		return resizeWidth;
	}
	public void setResizeWidth(int resizeWidth) {
		this.resizeWidth = resizeWidth;
	}

	public int getResizePercent() {
		return resizePercent;
	}
	public void setResizePercent(int resizePercent) {
		this.resizePercent = resizePercent;
	}

}
