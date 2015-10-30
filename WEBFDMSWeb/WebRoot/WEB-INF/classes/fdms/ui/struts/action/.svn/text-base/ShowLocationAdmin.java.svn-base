package fdms.ui.struts.action;

import java.util.ArrayList;
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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.comparators.LocaleLocationOptionsComparator;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.LocationListForm;


public class ShowLocationAdmin extends Action {
    
    private Logger logger = Logger.getLogger(ShowLocationAdmin.class.getName());
    
    /**
     * Gobal Action, this action prepares to show
     * WebFDMSLocationAdmin.jsp.
     * The LocationListForm allows the operator to select the location
     * to be modified or click add or delete.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("ShowLocationAdmin.doPerform");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbLocation[] dbLocation = null;
        LocationListForm locationListForm = new LocationListForm();
        //ArrayList locationlist = new ArrayList();
        TreeSet locationlist = new TreeSet(new LocaleLocationOptionsComparator() );
        
        //Database Access Logic
        try {
            logger.debug("Session region : " + sessionUser.getRegion());
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbLocation = FdmsDb.getInstance().getLocationsForCompany(t, sessionUser.getCompanyID());
            
            ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(sessionUser.getDbLookup(),
					(int) sessionUser.getCompanyID());
	        
            logger.debug("Dblocation is null : " + (dbLocation != null));
            
            //Populate the Location List
            for (int i=0; i < dbLocation.length; i++) {
                String listValue = String.valueOf(dbLocation[i].getId());
                String listLabel = dbLocation[i].getName();
                
                listLabel = getLocaleName(locales, dbLocation[i].getLocaleNumber()) + listLabel;
                
//                logger.debug("Location list label : " +  dbLocation[i].getId() + " = " + listLabel );
                locationlist.add(new OptionsList(listValue, listLabel));
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowLocationAdmin.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowLocationAdmin.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        session.setAttribute("locationList", locationListForm); // Form
        session.setAttribute("locationlist",locationlist); // list of locations
        
        return mapping.findForward("locationList");
    }
    
    private String getLocaleName (ArrayList <LocaleDTO> locales, int localeNumber) {
    	String localeName = "";
    	
    	if ( locales == null ) {
    		return ( localeName );
    	}
    	
        for ( LocaleDTO locale : locales ) {
        	if ( locale.getLocaleID() == localeNumber ) {
    			return ( locale.getName() + "-");
    		}
        }
    	
    	return ( localeName );
    	
    }
    
}
