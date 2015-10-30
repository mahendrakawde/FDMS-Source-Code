package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbCase - Subset of vitals for one case.
 * This class represents identifying and sorting data for a case list function.
 * Creation date: (10/29/2001 2:42:35 PM)
 * @author:
 */
public class DbCase extends Persistent
        implements com.aldorsolutions.webfdms.beans.DbCaseI  {
	
    //private DbCasePeer peer;
    static private final DbCasePeer peer = new DbCasePeer();
    private java.lang.Integer vitalsID;
    private java.lang.String firstName;
    private java.lang.String lastName;
    private java.lang.String caseCode;
    private java.lang.String contractCode;
    private String voidedcode;
    private java.lang.String voidedFromContract;
    private java.lang.String voidedToContract;
    private String preneedStatus;
    private java.lang.String saleDate;
    private java.lang.String deathDate;
    private java.lang.String serviceDate;
    //	private java.lang.String placeOfService;
    //	private int totalPaid;
    //	private int totalCharges;
    private java.lang.String chapelLocation;
    private int locale;
    private int chapelNumber;
    private String additionalServiceDate;
    private String dispositionMethod;
    private String dispositionPlace;
    private String dispositionDate;
    private String director;
    private String arrangeDate;
    private int active;
    private String voidedDate;
    /**
	 * @return the active
	 */
	public int getActiveCode() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActiveCode(int active) {
		this.active = active;
		modify();
	}
	/**
     * DbCase constructor comment.
     */
    public DbCase() {
        super();
        //peer = new DbCasePeer();
    }
    /**
     * getCaseCode method comment.
     */
    public String getCaseCode() {
        return caseCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/22/2001 10:08:08 PM)
     * @return java.lang.String
     */
    public java.lang.String getChapelLocation() {
        return chapelLocation;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @return int
     */
    public int getChapelNumber() {
        return chapelNumber;
    }
    /**
     * getContractCode method comment.
     */
    public String getContractCode() {
        return contractCode;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @return java.lang.String
     */
    public java.lang.String getDeathDate() {
        return deathDate;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:10:03 PM)
     * @return java.lang.String
     */
    public java.lang.String getFirstName() {
        return firstName;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:10:15 PM)
     * @return java.lang.String
     */
    public java.lang.String getLastName() {
        return lastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 1:38:08 PM)
     * @return int
     */
    public int getLocale() {
        return locale;
    }
    /**
     * getPersistentPeer method comment.
     */
    protected PersistentPeer getPersistentPeer() {
        return peer;
    }
    /**
     * getPreneedStatus method comment.
     */
    public String getPreneedStatus() {
        return preneedStatus;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @return java.lang.String
     */
    public java.lang.String getSaleDate() {
        return saleDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @return java.lang.String
     */
    public java.lang.String getServiceDate() {
        return serviceDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:09:40 PM)
     * @return java.lang.Integer
     */
    public java.lang.Integer getVitalsID() {
        return vitalsID;
    }
    /**
     * getVoidedContractCode method comment.
     */
    public String getVoidedContractCode() {
        return voidedcode;
    }
    /**
     * getVoidedFromContr method comment.
     */
    public String getVoidedFromContr() {
        return voidedFromContract;
    }
    /**
     * getVoidedToContr method comment.
     */
    public String getVoidedToContr() {
        return voidedToContract;
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
            DbCase mycase = (DbCase) DbCase.getPersistent(t,1,"com.aldorsolutions.webfdms.beans.DbCase" );
            logger.debug("Got case for: "+mycase.getFirstName());
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
    public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
        // Case master key (vitals ID)
        vitalsID = new Integer(data.get(DbCasePeer.CASE_ID).toString());
        // Set the last name
        lastName = (String)data.get(DbCasePeer.LAST_NAME);
        // Set the first name
        firstName = (String)data.get(DbCasePeer.FIRST_NAME);
        
        caseCode		= data.get(DbCasePeer.CASECODE ).toString();
        contractCode	= data.get(DbCasePeer.CONTRACTCODE).toString();
        preneedStatus 	= data.get(DbCasePeer.PRENEEDSTATUS).toString();
        voidedcode		= data.get(DbCasePeer.VOIDEDCONTRACTCODE).toString();
        voidedFromContract	= data.get(DbCasePeer.VOIDEDFROMCONTRACT).toString();
        voidedToContract	= data.get(DbCasePeer.VOIDEDTOCONTRACT).toString();
        saleDate			= data.get(DbCasePeer.SALEDATE).toString();
        deathDate			= data.get(DbCasePeer.DEATHDATE).toString();
        serviceDate			= data.get(DbCasePeer.SERVICEDATE).toString();
        //	setPlaceOfService( (String)data.get(peer.SERVICEPLACE));
        //	setTotalPaid( 	FormatNumber.parseInteger(data.get(peer.TOTALPAID).toString()));
        //	setTotalCharges(FormatNumber.parseInteger(data.get(peer.TOTALCHARGES).toString()));
        chapelLocation	= data.get(DbCasePeer.CHAPELLOCATION).toString();
        locale = FormatNumber.parseInteger(data.get(DbCasePeer.LOCALE).toString());
        chapelNumber 	= FormatNumber.parseInteger(data.get(DbCasePeer.CHAPELNUMBER).toString());
        active = FormatNumber.parseInteger(data.get(DbCasePeer.ACTIVECODE).toString());
        voidedDate		= data.get(DbCasePeer.VOIDEDDATE).toString();
    }
    /**
     * setCaseCode method comment.
     */
    public void setCaseCode(String code) {
        caseCode = code.trim();
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/22/2001 10:08:08 PM)
     * @param newChapelLocation java.lang.String
     */
    public void setChapelLocation(java.lang.String newChapelLocation) {
        chapelLocation = newChapelLocation;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @param newChapelNumber int
     */
    public void setChapelNumber(int newChapelNumber) {
        chapelNumber = newChapelNumber;
        modify();
    }
    /**
     * setContractCode method comment.
     */
    public void setContractCode(String acontract) {
        if(acontract != null){
        	contractCode = acontract.trim();
        }
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newDeathDate java.lang.String
     */
    public void setDeathDate(java.lang.String newDeathDate) {
        deathDate = newDeathDate;
        modify();
    }
    /**
     * Get the VITALS-ID key for this object from the hashtable and store it
     * in the object as the unique ID.
     */
    public void setId(java.util.Hashtable h) {
        setId(((Integer)h.get(DbCasePeer.CASE_ID)).intValue());
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/24/2001 1:38:08 PM)
     * @param newLocale int
     */
    public void setLocale(int newLocale) {
        locale = newLocale;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @param newSaleDate java.lang.String
     */
    public void setSaleDate(java.lang.String newSaleDate) {
        saleDate = newSaleDate;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newServiceDate java.lang.String
     */
    public void setServiceDate(java.lang.String newServiceDate) {
        serviceDate = newServiceDate;
        modify();
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 3:09:40 PM)
     * @param newVitalsID java.lang.Integer
     */
    public void setVitalsID(java.lang.Integer newVitalsID) {
        vitalsID = newVitalsID;
        modify();
    }
    /**
     * setVoidedContractCode method comment.
     */
    public void setVoidedContractCode(String code) {
        voidedcode = code;
        modify();
    }
    /**
     * setVoidedFromContr method comment.
     */
    public void setVoidedFromContr(String contr) {
        voidedFromContract = contr;
        modify();
    }
    /**
     * setVoidedToContr method comment.
     */
    public void setVoidedToContr(String contr) {
        voidedToContract = contr;
        modify();
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPreneedStatus(String preneedStatus) {
        this.preneedStatus = preneedStatus;
    }
        
    public String getAdditionalServiceDate() {
		return additionalServiceDate;
	}
	public void setAdditionalServiceDate(String additionalServiceDate) {
		this.additionalServiceDate = additionalServiceDate;
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
    

	public String getDispositionMethod() {
		return dispositionMethod;
	}
	public void setDispositionMethod(String in) {
		dispositionMethod = in;
	}
	public String getDispositionPlace() {
		return dispositionPlace;
	}
	public void setDispositionPlace(String in) {
		dispositionPlace = in;
	}
	public String getDispositionDate() {
		return dispositionDate;
	}
	public void setDispositionDate(String in) {
		dispositionDate = in;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String in) {
		director = in;
	}
	public String getArrangeDate() {
		return arrangeDate;
	}
	public void setArrangeDate(String in) {
		arrangeDate = in;
	}
	public String getVoidedDate() {
		return voidedDate;
	}
	public void setVoidedDate(String voidedDate) {
		this.voidedDate = voidedDate;
		modify();
	}
	
	
}
