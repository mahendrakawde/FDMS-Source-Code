package com.aldorsolutions.webfdms.webservice;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbUser;

public class QBSessionDTO {
	
	private ArrayList transactionIDstoPost = new ArrayList();
	private DbUser user = null;
	private int lastErrorCode = 0;
	private ActionErrors errors = new ActionErrors();
	private AccountingInterfaceDTO accountingInterfaceDTO = null;
	private ArrayList <String> qberrors = null;	
	private ArrayList <Integer> vitalsIDs = new ArrayList <Integer> ();
	private QBVitalsRecordInfo vitalsRecord = null;
	private int currentVitalsID = -1;
	
	public QBSessionDTO ( DbUser user ) {
		this.user = user;
		qberrors = new ArrayList <String> ();
	}
	
	public Integer getNextVitalsID() {
		
		if ( currentVitalsID < vitalsIDs.size() ) {
			currentVitalsID++;
			return ( vitalsIDs.get(currentVitalsID) );
			
		} else {
			return ( null );
		}
	}

	public Integer getCurrentVitalsID() {
		return ( vitalsIDs.get(currentVitalsID) );
	}
	
	public ArrayList<Integer> getVitalsIDs() {
		return vitalsIDs;
	}

	public void setVitalsIDs(ArrayList<Integer> vitalsIDs) {
		this.vitalsIDs = vitalsIDs;
	}

	public int getPercentageOfVitalsComplete() {
		int percentage=(currentVitalsID*100)/vitalsIDs.size();
		return ( percentage );
	}

	public void addTransactionIDstoPost(Collection collection) {
		transactionIDstoPost.addAll(collection);
	}

	public ArrayList getTransactionIDstoPost() {
		return transactionIDstoPost;
	}

	public void setTransactionIDstoPost(ArrayList transactionIDstoPost) {
		this.transactionIDstoPost = transactionIDstoPost;
	}

	public DbUser getUser() {
		return user;
	}

	public void setUser(DbUser user) {
		this.user = user;
	}

	public int getLastErrorCode() {
		return lastErrorCode;
	}

	public void setLastErrorCode(int lastErrorCode) {
		this.lastErrorCode = lastErrorCode;
	}

	public ActionErrors getErrors() {
		return errors;
	}

	public void setErrors(ActionErrors errors) {
		this.errors = errors;
	}
	
	public boolean hasErrors() {
		if ( errors != null && errors.size() > 0 ) {
			return true;
		}
		return false;
	}

	public boolean hasQBErrors() {
		if ( qberrors != null && qberrors.size() > 0 ) {
			return true;
		}
		return false;
	}
	
	public AccountingInterfaceDTO getAccountingInterfaceDTO() {
		return accountingInterfaceDTO;
	}

	public void setAccountingInterfaceDTO(
			AccountingInterfaceDTO accountingInterfaceDTO) {
		this.accountingInterfaceDTO = accountingInterfaceDTO;
	}

	public ArrayList<String> getQbErrors() {
		return qberrors;
	}

	public void setQbErrors(ArrayList<String> qberrors) {
		this.qberrors = qberrors;
	}
	
	public void addQbErrors(ArrayList<String> qberrors) {
		if ( qberrors != null ) {
			this.qberrors.addAll( qberrors );
		}
	}
	
	public QBVitalsRecordInfo getVitalsRecord() {
		return vitalsRecord;
	}

	public void setVitalsRecord(QBVitalsRecordInfo vitalsRecord) {
		this.vitalsRecord = vitalsRecord;
	}

	public String toString () {
		String returnString = this.getClass().getName()  +"\n";
		returnString += "user: " + user.getUserName() + " ";
		returnString += "lastErrorCode: " + lastErrorCode + " ";
		returnString += "errors: " + errors + " ";
		returnString += "QB Errors: " + qberrors + " ";
		return ( returnString );
	}
	
}