/*
 * ProcessLocaleForm.java
 *
 * Created on February 5, 2005, 2:20 PM
 */

package com.aldorsolutions.webfdms.sms.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.locale.form.LocaleForm;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.reporting.dao.SMSStoreProcDAO;
import com.aldorsolutions.webfdms.sms.form.SMSForm;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatDate;

/**
 * 
 * @author drollins
 *
 */
public class ProcessSMSForm extends Action {
    
    private Logger logger = Logger.getLogger(ProcessSMSForm.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    	String target = "showSMSDo";
        SMSForm form = (SMSForm) actionForm;
        String message = "";
        ActionErrors errors = new ActionErrors();
        MessageResources resources = this.getResources(request);
        ArrayList <SMSSchedulingDTO> smsRecs = new ArrayList <SMSSchedulingDTO>();
        
        setForm(form, smsRecs,errors);
        
        SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
        SMSSchedulingDTO smsScheduling = new SMSSchedulingDTO();
        
        try {
        	for (SMSSchedulingDTO smsRec: smsRecs){
        		smsScheDAo.addSMSCommonScheduling(smsRec);
        	}
		} catch (SQLException e) {

			e.printStackTrace();
			errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.companyForm.added"));
		} catch (Exception e) {

			e.printStackTrace();
			errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.companyForm.added"));
		}
        
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            target = "smsFormJsp";
        }
        
        return mapping.findForward(target);
    }


	private void setForm(SMSForm form, ArrayList<SMSSchedulingDTO> smsRecs, ActionErrors errors) {

		Date rDate = new Date();
		try {
			rDate = FormatDate.convertToDate(form.getRunDate());
		} catch (Exception e) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.date.rundate"));
			formErrors.add("runDate");
		}
		for(int i = 0; i <=form.getRepeatNumber(); i++){
			SMSSchedulingDTO smsRec = new SMSSchedulingDTO();
			smsRec.setLocaleId(0);
			smsRec.setLocationId(0);			
			try {
				smsRec.setRunDate(new java.sql.Date(rDate.getTime()));
			} catch (Exception e) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.date.runDate"));
				formErrors.add("runDate");
			}
			
	//		smsRec.setFromDate(form.getFromDate());
	//		smsRec.setToDate(form.getToDate());
			smsRec.setRunTimeHH(form.getHH());
			smsRec.setRunTimeMM(0);
			
			smsRec.setRepeatNumber(form.getRepeatNumber());
			long createdTimestamp = System.currentTimeMillis();
			
			smsRec.setDatetime(createdTimestamp);
			smsRec.setStartDateTime(createdTimestamp);
	//		smsRec.setStopDateTime(createdTimestamp);
			smsRec.setCountryCode(form.getCountryCode());
			smsRec.setAreaCode(form.getAreaCode());
			smsRec.setPhone(form.getPhonenumber());
	//		smsRec.setSqlScript(form.getSmsID());
			smsRec.setStartMessage(form.getStartMessage());
			smsRec.setEndMessage(form.getEndMessage());
			smsRec.setStatus("Q");
			smsRec.setRunType("S");
			smsRec.setUserId(0);
			smsRec.setFirstname(form.getFirstname());
			smsRec.setLastname(form.getLastname());
			smsRec.setSentMessage("");
			smsRec.setCompanyId(form.getCompanyID());
			
			CompanyManagerBean cmBean = new CompanyManagerBean();
	        CompanyDTO company = cmBean.getCompany(form.getCompanyID());
	        if (company != null) {  
	        	smsRec.setDataURL(company.getDataURL());
	        	smsRec.setDbLookup(company.getDbLookup());
	        	smsRec.setCompanyName(company.getName());
	        }
	        else {
	        	smsRec.setDataURL("");
	        	smsRec.setDbLookup("");
	        	smsRec.setCompanyName("");
	        }
	        smsRec.setSMSSqlId(form.getSmsID());
	        
	        smsRec.setRepeatType(form.getRepeatType());
	        if (form.getRepeatType().compareToIgnoreCase("D")==0){
	        	rDate = FormatDate.addToDate(rDate, 1, 0);
	        }
	        else if (form.getRepeatType().compareToIgnoreCase("W")==0){
	        	rDate = FormatDate.addToDate(rDate, 7, 0);
	        }
	        else if (form.getRepeatType().compareToIgnoreCase("M")==0) {
	        	rDate = FormatDate.addToDate(rDate, 0, 1);
	        }
	        smsRecs.add(smsRec);
		}
	}
    
 
    
}
