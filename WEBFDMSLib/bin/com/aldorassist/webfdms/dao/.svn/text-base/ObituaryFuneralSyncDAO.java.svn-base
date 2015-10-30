package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.SpeedDataRuleDTO;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.display.FuneralSyncListBean;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatString;



public class ObituaryFuneralSyncDAO extends DAO {

	private Logger logger = Logger.getLogger(ObituaryFuneralSyncDAO.class.getName());

	public ObituaryFuneralSyncDAO(String dbLookup) {
		super(dbLookup);
	}
	
//	public ObituaryFuneralSyncDAO(DbUserSession user) {
//		super(user);
//	}

//	public ObituaryFuneralSyncDAO(long companyID, long userID) {
//		super(companyID, userID);
//	}

	private static String selectFields() {
		return ("id, ObitID, LocationID, DecTitle, DecFirstName, DecMiddleName,	DecLastName, DecNickName, DecMaidenName, " +
				"DateOfBirth, PlaceOfBirth, DateOfDeath, PlaceOfDeath, ViewType, ViewNoBodyPresent, ViewDate, ViewTime, " +
				"ViewAtHome, ViewLocation, OtherViewAtHome,	ViewOtherLocation, ViewOtherDate, ViewOtherTime, BurialType, " +
				"BurialDate, BurialTime, CemeteryName, ServiceType,	ServiceDate, ServiceTime, ServiceAtHome, ServiceOtherLocation, " +
				"ExtraServices,	FlowersType, FlowersDate, FlowersTime, FlowersOtherDate, FlowersOtherTime, FlowersOtherLocation, " +
				"ContributionName, Eulogy, Survivors, DateEntered, DateModified, ArchiveDate, ClientId,	PostedYN, PostedDateTime");
	}

	public FuneralSyncListBean getObituaryFuneralSync(String dbLookup, int caseId) throws PersistenceException {
		FuneralSyncListBean bean = new FuneralSyncListBean();

		try {
			String sql = "select " + selectFields() + " from obituary WHERE id = ?; ";
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql);
			statement.setInt(1, caseId);
			
			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				bean.setId(rs.getInt(++col)); 
				bean.setObitID(rs.getString(++col)); 
				bean.setLocationID(rs.getString(++col));  
				bean.setDecTitle(rs.getString(++col));  
				bean.setDecFirstName(rs.getString(++col));  
				bean.setDecMiddleName(rs.getString(++col));  
				bean.setDecLastName(rs.getString(++col));  
				bean.setDecNickName(rs.getString(++col));  
				bean.setDecMaidenName(rs.getString(++col));  
				bean.setDateOfBirth(rs.getDate(++col));  
				bean.setPlaceOfBirth(rs.getString(++col));  
				bean.setDateOfDeath(rs.getDate(++col));  
				bean.setPlaceOfDeath(rs.getString(++col));  
				bean.setViewType(rs.getString(++col));  
				bean.setViewNoBodyPresent(rs.getString(++col));  
				bean.setViewDate(rs.getDate(++col));  
				bean.setViewTime(rs.getString(++col));  
				bean.setViewAtHome(rs.getString(++col));  
				bean.setViewLocation(rs.getString(++col));  
				bean.setOtherViewAtHome(rs.getString(++col));  
				bean.setViewOtherLocation(rs.getString(++col));  
				bean.setViewOtherDate(rs.getDate(++col));  
				bean.setViewOtherTime(rs.getString(++col));  
				bean.setBurialType(rs.getString(++col));  
				bean.setBurialDate(rs.getDate(++col));  
				bean.setBurialTime(rs.getString(++col));  
				bean.setCemeteryName(rs.getString(++col));  
				bean.setServiceType(rs.getString(++col));  
				bean.setServiceDate(rs.getDate(++col));  
				bean.setServiceTime(rs.getString(++col));  
				bean.setServiceAtHome(rs.getString(++col));  
				bean.setServiceOtherLocation(rs.getString(++col));  
				bean.setExtraServices(rs.getString(++col));  
				bean.setFlowersType(rs.getString(++col));  
				bean.setFlowersDate(rs.getDate(++col));  
				bean.setFlowersTime(rs.getString(++col));  
				bean.setFlowersOtherDate(rs.getDate(++col));  
				bean.setFlowersOtherTime(rs.getString(++col));  
				bean.setFlowersOtherLocation(rs.getString(++col));  
				bean.setContributionName(rs.getString(++col));  
				bean.setEulogy(rs.getString(++col));  
				bean.setSurvivors(rs.getString(++col));  
				bean.setDateEntered(rs.getDate(++col));  
				bean.setDateModified(rs.getDate(++col));  
				bean.setArchiveDate(rs.getDate(++col));  
				bean.setClientId(rs.getString(++col));  
				bean.setPostedYN(rs.getString(++col));  
				bean.setPostedDateTime(rs.getLong(++col)); 
				bean.setFullName();
				
			}

		} catch (SQLException e) {
			throw new PersistenceException (e); 
		} catch (Exception e) {
			logger.error("Exception in getObituaryFuneralSync() : ", e);
		} finally {
			closeConnection();
		}

		return bean;
	}
	
	public ArrayList<FuneralSyncListBean> getObituaryFuneralSync(String dbLookup, int clientId, boolean onlyUnposted) throws PersistenceException {
		ArrayList<FuneralSyncListBean> list = new ArrayList<FuneralSyncListBean>();

		try {
			ArrayList <Object> parameters = new ArrayList <Object> ();
			String sql = "select " + selectFields() + " from obituary WHERE ClientId = ? ";
			
			if(onlyUnposted){
				sql = sql + " and (PostedYN IS NULL or (PostedYN <> 'Y' and PostedYN <> 'D')) ";
			}
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql);
			statement.setInt(1, clientId);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				int col = 0;
				FuneralSyncListBean bean = new FuneralSyncListBean();
				bean.setId(rs.getInt(++col)); 
				bean.setObitID(rs.getString(++col)); 
				bean.setLocationID(rs.getString(++col));  
				bean.setDecTitle(rs.getString(++col));  
				bean.setDecFirstName(rs.getString(++col));  
				bean.setDecMiddleName(rs.getString(++col));  
				bean.setDecLastName(rs.getString(++col));  
				bean.setDecNickName(rs.getString(++col));  
				bean.setDecMaidenName(rs.getString(++col));  
				bean.setDateOfBirth(rs.getDate(++col));  
				bean.setPlaceOfBirth(rs.getString(++col));  
				bean.setDateOfDeath(rs.getDate(++col));  
				bean.setPlaceOfDeath(rs.getString(++col));  
				bean.setViewType(rs.getString(++col));  
				bean.setViewNoBodyPresent(rs.getString(++col));  
				bean.setViewDate(rs.getDate(++col));  
				bean.setViewTime(rs.getString(++col));  
				bean.setViewAtHome(rs.getString(++col));  
				bean.setViewLocation(rs.getString(++col));  
				bean.setOtherViewAtHome(rs.getString(++col));  
				bean.setViewOtherLocation(rs.getString(++col));  
				bean.setViewOtherDate(rs.getDate(++col));  
				bean.setViewOtherTime(rs.getString(++col));  
				bean.setBurialType(rs.getString(++col));  
				bean.setBurialDate(rs.getDate(++col));  
				bean.setBurialTime(rs.getString(++col));  
				bean.setCemeteryName(rs.getString(++col));  
				bean.setServiceType(rs.getString(++col));  
				bean.setServiceDate(rs.getDate(++col));  
				bean.setServiceTime(rs.getString(++col));  
				bean.setServiceAtHome(rs.getString(++col));  
				bean.setServiceOtherLocation(rs.getString(++col));  
				bean.setExtraServices(rs.getString(++col));  
				bean.setFlowersType(rs.getString(++col));  
				bean.setFlowersDate(rs.getDate(++col));  
				bean.setFlowersTime(rs.getString(++col));  
				bean.setFlowersOtherDate(rs.getDate(++col));  
				bean.setFlowersOtherTime(rs.getString(++col));  
				bean.setFlowersOtherLocation(rs.getString(++col));  
				bean.setContributionName(rs.getString(++col));  
				bean.setEulogy(rs.getString(++col));  
				bean.setSurvivors(rs.getString(++col));  
				bean.setDateEntered(rs.getDate(++col));  
				bean.setDateModified(rs.getDate(++col));  
				bean.setArchiveDate(rs.getDate(++col));  
				bean.setClientId(rs.getString(++col));  
				bean.setPostedYN(rs.getString(++col));  
				bean.setPostedDateTime(rs.getLong(++col)); 
				bean.setFullName();
				list.add(bean);
			}

		} catch (SQLException e) {
			throw new PersistenceException (e); 
		} catch (Exception e) {
			logger.error("Exception in getObituaryFuneralSync() : ", e);
		} finally {
			closeConnection();
		}

		return list;
	}
	
	public ArrayList <FuneralSyncListBean> getObituaryFuneralSync (String dbLookup, String [] ids ) {
		ArrayList <FuneralSyncListBean> list = new ArrayList <FuneralSyncListBean> ();
        String sqlIds = new String();
        
        	// Take the array of invoice numbers and create a string ="XX,XX,XX" where
        	// XX is equal to one of the invoice numbers.  We are creating the string
        	// so that we can use it in a SQL 'IN' statement
        for (int x=0; x < ids.length; x++) {
        	sqlIds += ids[x];
        	
        	if (x+1 < ids.length) {
        		sqlIds += ",";
        	}
        }
    	
        try {
            String sql = "select " + selectFields() + " FROM obituary WHERE id in (" + sqlIds + ") ORDER BY id";
            
            makeConnection(dbLookup);
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
				FuneralSyncListBean bean = new FuneralSyncListBean();
				bean.setId(rs.getInt(++col)); 
				bean.setObitID(rs.getString(++col)); 
				bean.setLocationID(rs.getString(++col));  
				bean.setDecTitle(rs.getString(++col));  
				bean.setDecFirstName(rs.getString(++col));  
				bean.setDecMiddleName(rs.getString(++col));  
				bean.setDecLastName(rs.getString(++col));  
				bean.setDecNickName(rs.getString(++col));  
				bean.setDecMaidenName(rs.getString(++col));  
				bean.setDateOfBirth(rs.getDate(++col));  
				bean.setPlaceOfBirth(rs.getString(++col));  
				bean.setDateOfDeath(rs.getDate(++col));  
				bean.setPlaceOfDeath(rs.getString(++col));  
				bean.setViewType(rs.getString(++col));  
				bean.setViewNoBodyPresent(rs.getString(++col));  
				bean.setViewDate(rs.getDate(++col));  
				bean.setViewTime(rs.getString(++col));  
				bean.setViewAtHome(rs.getString(++col));  
				bean.setViewLocation(rs.getString(++col));  
				bean.setOtherViewAtHome(rs.getString(++col));  
				bean.setViewOtherLocation(rs.getString(++col));  
				bean.setViewOtherDate(rs.getDate(++col));  
				bean.setViewOtherTime(rs.getString(++col));  
				bean.setBurialType(rs.getString(++col));  
				bean.setBurialDate(rs.getDate(++col));  
				bean.setBurialTime(rs.getString(++col));  
				bean.setCemeteryName(rs.getString(++col));  
				bean.setServiceType(rs.getString(++col));  
				bean.setServiceDate(rs.getDate(++col));  
				bean.setServiceTime(rs.getString(++col));  
				bean.setServiceAtHome(rs.getString(++col));  
				bean.setServiceOtherLocation(rs.getString(++col));  
				bean.setExtraServices(rs.getString(++col));  
				bean.setFlowersType(rs.getString(++col));  
				bean.setFlowersDate(rs.getDate(++col));  
				bean.setFlowersTime(rs.getString(++col));  
				bean.setFlowersOtherDate(rs.getDate(++col));  
				bean.setFlowersOtherTime(rs.getString(++col));  
				bean.setFlowersOtherLocation(rs.getString(++col));  
				bean.setContributionName(rs.getString(++col));  
				bean.setEulogy(rs.getString(++col));  
				bean.setSurvivors(rs.getString(++col));  
				bean.setDateEntered(rs.getDate(++col));  
				bean.setDateModified(rs.getDate(++col));  
				bean.setArchiveDate(rs.getDate(++col));  
				bean.setClientId(rs.getString(++col));  
				bean.setPostedYN(rs.getString(++col));  
				bean.setPostedDateTime(rs.getLong(++col)); 
				bean.setFullName();
				list.add(bean);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getObituaryFuneralSync() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getObituaryFuneralSync() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }

	   public boolean updateObituaryFuneralSync(FuneralSyncListBean bean) {
	        boolean added = false;
	        
	        try {
	        	
	        	
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE obituary set " +
	            		"PostedYN = ?, " +
	            		"PostedDateTime = ? " +
	            		"WHERE id = ? ");
	            
	            int col = 0;
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setString(++col, bean.getPostedYN());
	            statement.setTimestamp(++col, new java.sql.Timestamp(bean.getPostedDateTime()));
	            statement.setInt(++col, bean.getId());
	            statement.executeUpdate();
  
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in updateObituaryFuneralSync() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in updateObituaryFuneralSync() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return added;
	    }
	   
	   public boolean updateObituaryFuneralSync(String postedYN, Long postedDateTime, int id) {
	        boolean added = false;
	        
	        try {
	        	
	        	
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE obituary set " +
	            		"PostedYN = ?, " +
	            		"PostedDateTime = ? " +
	            		"WHERE id = ? ");
	            
	            int col = 0;
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setString(++col, postedYN);
	            statement.setTimestamp(++col, new java.sql.Timestamp(postedDateTime));
	            statement.setInt(++col, id);
	            statement.executeUpdate();
 
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in updateObituaryFuneralSync() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in updateObituaryFuneralSync() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return added;
	    }
	   
}
