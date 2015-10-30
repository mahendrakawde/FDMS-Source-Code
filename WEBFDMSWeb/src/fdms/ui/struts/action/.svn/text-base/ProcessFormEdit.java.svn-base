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
import com.aldorsolutions.webfdms.reporting.dao.FormAvailableFilterDAO;
import com.aldorsolutions.webfdms.reporting.filter.FilterException;
import com.aldorsolutions.webfdms.reporting.filter.FilterInterface;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FormEditForm;


public class ProcessFormEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFormEdit.class.getName());
    private ArrayList <String> formErrors;

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList <String> ();
        FormEditForm form = (FormEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        com.aldorsolutions.webfdms.beans.DbFormsAvailable dbForm = null;
        String submitType = form.getSubmitbutton();
        
        
        if (!submitType.equals("save")) {
            return mapping.findForward("showFormsAdminGlobal");
        }
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Process the Arranger add/change/delete.
            if (form.getDirective().equals("add")) {
                dbForm = new com.aldorsolutions.webfdms.beans.DbFormsAvailable();
                dbForm.setNew();
                dbForm.setLocaleNumber(sessionUser.getRegion());
            } else {
                dbForm = FdmsDb.getInstance().getFormsAvailable(t, Integer.parseInt(form.getFormID()));
            }
            
            if (form.getDirective().equals("delete")) {
                dbForm.remove();
                t.addPersistent(dbForm);
                t.save();
            } else {
                validateFormData(form, errors);

                if (errors.isEmpty()) {
                    setForm(form, dbForm, errors);
                    if (errors.isEmpty()) {
                        t.addPersistent(dbForm);
                        t.save();
                        processFormFilterOptions( dbForm.getId(), form, request, sessionUser );
                    }
                }
            }
            
        } catch (PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));            
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
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
        
        //Action Forward Logic
        
        ActionForward actionForward = mapping.findForward("showFormsAdminGlobal");
        
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessFormEdit Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        //AppLog.trace("Leaving ProcessFormEdit.");
        return actionForward;
        
    }
    /**
     * Called from ProcessFormEdit, this Method sets the FormsAvailable record
     * from the WebFDMSFormEdit.jsp values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/10/2002 12:30:36 PM)
     */
    public void setForm(fdms.ui.struts.form.FormEditForm form, DbFormsAvailable dbForm, ActionErrors errors)
    
    {
        String errorField = new String();
        String errorStr = "";
        
        try {
            errorField = "Category";
            errorStr = "category";
            dbForm.setCategory(Integer.parseInt(form.getCategory()));
            
            errorField = "ReportName";
            errorStr = "reportName";
            dbForm.setReportName(form.getReportName());
            
            errorField = "Description";
            errorStr = "description";
            dbForm.setDescription(form.getDescription());
            
            errorField = "MarginBottom";
            errorStr = "marginBottom";
            dbForm.setMarginBottom(Integer.parseInt(form.getMarginBottom()));
            
            errorField = "MarginLeft";
            errorStr = "marginLeft";
            dbForm.setMarginLeft(Integer.parseInt(form.getMarginLeft()));
            
            errorField = "MarginRight";
            errorStr = "marginRight";
            dbForm.setMarginRight(Integer.parseInt(form.getMarginRight()));
            
            errorField = "MarginTop";
            errorStr = "marginTop";
            dbForm.setMarginTop(Integer.parseInt(form.getMarginTop()));
            
            errorField = "ExportType";
            errorStr = "exportType";
            dbForm.setExportType(form.getExportType());
            
            errorField = "SelectFormula";
            errorStr = "selectFormula";
            dbForm.setSelectionFormula(form.getSelectFormula());
            
            errorField = "ChainTo";
            errorStr = "chainTo";
            dbForm.setChainToReport(	FormatNumber.parseInteger(form.getChainTo()));
            
            errorField = "Datapull";
            errorStr = "datapull";
            dbForm.setDatapull(form.getDatapull());
            
            dbForm.setStoredProc(form.getStoredProc());
            dbForm.setXmlFile(form.getXmlFile());
            dbForm.setAddLocalIDAndLocationIDReportFolder(form.getLocaleLocationIdSubfolder());
            
            if (Integer.parseInt(form.getCategory())== 18 
            		|| Integer.parseInt(form.getCategory())== 17
            		|| Integer.parseInt(form.getCategory())== 16){
            	dbForm.setLocaleNumber(0);
            }
            
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.set" +errorField));
            formErrors.add(errorStr);
        }
        
        return;
        
    }
    /**
     * Called from ProcessFormEdit, this Method validates the Form
     * data from WebFDMSFormEdit.jsp. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/11/2002 12:25:14 PM)
     */
    public void validateFormData(FormEditForm form, ActionErrors errors) {
        
        try {
            
            // Report Name is required.
            if (form.getReportName() == null || form.getReportName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullReportName"));
                formErrors.add("reportName");
            }
            
            // there is no white space in the report name and must end with .rpt
            boolean isErrorReportName = false;
		    String search = ".RPT";
		    String  reportName = form.getReportName().toUpperCase();
		    reportName = reportName.substring(reportName.length()-4, reportName.length());
		    int possition = reportName.indexOf(search);
		    if ( possition != -1) {
		    	isErrorReportName = false;
		    }else {
		    	isErrorReportName = true;
		    }
		    search = " ";
		    reportName = form.getReportName();
		    possition = reportName.indexOf(search);
		    if ( possition != -1) {
		    	isErrorReportName = true;
		    }
		    if (isErrorReportName) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.invalidReportName"));
                formErrors.add("reportName");
            }
		    
            
            
            // Description is required
            if (form.getDescription() == null || form.getDescription().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullDescription"));
                formErrors.add("description");
            }
            
	    
            
            // ExportType is required
            if (form.getExportType() == null || form.getExportType().trim().equals("") || form.getExportType().trim().equals("--Select--")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullExportType"));
                formErrors.add("exportType");
            }
            
            // Category is required
            if (form.getCategory() == null || form.getCategory().trim().equals("") || form.getCategory().trim().equals("--Select--")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullCategory"));
                formErrors.add("category");
            }
            
            try {
                // MarginBottom is required and must be numeric
                if (form.getMarginBottom() == null || form.getMarginBottom().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullMarginBottom"));
                    formErrors.add("marginBottom");
                } else {
                    Integer.parseInt(form.getMarginBottom());
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.invalidMarginBottom"));
                formErrors.add("marginTop");
            }
            
            try {
                // MarginTop is required and must be numeric
                if (form.getMarginTop() == null || form.getMarginTop().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullMarginTop"));
                    formErrors.add("marginTop");
                } else {
                    Integer.parseInt(form.getMarginTop());
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.invalidMarginTop"));
                formErrors.add("marginTop");
            }
            
            try {
                // MarginLeft is required and must be numeric
                if (form.getMarginLeft() == null || form.getMarginLeft().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullMarginLeft"));
                    formErrors.add("marginLeft");
                } else {
                    Integer.parseInt(form.getMarginLeft());
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.invalidMarginLeft"));
                formErrors.add("marginLeft");
            }
            
            try {
                // MarginRight is required and must be numeric
                if (form.getMarginRight() == null || form.getMarginRight().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullMarginRight"));
                    formErrors.add("marginRight");
                } else {
                    Integer.parseInt(form.getMarginRight());
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.invalidMarginRight"));
                formErrors.add("marginRight");
            }
            
            // Datapull is required
            if (form.getDatapull() == null || form.getDatapull().trim().equals("") || form.getDatapull().trim().equals("--Select--")) {
                //errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.form.nullDatapull"));
                //formErrors.add("datapull");
            	//we not checking this one yet just set it to CR.
            	form.setDatapull("CR");
            }
            
            
        } catch (Exception e) {
            logger.error("Error in validateFormData() : ", e);
        }
    }

    public void processFormFilterOptions (long formID, FormEditForm form, 
    		HttpServletRequest request, DbUserSession sessionUser ) throws FilterException {
    	
    	HttpSession session = request.getSession();
    	FormAvailableFilterDAO filterDAO = new FormAvailableFilterDAO (sessionUser);
    	ArrayList <FilterInterface> filters = (ArrayList) session.getAttribute("FORM_FILTER_OPTIONS");
    	
    	for ( FilterInterface filterI : filters ) {
    		FormAvailableFilterDTO filterDTO = null;
			filterDTO = filterI.processAdminSubmit(request);
			
			try {
				if ( filterDTO.getFormFilterID() > 0 ) {
					filterDAO.updateFormAvailableFilter(filterDTO);
				} else {
					filterDTO.setFormID(formID);
					filterDAO.addFormAvailableFilter(filterDTO);
				}
			} catch ( Exception e ) {
				logger.error("Error in filterDAO() : ", e);
			}
    		
    	}
    }
}
