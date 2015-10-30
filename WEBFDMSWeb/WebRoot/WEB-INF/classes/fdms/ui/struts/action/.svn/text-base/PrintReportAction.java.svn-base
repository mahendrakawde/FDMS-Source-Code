package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.reporting.crystal.model.CrystalReportCustomColDTO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.PrintReports;
import fdms.ui.struts.form.ReportsPrintForm;

public class PrintReportAction extends Action {
    
    private Logger logger = Logger.getLogger(PrintReportAction.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {                
        
        formErrors = new ArrayList();
        PrintReports form = (PrintReports) actionForm;
        ActionErrors errors = new ActionErrors();
        int formID = 0;
        String fromDate = null;
        String toDate = null;
        String location = null;
        
        logger.debug("PrintReportAction.doPerform.form: " +form);        
        
        // New variables for determining whether to update financialdata.DateInvoiceSent
        DatabaseTransaction t = null;
        DbFormsAvailable dbFormsAvailable = null;
        
        String selectedCategory = form.getCategory();
        logger.debug("category : " + selectedCategory);
        
        // Get Form Selected
        try {
            formID = FormatNumber.parseInteger(form.getSelectReport());
        } catch(Exception e){
            //
        }
        
        if (formID < 1) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("selectReport");
        }
        
        // Get From Date
        try {
            fromDate = FormatDate.convertToDateYYYYMMDD(form.getFromDate());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.date.fromdate"));
            formErrors.add("fromDate");
        }
        
        // Get To Date
        try {
            toDate = FormatDate.convertToDateYYYYMMDD(form.getToDate());
        } catch(Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.date.todate"));
            formErrors.add("toDate");
        }
        
        // Get selected location
        try {
            location = form.getSelectLocation();
        } catch (Exception e) {
            //
        }
        
        logger.debug("PrintReportAction. Report selected : " + formID + " from: " + fromDate + " to: " + toDate + " location : " + location);
        
        // Get user
        DbUserSession sessionUser = com.aldorsolutions.webfdms.util.SessionHelpers.getUserSession(request);
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            
            try {
                
                int category=-1;
                if (selectedCategory.compareToIgnoreCase("atneed")==0){
                    category=1;
                } else if (selectedCategory.compareToIgnoreCase("preneed")==0){
                    category=2;
                } else if (selectedCategory.compareToIgnoreCase("financial")==0){
                    category=3;
                } else if (selectedCategory.compareToIgnoreCase("donation")==0){
                    category=4;
                } else if (selectedCategory.compareToIgnoreCase("pricelist")==0){
                    category=5;
                } else if (selectedCategory.compareToIgnoreCase("inventory")==0){
                    category=6;
                }
        
                List locations = new ArrayList();
                List reports = new ArrayList();
                com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list;
                com.aldorsolutions.webfdms.beans.DbLocation[] chapels;

                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                //AppLog.trace("ShowReportList. retrieving reports for region:"+sessionUser.getRegion()+"  category:"+category);
                list = FdmsDb.getInstance().getFormsAvailableForLocale(t,sessionUser.getRegion(), category);                

                // create select list of reports
                for (int i=0; i<list.length; i++){
                    reports.add( new OptionsList(Integer.toString(list[i].getFormId()) ,list[i].getDescription()));
                }
                // create select list of locations
                chapels = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
                for (int i=0; i<chapels.length; i++){
                    locations.add( new OptionsList(Integer.toString(chapels[i].getId()) ,chapels[i].getName()));
                }

                // Put form beans and collections in request
                request.setAttribute("locations",locations);
                request.setAttribute("reports",reports);
                
                ReportsPrintForm reportsPrint = new fdms.ui.struts.form.ReportsPrintForm();
                reportsPrint.setCategory(selectedCategory);
                request.setAttribute("reportsPrint", reportsPrint);
                
            } catch (Exception e) {                
                logger.error("Exception in perform() : ", e);
            } finally {
                if (t != null) {
					t.closeConnection();
					t = null;
				}
            }
                        
            logger.debug("Invoking forward mapping getInput");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            request.setAttribute("printReports",form);
            
            return (new ActionForward(mapping.getInput()));
            //return mapping.findForward("reportsListPage");
        }
        
        
        String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");
        ArrayList customValues = getCustomValues(form, request);
        String pageName = null;

        if ( crystalFlag != null && "true".equals(crystalFlag) ) {
            CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());                
            pageName = crystalServerReport.printReport(
            		sessionUser, 
    				formID, 
    				fromDate, 
    				toDate, 
    				customValues,
    				location,
    				"", true);
            
            logger.debug( "Page Name: " + pageName );
        }
        else
        {
        	ExportReport crystal = new ExportReport();
            pageName = crystal.printForm(
            		sessionUser, 
            		formID, 
            		fromDate, 
            		toDate, 
            		customValues,
            		location, 
            		request, 
            		response, 
            		servlet.getServletContext());
            
            logger.debug( "Page Name: " + pageName );
            
            //pageName = URLEncoder.encode(pageName, "UTF-8");
            //logger.debug( "Page Name Encode: " + pageName );
            
        }
            
        
        
        //. URLEncoder.encode(pageName, "UTF-8")
        
        /*-----------
        Both RequestDispatcher.forward() and response.sendRedirect() work in this case.
        RequestDispatcher would keep session information whereas sendRediredcct does not.
        For displaying reports, we don't need session tracking.
        Plus, sendRedirect() alters the browser address bar to the actual report.pdf name
        which in this situation is better than the address "printAction.do"
         */
        
        // New logic to update the financialdata.DateInvoiceSent when Statements are printed.
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            dbFormsAvailable = FdmsDb.getInstance().getFormsAvailable(t, formID);
            
            if (dbFormsAvailable != null && dbFormsAvailable.getDescription() != null &&
                    dbFormsAvailable.getDescription().trim().toUpperCase().indexOf("STATEMENTS") >= 0) {
                com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().setDateInvoiceSent(sessionUser, fromDate, toDate, location);
            }
            
        } catch (PersistenceException pe) {
            logger.error("PersistenceException Error in doPerform() : " + pe);
        } catch(Exception pe) {
            logger.error("Error in doPerform() : " + pe);
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
        
        // AppLog.trace("PrintFormAction sendRedirect:"+pageName);
        
        response.sendRedirect( pageName );
        
        // Redisplay Case Status
        //ActionForward actionForward = mapping.findForward("showCaseStatusGlobal");
        //ActionMappings mappings = mapping.getMappings();
        //String returnPath = actionForward.getPath();
        //int periodpos = returnPath.indexOf(".do");
        //returnPath = returnPath.substring(0,periodpos);
        //ActionMapping finalMapping = mappings.findMapping(returnPath);
        //Action finalAction = null;
        //try {
        //    Class clazz = Class.forName(finalMapping.getType());
        //    finalAction = (Action) clazz.newInstance();
        //    AppLog.trace("chaining to:"+finalAction.toString());
        //  return finalAction.perform(finalMapping,form,request,response);
        //}
        //catch (Exception e) {
        //    AppLog.warning("Could not find chained action: " + e.getMessage());
        //    return forwardSearch(mapping) ;
        //} //end try catch
        
        // return NULL to signal that the action has handled sendRedirect
        // AppLog.trace("PrintFormAction. return null");
        return null;
    }
    
    /**
     * 
     * @param form
     * @param request
     * @return
     */
    private ArrayList getCustomValues(
    		PrintReports form,
    		HttpServletRequest request) {
    	
    	String category = FormatString.blankNull(form.getCategory());
    	String selectItemCategory = FormatString.blankNull(form.getSelectItemCategory());
    	ArrayList customValues = new ArrayList();
    	
    	if ("inventory".equalsIgnoreCase(category)) {
    		if (selectItemCategory.length() > 0)
	    		customValues.add(new CrystalReportCustomColDTO("prodline", selectItemCategory));
    	}
    	
    	return customValues;
    }
//    
//    private String getFileExtenstion(String fileUrl) {
//    	String fileExt = "";
//    	
//    	if (fileUrl.lastIndexOf(".") > 0)
//    		fileExt = fileUrl.substring(fileUrl.lastIndexOf("."));
//    	
//    	return fileExt;
//    }
//    
//    private String getContentType(String fileExt) {
//    	String contentType = "application/pdf";
//    	
//
//    	if (fileExt != null) {	
//    		if (fileExt.toLowerCase().equals("rtf"))
//    			contentType = "application/rtf";
//    		else if (fileExt.toLowerCase().equals("doc"))
//    			contentType = "application/doc";
//    		else if (fileExt.toLowerCase().equals("xls"))
//    			contentType = "application/vnd.ms-excel";
//    		else if (fileExt.toLowerCase().equals("htm"))
//    			contentType = "text/html";
//    	}
//    	
//    	return contentType;
//    }
}
