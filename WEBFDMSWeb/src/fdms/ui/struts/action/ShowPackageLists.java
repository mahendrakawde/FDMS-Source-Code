package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.PackagePriceListsForm;


public class ShowPackageLists extends Action {

    private Logger logger = Logger.getLogger(ShowPackageLists.class.getName());

/**
 * Called from the WebFDMSManagement.jsp, this action handles the
 * Edit Packages link.  It creates a list of existing Price Lists
 * from the pricelist table and displays them for user selection.
 * @return ActionForward
 *
 * Created 07/30/02 14:02PM
 */ 
 
 public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	ActionErrors errors = new ActionErrors();
	HttpSession session = request.getSession();
	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
	DatabaseTransaction t = null;
	PackagePriceListsForm packagePriceLists = new PackagePriceListsForm();
	DbSpeedData[] dbSpeedData = null;
	java.util.ArrayList dbPriceList = null;
	java.util.ArrayList priceListVersions = new java.util.ArrayList();
	java.util.ArrayList packageItemsList = new java.util.ArrayList();

	logger.debug("Entering showPackagePriceLists.do.");
   
	//Database Access Logic
	try {
	   t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	   dbSpeedData = FdmsDb.getInstance().getSpeedData( sessionUser.getDbLookup(),sessionUser.getRegion(), "VERSIONS");
	   //AppLog.trace("dbSpeedData.VERSIONS length is " +dbSpeedData.length);
	   
	   // Set priceListVersions Array
	   for (int i=0; i < dbSpeedData.length; i++) {
		  String listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
		  String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 2);
	      priceListVersions.add(new OptionsList(listValue, listValue +" - " +listLabel));
	   }

	   // If the Speedata is not found for VERISONS, then default from priceList table.
	   if (dbSpeedData.length == 0) {
	      dbPriceList = new PriceListManager().getPriceListVersions(t, sessionUser.getRegion());
	      //AppLog.trace("Defaulting PriceListVersions from pricelist table.");
		  for (int i=0; i < dbPriceList.size(); i++) {
		     String listValue = (String)dbPriceList.get(i);
		     String listLabel = "No Speeddata setup for this Price List.";
	         priceListVersions.add(new OptionsList(listValue, listValue + " - " +listLabel));
		  }
	   }

	   // If no VERSIONS in Speeddata and no VERSIONS in pricelist, set array to no data found.
	   if (priceListVersions.size() == 0) {
	      priceListVersions.add(new OptionsList("0", "No Price List Versions Found."));
	   }
	   
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowPackagePriceLists.doPerform. " + pe);
	}
	catch(Exception pe) { 
		logger.error("Exception in ShowPackagePriceLists.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
	   
	//Place Objects In Scope
	session.setAttribute("packagePriceLists", packagePriceLists);
	session.setAttribute("priceListVersions", priceListVersions);
	session.setAttribute("packageItemsList",packageItemsList);
	//AppLog.trace("Setting priceLists form and priceListVersions array into scope session.");

	//Action Forward Logic
	ActionForward actionForward = mapping.findForward("packagePriceLists");
	
	if (!errors.isEmpty() ) {
	   //AppLog.info("ShowPackagePriceLists Invoking forward mapping getInput() ");
	   saveErrors(request, errors );
	   actionForward = new ActionForward(mapping.getInput() );
	}
	return  actionForward;
  }  
}
