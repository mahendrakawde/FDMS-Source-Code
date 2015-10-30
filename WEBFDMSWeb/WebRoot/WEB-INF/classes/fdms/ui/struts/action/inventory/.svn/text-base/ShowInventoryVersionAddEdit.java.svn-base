package fdms.ui.struts.action.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.ServletException;
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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersion;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersionSel;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersionSelPeer;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersionSelSet;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.inventory.InventoryVersionAddEditForm;


public class ShowInventoryVersionAddEdit extends Action {

    private Logger logger = Logger.getLogger(ShowInventoryVersionAddEdit.class.getName());

  /**
  * Called from Inventory.JSP, this action handles the submit button from its form.
  * The form has multiple submit buttons to handle 
  * 1) Add a new item.
  * 2) Change the selected inventory item.
  * 3) Run Inventory Reports.
  * 4) Select to view Active/Inactive/All inventory items.
  * 5) Select to view by specific catalog.
  */
  /**@todo: implement this method */

    public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        InventoryVersionAddEditForm form = (InventoryVersionAddEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession user = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        
        session.setAttribute("locationsSize", null );
        session.setAttribute("localesSize", null );
	
        String invVersion = (String) request.getAttribute("InvVersionID");
        
        form.setInvVersionID(invVersion);
        
        int invVersionInt = FormatNumber.parseInteger(invVersion);
        
        if ( invVersionInt > 0 ) {

            try {
            	t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
            	DbInvVersion version = FdmsDb.getInstance().getInvVersion(t, invVersionInt);
                
                form.setActive(version.isActive());
                form.setCompanyID(version.getCompanyID());
                form.setDescription(version.getDescription());
                form.setName(version.getName());
                form.setInvFromDate(version.getInvFromDate());
                form.setInvToDate(version.getInvToDate());
                form.setPriceListID(version.getPriceListID());
                
                createLocationSelections ( version, form, t, user.getDbLookup() );
                createPriceListSelections ( version, form, t );
                

                session.setAttribute("inventoryVersionAddEditForm",form);

            } catch (PersistenceException pe) {
				logger.error("Persistence Exception in ShowInventoryCatalog.doPerform. " + pe);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
			} catch (Exception pe) {
				logger.error("Exception in ShowInventory.doPerform. ", pe);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
			} finally {
				if (t != null) {
					t.closeConnection();
					t = null;
				}
					
			}
        }
		
        // Action Forward
		ActionForward actionForward = mapping.findForward("success");

		if (!errors.isEmpty()) {
			logger.debug("inventoryCatalog Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		} 

		return actionForward;

	}          
    
    private void createLocationSelections (DbInvVersion version, InventoryVersionAddEditForm form, 
    		DatabaseTransaction t, String dbLookup ) throws PersistenceException {
    	DbInvVersionSelSet versionSel = new DbInvVersionSelSet();
        Hashtable columns = new Hashtable();
        columns.put(DbInvVersionSelPeer.COMPANYID, String.valueOf(version.getCompanyID()));
        columns.put(DbInvVersionSelPeer.INVVERSIONID, String.valueOf(version.getId()));
        
        versionSel.restore(t, columns);
        
        PersistentI [] objs = versionSel.getPersistents();
        
        HashSet selectedLocales = new HashSet();
        HashSet selectedLocations = new HashSet();
        
        for ( int i = 0; i < objs.length; i++ ) {
        	DbInvVersionSel versionSelected = (DbInvVersionSel) objs[i];
        	
        	selectedLocales.add( String.valueOf(versionSelected.getLocaleID()) );
        	selectedLocations.add( String.valueOf(versionSelected.getLocationID()) );
        }
        

        String [] arrayLocales = new String [selectedLocales.size()];
        String [] arrayLocations = new String [selectedLocations.size()];
        
        Iterator index = selectedLocales.iterator();
        int count = 0;
        
        while ( index.hasNext() ) {
        	String localeID = (String) index.next();
        	arrayLocales[count] = localeID;
        	count++;
        }

        index = selectedLocations.iterator();
        count = 0;
        
        while ( index.hasNext() ) {
        	String locationID = (String) index.next();
        	arrayLocations[count] = locationID;
        	count++;
        }
        
        form.setLocaleIDs(arrayLocales);
        form.setLocationIDs(arrayLocations);
        
        DbLocation dbLocations [] = FdmsDb.getInstance().getLocationsForCompany(t, (int) version.getCompanyID());
        
        ArrayList locationList = new ArrayList();
        
        //Populate the Location List
        for (int i=0; i < dbLocations.length; i++) {
            DbLocation location = (DbLocation) dbLocations[i];
            locationList.add(location);
        }
        
        form.setLocations(locationList);
        
        ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(dbLookup, (int) version.getCompanyID());
        ArrayList localeList = new ArrayList();
        
        // Populate the Locale List
        
        for ( LocaleDTO locale : locales ) {
        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
        }
        
        form.setLocales(localeList);
        
    	String js = createLocaleJavascript ( dbLocations, form.getLocationIDs() );
    	
    	form.setLocaleMapJavascript(js);
    }
    

    private void createPriceListSelections (DbInvVersion version, InventoryVersionAddEditForm form, 
    		DatabaseTransaction t ) throws PersistenceException {
    	ArrayList pricelists = new PriceListManager().getPriceListVersionsByCompanyID(t, version.getCompanyID());
    	        
        form.setPriceLists(pricelists);
    }
    

    
    private String createLocaleJavascript ( DbLocation [] dbLocations, String [] locationIds ) {
    	
    	String js = "\n";
		
    	js += "\tlocList = new Array(" + dbLocations.length + ");\n";
    	
    	for ( int j = 0; j < dbLocations.length; j++ ) {
    		DbLocation dbLocation = (DbLocation) dbLocations[j];
    		
    		int localeID = dbLocation.getLocaleNumber();
    		int locationID = dbLocation.getId();
    		String locName = dbLocation.getName();
    		boolean selected = false;
    		
    		if ( locationIds != null ) {
    			for ( int x = 0; x < locationIds.length; x++ ) {
    				int aLocID = Integer.parseInt( locationIds[x] );
				
    				if ( aLocID == locationID ) {
    					selected = true;
    				}
    			}
    		}
    		
			js += "\tlocList[" + j + "] = new location("+ localeID + ", "+ 
				locationID + ", \"" + locName + "\", " + selected +");\n";
			
			
    	}
    	
    	return ( js );
			
	}
}

