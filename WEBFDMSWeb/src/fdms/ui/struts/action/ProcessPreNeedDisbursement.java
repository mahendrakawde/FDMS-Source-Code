package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbPreneedDisbursement;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.PreNeedDisbursementForm;

/**
 * Workfile: ProcessPreNeedDisbursement.java
 * Date: Nov 11, 2005 11:43:14 AM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class ProcessPreNeedDisbursement extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPreNeedDisbursement.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(
    		ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {    	
                
    	logger.debug("/** Entering ProcessPreNeedDisbursement **/");
    	PreNeedDisbursementForm form = (PreNeedDisbursementForm) actionForm; 
        ActionErrors errors = new ActionErrors();
        formErrors = new ArrayList();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        DatabaseTransaction t = null;        
        logger.debug("DisbursementId : " + form.getDisbursementId());
        logger.debug("Directive : " + form.getDirective());
        int disbursementId = FormatNumber.parseInteger(form.getDisbursementId());
        String directive = form.getDirective();
        TreeMap disbursementMap = (TreeMap) session.getAttribute(Constants.PRENEED_DISBURSEMENT_MAP);
        SessionHelpers.setVitalsIdInRequest(request, FormatNumber.parseInteger(form.getVitalsMasterKey()));
        
        if (disbursementMap != null) {        	
        
	        try {
		        if (disbursementId > 0) {
		        	if ("update".equals(directive)) {
		        		validate(formErrors, errors, form);
		        		if (!errors.isEmpty()) {
		        			request.setAttribute(mapping.getInput(), form);
		        			saveErrors(request, errors);
		        			request.setAttribute("formErrors", formErrors);
		        			return new ActionForward(mapping.getInput());
		        		}
		        		
		        		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);		        		
		        		DbPreneedDisbursement pnDisbursement = 
		    				FdmsDb.getInstance().getPreneedDisbursement(t, disbursementId);	
		        		pnDisbursement.setId(disbursementId);
		        		pnDisbursement.setDisbursementId(disbursementId);
		        		pnDisbursement.setLabel(form.getLabel());
		        		pnDisbursement.setValue(FormatCurrency.convertToCurrencyDbl(form.getValue()));
		        		t.addPersistent(pnDisbursement);
		        		t.save();
		        		disbursementMap.put(Integer.toString(disbursementId), pnDisbursement);
		        	} else if ("delete".equals(directive)) {		        		
		        		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		        		DbPreneedDisbursement pnDisbursement = new DbPreneedDisbursement();
		        		pnDisbursement.setId(disbursementId);
		        		pnDisbursement.remove();
		        		t.addPersistent(pnDisbursement);
		                t.save();
		                disbursementMap.remove(Integer.toString(disbursementId));
		        	}
		        } else {
		        	 if ("add".equals(directive)) {
		        		logger.debug("Adding preneed disbursement");
		        		
		        		validate(formErrors, errors, form);
		        		if (!errors.isEmpty()) {
		        			request.setAttribute(mapping.getInput(), form);
		        			saveErrors(request, errors);
		        			request.setAttribute("formErrors", formErrors);
		        			return new ActionForward(mapping.getInput());
		        		}
		        		
		        		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		        		DbPreneedDisbursement pnDisbursement = new DbPreneedDisbursement();		        		
		        		pnDisbursement.setVitalsId(FormatNumber.parseInteger(form.getVitalsMasterKey()));
		        		pnDisbursement.setLabel(form.getLabel());
		        		pnDisbursement.setValue(FormatCurrency.convertToCurrencyDbl(form.getValue()));
		        		pnDisbursement.setNew();
		        		t.addPersistent(pnDisbursement);
		        		t.save();
		        		pnDisbursement.setDisbursementId(pnDisbursement.getId());
		        		disbursementMap.put(Integer.toString(pnDisbursement.getId()), pnDisbursement);
		        	 }
		        }
		        
		        session.setAttribute(Constants.PRENEED_DISBURSEMENT_MAP, disbursementMap);
		        	
	        } catch(PersistenceException pe) {
	            logger.error("ProcessPreNeedDisbursement: Persistence Exception. ", pe);
	        } catch(Exception pe) {
	            logger.error("ProcessPreNeedDisbursement Exception. ", pe);
	        } finally {
	            if (t != null) t.closeConnection();
	        }
        } else {
        	
        }
        
        request.setAttribute("From","NewPreNeed"); 
        
        // return actionForward;
        return mapping.findForward("showPreNeed");        
    }
    
    /**
     * 
     * @param formErrors
     * @param errors
     * @param form
     */
    private void validate(
    		ArrayList formErrors, 
    		ActionErrors errors, 
    		PreNeedDisbursementForm form) {
    	
    	if ((form.getLabel() == null) || ("".equals(form.getLabel().trim()))) {
    		formErrors.add("label");
    		errors.add(ActionErrors.GLOBAL_ERROR, 
    				new ActionError("error.ui.preneeddisbursement.label"));
    	}
    	
        try {
            FormatCurrency.convertToCurrency(form.getValue());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, 
            		new ActionError("error.ui.preneeddisbursement.value"));
            formErrors.add("value");
        }
    }

}
