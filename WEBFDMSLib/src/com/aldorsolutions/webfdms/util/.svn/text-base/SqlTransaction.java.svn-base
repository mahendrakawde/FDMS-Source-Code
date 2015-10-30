package com.aldorsolutions.webfdms.util;

/**
 * Insert the type's description here.
 * Creation date: (7/20/00 2:48:00 PM)
 * @author: 
 */

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlTransaction {
	private java.sql.Connection connection;
	private java.util.Properties props;
	private java.lang.String url;
	private java.lang.String driver;
/**
 * SqlTransaction constructor comment.
 */
public SqlTransaction() {
	super();
	url = "jdbc:odbc:FDMSj";
	driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	props = new Properties();
	props.put("user",     "");
	props.put("password", "");

}
/**
 * Insert the method's description here.
 * Creation date: (7/20/00 3:19:53 PM)
 */
public void closeConnection() {
	try {
		connection.close();
	}
	catch( SQLException e ) {
	    FdmsException x = new FdmsException(e.getMessage());
	    x.write("Exception closing connection."+ e.getMessage());
	}
	connection = null;
}
/**
 * Connect to database
 * Creation date: (7/20/00 2:50:02 PM)
 * @return java.sql.Connection
 */
public java.sql.Connection getConnection() {
	if( connection == null ) {

		try {
		    Class.forName(driver);
	//	    connection = DriverManager.getConnection("jdbc:pervasive://SOLO9100XL:1583/FDMSEFILES");
		    connection = DriverManager.getConnection(url, props);
		}
		catch( SQLException e ) {
		    FdmsException x = new FdmsException(e.getMessage());
		    x.write("Failed to connect to database."+ e.getMessage());
		    return connection;
		}
		catch( ClassNotFoundException e ) {
		    FdmsException x = new FdmsException(e.getMessage());
		    x.write("Unable to find driver class."+ e.getMessage());
		    return connection;
		}
	}
	return connection;
}
/**
 * Insert the method's description here.
 * Creation date: (7/20/00 2:59:41 PM)
 * @return java.lang.String
 */
java.lang.String getDriver() {
	return driver;
}
/**
 * Insert the method's description here.
 * Creation date: (7/20/00 2:51:41 PM)
 * @return java.lang.String
 */
java.lang.String getUrl() {
	return url;
}
/**
 * Insert the method's description here.
 * Creation date: (7/20/00 2:59:41 PM)
 * @param newDriver java.lang.String
 */
void setDriver(java.lang.String newDriver) {
	driver = newDriver;
}
/**
 * Insert the method's description here.
 * Creation date: (7/20/00 2:51:41 PM)
 * @param newUrl java.lang.String
 */
void setUrl(java.lang.String newUrl) {
	url = newUrl;
}
}
