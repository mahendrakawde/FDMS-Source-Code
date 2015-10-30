package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.ReportPreview;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.Translator;

import fdms.ui.struts.form.CaseStatusForm;

public class ShowCaseStatus extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatus.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        
        CaseStatusForm caseStatus = new CaseStatusForm();
        
        DbVitalsDeceased vitalsDeceased = null;
        DbServices services = null;
        
        int vitalsid = 0;
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            //AppLog.info("ShowPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors);
        }
        
        // Get vitals-ID from request or session
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            vitalsDeceased 	= FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
            services	= FdmsDb.getInstance().getServices(t, vitalsid);
            
            if (vitalsDeceased == null) {
                return mapping.findForward("showCaseList");                
            }
            
            caseStatus.setVitalsId(String.valueOf(vitalsDeceased.getId()));
            caseStatus.setDeceasedFullName(vitalsDeceased.getDecFullName());
            

            caseStatus.setDecResStreet( FormatString.escapeSingleQuotes(vitalsDeceased.getDecResStreet()) );
            caseStatus.setDecResCityStateZip(
                    FormatString.escapeSingleQuotes(
                        vitalsDeceased.getDecResCityTWP() +
                        ", " + vitalsDeceased.getDecResState() +
                        " " + vitalsDeceased.getDecResZip()));
            
            if ( services != null ) {
            	caseStatus.setCemeteryStreet(
            			FormatString.escapeSingleQuotes(services.getCSrvCemStreet()));
            	caseStatus.setCemeteryCitystate(FormatString.escapeSingleQuotes(
            			Translator.appendCityState(services.getCSrvCemCity(),services.getCSrvCemState())));
            }
            
            // Put form beans in request
            request.setAttribute("caseStatus",caseStatus);

            DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
            
            // get Locale specific details.
            int showFormsCompleted  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_FORMS_COMPLETED);
            int showAtNeedCheckList  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_AT_NEED_CHECKLIST);
            int showAfterCareCheckList  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_AFTER_CARE_CHECKLIST);
            int showBookMarks  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_BOOKMARKS);

            request.setAttribute("showFormsCompleted", Integer.valueOf(showFormsCompleted));
            request.setAttribute("showAtNeedCheckList", Integer.valueOf(showAtNeedCheckList));
            request.setAttribute("showAfterCareCheckList", Integer.valueOf(showAfterCareCheckList));
            request.setAttribute("showBookMarks", Integer.valueOf(showBookMarks));
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatus.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatus.doPerform. ", pe);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        // Check if print preview already exists in request.
        // If not, make an empty one
        try {
            ReportPreview preview =
                (ReportPreview)request.getAttribute("ReportPreview");
            if (preview==null){
                //AppLog.trace("Setting blank ReportPreview into request");
                preview = new ReportPreview();
                request.setAttribute("ReportPreview",preview);
            } else {
                //AppLog.trace("Found ReportReview in request");
            }
        } catch (Exception e) {
            //AppLog.trace("Blew Chunks trying to find ReportPreview.");
            ReportPreview preview = new ReportPreview();
            request.setAttribute("ReportPreview",preview);
        }
        
        // Put form beans in request
        request.setAttribute("caseStatus",caseStatus);
        
        return mapping.findForward("showCaseStatusJsp");
        
    }
    
}
