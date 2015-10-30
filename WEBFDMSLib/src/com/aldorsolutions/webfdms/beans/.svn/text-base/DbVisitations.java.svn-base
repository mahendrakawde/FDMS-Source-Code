package com.aldorsolutions.webfdms.beans;

/*import fdms.util.FormatNumber;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.database.PersistenceException;*/
/**
 * DbVisitations - This class represents visitation related
 * information associated with a case.
 * Creation date: (02/28/2006 4:00 PM)
 * @author: Kier Heyl
 */
public class DbVisitations { //extends com.aldorsolutions.webfdms.database.Persistent {
    //static private final DbServicesPeer peer = new DbServicesPeer();
    
	private int id;
    private int	mainKey;
    private String place;
    private String room;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String date;
    private String startTime;
    private String endTime;
    private String privateVisitation;
    private String notes;
    private char status;

    public DbVisitations() {
    	mainKey = -1;
    	id = 0;
    	place = "";
    	room = "";
    	address = "";
    	address2 = "";
    	city = "";
    	state = "";
    	zip = "";
    	date = "";
    	startTime = "";
    	endTime = "";
    	privateVisitation = "";
    	notes = "";
    	status = 'N';
    }
    
    public DbVisitations(DbVisitations visitation) {
    	mainKey = visitation.getMainKey();
    	id = visitation.getId();
    	place = visitation.getPlace();
    	room = visitation.getRoom();
    	address = visitation.getAddress();
    	address2 = visitation.getAddress2();
    	city = visitation.getCity();
    	state = visitation.getState();
    	zip = visitation.getZip();
    	date = visitation.getDate();
    	startTime = visitation.getStartTime();
    	endTime = visitation.getEndTime();
    	privateVisitation = visitation.getPrivateVisitation();
    	notes = visitation.getNotes();
    	status = visitation.getStatus();
    	
    }
    
    public int getId() {
    	return id;
    }
    public void setId(int in) {
    	id = in;
    }
    public int getMainKey() {
    	return mainKey;
    }
    public void setMainKey(int in) {
    	mainKey = in;
    }
    
    public String getPlace() {
    	return place;
    }
    public void setPlace(String in) {
    	place = in;
    }

    public String getRoom() {
    	return room;
    }
    public void setRoom(String in) {
    	room = in;
    }
    
    public String getAddress() {
    	return address;
    }
    public void setAddress(String in) {
    	address = in;
    }
    
    public String getAddress2() {
    	return address2;
    }
    public void setAddress2(String in) {
    	address2 = in;
    }
    
    public String getCity() {
    	return city;
    }
    public void setCity(String in) {
    	city = in;
    }
    
    public String getState() {
    	return state;
    }
    public void setState(String in) {
    	state = in;
    }
    
    public String getZip() {
    	return zip;
    }
    public void setZip(String in) {
    	zip = in;
    }
    
    public String getPrivateVisitation() {
    	return privateVisitation;
    }
    public void setPrivateVisitation(String in) {
    	privateVisitation = in;
    }
    
    public String getStartTime() {
    	return startTime;
    }
    public void setStartTime(String in) {
    	startTime = in;
    }

    public String getEndTime() {
    	return endTime;
    }
    public void setEndTime(String in) {
    	endTime = in;
    }
    
    public String getDate() {
    	return date;
    }
    public void setDate(String in) {
    	date = in;
    }

    public String getNotes() {
    	return notes;
    }
    public void setNotes(String in) {
    	notes = in;
    }
    
    public char getStatus() {
    	return status;
    }
    
    public void setStatus(char in) {
    	status = in;
    }
    
    /**
     * getPersistentPeer method comment.
     */
    /*protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }*/
    /**
     * isLocked method comment.
     */
    public boolean isLocked() {
        return false;
    }
    
    public String getFormatedAddress(){
    	StringBuffer address = new StringBuffer();
    	if(place != null && !"".equals(place)){
    		address.append(place + ", " );
    	}
    	if(room != null && !"".equals(room)){
    		address.append("Room : "+room + ", " );
    	}
    	if(this.address != null && !"".equals(this.address)){
    		address.append(this.address + ", " );
    	}
    	if(address2 != null && !"".equals(address2)){
    		address.append(address2 + ", " );
    	}
    	if(city != null && !"".equals(city)){
    		address.append(city + ", " );
    	}
    	if(state != null && !"".equals(state)){
    		address.append(state );
    		
    		if(zip != null && !"".equals(zip)){
        		address.append( " - " + zip );
        	}
    	}else{
    		if(zip != null && !"".equals(zip)){
        		address.append( zip );
        	}
    	}
    	
    	if(address.toString().endsWith(",")){
    		address.deleteCharAt(address.length()-1);
    	}
    	
    	return address.toString();
    }
    
    public String getTime(){
    	StringBuffer time = new StringBuffer();
    	
    	if(startTime != null && !"".equals(startTime)){
    		time.append(startTime);
    		if(endTime != null && !"".equals(endTime)){
    			time.append(" - "+endTime);
    		}
    	}else if(endTime != null && !"".equals(endTime)) {
    	
    		time.append(endTime);
    	}
    		
    	return time.toString();
    }
}
