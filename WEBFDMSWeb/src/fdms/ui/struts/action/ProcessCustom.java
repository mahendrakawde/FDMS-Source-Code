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

import com.aldorsolutions.webfdms.beans.DbCustom;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CustomForm;


/**
 * This action class processes the form from custom data.
 */
public class ProcessCustom extends Action {
    
    private Logger logger = Logger.getLogger(ProcessCustom.class.getName());

    /**
     * This action handles the form from CUSTOM.JSP.
     * Multiple submit buttons are handled.
     */
    public ActionForward execute(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws javax.servlet.ServletException, java.io.IOException {
        
        CustomForm form = (CustomForm) actionForm;
        //AppLog.trace("ProcessCustom doPerform starting");
        // get multiple DATA fields from <html:iterate/>
        ArrayList datafields = new ArrayList();
        for (int i = 0; i<40; i++) {
        	datafields.add(request.getParameter("shortCustomItemData[" + i + "]"));
        	
        }
        for (int i = 40; i<80; i++) {
        	datafields.add(request.getParameter("longCustomItemData[" + i + "]"));
        }
        //String[] datavals = request.getParameterValues("data");
        //for (int x=0; x<10; x++){
        //}
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbCustom customdata = null;
        int vitalsid=  0;
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        } else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid<1){
                //AppLog.warning("ShowFinancial. Invalid VitalsID to process:"+vitalsid);
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            }
        }
        //AppLog.trace("ProcessCustom directive: "+form.getDirective());
        try {
            if( form.getDirective().equals("cancel") )	{
                session.removeAttribute("custom");
                return mapping.findForward("showCaseStatusGlobal");
            }
            else if( form.getDirective().equals("help") ){
                return mapping.findForward("usingHelp");
            }
            else if ( form.getDirective().compareTo("save")!=0 ){
                //AppLog.error("ProcessCustom unrecognized directive:"+form.getDirective());
                saveErrors(request, errors );
                return ( new ActionForward(mapping.getInput() ));
            }
            // Handle "SAVE" operation
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            customdata = FdmsDb.getInstance().getCustom( t , vitalsid );
            if (customdata==null){
                // adding new row to table
                //AppLog.trace("ProcessCustom: Adding custom data row for:"+vitalsid);
                customdata = new DbCustom();
                customdata.setNew();
                customdata.setMainKey(vitalsid);
                t.addPersistent(customdata);
            }
            for (int i=0; i<datafields.size(); i++){
                if (i<40){
                    customdata.setCustom(i, datafields.get(i).toString());
                }
                else if (i<80){
                    customdata.setCustomLong(i-40, datafields.get(i).toString());
                }
            }
            // update headings
            String[] newheadings = request.getParameterValues("heading");
            //AppLog.trace("ProcessCustom headings:"+newheadings);
            if (newheadings!= null){
                //AppLog.trace("ProcessCustom headings length:"+newheadings.length);
                if (newheadings.length == 80){
                    updateHeadings(t, sessionUser, newheadings);
                }
            }
            t.save();
            //AppLog.trace("Process Custom finished.");
        } catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in ProcessApVendor.doPerform. "+pe.getMessage());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            // AppLog.criticalError("Exception in ProcessApVendor .doPerform. "+pe);
            // pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                    t = null;
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        ActionForward actionForward = mapping.findForward("showCaseStatusGlobal");
        if( !errors.isEmpty() )  {
            //AppLog.info("ProcessCustom Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        return  actionForward;
    }
    /**
     * Update custom headings from custom data form.
     * Creation date: (7/11/2002 4:01:31 PM)
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @exception PersistenceException database error.
     */
    private void updateHeadings(DatabaseTransaction t, DbUserSession sessionUser, String[] newheadings) throws PersistenceException {
        DbSpeedData[] headings = null;
        DbSpeedData  speedrec = null;
        
        // Make indexes for retrieving headings
        String keybase = "cust_hd_";
        String key=null;
        String custlabel = null;
        // Create collection of custom data
        for(int i=0 ; i < newheadings.length ; i++)	{
            // get label for short custom
            key = keybase + (i+1);
            headings = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), key);
            if (headings==null || headings.length < 1
            || (sessionUser.getRegion()>0 && headings[0].getLocale()==0)){ // insert for user's region instead of updating region 0
                // Add new heading for this key
                speedrec = new DbSpeedData();
                speedrec.setNew();
                speedrec.setLocale(sessionUser.getRegion());
                speedrec.setCategory(key);
                speedrec.setData(newheadings[i]);
                t.addPersistent(speedrec);
            }
            else if (headings[0].getData().compareTo(newheadings[i]) != 0){
                // update headings for this key
                headings[0].setData( newheadings[i] );
                t.addPersistent(headings[0]);
            }
        }
        
    }
}
