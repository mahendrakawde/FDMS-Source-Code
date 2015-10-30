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

import com.aldorsolutions.webfdms.beans.DbCustom;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;


public class ShowObituaryAddChange extends Action {

    private Logger logger = Logger.getLogger(ShowObituaryAddChange.class.getName());
    
  public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
{
	// AppLog.trace("ShowObitAddChanges doPerform beginning");
	fdms.ui.struts.form.ObitCustomData obitform = new fdms.ui.struts.form.ObitCustomData();
   	ActionErrors 		errors 		= new ActionErrors();
	ActionForward 		actionForward = null;
   	HttpSession 		session		= request.getSession();
   	DbUserSession 		sessionUser	= SessionHelpers.getUserSession(request);
   	DbCustom	 		custom		= null;
	DbVitalsDeceased  	deceased 	= null;
   	DatabaseTransaction t 			= null;
   	//Check for a DbUserSessioin
   	if(sessionUser==null){
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
   	int vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
	if (vitalsid<1){
		//AppLog.warning("ShowObitAddChange. Invalid VitalsID to process:"+vitalsid);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
   		//AppLog.info("ShowObitAddChange Invoking forward mapping getInput() ");
		saveErrors(request, errors );
		actionForward = new ActionForward(mapping.getInput() );
	}
	//AppLog.trace("ShowObitAddchange processing: "+vitalsid);
   
	//Database Access Logic 
	try{
		//Make Calls To Retrieve Db Objects
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		custom		= FdmsDb.getInstance().getCustom(t,vitalsid);
		deceased 	= FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
				
		obitform.setDeceasedFullName(	deceased.getDecFullName());
		obitform.setDirective("change");
		
		//Populate The Form Bean
		if (custom != null){
			obitform.setDeathDay(		custom.getCustom(2));
			obitform.setHowLongIll(		custom.getCustom(3));
			obitform.setMaidenName(		custom.getCustom(4));
			obitform.setBirthplace(		custom.getCustom(5));
			obitform.setResidences(		custom.getCustom(6));
			obitform.setSpouse(			custom.getCustom(7));
			obitform.setDateOfMarriage(	custom.getCustom(8));
			obitform.setMarriageInformation(	custom.getCustom(9));
			obitform.setRetired(		custom.getCustom(10));
			obitform.setClubs1(			custom.getCustom(11));
			obitform.setClubs2(			custom.getCustom(12));
			obitform.setPreceded(		custom.getCustom(13));
			obitform.setFamilyPresent(	custom.getCustom(14));
			obitform.setDaysAndTimes(	custom.getCustom(15));
			obitform.setEmployer(		custom.getCustom(16));
			obitform.setNewspaper(		custom.getCustom(17));
		}
	} catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowObitAddchange.doPerform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } finally {
            if (t != null) t.closeConnection();
        }
  
 	// Add form to session
	request.setAttribute("obituaryCustomData",obitform);

	//Action Forward Logic
	actionForward = mapping.findForward("obituaryAddChange");
	if( !errors.isEmpty() )  {
		//AppLog.info("ShowPallbearers Invoking forward mapping getInput() ");
		saveErrors(request, errors );
		actionForward = new ActionForward(mapping.getInput());
	}
	  return  actionForward;
}      
}
