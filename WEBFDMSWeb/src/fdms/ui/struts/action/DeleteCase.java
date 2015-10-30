package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbCustom;
import com.aldorsolutions.webfdms.beans.DbDonations;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVeteran;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CaseListForm;

public class DeleteCase extends Action {
    
    private Logger logger = Logger.getLogger(DeleteCase.class.getName());

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response) 
            throws javax.servlet.ServletException, java.io.IOException {
        
        CaseListForm form = (CaseListForm) actionForm;
        ActionForward actionForward = null;

        DbCase theCase = null;
        DbServices services = null;
        DbObituary obituary = null;
        DbVeteran veteran = null;
        DbFinancialSummary finan = null;
        DbHistory[] hist = null;
        DbSurvivor[] survivors = null;
        DbCustom custom = null;
        DbDonations[] donations = null;

        DatabaseTransaction t = null;
        int vitalsId;

        DbUserSession sessionUser = SessionHelpers.getUserSession(request);

            // Get vitalsId from request or session
            vitalsId = form.getVitalsId();

            try
              {
                // Get a database transaction.
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                
                // Get the data objects that must be deleted.
                theCase = FdmsDb.getInstance().getCase(t, vitalsId);
                services = FdmsDb.getInstance().getServices(t, vitalsId);
                obituary = FdmsDb.getInstance().getObituary(t, vitalsId);
                veteran = FdmsDb.getInstance().getVeteran(t, vitalsId);
                finan = FdmsDb.getInstance().getFinancial(t, vitalsId);
                hist = FdmsDb.getInstance().getHistoryForCase(t, vitalsId);
                custom = FdmsDb.getInstance().getCustom(t, vitalsId);
                donations = FdmsDb.getInstance().getDonationsForID(t, vitalsId);
                survivors = FdmsDb.getInstance().getSurvivorsForID(t, vitalsId);

    			// Delete the data objects.
    			if (custom != null) {
    				t.addPersistent(custom);
    				custom.remove();
    			}
    				
    			if (finan != null) {
    				t.addPersistent(finan);
    				finan.remove();
    			}
    			
    			if (veteran != null) {
    				t.addPersistent(veteran);
    				veteran.remove();
    			}
    			
    			if (obituary != null) {
    				t.addPersistent(obituary);
    				obituary.remove();
    			}
    			
    			if (services != null) {
    				t.addPersistent(services);
    				services.remove();
    			}

    			if (theCase != null) {
    				t.addPersistent(theCase);
    				theCase.remove();
    			}
    			
    			for (int i = 0; i < survivors.length; i++) {
    				if (survivors[i] != null) {
    					t.addPersistent(survivors[i]);
    					survivors[i].remove();
    				}
    			}
    			
    			for (int i = 0; i < hist.length; i++) {
    				if (hist[i] != null) {
    					t.addPersistent(hist[i]);
    					hist[i].remove();
    				}
    			}

    			for (int i = 0; i < donations.length; i++) {
    				if (donations[i] != null) {
    					t.addPersistent(donations[i]);
    					donations[i].remove();
    				}
    			}
                
                t.save();
              }
            catch (PersistenceException ex)
              {
                logger.error("Persistence Exception in DeleteCase.doPerform. " + ex, ex);
              }
            catch (Exception ex)
              {
                logger.error("Exception in DeleteCase.doPerform. ", ex);
              }
            finally
              {
                if (t != null){
					t.closeConnection();
					t = null;
				}
              }

            actionForward = mapping.findForward("caseListForm");


        return actionForward;
      }
  }
