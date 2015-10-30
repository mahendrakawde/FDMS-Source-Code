/*
 * Created on Dec 14, 2005
 *
 * This file is a replaceable module in the new FDMS database architecture. The
 * basic idea is to have %DBName%Database objects be swappable units so that
 * the actual database management can be database implementation independant.
 */
package com.aldorsolutions.webfdms.database.framework;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.aldorsolutions.webfdms.util.UtilSingleton;

/**
 * @author Ranando
 *
 * This class represents the connection to a MySQL database. Be
 * sure to call logIn before calling any of its get*() functions.
 * It's actually just easier to call the static openDatabase() and
 * closeDatabase() functions. That's the preferred way of getting
 * initial access to a table. All subsequent tables should be
 * accessed by calling getTable() directly.
 */
public class MySQLDatabase implements Database {
//    private Logger logger = Logger.getLogger(MySQLDatabase.class.getName());
	private boolean loggedIn = false;
	//private Properties dbProps = null;
//	private String dbUrl = null;
//	private String loginUrl = null;
	private String dbDriver = null;
	private String dbLookup = null;
	private Connection dbConnection;
	private HashMap userPermissions;
	private boolean useLoginUrl = false;
	private int userID = -1;
	
	public static Database openDatabase(String uname, String passwd, String tableName, Table table) 
						   throws MySQLDatabaseException, TableException {
	MySQLDatabase db = new MySQLDatabase();
	db.logIn(uname, passwd);
	table = db.getTable(tableName);
	return db;
}

	public static Database openDatabase(String uname, String passwd, boolean admin, String tableName, Table table) 
						   throws MySQLDatabaseException, TableException {
	MySQLDatabase db = new MySQLDatabase();
	db.logIn(uname, passwd, admin);
	table = db.getTable(tableName);
	return db;
}

	public static void closeDatabase(MySQLDatabase db) throws MySQLDatabaseException {
		db.close();
	}
	
	public MySQLDatabase() {
		/* Set up the info to make a connection. */
//		dbUrl = UtilSingleton.getInstance().getProperty("db.url");
//		loginUrl = UtilSingleton.getInstance().getProperty("db.users.url");
//		dbDriver = UtilSingleton.getInstance().getProperty("db.driver");
		//String user = UtilSingleton.getInstance().getProperty("db.username");
		//String pass = UtilSingleton.getInstance().getProperty("db.password");
		dbLookup = UtilSingleton.getInstance().getUserDBLookup();
//		dbProps = new Properties();
//		dbProps.put("user", user);
//		dbProps.put("password", pass);
		
		/*
		 * If there are missing entries in our config file
		 * then assume default values for them...
		 *//*
		if ((dbUrl == null) || (dbUrl.trim().length() < 1))
			dbUrl = "jdbc:odbc:///WebFdmsData";
		if ((loginUrl == null) || (loginUrl.trim().length() < 1))
			loginUrl = "jdbc:odbc:///WebFdmsUsers";
		if ((dbDriver == null) || (dbDriver.trim().length()<1))
			dbDriver = "org.gjt.mm.mysql.Driver";*/
	}
	
	public MySQLDatabase(String dbLookup) {
		/* Set up the info to make a connection. */
		//dbUrl = dataurl;
		this.dbLookup = dbLookup; 
//		dbDriver = UtilSingleton.getInstance().getProperty("db.driver");
//		String user = UtilSingleton.getInstance().getProperty("db.username");
//		String pass = UtilSingleton.getInstance().getProperty("db.password");
//		dbProps = new Properties();
//		dbProps.put("user", user);
//		dbProps.put("password", pass);
//		dbLookup = UtilSingleton.getInstance().getProperty("db.users.jndi");
		
		// If we're using this constructor, then this is a safe bet.
		loggedIn = true;
	}
	
	public boolean logIn(String uname, String passwd) throws MySQLDatabaseException {
		Connection con;
		
		try {
			/* You dufus! Why are you logging in twice? */
			if (loggedIn)
				throw new MySQLDatabaseException("Log-in attempted while logged in");
			
			/* Hey moron! Can't log-in without a username AND password! */
			if ((uname == null) ||
				(uname.trim().length() < 1) ||
				(passwd == null) ||
				(passwd.trim().length() < 1)) {
				throw new MySQLDatabaseException("Zero length login parameter detected: '" + uname + "', '" + passwd + "'");
			}

			/* Open a connection to the MySQL server. */
            con = UtilSingleton.getInstance().getConnectionFromCache(dbLookup);
            
//			con = DriverManager.getConnection(loginUrl, dbProps);
			/* AutoCommit is EVIL!!! */
			con.setAutoCommit(false);
			/* Only allow transactions to see committed data, but all of it. */
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			/* Grab the data. */
			Statement sql = con.createStatement();
			ResultSet rs = sql.executeQuery("Select * From usersecurity where Name='" + uname + "'");
			/* Jump to the top of the list... */
			rs.first();
			/* ... and keep going until there are none left. */
			while (!rs.isAfterLast()) {
				/* RULE #1: Log the attempt to access this user. */
				sql.execute("Insert Into accesslog");
				/* Is there a user with the expected password? */
				if (rs.getString("Password").equals(passwd)) {
					ResultSetMetaData rmd = rs.getMetaData();
					userPermissions = new HashMap();
					for (int i=1;i<=rmd.getColumnCount(); i++) {
						String cname = rmd.getColumnName(i);
						if (cname.equalsIgnoreCase("dbLookup")) {
							dbLookup = rs.getString(i);
						} else if (cname.equalsIgnoreCase("userid")) {
							userID = rs.getInt(i);
						} else {
							userPermissions.put(rmd.getColumnName(i), rs.getString(i));
						}
					}
					
					loggedIn = true;
					break;
				}
				rs.next();
			}
			if ( useLoginUrl)
				dbConnection = con;
			else {

	            try {
	                if (con != null) con.close();
	            } catch (SQLException e) {
	            }

				connect();
			}
		} catch (MySQLDatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new MySQLDatabaseException("Failed opening a connection to datbase");
		} catch (Throwable e) {
			throw new MySQLDatabaseException("Unspecified error in connection phase");
		}
		
		return loggedIn;
	}
	
	public void connect() throws MySQLDatabaseException  {
		/* Load the driver and get access to WebFDMS... */
		try {
			/* Load the MySQL driver. */
			Class.forName(dbDriver);
			/* Open a connection to the MySQL server. */
			//dbConnection = DriverManager.getConnection(dbUrl, dbProps);
            dbConnection = UtilSingleton.getInstance().getConnectionFromCache(dbLookup);
			
			/* AutoCommit is EVIL!!! */
			dbConnection.setAutoCommit(false);
			/* Only allow transactions to see committed data, but all of it. */
			dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (SQLException e) {
			throw new MySQLDatabaseException("Failed opening a connection to database " + dbLookup);
		} catch (ClassNotFoundException e) {
			throw new MySQLDatabaseException("Failed loading driver: " + dbDriver);
		} catch (Throwable e) {
			throw new MySQLDatabaseException("Unspecified error in connection phase");
		}
	}

	public boolean logIn(String uname, String passwd, boolean admin) throws MySQLDatabaseException {
		useLoginUrl = admin;
		return logIn(uname, passwd);
	}

	public void logOut() throws MySQLDatabaseException {
		if (loggedIn) {
			loggedIn = false;
			userPermissions = null;
		}
		else
		/* Idiot! Why are you logging out when you aren't logged in? */
			throw new MySQLDatabaseException("Log-out attempted while not logged-in");			
	}
	
	public void close() throws MySQLDatabaseException {
		if (loggedIn)
			logOut();
		
		try {
			/* Errors from this rollback don't matter. When the connection is closed,
			 * all remaining transaction data should be discarded anyway.
			 */
			try {
				dbConnection.rollback();
			} catch (Throwable e) {}
			
			dbConnection.close();
		} catch (SQLException e) {
			throw new MySQLDatabaseException("Failed closing database connection");
		} catch (Throwable e) {
			throw new MySQLDatabaseException("Unspecified error while closing database", e);
		}
	}
	
	public Table getTable(String tableName)
		throws MySQLDatabaseException, TableException {
		Table table = null;
		
		if (loggedIn) {
			if (getTableNames().contains(tableName)) {
				table = new Table(dbConnection, tableName);
			}
		}
		
		return table;
	}
	
	public ArrayList getTableNames() throws MySQLDatabaseException {
		ArrayList list = null;
		
		if (loggedIn) {
			try {
				DatabaseMetaData dbmd = dbConnection.getMetaData();
				//Statement sql = dbConnection.createStatement();
				//ResultSet table = sql.executeQuery("Show Tables");
				String[] types = new String[2];
				types[0] = "TABLE";
				types[1] = "VIEW";
				ResultSet table = dbmd.getTables(null, null, null, types);
				list = new ArrayList();
				while (!table.isAfterLast()) {
					list.add(table.getString(0));
				}
			} catch (SQLException e) {
				throw new MySQLDatabaseException("Failed fetching table names");
			} catch (Throwable e) {
				throw new MySQLDatabaseException("Unspecified error while fetching table names", e);
			}
		}
		
		return list;
	}
	
	public void commit() throws MySQLDatabaseException {
		try {
			dbConnection.commit();
		} catch (SQLException e) {
			throw new MySQLDatabaseException("Failed committing changes to database");
		} catch (Throwable e) {
			throw new MySQLDatabaseException("Unspecified error while commiting changes", e);
		}
	}
	
	public void rollback() throws MySQLDatabaseException {
		try {
			dbConnection.rollback();
		} catch (SQLException e) {
			throw new MySQLDatabaseException("Failed reversing changes to database");
		} catch (Throwable e) {
			throw new MySQLDatabaseException("Unspecified error while reversing changes", e);
		}
	}
	
	public void finalize() throws MySQLDatabaseException {
		close();
	}
	
	public boolean getUserPermission(String permName) {
		boolean b = false;
		String permVal = null;
		if (userPermissions.containsKey(permName))
			permVal = userPermissions.get(permName).toString();
		
		if ((permVal != null) &&
			(permVal.toLowerCase() != "n") &&
			(permVal.toLowerCase() != "false") &&
			(permVal != "0") &&
			(permVal != " ") &&
			(permVal != "")) {
			b = true;
		}
		
		return b;
	}
	
	public String getUserSetting(String settingName) {
		/* 
		 * TODO  Don't forget to implement this when we get a user 
		 * settings table! 
		 */
		return null;
	}
	
	public ResultSet execSQL(String query) throws MySQLDatabaseException {
		ResultSet retval = null;
		
		if (loggedIn) {
			try {
				Statement sql = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
															 ResultSet.CONCUR_UPDATABLE);
				retval = sql.executeQuery(query);
			} catch(SQLException e) {
				throw new MySQLDatabaseException(e);
			} catch(Throwable e) {
				throw new MySQLDatabaseException(e);				
			}
		}
		return retval;
	}
	
	public int execQuery(String query) {
		/*
		 * TODO  Implement a function here that can handle editing
		 * queries.
		 */
		return 0;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public Connection getConnection() {
		return dbConnection;
	}
}
