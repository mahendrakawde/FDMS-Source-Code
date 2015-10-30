package fdms.ui.struts.action;

import java.util.ArrayList;
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

import com.aldorsolutions.webfdms.beans.DbBookMark;
import com.aldorsolutions.webfdms.beans.DbBookMarkLocation;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.BookMarkAdminForm;

/**
 * ProcessBookMarkAdminAction This class processes the form for adding or
 * changing bookmark data and stores the information to the DBMS. Creation date:
 * 
 * @author:
 */
public class ProcessBookMarkAdminAction extends Action {

	private Logger logger = Logger.getLogger(ProcessBookMarkAdminAction.class.getName());

	private ArrayList formErrors;

	/**
	 * Called from EditBookMarkAdmin.JSP, this action validates the form data,
	 * stores it to the DBMS, and redisplays a new bookmark list so the operator
	 * can select another to modify.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		formErrors = new ArrayList();
		BookMarkAdminForm form = (BookMarkAdminForm) actionForm;
		ActionErrors errors = new ActionErrors();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbBookMark bookMark = null;

		// Check if CANCEL button was clicked
		if (form.getSubmitbutton().equals("cancel")) {
			return mapping.findForward("showBookMarkAdminGlobal");
		}

		// Must select userId for Change or Delete
		if (form.getActionID() == BookMarkAdminForm.ACTION_DELETE
				|| form.getActionID() == BookMarkAdminForm.ACTION_CHANGE) {
			if (form.getBookMarkId() < 1) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
				formErrors.add("bookmarkId");
				saveErrors(request, errors);
				request.setAttribute("formErrors", formErrors);
				return (new ActionForward(mapping.getInput()));
			}
		}

		// Database Access
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

			bookMark = form.getBookMark();

			if (form.getActionID() == BookMarkAdminForm.ACTION_DELETE) {
				// delete bookmark locations
				List list = form.getBookMarkLocationsList();
				for (int i = 0; list != null && i < list.size(); i++) {
					DbBookMarkLocation location = (DbBookMarkLocation) list.get(i);
					location.remove();
					t.addPersistent(location);
				}
				// delete bookmark
				bookMark.remove();
				t.addPersistent(bookMark);
				t.save();
			}

			validateData(form, errors, request);
			// Check for any errors so far
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("formErrors", formErrors);
				return new ActionForward(mapping.getInput());
			}

			if (form.getActionID() == BookMarkAdminForm.ACTION_ADD) {
				bookMark.setNew();
				t.addPersistent(bookMark);
				t.save();
				t.removePersistent(bookMark);
				if (Constants.GLOBAL_LOCALE_STRING.equals(form.getType())) {
					DbBookMarkLocation globalLocale = new DbBookMarkLocation();
					globalLocale.setLocaleId(Constants.GLOBAL_LOCALE);
					globalLocale.setLocationId(Constants.GLOBAL_LOCATION);
					globalLocale.setBookMarkId(bookMark.getId());
					globalLocale.setNew();
					t.addPersistent(globalLocale);
				} else if (Constants.GLOBAL_LOCATION_STRING.equals(form.getType())) {
					String[] locale = form.getLocaleIds();
					for (int i = 0; i < locale.length; i++) {
						DbBookMarkLocation globalLocation = new DbBookMarkLocation();
						globalLocation.setLocaleId(new Integer(locale[i]).intValue());
						globalLocation.setLocationId(Constants.GLOBAL_LOCATION);
						globalLocation.setBookMarkId(bookMark.getId());
						globalLocation.setNew();
						t.addPersistent(globalLocation);
					}
				} else {
					List list = getSaveLocations(form);
					for (int i = 0; list != null && i < list.size(); i++) {
						DbBookMarkLocation location = (DbBookMarkLocation) list.get(i);
						location.setBookMarkId(bookMark.getId());
						location.setNew();
						t.addPersistent(location);
					}
				}
				t.save();
			}

			if (form.getActionID() == BookMarkAdminForm.ACTION_CHANGE) {
				DbBookMark dbBookMark = (DbBookMark) FdmsDb.getInstance()
						.getBookMark(t, form.getBookMarkId());

				if (needsUpdate(bookMark, dbBookMark)) {
					// update bookmark
					bookMark.setModifications(DbBookMark.MODIFIED);
					t.addPersistent(bookMark);
					t.save();
					t.removePersistent(bookMark);
				}
				// remove the old selection.
				List list = form.getBookMarkLocationsList();
				for (int i = 0; list != null && i < list.size(); i++) {
					DbBookMarkLocation location = (DbBookMarkLocation) list.get(i);
					location.remove();
					t.addPersistent(location);
				}
				t.save();
				for (int i = 0; list != null && i < list.size(); i++) {
					DbBookMarkLocation location = (DbBookMarkLocation) list.get(i);
					location.remove();
					t.removePersistent(location);
				}
				// insert new selection.
				if (Constants.GLOBAL_LOCALE_STRING.equals(form.getType())) {
					DbBookMarkLocation globalLocale = new DbBookMarkLocation();
					globalLocale.setLocaleId(Constants.GLOBAL_LOCALE);
					globalLocale.setLocationId(Constants.GLOBAL_LOCATION);
					globalLocale.setBookMarkId(bookMark.getId());
					globalLocale.setNew();
					t.addPersistent(globalLocale);
				} else if (Constants.GLOBAL_LOCATION_STRING.equals(form.getType())) {
					String[] locale = form.getLocaleIds();
					for (int i = 0; i < locale.length; i++) {
						DbBookMarkLocation globalLocation = new DbBookMarkLocation();
						globalLocation.setLocaleId(new Integer(locale[i]).intValue());
						globalLocation.setLocationId(Constants.GLOBAL_LOCATION);
						globalLocation.setBookMarkId(bookMark.getId());
						globalLocation.setNew();
						t.addPersistent(globalLocation);
					}
				} else {
					list = getSaveLocations(form);
					for (int i = 0; list != null && i < list.size(); i++) {
						DbBookMarkLocation location = (DbBookMarkLocation) list.get(i);
						location.setBookMarkId(bookMark.getId());
						location.setNew();
						t.addPersistent(location);
					}
				}
				t.save();
			}

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ProcessBookMarkAdminAction.doPerform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in  ProcessBookMarkAdminAction.doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}

		// Check for any errors so far
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			return new ActionForward(mapping.getInput());
		}

		return mapping.findForward("showBookMarkAdminGlobal");
	}

	/**
	 * This method validates the jsp form values. Creation date:
	 * 
	 * @param t
	 *            com.aldorsolutions.webfdms.database.DatabaseTransaction
	 * @param form
	 *            fdms.ui.struts.form.BookMarkAdminForm
	 * @param errors
	 *            org.apache.struts.action.ActionErrors
	 */
	public void validateData(BookMarkAdminForm form, ActionErrors errors, HttpServletRequest request)
			throws Exception {
		// Check BookMark name
		DbBookMark bookMark = form.getBookMark();
		if (bookMark.getName() == null || bookMark.getName().trim().length() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.blank", "BookMarkName"));
			formErrors.add("BookMarkName");
		}

		// Check BookMark Link
		if (bookMark.getLink() == null || bookMark.getLink().trim().length() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.blank", "Link"));
			formErrors.add("Link");
		}
	}

	/**
	 * Checks Database update is needed or not.
	 * 
	 * @param bookMark
	 *            Modified bookmark
	 * @param dbBookMark
	 *            bookmark in Database
	 * @return true if update is required else false
	 */
	public boolean needsUpdate(DbBookMark bookMark, DbBookMark dbBookMark) {

		if (dbBookMark.getId() != bookMark.getId()) {
			return true;
		}
		if (dbBookMark.getOrder() != bookMark.getOrder()) {
			return true;
		}
		if (dbBookMark.getName() == null) {
			if (bookMark.getName() != null)
				return true;
		} else if (!dbBookMark.getName().equals(bookMark.getName()))
			return true;

		if (dbBookMark.getDescription() == null) {
			if (bookMark.getDescription() != null)
				return true;
		} else if (!dbBookMark.getDescription().equals(bookMark.getDescription()))
			return true;

		if (dbBookMark.getLink() == null) {
			if (bookMark.getLink() != null)
				return true;
		} else if (!dbBookMark.getLink().equals(bookMark.getLink()))
			return true;

		return false;
	}

	/**
	 * Gets new bookmark locations based on user selection
	 * 
	 * @param form
	 *            BookMarkAdminForm
	 * @return List of BookMarkLocations
	 */
	public ArrayList getSaveLocations(BookMarkAdminForm form) {

		ArrayList saveLocations = new ArrayList();
		ArrayList locationList = form.getLocations();
		String[] locationIds = form.getLocationIds();

		for (int i = 0; i < locationList.size(); i++) {
			DbLocation location = (DbLocation) locationList.get(i);

			int locationID = location.getId();

			if (locationIds != null) {
				for (int x = 0; x < locationIds.length; x++) {
					int aLocID = Integer.parseInt(locationIds[x]);

					if (aLocID == locationID) {

						int localeID = location.getLocaleNumber();

						DbBookMarkLocation loc = new DbBookMarkLocation();

						loc.setLocationId(locationID);
						loc.setLocaleId(localeID);
						loc.setBookMarkId(form.getBookMarkId());
						saveLocations.add(loc);
					}
				}
			}
		}

		return saveLocations;
	}
}
