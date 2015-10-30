package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ArrangerEditForm;


public class ProcessArrangerEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessArrangerEdit.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        ArrangerEditForm form = (ArrangerEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbArrangers dbArranger = null;
        String submitType = form.getSubmitbutton();
        
        // AppLog.trace("ProcessArrangerEdit submit ="+submitType);
        
        if (!submitType.equals("save")) {
            // AppLog.trace("ProcessArrangerEdit forwarding to ShowArrangerAdminGlobal");
            return mapping.findForward("showArrangerAdminGlobal");
        }
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Process the Arranger add/change/delete
            if (form.getDirective().equals("add")) {
                dbArranger = new DbArrangers();
                dbArranger.setNew();
                dbArranger.setLocale(sessionUser.getRegion());
            } else {
                dbArranger = FdmsDb.getInstance().getArranger(t, Integer.parseInt(form.getIdentity()));
            }
            
            if (form.getDirective().equals("delete")) {
             //   dbArranger.remove();
            	dbArranger.setDeleteCode("D");
            	dbArranger.setModifications(Persistent.MODIFIED);
                t.addPersistent(dbArranger);
                t.save();
            } else {
                validateArrangerData(sessionUser, form, dbArranger, errors);
                if (errors.isEmpty()) {
                    setArranger(form, dbArranger, errors);
                    if (errors.isEmpty()) {
                        if (form.getDirective().equals("add")) {
                            t.addPersistent(dbArranger);
                        }
                        t.save();
                    }
                }
            }
            
        } catch (PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe, pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Error in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
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
        
        //Action Forward Logic
        
        ActionForward actionForward = mapping.findForward("showArrangerAdminGlobal");
        
        if (!errors.isEmpty()) {
            // AppLog.info("ProcessArrangerEdits Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        // AppLog.trace("Leaving ProcessArrangerEdit.");
        return actionForward;
        
    }
    /**
     * Called from ProcessArrangerEdit, this Method sets the Arranger record
     * from the WebFDMSArrangerEdit.jsp values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/9/2002 10:05:36 AM)
     */
    public void setArranger(fdms.ui.struts.form.ArrangerEditForm form, DbArrangers dbArranger, ActionErrors errors) {
        String errorField = new String();
        String errorStr = "";
        
        try {
            errorField = "Name";
            errorStr = "name";
            dbArranger.setName(form.getName());
            
            errorField = "SocialSecurityNumber";
            errorStr = "ssn";
            dbArranger.setSocialSecurityNo(form.getSsn());
            
            errorField = "LicenseNumber";
            errorStr = "licenseNumber";
            dbArranger.setLicenseNumber(form.getLicenseNumber());
            
            errorField = "PnLicenseNumber";
            errorStr = "pnLicenseNumber";
            dbArranger.setPnLicenseNumber(form.getPnLicenseNumber());
            
            errorField = "BurialLicenseNumber";
            errorStr = "burialLicenseNumber";
            dbArranger.setBurialLicenseNumber(form.getBurialLicenseNumber());
                   
            errorField = "EmbalmerLicenseNumber";
            errorStr = "embalmerLicenseNumber";
            dbArranger.setEmbalmerLicenseNumber(form.getEmbalmerLicenseNumber());
                   
            errorField = "IsCounselor";
            errorStr = "isCounselor";
            dbArranger.setIsCounselor(form.getIsCounselor());
            
            dbArranger.setCommissionLevel(form.getCommissionLevel());
            
            dbArranger.setManagerForCommission(form.isManagerForCommission());
            dbArranger.setManagerCommissionLevel(form.getManagerCommissionLevel());
            
            if(form.isInactive()){
            	dbArranger.setDeleteCode("D");
            }else{
            	dbArranger.setDeleteCode("");
            }
            
            logger.debug("IsCounselor = " + Boolean.toString(form.getIsCounselor()));
                   
        } catch (Exception e) {
            logger.error("Error in setArranger() : ", e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.arranger.set" + errorField));
            if (!errorStr.equals("")) formErrors.add(errorStr);
        }
        
        return;
        
    }
    /**
     * Called from ProcessArrangerEdit, this Method validates the Arranger
     * data from WebFDMSArrangerEdit.jsp. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/9/2002 9:55:02 AM)
     */
    public void validateArrangerData(
            DbUserSession sessionUser, 
            fdms.ui.struts.form.ArrangerEditForm form, 
            DbArrangers dbArranger, 
            ActionErrors errors) {
        
        logger.debug("Called validateArrangerData()");
        
        try {
            // Verify Arranger doesn't already exist using the various license numbers and SSN.
            com.aldorsolutions.webfdms.beans.DbArrangers chkArranger = new com.aldorsolutions.webfdms.beans.DbArrangers();
            chkArranger.setId(Short.parseShort(form.getIdentity()));
            chkArranger.setLocale(sessionUser.getRegion());
            chkArranger.setBurialLicenseNumber(form.getBurialLicenseNumber());
            chkArranger.setEmbalmerLicenseNumber(form.getEmbalmerLicenseNumber());
            chkArranger.setLicenseNumber(form.getLicenseNumber());
            chkArranger.setPnLicenseNumber(form.getPnLicenseNumber());
            chkArranger.setSocialSecurityNo(form.getSsn());
            chkArranger.setIsCounselor(form.getIsCounselor());
            
            // AppLog.trace("Entering getArrangerExists checking Arranger Licenses.");
            if (form.getDirective().equals("add")) {
	            if (com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getArrangerExists(sessionUser, chkArranger)) {
	                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.arranger.arrangerExists"));
	                return;
	            }
            }
            
            // Arranger Name is required.
            if (form.getName() == null || form.getName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.arranger.nullName"));
                formErrors.add("name");
            }
        } catch (Exception e) {
            logger.error("Error in validateArrangerData() : ", e);
        }
    }
}
