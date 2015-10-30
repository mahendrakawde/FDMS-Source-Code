package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ImageMappingDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class ImageMappingDAO extends DAO {

	private Logger logger = Logger.getLogger(PresentationImageDAO.class
			.getName());

	/**
	 * 
	 * @param usersLookup
	 */
	public ImageMappingDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public ImageMappingDAO(DbUserSession user) {
		super(user);
	}

	/**
	 * 
	 * @param companyID
	 * @param userID
	 */
	public ImageMappingDAO(long companyID, long userID) {
		super(companyID, userID);
	}

	private static String selectFields() {
		return ("ImageId, CaseId, CompanyId, PresentationImageId, ImageCategory");
	}

	public List<ImageMappingDTO> getImagesByCase(long caseId, long companyId) {
		List<ImageMappingDTO> images = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM imagemapping WHERE CaseId=? AND CompanyId=?");

			makeConnection(getDbLookup());
			
			int col = 0;

			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, caseId);
			statement.setLong(++col, companyId);

			rs = statement.executeQuery();

			ImageMappingDTO imageMapping = null;

			int column = 0;

			while (rs.next()) {
				imageMapping = new ImageMappingDTO();

				imageMapping.setImageId(rs.getInt(++column));
				imageMapping.setCaseId(rs.getInt(++column));
				imageMapping.setCompanyId(rs.getInt(++column));
				imageMapping.setPresentationImageId(rs.getInt(++column));
				imageMapping.setImageCategory(rs.getString(++column));

				if (images == null) {
					images = new ArrayList<ImageMappingDTO>();
				}

				images.add(imageMapping);
			}
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:getImagesByCase() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:getImagesByCase() : ", e);
		} finally {
			closeConnection();
		}

		return images;
	}
	
	public List<ImageMappingDTO> getImagesByCaseAndCategory(long caseId, long companyId, String imageCategory) {
		List<ImageMappingDTO> images = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM imagemapping WHERE CaseId = ? AND CompanyId = ? AND ImageCategory = ?");

			makeConnection(getDbLookup());
			
			int col = 0;

			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, caseId);
			statement.setLong(++col, companyId);
			statement.setString(++col, imageCategory);

			rs = statement.executeQuery();

			ImageMappingDTO imageMapping = null;

			int column = 0;

			while (rs.next()) {
				imageMapping = new ImageMappingDTO();

				imageMapping.setImageId(rs.getInt(++column));
				imageMapping.setCaseId(rs.getInt(++column));
				imageMapping.setCompanyId(rs.getInt(++column));
				imageMapping.setPresentationImageId(rs.getInt(++column));
				imageMapping.setImageCategory(rs.getString(++column));

				if (images == null) {
					images = new ArrayList<ImageMappingDTO>();
				}

				images.add(imageMapping);
			}
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:getImagesByCase() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:getImagesByCase() : ", e);
		} finally {
			closeConnection();
		}

		return images;
	}
	
	public List<Long> getPresentationImageId(long caseId, long companyId, String imageCategory) {
		List<Long> images = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT PresentationImageId ");
			sql.append(" FROM imagemapping WHERE CaseId = ? AND CompanyId = ? AND ImageCategory = ?");

			makeConnection(getDbLookup());
			
			int col = 0;

			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, caseId);
			statement.setLong(++col, companyId);
			statement.setString(++col, imageCategory);

			rs = statement.executeQuery();

			while (rs.next()) {
				if (images == null) {
					images = new ArrayList<Long>();
				}

				images.add(rs.getLong(1));
			}
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:getImagesByCase() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:getImagesByCase() : ", e);
		} finally {
			closeConnection();
		}

		return images;
	}

	public boolean addImageMapping(ImageMappingDTO data) {
		boolean flag = false;

		try {
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			StringBuffer sql = new StringBuffer();

			sql.append("INSERT INTO imagemapping (CaseId, CompanyId, PresentationImageId, ImageCategory) ");

			sql.append("VALUES (?, ?, ?, ?)");

			int column = 0;

			makeConnection(getDbLookup());

			statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			statement.setLong(++column, data.getCaseId());
			statement.setLong(++column, data.getCompanyId());
			statement.setLong(++column, data.getPresentationImageId());
			statement.setString(++column, data.getImageCategory());

			statement.executeUpdate();

			flag = true;

			if (flag) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					data.setImageId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:addImageMapping() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:addImageMapping() : ", e);
		} finally {
			closeConnection();
		}

		return flag;
	}
	
	public boolean addBulkImageMapping(ImageMappingDTO data, Long[] selectedImages) {
		boolean flag = false;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append("INSERT INTO imagemapping (CaseId, CompanyId, PresentationImageId, ImageCategory) ");

			sql.append("VALUES (?, ?, ?, ?)");

			makeConnection(getDbLookup());

			statement = conn.prepareStatement(sql.toString());

			statement.setLong(1, data.getCaseId());
			statement.setLong(2, data.getCompanyId());
			statement.setString(4, data.getImageCategory());
			
			for(Long selectedImage : selectedImages) {
				statement.setLong(3, selectedImage);				
				
				statement.executeUpdate();
			}

			flag = true;

			/*if (flag) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					data.setImageId(rs.getInt(1));
				}
			}*/
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:addImageMapping() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:addImageMapping() : ", e);
		} finally {
			closeConnection();
		}

		return flag;
	}

	public boolean updateSingleImageMapping(ImageMappingDTO data) {
		boolean updated = false;

		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE imagemapping SET CaseId = ?, CompanyId = ?, PresentationImageId = ?, ImageCategory = ? ");
			sql.append("WHERE ImageId = ?");

			int col = 0;
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());

			statement.setLong(++col, data.getCaseId());
			statement.setLong(++col, data.getCompanyId());
			statement.setLong(++col, data.getPresentationImageId());
			statement.setString(++col, data.getImageCategory());
			
			statement.setLong(++col, data.getImageId());

			statement.executeUpdate();

			updated = true;
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:updateImageMapping() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:updateImageMapping() : ", e);
		} finally {
			closeConnection();
		}

		return updated;
	}
	
	public boolean updateBulkImageMapping(ImageMappingDTO data, Long[] selectedImages) {
		boolean updated = false;

		try {
			if(this.deleteImageMapping(data.getCaseId(), data.getCompanyId(), data.getImageCategory())) {
				this.addBulkImageMapping(data, selectedImages);
				
				updated = true;
			}
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:updateImageMapping() : ", e);
		} finally {
			closeConnection();
		}

		return updated;
	}
	
	public boolean deleteImageMapping(long caseId, long companyId, String imageCategory) {
		boolean deleted = false;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE FROM imagemapping WHERE CaseId = ? AND CompanyId = ? AND ImageCategory = ?");
			
			makeConnection(getDbLookup());
			
			int col = 0;
			
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(++col, caseId);
			statement.setLong(++col, companyId);
			statement.setString(++col, imageCategory);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException in ImageMappingDAO:updateImageMapping() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ImageMappingDAO:updateImageMapping() : ", e);
		} finally {
			closeConnection();
		}
		
		return deleted;
	}

}
