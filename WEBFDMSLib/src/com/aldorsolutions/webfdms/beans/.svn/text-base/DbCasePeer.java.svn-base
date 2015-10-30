package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.Persistent;
/**
 * The CasePeer handles database access for the DbCase business
 * object.  The DatabasePeer that it extends specifically prescribes
 * for it methods that allow it to create SQL statements.
 */
public class DbCasePeer extends com.aldorsolutions.webfdms.database.DatabasePeer  {
    
    /**
     * The column header for the customer ID field in the VitalStats table
     */
    static public final String CASE_ID              = "VitalsMasterKey";
    static public final String FIRST_NAME           = "DeceasedFirstName";
    static public final String LAST_NAME            = "DeceasedLastName";
    static public final String LOCALE               = "LocaleNumber";
    static public final String CHAPELLOCATION       = "ChapelLocation";
    static public final String CHAPELNUMBER         = "ChapelNumber";
    static public final String CASECODE             = "CaseCode"  ;
    static public final String CONTRACTCODE         = "ContractCode";
    static public final String VOIDEDCONTRACTCODE   = "VoidedCode";
    static public final String VOIDEDFROMCONTRACT   = "VoidedFromContract";
    static public final String VOIDEDTOCONTRACT     = "VoidedToContract";
    static public final String PRENEEDSTATUS        = "PNPreneedStatus"; // read-only: change in DbPreneed
    static public final String SALEDATE             = "SaleDateKey";
    static public final String DEATHDATE            = "DeathDateKey";
    static public final String SERVICEDATE          = "ServiceDateKey";
    static public final String ACTIVECODE				= "Active";
    static public final String VOIDEDDATE				= "VoidedDate";
    //  static public final String TOTALPAID      = "Total Paid To-Date";
    //  static public final String TOTALCHARGES     = "Total Charges";
    //  static public final String SERVICEPLACE     = "Place of service";
    /**
     * Insert the method's description here.
     * Creation date: (10/29/2001 7:17:27 PM)
     */
    public DbCasePeer() {
        super();
    }
    /**
     * getInsertSql method comment.
     */
    protected java.sql.PreparedStatement getInsertSql(
            com.aldorsolutions.webfdms.database.DatabaseTransaction t, 
            com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        
        throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCasePeer.Insert not allowed. Use DbVitalDeceased");
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
            pstmt = connection.prepareStatement("DELETE FROM vitalstats WHERE VitalsMasterKey=?");
            pstmt.setInt(1,p.getId());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalDeceasedPeer.Remove",e);
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
                "SELECT   VitalsMasterKey,"+
                "CaseCode,"+
                "ContractCode,"+
                "DeceasedFirstName,"+
                "DeceasedLastName,"+
                "VoidedCode,"+
                "VoidedFromContract,"+
                "VoidedToContract,"+
                "SaleDateKey,"+
                "DeathDateKey,"+
                "ServiceDateKey,"+
                "LocaleNumber,"+
                "ChapelLocation,"+
                "ChapelNumber,"+
                "PNPreneedStatus, "+
                "Active, "+
                "VoidedDate " +
                "from vitalstats"+
                " WHERE VitalsMasterKey = "
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
            DbCase vitals=(DbCase)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE vitalstats SET "+
                    "CaseCode=?,"+
                    "ContractCode=?,"+
                    "VoidedCode=?,"+
                    "VoidedFromContract=?,"+
                    "VoidedToContract=?,"+
                    "SaleDateKey=?," +
                    "DeathDateKey=?," +
                    "ServiceDateKey=?," +
                    "LocaleNumber=?,"+
                    "ChapelNumber=?,"+
                    "ChapelLocation=?, "+
                    "Active=?, "+
                    "VoidedDate=? "+
                    "WHERE VitalsMasterKey = ?",
                    java.sql.ResultSet.TYPE_SCROLL_SENSITIVE, 
                    java.sql.ResultSet.CONCUR_UPDATABLE);
            
            pstmt.setString(1, vitals.getCaseCode());
            pstmt.setString(2, vitals.getContractCode());
            pstmt.setString(3,vitals.getVoidedContractCode());
            pstmt.setString(4,vitals.getVoidedFromContr());
            pstmt.setString(5,vitals.getVoidedToContr());
            pstmt.setString(6,vitals.getSaleDate());
            pstmt.setString(7,vitals.getDeathDate());
            pstmt.setString(8,vitals.getServiceDate());
            pstmt.setInt(9, vitals.getLocale());
            pstmt.setInt(10, vitals.getChapelNumber());
            pstmt.setString(11,vitals.getChapelLocation());
            pstmt.setInt(12,vitals.getActiveCode());
            pstmt.setString(13,vitals.getVoidedDate());
            pstmt.setInt(14,vitals.getId());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsDeceasedPeer.Update",e);
        }
    }
}
