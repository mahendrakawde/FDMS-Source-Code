package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class CemDecCaseFormBase extends ActionForm {
	
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
    
    public java.lang.Integer getProperty(){
    	return property;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/3/2002 3:45:44 PM)
     * @param newOwnerID int
     */
    public void setDecID(java.lang.Integer newDecID) {
    	decID = newDecID;
    }
    /**
     * method comment.
     */
    public void setFirstName(java.lang.String newFirstName) {
    	firstName = newFirstName;
    }    
    /**
     *  method comment.
     */
    public void setLastName(java.lang.String newLastName) {
    	lastName = newLastName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param newOwnMidName java.lang.String
     */
    public void setMidName(java.lang.String newMidName) {
    	midName = newMidName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param java.lang.String
     */
    public void setAptNo(java.lang.String newAptNo) {
    	aptNo = newAptNo;
    }
   
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 6:51:41 PM)
     * @param  java.lang.String
     */
    public void setTitle(java.lang.String newTitle) {
    	title = newTitle;
    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (11/25/2001 7:53:44 AM)
     * @param java.lang.String
     */
    public void setStreet(java.lang.String newStreet) {
    	street = newStreet;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param java.lang.String
     */
    public void setCity(java.lang.String newCity) {
    	city = newCity;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param java.lang.String
     */
    public void setState(java.lang.String newState) {
    	state = newState;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param java.lang.String
     */
    public void setZip(java.lang.String newZip) {
    	zip = newZip;
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/2001 10:21:01 PM)
     * @param newOwnPhone java.lang.String
     */
    public void setPhone(java.lang.String newPhone) {
    	phone = newPhone;
    }
    public void setProperty (java.lang.Integer newProperty){
    	property = newProperty;
    }
}