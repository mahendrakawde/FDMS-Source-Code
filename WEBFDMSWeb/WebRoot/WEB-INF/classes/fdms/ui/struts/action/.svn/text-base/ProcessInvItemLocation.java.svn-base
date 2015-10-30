package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbInvChapelIndex;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.InvitemlocationForm;

/**
 * Assigns selected Master inventory items to the selected location 
 * @author Srini Kotha
 *
 */
public class ProcessInvItemLocation extends Action {
	private Logger logger = Logger.getLogger(ProcessInvItemLocation.class.getName());

	/**
	 * Action assigns selected Master inventory items to the selected location
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Entered ProcessInvItemLocation Action");
		DatabaseTransaction t =null;
	
		InvitemlocationForm form = (InvitemlocationForm) actionForm;
		HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
		
        
		String type = request.getParameter("submitbutton");
		
		if("location".equalsIgnoreCase(type)){
					
			return mapping.findForward("invItemLocation");
		}
		
		String location = (String)form.getLocation();
        int locationId = 0;
        if(location!= null && !"".equals(location)){
        	locationId = new Integer(location).intValue();
        }
        
		String[] dbList = (String[])form.getDbInvItemList();
		String[] userList = (String[])form.getInvItemList();
		// add inv item chapel index
		
		DbInvChapelIndex dbInvChapelIndex = null;
	
		try{
			
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		    // add newly selected items
			for(int i=0 ; userList!= null && i< userList.length; i++){
				if(!isExists(dbList, userList[i]) && !"".equals(userList[i])){
					dbInvChapelIndex = new DbInvChapelIndex();
					dbInvChapelIndex.setLocationNumber(locationId);
					dbInvChapelIndex.setItemNumber(new Integer(userList[i]).intValue());
					dbInvChapelIndex.setNew();
					t.addPersistent(dbInvChapelIndex);
				}
			}
			ArrayList  dbInvChapelIndexList = (ArrayList)form.getDbInvChapelIndexList();
			// delete inv item chapel index.
			for(int i=0 ; dbList!= null && i< dbList.length; i++){
				if(!isExists(userList, dbList[i])){
					dbInvChapelIndex = getChapelIndex(locationId, new Integer(dbList[i]).intValue(),
							dbInvChapelIndexList);
					
					if(dbInvChapelIndex != null){
						dbInvChapelIndex.setModifications(Persistent.DELETED);
						t.addPersistent(dbInvChapelIndex);
					}
				}
			}
			t.save();
		}catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowInventory.doPerform. " + pe);
        }
        catch(Exception pe) {
            logger.error("Exception in ShowInventory.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
		
		
		return mapping.findForward("invItemLocation");
	}

	/**
	 * checks given inv item in the list or not
	 * @param list List of Inviteems
	 * @param item invItem to check
	 * @return true if exists else false
	 */
	public boolean isExists(String[] list, String item) {

		for (int i = 0; list != null && i < list.length; i++) {
			if (item.equals(list[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets InvChapelIndex from list if exists
	 * @param locationId locationId to check
	 * @param itemNumber Item number to check
	 * @param list List of InvChapelIndex
	 * @return InvChapelIndex
	 */
	public DbInvChapelIndex getChapelIndex(int locationId, int itemNumber, ArrayList list) {
		DbInvChapelIndex dbInvChapelIndex = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			dbInvChapelIndex = (DbInvChapelIndex) list.get(i);
			if (dbInvChapelIndex.getItemNumber() == itemNumber
					&& dbInvChapelIndex.getLocationNumber() == locationId) {
				break;
			}
		}

		return dbInvChapelIndex;
	}
}
