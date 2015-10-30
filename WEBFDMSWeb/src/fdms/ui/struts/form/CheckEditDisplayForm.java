package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CheckEditDisplayForm extends CheckEditDisplayFormBase {

	private String previewFile = null;
	private String approvalStatusDesc = null;
	private long approvalStatus = 0;
	private boolean checkApproved = false;
	private String checkNumber;
	private String checkAmount;
	private String checkDate;
	private int y;
	private String directive;
	private String memo;
	private String editMemo;
	private int x;
	private String location;
	private String vendor;
	private String apmasterID;
	private String payee;
	private String locationName;
	private String locationAddr;
	private String locationCitySt;
	private String checkAmountLong;
	private String signature;
	private String approvalTimestamp;
	private boolean priorApproval = false;
	private int vitalsID;
	private String vitalsName;
	private String vitalsCaseNumber;
	private boolean check1099;
	private String check1099Amount;
	private boolean checkPrintingAvailable;
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6434883745484713369L;

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	/**
	 * @return the previewFile
	 */
	public String getPreviewFile() {
		return previewFile;
	}

	/**
	 * @param previewFile the previewFile to set
	 */
	public void setPreviewFile(String previewFile) {
		this.previewFile = previewFile;
	}

	/**
	 * @return the checkApproved
	 */
	public boolean isCheckApproved() {
		return checkApproved;
	}

	/**
	 * @param checkApproved the checkApproved to set
	 */
	public void setCheckApproved(boolean checkApproved) {
		this.checkApproved = checkApproved;
	}

	/**
	 * @return the approvalStatus
	 */
	public long getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @return the approvalStatusDesc
	 */
	public String getApprovalStatusDesc() {
		return approvalStatusDesc;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(long approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @param approvalStatusDesc the approvalStatusDesc to set
	 */
	public void setApprovalStatusDesc(String approvalStatusDesc) {
		this.approvalStatusDesc = approvalStatusDesc;
	}
	

	/**
	 * Insert the method's description here.
	 * Creation date: (2/13/2003 2:21:14 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getApmasterID() {
		return apmasterID;
	}

	public String getCheckAmount() {
		return checkAmount;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public String getDirective() {
		return directive;
	}

	public String getLocation() {
		return location;
	}

	public String getMemo() {
		return memo;
	}

	public String getVendor() {
		return vendor;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (2/13/2003 2:21:14 PM)
	 * @param newApmasterID java.lang.String
	 */
	public void setApmasterID(java.lang.String newApmasterID) {
		apmasterID = newApmasterID;
	}

	public void setCheckAmount(String in) {
		checkAmount = in;
	}

	public void setCheckDate(String in) {
		checkDate = in;
	}

	public void setCheckNumber(String in) {
		checkNumber = in;
	}

	public void setDirective(String in) {
		directive = in;
	}

	public void setLocation(String in) {
		location = in;
	}

	public void setMemo(String in) {
		memo = in;
	}

	public void setVendor(String in) {
		vendor = in;
	}

	public void setX(int in) {
		x = in;
	}

	public void setY(int in) {
		y = in;
	}

	/**
	 * @return the approvalTimestamp
	 */
	public String getApprovalTimestamp() {
		return approvalTimestamp;
	}

	/**
	 * @return the check1099
	 */
	public boolean isCheck1099() {
		return check1099;
	}

	/**
	 * @return the check1099Amount
	 */
	public String getCheck1099Amount() {
		return check1099Amount;
	}

	/**
	 * @return the checkAmountLong
	 */
	public String getCheckAmountLong() {
		return checkAmountLong;
	}

	/**
	 * @return the locationAddr
	 */
	public String getLocationAddr() {
		return locationAddr;
	}

	/**
	 * @return the locationCitySt
	 */
	public String getLocationCitySt() {
		return locationCitySt;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @return the payee
	 */
	public String getPayee() {
		return payee;
	}

	/**
	 * @return the priorApproval
	 */
	public boolean isPriorApproval() {
		return priorApproval;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @return the vitalsCaseNumber
	 */
	public String getVitalsCaseNumber() {
		return vitalsCaseNumber;
	}

	/**
	 * @return the vitalsID
	 */
	public int getVitalsID() {
		return vitalsID;
	}

	/**
	 * @return the vitalsName
	 */
	public String getVitalsName() {
		return vitalsName;
	}

	/**
	 * @param approvalTimestamp the approvalTimestamp to set
	 */
	public void setApprovalTimestamp(String approvalTimestamp) {
		this.approvalTimestamp = approvalTimestamp;
	}

	/**
	 * @param check1099 the check1099 to set
	 */
	public void setCheck1099(boolean check1099) {
		this.check1099 = check1099;
	}

	/**
	 * @param check1099Amount the check1099Amount to set
	 */
	public void setCheck1099Amount(String check1099Amount) {
		this.check1099Amount = check1099Amount;
	}

	/**
	 * @param checkAmountLong the checkAmountLong to set
	 */
	public void setCheckAmountLong(String checkAmountLong) {
		this.checkAmountLong = checkAmountLong;
	}

	/**
	 * @param locationAddr the locationAddr to set
	 */
	public void setLocationAddr(String locationAddr) {
		this.locationAddr = locationAddr;
	}

	/**
	 * @param locationCitySt the locationCitySt to set
	 */
	public void setLocationCitySt(String locationCitySt) {
		this.locationCitySt = locationCitySt;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @param payee the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * @param priorApproval the priorApproval to set
	 */
	public void setPriorApproval(boolean priorApproval) {
		this.priorApproval = priorApproval;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @param vitalsCaseNumber the vitalsCaseNumber to set
	 */
	public void setVitalsCaseNumber(String vitalsCaseNumber) {
		this.vitalsCaseNumber = vitalsCaseNumber;
	}

	/**
	 * @param vitalsID the vitalsID to set
	 */
	public void setVitalsID(int vitalsID) {
		this.vitalsID = vitalsID;
	}

	/**
	 * @param vitalsName the vitalsName to set
	 */
	public void setVitalsName(String vitalsName) {
		this.vitalsName = vitalsName;
	}

	/**
	 * @return the checkPrintingAvailable
	 */
	public boolean isCheckPrintingAvailable() {
		return checkPrintingAvailable;
	}

	/**
	 * @param checkPrintingAvailable the checkPrintingAvailable to set
	 */
	public void setCheckPrintingAvailable(boolean checkPrintingAvailable) {
		this.checkPrintingAvailable = checkPrintingAvailable;
	}
	
	public String getEditMemo() {
		return editMemo;
	}

	public void setEditMemo(String editMemo) {
		this.editMemo = editMemo;
	}

}