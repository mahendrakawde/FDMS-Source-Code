package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

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

import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbInvChapelIndex;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.InventoryMasterLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.InventoryForm;
import fdms.ui.struts.form.InvitemlocationForm;



/**
 * Shows the Master inventory items available to the selected location.
 * @author Srini Kotha
 *
 */
public class ShowInvItemLocation extends Action {
	private Logger logger = Logger.getLogger(ShowInvItemLocation.class.getName());

	/**
	 * Actions shows Master inventory items available to the selected location.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entered ShowInvItemLocation Action");
		
	
		ArrayList dbInvChapelIndexList = new ArrayList();
		ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        ArrayList userLocations = new ArrayList();
        ArrayList userLocationsOptions = new ArrayList();
        UserDAO dao = new UserDAO();
        userLocations = dao.getUserLocations(sessionUser.getId(),sessionUser.getRegion());
        
      
        InvitemlocationForm form = (InvitemlocationForm) actionForm;
        String location = (String)form.getLocation();
        int locationId = 0;
        if(location!= null && !"".equals(location)){
        	locationId = new Integer(location).intValue();
        }
        DatabaseTransaction t =null;
        InventoryForm inventory = new InventoryForm();
        
        // Specific to Inventory Master
        DbInvMaster[] dbInvMaster = null;
        java.util.ArrayList inventoryMasterList = null;
        java.util.ArrayList inventoryMasterDisplayList = null;
        java.util.ArrayList dbInvMasterList = null;
       
        
        
        java.util.ArrayList dbCategoryList = null;
        
        if (sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        ArrayList selectedList = new ArrayList();
        
        
        //Database Access Logic
        try {
            //Populate the Inventory List Objects
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbInvMaster = FdmsDb.getInstance().getInvMasterActive( t , "A", sessionUser.getRegion() );
            FdmsDb.getInstance().getUserLocationNames(t,userLocations ,sessionUser);
            inventoryMasterList = new java.util.ArrayList();
            inventoryMasterDisplayList = new java.util.ArrayList();
            dbInvMasterList = new java.util.ArrayList();
            DbInvChapelIndex dbInvChapelIndex = null;
            for(int i=0 ; i < dbInvMaster.length ; i++) {
                InventoryMasterLineItem inventoryMasterLineItem = new com.aldorsolutions.webfdms.beans.custom.InventoryMasterLineItem(dbInvMaster[i]);
                dbInvMasterList.add(i,dbInvMaster[i]);
                inventoryMasterList.add(i,inventoryMasterLineItem);
                String listLabel = inventoryMasterLineItem.getItemName()+" "+inventoryMasterLineItem.getItemSupplierCode()+" "+ inventoryMasterLineItem.getItemDescription()  ;
                inventoryMasterDisplayList.add(new OptionsList(inventoryMasterLineItem.getItemId(),listLabel));
                dbInvChapelIndex = FdmsDb.getInstance().getInvItemForLocation( t, locationId , inventoryMasterLineItem.getDbInvMaster().getId());
                if(dbInvChapelIndex != null){
                	selectedList.add(""+inventoryMasterLineItem.getDbInvMaster().getId());
                	dbInvChapelIndexList.add(dbInvChapelIndex);
                }
            }
            
            if (inventoryMasterDisplayList.isEmpty()) {
                inventory.setListStatus("EMPTY");
            } else {
                inventory.setListStatus("FULL");
            }
            
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowInventory.doPerform. " + pe);
        }
        catch(Exception pe) {
            logger.error("Exception in ShowInventory.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        for(int i=0; userLocations != null &&i < userLocations.size(); i++ ){
        	UserLocationDTO userlocation = (UserLocationDTO) userLocations.get(i);
        	userLocationsOptions.add(new OptionsList(""+userlocation.getLocationId(),userlocation.getName()));
        }
         
        form.setInvItemList((String[]) selectedList.toArray(new String[selectedList.size()]));
        form.setDbInvItemList((String[]) selectedList.toArray(new String[selectedList.size()]));
        form.setDbInvChapelIndexList(dbInvChapelIndexList);
        //Place Objects In Scope
        
        session.setAttribute("inventoryMasterDisplayList", inventoryMasterDisplayList);
        request.setAttribute("userLocations", userLocationsOptions);
        		
		return mapping.findForward("showinvitemlocation");
	}
}
