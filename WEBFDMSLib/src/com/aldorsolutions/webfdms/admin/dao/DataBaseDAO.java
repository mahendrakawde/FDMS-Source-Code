package com.aldorsolutions.webfdms.admin.dao;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbColumn;
import com.aldorsolutions.webfdms.beans.DbTable;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.PropertyUtil;


public class DataBaseDAO extends DAO {

	private Logger logger = Logger.getLogger(DataBaseDAO.class.getName());

	public DataBaseDAO() {
		super();
	}

	public boolean createDataBase(CompanyDTO company) {

		return createDataBase(company.getName());
	}
	/**
	 * Creates the new DataBase
	 * @param dataBaseName DataBase Name
	 * @return true if successful else false
	 */
	public boolean createDataBase(String dataBaseName) {
		boolean added = false;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("create database if not exists `");
			sql.append(dataBaseName);
			sql.append("`;");

			makeConnection(DAO.DB_DATABASEBUILDER, true);
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql.toString());
			
			SQLWarning sqlWarning = statement.getWarnings();
			
			if (i > 0) {
				insertAudit(sql.toString());
				added = true;
				logger.debug("Successfully created New DataBase :" + dataBaseName);
			}
			else if ( sqlWarning != null && sqlWarning.getErrorCode() == Constants.MYSQL_ERROR_DATABASE_EXISTS ) {
				added = true;
			}
			else{
				logger.debug("Failed to created New DataBase :" + dataBaseName);
			}

		} catch (SQLException e) {
			logger.error("SQLException in createDatabase() : ", e);
		} catch (Exception e) {
			logger.error("Exception in createDatabase() : ", e);
		} finally {
			closeConnection();
		}
		if (added){
			selectDataBase(dataBaseName, DAO.DB_DATABASEBUILDER);
			added = createTables();
		}
		return added;
	}

	/**
	 * Drops the new DataBase
	 * @param dataBaseName DataBase Name
	 * @return true if successful else false
	 */
	public boolean dropDataBase(String dataBaseName) {
		boolean removed = false;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("drop database if  exists `");
			sql.append(dataBaseName);
			sql.append("`;");

			makeConnection(DAO.DB_DATABASEBUILDER, false);
			Statement statement = conn.createStatement();
			int i = statement.executeUpdate(sql.toString());

			if (i > 0) {
				insertAudit(sql.toString());
				removed = true;
				logger.debug("Successfully droped the New DataBase :" + dataBaseName);
			}else{
				logger.debug("Failed to drop New DataBase :" + dataBaseName);
			}

		} catch (SQLException e) {
			logger.error("SQLException in dropDatabase() : ", e);
		} catch (Exception e) {
			logger.error("Exception in dropDatabase() : ", e);
		} finally {
			closeConnection();
		}
		
		return removed;
	}

	
	/**
	 * Creates DataBase Table created for New Customer/Company
	 * 
	 * @return true if successful else false
	 */
	public boolean createTables() {
		boolean created = true;

		Properties properties = null;
		try {
			properties = PropertyUtil.getAllProperties("dbasecreate");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		Iterator ite = properties.entrySet().iterator();
		while (ite.hasNext() && created) {
			String sqlString = String.valueOf(((Entry) ite.next()).getValue());

			try {

				makeConnection(DAO.DB_DATABASEBUILDER, true);
				Statement statement = conn.createStatement();
				logger.debug(sqlString);
				int i = -1;
				 i = statement.executeUpdate(sqlString);

				if (i != 0 && created) {
					created = false;
				}

			} catch (SQLException e) {
				logger.error("SQLException in createDatabase() : ", e);
			} catch (Exception e) {
				logger.error("Exception in createDatabase() : ", e);
			} finally {
				closeConnection();
			}
		}
		return created;
	}

	

	/**
	 * Grants all required privileges to default database user on newly created database.
	 * @param dbName DataBase Name
	 * @param userId User Id to grant privileges
	 * @param password User Password
	 * @return true if successful else false
	 */
	public boolean createDbUser(String dbName, String userId, String password) {
		boolean added = false;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("GRANT ALL ON ");
			sql.append(dbName);
			sql.append(".* TO '");
			sql.append(userId);
			sql.append("' IDENTIFIED BY '");
			sql.append(password);
			sql.append("';");

			makeConnection(DAO.DB_DATABASEBUILDER, true);
			Statement statement = conn.createStatement();
			int i = -1;
			i = statement.executeUpdate(sql.toString());

			if (i == 0) {
				insertAudit(sql.toString());
				added = true;
				logger.debug("Successfully added Privileges to user: "  + userId);
			}

		} catch (SQLException e) {
			logger.error("SQLException in createUser() : ", e);
		} catch (Exception e) {
			logger.error("Exception in createUser() : ", e);
		} finally {
			closeConnection();
		}
		return added;
	}
	
	/**
	 * Gets all Table Names for a given DataBase
	 * @param dataBaseName DataBase Name
	 * @return List of Table Names
	 */
	public List <String> getAllTables(String dataBaseName){
		List <String> list = new ArrayList <String> ();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select Table_name from information_schema.tables where table_schema='");
			sql.append(dataBaseName);
			sql.append("';");

			makeConnection(DAO.DB_DATABASEBUILDER);
			statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            while (rs.next()) {
          		list.add(rs.getString(1));
          	}  
            
		} catch (SQLException e) {
			logger.error("SQLException in getAllTables() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getAllTables() : ", e);
		} finally {
			closeConnection();
		}
		 return list;
	}
	
	public List <DbTable> getAllTablesGeneric(String dataBaseName){
		List <DbTable> list = new ArrayList <DbTable> ();
		try {
			makeConnection(DAO.DB_DATABASEBUILDER);
			DatabaseMetaData meta = conn.getMetaData();
			String[] names = {"TABLE"}; 
			
			ResultSet tableNames = meta.getTables(dataBaseName, null, "%", names);
			while (tableNames.next()) {
				   DbTable table = new DbTable(tableNames.getString("TABLE_NAME"));
				     // Set the databse name so the table object has a database that it belongs to.
				   table.setDatabaseName(dataBaseName);
				   readTableColumns(meta,dataBaseName, table);
				   list.add(table); 
			}
		    
		} catch (SQLException e) {
			logger.error("SQLException in getAllTables() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getAllTables() : ", e);
		} finally {
			closeConnection();
		}
		 return list;
	}
	
	public void readTableColumns(DatabaseMetaData metadata,String dataBaseName, DbTable table){
		StringBuilder info = new StringBuilder();
		info.append("TableName - " + table.getName() + " : ");
		try{
			ResultSet columns = metadata.getColumns(dataBaseName, null, table.getName(), "%");
			while (columns.next()) {
				  String columnName = columns.getString("COLUMN_NAME");
				  String datatype = columns.getString("TYPE_NAME");
				  int datasize = columns.getInt("COLUMN_SIZE");
				  int digits = columns.getInt("DECIMAL_DIGITS");
				  int nullable = columns.getInt("NULLABLE");
				  boolean isNull = (nullable == 1);
				  DbColumn newColumn = new DbColumn(columnName, datatype, datasize, digits, isNull);
				  table.addColumn(newColumn);
				  info.append(columnName+", ");
			}
		
		} catch (SQLException e) {
			logger.error("SQLException in readTableColumns() : ", e);
		} catch (Exception e) {
			logger.error("Exception in readTableColumns() : ", e);
		} finally {
			
		}
		logger.debug(info.toString());
	}
	
	
	
	
	/**
	 * Gets Column names in the table
	 * @param dataBaseName Database Name
	 * @param tableName Table Name
	 * @return List of column names
	 */
	public List <String> getTableMeta(String dataBaseName, String tableName){
	
		List <String> list = new ArrayList <String> ();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from `");
			sql.append(tableName);
			sql.append("`;");
			makeConnection(DAO.DB_DATABASEBUILDER);
			statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            for(int i=1; meta != null && i <= meta.getColumnCount(); i++){
            	list.add((String)(meta.getColumnName(i)));
            } 
            
		} catch (SQLException e) {
			logger.error("SQLException in getAllTables() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getAllTables() : ", e);
		} finally {
			closeConnection();
		}
		 return list;
	}

	/**
	 * Gets Column types in the table
	 * @param dataBaseName Database Name
	 * @param tableName Table Name
	 * @return List of column names
	 */
	public List <String> getTableMetaType(String dataBaseName, String tableName){
	
		List <String> list = new ArrayList <String> ();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from `");
			sql.append(tableName);
			sql.append("`;");
			makeConnection(DAO.DB_DATABASEBUILDER);
			statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            for(int i=1; meta != null && i <= meta.getColumnCount(); i++){
            	list.add((String)(meta.getColumnName(i)) +":"+meta.getColumnTypeName(i));
            	
            } 
            
		} catch (SQLException e) {
			logger.error("SQLException in getAllTables() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getAllTables() : ", e);
		} finally {
			closeConnection();
		}
		 return list;
	}
	
}
