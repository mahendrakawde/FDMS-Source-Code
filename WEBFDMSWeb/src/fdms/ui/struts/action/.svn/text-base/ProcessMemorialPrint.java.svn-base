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

import fdms.ui.struts.form.MemorialPrintForm;


public class ProcessMemorialPrint extends Action {
    
    private Logger logger = Logger.getLogger(ProcessMemorialPrint.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        MemorialPrintForm form = (MemorialPrintForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        fdms.ui.struts.form.EditVerseForm editVerse = null;
        String submitType = form.getDirective();
        logger.debug("ProcessMemorialPrint submit = "+submitType);
        
        if (sessionUser == null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (submitType.equals("cancel")) {
            ActionForward actionForward = mapping.findForward("openCase");
            return actionForward;
        }
        
        if ((submitType.equals("edit") || submitType.equals("copy") || submitType.equals("remove")) && (form.getVerse() == null || form.getVerse().trim().length() == 0)) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
        }
        
        if (submitType.equals("print") && ((form.getFormat() == null || form.getFormat().trim().length() == 0) || (form.getVerse() == null || form.getVerse().trim().length() == 0))) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
        }
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            if (!errors.isEmpty()) {
                DbVitalsDeceased dbVitals = FdmsDb.getInstance().getVitalsDeceased(t, SessionHelpers.getVitalsIdFromSession(request, sessionUser));
                DbMemorial[] dbMemorial = FdmsDb.getInstance().getMemorialSet(t, sessionUser.getRegion());
                DbFormsAvailable[] dbFormsAvailable = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(), DbFormsAvailable.MEMORIAL_TYPE);
                fdms.ui.struts.form.MemorialPrintForm memorialPrint = new fdms.ui.struts.form.MemorialPrintForm();
                java.util.ArrayList formatList = new java.util.ArrayList();
                java.util.ArrayList verseList = new java.util.ArrayList();
                
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
                
                //Populate the Verse List
                for (int i=0; i < dbMemorial.length; i++) {
                    String listValue = String.valueOf(dbMemorial[i].getId());
                    String listLabel = dbMemorial[i].getDescription();
                    verseList.add(new OptionsList(listValue, listLabel));
                }
                
                // Clean up
                t.closeConnection();
                request.setAttribute("memorialPrint", memorialPrint);
                request.setAttribute("formatList", formatList);
                request.setAttribute("verseList", verseList);
                saveErrors(request, errors);
                return (new ActionForward(mapping.getInput()));
            }
            
            editVerse = new fdms.ui.struts.form.EditVerseForm();
            editVerse.setVitalsId(form.getVitalsId());
            
            if (!form.getDirective().equals("add")) {
                try {
                    DbMemorial dbMemorial = FdmsDb.getInstance().getMemorial(t, Integer.parseInt(form.getVerse()));
                    if (dbMemorial != null) {
                        if (submitType.equals("remove")) {
                            dbMemorial.remove();
                            t.addPersistent(dbMemorial);
                            t.save();
                        } else {
                            editVerse.setDescription(dbMemorial.getDescription());
                            if (form.getDirective().equals("edit")) {
                                editVerse.setMemorialId(String.valueOf(dbMemorial.getId()));
                            }
                            editVerse.setVitalsId(form.getVitalsId());
                            editVerse.setTextValue(dbMemorial.getVerse());
                            
                            // Set form into request scope
                            request.setAttribute("editVerse", editVerse);
                        }
                    }
                } catch (Exception e) {
                }
            }
            
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessMemorialPrint.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
            t.closeConnection();
            
        } catch(Exception pe) {
            logger.error("Exception in ProcessMemorialPrint.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
            t.closeConnection();
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("editVerse");
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessMemorialPrint Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
        if (submitType.equals("remove")) {
            actionForward = mapping.findForward("showMemorialPrint");
        }
        
        //AppLog.trace("Leaving ProcessMemorialPrint.");
        return actionForward;
        
    }
}
