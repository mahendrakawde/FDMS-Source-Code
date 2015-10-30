package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

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
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbPreneedDisbursement;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;


public class ShowPreNeed extends Action {
    
    private Logger logger = Logger.getLogger(ShowPreNeed.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = null;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        fdms.ui.struts.form.PreNeedForm preNeed = new fdms.ui.struts.form.PreNeedForm();
        DatabaseTransaction t = null;
        DbPreneed dbPreNeed = null;
        DbVitalsDeceased dbVitalsDeceased = null;
        DbVitalsFirstCall firstCall = null;
        LocaleDTO region = null;
        DbCase dbCase= null;
        DbFinancialSummary finan = null;
        
        int vitalsid = 0;
        boolean fromNewPreneed=false;
        
        
        //Check for a DbUserSession
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            logger.debug("ShowPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        // Check if we got here from the new abbit pre-need page and the user
        // requested viewing the classic pre-need
        String fromParam = (String)request.getAttribute("From");
        if (fromParam != null && fromParam.equalsIgnoreCase("NewPreNeed")){
            vitalsid = SessionHelpers.getVitalsIdFromRequest(request);
            fromNewPreneed=true;
        } else {
            // if not from new pre-need then vitals ID must be passed as parameter
            // See if the vitalsId has been sent as a parameter
            String vitalsParam = request.getParameter("vitalsId");
            //AppLog.trace("Show the vitalsId param = " +vitalsParam);
            if (vitalsParam != null && vitalsParam.trim().length() > 0) {
                vitalsid = FormatNumber.parseInteger(vitalsParam);
                sessionUser.setCurrentCaseID(vitalsid);
            } else {
                vitalsid = 0;
            }
            // Store vitals-ID as request attribute
            SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        }
        // Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Check if we should be using the Abbit Pre-need or this generic pre-need
            // by fetching the locale for our current user
            region  = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            if (!fromNewPreneed && region!=null && region.getPreneedLicense().compareToIgnoreCase("A")==0){
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
           
            CompanyOptionsManager coMgr = new CompanyOptionsManager ();
	    	// Now check to see if this options is turned on for this customer
            int serviceDateOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_NEW_AT_NEED_SERVICE_DATE);
            if (serviceDateOption == 1) {
            	session.setAttribute("accoutDescDisplay", "display");
            }else {
            	session.removeAttribute("accountDescList");
            }
            
            // Create the Locations list
            SessionHelpers.setChapelListInSession(request);
            //chapels = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
            //for (int i=0; i<chapels.length; i++){
            //   locations.add( new OptionsList(Integer.toString(chapels[i].getId()) ,chapels[i].getName()));
            //}
            //session.setAttribute("mortuaryLocationList", locations);
            
            if (vitalsid == 0) {
                preNeed.setDirective("add");
                preNeed.setVitalsId("0");
                session.setAttribute(Constants.PRENEED_DISBURSEMENT_MAP, new TreeMap());
                request.setAttribute("PRENEED_DISBURSEMENT_MAP_SIZE", new Integer(0));
            } else {
            	TreeMap pnDisbursementsMap = FdmsDb.getInstance().getPreneedDisbursements(t, vitalsid);
            	Iterator it = pnDisbursementsMap.values().iterator();
            	double pnDisbursementTotal = 0.0d;
            	int i = 0;
            	while (it.hasNext()) {
            		i++;
            		DbPreneedDisbursement pnDisbursement = (DbPreneedDisbursement) it.next(); 
            		pnDisbursementTotal += (pnDisbursement.getValue() * 100);
            	}            	
            	request.setAttribute("PRENEED_DISBURSEMENT_MAP_SIZE", new Integer(i));
            	
            	session.setAttribute(Constants.PRENEED_DISBURSEMENT_MAP, pnDisbursementsMap);
            	
                dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
                //AppLog.trace("ShowPreNeed updating: " +dbPreNeed.getBuyerFirst() +" Middle: " +dbPreNeed.getBuyerMiddle() +" Last: " +dbPreNeed.getBuyerLast() +" status: " +dbPreNeed.getStatus());
                dbVitalsDeceased = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
                dbCase = FdmsDb.getInstance().getCase(t, vitalsid);
                firstCall = FdmsDb.getInstance().getVitalsFirstCall(t, vitalsid);
                
                //Populate the Form object
                preNeed.setDirective("change");
                preNeed.setVitalsId(String.valueOf(vitalsid));
                preNeed.setPreNeedId(String.valueOf(dbPreNeed.getId()));
                preNeed.setBuyerTitle(dbPreNeed.getBuyerTitle());
                preNeed.setBuyerFirst(dbPreNeed.getBuyerFirst());
                preNeed.setBuyerMiddle(dbPreNeed.getBuyerMiddle());
                preNeed.setBuyerLast(dbPreNeed.getBuyerLast());
                preNeed.setBuyerStreet(dbPreNeed.getBuyerStreet());
                preNeed.setBuyerCity(dbPreNeed.getBuyerCity());
                preNeed.setBuyerState(dbPreNeed.getBuyerState());
                preNeed.setBuyerZipCode(dbPreNeed.getBuyerZip());
                preNeed.setBuyerPhone(dbPreNeed.getBuyerPhone());
                preNeed.setCoBuyerName(dbPreNeed.getCobuyer());
                preNeed.setBuyerSocialSecurityNumber(FormatString.formatSocialSecurityNo(sessionUser.getLocaleCountry(), dbPreNeed.getBuyerSsNo()));
                preNeed.setCounselor(String.valueOf( firstCall.getArrangerID()));
//              populate exclusively if arrangerId is inactive.
    			DbArrangers arranger = FdmsDb.getInstance().getArrangerInactive(t,sessionUser.getRegion(),firstCall.getArrangerID());
    			if(arranger != null ){
    				List list = (List)request.getSession().getAttribute("counselorList");
    				 if(list == null){
    					 list = new ArrayList();
    				 }
    				 list.add(new OptionsList(Integer.toString(arranger.getId()),arranger.getName()));
    				 request.getSession().setAttribute("counselorList",list); 
    			}
                
                preNeed.setSource(dbPreNeed.getSource());
                preNeed.setBeneficiaryTitle(dbVitalsDeceased.getDecmrmrs());
                preNeed.setBeneficiaryFirst(dbVitalsDeceased.getDecFName());
                preNeed.setBeneficiaryMiddle(dbVitalsDeceased.getDecMName());
                preNeed.setBeneficiaryLast(dbVitalsDeceased.getDecLName());
                preNeed.setBeneficiaryStreet(dbVitalsDeceased.getDecResStreet());
                preNeed.setBeneficiaryCity(dbVitalsDeceased.getDecResMailCity());
                preNeed.setBeneficiaryState(dbVitalsDeceased.getDecResState());
                preNeed.setBeneficiaryZipCode(dbVitalsDeceased.getDecResZip());
                //preNeed.setBeneficiaryPhone(dbVitalsDeceased.getDecResP)
                preNeed.setBeneficiarySocialSecurityNumber(FormatString.formatSocialSecurityNo(sessionUser.getLocaleCountry(), dbVitalsDeceased.getSSNo()));
                
                //preNeed.setServiceDate(FormatDate.MDYtoMMDDYYYY(dbVitalsDeceased.getBurialDate()));
                preNeed.setServiceDate(FormatDate.YMDtoMMDDYYYY(dbVitalsDeceased.getServiceDateKey()));
                
                preNeed.setBeneficiaryDOB(FormatDate.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfBirth()));
                
                preNeed.setSaleDate(	FormatDate.MDYtoMMDDYYYY(dbPreNeed.getArrangeDate()));
                preNeed.setMortuaryLocation(String.valueOf(dbCase.getChapelNumber()));
                preNeed.setCaseCode(dbCase.getCaseCode());
                preNeed.setService(dbPreNeed.getServiceType());
                preNeed.setCasket(dbPreNeed.getCasketName());
                preNeed.setVault(dbPreNeed.getVaultName());
                preNeed.setUrn(dbPreNeed.getUrnName());
                preNeed.setServiceSale(FormatCurrency.toCurrency((long)dbPreNeed.getServiceAmt()));
                preNeed.setCasketSale(FormatCurrency.toCurrency((long)dbPreNeed.getCasketAmt()));
                preNeed.setVaultSale(FormatCurrency.toCurrency((long)dbPreNeed.getVaultAmt()));
                preNeed.setUrnSale(FormatCurrency.toCurrency((long)dbPreNeed.getUrnAmt()));
                preNeed.setGstAmt(FormatCurrency.toCurrency((long)dbPreNeed.getGSTAmt()));
                preNeed.setOtherSale(FormatCurrency.toCurrency((long)dbPreNeed.getOtherAmt()));
                preNeed.setTotalSale(FormatCurrency.toCurrency((long)(dbPreNeed.getServiceAmt()+dbPreNeed.getCasketAmt()+dbPreNeed.getVaultAmt()+dbPreNeed.getUrnAmt()+dbPreNeed.getGSTAmt()+dbPreNeed.getOtherAmt()+pnDisbursementTotal)));
                preNeed.setFundDepositoryType(dbPreNeed.getFundType());
                preNeed.setFundsStreet(dbPreNeed.getFundsStreet());
                preNeed.setFundsCity(dbPreNeed.getFundsCity());
                preNeed.setFundsState(dbPreNeed.getFundsState());
                preNeed.setFundsZip(dbPreNeed.getFundsZip());
                preNeed.setFundsDepositedDate(FormatDate.MDYtoMMDDYYYY(dbPreNeed.getFundsDepositedDate()));
                
                preNeed.setFundsWith(dbPreNeed.getDepository());
                preNeed.setDateStarted(FormatDate.MDYtoMMDDYYYY(dbPreNeed.getContractDate()));
                preNeed.setMaturity(FormatDate.MDYtoMMDDYYYY(dbPreNeed.getMaturityDate()));
                preNeed.setAccountNumber(dbPreNeed.getFundAcctNo());
                preNeed.setEstIntRate(Float.toString(dbPreNeed.getInterestRate()));
                preNeed.setFaceValue(FormatCurrency.toCurrency((long)dbPreNeed.getFaceValue()));
                preNeed.setContractAmount(FormatCurrency.toCurrency((long)dbPreNeed.getContractAmt()));
                preNeed.setYtdPaid(FormatCurrency.toCurrency((long)dbPreNeed.getPaidYTD()));
                preNeed.setTotalPaid(FormatCurrency.toCurrency((long)dbPreNeed.getPaidTotal()));
                preNeed.setYtdInterest(FormatCurrency.toCurrency((long)dbPreNeed.getInterestYtd()));
                preNeed.setTotalInterest(FormatCurrency.toCurrency((long)dbPreNeed.getInterestTotal()));
                preNeed.setManagementFee(FormatCurrency.toCurrency((long)dbPreNeed.getMgmtFee()));
                preNeed.setCommission(FormatCurrency.toCurrency((long)dbPreNeed.getCommission()));
                preNeed.setLastPaymentDate(FormatDate.MDYtoMMDDYYYY(	dbPreNeed.getLastPmtDate()));
                preNeed.setLastPaymentAmount(FormatCurrency.toCurrency((long)dbPreNeed.getLastPmtAmt()));
                preNeed.setComments(dbPreNeed.getComments());
                // set status check boxes
                if (dbPreNeed.getStatus().compareToIgnoreCase("A") == 0)
                    preNeed.setPreneedStatus("Active");
                if (dbPreNeed.getStatus().compareToIgnoreCase("S") == 0)
                    preNeed.setPreneedStatus("Serviced");
                if (dbPreNeed.getStatus().compareToIgnoreCase("C") == 0)
                    preNeed.setPreneedStatus("Cancelled");
                FdmsDb fdmsdb = FdmsDb.getInstance();
                finan         = fdmsdb.getFinancial(t,vitalsid);
                if(finan != null){
                preNeed.setPosted(finan.getIARsentBox());
                }
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowPreNeed.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowPreNeed.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Set the form and collections into session scope
        request.setAttribute("preNeed", preNeed);
        
        actionForward = mapping.findForward("preNeed");
        
        if (!errors.isEmpty()) {
            logger.debug("ShowPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
        return actionForward;
        
    }
    
}
