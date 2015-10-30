package com.aldorsolutions.webfdms.database;

/**
 * The DatabaseTransaction class basically servers as a wrapper
 * around the JDBC Connection class.  It allows peers access to
 * the JDBC Connection in addition to triggering commits and rollbacks
 * as needed by the persistence package.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.UtilSingleton;


public class DatabaseTransaction extends Transaction {
    
    private Logger logger = Logger.getLogger(DatabaseTransaction.class.getName());
    
    /****************** Instance attributes *****************/
    private Connection connection = null;
    private Properties props      = null;
    private String     url        = null;
    private String     jndiLookup = null;
    //	private static final String driver = "com.pervasive.jdbc.v2.Driver";
//    public static final String driver = UtilSingleton.getInstance().getProperty("db.driver");
    //	public static final String driver = "com.mysql.jdbc.Driver";
    private boolean readOnly=false;
//    /********************* Constructors *********************/
    
    /**
     * @deprecated
     */
    public DatabaseTransaction(String u, Properties p, DbUserSession user, boolean skip) throws PersistenceException {
        
    	this ( u, p, user.getId(), user.getCompanyID(), user.getRegion(), skip );
        
    }
    
    /**
     * Creates a DatabaseTransaction using the specified URL and
     * properties.
     * @param u the JDBC URL for this DatabaseTransaction
     * @param p the properties, generally containing user name and password
     * @exception PersistenceException An error occurred
     * finding a JDBC driver for the URL
     * @deprecated
     */
    public DatabaseTransaction(String u, Properties p, int userID, 
    		int companyID, int localeID, boolean skip) throws PersistenceException {
            
        super();
        url = u;
        props = p;
        try {
//            Class.forName(driver);
            connection = DriverManager.getConnection(url, props);
            // comment out setAutoCommit() for mSQL
            // which does not handle transaction logic
            connection.setAutoCommit(false);
        } catch( SQLException e ) {
            logger.error("SQL Exception in DatabaseTransaction()", e);
            throw new PersistenceException(e);
//        } catch( ClassNotFoundException e ) {
//            logger.error("Unable to find driver class: ", e);
//            connection=null;
        }
        
        setTransactionUserId(userID);
        setTransactionCompanyId(companyID);
        setTransactionLocaleId(localeID);
        
    }
    
    /**
     * 
     * @param user
     * @throws PersistenceException
     */
    public DatabaseTransaction(DbUserSession user) throws PersistenceException {
        
    	this (user.getDbLookup(), user.getId(), user.getCompanyID(), user.getRegion());
    	
    }
    
    /**
     * Creates a DatabaseTransaction using the specified URL and
     * properties.
     * @param u the JDBC URL for this DatabaseTransaction
     * @param p the properties, generally containing user name and password
     * @exception PersistenceException An error occurred
     * finding a JDBC driver for the URL
     */
    public DatabaseTransaction(String jndiLookup, DbUserSession user) throws PersistenceException {
        
    	this (jndiLookup, user.getId(), user.getCompanyID(), user.getRegion());
    	
    }
    
    /**
     * Creates a DatabaseTransaction using the specified URL and
     * properties.
     * @param u the JDBC URL for this DatabaseTransaction
     * @param p the properties, generally containing user name and password
     * @exception PersistenceException An error occurred
     * finding a JDBC driver for the URL
     */
    public DatabaseTransaction(String jndiLookup, int userID, 
    		int companyID, int localeID) throws PersistenceException {
            
        super();
        
        this.jndiLookup = jndiLookup;
        try {
            connection = UtilSingleton.getInstance().getConnectionFromCache(jndiLookup);
            connection.setAutoCommit(false);
        } catch (Exception e) {
        	logger.error("SQL Exception in DatabaseTransaction()", e);
        }
        
        if (connection == null) {
        	logger.error("Unable to create connection: " + jndiLookup);
        }
        
        setTransactionUserId(userID);
        setTransactionCompanyId(companyID);
        setTransactionLocaleId(localeID);
    }
    
    /**
     * Upon a successful JDBC rollback, this will trigger the
     * inherited abort() method.
     * @see imaginary.persist.Transaction#abort
     */
    public synchronized void abort() {
        try {
            connection.rollback();
            
        }
        catch( SQLException e ) {
        }
    }
    /**
     * End a driver connection
     * Creation date: (10/29/2001 3:43:29 PM)
     */
    public void closeConnection() {
        
        try {
            if (connection != null){
            	// Chad 4/28/08 When we get here we are closing the connection
            	// We are going to do a rollback here becuase if there was 
            	// anything to commit it would have already been commited.
            	if (connection.isClosed() == false || connection.getAutoCommit() == false) {
            		connection.rollback();
            	}
            	
            	if (connection.isClosed() == false) {
              connection.close();
            	}
            }
        }
        catch( SQLException e ) {
            logger.error("Error in closeConnection() : ", e);
        }
        connection = null;
        
    }
    /**
     * Upon a successful JDBC commit, this will call the inherited
     * commit in order to let all committed objects know the commit
     * was successful.
     * @exception imaginary.persist.PersistenceException An error occurred
     * attempting to commit the transaction
     */
    public synchronized void commit() throws PersistenceException {
        try {
            connection.commit();
        } catch( SQLException e ) {
            abort();
            throw new PersistenceException(e);            
        }
    }
    /**************** Attribute Accessors *******************/
    /**
     * @return the JDBC Connection for this DatabseTransaction
     */
    public synchronized Connection getConnection() throws PersistenceException {
    	
    	if ( connection == null ) {
    		if (jndiLookup != null) {

				try {
					connection = UtilSingleton.getInstance().getConnectionFromCache(jndiLookup);
					connection.setAutoCommit(false);
				} catch (Exception e) {
					throw new PersistenceException(e);
				}
			} else if (url != null && props != null) {

				try {
					connection = DriverManager.getConnection(url, props);
					connection.setAutoCommit(false);
				} catch (SQLException e) {
					throw new PersistenceException(e);
				}
			}        	
    	}
        
        return connection;
        
    }
    
    /**
	 * Gets an instance of a Transaction subclass based on the URL taken from a
	 * DbUser object.
	 * 
	 * @return a new Transaction instance
	 */
    static public Transaction getTransaction(DbUserSession user) throws PersistenceException {
        return ( getTransaction (user, user.getDbLookup() ) );
    }
    
    static public Transaction getTransaction(DbUserSession user, String jndiLookup) throws PersistenceException {
    	return ( getTransaction (user, jndiLookup, user.getId(), user.getCompanyID(), user.getRegion(), user.getSecurityViewOnly() != 0 ) );
    }
    
    static public Transaction getTransaction(DbUserSession user, String jndiLookup, int userId, 
    		int companyId, int localeID) throws PersistenceException {
    	return getTransaction ( user, jndiLookup, userId, companyId, localeID, false);
    }
    
    static public Transaction getTransaction(DbUserSession user, String jndiLookup, int userId, 
    		int companyId, int localeID, boolean readOnly) throws PersistenceException {
        
    	if (user == null) {
        	throw new PersistenceException("User session does not exist");
        }
        
        DatabaseTransaction t = new DatabaseTransaction(jndiLookup, userId, companyId, localeID);
        
        t.setReadOnly(readOnly);

        t.setTransactionUserId(userId);
        t.setTransactionCompanyId(companyId);
        t.setTransactionLocaleId(localeID);
        
        if (t == null) throw new PersistenceException("Unable to access database");
        return t;
        
    }
    
    
    
//    /**
//     * Gets an instance of a Transaction subclass based on the URL
//     * taken from a passed URL and properties.
//     * @return a new Transaction instance
//     */
//    static public Transaction getTransaction(String dbUrl, 
//    		Properties dbProps, DbUserSession user, boolean disable) throws PersistenceException {
//    	
//    	Transaction t = getTransaction (dbUrl, dbProps, user.getSecurityViewOnly(), 
//    			user.getId(), user.getCompanyID(), user.getRegion(), disable );
//    	
//    	return ( t );
//    }
//    
//
//    /**
//     * Gets an instance of a Transaction subclass based on the URL
//     * taken from a passed URL and properties.
//     * @return a new Transaction instance
//     */
//    static public Transaction getTransaction(String dbUrl, Properties dbProps, 
//    		int readOnly, int userID, int companyID, int localeID, boolean disable) throws PersistenceException {
//    	
//    	if (dbProps == null){
//    		dbProps = new java.util.Properties();
//    		dbProps.put("user",     "");
//    		dbProps.put("password", "");
//    	}
//    	
//    	if (dbUrl==null) {
//    		dbUrl = new String("jdbc:odbc:WebFdmsUsers");
//    	}
//    	
//    	DatabaseTransaction t = new DatabaseTransaction(dbUrl, dbProps, userID, companyID, localeID);
//        t.setReadOnly(readOnly!=0);
//        
//        if (t == null) {
//        	throw new PersistenceException("Unable to access database");
//        }
//        
//        t.setTransactionUserId(userID);
//        t.setTransactionCompanyId(companyID);
//        t.setTransactionLocaleId(localeID);
//        
//        return t;
//        
//    }
    
    
    /**
     * Insert the method's description here.
     * Creation date: (9/26/2002 11:38:49 AM)
     * @return boolean
     */
    public boolean isReadOnly() {
        return readOnly;
    }
    /**
     * Saves all of the objects associated with this object using
     * database persistence.
     * @exception imaginary.persist.PersistenceException An error occurred
     * during save.
     */
    public synchronized void save() throws PersistenceException {
        // If user is "read only" then throw exception
        if (isReadOnly()){
            Exception e = new Exception("You are not authorized to SAVE information.");
            throw new PersistenceException("You are not authorized to SAVE information.",e);
        }
        
        connection = getConnection();
        
        try {
            super.save();
        }
        finally {
            try {
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException e ) {
                logger.error("Error : ", e);
            }
            connection = null;
        }
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/26/2002 11:38:49 AM)
     * @param newReadOnly boolean
     */
    public void setReadOnly(boolean newReadOnly) {
        readOnly = newReadOnly;
    }
}
