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

import com.aldorsolutions.webfdms.beans.DbDonations;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.DonationsForm;


public class ShowDonations extends Action {
    
    private Logger logger = Logger.getLogger(ShowDonations.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbDonations[] dbDonations = null;
        DonationsForm donations = new DonationsForm();
        int vitalsid=0;
        java.util.ArrayList donationsDisplayList = null;
        DbFormsAvailable[] donationForms = null;
        java.util.ArrayList formsList = null;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            logger.debug("ShowPayment. Invalid VitalsID to process : " + vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
            
        //Database Access Logic
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbDonations = FdmsDb.getInstance(). getDonationsForID( t , vitalsid );
            DbVitalsDeceased dbVitalsDeceased = FdmsDb.getInstance().getVitalsDeceased( t, vitalsid );
            //Populate the DonationsForm object
            donations.setDeceasedFullName( dbVitalsDeceased.getDecFullName() );
            //AppLog.trace("dbDonations Length is "+ dbDonations.length );
            if (dbDonations.length < 1){
                donations.setDonation("EMPTY")	;
            } else {
                donations.setDonation("");
            }
            //Populate the List Objects
            donationsDisplayList = new java.util.ArrayList();
            for(int i=0 ; i < dbDonations.length ; i++){
            	String listLabel = "";
            	// if the user entered either a first or last name
            	if (dbDonations[i].getFirstName().compareTo("") != 0 || dbDonations[i].getLastName().compareTo("") != 0)
            		listLabel += dbDonations[i].getFirstName()+ " " + dbDonations[i].getLastName()+", ";
                listLabel += dbDonations[i].getCharityName();
                if (dbDonations[i].getCity().compareTo("") != 0) 
                	listLabel += ", " + dbDonations[i].getCity();
				if (dbDonations[i].getState().compareTo("") != 0)	
					listLabel += ", " + dbDonations[i].getState();
				if (dbDonations[i].getDonationAmount() > 0)
					listLabel += ", "+FormatCurrency.toCurrency((long)dbDonations[i].getDonationAmount()) ;
                donationsDisplayList.add(new OptionsList(String.valueOf(dbDonations[i].getId()),listLabel));
            }
            // Get donation forms
            donationForms = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(), DbFormsAvailable.DONATION_TYPE);
            formsList = new java.util.ArrayList();
            for (int i=0; i<donationForms.length; i++){
                formsList.add(new OptionsList(String.valueOf(donationForms[i].getId()), donationForms[i].getDescription()));
            }
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowDonation.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ShowDonation.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        
        //Place Objects In Scope
        session.setAttribute("donations",donations);
        session.setAttribute("donationsDisplayList", donationsDisplayList);
        session.setAttribute("donationForms", formsList);
        return mapping.findForward("donations");
        
    }
}
