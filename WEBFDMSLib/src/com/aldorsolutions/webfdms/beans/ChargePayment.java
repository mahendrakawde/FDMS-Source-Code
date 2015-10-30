package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.MailUtil;

public class ChargePayment {

	public boolean verifyOutOfBalance(int vitalsID, DbUserSession user){
		boolean result = true;
	
		ChargeDAO charge = new ChargeDAO(user);
		int totalCharge = charge.getTotalCharge(vitalsID);
		TransactionhistoryDAO tran = new TransactionhistoryDAO(user);
		int totalChargeInTran = tran.getTotalCharge(vitalsID);
		
		if (totalCharge != totalChargeInTran) {
			result = false;
		}
		
		return result;
	}
	
	public int getWrongCode(int vitalsID, DbUserSession user){
		int result = 0;
	
		ChargeDAO charge = new ChargeDAO(user);
		result = charge.getWrongCode(vitalsID);
		if (result > 0){
			sendMail( vitalsID, user);
		}
		return result;
	}
	
	public void sendMail(int vitalsID,DbUserSession sessionUser){
		CompanyManagerBean cmBean = new CompanyManagerBean();	
		CompanyDTO company = cmBean.getCompany(sessionUser.getCompanyID());
		
        	ArrayList <String> emailArray = new ArrayList <String> ();
//			emailArray.add("cjongs@aldorsolutions.com");
//			emailArray.add("support@aldorsolutions.com");
        	emailArray.add("bshah@aldorsolutions.com");
			String subject ="Error Contract line number.";	
			StringBuffer message = new StringBuffer();
			String endLine = "\r\n";
			message.append(endLine + endLine);
			message.append("Automatic email: Contrct Line number cannot be 0 or 9000. " + endLine + endLine);
			message.append("vitalMasterKey : "+ vitalsID  + endLine);
			message.append("     Companyid : "+ sessionUser.getCompanyID()+ endLine);
			message.append("  Company Name : "+ company.getName()+ endLine);
			message.append("       DataURL : "+ company.getDataURL()+ endLine);
			message.append("      DBLookup : "+ company.getDbLookup()+ endLine);
			message.append(endLine + endLine);
			message.append("     User Name : "+ sessionUser.getFirstName() + " "+ sessionUser.getLastName() + endLine);
			message.append(" Email address : "+ sessionUser.getEmailAddr() + endLine);
			message.append("   Locale Name : "+ sessionUser.getLocaleName()+ endLine);
			message.append(" Location Name : "+ sessionUser.getLocationName() + endLine);
			message.append(endLine + endLine);
			try {
				MailUtil.sendEmail(sessionUser,sessionUser.getConfigID(), emailArray, null, null, subject, message.toString());
			 }  catch(Exception pe) {
		        
		     } finally {
		            
		     }
		
	}
	
	public boolean unpostFinancial(int vitalsid, DbUserSession sessionUser){
		boolean result = true;
		DatabaseTransaction t = null;
		try {
			DbFinancialSummary finan = null;
			FdmsDb fdmsdb = FdmsDb.getInstance();
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			finan = fdmsdb.getFinancial(t, vitalsid);
			//set the senttoaccounting to be fault so that we can repost the financial again.
			finan.setIARsentBox((short)0);
			t.addPersistent(finan);
			
			DbHistory[] hist = null;
			hist = FdmsDb.getInstance().getHistoryForCase(t, vitalsid);
			//set all of sale transactionhistory to 'Y' which in the DeleteTransaction = 'Y' 
			for (int i = 0; i < hist.length; i++) {
				if (hist[i] != null ) {
					char SPF = hist[i].getCHistSPF();
					if (SPF != DbHistory.HIST_SPF_PAYMENT.charAt(0)) {
						t.addPersistent(hist[i]);
						hist[i].remove();
					}
				}
			}
			
			t.save();
		
		} catch (PersistenceException pe) {
			result = false;
		} catch (Exception pe) {
			result = false;
			
		} finally {
			if (t != null)
				t.closeConnection();
		}
		
		return result;
	}
	
	public boolean repostFinancial(int vitalsid, DbUserSession sessionUser){
		boolean result = true;
		DatabaseTransaction t = null;
		try {
		
			DbFinancialSummary finan = null;
	        DbVitalsFirstCall firstcall = null;
	        DbCase aCase = null;
	        DbPreneed preneed = null;
	        DbVitalsDeceased vitals = null;
			
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	        finan = FdmsDb.getInstance().getFinancial(t,vitalsid);
	        firstcall = FdmsDb.getInstance().getVitalsFirstCall(t,vitalsid);
	        preneed = FdmsDb.getInstance().getPreneed(t, vitalsid);
	        aCase = FdmsDb.getInstance().getCase(t, vitalsid);
	        vitals = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
	        LocaleDTO locale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
	        int action = FdmsDb.FINANCIAL_FIRST_POST;
	        finan.setIARsentBox((short)1);
	        InventorySold soldData = null;
	        DbCase caseinfo = FdmsDb.getInstance().getCase(t, vitalsid);

	        
	        FdmsDb fdmsdb = FdmsDb.getInstance();
	        Map financialInformationMap = (HashMap) fdmsdb.getChargesForCase(t, vitalsid);
	        Iterator fiIterator = financialInformationMap.values().iterator();
	        DbChargeItem unsortedLineItem;
	        int totalCharges = 0;
	        DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
	        int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
					DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
	        Map sortedFinancialInformationMap = new TreeMap();
	        while (fiIterator.hasNext()) {
	        	unsortedLineItem = (DbChargeItem) fiIterator.next();
				totalCharges += unsortedLineItem.getAmount();
				if (orderByContLine == 1){
					sortedFinancialInformationMap.put(new Integer(unsortedLineItem.getType()+""+unsortedLineItem.getSequenceNumber()),unsortedLineItem);
				}else {
					sortedFinancialInformationMap.put(new Integer(unsortedLineItem.getSequenceNumber()),unsortedLineItem);
				}
	        }
	        
	        java.util.TreeMap financialInformationLineItems;
	        financialInformationLineItems = new TreeMap();
	        Iterator sfiIterator = sortedFinancialInformationMap.values().iterator();
	        DbChargeItem sortedLineItem;
	        while (sfiIterator.hasNext()) {

				sortedLineItem = (DbChargeItem) sfiIterator.next();
				
				FinancialInformationLineItem fiLineItem = new FinancialInformationLineItem(sortedLineItem);
				try {

					DbInvMaster dbInvMaster = FdmsDb.getInstance().getInvMaster(t, sortedLineItem.getInvSeqNo());
			
					//check if the transactionhistory has been set
					fiLineItem.setPostedToTran(false);
					
					
					fiLineItem.setItemPicture(dbInvMaster.getImageUrl());
					fiLineItem.setFromPackage(sortedLineItem.isFromPackage());
					// Processing for serial numbered inventory items
					if (dbInvMaster.getCStockType().equalsIgnoreCase(DbInvMaster.STOCK_TYPE_SERIAL)) {
						fiLineItem.setStockType(DbInvMaster.STOCK_TYPE_SERIAL);
						fiLineItem.setSerialNumber("- Select -");
						DbInvOnHand onhand = FdmsDb.getInstance().getInvOnHand(t,
								fiLineItem.getDbChargeItem().getInvOnHandID());
						if (onhand != null && fiLineItem.getDbChargeItem().getInvOnHandID() > 0) {
							fiLineItem.setSerialNumber(onhand.getCSerialNumber());
						}
						// if contract is not yet posted then serial number is modifiable
						if (finan.getIARsentBox() == 0) {
							fiLineItem.setSerialNumberModifiable(1);
						}
					}
					
				} catch (Exception e) {
					fiLineItem.setItemPicture("");
				}
				// Calculate default GL account for display purposes only at this point
				// GL will be recaclulated when and if saving financial page.
				// If DbCharge object already has a GL account then use it instead of the default.
				if (finan != null) {
					if (sortedLineItem.getGlAcct() == null
							|| sortedLineItem.getGlAcct().compareTo("        ") <= 0) {
						soldData = new InventorySold();
						FdmsDb.getInstance().getDefaultGlSalesAccts(t, soldData, sessionUser.getRegion(),
								caseinfo.getChapelNumber(), String.valueOf(sortedLineItem.getItemCategoryType()),
								finan.getCFinSaleType(), firstcall.getDispositionCode());
						fiLineItem.setItemGLAccount(soldData.getAcctRevenue());
					}
				}
//				sort by Seq or Cont by locale setup
				if (orderByContLine == 1){
					financialInformationLineItems.put(new Integer(fiLineItem.getItemTypeCode()+""+fiLineItem.getItemSequenceNumber()), fiLineItem);
				}else {
					financialInformationLineItems.put(new Integer(fiLineItem.getItemSequenceNumber()), fiLineItem);
				}

//				financialInformationLineItems.put(new Integer(fiLineItem.getItemSequenceNumber()), fiLineItem);
			
	        }
	        
	        
	        FdmsDb.getInstance().postFinancial( t, action, finan, financialInformationLineItems, firstcall, locale,sessionUser.getDbLookup());
			
//			t.save();
		
		} catch (PersistenceException pe) {
			result = false;
		} catch (Exception pe) {
			result = false;
			
		} finally {
			if (t != null)
				t.closeConnection();
		}
		
		return result;
	}
	

	
}
