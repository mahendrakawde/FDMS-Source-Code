package fdms.ui.struts.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.aldorsolutions.webfdms.arrangers.dao.ArrangersFieldDAO;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.ReportPreview;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.Translator;
import com.sun.xml.ws.rm.jaxws.runtime.Session;

import fdms.ui.struts.form.CaseStatusForm;
import fdms.ui.struts.form.ShowArrangerFieldsForm;

public class ShowArrangerFieldsAction extends Action {

	private Logger logger = Logger.getLogger(ShowArrangerFieldsAction.class
			.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		DbUserSession sessionUser = SessionHelpers.getUserSession(request);		
		ShowArrangerFieldsForm showArrangersFieldsForm = (ShowArrangerFieldsForm) form;
		ArrangersFieldDAO arrangerFieldDao = new ArrangersFieldDAO();

		try {
			showArrangersFieldsForm.setArrangerMainCatagory(arrangerFieldDao.getArrangersFieldMainCategory(sessionUser.getDbLookup()));
			
			showArrangersFieldsForm.setArrangerSubCatagory(arrangerFieldDao.getArrangersFieldSubCategory(sessionUser.getDbLookup()));
			
			showArrangersFieldsForm.setArrangerCatagory(arrangerFieldDao.getArrangersFieldCategory(sessionUser.getDbLookup()));
			
		//	String test[] = arrangerFieldDao.getSelectedArrangers(sessionUser.getDbLookup());
			showArrangersFieldsForm.setSelectedItems(arrangerFieldDao.getSelectedArrangers(sessionUser.getDbLookup()));
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("showArrangerFields");

	}

}
