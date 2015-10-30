package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

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

import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.cache.SpeedDataCache;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.TableContentsForm;
import fdms.ui.struts.form.TableListForm;

public class ShowSelectedTableAction extends Action {
    
    private Logger logger = Logger.getLogger(ShowSelectedTableAction.class.getName());
    private ArrayList <String> formErrors;
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList<String> ();
        DatabaseTransaction t = null;
        TableListForm form = (TableListForm) actionForm;
        HttpSession session = request.getSession();
        // Retrieve user information
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
           
       
        String type = form.getSubmitbutton();
        if("updateCache".equals(type)){
        	try {

//    			CompanyManagerBean cmBean = new CompanyManagerBean();
//    			List list = cmBean.getCompanies(true);
//    			for (int i = 0; list != null && i < list.size(); i++) {	
//    				CompanyDTO company = (CompanyDTO) list.get(i);
//    				t = new DatabaseTransaction (company.getDbLookup(), sessionUser.getId(),
//    						sessionUser.getCompanyID(), sessionUser.getRegion());      
//    				SpeedDataCache.getInstance().setRefresh(company.getDbLookup());
//    				SpeedDataCache.getInstance().getCache(company.getDbLookup());
//    			}
        		
        		
        			// do only for the individual company of the user.
    				t = new DatabaseTransaction (sessionUser.getDbLookup(), sessionUser.getId(),
    						sessionUser.getCompanyID(), sessionUser.getRegion());      
    				SpeedDataCache.getInstance().setRefresh(sessionUser.getDbLookup());
    				SpeedDataCache.getInstance().getCache(sessionUser.getDbLookup());

    			
    		} catch (Exception ex) {
    			throw new ServletException("PersistenceException durig Cache initializaion", ex);
    		} finally {
    			if (t != null) {
    				t.closeConnection();
    			}
    		}
    		form.setSubmitbutton("");
    		request.setAttribute("msg", "Cache Refreshed Successfully.");
    		 return (new ActionForward(mapping.getInput()));
        	}
        	
        
        
        //AppLog.trace("ShowSelectTableAction doPerform");
        ActionErrors errors = new ActionErrors();
       

        // Check category selected
        String category = form.getSpeedDataCategory();
        if (category==null || category.compareTo(" ")<=0){
            errors.add(
            		ActionErrors.GLOBAL_ERROR, 
            		new ActionError("error.tables.noselect"));
            formErrors.add("speedDataCategory");
            category="none";
        }
        
        String locationIdStr = form.getLocationId();
        int locationId = 0;
        
        if (locationIdStr == null || "".equals(locationIdStr.trim())) {
        	errors.add(
        			ActionErrors.GLOBAL_ERROR,
					new ActionError("error.tables.noselect.location"));
        	formErrors.add("locationId");        	
        } else {
        	try {
        		locationId = Integer.parseInt(locationIdStr);
        	} catch (NumberFormatException e) {
        		// unable to parse int from String
        	}
        }
        
        logger.debug("LocationId : " + locationId);
                
        // Instantiate form and collection needed for jsp
        TableContentsForm 	contform = new TableContentsForm();
        DbSpeedData[]		contlist = null;
        
        // Retrieve list of categories
       
        ArrayList	<OptionsList> list = new ArrayList <OptionsList> ();
        
        try {
            contform.setCategory(category);
            contform.setLocationId(Integer.toString(locationId));
            
            //AppLog.trace("ShowSelectedTable TableContentsForm category:"+contform.getCategory());
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Get categories
            if (locationId == 0)
            	contlist = FdmsDb.getInstance()
					.getSpeedData(sessionUser.getDbLookup(),sessionUser.getRegion(),category);
            else
            	contlist = FdmsDb.getInstance()
					.getSpeedDataLocation(sessionUser.getDbLookup(),sessionUser.getRegion(),locationId,category,null);
            
            if ( contlist != null )
            {

                for (int i=0; i<contlist.length; i++){
                    //AppLog.trace("SpeedData rows: "+contlist[i].getData());
                    String value = Integer.toString(contlist[i].getId());
                    list.add(new OptionsList(value ,contlist[i].getData()));
                }
            }
            
        } catch (PersistenceException e){
            logger.error("ShowSelectedTableAction Persistence Exception. " + e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
            return mapping.findForward("logout");
        } finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            // AppLog.info("ShowSelectedTableAction: user entry errors found.");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }
        // Remove previous page's collections and form
        // can't do this because we loop back to this action after saving a row
        // need to move to a "cancel" process within this action.
        //session.removeAttribute("displayCategories");
        //session.removeAttribute("tableListForm");        
        
        // store collections in request
        session.setAttribute("displayContents", list);
        // store form beans in request
        session.setAttribute("tableContentsForm", contform);
        
        return mapping.findForward("displayTableContentsList");
    }
}
