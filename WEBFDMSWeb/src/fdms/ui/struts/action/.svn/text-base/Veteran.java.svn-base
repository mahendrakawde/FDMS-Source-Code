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

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionValueKeys;



public class Veteran extends Action {

    private Logger logger = Logger.getLogger(Veteran.class.getName());
 
  public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	  
	ActionErrors errors = new ActionErrors();
	HttpSession session = request.getSession();
	DatabaseTransaction t = null;
   	DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
	ArrayList formsList = new ArrayList();
   	int vitalsid = FormatNumber.parseInteger(request.getParameter("vitalsId"));
   	
   	//AppLog.trace("Veteran VitalsId param:"+request.getParameter("vitalsId")+":"+vitalsid);
   	//AppLog.trace("UserSession.Vitalsid:"+sessionUser.getCurrentCaseID());
	if(vitalsid > 1)	{
		sessionUser.setCurrentCaseID(vitalsid);
	}    
	else {    
		vitalsid = sessionUser.getCurrentCaseID();
	}
   	//AppLog.trace("Veteran VitalsId param:"+request.getParameter("vitalsId")+":"+vitalsid);
   	//AppLog.trace("UserSession.Vitalsid:"+sessionUser.getCurrentCaseID());
	
	//Database Access Logic 
	try{
	   // Get list of VA forms
	   t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	   com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list = FdmsDb.getInstance().getFormsAvailableForLocale(t,sessionUser.getRegion(),DbFormsAvailable.VA_TYPE); 
	   for (int i=0; i<list.length; i++){
		  int formid = list[i].getFormId();
		  formsList.add( new OptionsList(Integer.toString(formid) ,list[i].getDescription()));
	   }	
	} catch(PersistenceException pe) { 
	   logger.error("Persistence Exception in Veteran.doPerform. " + pe);
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	// Check if print preview already exists in request.
 	// If not, make an empty one
 	com.aldorsolutions.webfdms.util.ReportPreview preview = (com.aldorsolutions.webfdms.util.ReportPreview)request.getAttribute("ReportPreview");
 	if (preview==null){
 		ShowCaseStatusForms.addEmptyReportPreviews(request);
 	}

 	session.setAttribute("VAForms", formsList);
	return mapping.findForward("veteran");

  }        
}
