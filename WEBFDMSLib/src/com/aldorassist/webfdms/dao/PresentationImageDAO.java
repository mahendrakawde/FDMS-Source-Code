package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author Parth
 * 
 *         DAO used for database transactions for Presentation Images stored in
 *         as common database
 * 
 */

public class PresentationImageDAO extends DAO {
	
	private Logger logger = Logger.getLogger(PresentationImageDAO.class.getName());
	
	/**
	 * 
	 * @param usersLookup
	 */
	public PresentationImageDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public PresentationImageDAO(DbUserSession user) {
		super(user);
	}
	
	private static String selectFields() {
		return ("ImageId, ImageName, ImagePath, ImageNumber, ThemeId");
	}

	/**
	 * Returns Presentation Image based on presentationImageId. This method will
	 * return single image.
	 * 
	 * @param presentationImageId
	 * @return
	 */
	public PresentationImageDTO getPresentationImage(long presentationImageId) {
		PresentationImageDTO presentationImage = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			
			sql.append(" FROM presentation_images WHERE ImageId=?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(1, presentationImageId);

			rs = statement.executeQuery();
			
			int col = 0;
			
			if(rs.next()) {
				presentationImage = new PresentationImageDTO();
				
				presentationImage.setFileId(rs.getLong(++col));
				presentationImage.setFileName(rs.getString(++col));
				presentationImage.setFilePath(rs.getString(++col));
				presentationImage.setImageNumber(rs.getLong(++col));
				presentationImage.setThemeId(rs.getLong(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:getPresentationImage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:getPresentationImage() : ", e);
		} finally {
			closeConnection();
		}
		
		return presentationImage;
	}
	
	/**
	 * Returns Presentation Images based on themeId passed to the method.
	 * 
	 * @param themeId
	 * @return
	 */
	public List<PresentationImageDTO> getPresentationImageByTheme(long themeId) {
		List<PresentationImageDTO> images = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM presentation_images WHERE ThemeId=?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, themeId);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			PresentationImageDTO presentationImage = null;
			
			while(rs.next()) {
				presentationImage = new PresentationImageDTO();
				
				presentationImage.setFileId(rs.getLong(++col));
				presentationImage.setFileName(rs.getString(++col));
				presentationImage.setFilePath(rs.getString(++col));
				presentationImage.setImageNumber(rs.getLong(++col));
				presentationImage.setThemeId(rs.getLong(++col));
				
				if(images == null) {
					images = new ArrayList<PresentationImageDTO>();
				}
				
				images.add(presentationImage);
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:getPresentationImageByTheme() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:getPresentationImageByTheme() : ", e);
		} finally {
			closeConnection();
		}
		
		return images;
	}
	
	public List<PresentationImageDTO> getAllPresentationImages() {
		List<PresentationImageDTO> presentationImages = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			
			sql.append(" FROM presentation_images");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());

			rs = statement.executeQuery();
			
			PresentationImageDTO presentationImageDTO = null;
			
			int col = 0;
			
			while(rs.next()) {
				presentationImageDTO = new PresentationImageDTO();
				
				presentationImageDTO.setFileId(rs.getLong(++col));
				presentationImageDTO.setFileName(rs.getString(++col));
				presentationImageDTO.setFilePath(rs.getString(++col));
				presentationImageDTO.setImageNumber(rs.getLong(++col));
				presentationImageDTO.setThemeId(rs.getLong(++col));
				
				if(presentationImages == null) {
					presentationImages = new ArrayList<PresentationImageDTO>();
				}
				
				presentationImages.add(presentationImageDTO);
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:getAllPresentationImages() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:getAllPresentationImages() : ", e);
		} finally {
			closeConnection();
		}
		
		return presentationImages;
	}

	/**
	 * Returns maximum number of the image inserted for particular theme. This
	 * method is required when we insert a new presentation image entry in the
	 * database. It will use this number & add incremented number to the database
	 * of the newer entry.
	 * 
	 * @param themeId
	 * @return
	 */
	public long getLatestImageNumberByTheme(long themeId) {
		Long imageNumber = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT MAX(ImageNumber) FROM presentation_images ");
			sql.append("WHERE ThemeId=?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, themeId);
			
			rs = statement.executeQuery();
			
			while(rs.next()) {
				imageNumber = rs.getLong(1);
			}
			
			if(imageNumber == null || imageNumber < 1) {
				imageNumber = 0L;
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:getMaxImageNumberByTheme() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:getMaxImageNumberByTheme() : ", e);
		} finally {
			closeConnection();
		}
		
		return imageNumber;
	}
	
	public PresentationImageDTO getDefaultPlaceHolder() {
		PresentationImageDTO defaultPlaceHolder = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM presentation_images WHERE ThemeId IS NULL");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			int col = 0;
			
			rs = statement.executeQuery();
			
			while(rs.next()) {
				defaultPlaceHolder = new PresentationImageDTO();
				
				defaultPlaceHolder.setFileId(rs.getLong(++col));
				defaultPlaceHolder.setFileName(rs.getString(++col));
				defaultPlaceHolder.setFilePath(rs.getString(++col));
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:hasDefaultPlaceHolder() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:hasDefaultPlaceHolder() : ", e);
		} finally {
			closeConnection();
		}
		
		return defaultPlaceHolder;
	}
	
	/**
	 * Adds presentation image to the database.
	 * 
	 * @param data
	 * @return
	 */
	public boolean addPresentationImage(PresentationImageDTO data) {
		boolean flag = false;
		
		try {
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO presentation_images ");
			sql.append("(ImageName, ImagePath, ImageNumber, ThemeId) ");
			
			sql.append("VALUES (?, ?, ?, ?)");
			
			int column = 0;
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(++column, data.getFileName());
			statement.setString(++column, data.getFilePath());
			statement.setLong(++column, data.getImageNumber());
			statement.setLong(++column, data.getThemeId());

			statement.executeUpdate();
			
			flag = true;
			
			if (flag) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					data.setFileId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:addPresentationImage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:addPresentationImage() : ", e);
		} finally {
			closeConnection();
		}
		
		return flag;
	}
	
	public List<Object> getRandomImages(long themeId, int noOfImagesRequired) {
		List<Object> images = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM presentation_images WHERE ThemeId=? ORDER BY RAND() LIMIT ?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, themeId);
			statement.setInt(2, noOfImagesRequired);
			
			rs = statement.executeQuery();
			
			int col = 0;
			PresentationImageDTO imageDto = null;
			
			while(rs.next()) {
				imageDto = new PresentationImageDTO();
				
				imageDto.setFileId(rs.getLong(++col));
				imageDto.setFileName(rs.getString(++col));
				imageDto.setFilePath(rs.getString(++col));
				imageDto.setImageNumber(rs.getLong(++col));
				imageDto.setThemeId(rs.getLong(++col));
				
				if(images == null) {
					images = new ArrayList<Object>();
				}
				
				images.add(imageDto);
				
				col = 0;
			}
			
			int imagesFoundFromDb = images.size();
			
			if(imagesFoundFromDb == 0) {
				images.add(getDefaultPlaceHolder());
			}
			
			if(imagesFoundFromDb < noOfImagesRequired) {
				int diff = noOfImagesRequired - imagesFoundFromDb;
				
				int index = 0;
				
				for(int i = 0 ; i < diff; i++) {
					images.add(images.get(index++));
					
					if(imagesFoundFromDb == index) {
						index = 0;
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:addPresentationImage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:addPresentationImage() : ", e);
		} finally {
			closeConnection();
		}
		
		return images;
	}
	
	public boolean addPlaceHolder(String fileName, String filePath) {
		boolean added = false;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO presentation_images ");
			sql.append("(ImageName, ImagePath, ImageNumber, ThemeId) ");
			sql.append("VALUES (?, ?, 0, NULL)");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setString(1, fileName);
			statement.setString(2, filePath);
			
			statement.executeUpdate();
			
			added = true;
			
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:addPlaceHolder() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:addPlaceHolder() : ", e);
		} finally {
			closeConnection();
		}
		
		return added;
	}
	
	public boolean deletePresentationImage(long imageId) {
		boolean deleted = false;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE FROM presentation_images WHERE ImageId = ?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, imageId);
			
			statement.executeUpdate();
			
			deleted = true;
		} catch (SQLException e) {
			logger.error("SQLException in PresentationImageDAO:deletePresentationImage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in PresentationImageDAO:deletePresentationImage() : ", e);
		} finally {
			closeConnection();
		}
		
		return deleted;
	}

}
