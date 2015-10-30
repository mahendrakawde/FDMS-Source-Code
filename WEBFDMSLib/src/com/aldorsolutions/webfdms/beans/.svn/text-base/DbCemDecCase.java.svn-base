package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;

/**
 * DbCase - Subset of cemetery info for one case.
 * This class represents identifying and sorting data for a case list function.
 * Creation date: (10/29/2001 2:42:35 PM)
 * @author:
 */
public class DbCemDecCase extends com.aldorsolutions.webfdms.database.Persistent
    {
	
	private Logger logger = Logger.getLogger(DbCemDecCase.class.getName());
	
    //private DbCasePeer peer;
    static private final DbCemOwnCasePeer peer = new DbCemOwnCasePeer();
    private java.lang.Integer decID;
    private java.lang.String firstName;                
    private java.lang.String lastName;                
    private java.lang.String midName;
    private java.lang.String title;
    private java.lang.String aptNo;
    private java.lang.String street;
    private java.lang.String city;
    private java.lang.String state;
    private java.lang.String zip;
    private java.lang.String phone;
    private java.lang.Integer property;
    /**
     * DbCase constructor comment.
     */
    public DbCemDecCase() {
        super();
        //peer = new DbCasePeer();
    }
    /**
     * getOwnerID method comment.
     */
    public java.lang.Integer getDecID() {
        return decID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/22/2001 10:08:08 PM)
     * @return java.lang.String
     */
    public java.lang.String getFirstName() {
        return firstName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @return int
     */
    public java.lang.String getLastName() {
        return lastName;
    }
    /**
     * getOwnMidName method comment.
     */
    public String getMidName() {
        return midName;
    }
        
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:10:15 PM)
     * @return java.lang.String
     */
    public java.lang.String getTitle() {
        return title;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 1:38:08 PM)
     * @return int
     */
    public java.lang.String getAptNo() {
        return aptNo;
    }
    
    /**
     * getOwnStreet method comment.
     */
    public String getStreet() {
        return street;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @return java.lang.String
     */
    public java.lang.String getCity() {
        return city;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @return java.lang.String
     */
    public java.lang.String getState() {
        return state;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:09:40 PM)
     * @return java.lang.Integer
     */
    public java.lang.String getZip() {
        return zip;
    }
    /**
     * getOwnPhone method comment.
     */
    public java.lang.String getPhone() {
        return phone;
    }
    /**
     * getOwnPhone method comment.
     */
    public java.lang.Integer getProperty() {
        return property;
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
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            logger.debug("Connected to the database.");
            com.aldorsolutions.webfdms.beans.DbCemDecCase mycase = (com.aldorsolutions.webfdms.beans.DbCemDecCase)com.aldorsolutions.webfdms.beans.DbCemDecCase.getPersistent(t,1,"com.aldorsolutions.webfdms.beans.DbCemDecCase" );
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
    	      
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @param newOwnerID int
     */
    public void setDecID(java.lang.Integer newDecID) {
    	decID = newDecID;
        modify();
    }
    /**
     * setContractCode method comment.
     */
    public void setFirstName(java.lang.String newFirstName) {
    	firstName = newFirstName;
        modify();
    }
    
    
    /**
     * setContractCode method comment.
     */
    public void setLastName(java.lang.String newLastName) {
    	lastName = newLastName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnMidName java.lang.String
     */
    public void setMidName(java.lang.String newMidName) {
    	midName = newMidName;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnTitle java.lang.String
     */
    public void setAptNo(java.lang.String newAptNo) {
    	aptNo = newAptNo;
        modify();
    }
   
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnTitle java.lang.String
     */
    public void setTitle(java.lang.String newTitle) {
    	title = newTitle;
        modify();
    }
        
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @param newOwnStreet java.lang.String
     */
    public void setStreet(java.lang.String newStreet) {
    	street = newStreet;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnCity java.lang.String
     */
    public void setCity(java.lang.String newCity) {
    	city = newCity;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnState java.lang.String
     */
    public void setState(java.lang.String newState) {
    	state = newState;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnZip java.lang.String
     */
    public void setZip(java.lang.String newZip) {
    	zip = newZip;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnPhone java.lang.String
     */
    public void setPhone(java.lang.String newPhone) {
    	phone = newPhone;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnProperties java.lang.String
     */
    public void setPropertyID(java.lang.Integer newProperty) {
    	property = newProperty;
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
