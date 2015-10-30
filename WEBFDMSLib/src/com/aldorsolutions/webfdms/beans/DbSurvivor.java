package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbSurvivor - This class represents one name to associate with a case. The
 * name contained in this class can be of various relationships to the case. The
 * method getISeqKey() returns a short which identifies the relationship as
 * follows:
 * 
 * <PRE>
 * 
 * 0 Deceased name 1 Informant name -1 Contract number -2 Case number >1 a
 * survivor name 1001 minister1 1002 minister2 1003 organist 1004 soloist
 * 1005-1012 pall bearers 1013-1020 honorary pall bearers
 * 
 * </PRE>
 * 
 * Creation date: (10/29/2001 2:42:35 PM)
 * 
 * @author:
 */
public class DbSurvivor extends com.aldorsolutions.webfdms.database.Persistent {
	private static Logger logger = Logger.getLogger(DbSurvivor.class.getName());

	static public final String INFORMANTSTR 	= "INF";
	static public final String NEXTOFKINSTR 	= "NK";
	static public final String VITALSTR			= "VITAL";
	
	static private final DbSurvivorPeer peer = new DbSurvivorPeer();

	static public final short DECEASED = 0;

	static public final short INFORMANT = 1;

	static public final short MOTHER = 2;

	static public final short FATHER = 3;

	static public final short CASECODE = -1;

	static public final short CONTRACT = -2;

	private int lSurvivorMainKey;

	private String cSurvivorSalutation;

	private String cSurvivorLName; /* Converted from char[50] */

	private String cSurvivorMName; /* Converted from char[50] */

	private String cSurvivorFName; /* Converted from char[50] */

	private String cSurvivorSuffix;

	private String cSurvivorMaidenName;

	private String cSurvivorDisplayName;

	private String cSurvivorRelated; /* Converted from char[20] */

	private String cSurvivorAddr; /* Converted from char[40] */

	private String cSurvivorAddr2; /* Converted from char[40] */

	private String cSurvivorCity; /* Converted from char[33] */

	private String cSurvivorState; /* Converted from char[2] */

	private String cSurvivorZip; /* Converted from char[10] */

	// private BTRIEVE_DATE DateModified;
	// private BTRIEVE_TIME TimeModified;
	private short iSeqKey;

	private String cSurvivorVoided;

	private short iFileVersion;

	private String cSurvivorPhone; /* Converted from char[20] */

	private String cSurvivorPhone2; /* Converted from char[20] */

	private String cSurvivorEmail;

	private String cSurvivorPNLead; /*
									 * Whether the survivor is a good PNLead or
									 * not Y/N
									 */

	private String cSurvivorLiving; /* Whether the survivor is living or not Y/N */
	
	private String cGroupType; //* for a group of people (INF = informant) (NK = Next of Kin)*/

	private String cPreferConmunicate;
	
	/**
	 * When inserting a new survivor, initialize the sequence key to -9999. If
	 * still 9999 when insert takes place, the seuqnce number is replaced with
	 * the next highest sequence number over 1 but less than 999. Creation date:
	 * (1/28/2002 4:51:18 PM)
	 */
	public DbSurvivor() {
		setISeqKey((short) -9999);
	}

	/**
	 * Create a filled out survivor object. When inserting a new survivor,
	 * initialize the sequence key to -9999. If still 9999 when insert takes
	 * place, the seuqnce number is replaced with the next highest sequence
	 * number over 1 but less than 999. Creation date: (1/28/2002 4:51:18 PM)
	 */
	public DbSurvivor(int vitalsid, String salutation, String firstname, String middlename, String lastname,
			String suffix, String maidenname, String displayname, String street, String street2, String city,
			String state, String zip, String phone, String phone2, String email, String relation, String living,
			String pnlead, String Gtype,String preferCommunicate) {
		setLSurvivorMainKey(vitalsid);
		setCSurvivorSalutation(salutation);
		setCSurvivorFName(firstname);
		setCSurvivorMName(middlename);
		setCSurvivorLName(lastname);
		setCSurvivorMaidenName(maidenname);
		setCSurvivorDisplayName(displayname);
		setCSurvivorRelated(relation);
		setCSurvivorAddr(street);
		setCSurvivorAddr2(street2);
		setCSurvivorCity(city);
		setCSurvivorState(state);
		setCSurvivorZip(zip);
		setCSurvivorPhone(phone);
		setCSurvivorPhone2(phone2);
		setCSurvivorEmail(email);
		setCSurvivorLiving(living);
		setCSurvivorPNLead(pnlead);
		setCGroupType(Gtype);
		setCPreferConmunicate(preferCommunicate);
		setNew();
		setISeqKey((short) -9999);
	}

	/**
	 * Add or Update the survivor search information. If the row for the
	 * specified seq# doesn't exist, it will be prepared for addition when saved
	 * Creation date: (9/20/2002 2:46:35 PM)
	 * 
	 * @param t
	 *            com.aldorsolutions.webfdms.database.DatabaseTransaction
	 * @param vitalsid
	 *            int
	 * @param seq
	 *            short
	 * @param name
	 *            java.lang.String
	 * @param street
	 *            java.lang.String
	 * @param zip
	 *            java.lang.String
	 * @param relation
	 *            java.lang.String
	 * @return DbSurvivor object to be saved outside of this method
	 */
	public static DbSurvivor addUpdateSurvivor(DatabaseTransaction t, int vitalsid, short seq, String salutation,
			String firstname, String middlename, String lastname, String suffix, String maidenname, String displayname,
			String street, String street2, String city, String state, String zip, String phone, String phone2,
			String email, String relation, String living, String pnlead,String preferConmunicate) {
		DbSurvivor surv = null;
		DbSurvivor[] survlist = null;

		// first name and last name are required for the survivor to be
		// updated/added
		if ((firstname == null || firstname.compareTo(" ") <= 0) && lastname == null || lastname.compareTo(" ") <= 0) {
			return surv;
		}

		try {
			survlist = FdmsDb.getInstance().getSurvivorsForSequenceNo(t, vitalsid, seq);
			if (survlist.length < 1) {
				// add a new informant for survivor
				surv = new DbSurvivor();
				surv.setNew();
				surv.setLSurvivorMainKey(vitalsid);
				surv.setISeqKey(seq);
			} else {
				surv = survlist[0];
			}
			t.addPersistent(surv);
			surv.setCSurvivorSalutation(salutation);
			surv.setCSurvivorFName(firstname);
			surv.setCSurvivorMName(middlename);
			surv.setCSurvivorLName(lastname);
			surv.setCSurvivorSuffix(suffix);
			surv.setCSurvivorMaidenName(maidenname);
			surv.setCSurvivorDisplayName(displayname);
			surv.setCSurvivorAddr(street);
			surv.setCSurvivorAddr2(street2);
			surv.setCSurvivorCity(city);
			surv.setCSurvivorState(state);
			surv.setCSurvivorZip(zip);
			surv.setCSurvivorPhone(phone);
			surv.setCSurvivorPhone2(phone2);
			surv.setCSurvivorEmail(email);
			surv.setCSurvivorRelated(relation);
			surv.setCSurvivorLiving(living);
			surv.setCSurvivorPNLead(pnlead);
			surv.setCPreferConmunicate(preferConmunicate);
		} catch (Exception pe) {
			logger.error("DbSurvivor addUpdateSurvivor exception:", pe);
		}
		return surv;
	}

	public String getCSurvivorAddr() {
		return cSurvivorAddr;
	}

	public String getCSurvivorAddr2() {
		return cSurvivorAddr2;
	}

	public String getCSurvivorCity() {
		return cSurvivorCity;
	}

	public String getCSurvivorSalutation() {
		return cSurvivorSalutation;
	}

	public String getCSurvivorFName() {
		return cSurvivorFName;
	}

	public String getCSurvivorMName() {
		return cSurvivorMName;
	}

	public String getCSurvivorLName() {
		return cSurvivorLName;
	}

	public String getCSurvivorSuffix() {
		return cSurvivorSuffix;
	}

	public String getCSurvivorMaidenName() {
		return cSurvivorMaidenName;
	}

	public String getCSurvivorDisplayName() {
		return cSurvivorDisplayName;
	}

	public String getCSurvivorPhone() {
		return cSurvivorPhone;
	}

	public String getCSurvivorPhone2() {
		return cSurvivorPhone2;
	}

	public String getCSurvivorEmail() {
		return cSurvivorEmail;
	}

	public String getCSurvivorRelated() {
		return cSurvivorRelated;
	}

	public String getCSurvivorState() {
		return cSurvivorState;
	}

	public String getCSurvivorVoided() {
		return cSurvivorVoided;
	}

	public String getCSurvivorZip() {
		return cSurvivorZip;
	}

	public short getIFileVersion() {
		return iFileVersion;
	}

	public short getISeqKey() {
		return iSeqKey;
	}

	public int getLSurvivorMainKey() {
		return lSurvivorMainKey;
	}

	public String getCSurvivorLiving() {
		return cSurvivorLiving;
	}

	public String getCSurvivorPNLead() {
		return cSurvivorPNLead;
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
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbSurvivorPeer.IDENTITY).toString()));
		cSurvivorAddr = data.get(DbSurvivorPeer.ADDRESS).toString();
		cSurvivorAddr2 = data.get(DbSurvivorPeer.ADDRESS2).toString();
		cSurvivorCity = data.get(DbSurvivorPeer.CITY).toString();
		cSurvivorSalutation = data.get(DbSurvivorPeer.SALUTATION).toString();
		cSurvivorFName = data.get(DbSurvivorPeer.FIRSTNAME).toString();
		cSurvivorMName = data.get(DbSurvivorPeer.MIDDLENAME).toString();
		cSurvivorLName = data.get(DbSurvivorPeer.LASTNAME).toString();
		cSurvivorSuffix = data.get(DbSurvivorPeer.SUFFIX).toString();
		cSurvivorMaidenName = data.get(DbSurvivorPeer.MAIDENNAME).toString();
		cSurvivorDisplayName = data.get(DbSurvivorPeer.DISPLAYNAME).toString();
		cSurvivorRelated = data.get(DbSurvivorPeer.RELATION).toString();
		cSurvivorPhone = data.get(DbSurvivorPeer.PHONE).toString();
		cSurvivorPhone2 = data.get(DbSurvivorPeer.PHONE2).toString();
		cSurvivorState = data.get(DbSurvivorPeer.STATE).toString();
		cSurvivorVoided = data.get(DbSurvivorPeer.VOIDEDCODE).toString();
		cSurvivorZip = data.get(DbSurvivorPeer.ZIP).toString();
		cSurvivorEmail = data.get(DbSurvivorPeer.EMAIL).toString();
		iFileVersion = FormatNumber.parseShort((String) data.get(DbSurvivorPeer.FILEVERSION).toString());
		iSeqKey = FormatNumber.parseShort((String) data.get(DbSurvivorPeer.SEQNUMBER).toString());
		lSurvivorMainKey = FormatNumber.parseInteger((String) data.get(DbSurvivorPeer.CASE_ID).toString());
		cSurvivorLiving = data.get(DbSurvivorPeer.LIVING).toString();
		cSurvivorPNLead = data.get(DbSurvivorPeer.PNLEAD).toString();
		cGroupType = data.get(DbSurvivorPeer.GROUPTYPE).toString();
		cPreferConmunicate = data.get(DbSurvivorPeer.PREFERCONMUNICATE).toString();
	}

	public void setCSurvivorAddr(String lcl_arg0) {
		cSurvivorAddr = lcl_arg0;
		modify();
	}

	public void setCSurvivorAddr2(String lcl_arg0) {
		cSurvivorAddr2 = lcl_arg0;
		modify();
	}

	public void setCSurvivorCity(String lcl_arg0) {
		cSurvivorCity = lcl_arg0;
		modify();
	}

	public void setCSurvivorSalutation(String lcl_arg0) {
		cSurvivorSalutation = lcl_arg0;
		modify();
	}

	public void setCSurvivorFName(String lcl_arg0) {
		cSurvivorFName = lcl_arg0;
		modify();
	}

	public void setCSurvivorMName(String lcl_arg0) {
		cSurvivorMName = lcl_arg0;
		modify();
	}

	public void setCSurvivorLName(String lcl_arg0) {
		cSurvivorLName = lcl_arg0;
		modify();
	}

	public void setCSurvivorSuffix(String lcl_arg0) {
		cSurvivorSuffix = lcl_arg0;
		modify();
	}

	public void setCSurvivorMaidenName(String lcl_arg0) {
		cSurvivorMaidenName = lcl_arg0;
		modify();
	}

	public void setCSurvivorDisplayName(String lcl_arg0) {
		cSurvivorDisplayName = lcl_arg0;
		modify();
	}

	public void setCSurvivorPhone(String lcl_arg0) {
		cSurvivorPhone = lcl_arg0;
		modify();
	}

	public void setCSurvivorPhone2(String lcl_arg0) {
		cSurvivorPhone2 = lcl_arg0;
		modify();
	}

	public void setCSurvivorEmail(String lcl_arg0) {
		cSurvivorEmail = lcl_arg0;
		modify();
	}

	public void setCSurvivorRelated(String lcl_arg0) {
		cSurvivorRelated = lcl_arg0;
		modify();
	}

	public void setCSurvivorState(String lcl_arg0) {
		cSurvivorState = lcl_arg0;
		modify();
	}

	public void setCSurvivorVoided(String lcl_arg0) {
		cSurvivorVoided = lcl_arg0;
		modify();
	}

	public void setCSurvivorZip(String lcl_arg0) {
		cSurvivorZip = lcl_arg0;
		modify();
	}

	public void setCSurvivorLiving(String lcl_arg0) {
		cSurvivorLiving = lcl_arg0;
		modify();
	}

	public void setCSurvivorPNLead(String lcl_arg0) {
		cSurvivorPNLead = lcl_arg0;
		modify();
	}

	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		// if (h.containsKey(DbSurvivorPeer.NAME)){
		// getPersistentPeer().restore(this, t);
		// }
		setId(((Integer) h.get(DbSurvivorPeer.IDENTITY)).intValue());
	}

	public void setIFileVersion(short lcl_arg0) {
		iFileVersion = lcl_arg0;
		modify();
	}

	public void setISeqKey(short lcl_arg0) {
		iSeqKey = lcl_arg0;
		modify();
	}

	public void setLSurvivorMainKey(int lcl_arg0) {
		lSurvivorMainKey = lcl_arg0;
		modify();
	}

	public String getCGroupType() {
		return cGroupType;
	}

	public void setCGroupType(String groupType) {
		cGroupType = groupType;
		modify();
	}

	public String getCPreferConmunicate() {
		return cPreferConmunicate;
	}

	public void setCPreferConmunicate(String preferConmunicate) {
		cPreferConmunicate = preferConmunicate;
		modify();
	}
	
	
}
