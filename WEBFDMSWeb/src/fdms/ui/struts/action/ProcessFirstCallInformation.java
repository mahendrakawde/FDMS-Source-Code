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

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbCasePeer;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsExecutor;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.DbVitalsSchedule;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.FirstCallInformationForm;

public class ProcessFirstCallInformation extends Action {

	private Logger logger = Logger.getLogger(ProcessFirstCallInformation.class.getName());

	private ArrayList formErrors;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		logger.debug("********************************************");
		logger.debug("*** Entering ProcessFirstCallInformation ***");

		FirstCallInformationForm form = (FirstCallInformationForm) actionForm;
		ActionErrors errors = new ActionErrors();
		formErrors = new ArrayList();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);

		logger.debug("ExecutorSame request value : " + request.getParameter("executorSame"));

		DatabaseTransaction t = null;
		FdmsDb fdmsdb = null;
		DbVitalsDeceased deceased = null;
		DbVitalsInformant informant = null;
		DbVitalsFirstCall firstCall = null;
		DbCase caseinfo = null;
		DbVitalsNextKin nextkin = null;
		DbPreneed preneed = null;
		DbVitalsSchedule sched = null;
		DbVitalsExecutor executor = null;
		DbServices srv = null;
		boolean addmode = false;
		int vitalsid = 0;
		String directive = form.getDirective();

		// Try to set the vitalsid from the form.
		try {
			vitalsid = FormatNumber.parseInteger(form.getVitalsId());
		} catch (Exception e) {
			vitalsid = 0;
		}

		if (directive.equals("cancel")) {
			// go back to case status unless no vitalsid then, show.
			// introduction.
			vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
			if (vitalsid > 0) {
				return mapping.findForward("showCaseStatusGlobal");
			} else {
				return mapping.findForward("ShowIntroductionGlobal");
			}
		}

		if (directive.equals("help")) {
			return mapping.findForward("usingHelp");
		}

		// From this point, we need to access the database
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			fdmsdb = FdmsDb.getInstance();

			if (directive.equals("redisplay")) {
				redisplayForm(t, sessionUser, form, errors);
				form.setDirective(" ");
				session.setAttribute("firstCallInformation", form);
				return new ActionForward(mapping.getInput());
			}

			validateForm(t, sessionUser, form, errors);
			// if errors found, return to input screen without saving anything
			if (!errors.isEmpty()) {
				// AppLog.info("ProcessFirstCall Invoking forward mapping
				// getInput() after validation.");
				saveErrors(request, errors);
				request.setAttribute("formErrors", formErrors);

				form.setDirective(" ");
				session.setAttribute("firstCallInformation", form);
				return new ActionForward(mapping.getInput());
			} else {
				// AppLog.trace("ProcessFirstCall past validation.");
			}

			// Get the DbVitalDeceased and DbVitalsFirstCall objects
			if (vitalsid == 0) {
				deceased = new DbVitalsDeceased();
				deceased.setNew();
				addmode = true;
			} else {
				deceased = fdmsdb.getVitalsDeceased(t, vitalsid);
			}

			// Set the data in the DbVitalsDeceased and DbVitalsFirstCall
			// records
			setVitalsDeceased(deceased, informant, form, errors);
			// if errors found, return to input screen without saving anything
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("formErrors", formErrors);
				session.setAttribute("firstCallInformation", form);
				form.setDirective(" ");
				return new ActionForward(mapping.getInput());
			} else {
				// AppLog.trace("ProcessFirstCall past setVitalsDeceased.");
			}

			if (vitalsid == 0) {
				t.addPersistent(deceased);
				t.save();
				t.closeConnection();
				t = null;
				
				vitalsid = deceased.getId();
				form.setVitalsId(String.valueOf(vitalsid));
				sessionUser.setCurrentCaseID(vitalsid);
				// Need another transaction to continue with add
				t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
				// increment next contract number if user did not change it
				// and assign to this case (new at need)
				// if user changed contract# then use the one they entered.
				if (form.getContractNumber().equals(form.getNextContractNumber())) {
					int newnextno = SessionHelpers.nextContractNumber(sessionUser.getDbLookup(), sessionUser.getRegion());
					form.setContractNumber(String.valueOf(newnextno));
					form.setNextContractNumber(form.getContractNumber());
					// need new transaction since save in above method ends that transaction
					t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
				}
				sched = fdmsdb.getVitalsSchedule(t, vitalsid);
				sched.setDefaultAtNeedCheckList(sessionUser.getRegion(), sessionUser.getDbLookup());
			} else {
				sched = fdmsdb.getVitalsSchedule(t, vitalsid);
			}

			// Now, lets update the other Vitals information
			SessionHelpers.setVitalsIdInRequest(request, vitalsid);
			firstCall = fdmsdb.getVitalsFirstCall(t, vitalsid);
			informant = fdmsdb.getVitalsInformant(t, vitalsid);
			caseinfo = fdmsdb.getCase(t, vitalsid);
			nextkin = fdmsdb.getVitalsNextKin(t, vitalsid);
			executor = fdmsdb.getVitalsExecutor(t, vitalsid);
			
			// determine whether active preneed or deceased
			preneed = fdmsdb.getPreneed(t, vitalsid);
			String relation = "Deceased";
			if (preneed.getStatus().equals(DbPreneed.ACTIVE)) {
				relation = "Preneed";
			}
			t.removePersistent(preneed);
			
			if (executor == null) {
				executor = new DbVitalsExecutor();
				executor.setNew();
			}
			setVitalsRest(t, sessionUser, deceased, firstCall, informant, caseinfo, nextkin, executor, form, errors);
			// if errors found, return to input screen without saving anything
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("formErrors", formErrors);
				session.setAttribute("firstCallInformation", form);
				form.setDirective(" ");
				return new ActionForward(mapping.getInput());
			} else {
				logger.debug("no errors exist in processfirstcall information");
			}

			

			// update special survivor information for searching deceased, informant, case#, contract#
			DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.DECEASED, deceased.getSalutation(), 
					deceased.getDecFName(), deceased.getDecMName(), deceased.getDecLName(), deceased.getSuffix(), 
					deceased.getMaidenName(), deceased.getFullName(),
					deceased.getDecResStreet() + " " + deceased.getDecAptNo(), "", deceased.getDecResMailCity(),
					deceased.getDecResState(), deceased.getDecResZip(), "", "", "", relation, "N", "","");
			
			if (informant.getLname().length()> 0 ){
			DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.INFORMANT, informant.getSalutation(), 
					informant.getFname(), informant.getMname(), informant.getLname(), "", "", "", informant.getStreet() + " "
					+ informant.getRoad2() + " " + informant.getRoad3(), "", informant.getCity(), informant.getState(),
					informant.getZip(), informant.getPhone(), "", informant.getInformantEmail(), "Informant", "Y", "",informant.getInformantPreferCommunicate());
			}

			if (deceased.getDecLName().length()>0){
			DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.CONTRACT, "", deceased.getDecLName(), "", 
					caseinfo.getContractCode(), "", "", "", "", "", "", "", "", "", "", "", deceased.getDecFName(), "N", "","");

			DbSurvivor.addUpdateSurvivor(t, vitalsid, DbSurvivor.CASECODE, "", deceased.getDecLName(), "", 
					caseinfo.getCaseCode(), "", "", "", "", "", "", "", "", "", "", "", deceased.getDecFName(), "N", "","");
			}
			// add informant and next-of-kin as normal survivors but only during add cycle
			if (addmode) {
				// AppLog.trace("Adding informant and NOK as survivors.");
				if (informant.getLname().length()>0){
				DbSurvivor infsurv = new DbSurvivor(vitalsid, informant.getSalutation(), informant.getFname(),
						informant.getMname(), informant.getLname(), "", "", "", informant.getStreet() + " "
								+ informant.getRoad2() + " " + informant.getRoad3(), "", informant.getCity(), 
								informant.getState(), informant.getZip(), informant.getPhone(), "", 
								informant.getInformantEmail(), informant.getRelated(), "Y", "",DbSurvivor.INFORMANTSTR,informant.getInformantPreferCommunicate());

				t.addPersistent(infsurv);
				}
				
				if (!form.getNextKinSame()) {
					if (nextkin.getLastname().length()>0){
					DbSurvivor noksurv = new DbSurvivor(vitalsid, nextkin.getSalutation(), nextkin.getFirstname(), nextkin.getMiddleName(),
							nextkin.getLastname(), "", "", "", nextkin.getStreet() + nextkin.getRoad2() + " "
									+ nextkin.getRoad3(), "", nextkin.getCity(), nextkin.getState(), nextkin.getZip(),
							nextkin.getPhone(), "", nextkin.getEmail(), nextkin.getRelation(), "Y", "",DbSurvivor.NEXTOFKINSTR,nextkin.getNextKinPreferCommunicate());

					t.addPersistent(noksurv);
					}
				}
			}
			else { //set next of kin and informant relation.
				
				DbSurvivor[] survivorarray = null;
				survivorarray = fdmsdb.getSurvivorsForID(t, vitalsid, DbSurvivorPeer.SEQNUMBER);
				boolean noInfo = true;
				if (survivorarray != null) {
					
				//	if(!form.getDeceasedSame()){ //update relative
						if (informant.getLname().length()>0){
							for (int i = 0; i < survivorarray.length; i++) {
								DbSurvivor dbs = (DbSurvivor) survivorarray[i];
//								if (informant.getFname().compareToIgnoreCase(dbs.getCSurvivorFName())==0 &&
//									informant.getLname().compareToIgnoreCase(dbs.getCSurvivorLName())==0 &&
//									informant.getMname().compareToIgnoreCase(dbs.getCSurvivorMName())==0 &&
//									dbs.getISeqKey() != DbSurvivor.INFORMANT) {
									if (dbs.getISeqKey() != DbSurvivor.INFORMANT && dbs.getCGroupType().compareToIgnoreCase(DbSurvivor.INFORMANTSTR)== 0) {	
									DbSurvivor.addUpdateSurvivor(t, vitalsid, dbs.getISeqKey(), informant.getSalutation(), 
											informant.getFname(), informant.getMname(), informant.getLname(), "", "", "", informant.getStreet() + " "
											+ informant.getRoad2() + " " + informant.getRoad3(), "", informant.getCity(), informant.getState(),
											informant.getZip(), informant.getPhone(), "", informant.getInformantEmail(), informant.getRelated(), "Y", "",informant.getInformantPreferCommunicate());
									noInfo = false;
								}
							}
							if(noInfo){
								DbSurvivor infsurv = new DbSurvivor(vitalsid, informant.getSalutation(), informant.getFname(),
										informant.getMname(), informant.getLname(), "", "", "", informant.getStreet() + " "
												+ informant.getRoad2() + " " + informant.getRoad3(), "", informant.getCity(), 
												informant.getState(), informant.getZip(), informant.getPhone(), "", 
												informant.getInformantEmail(), informant.getRelated(), "Y", "",DbSurvivor.INFORMANTSTR,informant.getInformantPreferCommunicate());
								
								t.addPersistent(infsurv);
							}
							
						}
				//	}
					
					noInfo = true;
					// This will update NK information every time when CallInfo will be saved.
					//if(!form.getNextKinSame()){ //update nextofkin
						if (nextkin.getLastname().length()>0){
							for (int i = 0; i < survivorarray.length; i++) {
								DbSurvivor dbs = (DbSurvivor) survivorarray[i];
//								if (nextkin.getFirstname().compareToIgnoreCase(dbs.getCSurvivorFName())==0 &&
//									nextkin.getLastname().compareToIgnoreCase(dbs.getCSurvivorLName())==0) {
									if (dbs.getCGroupType().compareToIgnoreCase("NK") == 0) {	
									DbSurvivor.addUpdateSurvivor(t, vitalsid, dbs.getISeqKey(), nextkin.getSalutation(), nextkin.getFirstname(), 
											nextkin.getMiddleName(), nextkin.getLastname(), "", "", "", nextkin.getStreet() + " "
											+ nextkin.getRoad2() + " " + nextkin.getRoad3(), "", nextkin.getCity(), nextkin.getState(),
											nextkin.getZip(), nextkin.getPhone(), "", nextkin.getEmail(), nextkin.getRelation(), "Y", "",nextkin.getNextKinPreferCommunicate());
									noInfo = false;
								}
							}
							
							if(noInfo){
								DbSurvivor noksurv = new DbSurvivor(vitalsid, nextkin.getSalutation(), nextkin.getFirstname(), nextkin.getMiddleName(),
										nextkin.getLastname(), "", "", "", nextkin.getStreet() + " " +nextkin.getRoad2() + " "
												+ nextkin.getRoad3(), "", nextkin.getCity(), nextkin.getState(), nextkin.getZip(),
										nextkin.getPhone(), "", nextkin.getEmail(), nextkin.getRelation(), "Y", "",DbSurvivor.NEXTOFKINSTR,nextkin.getNextKinPreferCommunicate());
								t.addPersistent(noksurv);
							}
							
						}
					//}
				}
			}
			
			
			srv = FdmsDb.getInstance().getServices(t,vitalsid);
			if(srv != null) srv.setCSrvDayOfWeek(FormatDate.getDayOfWeek(form.getServiceDate()));
			if (executor != null)
				t.addPersistent(executor);

			// Final commit and cleanup
			
			t.save();

		} catch (PersistenceException pe) {
			logger.error("PersistenceException in doPerform() : ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Error in doPerform() : ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				try {
					t.closeConnection();
					t = null;
				} catch (Exception e) {
					logger.error("Error in closeConnection() : ", e);
				}
			}
		}

		if (!errors.isEmpty()) {
			// AppLog.info("ProcessFirstCallInformation Invoking forward mapping
			// getInput().");
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			form.setDirective(" ");
			session.setAttribute("firstCallInformation", form);
			return (new ActionForward(mapping.getInput()));
		}
		
		// now we are going to increment the contract code if no error at all
		
		if (errors.isEmpty()){
			setNextContractNoOnLocale(sessionUser);
		}

		// remove session variables used in FirstCall page
		SessionHelpers.removeArrangerListFromSession(request);
		SessionHelpers.removeChapelListInSession(request);
		session.removeAttribute("firstCallInformation");
		SessionHelpers.setVitalsIdInRequest(request, vitalsid);

		if (errors.isEmpty()) {
			request.setAttribute("redirect", Boolean.TRUE);
			request.setAttribute("vitalsId", new Integer(vitalsid));
		}
		return new ActionForward(mapping.getInput());
	}

	private void setNextContractNoOnLocale(DbUserSession user) {
		// TODO Auto-generated method stub
		
		LocaleDTO dbLocale = new LocaleDTO();
		dbLocale = FdmsDb.getInstance().getLocale(user.getDbLookup(), user.getRegion());
		int nextContractNo = dbLocale.getNextContractNo();
		
		// check if the vitalstats has been using this contract no. if it is increase it until no one use.
		boolean result = false;
		UserDAO userDao = new UserDAO();
		String contractCode = String.valueOf(nextContractNo);
		// we loop until we don't have a match in the vitalstats
		while (userDao.verifyContractNoUesed( contractCode,user.getRegion(),user)) {
			contractCode = String.valueOf(++nextContractNo);
		}
		
		dbLocale.setNextContractNo(Integer.parseInt(contractCode));
		LocaleDAO localeDAO = new LocaleDAO();
		try {
			localeDAO.updateLocale(dbLocale, user.getDbLookup());
		} catch (Exception e) {
        	
        }
		
	}

	private void redisplayForm(DatabaseTransaction t, DbUserSession sessionUser,
			fdms.ui.struts.form.FirstCallInformationForm form, ActionErrors errors) {

		// Don't retrieve data if Facility information has already been
		// supplied.
		if (form.getFacilityName() != null && form.getFacilityName().trim().length() > 0) {
			return;
		}

		DbLocation dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocation(t, Integer.parseInt(form.getChapel()));

//		form.setFacilityName(dbLocation.getName());
//		form.setFacilityStreet(dbLocation.getAddr1());
//		form.setFacilityCity(dbLocation.getCity());
//		form.setFacilityState(dbLocation.getState());
//		form.setFacilityZip(dbLocation.getZip());
//		form.setFacilityLicense(dbLocation.getLicenseNumber());
//		form.setFacilityPhone(FormatString.formatPhone(dbLocation.getPhone()));

		form.setChapelName(dbLocation.getName());
		form.setChapelStreet(dbLocation.getAddr1());
		form.setChapelCity(dbLocation.getCity());
		form.setChapelState(dbLocation.getState());
		form.setChapelZip(dbLocation.getZip());
		form.setChapelLicense(dbLocation.getLicenseNumber());
		form.setChapelPhone(FormatString.formatPhone(dbLocation.getPhone()));
		
		
		return;

	}

	/**
	 * Set the DbVitals and DbCase values from the FirstCallInformation form.
	 * Creation date: (10/25/2002 11:00:39 AM)
	 * 
	 * @param deceased
	 *            com.aldorsolutions.webfdms.beans.DbVitalsDeceased
	 * @param form
	 *            fdms.ui.struts.form.FirstCallInformationForm
	 * @param errors
	 *            org.apache.struts.action.ActionErrors
	 */
	public void setVitalsDeceased(DbVitalsDeceased deceased, DbVitalsInformant informant,
			FirstCallInformationForm form, ActionErrors errors) {
		try {
			deceased.setDecmrmrs(form.getPrefix());
			deceased.setDecFName(form.getFirstName());
			deceased.setDecMName(form.getMiddleName());
			deceased.setDecLName(form.getLastName());
			deceased.setSuffix(form.getSuffix());
			deceased.setDecFullName(form.getMemorialName());
			// this should not be in here since the customername = ladger name (accouting) 
			//deceased.setCustomerName(form.getMemorialName());  
			deceased.setDecMaiden(form.getMaidenName());
			if (deceased.getAliasLast() == null || deceased.getAliasLast().length() <= 0 ){
					deceased.setAliasLast(form.getMaidenName());
			}
			deceased.setDateOfBirth(FormatDate.convertToDateMMDDYYYY(form.getBirthDate()));
			deceased.setDateOfDeath(FormatDate.convertToDateMMDDYYYY(form.getDeathDate()));
			deceased.setDateOfBurial(FormatDate.convertToDateMMDDYYYY(form.getServiceDate()));
			deceased.setServiceDateKey(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
			
			if ( form.getPlaceDeathState().length() == 2) {
				deceased.setStateOfDeath( form.getPlaceDeathState().toUpperCase() );
			}
			else {
				deceased.setStateOfDeath( form.getPlaceDeathState() ); 	
			}
			
			
						
			// if the deceased's residence is the same as the informant's then
			// update the deceased's address
			if (form.getDeceasedSame()) {
				deceased.setDeceasedSame("Y");
				deceased.setDecResStreet(form.getInformantStreet() + " " + form.getInformantStreet2() + " "
						+ form.getInformantStreet3());
				deceased.setDecResCityTWP(form.getInformantCity());
				if ( form.getInformantState().length() == 2) {
					deceased.setDecResState(form.getInformantState().toUpperCase());
				}
				else {
					deceased.setDecResState(form.getInformantState());   	
				}
				
				
				deceased.setDecResZip(form.getInformantZip().toUpperCase());
				deceased.setDecResPhone(form.getInformantPhone());
			} else {
				deceased.setDeceasedSame("N");
			}

		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.setData"));
		}

		return;

	}

	/**
	 * Set the DbVitals and DbCase values from the FirstCallInformation form.
	 * Creation date: (10/25/2002 11:00:39 AM)
	 * 
	 * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
	 * @param firstCall com.aldorsolutions.webfdms.beans.DbVitalsFirstCall
	 * @param informant com.aldorsolutions.webfdms.beans.DbVitalsInformant
	 * @param caseinfo com.aldorsolutions.webfdms.beans.DbCase
	 * @param nextkin com.aldorsolutions.webfdms.beans.DbVitalsNextKin
	 * @param nextkin com.aldorsolutions.webfdms.beans.DbVitalsExecutor
	 * @param form fdms.ui.struts.form.FirstCallInformationForm
	 * @param errors org.apache.struts.action.ActionErrors
	 */
	public void setVitalsRest(DatabaseTransaction t, DbUserSession sessionUser, DbVitalsDeceased deceased,
			DbVitalsFirstCall firstCall, DbVitalsInformant informant, DbCase caseinfo, DbVitalsNextKin nextkin,
			DbVitalsExecutor executor, FirstCallInformationForm form, ActionErrors errors) {

		boolean continueDuplicate = true;

		try {

			if (form.isExecutorSame()) {
				logger.debug("Executor is the same");

				executor.setIsExecutorSame("Y");
				executor.setExecutorPersonId(form.getExecutorPersonId());
				executor.setExecutorFirstname(form.getInformantFirst());
				executor.setExecutorLastname(form.getInformantLast());
				executor.setExecutorStreet(form.getInformantStreet());
				executor.setExecutorStreet2(form.getInformantStreet2());
				executor.setExecutorStreet3(form.getInformantStreet3());
				executor.setExecutorCity(form.getInformantCity());
				
				if ( form.getInformantState().length() == 2) {
					executor.setExecutorState(form.getInformantState().toUpperCase());
				}
				else {
					executor.setExecutorState(form.getInformantState());   	
				}
				
				
				executor.setExecutorZip(form.getInformantZip().toUpperCase());
				executor.setExecutorPhone(FormatString.formatPhone(form.getInformantPhone()));
				executor.setExecutorCellPhone(FormatString.formatPhone(form.getInformantCellPhone()));
				executor.setExecutorRelation(form.getInformantRelation());
				executor.setExecutorEmail(form.getInformantEmail());

				// sets the vitals id for DbExecutor so I can insert values into database
				Persistent p = (Persistent) executor;
				p.setId(new Integer(form.getVitalsId()).intValue());

			} else {
				logger.debug("Executor is not the same");

				executor.setIsExecutorSame("N");
				executor.setExecutorPersonId(form.getExecutorPersonId());
				executor.setExecutorFirstname(form.getExecutorFirstName());
				executor.setExecutorLastname(form.getExecutorLastName());
				executor.setExecutorStreet(form.getExecutorStreet());
				executor.setExecutorStreet2(form.getExecutorStreet2());
				executor.setExecutorStreet3(form.getExecutorStreet3());
				executor.setExecutorCity(form.getExecutorCity());
				
				if ( form.getExecutorState().length() == 2) {
					executor.setExecutorState(form.getExecutorState().toUpperCase());
				}
				else {
					executor.setExecutorState(form.getExecutorState());  	
				}
				
				
				executor.setExecutorZip(form.getExecutorZip().toUpperCase());
				executor.setExecutorPhone(FormatString.formatPhone(form.getExecutorPhone()));
				executor.setExecutorCellPhone(FormatString.formatPhone(form.getExecutorCellPhone()));
				executor.setExecutorRelation(form.getExecutorRelation());
				executor.setExecutorEmail(form.getExecutorEmail());

				// sets the vitals id for DbExecutor so I can insert values into database
				Persistent p = (Persistent) executor;
				p.setId(new Integer(form.getVitalsId()).intValue());

			}

			firstCall.setArrangeDate(FormatDate.convertToDateMMDDYYYY(form.getArrangeDate()));
			firstCall.setDispositionDate(FormatDate.convertToDateMMDDYYYY(form.getDispDate()));
			firstCall.setOriginalPnDate(FormatDate.convertToDateMMDDYYYY(form.getPreneedDate()));
			firstCall.setArrangeTime(form.getTime());
			firstCall.setPlaceDeathAddr(form.getLocationDeceased());
			firstCall.setLocDeathLicenseNumber(form.getLocDeathLicenseNumber());
			firstCall.setSource(form.getSource());
			firstCall.setEmbalmingReason(form.getEmbalming());
			firstCall.setPlaceDeath(form.getPlaceDeath());
			firstCall.setPlaceDeathCity(form.getPlaceDeathCity());
			if ( form.getPlaceDeathState().length() == 2) {
				firstCall.setPlaceDeathState(form.getPlaceDeathState().toUpperCase());
			}
			else {
				firstCall.setPlaceDeathState(form.getPlaceDeathState());    	
			}
			
			firstCall.setPlaceDeathZip(form.getPlaceDeathZip().toUpperCase());
			firstCall.setAgeYears(FormatNumber.parseInteger(form.getAge()));

			int chapelId = 0;
			if (form.getChapel() != null) {
				try {
					chapelId = Integer.parseInt(form.getChapel());
				} catch (NumberFormatException e) {
					// unable to parse int from String
				}
			}

			logger.debug("ChapelId : " + chapelId);

//			DbLocation dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocation(t, chapelId);
//			if (dbLocation != null) {
//				firstCall.setFacilityName(dbLocation.getName());
//				firstCall.setFacilityStreet(dbLocation.getAddr1());
//				firstCall.setFacilityCityStZip(dbLocation.getCity() + ", " + dbLocation.getState() + ", "
//						+ dbLocation.getZip());
//				firstCall.setFacilityLicenseNo(dbLocation.getLicenseNumber());
//				firstCall.setFacilityPhone(FormatString.formatPhone(dbLocation.getPhone()));
//
//				caseinfo.setChapelLocation(dbLocation.getName());
//				caseinfo.setChapelNumber(dbLocation.getId());
//				caseinfo.setLocale(dbLocation.getLocaleNumber());
//			} else {
//				firstCall.setFacilityName(form.getFacilityName());
//				firstCall.setFacilityStreet(form.getFacilityStreet());
//				firstCall.setFacilityCityStZip(form.getFacilityCity() + ", " + form.getFacilityState() + ", "
//						+ form.getFacilityZip());
//				firstCall.setFacilityPhone(FormatString.formatPhone(form.getFacilityPhone()));
//				firstCall.setFacilityLicenseNo(form.getFacilityLicense());
//			}
			
			DbLocation dbLocation = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getLocation(t, chapelId);
			
			caseinfo.setChapelLocation(dbLocation.getName());
			caseinfo.setChapelNumber(dbLocation.getId());
			caseinfo.setLocale(dbLocation.getLocaleNumber());
			if (form.isFacilitySame()) {
				firstCall.setFacilitySameAsChapel("Y");
				firstCall.setFacilityName(dbLocation.getName());
				firstCall.setFacilityStreet(dbLocation.getAddr1());
				firstCall.setFacilityCityStZip(dbLocation.getCity() + ", " + dbLocation.getState() + ", "
						+ dbLocation.getZip());
				firstCall.setFacilityLicenseNo(dbLocation.getLicenseNumber());
				firstCall.setFacilityPhone(FormatString.formatPhone(dbLocation.getPhone()));


			} else {
				firstCall.setFacilitySameAsChapel("N");
				firstCall.setFacilityName(form.getFacilityName());
				firstCall.setFacilityStreet(form.getFacilityStreet());
				String tmpStr = "";
				if ( form.getFacilityState().length() == 2) {
					tmpStr = form.getFacilityState().toUpperCase();
				}
				else {
					tmpStr = form.getFacilityState();   	
				}
				
				firstCall.setFacilityCityStZip(form.getFacilityCity() + ", " + tmpStr + ", "
						+ form.getFacilityZip().toUpperCase());
				firstCall.setFacilityPhone(FormatString.formatPhone(form.getFacilityPhone()));
				firstCall.setFacilityLicenseNo(form.getFacilityLicense());
				
			}

			firstCall.setShippingInfo(form.getShippingInfo());
			firstCall.setCallInfoNote(form.getCallInfoNote());

			DbArrangers dbArranger = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance()
					.getArranger(t, Integer.parseInt(form.getDirector()));
			firstCall.setArrangerName(dbArranger.getName());
			firstCall.setArrangerID(dbArranger.getId());
			firstCall.setDirectorLicense(dbArranger.getLicenseNumber());

			informant.setSalutation(form.getInformantSalutation());
			informant.setFname(form.getInformantFirst());
			informant.setMname(form.getInformantMiddle());
			informant.setLname(form.getInformantLast());
			informant.setStreet(form.getInformantStreet());
			informant.setRoad2(form.getInformantStreet2());
			informant.setRoad3(form.getInformantStreet3());
			informant.setCity(form.getInformantCity());
			if ( form.getInformantState().length() == 2) {
				informant.setState(form.getInformantState().toUpperCase());
			}
			else {
				informant.setState(form.getInformantState());   	
			}
			
			informant.setZip(form.getInformantZip().toUpperCase());
			informant.setPhone(FormatString.formatPhone(form.getInformantPhone()));
			informant.setCellPhone(FormatString.formatPhone(form.getInformantCellPhone()));
			informant.setRelated(form.getInformantRelation());
			informant.setInformantEmail(form.getInformantEmail());
			informant.setInformantPreferCommunicate(form.getInformantPreferCommunicate());
			
			
			if ( form.isInformantBillToParty() ) {
				informant.setInformantIsBillToParty("Y");
			} else {
				informant.setInformantIsBillToParty("N");
			}
			
			if (form.getNextKinSame()) {
				nextkin.setSameAsInformant("Y");
				nextkin.setSalutation(form.getInformantSalutation());
				nextkin.setFirstname(form.getInformantFirst());
				nextkin.setMiddleName(form.getInformantMiddle());
				nextkin.setLastname(form.getInformantLast());
				nextkin.setStreet(form.getInformantStreet());
				nextkin.setRoad2(form.getInformantStreet2());
				nextkin.setRoad3(form.getInformantStreet3());
				nextkin.setCity(form.getInformantCity());
				if ( form.getInformantState().length() == 2) {
					nextkin.setState(form.getInformantState().toUpperCase());
				}
				else {
					nextkin.setState(form.getInformantState());   	
				}
				
				nextkin.setZip(form.getInformantZip().toUpperCase());
				nextkin.setPhone(FormatString.formatPhone(form.getInformantPhone()));
				nextkin.setCellPhone(FormatString.formatPhone(form.getInformantCellPhone()));
				nextkin.setRelation(form.getInformantRelation());
				nextkin.setEmail(form.getInformantEmail());
				nextkin.setNextKinPreferCommunicate(form.getInformantPreferCommunicate());
				
			} else {
				nextkin.setSameAsInformant("N");
				nextkin.setSalutation(form.getNextKinSalutation());
				nextkin.setFirstname(form.getNextKinFirst());
				nextkin.setMiddleName(form.getNextKinMiddle());
				nextkin.setLastname(form.getNextKinLast());
				nextkin.setStreet(form.getNextKinStreet());
				nextkin.setRoad2(form.getNextKinStreet2());
				nextkin.setRoad3(form.getNextKinStreet3());
				nextkin.setCity(form.getNextKinCity());
				if ( form.getNextKinState().length() == 2) {
					nextkin.setState(form.getNextKinState().toUpperCase());
				}
				else {
					nextkin.setState(form.getNextKinState());   	
				}
				
				nextkin.setZip(form.getNextKinZip().toUpperCase());
				nextkin.setPhone(FormatString.formatPhone(form.getNextKinPhone()));
				nextkin.setCellPhone(FormatString.formatPhone(form.getNextKinCellPhone()));
				nextkin.setRelation(form.getNextKinRelation());
				nextkin.setEmail(form.getNextKinEmail());
				nextkin.setNextKinPreferCommunicate(form.getNextKinPreferCommunicate());
			} 

			caseinfo.setDeathDate(FormatDate.convertToDateYYYYMMDD(form.getDeathDate()));
			caseinfo.setServiceDate(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
			
			// 
			// caseinfo.setSaleDate(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
			if (caseinfo.getSaleDate() == null || caseinfo.getSaleDate().trim().length()== 0){
				LocaleDTO alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), dbLocation.getLocaleNumber());
				if (alocale.getDefaultSaleDateCode() == DbLocale.SALE_DATE_DEATHDATE) {
					caseinfo.setSaleDate(FormatDate.convertToDateYYYYMMDD(form.getDeathDate()));
				} else if (alocale.getDefaultSaleDateCode() == DbLocale.SALE_DATE_ARRDATE) {
					caseinfo.setSaleDate(FormatDate.convertToDateYYYYMMDD(form.getArrangeDate()));
				} else if (alocale.getDefaultSaleDateCode() == DbLocale.SALE_DATE_SERVDATE) {
					caseinfo.setSaleDate(FormatDate.convertToDateYYYYMMDD(form.getServiceDate()));
				}
			}
			
			

			DatabaseTransaction x = null;

			// Regardless of whether the user changed the contract number or
			// not, we need to make sure that the
			// contract number is unique before we proceed.
			if (form.getContractNumber() != null && form.getContractNumber().trim().length() > 0) {
				try {
					x = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
					while (continueDuplicate) {
						// Check for duplicate Contract Numbers.
						DbCase checkCase = new DbCase();
						checkCase.setNew();
						checkCase.setLocale(sessionUser.getRegion());
						checkCase.setId(deceased.getId());
						checkCase.setContractCode(form.getContractNumber());
						if (FdmsDb.getInstance().checkCaseExists(x, checkCase, DbCasePeer.CONTRACTCODE)) {
							// If the user changed the contract number, give them an error
							if (!form.getNextContractNumber().equals(form.getContractNumber())) {
								errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
												"error.duplicate.contractNumber"));
								formErrors.add("contractNumber");
								continueDuplicate = false;
							} else {
								// contract number is same so need to increment and retest for duplicate
								form.setContractNumber(String.valueOf(SessionHelpers.nextContractNumber(sessionUser.getDbLookup(), sessionUser
										.getRegion())));
								form.setNextContractNumber(form.getNextContractNumber());
							}
						} else {
							continueDuplicate = false;
							form.setContractNumber(form.getContractNumber());
							form.setNextContractNumber(form.getNextContractNumber());
						}
					}
				} catch (Exception e) {
					logger.error("Error : ", e);
				} finally {
					if (x != null){
						x.closeConnection();
						x = null;
					}
					
				}
			}

			caseinfo.setContractCode(form.getContractNumber());
			
			
			
			// Regardless of whether the user changed the case number or
			// not, we need to make sure that the
			// case number is unique before we proceed.
			DatabaseTransaction y = null;
			continueDuplicate = true;
			
			if (form.getCaseNumber() != null && form.getCaseNumber().trim().length() > 0) {
				try {
					y = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
					while (continueDuplicate) {
						// Check for duplicate Contract Numbers.
						DbCase checkCase = new DbCase();
						checkCase.setNew();
						checkCase.setLocale(sessionUser.getRegion());
						checkCase.setId(deceased.getId());
						checkCase.setCaseCode(form.getCaseNumber());
						if (FdmsDb.getInstance().checkCaseExists(y, checkCase, DbCasePeer.CASECODE)) {
							
								errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.duplicate.caseNumber"));
								formErrors.add("caseNumber");
								continueDuplicate = false;
							
						} else {
							continueDuplicate = false;
						}
					}
				} catch (Exception e) {
					logger.error("Error : ", e);
				} finally {
					if (y != null){
						y.closeConnection();
						y = null;
					}
					
				}
			}
			caseinfo.setCaseCode(form.getCaseNumber());
			
			if (form.isInformantBillToParty()) {
				addUpdateBillToInformation(t, form, deceased.getId());
			}
			
		} catch (Exception e) {
			logger.error("Exception in setVitalsRest() ", e);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.setData"));
		}

		return;
	}
	
	private void addUpdateBillToInformation (DatabaseTransaction t, FirstCallInformationForm form, int vitalsID) {
		
		String informFirst = compareSafeString(form.getInformantFirst());
		String informLast = compareSafeString(form.getInformantLast());
		
//		if ((informFirst.length() > 0) || (informLast.length() > 0)) {
//			
//			DbBillto[] dbBillTo = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getBilltoForID(t, vitalsID);
//			if (dbBillTo == null || dbBillTo.length == 0) {
//				DbBillto newBillTo = new DbBillto();
//				newBillTo.setNew();
//				updateBillToInfo (newBillTo, form, vitalsID);
//				t.addPersistent(newBillTo);
//			}
//			
//			if ( form.isInformantBillToParty() && (dbBillTo != null || dbBillTo.length > 0) ) {
//				
//				boolean update = false;
//				
//				if(form.getBillToParty() != null){
//					updateBillToInfo (form.getBillToParty(), form, vitalsID);
//					t.addPersistent(form.getBillToParty());
//					update = true;
//				}else{
//					for ( int i = 0; i < dbBillTo.length; i++ ) {
//						DbBillto billToInfo = dbBillTo[i];
//						
//						if ( matchBillToInfo(billToInfo, form) ) {
//							updateBillToInfo (billToInfo, form, vitalsID);
//							t.addPersistent(billToInfo);
//							update = true;
//						}
//						
//					}
//				}
//				
//				if ( update == false ) {
//					DbBillto newBillTo = new DbBillto();
//					newBillTo.setNew();
//					updateBillToInfo (newBillTo, form, vitalsID);
//					t.addPersistent(newBillTo);
//				}
//				
//			}
//			
//		}
		if ((informFirst.length() > 0) || (informLast.length() > 0)) {
			DbBillto[] dbBillTo = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getBilltoForID(t, vitalsID);
			if (dbBillTo == null || dbBillTo.length == 0) {
				DbBillto newBillTo = new DbBillto();
				newBillTo.setNew();
				updateBillToInfo (newBillTo, form, vitalsID);
				t.addPersistent(newBillTo);
			}else{
				for ( int i = 0; i < dbBillTo.length; i++ ) {
					DbBillto billToInfo = dbBillTo[i];
					
					if ( billToInfo.getGroupType().compareToIgnoreCase(DbBillto.INFORMANT) == 0 ) {
						updateBillToInfo (billToInfo, form, vitalsID);
						t.addPersistent(billToInfo);
						
					}
					
				}
			}
		}
		
	}
	
	private void updateBillToInfo (DbBillto billToInfo, FirstCallInformationForm form, int vitalsID) {
		billToInfo.setCity(form.getInformantCity());
		billToInfo.setEmailAddress(form.getInformantEmail());
		billToInfo.setFirstName(form.getInformantFirst());
		billToInfo.setHomePhone(FormatString.formatPhone(form.getInformantPhone()));
		billToInfo.setCellPhone(FormatString.formatPhone(form.getInformantCellPhone()));
		billToInfo.setHonorific(form.getInformantSalutation());
		billToInfo.setLastName(form.getInformantLast());
		billToInfo.setRelation(form.getInformantRelation());
		billToInfo.setSendInvoice("Y");
		billToInfo.setSeqNo(Short.parseShort("0"));
		if ( form.getInformantState().length() == 2) {
			billToInfo.setState(form.getInformantState().toUpperCase());
		}
		else {
			billToInfo.setState(form.getInformantState());   	
		}
		
		billToInfo.setStreet1(form.getInformantStreet());
		billToInfo.setStreet2(form.getInformantStreet2());
		billToInfo.setStreet3(form.getInformantStreet3());
		billToInfo.setVitalsID(vitalsID);
		billToInfo.setZip(form.getInformantZip().toUpperCase());
		if (form.getInformantContractSigner()) {
			billToInfo.setContractSigner("Y");
		}
		billToInfo.setGroupType(DbBillto.INFORMANT);
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
	

	private void validateForm(DatabaseTransaction t, DbUserSession sessionUser,
			fdms.ui.struts.form.FirstCallInformationForm form, ActionErrors errors) {

		CompanyOptionsManager coMgr = new CompanyOptionsManager ();
		
		// Validate Arrange Date
		if (form.getArrangeDate() != null && form.getArrangeDate().trim().length() > 0) {
			try {
				FormatDate.convertToDateMMDDYYYY(form.getArrangeDate());
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.arrangedate"));
				formErrors.add("arrangeDate");
			}
		}

		// at-need contract number is required.
	    int contractNumberOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_AT_NEED_CONTRACT_NUMBER);
	    if (contractNumberOption == 1) {
			if (form.getContractNumber() == null || form.getContractNumber().trim().length() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.contractNumber"));
				formErrors.add("contractNumber");
			}
	    }
		// Chapel is required.
		if (form.getChapel() == null || form.getChapel().trim().length() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.chapel"));
			formErrors.add("chapel");
		}


		
		// Director is required.
		if (form.getDirector() == null || form.getDirector().trim().length() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.director"));
			formErrors.add("director");
		}

		// Validate PreNeed Date if entered.
		if (form.getPreneedDate() != null && form.getPreneedDate().trim().length() > 0) {
			try {
				FormatDate.convertToDateMMDDYYYY(form.getPreneedDate());
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.origpndate"));
				formErrors.add("preneedDate");
			}
		}

		// If the user changed the contract number, check for duplicate Contract
		// Numbers.
		if (form.getContractNumber() != null && form.getContractNumber().trim().length() > 0
				&& (!form.getContractNumber().equals(form.getNextContractNumber()))) {

			DbCase checkCase = new DbCase();
			checkCase.setNew();
			checkCase.setLocale(sessionUser.getRegion());
			if (form.getVitalsId() == null || form.getVitalsId().trim().length() == 0 || form.getVitalsId() == "0") {
				checkCase.setId(0);
			} else {
				checkCase.setId(FormatNumber.parseInteger(form.getVitalsId()));
			}
			checkCase.setContractCode(form.getContractNumber());
			if (FdmsDb.getInstance().checkCaseExists(t, checkCase, DbCasePeer.CONTRACTCODE)) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.duplicate.contractNumber"));
				formErrors.add("contractNumber");
			}
		}
		
		// If the user changed the case number, check for duplicate case Numbers.
		if (form.getCaseNumber() != null && form.getCaseNumber().trim().length() > 0
				&& (!form.getCaseNumber().equals(form.getNextCaseNumber()))) {

			DbCase checkCase = new DbCase();
			checkCase.setNew();
			checkCase.setLocale(sessionUser.getRegion());
			if (form.getVitalsId() == null || form.getVitalsId().trim().length() == 0 || form.getVitalsId() == "0") {
				checkCase.setId(0);
			} else {
				checkCase.setId(FormatNumber.parseInteger(form.getVitalsId()));
			}
			checkCase.setCaseCode(form.getCaseNumber());
			if (FdmsDb.getInstance().checkCaseExists(t, checkCase, DbCasePeer.CASECODE)) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.duplicate.caseNumber"));
				formErrors.add("caseNumber");
			}
		}		
		

		// Deceased Memorial Name is required.
		if (form.getMemorialName() == null || form.getMemorialName().trim().length() == 0) {
			if (form.getMiddleName() != null && form.getMiddleName().trim().length() > 0) {
				form.setMemorialName(form.getFirstName() + " " + form.getMiddleName() + " " + form.getLastName());
			} else {
				form.setMemorialName(form.getFirstName() + " " + form.getLastName());
			}
			if (form.getMemorialName() == null || form.getMemorialName().trim().length() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.memorialName.required"));
				formErrors.add("memorialName");
			}
		}

		// Deceased First Name is required.
		if (form.getFirstName() == null || form.getFirstName().trim().length() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullFirstName"));
			formErrors.add("firstName");
		}

		// Deceased Last Name is required.
		if (form.getLastName() == null || form.getLastName().trim().length() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.registration.nullLastName"));
			formErrors.add("lastName");
		}

		// Validate Date of Birth
		if (form.getBirthDate() != null && form.getBirthDate().trim().length() > 0) {
			try {
				FormatDate.convertToDateMMDDYYYY(form.getBirthDate());
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.birthdate"));
				formErrors.add("birthDate");
			}
		}

		// Validate Date of Death
		if (form.getDeathDate() != null && form.getDeathDate().trim().length() > 0) {
			try {
				FormatDate.convertToDateMMDDYYYY(form.getDeathDate());
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.deathdate"));
				formErrors.add("deathDate");
			}
		}

		// Validate Service Date
		if (form.getServiceDate() != null && form.getServiceDate().trim().length() > 0) {
			try {
				FormatDate.convertToDateMMDDYYYY(form.getServiceDate());
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.servicedate"));
				formErrors.add("serviceDate");
			}
		}
		
		
	    	// Now check to see if this options is turned on for this customer
	    int serviceDateOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_NEW_AT_NEED_SERVICE_DATE);
	    if (serviceDateOption == 1) {
	    	int vitalsid = 0;
	    	int preneedCase = 0;
	    	try {
				vitalsid = FormatNumber.parseInteger(form.getVitalsId());
				DbPreneed dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
				if (dbPreNeed.getStatus().compareToIgnoreCase("A") == 0){
					preneedCase = 1;
				}
			} catch (Exception e) {
				vitalsid = 0;
			}
			
			if (vitalsid == 0 || preneedCase == 0) {
		    	if (form.getServiceDate() == null || form.getServiceDate().trim().length() == 0) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.servicedateRequire"));
					formErrors.add("serviceDate");
		    	}
			}
	    }

		// Validate Disposition Date
		if (form.getDispDate() != null && form.getDispDate().trim().length() > 0) {
			try {
				FormatDate.convertToDateMMDDYYYY(form.getDispDate());
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.dispdate"));
				formErrors.add("dispDate");
			}
		}

		// Facility is required.
		if (!form.isFacilitySame()) {
			if (form.getFacilityName() == null || form.getFacilityName().trim().length() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.facility"));
				formErrors.add("facilityName");
			}
		}
		
		return;

	}
}
