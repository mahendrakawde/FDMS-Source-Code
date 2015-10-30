package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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

import com.aldorassist.webfdms.model.SpeedDataRuleDTO;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.speeddata.bean.SpeedDataManagerBean;
import com.aldorsolutions.webfdms.speeddata.comparator.SpeedDataRuleNameComparator;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.TableListForm;

public class ShowEditTablesList extends Action {

	private Logger logger = Logger.getLogger(ShowEditTablesList.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		//AppLog.trace("showEditTablesList doPerform starting.");
		// Retrieve user information
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession) session
				.getAttribute(SessionValueKeys.DB_USER);

		// Instantiate form and collection needed for jswp
		TableListForm tableform = new TableListForm();
		ArrayList list = new ArrayList();

		// Retrieve list of categories
		DatabaseTransaction t = null;
		ArrayList catlist = new ArrayList();
		ArrayList locations = new ArrayList();
		
        logger.debug("useLocalizedSpeedData : " + sessionUser.getLocalizedSpeedData());
        session.setAttribute(
        		"USELOCALIZEDSPEEDDATA", 
				new Boolean(sessionUser.getLocalizedSpeedData()));  		

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			// Get categories
			catlist = FdmsDb.getInstance().getSpeedDataCategories(t);
			
			TreeSet set = new TreeSet( new SpeedDataRuleNameComparator () );

            SpeedDataManagerBean speedDataManagerBean = new SpeedDataManagerBean();
            
            List sdRulesList = speedDataManagerBean.getSpeedDataRules(
            			sessionUser.getDbLookup() );
            
            if (sdRulesList != null) {
            	
            	for ( int i = 0; i < sdRulesList.size(); i++ ) {
            		SpeedDataRuleDTO sdr = (SpeedDataRuleDTO) sdRulesList.get(i);
//            		if (sdr.getLockForAdmin()!= null && sdr.getLockForAdmin().compareToIgnoreCase("N") == 0){
            			set.add( sdr.getTabCategory() );
//            		}
            	}
            	
            }       
			
            java.util.Iterator listIt = catlist.iterator();
			String aCat;
			while (listIt.hasNext()) {
				aCat = (String) listIt.next();
				if (!aCat.toUpperCase().equals("VERSIONS")) {
					//AppLog.trace("SpeedData Categories: "+aCat);
					
					if ( set.contains(aCat) == false ) {
						set.add( aCat );
					}
					
				}
			}
            
			// if it is aldor's admin then we don't eliminate the restriction of category.
			if (sessionUser.getSecurityFdmsAdmin()!=1){
				for ( int i = 0; i < sdRulesList.size(); i++ ) {
					SpeedDataRuleDTO sdr = (SpeedDataRuleDTO) sdRulesList.get(i);
					if (sdr.getLockForAdmin()!= null && sdr.getLockForAdmin().compareToIgnoreCase("Y") == 0){
						set.remove(sdr.getTabCategory());
					}
				}
			}
			
			Iterator index = set.iterator();
			
			while ( index.hasNext() ) {
				String tabCategory = (String) index.next();
				list.add(new OptionsList(tabCategory, tabCategory));
			}
			
			DbLocation[] dbLocations = FdmsDb.getInstance()
				.getLocationsForRegion(t, sessionUser.getRegion());
			
			if ((dbLocations != null) && (dbLocations.length > 0)) {
				for (int i = 0; i < dbLocations.length; i++) {
					locations.add(dbLocations[i]);
				}
			}
			
		} catch (PersistenceException e) {
			logger.error("ShowEditTablesList Persistence Exception. " + e);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.login.invalid"));
			return mapping.findForward("logout");
		} finally {
			if (t != null)
				t.closeConnection();
		}

		// store collections in request
		session.setAttribute("displayCategories", list);
		// store form beans in request
		session.setAttribute("tableListForm", tableform);

		session.setAttribute("tableListFormLocations", locations);

		return mapping.findForward("editTablesList");
	}
}
