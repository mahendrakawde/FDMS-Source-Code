package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.BookMarkAdminForm;

/**
 * ShowUserAdmin Prepares form bean with list of users. Creation date:
 * (5/13/2002 4:13:09 PM)
 * 
 * @author:
 */
public class BookMarkAdminAction extends Action {

	private Logger logger = Logger.getLogger(BookMarkAdminAction.class.getName());

	/**
	 * Gobal Action, this action prepares to show BookMarkAdmin.JSP. The
	 * BookMarkForm Allows the operator to select the BookMark to be modified or
	 * click add or delete.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		DatabaseTransaction t = null;
		BookMarkAdminForm bookMarkAdminForm;

		logger.debug("BookMarkAdminAction.doPerform");
		ActionErrors errors = new ActionErrors();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		java.util.List bookMarkList = new java.util.ArrayList();

		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
		}

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			bookMarkList = FdmsDb.getInstance().getBookMarks(t, 0, 0);
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in BookMarkAdminAction.doPerform. " + pe);
		} catch (Exception pe) {
			logger.error("Exception in BookMarkAdminAction.doPerform. ", pe);
		} finally {
			if (t != null){
				t.closeConnection();
				t = null;
			}
		}

		// Check for any errors so far
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		if (form == null) {
			bookMarkAdminForm = new BookMarkAdminForm();
		} else {
			bookMarkAdminForm = (BookMarkAdminForm) form;
		}
		bookMarkAdminForm.setBookMarkList(bookMarkList);
		return mapping.findForward("bookMarkAdmin");
	}
}
