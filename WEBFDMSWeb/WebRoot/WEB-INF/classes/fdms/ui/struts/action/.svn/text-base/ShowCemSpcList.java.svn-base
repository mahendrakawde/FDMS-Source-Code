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

import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.beans.DbCemSpcCase;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.caseset.bean.CemSetSpcManagerBean;
import fdms.ui.struts.form.CemSpcCaseForm;

public class ShowCemSpcList extends Action {

	private Logger logger = Logger.getLogger(ShowCemSpcList.class.getName());
	public Character spaceType;
	
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		logger.debug("***** Entering show case list *****");
		
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;

		ArrayList caseListForm = null;		
						
		CemSetSpcManagerBean caseSecListManagerBean = new CemSetSpcManagerBean();
		UserManagerBean userManager = new UserManagerBean();
	
		String[] locationIds = userManager.getUserLocationIds(sessionUser
				.getId(), sessionUser.getRegion());
			
		if ( locationIds == null ) {
			locationIds = new String [0];
		}

		logger.debug("Location IDs: " + locationIds);
										
		try {
			// Get a new database transaction.
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);

			// Use the transaction to retrieve the case set.
			ArrayList caseList = caseSecListManagerBean.getCaseList(sessionUser.getDbLookup(),
					sessionUser,spaceType);
							
			caseListForm = new ArrayList();
				
			for (int i = 0; i < caseList.size(); i++) {
				DbCemSpcCase dbCemSpcCase = (DbCemSpcCase) caseList.get(i);
				CemSpcCaseForm cemSpcListCase = new CemSpcCaseForm();
				cemSpcListCase.setSpaceID(dbCemSpcCase.getSpaceID());
				cemSpcListCase.setType(dbCemSpcCase.getType());
				cemSpcListCase.setTypeNumber(dbCemSpcCase.getTypeNumber());
				cemSpcListCase.setParentID(dbCemSpcCase.getParentID());
				cemSpcListCase.setTypeName(dbCemSpcCase.getTypeName());
				cemSpcListCase.setDesc(dbCemSpcCase.getDesc());
									
				caseListForm.add(cemSpcListCase);
				
			}

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

		session.setAttribute("caseList", caseListForm);
		session.setAttribute("beginCaseListCursor", new Integer(0));
		session.setAttribute("endCaseListCursor", new Integer(caseListForm
					.size()));
				    
		Character actForwSecTest = new Character('S');
		if(spaceType == actForwSecTest){
			return mapping.findForward("cemShowSecListJsp");
		}
		else{
			//change later
			return mapping.findForward("cemShowSecListJsp");
		}
	}
	
	public void SetSpaceType(Character spcPass ){
		spaceType = spcPass;
		return;	
	}
		
		
}

