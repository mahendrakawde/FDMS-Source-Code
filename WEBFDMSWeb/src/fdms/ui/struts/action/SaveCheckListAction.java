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
import org.apache.struts.config.ModuleConfig;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CheckListForm;

public class SaveCheckListAction extends Action {

    private Logger logger = Logger.getLogger(SaveCheckListAction.class.getName());

    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("Entering SaveCheckListAction");
        
        CheckListForm form = (CheckListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbVitalsSchedule checklistdata=null;
        DatabaseTransaction t = null;
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid = 0;
        
        if(sessionUser==null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        } else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid<1){
                //AppLog.warning("ProcessPallbearers. Invalid VitalsID to process:"+vitalsid);
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            }
        }

        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            checklistdata = FdmsDb.getInstance().getVitalsSchedule(t,vitalsid);

            // Make sure we have some data
            if (checklistdata==null){
                //AppLog.criticalError("SaveCheckList.doPerform null vitals data for:"+vitalsid);
                //return
            }
            // Save CHECK LIST
            if ("atNeedChecklist".equals(form.getChecklistId())) {
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked1()) ,0);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked2()) ,1);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked3()) ,2);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked4()) ,3);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked5()) ,4);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked6()) ,5);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked7()) ,6);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked8()) ,7);
                
                checklistdata.setDescription(form.getSchedule1()  ,0);
                checklistdata.setDescription(form.getSchedule2()  ,1);
                checklistdata.setDescription(form.getSchedule3()  ,2);
                checklistdata.setDescription(form.getSchedule4()  ,3);
                checklistdata.setDescription(form.getSchedule5()  ,4);
                checklistdata.setDescription(form.getSchedule6()  ,5);
                checklistdata.setDescription(form.getSchedule7()  ,6);
                checklistdata.setDescription(form.getSchedule8()  ,7);                
              }
            else if ("afterCareChecklist".equals(form.getChecklistId()))
              {
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked9()) ,8);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked10()),9);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked11()),10);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked12()),11);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked13()),12);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked14()),13);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked15()),14);
                checklistdata.setChecked(FormatNumber.translateBool(form.getChecked16()),15);
                checklistdata.setDescription(form.getSchedule9()  ,8);
                checklistdata.setDescription(form.getSchedule10() ,9);
                checklistdata.setDescription(form.getSchedule11() ,10);
                checklistdata.setDescription(form.getSchedule12() ,11);
                checklistdata.setDescription(form.getSchedule13() ,12);
                checklistdata.setDescription(form.getSchedule14() ,13);
                checklistdata.setDescription(form.getSchedule15() ,14);
                checklistdata.setDescription(form.getSchedule16() ,15);
                try
                  {
                    if (form.getDate9().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate9()),0);
                    if (form.getDate10().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate10()),1);
                    if (form.getDate11().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate11()),2);
                    if (form.getDate12().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate12()),3);
                    if (form.getDate13().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate13()),4);
                    if (form.getDate14().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate14()),5);
                    if (form.getDate15().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate15()),6);
                    if (form.getDate16().compareTo(" ")>0) checklistdata.setDate(FormatDate.convertToDateMMDDYYYY(form.getDate16()),7);
                  }
                catch (Exception ex)
                  {
                    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.form.badchecklistdate"));
                  }
              }

            // clean up
            t.save();
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in SaveCheckList do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  SaveCheckList .doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

        // Check for any errors
        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            //return  new ActionForward(mapping.getInput());
        }

        ActionForward actionForward = mapping.findForward("showChecklist");
        String returnPath = actionForward.getPath();
        int periodpos = returnPath.indexOf(".do");
        returnPath = returnPath.substring(0,periodpos);
        
        ModuleConfig modconf =  mapping.getModuleConfig();
        Action finalAction = null;
        ActionMapping finalAC = (ActionMapping) modconf.findActionConfig(returnPath);
        
        try {
            Class clazz = Class.forName(finalAC.getType());
            finalAction = (Action) clazz.newInstance();
            return finalAction.execute(finalAC,form,request,response);
            // AppLog.trace("chaining to:"+finalAction.toString());
        } catch (Exception e) {
            // AppLog.warning("Could not find chained action: " + e.getMessage());
        	logger.error("Could not find chained action: ", e);
            return mapping.findForward("search");
        }
        
        // can't chaing direct to another action: return forwardShowCaseStatus(mapping);
    }
}
