/*
 * Created on Dec 21, 2005
 */
package com.aldorsolutions.webfdms.database.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Ranando
 *
 * This Database interface enforces the list of functions that 
 * must be implemented to be considered compatible with this 
 * framework. It is also strongly suggested that 2 static functions 
 * be implemented as simplification functions: 
 * 
 * public static void openDatabase(String uname, String passwd, 
 * 								   String tableName, Table table)
 * 								   throws Throwable;
 * 
 * public static void closeDatabase(MySQLDatabase db) 
 * 								    throws MySQLDatabaseException;
 */
public interface Database {
	public boolean logIn(String uname, String passwd) throws Throwable;
	public boolean logIn(String uname, String passwd, boolean admin) throws Throwable;
	public void connect() throws Throwable;
	public void logOut() throws Throwable;
	public void close() throws Throwable;
	public Table getTable(String tableName)	throws Throwable;
	public ArrayList getTableNames() throws Throwable;
	public void commit() throws Throwable;
	public void rollback() throws Throwable;
	public void finalize() throws Throwable;
	public boolean getUserPermission(String permName);
	public String getUserSetting(String settingName);
	public ResultSet execSQL(String query) throws Throwable;
	public int execQuery(String query) throws Throwable;
	public int getUserID();
	public Connection getConnection();
}
