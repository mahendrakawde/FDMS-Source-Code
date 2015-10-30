package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
/**
 * DbCase - Subset of cemetery info for one case.
 * This class represents identifying and sorting data for a case list function.
 * Creation date: (10/29/2001 2:42:35 PM)
 * @author:
 */
public class DbCemSpcCase extends com.aldorsolutions.webfdms.database.Persistent
    {
	
	private Logger logger = Logger.getLogger(DbCemDecCase.class.getName());
	
    //private DbCasePeer peer;
    static private final DbCemOwnCasePeer peer = new DbCemOwnCasePeer();
    private java.lang.Integer spaceID;
    private java.lang.String type;                
    private java.lang.Integer typeNumber;                
    private java.lang.Integer parentID;
    private java.lang.String typeName;
    private java.lang.String desc;
        
    
    /**
     * DbCase constructor comment.
     */
    public DbCemSpcCase() {
        super();
        //peer = new DbCasePeer();
    }
    /**
     * method comment.
     */
    public java.lang.Integer getSpaceID() {
        return spaceID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/22/2001 10:08:08 PM)
     * @return java.lang.String
     */
    public java.lang.String getType() {
        return type;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @return int
     */
    public java.lang.Integer getTypeNumber() {
        return typeNumber;
    }
    /**
     * method comment.
     */
    public Integer getParentID() {
        return parentID;
    }
        
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:10:15 PM)
     * @return java.lang.String
     */
    public java.lang.String getTypeName() {
        return typeName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 1:38:08 PM)
     * @return int
     */
    public java.lang.String getDesc() {
        return desc;
    }
        
    /**
     * getPersistentPeer method comment.
     */
    protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }
       
    /**
     * isLocked method comment.
     */
    public boolean isLocked() {
        return false;
    }
    
    static public void main(String args[]) {

    	Logger logger = Logger.getLogger(DbCase.class.getName());
    	DatabaseTransaction t = null;
        try {
            DbUser user = DbUser.login("Guest","Guest");
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            logger.debug("Connected to the database.");
            DbCemSpcCase mycase = (DbCemSpcCase) DbCase.getPersistent(t,1,"com.aldorsolutions.webfdms.beans.DbCemSpcCase" );
            logger.debug("Got case for: "+mycase.getTypeName());
        } catch (PersistenceException e){
            logger.debug("Persistence Exception. "+e.getMessage());
            return;
        } catch (Throwable e){
            logger.debug("Persistence Exception. "+e.getMessage());
            return;
        }
        finally {
        	if ( t != null ) {
           		t.closeConnection();
           		t = null;
        		logger.debug("Connection closed.");
        	}
        }
        
    }
    
    /**
     * Move data from hashtable and copy into class variables
     * Peer object restores from database to hashtable.
     */
    public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data) throws com.aldorsolutions.webfdms.database.PersistenceException {
        // Case master key (vitals ID)
    	      
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @param int
     */
    public void setSpaceID(java.lang.Integer newSpaceID) {
    	spaceID = newSpaceID;
        modify();
    }
    
    /**
     *  method comment.
     */
    public void setType(java.lang.String newType) {
    	type = newType;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param java.lang.String
     */
    public void setTypeNumber(java.lang.Integer newTypeNumber) {
    	typeNumber = newTypeNumber;
        modify();
    }
    /**
     *  method comment.
     */
    public void setParentID(java.lang.Integer newParentID) {
    	parentID = newParentID;
        modify();
    }
     /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param java.lang.String
     */
    public void setTypeName(java.lang.String newTypeName) {
    	typeName = newTypeName;
        modify();
    }
   
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param java.lang.String
     */
    public void setDesc(java.lang.String newDesc) {
    	desc = newDesc;
        modify();
    }
        
    
	/**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString() {
        // Insert code to print the receiver here.
        // This implementation forwards the message to super. You may replace or supplement this.
        return super.toString();
    }
    /**
     * Get the CemID key for this object from the hashtable and store it
     * in the object as the unique ID.

     */
    public void setId(java.util.Hashtable h) {
        setId(((Integer)h.get(DbCemOwnCasePeer.OWNERID)).intValue());

    }
    

	
}
