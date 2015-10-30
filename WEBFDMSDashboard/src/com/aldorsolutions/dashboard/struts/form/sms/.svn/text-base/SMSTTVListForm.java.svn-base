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

import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorassist.webfdms.model.SMSStoreProcDTO;

/**
 * 
 * @author drollins
 *
 */
public class SMSTTVListForm extends ActionForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -770369556068722685L;

	private ArrayList <SMSSchedulingDTO> smss;
	private int schedulingID;
	private String direction;
	private String sendType;
	private String msgAvailable;
	
	
	public String getMsgAvailable() {
		return msgAvailable;
	}

	public void setMsgAvailable(String msgAvailable) {
		this.msgAvailable = msgAvailable;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getSchedulingID() {
		return schedulingID;
	}

	public void setSchedulingID(int schedulingID) {
		this.schedulingID = schedulingID;
	}

	public ArrayList<SMSSchedulingDTO> getSmss() {
		return smss;
	}

	public void setSmss(ArrayList<SMSSchedulingDTO> smss) {
		this.smss = smss;
	}

	/**
	 * 
	 */


	private Logger logger = Logger.getLogger(SMSTTVListForm.class.getName());
    



	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	
		
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ArrayList formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        
       
        
        return errors;
    }

	
}
