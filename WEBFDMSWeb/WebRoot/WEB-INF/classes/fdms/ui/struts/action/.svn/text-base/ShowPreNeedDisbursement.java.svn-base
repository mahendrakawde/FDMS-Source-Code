package fdms.ui.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbPreneedDisbursement;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PreNeedDisbursementForm;

/**
 * Workfile: ShowPreneedDisbursement.java
 * Date: Nov 11, 2005 11:51:34 AM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class ShowPreNeedDisbursement extends Action {
	
    private Logger logger = Logger.getLogger(ShowPreNeedDisbursement.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {    	
    	
    	logger.debug("Entering show preNeedDisbursement");
    	
    	String target = "preNeedDisbursement";
    	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
    	Integer disbursementIdI = (Integer) request.getAttribute("disbursementId");
    	Integer vitalsIdI = (Integer) request.getAttribute("vitalsId");
    	
    	int disbursementId = (disbursementIdI != null) ? disbursementIdI.intValue() : 0;
    	int vitalsId = (vitalsIdI != null) ? vitalsIdI.intValue() : 0;
    	logger.debug("VitalsId : " + vitalsId);
    	logger.debug("DisbursementId : " + disbursementId);
    	
    	PreNeedDisbursementForm form = new PreNeedDisbursementForm();
    	
    	if (disbursementId > 0) {
    		try {    			    		
    			DatabaseTransaction t = null;
    			
    			try {

        			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        			
        			DbPreneedDisbursement dbPreNeedDisbursement = 
        				FdmsDb.getInstance().getPreneedDisbursement(t, disbursementId);
        			
        			if (dbPreNeedDisbursement != null) {    				
        				form.setDisbursementId(Integer.toString(disbursementId));
        				form.setDirective("update");
        				form.setVitalsMasterKey(Integer.toString(dbPreNeedDisbursement.getVitalsId()));
        				form.setLabel(dbPreNeedDisbursement.getLabel());
        				form.setValue(FormatCurrency.toCurrency(dbPreNeedDisbursement.getValue()));	    				
        			}
        			
    			} finally {
    				if ( t != null ) {
    					t.closeConnection();
    				}
    			}
    			    			
    		} catch (Exception e) {
    			logger.error("Exception in perform() : ", e);
    		}
    	} else {
			form.setVitalsMasterKey(Integer.toString(vitalsId));
			form.setValue(FormatCurrency.toCurrency(0.0d));	
			form.setDirective("add");
		}
    	
    	request.setAttribute(mapping.getName(), form);
    	
    	return mapping.findForward(target);
    }

}
