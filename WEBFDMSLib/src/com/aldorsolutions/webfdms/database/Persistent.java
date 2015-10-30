package com.aldorsolutions.webfdms.database;

/**
 * The Persistent class is an abstract base class for all
 * persistent objects.  A persistent object is any object which
 * needs to be saved to a data store.  The class does not care
 * what type of data store is used.  All code specific to a
 * particular kind of persistence is pawned off onto the
 * PersistentPeer.
 * For example: DatabasePeer for saving to relational database
 *              FlatFilePeer to save using OS file I/O operations
 *              ObjectPeer for saving to an OO database
 * @see com.aldorsolutions.webfdms.database.PersistentPeer
 * version 1.3: "objects" hashtable buffers persistent objects.
 * version 1.4: REM'd out buffering objects. Always retrieve from DBMS.
 */

import java.util.Hashtable;

import org.apache.log4j.Logger;

public abstract class Persistent implements PersistentI{

    static Logger logger = Logger.getLogger(Persistent.class.getName());
    /************* Static persistence state values ***********/
    /**
     * The object is in the same form that is stored in the data store.
     */
    static public final int UNMODIFIED    = 1;

    /**
     * The object is brand new and has not yet been saved to the data store.
     */
    static public final int NEW           = 2;

    /**
     * The object has been modified either since creation or restoring
     * from the data store.
     */
    static public final int MODIFIED      = 4;
    /**
     * The object has been deleted but is still in the data store.
     */
    static public final int DELETED       = 8;

    // The master list of objects loaded on the application server
    // This list is a hashtable of hashtables.  For example,
    // objects.get("bank.server.Customer") returns a Hashtable of
    // all customer objects.
    static private Hashtable objects      = new Hashtable();

    /******************* Instance attributes *****************/
    // the object ID
    private int             id            = -1;
    // a Lock object is just a Thread that repeatedly calls mmonitorLock()
    //private Lock            lock          = null;
    // this is a bitmask of persistence states
    private int             modifications = Persistent.UNMODIFIED;
    // indicates a save is in progress; i.e. waiting on commit or rollback
    private boolean         saving        = false;
    // indicates if this persistent has a lock
    protected boolean   locked      = false;
    /*********************** Constructors ********************/
    /**
     * Constructor for the Persistent class.  Exports the object for
     * remote viewing.  Persistent is an abstract class, so it cannot
     * be instantiated directly.
     * @exception java.rmi.RemoteException Failed to export object
     */
    public Persistent() {
        super();
    }
    /************* Transaction management methods **************/
    /**
     * When a save to the data store is aborted for any reason, this
     * method gets called in order to give the object a chance to
     * clean up.  The object should be restored to its state
     * <I>immediately</I> before a save was attempted.  Within the
     * Persistent class, that simply means changing the flag indicating
     * a save is in progress.  Of course, objects extending this class
     * may or may not have their own clean up to do.
     */
    protected synchronized void abort() throws PersistenceException {
        if( !isSaving() ) { // If this object is not saving, it needs no abort
            return;
        }
        saving = false; // stop it from saving
        // keep the lock in place
    }
    /**
     * Use this method with care. It removes all persistent of a given type.
     * So, care must be take that it isn't done while any are in use.
     * Creation date: (1/30/2002 3:56:57 PM)
     */
    public void clearBuffer(String class_name) {
        // find the hashtable for this class in the objects hashtable
        if( objects.containsKey(class_name) ) {
            objects.remove(class_name)  ;
        }
        //objects.clear();
    }
    /**
     * When one or more saves have been sent to the data store successfully,
     * they are committed.  This method allows an object to know the
     * pending save was successful and release any locks.
     */
    protected synchronized void commit() throws PersistenceException {
        // Transaction t;

        // If the object is not being saved, commit not needed
        if( !isSaving() ) {
            return;
        }
        //if( lock == null ) {
        //  throw new PersistenceException("Attempt to commit an unlocked " +
        //                   "object.");
        //}
        // end saving
        saving = false;
        // release lock
        //lock.releaseLock(this);
        //lock = null;
        // reset modification state
        if( isDeleted() ) {
            modifications = Persistent.DELETED;
        }
        else {
            modifications = Persistent.UNMODIFIED;
        }
    }
    /***************** Attribute accessors ****************/
    /**
     * The ID is a unique identifier for this type of object.  In real
     * situations, you probably cannot always use an int for an ID field.
     * To keep this library simple, however, we have assumed that all ID's
     * are integers.
     * @return the unique identifier for this object
     */
    public synchronized int getId() {
        return id;
    }
    /**
     * Modifications is a bitmask of changes which have occurred to
     * an object since it was either restored or created.  Unmodified
     * objects have a Modifications value equal to Persistent.UNMODIFIED.
     * @return the modification state of this object
     * @see imaginary.persist.Persistent#UNMODIFIED
     * @see imaginary.persist.Persistent#NEW
     * @see imaginary.persist.Persistent#MODIFIED
     * @see imaginary.persist.Persistent#DELETED
     */
    public synchronized int getModifications() {
        return modifications;
    }
    public synchronized void setModifications(int newModifications) {
        modifications = newModifications;
    }
    /**
     * Find or restore an object of the specified class based on an ID.
     * @param trans the Transaction to use for data store access
     * @param id the ID of the object to be retrieved
     * @param class_name the name of the class to instantiate
     * @exception imaginary.persist.PersistenceException An error occurred
     * restoring the requested object.
     */
    static public Persistent getPersistent(Transaction trans,
                                           int id,
                                           String class_name)
                                           throws PersistenceException {

    	// Integer i = new Integer(id);
    	// Hashtable h = null;

        // the following 14 lines (including "else {" were rem'ed out
        // get the hashtable of objects for that class
        //if( objects.containsKey(class_name) ) {
        //  h = (Hashtable)objects.get(class_name);
        //}
        //else {
        //  h = new Hashtable();
        //  objects.put(class_name, h);
        //}
        // check if the object already exists
        //if( h.containsKey(i) ) {
        //  return (Persistent)h.get(i);
        //}
        //else {
        Persistent p = null;

        try {
            // no instance found, load a new one
            p = (Persistent)Class.forName(class_name).newInstance();
            // set its ID
            p.setId(id);
            //h.put(i, p);
            // restore it from the data store
            p.restore(trans);
        } catch( ClassNotFoundException e ) {
            logger.error("ClassNotFoundException : ", e);
            return null;
        } catch( InstantiationException e ) {
            logger.error("InstantiationException : ", e);
            return null;
        } catch( IllegalAccessException e ) {
            logger.error("IllegalAccessException : ", e);
            return null;
        }
        
        return p;
        //}
    }
    /************************* Class methods ***********************/
    /**
     * Gives a hashtable of values taken from a data store, this class
     * method will find an existing object for that data or instantiate
     * a new one.  Once it has created that object, it will tell the
     * object to restore itself using that data.
     * @param trans the Transaction object to use for any further data store
     * access
     * @param data  criteria for fetching the object from storage
     *        the data taken from the data store for this object
     * @param class_name the name of the class to instantiate for this
     * set of data
     * @exception imaginary.persist.PersistenceException An error occurred
     * retrieving the object.
     */
    static public Persistent getPersistent(Transaction trans,
                                           Hashtable data,
                                           String class_name)
                                           throws PersistenceException {

        Persistent p = null;
        // Hashtable h = null;
        // Integer i;

        // create an instance of the specified class name
        // so that we know what the ID is
        try {
            p = (Persistent)Class.forName(class_name).newInstance();
            p.setId(data);
        }
        catch( ClassNotFoundException e ) {
            logger.error("ClassNotFoundException : ", e);
            return null;
        }
        catch( InstantiationException e ) {
            logger.error("InstantiationException : ", e);
            return null;
        }
        catch( IllegalAccessException e ) {
            logger.error("IllegalAccessException : ", e);
            return null;
        }
        // find the hashtable for this class in the objects hashtable
        //if( objects.containsKey(class_name) ) {
        //  h = (Hashtable)objects.get(class_name);
        //}
        //else {
        //  h = new Hashtable();
        //  objects.put(class_name, h);
        //}
        // see if this object is already in that list
        //i = new Integer(p.getId());
        //if( h.containsKey(i) ) {
        //  return (Persistent)h.get(i);
        //}
        //else {
        //  h.put(i, p);
        // restore the new object
        p.restore(trans, data);
        return p;
        //}
    }
    /**
     * Persistent prescribes that a subclass implement this method to
     * provide it with an instance of the peer which will perform
     * all data store access for it.
     * @return the PersistentPeer for this class
     * @see imaginary.persist.PersistentPeer
     */
    protected abstract PersistentPeer getPersistentPeer();
    /******************* State check methods ****************/
    /**
     * Checks to see if the object will be deleted from the data store
     * at the next save.
     * @return true if the object has been deleted
     */
    public synchronized boolean isDeleted() {
        return ((modifications & Persistent.DELETED) != 0);
    }
    /**
     * Checks to see if this object differs from the data store.
     * @return true if the object is new, modified, or deleted
     */
    public synchronized boolean isModified() {
        // if any modifiers are present, it has been modified
        return !(modifications == Persistent.UNMODIFIED);
    }
    /**
     * Checks to see if this object is new and not yet in the data store.
     * This will also return false if the object has been deleted.
     * @return true if the object is new
     */
    public synchronized boolean isNew() {
        if( isDeleted() ) {
            return false;
        }
        else {
            return ((modifications & Persistent.NEW) != 0);
        }
    }
    /**
     * Checks to see if a save operation is in progress.
     * @return true if a save operation is in progress
     */
    public synchronized boolean isSaving() {
        return saving;
    }
    /**
     * Whenever an attribute in this object changes from its initial state,
     * this method should be called in order to change the object's state.
     * If an attempt is made to modify a locked by object a RemoteLockHolder
     * that does not hold the lock, then a LockException gets thrown.
     * (RD) this function adds the object to
     * @param h the client making the modifications
     * @exception imaginary.persist.LockException Attempt to modify locked
     * object by a Transaction not holding the lock.
     */
    protected synchronized void modify() {
        // Someone is doing something they should not!
        //if( lock != null ) {
        //  if( h.hashCode() != lock.getHolder().hashCode() ) {
        //    throw new LockException("Illegal attempt to modify " +
        //                "object without a lock.");
        //  }
        //}
        // First modification!
        //if( lock == null ) {
        //  lock = Lock.createLock(h, this);
        //}
        // Add Persistent.MODIFIED to the bitmask
        modifications |= Persistent.MODIFIED;
        //setChanged();
    }
    /******************* Persistence operations *****************/
    /**
     * This method flags the object for deletion.
     * @param h the client make the modification to this object
     * @exception imaginary.persist.LockException Attempt to delete locked
     * object by a client not holding the lock
     */
    public synchronized void remove(){
        // Check for a lock
        //if( lock != null ) {
        //  if( h.hashCode() != lock.getHolder().hashCode() ) {
        //    throw new LockException("Illegal attempt to delete object " +
        //                "without a lock.");
        //  }
        //}
        // Create the lock if it does not exist
        //if( lock == null ) {
        //  lock = Lock.createLock(h, this);
        //}
        // Mark the object deleted
        modifications |= Persistent.DELETED;
        //setChanged();
    }
    /**
     * Restore this object using only its ID for the query.
     * @param t the Transaction to use to access the data store
     * @exception imaginary.persist.PersistenceException An error occurred
     * accessing the data store.
     */
    public void restore(Transaction t) throws PersistenceException {
    	getPersistentPeer().restore(this, t);
    }
    /**
     * This object's peer will go to the data store and grab all of the
     * attributes for this object.  Once it has those values, it will shove
     * them into a Hashtable and pass them to this method.  Objects extending
     * the Persistent class should therefore implement this method so
     * that it takes the values out of the Hashtable and assigns them to the
     * appropriate object attributes.
     * @param t the Transaction used for the restore
     * @param data the Hashtable of object data
     * @exception imaginary.persist.PersistenceException An error occured
     * access the data store.
     */
    public abstract void restore(Transaction t, Hashtable data)
    throws PersistenceException;
    /**
     * This method flags the object as saving and then tells its
     * peer to perform the actual save.
     * @exception PersistenceException An error occured
     * access the data store
     */
    protected synchronized void save(Transaction t) throws PersistenceException {
        PersistentPeer peer;

        //if( lock == null ) {
        //  throw new PersistenceException("Attempt to save an unlocked: " +
        //                   "object.");
        //}
        peer = getPersistentPeer();
        // flag the object as saving
        saving = true;
        // determine what sort of operation is required and tell the
        // peer to do it
        if( isDeleted() ) {
            peer.remove(this, t);
        }
        else if( isNew() ) {
            peer.insert(this, t);
        }
        else {
            peer.update(this, t);
        }
    }
    /**
     * Sets the id field for the object.  Once an id is set, it cannot
     * be changed.
     * @param i the id to assign to the object
     */
    public synchronized void setId(int i) {
        if( id != -1 ) {
            return;
        }
        id = i;
    }
    /**
     * Classes extending Persistent must know how to pull an ID field
     * from a Hashtable describing restoration values.  Any such class
     * should implement this method by grabbing the ID field and
     * calling setId(int).  Example:
     * <PRE>
     * public void setId(Hashtable h) {
     *     setId(((Integer)h.get("t_customer.cust_id")).intValue());
     * }
     * </PRE>
     * @param h the Hashtable of values gotten from the data store on restore
     * @see imaginary.persist.Persistent#restore
     */
    public abstract void setId(Hashtable h);
    /**
     * Sets the modifications bitmask to NEW
     */
    public synchronized void setNew() {
        modifications = Persistent.NEW;
    }
}
