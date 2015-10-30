package com.aldorsolutions.webfdms.beans;

import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

public class DbBookMarkLocationSetPeer extends DatabaseSetPeer {

	public String getPersistentClass(Hashtable h) {
		
		return "com.aldorsolutions.webfdms.beans.DbBookMarkLocation";
	}

	public String getSql(Hashtable h) {
		
		String tmp=null;
		
		if (h == null || h.size() <= 0) { // If no query criteria are specified
				return "SELECT * from bookmarkxref";
		}else if(h.containsKey(DbBookMarkLocationPeer.BOOKMARKID )){
			tmp = "SELECT * from bookmarkxref where bookmark_id = " + h.get(DbBookMarkLocationPeer.BOOKMARKID);
		}else if(h.containsKey(DbBookMarkLocationPeer.LOCATIONID ) &&
				h.containsKey(DbBookMarkLocationPeer.LOCALEID )){
			tmp = "SELECT * from bookmarkxref where location_id = " + h.get(DbBookMarkLocationPeer.LOCATIONID);
			tmp = tmp + "and locale_id = " + h.get(DbBookMarkLocationPeer.LOCALEID);
		}else if(h.containsKey(DbBookMarkLocationPeer.LOCATIONID )){
			tmp = "SELECT * from bookmarkxref where location_id = " + h.get(DbBookMarkLocationPeer.LOCATIONID);
		}else if(h.containsKey(DbBookMarkLocationPeer.LOCALEID )){
			tmp = "SELECT * from bookmarkxref where locale_id = " + h.get(DbBookMarkLocationPeer.LOCALEID);
		}
		if (h.containsKey("MaxRows")){
			tmp += " LIMIT ";
			tmp += h.get("MaxRows").toString();
		}
		
		return tmp;
	}

}
