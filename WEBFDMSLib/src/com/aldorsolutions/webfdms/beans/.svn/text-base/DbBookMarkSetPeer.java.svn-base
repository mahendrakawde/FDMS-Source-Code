package com.aldorsolutions.webfdms.beans;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

public class DbBookMarkSetPeer extends DatabaseSetPeer {
	private Logger logger = Logger.getLogger(DbBookMarkSetPeer.class.getName());

	public String getPersistentClass(Hashtable h) {

		return "com.aldorsolutions.webfdms.beans.DbBookMark";
	}

	public String getSql(Hashtable h) {

		String tmp = "SELECT * from bookmarks";

		if ( h.containsKey(DbBookMarkLocationPeer.LOCALEID) && h.containsKey(DbBookMarkLocationPeer.LOCATIONID)  ) {
			tmp = "SELECT bookmarks.* FROM bookmarkxref, bookmarks " +
					"WHERE ( bookmarkxref.bookmark_id = bookmarks.id ) AND " +
					"( (bookmarkxref.locale_id = " + h.get(DbBookMarkLocationPeer.LOCALEID).toString() + 
					" OR bookmarkxref.locale_id = -1) AND (bookmarkxref.location_id = " + 
					h.get(DbBookMarkLocationPeer.LOCATIONID).toString() + " OR bookmarkxref.location_id = -1) ) " +
					" Group by bookmarks.id order by bookmarks.order;";
		} else if (h.containsKey(DbBookMarkLocationPeer.LOCALEID)) {
			tmp = "SELECT bookmarks.* FROM bookmarkxref, bookmarks WHERE ( bookmarkxref.bookmark_id = bookmarks.id ) AND " +
					" ( (bookmarkxref.locale_id = " + h.get(DbBookMarkLocationPeer.LOCALEID).toString() + 
					" OR bookmarkxref.locale_id = -1) ) Group by bookmarks.id order by bookmarks.order;";
		} else if (h.containsKey(DbBookMarkLocationPeer.LOCATIONID)) {
			tmp = "SELECT bookmarks.* FROM bookmarkxref, bookmarks " +
					"WHERE ( bookmarkxref.bookmark_id = bookmarks.id ) AND " +
					"( (bookmarkxref.location_id = " + h.get(DbBookMarkLocationPeer.LOCATIONID).toString() + 
					" OR bookmarkxref.location_id = -1) ) Group by bookmarks.id order by bookmarks.order;";
		}
		
		
		if (h.containsKey("MaxRows")) {
			tmp += " LIMIT ";
			tmp += h.get("MaxRows").toString();
		}

		return tmp;
	}

}
