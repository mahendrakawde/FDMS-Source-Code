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

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterDTO;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterTypeDTO;
import com.aldorsolutions.webfdms.reporting.dao.FormAvailableFilterDAO;
import com.aldorsolutions.webfdms.reporting.dao.FormAvailableFilterTypeDAO;
import com.aldorsolutions.webfdms.reporting.filter.FilterFactory;
import com.aldorsolutions.webfdms.reporting.filter.FilterInterface;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FormEditForm;
import fdms.ui.struts.form.FormsAdminForm;
import fdms.ui.struts.manager.FormsAvailableManager;

public class ShowFormEdit extends Action {
    
    private Logger logger = Logger.getLogger(ShowFormEdit.class.getName());
    
    /**
     * Called from WebFdmsFormsAdmin.jsp, this action prepares to show
     * WebFdmsFormEdit.jsp.
     * The FormsAdminForm is used to determine if adding or which
     * form is being updated.
     * Then, the FormEdit form is created for the jsp to use.
     */
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
    		ArrayList <String> formErrors = new ArrayList <String> ();
        FormsAdminForm form = (FormsAdminForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbFormsAvailable dbForm = null;
        FormEditForm formEdit = new FormEditForm();
        String submitType = form.getSubmitbutton();
        ArrayList <OptionsList> formslist = new ArrayList <OptionsList> ();
        OptionsList chainToForm = null;
        FormsAvailableManager faManager = new FormsAvailableManager();
        
        //AppLog.trace("ShowFormEdit action doPerform submit = " +submitType);
        
        // Exit
        if (submitType.equals("exit")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            return actionForward;
        }
        
        // Change or Delete
        if (!submitType.equals("add") && (form.getFormID() == null || form.getFormID().trim().equals("") || form.getFormID().trim().equals("0"))) {
            //AppLog.trace("ShowFormEdit - Change/delete with no selection.");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));            
            saveErrors(request, errors);
            formErrors.add("formID");
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        } 
         
        //Database Access Logic
        try {
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            //Populate the Form Information
            if (submitType.equals("change")) {                
                dbForm = FdmsDb.getInstance().getFormsAvailable(t, Integer.parseInt(form.getFormID()));
                formEdit.setFormID(form.getFormID());
                formEdit.setCategory(String.valueOf(dbForm.getCategory()));
                formEdit.setDescription(dbForm.getDescription());
                formEdit.setExportType(dbForm.getExportType());
                formEdit.setMarginBottom(String.valueOf(dbForm.getMarginBottom()));
                formEdit.setMarginLeft(String.valueOf(dbForm.getMarginLeft()));
                formEdit.setMarginRight(String.valueOf(dbForm.getMarginRight()));
                formEdit.setMarginTop(String.valueOf(dbForm.getMarginTop()));
                formEdit.setSelectFormula(String.valueOf(dbForm.getSelectionFormula()));
                formEdit.setReportName(String.valueOf(dbForm.getReportName()));
                formEdit.setChainTo(	 String.valueOf(dbForm.getChainToReport()));
                formEdit.setDatapull(String.valueOf(dbForm.getDatapull()));
                formEdit.setStoredProc(String.valueOf(dbForm.getStoredProc()));
                formEdit.setXmlFile(	 String.valueOf(dbForm.getXmlFile()));
                formEdit.setLocaleLocationIdSubfolder(String.valueOf(dbForm.getAddLocalIDAndLocationIDReportFolder()));
                setFormFilterOptions(dbForm.getId(), session, sessionUser);
                // clean up
                form.setFormID("");
                // We need to get an updated list of forms.  This will be used to
                // select the changeTo report.  This code is here so that we can
                // make sure a report cannot be chained to itself
                formslist = faManager.getFormsAvailable(sessionUser);

                // Now we want to go thru the list of forms and make sure
                // our form is not listed.  
                if (formslist != null && formslist.size() > 0) {
                	// now go thru each one looking for a match.
                	for (int x=0; x < formslist.size();) {
                		chainToForm = (OptionsList)formslist.get(x);
                		if (Integer.valueOf(chainToForm.getListValue()).intValue() == Integer.valueOf(formEdit.getFormID()).intValue()) {
                			// they match so remove it.
                			formslist.remove(x);
                			// We don't want to bump the count if we just removed an item
                		} else {
                			// bump the count because we did not find match.
                			x++;
                		}
                	}
                }
                
                // Now update our formslist that we display.
                session.setAttribute("formslist",formslist);
                
            } else if ( submitType.equals("add") ) {
            	setFormFilterOptions(0, session, sessionUser);
            } else if (submitType.equals("delete")) {
                dbForm = new DbFormsAvailable();
                dbForm.setId(Integer.parseInt(form.getFormID()));
                dbForm.remove();
                t.addPersistent(dbForm);
                t.save();
                
                return mapping.findForward("showFormsAdminGlobal");
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowFormEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowFormEdit.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors
        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            return  new ActionForward(mapping.getInput());
        }
        
        // Set collections in session
        formEdit.setDirective(submitType);
        session.setAttribute("formEdit",formEdit);
        request.getSession().setAttribute("FDMS_SA", Boolean.TRUE);
        return mapping.findForward("formEdit");
    }
    
    public void setFormFilterOptions (long formID, HttpSession session, DbUserSession sessionUser ) {
    	
    	FormAvailableFilterTypeDAO filterTypeDAO = new FormAvailableFilterTypeDAO(sessionUser);
    	FormAvailableFilterDAO filterDAO = new FormAvailableFilterDAO (sessionUser);
    	
    	ArrayList <FormAvailableFilterTypeDTO> filterTypes = filterTypeDAO.getFormAvailableFilterType();
    	ArrayList <FormAvailableFilterDTO> filterSaved = filterDAO.getFormAvailableFilterByFormID(formID);
        ArrayList <FilterInterface> filters = new ArrayList <FilterInterface> ();
        
        for ( FormAvailableFilterTypeDTO filterType :  filterTypes ) {
        	FilterInterface filterI = FilterFactory.createFilter(filterType);
			filters.add(filterI);
			
			for ( FormAvailableFilterDTO filterConfig :  filterSaved ) {				

				if (filterConfig.getFilterTypeID() == filterType.getFormFilterTypeID()) {
					filterI.setFilter(filterConfig);
					break;
				}
			}
			
        }
        
        session.setAttribute("FORM_FILTER_OPTIONS", filters );
        
    }
    
}
