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
import com.aldorsolutions.webfdms.beans.custom.CustomDataItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CustomForm;


public class ShowCustom extends Action {
    
    private Logger logger = Logger.getLogger(ShowCustom.class.getName());
    
    /**
     * Called from status window or case list, this action prepares to show a
     * list of user defined fields.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DatabaseTransaction t =null;
        DbCustom customdata = null;
        DbSpeedData[] headings = null;
        
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid=0;
        java.util.ArrayList longCustom = null;
        java.util.ArrayList shortCustom = null;
        fdms.ui.struts.form.CustomForm custform = new CustomForm();
        
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        } else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid<1){
                //AppLog.warning("ShowCustom. Invalid VitalsID to process:"+vitalsid);
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            }
        }
        //AppLog.trace("ShowCustom for ID:"+vitalsid);
        
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            longCustom = new ArrayList();
            shortCustom = new ArrayList();
            
            customdata = FdmsDb.getInstance().getCustom( t , vitalsid );
            // If no custom data exists for this person, make a blank object
            if (customdata==null){
                customdata = new DbCustom();
                customdata.setMainKey(vitalsid);
            }
            // Make indexes for retrieving headings
            String keybase = "cust_hd_";
            String key=null;
            String custlabel = null;
            // Create collection of custom data
            for(int i=0 ; i < 40 ; i++)	{
                // get label for short custom
                key = keybase + (i+1);
                headings = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), key);
                if (headings==null || headings.length < 1){
                    custlabel = "Heading "+(i+1);
                }
                else {
                    custlabel = headings[0].getData();
                }
                // create collection object
                CustomDataItem listItem = new CustomDataItem();
                listItem.setHeading(custlabel);
                listItem.setData(	customdata.getCustom(i));
                listItem.setTableName("custom"+String.valueOf(i+1));
                // store object in collection
                shortCustom.add(listItem);
                // get label for long custom
                key = keybase + (i+41);
                headings = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), key);
                if (headings==null || headings.length < 1){
                    custlabel = "Heading "+(i+41);
                }
                else {
                    custlabel = headings[0].getData();
                }
                // create collection object
                listItem = new CustomDataItem();
                listItem.setHeading(custlabel);
                listItem.setData(	customdata.getCustomLong(i));
                listItem.setTableName("custom"+String.valueOf(i+41));
                // store object in collection
                longCustom.add( listItem );
                //longCustom.add(  new OptionsList(customdata.getCustomLong(i), custlabel));
            }
            
            // Store data needed for JSP in form object
            custform.setLongCustom( longCustom);
            custform.setShortCustom(shortCustom);
            
            // Determine if should allow editing headings
            if (sessionUser.getSecurityAdmin() > 0){
                custform.setEnableHeadings("true");
            }
            else {
                custform.setEnableHeadings("false");
            }

        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowPayment.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ShowPayment.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        //Place Objects In Scope
        session.setAttribute("custom",custform);
        
        return mapping.findForward("custom");
    }
}
