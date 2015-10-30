package com.aldorsolutions.webfdms.database;

/**
 * The DatabaseSetPeer performs database queries on behalf of
 * PersistentSet sets.  Subclasses must provide methods specifying
 * the class name of persistent objects it should create from database
 * rows as well as the proper SELECT SQL for restoring objects from
 * the database.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import org.apache.log4j.Logger;

public abstract class DatabaseSetPeer implements PersistentSetPeer {
	
	Logger logger = Logger.getLogger(DatabaseSetPeer.class.getName());
	
	/**
	 * Subclasses must implement this method to provide the
	 * DatabaseSetPeer with the name of a subclasses of Persistent
	 * to instantiate for each row returned from the database.
	 * @param h the Hashtable of values returned from the database query
	 * @return the name of the class to instantiate for this row
	 */
	public abstract String getPersistentClass(Hashtable h);
	/**
	 * Subclasses must implement this method to provide the
	 * DatabaseSetPeer with the proper SQL for performing a query.
	 * The Hashtable may be null.
	 * @param h a list of values with which to limit the query
	 * @return the proper SELECT SQL for this set
	 */
	public abstract String getSql(Hashtable h);
	/**
	 * Implementation of the PersistentSetPeer method for restoring
	 * a set without query criteria.
	 * @param set the set being restored
	 * @param trans the transaction to use for the restore
	 * @exception imaginary.persist.PersistenceException An error
	 * occurred restoring from the database.
	 * @see imaginary.persist.PersistentSetPeer#restore
	 */
	public void restore(PersistentSet set, Transaction trans)
	throws PersistenceException {
		restore(set, trans, null); 
	}
	/**
	 * Implementation of the PersistentSetPeer method for restoring
	 * based on specified query criteria
	 * @param set the set being restored
	 * @param trans the transaction to use for the restore
	 * @param data the query criteria (can be null to select all records)
	 * @exception imaginary.persist.PersistenceException An error
	 * occurred restoring from the database.
	 * @see imaginary.persist.PersistentSetPeer#restore
	 */
	public void restore(PersistentSet set, Transaction trans, Hashtable data)
	throws PersistenceException {
		ResultSetMetaData meta = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		String sql = getSql(data); // Get the SQL from the subclass
		
		try {
			// Get the Connection from the DatabaseTransaction
			connection = ((DatabaseTransaction)trans).getConnection();
			// Create a Statement from the Connection
			statement = connection.createStatement();
			// Get the results
			results = statement.executeQuery(sql);
			// Get the meta data
			meta = results.getMetaData();
			// While there are rows in the result set
			while( results.next() ) {
				Hashtable h = new Hashtable(); // Store the results here
				String class_name;
				Persistent p;

				// For each column in the row
				for(int i=1; i<=meta.getColumnCount(); i++) {
					Object ob = null;
	                
	                try {
	                	ob = results.getObject(i);
	                } catch ( SQLException sqe ) {
	                	logger.debug("Error retrieving object; Object = " + meta.getColumnLabel(i) + 
	                			"; msg = " + sqe.getMessage() + "; SQL State = " + sqe.getSQLState() );
	                }
	                
					if (ob==null){
						// replace with empty string
						ob=new String("");
					}
					h.put(meta.getColumnLabel(i), ob); // Stick it in Hashtable
				}
				// Get the name of the class to create from this row
				class_name = getPersistentClass(h);
				// Get a Persistent from the Persistent
				p = Persistent.getPersistent(trans, h, class_name);
				// Add the Persistent to the set
				set.addPersistent(p);
			}
			statement.close(); // closing statement closes results
			// 1/15/02 removed commit() since I think that "ends" the
			// connection making this "transaction" object no longer good
			//connection.commit(); // Release database locks
		}
		catch( SQLException e ) {
			if( statement != null ) {
				try { statement.close(); } // closes results too
				catch( SQLException e2 ) { }
			}
			throw new PersistenceException(e);
		}
	}
}
