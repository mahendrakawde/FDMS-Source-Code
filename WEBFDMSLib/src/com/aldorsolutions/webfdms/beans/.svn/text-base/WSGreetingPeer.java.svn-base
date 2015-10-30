package com.aldorsolutions.webfdms.beans;

import java.sql.PreparedStatement;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;

public class WSGreetingPeer extends DatabasePeer {
	
	public final String INDEXID = "IndexId";
	public final String DATE_TIME = "DateTime";
	public final String GREETING = "Greeting";

	@Override
	protected PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p)
			throws PersistenceException {
		
		try{
			
			PreparedStatement pstm = createPreparedStatement("INSERT INTO webservicegreeting " + 
										" (DateTime, Greeting) VALUES( ?, ?);", t);
			WSGreeting greeting = (WSGreeting)p;
			pstm.setTimestamp(1, greeting.getDateTime());
			pstm.setString(2, greeting.getGreeting());
			return pstm;
			
		}catch(Exception e){
			throw new PersistenceException("WSGreetingPeer.Insert",e);
		}
	}

	@Override
	protected PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p)
			throws PersistenceException {
		throw new PersistenceException("WSGreetingPeer.Remove not allowed.");
	}

	@Override
	protected String getRestoreSql(Persistent p) {
		return "select IndexId, DateTime, Greeting from webservicegreeting where IndexId = " + p.getId();
	}

	@Override
	protected PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p)
			throws PersistenceException {
		
		try{
			
			PreparedStatement pstm = createPreparedStatement("update webservicegreeting set DateTime = ? , Greeting = ? where IndexId = ?", t);
			WSGreeting greeting = (WSGreeting)p;
			pstm.setTimestamp(1, greeting.getDateTime());
			pstm.setString(2, greeting.getGreeting());
			pstm.setInt(3, p.getId());
			return pstm;
			
		}catch(Exception e){
			throw new PersistenceException("WSGreetingPeerUpdate",e);
		}
	}

}
