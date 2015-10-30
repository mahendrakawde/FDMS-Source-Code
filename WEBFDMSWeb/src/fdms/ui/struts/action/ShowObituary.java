package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;

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
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorassist.webfdms.model.ERegisterBookDTO;
import com.aldorsolutions.asimas.webfdms.obituary.delegate.ObituaryManagerDelegate;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.dao.ERegisterBookDAO;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.visitation.bean.VisitationManagerBean;
import com.asimas.asimasBeans.Obituary;

import fdms.ui.struts.form.ObituaryImageForm;

public class ShowObituary extends Action {

    private Logger logger = Logger.getLogger(ShowObituary.class.getName());
 
  public ActionForward execute(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws javax.servlet.ServletException, java.io.IOException {
                                        
	//AppLog.trace("ShowObituary action doPerfrom");
	ActionErrors errors = new ActionErrors();
   	HttpSession session = request.getSession();
   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
	DatabaseTransaction t = null;
	DbObituary obituary = null;
	DbVitalsDeceased deceased = null;
	DbLocation location = null;
	DbCase caseinfo = null;
	DbServices srv = null;
	ArrayList visitations = null;
	fdms.ui.struts.form.ObituaryForm obitForm = new fdms.ui.struts.form.ObituaryForm();
	ArrayList formsList = new ArrayList();
	ArrayList formatsList = new ArrayList();
	int vitalsid=0;
	long asimasDeceasedId = 0L;
	String externalAppId = "0";
	ObituaryImageForm obituaryImageForm = new ObituaryImageForm();
	
	if (sessionUser == null) {
	   errors.add(
                ActionErrors.GLOBAL_ERROR,
                new ActionError("error.login.invalid")
                );
           
	} else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid < 1){
                //AppLog.warning("ShowObituary. Invalid VitalsID to process:"+vitalsid);
                errors.add(
                    ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.ui.nocase")
                    );
            }
	}
	//AppLog.trace("ShowObituary for ID:"+vitalsid);

	//Database Access Logic 
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		obituary = FdmsDb.getInstance().getObituary(t, vitalsid);
		deceased = FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
		caseinfo = FdmsDb.getInstance().getCase(t, vitalsid);
		location = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());
		srv		 	= FdmsDb.getInstance().getServices(t,vitalsid);
		VisitationManagerBean visitationManager = new VisitationManagerBean();
		visitations = visitationManager.getVisitationsForID(vitalsid, sessionUser);
        
		if (deceased != null) {
            obitForm.setDeceasedFullName(deceased.getDecFullName());
            
            obitForm.setDeceasedFirstName(deceased.getFirstName());
            obitForm.setDeceasedLastName(deceased.getLastName());
        }
        
        /*if (location != null) {
			obitForm.setWebsite(location.getWebsite());
		}else{
			obitForm.setWebsite("");
		}*/

		if (obituary != null) {
			// System.out.println("Obituary content: " + obituary.composeObit(sessionUser));
			
			obitForm.setTextValue(obituary.getObitNotice());
			obitForm.setWebsite(obituary.getObituaryLink());
		}
		
		// get list of obit forms
		DbFormsAvailable[] list = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(),
				DbFormsAvailable.OBITUARY_TYPE);

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				int formid = list[i].getFormId();
				formsList.add(new OptionsList(Integer.toString(formid), list[i].getDescription()));
			}
		}

		// get list of Obituary Compose Formats
		DbFormsAvailable[] formats = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(),
					DbFormsAvailable.OBITUARY_FORMAT);

		if (formats != null) {
			for (int i = 0; i < formats.length; i++) {
				int formid = formats[i].getFormId();
				formatsList.add(new OptionsList(Integer.toString(formid), formats[i].getDescription()));
			}
		}								 			 	
			
		HashMap externalConfigMap = sessionUser.getExternalAppConfigMap();
		if ((externalConfigMap != null) && (externalConfigMap.containsKey(Constants.ASIMAS_APPLICATION_ID))) {

			logger.debug("ASIMAS Configured");

			externalAppId = Constants.ASIMAS_APPLICATION_ID;

			long domainId = ((Long) externalConfigMap.get(Constants.ASIMAS_APPLICATION_ID)).longValue();
			
			asimasDeceasedId = FdmsDb.getInstance().getExternalVitalsId(t, 
					Constants.ASIMAS_APPLICATION_ID, vitalsid);
			
			ObituaryManagerDelegate obituaryManagerDelegate = new ObituaryManagerDelegate();
			
			HashMap obitMap = obituaryManagerDelegate.readObituary(asimasDeceasedId, domainId);
			if (obitMap != null) {
				Obituary asimasObituary = (Obituary) obitMap.get("obituary");
				if (asimasObituary != null) {
					String obituaryImageUrl = asimasObituary.getImageURL();
					logger.debug("ASIMAS obituary image url : " + obituaryImageUrl);
					if ((obituaryImageUrl != null) && (!"".equals(obituaryImageUrl))) {
						obituaryImageForm.setHasImage("Y");
						obituaryImageForm.setObitImageUrl(obituaryImageUrl);
					} else {
						obituaryImageForm.setObitImageUrl("images/noAsimasImage.gif");
					}

					// add link to ASIMAS obituary on website into obitForm
					String asimasObituaryUrl = asimasObituary.getObituaryURL();
					logger.debug("ASIMAS obituary url : " + asimasObituaryUrl);
					obitForm.setAsimasObitUrl(asimasObituaryUrl);
				}
			}
		}

		// if asimas deceasedId found, set send to asimas to true
		if (asimasDeceasedId > 0L)
			obitForm.setSendToAsimas(true);

		// if asimas is configured, set asimas domainId
		obitForm.setAsimasId(externalAppId);
		
		// obitForm.setObitConnectionUrl(security.getObitConnectURL() + vitalsid);
                
	} catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowObituary.doPerform. " + pe);
		errors.add(
                    ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.PersistenceException",pe.getCause())
                    );
	} catch(Exception pe) { 
		logger.error("Exception in  ShowObituary .doPerform. ", pe);
		errors.add(
                    ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.GeneralException",pe.getMessage())
                    );
        } finally {
            if (t != null) t.closeConnection();
        }
	
	// Check if print preview already exists in request.
 	// If not, make an empty one
 	com.aldorsolutions.webfdms.util.ReportPreview preview =
            (com.aldorsolutions.webfdms.util.ReportPreview)request.getAttribute("ReportPreview");
        
 	if (preview == null) 
 		{ShowCaseStatusForms.addEmptyReportPreviews(request);}
 	 	
 	obituaryImageForm.setVitalsId(Integer.toString(vitalsid));
 	obituaryImageForm.setDeceasedId(Long.toString(asimasDeceasedId));
 	request.setAttribute("obituaryImage", obituaryImageForm);
 	
 	obitForm.setAsimasDeceasedId(Long.toString(asimasDeceasedId));
 		
	ERegisterBookDAO regBookDAo = new ERegisterBookDAO(DAO.DB_FDMSSHARE);
	ERegisterBookDTO data = new ERegisterBookDTO();
	data = regBookDAo.getERegisterBook(sessionUser.getCompanyID(),vitalsid);
	
	if (data!=null){
		obitForm.setTargetWebPage(data.getTargetWebPage());
		obitForm.setVideolink(data.getVideolink());
		obitForm.setCompanyId(String.valueOf(data.getCompanyId()));
		obitForm.setCaseId(String.valueOf(data.getCaseId()));
	} else {
		obitForm.setCompanyId(String.valueOf(sessionUser.getCompanyID()));
		obitForm.setCaseId(String.valueOf(vitalsid));
	}
	
	request.setAttribute("obituary",obitForm);
	session.setAttribute("obituaryForms", formsList);
	session.setAttribute("obituaryFormats", formatsList);

	
	return mapping.findForward("obituary");
  }    
}
