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

import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialBillToPartiesAddChangeForm;
import fdms.ui.struts.form.FinancialBillToPartiesForm;


public class FinancialBillToPartiesAddChange extends Action {

    private Logger logger = Logger.getLogger(FinancialBillToPartiesAddChange.class.getName());
    private ArrayList formErrors;
    
    /**
     * FinancialBillToPartiesAddChange
     * This action receives the form from FinancialBillToParties.JSP which
     * contains primarily a select box with a list of existing bill-to parties
     * for this case. The operator selects one and clicks change or delete.
     * ADD can be clicked without selecting a line.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        FinancialBillToPartiesForm form = (FinancialBillToPartiesForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbBillto dbBillTo = null;
        int vitalsid= 0;
        FinancialBillToPartiesAddChangeForm  billToChangeForm = new FinancialBillToPartiesAddChangeForm();

        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid < 1){
            logger.debug("ShowFinancial. Invalid VitalsID to process : " + vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }

        String directive = form.getSubmitbutton();
        if (directive.equals("exit"))
          return mapping.findForward("showFinancialInformation");
        String selectedID = form.getBillToParty();
        if ( (directive.equals("change") || directive.equals("delete"))&& selectedID==null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.billto.noselect"));
            formErrors.add("billToParty");
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            logger.debug("BillToAddChange Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput() ));
        }
        
        //Database Access Logic
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbBillTo = FdmsDb.getInstance().getBillto( t , FormatNumber.parseInteger( form.getBillToParty() ) );
            // Populate form displaying retieved information to modify or delete
            billToChangeForm.setDirective( directive );
            billToChangeForm.setBillToPartyId( form.getBillToParty() );
            if( directive.equals("change") || directive.equals("delete") ) {
                billToChangeForm.setBillToPartyTitle(   dbBillTo.getHonorific());
                billToChangeForm.setBillToPartyRelation(  dbBillTo.getRelation());
                billToChangeForm.setBillToPartyFirstName( dbBillTo.getFirstName());
                billToChangeForm.setBillToPartyLastName(  dbBillTo.getLastName());
                billToChangeForm.setBillToPartyAddress1(  dbBillTo.getStreet1());
                billToChangeForm.setBillToPartyAddress2(  dbBillTo.getStreet2());
                billToChangeForm.setBillToPartyAddress3(  dbBillTo.getStreet3());
                billToChangeForm.setBillToPartyAddress4(  dbBillTo.getStreet4());
                billToChangeForm.setBillToPartyCity(    dbBillTo.getCity());
                billToChangeForm.setBillToPartyState(   dbBillTo.getState());
                billToChangeForm.setBillToPartyZipCode(   dbBillTo.getZip());
                billToChangeForm.setBillToPartyEMailAddress(dbBillTo.getEmailAddress());
                billToChangeForm.setBillToPartyLanguage(  dbBillTo.getLanguage());
                billToChangeForm.setBillToPartyHomePhone(FormatString.formatPhone(dbBillTo.getHomePhone()));
                billToChangeForm.setBillToPartyCellPhone(FormatString.formatPhone(dbBillTo.getCellPhone()));
                billToChangeForm.setBillToPartyWorkPhone(FormatString.formatPhone(dbBillTo.getWorkPhone()));
                billToChangeForm.setBillToPartySocialSecurityNumber( SessionHelpers.formatSocSecNo(dbBillTo.getSocialSecurityNo()));
                billToChangeForm.setBillToPartyCashSale(  dbBillTo.getCashSale().equalsIgnoreCase("Y") );
                billToChangeForm.setBillToPartyRefused(   dbBillTo.getRefused().equalsIgnoreCase("Y") );
                billToChangeForm.setBillToPartyContractSigner( dbBillTo.getContractSigner().equalsIgnoreCase("Y") );
                billToChangeForm.setBillToPartyReceiveInvoice( dbBillTo.getSendInvoice().equalsIgnoreCase("Y") );
                
                DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
                
    			int showCashSaleInt  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_BILL_TO_PARTY_SCREEN_SHOW_CASH_SALE);
    			
    			billToChangeForm.setShowCashSale( (showCashSaleInt > 0) );
    			
    			int showRefusedInt  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_BILL_TO_PARTY_SCREEN_SHOW_REFUSED);
    			
    			billToChangeForm.setShowRefused( (showRefusedInt > 0) );
    			
                
            }
        } catch(PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
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

        //Set Form Bean Into Scope
        session.setAttribute("financialBillToPartiesAddChange",billToChangeForm);
        ActionForward actionForward =  mapping.findForward("financialBillToPartiesAddChange");
        if( !errors.isEmpty()) {
            logger.debug("FinancialBillToPartiesAddChange Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        logger.debug("BillTo Relation : " + billToChangeForm.getBillToPartyRelation());
        return  actionForward;
    }
}
