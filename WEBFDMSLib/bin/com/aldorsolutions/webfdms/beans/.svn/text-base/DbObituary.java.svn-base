package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.admin.user.model.UserSessionDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbObituary - This class contains the obituary notice for one case.
 * Creation date: (11/26/2001 2:42:35 PM)
 * @author: 
 */
public class DbObituary extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbObituaryPeer peer = new DbObituaryPeer();

	private java.lang.String obitNotice;
	private long asimasId;
	private String obituaryLink;
/**
 * Insert the method's description here.
 * Creation date: (3/28/2002 2:27:03 PM)
 * @return java.lang.String
 * @param user com.aldorsolutions.webfdms.beans.DbUserSession
 * @exception com.aldorsolutions.webfdms.database.PersistenceException The exception description.
 */
public String composeObit(DbUserSession user) throws com.aldorsolutions.webfdms.database.PersistenceException {
	StringBuffer obit = new StringBuffer();
	com.aldorsolutions.webfdms.database.DatabaseTransaction t = null;
	DbVitalsDeceased deceased 	= null;
	DbCustom		 custom		= null;
	DbServices		 services	= null;
	DbVitalsFirstCall firstcall = null;
	DbSurvivor[]	 survivors	= null;
	try {
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		deceased = FdmsDb.getInstance().getVitalsDeceased(t,user.getCurrentCaseID());
		custom	 = FdmsDb.getInstance().getCustom(t, user.getCurrentCaseID());
		services = FdmsDb.getInstance().getServices(t, user.getCurrentCaseID());
		firstcall= FdmsDb.getInstance().getVitalsFirstCall(t, user.getCurrentCaseID());
		survivors= FdmsDb.getInstance().getSurvivorsForID(t, user.getCurrentCaseID())		;
		
		if (deceased==null) return "";
		if (services == null) services = new DbServices();

		obit.append(deceased.getDecFullName());
		obit.append("\nAge ");
		obit.append(firstcall.getAgeYears());
		obit.append(", of ");
		obit.append(deceased.getDecResCityTWP()+", "+deceased.getDecResState()+", ");
		obit.append("passed away "+custom.getCustom(2)+", "+FormatDate.MDYtoMMDDYY(deceased.getDateOfDeath(),java.text.DateFormat.LONG)+" at ");
		obit.append(firstcall.getPlaceDeath());
		// option illness sentence
		if (custom.getCustom(3).compareTo(" ")>0){
			obit.append(" after a "+custom.getCustom(3)+" illness");
		}
		obit.append(". "+custom.getCustom(4)+" was born "+custom.getCustom(5)+". ");
		//obit.append(FormatDate.MDYtoMMDDYY(deceased.getDateOfBirth(),java.text.DateFormat.LONG));
		//obit.append(" in "+deceased.getBirthPlace()+". ");
		// other residences
		obit.append(custom.getCustom(6));
		// He or She
		obit.append("\n");
		if (deceased.getSex().equalsIgnoreCase("M")){
			obit.append("He ");
		} else {
			obit.append("She ");
		}
		obit.append("was married to "+custom.getCustom(7)+" on "+custom.getCustom(8)+" "+custom.getCustom(9)+".\n");
		obit.append(deceased.getDecmrmrs()+" "+deceased.getDecLName());
		// retired?
		if (custom.getCustom(10).compareToIgnoreCase("Retired")>=0){
			obit.append(" retired from ");
		}
		else {
			obit.append(" was employed at ");
		}
		obit.append(custom.getCustom(16)+". ");
		// clubs
		if (deceased.getSex().equalsIgnoreCase("M")){
			obit.append("He ");
		} else {
			obit.append("She ");
		}
		obit.append("was a member of "+custom.getCustom(11)+" "+custom.getCustom(12)+". ");
		// Survivors
		obit.append("\nSurviving are ");
		boolean completedFirst=false;
		for (int i=0; i<survivors.length; i++){
			if (survivors[i].getISeqKey()>1 && survivors[i].getISeqKey()<1000){
				if (completedFirst) obit.append("; ");
				obit.append(survivors[i].getCSurvivorRelated()+", "+survivors[i].getCSurvivorFName()+" "+survivors[i].getCSurvivorLName());
				obit.append(" of "+survivors[i].getCSurvivorCity());
				completedFirst=true;
			}
		}
		obit.append(". ");
		// predeceased
		if (custom.getCustom(13).compareTo(" ")> 0){
			obit.append(custom.getCustom(13)+" predeceased ");
			if (deceased.getSex().equalsIgnoreCase("M")){
				obit.append("him.");
			} else {
				obit.append("her.");
			}
		}
		// funeral service
		if (services.getCSrvTime() !=null){
			obit.append("\nFuneral service will take place "+services.getCSrvTime()+" "+services.getCSrvDayOfWeek()+" at ");
			obit.append(services.getCSrvPlace()+", "+services.getCSrvPlaceStreet()+", "+services.getCSrvPlaceCity()+", ");
			obit.append(services.getCSrvPlaceState()+". "+services.getCSrvMinister1()+" will officiate with burial in ");
			obit.append(services.getCSrvCem()+". ");
		}
		// visitation
		obit.append("Friends may call at "+firstcall.getFacilityName()+", "+firstcall.getFacilityStreet()+", "+firstcall.getFacilityCityStZip());
		obit.append(" "+custom.getCustom(15)+". ");
		if (custom.getCustom(14).compareToIgnoreCase("Y")>=0){
			obit.append("The family will be present. ");
		}
		if (services.getCSrvSpecialService()!=null && services.getCSrvSpecialService().compareTo(" ")>0){
			obit.append("\nSpecial services: "+services.getCSrvSpecialService()+". ");
		}
		obit.append("\nThose planning an expression of sympathy may wish to consider memorials to ");
		if (services.getCSrvDonations()!=null){
			obit.append(services.getCSrvDonations()+". ");
		}
	}
	catch (Exception e){
		throw new PersistenceException(e.getMessage());
	}
    finally {
    	if ( t != null ) {
    		t.closeConnection();
    		t = null;
    	}
    }

	
	return obit.toString();
}

public String createObitCOntent(DbUserSession user) {
	StringBuffer obit = new StringBuffer();
	
	DatabaseTransaction t = null;
	
	DbVitalsDeceased deceased 	= null;
	DbCustom		 custom		= null;
	DbServices		 services	= null;
	DbVitalsFirstCall firstcall = null;
	DbSurvivor[]	 survivors	= null;
	// Get info of Deceased & then if it has value on relatives tab then set values of survivors & preceaded by.
	try {
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		
		deceased = FdmsDb.getInstance().getVitalsDeceased(t,user.getCurrentCaseID());
		custom	 = FdmsDb.getInstance().getCustom(t, user.getCurrentCaseID());
		services = FdmsDb.getInstance().getServices(t, user.getCurrentCaseID());
		firstcall= FdmsDb.getInstance().getVitalsFirstCall(t, user.getCurrentCaseID());
		survivors= FdmsDb.getInstance().getSurvivorsForID(t, user.getCurrentCaseID());
		
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		if(t != null) {
			t.closeConnection();
		}
	}
	
	return obit.toString();
}


/**
 * Insert the method's description here.
 * Creation date: (11/26/2001 3:31:54 PM)
 * @return java.lang.String
 */
public java.lang.String getObitNotice() {
	return obitNotice;
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
/**
 * Move data from hashtable and copy into class variables
 * Peer object restores from database to hashtable.
 */
public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
	setId(			FormatNumber.parseInteger(data.get(DbObituaryPeer.IDENTITY).toString()));
	obitNotice = (String)data.get(DbObituaryPeer.OBITNOTICE);
	asimasId = (Long)FormatNumber.parseLong((data.get(DbObituaryPeer.ASIMASID).toString()));
	obituaryLink = (String) data.get(DbObituaryPeer.OBITUARY_LINK);
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbObituaryPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbObituaryPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (11/26/2001 3:31:54 PM)
 * @param newObitNotice java.lang.String
 */
public void setObitNotice(java.lang.String newObitNotice) {
	obitNotice = newObitNotice;
	modify();
}
/**
 * @return the asimasId
 */
public long getAsimasId() {
	return asimasId;
}
/**
 * @param asimasId the asimasId to set
 */
public void setAsimasId(long asimasId) {
	this.asimasId = asimasId;
}

public String getObituaryLink() {
	return obituaryLink;
}
public void setObituaryLink(String obituaryLink) {
	this.obituaryLink = obituaryLink;
	
	modify();
}


}
