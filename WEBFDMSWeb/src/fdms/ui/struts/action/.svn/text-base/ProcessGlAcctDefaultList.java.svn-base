package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbGlAcctDefault;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.GlAcctDefaultEditForm;
import fdms.ui.struts.form.GlAcctDefaultListForm;


/**
 * ProcessGlAcctDefaultList
 * This class processes the form listing existing default GL accounts
 * Creation date: (6/2/2003 4:13:09 PM)
 * @author:
 */
public class ProcessGlAcctDefaultList extends Action {
    
    private Logger logger = Logger.getLogger(ProcessGlAcctDefaultList.class.getName());
    private ArrayList formErrors;

    /**
     * Called from glAcctDefaultList.JSP, this action prepares to show
     * glAcctDefaultEdit.JSP.
     * The input form is used to determine if adding or which
     * account is being updated.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ProcessGlAcctdefaultList action doPerfrom");
        
        formErrors = new ArrayList();
        GlAcctDefaultListForm form = (GlAcctDefaultListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        GlAcctDefaultEditForm	editform = new GlAcctDefaultEditForm();
        DbGlAcctDefault		anAcct = null;
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);

        // Look for exit request
        if (form.getSubmitButton().equals("exit")){
            return mapping.findForward("webFDMSManagement");
        }
        
        try {
            if (form.getSubmitButton().equals("add")){
                // prepare empty form for addition
                editform.setRecordID("0");
            } else {
                // must be "change" or "delete"
                int recid = FormatNumber.parseInteger( form.getListValue());
                if (recid < 1){
                    //AppLog.trace("ProcessGlAcctdefaultList- Change/delete with no selection.");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                    saveErrors(request, errors);
                    formErrors.add("listValue");
                    request.setAttribute("formErrors", formErrors);
                    request.setAttribute("glAcctDefaultListForm",form);
                    return (new ActionForward(mapping.getInput()));
                }
                // fetch account from DBMS
                logger.debug("ProcessGlAcctdefaultList - Update acct : " + recid);
                DatabaseTransaction t = null;
                try {
                    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        
                    anAcct = FdmsDb.getInstance().getGlAccountDefault(t, recid);
                    if (anAcct==null){
                        //AppLog.error("ProcessGlAcctdefaultList. Attempted update of recid:"+recid+" but record not found in DBMS");
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Account record could not be accessed."));
                    } else {
                        // Load form with DB data
                        editform.setCategory(	anAcct.getCategory());
                        editform.setDisposition(anAcct.getDisposition());
                        editform.setLocation(	String.valueOf(anAcct.getLocationID()));
                        editform.setRecordID(	form.getListValue());
                        editform.setRevacct(	anAcct.getRevenueAcct());
                        editform.setSaletype(	anAcct.getSaleType());
                        editform.setAssetacct(	anAcct.getInvAssetAcct());
                        editform.setCogsacct(	anAcct.getInvCogsAcct());
                        if (form.getSubmitButton().equals("delete")){
                            //AppLog.trace("ProcessGlAcctdefaultList - Deleting acct:"+recid);
                            anAcct.remove();
                            t.save();
                            return mapping.findForward("showGlAcctDefaultList");
                        }
                    }
                } catch (Exception e) {
                    logger.error("Error : ", e);
                } finally {
                    if (t != null) t.closeConnection();
                }
            }
            // Set collections in session
            request.setAttribute( "GlAcctDefaultEditForm",editform);
            SessionHelpers.setChapelListInSession(request) ;
            SessionHelpers.setPriceCategoryListInSession(request);
            SessionHelpers.setSaleTypeListInSession(request);
            SessionHelpers.setDispositionListInSession(request);
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessGlAcctdefaultList.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ProcessGlAcctdefaultList.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            request.setAttribute("glAcctDefaultListForm",form);
            return  new ActionForward(mapping.getInput());
        }
        
        request.setAttribute(mapping.getName(), editform);
        
        return mapping.findForward("ShowGlAcctDefaultEdit");
    }
}
