package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * @author: Guadalupe Garcia
 */
public class DbExternalAppConfig extends com.aldorsolutions.webfdms.database.Persistent {

    static private final DbExternalAppConfigPeer peer = new DbExternalAppConfigPeer();

    private long externalAppId;
    private long externalAppConfigId;
    
    /**
     * DbUser constructor comment.
     */
    public DbExternalAppConfig() {
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
    	
    	externalAppId = 
    		FormatNumber.parseLong(data.get(DbExternalAppConfigPeer.EXTERNALAPPID).toString());
    	externalAppConfigId = 
    		FormatNumber.parseLong(data.get(DbExternalAppConfigPeer.EXTERNALAPPCONFIGID).toString());

    }

	public long getExternalAppConfigId() {
		return externalAppConfigId;
	}

	public void setExternalAppConfigId(long externalAppConfigId) {
		this.externalAppConfigId = externalAppConfigId;
	}

	public long getExternalAppId() {
		return externalAppId;
	}

	public void setExternalAppId(long externalAppId) {
		this.externalAppId = externalAppId;
	}
	
    public void setId(java.util.Hashtable h) {
        setId(((Integer)h.get(DbExternalAppConfigPeer.EXTERNALAPPID)).intValue());
    }	
    
    public boolean isLocked() {
        return false;
    }
 
}
