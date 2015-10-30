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

import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.Translator;

import fdms.ui.struts.form.PallBearersForm;

public class ShowPallBearers extends Action {
    
    private Logger logger = Logger.getLogger(ShowPallBearers.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        // AppLog.trace("ShowPallBearers doPerform beginning");
        
        PallBearersForm form = (PallBearersForm) actionForm;
        ActionErrors 		errors 		= new ActionErrors();
        ActionForward 		actionForward = null;
        HttpSession 		session		= request.getSession();
        DbUserSession 		sessionUser	= SessionHelpers.getUserSession(request);
        form 		= new PallBearersForm();
        DbSurvivor[]	 	bearers		= null;
        DbVitalsDeceased  	deceased 	= null;
        DatabaseTransaction t 			= null;
        int vitalsid = 0;
        
        // Check for a DbUserSession
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        // See if the vitalsId has been sent as a parameter
        String vitalsParam = request.getParameter("vitalsId");
        //AppLog.trace("Show the vitalsId param = " +vitalsParam);
        if (vitalsParam != null && vitalsParam.trim().length() > 0) {
            vitalsid = FormatNumber.parseInteger(vitalsParam);
            SessionHelpers.setVitalsIdInRequest(request,vitalsid);
        } else {
            vitalsid = 0;
        }
        
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid < 1) {
            //AppLog.warning("ShowPallBearers. Invalid VitalsID to process:"+vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            //AppLog.info("ShowPallBearers Invoking forward mapping getInput().");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        //AppLog.trace("ShowPallBearers processing: "+vitalsid);
        
        //Database Access Logic
        try{
            //Make Calls To Retrieve Db Objects
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            form.setVitalsId(String.valueOf(vitalsid));
            bearers = FdmsDb.getInstance().getPallBearers(t,vitalsid);
            deceased = FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
            
            //get Locale
            int showAddressInt  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, sessionUser.getRegion(), 
					DbLocaleConfigType.CONFIG_SERVICES_SCREEN_SHOW_ADDRESS_PALLBEAR);
	
            
            form.setDeceasedFullName(deceased.getDecFullName());
            form.setDirective("change");
            form.setFlagShowPallBearerDetailAddress( (showAddressInt > 0) );
        	
            
            //Populate The Form Bean
            for (int i=0; i < bearers.length; i++) {
                if (bearers[i].getISeqKey()==1005){
                    form.setPallBearer1Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer1Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer1CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1006){
                    form.setPallBearer2Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer2Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer2CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1007){
                    form.setPallBearer3Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer3Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer3CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1008){
                    form.setPallBearer4Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer4Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer4CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1009){
                    form.setPallBearer5Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer5Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer5CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1010){
                    form.setPallBearer6Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer6Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer6CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1011){
                    form.setPallBearer7Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer7Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer7CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1012){
                    form.setPallBearer8Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setPallBearer8Street(bearers[i].getCSurvivorAddr());
                    form.setPallBearer8CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1013){
                    form.setHonoraryPallBearer1Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer1Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer1CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1014){
                    form.setHonoraryPallBearer2Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer2Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer2CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1015){
                    form.setHonoraryPallBearer3Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer3Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer3CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1016){
                    form.setHonoraryPallBearer4Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer4Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer4CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1017){
                    form.setHonoraryPallBearer5Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer5Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer5CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1018){
                    form.setHonoraryPallBearer6Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer6Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer6CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1019){
                    form.setHonoraryPallBearer7Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer7Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer7CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
                else if (bearers[i].getISeqKey()==1020){
                    form.setHonoraryPallBearer8Name(bearers[i].getCSurvivorFName()+" "+bearers[i].getCSurvivorLName());
                    form.setHonoraryPallBearer8Street(bearers[i].getCSurvivorAddr());
                    form.setHonoraryPallBearer8CityStateZip(Translator.appendCityStateZip(bearers[i].getCSurvivorCity(), bearers[i].getCSurvivorState(), bearers[i].getCSurvivorZip()));
                }
            }
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowPallBearers.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Add form to session
        session.setAttribute("pallbearerform",form);
        //Action Forward Logic
        actionForward = mapping.findForward("pallBearers");
        if( !errors.isEmpty() )  {
            //AppLog.info("ShowPallbearers Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        return  actionForward;
    }
}
