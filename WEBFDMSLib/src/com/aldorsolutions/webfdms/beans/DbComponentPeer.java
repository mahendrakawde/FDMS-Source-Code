package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbComponentPeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author:
 */
public class DbComponentPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {
    
    static public final String IDENTITY    	= "PmntComponentID";
    static public final String CASE_ID	 	= "VitalsID";
    static public final String CODE			= "Code";
    static public final String DESCRIPTION 	= "Description";
    static public final String SALEAMOUNT 	= "SaleAmount";
    static public final String PAIDAMOUNT 	= "PaidAmount";
    static public final String SOURCE 		= "Source";
    static public final String TYPE			= "Type";
    /**
     * getInsertSql method comment.
     */
    protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.Connection connection = null;
        java.sql.PreparedStatement pstmt=null;
        try {
            DbComponent rec=(DbComponent)p;
            connection = t.getConnection();
            pstmt = connection.prepareStatement(
                    "INSERT INTO pmntcomponents ("+
                    IDENTITY +","+
                    CASE_ID +","+
                    CODE +","+
                    DESCRIPTION +","+
                    SALEAMOUNT +","+
                    PAIDAMOUNT +","+
                    SOURCE +","+
                    TYPE + " " +
                    ") VALUES (0,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt   (1, rec.getVitalsID());
            pstmt.setString(2, rec.getCode());
            pstmt.setString(3, rec.getDescription());
            pstmt.setInt   (4, rec.getSaleAmt());
            pstmt.setInt   (5, rec.getPaidAmt());
            pstmt.setString(6, rec.getSource());
            pstmt.setString(7, rec.getType());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbComponentPeer.Insert",e);
        }
    }
    /**
     * getRemoveSql method comment.
     */
    protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.PreparedStatement pstmt=null;
        java.sql.Connection connection = null;
        try {
            connection = t.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM pmntcomponents WHERE PmntComponentID=?");
            pstmt.setInt(1,p.getId());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbComponentPeer.Remove",e);
        }
    }
    /**
     * getRestoreSql method comment.
     */
    protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
        return
                "SELECT "
                + CASE_ID
                +"," + IDENTITY
                +"," + CODE
                +"," + DESCRIPTION
                +"," + SALEAMOUNT
                +"," + PAIDAMOUNT
                +"," + SOURCE
                +"," + TYPE
                +" from pmntcomponents WHERE PmntComponentID = "
                + p.getId();
    }
    /**
     * getUpdateSql method comment.
     */
    protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        //AppLog.trace("DbComponentPeer.getUpdateSql id:"+p.getId());
        java.sql.Connection connection = null;
        try {
            DbComponent rec=(DbComponent)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE pmntcomponents SET "+
                    CASE_ID +"=?,"+
                    CODE +"=?,"+
                    DESCRIPTION +"=?,"+
                    SALEAMOUNT +"=?,"+
                    PAIDAMOUNT +"=?,"+
                    SOURCE +"=?,"+
                    TYPE +"=? "+
                    "WHERE "+IDENTITY+" = ?");
            
            pstmt.setInt   (1, rec.getVitalsID());
            pstmt.setString(2, rec.getCode());
            pstmt.setString(3, rec.getDescription());
            pstmt.setInt   (4, rec.getSaleAmt());
            pstmt.setInt   (5, rec.getPaidAmt());
            pstmt.setString(6, rec.getSource());
            pstmt.setString(7, rec.getType());
            pstmt.setInt   (8,rec.getId());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbComponentPeer.Update:"+e.getMessage(),e);
        }
    }
}
