package com.aldorsolutions.webfdms.company.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.dao.DataBaseDAO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.company.dao.CompanyDAO;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.PropertyUtil;

/**
 * @author drollins
 * 
 */
public class CompanyManagerBean {

	private Logger logger = Logger.getLogger(CompanyManagerBean.class.getName());

	/**
	 * 
	 */
	public CompanyManagerBean() {
		super();
	}

	public ArrayList <CompanyDTO> getCompanies() {
		return (getCompanies(true));
	}

	public ArrayList <CompanyDTO> getCompanies(boolean activeOnly) {
		return (new CompanyDAO().getCompanies(activeOnly));
	}

	public CompanyDTO getCompany(int companyID) {
		return (new CompanyDAO().getCompany(companyID));
	}

	public CompanyDTO getCompany(String databaseName) {
		return (new CompanyDAO().getCompany(databaseName));
	}

	public boolean addCompany(CompanyDTO company) throws Exception {
		boolean result = true;
		Properties properties = null;
		LocaleDTO locale = null;
		LocationDTO location = null;
		String dbName = getDataBaseName(company.getDataURL());
		try {
			properties = PropertyUtil.getAllProperties(Constants.DATASOURCETEMPLATE);
			if (properties != null) {
				company.setSqlUser((String) properties.get(Constants.USERID));
				company.setSqlPass((String) properties.get(Constants.PASSWORD));
			}

			if (result && dbName != null && !"".equals(dbName)) {
				// creates company
				company.setDatabaseStatus("Y");
				result = (new CompanyDAO().addCompany(company, true));

//				// creates database with tables and default data
//				if (result) {
//					result = createDatabase(dbName);
//				}
//
//				// adds privileges to user
//				if (result) {
//					result = createUser(dbName, company.getSqlUser(), company.getSqlPass());
//				}
//				
//				if (result) {
//					result = createDataSource(company);
//				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			// drop database in this as mysql contains implicit commits.
			throw e;
		}
		/* Code commented by Haranesh for Ticket# 5554 New Setup Issues  (Default Location added)*/
		/*
		// Create default location and locale
		if (result) {
			logger.debug("Successfully created record for Company : " + company.getName());
			locale = new LocaleDTO();
			locale.setCompanyID(company.getCompanyID());
			locale.setName("default");

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 90);
			locale.setAccessExpirationDate(new java.sql.Date(cal.getTime().getTime()));

			LocaleDAO localeDAO = new LocaleDAO();
			localeDAO.addLocale(locale, company.getDbLookup(), true);

			location = new LocationDTO();
			location.setName("default");
			location.setCompanyID(company.getCompanyID());
			location.setLocaleID(locale.getLocaleID());

			LocationDAO locationDAO = new LocationDAO(company.getCompanyID(), 0);
			locationDAO.setDbLookup(company.getDbLookup());
			locationDAO.addLocation(location, company.getDbLookup(), true);
		}
		*/
		if (result) {
			
			/* Code added by Haranesh for Ticket# 5554 New Setup Issues  (Default Location added)*/
			// get the Locale and Location number which are used in user region and userlocations tables
			   LocationDAO locationDAO = new LocationDAO(company.getCompanyID(),0);
			   ArrayList <LocationDTO> locationDTO = locationDAO.getLocations(company.getDbLookup());
			   int localeId=0;
			   int locationId=0;
			   try {
			    if(locationDTO != null)
			    {
			     localeId=Integer.valueOf(""+locationDTO.get(0).getLocaleID());
			     locationId=Integer.valueOf(""+locationDTO.get(0).getLocationID());
			    }
			   }catch(Exception ex){
			    logger.error(ex.getMessage());
			    throw new Exception(ex);
			   }
			   //code complete
			   
			
			// creates a new application user
			UserDTO user = new UserDTO();
			user.setName("admin" + dbName);
			user.setPassword("admin");

			user.setDbUsername(company.getSqlUser());
			user.setDbPassword(company.getSqlPass());
			user.setCompanyID(company.getCompanyID());
			
			//user.setRegionId(locale.getLocaleID());
			user.setRegionId(localeId);
			user.setDbLookup(company.getDbLookup());
			user.setDbUrl(company.getDataURL());
			result = createAppUser(user);
			if (result)
				logger.debug("Succesfully Created User : " + user.getName());
			// added default userlocation
			UserLocationDTO userLocation = new UserLocationDTO();
			//userLocation.setLocationId(location.getLocationID() );
			//userLocation.setRegionId(locale.getLocaleID());
			userLocation.setLocationId(locationId);
			userLocation.setRegionId(localeId);
			
			ArrayList<UserLocationDTO> list = new ArrayList<UserLocationDTO>();
			list.add(userLocation);
			addUserLocations(user.getUserId(), list);

			SecurityConfigDTO security = new SecurityConfigDTO();
			security.setCompanyID(company.getCompanyID());
			security.setAdjacentNumberAllowed(true);

			SecurityManagerBean smBean = new SecurityManagerBean();
			smBean.addSecurityConfig(security, 0, true);

			
		}

		return result;
	}

	public boolean updateCompany(CompanyDTO company) {
		return (new CompanyDAO().updateCompany(company));
	}

	public boolean deleteCompany(int companyID) {
		return (new CompanyDAO().deleteCompany(companyID));
	}

	public boolean createDatabase(String databaseName) {
		return (new DataBaseDAO().createDataBase(databaseName));
	}

	public boolean createUser(String databaseName, String userId, String password) {
		return (new DataBaseDAO().createDbUser(databaseName, userId, password));
	}

	public boolean createAppUser(UserDTO user) throws Exception {
		boolean added = false;
		UserManagerBean bean = new UserManagerBean();
		added = bean.addUser(user, true);
		return added;
	}

	public void addUserLocations(long userId, ArrayList list) throws Exception {

		UserManagerBean bean = new UserManagerBean();
		bean.addUserLocations(userId, list, true);
	}

	/**
	 * Creates new Data source if doesnot exists.
	 * 
	 * @param dbName
	 *            DataBase name.
	 * @return true if successful else false
	 * @throws Exception
	 *             on error condition
	 */
	public boolean createDataSource(CompanyDTO company) throws Exception {
		boolean result = true;
		String dbName = getDataBaseName(company.getDataURL());
		Properties properties = null;
		try {
			properties = PropertyUtil.getAllProperties("newdatasourcesample");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		StringBuffer dataSource = new StringBuffer((String) properties.get("newdatasource"));

		if (dataSource != null) {

			int startIndex = dataSource.indexOf(Constants.USERID);
			dataSource.delete(startIndex, startIndex + Constants.USERID.length());
			dataSource.insert(startIndex, company.getSqlUser());

			startIndex = dataSource.indexOf(Constants.PASSWORD);
			dataSource.delete(startIndex, startIndex + Constants.PASSWORD.length());
			dataSource.insert(startIndex, company.getSqlPass());

			startIndex = dataSource.indexOf(Constants.JNDINAME);
			dataSource.delete(startIndex, startIndex + Constants.JNDINAME.length());
			dataSource.insert(startIndex, company.getDbLookup().substring(
					company.getDbLookup().indexOf(":") + 1));

			startIndex = dataSource.indexOf(Constants.CONNECTIONURL);
			dataSource.delete(startIndex, startIndex + Constants.CONNECTIONURL.length());
			dataSource.insert(startIndex, company.getDataURL());

			try {

				StringBuffer fileName = new StringBuffer();
				if (System.getProperty("jboss.home") != null)
					fileName.append(System.getProperty("jboss.home"));
				else if (System.getProperty("JBOSS_HOME") != null)
					fileName.append(System.getProperty("JBOSS_HOME"));
				else if (System.getenv("JBOSS_HOME") != null) {
					fileName.append(System.getenv("JBOSS_HOME"));
				} else {
					throw new Exception("JBOSS_HOME environment variable not found");
				}

				logger.debug("jboss env: " + System.getenv("JBOSS_HOME"));
				logger.debug("jboss property: " + System.getProperty("JBOSS_HOME"));

				fileName.append(File.separator);
				fileName.append("server");
				fileName.append(File.separator);
				fileName.append("default");
				fileName.append(File.separator);
				fileName.append("deploy");
				fileName.append(File.separator);
				fileName.append("webfdms-");
				fileName.append(dbName);
				fileName.append("-ds.");
//				File file1 = new File("testfile.txt");
				// logger.debug("Default location : " +
				// file1.getAbsolutePath());
				logger.debug(" temp DataSourceFileName : " + fileName.toString());
				File file = new File(fileName.toString() + "tmp");
				File renameToFile = new File(fileName.toString() + "xml");
				
				if ( renameToFile.exists() ) {
					logger.debug(" Datasource already exists : " + renameToFile.getName() );
					return ( result );
				}
				
				// Create file if it does not exist
				boolean success = file.createNewFile();
				if (success) {
					BufferedWriter out = new BufferedWriter(new FileWriter(file));
					out.write(dataSource.toString());
					out.close();
					
					if ( !file.renameTo(renameToFile) ) {
						result = false;
						logger.debug("DataSourceFileName : " + fileName.toString() + "xml");
						file.delete();
					} else {
						logger.debug("Successfully created DataSource for Database : " + dbName);
					}
				} else {
					logger.error(file.getAbsolutePath());
					throw new Exception("DataSource alreadyy exists.");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new Exception(e);
			}
		}
		Thread.currentThread().sleep(5000);
		return result;
	}

	/**
	 * Creates new Data source if doesnot exists.
	 * 
	 * @param dbName
	 *            DataBase name.
	 * @return true if successful else false
	 * @throws Exception
	 *             on error condition
	 */
	public void deleteDataSource(CompanyDTO company) throws Exception {

		String dbName = getDataBaseName(company.getDataURL());
		try {

			StringBuffer fileName = new StringBuffer();
			if (System.getProperty("jboss.home") != null)
				fileName.append(System.getProperty("jboss.home"));
			else if (System.getProperty("JBOSS_HOME") != null)
				fileName.append(System.getProperty("JBOSS_HOME"));
			else if (System.getenv("JBOSS_HOME") != null) {
				fileName.append(System.getenv("JBOSS_HOME"));
			} else {
				throw new Exception("JBOSS_HOME environment variable not found");
			}

			logger.debug("jboss env: " + System.getenv("JBOSS_HOME"));
			logger.debug("jboss property: " + System.getProperty("JBOSS_HOME"));

			fileName.append(File.separator);
			fileName.append("server");
			fileName.append(File.separator);
			fileName.append("default");
			fileName.append(File.separator);
			fileName.append("deploy");
			fileName.append(File.separator);
			fileName.append(dbName);
			fileName.append("-ds.");
			File file1 = new File("testfile.txt");
			logger.debug("Default location : " + file1.getAbsolutePath());
			logger.debug("DataSourceFileName : " + fileName.toString());
			File file = new File(fileName.toString() + "xml");

			// delete file if exists
			if (file.exists()) {
				if (file.delete()) {
					logger.debug("Successfully deleted the DataSource file");
				} else {
					logger.debug("Failed to deleted the DataSource file");
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	public String getDataBaseName(String dataUrl) {
		String dbName = null;
		if (dataUrl != null && !"".equals(dataUrl)) {
			int beginIndex = dataUrl.lastIndexOf("/") + 1;
			dbName = dataUrl.substring(beginIndex);
		}
		return dbName;
	}

}
