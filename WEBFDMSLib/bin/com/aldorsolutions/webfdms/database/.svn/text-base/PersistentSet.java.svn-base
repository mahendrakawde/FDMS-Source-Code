package com.aldorsolutions.webfdms.database;

/**
 * A PersistentSet represents a group of Persistent objects
 * based on certain query criteria.
 */

import java.util.Hashtable;
import java.util.Vector;

public abstract class PersistentSet implements PersistentSetI {
	/****************** Instance attributes ****************/
	// objects in the set
	private Vector      objects     = new Vector(); 

	/******************** Constructors *********************/
	/**
	 * Creates a new PersistentSet.
	 */
	public PersistentSet() {
		super();
	}
	/**************** Attribute accessors ******************/
	/**
	 * Called by its peer to add a restored Persistent to the set.
	 * @param p the Persistent to be added to the set
	 */
	public synchronized void addPersistent(Persistent p) {
		if( !objects.contains(p) ) {
			objects.addElement(p);
		}
	}
	/**
	 * Allows external objects to get a list of the Persistent objects
	 * in this set.
	 * @return the Persistent objects in the set
	 */
	public synchronized PersistentI[] getPersistents() {
		PersistentI[] persistents = new PersistentI[objects.size()];

		objects.copyInto(persistents);
		return persistents;
	}
	/**
	 * Subclasses must implement this method in order to tell
	 * this class what to use for a PersistentSetPeer.
	 * @return the peer for this set
	 */
	protected abstract PersistentSetPeer getPersistentSetPeer();
/**
 * Clear contents of Objects vector
 */
public void removePersistents() {
	objects.removeAllElements();
}
	/***************** Persistence operations ****************/
	/**
	 * Loads the set without any query criteria.  In other words,
	 * it loads all objects of a given type.
	 * @exception PersistenceException An error occurred
	 * in accessing the database.
	 */
	public synchronized void restore(Transaction trans) throws PersistenceException {

		getPersistentSetPeer().restore(this, trans);
	}
	/**
	 * Loads a set of persistent objects based on query criteria listed in
	 * a the Hashtable values.
	 * @param trans the Transaction object to use for the restore
	 * @param data the values for filtering the objects of a specific type
	 * @exception imaginary.persist.PersistenceException An error occurred
	 * in accessing the database.
	 */
	public synchronized void restore(Transaction trans, Hashtable data) throws PersistenceException {
		getPersistentSetPeer().restore(this, trans, data);
	}
}
