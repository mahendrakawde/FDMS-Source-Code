package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;



import com.aldorassist.webfdms.model.LifeFilesDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class LifeFilesDAO extends DAO {

	
	private Logger logger = Logger.getLogger(LifeFilesDAO.class.getName());
	
	public LifeFilesDAO(String userLookup){
		super(userLookup);
	}
	
	public LifeFilesDAO(DbUserSession user){
		super(user);
	}
	
	
	public LifeFilesDAO(long companyID, long userId) {
		super(companyID, userId);
	}
	
	public LifeFilesDTO getLifeFilesRecords(int caseId,int companyId){
		
		LifeFilesDTO lifeFilesDTO =null;
		
		try{
			
			String sql= "SELECT  vital.DecMrMrs, vital.DeceasedFirstName, vital.DeceasedMidName, vital.DeceasedLastName, vital.DeceasedSuffix, vital.DecEducation, vital.DeceasedFullName, donation.CharityName, vital.DateOfBirth, vital.DateOfDeath, obit.ObitNotice "
					+"FROM vitalstats vital "
					+"LEFT JOIN obituary AS obit ON   vital.VitalsMasterKey = obit.VitalsId "
					+"LEFT JOIN donations AS donation ON vital.VitalsMasterKey = donation.vitalsId "
					+"WHERE  "
					+"vital.VitalsMasterKey=? ";
			
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setLong(1, caseId);
			
			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				
				lifeFilesDTO = new LifeFilesDTO();
				lifeFilesDTO.setClientId(companyId);
				lifeFilesDTO.setNamePrefix(rs.getString(++col));
				lifeFilesDTO.setFirstName(rs.getString(++col));
				lifeFilesDTO.setMiddleName(rs.getString(++col));
				lifeFilesDTO.setLastName(rs.getString(++col));
				lifeFilesDTO.setNameSuffix(rs.getString(++col));
				lifeFilesDTO.setTitleRankDegree(rs.getString(++col));
				lifeFilesDTO.setDisplayName(rs.getString(++col));
				lifeFilesDTO.setCharityName(rs.getString(++col));
				lifeFilesDTO.setDateofBirth(rs.getString(++col));
				lifeFilesDTO.setDateofDied(rs.getString(++col));
				lifeFilesDTO.setObituary(rs.getString(++col));
				lifeFilesDTO.setVitalsId(caseId);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in getLifeFilesDTO() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getLifeFilesDTO() : ", e);
		} finally {
			closeConnection();
		}
		return lifeFilesDTO;
	}
	
	public long getLifefilesClientId(String lifeFilesUserName) {
		long lifeFilesClientId = 0;
		
		try {
			String sql = "SELECT client_id FROM clients WHERE username = ?";
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, lifeFilesUserName);
			
			rs = statement.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt(1)>0){
					lifeFilesClientId = rs.getLong(1);
				}
			}
			
		} catch (SQLException e) {
			logger.error("Exception in getLifefilesClientId() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getLifefilesClientId() : ", e);
		} finally {
			closeConnection();
		}
		
		return lifeFilesClientId;
	}
	
	public int addLifeFiles(LifeFilesDTO data) throws Exception {
			
		boolean added=false;
		
		int broadcastsId = 0;
		
		try{
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			String sql = "INSERT INTO broadcasts (client_id,name_prefix, first_name, middle_name, last_name, name_suffix, title_rank_degree, display_name, charity_name, born_date, died_date,obituary,photo,original_photo,vitals_id,fdms_company_id)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
				int col = 0;
				
				makeConnection(getDbLookup());
				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				statement.setLong(++col, data.getClientId());
				statement.setString(++col, data.getNamePrefix());
				statement.setString(++col,data.getFirstName());
				statement.setString(++col,data.getMiddleName());
				statement.setString(++col,data.getLastName());
				statement.setString(++col,data.getNameSuffix());
				statement.setString(++col,data.getTitleRankDegree());
				statement.setString(++col,data.getDisplayName());
				statement.setString(++col,data.getCharityName());
				statement.setString(++col,data.getDateofBirth());
				statement.setString(++col,data.getDateofDied());
				statement.setString(++col,data.getObituary());
				statement.setString(++col,data.getPhotoName());
				statement.setString(++col,data.getOriginalPhoto());
				statement.setInt(++col,data.getVitalsId());
				statement.setLong(++col,data.getFdmsCompanyId());
				
				statement.executeUpdate();
				added = true;
			
				if (added) {
					rs = statement.getGeneratedKeys();
					if (rs.next()) {
						broadcastsId = rs.getInt(1);
					}
				}
			
			
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	
		return broadcastsId;
	}
	
	public int updateLifeFiles(LifeFilesDTO data) throws Exception{
		
		int broadcastsId = 0;
		
		try{
			String sql = "UPDATE broadcasts SET	 client_id = ? , name_prefix = ? , first_name = ? , "
				+" middle_name = ? , last_name= ? , name_suffix= ?, title_rank_degree= ? , display_name= ? ,charity_name= ? , born_date= ? , died_date= ?, obituary= ?, photo= ?, "
				+" original_photo = ?, vitals_id = ?, fdms_company_id = ? WHERE vitals_id=? AND client_id = ?";

			int col = 0;
			
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			
			statement.setLong(++col, data.getClientId());
			statement.setString(++col, data.getNamePrefix());
			statement.setString(++col,data.getFirstName());
			statement.setString(++col,data.getMiddleName());
			statement.setString(++col,data.getLastName());
			statement.setString(++col,data.getNameSuffix());
			statement.setString(++col,data.getTitleRankDegree());
			statement.setString(++col,data.getDisplayName());
			statement.setString(++col,data.getCharityName());
			statement.setString(++col,data.getDateofBirth());
			statement.setString(++col,data.getDateofDied());
			statement.setString(++col,data.getObituary());
			statement.setString(++col,data.getPhotoName());
			statement.setString(++col,data.getOriginalPhoto());
			// Set value for Vitals
			statement.setLong(++col, data.getVitalsId());
			statement.setLong(++col, data.getFdmsCompanyId());
			
			// Set value of Vitals for where clause
			statement.setLong(++col, data.getVitalsId());
			statement.setLong(++col, data.getClientId());
		
			statement.executeUpdate();
			
			broadcastsId = getBroadcastId(data.getClientId(), data.getVitalsId());
			
		}catch(SQLException ex){
			throw ex;
		}catch(Exception ex){
			throw ex;
		}finally{
			closeConnection();
		}
		
		return broadcastsId;
	}
	
	private int getBroadcastId(long companyId, long caseId) {
		int broadcastId = 0;
		
		try {
			String sql = "SELECT id FROM broadcasts WHERE client_id = ? AND vitals_id = ?";
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql);
			
			statement.setLong(1, companyId);
			statement.setLong(2, caseId);
			
			rs = statement.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt(1)>0){
					broadcastId = rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in getBroadcastId() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getBroadcastId() : ", e);
		} finally {
			closeConnection();
		}
		
		return broadcastId;
	}
	
	public boolean checkAddOrUpdate(int caseId, int clientId) {
		boolean flag=false;
		try{
			String sql= "SELECT COUNT(*) FROM broadcasts WHERE vitals_id=? AND fdms_company_id = ?";
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
			statement.setLong(1, caseId);
			statement.setLong(2, clientId);
			
			rs = statement.executeQuery();
			if (rs.next()) {
				if(rs.getInt(1) > 0){
					flag = true;
				}
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in getLifeFilesDTO() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getLifeFilesDTO() : ", e);
		} finally {
			closeConnection();
		}
		
		return flag;
	}
	
	public boolean addImageToLifeFiles(LifeFilesDTO data) {
		
		boolean added = false;
		
		try {
			StringBuffer query = new StringBuffer();
			
			query.append("INSERT INTO images (client_id, deceased_id, image_name, caption, order_id, fdms_company_id) VALUES (?, ?, ?, ?, ?, ?)");
			
			int col = 0;
			
			makeConnection(getDbLookup());

			statement = conn.prepareStatement(query.toString());
			
			statement.setLong(++col, data.getClientId());
			statement.setLong(++col, data.getDecedentId());
			statement.setString(++col, data.getPhotoName());
			statement.setString(++col, data.getCaption());
			statement.setLong(++col, data.getImageOrder());
			statement.setLong(++col, data.getFdmsCompanyId());
			
			statement.executeUpdate();
			
			added = true;
		
			if (added) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					data.setId(rs.getInt(1));
				}
			}
		
		} catch (SQLException e) {
			logger.error("SQLException in LifeFilesDAO:addImageToLifeFiles() : ", e);
		} catch (Exception e) {
			logger.error("Exception in LifeFilesDAO:addImageToLifeFiles() : ", e);
		} finally {
			closeConnection();
		}
		
		return added;
	}
	
	public boolean isImageAlreadyExist(long companyId, long decedentId, long orderId){
		
		boolean flag=false;
		
		try{
			String sql= "SELECT COUNT(*) FROM images WHERE fdms_company_id=? AND deceased_id=? AND order_id=?";
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql);
			
			statement.setLong(1, companyId);
			statement.setLong(2, decedentId);
			statement.setLong(3, orderId);
			
			rs = statement.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt(1)>0){
					flag=true;
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in isImageAlreadyExist() : ", e);
		} catch (Exception e) {
			logger.error("Exception in isImageAlreadyExist() : ", e);
		} finally {
			closeConnection();
		}
		
		return flag;
	}
	
	
	public boolean updateImageToLifeFiles(LifeFilesDTO data) throws Exception{
		boolean updated=false;
		
		try{
			String sql = "UPDATE images SET  image_name = ?, caption = ?, order_id = ?, client_id = ? "
				+" WHERE fdms_company_id = ? AND deceased_id = ? AND order_id = ?";

			int col = 0;
			
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql);
		
			
			statement.setString(++col, data.getPhotoName());
			statement.setString(++col, data.getCaption());
			statement.setLong(++col, data.getImageOrder());
			statement.setLong(++col, data.getClientId());
			statement.setLong(++col, data.getFdmsCompanyId());
			statement.setLong(++col, data.getDecedentId());
			statement.setLong(++col, data.getImageOrder());
			
			statement.executeUpdate();
			
			updated = true;
			
		}catch(SQLException ex){
			throw ex;
		}catch(Exception ex){
			throw ex;
		}finally{
			closeConnection();
		}
		
		return updated;
		
	}
}
