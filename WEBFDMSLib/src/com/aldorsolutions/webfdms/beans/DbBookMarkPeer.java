package com.aldorsolutions.webfdms.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;

public class DbBookMarkPeer extends DatabasePeer {
	
	
	
	static public final String NAME				= "Name";
	static public final String DESCRIPTION    	= "Description";
	static public final String ID 				= "id";
	static public final String LINK 			= "Link";
	static public final String ORDER 			= "Order";

	

	protected PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		Connection connection = null;
		PreparedStatement pstmt=null;
		try {
			DbBookMark myrow=(DbBookMark)p;
			connection = t.getConnection();
			// Check for special case where sequence number is -9999
			// and replace with next highest sequence number between 1 and 999.
			// get a list of survivors for this case.
			short highest=2;
			if (myrow.getOrder()== -9999){
				//DbSurvivor[] survs = FdmsDb.getInstance().getSurvivorsForID(t,myrow.getLSurvivorMainKey());
				//for (int i=0; i< survs.length; i++){
				//	if (survs[i].getISeqKey()> highest && survs[i].getISeqKey()< 1000)
				//		highest=survs[i].getISeqKey();
				//}
				PreparedStatement stmtmax = connection.prepareStatement("SELECT max(`Order`) FROM bookmarks");
				ResultSet rs = stmtmax.executeQuery();
				if (rs.next()){
					if (rs.getShort(1) > highest)
						highest=rs.getShort(1);
				}
				myrow.setOrder((short)(highest+1));
				stmtmax.close();
			}
			
			pstmt = connection.prepareStatement(
			"INSERT INTO bookmarks ("+
						NAME +","+
						DESCRIPTION +","+
						LINK +",`"+
						ORDER +
						"`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					
				pstmt.setString(1, myrow.getName());		
				pstmt.setString(2, myrow.getDescription());		
				pstmt.setString(3, myrow.getLink());	
				pstmt.setShort(4, myrow.getOrder());		
				return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbSurvivorPeer.Insert",e);
		}

	}

	protected PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		PreparedStatement pstmt=null;
		Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM bookmarks WHERE id=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbSurvivorPeer.Remove",e);
		}
	}

	protected String getRestoreSql(Persistent p) {
		return "SELECT 	* from bookmarks WHERE id = "
		+ p.getId();
	}
	

	protected PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		Connection connection = null;
		try {
			DbBookMark myrow=(DbBookMark)p;
			connection = t.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE bookmarks SET "+
						NAME +"=?,"+
						DESCRIPTION +"=?,"+
						LINK +"=?,`"+
						ORDER +"`=? "+
						"WHERE "+ID+"=?"   );		
			pstmt.setString(1, myrow.getName());
			pstmt.setString(2, myrow.getDescription());	
			pstmt.setString(3, myrow.getLink());
			pstmt.setShort(4, myrow.getOrder());	
			pstmt.setInt   (5, myrow.getId());	
			return pstmt;
			}
			catch (SQLException e){
				throw new com.aldorsolutions.webfdms.database.PersistenceException("DbSurvivorPeer.Update:"+e.getMessage(),e);
			}
	}

}
