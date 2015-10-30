package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsExecutor;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.FirstCallInformationForm;

public class ShowFirstCallInformation extends Action {

	private Logger logger = Logger.getLogger(ShowFirstCallInformation.class
			.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		FirstCallInformationForm form = (FirstCallInformationForm) actionForm;
		ActionErrors errors = new ActionErrors();
		ActionForward actionForward = null;
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession) session
				.getAttribute(SessionValueKeys.DB_USER);
		
//		Locale myLocale = new Locale("en", sessionUser.getLocaleCountry().toUpperCase());
//		session.setAttribute( Constants.INTERNATIONAL_LOCALE, myLocale );.
		
		fdms.ui.struts.form.FirstCallInformationForm firstCallInformation = new fdms.ui.struts.form.FirstCallInformationForm();

		DbVitalsDeceased deceased = null;
		DbVitalsInformant informant = null;
		DbVitalsFirstCall firstCall = null;
		DbCase caseinfo = null;
		DbVitalsNextKin nextkin = null;
		DbVitalsExecutor executor = null;

		DatabaseTransaction t = null;
		FdmsDb fdmsdb = null;
		int vitalsid = 0;
      
		// Check for a DbUserSession
		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.login.invalid"));
		}

		// Check for existing vitalsId
		if (request.getParameter("vitalsId") != null) {
			vitalsid = FormatNumber.parseInteger(request.getParameter(
					"vitalsId").toString());
			sessionUser.setCurrentCaseID(vitalsid);
		} else {
			vitalsid = sessionUser.getCurrentCaseID();
		}

		if (vitalsid < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.nocase"));
		}

		// If errors found, bail out and return to input page
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}

		// Database Access Logic
		try {
			// Make Calls To Retrieve Db Objects
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			fdmsdb = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance();
			deceased = fdmsdb.getVitalsDeceased(t, vitalsid);
			informant = fdmsdb.getVitalsInformant(t, vitalsid);
			firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
			caseinfo = fdmsdb.getCase(t, vitalsid);
			nextkin = fdmsdb.getVitalsNextKin(t, vitalsid);
			executor = fdmsdb.getVitalsExecutor(t, vitalsid);
			SessionHelpers.setChapelListInSession(request);
			SessionHelpers.setArrangerListInSession(request);

			// add to request to populate javascript array
			DbLocation[] dbLocations = fdmsdb.getLocationsForRegion(t,sessionUser.getRegion());
			request.setAttribute("chapels", dbLocations);

			// Populate The Form Bean
			if (form == null) {
				form = new fdms.ui.struts.form.FirstCallInformationForm();
			}

			// create executor bean if one does not exist
			if (executor == null) {
				executor = new DbVitalsExecutor();
			}

			// executor part of call info page
			if ("Y".equalsIgnoreCase(executor.getIsExecutorSame())) {
				form.setExecutorSame(true);
			} else {
				form.setExecutorSame(false);
			}

			form.setExecutorPersonId(executor.getExecutorPersonId());
			form.setExecutorFirstName(executor.getExecutorFirstname());
			form.setExecutorLastName(executor.getExecutorLastname());
			form.setExecutorPhone(executor.getExecutorPhone());
			form.setExecutorCellPhone(executor.getExecutorCellPhone());
			form.setExecutorRelation(executor.getExecutorRelation());
			form.setExecutorState(executor.getExecutorState());
			form.setExecutorStreet(executor.getExecutorStreet());
			form.setExecutorStreet2(executor.getExecutorStreet2());
			form.setExecutorStreet3(executor.getExecutorStreet3());
			form.setExecutorZip(executor.getExecutorZip());
			form.setExecutorEmail(executor.getExecutorEmail());

			form.setVitalsId(new Integer(sessionUser.getCurrentCaseID()).toString());
			form.setDirective("change");
			form.setArrangeDate(FormatDate.MDYtoMMDDYYYY(firstCall.getArrangeDate()));
			form.setTime(firstCall.getArrangeTime());
			form.setPreneedDate(FormatDate.MDYtoMMDDYYYY(firstCall.getOriginalPnDate()));
			
			CompanyOptionsManager coMgr = new CompanyOptionsManager ();
			DbPreneed preneed		= FdmsDb.getInstance().getPreneed(t, vitalsid);	
		    int turnOffcontractNumberOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_TURN_OFF_AUTOMATIC_ASSIGN_CONTRACT_NUMBER);
		    int turnOffcontractNumberForPreneed = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_TURN_OFF_AUTOMATIC_ASSIGN_CONTRACT_NUMBER_PRENEED);

		    //if (turnOffcontractNumberOption == 0 && (preneed!=null && preneed.getStatus() != null && preneed.getStatus().compareToIgnoreCase("A") != 0)){
			if (turnOffcontractNumberOption == 0){
		    	if (turnOffcontractNumberForPreneed ==0){
				int contractNumber = SessionHelpers.getContractNumber(sessionUser.getDbLookup(), sessionUser.getRegion());
				//if case is preneed we need to set the contract number
				//DbPreneed preneed		= FdmsDb.getInstance().getPreneed(t, vitalsid);	
				if (preneed!=null && preneed.getStatus() != null && (preneed.getStatus().compareToIgnoreCase("A") == 0) && caseinfo.getContractCode().length()<=0) {
	//					 contractNumber = SessionHelpers.getContractNumber(sessionUser.getDbLookup(), sessionUser.getRegion());
						 form.setContractNumber(String.valueOf(contractNumber));
						 form.setNextContractNumber(String.valueOf(contractNumber+1));
						 form.setPreSetContractNumber(String.valueOf(contractNumber));
			    }
					 
				if (caseinfo.getContractCode() == null || caseinfo.getContractCode().length() == 0){
					form.setContractNumber(String.valueOf(contractNumber));
					form.setNextContractNumber(String.valueOf(contractNumber+1));
					form.setPreSetContractNumber(String.valueOf(contractNumber));
				}
				
				//if (preneed.getStatus().compareToIgnoreCase("A") == 0){
					//form.setContractNumber("");
					//form.setNextContractNumber(String.valueOf(contractNumber+1));
					//form.setPreSetContractNumber("");
				//}
		    	}
		    }	
		    
		    if (caseinfo.getContractCode() != null && caseinfo.getContractCode().length() > 0){
				form.setContractNumber(caseinfo.getContractCode());
				form.setPreSetContractNumber(caseinfo.getContractCode());
		    }
			form.setCaseNumber(caseinfo.getCaseCode());
			int nextCaseNumber = SessionHelpers.getCaseNumber(sessionUser.getDbLookup(), sessionUser.getRegion());
			form.setNextCaseNumber(String.valueOf(nextCaseNumber));
			form.setChapel(String.valueOf(caseinfo.getChapelNumber())); // caseinfo.getChapelLocation());	
			
			form.setDirector(String.valueOf(firstCall.getArrangerID()));
			// populate exclusively if arrangerId is inactive.
			DbArrangers arranger = FdmsDb.getInstance().getArrangerInactive(t,sessionUser.getRegion(),firstCall.getArrangerID());
			if(arranger != null ){
				List list = (List)request.getSession().getAttribute("directorList");
				 if(list == null){
					 list = new ArrayList();
				 }
				 list.add(new OptionsList(Integer.toString(arranger.getId()) ,arranger.getName()));
				 request.getSession().setAttribute("directorList",list); 
			}
			
			form.setLocationDeceased(firstCall.getPlaceDeathAddr());
			form.setLocDeathLicenseNumber(firstCall.getLocDeathLicenseNumber());
			form.setSource(firstCall.getSource());
			form.setEmbalming(firstCall.getEmbalmingReason());
			form.setMemorialName(deceased.getDecFullName());
			form.setPrefix(deceased.getDecmrmrs());
			form.setFirstName(deceased.getDecFName());
			form.setMiddleName(deceased.getDecMName());
			form.setLastName(deceased.getDecLName());
			form.setSuffix(deceased.getSuffix());
			form.setMaidenName(deceased.getDecMaiden());
			form.setPlaceDeath(firstCall.getPlaceDeath());
			form.setPlaceDeathCity(firstCall.getPlaceDeathCity());
			form.setPlaceDeathState(firstCall.getPlaceDeathState());
			form.setPlaceDeathZip(FormatString.formatZip(sessionUser.getLocaleCountry(), firstCall.getPlaceDeathZip()));
			form.setBirthDate(FormatDate.MDYtoMMDDYYYY(deceased.getDateOfBirth()));
			form.setDeathDate(FormatDate.MDYtoMMDDYYYY(deceased.getDateOfDeath()));
			form.setServiceDate(FormatDate.MDYtoMMDDYYYY(deceased.getDateOfBurial()));
			form.setDispDate(FormatDate.MDYtoMMDDYYYY(firstCall.getDispositionDate()));
			form.setAge(String.valueOf(firstCall.getAgeYears()));
			
			if (firstCall.getFacilitySameAsChapel() != null && firstCall.getFacilitySameAsChapel().equals("Y")) {
				form.setFacilitySame(true);
			} else {
				form.setFacilitySame(false);
			}
			
			form.setFacilityName(firstCall.getFacilityName());
			form.setFacility(firstCall.getFacilityName());
			
			logger.debug("firstCall facilityName : " + firstCall.getFacilityName());
			form.setFacilityStreet(firstCall.getFacilityStreet());
			form.setFacilityCity(CsvTable.getField(firstCall.getFacilityCityStZip(), 1));
			form.setFacilityState(CsvTable.getField(firstCall.getFacilityCityStZip(), 2));
			form.setFacilityZip(FormatString.formatZip(sessionUser.getLocaleCountry(), CsvTable.getField(firstCall.getFacilityCityStZip(), 3)));
			form.setFacilityPhone(FormatString.formatPhone(firstCall.getFacilityPhone()));
			form.setFacilityLicense(firstCall.getFacilityLicenseNo());
			logger.debug("deceasedSame is: " + deceased.getDeceasedSame());
			if (deceased.getDeceasedSame() != null && deceased.getDeceasedSame().equals("Y")) {
				form.setDeceasedSame(true);
			} else {
				form.setDeceasedSame(false);
			}
			form.setInformantSalutation(informant.getSalutation());
			form.setInformantFirst(informant.getFname());
			form.setInformantMiddle(informant.getMname());
			form.setInformantLast(informant.getLname());
			form.setInformantStreet(informant.getStreet());
			form.setInformantStreet2(informant.getRoad2());
			form.setInformantStreet3(informant.getRoad3());
			form.setInformantCity(informant.getCity());
			form.setInformantState(informant.getState());
			form.setInformantZip(FormatString.formatZip(sessionUser.getLocaleCountry(), informant.getZip()));
			form.setInformantPhone(FormatString.formatPhone(informant.getPhone()));
			form.setInformantCellPhone(FormatString.formatPhone(informant.getCellPhone()));
			form.setInformantRelation(informant.getRelated());
			form.setInformantEmail(informant.getInformantEmail());
			form.setInformantPreferCommunicate(informant.getInformantPreferCommunicate());
			form.setNextKinSalutation(nextkin.getSalutation());
			form.setNextKinFirst(nextkin.getFirstname());
			form.setNextKinLast(nextkin.getLastname());
			form.setNextKinMiddle(nextkin.getMiddleName());
			form.setNextKinStreet(nextkin.getStreet());
			form.setNextKinStreet2(nextkin.getRoad2());
			form.setNextKinStreet3(nextkin.getRoad3());
			form.setNextKinCity(nextkin.getCity());
			form.setNextKinState(nextkin.getState());
			form.setNextKinZip(FormatString.formatZip(sessionUser.getLocaleCountry(), nextkin.getZip()));
			form.setNextKinPhone(FormatString.formatPhone(nextkin.getPhone()));
			form.setNextKinCellPhone(FormatString.formatPhone(nextkin.getCellPhone()));
			form.setNextKinRelation(nextkin.getRelation());
			form.setNextKinEmail(nextkin.getEmail());
			form.setNextKinPreferCommunicate(nextkin.getNextKinPreferCommunicate());
			
			if (nextkin.getSameAsInformant().equals("Y")) {
				form.setNextKinSame(true);
			} else {
				form.setNextKinSame(false);
			}
			form.setShippingInfo(firstCall.getShippingInfo());
			form.setCallInfoNote(firstCall.getCallInfoNote());
			
			form.setShowInformantContractSigner(false);
			/*
			DbBillto[] dbBillTo = com.aldorsolutions.webfdms.databaseFdmsDb.getInstance().getBilltoForID(t, deceased.getId());
            if (dbBillTo != null && dbBillTo.length > 0) {
            	form.setInformantContractSigner( "Y".equals(dbBillTo[0].getContractSigner()) ? true : false );
            }
            */
			if("Y".equals(informant.getInformantIsBillToParty())){
				DbBillto[] dbBillTo = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getBilltoForID(t, vitalsid);
				for ( int i = 0; i < dbBillTo.length; i++ ) {
					DbBillto billToInfo = dbBillTo[i];
					
					if ( matchBillToInfo(billToInfo, form)) {
						form.setInformantBillToParty(true);
						form.setBillToParty(billToInfo);
						break;
					}
					
				}
			}else{
				form.setInformantBillToParty(false);
				form.setBillToParty(null);
			}
			// Fill the facility information from the locale and location
			// tables.
			LocaleDTO dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
			DbLocation dbLocation = fdmsdb.getLocation(t, Integer.parseInt(form
					.getChapel()));

			if (dbLocation != null) {
				
				form.setChapelName(dbLocation.getName());
				form.setChapelStreet(dbLocation.getAddr1());
				form.setChapelCity(dbLocation.getCity());
				form.setChapelState(dbLocation.getState());
				form.setChapelZip(dbLocation.getZip());
				form.setChapelLicense(dbLocation.getLicenseNumber());
				form.setChapelPhone(FormatString.formatPhone(dbLocation.getPhone()));
		
				logger.debug("dbLocation != null");
				logger.debug("dbLocale name : " + dbLocale.getName());
				if (form.getFacilityName() == null
						|| form.getFacilityName().trim().length() == 0) {
					form.setFacilityName(dbLocale.getName());
					form.setFacility(dbLocale.getName());
				}
				if (form.getFacilityStreet() == null
						|| form.getFacilityStreet().trim().length() == 0) {
					form.setFacilityStreet(dbLocation.getAddr1());
				}
				if (form.getFacilityCity() == null
						|| form.getFacilityCity().trim().length() == 0) {
					form.setFacilityCity(dbLocation.getCity());
				}
				if (form.getFacilityState() == null
						|| form.getFacilityState().trim().length() == 0) {
					form.setFacilityState(dbLocation.getState());
				}
				if (form.getFacilityZip() == null
						|| form.getFacilityZip().trim().length() == 0) {
					form.setFacilityZip(FormatString.formatZip(sessionUser.getLocaleCountry(), dbLocation
							.getZip()));
				}
				if (form.getFacilityLicense() == null
						|| form.getFacilityLicense().trim().length() == 0) {
					form.setFacilityLicense(dbLocation.getLicenseNumber());
				}
				if (form.getFacilityPhone() == null
						|| form.getFacilityPhone().trim().length() == 0) {
					form.setFacilityPhone(FormatString.formatPhone(dbLocation
							.getPhone()));
				}
			} else {
				logger.debug("dbLocation == null");
				form.setChapelName("");
				form.setChapelStreet("");
				form.setChapelCity("");
				form.setChapelState("");
				form.setChapelZip("");
				form.setChapelLicense("");
				form.setChapelPhone("");
			}

			// Add form to session
			session.setAttribute("firstCallInformation", form);
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowFirstCall.doPerform. "
					+ pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));
		} finally {
			if (t != null)
				t.closeConnection();
		}

		actionForward = mapping.findForward("firstCallInformation");
		if (!errors.isEmpty()) {
			// AppLog.info("ShowFirstCallInformation Invoking forward mapping
			// getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}
		return actionForward;
	}
	
	private boolean matchBillToInfo (DbBillto billToInfo, FirstCallInformationForm form) {
		String informFirst = compareSafeString(form.getInformantFirst());
		String informLast = compareSafeString(form.getInformantLast());
		String billToFirst = compareSafeString(billToInfo.getFirstName());
		String billToLast = compareSafeString(billToInfo.getLastName());
		
		if ( informFirst.equalsIgnoreCase(billToFirst) && informLast.equalsIgnoreCase(billToLast) ) {
			return ( true );
		}
		
		return ( false );
		
	}
	private String compareSafeString (String str) {
		if ( str == null ) {
			return ("");
		}
		
		return ( str.trim() );
	}
}
