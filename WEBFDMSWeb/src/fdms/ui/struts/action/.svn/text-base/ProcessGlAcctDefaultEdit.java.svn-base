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

import com.aldorsolutions.webfdms.beans.DbGlAcctDefault;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.GlAcctDefaultEditForm;


/**
 * ProcessGlAcctDefaultEdit
 * This class processes the form for updating or adding a GL default account
 * Creation date: (6/3/2003 4:13:09 PM)
 * @author:
 */
public class ProcessGlAcctDefaultEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessGlAcctDefaultEdit.class.getName());

    /**
     * Called from glAcctDefaultEdit.JSP, this action updates the DBMS
     * from the form.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        //AppLog.trace("ProcessGlAcctdefaultEdit action doPerfrom");

        GlAcctDefaultEditForm form = (GlAcctDefaultEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbGlAcctDefault		anAcct = null;
        DatabaseTransaction t = null;
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        
        try {
            if (form.getDirective().compareToIgnoreCase("save")!=0){
                // must be cancelling if not saving.
                return mapping.findForward("showGlAcctDefaultList");
            }
            else {
            	try {
                    // Save changes
                    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    int recid = FormatNumber.parseInteger( form.getRecordID());
                    if (recid < 1){
                        //AppLog.trace("ProcessGlAcctdefaultEdit- Adding record.");
                        anAcct = new DbGlAcctDefault();
                        anAcct.setNew();
                        anAcct.setLocale( sessionUser.getRegion() );
                        anAcct.setDeleteCode(" ");
                        t.addPersistent(anAcct);
                    }else {
                        // fetch account from DBMS
                        //AppLog.trace("ProcessGlAcctdefaultEdit - Update acct:"+recid);
                        anAcct = FdmsDb.getInstance().getGlAccountDefault(t, recid);
                        if (anAcct==null){
                            //AppLog.error("ProcessGlAcctdefaultList. Attempted update of recid:"+recid+" but record not found in DBMS");
                            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Account record could not be accessed."));
                        }
                    }
                    // Load form with DB data
                    anAcct.setCategory(			form.getCategory());
                    anAcct.setDisposition(		form.getDisposition());
                    anAcct.setLocationID(		FormatNumber.parseInteger(form.getLocation()));
                    anAcct.setRevenueAcct(		form.getRevacct());
                    anAcct.setSaleType(			form.getSaletype());
                    anAcct.setInvAssetAcct(		form.getAssetacct());
                    anAcct.setInvCogsAcct(		form.getCogsacct());
                    // clean up
                    t.save();
        		} finally {
        			if ( t != null ) {
        				t.closeConnection();
        			}
        		}
            }
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessGlAcctdefaultEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ProcessGlAcctdefaultEdit.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            request.setAttribute("glAcctDefaultEditForm",form);
            return  new ActionForward(mapping.getInput());
        }
        
        return mapping.findForward("showGlAcctDefaultList");
    }
}
