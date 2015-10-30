package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class CemOwnCaseFormBase extends ActionForm {
	
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
    
    public java.util.Collection getOwnProperties(){
    	return ownProperties;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @param newOwnerID int
     */
    public void setOwnerID(java.lang.Integer newOwnerID) {
    	ownerID = newOwnerID;
    }
    /**
     * setContractCode method comment.
     */
    public void setOwnFirstName(java.lang.String newOwnFirstName) {
    	ownFirstName = newOwnFirstName;
    }    
    /**
     * setContractCode method comment.
     */
    public void setOwnLastName(java.lang.String newOwnLastName) {
    	ownLastName = newOwnLastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnMidName java.lang.String
     */
    public void setOwnMidName(java.lang.String newOwnMidName) {
    	ownMidName = newOwnMidName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnTitle java.lang.String
     */
    public void setOwnAptNo(java.lang.String newOwnAptNo) {
    	ownAptNo = newOwnAptNo;
    }
   
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnTitle java.lang.String
     */
    public void setOwnTitle(java.lang.String newOwnTitle) {
    	ownTitle = newOwnTitle;
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @param newOwnStreet java.lang.String
     */
    public void setOwnStreet(java.lang.String newOwnStreet) {
    	ownStreet = newOwnStreet;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnCity java.lang.String
     */
    public void setOwnCity(java.lang.String newOwnCity) {
    	ownCity = newOwnCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnState java.lang.String
     */
    public void setOwnState(java.lang.String newOwnState) {
    	ownState = newOwnState;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnZip java.lang.String
     */
    public void setOwnZip(java.lang.String newOwnZip) {
    	ownZip = newOwnZip;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnPhone java.lang.String
     */
    public void setOwnPhone(java.lang.String newOwnPhone) {
    	ownPhone = newOwnPhone;
    }
    public void setOwnProperties (java.util.Collection newownProperties){
    	ownProperties = newownProperties;
    }
}