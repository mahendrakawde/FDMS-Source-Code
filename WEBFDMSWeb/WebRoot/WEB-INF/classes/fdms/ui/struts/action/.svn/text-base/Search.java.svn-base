package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.DbSurvivorDisplayList;
import fdms.ui.struts.form.SearchSurvivorsForm;

public class Search extends Action {

	private Logger logger = Logger.getLogger(Search.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		ActionErrors errors = new ActionErrors();
		DatabaseTransaction t = null;
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		
		String isCheckWriter = (String) request.getAttribute("checkWriterSearch");
		
		
		logger.debug("Performing Search Action. user=" + sessionUser);

		// instantiate form bean
		SearchSurvivorsForm survform = new SearchSurvivorsForm();
		survform.setMaxLimit("999");
		survform.setCaseCode(false);
		survform.setContractNo(false);
		survform.setDeceased(true);
		survform.setInformant(false);
		survform.setPreneed(false);
		survform.setSurvivors(false);
		survform.setCheckWriterSearch( Boolean.parseBoolean(isCheckWriter) );

		try {
			// Check if we are pre-need only then make pre-need true also
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			LocaleDTO region = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
			
			if (region != null
					&& region.getAtneedLicense().compareToIgnoreCase("N") == 0) {
				survform.setPreneed(true);
			}
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in Search action. " + pe);
		} finally {
			if (t != null)
				t.closeConnection();
		}

		request.setAttribute("searchSurvivors", survform);
		ArrayList displayList = new DbSurvivorDisplayList();
		request.setAttribute("displayList", displayList);
		request.setAttribute("fromSearch", "false");
		
		// Report any errors we have discovered back to the original form
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		return mapping.findForward("search");
	}
}
