package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import org.apache.struts.config.ModuleConfig;

import com.aldorassist.webfdms.delegate.LocationOptionsManager;
import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsMedical;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.DbVitalsSpouse;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.Translator;

import fdms.ui.struts.form.VitalsForm;

public class ProcessVitals extends Action {

	private Logger logger = Logger.getLogger(ProcessVitals.class.getName());
	private ArrayList formErrors;

	/**
	 * This method is called by the "SAVE" button from the Vitals form. It
	 * handles validating data and updating the various database beans. Both the
	 * MI vitals and generic vitals use this same action..
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		// AppLog.info("ProcessVitals action starting.");

		formErrors = new ArrayList();
		VitalsForm form = (VitalsForm) actionForm;
		ActionErrors errors = new ActionErrors();
		ActionForward actionForward;
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession) session
				.getAttribute(SessionValueKeys.DB_USER);
		DatabaseTransaction t = null;
		DbVitalsDeceased deceased = null;
		DbVitalsInformant informant = null;
		DbVitalsFirstCall firstCall = null;
		DbCase caseinfo = null;
		DbVitalsNextKin nextkin = null;
		DbVitalsSpouse spouse = null;
		DbVitalsMedical medical = null;
		DbArrangers arranger = null;
		DbServices srv = null;

		int vitalsid = form.getVitalsid();// = sessionUser.getCurrentCaseID();
		// AppLog.trace("ProcessVitals directive:"+
		// form.getDirective()+" for vitalsid:"+vitalsid);
		// Make sure we are logged in and have the right data stored in the
		// session
		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.login.invalid"));
		} else {
			vitalsid = SessionHelpers.getVitalsIdFromSession(request,
					sessionUser);
			if (vitalsid < 1) {
				// AppLog.warning("ProcessServices. Invalid VitalsID to process:"+vitalsid);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.ui.nocase"));
			}
		}
		// Check for any errors so far
		if (!errors.isEmpty()) {
			// AppLog.info("ProcessVitals Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}
		// Default forwarding to case status page
		actionForward = mapping.findForward("showCaseStatusGlobal");
		try {
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			if (form.getDirective().equals("addredisplay")
					|| form.getDirective().equals("changeredisplay")) {
				// redisplayForm(t, sessionUser, form, errors);
				session.removeAttribute("vitals");
				request.removeAttribute("vitals");
				session.setAttribute("vitals", form);
				request.setAttribute("vitals", form);
				return new ActionForward(mapping.getInput());
			}
			if (form.getDirective().equals("cancel")) {
				// go back to case status unless no vitals-id then show
				// introduciton
				if (vitalsid > 0) {
					actionForward = mapping.findForward("showCaseStatusGlobal");
				} else {
					actionForward = mapping.findForward("showCaseList");
				}
			} else if (form.getDirective().equals("help")) {
				SessionHelpers.setVitalsIdInRequest(request, vitalsid);
				return mapping.findForward("usingHelp");
			} else if (form.getDirective().equals("change")) {
				deceased = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
				informant = FdmsDb.getInstance()
						.getVitalsInformant(t, vitalsid);
				firstCall = FdmsDb.getInstance()
						.getVitalsFirstCall(t, vitalsid);
				caseinfo = FdmsDb.getInstance().getCase(t, vitalsid);
				nextkin = FdmsDb.getInstance().getVitalsNextKin(t, vitalsid);
				spouse = FdmsDb.getInstance().getVitalsSpouse(t, vitalsid);
				medical = FdmsDb.getInstance().getVitalsMedical(t, vitalsid);
				srv = FdmsDb.getInstance().getServices(t, vitalsid);
				// If this is a new case create a services record so that data
				// can be pushed forward
				if (srv == null) {
					// add to services table for this case
					srv = new DbServices();
					srv.setNew();
					t.addPersistent(srv);
					srv.setLSrvMainKey(vitalsid);
				}
				// AppLog.trace("ProcessVitals Updating DBMS objects. errors:"+errors.isEmpty());
				updateDatabase(deceased, informant, firstCall, caseinfo,
						nextkin, spouse, medical, srv, form, errors);
				updateSurvivors(sessionUser, vitalsid, form);
				updateMotherFatherInfo(sessionUser, vitalsid, form);

				/*****************************************************/

				// code added by Bhavesh for add mother and father information
				// in Survivors(Relative Tab)
				LocationOptionsManager locationOptionManager = new LocationOptionsManager();
				int locationOptionValue = locationOptionManager.getLocationOptionValue(sessionUser.getCompanyID(),sessionUser.getLocationId(),LocationOptionsDTO.LOCATION_OPTION_SHOW_FATHER_MOTHER_INFO_FROM_ON_RELATIVE_PAGE);
				
				
				
				boolean infoFlag = true;
				boolean addUpdateFlag = true;
				if (deceased.getFatherFirstName() != null
						&& deceased.getFatherFirstName().length() > 0) {

					if (informant.getFname() == null) {
						addUpdateFlag = true;
					} else if (informant.getFname() != null
							&& informant.getFname().length() > 0
							&& !informant.getRelated().equalsIgnoreCase(
									"Father")) {
						addUpdateFlag = true;

					} else {
						addUpdateFlag = false;
					}
					if (addUpdateFlag && locationOptionValue ==1) {
						DbSurvivor[] survivorarray = null;
						survivorarray = FdmsDb.getInstance().getSurvivorsForID(
								t, vitalsid, DbSurvivorPeer.SEQNUMBER);
						if (survivorarray != null) {
							for (DbSurvivor dbs : survivorarray) {
								if (dbs.getCGroupType().equals(
										DbSurvivor.VITALSTR)
										&& dbs.getCSurvivorRelated()
												.equalsIgnoreCase("Father")) {
									DbSurvivor.addUpdateSurvivor(t, vitalsid,
											dbs.getISeqKey(), "", form
													.getFatherFirstName(), form
													.getFatherMiddleName(),
											form.getFatherLastName(), "", "",
											"", "" + "" + "" + "" + "", "", "",
											"", "", "", "", "", "Father", "",
											"", "");
									infoFlag = false;
								}
							}
							if (infoFlag) {
								DbSurvivor fatherSurv = new DbSurvivor(
										vitalsid, "",
										form.getFatherFirstName(), form
												.getFatherMiddleName(), form
												.getFatherLastName(), "", "",
										"", "" + " " + "" + "" + "", "", "",
										"", "", "", "", "", "Father", "", "",
										DbSurvivor.VITALSTR, "");
								t.addPersistent(fatherSurv);
							}
						}
					}
				}
				addUpdateFlag = true;
				infoFlag = true;
				if (deceased.getMotherFirstName() != null
						&& deceased.getMotherFirstName().length() > 0) {

					if (informant.getFname() == null) {
						addUpdateFlag = true;
					} else if (informant.getFname() != null
							&& informant.getFname().length() > 0
							&& !informant.getRelated().equalsIgnoreCase(
									"Mother")) {
						addUpdateFlag = true;

					} else {
						addUpdateFlag = false;
					}
					if (addUpdateFlag && locationOptionValue ==1) {
						DbSurvivor[] survivorarray = null;
						survivorarray = FdmsDb.getInstance().getSurvivorsForID(
								t, vitalsid, DbSurvivorPeer.SEQNUMBER);
						if (survivorarray != null) {
							for (DbSurvivor dbs : survivorarray) {
								if (dbs.getCGroupType().equals(
										DbSurvivor.VITALSTR)
										&& dbs.getCSurvivorRelated()
												.equalsIgnoreCase("Mother")) {
									DbSurvivor.addUpdateSurvivor(t, vitalsid,
											dbs.getISeqKey(), "", form
													.getMotherFirstName(), form
													.getMotherMiddleName(),
											form.getMotherLastName(), "", "",
											"", "" + "" + "" + "" + "", "", "",
											"", "", "", "", "", "Mother", "",
											"", "");
									infoFlag = false;
								}
							}
							if (infoFlag) {
								DbSurvivor fatherSurv = new DbSurvivor(
										vitalsid, "",
										form.getMotherFirstName(), form
												.getMotherMiddleName(), form
												.getMotherLastName(), "", "",
										"", "" + " " + "" + "" + "", "", "",
										"", "", "", "", "", "Mother", "", "",
										DbSurvivor.VITALSTR, "");
								t.addPersistent(fatherSurv);
							}
						}
					}
				}

				/*****************************************************/

				// AppLog.trace("ProcessVitals Completed updating DBMS objects. errors:"+errors.isEmpty());
				// update arranger ID and emablamer ID
				int embalmerid = FormatNumber
						.parseInteger(form.getEmbalmerID());
				if (embalmerid > 0) {
					arranger = FdmsDb.getInstance().getArranger(t, embalmerid);
					if (arranger != null) {
						firstCall.setEmbalmerID(embalmerid);
						firstCall.setEmbalmerName(arranger.getName());
						firstCall.setEmbalmerLicense(arranger
								.getLicenseNumber());
					}
				} else {
					firstCall.setEmbalmerID(0);
					firstCall.setEmbalmerName("");
					firstCall.setEmbalmerLicense("");
				}

				// firstCall.setEmbalmerID(embalmerid);
				int arrangerid = FormatNumber
						.parseInteger(form.getDirectorID());
				// int licenseid =
				// FormatNumber.parseInteger(form.getLicenseNumber()); // field
				// only used in old MI vitals page
				// //AppLog.trace("ProcessVitals: ArrID:"+arrangerid+"  licenseID:"+licenseid);
				// if (licenseid > 0) {
				// arrangerid = licenseid;
				// }
				firstCall.setArrangerID(arrangerid);
				if (arrangerid > 0) {
					arranger = FdmsDb.getInstance().getArranger(t, arrangerid);
					if (arranger != null) {
						firstCall.setDirectorLicense(arranger
								.getLicenseNumber());
						firstCall.setArrangerName(arranger.getName());
					}
				}

				// if errors found, return to input screen without saving
				// anything
				if (!errors.isEmpty()) {
					// AppLog.info("ProcessVitals(2) Invoking forward mapping getInput() ");
					saveErrors(request, errors);
					request.setAttribute("formErrors", formErrors);
					request
							.setAttribute("STATE_OF_DEATH", form
									.getVitalsPage());

					return (new ActionForward(mapping.getInput()));
				}
				// AppLog.trace("ProcessVitals Updating persistents");
				t.save();
				// AppLog.trace("ProcessVitals Completed updating persistents");
				actionForward = mapping.findForward("showCaseStatusGlobal");
			} else {
				// AppLog.trace("ProcessVitals Invalid action: directive:"+
				// form.getDirective());
			}
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ProcessVitals do.Perform. "
					+ pe, pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in  ProcessVitals .doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}

		if (!errors.isEmpty()) {
			// AppLog.info("ProcessVitals(3) Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			request.setAttribute("STATE_OF_DEATH", form.getVitalsPage());
			return (new ActionForward(mapping.getInput()));
		}
		// remove session variables used in FirstCall page
		SessionHelpers.removeArrangerListFromSession(request);
		SessionHelpers.removeChapelListInSession(request);
		session.removeAttribute("vitals");

		// Since we are forwarding to another ACTION, need to go through this
		// exercise
		// AppLog.trace("ProcessVitals completed. Forwarding to next action.");
		String returnPath = actionForward.getPath();
		int periodpos = returnPath.indexOf(".do");
		returnPath = returnPath.substring(0, periodpos);

		ModuleConfig modconf = mapping.getModuleConfig();
		ActionMapping finalAC = (ActionMapping) modconf
				.findActionConfig(returnPath);

		try {
			Class.forName(finalAC.getType());
			// AppLog.trace("chaining to:"+finalAction.toString());
		} catch (Exception e) {
			// AppLog.warning("Could not find chained action: " +
			// e.getMessage());
			logger.error("Could not find chained action: ", e);
			return mapping.findForward("globalCancel");
		}

		SessionHelpers.setVitalsIdInRequest(request, vitalsid);

		// return finalAction.perform(finalMapping,form,request,response);
		if (errors.isEmpty()) {
			request.setAttribute("vitalsId", new Integer(vitalsid));
			logger.debug("Redirecting to : "
					+ mapping.findForward("redirect").getPath());
			return mapping.findForward("redirect");
		}

		logger.debug("Returning to : " + mapping.getInput());
		return new ActionForward(mapping.getInput());
	}

	/**
	 * Insert the method's description here. Creation date: (3/15/2002 2:20:30
	 * PM)
	 */
	private void updateDatabase(DbVitalsDeceased deceased,
			DbVitalsInformant informant, DbVitalsFirstCall firstCall,
			DbCase caseinfo, DbVitalsNextKin nextkin, DbVitalsSpouse spouse,
			DbVitalsMedical medical, DbServices srv,
			fdms.ui.struts.form.VitalsForm vitalform, ActionErrors errors) {

		deceased.setDecFName(vitalform.getDeceasedFirstName());
		deceased.setDecMName(vitalform.getDeceasedMiddleName());
		deceased.setDecLName(vitalform.getDeceasedLastName());
		deceased.setCountyOfBirth(vitalform.getCountyOfBirth());
		deceased.setIdentificationMarks(vitalform.getIdentificationMarks());
		deceased.setCountyOfIssue(vitalform.getCountyOfIssue());

		String deceasedSex = vitalform.getSex();

		if (deceasedSex == null
				|| (deceasedSex != null && deceasedSex.trim().length() == 0)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.vitals.invalidsex"));
			formErrors.add("sex");
		} else {
			deceased.setSex(deceasedSex);
		}

		try {
			deceased.setDateOfDeath(FormatDate.convertToDateMMDDYYYY(vitalform
					.getDateOfDeath()));
			caseinfo.setDeathDate(FormatDate.convertToDateYYYYMMDD(vitalform
					.getDateOfDeath()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.deathdate"));
			formErrors.add("dateOfDeath");
		}

		firstCall.setAgeYears(FormatNumber
				.parseInteger(vitalform.getAgeYears()));
		try {
			deceased.setDateOfBirth(FormatDate.convertToDateMMDDYYYY(vitalform
					.getDateOfBirth()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.birthdate"));
			formErrors.add("dateOfBirth");
		}
		try {
			if (vitalform.getIssueDate().compareToIgnoreCase("\\N") == 0) {
				vitalform.setIssueDate("");
			}
			deceased.setIssueDate(FormatDate.convertToDateMMDDYYYY(vitalform
					.getIssueDate()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.issueDate"));
			formErrors.add("issueDate");
		}
		firstCall.setCountyOfDeath(vitalform.getCountyOfDeath());
		firstCall.setAgeMonths(FormatNumber.parseInteger(vitalform
				.getAgeMonths()));
		firstCall.setAgeDays(FormatNumber.parseInteger(vitalform.getAgeDays()));
		firstCall.setAgeHours(FormatNumber
				.parseInteger(vitalform.getAgeHours()));
		firstCall.setAgeMinutes(FormatNumber.parseInteger(vitalform
				.getAgeMinutes()));
		firstCall.setPlaceDeath(vitalform.getLocationOfDeath());
		firstCall
				.setLocDeathLicenseNumber(vitalform.getLocDeathLicenseNumber());
		firstCall.setInpatientDOA(vitalform.getInpatient());

		try {
			firstCall.setAdmittedFacilityDateOfPlaceOfDeath(FormatDate
					.convertToDateMMDDYYYY(vitalform
							.getAdmittedFacilityDateOfPlaceOfDeath()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.admittedFacilityDate"));
			formErrors.add("admittedDate");
		}

		firstCall.setPlaceDeathCity(vitalform.getCityOfDeath());
		firstCall.setPlaceDeathInside(vitalform.getDeathInsideCity());
		deceased.setSSNo(FormatString.removeDashes(vitalform
				.getSocialSecurityNumber()));
		deceased.setOccupation(vitalform.getDeceasedOccupation());
		deceased.setKindBusiness(vitalform.getDeceasedKindBusinessOrIndustry());
		deceased.setEmployer(vitalform.getEmployer());
		deceased.setYearsAtOccupation(FormatNumber.parseInteger(vitalform
				.getYearsAtOccupation()));
		if (vitalform.getDeceasedState().length() == 2) {
			deceased.setDecResState(vitalform.getDeceasedState().toUpperCase());
		} else {
			deceased.setDecResState(vitalform.getDeceasedState());
		}

		deceased.setDecResCounty(vitalform.getDeceasedCounty());

		if (vitalform.getLocalityInsideCity()) {
			deceased.setDecResCityTWPBox("C");
		} else if (vitalform.isLocalityInsideVillage()) {
			deceased.setDecResCityTWPBox("V");
		} else if (vitalform.getLocalityInsideTwp()) {
			deceased.setDecResCityTWPBox("T");
		} else if (vitalform.getLocalityUnincorporated()) {
			deceased.setDecResCityTWPBox("I");
		} else if (vitalform.getLocalityUnknown()) {
			deceased.setDecResCityTWPBox("U");
		} else {
			deceased.setDecResCityTWPBox(" ");
		}

		if (deceased.getStateOfDeath().compareToIgnoreCase("WA") == 0
				|| deceased.getStateOfDeath().compareToIgnoreCase("NY") == 0) {
			deceased.setDecResCityTWPBox(vitalform
					.getLocalityInsideCityOption());
		}

		deceased.setLocOfDeathCVT(vitalform.getLocOfDeathCVT());
		deceased.setDecResCityTWP(vitalform.getDeceasedCity2());
		deceased.setDecResStreet(vitalform.getDeceasedStreet());
		deceased.setDecResZip(vitalform.getDeceasedZipCode().toUpperCase());
		deceased.setDecResCountry(vitalform.getDeceasedCountry());
		deceased.setDecResLengthOfTime(vitalform.getDeceasedResLengthTime());

		/*
		 * deceased.setBirthPlaceCSV(
		 * Translator.appendCityStateCountry(vitalform.getBirthplaceCity(),
		 * vitalform.getBirthplaceState(), vitalform.getBirthplaceCountry()) );
		 */
		/*
		 * deceased.setBirthPlace(
		 * Translator.createDisplayCityStateCountry(vitalform
		 * .getBirthplaceCity(), vitalform.getBirthplaceState(),
		 * vitalform.getBirthplaceCountry()) );
		 */
		// CsvTable.getField(vitalform.getBirthplaceState(),2)+","+CsvTable.getField(vitalform.getBirthplaceState(),1),
		deceased.setBirthPlaceCSV(Translator.appendCityStateCountry(vitalform
			    .getBirthplaceCity(), vitalform.getBirthplaceState(), vitalform
			    .getBirthplaceCountry()));
		deceased.setBirthPlace(Translator.createDisplayCityStateCountry(vitalform
			    .getBirthplaceCity(), vitalform.getBirthplaceState(), vitalform
			    .getBirthplaceCountry()));
		/*code commented by Bhavesh because of Santosh has print issue on Report on Release 3.0.1
		 * deceased.setBirthPlace(Translator.createDisplayCityStateCountry("",
			    vitalform.getBirthplaceState(), ""));
		 */
		deceased.setDecMarried(vitalform.getMaritalStatus());
		spouse.setFname(vitalform.getSurvivingSpouse1());
		spouse.setMname(vitalform.getSurvivingSpouse2());
		spouse.setLname(vitalform.getSurvivingSpouse3());
		spouse.setSurvivingSpouseStreet(vitalform.getSurvivingSpouseStreet());
		spouse.setSurvivingSpouseCity(vitalform.getSurvivingSpouseCity());
		if (vitalform.getSurvivingSpouseState().length() == 2) {
			spouse.setSurvivingSpouseState(vitalform.getSurvivingSpouseState()
					.toUpperCase());
		} else {
			spouse.setSurvivingSpouseState(vitalform.getSurvivingSpouseState());
		}

		spouse.setSurvivingSpouseZip(vitalform.getSurvivingSpouseZip()
				.toUpperCase());
		deceased.setVeteranYN(vitalform.getVeteran());
		deceased.setWasPeaceOfficerYN(vitalform.getWasPeaceOfficer());
		deceased.setAncestry(vitalform.getAncestry());
		deceased.setRace(vitalform.getRace());
		deceased.setTribalMember(vitalform.getTribalMember());
		deceased.setTribalName(vitalform.getTribalName());
		deceased.setElementaryEducation(vitalform
				.getDeceasedElementaryEducation());
		deceased.setCollegeEducation(vitalform.getDeceasedCollegeEducation());
		deceased.setFatherFirstName(vitalform.getFatherFirstName());
		deceased.setFatherMiddleName(vitalform.getFatherMiddleName());
		deceased.setFatherLastName(vitalform.getFatherLastName());
		deceased.setFatherMaidenName(vitalform.getFatherMaidenName());
		deceased.setMotherFirstName(vitalform.getMotherFirstName());
		deceased.setMotherMiddleName(vitalform.getMotherMiddleName());
		deceased.setMotherLastName(vitalform.getMotherLastName());
		deceased.setMotherMaidenName(vitalform.getMotherMaidenName());
		deceased.setBurialPermitNumber(vitalform.getBurialPermitNumber());
		informant.setFname(vitalform.getInformantFirstName());
		informant.setMname(vitalform.getInformantMiddleName());
		informant.setLname(vitalform.getInformantLastName());
		informant.setRelated(vitalform.getInformantRelation());
		informant.setStreet(vitalform.getInformantStreet());
		informant.setCity(vitalform.getInformantCity());
		if (vitalform.getInformantState().length() == 2) {
			informant.setState(vitalform.getInformantState().toUpperCase());
		} else {
			informant.setState(vitalform.getInformantState());
		}

		informant.setZip(vitalform.getInformantZipCode().toUpperCase());
		firstCall.setDispositionMethod(vitalform.getDisposition());
		firstCall.setDispositionPlace(vitalform.getDispositionPlace());
		firstCall.setDispositionCity(vitalform.getDispositionCity());
		if (vitalform.getDispositionState().length() == 2) {
			firstCall.setDispositionState(vitalform.getDispositionState()
					.toUpperCase());
		} else {
			firstCall.setDispositionState(vitalform.getDispositionState());
		}

		firstCall.setDispositionZip(vitalform.getDispositionZipCode()
				.toUpperCase());
		firstCall.setDispositionCounty(vitalform.getDispositionCounty());
		firstCall.setDispositionStreet(vitalform.getDispositionStreet());
		firstCall.setDispositionPlaceType(vitalform.getDispositionPlaceType());

		if (vitalform.getDispositionPlaceType().compareToIgnoreCase("Cemetery") == 0) {
			// if (srv.getCSrvCem() != null && srv.getCSrvCem().length() <= 0) {
			srv.setCSrvCem(vitalform.getDispositionPlace());
			srv.setCSrvCemCity(vitalform.getDispositionCity());
			if (vitalform.getDispositionState().length() == 2) {
				srv.setCSrvCemState(vitalform.getDispositionState()
						.toUpperCase());
			} else {
				srv.setCSrvCemState(vitalform.getDispositionState());
			}

			srv.setCSrvCemCounty(vitalform.getDispositionCounty());
			srv.setCSrvCemStreet(vitalform.getDispositionStreet());
			srv.setCSrvCemPhone(vitalform.getDispositionPhone());
			srv.setCSrvCemZip(vitalform.getDispositionZipCode().toUpperCase());

			srv.setCrematoryName("");
			srv.setCrematoryCity("");
			srv.setCrematoryState("");
			srv.setCrematoryCounty("");
			srv.setCrematoryStreet("");
			srv.setCrematoryZip("");
			srv.setCremationDateOfService("");
			// }

		} else if (vitalform.getDispositionPlaceType().compareToIgnoreCase(
				"Crematory") == 0) {
			// if (srv.getCrematoryName() != null &&
			// srv.getCrematoryName().length() <= 0) {
			srv.setCrematoryName(vitalform.getDispositionPlace());
			srv.setCrematoryCity(vitalform.getDispositionCity());
			if (vitalform.getDispositionState().length() == 2) {
				srv.setCrematoryState(vitalform.getDispositionState()
						.toUpperCase());
			} else {
				srv.setCrematoryState(vitalform.getDispositionState());
			}

			srv.setCrematoryCounty(vitalform.getDispositionCounty());
			srv.setCrematoryStreet(vitalform.getDispositionStreet());
			srv
					.setCrematoryZip(vitalform.getDispositionZipCode()
							.toUpperCase());
			deceased.setCrematoryName(vitalform.getDispositionPlace());
			try {
				srv
						.setCremationDateOfService(FormatDate
								.convertToDateMMDDYYYY(vitalform
										.getDateOfDisposition()));
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.ui.dispdate"));
				formErrors.add("dateOfDisposition");
			}

			srv.setCSrvCem("");
			srv.setCSrvCemCity("");
			srv.setCSrvCemState("");
			srv.setCSrvCemCounty("");
			srv.setCSrvCemStreet("");
			srv.setCSrvCemPhone("");
			srv.setCSrvCemZip("");

			// }

		} else {
			// srv.setCrematoryName("");
			// srv.setCrematoryCity("");
			// srv.setCrematoryState("");
			// srv.setCrematoryCounty("");
			// srv.setCrematoryStreet("");
			// srv.setCrematoryZip("");
			// srv.setCremationDateOfService("");
			//        	
			// srv.setCSrvCem("");
			// srv.setCSrvCemCity("");
			// srv.setCSrvCemState("");
			// srv.setCSrvCemCounty("");
			// srv.setCSrvCemStreet("");
			// srv.setCSrvCemPhone("");
			// srv.setCSrvCemZip("");

		}

		// srv.setCSrvCem(vitalform.getDispositionPlace());
		// srv.setCSrvCemStreet(vitalform.getDispositionStreet());
		// srv.setCSrvCemCity(vitalform.getDispositionCity());
		// srv.setCSrvCemState(vitalform.getDispositionState());
		// srv.setCSrvCemCounty(vitalform.getDispositionCounty());
		// srv.setCSrvCemPhone(vitalform.getDispositionPhone());
		// srv.setCSrvCemZip(vitalform.getDispositionZipCode());

		try {
			firstCall.setDispositionDate(FormatDate
					.convertToDateMMDDYYYY(vitalform.getDateOfDisposition()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.dispdate"));
			formErrors.add("dateOfDisposition");
		}
		try {
			firstCall.setDateOfNotifyToDirector(FormatDate
					.convertToDateMMDDYYYY(vitalform
							.getDateOfNotifyToDirector()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.dispdate"));
			formErrors.add("dateOfnotifyDirector");
		}
		firstCall.setTimeOfNotifyToDirectory(vitalform
				.getTimeOfNotifyToDirectory());

		firstCall.setFacilityLicenseNo(vitalform.getFacilityLicenseNumber());
		if (vitalform.getLicenseNumber() != null
				&& !vitalform.getLicenseNumber().trim().equals(""))
			firstCall.setDirectorLicense(vitalform.getLicenseNumber());
		firstCall.setFacilityName(vitalform.getFacilityName());
		firstCall.setFacilityStreet(vitalform.getFacilityStreet());
		String tmpStr = "";
		if (vitalform.getFacilityState().length() == 2) {
			tmpStr = vitalform.getFacilityState().toUpperCase();
		} else {
			tmpStr = vitalform.getFacilityState().toUpperCase();
		}
		firstCall.setFacilityCityStZip(Translator.appendCityState(Translator
				.appendCityState(vitalform.getFacilityCity(), tmpStr),
				vitalform.getFacilityZipCode().toUpperCase()));

		firstCall.setFacilityPhone(FormatString.formatPhone(vitalform
				.getFacilityPhone()));
		// AppLog.trace("firstCall Facility info = "
		// +vitalform.getFacilityName() +" " +vitalform.getFacilityStreet() +" "
		// +vitalform.getFacilityCity() +" " +vitalform.getFacilityState() +" "
		// +vitalform.getFacilityZipCode() +" " +vitalform.getFacilityPhone());
		medical.setCause1(vitalform.getCause1());
		medical.setCause2(vitalform.getCause2());
		medical.setCause3(vitalform.getCause3());
		medical.setCause4(vitalform.getCause4());
		medical.setInterval1(vitalform.getInterval1());
		medical.setInterval2(vitalform.getInterval2());
		medical.setInterval3(vitalform.getInterval3());
		medical.setInterval4(vitalform.getInterval4());
		medical.setOtherConditions(vitalform.getOtherCondition());
		medical.setAutopsy(vitalform.getAutopsy());
		medical.setBodyFoundMore24Hr(vitalform.getBodyFoundMore24Hr());
		medical.setNotificationOfExaminerRequired(vitalform
				.getNotificationOfExaminerRequired());
		medical.setHospiceStatus(vitalform.getHospiceStatus());
		medical.setFindings(vitalform.getFindings());
		medical.setActualPlaceDeath(vitalform.getActualPlaceDeath());
		medical.setReferredToME(vitalform.getReferredToMedicalExaminer());
		medical.setDoctorOccupation(vitalform.getDoctorOccupation());
		medical.setBiopsyYN(vitalform.getBiopsyYN());
		medical.setXopsyToFindCause(vitalform.getXopsyToFindCause());
		medical.setOperationType(vitalform.getOperationType());
		medical.setOperationPerformed(vitalform.getOperationPerformed());

		// code added by Bhavesh for ticket #5553 CA DC
		if (vitalform.getReferredToMedicalExaminer().equals("Yes")
				|| vitalform.getReferredToMedicalExaminer().equals("Y")) {
			medical.setReferralNumber(vitalform.getReferralNumber());
		} else {
			medical.setReferralNumber("");
		}

		int mebox = 0;
		if (vitalform.getMedicalExaminerBox1())
			mebox = 1;
		if (vitalform.getMedicalExaminerBox2())
			mebox += 2;
		medical.setMeCheckBox(mebox);

		if (vitalform.isNpCheckBox1()) {
			medical.setNpCheckBox(1);
		} else {
			medical.setNpCheckBox(0);
		}
		try {
			medical.setNpDateSigned(FormatDate.convertToDateMMDDYYYY(vitalform
					.getNpDateSigned()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.datesigned"));
			formErrors.add("NpDateSigned");
		}
		medical.setNpLicenseNumber(vitalform.getNpLicenseNumber());

		try {
			medical.setDateSignedCertifier(FormatDate
					.convertToDateMMDDYYYY(vitalform.getCertifierDateSigned()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.datesigned"));
			formErrors.add("certifiedDateSigned");
		}

		deceased.setTimeOfDeath(vitalform.getTimeOfDeath());
		deceased.setTimeOfDeathStatus(vitalform.getTimeOfDeathStatus());

		deceased.setTransferredLocationName(vitalform
				.getTransferredLocationName());
		deceased.setTransferredLocationAddr(vitalform
				.getTransferredLocationAddr());
		deceased.setTransferredLocationCity(vitalform
				.getTransferredLocationCity());
		deceased.setTransferredLocationState(vitalform
				.getTransferredLocationState());
		deceased.setTransferredLocationZip(vitalform
				.getTransferredLocationZip());
		deceased.setTransferredLocationPhone(FormatString.formatPhone(vitalform
				.getTransferredLocationPhone()));

		deceased.setHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld(vitalform
				.getHospitalNameOfBirthPlaceOfDeceasedUnder1YearOld());

		deceased.setEmployerAddr(vitalform.getEmployerAddr());

		deceased.setResidentWithinCityVillageLimit(vitalform
				.getResidentWithinCityVillageLimit());
		deceased.setResidentLocalityLimitName(vitalform
				.getResidentLocalityLimitName());

		deceased.setHospitalizedLast2Months(vitalform
				.getHospitalizedLast2Months());
		try {
			deceased
					.setPregnantDeliveryDate(FormatDate
							.convertToDateMMDDYYYY(vitalform
									.getPregnantDeliveryDate()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.pregnantDeliveryDate"));
			formErrors.add("pregnantDeliveryDate");
		}

		medical
				.setCoronerSPhysicianTitle(vitalform
						.getCoronerSPhysicianTitle());
		medical.setCoronerSPhysician(vitalform.getCoronerSPhysician());
		medical.setCoronerSPhysicianLicense(vitalform
				.getCoronerSPhysicianLicense());
		medical.setCoronerSPhysicianDateSign(vitalform
				.getCoronerSPhysicianDateSign());

		medical.setAttendingPhysicianTitle(vitalform
				.getAttendingPhysicianTitle());
		medical
				.setAttendingPhysicianAddr(vitalform
						.getAttendingPhysicianAddr());
		medical
				.setAttendingPhysicianCity(vitalform
						.getAttendingPhysicianCity());
		medical.setAttendingPhysicianState(vitalform
				.getAttendingPhysicianState());
		medical.setAttendingPhysicianPhone(vitalform
				.getAttendingPhysicianPhone());
		medical.setAttendingPhysicianZip(vitalform.getAttendingPhysicianZip());
		medical.setAttendingPhysicianLicense(vitalform
				.getAttendingPhysicianLicense());

		try {
			medical.setMeDateSigned(FormatDate.convertToDateMMDDYYYY(vitalform
					.getMedicalExaminerDateSigned()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.datesigned"));
			formErrors.add("medicalExaminerDateSigned");
		}

		try {
			String operationPerformed = vitalform.getOperationPerformed();

			if (operationPerformed != null && operationPerformed.equals("Y")) {
				medical.setOperationDate(FormatDate
						.convertToDateMMDDYYYY(vitalform.getOperationDate()));
			}

		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.operationdate"));
			formErrors.add("operationDate");
		}

		medical.setMeCaseNumber(vitalform.getMedicalExaminerCaseNumber());
		medical.setAttendingPhysician(vitalform.getAttendingPhysician());
		try {
			medical.setMeDateDeath(FormatDate.convertToDateMMDDYYYY(vitalform
					.getMedicalExaminerDeathDate()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.pronounceddeaddate"));
			formErrors.add("medicalExaminerDeathDate");
		}

		medical.setMeTimeDeath(vitalform.getTimeOfDeath2());
		medical.setDrName(vitalform.getCompletedCauseOfDeathDoctorName());
		medical.setDrStreet(vitalform.getCompletedCauseOfDeathDoctorStreet());
		medical.setDrPhone(FormatString.formatPhone(vitalform
				.getCompletedCauseOfDeathDoctorPhone()));
		medical.setDrFax(vitalform.getCompletedCauseOfDeathDoctorFax());
		medical.setDrCity(vitalform.getCompletedCauseOfDeathDoctorCity());
		if (vitalform.getCompletedCauseOfDeathDoctorState().length() == 2) {
			medical.setDrState(vitalform.getCompletedCauseOfDeathDoctorState()
					.toUpperCase());
		} else {
			medical.setDrState(vitalform.getCompletedCauseOfDeathDoctorState());
		}

		medical.setDrZip(vitalform.getCompletedCauseOfDeathDoctorZip()
				.toUpperCase());
		medical.setDrLicense(vitalform.getCompletedCauseOfDeathDoctorLicense());
		medical.setAccidentSuicide(vitalform
				.getMedicalExaminerAccidentSuicide());
		medical.setDrJusticeOfThePeace(vitalform
				.getCompletedCauseOfDeathDrJusticeOfThePeace());

		try {
			medical.setInjuryDate(FormatDate.convertToDateMMDDYYYY(vitalform
					.getMedicalExaminerInjuryDate()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.injurydate"));
			formErrors.add("medicalExaminerInjuryDate");
		}

		try {
			medical.setDateEmbalmed(FormatDate.convertToDateMMDDYYYY(vitalform
					.getDateEmbalmed()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.dateEmbalmed"));
			formErrors.add("dateEmbalmed");
		}

		medical.setInjuryTime(vitalform.getMedicalExaminerInjuryTime());
		medical.setInjuryDescription(vitalform
				.getMedicalExaminerInjuryDescription());
		medical.setInjuryAtWork(vitalform.getMedicalExaminerInjuryAtWork());
		medical.setInjuryPlace(vitalform.getMedicalExaminerInjuryPlace());
		medical.setInjuryTransportation(vitalform
				.getMedicalExaminerInjuryTransportation());
		medical.setInjuryAddress(vitalform.getMedicalExaminerInjuryStreet());
		tmpStr = "";
		if (vitalform.getMedicalExaminerInjuryState().length() == 2) {
			tmpStr = vitalform.getMedicalExaminerInjuryState().toUpperCase();
		} else {
			tmpStr = vitalform.getMedicalExaminerInjuryState();
		}
		medical.setInjuryCityState(Translator.appendCityState(vitalform
				.getMedicalExaminerInjuryCity(), tmpStr));
		medical.setInjuryCity(vitalform.getMedicalExaminerInjuryCity());
		medical.setInjuryState(tmpStr);
		medical.setInjuryZipCode(vitalform.getMedicalExaminerInjuryZipCode()
				.toUpperCase());
		medical.setInjuryAptNo(vitalform.getMedicalExaminerInjuryAptNo());
		medical.setInjuryCounty(vitalform.getMedicalExaminerInjuryCounty());

		// deceased.setDecMaiden(vitalform.getDeceasedMaiden());
		deceased.setDecMaiden(vitalform.getDeceasedMaiden());
		deceased.setDecmrmrs(vitalform.getDecPrefix());
		deceased.setSuffix(vitalform.getDecSuffix());
		deceased.setHispanicYN(vitalform.getHispanic());
		deceased.setAboriginal(vitalform.getAboriginal());
		deceased.setLivedOnReserve(vitalform.getLivedOnReserve());
		deceased.setAliasFirst(vitalform.getAliasFirstName());
		deceased.setAliasMiddle(vitalform.getAliasMiddleName());
		deceased.setAliasLast(vitalform.getAliasLastName());
		deceased.setAliasPrefix(vitalform.getAliasPrefix());
		deceased.setAliasSuffix(vitalform.getAliasSuffix());
		// deceased.setCrematoryName(vitalform.getCrematoryName());
		medical.setDr2Name(vitalform.getAuthorizingCoroner());

		if (vitalform.getPhysicianMD()) {
			medical.setDrTitle("MD");
		} else if (vitalform.getPhysicianME()) {
			medical.setDrTitle("ME");
		} else if (vitalform.getPhysicianDO()) {
			medical.setDrTitle("DO");
		} else if (vitalform.isPhysicianHO()) {
			medical.setDrTitle("HO");
		} else if (vitalform.getCertifierTitle() != null
				&& vitalform.getCertifierTitle().length() > 0) {
			medical.setDrTitle(vitalform.getCertifierTitle());
		}

		// Verify we have a valid from date
		java.util.Date checkDate = null;

		try {
			checkDate = FormatDate.convertToDate(vitalform.getPhysDateFrom());
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.vitals.attendfromdate"));
			formErrors.add("physDateFrom");
		}

		medical.setDrAttendDateFrom(checkDate);
		// Verify we have a valid to date
		try {
			checkDate = FormatDate.convertToDate(vitalform.getPhysDateTo());
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.vitals.attendtodate"));
			formErrors.add("physDateTo");
		}

		medical.setDrAttendDateTo(checkDate);
		// Verify we have a valid last date
		try {
			checkDate = FormatDate.convertToDate(vitalform.getPhysDateLast());
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.vitals.attendlastdate"));
			formErrors.add("physDateLast");
		}

		medical.setDrAttendDateLast(checkDate);
		medical.setPregnantAtDeath(vitalform.getPregnantAtDeath());
		medical.setPregnantLast12mos(vitalform.getPregnant12Months());

		String physViewed = vitalform.getPhysViewedYesNo();

		if (physViewed != null && physViewed.equals("Yes")) {
			physViewed = "Y";
		} else if (physViewed != null && physViewed.equals("No")) {
			physViewed = "N";
		}
		medical.setDoctorViewedBody(physViewed);

		// NE fields
		deceased.setMilitaryOrganizatn(vitalform.getVeteranWar());
		try {
			deceased.setWarFromDate(FormatDate.convertToDateMMDDYYYY(vitalform
					.getVeteranDateEntered()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.dateenteredservice"));
			formErrors.add("veteranDateEntered");
		}
		try {
			deceased.setWarToDate(FormatDate.convertToDateMMDDYYYY(vitalform
					.getVeteranDateSeparated()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.ui.dateseparatedservice"));
			formErrors.add("veteranDateSeperated");
		}

		// vitalform.setPregnancyPast3Months();
		medical.setTobacco(vitalform.getTobaccoUser());
		medical.setOrganDonationConsidered(vitalform.getOrganDonationGranted());
		medical.setOrganDonationGranted(vitalform.getOrganDonationConsidered());
		// IA field
		deceased.setCitizenship(vitalform.getCitizenship());

		// New Fields 2-5-04
		deceased.setDecEducation(vitalform.getDecEducation());
		if (vitalform.getLoctnOfDeathZip().length() == 2) {
			deceased.setLoctnOfDeathZip(vitalform.getLoctnOfDeathZip()
					.toUpperCase());
		} else {
			deceased.setLoctnOfDeathZip(vitalform.getLoctnOfDeathZip());
		}

		deceased.setTribalReservation(vitalform.getTribalReservation());
		deceased.setDecSpecifyHispanic(vitalform.getDecSpecifyHispanic());
		if (vitalform.getStateOfDeath().length() == 2) {
			deceased.setStateOfDeath(vitalform.getStateOfDeath().toUpperCase());
		} else {
			deceased.setStateOfDeath(vitalform.getStateOfDeath());
		}

		try {
			medical.setDateCertified(FormatDate.convertToDateMMDDYYYY(vitalform
					.getDateCertified()));
		} catch (Exception e) {
			// unable to parse date from String
		}
		deceased.setDeceasedPrefix(vitalform.getDeceasedPrefix());
		deceased.setSuffix(vitalform.getDeceasedSuffix());
		deceased.setAliasPrefix(vitalform.getDecAliasPrefix());
		deceased.setAliasSuffix(vitalform.getDecAliasSuffix());

		deceased.setFatherBirthCity(vitalform.getFatherBirthCity());
		deceased.setFatherBirthState(vitalform.getFatherBirthState());
		deceased.setFatherBirthCountry(vitalform.getFatherBirthCountry());
		deceased.setMotherBirthCity(vitalform.getMotherBirthCity());
		deceased.setMotherBirthState(vitalform.getMotherBirthState());
		deceased.setMotherBirthCountry(vitalform.getMotherBirthCountry());

		deceased.setMotherBirthday(vitalform.getMotherBirthday());
		deceased.setFatherBirthday(vitalform.getFatherBirthday());
		deceased.setMotherStreetAddress(vitalform.getMotherStreetAddress());

		if ((!"".equals(vitalform.getFatherAge()))
				&& vitalform.getFatherAge() != null) {
			try {
				deceased.setFatherAge(Integer
						.parseInt(vitalform.getFatherAge()));
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.int", "Father Age"));
				formErrors.add("fatherAge");
			}
		}

		if ((!"".equals(vitalform.getMotherAge()))
				&& vitalform.getMotherAge() != null) {
			try {
				deceased.setMotherAge(Integer
						.parseInt(vitalform.getMotherAge()));
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.int", "Mother Age"));
				formErrors.add("motherAge");
			}
		}

		deceased.setFatherForename(vitalform.getFatherForename());
		deceased.setFatherSurnameBirth(vitalform.getFatherSurnameBirth());
		deceased.setFatherOtherSurname(vitalform.getFatherOtherSurname());
		deceased.setFatherOccupation(vitalform.getFatherOccupation());
		deceased.setMotherForename(vitalform.getMotherForename());
		deceased.setMotherSurnameBirth(vitalform.getMotherSurnameBirth());
		deceased.setMotherOtherSurname(vitalform.getMotherOtherSurname());
		deceased.setMotherOccupation(vitalform.getMotherOccupation());
		deceased.setStillBirthDesc(vitalform.getStillBirthDesc());
		deceased.setPregnancyDuration(FormatNumber.parseInteger(vitalform
				.getPregnancyDuration()));

		deceased.setNumberChildren(FormatNumber.parseInteger(vitalform
				.getNumberChildren()));
		deceased.setNumberLiveborn(FormatNumber.parseInteger(vitalform
				.getNumberLiveborn()));
		deceased.setNumberStillborn(FormatNumber.parseInteger(vitalform
				.getNumberStillborn()));

		deceased.setChildWeight(vitalform.getChildWeight());
		deceased.setBirthType(vitalform.getBirthType());
		deceased.setBirthOrder(vitalform.getBirthOrder());
		deceased.setChildNameAgreed(vitalform.getChildNameAgreed());

		try {
			informant.setInformantDateSigned(FormatDate
					.convertToDateMMDDYYYY(vitalform.getInformantDateSigned()));
		} catch (Exception e) {
			// unable to parse date from String
		}
		try {
			firstCall.setFuneralDirectorDateSigned(FormatDate
					.convertToDateMMDDYYYY(vitalform
							.getFuneralDirectorDateSigned()));
		} catch (Exception e) {
			// uable to parse date from String
		}
		firstCall.setBurialPermitIssuer(vitalform.getBurialPermitIssuer());
		firstCall.setPlaceOfIssue(vitalform.getPlaceOfIssue());
		try {
			firstCall.setBurialIssuedDate(FormatDate
					.convertToDateMMDDYYYY(vitalform.getBurialIssuedDate()));
		} catch (Exception e) {
			// uable to parse date from String
		}
		firstCall.setRegistrationNumber(vitalform.getRegistrationNumber());
		firstCall.setRegCodeNumber(vitalform.getRegCodeNumber());
		firstCall.setArrangerName(vitalform.getArrangerName());
		try {
			firstCall.setRegistrarFileDate(FormatDate
					.convertToDateMMDDYYYY(vitalform.getRegistrarFileDate()));
		} catch (Exception e) {
			// uable to parse date from String
		}

		medical.setDiagnosisDeferred(vitalform.getDiagnosisDeferred());
		medical.setInjuryOccurred(vitalform.getInjuryOccurred());
		deceased.setCremationAuthorizer(vitalform.getCremationAuthorizer());

	}

	/**
	 * Insert the method's description here. Creation date: (10/31/2002 4:23:50
	 * PM)
	 * 
	 * @param t
	 *            com.aldorsolutions.webfdms.database.DatabaseTransaction
	 * @param vitalsid
	 *            int
	 * @param form
	 *            fdms.ui.struts.form.VitalsForm
	 */
	public void updateSurvivors(DbUserSession sessionUser, int vitalsId,
			VitalsForm form) {

		DatabaseTransaction y = null;
		DatabaseTransaction x = null;
		DbSurvivor[] dbSurvivors = null;
		DbSurvivor dbSurvivor = null;
		boolean deceasedFound = false;
		boolean informantFound = false;
		String checkData = new String();

		// Find the Deceased Survivor (oxymoron!) record by the sequence number
		// and update it.
		try {
			y = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance()
					.getSurvivorsForSequenceNo(y, vitalsId, 0);
			for (int i = 0; i < dbSurvivors.length; i++) {
				x = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(sessionUser);
				dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb
						.getInstance().getSurvivor(x, dbSurvivors[i].getId());
				checkData = form.getDeceasedStreet();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorAddr(checkData);
				}
				checkData = form.getDeceasedCity2();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorCity(checkData);
				}
				checkData = form.getDeceasedFirstName();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorFName(checkData);
				}
				checkData = form.getDeceasedLastName();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorLName(checkData);
				}
				if (form.getDeceasedState().length() == 2) {
					checkData = form.getDeceasedState().toUpperCase();
				} else {
					checkData = form.getDeceasedState();
				}

				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorState(checkData);
				}
				checkData = form.getDeceasedZipCode().toUpperCase();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorZip(checkData);
				}
				// could be a pre-need, but, don't need to alter relationship
				// here since
				// it is done in FirstCall and Preneed pages.
				// dbSurvivor.setCSurvivorRelated("Deceased");
				dbSurvivor.setISeqKey((short) 0);
				x.save();
				x.closeConnection();
				deceasedFound = true;
			}
		} catch (Exception e) {
			logger.error("Error : ", e);
		} finally {
			if (y != null)
				y.closeConnection();
		}

		// Find the Informant Survivor record by the sequence number and update
		// it.
		try {
			y = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance()
					.getSurvivorsForSequenceNo(y, vitalsId, 1);
			for (int i = 0; i < dbSurvivors.length; i++) {
				x = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(sessionUser);
				dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb
						.getInstance().getSurvivor(x, dbSurvivors[i].getId());
				checkData = form.getInformantStreet();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorAddr(checkData);
				}
				checkData = form.getInformantCity();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorCity(checkData);
				}
				checkData = form.getInformantFirstName();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorFName(checkData);
				}
				checkData = form.getInformantLastName();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorLName(checkData);
				}
				checkData = form.getInformantRelation();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorRelated(checkData);
				}
				if (form.getInformantState().length() == 2) {
					checkData = form.getInformantState().toUpperCase();
				} else {
					checkData = form.getInformantState();
				}

				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorState(checkData);
				}
				checkData = form.getInformantZipCode().toUpperCase();
				if (checkData != null && checkData.trim().length() > 0) {
					dbSurvivor.setCSurvivorZip(checkData);
				}
				dbSurvivor.setCSurvivorRelated("Informant");
				dbSurvivor.setISeqKey((short) 1);
				x.save();
				x.closeConnection();
				informantFound = true;
			}
		} catch (Exception e) {
			logger.error("Error : ", e);
		} finally {
			if (y != null)
				y.closeConnection();
		}

		if (informantFound && deceasedFound) {
			return;
		}

		// Find the Deceased and Informant Survivor records by the relationship
		// and update them.
		try {
			y = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance()
					.getSurvivorsForID(y, vitalsId);
			for (int i = 0; i < dbSurvivors.length; i++) {
				if ((!deceasedFound)
						&& dbSurvivors[i].getCSurvivorRelated().toUpperCase()
								.equals("DECEASED")) {
					x = (DatabaseTransaction) DatabaseTransaction
							.getTransaction(sessionUser);
					dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb
							.getInstance().getSurvivor(x,
									dbSurvivors[i].getId());
					checkData = form.getDeceasedStreet();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorAddr(checkData);
					}
					checkData = form.getDeceasedCity2();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorCity(checkData);
					}
					checkData = form.getDeceasedFirstName();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorFName(checkData);
					}
					checkData = form.getDeceasedLastName();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorLName(checkData);
					}
					if (form.getDeceasedState().length() == 2) {
						checkData = form.getDeceasedState().toUpperCase();
					} else {
						checkData = form.getDeceasedState();
					}

					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorState(checkData);
					}
					checkData = form.getDeceasedZipCode().toUpperCase();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorZip(checkData);
					}
					dbSurvivor.setCSurvivorRelated("Deceased");
					dbSurvivor.setISeqKey((short) 0);
					x.save();
					x.closeConnection();
					deceasedFound = true;
				}
				// Find the Informant Survivor record and update it.
				if ((!informantFound)
						&& dbSurvivors[i].getCSurvivorRelated().toUpperCase()
								.equals("INFORMANT")) {
					x = (DatabaseTransaction) DatabaseTransaction
							.getTransaction(sessionUser);
					dbSurvivor = com.aldorsolutions.webfdms.beans.FdmsDb
							.getInstance().getSurvivor(x,
									dbSurvivors[i].getId());
					checkData = form.getInformantStreet();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorAddr(checkData);
					}
					checkData = form.getInformantCity();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorCity(checkData);
					}
					checkData = form.getInformantFirstName();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorFName(checkData);
					}
					checkData = form.getInformantLastName();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorLName(checkData);
					}
					checkData = form.getInformantRelation();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorRelated(checkData);
					}
					if (form.getInformantState().length() == 2) {
						checkData = form.getInformantState().toUpperCase();
					} else {
						checkData = form.getInformantState();
					}

					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorState(checkData);
					}
					checkData = form.getInformantZipCode().toUpperCase();
					if (checkData != null && checkData.trim().length() > 0) {
						dbSurvivor.setCSurvivorZip(checkData);
					}
					dbSurvivor.setCSurvivorRelated("Informant");
					dbSurvivor.setISeqKey((short) 1);
					x.save();
					x.closeConnection();
					informantFound = true;
				}
				if (informantFound && deceasedFound) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error : ", e);
		} finally {
			if (y != null)
				y.closeConnection();
		}

		if (informantFound && deceasedFound) {
			return;
		}

		if (!deceasedFound && form.getDeceasedFirstName() != null
				&& form.getDeceasedFirstName().trim().length() > 0
				&& form.getDeceasedLastName() != null
				&& form.getDeceasedLastName().trim().length() > 0) {
			try {
				y = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(sessionUser);
				dbSurvivor = new DbSurvivor();
				dbSurvivor.setNew();
				dbSurvivor.setLSurvivorMainKey(vitalsId);
				dbSurvivor.setCSurvivorAddr(form.getDeceasedStreet());
				dbSurvivor.setCSurvivorCity(form.getDeceasedCity2());
				dbSurvivor.setCSurvivorFName(form.getDeceasedFirstName());
				dbSurvivor.setCSurvivorLName(form.getDeceasedLastName());
				if (form.getDeceasedState().length() == 2) {
					dbSurvivor.setCSurvivorState(form.getDeceasedState()
							.toUpperCase());
				} else {
					dbSurvivor.setCSurvivorState(form.getDeceasedState());
				}

				dbSurvivor.setCSurvivorZip(form.getDeceasedZipCode()
						.toUpperCase());
				dbSurvivor.setCSurvivorRelated("Deceased");
				dbSurvivor.setISeqKey((short) 0);
				// y.save();
				y.addPersistent(dbSurvivor);
			} catch (Exception e) {
				logger.error("Error : ", e);
			} finally {
				if (y != null)
					y.closeConnection();
			}
		}

		if (!informantFound && form.getInformantFirstName() != null
				&& form.getInformantFirstName().trim().length() > 0
				&& form.getInformantLastName() != null
				&& form.getInformantLastName().trim().length() > 0) {
			try {
				y = (DatabaseTransaction) DatabaseTransaction
						.getTransaction(sessionUser);
				dbSurvivor = new DbSurvivor();
				dbSurvivor.setNew();
				dbSurvivor.setLSurvivorMainKey(vitalsId);
				dbSurvivor.setCSurvivorAddr(form.getInformantStreet());
				dbSurvivor.setCSurvivorCity(form.getInformantCity());
				dbSurvivor.setCSurvivorFName(form.getInformantFirstName());
				dbSurvivor.setCSurvivorLName(form.getInformantLastName());
				dbSurvivor.setCSurvivorRelated(form.getInformantRelation());
				if (form.getInformantState().length() == 2) {
					dbSurvivor.setCSurvivorState(form.getInformantState()
							.toUpperCase());
				} else {
					dbSurvivor.setCSurvivorState(form.getInformantState());
				}

				dbSurvivor.setCSurvivorZip(form.getInformantZipCode()
						.toUpperCase());
				dbSurvivor.setCSurvivorRelated("Informant");
				dbSurvivor.setISeqKey((short) 1);
				y.addPersistent(dbSurvivor);
				y.save();
			} catch (Exception e) {
				logger.error("Error : ", e);
			} finally {
				if (y != null)
					y.closeConnection();
			}
		}
	}

	/**
	 * Method to add Mother & Father information on Survivors table. This will
	 * fetch data from Vitals tab & display information of Mother & Father on
	 * Relatives tab.
	 * 
	 * @param sessionUser
	 * @param vitalsId
	 * @param form
	 */
	private void updateMotherFatherInfo(DbUserSession sessionUser,
			int vitalsId, VitalsForm form) {

		DatabaseTransaction tx = null;
		DatabaseTransaction x = null;
		DbSurvivor[] dbSurvivors = null;
		DbSurvivor dbSurvivor = null;
		Set<DbSurvivor> relativesSet = new HashSet<DbSurvivor>();
		boolean flag = false;
		String checkData = new String();

		try {
			tx = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			dbSurvivors = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance()
					.getSurvivorsForID(tx, vitalsId, DbSurvivorPeer.SEQNUMBER);

			if (dbSurvivors != null) {
				dbSurvivor = new DbSurvivor();

				for (int i = 0; i < dbSurvivors.length; i++) {

					dbSurvivor = (DbSurvivor) dbSurvivors[i];

					if (dbSurvivor.getISeqKey() > 0
							&& dbSurvivor.getISeqKey() < 1000
							&& !dbSurvivor.getCSurvivorRelated()
									.equalsIgnoreCase("informant")) {
						if (dbSurvivor.getCSurvivorAddr().trim()
								.compareToIgnoreCase("null") == 0) {
							dbSurvivor.setCSurvivorAddr("");
						}
						relativesSet.add(dbSurvivor);
					}
					/*
					 * System.out.println("Relations.... " + );
					 * 
					 * if(dbSurvivor.getCSurvivorRelated().equalsIgnoreCase(
					 * DbSurvivor.FATHER)) {
					 * 
					 * }
					 */
				}
			}

			if (form.getFatherFirstName() != null
					|| form.getFatherLastName() != null) {

			}
		} catch (Exception e) {
			logger.error(
					"Exception at ProcessVitals:updateMotherFatherInfo : ", e);
		} finally {
			if (tx != null)
				tx.closeConnection();
		}
	}
}
