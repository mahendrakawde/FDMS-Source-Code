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

import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.BookMarksForm;

public class ShowCaseStatusBookmarks extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatusBookmarks.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        //AppLog.trace("ShowCaseStatus action doPerfrom");
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        List bookMarkList = new ArrayList();

    	BookMarksForm bookMarksForm = new BookMarksForm();
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // get Locale specific details.
            int showBookMarks  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_BOOKMARKS);

            request.setAttribute("showBookMarks", new Integer(showBookMarks));
            

            if ( showBookMarks > 0 ) {
                // BookMarks Section
                bookMarkList = FdmsDb.getInstance().getBookMarks(t, sessionUser.getRegion(), sessionUser.getLocationId());
                bookMarksForm.setBookMarkList(bookMarkList);
                
            }
            
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatusBookmarks.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatusBookmarks.doPerform. ", pe);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        // Put form beans in request
        request.setAttribute("bookMarksForm",bookMarksForm);
        
        return mapping.findForward("showBookmarksJSP");
        
    }
    
}
