package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class pnstatus extends pnstatusBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853193064533850944L;

	private java.lang.String reportType = "preneed";

	private java.lang.String previewFile;

	/**
	 * Insert the method's description here. Creation date: (2/18/2003 1:58:59
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPreviewFile() {
		return previewFile;
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2003 11:33:49
	 * AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getReportType() {
		return reportType;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
		this.beneSameAsBuyer = false;
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2003 1:58:59
	 * PM)
	 * 
	 * @param newPreviewFile
	 *            java.lang.String
	 */
	public void setPreviewFile(java.lang.String newPreviewFile) {
		previewFile = newPreviewFile;
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2003 11:33:49
	 * AM)
	 * 
	 * @param newReportType
	 *            java.lang.String
	 */
	public void setReportType(java.lang.String newReportType) {
		reportType = newReportType;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}
}
