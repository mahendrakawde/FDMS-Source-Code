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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class NewAtNeed extends Action {
    
    private Logger logger = Logger.getLogger(NewAtNeed.class.getName());    
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        /**@todo: implement this method */
        //throw new IllegalAccessError("Method not yet implemented").;
        // AppLog.trace("Performing New At Need.doPerform action.");
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        fdms.ui.struts.form.FirstCallInformationForm firstCallInformation = null;
        DatabaseTransaction t = null;
        LocaleDTO dbLocale = null;   

        sessionUser.setCurrentCaseID(0);   
        
        try {
            firstCallInformation = new fdms.ui.struts.form.FirstCallInformationForm();
            // Get the next contract number from locale record.
            
            
            int contractNumber = SessionHelpers.getContractNumber(sessionUser.getDbLookup(), sessionUser.getRegion());
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            firstCallInformation.setShowInformantContractSigner(false);
            
			CompanyOptionsManager coMgr = new CompanyOptionsManager ();
		    int turnOffcontractNumberOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_TURN_OFF_AUTOMATIC_ASSIGN_CONTRACT_NUMBER);
		    if (turnOffcontractNumberOption == 0){
	            firstCallInformation.setContractNumber(String.valueOf(contractNumber));
	            firstCallInformation.setNextContractNumber(String.valueOf(contractNumber));
	            firstCallInformation.setPreSetContractNumber(String.valueOf(contractNumber));
		    }
		    
            SessionHelpers.setChapelListInSession(request);
            SessionHelpers.setArrangerListInSession(request);
            // Initialize dates with current date if the user has set the preference to do so
            if (dbLocale.isAutoFillArrangeDate()) {
            	firstCallInformation.setArrangeDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
            }
            if (dbLocale.isAutoFillDateOfDeath()) {
            	firstCallInformation.setDeathDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
            }
            firstCallInformation.setDirective("add");
            firstCallInformation.setFacility("");

            // Add form to session
            
            request.setAttribute("firstCallInformation",firstCallInformation);

            DbLocation[] dbLocations = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());            
            request.setAttribute("chapels", dbLocations);            
        } catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in NewAtNeed.doPerform. "+pe.getCause());
            
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
            logger.error("PersistenceException in doPerform() : " + pe);
        } catch(Exception pe) {
            // AppLog.criticalError("Exception in BilltoAddChange .doPerform. "+pe);
            // pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
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
        
        ActionForward actionForward = mapping.findForward("newAtNeedToFirstCallJsp");
        if( !errors.isEmpty() ) {
            // AppLog.info("NewAtNeed Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );            
        }
        
        return  actionForward;
    }
}
