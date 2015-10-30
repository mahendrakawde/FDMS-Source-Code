package com.aldorsolutions.dashboard.struts.form.sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.model.SMSStoreProcDTO;

/**
 * 
 * @author drollins
 *
 */
public class SMSTTVEditForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4733453717984189609L;


	private Logger logger = Logger.getLogger(SMSTTVEditForm.class.getName());
    

	private int localeID = 0;
	private int locationID = 0;
	private int smsID;
	private int[] recipientId;
	private HashMap smsNameMap = new HashMap();
	private String requestType;
	private String toDate ;  
	private String fromDate ;  
	private String runDate;
	private String endDate;
	private String recurrenceChoice;
	private String repeatType;
	private int repeatNumber;
	private int HH;
	private int MM;
	private int companyID = 0;
	private HashMap companyMap = new HashMap();
	private String firstname;
	private String lastname;
	private String areaCode;
	private String phonenumber;
	private String countryCode;
	private String startMessage;
	private String endMessage;
	private ArrayList <LabelValueBean> sqls = new ArrayList <LabelValueBean> ();
	private ArrayList <SMSStoreProcDTO> sqlStrings = new ArrayList <SMSStoreProcDTO> ();
	private ArrayList <LabelValueBean> recipients = new ArrayList <LabelValueBean> ();
	private String sendType;
	private String direction;
	private String greeting;
	private int sendToDefaultRecipient;  


	public int getSendToDefaultRecipient() {
		return sendToDefaultRecipient;
	}

	public void setSendToDefaultRecipient(int sendToDefaultRecipient) {
		this.sendToDefaultRecipient = sendToDefaultRecipient;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public int[] getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int[] recipientId) {
		this.recipientId = recipientId;
	}

	public ArrayList<LabelValueBean> getRecipients() {
		return recipients;
	}

	public void setRecipients(ArrayList<LabelValueBean> recipients) {
		this.recipients = recipients;
	}

	public ArrayList<SMSStoreProcDTO> getSqlStrings() {
		return sqlStrings;
	}

	public void setSqlStrings(ArrayList<SMSStoreProcDTO> sqlStrings) {
		this.sqlStrings = sqlStrings;
	}

	public ArrayList<LabelValueBean> getSqls() {
		return sqls;
	}

	public void setSqls(ArrayList<LabelValueBean> sqls) {
		this.sqls = sqls;
	}

	/**
	 * @return the localeID
	 */
	public int getLocaleID() {
		return localeID;
	}

	/**
	 * @param localeID the localeID to set
	 */
	public void setLocaleID(int localeID) {
		this.localeID = localeID;
	}

	/**
	 * @return the companyID
	 */
	public int getCompanyID() {
		return companyID;
	}
	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	/**
	 * @return the companyMap
	 */
	public HashMap getCompanyMap() {
		return companyMap;
	}
	/**
	 * @param companyMap the companyMap to set
	 */
	public void setCompanyMap(HashMap companyMap) {
		this.companyMap = companyMap;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	
		localeID = 0;
		companyID = 0;
		companyMap = new HashMap();
		smsNameMap = new HashMap();
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ArrayList formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public int getSmsID() {
		return smsID;
	}

	public void setSmsID(int smsID) {
		this.smsID = smsID;
	}

	public HashMap getSmsNameMap() {
		return smsNameMap;
	}

	public void setSmsNameMap(HashMap smsNameMap) {
		this.smsNameMap = smsNameMap;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
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

	public int getHH() {
		return HH;
	}

	public void setHH(int hh) {
		HH = hh;
	}

	public int getMM() {
		return MM;
	}

	public void setMM(int mm) {
		MM = mm;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRecurrenceChoice() {
		return recurrenceChoice;
	}

	public void setRecurrenceChoice(String recurrenceChoice) {
		this.recurrenceChoice = recurrenceChoice;
	}
  
}
