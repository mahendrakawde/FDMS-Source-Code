package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * @author: Guadalupe Garcia
 */
public class DbExternalVitalsId extends com.aldorsolutions.webfdms.database.Persistent {

    static private final DbExternalVitalsIdPeer peer = new DbExternalVitalsIdPeer();

    private long vitalsId;
    private long externalAppId;
    private long externalVitalsId;
    
    /**
     * DbUser constructor comment.
     */
    public DbExternalVitalsId() {
        super();
    }

    /**
     * getPersistentPeer method comment.
     */
    protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
        return peer;
    }

    public void restore(
    		com.aldorsolutions.webfdms.database.Transaction t, 
    		java.util.Hashtable data) 
    		throws com.aldorsolutions.webfdms.database.PersistenceException {
    	vitalsId = 
    		FormatNumber.parseLong(data.get(DbExternalVitalsIdPeer.VITALSMASTERKEY).toString());
    	externalAppId = 
    		FormatNumber.parseLong(data.get(DbExternalVitalsIdPeer.EXTERNALAPPID).toString());
    	externalVitalsId = 
    		FormatNumber.parseLong(data.get(DbExternalVitalsIdPeer.EXTERNALVITALSID).toString());

    }        

	public long getExternalVitalsId() {
		return externalVitalsId;
	}

	public void setExternalVitalsId(long externalVitalsId) {
		this.externalVitalsId = externalVitalsId;
	}

	public long getVitalsId() {
		return vitalsId;
	}

	public void setVitalsId(long vitalsId) {
		this.vitalsId = vitalsId;
	}

	public long getExternalAppId() {
		return externalAppId;
	}

	public void setExternalAppId(long externalAppId) {
		this.externalAppId = externalAppId;
	}
	
    public void setId(java.util.Hashtable h) {
        setId(((Integer)h.get(DbExternalVitalsIdPeer.VITALSMASTERKEY)).intValue());
    }	
    
    public boolean isLocked() {
        return false;
    }
 
}
