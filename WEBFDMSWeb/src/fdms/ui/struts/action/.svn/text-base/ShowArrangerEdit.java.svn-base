package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

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

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ArrangerAdminForm;
import fdms.ui.struts.form.ArrangerEditForm;


public class ShowArrangerEdit extends Action {
    
    private Logger logger = Logger.getLogger(ShowArrangerEdit.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from WebFdmsArrangerAdmin.jsp, this action prepares to show
     * WebFdmsArrangerEdit.jsp.
     *	 The ArrangerAdminForm is used to determine if adding or which
     * arranger is being updated.
     * Then, the ArrangerEdit form is created for the jsp to use.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        ArrangerAdminForm form = (ArrangerAdminForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbArrangers dbArranger = null;
        ArrangerEditForm arrangerEdit = new ArrangerEditForm();
        String submitType = form.getSubmitbutton();
        
        //AppLog.trace("ShowArrangerEdit action doPerform submit = " +submitType);
        
        // Exit
        if (submitType.equals("exit")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            return actionForward;
        }
        
        // Change or Delete
        if (!submitType.equals("add") && (form.getIdentity() == null || form.getIdentity().trim().equals("") || form.getIdentity().trim().equals("0"))) {
            //AppLog.trace("ShowArrangerEdit - Change/delete with no selection.");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("identity");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }
        
        //Database Access Logic
        try {
            //Populate the Facility Information
        	
        	SecurityManagerBean smBean = new SecurityManagerBean();
            SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
            List list = new ArrayList();
            for (int i=1; i <= security.getCommissionLevel(); i++){
            	list.add(new OptionsList(Integer.toString(i),String.valueOf(i)));
            }
            request.getSession().setAttribute("commissionList",list);
            if (!submitType.equals("add")) {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                dbArranger = FdmsDb.getInstance().getArranger(t, Integer.parseInt(form.getIdentity()));
                arrangerEdit.setIdentity(form.getIdentity());
                arrangerEdit.setName(dbArranger.getName());
                arrangerEdit.setLicenseNumber(dbArranger.getLicenseNumber());
                arrangerEdit.setSsn(dbArranger.getSocialSecurityNo());
                arrangerEdit.setPnLicenseNumber(dbArranger.getPnLicenseNumber());
                arrangerEdit.setBurialLicenseNumber(dbArranger.getBurialLicenseNumber());
                arrangerEdit.setEmbalmerLicenseNumber(dbArranger.getEmbalmerLicenseNumber());
                arrangerEdit.setIsCounselor(dbArranger.getIsCounselor());
                arrangerEdit.setCommissionLevel(dbArranger.getCommissionLevel());
                arrangerEdit.setManagerForCommission(dbArranger.isManagerForCommission());
                arrangerEdit.setManagerCommissionLevel(dbArranger.getManagerCommissionLevel());
                if("D".equals(dbArranger.getDeleteCode())){
                	arrangerEdit.setInactive(true);
                }else{
                	arrangerEdit.setInactive(false);
                }
                // clean up
                form.setIdentity("");
                
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowArrangerEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getMessage()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowArrangerEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors
        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            return  new ActionForward(mapping.getInput());
        }
        
        // Set collections in session
        arrangerEdit.setDirective(submitType);
        session.setAttribute("arrangerEdit",arrangerEdit);
        
        return mapping.findForward("arrangerEdit");
    }
}
