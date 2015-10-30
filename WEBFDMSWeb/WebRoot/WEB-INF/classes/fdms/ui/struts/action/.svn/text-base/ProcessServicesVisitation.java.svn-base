package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

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

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.visitation.dao.VisitationDAO;

import fdms.ui.struts.form.ServicesVisitationForm;

public class ProcessServicesVisitation extends Action {
    
    private Logger logger = Logger.getLogger(ProcessServicesVisitation.class.getName());
    private ArrayList formErrors;
    private ActionErrors errors;    
    
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
            
        logger.debug("ProcessServicesVisitation action starting.");
        
        ServicesVisitationForm form = (ServicesVisitationForm) actionForm;
        
        errors = new ActionErrors();
        ActionForward actionForward = mapping.findForward("showServices");
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid = 0;
        formErrors = new ArrayList();
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));            
        }                
        
        int visitationID = form.getVisitationID();
        form.setVitalsMasterKey(vitalsid);
        
        try {
            if( form.getDirective().equals("cancel") ){
                // go back to case status unless no vitals-id then show introduciton
                return mapping.findForward("showServices");
            }
            
            if( form.getDirective().equals("help") ){
                return mapping.findForward("usingHelp");
            }
            
            // if errors found, return to input screen without saving anything
            if( !errors.isEmpty() )   {
                saveErrors(request, errors );
                request.setAttribute("formErrors", formErrors);
                return (new ActionForward(mapping.getInput() ));
            }

            VisitationDAO visDao = new VisitationDAO(sessionUser);
            DbVisitations visitation = createVisitation (form);
            
            //Action Forward Logic
            if( !errors.isEmpty() )  {
                saveErrors(request, errors );       
                request.setAttribute("formErrors", formErrors);
                return (new ActionForward(mapping.getInput() ));
            }   
            
            if ( visitationID > 0 ) {

                if( form.getDirective().equals("delete") ){
                	updateVisitation(visDao, form, visitation, true);
                } else {
                	updateVisitation(visDao, form, visitation, false);
                }
                
            } else {
            	addVisitation(visDao, form, visitation);
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessServicesVisitation do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ProcessServicesVisitation .doPerform. ",  pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.GeneralException",pe.getMessage()));
        } 
        
        //Action Forward Logic
        if( !errors.isEmpty() )  {
            saveErrors(request, errors );       
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput() ));
        }   
        
        request.setAttribute("vitalsId", new Integer(vitalsid));               
        actionForward = mapping.findForward("showServices");

        return  actionForward;
    }
    
    private void addVisitation (VisitationDAO visDao, ServicesVisitationForm form, DbVisitations visitation) {
    	// create a new visitation manager bean so that we can update multiple visitations for one case
        visitation.setStatus('A');
        visDao.updateVisitation( visitation, form.getVitalsMasterKey() );
    }
    
    private void updateVisitation (VisitationDAO visDao, ServicesVisitationForm form, DbVisitations visitation, boolean delete) {
    	// create a new visitation manager bean so that we can update multiple visitations for one case
        visitation.setStatus('U');
        
        if ( delete == true ) {
        	visitation.setStatus('D');
        }
        
        visDao.updateVisitation( visitation, form.getVitalsMasterKey() );
    }
    
    private DbVisitations createVisitation ( ServicesVisitationForm form ) {
    	
    	if ( form.getDate() == null || form.getDate().trim().length() == 0) {
    		errors.add("date",new ActionError("error.ui.invaliddate"));
            formErrors.add("date");
    	}
    	else {
    		try {
    			FormatDate.convertToDateMMDDYYYY(form.getDate());
    		} catch(Exception e){
    			errors.add("date", new ActionError("error.ui.invaliddate"));
    			formErrors.add("date");
    		}
    	}
        
        if ( form.getPlace() == null || form.getPlace().trim().length() == 0) {
    		errors.add("place",new ActionError("error.ui.visitationPlace"));
            formErrors.add("place");
    	}
        
    	if ( form.getStartTime() == null || form.getStartTime().trim().length() == 0) {
    		errors.add("startTime",new ActionError("error.ui.startTime"));
            formErrors.add("startTime");
    	}

    	if ( form.getEndTime() == null || form.getEndTime().trim().length() == 0) {
    		errors.add("endTime",new ActionError("error.ui.endTime"));
            formErrors.add("endTime");
    	}
    	
    	DbVisitations visitation = new DbVisitations ();
    	visitation.setAddress(form.getAddress());
    	visitation.setAddress2(form.getAddress2());
    	visitation.setCity(form.getCity());
    	visitation.setDate(form.getDate());
    	visitation.setEndTime(form.getEndTime());
    	visitation.setId(form.getVisitationID());
    	visitation.setMainKey(form.getVitalsMasterKey());
    	visitation.setNotes(form.getNotes());
    	visitation.setPlace(form.getPlace());
    	visitation.setPrivateVisitation(form.getPrivateVisitation());
    	visitation.setRoom(form.getRoom());
    	visitation.setStartTime(form.getStartTime());
    	visitation.setState(form.getState().toUpperCase());
    	visitation.setZip(form.getZip().toUpperCase());
    	
    	return ( visitation );
    }
    
}
