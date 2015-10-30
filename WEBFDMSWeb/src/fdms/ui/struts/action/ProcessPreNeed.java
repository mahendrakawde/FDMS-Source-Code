package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbCasePeer;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.PreNeedForm;

public class ProcessPreNeed extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPreNeed.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(
    		ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
                
        PreNeedForm form = (PreNeedForm) actionForm;
        ActionErrors errors = new ActionErrors();
        formErrors = new ArrayList();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        DatabaseTransaction t =null;
        DbPreneed dbPreNeed = null;
        DbVitalsDeceased vitals = null;
        DbCase acase = null;
        DbVitalsFirstCall firstCall = null;
        DbArrangers dbArranger = null;
        DbLocation dbLocation = null;
        DbVitalsSchedule sched = null;
        FdmsDb fdmsdb = null;
        int vitalsid = 0;
        String saledateymd=null;
        
        TreeMap disbursementMap = (TreeMap) session.getAttribute(Constants.PRENEED_DISBURSEMENT_MAP);
        int disbursementMapSize = (disbursementMap != null) ? disbursementMap.size() : 0;
        
        if (form.getDirective().equals("cancel")) {
            vitalsid = sessionUser.getCurrentCaseID();
            // go back to case status unless no vitals-id then show introduciton
            if (vitalsid > 0) {
                return mapping.findForward("showCaseStatusGlobal");
            } else {
                return mapping.findForward("ShowIntroductionGlobal");
            }
        }
        
        if (form.getDirective().equals("help")) {
            return mapping.findForward("usingHelp");
        }        
        
        // validate operator entries
        
        // Casecode cannot be duplict
        if (form.getCaseCode() != null && form.getCaseCode().trim().length() > 0 ){
//				&& (!form.getCaseCode().equals(form.getNextCaseNumber()))) {

			DbCase checkCase = new DbCase();
			checkCase.setNew();
			checkCase.setLocale(sessionUser.getRegion());
			if (form.getVitalsId() == null || form.getVitalsId().trim().length() == 0 || form.getVitalsId() == "0") {
				checkCase.setId(0);
			} else {
				checkCase.setId(FormatNumber.parseInteger(form.getVitalsId()));
			}
			checkCase.setCaseCode(form.getCaseCode());
			DatabaseTransaction tmp =null;
			try {	
				tmp = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
				if (FdmsDb.getInstance().checkCaseExists(tmp, checkCase, DbCasePeer.CASECODE)) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.duplicate.caseNumber"));
					formErrors.add("caseNumber");
				}
				} catch (PersistenceException pe) {
					logger.error("PersistenceException in doPerform() : ", pe);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
				} catch (Exception pe) {
					logger.error("Error in doPerform() : ", pe);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
				} finally {
					if (tmp != null) {
						try {
							tmp.closeConnection();
							tmp = null;
						} catch (Exception e) {
							logger.error("Error in closeConnection() : ", e);
						}
					}
				}
		}		
        
        // Sale Date is required.
        if (form.getSaleDate() == null || form.getSaleDate().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.saledate"));
            formErrors.add("saleDate");            
        } else {
            try {
                saledateymd = FormatDate.convertToDateYYYYMMDD(form.getSaleDate());
            } catch(Exception e) {                
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.saledate"));
                formErrors.add("saleDate");
            }
        }                        
        // Chapel is required.
        if (form.getMortuaryLocation() == null || form.getMortuaryLocation().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.chapel"));
            formErrors.add("mortuaryLocation");
        }
        // Director is required.
        if (form.getCounselor() == null || form.getCounselor().trim().length() == 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.director"));
            formErrors.add("counselor");            
        }
        
        if (form.getPreneedStatus() != null && form.getPreneedStatus().equalsIgnoreCase("Serviced")) {
			CompanyOptionsManager coMgr = new CompanyOptionsManager ();
	    	// Now check to see if this options is turned on for this customer
			int serviceDateOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_NEW_AT_NEED_SERVICE_DATE);
			if (serviceDateOption == 1) {
		    	if (form.getServiceDate() == null || form.getServiceDate().trim().length() == 0) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.servicedateRequire"));
					formErrors.add("serviceDate");
		    	}
			}
        }
        
        
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessNewPreNeed. validation errors. Invoking forward mapping getInput() ");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            request.setAttribute("PRENEED_DISBURSEMENT_MAP_SIZE", new Integer(disbursementMapSize));
            request.setAttribute("preNeed", form);
            return (new ActionForward(mapping.getInput()));
        }
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            fdmsdb = FdmsDb.getInstance();
            //AppLog.trace("ProcessPreNeed: directive:"+form.getDirective());
            dbArranger = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getArranger(t, Integer.parseInt(form.getCounselor()));
            dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocation(t, Integer.parseInt(form.getMortuaryLocation()));
            if (form.getDirective().equals("add")) {
                dbPreNeed = new DbPreneed();
                dbPreNeed.setNew();
                populateDbPreNeedFromForm(dbArranger, dbPreNeed, form, errors);
                
                // if errors found, return to input screen without saving anything
                if (!errors.isEmpty()) {
                    //AppLog.info("ProcessNewPreNeed Invoking forward mapping getInput()");
                    saveErrors(request, errors );
                    request.setAttribute("formErrors", formErrors);
                    request.setAttribute("PRENEED_DISBURSEMENT_MAP_SIZE", new Integer(disbursementMapSize));
                    request.setAttribute("preNeed", form);
                    return (new ActionForward(mapping.getInput()));
                }
                
                t.addPersistent(dbPreNeed);
                t.save();
                t.closeConnection();
                
                // Update case object for this new case-id
                vitalsid = dbPreNeed.getId();
                //AppLog.trace("ProcessPreNeed: New Id:" +vitalsid);
                
                // Set the Case Id in the DbUserSession Object
                sessionUser.setCurrentCaseID(vitalsid);
                
                // Update vitals object for this new case-id
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                vitals = fdmsdb.getVitalsDeceased(t, vitalsid);
                populateDbVitalsFromForm(vitals, form, errors);
                firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
                firstCall.setArrangerName(dbArranger.getName());
                firstCall.setArrangerID(Integer.parseInt(form.getCounselor()));
                // Note ArrangeDate is defined in both DbPreNeed and DbVitalsFirstCall.
                // So, both fields in both Db clasess must be updated from form.getSaleDate()
                try {
                    firstCall.setArrangeDate(	FormatDate.convertToDateMMDDYYYY(form.getSaleDate()));
                    firstCall.setOriginalPnDate(FormatDate.convertToDateMMDDYYYY(form.getSaleDate()));
                } catch(Exception e){
                    errors.add("saleDate", new ActionError("error.ui.preneed.date.sale"));
                    formErrors.add("saleDate");
                }
                sched = fdmsdb.getVitalsSchedule(t, vitalsid);
                sched.setDefaultPreNeedCheckList(sessionUser.getRegion(),sessionUser.getDbLookup());
                
                // Update case object for new ID
                acase = fdmsdb.getCase(t,vitalsid);
                populateDbCaseFromForm(sessionUser, saledateymd, dbLocation,acase, form, errors);
                
                // Update survivor table
                String relation = "Deceased";
                if (dbPreNeed.getStatus().equals(DbPreneed.ACTIVE)) {
                    relation = "Preneed";
                }
                
                DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.DECEASED, "", vitals.getDecFName(), vitals.getDecMName(), vitals.getDecLName(), "", "", "", vitals.getDecResStreet()+" "+vitals.getDecAptNo(), "", vitals.getDecResMailCity(), vitals.getDecResState(), vitals.getDecResZip(), "", "", "", relation, "", "","");
                String street = ""; 
                String street1 = ""; 
                String street2 = "";
                if (dbPreNeed.getBuyerStreet() == null || dbPreNeed.getBuyerStreet().length() < 1 ) {
                	street1 = "";
                }
                else {
                	street1 = dbPreNeed.getBuyerStreet();
                }
                if (dbPreNeed.getBuyerAptNo() == null || dbPreNeed.getBuyerAptNo().length() < 1 ) {
                	street2 = "";
                }
                else {
                	street2 = dbPreNeed.getBuyerAptNo();
                }
                if (street1.length()>0 || street2.length()>0) {
                	street =  street1 +" "+street2;
                }else {
                	street ="";
                }
//                DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, "", dbPreNeed.getBuyerFirst(), dbPreNeed.getBuyerMiddle(), dbPreNeed.getBuyerLast(), "", "", "", dbPreNeed.getBuyerStreet()+" "+dbPreNeed.getBuyerAptNo(), "", dbPreNeed.getBuyerCity(), dbPreNeed.getBuyerState(), dbPreNeed.getBuyerZip(), "", "", "", "Informant", "", "");
                DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, "", dbPreNeed.getBuyerFirst(), dbPreNeed.getBuyerMiddle(), dbPreNeed.getBuyerLast(), "", "", "", street, "", dbPreNeed.getBuyerCity(), dbPreNeed.getBuyerState(), dbPreNeed.getBuyerZip(), dbPreNeed.getBuyerPhone(), "", "", "Informant", "", "","");
                t.save();
            }
            
            if (form.getDirective().equals("change")) {
                vitalsid = FormatNumber.parseInteger(form.getVitalsId());
                //AppLog.trace("ProcessPreNeed: change id: " +vitalsid);
                dbPreNeed = fdmsdb.getPreneed(t, vitalsid);
                //AppLog.trace("ProcessPreNeed preneed object: " +dbPreNeed.getBuyerFirst() +" form status: " +form.getPreneedStatus());
                String prevStatus = dbPreNeed.getStatus();
                
                //Populate the dbPreNeed object from the form object
                populateDbPreNeedFromForm(dbArranger, dbPreNeed,form, errors);
                // if errors found, return to input screen without saving anything
                if (!errors.isEmpty()) {
                    //AppLog.info("ProcessPreNeed Invoking forward mapping getInput().");
                    saveErrors(request, errors);
                    request.setAttribute("formErrors", formErrors);
                    request.setAttribute("PRENEED_DISBURSEMENT_MAP_SIZE", new Integer(disbursementMapSize));
                    request.setAttribute("preNeed", form);
                    return (new ActionForward(mapping.getInput()));
                }
                
                // Update vitals object for this new case-id
                vitals = fdmsdb.getVitalsDeceased(t, vitalsid);
                //AppLog.trace("ProcessPreNeed vitals:"+vitals.getDecFullName());
                populateDbVitalsFromForm(vitals, form, errors);
                firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
                firstCall.setArrangerName(dbArranger.getName());
                firstCall.setArrangerID(Integer.parseInt(form.getCounselor()));
                firstCall.setSource( form.getSource() );
                
                // Note ArrangeDate is defined in both DbPreNeed and DbVitalsFirstCall.
                // So, both fields in both Db clasess must be updated from form.getSaleDate()
                try {
                    firstCall.setArrangeDate(	FormatDate.convertToDateMMDDYYYY(form.getSaleDate()));
                    firstCall.setOriginalPnDate(FormatDate.convertToDateMMDDYYYY(form.getSaleDate()));
                } catch(Exception e){
                    errors.add("saleDate",new ActionError("error.ui.preneed.date.sale"));
                    formErrors.add("saleDate");
                }
                
                // Update case object for new ID
                acase = fdmsdb.getCase(t,vitalsid);
                populateDbCaseFromForm(sessionUser, saledateymd, dbLocation,acase, form, errors);
                // Check if converting a preneed from ACTIVE to SERVICED
                if (prevStatus.equals(DbPreneed.ACTIVE) && dbPreNeed.getStatus().equals(DbPreneed.SERVICED)){
                    sched = fdmsdb.getVitalsSchedule(t, vitalsid);
                    sched.setDefaultAtNeedCheckList(sessionUser.getRegion(),sessionUser.getDbLookup());
                }
                // clean up
                t.save();
            }
            
        } catch(PersistenceException pe) {
            logger.error("ProcessPreNeed: Persistence Exception. " + pe);
        } catch(Exception pe) {
            logger.error("ProcessPreNeed Exception. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // If ERRORS then return to input page
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessNewPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors );
            request.setAttribute("preNeed", form);
            request.setAttribute("formErrors", formErrors);
            request.setAttribute("PRENEED_DISBURSEMENT_MAP_SIZE", new Integer(disbursementMapSize));
            return mapping.findForward(mapping.getInput());
        }
        
        // return actionForward;
        if (errors.isEmpty()) {
        	String redirect = request.getParameter("redirect");
        	logger.debug("Redirect : " + redirect);
        	if ((redirect != null) && ("Y".equals(redirect))) {
        			request.setAttribute("redirect", Boolean.TRUE);        			
        	} else {        		
            	
            	if ("Y".equals(form.getForwardPreNeedDisbursement())) {
                	int disbursementId = FormatNumber.parseInteger(form.getDisbursementId());
                	
            		request.setAttribute("vitalsId", new Integer(vitalsid));
            		request.setAttribute("disbursementId", new Integer(disbursementId));
            		return mapping.findForward("showPreNeedDisbursement");
            	}
            	
        		SessionHelpers.setVitalsIdInRequest(request, vitalsid);
        		request.setAttribute("From","NewPreNeed"); 
        		return mapping.findForward("showPreNeedGlobal");
        	}
        }
        
        return new ActionForward(mapping.getInput());
        
    }
    
    private void populateDbCaseFromForm(
        DbUserSession sessionUser,
        String saledateymd,
        DbLocation location,
        DbCase acase,
        fdms.ui.struts.form.PreNeedForm form, ActionErrors errors){
            
        acase.setChapelLocation(location.getName());
        acase.setLocale(sessionUser.getRegion());
        acase.setChapelNumber(Integer.parseInt(form.getMortuaryLocation()));
        acase.setSaleDate(saledateymd);	// sale date key
        acase.setCaseCode(form.getCaseCode()); // preneed contract number
        try {
        	acase.setServiceDate(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
        } catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.setData"));
		}
        
    }
    
    private void populateDbPreNeedFromForm(
        DbArrangers dbArranger,
        DbPreneed dbPreNeed,
        fdms.ui.struts.form.PreNeedForm form, 
        ActionErrors errors){
        
    	dbPreNeed.setBuyerTitle(form.getBuyerTitle());
        dbPreNeed.setBuyerFirst(form.getBuyerFirst() );
        dbPreNeed.setBuyerMiddle(form.getBuyerMiddle());
        dbPreNeed.setBuyerLast(form.getBuyerLast() );
        dbPreNeed.setBuyerStreet(form.getBuyerStreet());
        dbPreNeed.setBuyerCity(form.getBuyerCity() );
        if (form.getBuyerState().toUpperCase().length() == 2) {
        	dbPreNeed.setBuyerState(form.getBuyerState().toUpperCase() );
        }
        else {
        	dbPreNeed.setBuyerState(form.getBuyerState());
        }
        dbPreNeed.setBuyerZip(form.getBuyerZipCode().toUpperCase() );
        dbPreNeed.setCobuyer(form.getCoBuyerName());
        dbPreNeed.setBuyerPhone(form.getBuyerPhone());
        dbPreNeed.setBuyerSsNo(FormatString.removeDashes(form.getBuyerSocialSecurityNumber()));
        dbPreNeed.setCounselor(dbArranger.getName());
        dbPreNeed.setSource(form.getSource());
        dbPreNeed.setComments(form.getComments());        
        
        // Note ArrangeDate is defined in both DbPreNeed and DbVitalsFirstCall.
        // So, both fields in both Db clasess must be updated from form.getSaleDate()
        
        try {
            dbPreNeed.setArrangeDate(FormatDate.convertToDateMMDDYYYY(form.getSaleDate()));
        } catch(Exception e){
            errors.add("saleDate",new ActionError("error.ui.preneed.date.sale"));
            formErrors.add("saleDate");
        }
        
        dbPreNeed.setServiceType(form.getService());
        dbPreNeed.setCasketName(form.getCasket());
        dbPreNeed.setVaultName(form.getVault());
        dbPreNeed.setUrnName(form.getUrn());
        
        try {
            dbPreNeed.setServiceAmt(FormatCurrency.convertToCurrency(form.getServiceSale()));
        } catch(Exception e){
            errors.add("serviceSale",new ActionError("error.ui.preneed.serviceamt"));
            formErrors.add("serviceSale");
        }
        
        try {
            dbPreNeed.setCasketAmt(FormatCurrency.convertToCurrency(form.getCasketSale()));
        } catch(Exception e){
            errors.add("casketSale",new ActionError("error.ui.preneed.casketamt"));
            formErrors.add("casketSale");
        }
        
        try {
            dbPreNeed.setVaultAmt(FormatCurrency.convertToCurrency(form.getVaultSale()));
        } catch(Exception e){
            errors.add("vaultSale",new ActionError("error.ui.preneed.vaultamt"));
            formErrors.add("vaultSale");
        }
        
        try {
            dbPreNeed.setUrnAmt(FormatCurrency.convertToCurrency(form.getUrnSale()));
        } catch(Exception e){
            errors.add("urnSale",new ActionError("error.ui.preneed.urnamt"));
            formErrors.add("urnSale");
        }
        
        try {
            dbPreNeed.setGSTAmt(FormatCurrency.convertToCurrency(form.getGstAmt()));
        } catch(Exception e){
            errors.add("gstAmt",new ActionError("error.ui.preneed.gstamt"));
            formErrors.add("gstAmt");
        }        
        
        try {
            dbPreNeed.setOtherAmt(FormatCurrency.convertToCurrency(form.getOtherSale()));
        } catch(Exception e){
            errors.add("otherSale",new ActionError("error.ui.preneed.otheramt"));
            formErrors.add("otherSale");
        }       
        
        dbPreNeed.setFundType(form.getFundDepositoryType());
        dbPreNeed.setDepository(form.getFundsWith());
        dbPreNeed.setFundsStreet(form.getFundsStreet());
        dbPreNeed.setFundsCity(form.getFundsCity());
        dbPreNeed.setFundsState(form.getFundsState());
        dbPreNeed.setFundsZip(form.getFundsZip());
        
        
        try {
            dbPreNeed.setFaceValue(FormatCurrency.convertToCurrency(form.getFaceValue()));
        } catch(Exception e){
            errors.add("faceValue",new ActionError("error.ui.preneed.facevalue"));
            formErrors.add("faceValue");
        }   
        
        try {
            dbPreNeed.setContractAmt(FormatCurrency.convertToCurrency(form.getContractAmount()));
        } catch(Exception e){
            errors.add("contractAmount",new ActionError("error.ui.preneed.contractamt"));
            formErrors.add("contractAmount");
        }            
        
        try {
            dbPreNeed.setContractDate(FormatDate.convertToDateMMDDYYYY(form.getDateStarted()));
        } catch(Exception e){
            errors.add("dateStarted",new ActionError("error.ui.preneed.date.started"));
            formErrors.add("dateStarted");
        }             
        
        try {
            dbPreNeed.setMaturityDate(FormatDate.convertToDateMMDDYYYY(form.getMaturity()));
        } catch(Exception e){
            errors.add("maturity",new ActionError("error.ui.preneed.date.maturity"));
            formErrors.add("maturity");
        }
        
        try {
            dbPreNeed.setPaidYTD(FormatCurrency.convertToCurrency(form.getYtdPaid()));
        } catch(Exception e){
            errors.add("ytdPaid",new ActionError("error.ui.preneed.ytdpaid"));
            formErrors.add("ytdPaid");
        }    
        
        try {
            dbPreNeed.setPaidTotal(FormatCurrency.convertToCurrency(form.getTotalPaid()));
        } catch(Exception e){
            errors.add("totalPaid",new ActionError("error.ui.preneed.totalpaid"));
            formErrors.add("totalPaid");
        }
        
        try {
            dbPreNeed.setInterestYtd(FormatCurrency.convertToCurrency(form.getYtdInterest()));            
        } catch(Exception e){
            errors.add("ytdInterest",new ActionError("error.ui.preneed.ytdinterest"));
            formErrors.add("ytdInterest");
        }        
        
        try {
            dbPreNeed.setInterestTotal(FormatCurrency.convertToCurrency(form.getTotalInterest()));
        } catch(Exception e){
            errors.add("totalInterest",new ActionError("error.ui.preneed.totalInterest"));
            formErrors.add("totalInterest");
        }        
        
        dbPreNeed.setFundAcctNo(form.getAccountNumber());        
        dbPreNeed.setInterestRate(FormatNumber.parseFloat(form.getEstIntRate()));                                        
        
        try {
            dbPreNeed.setMgmtFee(FormatCurrency.convertToCurrency(form.getManagementFee()));
        } catch(Exception e){
            errors.add("managementFee",new ActionError("error.ui.preneed.managementfee"));
            formErrors.add("managementFee");
        }
        
        try {
            dbPreNeed.setCommission(FormatCurrency.convertToCurrency(form.getCommission()));
        } catch(Exception e){
            errors.add("commission",new ActionError("error.ui.preneed.commission"));
            formErrors.add("commission");
        }
        
        try {
            dbPreNeed.setFundsDepositedDate(FormatDate.convertToDateMMDDYYYY(form.getFundsDepositedDate()));
        } catch(Exception e){
            errors.add("fundsDepostiedDate",new ActionError("error.ui.preneed.fundsDepostiedDate"));
            formErrors.add("fundsDepostiedDate");
        }
        
        
        try {
            dbPreNeed.setLastPmtDate(FormatDate.convertToDateMMDDYYYY(form.getLastPaymentDate()));
        } catch(Exception e){
            errors.add("lastPaymentDate",new ActionError("error.ui.preneed.lastpmtdate"));
            formErrors.add("lastPaymentDate");
        }
        
        try {
            dbPreNeed.setLastPmtAmt(FormatCurrency.convertToCurrency(form.getLastPaymentAmount()));
        } catch(Exception e){
            errors.add("lastPaymentAmount",new ActionError("error.ui.preneed.lastpmtamt"));
            formErrors.add("lastPaymentAmount");
        }
                
        if (form.getPreneedStatus() != null){
            dbPreNeed.setStatus(form.getPreneedStatus().substring(0,1));
        } else {
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.ui.preneed.status"));
            formErrors.add("preneedStatus");
        }
        
    }
    
    private void populateDbVitalsFromForm(
        DbVitalsDeceased vitals,
        fdms.ui.struts.form.PreNeedForm form, ActionErrors errors){
            
    	vitals.setDecmrmrs(form.getBeneficiaryTitle());
        vitals.setDecFName(form.getBeneficiaryFirst());
        vitals.setDecMName(form.getBeneficiaryMiddle());
        vitals.setDecLName(form.getBeneficiaryLast());
        vitals.setDecResStreet(form.getBeneficiaryStreet());
        vitals.setDecResMailCity(form.getBeneficiaryCity());
        if (form.getBeneficiaryState().length() == 2) {
        	vitals.setDecResState(form.getBeneficiaryState().toUpperCase());
        }
        else {
        	vitals.setDecResState(form.getBeneficiaryState());	
        }
        vitals.setDecResZip(form.getBeneficiaryZipCode().toUpperCase());
        vitals.setSSNo(FormatString.removeDashes(form.getBeneficiarySocialSecurityNumber()));
        vitals.setDecFullName(form.getBeneficiaryFirst()+" "+form.getBeneficiaryLast());
		try {
	        vitals.setDateOfBurial(FormatDate.convertToDateMMDDYYYY(form.getServiceDate()));
			vitals.setServiceDateKey(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
			vitals.setDateOfBirth(FormatDate.convertToDateMMDDYYYY(form.getBeneficiaryDOB()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.setData"));
		}
		
    }
}
