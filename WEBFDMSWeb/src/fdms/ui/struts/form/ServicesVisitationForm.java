package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ServicesVisitationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2024878938482638065L;

	private Logger logger = Logger.getLogger(ServicesVisitationForm.class.getName());

	private int visitationID;

	private int vitalsMasterKey;

	private String place;

	private String room;

	private String address;

	private String address2;

	private String city;

	private String state;

	private String zip;

	private String date;

	private String startTime;

	private String endTime;

	private String privateVisitation = "No";

	private String notes;

	private char status;

	private String directive;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the directive
	 */
	public String getDirective() {
		return directive;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @return the privateVisitation
	 */
	public String getPrivateVisitation() {
		return privateVisitation;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @return the visitationID
	 */
	public int getVisitationID() {
		return visitationID;
	}

	/**
	 * @return the vitalsMasterKey
	 */
	public int getVitalsMasterKey() {
		return vitalsMasterKey;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param directive
	 *            the directive to set
	 */
	public void setDirective(String directive) {
		this.directive = directive;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @param logger
	 *            the logger to set
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @param privateVisitation
	 *            the privateVisitation to set
	 */
	public void setPrivateVisitation(String privateVisitation) {
		this.privateVisitation = privateVisitation;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * @param visitationID
	 *            the visitationID to set
	 */
	public void setVisitationID(int visitationID) {
		this.visitationID = visitationID;
	}

	/**
	 * @param vitalsMasterKey
	 *            the vitalsMasterKey to set
	 */
	public void setVitalsMasterKey(int vitalsMasterKey) {
		this.vitalsMasterKey = vitalsMasterKey;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request)
	{
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		this.setAddress(null);
		this.setAddress2(null);
		this.setCity(null);
		this.setDate(null);
		this.setEndTime(null);
		this.setNotes(null);
		this.setPlace(null);
		this.setPrivateVisitation(null);
		this.setRoom(null);
		this.setStartTime(null);
		this.setState(null);
		this.setStatus(' ');
		this.setZip(null);
	}
	
}