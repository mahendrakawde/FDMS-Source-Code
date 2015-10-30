package com.aldorsolutions.webfdms.database;

/**
 * The DatabasePeer class implements the persistence methods from
 * PersistentPeer and performs the actual database access for
 * individual persistent objects.  Of course, at this level it is impossible
 * to know everything about the application in order to isolate database
 * access completely.  Applications requiring database persistence
 * should extend this class and implement the abstract methods that
 * allow an application to formulate the SQL.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import org.apache.log4j.Logger;

public abstract class DatabasePeer implements PersistentPeer {
    
    Logger logger = Logger.getLogger(DatabasePeer.class.getName());
    /**
     * Subclasses must implement this method to build an INSERT
     * statement for the specified persistent object.
     * @param t Transaction with our connection
     * @param p the Persistent that is being inserted into the database
     * @return the SQL Prepared Statement to be sent to the database
     */
    protected abstract java.sql.PreparedStatement getInsertSql(
    		DatabaseTransaction t, 
            Persistent p)throws PersistenceException;
    /**
     * Subclasses must implement this method to build a DELETE
     * statement for the specified persistent object.
     * @param t our Transaction with the connection
     * @param p the Persistent that is being deleted from the database
     * @return the SQL DELETE Prepared Statement
     */
    
    protected abstract java.sql.PreparedStatement getRemoveSql(
    		DatabaseTransaction t, 
            Persistent p)throws PersistenceException;
    /**
     * Subclasses must implement this method to build a SELECT
     * statement that will restore only the named persistent object.
     * At the time it gets passed to this method, the persistent
     * object has only its ID set.  All other values are awaiting
     * a restore operation.
     * @param p the Persistent that is being restored from the database
     * @return the SQL SELECT statement
     */
    
    protected abstract String getRestoreSql(Persistent p);
    /**
     * Subclasses must implement this method to build an UPDATE
     * statement that will update the specified persistent object.
     * @param t the DatabaseTransaction encapsulating our connection
     * @param p the Persistent object to be updated
     * @return the SQL UPDATE PreparedStatement
     */
    
    protected abstract java.sql.PreparedStatement getUpdateSql(
    		DatabaseTransaction t, 
            Persistent p)
        throws PersistenceException;
    
    /**
     * This method implements the insert persistence operation
     * as prescribed by the PersistentPeer interface.  It asks
     * its subclasses for the proper SQL and then triggers
     * a save using that SQL statement.
     * @param p the persistent object to be inserted
     * @param t the DatabaseTransaction to use for that insert
     * @exception imaginary.persist.PersistenceException An error occurred
     * in saving the object to the database.
     */    
    public void insert(Persistent p, 
            Transaction t) throws PersistenceException {
        
        int autoinc = 0;
        java.sql.PreparedStatement pstmt=null;
        
        try {
            pstmt = getInsertSql((DatabaseTransaction)t,p);
            pstmt.executeUpdate(); // Execute the SQL
            
            ResultSet rs = pstmt.getGeneratedKeys();
            // ResultSet rs = stmt.executeQuery("SELECT @@IDENTITY");
            
            if (rs.next()) {
                autoinc = rs.getInt(1);
                p.setId(autoinc);
            }
            
        } catch( SQLException e ) {
            // logger.error("SQLException in DatabasePeer.save: ", e);
            throw new PersistenceException("PersistentObj:" + p.getClass().getName() + " Message: " + e.getMessage(), e);
        }
        finally {
        	try {
        		if ( pstmt != null ) {
        			pstmt.close();
        			pstmt = null;
        		}
        	} catch ( SQLException e ){
        		throw new PersistenceException(e);
        	}
        }
        
    }
    
    /**
     * This method implements the remove persistence operation
     * as prescribed by the PersistentPeer interface.  It asks
     * its subclasses for the proper SQL and then triggers
     * a save using that SQL statement.
     * @param p the persistent object to be removed
     * @param t the DatabaseTransaction to use for that removal
     * @exception imaginary.persist.PersistenceException An error occurred
     * in removing the object from the database.
     */
    public void remove(Persistent p, Transaction t)
    throws PersistenceException {
        save((DatabaseTransaction)t, getRemoveSql((DatabaseTransaction)t,p));
    }
    /**
     * This method implements the restore persistence operation
     * as prescribed by the PersistentPeer interface.  It asks
     * its subclasses for the proper SQL and then triggers
     * a save using that SQL statement.
     * @param p the persistent object to be restored
     * @param trans the DatabaseTransaction to use for restoring the object
     * @exception imaginary.persist.PersistenceException An error occurred
     * in restoring the object from the database.
     */
    public void restore(Persistent p, Transaction trans)
        throws PersistenceException {
        
        String sql = getRestoreSql(p); // get the restore SQL
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        ResultSetMetaData meta = null;
        String err = null;
        
        try {
            Hashtable h = new Hashtable(); // store the values here
            
            // Get the connection from the DatabaseTransaction
            connection = ((DatabaseTransaction)trans).getConnection();
            // Create a JDBC statement from the connection
            statement = connection.createStatement();
            // Execute the SQL
            results = statement.executeQuery(sql);
            // Get the meta data
            meta = results.getMetaData();
            
            // Make sure we got a row!
            if(!results.next()) throw new PersistenceException("No rows found!");             
            
            // For each column in the result set
            for(int i=1; i<=meta.getColumnCount(); i++) {
                Object ob = null;
                
                try {
                	ob = results.getObject(i);
                } catch ( SQLException sqe ) {
                	logger.debug("Error retrieving object; Object = " + meta.getColumnLabel(i) + 
                			"; msg = " + sqe.getMessage() + "; SQL State = " + sqe.getSQLState() );
                }
                
                // Objects (strings?) without values were returned as "null"
                // So, I replaced with empty string objects (RD)
                if (ob==null){
                    ob= new String("");
                }
                // Put the value in the Hashtable using the column name
                // as a key
                h.put(meta.getColumnLabel(i), ob);
            }
            // Call the restore from Hashtable method in the Persistent
            p.restore(trans, h);

            
        } catch( SQLException e ) {
            err = e.toString();
            logger.error("SQLException in restore() : " + err + "; SQL State: " + e.getSQLState() + 
            		" Persistent: " + p.getClass().getName() );
            
            throw new PersistenceException(e);
        } finally {

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException se) {
                    logger.error("Error in closing statement : ", se);
                    err = se.toString();
                }                
            }  
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    logger.error("Error in closing statement : ", se);
                    err = se.toString();
                }
            }
                                
        }
                
        if (err != null) throw new PersistenceException(err);
    }
    
    /**
     * This method restores a persistent AND locks the table row.
     * It asks its subclasses for the proper SQL  and appends "FOR UPDATE"
     * It then triggers a save using that SQL statement.
     * @param p the persistent object to be restored
     * @param trans the DatabaseTransaction to use for restoring the object
     * @exception imaginary.persist.PersistenceException An error occurred
     * in restoring the object from the database.
     */
    public void restoreWithLock(Persistent p, Transaction trans)
        throws SQLException, PersistenceException {
            
        // Get normal restore SQL and add "FOR UPDATE" to lock the row
        String sql = getRestoreSql(p) + " FOR UPDATE";
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        ResultSetMetaData meta = null;
        
        //		 try {
        Hashtable h = new Hashtable(); // store the values here
        
        // Get the connection from the DatabaseTransaction
        connection = ((DatabaseTransaction)trans).getConnection();
        // Create a JDBC statement from the connection
        statement = connection.createStatement();
        // Execute the SQL
        results = statement.executeQuery(sql);
        // Get the meta data
        meta = results.getMetaData();
        // Make sure we got a row!
        if( !results.next() ) {
            throw new PersistenceException("No rows found!");
        }
        // For each column in the result set
        for(int i=1; i<=meta.getColumnCount(); i++) {
        	Object ob = null;
            
            try {
            	ob = results.getObject(i);
            } catch ( SQLException sqe ) {
            	logger.debug("Error retrieving object");
            }
            
            // Objects (strings?) without values were returned as "null"
            // So, I replaced with empty string objects (RD)
            if (ob==null){
                ob= new String("");
            }
            // Put the value in the Hashtable using the column name
            // as a key
            h.put(meta.getColumnLabel(i), ob);
        }
        // Call the restore from Hashtable method in the Persistent
        p.restore(trans, h);
/*		 }
                 catch( SQLException e ) {
                         if( statement != null ) { // This is not required
                                 try { statement.close(); }
                                 catch( SQLException e2 ) { }
                         }
                         throw new PersistenceException(e);
                 }
 */
    }
    //        * private methods generate no javadoc *
    // Because JDBC allows all statements that modify the database
    // go through the executeUpdate() method in java.sql.PreparedStatement,
    // we can encapsulate all of the persistence operations inside
    // a single method after we get the specific SQL.  This private
    // method takes a prepared statment and executes it.
    private void save(DatabaseTransaction trans, java.sql.PreparedStatement pstmt)
    throws PersistenceException {
        
        try {
            pstmt.executeUpdate(); // Execute the SQL
        } catch( SQLException e ) {
            throw new PersistenceException("Class: " + this.getClass().getName() + "; " + e.getMessage() );
        }
        finally {
        	try {
        		if ( pstmt != null ) {
        			pstmt.close();
        			pstmt = null;
        		}
        	} catch ( SQLException e ){
        		throw new PersistenceException(e);
        	}
        }
    }
    /**
     * This method implements the update persistence operation
     * as prescribed by the PersistentPeer interface.  It asks
     * its subclasses for the proper SQL and then triggers
     * a save using that SQL statement.
     * @param p the persistent object to be updated
     * @param t the DatabaseTransaction to use for updating the object
     * @exception PersistenceException An error occurred
     * in updating the object in the database.
     */
    public void update(Persistent p, Transaction t)
    throws PersistenceException {
        save((DatabaseTransaction)t, getUpdateSql((DatabaseTransaction)t,p));
    }
    
    protected PreparedStatement createPreparedStatement(String sql, DatabaseTransaction t) throws PersistenceException, SQLException{
    	if(sql != null && sql.length()>0){
        	Connection connection = t.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            return pstmt;
    	}else{
    		throw new PersistenceException("No SQL specified");
    	}
    }
}
