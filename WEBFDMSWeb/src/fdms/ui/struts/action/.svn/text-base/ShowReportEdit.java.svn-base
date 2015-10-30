package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.forms.dao.FormsAvailableDAO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.ReportAdminForm;
import fdms.ui.struts.form.ReportEditForm;
import fdms.ui.struts.form.UserEditForm;
import fdms.ui.struts.form.UserListForm;


/**
 * ShowReportEdit Prepares form bean with data from selected report
 * Creation date: (6/16/09 4:13:09 PM)
 * @author: CJongs
 */
public class ShowReportEdit extends Action {
    
    private Logger logger = Logger.getLogger(ShowReportEdit.class.getName());
    private ArrayList formErrors;
    
    static {
    	final Logger logTmp = Logger.getLogger(ShowReportEdit.class.getName());
    	logTmp.debug("ShowReportEdit Loaded");
    }
    
    /**
     * Called from WebFdmsManagementUserAdmin.JSP, this action prepares to show
     * WebFdmsManagementAdmin2.JSP.
     * The UserListForm is used to determine if adding or which
     * user is being updated.
     * Then the UserEdit form is created for the JSP to use.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {    	
    	
        logger.debug("Entering ShowUserEdit action doPerfrom");
        
        formErrors = new ArrayList();
        ReportAdminForm form = (ReportAdminForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        ReportEditForm editform = new ReportEditForm();
        DbUserSession sessionUser = com.aldorsolutions.webfdms.util.SessionHelpers.getUserSession(request);
 
        if (form.getSubmitbutton().equals("exit")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            return actionForward;
        }
        //DatabaseTransaction t = null;
         
        //try {
	     //   t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	        int formID = FormatNumber.parseInteger( form.getFormID());
//	        DbFormsAvailable formsAvailable = FdmsDb.getInstance().getFormsAvailable(t,formID);
	        
	        ArrayList <DbFormsAvailable> formsAvailables = new ArrayList <DbFormsAvailable>();
	        FormsAvailableDAO formsAvailableDAo = new FormsAvailableDAO(sessionUser);
	        DbFormsAvailable formsAvailable =formsAvailableDAo.getFormsAvailable(formID);
	        
	        editform.setFormID(formID);
	        editform.setReportName(formsAvailable.getReportName());
	        editform.setDescription(formsAvailable.getDescription());
	        editform.setCategory(formsAvailable.getCategory());
	        editform.setExportType(formsAvailable.getExportType());
	        editform.setMarginLeft(formsAvailable.getMarginLeft());
	        editform.setMarginRight(formsAvailable.getMarginRight());
	        editform.setMarginTop(formsAvailable.getMarginTop());
	        editform.setMarginBottom(formsAvailable.getMarginBottom());
	        editform.setSelectionFormula(formsAvailable.getSelectionFormula());
	        
	        formsAvailables = formsAvailableDAo.getFormsAvailables(formsAvailable.getReportName());
	            
	            	ArrayList <LocaleDTO> locales = new ArrayList <LocaleDTO> ();
	    		
	    			LocaleDAO localeDAO = new LocaleDAO();
	    			locales = localeDAO.getLocales(true, (int) sessionUser.getCompanyID(), sessionUser.getDbLookup(), true);
	            
	            
		        ArrayList localeList = new ArrayList();
		        
		        // Populate the Locale List
		        localeList.add(new OptionsList("0", "-- All --"));
		        for ( LocaleDTO locale : locales ) {
		        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
		        }
	            
	            editform.setLocales(localeList);
	            editform.setLocaleIds( getLocaleIds( formsAvailables ) );
	            
	            session.setAttribute("localesSize", new Integer(localeList.size()) );
	            session.setAttribute("reportEditForm",editform);
	        
//        }catch (PersistenceException e){
//	        logger.error("ShowReportEdit Persistence Exception. " + e);
//	        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.dbms.exception"));
//	        
//	        return (new ActionForward(mapping.getInput()));
//	    } finally {
//	        if (t != null) t.closeConnection();
//	    }
      
        return mapping.findForward("showReportEditJsp");
    }
    
    
   public String[] getLocaleIds(ArrayList <DbFormsAvailable> formsAvailables) {
        
        logger.debug("Entering getUserLocationIds()");
        
        String[] localeIds = null;
        localeIds = new String[formsAvailables.size()];
        int i = 0;
        for (DbFormsAvailable formsAvailable: formsAvailables){
        	localeIds[i++] = String.valueOf(formsAvailable.getLocaleNumber());
        }
        
        return localeIds;
    }

    
}
