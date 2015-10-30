package fdms.ui.struts.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPasswordLog;
import com.aldorsolutions.webfdms.beans.DbSecurityConfig;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.forms.dao.FormsAvailableDAO;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.security.util.Encryptor;
import com.aldorsolutions.webfdms.util.LoginLogger;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.ReportEditForm;
import fdms.ui.struts.form.UserEditForm;


/**
 * ProcessReportEdit
 * This class processes the form for adding or changing locale of formsavailable
 * and stores the information to the DBMS.
 * Creation date: (06/16/09 4:13:09 PM)
 * @author: CJongs
 */
public class ProcessReportEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessReportEdit.class.getName());
    private ArrayList <String> formErrors;
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList <String> ();
        ReportEditForm form = (ReportEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        
        // Check if CANCEL button was clicked
        if (form.getSubmitbutton().equals("cancel")){
            return mapping.findForward("showReportAdminDo");
        }
        
        String [] localeIds  = form.getLocaleIds();
        int formID = form.getFormID();
        try {	
        	
        	ArrayList <DbFormsAvailable> formsAvailables = new ArrayList <DbFormsAvailable>();
	        FormsAvailableDAO formsAvailableDAo = new FormsAvailableDAO(sessionUser);
	        DbFormsAvailable formsAvailable =formsAvailableDAo.getFormsAvailable(formID);
	        
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	       
	        DbFormsAvailable[] list;
	        list = FdmsDb.getInstance().getFormsAvailableForReportName(t, formsAvailable.getReportName());
	        
	        // check if you want to set to all locales
	        boolean setToAllLocales = false;
	        for (int i=0; i < localeIds.length; i++){
	        	if (localeIds[i].compareToIgnoreCase("0")== 0) {
	        		setToAllLocales = true;
	        	}
	        }
	        
	        boolean keepIt = false;
	        if (setToAllLocales) { //operate if you chooes to set the report to all locales
	        	boolean isAlreadySetToAllLocale = false;
	        	for (DbFormsAvailable aForm:list){
	        		keepIt = false;
	        		if (aForm.getLocaleNumber() == 0){
	        			keepIt = true;
	        			isAlreadySetToAllLocale = true;
	        		}
	        		if (!keepIt){
	        			aForm.remove();
	        			t.addPersistent(aForm);
	        		}
	        	}
	        	if (!isAlreadySetToAllLocale){
	        		DbFormsAvailable newForm = new DbFormsAvailable();
        			newForm.setNew();
        			newForm.setLocaleNumber(0);
        			copyData(newForm,formsAvailable);
        			t.addPersistent(newForm);
	        	}
	        } else {
	        	// we are going to check and keep only the locale that already been set.
	        	
	        	for (DbFormsAvailable aForm:list){
	        		keepIt = false;
	        		for (int i=0; i < localeIds.length; i++){
	    	        	if ( (Integer.valueOf(localeIds[i])) == aForm.getLocaleNumber()) {
	    	        		keepIt = true;
	    	        	}
	    	        }
	        		if (!keepIt){
	        			aForm.remove();
	        			t.addPersistent(aForm);
	        		}
		        }
	        	// we are going to add a new locale to the report.
	        	boolean isNew = false;
	        	for (int i=0; i < localeIds.length; i++){
	        		isNew = true;
	        		for (DbFormsAvailable aForm:list){
	        			if(aForm.getLocaleNumber() == (Integer.valueOf(localeIds[i]))){
	        				isNew = false;
	        			}
	        		}
	        		if (isNew){
	        			DbFormsAvailable newForm = new DbFormsAvailable();
	        			newForm.setNew();
	        			newForm.setLocaleNumber(Integer.valueOf(localeIds[i]));
	        			copyData(newForm,formsAvailable);
	        			t.addPersistent(newForm);
	        		}
	        	}
	        	
	        }
	        
	        t.save();
	        
        
        }catch (PersistenceException e){
	        logger.error("ShowReportAdmin Persistence Exception. " + e);
	        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.dbms.exception"));
	        
	        return (new ActionForward(mapping.getInput()));
	    } finally {
	        if (t != null) t.closeConnection();
	    }
        
        return mapping.findForward("webFDMSManagement");        
    }
    
    private void copyData(DbFormsAvailable toForm, DbFormsAvailable fromForm){
    	
    	toForm.setDescription(fromForm.getDescription());
    	toForm.setReportName(fromForm.getReportName());
    	toForm.setCategory(fromForm.getCategory());
    	toForm.setSelectionFormula(fromForm.getSelectionFormula());
    	toForm.setExportType(fromForm.getExportType());
    	toForm.setMarginLeft(fromForm.getMarginLeft());
    	toForm.setMarginRight(fromForm.getMarginRight());
    	toForm.setMarginTop(fromForm.getMarginTop());
    	toForm.setMarginBottom(fromForm.getMarginBottom());
    	toForm.setChainToReport(fromForm.getChainToReport());
    	toForm.setUserDefined(fromForm.getUserDefined());
    	toForm.setDatapull(fromForm.getDatapull());
    	toForm.setStoredProc(fromForm.getStoredProc());
    	toForm.setXmlFile(fromForm.getXmlFile());
    	
    	
    }
    
}
