package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Hashtable;
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

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbArrangersPeer;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsMedical;
import com.aldorsolutions.webfdms.beans.DbVitalsSpouse;
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
import com.aldorsolutions.webfdms.util.Translator;

public class ShowVitals extends Action {

	private Logger logger = Logger.getLogger(ShowVitals.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		ActionErrors errors = new ActionErrors();
		ActionForward actionForward = null;
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession) session
				.getAttribute(SessionValueKeys.DB_USER);
		fdms.ui.struts.form.VitalsForm vitalform = new fdms.ui.struts.form.VitalsForm();

		DbVitalsDeceased deceased = null;
		DbVitalsInformant informant = null;
		DbArrangers[] dirlist = null;
		DbVitalsFirstCall firstCall = null;
		DbCase caseinfo = null;
		DbVitalsSpouse spouse = null;
		DbVitalsMedical medical = null;
		DbLocation chapel = null;
		DatabaseTransaction t = null;
		DbArrangers arrangerDetail = null;
		int vitalsid = 0;

		// Added by haranesh patel

		DbSpeedData[] dbSpeedData = FdmsDb.getInstance().getSpeedData(
				sessionUser.getDbLookup(), sessionUser.getRegion(), "States");
		ArrayList states = new java.util.ArrayList();

		for (int i = 0; i < dbSpeedData.length; i++) {
			String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
			String listLabel2 = CsvTable.getField(dbSpeedData[i].getData(), 2);

			states.add(new OptionsList(listLabel2, listLabel2));
		}

		// Check for existing vitalsId.
		// AppLog.trace("VitalsId param:"+request.getParameter("vitalsId"));
		// AppLog.trace("UserSession.Vitalsid:"+sessionUser.getCurrentCaseID());
		if (request.getParameter("vitalsId") != null) {
			vitalsid = FormatNumber.parseInteger(request.getParameter(
					"vitalsId").toString());
			sessionUser.setCurrentCaseID(vitalsid);
		} else {
			// AppLog.trace("ShowVitals no vitals ID parameter. Retrieving from user.");
			vitalsid = sessionUser.getCurrentCaseID();
		}
		// AppLog.trace("ShowVitals processing ID:"+vitalsid);
		if (vitalsid < 1) {
			// AppLog.warning("ShowVitals. Invalid VitalsID to process:"+vitalsid);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.nocase"));
		}
		// If errors found, bail out and return to input page
		if (!errors.isEmpty()) {
			// AppLog.info("ShowVitals Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}
		SessionHelpers.setVitalsIdInRequest(request, vitalsid);
		// Database Access Logic
		try {
			// Make Calls To Retrieve Db Objects
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			FdmsDb fdms = FdmsDb.getInstance();

			deceased = fdms.getVitalsDeceased(t, vitalsid);
			informant = fdms.getVitalsInformant(t, vitalsid);
			firstCall = fdms.getVitalsFirstCall(t, vitalsid);

			caseinfo = fdms.getCase(t, vitalsid);
			spouse = fdms.getVitalsSpouse(t, vitalsid);
			medical = fdms.getVitalsMedical(t, vitalsid);
			chapel = fdms.getLocation(t, caseinfo.getChapelNumber());
			arrangerDetail = FdmsDb.getInstance().getArrangerInactive(t,
					sessionUser.getRegion(), firstCall.getArrangerID());

			Hashtable h = new Hashtable();
			Integer locale = new Integer(sessionUser.getRegion());

			h.put(DbArrangersPeer.LOCALE, locale);
			h.put(DbArrangersPeer.EMBALMERLICENSENUMBER, "**");
			dirlist = fdms.getArrangersByType(t, h);

			SessionHelpers.setChapelListInSession(request);
			SessionHelpers.setArrangerListInSession(request);
			SessionHelpers.setLicenseListInSession(request);
		} catch (PersistenceException pe) {
			logger
					.error("Persistence Exception in ShowVitals.doPerform. "
							+ pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));
		} finally {
			if (t != null)
				t.closeConnection();
		}

		// Populate The Form Bean
		vitalform.setDirective("change");
		vitalform.setVitalsid(vitalsid);
		vitalform.setDeceasedFirstName(deceased.getDecFName());
		vitalform.setDeceasedMiddleName(deceased.getDecMName());
		vitalform.setDeceasedLastName(deceased.getDecLName());
		vitalform.setSex(deceased.getSex());
		vitalform.setIdentificationMarks(deceased.getIdentificationMarks());
		vitalform.setDateOfDeath(FormatDate.MDYtoMMDDYYYY(deceased
				.getDateOfDeath()));
		vitalform.setAdmittedFacilityDateOfPlaceOfDeath(FormatDate
				.MDYtoMMDDYYYY(firstCall
						.getAdmittedFacilityDateOfPlaceOfDeath()));
		vitalform.setAgeYears(String.valueOf(firstCall.getAgeYears()));
		vitalform.setDateOfBirth(FormatDate.MDYtoMMDDYYYY(deceased
				.getDateOfBirth()));
		vitalform.setCountyOfDeath(firstCall.getCountyOfDeath());
		vitalform.setAgeMonths(String.valueOf(firstCall.getAgeMonths()));
		vitalform.setAgeDays(String.valueOf(firstCall.getAgeDays()));
		vitalform.setAgeHours(String.valueOf(firstCall.getAgeHours()));
		vitalform.setAgeMinutes(String.valueOf(firstCall.getAgeMinutes()));
		vitalform.setLocationOfDeath(firstCall.getPlaceDeath());
		vitalform
				.setLocDeathLicenseNumber(firstCall.getLocDeathLicenseNumber());
		vitalform.setInpatient(firstCall.getInpatientDOA());
		vitalform.setCityOfDeath(firstCall.getPlaceDeathCity());
		vitalform.setSocialSecurityNumber(FormatString.formatSocialSecurityNo(
				sessionUser.getLocaleCountry(), deceased.getSSNo()));
		vitalform.setDeceasedOccupation(deceased.getOccupation());
		vitalform.setDeceasedKindBusinessOrIndustry(deceased.getKindBusiness());
		vitalform.setYearsAtOccupation(Integer.toString(deceased
				.getYearsAtOccupation()));
		vitalform.setEmployer(deceased.getEmployer());
		vitalform.setDeceasedState(deceased.getDecResState());
		vitalform.setDeceasedCounty(deceased.getDecResCounty());

		vitalform.setLocalityInsideCity(deceased.getDecResCityTWPBox()
				.compareToIgnoreCase("C") == 0);
		vitalform.setLocalityInsideVillage(deceased.getDecResCityTWPBox()
				.compareToIgnoreCase("V") == 0);
		vitalform.setLocalityInsideTwp(deceased.getDecResCityTWPBox()
				.compareToIgnoreCase("T") == 0);
		vitalform.setLocalityUnincorporated(deceased.getDecResCityTWPBox()
				.compareToIgnoreCase("I") == 0);
		vitalform.setLocalityUnknown(deceased.getDecResCityTWPBox()
				.compareToIgnoreCase("U") == 0);
		vitalform.setLocalityInsideCityOption(deceased.getDecResCityTWPBox());

		vitalform.setTransferredLocationName(deceased
				.getTransferredLocationName());
		vitalform.setTransferredLocationAddr(deceased
				.getTransferredLocationAddr());
		vitalform.setTransferredLocationCity(deceased
				.getTransferredLocationCity());
		vitalform.setTransferredLocationState(deceased
				.getTransferredLocationState());
		vitalform.setTransferredLocationZip(deceased
				.getTransferredLocationZip());
		vitalform.setTransferredLocationPhone(FormatString.formatPhone(deceased
				.getTransferredLocationPhone()));

		vitalform.setHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld(deceased
				.getHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld());
		vitalform.setEmployerAddr(deceased.getEmployerAddr());
		vitalform.setResidentWithinCityVillageLimit(deceased
				.getResidentWithinCityVillageLimit());
		vitalform.setResidentLocalityLimitName(deceased
				.getResidentLocalityLimitName());

		vitalform.setHospitalizedLast2Months(deceased
				.getHospitalizedLast2Months());
		vitalform.setPregnantDeliveryDate(FormatDate.MDYtoMMDDYYYY(deceased
				.getPregnantDeliveryDate()));

		vitalform.setCoronerSPhysician(medical.getCoronerSPhysician());
		vitalform
				.setCoronerSPhysicianTitle(medical.getCoronerSPhysicianTitle());
		vitalform.setCoronerSPhysicianLicense(medical
				.getCoronerSPhysicianLicense());
		vitalform.setCoronerSPhysicianDateSign(medical
				.getCoronerSPhysicianDateSign());

		vitalform.setAttendingPhysicianTitle(medical
				.getAttendingPhysicianTitle());
		vitalform
				.setAttendingPhysicianAddr(medical.getAttendingPhysicianAddr());
		vitalform
				.setAttendingPhysicianCity(medical.getAttendingPhysicianCity());
		vitalform.setAttendingPhysicianState(medical
				.getAttendingPhysicianState());
		vitalform.setAttendingPhysicianZip(medical.getAttendingPhysicianZip());
		vitalform.setAttendingPhysicianPhone(medical
				.getAttendingPhysicianPhone());
		vitalform.setAttendingPhysicianLicense(medical
				.getAttendingPhysicianLicense());
		// added by haranesh patel
		vitalform.setReferralNumber(medical.getReferralNumber());

		vitalform.setLocOfDeathCVT(deceased.getLocOfDeathCVT());
		vitalform.setDeceasedCity2(deceased.getDecResCityTWP());
		vitalform.setDeceasedStreet(deceased.getDecResStreet());
		vitalform.setDeceasedZipCode(deceased.getDecResZip());
		vitalform.setDeceasedCountry(deceased.getDecResCountry());
		vitalform.setDeceasedResLengthTime(deceased.getDecResLengthOfTime());
		vitalform.setCountyOfBirth(deceased.getCountyOfBirth());
		vitalform.setCountyOfIssue(deceased.getCountyOfIssue());
		vitalform.setIssueDate(FormatDate.MDYtoMMDDYYYY(deceased.getIssueDate()));
		vitalform.setBirthplaceCity(CsvTable.getField(deceased.getBirthPlaceCSV(), 1));
		vitalform.setBirthplaceState(CsvTable.getField(deceased.getBirthPlaceCSV(), 2));
		vitalform.setBirthplaceCountry(CsvTable.getField(deceased.getBirthPlaceCSV(), 3));
	
		
		vitalform.setMaritalStatus(deceased.getDecMarried());
		// AppLog.trace("ShowVitals: Spouse maiden:"+spouse.getMaiden());
		// AppLog.trace("ShowVitals: Spouse First:"+spouse.getFname());
		if (spouse.getMaiden().compareTo(" ") > 0
				&& spouse.getFname().compareTo(" ") <= 0) {
			// AppLog.trace("ShowVitals: Splitting spouse name");
			// this case has a full spouse name but no split -- so split the
			// names manually
			spouse.convertSpouseFullToSplit();
		}
		// vitalform.setBirthplaceCity(deceased.getBirthPlace());
		
		vitalform.setSurvivingSpouse1(spouse.getFname());
		vitalform.setSurvivingSpouse2(spouse.getMname());
		vitalform.setSurvivingSpouse3(spouse.getLname());
		vitalform.setSurvivingSpouseStreet(spouse.getSurvivingSpouseStreet());
		vitalform.setSurvivingSpouseCity(spouse.getSurvivingSpouseCity());
		vitalform.setSurvivingSpouseState(spouse.getSurvivingSpouseState());
		vitalform.setSurvivingSpouseZip(spouse.getSurvivingSpouseZip());

		vitalform.setVeteran(Translator.formatYesNo(deceased.getVeteranYN()));
		vitalform.setWasPeaceOfficer(Translator.formatYesNo(deceased
				.getWasPeaceOfficerYN()));
		vitalform.setAncestry(deceased.getAncestry());
		vitalform.setRace(deceased.getRace());
		vitalform.setTribalMember(deceased.getTribalMember());
		vitalform.setTribalName(deceased.getTribalName());
		vitalform.setDeceasedElementaryEducation(deceased
				.getElementaryEducation());
		vitalform.setDeceasedCollegeEducation(deceased.getCollegeEducation());
		vitalform.setFatherFirstName(deceased.getFatherFirstName());
		vitalform.setFatherMiddleName(deceased.getFatherMiddleName());
		vitalform.setFatherLastName(deceased.getFatherLastName());
		vitalform.setFatherMaidenName(deceased.getFatherMaidenName());
		vitalform.setMotherFirstName(deceased.getMotherFirstName());
		vitalform.setMotherMiddleName(deceased.getMotherMiddleName());
		vitalform.setMotherLastName(deceased.getMotherLastName());
		vitalform.setMotherMaidenName(deceased.getMotherMaidenName());
		vitalform.setInformantFirstName(informant.getFname());
		vitalform.setInformantMiddleName(informant.getMname());
		vitalform.setInformantLastName(informant.getLname());
		vitalform.setInformantRelation(informant.getRelated());
		vitalform.setInformantStreet(informant.getStreet());
		vitalform.setInformantCity(informant.getCity());
		vitalform.setInformantState(informant.getState());
		vitalform.setInformantZipCode(informant.getZip());
		vitalform.setDisposition(firstCall.getDispositionMethod());
		vitalform.setDispositionPlaceType(firstCall.getDispositionPlaceType());
		vitalform.setBurialPermitNumber(deceased.getBurialPermitNumber());
		vitalform.setDispositionPlace(firstCall.getDispositionPlace());
		vitalform.setDispositionStreet(firstCall.getDispositionStreet());
		vitalform.setDispositionCity(firstCall.getDispositionCity());
		vitalform.setDispositionState(firstCall.getDispositionState());
		vitalform.setDispositionCounty(firstCall.getDispositionCounty());
		vitalform.setDispositionZipCode(firstCall.getDispositionZip());

		vitalform.setDateOfDisposition(FormatDate.MDYtoMMDDYYYY(firstCall
				.getDispositionDate()));
		vitalform.setFacilityLicenseNumber(firstCall.getFacilityLicenseNo());
		vitalform.setDateOfNotifyToDirector(FormatDate.MDYtoMMDDYYYY(firstCall
				.getDateOfNotifyToDirector()));
		vitalform.setTimeOfNotifyToDirectory(firstCall
				.getTimeOfNotifyToDirectory());
		vitalform.setLicenseNumber(String.valueOf(firstCall.getArrangerID()));
		vitalform.setFacilityName(firstCall.getFacilityName());
		vitalform.setFacilityStreet(firstCall.getFacilityStreet());
		vitalform.setFacilityCity(CsvTable.getField(firstCall
				.getFacilityCityStZip(), 1));
		vitalform.setFacilityState(CsvTable.getField(firstCall
				.getFacilityCityStZip(), 2));
		vitalform.setFacilityZipCode(CsvTable.getField(firstCall
				.getFacilityCityStZip(), 3));
		vitalform.setFacilityPhone(FormatString.formatPhone(firstCall
				.getFacilityPhone()));
		vitalform.setCause1(medical.getCause1());
		vitalform.setCause2(medical.getCause2());
		vitalform.setCause3(medical.getCause3());
		vitalform.setCause4(medical.getCause4());
		vitalform.setInterval1(medical.getInterval1());
		vitalform.setInterval2(medical.getInterval2());
		vitalform.setInterval3(medical.getInterval3());
		vitalform.setInterval4(medical.getInterval4());
		vitalform.setOtherCondition(medical.getOtherConditions());
		vitalform.setAutopsy(Translator.formatYesNo(medical.getAutopsy()));
		vitalform.setBodyFoundMore24Hr(Translator.formatYesNo(medical
				.getBodyFoundMore24Hr()));
		vitalform.setNotificationOfExaminerRequired(Translator
				.formatYesNo(medical.getNotificationOfExaminerRequired()));
		vitalform.setHospiceStatus(Translator.formatYesNo(medical
				.getHospiceStatus()));
		vitalform.setFindings(Translator.formatYesNo(medical.getFindings()));
		vitalform.setActualPlaceDeath(medical.getActualPlaceDeath());
		vitalform.setReferredToMedicalExaminer(Translator.formatYesNo(medical
				.getReferredToME()));
		vitalform.setReferralNumber(medical.getReferralNumber());
		// meCheckBox
		// 1 = certifying physician signed (MD)
		// 2 = medical examiner signed (ME)
		// 3 = Both certifying and medical signed
		vitalform
				.setMedicalExaminerBox1((medical.getMeCheckBox() == 1 || medical
						.getMeCheckBox() == 3));
		vitalform
				.setMedicalExaminerBox2((medical.getMeCheckBox() == 2 || medical
						.getMeCheckBox() == 3));
		vitalform.setNpCheckBox1(medical.getNpCheckBox() == 1);
		vitalform.setNpDateSigned(FormatDate.MDYtoMMDDYYYY(medical
				.getNpDateSigned()));
		vitalform.setNpLicenseNumber(medical.getNpLicenseNumber());
		vitalform.setCertifierDateSigned(FormatDate.MDYtoMMDDYYYY(medical
				.getDateSignedCertifier()));
		vitalform.setTimeOfDeath(deceased.getTimeOfDeath());
		vitalform.setTimeOfDeathStatus(deceased.getTimeOfDeathStatus());
		vitalform.setMedicalExaminerDateSigned(FormatDate.MDYtoMMDDYYYY(medical
				.getMeDateSigned()));
		vitalform.setMedicalExaminerCaseNumber(medical.getMeCaseNumber());
		vitalform.setAttendingPhysician(medical.getAttendingPhysician());
		vitalform.setMedicalExaminerDeathDate(FormatDate.MDYtoMMDDYYYY(medical
				.getMeDateDeath()));
		vitalform.setTimeOfDeath2(medical.getMeTimeDeath());
		vitalform.setCompletedCauseOfDeathDoctorName(medical.getDrName());
		vitalform.setCompletedCauseOfDeathDoctorStreet(medical.getDrStreet());
		vitalform.setCompletedCauseOfDeathDoctorPhone(FormatString
				.formatPhone(medical.getDrPhone()));
		vitalform.setCompletedCauseOfDeathDoctorFax(medical.getDrFax());
		vitalform.setCompletedCauseOfDeathDrJusticeOfThePeace(medical
				.getDrJusticeOfThePeace());
		vitalform.setCompletedCauseOfDeathDoctorCity(medical.getDrCity());
		vitalform.setCompletedCauseOfDeathDoctorState(medical.getDrState());
		vitalform.setCompletedCauseOfDeathDoctorZip(medical.getDrZip());
		vitalform.setCompletedCauseOfDeathDoctorLicense(medical.getDrLicense());
		vitalform.setMedicalExaminerAccidentSuicide(medical
				.getAccidentSuicide());
		vitalform.setMedicalExaminerInjuryDate(FormatDate.MDYtoMMDDYYYY(medical
				.getInjuryDate()));
		vitalform.setMedicalExaminerInjuryTime(medical.getInjuryTime());
		vitalform.setMedicalExaminerInjuryDescription(medical
				.getInjuryDescription());
		vitalform.setDateEmbalmed(FormatDate.MDYtoMMDDYYYY(medical
				.getDateEmbalmed()));
		vitalform.setMedicalExaminerInjuryAtWork(Translator.formatYesNo(medical
				.getInjuryAtWork()));
		vitalform.setMedicalExaminerInjuryTransportation(medical
				.getInjuryTransportation());
		vitalform.setMedicalExaminerInjuryPlace(medical.getInjuryPlace());
		vitalform.setMedicalExaminerInjuryStreet(medical.getInjuryAddress());
		vitalform.setMedicalExaminerInjuryCity(CsvTable.getField(medical
				.getInjuryCityState(), 1));
		vitalform.setMedicalExaminerInjuryState(CsvTable.getField(medical
				.getInjuryCityState(), 2));
		vitalform.setMedicalExaminerInjuryCounty(medical.getInjuryCounty());
		vitalform.setMedicalExaminerInjuryAptNo(medical.getInjuryAptNo());
		vitalform.setMedicalExaminerInjuryZipCode(medical.getInjuryZipCode());
		// extra generic fields
		vitalform.setDeathInsideCity(Translator.formatYesNo(firstCall
				.getPlaceDeathInside()));
		vitalform.setEmbalmerID(String.valueOf(firstCall.getEmbalmerID()));
		vitalform.setDirectorID(String.valueOf(firstCall.getArrangerID()));
		// populate exclusively if arrangerId is inactive.
		if (arrangerDetail != null) {
			List list = (List) request.getSession()
					.getAttribute("directorList");
			if (list == null) {
				list = new ArrayList();
			}
			list.add(new OptionsList(Integer.toString(arrangerDetail.getId()),
					arrangerDetail.getName()));
			request.getSession().setAttribute("directorList", list);
		}
		vitalform.setDeceasedMaiden(deceased.getDecMaiden());
		vitalform.setDecPrefix(deceased.getDecmrmrs());
		vitalform.setDecSuffix(deceased.getSuffix());
		vitalform.setHispanic(Translator.formatYesNo(deceased.getHispanicYN()));
		vitalform.setAboriginal(deceased.getAboriginal());
		vitalform.setLivedOnReserve(deceased.getLivedOnReserve());
		vitalform.setAliasFirstName(deceased.getAliasFirst());
		vitalform.setAliasMiddleName(deceased.getAliasMiddle());
		vitalform.setAliasLastName(deceased.getAliasLast());
		vitalform.setAliasPrefix(deceased.getAliasPrefix());
		vitalform.setAliasSuffix(deceased.getAliasSuffix());
		/*
		 * in old FDMS2000, the alias was stored as follows
		 * lstrncpy(dcvital->DecAliasFirst, vitals.cVitMotherBirthState,
		 * BIRTHSTATE); lstrncpy(dcvital->AliasMiddle,
		 * vitals.cVitFatherBirthState, BIRTHSTATE);
		 * lstrncpy(dcvital->AliasLast, vitals.cVitIDMarks, IDMARKS);
		 * lstrncpy(dcvital->AliasPrefix,vitals.cVitDecResLengthOfTime,
		 * RESTIME); lstrncpy(dcvital->AliasSuffix, vitals.cVitBirthSurname,
		 * DECLNAME);
		 * 
		 * if (vitalform.getAliasFirstName().compareTo(" ")<=0)
		 * vitalform.setAliasFirstName(deceased.getMotherBirthState()); if
		 * (vitalform.getAliasMiddleName().compareTo(" ")<=0)
		 * vitalform.setAliasMiddleName(deceased.getFatherBirthState()); //if
		 * (vitalform.getAliasLastName().compareTo(" ")<=0)
		 * vitalform.setAliasLastName(deceased.getMotherBirthState());
		 */
		vitalform.setCrematoryName(deceased.getCrematoryName());
		vitalform.setAuthorizingCoroner(medical.getDr2Name());
		if (medical.getDrTitle().equals("MD"))
			vitalform.setPhysicianMD(true);
		if (medical.getDrTitle().equals("ME"))
			vitalform.setPhysicianME(true);
		if (medical.getDrTitle().equals("DO"))
			vitalform.setPhysicianDO(true);
		if (medical.getDrTitle().equals("HO"))
			vitalform.setPhysicianHO(true);
		vitalform.setCertifierTitle(medical.getDrTitle());
		// if ( (medical.getDrTitle().compareToIgnoreCase("HO") !=0) &&
		// (medical.getDrTitle().compareToIgnoreCase("DO") !=0) &&
		// (medical.getDrTitle().compareToIgnoreCase("ME") !=0) &&
		// (medical.getDrTitle().compareToIgnoreCase("MD") !=0) ) {
		// vitalform.setCertifierTitle(medical.getDrTitle());
		// }
		vitalform.setPhysDateFrom(FormatDate.convertDateToMM_DD_YYYY(medical
				.getDrAttendDateFrom()));
		vitalform.setPhysDateTo(FormatDate.convertDateToMM_DD_YYYY(medical
				.getDrAttendDateTo()));
		vitalform.setPhysDateLast(FormatDate.convertDateToMM_DD_YYYY(medical
				.getDrAttendDateLast()));
		vitalform.setPhysViewedYesNo(FormatString.blankNull(medical
				.getDoctorViewedBody()));
		vitalform.setPregnantAtDeath(medical.getPregnantAtDeath());
		vitalform.setPregnant12Months(Translator.formatYesNo(medical
				.getPregnantLast12mos()));
		// NE fields
		vitalform.setVeteranWar(deceased.getMilitaryOrganizatn());
		vitalform.setVeteranDateEntered(FormatDate.MDYtoMMDDYYYY(deceased
				.getWarFromDate()));
		vitalform.setVeteranDateSeparated(FormatDate.MDYtoMMDDYYYY(deceased
				.getWarToDate()));
		// vitalform.setPregnancyPast3Months();
		vitalform.setTobaccoUser(medical.getTobacco());
		vitalform.setOrganDonationGranted(medical.getOrganDonationConsidered());
		vitalform.setOrganDonationConsidered(medical.getOrganDonationGranted());
		// IA extra field
		vitalform.setCitizenship(deceased.getCitizenship());
		vitalform.setDoctorOccupation(medical.getDoctorOccupation());

		// New Fields 2/5/04
		vitalform.setDecEducation(deceased.getDecEducation());
		vitalform.setLocationDeceased(firstCall.getPlaceDeathAddr());
		vitalform.setLoctnOfDeathZip(deceased.getLoctnOfDeathZip());
		vitalform.setTribalReservation(deceased.getTribalReservation());
		vitalform.setDecSpecifyHispanic(deceased.getDecSpecifyHispanic());
		vitalform.setStateOfDeath(deceased.getStateOfDeath());
		vitalform.setDateCertified(FormatDate.MDYtoMMDDYYYY(medical
				.getDateCertified()));
		vitalform.setDeceasedPrefix(deceased.getDeceasedPrefix());
		vitalform.setDeceasedSuffix(deceased.getSuffix());
		vitalform.setDeceasedMaidenName(deceased.getDecMaiden());
		vitalform.setAliasPrefix(deceased.getAliasPrefix());
		vitalform.setAliasSuffix(deceased.getAliasSuffix());

		vitalform.setFatherBirthCity(deceased.getFatherBirthCity());
		vitalform.setFatherBirthState(deceased.getFatherBirthState());
		vitalform.setFatherBirthCountry(deceased.getFatherBirthCountry());
		vitalform.setMotherBirthCity(deceased.getMotherBirthCity());
		vitalform.setMotherBirthState(deceased.getMotherBirthState());
		vitalform.setMotherBirthCountry(deceased.getMotherBirthCountry());

		vitalform.setInformantDateSigned(FormatDate.MDYtoMMDDYYYY(informant
				.getInformantDateSigned()));

		if (firstCall.getPlaceDeathState().equals("ON")
				&& (firstCall.getFuneralDirectorDateSigned() == null || firstCall
						.getFuneralDirectorDateSigned().trim().equals("")))
			vitalform.setFuneralDirectorDateSigned(FormatDate
					.MDYtoMMDDYYYY(firstCall.getArrangeDate()));
		else {
			vitalform.setFuneralDirectorDateSigned(FormatDate
					.MDYtoMMDDYYYY(firstCall.getFuneralDirectorDateSigned()));
		}

		vitalform.setBurialPermitIssuer(firstCall.getBurialPermitIssuer());
		vitalform.setPlaceOfIssue(firstCall.getPlaceOfIssue());
		vitalform.setBurialIssuedDate(FormatDate.MDYtoMMDDYYYY(firstCall
				.getBurialIssuedDate()));
		vitalform.setRegistrationNumber(firstCall.getRegistrationNumber());
		vitalform.setRegCodeNumber(firstCall.getRegCodeNumber());
		vitalform.setArrangerName(firstCall.getArrangerName());
		vitalform.setRegistrarFileDate(FormatDate.MDYtoMMDDYYYY(firstCall
				.getRegistrarFileDate()));

		vitalform.setDiagnosisDeferred(Translator.formatYesNo(medical
				.getDiagnosisDeferred()));
		vitalform.setInjuryOccurred(Translator.formatYesNo(medical
				.getInjuryOccurred()));
		vitalform.setCremationAuthorizer(deceased.getCremationAuthorizer());

		vitalform.setFatherForename(deceased.getFatherForename());
		vitalform.setFatherSurnameBirth(deceased.getFatherSurnameBirth());
		vitalform.setFatherOtherSurname(deceased.getFatherOtherSurname());
		vitalform.setFatherOccupation(deceased.getFatherOccupation());
		vitalform.setMotherForename(deceased.getMotherForename());

		vitalform.setMotherBirthday(deceased.getMotherBirthday());
		vitalform.setFatherBirthday(deceased.getFatherBirthday());
		vitalform.setMotherStreetAddress(deceased.getMotherStreetAddress());

		vitalform.setMotherSurnameBirth(deceased.getMotherSurnameBirth());
		vitalform.setMotherOtherSurname(deceased.getMotherOtherSurname());
		vitalform.setMotherOccupation(deceased.getMotherOccupation());
		vitalform.setStillBirthDesc(deceased.getStillBirthDesc());
		vitalform.setPregnancyDuration(Integer.toString(deceased
				.getPregnancyDuration()));
		vitalform.setNumberChildren(Integer.toString(deceased
				.getNumberChildren()));
		vitalform.setNumberLiveborn(Integer.toString(deceased
				.getNumberLiveborn()));
		vitalform.setNumberStillborn(Integer.toString(deceased
				.getNumberStillborn()));
		vitalform.setChildWeight(deceased.getChildWeight());
		vitalform.setBirthType(deceased.getBirthType());
		vitalform.setBirthOrder(deceased.getBirthOrder());
		vitalform.setChildNameAgreed(deceased.getChildNameAgreed());
		if (deceased.getFatherAge() > 0)
			vitalform.setFatherAge(Integer.toString(deceased.getFatherAge()));
		if (deceased.getMotherAge() > 0)
			vitalform.setMotherAge(Integer.toString(deceased.getMotherAge()));

		// California Vitals fields
		vitalform.setBiopsyYN(medical.getBiopsyYN());
		vitalform.setXopsyToFindCause(medical.getXopsyToFindCause());
		vitalform.setOperationType(medical.getOperationType());
		vitalform.setOperationPerformed(medical.getOperationPerformed());
		vitalform.setOperationDate(FormatDate.MDYtoMMDDYYYY(medical
				.getOperationDate()));

		for (int i = 0; i < dirlist.length; i++) {
			DbArrangers arranger = (DbArrangers) dirlist[i];
			if (String.valueOf(firstCall.getArrangerID()).compareToIgnoreCase(
					String.valueOf(arranger.getId())) == 0) {
				vitalform.setEmbalmerLicenseNumber(arranger
						.getEmbalmerLicenseNumber());
				vitalform.setDirectorLicenseNumber(arranger.getLicenseNumber());
				break;
			}
		}

		// Add form to session
		// request.setAttribute("vitals",vitalform);
		session.setAttribute("vitals", vitalform);
		actionForward = mapping.findForward("showVitals");

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			// actionForward = new ActionForward(mapping.getInput() );
		}

		String vitalsPage = "GenericVitals";
		String genericVitals = (chapel != null) ? chapel
				.getPreferenceGenericVitals() : "N";

		logger.debug("Chapel != null is " + (chapel != null));
		logger.debug("UseGenericVitals : " + genericVitals);
		logger.debug("User default state : " + sessionUser.getState());

		// if location setup to not use generic vitals page
		if (!"Y".equals(genericVitals)) {

			// check if first call info already exists
			if ((firstCall != null) && (firstCall.getPlaceDeathState() != null)
					&& (!"".equals(firstCall.getPlaceDeathState().trim()))) {

				vitalsPage = firstCall.getPlaceDeathState();
			}
			// else check if user has default state setup
			else if ((sessionUser.getState() != null)
					&& (!"".equals(sessionUser.getState()))) {

				vitalsPage = sessionUser.getState();
			}
			// else check if chapel state value is set
			else if ((chapel != null) && (chapel.getState() != null)
					&& (!"".equals(chapel.getState().trim()))) {

				vitalsPage = chapel.getState();
			}
		}

		logger.debug("STATE_OF_DEATH " + vitalsPage);
		request.setAttribute("STATE_OF_DEATH", vitalsPage);
		vitalform.setVitalsPage(vitalsPage);

		return actionForward;
	}

}
