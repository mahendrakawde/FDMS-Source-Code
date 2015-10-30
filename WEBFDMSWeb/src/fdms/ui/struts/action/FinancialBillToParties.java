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

import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;



public class FinancialBillToParties extends Action {
    
    private Logger logger = Logger.getLogger(FinancialBillToParties.class.getName());

    /**
     * FinancialBillToParties
     * This action is started from the financial page when the user clicks the button
     * labeled "Bill To". A list of survivors for this case is put into a collection
     * and placed in request scope. The page FinancialBillToPartiesAddChange.JSP next
     * displays the collection.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbBillto[] dbBillTo = null;
        int vitalsid=0;
        //java.util.ArrayList billToPartiesList = null;
        java.util.ArrayList billToPartiesDisplayList = null;
        //java.util.ArrayList dbBilltoList = null;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            // AppLog.warning("ShowFinancial. Invalid VitalsID to process:"+vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }

        // AppLog.trace("FinancialBillToParties starting for ID:"+vitalsid);
        //Database Access Logic
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbBillTo = FdmsDb.getInstance().getBilltoForID( t , vitalsid );
            // AppLog.trace("FinancialBillToParties- dbBillTo Length is "+ dbBillTo.length );
            //billToPartiesList = new java.util.ArrayList();
            billToPartiesDisplayList = new java.util.ArrayList();
            //dbBilltoList = new java.util.ArrayList();
            
            for(int i=0 ; i < dbBillTo.length ; i++)	{
                // AppLog.trace("dbBillTo " +i+" deleted:"+dbBillTo[i].getDeleteCode() +" First Name is  "+ dbBillTo[i].getFirstName() );
                if ( !dbBillTo[i].getDeleteCode().equalsIgnoreCase("D")){
                    //FinancialInformationBillToPartiesLineItem fbtpLineItem = new fdms.ui.struts.custom.FinancialInformationBillToPartiesLineItem(dbBillTo[i]);
                    //dbBilltoList.add(dbBillTo[i]);
                    //billToPartiesList.add(fbtpLineItem);
                    billToPartiesDisplayList.add( new OptionsList(String.valueOf(dbBillTo[i].getId()),dbBillTo[i].getRelation()+":    "+dbBillTo[i].getFirstName()+" "+dbBillTo[i].getLastName()+",     "+dbBillTo[i].getCity() ) );
                }
            }
        }
        catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in BillTo.doPerform. "+pe.getMessage());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            // AppLog.criticalError("Exception in  BillTo .doPerform. "+pe);
            // pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }
        
        //Place Objects In Scope
        session.setAttribute( "billToPartiesDisplayList", billToPartiesDisplayList);
        ActionForward actionForward = mapping.findForward("financialBillToParties");
        if( !errors.isEmpty() )	   {
            // AppLog.info("FinancialBillToParties Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        return  actionForward;
    }
    
}
