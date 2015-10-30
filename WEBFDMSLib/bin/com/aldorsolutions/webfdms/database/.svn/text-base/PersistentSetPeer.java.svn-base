package com.aldorsolutions.webfdms.database;

/**
 * The PersistentSetPeer interface prescribes methods for data store
 * specific operations.
 */
import java.util.Hashtable;

public interface PersistentSetPeer {
	/**
	 * Restores all objects of a given type from the data store.
	 * @param set the set whose peer this class is
	 * @param t the Transaction to use for the restore
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * restoring the set
	 */
   public abstract void restore(PersistentSet set, Transaction t)
   throws PersistenceException;      
	/**
	 * Restores the set based on the specified query criteria
	 * @param set the set whose peer this class is
	 * @param t the Transaction to use for the restore
	 * @param h the query criteria
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * restoring the set
	 */
	public abstract void restore(PersistentSet set, Transaction t,
				 Hashtable h)
	throws PersistenceException;
}
