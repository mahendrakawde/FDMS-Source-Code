package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.custom.AccountDistributionItem;

public class CheckWriterForm extends CheckWriterFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2247384655146634009L;

	private String vendorAddr1;

	private String vendorAddr2;

	private String vendorCityState;

	private String vendorZip;

	private String vendorContact;

	private String vendorPhone;

	private ArrayList <AccountDistributionItem> accountDistributionList;

	private String removeline;

	private String distMemo;

	private String focus;

	private String previewFile;

	private int vitalsID;
	
	private String vitalsName;
	
	private String vitalsCaseNum;
	
	private boolean check1099;
	
	private String check1099Amount;
	
	private boolean checkApprovalRequired;
	
	private boolean checkUseLimits;
	
	private double userLimit;
	
	private long apAccountID;
	
	private String vendorState;

	private long locationID;
	
	private long vendorID;
	
	private String locationVendorMapJavascript;
	
	private String oneTimeVendorName;
	
	private String isOneTimeVendor;
	
	private String sumDistributions;
	
	
	public String getSumDistributions() {
		return sumDistributions;
	}

	public void setSumDistributions(String sumDistributions) {
		this.sumDistributions = sumDistributions;
	}

	public String getIsOneTimeVendor() {
		return isOneTimeVendor;
	}

	public void setIsOneTimeVendor(String isOneTimeVendor) {
		this.isOneTimeVendor = isOneTimeVendor;
	}

	public String getOneTimeVendorName() {
		return oneTimeVendorName;
	}

	public void setOneTimeVendorName(String oneTimeVendorName) {
		this.oneTimeVendorName = oneTimeVendorName;
	}

	/**
	 * @return the locationVendorMapJavascript
	 */
	public String getLocationVendorMapJavascript() {
		return locationVendorMapJavascript;
	}

	/**
	 * @param locationVendorMapJavascript the locationVendorMapJavascript to set
	 */
	public void setLocationVendorMapJavascript(String locationVendorMapJavascript) {
		this.locationVendorMapJavascript = locationVendorMapJavascript;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	/**
	 * @return the vendorState
	 */
	public String getVendorState() {
		return vendorState;
	}

	/**
	 * @param vendorState the vendorState to set
	 */
	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}

	/**
	 * @return the apAccountID
	 */
	public long getApAccountID() {
		return apAccountID;
	}

	/**
	 * @param apAccountID the apAccountID to set
	 */
	public void setApAccountID(long apAccountID) {
		this.apAccountID = apAccountID;
	}

	/**
	 * Insert the method's description here. Creation date: (5/7/2002 10:00:46
	 * AM)
	 * 
	 * @return TreeMap
	 */
	public ArrayList <AccountDistributionItem> getAccountDistributionList() {
		return accountDistributionList;
	}

	/**
	 * Insert the method's description here. Creation date: (5/8/2002 11:46:41
	 * AM)
	 * 
	 * @return String
	 */
	public String getDistMemo() {
		return distMemo;
	}

	/**
	 * Insert the method's description here. Creation date: (5/8/2002 3:27:54
	 * PM)
	 * 
	 * @return String
	 */
	public String getFocus() {
		return focus;
	}

	/**
	 * Insert the method's description here. Creation date: (5/8/2002 9:30:28
	 * PM)
	 * 
	 * @return String
	 */
	public String getPreviewFile() {
		return previewFile;
	}

	/**
	 * Insert the method's description here. Creation date: (5/7/2002 11:12:48
	 * AM)
	 * 
	 * @return String
	 */
	public String getRemoveline() {
		return removeline;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:42:10
	 * PM)
	 * 
	 * @return String
	 */
	public String getVendorAddr1() {
		return vendorAddr1;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:42:30
	 * PM)
	 * 
	 * @return String
	 */
	public String getVendorAddr2() {
		return vendorAddr2;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:42:46
	 * PM)
	 * 
	 * @return String
	 */
	public String getVendorCityState() {
		return vendorCityState;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:43:35
	 * PM)
	 * 
	 * @return String
	 */
	public String getVendorContact() {
		return vendorContact;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:43:59
	 * PM)
	 * 
	 * @return String
	 */
	public String getVendorPhone() {
		return vendorPhone;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:43:23
	 * PM)
	 * 
	 * @return String
	 */
	public String getVendorZip() {
		return vendorZip;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (5/7/2002 10:00:46
	 * AM)
	 * 
	 * @param newAccountDistributionList
	 *            ArrayList
	 */
	public void setAccountDistributionList(ArrayList <AccountDistributionItem> newAccountDistributionList) {
		accountDistributionList = newAccountDistributionList;
	}

	/**
	 * Insert the method's description here. Creation date: (5/8/2002 11:46:41
	 * AM)
	 * 
	 * @param newDistMemo
	 *            String
	 */
	public void setDistMemo(String newDistMemo) {
		distMemo = newDistMemo;
	}

	/**
	 * Insert the method's description here. Creation date: (5/8/2002 3:27:54
	 * PM)
	 * 
	 * @param newFocus
	 *            String
	 */
	public void setFocus(String newFocus) {
		focus = newFocus;
	}

	/**
	 * Insert the method's description here. Creation date: (5/8/2002 9:30:28
	 * PM)
	 * 
	 * @param newPreviewFile
	 *            String
	 */
	public void setPreviewFile(String newPreviewFile) {
		previewFile = newPreviewFile;
	}

	/**
	 * Insert the method's description here. Creation date: (5/7/2002 11:12:48
	 * AM)
	 * 
	 * @param newRemoveline
	 *            String
	 */
	public void setRemoveline(String newRemoveline) {
		removeline = newRemoveline;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:42:10
	 * PM)
	 * 
	 * @param newVendorAddr1
	 *            String
	 */
	public void setVendorAddr1(String newVendorAddr1) {
		vendorAddr1 = newVendorAddr1;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:42:30
	 * PM)
	 * 
	 * @param newVendorAddr2
	 *            String
	 */
	public void setVendorAddr2(String newVendorAddr2) {
		vendorAddr2 = newVendorAddr2;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:42:46
	 * PM)
	 * 
	 * @param newVendorCityState
	 *            String
	 */
	public void setVendorCityState(String newVendorCityState) {
		vendorCityState = newVendorCityState;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:43:35
	 * PM)
	 * 
	 * @param newVendorContact
	 *            String
	 */
	public void setVendorContact(String newVendorContact) {
		vendorContact = newVendorContact;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:43:59
	 * PM)
	 * 
	 * @param newVendorPhone
	 *            String
	 */
	public void setVendorPhone(String newVendorPhone) {
		vendorPhone = newVendorPhone;
	}

	/**
	 * Insert the method's description here. Creation date: (5/6/2002 3:43:23
	 * PM)
	 * 
	 * @param newVendorZip
	 *            String
	 */
	public void setVendorZip(String newVendorZip) {
		vendorZip = newVendorZip;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
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
	 * @return the vitalsID
	 */
	public int getVitalsID() {
		return vitalsID;
	}

	/**
	 * @param vitalsID the vitalsID to set
	 */
	public void setVitalsID(int vitalsID) {
		this.vitalsID = vitalsID;
	}

	/**
	 * @return the vitalsCaseNum
	 */
	public String getVitalsCaseNum() {
		return vitalsCaseNum;
	}

	/**
	 * @return the vitalsName
	 */
	public String getVitalsName() {
		return vitalsName;
	}

	/**
	 * @param vitalsCaseNum the vitalsCaseNum to set
	 */
	public void setVitalsCaseNum(String vitalsCaseNum) {
		this.vitalsCaseNum = vitalsCaseNum;
	}

	/**
	 * @param vitalsName the vitalsName to set
	 */
	public void setVitalsName(String vitalsName) {
		this.vitalsName = vitalsName;
	}

	/**
	 * @return the checkApprovalRequired
	 */
	public boolean isCheckApprovalRequired() {
		return checkApprovalRequired;
	}

	/**
	 * @param checkApprovalRequired the checkApprovalRequired to set
	 */
	public void setCheckApprovalRequired(boolean checkApprovalRequired) {
		this.checkApprovalRequired = checkApprovalRequired;
	}

	/**
	 * @return the checkUseLimits
	 */
	public boolean isCheckUseLimits() {
		return checkUseLimits;
	}

	/**
	 * @param checkUseLimits the checkUseLimits to set
	 */
	public void setCheckUseLimits(boolean checkUseLimits) {
		this.checkUseLimits = checkUseLimits;
	}

	/**
	 * @return the userLimit
	 */
	public double getUserLimit() {
		return userLimit;
	}

	/**
	 * @param userLimit the userLimit to set
	 */
	public void setUserLimit(double userLimit) {
		this.userLimit = userLimit;
	}

	/**
	 * @return the vendorID
	 */
	public long getVendorID() {
		return vendorID;
	}

	/**
	 * @param vendorID the vendorID to set
	 */
	public void setVendorID(long vendorID) {
		this.vendorID = vendorID;
	}
	
}