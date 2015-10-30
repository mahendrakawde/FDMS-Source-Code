package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;

/**
 * DbCase - Subset of cemetery info for one case.
 * This class represents identifying and sorting data for a case list function.
 * Creation date: (10/29/2001 2:42:35 PM)
 * @author:
 */
public class DbCemOwnCase extends com.aldorsolutions.webfdms.database.Persistent
    {
	
	private Logger logger = Logger.getLogger(DbCemOwnCase.class.getName());
	
    //private DbCasePeer peer;
    static private final DbCemOwnCasePeer peer = new DbCemOwnCasePeer();
    private java.lang.Integer ownerID;
    private java.lang.String ownFirstName;                
    private java.lang.String ownLastName;                
    private java.lang.String ownMidName;
    private java.lang.String ownTitle;
    private java.lang.String ownAptNo;
    private java.lang.String ownStreet;
    private java.lang.String ownCity;
    private java.lang.String ownState;
    private java.lang.String ownZip;
    private java.lang.String ownPhone;
    private java.util.Collection ownProperties;
    /**
     * DbCase constructor comment.
     */
    public DbCemOwnCase() {
        super();
        //peer = new DbCasePeer();
    }
    /**
     * getOwnerID method comment.
     */
    public java.lang.Integer getOwnerID() {
        return ownerID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/22/2001 10:08:08 PM)
     * @return java.lang.String
     */
    public java.lang.String getOwnFirstName() {
        return ownFirstName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @return int
     */
    public java.lang.String getOwnLastName() {
        return ownLastName;
    }
    /**
     * getOwnMidName method comment.
     */
    public String getOwnMidName() {
        return ownMidName;
    }
        
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:10:15 PM)
     * @return java.lang.String
     */
    public java.lang.String getOwnTitle() {
        return ownTitle;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 1:38:08 PM)
     * @return int
     */
    public java.lang.String getOwnAptNo() {
        return ownAptNo;
    }
    
    /**
     * getOwnStreet method comment.
     */
    public String getOwnStreet() {
        return ownStreet;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @return java.lang.String
     */
    public java.lang.String getOwnCity() {
        return ownCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @return java.lang.String
     */
    public java.lang.String getOwnState() {
        return ownState;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:09:40 PM)
     * @return java.lang.Integer
     */
    public java.lang.String getOwnZip() {
        return ownZip;
    }
    /**
     * getOwnPhone method comment.
     */
    public java.lang.String getOwnPhone() {
        return ownPhone;
    }
    /**
     * getOwnPhone method comment.
     */
    public java.util.Collection getOwnProperties() {
        return ownProperties;
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
        String buffer = "", url, driver;
        driver = "sun.jdbc.odbc.JdbcOdbcDriver";

    	Logger logger = Logger.getLogger(DbCase.class.getName());
    	DatabaseTransaction t = null;
        try {
            Class.forName(driver);
            //	    connection = DriverManager.getConnection("jdbc:pervasive://SOLO9100XL:1583/FDMSEFILES");
            //connection = java.sql.DriverManager.getConnection(url, props);
            com.aldorsolutions.webfdms.beans.DbUser user = com.aldorsolutions.webfdms.beans.DbUser.login("Guest","Guest");
            t = (DatabaseTransaction)com.aldorsolutions.webfdms.database.DatabaseTransaction.getTransaction(user);
            logger.debug("Connected to the database.");
            com.aldorsolutions.webfdms.beans.DbCemOwnCase mycase = (com.aldorsolutions.webfdms.beans.DbCemOwnCase)com.aldorsolutions.webfdms.beans.DbCemOwnCase.getPersistent(t,1,"com.aldorsolutions.webfdms.beans.DbCemOwnCase" );
            logger.debug("Connection closed.");
        } catch( ClassNotFoundException e ) {
            logger.debug("Unable to find driver class.");
            return;
        } catch (com.aldorsolutions.webfdms.database.PersistenceException e){
            logger.debug("Persistence Exception. "+e.getMessage());
            return;
        } catch (Throwable th){
            logger.debug("Persistence Exception. "+th.getMessage());
            return;
        } finally {
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
    	ownerID = new Integer(data.get(DbCemOwnCasePeer.OWNERID).toString());
        ownFirstName = data.get(DbCemOwnCasePeer.OWNFIRSTNAME).toString();
       	ownLastName = data.get(DbCemOwnCasePeer.OWNLASTNAME).toString();
        ownMidName		= data.get(DbCemOwnCasePeer.OWNMIDNAME ).toString();
    	ownTitle	= data.get(DbCemOwnCasePeer.OWNTITLE).toString();
    	ownAptNo 	= data.get(DbCemOwnCasePeer.OWNAPTNO).toString();
    	ownStreet		= data.get(DbCemOwnCasePeer.OWNSTREET).toString();
    	ownCity	= data.get(DbCemOwnCasePeer.OWNCITY).toString();
    	ownState	= data.get(DbCemOwnCasePeer.OWNSTATE).toString();
    	ownZip		= data.get(DbCemOwnCasePeer.OWNZIP).toString();
    	ownPhone	= data.get(DbCemOwnCasePeer.OWNPHONE).toString();
                    
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @param newOwnerID int
     */
    public void setOwnerID(java.lang.Integer newOwnerID) {
    	ownerID = newOwnerID;
        modify();
    }
    /**
     * setContractCode method comment.
     */
    public void setOwnFirstName(java.lang.String newOwnFirstName) {
    	ownFirstName = newOwnFirstName;
        modify();
    }
    
    
    /**
     * setContractCode method comment.
     */
    public void setOwnLastName(java.lang.String newOwnLastName) {
    	ownLastName = newOwnLastName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnMidName java.lang.String
     */
    public void setOwnMidName(java.lang.String newOwnMidName) {
    	ownMidName = newOwnMidName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnTitle java.lang.String
     */
    public void setOwnAptNo(java.lang.String newOwnAptNo) {
    	ownAptNo = newOwnAptNo;
        modify();
    }
   
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnTitle java.lang.String
     */
    public void setOwnTitle(java.lang.String newOwnTitle) {
    	ownTitle = newOwnTitle;
        modify();
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @param newOwnStreet java.lang.String
     */
    public void setOwnStreet(java.lang.String newOwnStreet) {
    	ownStreet = newOwnStreet;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnCity java.lang.String
     */
    public void setOwnCity(java.lang.String newOwnCity) {
    	ownCity = newOwnCity;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnState java.lang.String
     */
    public void setOwnState(java.lang.String newOwnState) {
    	ownState = newOwnState;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnZip java.lang.String
     */
    public void setOwnZip(java.lang.String newOwnZip) {
    	ownZip = newOwnZip;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnPhone java.lang.String
     */
    public void setOwnPhone(java.lang.String newOwnPhone) {
    	ownPhone = newOwnPhone;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnProperties java.lang.String
     */
    public void setOwnProperties(java.util.Collection newOwnProperties) {
    	ownProperties = newOwnProperties;
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
