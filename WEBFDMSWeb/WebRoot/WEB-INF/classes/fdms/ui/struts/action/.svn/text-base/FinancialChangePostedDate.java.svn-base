package fdms.ui.struts.action;

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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.TransactionhistoryDAO;
import com.aldorsolutions.webfdms.beans.TransactionhistoryDTO;
import com.aldorsolutions.webfdms.beans.custom.FinancialAddServicesLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialChangePostedDateForm;
import fdms.ui.struts.form.FinancialInformationForm;



public class FinancialChangePostedDate extends Action {
    
    private Logger logger = Logger.getLogger(FinancialChangePostedDate.class.getName());

/**
* This Action is called from FinancialInformation.JSP when the operator
* wants to add new items from the price list to the current case's contract.
* FinancialAddServices.JSP is used to show a list of available service
* items from which the operator chooses.
*/
public ActionForward execute(
        ActionMapping mapping,
	ActionForm actionForm,
	HttpServletRequest request,
        HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException {
    
	ActionErrors errors = new ActionErrors();

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	
	int vitalsid=0;
	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	else {
   		vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		if (vitalsid<1){
			//AppLog.warning("AddServices. Invalid VitalsID to process:"+vitalsid);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
		}
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
   		// AppLog.info("AddServices Invoking forward mapping getInput() ");
		saveErrors(request, errors );
		return (new ActionForward(mapping.getInput() ));
	}
	  
   	TransactionhistoryDTO tran = new TransactionhistoryDTO();
   	TransactionhistoryDAO tranDao = new TransactionhistoryDAO(sessionUser);
   	
   	tran = tranDao.getPostedDate(vitalsid);
   	
   	String fromPostedDate = FormatDate.convertDateToMM_DD_YYYY((java.util.Date)tran.getDateOfTran());
   	String toPostedDate = FormatDate.convertDateToMM_DD_YYYY((java.util.Date)tran.getDateOfTran());
   	
   	FinancialChangePostedDateForm form = (FinancialChangePostedDateForm) actionForm;
   	
   	if (form == null) {
		form = new FinancialChangePostedDateForm();
	}
   	
   	form.setFromPostedDate((fromPostedDate));
   	form.setToPostedDate((toPostedDate));

	return mapping.findForward("financialChangePostedDate");
  }  
}
