package fdms.ui.struts.form;



import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbVisitations;

public class ObitAsimasForm extends ActionForm{
	
	public ObitAsimasForm(){
		
	}
	
	private DbServices srv = new DbServices();
	private DbVisitations visitation1 = new DbVisitations();
	private DbVisitations visitation2 = new DbVisitations();
	private DbVisitations visitation3 = new DbVisitations();
	
	
	private String firstName="";
	private String lastName="";
	private String dateOfBirth="";
	private String dateOfDeath="";
	private String serviceDate="";
	private String obituaryText="";
//	private String imageFile;.
	private String url="";
	private long asimasId ;
	private FormFile fileName;
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the dateOfDeath
	 */
	public String getDateOfDeath() {
		return dateOfDeath;
	}
	/**
	 * @param dateOfDeath the dateOfDeath to set
	 */
	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the obituaryText
	 */
	public String getObituaryText() {
		return obituaryText;
	}
	/**
	 * @param obituaryText the obituaryText to set
	 */
	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the serviceDate
	 */
	public String getServiceDate() {
		return serviceDate;
	}
	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	/**
	 * @return the srv
	 */
	public DbServices getSrv() {
		return srv;
	}
	/**
	 * @param srv the srv to set
	 */
	public void setSrv(DbServices srv) {
		this.srv = srv;
	}
	/**
	 * @return the visitation1
	 */
	public DbVisitations getVisitation1() {
		return visitation1;
	}
	/**
	 * @param visitation1 the visitation1 to set
	 */
	public void setVisitation1(DbVisitations visitation1) {
		this.visitation1 = visitation1;
	}
	/**
	 * @return the visitation2
	 */
	public DbVisitations getVisitation2() {
		return visitation2;
	}
	/**
	 * @param visitation2 the visitation2 to set
	 */
	public void setVisitation2(DbVisitations visitation2) {
		this.visitation2 = visitation2;
	}
	/**
	 * @return the visitation3
	 */
	public DbVisitations getVisitation3() {
		return visitation3;
	}
	/**
	 * @param visitation3 the visitation3 to set
	 */
	public void setVisitation3(DbVisitations visitation3) {
		this.visitation3 = visitation3;
	}
	/**
	 * @return the asimasId
	 */
	public long getAsimasId() {
		return asimasId;
	}
	/**
	 * @param asimasId the asimasId to set
	 */
	public void setAsimasId(long asimasId) {
		this.asimasId = asimasId;
	}
	/**
	 * @return the fileName
	 */
	public FormFile getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}

	
	
}
