package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbComponent - Payment Components Class represents one payment component
 * object. Creation date: (11/26/01 2:36:17 PM)
 * 
 * @author:
 */
public class DbComponent extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbComponentPeer peer = new DbComponentPeer();

	private Logger logger = Logger.getLogger(DbComponent.class.getName());
	
	
	private String code;

	private int vitalsID;

	private String description;

	private int saleAmt;

	private int paidAmt;
	
	private String source;

	private String type;

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:46:03
	 * PM)
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:47:03
	 * PM)
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 7:01:02
	 * PM)
	 * 
	 * @return int
	 */
	public int getPaidAmt() {
		return paidAmt;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:47:28
	 * PM)
	 * 
	 * @return int
	 */
	public int getSaleAmt() {
		return saleAmt;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 7:01:23
	 * PM)
	 * 
	 * @return char
	 */
	public String getType() {
		return type;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:46:39
	 * PM)
	 * 
	 * @return int
	 */
	public int getVitalsID() {
		return vitalsID;
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

		logger.debug("Entering restore");

		if (data == null) {
			logger.debug("No Data");
		}
			
		logger.debug("Id : " + FormatNumber.parseInteger(data.get(DbComponentPeer.IDENTITY).toString()));
		
		setId(FormatNumber.parseInteger(data.get(DbComponentPeer.IDENTITY).toString()));
		logger.debug("VitalsId : " + data.get(DbComponentPeer.CASE_ID).toString());
		setVitalsID(FormatNumber.parseInteger(data.get(DbComponentPeer.CASE_ID).toString()));
		logger.debug("Code : " + (String) data.get(DbComponentPeer.CODE));
		setCode((String) data.get(DbComponentPeer.CODE));
		logger.debug("Description : " + (String) data.get(DbComponentPeer.DESCRIPTION));
		setDescription((String) data.get(DbComponentPeer.DESCRIPTION));
		setPaidAmt(FormatNumber.parseInteger(data.get(DbComponentPeer.PAIDAMOUNT).toString()));
		setSaleAmt(FormatNumber.parseInteger(data.get(DbComponentPeer.SALEAMOUNT).toString()));
		setType(data.get(DbComponentPeer.TYPE).toString());
		setSource(data.get(DbComponentPeer.SOURCE).toString());
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:46:03
	 * PM)
	 * 
	 * @param newCode
	 *            String
	 */
	public void setCode(String newCode) {
		modify();
		code = newCode;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:47:03
	 * PM)
	 * 
	 * @param newDescription
	 *            String
	 */
	public void setDescription(String newDescription) {
		modify();
		description = newDescription;
	}

	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		// if (h.containsKey(peer.NAME)){
		// getPersistentPeer().restore(this, t);
		// }
		setId(((Integer) h.get(DbComponentPeer.IDENTITY)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 7:01:02
	 * PM)
	 * 
	 * @param newPaidAmt
	 *            int
	 */
	public void setPaidAmt(int newPaidAmt) {
		modify();
		paidAmt = newPaidAmt;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:47:28
	 * PM)
	 * 
	 * @param newSaleAmt
	 *            int
	 */
	public void setSaleAmt(int newSaleAmt) {
		modify();
		saleAmt = newSaleAmt;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 7:01:23
	 * PM)
	 * 
	 * @param newType
	 *            char
	 */
	public void setType(String newType) {
		modify();
		type = newType;
	}

	/**
	 * Insert the method's description here. Creation date: (11/26/2001 6:46:39
	 * PM)
	 * 
	 * @param newVitalsID
	 *            int
	 */
	public void setVitalsID(int newVitalsID) {
		modify();
		vitalsID = newVitalsID;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		modify();
		this.source = source;
	}
	
	

}
