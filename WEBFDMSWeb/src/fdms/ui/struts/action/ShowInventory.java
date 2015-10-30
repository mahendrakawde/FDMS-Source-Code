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

import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.InventoryMasterLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.InventoryForm;


/**
 * This global action shows INVENTORY.JSP.
 */

public class ShowInventory extends Action {
    
    private Logger logger = Logger.getLogger(ShowInventory.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        DatabaseTransaction t =null;
        InventoryForm inventory = new InventoryForm();
        
        // Specific to Inventory Master
        DbInvMaster[] dbInvMaster = null;
        java.util.ArrayList inventoryMasterList = null;
        java.util.ArrayList inventoryMasterDisplayList = null;
        java.util.ArrayList dbInvMasterList = null;
        boolean specificCategory = false;
        
        // Specific to Category Option List
        DbSpeedData[] dbSpeedData = null;
        java.util.ArrayList dbCategoryList = null;
        
        if (sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        
        
        
        //Database Access Logic
        try {
            //Populate the Inventory List Objects
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbInvMaster = FdmsDb.getInstance().getInvMasterActive( t , "A", sessionUser.getRegion() );
            inventoryMasterList = new java.util.ArrayList();
            inventoryMasterDisplayList = new java.util.ArrayList();
            dbInvMasterList = new java.util.ArrayList();
            String findName = null;
            findName = request.getParameter("findName");
            if (findName != null){
            	findName = findName.toUpperCase();
            }
            for(int i=0 ; i < dbInvMaster.length ; i++) {
                InventoryMasterLineItem inventoryMasterLineItem = new com.aldorsolutions.webfdms.beans.custom.InventoryMasterLineItem(dbInvMaster[i]);
                dbInvMasterList.add(i,dbInvMaster[i]);
                inventoryMasterList.add(i,inventoryMasterLineItem);
                String listLabel = inventoryMasterLineItem.getItemName()+" "+inventoryMasterLineItem.getItemSupplierCode()+" "+ inventoryMasterLineItem.getItemDescription()  ;
                
                if (findName != null && findName.trim().length()> 0){
                	String searchMe = listLabel.toUpperCase();
                	
                	if (searchMe.contains(findName)) {
                		inventoryMasterDisplayList.add(new OptionsList(inventoryMasterLineItem.getItemId(),listLabel));
                	}
                }
                else {
                	inventoryMasterDisplayList.add(new OptionsList(inventoryMasterLineItem.getItemId(),listLabel));
                }
            }
            
            if (inventoryMasterDisplayList.isEmpty()) {
                inventory.setListStatus("EMPTY");
            } else {
                inventory.setListStatus("FULL");
            }
            
            //Populate the Category List Objects
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),  sessionUser.getRegion(), "PRODLINE");
            //AppLog.trace("dbSpeedData Length is "+ dbSpeedData.length );
            dbCategoryList = new java.util.ArrayList();
            
            
            for(int i=0 ; i < dbSpeedData.length ; i++) {
                String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
                dbCategoryList.add(new OptionsList(listLabel,listLabel));
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
        
        //Place Objects In Scope
        session.setAttribute("inventory",inventory);
        session.setAttribute("inventoryMasterDisplayList", inventoryMasterDisplayList);
        session.setAttribute("dbCategoryList", dbCategoryList);
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("inventory");
        if (!errors.isEmpty() ) {
            //AppLog.info("ShowInvenory Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        return  actionForward;
    }
}
