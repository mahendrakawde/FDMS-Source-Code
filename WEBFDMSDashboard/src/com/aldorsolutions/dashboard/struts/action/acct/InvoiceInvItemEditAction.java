package com.aldorsolutions.dashboard.struts.action.acct;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.notpersisted.InvoiceInventoryItemLinePO;
import com.aldorsolutions.dashboard.struts.form.acct.InvoiceEditDisplayForm;
import com.aldorsolutions.dashboard.struts.form.acct.InvoiceInvItemEditForm;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class InvoiceInvItemEditAction extends Action {
	
	private Logger logger = Logger.getLogger(InvoiceInvItemEditAction.class.getName());
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		InvoiceInvItemEditForm invItemEditForm = (InvoiceInvItemEditForm) form;
		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
		ApVendorDAO vendorDao = new ApVendorDAO(user);		
		ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(user);
		LocationDAO locationDao = new LocationDAO(user);
		
		InvoiceEditDisplayForm invoiceEditForm = (InvoiceEditDisplayForm) session.getAttribute("invoiceEditDisplayForm");
		
		String itemIDStr = invoiceEditForm.getEditInvItemID();
		
		String defaultAcctID = "";
		InvoiceManager imvMgr = new InvoiceManager();
		DbApAccount account;
		try {
			LocationDTO locationDetail = locationDao.getLocationsByLocationID((int)invoiceEditForm.getLocationID());
			ApVendorDTO vendorDetail = vendorDao.getApVendor((long) invoiceEditForm.getVendorID());
			
			ArrayList <ApVendorLocationDTO> apVendorLocationList = apVendorLocationDao.getApVendorLocationByLocationID(locationDetail.getLocaleID(),locationDetail.getLocationID());
			for ( ApVendorLocationDTO apVendorLocation : apVendorLocationList ) {
				if (apVendorLocation.getVendorID() == invoiceEditForm.getVendorID()){
					//defaultAcctID = apVendorLocation.getDefaultAcct();
					
			    	account = imvMgr.getAccount(request, apVendorLocation.getDefaultAcctID());
			    	if (account == null) {
			    		defaultAcctID = "";
			    	}else {
			    		defaultAcctID = account.getAccountNumber();
			    	}
					
					break;
				}
			}		
			if (defaultAcctID.compareTo("") == 0) {
				//defaultAcctID = vendorDetail.getDefaultAcct();
				account = imvMgr.getAccount(request, vendorDetail.getDefaultAcctID());
		    	if (account == null) {
		    		defaultAcctID = "";
		    	}else {
		    		defaultAcctID = account.getAccountNumber();
		    	}
			} else {
				invItemEditForm.setItemGLAccount(defaultAcctID);
				
			}
				
		} catch ( Exception e ) {
			logger.debug(e.getMessage(), e);
		}

		
		
	
		if ( itemIDStr != null ) {
			long itemID = FormatNumber.parseLong(itemIDStr);
			invItemEditForm.setInvoiceInventoryItemID(itemID);
		}
		
		if ( invItemEditForm.getInvoiceInventoryItemID() <= 0 ) {
			invItemEditForm.setAdded(true);
		}
		
		try {
			loadForm ( invItemEditForm, request, defaultAcctID );
					
		} catch ( Exception e ) {
			logger.debug("Exception: ", e);
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
			saveErrors(request, errors);

			return (mapping.getInputForward());
		}
		
		return (mapping.findForward("invoiceItemEdit"));
	}
	
	/**
	 * 
	 * @param form
	 * @param request
	 * @throws Exception
	 */
	private void loadForm (InvoiceInvItemEditForm form, HttpServletRequest request, String defaultAcctID ) throws Exception {

		HttpSession session = request.getSession();

	
		InvoiceEditDisplayForm invoiceEditForm = (InvoiceEditDisplayForm) session.getAttribute("invoiceEditDisplayForm");
		
		ArrayList <InvoiceInventoryItemLinePO> invoiceItems = invoiceEditForm.getInvoiceInvItems();
		ArrayList <InvoiceInventoryItemLinePO> newInvoiceItemList = new ArrayList <InvoiceInventoryItemLinePO> ();
		double totalValue =  0.0;
		double itemTotal = 0.0;
		SessionHelpers.setApAccountListInSession(request, (int)invoiceEditForm.getLocationID());
		ArrayList <DbApAccount> listOfApVendors  = null;
		DbApAccount dpApAcct = null;
		int index = 0;
		
		listOfApVendors = SessionHelpers.getApAccountList(request, (int)invoiceEditForm.getLocationID());
		for (int i = 0; i < listOfApVendors.size(); i++) {
			dpApAcct = (DbApAccount) listOfApVendors.get(i);

			if ((defaultAcctID).compareToIgnoreCase(dpApAcct.getAccountNumber()) == 0){
				index = dpApAcct.getId();
				break;
			}
			
		}		
		form.setApAccountID(index);
		
		long itemID = form.getInvoiceInventoryItemID();
		
		InvoiceInventoryItemLinePO lineItem = null;
		
		for ( InvoiceInventoryItemLinePO item : invoiceItems ) {
			if ( item.getInvoiceInventoryItemID() == itemID ) {
				lineItem = item;
			} else if ( item.getInvoiceInventoryItemID() != itemID ) {
				totalValue += InvoiceManager.getCostTotal(item );
				itemTotal += item.getItemCost();
				newInvoiceItemList.add(item);
			}  
		}
		
		if ( lineItem != null ) {
			form.setInvoiceInventoryItemID(itemID);
			form.setItemCode(lineItem.getItemCode());
			form.setItemDescription(lineItem.getItemDescription());
			form.setItemGLAccount(lineItem.getItemGLAccount());
			form.setUnitCost(lineItem.getUnitCost());
			form.setItemQuant(lineItem.getItemQuant());
			form.setMerchandise(lineItem.isMerchandise());
			form.setUnitCost(lineItem.getUnitCost());
			form.setItemCost(lineItem.getItemCost());
			form.setApAccountID(lineItem.getApAccountID());
		}
		
		/*
		 	itemTotal += lineItem.getItemCost();
			totalValue += getCostTotal(lineItem, invoiceEditForm );
		 */
		
		totalValue = FormatNumber.roundDoubleDollars(totalValue);
		itemTotal = FormatNumber.roundDoubleDollars(itemTotal);
		
		form.setInvoiceInvItems(newInvoiceItemList);
		form.setInvoiceTotal(totalValue);
		form.setItemTotal(itemTotal);
	}
	
}