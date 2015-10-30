package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ERegisterBackgroundThemeDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class ERegisterBackgroundThemeDAO extends DAO {
	
	private Logger logger = Logger.getLogger(ERegisterBackgroundThemeDAO.class
			.getName());

	/**
	 * 
	 * @param usersLookup
	 */
	public ERegisterBackgroundThemeDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public ERegisterBackgroundThemeDAO(DbUserSession user) {
		super(user);
	}

	private String selectFields() {
		return ("ThemeId, ThemeDesc");
	}
	
	public List<ERegisterBackgroundThemeDTO> getAllBackgroundThemes() {
		List<ERegisterBackgroundThemeDTO> themes = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM eregister_background_themes");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			ERegisterBackgroundThemeDTO themeDto = null;
			
			while(rs.next()) {
				themeDto = new ERegisterBackgroundThemeDTO();
				
				themeDto.setThemeId(rs.getLong(++col));
				themeDto.setThemeDesc(rs.getString(++col));
				
				if(themes == null) {
					themes = new ArrayList<ERegisterBackgroundThemeDTO>();
				}
				
				col = 0;
				
				themes.add(themeDto);
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterBackgroundThemeDAO:getAllBackgroundThemes() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterBackgroundThemeDAO:getAllBackgroundThemes() : ", e);
		} finally {
			closeConnection();
		}
		
		return themes;
	}
	
	public ERegisterBackgroundThemeDTO getThemeById(long themeId) {
		ERegisterBackgroundThemeDTO theme = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM eregister_background_themes WHERE ThemeId=?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, themeId);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			while(rs.next()) {
				theme = new ERegisterBackgroundThemeDTO();
				
				theme.setThemeId(rs.getLong(++col));
				theme.setThemeDesc(rs.getString(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterBackgroundThemeDAO:getThemeById() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterBackgroundThemeDAO:getThemeById() : ", e);
		} finally {
			closeConnection();
		}
		
		return theme;
	}
	
	public boolean addBackgroundTheme(ERegisterBackgroundThemeDTO theme) {
		boolean added = false;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO eregister_background_themes (ThemeDesc) ");
			sql.append(" VALUES (?)");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setString(1, theme.getThemeDesc());
			
			if(statement.executeUpdate() > 0) {
				added = true;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterBackgroundThemeDAO:addBackgroundTheme() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterBackgroundThemeDAO:addBackgroundTheme() : ", e);
		} finally {
			closeConnection();
		}
		
		return added;
		
	}
	
	public ERegisterBackgroundThemeDTO getThemeByDesc(String themeDesc) {
		ERegisterBackgroundThemeDTO theme = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM eregister_background_themes WHERE ThemeDesc LIKE ?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setString(1, themeDesc);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			while (rs.next()) {
				theme = new ERegisterBackgroundThemeDTO();
				
				theme.setThemeId(rs.getLong(++col));
				theme.setThemeDesc(rs.getString(++col));
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ERegisterBackgroundThemeDAO:getThemeByDesc() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ERegisterBackgroundThemeDAO:getThemeByDesc() : ", e);
		} finally {
			closeConnection();
		}
		
		return theme;
	}
}
