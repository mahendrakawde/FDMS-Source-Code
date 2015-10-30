/*
 * CemDecCaseSetDAO.java
 *
 */

package fdms.ui.caseset.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbCemDecCase;
import com.aldorsolutions.webfdms.util.DAO;

/**
 *
 * @author Guadalupe Garcia
 */
public class CemDecCaseSetDAO extends DAO {
    
    private static Logger logger = Logger.getLogger(CaseSetDAO.class.getName());
    private String dbLookup = null;

	/**
	 * Creates a new instance of CemDecCaseSetDAO
	 * 
	 * @param dbLookup
	 * @throws Exception
	 */
	public CemDecCaseSetDAO(String dbLookup) throws Exception {
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
            sql.append("SELECT COUNT(*) FROM cem_deceased ");
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
            sql.append("SELECT DecID,");
            sql.append("    DecFirstName,");
            sql.append("    DecLastName,");
            sql.append("    DecMidName,");
            sql.append("    DecTitle,");
            sql.append("    DecAptNo,");
            sql.append("    DecStreet,");
            sql.append("    DecCity,");
            sql.append("    DecState,");
            sql.append("    DecZip,");
            sql.append("    DecPhone,");
            sql.append("    DecPropertyID ");
            sql.append("FROM cem_deceased ");
            sql.append("LIMIT ?,?");           
            statement = conn.prepareStatement(sql.toString());
            int col = 0;
            
            //statement.setInt(++col, active);
                     
            statement.setInt(1, start);
            statement.setInt(2, limit);
            int intDID;
            intDID = 0;
            int intPID;
            intPID = 0;
            rs = statement.executeQuery();
            while (rs.next()) {
            	ArrayList properties = new ArrayList();
            	col = 0;
                DbCemDecCase dbCemDecCase = new DbCemDecCase();                
                intDID = rs.getInt(++col);
                Integer intDecID = new Integer(intDID);
                dbCemDecCase.setDecID(intDecID);
                
                dbCemDecCase.setFirstName(rs.getString(++col));                
                dbCemDecCase.setLastName(rs.getString(++col));                
                dbCemDecCase.setMidName(rs.getString(++col));
                dbCemDecCase.setTitle(rs.getString(++col));
                dbCemDecCase.setAptNo(rs.getString(++col));
                dbCemDecCase.setStreet(rs.getString(++col));
                dbCemDecCase.setCity(rs.getString(++col));
                dbCemDecCase.setState(rs.getString(++col));
                dbCemDecCase.setZip(rs.getString(++col));
                dbCemDecCase.setPhone(rs.getString(++col));
                
                intPID = rs.getInt(++col);
                Integer intPropID = new Integer(intPID);
                dbCemDecCase.setPropertyID(intPropID);
                
                cases.add(dbCemDecCase);
            }
            
        } catch (SQLException e) {
            logger.error("CemDecCaseSetDao SQL Exception in getCaseList() : ", e);
        } catch (Exception e) {
            logger.error("CemDecCaseSetDao Exception in getCaseList() : ", e);
        } finally {
            closeConnection();
        }
        
        return cases;
    }
}

