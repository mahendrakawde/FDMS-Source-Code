package com.aldorsolutions.webfdms.beans;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.util.DAO;

public class ChargeDAO extends DAO {

    static public final String VITALSMASTERKEY    	= "VitalsMasterKey";
    static public final String CHARGETYPE 	= "ChargeType";
    static public final String DISCRIPTION 	= "Description";
    static public final String AMOUNT 	= "Amount";
    static public final String ARACCT		= "ARacct";
    static public final String GLACCT 		= "GLacct";
    static public final String TAXEXEMPTAMT	= "TaxExemptAmount";
    static public final String TAXCODE		= "TaxCode";
    static public final String DATEMODIFIED     = "DateModified";
    static public final String TIMEMODIFIED     = "TimeModified";
    static public final String SEQUENCE     = "Sequence";
    static public final String SPFCODE		= "SPFcode";
    static public final String INVENTORYITEMNAME	= "InventoryItemName";
    static public final String INVMASTERKEY	= "InvMasterKey";
    static public final String CATEGORYCODE		= "CategoryCode";
    static public final String ORIGINALPRICE     = "OriginalPrice";
    static public final String RECORDVERSION	= "RecordVersion";
    static public final String AUTOINDEX	= "AutoIndex";
    static public final String PRICELISTID	= "PriceListId";
    static public final String INVONHANDID	= "InvOnHandID";
    static public final String FROMPACKAGE	= "fromPackage";

    /** Creates a new instance of ApMasterDAO */
    public ChargeDAO(DbUserSession user) {
    	super(user);
    }

    /** Creates a new instance of ApMasterDAO */
    public ChargeDAO ( long companyID, long userId ) {
    	super(companyID, userId);
    }
    
    private Logger logger = Logger.getLogger(TransactionhistoryDAO.class.getName());
    
    
    public int getTotalCharge(int vitalsID){
    	int total = 0;
    	
    	try {
    		 StringBuffer sql = new StringBuffer();

         sql.append("select sum("+AMOUNT +") from charges where "+VITALSMASTERKEY+"= ?");
              
           
           makeConnection(this.dbLookup);
           statement = conn.prepareStatement(sql.toString());
           statement.setInt(1, vitalsID);
           
           rs = statement.executeQuery();
           if (rs.next()) {
           		int col = 1;
           		total = rs.getInt(col++);
           }	
    		
    	} catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
    	return total;
    }
    
    public int getWrongCode(int vitalsID){
    	int total = 0;
    	
    	try {
    		 StringBuffer sql = new StringBuffer();

         sql.append("select count("+AUTOINDEX +") from charges where "+VITALSMASTERKEY+"= ? and ChargeType in (0,9000)");
              
           
           makeConnection(this.dbLookup);
           statement = conn.prepareStatement(sql.toString());
           statement.setInt(1, vitalsID);
           
           rs = statement.executeQuery();
           if (rs.next()) {
           		int col = 1;
           		total = rs.getInt(col++);
           }	
    		
    	} catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
    	return total;
    }
	
	
}
