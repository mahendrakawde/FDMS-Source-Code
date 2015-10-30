package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.forms.dao.FormsAvailableDAO;
import com.aldorsolutions.webfdms.util.OptionsList;




/**
 * ShowReportAdmin 
 * Creation date: (6/15/2009 12:33:00 PM)
 * @author: CJongs
 */
public class ShowReportAdmin extends Action {
    
    private Logger logger = Logger.getLogger(ShowReportAdmin.class.getName());
    private ArrayList formErrors;
    
    static {
    	final Logger logTmp = Logger.getLogger(ShowReportAdmin.class.getName());
    	logTmp.debug("ShowReportAdmin Loaded");
    }
    
    /**
     * Called from topMenu.JSP, this action prepares the report name 
     * for set up the locales/locations
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {    	
    	
        logger.debug("Entering ShowReportEdit action doPerfrom");
        
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = com.aldorsolutions.webfdms.util.SessionHelpers.getUserSession(request);
//        ReportAdminForm reportForm = new ReportAdminForm();
        List reports = new ArrayList();
        	
        	 ArrayList <DbFormsAvailable> formsAvailables = new ArrayList <DbFormsAvailable>();
 	        FormsAvailableDAO formsAvailableDAo = new FormsAvailableDAO(sessionUser);
 	        formsAvailables = formsAvailableDAo.getFormsAvailables(true);
 	        for (DbFormsAvailable formsAvailable : formsAvailables){
 	        	reports.add( new OptionsList(Integer.toString(formsAvailable.getFormId()) ,formsAvailable.getReportName()+" -- "+formsAvailable.getDescription()));
 	        }
        	    
	    request.setAttribute("reports",reports);
        return mapping.findForward("reportAdminList");
    }
    
 
}
