package com.aldorsolutions.webfdms.database;

/**
 * The Transaction class is an abstract class implemented by different
 * persistence packages for managing persistence operations on groups
 * of objects.  
 */

import java.util.Vector;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.AuditDTO;
import com.aldorsolutions.webfdms.audit.client.AuditClient;
import com.aldorsolutions.webfdms.util.ReflectionUtil;


public abstract class Transaction {

    private Logger logger = Logger.getLogger(Transaction.class.getName());
    	
	/** For Auditing purposes *********/
	private int transactionUserId = -1;
	private int transactionCompanyId = -1;
	private int transactionLocaleId = -1;
	/**********************************/

	/****************** Instance attributes ******************/
	// the list of objects locked by this Transaction.
	private Vector <Persistent> objects = new Vector <Persistent> ();

	/********************* Constructors **********************/
	/**
	 * Creates a new transaction object.
	 */
	public Transaction() {
		super();
	}
	/*************** Persistence operations ****************/
	/**
	 * Aborts any persistence operations in process and allows
	 * persistent objects which have already done their operation
	 * to take it back.
	 */
	public abstract void abort();
	/***************** Attribute accessors ******************/
	/**
	 * Ties a new Persistent instance to this transactioon.
	 * @param p the persistent associated with this transaction
	 */
	public synchronized void addPersistent(Persistent p) {
		if( !objects.contains(p) ) {
			objects.addElement(p);
		}
	}
	/**
	 * This method should be extended in data store specific
	 * transaction objects for sending a commit to the data store.
	 * Within the Transaction class, it lets all locked objects know
	 * that any data store commit was successful.
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * trying to commit.
	 */
	public abstract void commit() throws PersistenceException;
	/**
	 * @return an array of persistent objects associated with this
	 * transaction
	 */
	synchronized Persistent[] getPersistents() {
		Persistent[] obs = new Persistent[objects.size()];

		objects.copyInto(obs);
		return obs;
	}
	/**
	 * Gets an instance of a Transaction subclass based on the
	 * the default URL for the appliation
	 * Currently, only database persistence
	 * is supported so all URL's are assumed to be JDBC URL's.
	 * @return a new Transaction instance
	 */
	
	/*
	static protected Transaction getTransaction() throws PersistenceException {
		java.util.Properties dbProps = new java.util.Properties();
		String dbUrl;
		dbProps.put("user",     "");
		dbProps.put("password", "");
		dbUrl = "jdbc:odbc:WebFdmsData";
		//if (url.compareTo("PMKE:")==0)
		//	return new BtrieveTransaction();
		//else
		return new DatabaseTransaction(dbUrl, dbProps);
	}
	*/
	
	/**
	 * Gets an instance of a Transaction subclass based on the URL
	 * and properties passed.  Currently, only database persistence
	 * is supported so all URL's are assumed to be JDBC URL's.
	 * @return a new Transaction instance
	 * @deprecated
	 */
	/*
	static protected Transaction getTransaction(String dbUrl, java.util.Properties dbProps, DbUserSession sessionUser) throws PersistenceException {
		if (dbProps == null) {
			dbProps = new java.util.Properties();
			dbProps.put("user", "");
			dbProps.put("password", "");
		}

		if (dbUrl == null) {
			dbUrl = new String("jdbc:odbc:WebFdmsUsers");
		}

		return new DatabaseTransaction(dbUrl, dbProps, sessionUser, false);
	}*/
	
	/**
	 * Insert the method's description here. Creation date: (6/10/2002 10:03:06
	 * PM)
	 */
	/*
	public void printPersistents() {
		Persistent p;
		for (int i = 0; i < objects.size(); i++) {
			p = (Persistent) objects.elementAt(i);
			//logger.debug( "Modified:"+p.getModifications() );
		}	
	}*/
	
	/**
	 * Removes the specified persistent from this transaction
	 * @param p the persistent to be removed
	 */
	public synchronized void removePersistent(Persistent p) {
		if( objects.contains(p) ) {
			objects.removeElement(p);
		}
	}
	/**
	 * This method goes through and triggers a save on all locked objects.
	 * If the saves are all successful, then a commit() is triggered.  If
	 * any one of the saves fails, however, then an abort() is triggered.
	 * @exception imaginary.persist.PersistenceException An error triggering
	 * an abort occurred.
	 */
	public void save() throws PersistenceException {
		Persistent[] obs = getPersistents();
		
		for(int i=0; i<obs.length; i++) {
			try {
				if( obs[i].isModified() ) { // only save modified objects
					AuditDTO auditDto = new AuditDTO();
					Persistent tmpModifiedObj = obs[i];
					StringBuffer msg = new StringBuffer();
					
					String objWasStr = null;
					
					if ( obs[i].isNew() == false) {
						Persistent restoreObj =  (Persistent) ReflectionUtil.getNewInstance(obs[i]);
						restoreObj.setId(obs[i].getId());
						restoreObj.restore(this);
						objWasStr = ReflectionUtil.getFieldValues(restoreObj);
						restoreObj = null;
						//poplute what was
					}
					
					msg.append("Object : " + obs[i].getClass().getName() + "\n");
					msg.append("What obj was : " + objWasStr + "\n");
					
					// set object back to modified state
					obs[i] = tmpModifiedObj;
					obs[i].save(this);
					// Perform Audit Details
					// populate audit obj with details
					msg.append("What it changed to : " + ReflectionUtil.getFieldValues(tmpModifiedObj));
					auditDto.setMessage(msg.toString());
					auditDto.setCompanyId(getTransactionCompanyId());
					auditDto.setUserId(getTransactionUserId());
					auditDto.setLocaleId(getTransactionLocaleId());
					auditDto.setCreatedBy(this.getTransactionUserId());
					//logger.debug("Audit Message: " + msg.toString());
					AuditClient auditClient = new AuditClient(getTransactionCompanyId());
					auditClient.record(auditDto);     
				}
			}
			catch( PersistenceException e ) {
				logger.error("Error creating Audit Record");
				abort(); // abort on error
				throw e;
			}
		}
		try {
			commit(); // commit on success
		}
		catch( PersistenceException e ) {
			logger.error("Error creating Audit Record");
			abort(); // abort if the commit failed
			throw e;
		}
	}
	/**
	 * @return Returns the transactionCompanyId.
	 */
	public int getTransactionCompanyId() {
		return transactionCompanyId;
	}
	/**
	 * @param transactionCompanyId The transactionCompanyId to set.
	 */
	public void setTransactionCompanyId(int transactionCompanyId) {
		this.transactionCompanyId = transactionCompanyId;
	}
	/**
	 * @return Returns the transactionLocaleId.
	 */
	public int getTransactionLocaleId() {
		return transactionLocaleId;
	}
	/**
	 * @param transactionLocaleId The transactionLocaleId to set.
	 */
	public void setTransactionLocaleId(int transactionLocaleId) {
		this.transactionLocaleId = transactionLocaleId;
	}
	/**
	 * @return Returns the transactionUserId.
	 */
	public int getTransactionUserId() {
		return transactionUserId;
	}
	/**
	 * @param transactionUserId The transactionUserId to set.
	 */
	public void setTransactionUserId(int transactionUserId) {
		this.transactionUserId = transactionUserId;
	}
	
	
}
