package fdms.ui.struts.action;
// Cloned and modified by JO - QPRIME - SOW: F030601A Cemetery Management System

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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.bean.UserPermissionsBean;
import com.aldorsolutions.webfdms.beans.DbCemDecCase;
import com.aldorsolutions.webfdms.beans.DbCemOwnCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.caseset.bean.CemDecSetManagerBean;
import fdms.ui.caseset.bean.CemOwnSetManagerBean;
import fdms.ui.struts.form.CaseListOptionsForm;
import fdms.ui.struts.form.CemDecCaseForm;
import fdms.ui.struts.form.CemListForm;
import fdms.ui.struts.form.CemOwnCaseForm;

public class ShowCemList extends Action {

	private Logger logger = Logger.getLogger(ShowCemList.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		logger.debug("***** Entering show case list *****");
		
		String target = "cemOwnList";
		CemListForm form = (CemListForm) actionForm;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;

		String sortOrderId = "DeathDateKey";
		boolean ownerShow = false;
		
		int startIndex = 1;
		int numRecordsPerScreen = sessionUser.getCaseListPerScreen();

		
		String requestType = form.getRequestType();
		if (requestType == null)
			requestType = "";

		if (((Integer) session.getAttribute("USER_START_INDEX")) != null) {
			startIndex = ((Integer) session.getAttribute("USER_START_INDEX"))
					.intValue();
		}
		logger.debug("Start index : " + startIndex);

		String showOwner = (String) session
				.getAttribute(Constants.SHOW_CEM_OWN_LIST);
				
		if ((showOwner != null)
				&& (("Y".equals(showOwner)) || "N"
						.equals(showOwner)))
			ownerShow = FormatString.ynToBoolean(showOwner);
		
		else
			ownerShow = true;
			
		
		if ((form.getUserLocationId() == null)
				|| (form.getUserLocationId().trim().equals("")))
			form.setUserLocationId(Integer
					.toString(sessionUser.getLocationId()));

		logger.debug("User Default Location : " + form.getUserLocationId());

		while (true) {

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
					// update user caseListSortOrder value in
					// webfdmsusers.usersecurity table
					t = new DatabaseTransaction( UtilSingleton.getInstance().getUserDBLookup(), sessionUser );
					
					DbUser dbUser = (DbUser) FdmsDb.getInstance().getUser(t,
							sessionUser.getId());
					dbUser.setCaseListSortOrder(form.getSortOrderId());
					t.save();

					// update values in session
					sessionUser.setCaseListSortOrder(form.getSortOrderId());
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

			if ((sortOrderId == null) || (sortOrderId.trim().length() == 0)
					|| (numRecordsPerScreen == 0)) {
				CaseListOptionsForm caseListOptions = new CaseListOptionsForm();
				caseListOptions.setOrderby("DeathDateKey");
				caseListOptions.setPerScreen("10");
				request.setAttribute("caseListOptions", caseListOptions);
				target = "cemListOptions";
				break;
			}

			if (requestType.equals("options")) {
				form.setRequestType(" ");
				CaseListOptionsForm caseListOptions = new CaseListOptionsForm();
				caseListOptions.setOrderby(sessionUser.getCaseListSortOrder());
				caseListOptions.setPerScreen(String.valueOf(sessionUser
						.getCaseListPerScreen()));
				request.setAttribute("caseListOptions", caseListOptions);
				target = "cemListOptions";
				break;
			}

			String searchParam = null;
			if ((form.getJumpTo() != null)
					&& (!form.getJumpTo().trim().equals(""))) {
				searchParam = form.getJumpTo().trim();
			}
			
			if (ownerShow == true){
				target = "cemOwnList";
				this.showOwnerCaseList(form, sessionUser, session, requestType,
						startIndex, searchParam, numRecordsPerScreen, sortOrderId);
			   
			}else{
				target = "cemDecList";
				this.showDeceasedCaseList(form, sessionUser, session, requestType,
						startIndex, searchParam, numRecordsPerScreen, sortOrderId);
			}
		    break;
		    
		}	
		return mapping.findForward(target);
	}	
		
		
	private void showOwnerCaseList(CemListForm form, DbUserSession sessionUser,
			HttpSession session, String requestType, int startIndex, String searchParam,
			int numRecordsPerScreen, String sortOrderId){
		
		ArrayList <CemOwnCaseForm> caseListForm = null;		
		DatabaseTransaction t = null;
		ActionErrors errors = new ActionErrors();
		
		int count = 0;
		int endIndex = 1;
				
		CemOwnSetManagerBean caseOwnSetManagerBean = new CemOwnSetManagerBean();
		UserManagerBean userManager = new UserManagerBean();

		
		
		String[] locationIds = userManager.getUserLocationIds(sessionUser
				.getId(), sessionUser.getRegion());
			
		if ( locationIds == null ) {
			locationIds = new String [0];
		}

		logger.debug("Location IDs: " + locationIds);

		int active = 1;
		String showActiveCases = (String) session
				.getAttribute("SHOW_ACTIVE_CASES");
		if (showActiveCases != null) {
			active = FormatNumber.parseInteger(showActiveCases);
		}

		UserPermissionsBean userPermissions = (UserPermissionsBean) session
				.getAttribute("permissions");
		String showPreNeed = "N";

		if (userPermissions.isPreNeedGranted()) {
			showPreNeed = sessionUser.getCaseListDisplayPreneed();
		}

		count = caseOwnSetManagerBean.getCaseCount(sessionUser.getDbLookup(),
				form.getUserLocationId(), locationIds, 
				form.getSortOrderId(), searchParam, active,
				showPreNeed, sessionUser.getCaseListDisplayVoided(),
				sessionUser);

		logger.debug("Checking RequestType : " + requestType);

		form.setRequestType("");

		int userCaseStart = 0;
		int userCaseEnd = 0;

		logger.debug("Record count found : " + count);
		endIndex = caseOwnSetManagerBean.getEndIndex(count,
				numRecordsPerScreen);
		logger.debug("End index : " + endIndex);

		if (count > 0) {
			if (requestType.equals("first")) {
				startIndex = 1;
			} else if (requestType.equals("next")
					&& (endIndex >= (startIndex + 1))) {
				startIndex++;
			} else if ((requestType.equals("previous")) && (startIndex > 1)) {
				startIndex--;
			} else if (requestType.equals("last")) {
				startIndex = endIndex;
			}

			if (startIndex != endIndex)
				userCaseEnd = ((startIndex - 1) * numRecordsPerScreen)
						+ numRecordsPerScreen;
			else
				userCaseEnd = count;

			userCaseStart = ((startIndex - 1) * numRecordsPerScreen) + 1;
		}
						
		logger.debug("startIndex : " + startIndex);
		logger.debug("caseListSize : " + count);
		logger.debug("userCaseStart : " + userCaseStart);
		logger.debug("userCaseEnd : " + userCaseEnd);
		logger.debug("numRecordsPerScreen : " + numRecordsPerScreen);

		session.setAttribute("USER_START_INDEX", new Integer(startIndex));
		session.setAttribute("USER_CASE_COUNT", new Integer(count));
		session.setAttribute("USER_CASE_START", new Integer(userCaseStart));
		session.setAttribute("USER_CASE_END", new Integer(userCaseEnd));

		try {
			// Get a new database transaction.
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			// Use the transaction to retrieve the case set.
			ArrayList caseList = caseOwnSetManagerBean.getCaseList(sessionUser.getDbLookup(), 
					form.getUserLocationId(),
					locationIds, sortOrderId, startIndex,
					numRecordsPerScreen, searchParam, active, showPreNeed,
					sessionUser.getCaseListDisplayVoided(), 
					sessionUser);

			// Handle empty case sets.
			if (caseList.size() == 0) {
				if (requestType.equals("next")
						|| requestType.equals("back")) {
					// Leave the old case list in the session, and trigger a
					// message indicating that
					// there is no more data in this direction.
					form.setRequestType("NoDataDirection");
				} else {
					// Pass an empty case list to the view.
					session.setAttribute("caseList",
							new java.util.ArrayList());
					session.setAttribute("beginCaseListCursor",
							new Integer(0));
					session.setAttribute("endCaseListCursor",
								new Integer(0));
				}
				return;
			}				
							
			caseListForm = new ArrayList <CemOwnCaseForm> ();
				
			for (int i = 0; i < caseList.size(); i++) {
				DbCemOwnCase dbCemOwnCase = (DbCemOwnCase) caseList.get(i);
				CemOwnCaseForm cemOwnCase = new CemOwnCaseForm();
				cemOwnCase.setOwnerID(dbCemOwnCase.getOwnerID());
				cemOwnCase.setOwnFirstName(dbCemOwnCase.getOwnFirstName());
				cemOwnCase.setOwnLastName(dbCemOwnCase.getOwnLastName());
				cemOwnCase.setOwnMidName(dbCemOwnCase.getOwnMidName());
				cemOwnCase.setOwnTitle(dbCemOwnCase.getOwnTitle());
				cemOwnCase.setOwnAptNo(dbCemOwnCase.getOwnAptNo());
				cemOwnCase.setOwnStreet(dbCemOwnCase.getOwnStreet());
				cemOwnCase.setOwnCity(dbCemOwnCase.getOwnCity());
				cemOwnCase.setOwnState(dbCemOwnCase.getOwnState());
				cemOwnCase.setOwnZip(dbCemOwnCase.getOwnZip());
				cemOwnCase.setOwnPhone(dbCemOwnCase.getOwnPhone());
				cemOwnCase.setOwnProperties(dbCemOwnCase.getOwnProperties());
					
				caseListForm.add(cemOwnCase);
				
			}

			// Set flag for Abbit or Classic pre-need icon
			LocaleDTO alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
			form.setAbbitPreneed(alocale.getPreneedLicense());

		} catch (PersistenceException ex) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.internal"));
			logger
					.error(
							"Persistence Exception in ShowCemList.doPerform. ",
							ex);
		} catch (Exception ex) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.internal"));
			logger.error("Exception in ShowCemList.doPerform. ", ex);
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}

		if (form == null) {
			form = new CemListForm();
		}

		form.setUserLocationId(Integer
				.toString(sessionUser.getLocationId()));
			
		form.setUserLocaleId(Integer.toString(sessionUser.getRegion()));

		session.setAttribute("caseList", caseListForm);
		session.setAttribute("beginCaseListCursor", new Integer(0));
		session.setAttribute("endCaseListCursor", new Integer(caseListForm
					.size()));
		session.setAttribute("caseListForm", form);
		
		return;
		
	}
	
	private void showDeceasedCaseList(CemListForm form, DbUserSession sessionUser,
			HttpSession session, String requestType, int startIndex, String searchParam,
			int numRecordsPerScreen, String sortOrderId){
		
		ArrayList <CemDecCaseForm> caseListForm = null;		
		DatabaseTransaction t = null;
		ActionErrors errors = new ActionErrors();
		
		int count = 0;
		int endIndex = 1;
				
		CemDecSetManagerBean caseDecSetManagerBean = new CemDecSetManagerBean();
		UserManagerBean userManager = new UserManagerBean();

		
		
		String[] locationIds = userManager.getUserLocationIds(sessionUser
				.getId(), sessionUser.getRegion());
			
		if ( locationIds == null ) {
			locationIds = new String [0];
		}

		logger.debug("Location IDs: " + locationIds);

		int active = 1;
		String showActiveCases = (String) session
				.getAttribute("SHOW_ACTIVE_CASES");
		if (showActiveCases != null) {
			active = FormatNumber.parseInteger(showActiveCases);
		}

		UserPermissionsBean userPermissions = (UserPermissionsBean) session
				.getAttribute("permissions");
		String showPreNeed = "N";

		if (userPermissions.isPreNeedGranted()) {
			showPreNeed = sessionUser.getCaseListDisplayPreneed();
		}

		count = caseDecSetManagerBean.getCaseCount(sessionUser.getDbLookup(),
				form.getUserLocationId(), locationIds, 
				form.getSortOrderId(), searchParam, active,
				showPreNeed, sessionUser.getCaseListDisplayVoided(),
				sessionUser);

		logger.debug("Checking RequestType : " + requestType);

		form.setRequestType("");

		int userCaseStart = 0;
		int userCaseEnd = 0;

		logger.debug("Record count found : " + count);
		endIndex = caseDecSetManagerBean.getEndIndex(count,
				numRecordsPerScreen);
		logger.debug("End index : " + endIndex);

		if (count > 0) {
			if (requestType.equals("first")) {
				startIndex = 1;
			} else if (requestType.equals("next")
					&& (endIndex >= (startIndex + 1))) {
				startIndex++;
			} else if ((requestType.equals("previous")) && (startIndex > 1)) {
				startIndex--;
			} else if (requestType.equals("last")) {
				startIndex = endIndex;
			}

			if (startIndex != endIndex)
				userCaseEnd = ((startIndex - 1) * numRecordsPerScreen)
						+ numRecordsPerScreen;
			else
				userCaseEnd = count;

			userCaseStart = ((startIndex - 1) * numRecordsPerScreen) + 1;
		}
						
		logger.debug("startIndex : " + startIndex);
		logger.debug("caseListSize : " + count);
		logger.debug("userCaseStart : " + userCaseStart);
		logger.debug("userCaseEnd : " + userCaseEnd);
		logger.debug("numRecordsPerScreen : " + numRecordsPerScreen);

		session.setAttribute("USER_START_INDEX", new Integer(startIndex));
		session.setAttribute("USER_CASE_COUNT", new Integer(count));
		session.setAttribute("USER_CASE_START", new Integer(userCaseStart));
		session.setAttribute("USER_CASE_END", new Integer(userCaseEnd));

		try {
			// Get a new database transaction.
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			// Use the transaction to retrieve the case set.
			ArrayList caseList = caseDecSetManagerBean.getCaseList(sessionUser.getDbLookup(), 
					form.getUserLocationId(),
					locationIds, sortOrderId, startIndex,
					numRecordsPerScreen, searchParam, active, showPreNeed,
					sessionUser.getCaseListDisplayVoided(), 
					sessionUser);

			// Handle empty case sets.
			if (caseList.size() == 0) {
				if (requestType.equals("next")
						|| requestType.equals("back")) {
					// Leave the old case list in the session, and trigger a
					// message indicating that
					// there is no more data in this direction.
					form.setRequestType("NoDataDirection");
				} else {
					// Pass an empty case list to the view.
					session.setAttribute("caseList",
							new java.util.ArrayList());
					session.setAttribute("beginCaseListCursor",
							new Integer(0));
					session.setAttribute("endCaseListCursor",
								new Integer(0));
				}
				return;
			}				
							
			caseListForm = new ArrayList <CemDecCaseForm>();
				
			for (int i = 0; i < caseList.size(); i++) {
				DbCemDecCase dbCemDecCase = (DbCemDecCase) caseList.get(i);
				CemDecCaseForm cemDecCase = new CemDecCaseForm();
				cemDecCase.setDecID(dbCemDecCase.getDecID());
				cemDecCase.setFirstName(dbCemDecCase.getFirstName());
				cemDecCase.setLastName(dbCemDecCase.getLastName());
				cemDecCase.setMidName(dbCemDecCase.getMidName());
				cemDecCase.setTitle(dbCemDecCase.getTitle());
				cemDecCase.setAptNo(dbCemDecCase.getAptNo());
				cemDecCase.setStreet(dbCemDecCase.getStreet());
				cemDecCase.setCity(dbCemDecCase.getCity());
				cemDecCase.setState(dbCemDecCase.getState());
				cemDecCase.setZip(dbCemDecCase.getZip());
				cemDecCase.setPhone(dbCemDecCase.getPhone());
				cemDecCase.setProperty(dbCemDecCase.getProperty());
					
				caseListForm.add(cemDecCase);
				
			}

			// Set flag for Abbit or Classic pre-need icon
			LocaleDTO alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
			form.setAbbitPreneed(alocale.getPreneedLicense());

		} catch (PersistenceException ex) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.internal"));
			logger
					.error(
							"Persistence Exception in ShowCemList.doPerform. ",
							ex);
		} catch (Exception ex) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.internal"));
			logger.error("Exception in ShowCemList.doPerform. ", ex);
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}

		if (form == null) {
			form = new CemListForm();
		}

		form.setUserLocationId(Integer
				.toString(sessionUser.getLocationId()));
			
		form.setUserLocaleId(Integer.toString(sessionUser.getRegion()));

		session.setAttribute("caseList", caseListForm);
		session.setAttribute("beginCaseListCursor", new Integer(0));
		session.setAttribute("endCaseListCursor", new Integer(caseListForm.size()));
		session.setAttribute("caseListForm", form);
		
		return;
		
	}
	
	private void changeLocation(CemListForm form, DbUserSession sessionUser,
			HttpSession session) {

		ArrayList userLocations = (ArrayList) session.getAttribute(Constants.USER_LOCATIONS);
		
		if (userLocations != null) {
			// updating user default locationId
			UserManagerBean userManagerBean = new UserManagerBean();
			userManagerBean.updateUserDefaultLocation(Integer
					.toString(sessionUser.getId()), form.getUserLocationId());

		} else {
			logger.debug("Unable to find USER_LOCATIONS in session");
		}

		logger.debug("Updating userDefaultLocation : "
				+ form.getUserLocationId());
		sessionUser.setLocationId(FormatNumber.parseInteger(form
				.getUserLocationId()));
		session.setAttribute("USER_START_INDEX", new Integer(1));
		form = new CemListForm();

		session.removeAttribute("caseList");
		session.removeAttribute("beginCaseListCursor");
		session.removeAttribute("endCaseListCursor");
	}

	private void changeLocale(CemListForm form, DbUserSession sessionUser,
			HttpSession session, ActionErrors errors) {

		ArrayList userLocales = (ArrayList) session
				.getAttribute(Constants.USER_LOCALES);

		if (userLocales != null) {

			DatabaseTransaction t = null;
			DatabaseTransaction trans = null;

			try {
				// Get a new database transaction.
				String userLookup = UtilSingleton.getInstance().getUserDBLookup();
				t = new DatabaseTransaction ( userLookup, sessionUser );
				trans = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
				
				DbUser user = (DbUser) sessionUser;

				t.addPersistent(user);

				user.setRegion(Integer.parseInt(form.getUserLocaleId()));
				sessionUser.setRegion(Integer.parseInt(form.getUserLocaleId()));

				form.setUserLocationId("-1");

				String[] locationIds = (String[]) session
						.getAttribute(Constants.USER_LOCATION_IDS);
				DbLocation[] dbLocations = FdmsDb.getInstance()
						.getLocationsForRegion(trans, user.getRegion());

				ArrayList <DbLocation> userLocaleLocs = new ArrayList <DbLocation> ();
				
				if ( dbLocations != null ) {
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

				session.setAttribute(Constants.USER_LOCATIONS, userLocaleLocs);

				t.save();

			} catch (PersistenceException ex) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.internal"));
				logger.error("Persistence Exception in ShowCemList.doPerform. ",
								ex);
			} catch (Exception ex) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.internal"));
				logger.error("Exception in ShowCemList.doPerform. ", ex);
			} finally {
				if (t != null) {
					t.closeConnection();
				}
				
				if ( trans != null ) {
					trans.closeConnection();
				}
			}

		} else {
			logger.debug("Unable to find USER_LOCALES in session");
		}

		changeLocation(form, sessionUser, session);
	}

}

