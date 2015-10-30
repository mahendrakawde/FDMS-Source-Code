package com.aldorsolutions.webfdms.vitalstat.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.vitalstat.model.VitalstatDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class VitalstatDAO extends DAO {

	public VitalstatDAO() {
		// TODO Auto-generated constructor stub
	}

	public VitalstatDAO(String dbLookup) {
		super(dbLookup);
		// TODO Auto-generated constructor stub
	}

	public VitalstatDAO(DbUserSession user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	public VitalstatDAO(long companyID, long userId) {
		super(companyID, userId);
		// TODO Auto-generated constructor stub
	}
	
	 private Logger logger = Logger.getLogger(VitalstatDAO.class.getName());
	 
	 
	 public VitalstatDTO getVitalstat(int vitalstatID) {
	    	VitalstatDTO version = null;
	        
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append("select VitalsMasterKey, WarFromDate, WarToDate  from vitalstats where vitalsmasterkey = ?");
	            
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setInt(1, vitalstatID);
	            
	            rs = statement.executeQuery();
	            
	            if (rs.next()) {
	            	int col = 1;
	                version = new VitalstatDTO();
	                version.setVitalsmasterkey(rs.getInt(col++));
	                version.setWarFromDate(rs.getString(col++));
	                version.setWarToDate(rs.getString(col++));
	         
	                return(version);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in getVitalstat() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in getVitalstat() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return null;
	    }
	    
	    public boolean updateVitalstat(VitalstatDTO vitalstat) {
	        boolean added = false;
	        
	        try {
	        	VitalstatDTO oldVer = getVitalstat(vitalstat.getVitalsmasterkey());
	        	
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE vitalstats set WarFromDate = ?, WarToDate = ? WHERE VitalsMasterKey = ? ");
	            
	            int col = 0;
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setString(++col, vitalstat.getWarFromDate());
	            statement.setString(++col, vitalstat.getWarToDate());
	            statement.setInt(++col, vitalstat.getVitalsmasterkey());
	            statement.executeUpdate();
	            added = true;
	            
	            if (added) {
		            auditDTO.setCompanyId((int) this.companyID);
		            auditDTO.setUserId((int) this.userID);
		            
		            updateAudit(oldVer, vitalstat);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in updateVitalstat() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in updateVitalstat() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return added;
	    }
	    
	    
	    
	    
}
