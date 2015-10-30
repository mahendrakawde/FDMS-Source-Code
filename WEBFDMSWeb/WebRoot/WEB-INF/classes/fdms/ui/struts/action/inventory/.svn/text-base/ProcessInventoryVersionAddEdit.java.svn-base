package fdms.ui.struts.action.inventory;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersion;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.inventory.bean.InventoryVersionManager;
import com.aldorsolutions.webfdms.inventory.model.InvVersionSelDTO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.inventory.InventoryVersionAddEditForm;

/**
 * @struts.action path="/processInventoryVersionAddEditForm" name="inventoryVersionAddEditForm"
 *                scope="session" input="/InventoryVersionAddEdit.jsp"
 *                validate="true"
 * @struts.action-forward name="success" path="/showInventoryVersion" redirect="true"
 * 
 */
public class ProcessInventoryVersionAddEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessInventoryVersionAddEdit.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        InventoryVersionAddEditForm form = (InventoryVersionAddEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbInvVersion dbInvVersion = null;
        
        String directive = form.getDirective();
        
       logger.debug("Show the directive : " + form.getDirective());
            
            if (directive.equals("add")) {
            	try {
            		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    dbInvVersion = new DbInvVersion();
                 	dbInvVersion.setNew();
                    
                 	if (errors.isEmpty()) {
                 		if (!setInvVersion(form, dbInvVersion)) {
                 			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error in ProcessInventoryCatalog.setInvMaster."));
                         } else {
                             t.addPersistent(dbInvVersion);
                             t.save();
                             setLocaleSelections(form, dbInvVersion, sessionUser);
                         }
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
            }
            
            if (directive.equals("change")) {
            	try {
            		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            		dbInvVersion = FdmsDb.getInstance().getInvVersion(t, FormatNumber.parseInteger(form.getInvVersionID()));
                    
                    if (errors.isEmpty()) {
                    	if (!setInvVersion(form, dbInvVersion)) {
                    		//AppLog.criticalError("Exception in ProcessInventoryCatalog.setInvMaster. ");
                    		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error in ProcessInventoryCatalog.setInvMaster."));
                    	} else {
                    		t.save();
                    		setLocaleSelections(form, dbInvVersion, sessionUser);
                    	}
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
            	
        }
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("showInventory");
        if (!errors.isEmpty()) {
            logger.debug("ProcessInventoryCatalog Invoking forward mapping getInput()");            
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        return actionForward;
    }
    /**
     * Called from ProcessInventoryCatalog, this Method sets the InvMaster record
     * from the InventoryCatalog form values.  It returns a boolean of setStatus.
     * if setStatus is true, the dbInvMaster is set successfully.  If setStatus is false,
     * an error occured.
     * Creation date: (7/16/2002 3:12:04 PM)
     */
    private boolean setInvVersion(InventoryVersionAddEditForm form, DbInvVersion version) {
        
        boolean setStatus = true;
        
        try {
        	version.setActive(form.isActive());
            version.setCompanyID(FormatNumber.parseInteger(form.getCompanyID()));
            version.setDescription(form.getDescription());
            version.setId(FormatNumber.parseInteger(form.getInvVersionID()));
            version.setName(form.getName());
            version.setPriceListID(form.getPriceListID());
            
            if ( form.getInvFromDate() != null && form.getInvFromDate().length() > 0 ) {
            	version.setInvFromDate(FormatDate.convertToSQLDate(form.getInvFromDate()));
            } else {
            	version.setInvFromDate(null);
            }

            if ( form.getInvToDate() != null && form.getInvToDate().length() > 0 ) {
            	version.setInvToDate( FormatDate.convertToSQLDate(form.getInvFromDate()) );
            } else {
            	version.setInvToDate(null);
            }
            
            version.setTimestamp( new java.sql.Timestamp ( Calendar.getInstance().getTimeInMillis() ) );
            
        } catch (Exception e) {
            logger.error("Catching exception in setInvMaster ", e);
            setStatus = false;
        }
        return setStatus;
    }
    
    private void setLocaleSelections (InventoryVersionAddEditForm form, DbInvVersion version, DbUserSession sessionUser) 
    	throws PersistenceException {
    	
   		ArrayList locationList = form.getLocations();
        ArrayList saveLocations = new ArrayList();
        String [] locationIds  = form.getLocationIDs();
        
        for ( int i=0; i < locationList.size(); i++ ) {
        	DbLocation location = (DbLocation) locationList.get(i);
        	
        	int locationID = location.getId();
        	
        	if ( locationIds != null ) {
            	for ( int x = 0; x < locationIds.length; x++ ) {
            		int aLocID = Integer.parseInt(locationIds[x]);
            		
            		if ( aLocID == locationID ) {
            			int localeID = location.getLocaleNumber();
                    	
            			InvVersionSelDTO loc = new InvVersionSelDTO();
            			
            			loc.setLocationID( locationID );
            			loc.setLocaleID( localeID );
            			loc.setCompanyID( version.getCompanyID() );
            			
            			saveLocations.add(loc);
            		}
            		
            	}
        	}
        		
        }
        
        InventoryVersionManager versionManager = new InventoryVersionManager(version.getCompanyID(), sessionUser.getId());
        
        versionManager.deleteVersionLocations(version.getId());
        versionManager.addVersionLocations ( version.getId(), saveLocations );
    	
    }
    
}