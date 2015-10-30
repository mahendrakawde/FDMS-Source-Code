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

import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialChangePriceListForm;

public class FinancialChangePriceList extends Action {
    
    private Logger logger = Logger.getLogger(FinancialChangePriceList.class.getName());

    public ActionForward execute(
            ActionMapping mapping,            
            ActionForm actionForm,                                    
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        FinancialChangePriceListForm form = (FinancialChangePriceListForm) actionForm;
        // AppLog.trace("FinancialChangePriceList.doPerform()");
        java.util.ArrayList changePriceList = new java.util.ArrayList();
        form = new fdms.ui.struts.form.FinancialChangePriceListForm();
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        int vitalsid=0;
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid<1){
                // AppLog.warning("AddServices. Invalid VitalsID to process:"+vitalsid);
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            }
        }
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            // AppLog.info("AddServices Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
        
        //Database Access Logic
        
        DbSpeedData[] dbSpeedData = null;
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            fdms.ui.struts.form.FinancialInformationForm fiform =  (fdms.ui.struts.form.FinancialInformationForm) session.getAttribute("financialInformation");
            form.setPriceChange(fiform.getPriceListVersion()) ;
            // AppLog.trace("ChangePriceList: old version:"+fiform.getPriceListVersion());
            
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "VERSIONS");
            String listValue = "";
            String listLabel = "";	
            
            ArrayList versions = new PriceListManager().getPriceListVersions(t, sessionUser.getRegion());
            java.util.Iterator myloop = versions.listIterator();
            while (myloop.hasNext()){
                String aVersion = myloop.next().toString();
                
                listValue = "";
                listLabel = "No Speeddata setup for this Price List.";	
                if (dbSpeedData != null) {
                	for (int i=0; i < dbSpeedData.length; i++) {
                		listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
                        if (listValue.trim().compareToIgnoreCase(aVersion.trim()) == 0){
                        	listLabel = CsvTable.getField(dbSpeedData[i].getData(), 2);
                        }
                	}
                }
                
                // AppLog.trace("ChangePriceList: version:"+aVersion);
                changePriceList.add(new OptionsList(aVersion, aVersion+" - "+listLabel));
            }
        }
        catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in ChangePriceList do.Perform. "+pe.getCause());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            // AppLog.criticalError("Exception in  ChangePriceList .doPerform. "+pe);
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
        
        //Set Server Objects in Scope
        request.setAttribute("changePriceList",changePriceList);
        request.setAttribute("financialChangePriceList",form);
        return mapping.findForward("financialChangePriceList");
    }
}
