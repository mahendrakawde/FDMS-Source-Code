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

import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ChangePriceListForm;

public class ProcessPnChangePriceList extends Action {

    private Logger logger = Logger.getLogger(ProcessPnChangePriceList.class.getName());

/**
 * Called from PnCharges.JSP, this action Takes the selected
 * package and changes the price list version for this case.
 */
  public ActionForward execute(ActionMapping mapping,
	ActionForm actionForm,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	//AppLog.trace("ProcessPnChangePriceList beginning."+form.getVitalsId());
      
      ChangePriceListForm form = (ChangePriceListForm) actionForm;
	ActionErrors errors = new ActionErrors();
   	DatabaseTransaction t =null;
   	DbPnContract		acontract = null;

   	int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
	int contractid = FormatNumber.parseInteger(form.getContractId());
	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
	   	saveErrors(request, errors);
		return new ActionForward(mapping.getInput());
	}
	
	// Prepare to show PnCharges page
	try {
		// Make sure PriceChange was selected.
		if (form.getPriceChange() != null && form.getPriceChange().trim().length() > 0) {
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
			acontract = FdmsDb.getInstance().getPnContract(t, contractid);
			acontract.setPriceListVersion(	form.getPriceChange() )	;
			t.save();
		}
		ProcessPnStatus.preparePnCharges(errors,request,vitalsid, contractid, (DbUser) sessionUser);
	} catch (PersistenceException pe) { 
	   logger.error("Persistence Exception in ProcessPnAddMerchandise do.Perform. " + pe);
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe));
    } finally {
       if (t != null) t.closeConnection();
    }
	
	if (!errors.isEmpty()) {
	   	saveErrors(request, errors);
	}
	
	return mapping.findForward("ShowPnChargesJspGlobal");
  }          
}
