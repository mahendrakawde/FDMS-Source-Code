package com.aldorassist.webfdms.model;

import java.sql.Date;

public class SMSSchedulingDTO {

	private int schedulingID;
	private int localeId;
	private int locationId;
	private Date fromDate;
	private Date toDate;
	private Date runDate;
	private int runTimeHH;
	private int runTimeMM;
	private String repeatType;
	private int repeatNumber;
	private long datetime;
	private long startDateTime;
	private long stopDateTime;
	private String countryCode;
	private String areaCode;
	private String phone;
	private String sqlScript;
	private String startMessage;
	private String endMessage;
	private String status; // D = Delete, Q= Queue, S = Success/finish, E=Error.
	private String runType; // R = Realtime, S = Schedule
	private int userId;
	private String firstname;
	private String lastname;
	private String sentMessage;
	private int companyId;
	private String dataURL;
	private String dbLookup;
	private String companyName;
	private int SMSSqlId;
	private String sendType;

	
	
	public static final String DELETE  = "D";
	public static final String QUEUE  = "Q";
	public static final String SUCCESS  = "S";
	public static final String ERROR  = "E";
	
	public static final String REPORT_TYPE_REALTIME  = "R";
	public static final String REPORT_TYPE_SCHEDULING  = "S";
	public int getSchedulingID() {
		return schedulingID;
	}
	public void setSchedulingID(int schedulingID) {
		this.schedulingID = schedulingID;
	}

	public int getLocaleId() {
		return localeId;
	}
	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public int getRunTimeHH() {
		return runTimeHH;
	}
	public void setRunTimeHH(int runTimeHH) {
		this.runTimeHH = runTimeHH;
	}
	public int getRunTimeMM() {
		return runTimeMM;
	}
	public void setRunTimeMM(int runTimeMM) {
		this.runTimeMM = runTimeMM;
	}
	public String getRepeatType() {
		return repeatType;
	}
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}
	public int getRepeatNumber() {
		return repeatNumber;
	}
	public void setRepeatNumber(int repeatNumber) {
		this.repeatNumber = repeatNumber;
	}
	public long getDatetime() {
		return datetime;
	}
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	public long getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(long startDateTime) {
		this.startDateTime = startDateTime;
	}
	public long getStopDateTime() {
		return stopDateTime;
	}
	public void setStopDateTime(long stopDateTime) {
		this.stopDateTime = stopDateTime;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSqlScript() {
		return sqlScript;
	}
	public void setSqlScript(String sqlScript) {
		this.sqlScript = sqlScript;
	}
	public String getStartMessage() {
		return startMessage;
	}
	public void setStartMessage(String startMessage) {
		this.startMessage = startMessage;
	}
	public String getEndMessage() {
		return endMessage;
	}
	public void setEndMessage(String endMessage) {
		this.endMessage = endMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRunType() {
		return runType;
	}
	public void setRunType(String runType) {
		this.runType = runType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getSentMessage() {
		return sentMessage;
	}
	public void setSentMessage(String sentMessage) {
		this.sentMessage = sentMessage;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getDataURL() {
		return dataURL;
	}
	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}
	public String getDbLookup() {
		return dbLookup;
	}
	public void setDbLookup(String dbLookup) {
		this.dbLookup = dbLookup;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getSMSSqlId() {
		return SMSSqlId;
	}
	public void setSMSSqlId(int sqlId) {
		this.SMSSqlId = sqlId;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	
	
}