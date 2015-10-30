/*
 * CaseSetDAO.java
 *
 * Created on April 28, 2005, 10:17 AM
 */

package com.aldorsolutions.webfdms.visitation.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatTime;



/**
 *
 * @author Kier Heyl
 */
public class VisitationDAO extends DAO {
    
    private static Logger logger = Logger.getLogger(VisitationDAO.class.getName());
    private String jndiLookup = null;
    private Date currentDate = new Date();
    
    /** Creates a new instance of CaseSetDAO 
     * @param dbUrl
     * @param dbUsername
     * @param dbPassword
     * @throws Exception     
     */
    public VisitationDAO(DbUserSession user) throws Exception {
    	jndiLookup = user.getDbLookup();
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
    public DbVisitations getVisitationsByPK(int id) {
        
        try {            
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT VitalsMasterKey,");
			sql.append("    Place,");
            sql.append("    Room,");
            sql.append("    Address,");
            sql.append("    Address2,");
            sql.append("    City,");
            sql.append("    State,");
            sql.append("    Zip,");
            sql.append("    StartTime,");
            sql.append("    EndTime,");
            sql.append("    EventDate,");
            sql.append("    Private,");
            sql.append("	Notes ");
            sql.append("FROM visitations ");              
            sql.append("WHERE (id = " + id +") AND (Deleted = 0) ");
            
            makeConnection(jndiLookup);
            statement = conn.prepareStatement(sql.toString());
            int col = 0;
            
            rs = statement.executeQuery();
            if (rs.next()) {
                col = 0;
                DbVisitations dbVisitation = new DbVisitations();                
                dbVisitation.setId(id);
                dbVisitation.setMainKey(rs.getInt(++col));
                dbVisitation.setPlace(rs.getString(++col));
                dbVisitation.setRoom(rs.getString(++col));      
                dbVisitation.setAddress(rs.getString(++col));           
                dbVisitation.setAddress2(rs.getString(++col));              
                dbVisitation.setCity(rs.getString(++col));          
                dbVisitation.setState(rs.getString(++col));           
                dbVisitation.setZip(rs.getString(++col)); 
                dbVisitation.setStartTime(FormatTime.convertTimeToAMPM(rs.getString(++col)));                
                dbVisitation.setEndTime(FormatTime.convertTimeToAMPM(rs.getString(++col)));
                Date date = rs.getDate(++col);
                dbVisitation.setDate(FormatDate.convertDateToMM_DD_YYYY(date));        
                // convert privacy status from 1/0 to true/false boolean
                dbVisitation.setPrivateVisitation(rs.getInt(++col)==1?"Yes":"No");
                dbVisitation.setNotes(rs.getString(++col));
                dbVisitation.setStatus('U'); // status is update by default
                
                return ( dbVisitation );
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getVisitationsForID() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getVisitationsForID() : ", e);
        } finally {
            closeConnection();
        }
        
        return (null);
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
    public ArrayList <DbVisitations> getVisitationsForID(int vitalsId) {
        
        ArrayList <DbVisitations> visitations = new ArrayList <DbVisitations> ();
        
        try {            
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT id,");
			sql.append("    Place,");
            sql.append("    Room,");
            sql.append("    Address,");
            sql.append("    Address2,");
            sql.append("    City,");
            sql.append("    State,");
            sql.append("    Zip,");
            sql.append("    StartTime,");
            sql.append("    EndTime,");
            sql.append("    EventDate,");
            sql.append("    Private,");
            sql.append("	Notes ");
            sql.append("FROM visitations ");              
            sql.append("WHERE (VitalsMasterKey = " + vitalsId +") AND (Deleted = 0) ");
            
            makeConnection(jndiLookup);
            statement = conn.prepareStatement(sql.toString());
            int col = 0;
            
            rs = statement.executeQuery();
            while (rs.next()) {
                col = 0;
                DbVisitations dbVisitation = new DbVisitations();                
                dbVisitation.setMainKey(vitalsId);
                dbVisitation.setId(rs.getInt(++col));
                dbVisitation.setPlace(rs.getString(++col));
                dbVisitation.setRoom(rs.getString(++col));      
                dbVisitation.setAddress(rs.getString(++col));           
                dbVisitation.setAddress2(rs.getString(++col));              
                dbVisitation.setCity(rs.getString(++col));          
                dbVisitation.setState(rs.getString(++col));           
                dbVisitation.setZip(rs.getString(++col)); 
                dbVisitation.setStartTime(FormatTime.convertTimeToAMPM(rs.getString(++col)));                
                dbVisitation.setEndTime(FormatTime.convertTimeToAMPM(rs.getString(++col)));
                Date date = rs.getDate(++col);
                dbVisitation.setDate(FormatDate.convertDateToMM_DD_YYYY(date));        
                // convert privacy status from 1/0 to true/false boolean
                dbVisitation.setPrivateVisitation(rs.getInt(++col)==1?"Yes":"No");
                dbVisitation.setNotes(rs.getString(++col));
                dbVisitation.setStatus('U'); // status is update by default
                
                visitations.add(dbVisitation);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getVisitationsForID() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getVisitationsForID() : ", e);
        } finally {
            closeConnection();
        }
        
        return visitations;
    }
    
    /**
     * @author Kier Heyl
     * 
     * Updates all of the visitations using updates, inserts and deletes
     */
    public void updateVisitations(ArrayList <DbVisitations> visitations, int vitalsId) {
    	
    	DbVisitations visitation;
    	
    	try {                
    		makeConnection(jndiLookup);
            
	    	for (int i=0; i < visitations.size(); i++) {
	    		visitation = (DbVisitations) visitations.get(i);
	    		visitation.setMainKey(vitalsId);
	    		// if this is a new visitation insert it
	    		if (visitation.getStatus() == 'A') {
	    			logger.debug("Did the visitation make it this far? " + visitation.getPlace());
					addVisitation(visitation);
	    		}
	    		// if this is a changed visitation update it
	    		else if (visitation.getStatus() == 'U') {
					updateVisitation(visitation);
	    		}
	    		// if this is a removed visitation delete it
	    		else if (visitation.getStatus() == 'D') {
					deleteVisitation(visitation);	    			
	    		}
	    		// if the status is still blank it means no changes were made and no actions need to be performed
	    	}
        } catch (Exception e) {
            logger.error("Exception in updateVisitations() : ", e);
        } finally {
            closeConnection();
        }
    }
    
    /**
     * @author Kier Heyl
     * 
     * Updates all of the visitations using updates, inserts and deletes
     */
    public void updateVisitation(DbVisitations visitation, int vitalsId) {
    	
    	try {                
    		makeConnection(jndiLookup);
            
            visitation.setMainKey(vitalsId);
			// if this is a new visitation insert it
			if (visitation.getStatus() == 'A') {
				logger.debug("Did the visitation make it this far? " + visitation.getPlace());
				addVisitation(visitation);
			}
			// if this is a changed visitation update it
			else if (visitation.getStatus() == 'U') {
				updateVisitation(visitation);
			}
			// if this is a removed visitation delete it
			else if (visitation.getStatus() == 'D') {
				deleteVisitation(visitation);
			}
			
        } catch (Exception e) {
            logger.error("Exception in updateVisitations() : ", e);
        } finally {
            closeConnection();
        }
    }
    
    /**
     * 
     * @param visitation
     * @param vitalsId
     * @author Kier Heyl
     * 
     * Inserts a visitation into the DB
     */
    private void addVisitation(DbVisitations visitation) throws SQLException {
                
        try {            
            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO visitations (");
            sql.append("    VitalsMasterKey,");
			sql.append("    Place,");
            sql.append("    Room,");
            sql.append("    Address,");
            sql.append("    Address2,");
            sql.append("    City,");
            sql.append("    State,");
            sql.append("    Zip,");
            sql.append("    StartTime,");
            sql.append("    EndTime,");
            sql.append("    EventDate,");
            sql.append("    Private,");
            sql.append("    Notes,");
            sql.append("	Modified) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            statement = conn.prepareStatement(sql.toString());
            
            int col=0;
            
            statement.setInt(++col, visitation.getMainKey());
            statement.setString(++col, visitation.getPlace());
            statement.setString(++col, visitation.getRoom());
            statement.setString(++col, visitation.getAddress());
            statement.setString(++col, visitation.getAddress2());
            statement.setString(++col, visitation.getCity());
            statement.setString(++col, visitation.getState());
            statement.setString(++col, visitation.getZip());
            statement.setString(++col, FormatTime.convertTimeToMilitary(visitation.getStartTime()));
            statement.setString(++col, FormatTime.convertTimeToMilitary(visitation.getEndTime()));
            statement.setString(++col, FormatDate.convertToDateYYYYMMDD(visitation.getDate()));
            statement.setInt(++col, visitation.getPrivateVisitation().compareTo("Yes") == 0?1:0);
            statement.setString(++col, visitation.getNotes());
            statement.setTimestamp(++col, new Timestamp(currentDate.getTime()));
                        
            statement.executeUpdate();
//            conn.commit();
            
        } catch (SQLException e) {
            logger.error("SQL Exception in addVisitation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addVisitation() : ", e);
        } finally {
        	if ( statement != null ) {
        		statement.close();
        		statement = null;
        	}
        }
        
    }
    
    /**
     * 
     * @param visitation
     * @param vitalsId
     * @author Kier Heyl
     * 
     * Updates a visitation in the DB
     */
    private void updateVisitation(DbVisitations visitation) throws SQLException {
                
        try {            
            
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE visitations SET");
			sql.append("    Place = ?,");
            sql.append("    Room = ?,");
            sql.append("    Address = ?,");
            sql.append("    Address2 = ?,");
            sql.append("    City = ?,");
            sql.append("    State = ?,");
            sql.append("    Zip = ?,");
            sql.append("    StartTime = ?,");
            sql.append("    EndTime = ?,");
            sql.append("    EventDate = ?,");
            sql.append("    Private = ?,");
            sql.append("	Notes = ?, ");
            sql.append("	Modified = ? ");
            sql.append("WHERE id = ?");
            
            statement = conn.prepareStatement(sql.toString());
            
            int col=0;
            
            statement.setString(++col, visitation.getPlace());
            statement.setString(++col, visitation.getRoom());
            statement.setString(++col, visitation.getAddress());
            statement.setString(++col, visitation.getAddress2());
            statement.setString(++col, visitation.getCity());
            statement.setString(++col, visitation.getState());
            statement.setString(++col, visitation.getZip());
            statement.setString(++col, FormatTime.convertTimeToMilitary(visitation.getStartTime()));
            statement.setString(++col, FormatTime.convertTimeToMilitary(visitation.getEndTime()));
            statement.setString(++col, FormatDate.convertToDateYYYYMMDD(visitation.getDate()));
            statement.setInt(++col, visitation.getPrivateVisitation().compareTo("Yes") == 0?1:0);
            statement.setString(++col, visitation.getNotes());
            statement.setTimestamp(++col, new Timestamp(currentDate.getTime()));
            statement.setInt(++col, visitation.getId());
            
            statement.executeUpdate();
//            conn.commit();
            
        } catch (SQLException e) {
            logger.error("SQL Exception in updateVisitation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateVisitation() : ", e);
        } finally {
        	if ( statement != null ) {
        		statement.close();
        		statement = null;
        	}
        }
    }
    
    /**
     * 
     * @param visitation
     * @param vitalsId
     * @author Kier Heyl
     * 
     * Deletes a visitation from the DB
     */
    private void deleteVisitation(DbVisitations visitation) throws SQLException {
                
        try {            
            
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE visitations SET");
			sql.append("    Deleted = 1,");
            sql.append("    Modified = ? ");
            sql.append("WHERE id = ?");
            
            statement = conn.prepareStatement(sql.toString());
            int col=0;

            statement.setTimestamp(++col, new Timestamp(currentDate.getTime()));
            statement.setInt(++col, visitation.getId());
            
            statement.executeUpdate(); 
//            conn.commit();           
            
        } catch (SQLException e) {
            logger.error("SQL Exception in deleteVisitation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteVisitation() : ", e);
        } finally {
        	if ( statement != null ) {
        		statement.close();
        		statement = null;
        	}
        }
    }
    
    /**
     * 
     * @return
     */
    public DbVisitations getVisitationForCaseList(int vitalsId) {
        
        DbVisitations visitation = new DbVisitations ();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        int col=0;
        
        try {           
        	makeConnection(jndiLookup);
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT iD, Place, Room, Address, Address2, City, State, Zip, ");
            sql.append("    StartTime, EndTime, EventDate, Private, Notes ");
            sql.append("FROM visitations ");              
            sql.append("WHERE (VitalsMasterKey = " + vitalsId +") ");
			sql.append("AND EventDate >= " + sqlDate.toString() + " ");
			sql.append("AND (Deleted = 0) ");
			sql.append("ORDER BY EventDate, StartTime ");
			sql.append("LIMIT 1");

			statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            if (rs.next()) {
            	visitation.setId(rs.getInt(++col));
                visitation.setPlace(rs.getString(++col));
                visitation.setRoom(rs.getString(++col));
                visitation.setAddress(rs.getString(++col));
                visitation.setAddress2(rs.getString(++col));
                visitation.setCity(rs.getString(++col));
                visitation.setState(rs.getString(++col));
                visitation.setZip(rs.getString(++col));
                visitation.setStartTime(FormatTime.convertTimeToAMPMShortHand(rs.getString(++col)));                
                visitation.setEndTime(FormatTime.convertTimeToAMPMShortHand(rs.getString(++col)));
                Date date = rs.getDate(++col);
                visitation.setDate(FormatDate.convertDateToMM_DD_YYYY(date));        
                // convert privacy status from 1/0 to true/false boolean
                visitation.setPrivateVisitation(rs.getInt(++col)==1?"Yes":"No");
                visitation.setNotes(rs.getString(++col));
            }
            else {

                sql = new StringBuffer();
	            sql.append("SELECT iD, Place, Room, Address, Address2, City, State, Zip, ");
	            sql.append("    StartTime, EndTime, EventDate, Private, Notes ");
	            sql.append("FROM visitations ");    
	            sql.append("WHERE (VitalsMasterKey = " + vitalsId +") "); 
				sql.append("AND (Deleted = 0) ");  
				sql.append("ORDER BY EventDate DESC, StartTime DESC ");
				sql.append("LIMIT 1");

				statement = conn.prepareStatement(sql.toString());
	            
	            rs = statement.executeQuery();
	            if (rs.next()) {
	            	visitation.setId(rs.getInt(++col));
	            	visitation.setPlace(rs.getString(++col));
	                visitation.setRoom(rs.getString(++col));
	                visitation.setAddress(rs.getString(++col));
	                visitation.setAddress2(rs.getString(++col));
	                visitation.setCity(rs.getString(++col));
	                visitation.setState(rs.getString(++col));
	                visitation.setZip(rs.getString(++col));
	                visitation.setStartTime(FormatTime.convertTimeToAMPMShortHand(rs.getString(++col)));                
	                visitation.setEndTime(FormatTime.convertTimeToAMPMShortHand(rs.getString(++col)));
	                Date date = rs.getDate(++col);
	                visitation.setDate(FormatDate.convertDateToMM_DD_YYYY(date));        
	                // convert privacy status from 1/0 to true/false boolean
	                visitation.setPrivateVisitation(rs.getInt(++col)==1?"Yes":"No");
	                visitation.setNotes(rs.getString(++col));              
	            }
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getVisitationsForID() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getVisitationsForID() : ", e);
        } finally {
            this.closeConnection();
        }
        
        return visitation;
    }
    
}
