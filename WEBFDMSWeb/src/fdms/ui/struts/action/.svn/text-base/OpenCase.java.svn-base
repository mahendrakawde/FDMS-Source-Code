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
import org.apache.struts.config.ModuleConfig;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserPermissionsBean;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.OpenCaseForm;

public class OpenCase extends Action {
    
    private Logger logger = Logger.getLogger(OpenCase.class.getName());

    /**
     * OpenCase.doPerform takes the selected name from SEARCH.JSP and attempts to
     * access the database to retrieve all the case information.
     * If successful, the case status page is displayed
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        // AppLog.trace("OpenCase Action doPerform");
        
        OpenCaseForm form = (OpenCaseForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        LocaleDTO alocale = null;
        DatabaseTransaction t = null;
//        HttpSession session = request.getSession();
        int vitalsid = 0;
        //  Determine calling program
        String caller = (String)request.getAttribute("From");
        String s = form.getId();
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);

        if (caller != null && caller.compareToIgnoreCase("CallInfo") == 0) {
            // From Call Info action - get vitalsid from UserSession object.
            vitalsid = sessionUser.getCurrentCaseID();
        } else {
            if (caller != null && caller.compareToIgnoreCase("update") == 0) {
                // From Update action - get vitalsid from UserSession object.
                vitalsid = sessionUser.getCurrentCaseID();
            } else if (s==null && vitalsid > 0){
                // Got here with ID as request parameter, handled by getVitalsIdFromSession()
            }
            else {
                // From Search-Open - get vitalsid from form object
//            	request.setAttribute("vitalsId",new Integer(vitalsid));
            	
                try {
                    vitalsid = Integer.parseInt(s);
                    // store current vitals ID in user session object
                    sessionUser.setCurrentCaseID(vitalsid);
                    // Store vitals-ID as request attribute
                    SessionHelpers.setVitalsIdInRequest(request,vitalsid);  
                    sessionUser.setCurrentCaseID(vitalsid);
                    // Check if licensed for at-need or pre-need only
                    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(),sessionUser.getRegion());
                    
                    if (alocale.getAtneedLicense().compareToIgnoreCase("N")==0){
                        // if pre-need only then open pre-need status page instead of case status
                        return mapping.findForward("showPnStatus");
                    }
                    
                    vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
                    
//                    if ( vitalsid > 0){
//                    	int locationNumber = 0;
//                    	DbCase caseinfo = null;
//                    	try {
//                			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
//                			
//                			caseinfo = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getCase(t, vitalsid);
//                			locationNumber = caseinfo.getChapelNumber();
//                			
//                			DbLocation dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocation(t, locationNumber);
//                			SessionHelpers.setWebsiteInRequest(request,dbLocation.getWebsite());  
//                    	} catch (PersistenceException e) {
//                			// TODO Auto-generated catch block
//                			e.printStackTrace();
//                		} finally {
//                			if (t != null)
//                				t.closeConnection();
//                		}	
//                    }
                    
                    // AppLog.trace("Got here from: Search, processing ID:"+vitalsid);
                } catch (Exception nfe) {
                    // AppLog.trace("Cannot convert vitals ID from search:"+s+"\n"+nfe);
                    logger.error("Error in doPerform() : ", nfe);
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",nfe.toString()));
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
        }
        
        // if errors found, go to introduction page (there is no input page)
        if (!errors.isEmpty()) {
            // AppLog.trace("OpenCase errors found. display main");
            saveErrors(request, errors );
            return mapping.findForward("main");
        }
        
   /* The following code allows chaining to another action instead of to a JSP.  In this case,
          we are forwarding to the "showCaseStatus" action to prepare for showing the case status
          JSP for the case just opened.*/
        ActionForward actionForward = mapping.findForward("showCaseStatusGlobal");
        String returnPath = actionForward.getPath();
        int periodpos = returnPath.indexOf(".do");
        returnPath = returnPath.substring(0,periodpos);
        ModuleConfig modconf =  mapping.getModuleConfig();
        Action finalAction = null;
        ActionMapping finalAC = (ActionMapping) modconf.findActionConfig(returnPath);
        try {
            Class clazz = Class.forName(finalAC.getType());
            finalAction = (Action) clazz.newInstance();
            return finalAction.execute(finalAC,form,request,response);
            // AppLog.trace("chaining to:"+finalAction.toString());
        } catch (Exception e) {
            // AppLog.warning("Could not find chained action: " + e.getMessage());
            logger.error("Error in doPerform() : ", e);
            return mapping.findForward("search");
        }
                        
    }
}
