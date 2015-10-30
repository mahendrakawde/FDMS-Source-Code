package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbFormsPrinted;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.ReportPreview;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PrintForm;

public class ShowCaseStatusForms extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatusForms.class.getName());
    
    /**
     * Write empty report preview objects into the request object.
     * Creation date: (3/5/2003 6:35:33 PM)
     */
    public static void addEmptyReportPreviews(HttpServletRequest request) {
        
        //AppLog.trace("Setting blank ReportPreviews in request");
        ReportPreview preview;
        preview = new ReportPreview();
        request.setAttribute("ReportPreview",preview);
        for (int i=2; i<10; i++){
            request.setAttribute("ReportPreview"+i,preview);
        }
    }
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        
        PrintForm printform = new PrintForm();
        int vitalsid = 0;
        
        // Instantiate collections for "options" tags
        List formslist = new ArrayList();
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        // Get vitals-ID from request or session
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        
        // Check if print preview already exists in request.
        // If not, make an empty one
        try {
            ReportPreview preview = (ReportPreview)request.getAttribute("ReportPreview");
            if (preview==null){
                addEmptyReportPreviews(request);
            } 
        } catch (Exception e) {
            addEmptyReportPreviews(request);
        }
        
        printform.setVitalsId(String.valueOf(vitalsid));
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // get Locale specific details.
            int showFormsCompleted  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_FORMS_COMPLETED);

            request.setAttribute("showFormsCompleted", Integer.valueOf(showFormsCompleted));
            
//            if ( showFormsCompleted > 0 ) {

                // --- Populate FORMS section
                // get list of forms available for this case
                DbFormsAvailable[] list = FdmsDb.getInstance().getFormsAvailableForLocale(t,sessionUser.getRegion(),0);
                for (int i=0; i<list.length; i++){
                    int formid = list[i].getFormId();
                    // get the form status for this case
                    DbFormsPrinted[] printed = FdmsDb.getInstance().getFormsPrintedForCase(t,vitalsid,formid);
                    String completed = "-"; // Struts escapes so doesn't work: "&nbsp;";
                    if (printed.length>0){
                        if (printed[0].isCompleted())
                            completed="*"; //"&#8730;";	// check mark (square root symbol)
                    }
                    formslist.add( new OptionsList(Integer.toString(formid) ,completed+list[i].getDescription()));
                }
                
//            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatusForms.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatusForms.doPerform. ", pe);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        request.setAttribute("formsCompletedList",formslist);
        request.setAttribute("printForm",printform);
        
        return mapping.findForward("showCaseStatusJsp");
        
    }
    
    
    
}
