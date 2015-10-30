package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbCustom;
import com.aldorsolutions.webfdms.beans.DbDonations;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbFormsPrinted;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVeteran;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.DbVitalsSpouse;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CaseStatusForm;
import fdms.ui.struts.form.CheckListForm;
import fdms.ui.struts.form.PrintForm;

public class ShowCemAtNeed extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatus.class.getName());
    
    /**
     * Write empty report preview objects into the request object.
     * Creation date: (3/5/2003 6:35:33 PM)
     */
    public static void addEmptyReportPreviews(HttpServletRequest request) {
        
        //AppLog.trace("Setting blank ReportPreviews in request");
        com.aldorsolutions.webfdms.util.ReportPreview preview;
        preview = new com.aldorsolutions.webfdms.util.ReportPreview();
        request.setAttribute("ReportPreview",preview);
        for (int i=2; i<10; i++){
            request.setAttribute("ReportPreview"+i,preview);
        }
    }
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        //AppLog.trace("ShowCaseStatus action doPerfrom");
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = null;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbVitalsDeceased vitalsDeceased = null;
        DbVitalsInformant vitalsInformant =null;
        DbCase theCase = null;
        DbVitalsFirstCall firstCall = null;
        DbVitalsSpouse spouse = null;
        DbServices services = null;
        DbObituary obituary = null;
        DbVeteran veteran = null;
        DbFinancialSummary finan = null;
        DbHistory[] hist = null;
        DbCustom custom = null;
        DbDonations[] donations = null;
        DbVitalsSchedule checklistdata = null;
        DbPreneed preneed = null;
        LocaleDTO alocale = null;
        CaseStatusForm caseStatus = new CaseStatusForm();
        CheckListForm checklistform = new CheckListForm();
        PrintForm printform = new PrintForm();
        int vitalsid = 0;
        
        // Instantiate collections for "options" tags
        List survivorsList = new java.util.ArrayList();
        List chargelist = new ArrayList();
        List paymentlist = new ArrayList();
        List customlist = new ArrayList();
        List donationslist = new ArrayList();
        List formslist = new ArrayList();
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            //AppLog.info("ShowPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        // Get vitals-ID from request or session
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        //AppLog.trace("ShowCaseStatus for ID:"+vitalsid);
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            vitalsDeceased 	= FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
            vitalsInformant = FdmsDb.getInstance().getVitalsInformant(t,vitalsid);
            spouse		= FdmsDb.getInstance().getVitalsSpouse(t,vitalsid);
            checklistdata	= FdmsDb.getInstance().getVitalsSchedule(t,vitalsid);
            theCase 	= FdmsDb.getInstance().getCase(t,vitalsid);
            firstCall	= FdmsDb.getInstance().getVitalsFirstCall(t,vitalsid);
            services	= FdmsDb.getInstance().getServices(t, vitalsid);
            obituary	= FdmsDb.getInstance().getObituary(t, vitalsid);
            veteran		= FdmsDb.getInstance().getVeteran(t, vitalsid);
            finan		= FdmsDb.getInstance().getFinancial(t, vitalsid);
            hist		= FdmsDb.getInstance().getHistoryForCase(t,vitalsid);
            custom 		= FdmsDb.getInstance().getCustom(t, vitalsid);
            donations	= FdmsDb.getInstance().getDonationsForID(t, vitalsid);
            preneed		= FdmsDb.getInstance().getPreneed(t, vitalsid);
            alocale		= FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            
            
            
            // Make sure we have some data
            //if (vitalsDeceased == null || vitalsInformant == null || firstCall == null || checklistdata == null) {
            //    return mapping.findForward("showCaseList");                
                //AppLog.criticalError("ShowCaseStatus.doPerform null vitals data for: " +vitalsid);
            //}
            
//            vitalsDeceased.get
            
            // Populate Form Bean with Deceased Vitals Data ---
            //AppLog.trace("ShowCaseStatus.doPerform Vitals section" +vitalsDeceased +vitalsInformant +firstCall);
            //caseStatus.setAbbitPreneed(	alocale.getPreneedLicense());
            //caseStatus.setVitalsId(String.valueOf(vitalsDeceased.getId()));
            //caseStatus.setDeceasedFullName(vitalsDeceased.getDecFullName());
            //caseStatus.setInformantName(vitalsInformant.getFname() +" " +vitalsInformant.getLname());
            //if (vitalsInformant.getRelated() != null && vitalsInformant.getRelated().trim().length() > 0) {
                //caseStatus.setRelation("(" +vitalsInformant.getRelated() +")");
           // }
            //caseStatus.setContractCode( theCase.getContractCode());
            //caseStatus.setCaseCode(theCase.getCaseCode());
            //caseStatus.setContractLiteral(" ");
            
            // --- Populate Form Bean with Preneed Data ---
            //if (preneed!=null && preneed.getStatus() != null) {
                //AppLog.trace("ShowCaseStatus PreneedStatus="+preneed.getStatus());
                //if (preneed.getStatus().compareToIgnoreCase("S") == 0) {
                    //caseStatus.setPreNeedStatus("Serviced");
               // }
               // if (preneed.getStatus().compareToIgnoreCase("A") == 0) {
                    //caseStatus.setPreNeedStatus("Active");
                    //caseStatus.setContractLiteral("Pre-need");
                    
                //}
                //if (preneed.getStatus().compareToIgnoreCase("C")==0) {
                    //caseStatus.setPreNeedStatus("Cancelled");
                //}
                //caseStatus.setBuyerName(preneed.getBuyerFirst() +" " +preneed.getBuyerLast());
                //caseStatus.setCounselorName(preneed.getCounselor());
            //}
            
            // --- Populate form bean with Call Info data ---
            //caseStatus.setChapel(theCase.getChapelLocation());
            //caseStatus.setDirector(firstCall.getArrangerName());
            //caseStatus.setArrangementDateTime(FormatDate.MDYtoMMDDYY(firstCall.getArrangeDate())+" "+firstCall.getArrangeTime());
            //caseStatus.setPlaceOfDeath( firstCall.getPlaceDeath());
            
            // --- Populate form bean VITALS section
            //caseStatus.setDateOfBirth(FormatDate.MDYtoMMDDYY(vitalsDeceased.getDateOfBirth()));
            //caseStatus.setDateOfDeath(FormatDate.MDYtoMMDDYY(vitalsDeceased.getDateOfDeath()));
            StringBuffer resaddr = new StringBuffer();
            //resaddr.append(vitalsDeceased.getDecResStreet());
            //if (vitalsDeceased.getDecResCityTWP().compareTo(" ") > 0) {
           //     resaddr.append(", ");
            //    resaddr.append(vitalsDeceased.getDecResCityTWP());
           // }
           // if (vitalsDeceased.getDecResState().compareTo(" ") > 0) {
           //     resaddr.append(", ");
            //    resaddr.append(vitalsDeceased.getDecResState());
           // }
            //caseStatus.setResidenceAddress(resaddr.toString());
            //caseStatus.setMaritalStatus(vitalsDeceased.getDecMarried());
            //caseStatus.setSpouse(spouse.getFullName());
            //caseStatus.setDecResStreet(
                    //FormatString.escapeSingleQuotes(vitalsDeceased.getDecResStreet()));
            //caseStatus.setDecResCityStateZip(
                    //FormatString.escapeSingleQuotes(
                       // vitalsDeceased.getDecResCityTWP() +
                      //  ", " + vitalsDeceased.getDecResState() +
                      //  " " + vitalsDeceased.getDecResZip()));
            
            // --- Populate form bean SERVICES section ---
            //AppLog.trace("ShowCaseStatus.doPerform services section"+services);
            if (services != null) {
                //caseStatus.setVisitation(services.getCSrvVisitation());
                //caseStatus.setTimeAndPlaceOfService( services.getCSrvPlace()+", "+services.getCSrvDayOfWeek()+", "+FormatDate.MDYtoMMDDYY(vitalsDeceased.getDateOfBurial())+", "+services.getCSrvTime());
                StringBuffer ceminfo = new StringBuffer(services.getCSrvCem());
                if ( services.getCSrvCemSection().compareTo(" ")>0){
                    ceminfo.append(", "+services.getCSrvCemSection());
                }
                if (services.getCSrvCemLot().compareTo(" ")>0){
                    ceminfo.append(", Lot "+services.getCSrvCemLot());
                }
                //caseStatus.setCemeteryAndPlotInformation(ceminfo.toString());
                //caseStatus.setStaffAutos(services.getCSrvAutos());
                //caseStatus.setMinister(services.getCSrvMinister1());
                //caseStatus.setCemeteryStreet(
                        //FormatString.escapeSingleQuotes(services.getCSrvCemStreet()));
                //caseStatus.setCemeteryCitystate(FormatString.escapeSingleQuotes(
                        //Translator.appendCityState(services.getCSrvCemCity(),services.getCSrvCemState())));
            }
            
            // --- Populate SURVIVORS section ---
            //AppLog.trace("ShowCaseStatus.doPerform Survivors section");
            // Make collection of survivors: collection="survivorsList" property="survivor"
            DbSurvivor[] survivorarray =null;
            survivorarray =com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getSurvivorsForID(t,vitalsid,DbSurvivorPeer.SEQNUMBER);
            if (survivorarray != null){
                //AppLog.trace("ShowCaseStatus.doPerform survivorarray:"+survivorarray.length);
                //Iterate Through result DB object and store as a Session Variable
                for(int i =0; i<survivorarray.length; i++) {
                    DbSurvivor dbs = (DbSurvivor)survivorarray[i];
                    if (dbs.getISeqKey()>DbSurvivor.INFORMANT && dbs.getISeqKey()<100){
                        fdms.ui.struts.DbSurvivorDisplay dssd = null;
                        dssd = new fdms.ui.struts.DbSurvivorDisplay(dbs);
                        survivorsList.add(dssd);
                    }
                }
            }
            
            // --- Populate OBITUARY section ---
            //AppLog.trace("ShowCaseStatus.doPerform obituary section"+obituary);
            if (obituary != null){
                //caseStatus.setObituary(		obituary.getObitNotice());
            }
            
            // --- Populate VETERANS section ---
            //AppLog.trace("ShowCaseStatus.doPerform veterans section"+veteran);
            if (veteran != null){
                
                //caseStatus.setBranchOfService(	veteran.getBranchOfService());
                //caseStatus.setWar(	veteran.getWar());
                //caseStatus.setVeteranServiceFromDate(	FormatDate.MDYtoMMDDYY(veteran.getFlag_EnlistmentDate()));
                //caseStatus.setVeteranServiceToDate(		FormatDate.MDYtoMMDDYY(veteran.getFlag_DischargeDate()));
            }
            
            // --- Populate FINANCIAL section, Charges list ---
            //AppLog.trace("ShowCaseStatus.doPerform financial section"+finan);
            HashMap chargeSet= (HashMap)FdmsDb.getInstance().getChargesForCase(t,vitalsid);
            java.util.Iterator fiIterator = chargeSet.values().iterator();
            com.aldorsolutions.webfdms.beans.DbChargeItem aCharge;
            java.util.TreeMap sortedMap = new java.util.TreeMap();
            while(fiIterator.hasNext()){
                aCharge = ( com.aldorsolutions.webfdms.beans.DbChargeItem )fiIterator.next();
                sortedMap.put(new Integer(aCharge.getSequenceNumber()),aCharge);
            }
            fiIterator = sortedMap.values().iterator(); // iterator for sorted treemap
            while(fiIterator.hasNext()){
                aCharge = ( com.aldorsolutions.webfdms.beans.DbChargeItem )fiIterator.next();
                chargelist.add(new OptionsList(aCharge.getDescription()));
            }
            
            // Populate reminaing financial seciton fields
            //if (finan != null){
                //caseStatus.setSaleType( finan.getCFinSaleType()	);
                //caseStatus.setTotalCharges( FormatCurrency.toCurrency((long)finan.getLFinTotalCharges()));
                //caseStatus.setBalance(	FormatCurrency.toCurrency((long)(finan.getLFinTotalCharges()-finan.getLTotalPaidToDate())));
                //caseStatus.setStatementLastSent( FormatDate.MDYtoMMDDYYYY(finan.getCFinDateInvoiceSent()) );
               // if (//caseStatus.getContractLiteral().compareTo(" ")<=0 && finan.getIARsentBox() == 0) {
                    //caseStatus.setContractLiteral("(Unposted)");
               // }
            //}
           // else if (caseStatus.getContractLiteral().compareTo(" ")<=0) {
            //    //caseStatus.setContractLiteral("(Unposted)");
            //}
            
            // --- Populate PAYMENTS section ---
            //AppLog.trace("ShowCaseStatus.doPerform payments section"+hist);
            if (hist != null) {
                for (int i=0; i<hist.length; i++){
                    if (hist[i].getCHistSPF()=='P'){
                        String pmtamt = FormatCurrency.toCurrency((long)hist[i].getLHistAmount());
                        String pmtdate= FormatDate.convertDateToSHORT( hist[i].getCHistDate());
                        paymentlist.add(new OptionsList( pmtdate+" "+hist[i].getCHistDesc()+" "+pmtamt));
                    }
                }
            }
            
            // --- Populate CUSTOM section ---
            //AppLog.trace("ShowCaseStatus.doPerform custom section"+custom);
            if (custom != null){
                for (int i=0; i<40; i++){
                    if ((custom.getCustom(i)).compareTo(" ")>0){
                        customlist.add( new OptionsList(custom.getCustom(i)));
                    }
                    if ((custom.getCustomLong(i)).compareTo(" ")>0){
                        customlist.add( new OptionsList(custom.getCustomLong(i)));
                    }
                }
            }
            
            // --- Populate DONATIONS section
            //AppLog.trace("ShowCaseStatus.doPerform donations section"+donations);
            if (donations != null){
                for (int i=0; i<donations.length; i++){
                	String outputString = donations[i].getCharityName();
                	if (donations[i].getDonationAmount() > 0)
                		outputString += " " + FormatCurrency.toCurrency((long)donations[i].getDonationAmount());
                    donationslist.add( new OptionsList(outputString));
                }
            }
            
            // --- Populate FORMS section
            // get list of forms available for this case
            com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list = FdmsDb.getInstance().getFormsAvailableForLocale(t,sessionUser.getRegion(),0);
            for (int i=0; i<list.length; i++){
                int formid = list[i].getFormId();
                // get the form status for this case
                DbFormsPrinted[] printed = FdmsDb.getInstance().getFormsPrintedForCase(t,vitalsid,formid);
                String completed = "-"; // Struts escapes so doesn't work: "&nbsp;";
                if (printed.length>0){
                    if (printed[0].isCompleted())
                        completed="*"; //"&#8730;";	// check mark (square root symbol)
                }
                //AppLog.trace(completed+list[i].getDescription());
                formslist.add( new OptionsList(Integer.toString(formid) ,completed+list[i].getDescription()));
            }
            
            // --- Populate CHECK LIST section
            //checklistform.setChecked1(checklistdata.getChecked(0)>0);
            //checklistform.setChecked2(checklistdata.getChecked(1)>0);
            //checklistform.setChecked3(checklistdata.getChecked(2)>0);
            //checklistform.setChecked4(checklistdata.getChecked(3)>0);
            //checklistform.setChecked5(checklistdata.getChecked(4)>0);
            //checklistform.setChecked6(checklistdata.getChecked(5)>0);
            //checklistform.setChecked7(checklistdata.getChecked(6)>0);
            //checklistform.setChecked8(checklistdata.getChecked(7)>0);
            //checklistform.setChecked9(checklistdata.getChecked(8)>0);
            //checklistform.setChecked10(checklistdata.getChecked(9)>0);
            //checklistform.setChecked11(checklistdata.getChecked(10)>0);
            //checklistform.setChecked12(checklistdata.getChecked(11)>0);
            //checklistform.setChecked13(checklistdata.getChecked(12)>0);
            //checklistform.setChecked14(checklistdata.getChecked(13)>0);
            //checklistform.setChecked15(checklistdata.getChecked(14)>0);
            //checklistform.setChecked16(checklistdata.getChecked(15)>0);
            //checklistform.setSchedule1(checklistdata.getDescription(0));
            //checklistform.setSchedule2(checklistdata.getDescription(1));
            //checklistform.setSchedule3(checklistdata.getDescription(2));
            //checklistform.setSchedule4(checklistdata.getDescription(3));
            //checklistform.setSchedule5(checklistdata.getDescription(4));
            //checklistform.setSchedule6(checklistdata.getDescription(5));
            //checklistform.setSchedule7(checklistdata.getDescription(6));
            //checklistform.setSchedule8(checklistdata.getDescription(7));
            //checklistform.setSchedule9(checklistdata.getDescription(8));
            //checklistform.setSchedule10(checklistdata.getDescription(9));
            //checklistform.setSchedule11(checklistdata.getDescription(10));
            //checklistform.setSchedule12(checklistdata.getDescription(11));
            //checklistform.setSchedule13(checklistdata.getDescription(12));
            //checklistform.setSchedule14(checklistdata.getDescription(13));
            //checklistform.setSchedule15(checklistdata.getDescription(14));
            //checklistform.setSchedule16(checklistdata.getDescription(15));
            //checklistform.setDate9(	 FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(0)));
            //checklistform.setDate10( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(1)));
            //checklistform.setDate11( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(2)));
            //checklistform.setDate12( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(3)));
            //checklistform.setDate13( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(4)));
            //checklistform.setDate14( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(5)));
            //checklistform.setDate15( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(6)));
            //checklistform.setDate16( FormatDate.MDYtoMMDDYYYY(checklistdata.getDate(7)));
            //AppLog.trace("Check List #1:"+checklistdata.getId()+"-"+checklistdata.getDescription(0));
            
            // Set select boxes item to be SELECTED (to none of them)
            //caseStatus.setSurvivor(" ");
            //caseStatus.setPayment(" ");
            //caseStatus.setDonation(" ");
            //caseStatus.setCustomSelect(" ");
            //caseStatus.setCharges(" ");
            
            //if (theCase.getVoidedContractCode().equals("V")) {
                //caseStatus.setContractLiteral("(Voided)");
                //caseStatus.setVoidContract(true);
           // } else {
                //caseStatus.setVoidContract(false);
           // }
            
            // Check if print preview already exists in request.
            // If not, make an empty one
            try {
                com.aldorsolutions.webfdms.util.ReportPreview preview = (com.aldorsolutions.webfdms.util.ReportPreview)request.getAttribute("ReportPreview");
                if (preview==null){
                    addEmptyReportPreviews(request);
                } else {
                    //AppLog.trace("Found ReportReview in request");
                }
            } catch (Exception e) {
                //AppLog.trace("Blew Chunks trying to find ReportPreview.");
                addEmptyReportPreviews(request);
            }
            
            // put collections in request
            request.setAttribute("survivorsList",survivorsList);
            request.setAttribute("chargesList",chargelist);
            request.setAttribute("paymentsList",paymentlist);
            request.setAttribute("customList",customlist);
            request.setAttribute("donationsList",donationslist);
            request.setAttribute("formsCompletedList",formslist);
            //request.setAttribute("displayList",session.getAttribute("displayList"));
            
            // Put form beans in request
            request.setAttribute("caseStatus",caseStatus);
            request.setAttribute("checkListForm",checklistform);
            request.setAttribute("printForm",printform);            
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatus.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatus.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check if print preview already exists in request.
        // If not, make an empty one
        try {
            com.aldorsolutions.webfdms.util.ReportPreview preview =
                (com.aldorsolutions.webfdms.util.ReportPreview)request.getAttribute("ReportPreview");
            if (preview==null){
                //AppLog.trace("Setting blank ReportPreview into request");
                preview = new com.aldorsolutions.webfdms.util.ReportPreview();
                request.setAttribute("ReportPreview",preview);
            } else {
                //AppLog.trace("Found ReportReview in request");
            }
        } catch (Exception e) {
            //AppLog.trace("Blew Chunks trying to find ReportPreview.");
            com.aldorsolutions.webfdms.util.ReportPreview preview = new com.aldorsolutions.webfdms.util.ReportPreview();
            request.setAttribute("ReportPreview",preview);
        }
        // put collections in request
        request.setAttribute("survivorsList",survivorsList);
        request.setAttribute("chargesList",chargelist);
        request.setAttribute("paymentsList",paymentlist);
        request.setAttribute("customList",customlist);
        request.setAttribute("donationsList",donationslist);
        request.setAttribute("formsCompletedList",formslist);
        //request.setAttribute("displayList",session.getAttribute("displayList"));
        
        // Put form beans in request
        request.setAttribute("caseStatus",caseStatus);
        request.setAttribute("checkListForm",checklistform);
        request.setAttribute("printForm",printform);
        return mapping.findForward("showCemAtNeedJsp");
        
    }
    
}
