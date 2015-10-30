package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.delegate.LocationOptionsManager;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbCustom;
import com.aldorsolutions.webfdms.beans.DbDonations;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVeteran;
import com.aldorsolutions.webfdms.beans.DbVisitations;
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
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.ReportPreview;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.Translator;
import com.aldorsolutions.webfdms.visitation.bean.VisitationManagerBean;

import fdms.ui.struts.form.CaseStatusOverviewForm;

public class ShowCaseStatusOverview extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatusOverview.class.getName());
    
    /**
     * Write empty report preview objects into the request object.
     * Creation date: (3/5/2003 6:35:33 PM)
     */
    /*
    public static void addEmptyReportPreviews(HttpServletRequest request) {
        
        //AppLog.trace("Setting blank ReportPreviews in request");
        ReportPreview preview;
        preview = new ReportPreview();
        request.setAttribute("ReportPreview",preview);
        for (int i=2; i<10; i++){
            request.setAttribute("ReportPreview"+i,preview);
        }
    }
    */
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        //AppLog.trace("ShowCaseStatus action doPerfrom");
        ActionErrors errors = new ActionErrors();
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
        CaseStatusOverviewForm caseStatus = new CaseStatusOverviewForm();
        int vitalsid = 0;
        
        // Instantiate collections for "options" tags
        List survivorsList = new java.util.ArrayList();
        List predeceaseList = new java.util.ArrayList();
        List chargelist = new ArrayList();
        List paymentlist = new ArrayList();
        List customlist = new ArrayList();
        List donationslist = new ArrayList();
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        if (!errors.isEmpty()) {
            //AppLog.info("ShowPreNeed Invoking forward mapping getInput().");
            saveErrors(request, errors);
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
            alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            
            // Make sure we have some data
            if (vitalsDeceased == null || vitalsInformant == null || firstCall == null || checklistdata == null) {
                return mapping.findForward("showCaseList");                
                //AppLog.criticalError("ShowCaseStatus.doPerform null vitals data for: " +vitalsid);
            }
            
            // Populate Form Bean with Deceased Vitals Data ---
            //AppLog.trace("ShowCaseStatus.doPerform Vitals section" +vitalsDeceased +vitalsInformant +firstCall);
            caseStatus.setAbbitPreneed(	alocale.getPreneedLicense());
            caseStatus.setVitalsId(String.valueOf(vitalsDeceased.getId()));
            caseStatus.setDeceasedFullName(vitalsDeceased.getDecFullName());
            caseStatus.setInformantName(vitalsInformant.getFname() +" " +vitalsInformant.getLname());
            if (vitalsInformant.getRelated() != null && vitalsInformant.getRelated().trim().length() > 0) {
                caseStatus.setRelation("(" +vitalsInformant.getRelated() +")");
            }
            caseStatus.setContractCode( theCase.getContractCode());
            caseStatus.setCaseCode(theCase.getCaseCode());
            caseStatus.setContractLiteral(" ");
            
            // --- Populate Form Bean with Preneed Data ---
            if (preneed!=null && preneed.getStatus() != null) {
                //AppLog.trace("ShowCaseStatus PreneedStatus="+preneed.getStatus());
                if (preneed.getStatus().compareToIgnoreCase("S") == 0) {
                    caseStatus.setPreNeedStatus("Serviced");
                }
                if (preneed.getStatus().compareToIgnoreCase("A") == 0) {
                    caseStatus.setPreNeedStatus("Active");
                    caseStatus.setContractLiteral("Pre-need");
                    
                }
                if (preneed.getStatus().compareToIgnoreCase("C")==0) {
                    caseStatus.setPreNeedStatus("Cancelled");
                }
                caseStatus.setBuyerName(preneed.getBuyerFirst() +" " +preneed.getBuyerLast());
                caseStatus.setCounselorName(preneed.getCounselor());
            }
            
            // --- Populate form bean with Call Info data ---
            caseStatus.setChapel(theCase.getChapelLocation());
            caseStatus.setDirector(firstCall.getArrangerName());
            caseStatus.setArrangementDateTime(FormatDate.MDYtoMMDDYYYY(firstCall.getArrangeDate())+" "+firstCall.getArrangeTime());
            caseStatus.setPlaceOfDeath( firstCall.getPlaceDeath());
            
            // --- Populate form bean VITALS section
            caseStatus.setDateOfBirth(FormatDate.MDYtoMMDDYYYY(vitalsDeceased.getDateOfBirth()));
            caseStatus.setDateOfDeath(FormatDate.MDYtoMMDDYYYY(vitalsDeceased.getDateOfDeath()));
            StringBuffer resaddr = new StringBuffer();
            resaddr.append(vitalsDeceased.getDecResStreet());
            if (vitalsDeceased.getDecResCityTWP().compareTo(" ") > 0) {
                resaddr.append(", ");
                resaddr.append(vitalsDeceased.getDecResCityTWP());
            }
            if (vitalsDeceased.getDecResState().compareTo(" ") > 0) {
                resaddr.append(", ");
                resaddr.append(vitalsDeceased.getDecResState());
            }
            caseStatus.setResidenceAddress(resaddr.toString());
            caseStatus.setMaritalStatus(vitalsDeceased.getDecMarried());
            caseStatus.setSpouse(spouse.getFullName());
            caseStatus.setDecResStreet(
                    FormatString.escapeSingleQuotes(vitalsDeceased.getDecResStreet()));
            caseStatus.setDecResCityStateZip(
                    FormatString.escapeSingleQuotes(
                        vitalsDeceased.getDecResCityTWP() +
                        ", " + vitalsDeceased.getDecResState() +
                        " " + vitalsDeceased.getDecResZip()));
            
            // --- Populate form bean SERVICES section ---
            //AppLog.trace("ShowCaseStatus.doPerform services section"+services);
            if (services != null) {
            	
            	//add visitations info 
                VisitationManagerBean visitationManager = new VisitationManagerBean();
            	ArrayList <DbVisitations> visitations = new ArrayList <DbVisitations> ();
            	visitations = visitationManager.getVisitationsForID(
						vitalsid, sessionUser);
            	String visitationinfo = "";
            	for ( DbVisitations visitation: visitations) {
            		visitationinfo = visitationinfo + visitation.getPlace() + " " + visitation.getDate() + " "+visitation.getStartTime()+",\r\n ";
            	}
            	
            	if (visitationinfo.trim().length()> 0){
            		caseStatus.setVisitation(services.getCSrvVisitation()+ " \r\n :Place: " + visitationinfo);
            	} else {
            		caseStatus.setVisitation(services.getCSrvVisitation());
            	}	
                //caseStatus.setVisitation(services.getCSrvVisitation()+ " \r\n, Place: " + visitationinfo);
                caseStatus.setTimeAndPlaceOfService( services.getCSrvPlace()+", "+services.getCSrvDayOfWeek()+", "+FormatDate.MDYtoMMDDYYYY(vitalsDeceased.getDateOfBurial())+", "+services.getCSrvTime());
                StringBuffer ceminfo = new StringBuffer(services.getCSrvCem());
                if ( services.getCSrvCemSection().compareTo(" ")>0){
                    ceminfo.append(", "+services.getCSrvCemSection());
                }
                if (services.getCSrvCemLot().compareTo(" ")>0){
                    ceminfo.append(", Lot "+services.getCSrvCemLot());
                }
                if (services.getCSrvCemGrave().compareTo(" ")>0){
                    ceminfo.append(", Grave "+services.getCSrvCemGrave());
                }
                caseStatus.setCemeteryAndPlotInformation(ceminfo.toString());
                caseStatus.setStaffAutos(services.getCSrvAutos());
                caseStatus.setMinister(services.getCSrvMinister1());
                caseStatus.setCemeteryStreet(
                        FormatString.escapeSingleQuotes(services.getCSrvCemStreet()));
                caseStatus.setCemeteryCitystate(FormatString.escapeSingleQuotes(
                        Translator.appendCityState(services.getCSrvCemCity(),services.getCSrvCemState())));
            }
                        
            LocationOptionsManager locationOptionManager = new LocationOptionsManager();
    		int locationOptionValue = locationOptionManager.getLocationOptionValue(sessionUser.getCompanyID(),sessionUser.getLocationId(),LocationOptionsDTO.LOCATION_OPTION_SHOW_FATHER_MOTHER_INFO_FROM_ON_RELATIVE_PAGE);
    		
            // --- Populate SURVIVORS section ---
            //AppLog.trace("ShowCaseStatus.doPerform Survivors section");
            // Make collection of survivors: collection="survivorsList" property="survivor"
            DbSurvivor[] survivorarray =null;
            survivorarray =FdmsDb.getInstance().getSurvivorsForID(t,vitalsid,DbSurvivorPeer.SEQNUMBER);
            if (survivorarray != null){
                //Iterate Through result DB object and store as a Session Variable
                for(int i =0; i<survivorarray.length; i++) {
                    DbSurvivor dbs = (DbSurvivor)survivorarray[i];
                    if (dbs.getISeqKey()>DbSurvivor.INFORMANT && dbs.getISeqKey()<100){
                        fdms.ui.struts.DbSurvivorDisplay dssd = null;
                        dssd = new fdms.ui.struts.DbSurvivorDisplay(dbs);
                        if (dbs.getCSurvivorLiving() == null || dbs.getCSurvivorLiving().compareToIgnoreCase("Y") == 0 || dbs.getCSurvivorLiving().trim().length() == 0  ){
                        	if(dbs.getCGroupType().equalsIgnoreCase("VITAL")){
                        		if(locationOptionValue==1)
                        			survivorsList.add(dssd);
                        	}else{
                        		survivorsList.add(dssd);
                        	}
                        	
                        }else {
                        	if(dbs.getCGroupType().equalsIgnoreCase("VITAL")){
                        		if(locationOptionValue==1)
                        			predeceaseList.add(dssd);
                        	}else
                        		predeceaseList.add(dssd);
                        }
                    }
                }
            }
            
            // --- Populate OBITUARY section ---
            if (obituary != null){
                caseStatus.setObituary(	obituary.getObitNotice() );
            }
            
            // --- Populate VETERANS section ---
            if (veteran != null){
                
                caseStatus.setBranchOfService(	veteran.getBranchOfService());
                caseStatus.setWar(	veteran.getWar());
                caseStatus.setVeteranServiceFromDate(	FormatDate.MDYtoMMDDYYYY(veteran.getFlag_EnlistmentDate()));
                caseStatus.setVeteranServiceToDate(		FormatDate.MDYtoMMDDYYYY(veteran.getFlag_DischargeDate()));
            }
            
            // --- Populate FINANCIAL section, Charges list ---
            HashMap chargeSet= (HashMap)FdmsDb.getInstance().getChargesForCase(t,vitalsid);
            java.util.Iterator fiIterator = chargeSet.values().iterator();
            DbChargeItem aCharge;
            java.util.TreeMap sortedMap = new java.util.TreeMap();
            while(fiIterator.hasNext()){
                aCharge = ( DbChargeItem )fiIterator.next();
                sortedMap.put(new Integer(aCharge.getSequenceNumber()),aCharge);
            }
            fiIterator = sortedMap.values().iterator(); // iterator for sorted treemap
            while(fiIterator.hasNext()){
                aCharge = ( DbChargeItem )fiIterator.next();
                chargelist.add(new OptionsList(aCharge.getDescription()));
            }
            
            // Populate reminaing financial seciton fields
            if (finan != null){
                caseStatus.setSaleType( finan.getCFinSaleType()	);
                caseStatus.setTotalCharges( FormatCurrency.toCurrency((long)finan.getLFinTotalCharges()));
                caseStatus.setBalance(	FormatCurrency.toCurrency((long)(finan.getLFinTotalCharges()-finan.getLTotalPaidToDate())));
                caseStatus.setStatementLastSent( FormatDate.MDYtoMMDDYYYY(finan.getCFinDateInvoiceSent()) );
                if (caseStatus.getContractLiteral().compareTo(" ")<=0 && finan.getIARsentBox() == 0) {
                    caseStatus.setContractLiteral("(Unposted)");
                }
            }
            else if (caseStatus.getContractLiteral().compareTo(" ")<=0) {
                caseStatus.setContractLiteral("(Unposted)");
            }
            
            // --- Populate PAYMENTS section ---
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
            if (donations != null){
                for (int i=0; i<donations.length; i++){
                	String outputString = donations[i].getCharityName();
                	if (donations[i].getDonationAmount() > 0)
                		outputString += " " + FormatCurrency.toCurrency((long)donations[i].getDonationAmount());
                    donationslist.add( new OptionsList(outputString));
                }
            }
            
            // Set select boxes item to be SELECTED (to none of them)
            caseStatus.setSurvivor(" ");
            caseStatus.setPredecease(" ");
            caseStatus.setPayment(" ");
            caseStatus.setDonation(" ");
            caseStatus.setCustomSelect(" ");
            caseStatus.setCharges(" ");
            
            if (theCase.getVoidedContractCode().equals("V")) {
                caseStatus.setContractLiteral("(Voided)");
                caseStatus.setVoidContract(true);
            } else {
                caseStatus.setVoidContract(false);
            }
            
            if (theCase.getActiveCode() == 1) {
                caseStatus.setArchive(false); 
            } else {
                caseStatus.setArchive(true);
            }
            
            // Check if print preview already exists in request.
            // If not, make an empty one
            
            // put collections in request
            request.setAttribute("survivorsList",survivorsList);
            request.setAttribute("predeceaseList",predeceaseList);
            request.setAttribute("chargesList",chargelist);
            request.setAttribute("paymentsList",paymentlist);
            request.setAttribute("customList",customlist);
            request.setAttribute("donationsList",donationslist);
            
            // Put form beans in request
            request.setAttribute("caseStatusOverview",caseStatus);
            
            DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
            
            // get Locale specific details.
            int showFormsCompleted  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_FORMS_COMPLETED);
            int showAtNeedCheckList  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_AT_NEED_CHECKLIST);
            int showAfterCareCheckList  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_AFTER_CARE_CHECKLIST);
            int showBookMarks  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    				DbLocaleConfigType.CONFIG_SHOW_BOOKMARKS);

            request.setAttribute("showFormsCompleted", Integer.valueOf(showFormsCompleted));
            request.setAttribute("showAtNeedCheckList", Integer.valueOf(showAtNeedCheckList));
            request.setAttribute("showAfterCareCheckList", Integer.valueOf(showAfterCareCheckList));
            request.setAttribute("showBookMarks", Integer.valueOf(showBookMarks));
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatus.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatus.doPerform. ", pe);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        // Check if print preview already exists in request.
        // If not, make an empty one
        try {
            ReportPreview preview =
                (ReportPreview)request.getAttribute("ReportPreview");
            if (preview==null){
                //AppLog.trace("Setting blank ReportPreview into request");
                preview = new ReportPreview();
                request.setAttribute("ReportPreview",preview);
            } else {
                //AppLog.trace("Found ReportReview in request");
            }
        } catch (Exception e) {
            //AppLog.trace("Blew Chunks trying to find ReportPreview.");
            ReportPreview preview = new ReportPreview();
            request.setAttribute("ReportPreview",preview);
        }
        // put collections in request
        request.setAttribute("survivorsList",survivorsList);
        request.setAttribute("chargesList",chargelist);
        request.setAttribute("paymentsList",paymentlist);
        request.setAttribute("customList",customlist);
        request.setAttribute("donationsList",donationslist);
        
        // Put form beans in request
        request.setAttribute("caseStatusOverview",caseStatus);
        
        return mapping.findForward("showCaseStatusJsp");
        
    }
    
}
