package fdms.ui.struts.action;

import java.io.FileWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.accounting.bean.EdenManagerBean;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.EdenForm;


public class ProcessEden extends Action {

    private Logger logger = Logger.getLogger(ProcessEden.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {

        EdenForm form = (EdenForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        DbCase caseinfo = null;
        DbVitalsFirstCall firstCall = null;
        FdmsDb fdmsdb = null;
        fdmsdb = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance();
        DatabaseTransaction t = null;
        int localeId = 0;
    	int locationId = 0;
    	String deathState = "";
        try {
        	t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
	    	caseinfo = fdmsdb.getCase(t, vitalsid);
	    	localeId = caseinfo.getLocale();
	    	locationId = caseinfo.getChapelNumber();
	    	firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
	    	deathState = firstCall.getPlaceDeathState();
        }catch(PersistenceException pe) {
	            logger.error("Persistence Exception in ProcessEden.doPerform. " + pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	    }
	    catch(Exception pe) {
	            logger.error("Exception in  ProcessEden.doPerform. ", pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	    }
        
        

        String directive = form.getSubmitbutton();
        // AppLog.trace("ProcessEden. action:"+directive);
        try {
            logger.debug("\n\ndirective is " + form.getSubmitbutton() + "\n\n");
            
            if (directive.equalsIgnoreCase("DeleteFile")){
                // AppLog.trace("ProcessEden deleting file:"+form.getRemoveline());
                java.io.File f = new java.io.File(AccountingInterfaceUtil.getEdenFileBaseDir((DbUser)sessionUser,localeId,locationId)+form.getRemoveline());
                if (!f.delete()){
                    // AppLog.error("Unable to delete file: "+f.getAbsolutePath());
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.cannotdelete",f.getAbsolutePath()));
                }
            } else if (directive.equalsIgnoreCase("new")){
            	
            	String casecode = caseinfo.getCaseCode();
            	if (casecode == null || casecode.trim().length()==0){ 
            		casecode = "noCaseCode";
            	}
            	String contractCode = caseinfo.getContractCode();
            	if (contractCode == null || contractCode.trim().length()==0){
            		contractCode = "noContractCode";
            	}
//            	String filename = "vitals_"+deathState.toUpperCase()+"_"+casecode+"_"+contractCode+"_"+vitalsid+".dat";
            	String filename = "eden.dat";
            	String fullFileName = AccountingInterfaceUtil.getEdenFileBaseDir((DbUser)sessionUser,localeId,locationId)+filename;
            	
            	java.io.File f = new java.io.File(AccountingInterfaceUtil.getEdenFileBaseDir((DbUser)sessionUser,localeId,locationId)+filename);
            	f.delete();
            	
            	EdenManagerBean edenManagerBean = new EdenManagerBean();
            	edenManagerBean.createEdenFile(sessionUser,fullFileName,deathState.toUpperCase(), vitalsid);

            } else {
                logger.error("ProcessEden unrecognized directive:"+directive);
            }
            // clean up
            //t.save();
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } 

        if( !errors.isEmpty() )  {
            saveErrors(request, errors );
        }
        
        return mapping.findForward("showEden");
    }
    
    
    private Date parseDate ( String dateStr ) {
    	
    	Date returnDate = null;
    	
    	try {
    		returnDate = new Date ( FormatDate.convertToDate(dateStr).getTime() );
    	}
    	catch ( Exception pe ) {
    		
    	}
    	
    	return ( returnDate );    	
    	
    }
    
}
