package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ServiceScreenThemeDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class ServiceScreenThemeDAO extends DAO {
	
	private Logger logger = Logger.getLogger(ServiceScreenThemeDAO.class
			.getName());

	/**
	 * 
	 * @param usersLookup
	 */
	public ServiceScreenThemeDAO(String usersLookup) {
		super(usersLookup);
	}

	/**
	 * 
	 * @param user
	 */
	public ServiceScreenThemeDAO(DbUserSession user) {
		super(user);
	}

	private String selectFields() {
		return ("ThemeId, ThemeDesc");
	}
	
	public List<ServiceScreenThemeDTO> getAllServiceScreenThemes() {
		List<ServiceScreenThemeDTO> themes = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM service_screen_themes");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			ServiceScreenThemeDTO themeDto = null;
			
			while(rs.next()) {
				themeDto = new ServiceScreenThemeDTO();
				
				themeDto.setThemeId(rs.getLong(++col));
				themeDto.setThemeDesc(rs.getString(++col));
				
				if(themes == null) {
					themes = new ArrayList<ServiceScreenThemeDTO>();
				}
				
				col = 0;
				
				themes.add(themeDto);
			}
		} catch (SQLException e) {
			logger.error("SQLException in ServiceScreenThemeDAO:getAllBackgroundThemes() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ServiceScreenThemeDAO:getAllBackgroundThemes() : ", e);
		} finally {
			closeConnection();
		}
		
		return themes;
	}
	
	public ServiceScreenThemeDTO getThemeById(long themeId) {
		ServiceScreenThemeDTO theme = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM service_screen_themes WHERE ThemeId=?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setLong(1, themeId);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			while(rs.next()) {
				theme = new ServiceScreenThemeDTO();
				
				theme.setThemeId(rs.getLong(++col));
				theme.setThemeDesc(rs.getString(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in ServiceScreenThemeDAO:getThemeById() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ServiceScreenThemeDAO:getThemeById() : ", e);
		} finally {
			closeConnection();
		}
		
		return theme;
	}
	
	public boolean addServiceScreenTheme(ServiceScreenThemeDTO theme) {
		boolean added = false;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO service_screen_themes (ThemeDesc) ");
			sql.append(" VALUES (?)");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setString(1, theme.getThemeDesc());
			
			if(statement.executeUpdate() > 0) {
				added = true;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ServiceScreenThemeDAO:addServiceScreenTheme() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ServiceScreenThemeDAO:addServiceScreenTheme() : ", e);
		} finally {
			closeConnection();
		}
		
		return added;
		
	}
	
	public ServiceScreenThemeDTO getThemeByDesc(String themeDesc) {
		ServiceScreenThemeDTO theme = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ").append(selectFields());
			sql.append(" FROM service_screen_themes WHERE ThemeDesc LIKE ?");
			
			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			
			statement.setString(1, themeDesc);
			
			rs = statement.executeQuery();
			
			int col = 0;
			
			while (rs.next()) {
				theme = new ServiceScreenThemeDTO();
				
				theme.setThemeId(rs.getLong(++col));
				theme.setThemeDesc(rs.getString(++col));
				
				col = 0;
			}
		} catch (SQLException e) {
			logger.error("SQLException in ServiceScreenThemeDAO:getThemeByDesc() : ", e);
		} catch (Exception e) {
			logger.error("Exception in ServiceScreenThemeDAO:getThemeByDesc() : ", e);
		} finally {
			closeConnection();
		}
		
		return theme;
	}
	
}
