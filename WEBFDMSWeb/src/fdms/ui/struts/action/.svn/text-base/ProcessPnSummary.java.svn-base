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

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PnSummary;

public class ProcessPnSummary extends Action {
    
    private static Logger logger = Logger.getLogger(ProcessPnSummary.class.getName());
    private ArrayList formErrors = null;
    
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, 
        HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {

        formErrors = new ArrayList();
        PnSummary form = (PnSummary) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        logger.debug("ProcessPnSummary beginning."+form.getVitalsId()+":"+form.getContractId());
                
        int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
        SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        // Check for CANCEL
        if (form.getSubmitButton().equalsIgnoreCase("cancel")){
            return mapping.findForward("showPnStatus");
        }
        
        // get contract id of the contract being udpated or added
        int contractid = FormatNumber.parseInteger(form.getContractId());
        //if (contractid < 1){
        //	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.selectcontract"));
        //}
        // verify commission / refund are within guidlines
        if (sessionUser.getLocaleCountry().equals(Constants.LOCALE_US)) {
	        double commission = FormatNumber.parseDouble(form.getCommission());
	        double refund = FormatNumber.parseDouble( form.getRefund());
	        if (refund > 100 || refund < 90){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.refunderror"));
	            formErrors.add("refund");
	        }
	        // Maximum commission is 10%
	        if (commission > 10){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionover10"));
	            formErrors.add("commission");
	        }
	        // if commission over 5% then refund must be 100%
	        if (commission > 5 && refund < 100){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionover5"));
	            formErrors.add("commission");
	        }
	        // if commission <= 5% then refund must be 100%
	        if (commission > 0 & commission <= 5 && refund < 95){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionunder5"));
	            formErrors.add("commission");
	        }
	        // if commission is zero then refund at least 90%
	        if (commission ==0 && refund < 90){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionzero"));
	            formErrors.add("commission");
	        }
        }
        
        java.util.Date contractDate = null;
        
        try {
            contractDate = FormatDate.convertToDate(form.getDateSigned());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.datesigned"));
            formErrors.add("dateSigned");
        }
        
        if (!errors.isEmpty()) {
            logger.debug("ProcessPnSummary errors found, returning to input.");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            request.setAttribute("PnSummary",form);
            return (new ActionForward(mapping.getInput()));
        }
        
        // Save to database
        DbPnContract acontract = null;
        DbCase acase = null;
        DbLocation alocation = null;
        DbPreneed dbPreNeed = null;
        DatabaseTransaction t = null;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            acase = FdmsDb.getInstance().getCase(t, vitalsid);
            alocation = FdmsDb.getInstance().getLocation(t, acase.getChapelNumber());
            acontract = FdmsDb.getInstance().getPnContract(t, contractid);
            dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
            
            if (acontract==null){
                // then we are adding a new contract
                acontract = new DbPnContract();
                acontract.setNew();
                t.addPersistent(acontract);
                acontract.setVitalsID(vitalsid);
                
                // set default price list from location table
                acontract.setPriceListVersion(	alocation.getPriceListVersion());
                
                // allow user to assign their own contract numbers
                if (form.getContractNumber().compareToIgnoreCase("new")==0
                    || form.getContractNumber().compareTo(" ")<=0){
                    acontract.setContractNumber( getNextContractNumber(sessionUser));
                } else {
                    acontract.setContractNumber(Integer.parseInt(form.getContractNumber()));
                }
                
                logger.debug("Adding PN contract: "+acontract.getContractNumber());
                
                // set preneed status to active since adding a new contract
                if (dbPreNeed.getStatus().compareTo(DbPreneed.ACTIVE)!=0){
                    dbPreNeed.setStatus(DbPreneed.ACTIVE);
                }
            }
            
            // Update other contract fields regardless if add or update
            acontract.setCustomContractNumber(form.getCustomContractNumber());
            acontract.setCertificateNumber(form.getCertificateNumber());
            acontract.setBankID(FormatNumber.parseInteger(form.getBankName()));
            acontract.setContractDate(contractDate);
            acontract.setAnnualStmt("N");
            acontract.setCertifiedIrrevocable("N");
            if (form.getYearlyStatements()) acontract.setAnnualStmt(	"Y");
            if (form.getIrrevocable()) acontract.setCertifiedIrrevocable("Y");
            acontract.setContractType(FormatNumber.parseInteger(form.getTaxStatus()));
            acontract.setTaxpayer(form.getTaxPayer());
            acontract.setSend1099To(form.getSend1099());
            acontract.setCommissionRate(FormatNumber.parseDouble(form.getCommission())/100.0);
            acontract.setRefundRate(FormatNumber.parseDouble(form.getRefund())/100.0);
            acontract.setDiscountRate(1-(1/(1+acontract.getCommissionRate())));
            acontract.setNotes(form.getNotes());
            
            // if no contract number in CASE object then put in this pre-need contract number.
            if (acase.getContractCode().compareTo(" ")<=0){
                acase.setContractCode(Integer.toString(acontract.getContractNumber()));
            }
            
            t.save();
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessPnSummary do.Perform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
        } catch(Exception pe) {
            logger.error("Exception in  ProcessPnSummary .doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        if (!errors.isEmpty()) {
            logger.debug("saving errors");
            saveErrors(request, errors);
        }
        
        return mapping.findForward("showPnStatus");
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/7/2003 5:14:59 PM)
     * @return int
     * @param param com.aldorsolutions.webfdms.beans.DbUserSession
     */
    public static int getNextContractNumber(DbUserSession user)
    throws PersistenceException, java.sql.SQLException{
        
        int nextcontract = 0;
        DatabaseTransaction t = null;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            DbLocale alocale= FdmsDb.getInstance().getLocaleWithLock(t, 1);
            nextcontract = alocale.getNextPreNeedNo();
            alocale.setNextPreNeedNo(nextcontract+1);
            t.save();
        } catch (Exception e) {
            logger.error("Error in getNextContract() : ", e);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        return nextcontract;
    }
}
