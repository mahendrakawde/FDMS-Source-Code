package fdms.ui.struts.action;

import java.util.ArrayList;

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
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.DonationsAddChangeForm;
import fdms.ui.struts.form.DonationsForm;

/**
 * This action process the form in DONATIONS.JSP. Depending on the submit button,
 * either we prepare for an "ADD" or fetch the donation row to edit.
 * We forward to DonationAddChange.JSP
 */

public class ShowDonationsAddChange extends Action {

    private Logger logger = Logger.getLogger(ShowDonationsAddChange.class.getName());
    private ArrayList formErrors;

    /**
     * This action process the form in DONATIONS.JSP. Depending on the submit button,
     * either we prepare for an "ADD" or fetch the donation row to edit.
     * We forward to DonationAddChange.JSP
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("ShowDonationsAddChange. Start.");
        // clear print preview field.
        
        formErrors = new ArrayList();
        DonationsForm form = (DonationsForm) actionForm;
        form.setPreviewFile("");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        int vitalsid=0;
        DonationsAddChangeForm donationsAddChange = new DonationsAddChangeForm();
        DbDonations dbDonations = null;
        //DbVitalsDeceased dbVitalsDeceased = null;
        ArrayList titles = null;
        ArrayList pmttypes = null;

        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            logger.debug("ShowPayment. Invalid VitalsID to process : " + vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
            
        logger.debug("ShowDonationsAddChange directive : " + form.getSubmitbutton());
        donationsAddChange.setDirective( form.getSubmitbutton() );

        try{
            int dbDonationsID = FormatNumber.parseInteger( form.getDonation() );
            // Evaluate which submit button was used by the operator
            if ((form.getSubmitbutton().equals("change")
            || form.getSubmitbutton().equals("delete"))
            && dbDonationsID < 1){
                // Error since nothing was selected
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                formErrors.add("donation");
                saveErrors(request, errors );
                request.setAttribute("formErrors", formErrors);
                return (new ActionForward(mapping.getInput() ));
            }
            else if (form.getSubmitbutton().equals("exit")){
                session.removeAttribute("donations");
                session.removeAttribute("donationsDisplayList");
                session.removeAttribute("donationForms");
                return mapping.findForward("showCaseStatusGlobal");
            }
            else if (form.getSubmitbutton().equals("help")){
                return mapping.findForward("showCaseStatusGlobal");
            }
            else if (form.getSubmitbutton().equals("printletter")){
                //AppLog.trace("ShowDonations print letter");
                // --- HANDLE PRINTING REPORT ---
                // get form-ID
                int formid = FormatNumber.parseInteger(form.getDonationLetter());
                if (formid < 1){
                    logger.debug("ShowDonation - No report selected.");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.donation.noreport"));
                    formErrors.add("donationLetter");
                    saveErrors(request, errors );
                    request.setAttribute("formErrors", formErrors);
                    return(new ActionForward(mapping.getInput() ));
                }
//                t = (com.aldorsolutions.webfdms.database.DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

                String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");
                String pageName = null;
                
                if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                    CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());                
                    pageName = crystalServerReport.printReport(sessionUser, formid, "", "", null, "", Integer.toString(dbDonationsID), true);
                }
                else
                {
                	ExportReport crystal = new ExportReport();
                    crystal.setRecordIdSelParam( dbDonationsID ); // selection parameter
                    //default is OK crystal.setExportFormat("RTF");
                    pageName = crystal.printForm(sessionUser, formid, "", "", null, "", request, response, servlet.getServletContext());
                }
                  
                form.setPreviewFile(pageName);
                return (new ActionForward(mapping.getInput() ));
            }
            // Begin processing Add or Change (or delete)
            // Make collections for select/option controls
            titles    = SessionHelpers.getSpeedDataOptionCollection(sessionUser,"Honorific");
            // popup- charities = SessionHelpers.getSpeedDataOptionCollection(sessionUser,"Charities");
            pmttypes  = SessionHelpers.getSpeedDataOptionCollection(sessionUser,"DONATETYPE",1,1);
            //moved - letters   = SessionHelpers.getSpeedDataOptionCollection(sessionUser,"DONATELTR",1);

            //session.setAttribute("charities",charities);
            session.setAttribute("titles",titles);
            session.setAttribute("paytypes",pmttypes);
            //Populate the DonationsForm object
            if (  form.getSubmitbutton().equals("add")){
                donationsAddChange.setDonatorId("add");
                donationsAddChange.setDonationAmount("$0");
            }
            if (  form.getSubmitbutton().equals("change")
            || form.getSubmitbutton().equals("delete")){
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbDonations = FdmsDb.getInstance().getDonations( t , dbDonationsID  );
                //dbVitalsDeceased = FdmsDb.getInstance().getVitalsDeceased( t, vitalsid );
                donationsAddChange.setDeceasedFullName( form.getDeceasedFullName() );
                donationsAddChange.setDonatorId( form.getDonation()  );
                donationsAddChange.setDonatorTitle(   dbDonations.getHonorific());
                donationsAddChange.setDonatorFirstName( dbDonations.getFirstName());
                donationsAddChange.setDonatorLastName(  dbDonations.getLastName());
                donationsAddChange.setDonatorAddress(   dbDonations.getAddr1());
                donationsAddChange.setDonatorAddress2(  dbDonations.getAddr2());
                donationsAddChange.setDonatorAddress3(  dbDonations.getAddr3());
                donationsAddChange.setDonatorCity(    dbDonations.getCity());
                donationsAddChange.setDonatorState(   dbDonations.getState());
                donationsAddChange.setDonatorZipCode( dbDonations.getPostalCode());
                donationsAddChange.setDonationAmount( FormatCurrency.toCurrency((long)dbDonations.getDonationAmount()) );
                donationsAddChange.setDonationOrganization(dbDonations.getCharityName());
                donationsAddChange.setDonationPaymentType(dbDonations.getPaymentType() );
                // clean up
            }
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowDonationsAddChange " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getMessage()+" "+pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in ShowDonationsAddChange. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        }
        finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}
        
        if( !errors.isEmpty() ){
            logger.debug("ShowDonationsAddChange Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
        //Set Form Bean Into Scope
        session.setAttribute("donationsAddChange",donationsAddChange);
        return mapping.findForward("donationsAddChange");
    }
}
