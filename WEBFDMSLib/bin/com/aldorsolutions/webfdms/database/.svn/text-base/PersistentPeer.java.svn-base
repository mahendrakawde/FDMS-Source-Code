package com.aldorsolutions.webfdms.database;

/**
 * This interface prescribes methods for peers that handle
 * persistence operations for specific data storage types.
 */
public interface PersistentPeer {
	/**
	 * Handles the creation of an object.
	 * @param p the Persistent being created in the data store
	 * @param t the Transaction for performing the operation
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * creating the object in the data store.
	 */
	public abstract void insert(Persistent p, Transaction t)
	throws PersistenceException;
	/**
	 * Handles the deletion of the object from the data store
	 * @param p the Persistent being deleted from the data store
	 * @param t the Transaction for performing the operation
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * removing the object in the data store.
	 */
	public abstract void remove(Persistent p, Transaction t)	
	throws PersistenceException;
	/**
	 * Handles the restoration of the object from the data store
	 * @param p the Persistent being restored from the data store
	 * @param t the Transaction for performing the operation
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * accessing the object in the data store.
	 */
	public abstract void restore(Persistent p, Transaction t)
	throws PersistenceException;
	/**
	 * Handles updating the object in the data store
	 * @param p the Persistent being updated in the data store
	 * @param t the Transaction for performing the operation
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * updating the object in the data store.
	 */
	public abstract void update(Persistent p, Transaction t)
	throws PersistenceException;
}
