package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.asimas.webfdms.obituary.delegate.ObituaryManagerDelegate;
import com.aldorsolutions.webfdms.beans.DbExternalVitalsId;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.ObituaryTranslator;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.asimas.asimasBeans.Obituary;
import com.asimas.asimasBeans.ServiceSchedule;

import fdms.ui.struts.form.ObituaryForm;


public class SaveObituary extends Action {

    private Logger logger = Logger.getLogger(SaveObituary.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.info("SaveObituary action starting.");
        
        formErrors = new ArrayList();
        ObituaryForm form = (ObituaryForm) actionForm;
        ActionErrors errors  = new ActionErrors();
        ActionForward actionForward = mapping.findForward("showObituaryAction");
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbObituary obit  = null;
        int vitalsid = 0;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            //AppLog.warning("SaveObituary. Invalid VitalsID to process:"+vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }

        // If Cancel, go back to case status unless no vitals-id then show introduction
        if (form.getSubmitbutton().equals("cancel")) {
            return mapping.findForward("showCaseStatusGlobal");
        }

        // If Help, forward to Help
        if (form.getSubmitbutton().equals("help")) {
            return mapping.findForward("usingHelp");
        }
        // Get form ID
        int formID = FormatNumber.parseInteger(form.getForm());
        if (formID < 1 && form.getSubmitbutton().equals("print")){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.print.noselect"));
            formErrors.add("form");
        }
        
        // If errors found, bail out and return to input page
        if (!errors.isEmpty()) {
            logger.debug("SaveObituary Invoking forward mapping getInput().");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            ShowCaseStatusForms.addEmptyReportPreviews(request);
            return (new ActionForward(mapping.getInput()));
        }
        
        logger.debug("processing : " + vitalsid);

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            //AppLog.trace("SaveObituary directive:"+ form.getSubmitbutton());

            // Save changes
            if (form.getSubmitbutton().equals("save") || form.getSubmitbutton().equals("print")){
                obit = FdmsDb.getInstance().getObituary(t,vitalsid);
                if (obit == null ) {
                    // Add Obituary
                    obit = new DbObituary();
                    obit.setNew();
                    t.addPersistent(obit);
                    obit.setId(vitalsid );
                }

                // Update persistent objects from form and check for validation errors
                obit.setObitNotice(form.getTextValue());
                obit.setObituaryLink(form.getWebsite());
                
//                obit.setObitNotice(request.getParameter("textValue"));

                // if errors found, return to input screen without saving anything
                if (!errors.isEmpty())   {
                    //AppLog.info("SaveObit Invoking forward mapping getInput() ");
                    saveErrors(request, errors );
                    return (new ActionForward(mapping.getInput() ));
                }
                // finish up
                t.save();
                
                boolean sendToAsimas = form.getSendToAsimas();
                logger.debug("Send to ASIMAS : " + sendToAsimas);
                
                long asimasId = FormatNumber.parseLong(form.getAsimasId());
                long domainId = getASIMASDomainId(sessionUser);
                long deceasedId = FormatNumber.parseLong(form.getAsimasDeceasedId());
                
                if ((asimasId > 0L) && (domainId > 0L) && (sendToAsimas)) {
                	logger.debug("Saving obituary to ASIMAS");
                	
                	// save obituary to ASIMAS
                	ObituaryTranslator obituaryTranslator = new ObituaryTranslator();
                	HashMap map = obituaryTranslator.createObituary(vitalsid, domainId, deceasedId, sessionUser);
                	
                	if (map != null) {                		
	                	ServiceSchedule serviceSchedule = (ServiceSchedule) map.get("serviceSchedule");
	                	logger.debug("Service Schedule : " + serviceSchedule);
	                	
	                	Obituary obituary = (Obituary) map.get("obituary");
	                	logger.debug("Obituary : " + obituary);
	                	
	                	ObituaryManagerDelegate obituaryManagerDelegate = 
	                		new ObituaryManagerDelegate();                	
	                	
	                	if (deceasedId > 0L) { 
	                		obituaryManagerDelegate.updateObituary(serviceSchedule, obituary);
                		} else {
	                		deceasedId = obituaryManagerDelegate.createObituary(serviceSchedule, obituary);
	                		logger.debug("DeceasedId created : " + deceasedId);

	                		if (deceasedId > 0L) {
	                			// save to xref table
	                			DbExternalVitalsId dbExternalVitalsId = new DbExternalVitalsId();
	                			dbExternalVitalsId.setNew();
	                			dbExternalVitalsId.setExternalAppId(FormatNumber.parseLong(Constants.ASIMAS_APPLICATION_ID));
	                			dbExternalVitalsId.setVitalsId((long) vitalsid);
	                			dbExternalVitalsId.setExternalVitalsId(deceasedId);

	                            t.closeConnection();
	                			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	                			t.addPersistent(dbExternalVitalsId);
	                			t.save();
	                		}
                		}
                	}
                	
                }
                
                if (form.getSubmitbutton().equals("print")) {
                    // This method generates print preview files and loads them into the request as attributes
                    PrintFormAction.printFormChain(formID, vitalsid, sessionUser, request, response, servlet.getServletContext());
                    actionForward = mapping.findForward("showObituaryAction");
                }
            }

        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in SaveObituary do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in SaveObituary.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

        //Action Forward Logic
        if (!errors.isEmpty()) {
            //AppLog.info("SaveObituary Invoking forward mapping getInput().");
            saveErrors(request, errors );
          //  actionForward = new ActionForward(mapping.getInput() );
        }

        return actionForward;

    }
    
    /**
     * 
     * @param dbUserSession
     * @return
     */
    private long getASIMASDomainId(DbUserSession dbUserSession) {
    	long domainId = 0L;
    	
    	try {
    		HashMap map = dbUserSession.getExternalAppConfigMap();
    		if ((map != null) && (map.containsKey(Constants.ASIMAS_APPLICATION_ID)))
    			domainId = ((Long) map.get(Constants.ASIMAS_APPLICATION_ID)).longValue();
    	} catch (Exception e) {
    		logger.error("Exception in getDomainId() ", e);
    	}
    	
    	return domainId;
    }
    
}
