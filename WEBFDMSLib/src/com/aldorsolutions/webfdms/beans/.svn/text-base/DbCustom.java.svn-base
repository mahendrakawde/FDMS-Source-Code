package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbCustom - This class represents custom data associated with one case.
 * Creation date: (11/26/2001 2:42:35 PM)
 * @author: 
 */
public class DbCustom extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbCustomPeer peer = new DbCustomPeer();

	private int	lCustomMainKey;
	private String[] 	cCustom = new String[40];	//  Converted from char[40][50]
	private String[]	cCustomLong = new String[40];	//  Converted from char[39][80]
	//private BTRIEVE_DATE	DateModified;
	//private BTRIEVE_TIME	TimeModified;
	public String getCustom(int ix)
	{
		if (ix <0 || ix>39) ix=0;
	return cCustom[ix];
	}
	public String getCustomLong(int ix)
	{
		if (ix <0 || ix>39) ix=0;
	return cCustomLong[ix];
	}
	public int getMainKey()
	{
	return lCustomMainKey;
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
	setId(				FormatNumber.parseInteger(data.get(DbCustomPeer.IDENTITY).toString()));
	setMainKey(			FormatNumber.parseInteger((String)data.get(DbCustomPeer.CASE_ID).toString()));
	setCustom( 		0,(String)data.get(DbCustomPeer.CUSTOM1));
	setCustom( 		1,(String)data.get(DbCustomPeer.CUSTOM2));
	setCustom( 		2,(String)data.get(DbCustomPeer.CUSTOM3));
	setCustom( 		3,(String)data.get(DbCustomPeer.CUSTOM4));
	setCustom( 		4,(String)data.get(DbCustomPeer.CUSTOM5));
	setCustom( 		5,(String)data.get(DbCustomPeer.CUSTOM6));
	setCustom( 		6,(String)data.get(DbCustomPeer.CUSTOM7));
	setCustom( 		7,(String)data.get(DbCustomPeer.CUSTOM8));
	setCustom( 		8,(String)data.get(DbCustomPeer.CUSTOM9));
	setCustom( 		9,(String)data.get(DbCustomPeer.CUSTOM10));
	setCustom( 		10,(String)data.get(DbCustomPeer.CUSTOM11));
	setCustom( 		11,(String)data.get(DbCustomPeer.CUSTOM12));
	setCustom( 		12,(String)data.get(DbCustomPeer.CUSTOM13));
	setCustom( 		13,(String)data.get(DbCustomPeer.CUSTOM14));
	setCustom( 		14,(String)data.get(DbCustomPeer.CUSTOM15));
	setCustom( 		15,(String)data.get(DbCustomPeer.CUSTOM16));
	setCustom( 		16,(String)data.get(DbCustomPeer.CUSTOM17));
	setCustom( 		17,(String)data.get(DbCustomPeer.CUSTOM18));
	setCustom( 		18,(String)data.get(DbCustomPeer.CUSTOM19));
	setCustom( 		19,(String)data.get(DbCustomPeer.CUSTOM20));
	setCustom( 		20,(String)data.get(DbCustomPeer.CUSTOM21));
	setCustom( 		21,(String)data.get(DbCustomPeer.CUSTOM22));
	setCustom( 		22,(String)data.get(DbCustomPeer.CUSTOM23));
	setCustom( 		23,(String)data.get(DbCustomPeer.CUSTOM24));
	setCustom( 		24,(String)data.get(DbCustomPeer.CUSTOM25));
	setCustom( 		25,(String)data.get(DbCustomPeer.CUSTOM26));
	setCustom( 		26,(String)data.get(DbCustomPeer.CUSTOM27));
	setCustom( 		27,(String)data.get(DbCustomPeer.CUSTOM28));
	setCustom( 		28,(String)data.get(DbCustomPeer.CUSTOM29));
	setCustom( 		29,(String)data.get(DbCustomPeer.CUSTOM30));
	setCustom( 		30,(String)data.get(DbCustomPeer.CUSTOM31));
	setCustom( 		31,(String)data.get(DbCustomPeer.CUSTOM32));
	setCustom( 		32,(String)data.get(DbCustomPeer.CUSTOM33));
	setCustom( 		33,(String)data.get(DbCustomPeer.CUSTOM34));
	setCustom( 		34,(String)data.get(DbCustomPeer.CUSTOM35));
	setCustom( 		35,(String)data.get(DbCustomPeer.CUSTOM36));
	setCustom( 		36,(String)data.get(DbCustomPeer.CUSTOM37));
	setCustom( 		37,(String)data.get(DbCustomPeer.CUSTOM38));
	setCustom( 		38,(String)data.get(DbCustomPeer.CUSTOM39));
	setCustom( 		39,(String)data.get(DbCustomPeer.CUSTOM40));
	setCustomLong( 	0,(String)data.get(DbCustomPeer.LONG1));
	setCustomLong( 	1,(String)data.get(DbCustomPeer.LONG2));
	setCustomLong( 	2,(String)data.get(DbCustomPeer.LONG3));
	setCustomLong( 	3,(String)data.get(DbCustomPeer.LONG4));
	setCustomLong( 	4,(String)data.get(DbCustomPeer.LONG5));
	setCustomLong( 	5,(String)data.get(DbCustomPeer.LONG6));
	setCustomLong( 	6,(String)data.get(DbCustomPeer.LONG7));
	setCustomLong( 	7,(String)data.get(DbCustomPeer.LONG8));
	setCustomLong( 	8,(String)data.get(DbCustomPeer.LONG9));
	setCustomLong( 	9,(String)data.get(DbCustomPeer.LONG10));
	setCustomLong( 	10,(String)data.get(DbCustomPeer.LONG11));
	setCustomLong( 	11,(String)data.get(DbCustomPeer.LONG12));
	setCustomLong( 	12,(String)data.get(DbCustomPeer.LONG13));
	setCustomLong( 	13,(String)data.get(DbCustomPeer.LONG14));
	setCustomLong( 	14,(String)data.get(DbCustomPeer.LONG15));
	setCustomLong( 	15,(String)data.get(DbCustomPeer.LONG16));
	setCustomLong( 	16,(String)data.get(DbCustomPeer.LONG17));
	setCustomLong( 	17,(String)data.get(DbCustomPeer.LONG18));
	setCustomLong( 	18,(String)data.get(DbCustomPeer.LONG19));
	setCustomLong( 	19,(String)data.get(DbCustomPeer.LONG20));
	setCustomLong( 	20,(String)data.get(DbCustomPeer.LONG21));
	setCustomLong( 	21,(String)data.get(DbCustomPeer.LONG22));
	setCustomLong( 	22,(String)data.get(DbCustomPeer.LONG23));
	setCustomLong( 	23,(String)data.get(DbCustomPeer.LONG24));
	setCustomLong( 	24,(String)data.get(DbCustomPeer.LONG25));
	setCustomLong( 	25,(String)data.get(DbCustomPeer.LONG26));
	setCustomLong( 	26,(String)data.get(DbCustomPeer.LONG27));
	setCustomLong( 	27,(String)data.get(DbCustomPeer.LONG28));
	setCustomLong( 	28,(String)data.get(DbCustomPeer.LONG29));
	setCustomLong( 	29,(String)data.get(DbCustomPeer.LONG30));
	setCustomLong( 	30,(String)data.get(DbCustomPeer.LONG31));
	setCustomLong( 	31,(String)data.get(DbCustomPeer.LONG32));
	setCustomLong( 	32,(String)data.get(DbCustomPeer.LONG33));
	setCustomLong( 	33,(String)data.get(DbCustomPeer.LONG34));
	setCustomLong( 	34,(String)data.get(DbCustomPeer.LONG35));
	setCustomLong( 	35,(String)data.get(DbCustomPeer.LONG36));
	setCustomLong( 	36,(String)data.get(DbCustomPeer.LONG37));
	setCustomLong( 	37,(String)data.get(DbCustomPeer.LONG38));
	setCustomLong( 	38,(String)data.get(DbCustomPeer.LONG39));
	setCustomLong( 	39,(String)data.get(DbCustomPeer.LONG40));
}
	public void setCustom(int ix,String lcl_arg0)
	{
		if (ix <0 || ix>39) return;
		if (lcl_arg0.length()> DbCustomPeer.CUSTOM_SHORT_LENGTH){
			lcl_arg0 = lcl_arg0.substring(0,DbCustomPeer.CUSTOM_SHORT_LENGTH-1);
		}
		cCustom[ix] = lcl_arg0;
		modify();
	}
	public void setCustomLong(int ix,String lcl_arg0)
	{
		if (ix <0 || ix>39) return;
		if (lcl_arg0.length()> DbCustomPeer.CUSTOM_LONG_LENGTH){
			lcl_arg0 = lcl_arg0.substring(0,DbCustomPeer.CUSTOM_LONG_LENGTH-1);
		}
		cCustomLong[ix] = lcl_arg0;
		modify();
	}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(peer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbCustomPeer.IDENTITY)).intValue());
}
/**
* Setting the case code with setMainKey() should only be done when
* adding a new record.
*/
public void setMainKey(int lcl_arg0)
	{
	lCustomMainKey = lcl_arg0;
	setId(lcl_arg0);
	}
}
