package com.aldorsolutions.webfdms.util.checks;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;

public class CheckPrinter {

	private Logger logger = Logger.getLogger(CheckPrinter.class.getName());
	
	public String printCheckForCheckWriter (HttpServletRequest request, HttpServletResponse response, ServletContext context, DbUserSession user, 
			ActionErrors errors, int checkMasterID) {
		
		String pageName = null;
		
		DatabaseTransaction t = null;
		try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            // get form-ID for check form
            DbFormsAvailable[] forms = FdmsDb.getInstance().getFormsAvailableForLocale(t,user.getRegion(), DbFormsAvailable.CHECK_HISTORY_TYPE);
            if (forms.length < 1){
                // AppLog.error("ProcessApCheckWriter:handleSave - No check forms found.");
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.noform"));
                return (pageName);
            }
            
            String crystalFlag = UtilSingleton.getInstance().getProperty(user.getConfigID(), "CrystalServer.useReportingService");
            
            if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(user.getConfigID());                
                pageName = crystalServerReport.printReport(user, forms[0].getFormId(), "", "", null, "", Integer.toString(checkMasterID), true);
            }
            else
            {
            	ExportReport crystal = new ExportReport();
                crystal.setRecordIdSelParam( checkMasterID ); // selection parameter
                pageName = crystal.printForm(user, forms[0].getFormId(), "", "", null, "", request, response, context);
            }
            
            return ( pageName );
            
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }
        
        return ( pageName );
	}
}
