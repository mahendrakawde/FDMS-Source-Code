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

import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CheckListForm;

public class ShowCaseStatusCheckList extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatusCheckList.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbVitalsSchedule checklistdata = null;
        CheckListForm checklistform = new CheckListForm();
        int vitalsid = 0;
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            checklistdata	= FdmsDb.getInstance().getVitalsSchedule(t,vitalsid);
            
            DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
            
            // get Locale specific details.
            int showAtNeedCheckList  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_AT_NEED_CHECKLIST);
            int showAfterCareCheckList  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_AFTER_CARE_CHECKLIST);
            
            request.setAttribute("showAtNeedCheckList", Integer.valueOf(showAtNeedCheckList));
            request.setAttribute("showAfterCareCheckList", Integer.valueOf(showAfterCareCheckList));
            
            if ( checklistdata != null ) {
            	
            	if ( (showAtNeedCheckList > 0) || (showAfterCareCheckList > 0) ) {
            		
            	}
            	
                // --- Populate CHECK LIST section
                checklistform.setChecked1(checklistdata.getChecked(0)>0);
                checklistform.setChecked2(checklistdata.getChecked(1)>0);
                checklistform.setChecked3(checklistdata.getChecked(2)>0);
                checklistform.setChecked4(checklistdata.getChecked(3)>0);
                checklistform.setChecked5(checklistdata.getChecked(4)>0);
                checklistform.setChecked6(checklistdata.getChecked(5)>0);
                checklistform.setChecked7(checklistdata.getChecked(6)>0);
                checklistform.setChecked8(checklistdata.getChecked(7)>0);
                checklistform.setChecked9(checklistdata.getChecked(8)>0);
                
                
                checklistform.setChecked10(checklistdata.getChecked(9)>0);
                checklistform.setChecked11(checklistdata.getChecked(10)>0);
                checklistform.setChecked12(checklistdata.getChecked(11)>0);
                checklistform.setChecked13(checklistdata.getChecked(12)>0);
                checklistform.setChecked14(checklistdata.getChecked(13)>0);
                checklistform.setChecked15(checklistdata.getChecked(14)>0);
                checklistform.setChecked16(checklistdata.getChecked(15)>0);
                
                
                checklistform.setSchedule1(checklistdata.getDescription(0));
                checklistform.setSchedule2(checklistdata.getDescription(1));
                checklistform.setSchedule3(checklistdata.getDescription(2));
                checklistform.setSchedule4(checklistdata.getDescription(3));
                checklistform.setSchedule5(checklistdata.getDescription(4));
                checklistform.setSchedule6(checklistdata.getDescription(5));
                checklistform.setSchedule7(checklistdata.getDescription(6));
                checklistform.setSchedule8(checklistdata.getDescription(7));
                
                
                checklistform.setSchedule9(checklistdata.getDescription(8));
                checklistform.setSchedule10(checklistdata.getDescription(9));
                checklistform.setSchedule11(checklistdata.getDescription(10));
                checklistform.setSchedule12(checklistdata.getDescription(11));
                checklistform.setSchedule13(checklistdata.getDescription(12));
                checklistform.setSchedule14(checklistdata.getDescription(13));
                checklistform.setSchedule15(checklistdata.getDescription(14));
                checklistform.setSchedule16(checklistdata.getDescription(15));
                
                checklistform.setDate9(	 FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(0)));
                checklistform.setDate10( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(1)));
                checklistform.setDate11( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(2)));
                checklistform.setDate12( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(3)));
                checklistform.setDate13( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(4)));
                checklistform.setDate14( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(5)));
                checklistform.setDate15( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(6)));
                checklistform.setDate16( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(7)));
            }

        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatusCheckList.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatusCheckList.doPerform. ", pe);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        request.setAttribute("checkListForm",checklistform);
        
        return mapping.findForward("showCaseStatusJsp");
        
    }
    
}
