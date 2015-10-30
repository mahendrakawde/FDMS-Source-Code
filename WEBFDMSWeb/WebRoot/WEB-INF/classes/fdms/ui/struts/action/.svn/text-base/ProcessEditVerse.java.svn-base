package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.beans.DbMemorial;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.EditVerseForm;


public class ProcessEditVerse extends Action {
    
    private Logger logger = Logger.getLogger(ProcessEditVerse.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        // AppLog.trace("ProcessEditVerse.doPerform");
        
        EditVerseForm form = (EditVerseForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbMemorial dbMemorial = null;
        
        if (sessionUser == null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (form.getDirective().equals("cancel")) {
            return mapping.findForward("showMemorialPage");
        }
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            if (form.getDescription() == null || form.getDescription().trim().length() == 0) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullDescription"));
            }
            
            if (form.getTextValue() == null || form.getTextValue().trim().length() == 0) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.verse.nullVerse"));
            }
            String status = "";
            if (errors.isEmpty()) {
                if (form.getMemorialId() == null || form.getMemorialId().trim().length() == 0) {
                    dbMemorial = new DbMemorial();
                    dbMemorial.setNew();
                    dbMemorial.setLocaleID(sessionUser.getRegion());
                    status = "new";
                } else {
                    dbMemorial = FdmsDb.getInstance().getMemorial(t, Integer.parseInt(form.getMemorialId()));
                    dbMemorial.setVerse(form.getTextValue());
                    status = "update";
                }
                
                setMemorial(dbMemorial, form, errors);
                if (errors.isEmpty()) {
                    if (dbMemorial.isNew()) {
                    	if (status.compareToIgnoreCase("new")== 0) {
                    		//Populate the Verse List
                            //java.util.ArrayList verseList = new java.util.ArrayList();
                            DbMemorial[] dbMemorialA = null;
                    		dbMemorialA = FdmsDb.getInstance().getMemorialSet(t, sessionUser.getRegion());
                            for (int i=0; i < dbMemorialA.length; i++) {
                                String listValue = String.valueOf(dbMemorialA[i].getId());
                                String listLabel = dbMemorialA[i].getDescription();
                                //verseList.add(new OptionsList(listValue, listLabel));
                                if (listLabel.compareToIgnoreCase(form.getDescription())==0){
                                	//do nothing since the record is there.
                                	status = "Duplicate";
                                	break;
                                }
                            }
                            if (status.compareToIgnoreCase("Duplicate")!=0){
                            	t.addPersistent(dbMemorial);
                            	form.setDirective("cancel");
                            }
                            
                    	}
                    	else {
                    		t.addPersistent(dbMemorial);
                    	}
                    }
                    t.save();
                }
            }
            
        } catch(PersistenceException pe) {
            //AppLog.criticalError("Persistence Exception in ShowMemorialPrint.doPerform. "+pe.getMessage());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            //AppLog.criticalError("Exception in  ShowMemorialPrint.doPerform. "+pe);
            //pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                    t = null;
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }        
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessDonationsAddChange Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return new ActionForward(mapping.getInput());
        }
        
        return mapping.findForward("showMemorialPage");
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/9/2002 10:28:39 AM)
     * @param dbMemorial com.aldorsolutions.webfdms.beans.DbMemorial
     * @param form fdms.ui.struts.form.EditVerseForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    public void setMemorial(DbMemorial dbMemorial, EditVerseForm form, ActionErrors errors) {
        
        dbMemorial.setDescription(form.getDescription());
        dbMemorial.setVerse(form.getTextValue());
    }
}
