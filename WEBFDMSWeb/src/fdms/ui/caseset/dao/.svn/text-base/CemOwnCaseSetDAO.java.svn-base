/*
 * CemCaseSetDAO.java
 *
 */

package fdms.ui.caseset.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbCemOwnCase;
import com.aldorsolutions.webfdms.util.CemCasePrptySet;
import com.aldorsolutions.webfdms.util.DAO;

/**
 *
 * @author Guadalupe Garcia
 */
public class CemOwnCaseSetDAO extends DAO  {
    
    private static Logger logger = Logger.getLogger(CaseSetDAO.class.getName());
    private String dbLookup = null;

	/**
	 * Creates a new instance of CemOwnCaseSetDAO
	 * 
	 * @param dbLookup
	 * @throws Exception
	 */
	public CemOwnCaseSetDAO(String dbLookup) throws Exception {
		this.dbLookup = dbLookup;
	}
    
    /**
     * Finds number of records for given location
     * @param locationId
     * @param sortBy
     * @param searchParam
     * @return count
     */
    public int getCaseCount(
            int locationId,
            String [] locationIds,
            String sortBy,
            String searchParam,
            int active,
            String caseListDisplayPreneed,
            String caseListDisplayVoided) {
        
        int count = 0;
        
        try {
        	makeConnection(dbLookup);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT COUNT(*) FROM cem_owners ");
            statement = conn.prepareStatement(sql.toString());
                                        
            rs = statement.executeQuery();
            
            if (rs.next()) count = rs.getInt(1);
        } catch (SQLException e) {
            logger.error("SQL Exception in getCaseCount() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCaseCount() : ", e);
        } finally {
            closeConnection();
        }
        
        return count;
    }
    
    /**
     * Gets list of owners and properties for main cemetery screen.
     * @return arraylist
     */
    public ArrayList getCaseList(
            int locationId, 
            String [] locationIds,
            String sortBy, 
            int start, 
            int limit,
            String searchParam,
            int active,
            String caseListDisplayPreneed,
            String caseListDisplayVoided) {
        
        String sortOrder = "DESC";
        ArrayList cases = new ArrayList();
        
        if ( sortBy.equals("DeceasedFirstName") || sortBy.equals("DeceasedLastName") || 
        	 sortBy.equals("NextKinFirstName")  || sortBy.equals("NextKinLastName") ) {
        	sortOrder = "ASC";
        }
        
        try {            
        	makeConnection(dbLookup);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT OwnerID,");
            sql.append("    OwnFirstName,");
            sql.append("    OwnLastName,");
            sql.append("    OwnMidName,");
            sql.append("    OwnTitle,");
            sql.append("    OwnAptNo,");
            sql.append("    OwnStreet,");
            sql.append("    OwnCity,");
            sql.append("    OwnState,");
            sql.append("    OwnZip,");
            sql.append("    OwnPhone ");
            sql.append("FROM cem_owners ");
            sql.append("LIMIT ?,?");           
            statement = conn.prepareStatement(sql.toString());
            int col = 0;
            
            //statement.setInt(++col, active);
            
            statement.setInt(1, start);
            statement.setInt(2, limit);
            int intOID;
            intOID = 0;
            rs = statement.executeQuery();
            
            CemOwnCaseSetDAO caseSetDAO = new CemOwnCaseSetDAO (this.dbLookup);
            
            while (rs.next()) {
            	ArrayList properties = new ArrayList();
            	col = 0;
                DbCemOwnCase dbCemOwnCase = new DbCemOwnCase();                
                intOID = rs.getInt(++col);
                Integer intOwnID = new Integer(intOID);
                dbCemOwnCase.setOwnerID(intOwnID);
                
                dbCemOwnCase.setOwnFirstName(rs.getString(++col));                
                dbCemOwnCase.setOwnLastName(rs.getString(++col));                
                dbCemOwnCase.setOwnMidName(rs.getString(++col));
                dbCemOwnCase.setOwnTitle(rs.getString(++col));
                dbCemOwnCase.setOwnAptNo(rs.getString(++col));
                dbCemOwnCase.setOwnStreet(rs.getString(++col));
                dbCemOwnCase.setOwnCity(rs.getString(++col));
                dbCemOwnCase.setOwnState(rs.getString(++col));
                dbCemOwnCase.setOwnZip(rs.getString(++col));
                dbCemOwnCase.setOwnPhone(rs.getString(++col));
                
                properties = caseSetDAO.getPropertiesList(intOwnID);
                dbCemOwnCase.setOwnProperties(properties);
                cases.add(dbCemOwnCase);
            }
            
        } catch (SQLException e) {
            logger.error("CemOwnCaseSetDao SQL Exception in getCaseList() : ", e);
        } catch (Exception e) {
            logger.error("CemOwnCaseSetDao Exception in getCaseList() : ", e);
        } finally {
            closeConnection();
        }
        
        return cases;
    }
    /**
    * Gets list of properties for an owner.
    * @return arraylist
    */ 
    public ArrayList getPropertiesList(Integer intOwnID){
    	ArrayList properties = new ArrayList();
    	
    	PreparedStatement statement2 = null;
    	ResultSet rs2 = null;
    	try {            
    		makeConnection(dbLookup);
    		
    		StringBuffer sql = new StringBuffer();
            sql.append("SELECT PropID,");
            sql.append("    PropType,");
            sql.append("    PropOccStat ");
            
            sql.append("FROM cem_properties WHERE PropOwnerID1 = ? " );
                       
            statement2 = conn.prepareStatement(sql.toString());
            int col = 0;
            int intOID = intOwnID.intValue();
            statement2.setInt(1, intOID);        
            rs2 = statement2.executeQuery();
            while (rs2.next()) {
            	int intPID;
            	int intOcc;
            	String strPropType;
            	intPID = 0;
            	intOcc = 0;
            	col = 0;
                CemCasePrptySet cemCasePrptySet = new CemCasePrptySet();                
                intPID = rs2.getInt(++col);
                Integer intPropID = new Integer(intPID);
                cemCasePrptySet.setPropID(intPropID);                
                strPropType = rs2.getString(++col);
                cemCasePrptySet.setPropType(strPropType);                
                intOcc = rs2.getInt(++col);
                Integer intOccup = new Integer(intOcc);
                cemCasePrptySet.setOccupancy(intOccup);
                                
                properties.add(cemCasePrptySet);                               
                
            }
            
    	} catch (SQLException e) {
            logger.error("CemCaseSetDao SQL Exception in getPropertiesList() : ", e);
        } catch (Exception e) {
            logger.error("CemCaseSetDao Exception in getPropertiesList() : ", e);
        } 
        finally {
        	try {
        		if ( rs2 != null ) {
            		rs2.close();
            		rs2 = null;
            	}
        	} catch ( Exception e ) {
        		logger.error("CemCaseSetDao Exception in getPropertiesList() : ", e);
        	}
        	
        	try {
        		if ( statement2 != null ) {
            		statement2.close();
            		statement2 = null;
            	}
        	} catch ( Exception e ) {
        		logger.error("CemCaseSetDao Exception in getPropertiesList() : ", e);
        	}
        	
        	closeConnection();
        	
        }
        
        
        return properties; 	
    }
}

