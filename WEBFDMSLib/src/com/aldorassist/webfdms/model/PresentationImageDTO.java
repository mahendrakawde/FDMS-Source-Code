package com.aldorassist.webfdms.model;

/**
 * 
 * @author Parth
 * 
 * This DTO will contains default images stored in FDMS
 *
 */

public class PresentationImageDTO {

	private long fileId;
	private String fileName;
	private String filePath;
	private long imageNumber;
	private Long themeId;

	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public long getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(long imageNumber) {
		this.imageNumber = imageNumber;
	}

	public Long getThemeId() {
		return themeId;
	}
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
}
