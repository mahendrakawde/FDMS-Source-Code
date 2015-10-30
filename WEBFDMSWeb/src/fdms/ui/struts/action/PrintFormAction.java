package fdms.ui.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.PrintForm;


public class PrintFormAction extends Action {

    private static Logger logger = Logger.getLogger(PrintFormAction.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws javax.servlet.ServletException, java.io.IOException {
    	
        PrintForm form = (PrintForm) actionForm;
        DatabaseTransaction t =null;
        ActionErrors errors = new ActionErrors();

        // Put empty report preview objects into request to be replaced by real ones below
        ShowCaseStatusForms.addEmptyReportPreviews(request);
        // get form selected
        int formID = FormatNumber.parseInteger(form.getFormName());
        // AppLog.trace("PrintFormAction. Form selected:"+formID);

        if (formID < 1) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
        }

        // Get user
        DbUserSession sessionUser = com.aldorsolutions.webfdms.util.SessionHelpers.getUserSession(request);
        if (sessionUser==null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }

        // See if vitalsId is set in form
        try {
            if (form.getVitalsId() != null && Integer.parseInt(form.getVitalsId()) > 0) {
                // AppLog.trace("PrintFormAction.printForm.vitalsId: " +Integer.parseInt(form.getVitalsId()));
                SessionHelpers.setVitalsIdInRequest(request, Integer.parseInt(form.getVitalsId()));
            }
        } catch (Exception e) {
            // logger.error("Error in doPerform() : ", e);
        }

        // Get Vitals Case ID
        int vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        // AppLog.trace("PrintFormAction. CaseID: " +vitalsid);
        if (vitalsid==0) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }

        //See if memorialId is set in form
        try {
            if (form.getMemorialId() != null && Integer.parseInt(form.getMemorialId()) > 0) {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                DbServices dbServices = FdmsDb.getInstance().getServices(t, vitalsid);
                if (dbServices==null){
                    dbServices = new DbServices();
                    dbServices.setNew();
                    t.addPersistent(dbServices);
                    dbServices.setLSrvMainKey(vitalsid );
                }
                dbServices.setMemorialID(Integer.parseInt(form.getMemorialId()));
                // t.addPersistent(dbServices);
                t.save();
                // AppLog.trace("Updated servicedata.memorialID = " +form.getMemorialId() +" for vitalsId " +vitalsid);
            }
        } catch (Exception e) {
            logger.error("Error in doPerform() : ", e);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }

        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            // AppLog.info("Invoking forward mapping getInput");
            saveErrors(request, errors);
            // none defined! return (new ActionForward(mapping.getInput()));
        } else {
            try {
                // Here is where we print the actual form.
                // This method generates print preview files and loads them into the request as attributes
                printFormChain(formID, vitalsid, sessionUser, request, response, servlet.getServletContext());
            }
            catch(PersistenceException pe) {
                // AppLog.criticalError("Persistence Exception in PrintForm.doPerform. "+pe.getMessage());
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getMessage()));
            }
                /*-----------
                Both RequestDispatcher.forward() and response.sendRedirect() work in this case.
                RequestDispatcher would keep session information whereas sendRediredcct does not.
                For displaying reports, we don't need session tracking.
                Plus, sendRedirect() alters the browser address bar to the actual report.pdf name
                which in this situation is better than the address "printAction.do"
                 */
            //RequestDispatcher rd;
            //rd = servlet.getServletContext().getRequestDispatcher(pageName);
            //AppLog.trace("PrintFormAction.page="+pageName+" rd="+rd);
            //rd.forward(request, response);
                /* old way when   form tag had a target parameter
                AppLog.trace("PrintFormAction sendRedirect:"+pageName);
                response.sendRedirect("/WebFdms"+pageName);
                 */
        }
        // Report any errors to next page dislayed
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        // Check if called from memorialPrint.JSP
        // if so, redisplay print memorial
        ActionForward actionForward = null;
        if (form.getMemorialId() != null && Integer.parseInt(form.getMemorialId()) > 0) {
            actionForward = mapping.findForward("showMemorialPrint");
        } else if (form.getMemorialId() != null && Integer.parseInt(form.getMemorialId())==-1) {
            // -1 value set in obituary.jsp
            // Error we shouldn't be here because Obituary was changed to SaveObituary action
            // will handle saving the obit and THEN printing the obit form.
            // This action should no longer be used for Obituary printing.
            // AppLog.error("PrintFormAction using old sendRedirect:"+pageName);
            //response.sendRedirect("/WebFdms"+pageName);
            //return null;
        } else if (form.getMemorialId() != null && Integer.parseInt(form.getMemorialId())==-2) {
            // -2 value set in veteran.jsp
            actionForward = mapping.findForward("showVeteran");
        } else{
            // Redisplay Case Status
            actionForward = mapping.findForward("showCaseStatusFromPrintForm");
        }
        
        String returnPath = actionForward.getPath();
        int periodpos = returnPath.indexOf(".do");
        returnPath = returnPath.substring(0,periodpos);
        
        ModuleConfig modconf =  mapping.getModuleConfig();
        Action finalAction = null;
        ActionMapping finalAC = (ActionMapping) modconf.findActionConfig(returnPath);
        
        try {
            Class clazz = Class.forName(finalAC.getType());
            finalAction = (Action) clazz.newInstance();
            return finalAction.execute(finalAC,form,request,response);
            // AppLog.trace("chaining to:"+finalAction.toString());
        } catch (Exception e) {
            // AppLog.warning("Could not find chained action: " + e.getMessage());
            logger.error("Error in doPerform() : ", e);
            return mapping.findForward("search");
        }

        // return NULL to signal that the action has handled sendRedirect
        //AppLog.trace("PrintFormAction. shouldn't get here.");
        //return null;
    }
    /**
     * Create print preview files for up to 9 forms given the starting form-ID
     * Creation date: (3/6/2003 11:19:24 AM)
     * @return java.lang.String
     * @param sessionUser com.aldorsolutions.webfdms.beans.DbUserSession
     * @exception PersistenceException The exception description.
     */
    public static String printFormChain(
    		int formID, 
    		int vitalsid, 
    		DbUserSession sessionUser, 
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		ServletContext servletContext) throws PersistenceException {
    	
        DatabaseTransaction t = null;
        int reportCounter=0;
        boolean success = false;
        String requestName;
        DbFormsAvailable aform = null;
        String pageName=null;
        int nextForm = formID;

        // Put empty report preview objects into request to be replaced by real ones below
        ShowCaseStatusForms.addEmptyReportPreviews(request);
        // Generate form preview files
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            while (nextForm > 0 && reportCounter < 10){
                // run crystal report
                reportCounter++;
                
                String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");

                if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                    CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());                
                    pageName = crystalServerReport.printReport(sessionUser, nextForm, "", "", null, "", "", true);
                }
                else
                {
                	logger.debug( "Servlet Path: " + request.getServletPath() );
                	logger.debug( "Context Path: " + request.getContextPath() );
                	logger.debug( "Path Info: " + request.getPathInfo() );
                	logger.debug( "Path Translated: " + request.getPathTranslated() );
                	
                	logger.debug( "Server Info: " + servletContext.getServerInfo() );
                	logger.debug( "Real Path: " + servletContext.getRealPath( "/" ) );
                	
                	ExportReport crystal = new ExportReport();
                	pageName = crystal.printForm(sessionUser, nextForm,"","",null,"", request, response, servletContext);
                }
                // Instantiate class to contain report URL and add to request
                com.aldorsolutions.webfdms.util.ReportPreview preview = new com.aldorsolutions.webfdms.util.ReportPreview(pageName);
                // construct request attribute name. First one is ReportPreview, subsequently number appended.
                if (reportCounter > 1){
                    requestName = "ReportPreview" + reportCounter;
                } else { requestName = "ReportPreview";}
                // AppLog.trace("PrintForm setting "+preview.getPreviewFile()+" into request name:"+requestName);
                request.setAttribute(requestName,preview);
                // Update FormsPrinted to show this one has been done
                success = FdmsDb.getInstance().setFormPrintedForCase(sessionUser,vitalsid,nextForm);
                
                // Get form object to check for report chaining.
                aform = FdmsDb.getInstance().getFormsAvailable(t,nextForm);
                nextForm=0;
                if (aform!=null){
                    nextForm = aform.getChainToReport();
                }
            }
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null){
				t.closeConnection();
				t = null;
			}
        }

        return pageName;
    }
    /**
     * Create print preview files for up to 9 forms given the starting form-ID.
     * This method differs by the extra parameter for setting the Crystal report record ID
     * to be replaced in the record selection formula.
     * Creation date: (3/6/2003 11:19:24 AM)
     * @return java.lang.String
     * @param sessionUser com.aldorsolutions.webfdms.beans.DbUserSession
     * @exception PersistenceException The exception description.
     */
    public static String printFormChain(int formID, int vitalsid, DbUserSession sessionUser, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, int selectionParam) throws PersistenceException {
        DatabaseTransaction t = null;
        int reportCounter=0;
        boolean success = false;
        String requestName;
        DbFormsAvailable aform = null;
        String pageName=null;
        int nextForm = formID;

        // Put empty report preview objects into request to be replaced by real ones below
        ShowCaseStatusForms.addEmptyReportPreviews(request);

        // Generate form preview files
        try {
        	
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            while (nextForm > 0 && reportCounter < 10){
                // run crystal report
                reportCounter++;

                String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");

                if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                	CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());      
                	pageName = crystalServerReport.printReport(sessionUser, nextForm, "", "", null, "", "", true);
                }
                else
                {
                	logger.debug( "Servlet Path: " + request.getServletPath() );
                	logger.debug( "Context Path: " + request.getContextPath() );
                	logger.debug( "Path Info: " + request.getPathInfo() );
                	logger.debug( "Path Translated: " + request.getPathTranslated() );

                	logger.debug( "Server Info: " + servletContext.getServerInfo() );
                	logger.debug( "Real Path: " + servletContext.getRealPath( "/" ) );

                	ExportReport crystal = new ExportReport();

                	if (selectionParam != 0){
                		// set the report selection record ID
                		crystal.setRecordIdSelParam( selectionParam );
                	}

                	pageName = crystal.printForm(sessionUser, nextForm,"","",null,"", request, response, servletContext);
                }
                                 
                // Instantiate class to contain report URL and add to request
                com.aldorsolutions.webfdms.util.ReportPreview preview = new com.aldorsolutions.webfdms.util.ReportPreview(pageName);
                // construct request attribute name. First one is ReportPreview, subsequently number appended.
                if (reportCounter > 1){
                    requestName = "ReportPreview" + reportCounter;
                }
                else { requestName = "ReportPreview";}
                // AppLog.trace("PrintForm setting "+preview.getPreviewFile()+" into request name:"+requestName);
                request.setAttribute(requestName,preview);
                // Update FormsPrinted to show this one has been done
                success = FdmsDb.getInstance().setFormPrintedForCase(sessionUser,vitalsid,nextForm);
                
                // Get form object to check for report chaining.
                aform = FdmsDb.getInstance().getFormsAvailable(t,nextForm);
                nextForm=0;
                if (aform!=null){
                    nextForm = aform.getChainToReport();
                }
            }
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }

        return pageName;
    }
}
