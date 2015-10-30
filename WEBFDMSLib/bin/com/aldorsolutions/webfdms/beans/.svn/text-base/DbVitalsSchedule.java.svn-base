package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbVitalsSchedule represents the schedule and check list for one case.
 * The peer class provides the class name and SQL for restoring.
 * Creation date: (1/17/2002 11:14:33 AM)
 * @author: 
 */
public class DbVitalsSchedule extends com.aldorsolutions.webfdms.database.Persistent {
	final static DbVitalsSchedulePeer peer= new DbVitalsSchedulePeer();
	private java.lang.String[] description;
	private int[] checked;
	private java.lang.String[] date;
/**
 * Insert the method's description here.
 * Creation date: (1/18/2002 7:23:12 AM)
 */
public DbVitalsSchedule() {
	super();
	description = new java.lang.String[16];
	checked = new int[16];
	date = new java.lang.String[8];
	
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 9:57:02 PM)
 * @return java.lang.String[]
 */
public int getChecked(int index) {
	if (index <0 || index > 15)
		return 0;
	return checked[index];
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 9:57:02 PM)
 * @return java.lang.String[]
 */
public java.lang.String getDate(int index) {
	if (index <0 || index >7 )
		return "";
	return date[index];
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 9:57:02 PM)
 * @return java.lang.String[]
 */
public java.lang.String getDescription(int index) {
	if (index <0 || index > 15)
		return "";
	return description[index];
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
	setId(				FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.IDENTITY).toString()));
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION1),0);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION2),1);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION3),2);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION4),3);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION5),4);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION6),5);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION7),6);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION8),7);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION9),8);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION10),9);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION11),10);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION12),11);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION13),12);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION14),13);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION15),14);
	setDescription(			(String)data.get(DbVitalsSchedulePeer.DESCRIPTION16),15);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED1).toString()),0);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED2).toString()),1);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED3).toString()),2);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED4).toString()),3);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED5).toString()),4);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED6).toString()),5);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED7).toString()),6);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED8).toString()),7);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED9).toString()),8);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED10).toString()),9);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED11).toString()),10);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED12).toString()),11);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED13).toString()),12);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED14).toString()),13);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED15).toString()),14);
	setChecked(			FormatNumber.parseInteger(data.get(DbVitalsSchedulePeer.CHECKED16).toString()),15);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE1),0);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE2),1);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE3),2);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE4),3);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE5),4);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE6),5);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE7),6);
	setDate(			(String)data.get(DbVitalsSchedulePeer.DATE8),7);
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 9:57:02 PM)
 * @param newDescription java.lang.String
 */
public void setChecked(int newChecked, int index) {
	if (index >=0 && index <16){
		checked[index] = newChecked;
		modify();
	}
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 9:57:02 PM)
 * @param newDescription java.lang.String
 */
public void setDate(java.lang.String newDate, int index) {
	if (index >=0 && index <8){
		date[index] = newDate;
		modify();
	}
}
/**
 * Read default AN checklist from speed data and load into this schedule.
 * Creation date: (9/20/2002 8:40:16 PM)
 */
public void setDefaultAtNeedCheckList(int locale, String dbLookup) {
	DbSpeedData[] checklist = null;

	checklist = FdmsDb.getInstance().getSpeedData(dbLookup, locale, "CheckListAN");
	int max = 16;
	if (checklist.length < max){
		max = checklist.length;
	}
	
	for (int i=0; i<max; i++){
		java.util.StringTokenizer aCheck = new java.util.StringTokenizer(checklist[i].getData(), ",");
		if (aCheck.countTokens()==1){
			setDescription(aCheck.nextToken(),i);
		}
		else if (aCheck.countTokens()>1){
			aCheck.nextToken();	// skip sequence#
			setDescription(aCheck.nextToken(),i);
			if (aCheck.hasMoreTokens() && i >7){
				// 3rd parameter is date
				String param = aCheck.nextToken();
				int days=0;
				try {
					days = Integer.parseInt(param.trim());
				}
				catch (Exception e){	}
				com.aldorsolutions.webfdms.util.JulianDay schedDate = new com.aldorsolutions.webfdms.util.JulianDay();
				schedDate.add(com.aldorsolutions.webfdms.util.JulianDay.DATE, days);
				String datestr = com.aldorsolutions.webfdms.util.FormatDate.convertDateToMMDDYYYY(schedDate.getCalendar().getTime());
				setDate(datestr, i-8);
			}
		}	
	}
}
/**
 * Read default AN checklist from speed data and load into this schedule.
 * Creation date: (9/20/2002 8:40:16 PM)
 */
public void setDefaultPreNeedCheckList(int locale, String dbLookup) {
	DbSpeedData[] checklist = null;

	checklist = FdmsDb.getInstance().getSpeedData(dbLookup, locale, "CheckListPN");
	int max = 16;
	if (checklist.length < max){
		max = checklist.length;
	}
	
	for (int i=0; i<max; i++){
		java.util.StringTokenizer aCheck = new java.util.StringTokenizer(checklist[i].getData(), ",");
		if (aCheck.countTokens()==1){
			setDescription(aCheck.nextToken(),i);
		}
		else if (aCheck.countTokens()>1){
			aCheck.nextToken();	// skip sequence#
			setDescription(aCheck.nextToken(),i);
			if (aCheck.hasMoreTokens() && i >7){
				// 3rd parameter is date
				String param = aCheck.nextToken();
				int days=0;
				try {
					days = Integer.parseInt(param.trim());
				}
				catch (Exception e){	}
				com.aldorsolutions.webfdms.util.JulianDay schedDate = new com.aldorsolutions.webfdms.util.JulianDay();
				schedDate.add(com.aldorsolutions.webfdms.util.JulianDay.DATE, days);
				String datestr = com.aldorsolutions.webfdms.util.FormatDate.convertDateToMMDDYYYY(schedDate.getCalendar().getTime());
				setDate(datestr, i-8);
			}
		}	
	}
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/2002 9:57:02 PM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription, int index) {
	if (newDescription == null){
		return;
	}
	if (index >=0 && index <16) {
		if (newDescription.length()>15){
			newDescription = newDescription.substring(0,15);
		}
		description[index] = newDescription;
		modify();
	}
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbVitalsSchedulePeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbVitalsSchedulePeer.IDENTITY)).intValue());
}
}
