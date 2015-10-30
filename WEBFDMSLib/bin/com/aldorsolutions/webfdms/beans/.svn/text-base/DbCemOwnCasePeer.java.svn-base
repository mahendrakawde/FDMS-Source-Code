package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.Persistent;
/**
 * The CasePeer handles database access for the DbCase business
 * object.  The DatabasePeer that it extends specifically prescribes
 * for it methods that allow it to create SQL statements.
 * Modified by :QPRIME (SJD) for SOW# F030602W - WebFDMS database Upgrade
 *               Orginal code is commented out and placed at the bottom of this class when extensive changes were requried
                 Alterations made to the getRemoveSQL, getUpdateSQL and getRestoreSQL methods.
 */

public class DbCemOwnCasePeer extends com.aldorsolutions.webfdms.database.DatabasePeer  {

    /***
     * The column header for the customer ID field in the vitalstats table
     */
    		
	static public final String OWNERID              = "OwnerID";
    static public final String OWNFIRSTNAME         = "OwnFirstName";
    static public final String OWNLASTNAME          = "OwnLastName";
    static public final String OWNMIDNAME           = "OwnMidName";
    static public final String OWNTITLE             = "OwnTitle";
    static public final String OWNAPTNO             = "OwnAptNo";
    static public final String OWNSTREET            = "OwnStreet"  ;
    static public final String OWNCITY              = "OwnCity"  ;
    static public final String OWNSTATE             = "OwnState";
    static public final String OWNZIP               = "OwnZip";
    static public final String OWNPHONE             = "OwnPhone";
    
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 7:17:27 PM)
     */
    public DbCemOwnCasePeer() {
        super();
    }
    /**
     * getInsertSql method comment.
     */
    protected java.sql.PreparedStatement getInsertSql(
            com.aldorsolutions.webfdms.database.DatabaseTransaction t,
            com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

        throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCemCasePeer.Insert not allowed. Use DbVitalDeceased");
    }
    /**
     * getRemoveSql method comment.
     */
    protected java.sql.PreparedStatement getRemoveSql(
            com.aldorsolutions.webfdms.database.DatabaseTransaction t,
            com.aldorsolutions.webfdms.database.Persistent p)
            throws com.aldorsolutions.webfdms.database.PersistenceException {

            java.sql.PreparedStatement pstmt=null;
            java.sql.Connection connection = null;
        try {
            connection = t.getConnection();
            pstmt = connection.prepareStatement(
             "DELETE OWNERS "+
    "FROM   owners "+
    "WHERE  OWNERS.OwnerId = ?;"    
	      );	
	        pstmt.setInt(1,p.getId());			
			    return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCemCasePeer.Remove",e);
        }
    }

    /**
     * Provides the SQL used to restore a customer from the database
     * @param p the Customer being restored (only the ID field is filled in)
     * @return the SQL used to restore that customer
     */
    public String getRestoreSql(Persistent p) {
        // SELECT * from t_customer WHERE cust_id = 1
        return
                "SELECT   OwnerID,"+
                "OwnFirstName,"+
                "OwnLastName,"+
                "OwnMidName,"+
                "OwnTitle,"+
                "OwnAptNo,"+
                "OwnStreet,"+
                "OwnCity,"+
                "OwnState,"+
                "OwnZip,"+
                "OwnPhone "+
                "from owners"+
                " WHERE OwnerID = "
                + p.getId();
    }

    /**
     * getUpdateSql method comment.
     */
    protected java.sql.PreparedStatement getUpdateSql(
            com.aldorsolutions.webfdms.database.DatabaseTransaction t,
            com.aldorsolutions.webfdms.database.Persistent p)
            throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.Connection connection = null;

        //AppLog.trace("DbCasePeer.getUpdateSql");
        try {
            DbCemOwnCase owners=(DbCemOwnCase)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
            "UPDATE owners SET "+
               "OwnFirstName=?,"+
               "OwnLastName=?,"+
               "OwnMidName=?,"+
               "OwnTitle=?,"+
               "OwnAptNo=?,"+
               "OwnStreet=?," +
               "OwnCity=?," +
               "OwnState=?," +
               "OwnZip=?,"+
               "OwnPhone=? "+
            "WHERE OwnerID = ?",
            
            java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,
            java.sql.ResultSet.CONCUR_UPDATABLE);
            // vs_master
            pstmt.setString(1,owners.getOwnFirstName());
            pstmt.setString(2,owners.getOwnLastName());
            pstmt.setString(3,owners.getOwnMidName());
            pstmt.setString(4,owners.getOwnTitle());
            pstmt.setString(5,owners.getOwnAptNo());
            pstmt.setString(6,owners.getOwnStreet());
            pstmt.setString(7,owners.getOwnCity());
            pstmt.setString(8,owners.getOwnState());
            pstmt.setString(9,owners.getOwnZip());
            pstmt.setString(10,owners.getOwnPhone());
            pstmt.setInt(11,owners.getId());

            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCemCasePeer.Update",e);
        }
    }
}


