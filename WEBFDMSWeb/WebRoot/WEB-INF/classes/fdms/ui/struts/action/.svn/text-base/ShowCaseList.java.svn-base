package fdms.ui.struts.action;

import java.util.ArrayList;
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

import com.aldorassist.webfdms.dao.ComboDataDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.ComboDataDTO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.bean.UserPermissionsBean;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;
import com.aldorsolutions.webfdms.visitation.bean.VisitationManagerBean;

import fdms.messenger.CaseProfile;
import fdms.messenger.Messenger;
import fdms.ui.caseset.bean.CaseSetManagerBean;
import fdms.ui.struts.form.CaseListCaseForm;
import fdms.ui.struts.form.CaseListForm;
import fdms.ui.struts.form.CaseListOptionsForm;

public class ShowCaseList extends Action {

	private Logger logger = Logger.getLogger(ShowCaseList.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		logger.debug("***** Entering show case list *****");

		String target = "caseListForm";
		CaseListForm form = (CaseListForm) actionForm;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;

		ArrayList <CaseListCaseForm> caseListForm = null;
		String sortOrderId = "DeathDateKey";
		boolean preneedOnly = false;
		int count = 0;
		int startIndex = 1;
		int endIndex = 1;
		int numRecordsPerScreen = sessionUser.getCaseListPerScreen();

		VisitationManagerBean visitationManager = new VisitationManagerBean();

		String requestType = form.getRequestType();
		if (requestType == null) {
			requestType = "";
		}

		if (((Integer) session.getAttribute("USER_START_INDEX")) != null) {
			startIndex = ((Integer) session.getAttribute("USER_START_INDEX")).intValue();
		}
		logger.debug("Start index : " + startIndex);

		String showPreneedOnly = (String) session.getAttribute(Constants.SHOW_PRENEED_ONLY);
		if ((showPreneedOnly != null) && (("Y".equals(showPreneedOnly)) || "N".equals(showPreneedOnly))) {
			preneedOnly = FormatString.ynToBoolean(showPreneedOnly);
		}
			
		if ((form.getUserLocationId() == null) || (form.getUserLocationId().trim().equals(""))) {
			form.setUserLocationId(Integer.toString(sessionUser.getLocationId()));
		}

		logger.debug("User Default Location : " + form.getUserLocationId());

		while (true) {
			

			ArrayList userLocaleLocs = (ArrayList) session.getAttribute(Constants.USER_LOCATIONS);

			if ( userLocaleLocs != null && userLocaleLocs.size() == 1 ) {
				form.setShowLocationList(false);
				
				DbLocation loc = (DbLocation) userLocaleLocs.get(0);
				form.setUserLocationId( String.valueOf(loc.getId()) );
				
			} else if ( userLocaleLocs != null && userLocaleLocs.size() > 1 ) {
				form.setShowLocationList(true);
			}
			
      if(requestType.equals("exportToMessenger")) {
      	Messenger messenger = new Messenger(sessionUser);
      	CaseProfile caseProfile = messenger.loadMessengerData(form.getVitalsId());
      	if (caseProfile != null) {
        	messenger.sendXMLResponseToClient(caseProfile, form.getVitalsId(), response);
      	}
      	
      	form.setRequestType("");
	      form.setJumpTo(null);
	      return null;
      }


			if (requestType.equals("changeLocation")) {
				changeLocation(form, sessionUser, session);
				requestType = "";
				startIndex = 1;
				requestType = "first";

				form.setJumpTo(null);
			} else if (requestType.equals("changeLocale")) {
				changeLocale(form, sessionUser, session, errors);
				requestType = "";
				startIndex = 1;
				requestType = "first";

				form.setJumpTo(null);
			} else if (requestType.equals("update")) {
				try {
					String userSort = sessionUser.getCaseListSortOrder();					
//					String formSort = form.getSortOrderId();
					
					if ( (userSort == null) || ( (userSort != null)  ) ) {
						
						// update user caseListSortOrder value in webfdmsusers.usersecurity table
						String userLookup = UtilSingleton.getInstance().getUserDBLookup();
						t = new DatabaseTransaction(userLookup, sessionUser );

						DbUser dbUser = (DbUser) FdmsDb.getInstance().getUser(t, sessionUser.getId());
						dbUser.setCaseListSortOrder(form.getSortOrderId());
						t.save();

						// update values in session
						sessionUser.setCaseListSortOrder(form.getSortOrderId());
					}
					
					form.setJumpTo(null);
				} catch (Exception e) {
					logger.error("Exception in perform() : ", e);
				} finally {
					if (t != null)
						t.closeConnection();
				}

				requestType = "first";
				startIndex = 1;
			}
			
			sortOrderId = sessionUser.getCaseListSortOrder();

			form.setSortOrderId(sortOrderId);

			/*
			 * if (!requestType.equals("jump")) form.setJumpTo(null);
			 */

			if ((sortOrderId == null) || (sortOrderId.trim().length() == 0) || (numRecordsPerScreen == 0)) {
				CaseListOptionsForm caseListOptions = new CaseListOptionsForm();
				caseListOptions.setOrderby("DeathDateKey");
				caseListOptions.setPerScreen("10");
				request.setAttribute("caseListOptions", caseListOptions);
				target = "caseListOptions";
				break;
			}

			if (requestType.equals("options")) {
				form.setRequestType(" ");
				CaseListOptionsForm caseListOptions = new CaseListOptionsForm();
				caseListOptions.setOrderby(sessionUser.getCaseListSortOrder());
				caseListOptions.setPerScreen(String.valueOf(sessionUser.getCaseListPerScreen()));
				request.setAttribute("caseListOptions", caseListOptions);
				target = "caseListOptions";
				break;
			}

			String searchParam = null;
			if ((form.getJumpTo() != null) && (!form.getJumpTo().trim().equals(""))) {
				searchParam = form.getJumpTo().trim();
// This no use it moved to casesetdao.java				
//				if (form.getSortOrderId().compareToIgnoreCase("DeceasedLastFirstName") == 0) {
//					String lastFirstName = searchParam;
//					String [] temp = null;
//					temp = lastFirstName.split(",");
//					if (temp.length == 2) {
//						String lastName = temp[0].trim();
//						String Firstname = temp[1].trim();
//					}
//					else {
//						String lastName = temp[0].trim();
//						String Firstname = "";
//					}
//				}
				
			}

			CaseSetManagerBean caseSetManagerBean = new CaseSetManagerBean();
			UserManagerBean userManager = new UserManagerBean();

			String[] locationIds = userManager.getUserLocationIds(sessionUser.getId(), sessionUser.getRegion());

			if (locationIds == null) {
				locationIds = new String[0];
			}

			logger.debug("Location IDs: " + locationIds);

			int active = 1;
			String showActiveCases = (String) session.getAttribute("SHOW_ACTIVE_CASES");
			if (showActiveCases != null) {
				active = FormatNumber.parseInteger(showActiveCases);
			}

			UserPermissionsBean userPermissions = (UserPermissionsBean) session.getAttribute("permissions");
			String showPreNeed = "N";

			if (userPermissions.isPreNeedGranted()) {
				showPreNeed = sessionUser.getCaseListDisplayPreneed();
			}

			count = caseSetManagerBean.getCaseCount(sessionUser.getDbLookup(), 
					form.getUserLocationId(), locationIds, form.getSortOrderId(), 
					searchParam, active, showPreNeed, 
					sessionUser.getCaseListDisplayVoided(), preneedOnly, 
					sessionUser);

			logger.debug("Checking RequestType : " + requestType);

			form.setRequestType("");

			int userCaseStart = 0;
			int userCaseEnd = 0;

			logger.debug("Record count found : " + count);
			endIndex = caseSetManagerBean.getEndIndex(count, numRecordsPerScreen);
			logger.debug("End index : " + endIndex);

			if (count > 0) {
				if (requestType.equals("first")) {
					startIndex = 1;
				} else if (requestType.equals("next") && (endIndex >= (startIndex + 1))) {
					startIndex++;
				} else if ((requestType.equals("previous")) && (startIndex > 1)) {
					startIndex--;
				} else if (requestType.equals("last")) {
					startIndex = endIndex;
				}

				if (startIndex != endIndex)
					userCaseEnd = ((startIndex - 1) * numRecordsPerScreen) + numRecordsPerScreen;
				else
					userCaseEnd = count;

				userCaseStart = ((startIndex - 1) * numRecordsPerScreen) + 1;
			}
/*
			logger.debug("startIndex : " + startIndex);
			logger.debug("caseListSize : " + count);
			logger.debug("userCaseStart : " + userCaseStart);
			logger.debug("userCaseEnd : " + userCaseEnd);
			logger.debug("numRecordsPerScreen : " + numRecordsPerScreen);
*/
			session.setAttribute("USER_START_INDEX", new Integer(startIndex));
			session.setAttribute("USER_CASE_COUNT", new Integer(count));
			session.setAttribute("USER_CASE_START", new Integer(userCaseStart));
			session.setAttribute("USER_CASE_END", new Integer(userCaseEnd));

			try {
				// Get a new database transaction.
				t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

				// Use the transaction to retrieve the case set.
				ArrayList caseList = caseSetManagerBean.getCaseList(sessionUser.getDbLookup(), 
						form.getUserLocationId(), locationIds, sortOrderId, startIndex,
						numRecordsPerScreen, searchParam, active, showPreNeed, sessionUser.getCaseListDisplayVoided(),
						preneedOnly, sessionUser);

				// Handle empty case sets.
				if (caseList.size() == 0) {
					if (requestType.equals("next") || requestType.equals("back")) {
						// Leave the old case list in the session, and trigger a
						// message indicating that
						// there is no more data in this direction.
						form.setRequestType("NoDataDirection");
					} else {
						// Pass an empty case list to the view.
						session.setAttribute("caseList", new java.util.ArrayList());
						session.setAttribute("beginCaseListCursor", new Integer(0));
						session.setAttribute("endCaseListCursor", new Integer(0));
					}
					break;
				}

				DbServices srv = null;
				DbFinancialSummary finan = null;
				DbVitalsInformant vitalsInformant = null;
				DbLocation dbLocation = null;
				DbPreneed dbPreneed = null;

				caseListForm = new ArrayList <CaseListCaseForm> ();

				for (int i = 0; i < caseList.size(); i++) {
					DbCase dbCase = (DbCase) caseList.get(i);
					CaseListCaseForm caseListCase = new CaseListCaseForm();
					caseListCase.setVitalsId(dbCase.getVitalsID().toString());
					caseListCase.setCaseCode(dbCase.getCaseCode());
					caseListCase.setContractCode(dbCase.getContractCode());

					DbVisitations visitation = visitationManager.getVisitationForCaseList( 
							dbCase.getVitalsID().intValue(), sessionUser);

					caseListCase.setVisitationPlace(visitation.getPlace());
					caseListCase.setVisitationDate(visitation.getDate());
					caseListCase.setVisitationStartTime(visitation.getStartTime());
					caseListCase.setVisitationEndTime(visitation.getEndTime());
					caseListCase.setVisitationPrivate(visitation.getPrivateVisitation());

					caseListCase.setDeathDateKey(dbCase.getDeathDate());
					caseListCase.setDateOfDeath(FormatDate.YMDtoMMDDYYYY(dbCase.getDeathDate()));
					caseListCase.setServiceDateKey(dbCase.getServiceDate());
					caseListCase.setDateOfService(FormatDate.YMDtoMMDDYYYY(dbCase.getServiceDate()));
					caseListCase.setDispositionMethod(dbCase.getDispositionMethod());
					caseListCase.setDispositionPlace(dbCase.getDispositionPlace());
					caseListCase.setDispositionDate(FormatDate.MDYtoMMDDYYYY(dbCase.getDispositionDate()));
					if (dbCase.getAdditionalServiceDate() != null) {
						caseListCase.setAdditionalDateOfService(FormatDate.MDYtoMMDDYYYY(dbCase
								.getAdditionalServiceDate()));
					}

					caseListCase.setFirstName(dbCase.getFirstName());
					caseListCase.setLastName(dbCase.getLastName());
					caseListCase.setDeceasedFullName(  FormatString.formatHTMLString(dbCase.getFirstName() + " " + dbCase.getLastName()) );
					caseListCase.setContractLiteral("Posted");
					caseListCase.setArrangeDate(FormatDate.MDYtoMMDDYYYY(dbCase.getArrangeDate()));
					caseListCase.setDirector(dbCase.getDirector());
					dbPreneed = FdmsDb.getInstance().getPreneed(t, Integer.parseInt(caseListCase.getVitalsId()));

					if (dbPreneed.getStatus().compareToIgnoreCase(DbPreneed.ACTIVE) == 0
							|| dbPreneed.getStatus().compareToIgnoreCase(DbPreneed.CANCELLED) == 0) {
						caseListCase.setContractLiteral("Pre-Need");
						caseListCase.setPersonType("Pre-Need");
					} else
						caseListCase.setPersonType("Decedent");

					try {
						// Chapel Name
						dbLocation = FdmsDb.getInstance().getLocation(t, dbCase.getChapelNumber());
						caseListCase.setChapelName(dbLocation.getName());
						caseListCase.setChapelID(Integer.toString(dbCase.getChapelNumber()));
					} catch (Exception e) {
					}

					try {
						// Service information for this case
						srv = FdmsDb.getInstance().getServices(t, Integer.parseInt(caseListCase.getVitalsId()));
						if ((srv.getCSrvPlace() != null) && (srv.getCSrvPlace().length() > 0)) {
							caseListCase.setPlaceOfService(srv.getCSrvPlace());
							caseListCase.setChurch(srv.getCSrvChurch());
							caseListCase.setDateOfService(caseListCase.getDateOfService()+" "+srv.getCSrvTime());
							if (caseListCase.getAdditionalDateOfService() != null) {
								caseListCase.setAdditionalDateOfService(caseListCase.getAdditionalDateOfService()+" "+srv.getAddnlServiceTime());
							}
						} else {
							caseListCase.setPlaceOfService(srv.getCSrvChurch());
							if (srv.getCSrvPlace() != null) {
								caseListCase.setDateOfService(caseListCase.getDateOfService()+" "+srv.getCSrvTime());
								if (caseListCase.getAdditionalDateOfService() != null) {
									caseListCase.setAdditionalDateOfService(caseListCase.getAdditionalDateOfService()+" "+srv.getAddnlServiceTime());
								}
							}
						}
						
					} catch (Exception e) {
					}

					try {
						// Financial information for this case
						finan = FdmsDb.getInstance().getFinancial(t, Integer.parseInt(caseListCase.getVitalsId()));
						caseListCase.setBilled(FormatCurrency.toCurrency((long) finan.getLFinTotalCharges()));
						caseListCase.setBalance(FormatCurrency.toCurrency((long) (finan.getLFinTotalCharges() - finan
								.getLTotalPaidToDate())));
						caseListCase.setServiceType(finan.getCFinSaleType());
						
						CompanyOptionsManager coMgr = new CompanyOptionsManager ();
					    	// Now check to see if this options is turned on for this customer
					    int acctDescription = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_ACCOUNT_DESCRIPTIONS);
					    	// If account descriptions are turned on then load them
					    if (acctDescription == 1) {
					    	session.setAttribute("salesTypeDisplay", "display");
						  	ComboDataDAO comboDataDAO = new ComboDataDAO(sessionUser);
						  	ArrayList accountDescriptions = comboDataDAO.getComboDataOptions(ComboDataDTO.COMBODATA_LOCAL_DEFULT, 
						  				ComboDataDTO.COMBODATA_TYPE_SALES_TYPE);
						  		
						  		// Now go thru the elements and 
						  	for (int k=0; k < accountDescriptions.size(); k++) {
						  		ComboDataDTO comboData = (ComboDataDTO)accountDescriptions.get(k);
						  		if (finan.getSalesDescCDID() == Long.valueOf(comboData.getValue())) {
						  			caseListCase.setSaleType(comboData.getName());
						  		}
						  		
						  	}
					    } else {
					    	session.setAttribute("salesTypeDisplay", "none");
					    }
						
						
						
						if (caseListCase.getContractLiteral().compareTo("Posted") == 0 && finan.getIARsentBox() == 0) {
							caseListCase.setContractLiteral("Unposted");
						}
					} catch (Exception e) {
						if (caseListCase.getContractLiteral().compareTo("Posted") == 0 && finan == null) {
							caseListCase.setContractLiteral("Unposted");
						}
					}

					try {
						// Informant information for this case
						vitalsInformant = FdmsDb.getInstance().getVitalsInformant(t,
								Integer.parseInt(caseListCase.getVitalsId()));
						caseListCase.setInformantFirstName(vitalsInformant.getFname());
						caseListCase.setInformantLastName(vitalsInformant.getLname());
						caseListCase.setRelation(vitalsInformant.getRelated());
						caseListCase.setInformantPhone(vitalsInformant.getPhone());
						caseListCase.setInformantEMail(vitalsInformant.getInformantEmail());
					} catch (Exception e) {
					}

					// AppLog.trace("Setting caseList array element " +i);
					if (dbCase.getVoidedContractCode() != null && dbCase.getVoidedContractCode().equals("V")) {
						caseListCase.setContractLiteral("Voided");
					}

					caseListForm.add(caseListCase);
				}

				// Set flag for Abbit or Classic pre-need icon
				LocaleDTO alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
				form.setAbbitPreneed(alocale.getPreneedLicense());

			} catch (PersistenceException ex) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.internal"));
				logger.error("Persistence Exception in ShowCaseList.doPerform. ", ex);
			} catch (Exception ex) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.internal"));
				logger.error("Exception in ShowCaseList.doPerform. ", ex);
			} finally {
				if (t != null) {
					t.closeConnection();
				}
			}

			if (form == null) {
				form = new CaseListForm();
			}

			form.setUserLocationId(Integer.toString(sessionUser.getLocationId()));

			form.setUserLocaleId(Integer.toString(sessionUser.getRegion()));

			session.setAttribute("caseList", caseListForm);
			session.setAttribute("beginCaseListCursor", new Integer(0));
			session.setAttribute("endCaseListCursor", new Integer(caseListForm.size()));
			session.setAttribute("caseListForm", form);

			break;
		}

		return mapping.findForward(target);
	}

	private void changeLocation(CaseListForm form, DbUserSession sessionUser, HttpSession session) {

//		ArrayList userLocations = (ArrayList) session.getAttribute(Constants.USER_LOCATIONS);
	
		logger.debug("Updating userDefaultLocation : " + form.getUserLocationId());
		sessionUser.setLocationId(FormatNumber.parseInteger(form.getUserLocationId()));
		session.setAttribute("USER_START_INDEX", new Integer(1));
		form = new CaseListForm();

		session.removeAttribute("caseList");
		session.removeAttribute("beginCaseListCursor");
		session.removeAttribute("endCaseListCursor");
	}

	private void changeLocale(CaseListForm form, DbUserSession sessionUser, HttpSession session, ActionErrors errors) {

		ArrayList userLocales = (ArrayList) session.getAttribute(Constants.USER_LOCALES);

		if (userLocales != null) {

			DatabaseTransaction t = null;
			DatabaseTransaction trans = null;

			try {
				// Get a new database transaction.
				String jndiLookup = UtilSingleton.getInstance().getUserDBLookup();

				t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser, jndiLookup);
				trans = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

				DbUser user = (DbUser) sessionUser;

				t.addPersistent(user);

				user.setRegion(Integer.parseInt(form.getUserLocaleId()));
				sessionUser.setRegion(Integer.parseInt(form.getUserLocaleId()));

				LocaleManagerBean lmb = new LocaleManagerBean(user);
				LocaleDTO locale = lmb.getLocale(user.getRegion());

    			Locale myLocale = new Locale("en", locale.getCountry());
    			session.setAttribute( Constants.INTERNATIONAL_LOCALE, myLocale );
    			

    			form.setUserLocationId("-1");
				
				String[] locationIds = (String[]) session.getAttribute(Constants.USER_LOCATION_IDS);
				DbLocation[] dbLocations = FdmsDb.getInstance().getLocationsForRegion(trans, user.getRegion());

				ArrayList <DbLocation> userLocaleLocs = new ArrayList <DbLocation>();

				if (dbLocations != null) {
					for (int i = 0; i < dbLocations.length; i++) {
						DbLocation loc = dbLocations[i];
						int locID = loc.getId();

						for (int x = 0; x < locationIds.length; x++) {
							int aLocID = Integer.parseInt(locationIds[x]);

							if (aLocID == locID) {
								userLocaleLocs.add(loc);
							}
						}

					}
				}
				
				if ( userLocaleLocs.size() == 1 ) {
					form.setShowLocationList(false);
					
					DbLocation loc = (DbLocation) userLocaleLocs.get(0);
					form.setUserLocationId( String.valueOf(loc.getId()) );
					
				} else if ( userLocaleLocs.size() > 1 ) {
					form.setShowLocationList(true);
				}
				
				session.setAttribute(Constants.USER_LOCATIONS, userLocaleLocs);

				t.save();

			} catch (PersistenceException ex) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.internal"));
				logger.error("Persistence Exception in ShowCaseList.doPerform. ", ex);
			} catch (Exception ex) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.internal"));
				logger.error("Exception in ShowCaseList.doPerform. ", ex);
			} finally {
				if (t != null) {
					t.closeConnection();
				}

				if (trans != null) {
					trans.closeConnection();
				}
			}

		} else {
			logger.debug("Unable to find USER_LOCALES in session");
		}

		changeLocation(form, sessionUser, session);
	}

}
