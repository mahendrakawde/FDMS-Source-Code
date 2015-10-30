package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbBookMark;
import com.aldorsolutions.webfdms.beans.DbBookMarkLocation;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.BookMarkAdminForm;

/**
 * EditBookMarkAdmin Prepares form bean with data from selected bookmark
 * Creation date:$Date: 2007/09/17 16:20:12 $
 * 
 * @author:
 */
public class EditBookMarkAdmin extends Action {
	private Logger logger = Logger.getLogger(EditBookMarkAdmin.class.getName());

	private ArrayList formErrors;

	static {
		final Logger logTmp = Logger.getLogger(EditBookMarkAdmin.class.getName());
		logTmp.debug("EditBookMarkAdmin Loaded");
	}

	/**
	 * Called from BookMarkAdmin.JSP, this action prepares to show
	 * EditBookMarkAdmin.JSP. The UserListForm is used to determine if adding or
	 * which bookmark is being updated. Then the UserListForm form is created
	 * for the JSP to use.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		logger.debug("Entering EditBookMarkAdmin action doPerfrom");

		formErrors = new ArrayList();
		BookMarkAdminForm form = (BookMarkAdminForm) actionForm;
		ActionErrors errors = new ActionErrors();
		DbBookMark bookMark = null;
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		int recid = 0;

		if (form.getSubmitbutton().equals("exit")) {
			ActionForward actionForward = mapping.findForward("webFDMSManagement");
			return actionForward;
		}

		DatabaseTransaction tdata = null;

		try {
			tdata = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

			if (form.getSubmitbutton().equals("cancel")) {
				return new ActionForward(mapping.getInput());
			}
			if (form.getSubmitbutton().equals("add")) {
				// prepare empty form for addition
				form.setBookMark(new DbBookMark());
				form.setActionID(BookMarkAdminForm.ACTION_ADD);
				form.setAction("Add");
			} else {
				// must be "change" or "delete"
				recid = form.getBookMarkId();
				if (recid < 1) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
					formErrors.add("BookMark");
					saveErrors(request, errors);
					request.setAttribute("formErrors", formErrors);
					return (new ActionForward(mapping.getInput()));
				}

				List list = form.getBookMarkList();
				DbBookMark dbbookMark = null;
				for (int i = 0; list != null && i < list.size(); i++) {
					dbbookMark = (DbBookMark) list.get(i);
					if (dbbookMark.getId() == recid) {
						bookMark = dbbookMark;
						break;
					}
				}

				if (bookMark == null) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",
							"BookMark record could not be accessed."));
				} else {
					// Load form with DB data
					form.setBookMark(bookMark);
					if (form.getSubmitbutton().equals("delete")) {
						form.setAction("Delete");
						form.setActionID(BookMarkAdminForm.ACTION_DELETE);
					} else {
						form.setAction("Change");
						form.setActionID(BookMarkAdminForm.ACTION_CHANGE);
					}
				}

			}

			// UserManagerBean userManagerBean = new UserManagerBean();
			DbLocation dbLocations[] = FdmsDb.getInstance().getLocationsForCompany(tdata,
					(int) sessionUser.getCompanyID());

			logger.debug("Dblocations is : " + dbLocations);
			ArrayList locationList = new ArrayList();
			// Populate the Location List
			for (int i = 0; i < dbLocations.length; i++) {
				DbLocation location = (DbLocation) dbLocations[i];
				locationList.add(location);
			}
			form.setLocations(locationList);

			ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(sessionUser.getDbLookup(),
					(int) sessionUser.getCompanyID());
	        ArrayList localeList = new ArrayList();
	        
	        // Populate the Locale List
	        
	        for ( LocaleDTO locale : locales ) {
	        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
	        }
	        
			form.setLocales(localeList);
			request.getSession().setAttribute("localesSize", new Integer(localeList.size()));
			request.getSession().setAttribute("locationsSize", new Integer(locationList.size()));
			List list = new ArrayList();
			if(!form.getSubmitbutton().equals("add")){
				 list = FdmsDb.getInstance().getBookMarksLocations(tdata, recid);
			}
			form.setLocationIds(getLocations(list));
			form.setLocaleIds(getLocale(list));
			form.setBookMarkLocationsList(list);
			form.setType(getType(list));

			logger.debug("LocaleIds: " + form.getLocaleIds());
			logger.debug("LocationIds: " + form.getLocationIds());

			String js = createLocaleJavascript(dbLocations, form.getLocationIds());

			form.setLocaleMapJavascript(js);

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in EditBookMarkAdmin.doPerform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in  EditBookMarkAdmin.doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (tdata != null) {
				tdata.closeConnection();
				tdata = null;
			}
		}

		// Check for any errors so far
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			return new ActionForward(mapping.getInput());
		}

		return mapping.findForward("editBookMarkAdmin");
	}

	private String createLocaleJavascript(DbLocation[] dbLocations, String[] locationIds) {

		String js = "\n";

		js += "\tlocList = new Array(" + dbLocations.length + ");\n";

		for (int j = 0; j < dbLocations.length; j++) {
			DbLocation dbLocation = (DbLocation) dbLocations[j];

			int localeID = dbLocation.getLocaleNumber();
			int locationID = dbLocation.getId();
			String locName = dbLocation.getName();
			boolean selected = false;

			if (locationIds != null) {
				for (int x = 0; x < locationIds.length; x++) {
					int aLocID = Integer.parseInt(locationIds[x]);

					if (aLocID == locationID) {
						selected = true;
					}
				}
			}
			
			js += "\tlocList[" + j + "] = new Array(" + localeID + ", " + locationID + ", \"" + locName
					+ "\", " + selected + ");\n";

		}

		return (js);

	}

	/**
	 * Gets Array of String locations
	 * @param list containing locations
	 * @return Array
	 */
	public String[] getLocations(List list) {

		Map locations = new HashMap();
		for (int i = 0; list != null && i < list.size(); i++) {
			DbBookMarkLocation bookMarkLocation = (DbBookMarkLocation) list.get(i);
			locations.put(new Integer(bookMarkLocation.getLocationId()).toString(), new Integer(
					bookMarkLocation.getLocationId()).toString());
		}

		return (String[]) locations.keySet().toArray(new String[locations.keySet().size()]);

	}

	/**
	 * Gets Array of String locale
	 * @param list containing locale
	 * @return Array
	 */
	public String[] getLocale(List list) {

		Map locale = new HashMap();
		for (int i = 0; list != null && i < list.size(); i++) {
			DbBookMarkLocation bookMarkLocation = (DbBookMarkLocation) list.get(i);
			locale.put(new Integer(bookMarkLocation.getLocaleId()).toString(), new Integer(bookMarkLocation
					.getLocaleId()).toString());
		}

		return (String[]) locale.keySet().toArray(new String[locale.keySet().size()]);
	}

	public String getType(List list){
		boolean isAll = false;
		boolean isLocaleSelection = false;
		for (int i = 0; list != null && i < list.size(); i++) {
			DbBookMarkLocation bookMarkLocation = (DbBookMarkLocation) list.get(i);
			if(bookMarkLocation.getLocaleId() == Constants.GLOBAL_LOCALE &&
					bookMarkLocation.getLocationId() == Constants.GLOBAL_LOCATION){
				isAll = true;
				break;
			}else if(bookMarkLocation.getLocationId() == Constants.GLOBAL_LOCATION){
				isLocaleSelection = true;
			}
		}
		if(isAll) return Constants.GLOBAL_LOCALE_STRING;
		else if(isLocaleSelection) return Constants.GLOBAL_LOCATION_STRING;
		return Constants.LOCATION_SELECTION;
	}
	
}
