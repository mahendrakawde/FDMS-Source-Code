package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;

/**
 * DbCemAtneed represents the Cem at need information for one case.
 * Cem At Need Status - 'A'=Active, 'S'=Serviced, 'C'=Cancelled.
 * The peer class provides the class name and SQL for restoring.
 * Creation date: (1/21/2002 11:14:33 AM)
 * @author: 
 */
public class DbCemProperty extends com.aldorsolutions.webfdms.database.Persistent {
	final static DbCemPropertyPeer peer= new DbCemPropertyPeer();
	static public final String ACTIVE = "A";
	static public final String SERVICED = "S";
	static public final String CANCELLED= "C";
	
	private int propID;
	private int propOwnerID1;
	private int propOwnerID2;
	private int propOwnerID3;
	private int propBuyerID;
	private boolean propPNFlag;
	private boolean propOccStatus;
	private String propType;
	private int propSpaceID;
    private String propAgreementDate;
	private String propAgreeTime;
	private String propCemetery;
	private String propDirector;
	private int propWorkOrderNum;
	
	 /**
     * DbCemProperty constructor comment.
     */
    public DbCemProperty() {
        super();
    }
	

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:52 AM)
 * @return int
 */
public int getPropID() {
	return propID;
}	

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public int getPropOwnerID1() {
	return propOwnerID1;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public int getPropOwnerID2() {
	return propOwnerID2;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public int getPropOwnerID3() {
	return propOwnerID3;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public int PropBuyerID() {
	return propBuyerID;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return boolean
 */
public boolean getPropPNFlag() {
	return propPNFlag;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 7:06:52 AM)
 * @return boolean
 */
public boolean getPropOccStatus() {
	return propOccStatus;
}	
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public String getPropType() {
	return propType;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public int getPropSpaceID() {
	return propSpaceID;
} 
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public String getPropAgreementDate() {
	return propAgreementDate;
} 
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public String getPropAgreeTime() {
	return propAgreeTime;
} 

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public String getPropCemetery() {
	return propCemetery;
} 
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public String getPropDirector() {
	return propDirector;
} 
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public int getPropWorkOrderNum() {
	return propWorkOrderNum ;
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
	//setId(				FormatNumber.parseInteger(data.get(DbCemAtneedPeer.IDENTITY).toString()));
	
   //setCem_plottype(	(String)data.get(DbCemAtneedPeer.PLOTTYPE));
   //setCem_section(	(String)data.get(DbCemAtneedPeer.SECTION));
   //setCem_block(	(String)data.get(DbCemAtneedPeer.BLOCK));
   //setCem_lot_tier(	(String)data.get(DbCemAtneedPeer.LOTTIER));
   //setCem_grave_row(	(String)data.get(DbCemAtneedPeer.GRAVEROW));
   //setCem_Amount(FormatNumber.parseInteger(data.get(DbCemAtneedPeer.CEMAMOUNT).toString()));
   //setCem_ANBuyerAptNo(	(String)data.get(DbCemAtneedPeer.CEMANBUYERAPTNO));
   //setCem_ANBuyerCity(	(String)data.get(DbCemAtneedPeer.CEMANBUYERCITY));
   //setCem_ANBuyerMidName(	(String)data.get(DbCemAtneedPeer.CEMANBUYERMIDNAME));
   //setCem_ANBuyerTitle(	(String)data.get(DbCemAtneedPeer.CEMANBUYERTITLE));
   //setCem_ANBuyerPhone(	(String)data.get(DbCemAtneedPeer.CEMANBUYERPHONE));
   //setCem_ANBuyerState(	(String)data.get(DbCemAtneedPeer.CEMANBUYERSTATE));
   //setCem_ANBuyerStreet(	(String)data.get(DbCemAtneedPeer.CEMANBUYERSTREET));
   //setCem_ANBuyerFirstName(	(String)data.get(DbCemAtneedPeer.CEMANBUYERFIRSTNAME));
   //setCem_ANBuyerLastName(	(String)data.get(DbCemAtneedPeer.CEMANBUYERLASTNAME));
   //setCem_ANBuyerZip(	(String)data.get(DbCemAtneedPeer.CEMANBUYERZIP));
   //setCem_MapID(	(String)data.get(DbCemAtneedPeer. CEMMAPID));
   //setCem_Record(	(String)data.get(DbCemAtneedPeer.CEMRECORD));
   //setCem_ContractDate(	(String)data.get(DbCemAtneedPeer.CEMCONTRACTDATE));
   //setCem_MiscDesc(	(String)data.get(DbCemAtneedPeer.CEMMISCDESC));
   //setCem_MiscAmount(	FormatNumber.parseInteger(data.get(DbCemAtneedPeer.CEMMISCAMOUNT).toString()));
}


/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param  int
 */
public void setPropID(int newPropID) {
	modify();
	propID = newPropID;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param int
 */
public void setPropOwnerID1(int  newPropOwnerID1) {
	modify();
	propOwnerID1 = newPropOwnerID1;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param int
 */
public void setPropOwnerID2(int  newPropOwnerID2) {
	modify();
	propOwnerID1 = newPropOwnerID2;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param int
 */
public void setPropOwnerID3(int newPropOwnerID3) {
	modify();
	propOwnerID1 = newPropOwnerID3;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param int
 */
public void setPropBuyerID(int newPropBuyerID) {
	modify();
	propBuyerID = newPropBuyerID;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param boolean
 */
public void setPropPNFlag(boolean newPropPNFlag) {
	modify();
	propPNFlag = newPropPNFlag;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param boolean
 */
public void setPropOccStatus(boolean newPropOccStatus) {
	modify();
	propOccStatus = newPropOccStatus;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param String
 */
public void setPropType(String newPropType) {
	modify();
	propType = newPropType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @param int
 */
public void setPropSpaceID(int newPropSpaceID) {
	modify();
	propSpaceID = newPropSpaceID;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public void setPropAgreementDate(String newPropAgreementDate) {
	modify();
	propAgreementDate = newPropAgreementDate;
}

/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public void setPropAgreeTime(String newPropAgreeTime) {
	modify();
	propAgreeTime = newPropAgreeTime;
}
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public void setPropCemetery(String newPropCemetery) {
	modify();
	propCemetery = newPropCemetery;
} 
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return String
 */
public void setPropDirector(String newPropDirector) {
	modify();
	propDirector = newPropDirector;
} 
/**
 * Insert the method's description here.
 * Creation date: (1/21/2002 6:55:36 AM)
 * @return int
 */
public void setPropWorkOrderNum(int newPropWorkOrderNum) {
	modify();
	propWorkOrderNum = newPropWorkOrderNum;
} 


/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbCemAtneedPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbCemPropertyPeer.PROPID)).intValue());
}



}
