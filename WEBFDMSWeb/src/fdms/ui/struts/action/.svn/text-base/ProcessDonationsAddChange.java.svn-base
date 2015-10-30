package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbDonations;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.DonationsAddChangeForm;

/**
 * Input to this action is from DonationsAddChange.JSP.
 * This action will add or update the donation entered.
 * Amount, name, charity, payment type are all mandatory fields.
 * If errors are found, the input jsp is redisplayed.
 */
public class ProcessDonationsAddChange extends Action {
    
    private Logger logger = Logger.getLogger(ProcessDonationsAddChange.class.getName());
    private ArrayList formErrors;

    /**
     * Input to this action is from DonationsAddChange.JSP.
     * This action will add or update the donation entered.
     * Amount, name, charity, payment type are all mandatory fields.
     * If errors are found, the input jsp is redisplayed.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        DonationsAddChangeForm form = (DonationsAddChangeForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbDonations dbDonations = null;
        int vitalsid= 0;
        int donationAmount=0;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            logger.debug("ProcessDonations. Invalid VitalsID to process : " + vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
            
        //AppLog.trace("ProcessDonationsAddChange directive:"+ form.getDirective()+" "+ form.getDonatorId() );
        // Check for "exit" or "help"
        if( form.getDirective().equals("cancel") ) {
            return mapping.findForward("showDonations");
        }
        
        if( form.getDirective().equals("help") ) {
            return mapping.findForward("help");
        }
        
        // Validate form entries
        // Name is mandatory
        //AppLog.trace("name:"+form.getDonatorFirstName() +"-"+ form.getDonatorLastName());
        //if (form.getDonatorFirstName().compareTo(" ")<=0 && form.getDonatorLastName().compareTo(" ")<=0){
        //    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.donations.noname"));
        //    formErrors.add("donatorFirstName");
        //    formErrors.add("donatorLastName");
        //}
        // validate amount
        try {
            donationAmount = FormatCurrency.convertToCurrency(form.getDonationAmount());
            //if (donationAmount ==0){
            //    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.donations.noamount"));
            //    formErrors.add("donationAmount");
            //}
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.donations.noamount"));
            formErrors.add("donationAmount");
        }
        
        // Validate charity
        //AppLog.trace("org:"+form.getDonationOrganization());
        if (form.getDonationOrganization().compareTo(" ")<=0){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.donations.nocharity"));
            formErrors.add("donationOrganization");
        }
        // Validate payment type
        //AppLog.trace("type:"+form.getDonationPaymentType());
        //if (form.getDonationPaymentType()==null
        //|| form.getDonationPaymentType().compareTo(" ")<=0){
        //    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.donations.nopaytype"));
        //    formErrors.add("donationPaymentType");
        //}
        // Return to input JSP if errors found
        if( !errors.isEmpty() ){
            logger.debug("ProcessDonationsAddChange Invoking forward mapping getInput() ");
            request.setAttribute("formErrors", formErrors);
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
        // Save donation
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            if( form.getDonatorId().equals("add") ) {
                dbDonations = new DbDonations();
                dbDonations.setNew();
                dbDonations.setVitalsID( vitalsid );
                t.addPersistent(dbDonations);
            }
            else {
                dbDonations = FdmsDb.getInstance().getDonations(t, FormatNumber.parseInteger( form.getDonatorId() ) );
                //AppLog.trace("donations object:"+dbDonations);
                if (dbDonations==null){
                    //AppLog.error("ProcessDonationsAddChange invalid donation ID. unable to retrieve from DB.");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","ProcessDonationsAddChange invalid donation ID. unable to retrieve from DB."));
                    saveErrors(request, errors );
                    return (new ActionForward(mapping.getInput() ));
                }
                else if( form.getDirective().equals("delete") ) {
                	t.addPersistent(dbDonations);
                	dbDonations.remove();
                	t.save();
                	return mapping.findForward("showDonations");
                }
                
            }
            dbDonations.setHonorific(		form.getDonatorTitle(	));
            dbDonations.setFirstName( 		form.getDonatorFirstName() );
            dbDonations.setLastName(  		form.getDonatorLastName() );
            dbDonations.setDonationAmount( 	donationAmount );
            dbDonations.setAddr1(			form.getDonatorAddress( 	));
            dbDonations.setAddr2(			form.getDonatorAddress2( ));
            dbDonations.setAddr3(			form.getDonatorAddress3( 	));
            dbDonations.setCity(			form.getDonatorCity());
            dbDonations.setState(			form.getDonatorState(	));
            dbDonations.setPostalCode(		form.getDonatorZipCode());
            dbDonations.setCharityName(		form.getDonationOrganization());
            dbDonations.setPaymentType(		form.getDonationPaymentType() );
            t.save();
            //AppLog.trace("ProcessDonations finished."+dbDonations.getId());
        }
        catch(PersistenceException pe) {
            //AppLog.criticalError("Persistence Exception in ProcessDonationsAddChange "+pe.getMessage()+" "+pe.getCause());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getMessage()+" "+pe.getCause()));
        }
        catch(Exception pe) {
            //AppLog.criticalError("Exception in ProcessDonationsAddChange. "+pe);
            //pe.printStackTrace();
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
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("showDonations");
        if( !errors.isEmpty()) {
            logger.debug("ProcessDonationsAddChange Invoking forward mapping getInput()");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        return  actionForward;
    }
}
