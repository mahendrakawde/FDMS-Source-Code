package com.aldorsolutions.webfdms.beans.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaProperty;

import com.aldorsolutions.webfdms.admin.dao.DataBaseDAO;
import com.aldorsolutions.webfdms.beans.DbColumn;
import com.aldorsolutions.webfdms.beans.DbTable;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class MetaTablesCache extends Cache {

	private static MetaTablesCache instance = null;

	/**
	 * default CTOR (hidden)
	 */
	protected MetaTablesCache() {
		super();
	}

	/**
	 * the singleton instance
	 * 
	 * @return - the instance of this class
	 */
	public static MetaTablesCache getInstance() {
		if (instance == null) {
			instance = new MetaTablesCache();
		}
		return instance;
	}

	/**
	 * convenience method for creating dynamic filters based table name
	 * 
	 * @param name -
	 *            the table name for the filter
	 * @return - the filter array
	 */
	public Entry makeNameFilter(String tableName) {
		return new Entry("table", tableName);
	}

	@Override
	protected boolean matchFilter(Object obj, Entry[] filter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Collection <Object> updateCache(String dblookup) throws Exception {

		boolean refresh = false;
		List <Object> tableslist = null;
		List <Object> cacheList = (List <Object>) cache.get(dblookup);
		Boolean refreshObj = (Boolean) refreshMap.get(dblookup);
		if (refreshObj != null) {
			refresh = refreshObj.booleanValue();
		}
		if (cacheList == null || refresh) {
			try {
				DynaProperty[] props = new DynaProperty[] { new DynaProperty("dataBaseName", String.class),
						new DynaProperty("tableName", String.class),
						new DynaProperty("colName", String.class), };
				BasicDynaClass dynaClass = new BasicDynaClass("database", null, props);

				List <Object> companylist = MetaDataBaseCache.getInstance().getCache(null, DAO.DB_FDMSUSERS);
				CompanyManagerBean bean = new CompanyManagerBean();

				for (int i = 0; companylist != null && i < companylist.size(); i++) {
					CompanyDTO company = (CompanyDTO) companylist.get(i);
					if ("Y".equalsIgnoreCase(company.getDatabaseStatus()) && 
							company.getDbLookup().equals(dblookup)) {

						String dataBaseName = bean.getDataBaseName(((CompanyDTO) companylist.get(i))
								.getDataURL());
						DataBaseDAO dao = new DataBaseDAO();
						boolean databaseExists = dao.selectDataBase(dataBaseName, DAO.DB_DATABASEBUILDER);
						if (databaseExists) {
							if (tableslist == null) {
								tableslist = new ArrayList <Object> ();
							}
							tableslist.addAll( dao.getAllTablesGeneric(dataBaseName) );
							// tableslist = dao.getAllTables(dataBaseName);
//							for (int j = 0; tableslist != null && j < tableslist.size(); j++) {
//								((DbTable) tableslist.get(j)).setDatabaseName(dataBaseName);
//							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("MetaTablesCache::updateCache Persistence Exception:" + e.getMessage());
				throw e;
			}
		}

		return tableslist;
	}

	/**
	 * Gets all Table Names for a given database name
	 * 
	 * @param dataBaseName
	 *            DataBase name
	 * @param dbLookUp
	 *            JNDI name
	 * @return list of table names
	 */
	public List <String> getAllTables(String dataBaseName, String dbLookUp) {

		getCache(null, dbLookUp);
		List <Object> cacheList = (List <Object>) cache.get(dbLookUp);

		TreeSet <String> set = new TreeSet <String> ();
		
		for (int i = 0; cacheList != null && i < cacheList.size(); i++) {
			DbTable table = (DbTable) cacheList.get(i);
			if (table != null && dataBaseName.equals(table.getDatabaseName())) {
				set.add(table.getName());
			}

		}
		
		List <String> list = new ArrayList <String> (set);
		return (list);
	}

	/**
	 * List of column names for a given database and table name
	 * 
	 * @param dataBaseName
	 *            DataBase Name
	 * @param tableName
	 *            Table name
	 * @param dbLookUp
	 *            JNDI name for datasource
	 * @return List of column names.
	 */
	public List <Object> getAllColumns(String dataBaseName, String tableName, String dbLookUp) {
		List <Object> cacheList = (List<Object>) cache.get(dbLookUp);
		List <Object> list = null;
		for (int i = 0; cacheList != null && i < cacheList.size(); i++) {
			DbTable table = (DbTable) cacheList.get(i);
			if (dataBaseName.equals(table.getDatabaseName()) && tableName.equals(table.getName())) {
				list = table.getColumns();
			}

		}

		// List list = new ArrayList(map.keySet());
		return list;
	}
	/**
	 * Gets Column meta details
	 * @param dataBaseName DataBase Name
	 * @param tableName Table Name
	 * @param columnName Column Name
	 * @param dbLookUp Database lookup name
	 * @return Column meta details
	 */
	public DbColumn getMetaColumn(String dataBaseName, String tableName, String columnName, String dbLookUp) {
		List <Object> cacheList = (List <Object> ) cache.get(dbLookUp);
		DbColumn col = null;
		for (int i = 0; cacheList != null && i < cacheList.size(); i++) {
			DbTable table = (DbTable) cacheList.get(i);
			if (dataBaseName.equals(table.getDatabaseName()) && tableName.equals(table.getName())) {

				List <Object> list = table.getColumns();
				for (int k = 0; list != null && k < list.size(); k++) {
					if (columnName.equals(((DbColumn) list.get(k)).getName())) {
						col = (DbColumn) list.get(k);
						break;
					}
				}

			}
		}
		return col;
	}
	/**
	 * Gets list of columns in a given database and table
	 * @param dataBaseName Database name
	 * @param tableName table name
	 * @param dbLookUp jndi lookup name
	 * @return list of columns
	 */
	public List <Object> getColumns(String dataBaseName, String tableName,String dbLookUp){
		List <Object> cacheList = (List <Object>) cache.get(dbLookUp);
		List <Object>cols = null;
		for (int i = 0; cacheList != null && i < cacheList.size(); i++) {
			DbTable table = (DbTable) cacheList.get(i);
			if (dataBaseName.equals(table.getDatabaseName()) && tableName.equals(table.getName())) {
				cols = table.getColumns();
				break;
			}
		}
		return cols;
	}
	/**
	 * @param refresh
	 *            the refresh to set
	 */
	public void setRefresh(String  dblookup) {
		refreshMap.put(dblookup, new Boolean(true));
		
	}
	/**
	 * @return the refresh
	 */
	public boolean isRefresh(String dblookup) {
		return ((Boolean) refreshMap.get(dblookup)).booleanValue();
	}
}
