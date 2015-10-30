package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

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

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.LocaleListForm;


public class ShowLocaleAdmin extends Action {
    
    private Logger logger = Logger.getLogger(ShowLocaleAdmin.class.getName());
    
    /**
     * Gobal Action, this action prepares to show
     * WebFDMSLocationAdmin.jsp.
     * The LocationListForm allows the operator to select the location
     * to be modified or click add or delete.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        logger.debug("ShowLocaleAdmin.doPerform");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        LocaleListForm localeListForm = new LocaleListForm();
        ArrayList <OptionsList> localeList = new ArrayList <OptionsList> ();
        
        //Database Access Logic
        try {
            logger.debug("Session region : " + sessionUser.getRegion());
            
//            ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(sessionUser.getDbLookup(),
//					(int) sessionUser.getCompanyID());
	        
            ArrayList <LocaleDTO> locales = new ArrayList <LocaleDTO>();
            LocaleDAO localeDAO = new LocaleDAO();
			locales = localeDAO.getLocales(true, (int) sessionUser.getCompanyID(), sessionUser.getDbLookup(), true);
            
	        // Populate the Locale List
	        for ( LocaleDTO locale : locales ) {
	        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
	        }
            
        } catch(Exception pe) {
            logger.error("Exception in  ShowLocaleAdmin.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } 
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        session.setAttribute("localeListForm", localeListForm);
        session.setAttribute("locales", localeList);
        
        return mapping.findForward("localeList");
    }
}
