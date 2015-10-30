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

import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.Translator;

import fdms.ui.struts.form.PallBearersForm;

public class ProcessPallBearers extends Action {
    
    private Logger logger = Logger.getLogger(ProcessPallBearers.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        //AppLog.info("ProcessPallBearers action starting.");
        
        PallBearersForm form = (PallBearersForm) actionForm;
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward= null;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        int vitalsid = 0;
        
        // See if vitalsId is set in form
        try {
            if (form.getVitalsId() != null && Integer.parseInt(form.getVitalsId()) > 0) {
                vitalsid = Integer.parseInt(form.getVitalsId());
                SessionHelpers.setVitalsIdInRequest(request, vitalsid);
            }
        } catch (Exception e) {
            logger.debug("Exception : ", e);
        }
        
        // Get Vitals Case ID
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid < 1) {
            //AppLog.warning("ProcessPallBearers. Invalid VitalsID to process:"+vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        if (form.getDirective().equals("cancel")) {
            return mapping.findForward("closeWindow");
        }
        
        if (form.getDirective().equals("help")) {
            return mapping.findForward("usingHelp");
        }
        
        // If errors found, bail out and return to input page
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessPallbearers Invoking forward mapping getInput().");
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        
        //AppLog.trace("ProcessPallbearers processing: "+vitalsid);
        
        //AppLog.trace("ProcessPallbearers directive:"+ form.getDirective());
        
        // continue with attempt to save changes
        // Check each of the 8 pall bearers and 8 honorary bearers for data
        // and save if data is present.
        updateBearer(sessionUser,vitalsid,1005,form.getPallBearer1Name(), form.getPallBearer1Street(), form.getPallBearer1CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1006,form.getPallBearer2Name(), form.getPallBearer2Street(), form.getPallBearer2CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1007,form.getPallBearer3Name(), form.getPallBearer3Street(), form.getPallBearer3CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1008,form.getPallBearer4Name(), form.getPallBearer4Street(), form.getPallBearer4CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1009,form.getPallBearer5Name(), form.getPallBearer5Street(), form.getPallBearer5CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1010,form.getPallBearer6Name(), form.getPallBearer6Street(), form.getPallBearer6CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1011,form.getPallBearer7Name(), form.getPallBearer7Street(), form.getPallBearer7CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1012,form.getPallBearer8Name(), form.getPallBearer8Street(), form.getPallBearer8CityStateZip(),"Pall Bearer",errors);
        updateBearer(sessionUser,vitalsid,1013,form.getHonoraryPallBearer1Name(), form.getHonoraryPallBearer1Street(), form.getHonoraryPallBearer1CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1014,form.getHonoraryPallBearer2Name(), form.getHonoraryPallBearer2Street(), form.getHonoraryPallBearer2CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1015,form.getHonoraryPallBearer3Name(), form.getHonoraryPallBearer3Street(), form.getHonoraryPallBearer3CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1016,form.getHonoraryPallBearer4Name(), form.getHonoraryPallBearer4Street(), form.getHonoraryPallBearer4CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1017,form.getHonoraryPallBearer5Name(), form.getHonoraryPallBearer5Street(), form.getHonoraryPallBearer5CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1018,form.getHonoraryPallBearer6Name(), form.getHonoraryPallBearer6Street(), form.getHonoraryPallBearer6CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1019,form.getHonoraryPallBearer7Name(), form.getHonoraryPallBearer7Street(), form.getHonoraryPallBearer7CityStateZip(),"Honorary Bearer",errors);
        updateBearer(sessionUser,vitalsid,1020,form.getHonoraryPallBearer8Name(), form.getHonoraryPallBearer8Street(), form.getHonoraryPallBearer8CityStateZip(),"Honorary Bearer",errors);
        
        //Action Forward Logic
        actionForward= mapping.findForward("closeWindow");
        
        // if errors found, return to input screen without saving anything
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessPallBearers found validation errors. Invoking forward mapping getInput().");
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        
        return  actionForward;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/20/2002 2:46:35 PM)
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param vitalsid int
     * @param seq short
     * @param name java.lang.String
     * @param street java.lang.String
     * @param zip java.lang.String
     * @param relation java.lang.String
     */
    public void updateBearer(DbUserSession sessionUser, int vitalsid, int seq, String name, String street, String citystatzip, String relation, ActionErrors errors) {
        
        DatabaseTransaction t = null;
        DbSurvivor bearer = null;
        DbSurvivor[] bearerlist	= null;
        
        /*
        if (name.length() < 1){
            return;
        }
         */
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            bearerlist = FdmsDb.getInstance().getSurvivorsForSequenceNo(t, vitalsid, seq);
            if (bearerlist.length < 1) {
                // add a new pall bearer
                bearer = new DbSurvivor();
                bearer.setNew();
                bearer.setLSurvivorMainKey(vitalsid);
                bearer.setISeqKey((short)seq);
                t.addPersistent(bearer);
            }
            else {
                bearer = bearerlist[0];
                t.addPersistent(bearer);
            }
            
            String checkData = name;
            if (checkData != null && checkData.trim().length() > 0) {
                bearer.setCSurvivorFName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getFirstName());
                bearer.setCSurvivorLName(new com.aldorsolutions.webfdms.util.ConvertNameString("FL",checkData).getLastName());
            } else {
                bearer.setCSurvivorFName("");
                bearer.setCSurvivorLName("");
            }
            bearer.setCSurvivorAddr(street);
            StringBuffer city = new StringBuffer();
            StringBuffer state = new StringBuffer();
            StringBuffer zip = new StringBuffer();
        	if(citystatzip != null){
        		Translator.parseCityStateZip(citystatzip, city, state, zip);
        	}
            bearer.setCSurvivorCity(city.toString());
            bearer.setCSurvivorState(state.toString());
            bearer.setCSurvivorZip(zip.toString());
            bearer.setCSurvivorRelated(relation);
            t.save();
        }
        catch(Exception pe) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.pallbearer",pe.getMessage(),name));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
    }
}
