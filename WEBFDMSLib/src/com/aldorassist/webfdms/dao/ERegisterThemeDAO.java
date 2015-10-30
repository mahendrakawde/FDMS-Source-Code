package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ERegisterThemeDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class ERegisterThemeDAO extends DAO {
	
	private Logger logger = Logger.getLogger(PresentationImageDAO.class
			.getName());

	/**
	 * 
	 * @param usersLookup
	 */
	public ERegisterThemeDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public ERegisterThemeDAO(DbUserSession user) {
		super(user);
	}

	private String getFields() {
		return "ThemeId, ThemeDesc";
	}
	
	public ERegisterThemeDTO getThemeById(Long themeId) {
		ERegisterThemeDTO themeDto = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(getFields());
			sql.append(" FROM presentation_theme WHERE ThemeId=?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, themeId);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			while(rs.next()) {
				themeDto = new ERegisterThemeDTO();
				
				themeDto.setThemeId(rs.getLong(++col));
				themeDto.setThemeDesc(rs.getString(++col));
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterThemeDAO:getThemeById() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterThemeDAO:getThemeById() : ", e);
		} finally {
			closeConnection();
		}
		
		return themeDto;
	}
	
	/**
	 * Returns all themes stored in the database.
	 *  
	 * @return
	 */
	public List<ERegisterThemeDTO> getAllThemes() {
		List<ERegisterThemeDTO> themes = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(getFields());
			sql.append(" FROM presentation_theme");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			rs = statement.executeQuery();
			
			int column = 0;
			ERegisterThemeDTO theme = null;
			
			while(rs.next()) {
				theme = new ERegisterThemeDTO();
				
				theme.setThemeId(rs.getLong(++column));
				theme.setThemeDesc(rs.getString(++column));
				
				if(themes == null) {
					themes = new ArrayList<ERegisterThemeDTO>();
				}
				
				themes.add(theme);
				
				column = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterThemeDAO:getAllThemes() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterThemeDAO:getAllThemes() : ", e);
		} finally {
			closeConnection();
		}
		
		return themes;
	}
	
	/**
	 * Inserts entry for new theme to the database.
	 * 
	 * @param data
	 * @return
	 */
	public boolean addTheme(ERegisterThemeDTO data) {
		boolean added = false;
		
		try {
			logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO presentation_theme ");
			sql.append("(ThemeDesc) VALUES (?)");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, data.getThemeDesc());
			
			statement.executeUpdate();
			
			added = true;
			
			if(added) {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					data.setThemeId(rs.getLong(1));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterThemeDAO:addTheme() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterThemeDAO:addTheme() : ", e);
		} finally {
			closeConnection();
		}
		
		return added;
	}

	/**
	 * Returns ERegister Theme based on the theme name. Theme name will be
	 * searched case insensitively.
	 * 
	 * @param themeDesc
	 * @return
	 */
	public ERegisterThemeDTO getThemeByDesc(String themeDesc) {
		ERegisterThemeDTO theme = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(getFields());
			sql.append(" FROM presentation_theme WHERE ThemeDesc LIKE ?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setString(1, themeDesc);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			while (rs.next()) {
				theme = new ERegisterThemeDTO();
				
				theme.setThemeId(rs.getLong(++col));
				theme.setThemeDesc(rs.getString(++col));
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterThemeDAO:getThemeByDesc() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterThemeDAO:getThemeByDesc() : ", e);
		} finally {
			closeConnection();
		}
		
		return theme;
	}
}
