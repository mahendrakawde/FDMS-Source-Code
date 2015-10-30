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
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ObituaryImageForm;


public class SaveObituaryImage extends Action {

    private Logger logger = Logger.getLogger(SaveObituaryImage.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(
    		ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, 
        HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("** SaveObituaryImage action starting **");
        
        formErrors = new ArrayList();
        ObituaryImageForm form = (ObituaryImageForm) actionForm;
        
        ActionErrors errors  = new ActionErrors();
        
        // get DbUserSession object from session
        DbUserSession dbUserSession = SessionHelpers.getUserSession(request);    

        long vitalsId = FormatNumber.parseLong(form.getVitalsId());
        long deceasedId = FormatNumber.parseLong(form.getDeceasedId());
        long domainId = getASIMASDomainId(dbUserSession);
        
        logger.debug("VitalsId : " + vitalsId);
        logger.debug("DeceasedId : " + deceasedId);
        logger.debug("DomainId : " + domainId);

        if ((domainId > 0L) && (vitalsId > 0L) && (deceasedId > 0L)) {

	        try {
	        	
	        	ObituaryManagerDelegate obituaryManagerDelegate = new ObituaryManagerDelegate();        	    
	        	
	            if ("Upload Image".equals(form.getSubmitButton())) {
	                if ((form.getFile() != null) 
	                		&& (form.getFile().getFileName() != null) 
	                		&& (!form.getFile().getFileName().equals(""))) { 
	                	
	                	String mimeType = form.getFile().getContentType();
	                	logger.debug("File mimeType : " + mimeType);
	                	
	                	String[] validMimeTypes = {"jpeg", "gif", "pjpeg" };
	                	boolean isValidMimeType = false;
	                	for (int i = 0; i < validMimeTypes.length; i++) {
	                		if (mimeType.equalsIgnoreCase("image/" + validMimeTypes[i])) {
	                			isValidMimeType = true;
	                			break;
	                		}
	                	}
	                	
	                	if (isValidMimeType) {
	                	
		                	byte[] imageBytes = form.getFile().getFileData(); 
		                	
		                	obituaryManagerDelegate.saveImage(
		                			domainId, 
		                			deceasedId, 
		                			form.getFile().getFileName(), 
		                			imageBytes);
	                	} else {
    	                	formErrors.add("file");
    	                	errors.add(ActionErrors.GLOBAL_ERROR,
    	                			new ActionError("error.obitimage.not.validMimeType"));
	    	       
	                	}
	                } else {
	                	logger.debug("No image uploaded");
	                	formErrors.add("file");
	                	errors.add(ActionErrors.GLOBAL_ERROR,
	                			new ActionError("error.obitimage.not.uploaded"));
	                }
	            } else if ("Delete Image".equals(form.getSubmitButton())) {                
	                obituaryManagerDelegate.deleteImage(domainId, deceasedId);
	            }
	
	        } catch(Exception pe) {
	            logger.error("Exception in SaveObituary.doPerform. ", pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	        }
        }
        
        // If errors found, bail out and return to input page
        if (!errors.isEmpty()) {
            logger.debug("SaveObituary Invoking forward mapping getInput().");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
        }        

        return mapping.findForward("showObituaryAction");

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
