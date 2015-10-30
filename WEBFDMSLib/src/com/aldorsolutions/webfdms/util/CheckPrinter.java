package com.aldorsolutions.webfdms.util;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.reporting.crystal.model.CrystalReportCustomColDTO;

public class CheckPrinter {

	private Logger logger = Logger.getLogger(CheckPrinter.class.getName());

	public String printCheckForCheckWriter(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context, DbUserSession user, ActionMessages errors, int checkMasterID) {

	String pageName = null;

	DatabaseTransaction t = null;
	try {
		t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
		// get form-ID for check form
		DbFormsAvailable[] forms = FdmsDb.getInstance().getFormsAvailableForLocale(t, user.getRegion(), 
				DbFormsAvailable.CHECK_TYPE);
		if (forms.length < 1) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.ui.checks.noform"));
			return (pageName);
		}

		String crystalFlag = UtilSingleton.getInstance().getProperty(user.getConfigID(), "CrystalServer.useReportingService");

		if (crystalFlag != null && "true".equals(crystalFlag)) {
			CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(user.getConfigID());
			pageName = crystalServerReport.printReport(user, forms[0].getFormId(), "", "", null, "", Integer.toString(checkMasterID), true);
		} else {
			ExportReport crystal = new ExportReport(user.getConfigID());
			crystal.setRecordIdSelParam(checkMasterID); // selection parameter
			pageName = crystal.printForm(user, forms[0].getFormId(), "", "", null, "", request, response, context);
		}

		return (pageName);

	} catch (Exception e) {
		logger.error("Error : ", e);
	} finally {
		if (t != null) {
			t.closeConnection();
			t = null;
		}
	}

	return (pageName);
	}

	
	
	/**
	 * Note: To use this routine the report formula must be setup in WEBFDMS to
	 * {ApMaster.MasterID} in %recid%
	 * 
	 * @param request
	 * @param response
	 * @param context
	 * @param user
	 * @param errors
	 * @param checks This is a list of check numbers seperated by a comma
	 * @return
	 * 
	 * 
	 */
	public String printCheckForCheckWriter(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context, DbUserSession user, ActionMessages errors, String checks) {

		String pageName = null;
	
		DatabaseTransaction t = null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			// get form-ID for check form
			DbFormsAvailable[] forms = FdmsDb.getInstance().getFormsAvailableForLocale(t, user.getRegion(), 
					 DbFormsAvailable.CHECK_TYPE);
			if (forms.length < 1) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.ui.checks.noform"));
				return (pageName);
			}
	
			String crystalFlag = UtilSingleton.getInstance().getProperty(user.getConfigID(), "CrystalServer.useReportingService");
	
			if (crystalFlag != null && "true".equals(crystalFlag)) {
				CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(user.getConfigID());
				pageName = crystalServerReport.printReport(user, forms[0].getFormId(), "", "", null, "", "["+checks+"]", true);
			}	else {
	// This routine uses a string of AmPasterIds so there is no way to set the recordIdSelection so 
	// I chose to throw an exception			
	//			ExportReport crystal = new ExportReport(user.getConfigID());
	//			crystal.setRecordIdSelParam(checks); // selection parameter
	//			pageName = crystal.printForm(user, forms[0].getFormId(), "", "", null,
	//					"", request, response, context);
				throw new NullPointerException("WEBFDMS Forced Print error");
			}
	
			return (pageName);
	
		} catch (Exception e) {
			logger.error("Error : ", e);
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}
	
		return (pageName);
	}
}
