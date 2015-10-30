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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;


public class NewPreNeed extends Action {
    
    private Logger logger = Logger.getLogger(NewPreNeed.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        // AppLog.trace("NewPreNeed.");
        
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = null;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        fdms.ui.struts.form.PreNeedForm preNeed = new fdms.ui.struts.form.PreNeedForm();
        DatabaseTransaction t = null;
        
        sessionUser.setCurrentCaseID(0);
        
        //Check for a DbUserSession
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            // AppLog.info("NewPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
        preNeed.setDirective("add");
        preNeed.setVitalsId("0");
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // Check if we should be using the Abbit Pre-need or this generic pre-need
            // by fetching the locale for our current user
            LocaleDTO region  = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            if (region!=null && region.getPreneedLicense().compareToIgnoreCase("A")==0){
                SessionHelpers.setVitalsIdInRequest(request,0);
                return  mapping.findForward("showPnStatus");
            }
            
            
            // Set collections in session
            //session.setAttribute("counselorList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Counselors"));
            SessionHelpers.setArrangerListInSession(request);
            session.setAttribute("pnSourceList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnSource"));
            session.setAttribute("saleTypeList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "SaleType"));
            session.setAttribute("pnCasketList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnCasket"));
            session.setAttribute("pnVaultList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnVault"));
            session.setAttribute("pnUrnList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnUrn"));
            session.setAttribute("pnFundTypeList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnFundType"));
            session.setAttribute("pnDepositoryList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnDepository"));
            
            // Create the Locations list
            SessionHelpers.setChapelListInSession(request);
            //chapels = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
            //for (int i=0; i<chapels.length; i++){
            //   locations.add( new OptionsList(Integer.toString(chapels[i].getId()) ,chapels[i].getName()));
            //}
            //session.setAttribute("mortuaryLocationList", locations);
            
        } catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in NewPreNeed.doPerform. "+pe.getCause());
            logger.error("PersistenceException in doPerform() : " + pe);
        } catch(Exception pe) {
            // AppLog.warning("Exception in NewPreNeed.doPerform. "+pe);
            logger.error("Error in doPerform() : ", pe);
        } finally {
            if (t != null) {
                try {
					t.closeConnection();
					t = null;
				} catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        // Add form to session
        request.setAttribute("preNeed",preNeed);
        
        // AppLog.trace("Finished NewPreNeed");
        actionForward = mapping.findForward("preNeed");
        
        if (!errors.isEmpty()) {
            // AppLog.info("NewPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
        return  actionForward;
        
    }
}
