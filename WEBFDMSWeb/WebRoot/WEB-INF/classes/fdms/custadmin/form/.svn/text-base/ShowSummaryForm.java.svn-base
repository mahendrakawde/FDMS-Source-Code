/*
 * Created on Dec 23, 2005
 */
package fdms.custadmin.form;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.database.framework.Database;
import com.aldorsolutions.webfdms.database.framework.MySQLDatabase;
import com.aldorsolutions.webfdms.database.framework.MySQLDatabaseException;
import com.aldorsolutions.webfdms.database.framework.Table;
import com.aldorsolutions.webfdms.database.framework.TableException;

/**
 * @author Ranando
 *
 * Don't go looking for "ProcessSummaryForm". 
 * There isn't one. This form is view only!
 */
public class ShowSummaryForm extends Action {

    private Logger logger = Logger.getLogger(ShowSummaryForm.class.getName());
    
	public ShowSummaryForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	/**
    	 * Here we need to shove a ton of crap into the request variable
    	 * for the summary form to display. There's everything from the
    	 * business addresses to the individual user information to deal
    	 * with.
    	 */
    	
		HttpSession session = request.getSession();
    	String user = session.getAttribute("SiteUserName").toString();
		String pass = session.getAttribute("SitePassword").toString();

    	Table contracts = null;
    	
    	
    	try {
	    	Database db = MySQLDatabase.openDatabase(user, pass, true, "contract", contracts);
	    	
	    	db.getUserSetting("CompanyID");
	    	contracts.setConditions("");
	    	contracts.Select();
    	} catch (MySQLDatabaseException e) {
    		logger.debug("MySQLDatabaseException: ", e);
    	} catch (TableException e) {
    		logger.debug("TableException: ", e);
    	} catch (Throwable e) {
    		logger.debug("Throwable: ", e);
    	}
    	
    	return mapping.findForward("showSummary");
    }                       
}
