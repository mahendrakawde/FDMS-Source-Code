package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
/**
 * DbFormsPrinted represents the completion of one form for one case-ID.
 * The peer class provides the class name and SQL for restoring
 * Creation date: (1/17/2002 11:14:33 AM)
 * @author: 
 */
public class DbFormsPrinted extends com.aldorsolutions.webfdms.database.Persistent {
	final static DbFormsPrintedPeer peer= new DbFormsPrintedPeer();
	private int caseId;
	private int formId;
	private boolean completed;
	private java.sql.Date completedDate;
	private java.sql.Time completedTime;
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:12:36 PM)
 * @return int
 */
public int getCaseId() {
	return caseId;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 1:29:10 PM)
 * @return java.sql.Date
 */
public java.sql.Date getCompletedDate() {
	return completedDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 1:29:31 PM)
 * @return java.sql.Time
 */
public java.sql.Time getCompletedTime() {
	return completedTime;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:12:44 PM)
 * @return int
 */
public int getFormId() {
	return formId;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:13:14 PM)
 * @return boolean
 */
public boolean isCompleted() {
	return completed;
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
	setId(				FormatNumber.parseInteger(data.get(DbFormsPrintedPeer.IDENTITY).toString()));
	setCaseId(			FormatNumber.parseInteger(data.get(DbFormsPrintedPeer.CASEID).toString()));
	setFormId(			FormatNumber.parseInteger(data.get(DbFormsPrintedPeer.FORMID).toString()));
	setCompleted(		FormatString.getFirstChar(data.get(DbFormsPrintedPeer.COMPLETED).toString()));
	setCompletedDate(	(java.sql.Date)(data.get(DbFormsPrintedPeer.DATE)));
	setCompletedTime(	(java.sql.Time)(data.get(DbFormsPrintedPeer.TIME)));
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:12:36 PM)
 * @param newCaseId int
 */
public void setCaseId(int newCaseId) {
	modify();
	caseId = newCaseId;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:13:14 PM)
 * @param newCompleted boolean
 */
public void setCompleted(char yesno) {
	modify();
	if (yesno=='Y') completed=true;
	if (yesno=='N') completed=false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:13:14 PM)
 * @param newCompleted boolean
 */
public void setCompleted(boolean newCompleted) {
	modify();
	completed = newCompleted;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 1:29:10 PM)
 * @param newCompletedDate java.sql.Date
 */
public void setCompletedDate(java.sql.Date newCompletedDate) {
	modify();
	completedDate = newCompletedDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 1:29:31 PM)
 * @param newCompletedTime java.sql.Time
 */
public void setCompletedTime(java.sql.Time newCompletedTime) {
	modify();
	completedTime = newCompletedTime;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 12:12:44 PM)
 * @param newFormId int
 */
public void setFormId(int newFormId) {
	modify();
	formId = newFormId;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbFormsPrintedPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbFormsPrintedPeer.IDENTITY)).intValue());
}
}
