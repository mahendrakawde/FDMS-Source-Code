/*
 * CaseSetDAO.java
 *
 * Created on April 28, 2005, 10:17 AM
 */

package fdms.ui.caseset.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.util.DAO;


/**
 *
 * @author Guadalupe Garcia
 */
public class CaseSetDAO extends DAO {
    
    private static Logger logger = Logger.getLogger(CaseSetDAO.class.getName());
    
    private String dbLookup = null;
    
    /**
     * @param dbLookup
     * @throws Exception
     */
    public CaseSetDAO( String dbLookup ) throws Exception {
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
            String caseListDisplayVoided,
            boolean preneedOnly) {
        
        int count = 0;
        
        try {
        	makeConnection(dbLookup);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT COUNT(*) FROM vitalstats ");
            
            if ("AddnlServiceDate".equals(sortBy)) {
            	sql.append("LEFT OUTER JOIN servicedata ");
            	sql.append("ON vitalstats.VitalsMasterKey = servicedata.VitalsMasterKey ");
            }
            	
            sql.append("WHERE (vitalstats.Active != ?) "); // active cases will be set as 1, but test against not active incase of null values
            
            if (locationId > 0) {
            	sql.append("AND (vitalstats.ChapelNumber = ?) ");
            }
            else {
            	//sql.append("AND (vitalstats.LocaleNumber = ?) ");
            	
            	if ( locationIds.length == 1 ) {
            		sql.append("AND (vitalstats.ChapelNumber = " + locationIds[0] + " ) ");
            	}
            	else if ( locationIds.length > 1 ) {
            		
            		sql.append("AND (vitalstats.ChapelNumber in (");
            		
            		for ( int i = 0; i < locationIds.length; i++ ) {
            			sql.append( locationIds[i] );
            			
            			if ( (i+1) < locationIds.length ){
            				sql.append (", ");
            			}
            			
                	}
            		
            		sql.append(") ) ");
            	} 
            	
            	
            }
            
            if (!preneedOnly) {            
	            if (("N".equals(caseListDisplayPreneed)) && ("N".equals(caseListDisplayVoided))) {
	            	sql.append("AND ((vitalstats.PNPreneedStatus != 'A') OR (vitalstats.PNPreneedStatus IS NULL)) ");
	            	sql.append("AND ((vitalstats.VoidedCode != 'V') OR (vitalstats.VoidedCode IS NULL)) ");
	            } else {
		            if ("N".equals(caseListDisplayPreneed)) {
		            	sql.append("AND ((vitalstats.PNPreneedStatus != 'A') OR (vitalstats.PNPreneedStatus IS NULL)) ");
		            	if ("N".equals(caseListDisplayVoided))  
		            		sql.append("OR ");
		            } else {
		            	if ("N".equals(caseListDisplayVoided))  
		            		sql.append("AND ");
		            }
		            
		            if ("N".equals(caseListDisplayVoided))            	
		            	sql.append("((vitalstats.VoidedCode != 'V') OR (vitalstats.VoidedCode IS NULL)) ");
	            }
            } else {
            	sql.append("AND (vitalstats.PNPreneedStatus = 'A') ");
            	sql.append("AND ((vitalstats.VoidedCode != 'V') OR (vitalstats.VoidedCode IS NULL)) ");
            }
            
            if (searchParam != null) {
            	if (!"AddnlServiceDate".equals(sortBy)){
            		if (!"DeceasedLastFirstName".equals(sortBy)) {
            			sql.append(" AND (vitalstats." + sortBy + " LIKE ?)");
            		}
            		else {
            			sql.append(" AND (vitalstats.DeceasedLastName LIKE ?) AND (vitalstats.DeceasedFirstName LIKE ?) ");
            		}
            	}
            	else{
            		sql.append(" AND (servicedata." + sortBy + " LIKE ?)");
            	}
            }
                
            statement = conn.prepareStatement(sql.toString());

            int col = 0;
            
            statement.setInt(++col, active);
            
            if (locationId > 0) {
            	statement.setInt(++col, locationId);
            }
            else {
//            	statement.setLong(++col, companyID);
            }
            
            if (searchParam != null){
            	if (!"DeceasedLastFirstName".equals(sortBy)) {
            		statement.setString(++col, searchParam + "%");
            	}
            	else {
            		String lastFirstName = searchParam;
					String [] temp = null;
					temp = lastFirstName.split(",");
					String LastName,FirstName;
					if (temp.length == 2) {
						LastName = temp[0].trim();
						FirstName = temp[1].trim();
					}
					else {
						LastName = temp[0].trim();
						FirstName = "";
					}

					
					statement.setString(++col, LastName +"");
					statement.setString(++col, FirstName + "%");
            	}
            }
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
     * 
     * @param locationId
     * @param localeId
     * @param sortBy
     * @param start
     * @param limit
     * @param searchParam
     * @param active
     * @param caseListDisplayDeceased
     * @param caseListDisplayPreneed
     * @param caseListDisplayVoided
     * @return
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
            String caseListDisplayVoided,
            boolean preneedOnly) {
        
        String sortOrder = "DESC";
        ArrayList cases = new ArrayList();
        
        if ( sortBy.equals("DeceasedFirstName") || sortBy.equals("DeceasedLastName") || 
        	 sortBy.equals("NextKinFirstName")  || sortBy.equals("NextKinLastName")  || sortBy.equals("DeceasedLastFirstName")) {
        	sortOrder = "ASC";
        }
        
        try {            
        	makeConnection(dbLookup);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT vitalstats.VitalsMasterKey,");
            sql.append("    vitalstats.CaseCode,");
            sql.append("    vitalstats.ContractCode,");
            sql.append("    vitalstats.DeceasedFirstName,");
            sql.append("    vitalstats.DeceasedLastName,");
            sql.append("    vitalstats.VoidedCode,");
            sql.append("    vitalstats.VoidedFromContract,");
            sql.append("    vitalstats.VoidedToContract,");
            sql.append("    vitalstats.SaleDateKey,");
            sql.append("    vitalstats.DeathDateKey,");
            sql.append("    vitalstats.ServiceDateKey,");
            sql.append("    vitalstats.LocaleNumber,");
            sql.append("    vitalstats.ChapelLocation,");
            sql.append("    vitalstats.PNPreneedStatus, ");
            sql.append("	vitalstats.ChapelNumber, ");
            sql.append("	servicedata.AddnlServiceDate, ");
            sql.append("	vitalstats.DispositionMethod, ");
            sql.append("	vitalstats.DispositionPlace, ");
            sql.append("	vitalstats.DispositionDate, ");
            sql.append("	vitalstats.PreneedArrngDate, ");
            sql.append("	vitalstats.DirectorName ");
            sql.append("FROM vitalstats ");
            sql.append("LEFT OUTER JOIN servicedata ");
            sql.append("ON vitalstats.VitalsMasterKey = servicedata.VitalsMasterKey ");               
            sql.append("WHERE (vitalstats.Active != ?) ");
            
            if (locationId > 0) {
            	sql.append("AND (vitalstats.ChapelNumber = ?) ");
            }
            else {
//            	sql.append("AND (vitalstats.LocaleNumber = ?) ");
            	
//            	sql.append("AND (vitalstats.localeNumber in ( select LocaleID from locales where companyID = ?) ) ");

            	if ( locationIds.length == 1 ) {
            		sql.append("AND (vitalstats.ChapelNumber = " + locationIds[0] + " ) ");
            	}
            	else if ( locationIds.length > 1 ) {
            		
            		sql.append("AND (vitalstats.ChapelNumber in (");
            		
            		for ( int i = 0; i < locationIds.length; i++ ) {
            			sql.append( locationIds[i] );
            			
            			if ( (i+1) < locationIds.length ){
            				sql.append (", ");
            			}
            			
                	}
            		
            		sql.append(") ) ");
            	}
            }
            
            if (!preneedOnly) {
	            if (("N".equals(caseListDisplayPreneed)) && ("N".equals(caseListDisplayVoided))) {
	            	sql.append("AND ((vitalstats.PNPreneedStatus != 'A') OR (vitalstats.PNPreneedStatus IS NULL)) ");
	            	sql.append("AND ((vitalstats.VoidedCode != 'V') OR (vitalstats.VoidedCode IS NULL)) ");
	            } 
	            else {
		            if ("N".equals(caseListDisplayPreneed)) {
		            	sql.append("AND ((vitalstats.PNPreneedStatus != 'A') OR (vitalstats.PNPreneedStatus IS NULL)) ");
		            	
		            	if ("N".equals(caseListDisplayVoided)) {  
		            		sql.append("OR ");
		            	}
		            	
		            } 
		            else {
		            	if ("N".equals(caseListDisplayVoided)) {  
		            		sql.append("AND ");
		            	}
		            }
		            
		            if ("N".equals(caseListDisplayVoided)) {           	
		            	sql.append("((vitalstats.VoidedCode != 'V') OR (vitalstats.VoidedCode IS NULL)) ");
		            }
	            }
            } else {
            	sql.append("AND (vitalstats.PNPreneedStatus = 'A') ");
            	sql.append("AND ((vitalstats.VoidedCode != 'V') OR (vitalstats.VoidedCode IS NULL)) ");
            }
            
            if (searchParam != null) {
            	if (!"AddnlServiceDate".equals(sortBy)) {
            		if (!"DeceasedLastFirstName".equals(sortBy)) {
            			sql.append(" AND (vitalstats." + sortBy + " LIKE ?)");
            		}
            		else {
            			sql.append(" AND (vitalstats.DeceasedLastName LIKE ?) AND (vitalstats.DeceasedFirstName LIKE ?) ");
            		}			
            	}
            	else {
            		sql.append(" AND (servicedata." + sortBy + " LIKE ?)");
            	}
            }           
            
            if (!"AddnlServiceDate".equals(sortBy)) {
            	if (!"DeceasedLastFirstName".equals(sortBy)) {
            		sql.append("ORDER BY vitalstats." + sortBy + " " + sortOrder + " ");
            	}
        		else {
        			sql.append(" ORDER BY vitalstats.DeceasedLastName, vitalstats.DeceasedFirstName ");
        		}		
            } 
            else {
            	sql.append("ORDER BY servicedata." + sortBy + " " + sortOrder + " ");
            }
            
            sql.append("LIMIT ?,?");
            
            statement = conn.prepareStatement(sql.toString());
            int col = 0;
            
            statement.setInt(++col, active);
            
            if (locationId > 0) {
            	statement.setInt(++col, locationId);
            }
            else {
//            	statement.setLong(++col, companyID);            
            }
            
            if (searchParam != null) {
                if (!"DeceasedLastFirstName".equals(sortBy)) {
            		statement.setString(++col, searchParam + "%");
            	}
            	else {
            		String lastFirstName = searchParam;
					String [] temp = null;
					temp = lastFirstName.split(",");
					String LastName,FirstName;
					if (temp.length == 2) {
						LastName = temp[0].trim();
						FirstName = temp[1].trim();
					}
					else {
						LastName = temp[0].trim();
						FirstName = "";
					}
					statement.setString(++col, LastName +"");
					statement.setString(++col, FirstName + "%");
            	}  
            }
            
            statement.setInt(++col, start);
            statement.setInt(++col, limit);
            
            rs = statement.executeQuery();
            while (rs.next()) {
                col = 0;
                DbCase dbCase = new DbCase();                
                dbCase.setVitalsID(new Integer(rs.getInt(++col)));
                dbCase.setCaseCode(rs.getString(++col));                
                dbCase.setContractCode(rs.getString(++col));                
                dbCase.setFirstName(rs.getString(++col));
                dbCase.setLastName(rs.getString(++col));
                dbCase.setVoidedContractCode(rs.getString(++col));
                dbCase.setVoidedFromContr(rs.getString(++col));
                dbCase.setVoidedToContr(rs.getString(++col));
                dbCase.setSaleDate(rs.getString(++col));
                dbCase.setDeathDate(rs.getString(++col));
                dbCase.setServiceDate(rs.getString(++col));
                dbCase.setLocale(rs.getInt(++col));
                dbCase.setChapelLocation(rs.getString(++col));
                dbCase.setPreneedStatus(rs.getString(++col));
                dbCase.setChapelNumber(rs.getInt(++col));
                dbCase.setAdditionalServiceDate(rs.getString(++col));
                dbCase.setDispositionMethod(rs.getString(++col));
                dbCase.setDispositionPlace(rs.getString(++col));
                dbCase.setDispositionDate(rs.getString(++col));
                dbCase.setArrangeDate(rs.getString(++col));
                dbCase.setDirector(rs.getString(++col));
                
                cases.add(dbCase);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getCaseList() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCaseList() : ", e);
        } finally {
            closeConnection();
        }
        
        return cases;
    }
    
}
