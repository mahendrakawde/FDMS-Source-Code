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

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbMemorial;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;



public class ShowMemorialPrint extends Action {
    
    private Logger logger = Logger.getLogger(ShowMemorialPrint.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        //AppLog.trace("ShowMemorialPrint.doPerform");
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbVitalsDeceased dbVitals = null;
        DbFormsAvailable[] dbFormsAvailable = null;
        DbMemorial[] dbMemorial = null;
        fdms.ui.struts.form.MemorialPrintForm memorialPrint = new fdms.ui.struts.form.MemorialPrintForm();
        java.util.ArrayList formatList = new java.util.ArrayList();
        java.util.ArrayList verseList = new java.util.ArrayList();
        
        if (sessionUser == null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbVitals = FdmsDb.getInstance().getVitalsDeceased(t, SessionHelpers.getVitalsIdFromSession(request, sessionUser));
            dbMemorial = FdmsDb.getInstance().getMemorialSet(t, sessionUser.getRegion());
            dbFormsAvailable = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(), DbFormsAvailable.MEMORIAL_TYPE);
            
            memorialPrint.setVitalsId(String.valueOf(dbVitals.getId()));
            memorialPrint.setDeceasedFullName(dbVitals.getDecFullName());
            
            if (memorialPrint.getDeceasedFullName() == null || memorialPrint.getDeceasedFullName().trim().length() == 0) {
                memorialPrint.setDeceasedFullName(dbVitals.getDecFName() +" " +dbVitals.getDecMName() +" " +dbVitals.getDecLName());
            }
            
            //Populate the format List
            for (int i=0; i < dbFormsAvailable.length; i++) {
                String listValue = String.valueOf(dbFormsAvailable[i].getId());
                String listLabel = dbFormsAvailable[i].getDescription();
                formatList.add(new OptionsList(listValue, listLabel));
            }

            if ( formatList.size() > 0 ) {
            	OptionsList option = (OptionsList) formatList.get(0);
            	memorialPrint.setFormat( option.getListValue() );
            }
            
            //Populate the Verse List
            for (int i=0; i < dbMemorial.length; i++) {
                String listValue = String.valueOf(dbMemorial[i].getId());
                String listLabel = dbMemorial[i].getDescription();
                verseList.add(new OptionsList(listValue, listLabel));
            }
            
            if ( verseList.size() > 0 ) {
            	OptionsList option = (OptionsList) verseList.get(0);
            	memorialPrint.setVerse( option.getListValue() );
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowMemorialPrint.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowMemorialPrint.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        // Check if print preview already exists in request.
        // If not, make an empty one
        com.aldorsolutions.webfdms.util.ReportPreview preview = (com.aldorsolutions.webfdms.util.ReportPreview)request.getAttribute("ReportPreview");
        if (preview==null){
        	ShowCaseStatusForms.addEmptyReportPreviews(request);
        }
        
        request.setAttribute("formatList",formatList);
        request.setAttribute("verseList",verseList);
        request.setAttribute("memorialPrint",memorialPrint);
        return mapping.findForward("memorialPrint");
    }
}
