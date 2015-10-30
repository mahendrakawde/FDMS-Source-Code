package fdms.ui.struts.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.AccountDistributionItem;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.CheckWriterForm;

/**
 * ShowApCheckWriter Prepares form bean with DBMS data needed
 * to show the check writer JSP page.
 * Creation date: (5/06/2002 4:13:09 PM)
 * @author: 
 */
public class ShowApCheckWriter extends Action {

    private Logger logger = Logger.getLogger(ShowApCheckWriter.class.getName());
    
/**
 * Fetch data from DBMS for select tags. This action is called from a
 * global forward and displays ApCheckWriter.JSP.
 * Creation date: (5/06/2002 3:29:15 PM)
 */
  public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	//AppLog.trace("ShowCheckWriter action doPerfrom");
   	ActionErrors errors = new ActionErrors();
   	HttpSession session = request.getSession();
	CheckWriterForm		checkform = new CheckWriterForm();
	
	DbUserSession sessionUser =  SessionHelpers.getUserSession(request);

        DatabaseTransaction t = null;
	try {
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		checkform.setCheckDate( FormatDate.getCurrentDateFormatedMMDDYYYY() );
		checkform.setAccount("0");
		checkform.setLocation("0");
		checkform.setVendor("0");
		checkform.setFocus("location");
		// create empty account distribution list
		checkform.setAccountDistributionList( new ArrayList <AccountDistributionItem>());
		checkform.setIsOneTimeVendor("false");
		session.setAttribute( "checkWriterForm", checkform );
		
		
		DbLocation[] userLocations = null;
		userLocations = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
		ArrayList<Long> locationAllIDs = new ArrayList<Long>();
		UserManagerBean userManagerBean = new UserManagerBean();
		String sLocationIDs[] = userManagerBean.getUserLocationIds( sessionUser.getId() );
		
		for(DbLocation location: userLocations ){
			for(int i = 0; i < sLocationIDs.length; i++){
				if (location.getId() == Integer.valueOf(sLocationIDs[i])) {
					locationAllIDs.add( (long)location.getId());
					break;
				}
			}
		}	
		LocationDAO locationDao = new LocationDAO(sessionUser);
		ArrayList <LocationDTO> locations = locationDao.getLocationsByLocationIDs(locationAllIDs);
		session.setAttribute("locationListAll", locations);		
		
		// get the vender for user
		UserManagerBean uMgr = new UserManagerBean();
		ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(sessionUser);

		ArrayList <ApVendorLocationDTO> apVendorLocationListAll = apVendorLocationDao.getApVendorLocationByLocationIDs(locationAllIDs);
		String [] jsVendorIDs = { String.valueOf(0) }; 
		String js = uMgr.createLocationWithVendorJavascript ( apVendorLocationListAll, jsVendorIDs, sessionUser );
		checkform.setLocationVendorMapJavascript(js);
		
		ArrayList<Long> vendorIDs = new ArrayList<Long>();
		for ( ApVendorLocationDTO apVendorLocation : apVendorLocationListAll ) {
			vendorIDs.add(apVendorLocation.getVendorID());
		}
		ApVendorDAO vendorDao = new ApVendorDAO(sessionUser);
		ArrayList <ApVendorDTO> vendorList = vendorDao.getApVendorByVendorIDs(true,vendorIDs);
		
		if (vendorList.size() == 1) {
			checkform.setVendorID((int)((ApVendorDTO)vendorList.get(0)).getVendorID());
		}		
		//ArrayList <ApVendorDTO> vendorList = new ArrayList <ApVendorDTO> ();
		session.setAttribute("vendorListAll", vendorList);
		
		
		// Set collections in session
		SessionHelpers.setChapelListInSession(request);
			// Now check and see how many chapels we have and if we have only one
			// then set it.
		ArrayList chapels = (ArrayList)session.getAttribute("chapelList");


		SessionHelpers.setVendorListInSession(request,sessionUser.getRegion());


		SessionHelpers.setApAccountListInSession(request,0) ;
			// Now check and see how many chapels we have and if we have only one
			// then set it.
		ArrayList accounts = (ArrayList)session.getAttribute("accountList");
		if (accounts.size() == 1) {
			checkform.setAccount(((OptionsList)accounts.get(0)).getListValue());			
		}
		
		CompanyOptionsManager coMgr = new CompanyOptionsManager ();
		int companyID = sessionUser.getCompanyID();
		int checkApprovalRqd = coMgr.getCompanyOptionValueForCompany(companyID, CompanyOptionsDTO.COMPANY_OPTION_CHECK_APPROVAL_REQUIRED);
		int checkUseLimits   = coMgr.getCompanyOptionValueForCompany(companyID, CompanyOptionsDTO.COMPANY_OPTION_CHECK_VENDOR_LIMITS);
		checkform.setCheckApprovalRequired( checkApprovalRqd == 1 );
		checkform.setCheckUseLimits( checkUseLimits == 1 ); 
		checkform.setSumDistributions("0");
		
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowCheckWriter.doPerform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in  ShowCheckWriter .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
    } finally {
        if (t != null) t.closeConnection();
    }
        
	// Check for any errors so far
	if( !errors.isEmpty() )   {
		saveErrors(request, errors );
   	}
	return mapping.findForward("showApCheckWriterJsp");

  }      
  
  
  
}
