package fdms.ui.struts.action.inventory;

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

import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersion;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.inventory.InventoryVersionForm;


/**
 * @author David Rollins
 * Date: Feb 28, 2007
 * File: ProcessInventoryVersion.java	
 * 
 */
public class ProcessInventoryVersion extends Action {
    
    private Logger logger = Logger.getLogger(ProcessInventoryVersion.class.getName());
    private ArrayList formErrors;
    
    /* (non-Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        InventoryVersionForm form = (InventoryVersionForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbLocation[] dbLocation = null;
        DbLocale userLocale = null;
        DbInvVersion dbInvVersion = null;
        
        String directive = form.getDirective();
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            logger.debug("Show the directive : " + form.getDirective());
            /*
            
            dbLocation = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
            userLocale = FdmsDb.getInstance().getLocale(t, sessionUser.getRegion());
            if (directive.equals("add")) {
                dbInvMaster = new DbInvMaster();
                dbInvMaster.setNew();
                validateData(t, form, errors, sessionUser);
                if (errors.isEmpty()) {
                    if (!setInvMaster(form, dbInvMaster, userLocale)) {
                        //AppLog.criticalError("Exception in ProcessInventoryCatalog.setInvMaster. ");
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error in ProcessInventoryCatalog.setInvMaster."));
                    } else {
                        t.addPersistent(dbInvMaster);
                        t.save();
                        t.closeConnection();
                        
                        t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                        updateInvChapelIndex(t, form, dbInvMaster, dbLocation);
                        t.save();
                    }
                }
            }
            */
            if (directive.equals("change")) {
            	dbInvVersion = FdmsDb.getInstance().getInvVersion(t, FormatNumber.parseInteger(form.getInvVersionID()));
                
            	validateData(t, form, errors, sessionUser);
            	request.setAttribute("InvVersionID", form.getInvVersionID() );
            	            
            	/*
            	if (!errors.isEmpty()) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error in ProcessInventoryVersion.setInvVersion."));
                }
                */
            	
                // Inactive the Inventory Item
                /*
                if (request.getParameter("submitbutton").equals("inactivate")) {
                    if (dbInvMaster.getCDeleteCode().equals("D")) {
                        dbInvMaster.setCDeleteCode(" ");
                    } else {
                        dbInvMaster.setCDeleteCode("D");
                    }
                    t.save();
                } else {
                */
                    
                    
                // }
            }
        }
        catch (PersistenceException pe) {
            logger.error("Persistence Exception in ShowInventoryCatalog.doPerform. " + pe, pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in ShowInventoryCatalog.doPerform. ", pe);
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
        ActionForward actionForward = mapping.findForward("success");
        if (!errors.isEmpty()) {
            logger.debug("ProcessInventoryCatalog Invoking forward mapping getInput()");            
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            
            ShowInventoryVersion showInv = new ShowInventoryVersion();
            showInv.setVersions(request, form);
            
            actionForward = new ActionForward(mapping.getInput());
        }
        return actionForward;
    }
    
    
    /**
     * @param t
     * @param form
     * @param errors
     * @param sessionUser
     */
    public void validateData(DatabaseTransaction t, InventoryVersionForm form, ActionErrors errors, DbUserSession sessionUser) {
        
        try {
            if (form.getInvVersionID().trim() == null || form.getInvVersionID().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullInvVersion"));
                formErrors.add("invVersionID");
            } // if
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessInventoryCatalog.do validateData. ", e);
        }
    }
}
