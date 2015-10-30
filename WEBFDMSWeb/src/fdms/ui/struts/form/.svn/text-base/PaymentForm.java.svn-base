package fdms.ui.struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PaymentForm extends PaymentFormBase {

	/**
	 * CJongs
	 */
	private static final long serialVersionUID = 1942517020621897414L;

	private ArrayList componentList;

	private ArrayList paymentList;

	private String submitbutton;

	private String selectedPayment;

	private String previewFile;
	
	private Boolean goodTrans;
	
	private String comment;
	
	private String verifyFinancial;

	public String getVerifyFinancial() {
		return verifyFinancial;
	}

	public void setVerifyFinancial(String verifyFinancial) {
		this.verifyFinancial = verifyFinancial;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	public PaymentForm() {
	}

	/**
	 * Insert the method's description here. Creation date: (6/17/2002 2:50:10
	 * PM)
	 * 
	 * @return TreeMap
	 */
	public ArrayList getComponentList() {
		return componentList;
	}

	/**
	 * Insert the method's description here. Creation date: (6/17/2002 4:13:48
	 * PM)
	 * 
	 * @return ArrayList
	 */
	public ArrayList getPaymentList() {
		return paymentList;
	}

	/**
	 * Insert the method's description here. Creation date: (6/25/2002 3:49:21
	 * PM)
	 * 
	 * @return String
	 */
	public String getPreviewFile() {
		return previewFile;
	}

	/**
	 * Insert the method's description here. Creation date: (6/25/2002 3:07:44
	 * PM)
	 * 
	 * @return String
	 */
	public String getSelectedPayment() {
		return selectedPayment;
	}

	/**
	 * Insert the method's description here. Creation date: (6/19/2002 4:25:30
	 * PM)
	 * 
	 * @return String
	 */
	public String getSubmitbutton() {
		return submitbutton;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		super.reset(actionMapping, request);
	}

	/**
	 * Insert the method's description here. Creation date: (6/17/2002 2:50:10
	 * PM)
	 * 
	 * @param newComponentList
	 *            TreeMap
	 */
	public void setComponentList(ArrayList newComponentList) {
		componentList = newComponentList;
	}

	/**
	 * Insert the method's description here. Creation date: (6/17/2002 4:13:48
	 * PM)
	 * 
	 * @param newPaymentList
	 *            ArrayList
	 */
	public void setPaymentList(ArrayList newPaymentList) {
		paymentList = newPaymentList;
	}

	/**
	 * Insert the method's description here. Creation date: (6/25/2002 3:49:21
	 * PM)
	 * 
	 * @param newPreviewFile
	 *            String
	 */
	public void setPreviewFile(String newPreviewFile) {
		previewFile = newPreviewFile;
	}

	/**
	 * Insert the method's description here. Creation date: (6/25/2002 3:07:44
	 * PM)
	 * 
	 * @param newSelectedComponent
	 *            String
	 */
	public void setSelectedPayment(String newSelectedPayment) {
		selectedPayment = newSelectedPayment;
	}

	/**
	 * Insert the method's description here. Creation date: (6/19/2002 4:25:30
	 * PM)
	 * 
	 * @param newSubmitbutton
	 *            String
	 */
	public void setSubmitbutton(String newSubmitbutton) {
		submitbutton = newSubmitbutton;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
		/** @todo: Override this org.apache.struts.action.ActionForm method */
		return super.validate(actionMapping, request);
	}

	/**
	 * @return the goodTrans
	 */
	public Boolean getGoodTrans() {
		return goodTrans;
	}

	/**
	 * @param goodTrans the goodTrans to set
	 */
	public void setGoodTrans(Boolean goodTrans) {
		this.goodTrans = goodTrans;
	}
}
