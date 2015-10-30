package com.aldorsolutions.webfdms.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author David Rollins
 *
 */
public class DataSourceManager {
	private Map <String, DataSourceRecord> jndiCache = null;
	
	/**
	 * 
	 */
	public DataSourceManager () {
		super();
		jndiCache = Collections.synchronizedMap(new HashMap <String, DataSourceRecord> ());
	}

	/**
	 * @param jndiLookup
	 * @return
	 * @throws Exception
	 */
	public DataSource getDataSource ( String jndiLookup ) throws Exception {
		DataSourceRecord dsr = null;
		
		if (jndiCache.containsKey(jndiLookup)) {
			dsr = (DataSourceRecord) jndiCache.get(jndiLookup);
			
			if ( dsr.isExpired() == false ) {
				dsr.updateTimestamp();
				return ( dsr.getDataSource() );
			} else {
				// to get here we are expired so lets get our object out of the map
				dsr = (DataSourceRecord) jndiCache.remove(jndiLookup);
				// now make sure it gets garbage collected.
				dsr = null;
			}
        }
		
		return ( updateDataSourceCache ( jndiLookup ) );
	}
	
	/**
	 * @param jndiLookup
	 * @return
	 * @throws Exception
	 */
	public DataSource updateDataSourceCache ( String jndiLookup ) throws Exception {
		DataSource ds = null;

        Context ctx = new InitialContext();
	    ds = (DataSource) ctx.lookup(jndiLookup);
	    
	    jndiCache.put(jndiLookup, new DataSourceRecord(ds));
		
		return (ds);
	}
	
	/**
	 * @param jndiLookup
	 * @return
	 * @throws SQLException
	 */
	public Connection createConnection (String jndiLookup) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = createConnection (jndiLookup, false);
    	} catch (SQLException e) {
    		
    		try {
    			conn = createConnection (jndiLookup, true);
    		} catch (Exception x) {
                throw new SQLException(x.getMessage());
            }
    		
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        
        return ( conn );
	}
	
	/**
	 * @param jndiLookup
	 * @param updateCache
	 * @return
	 * @throws Exception
	 */
	private Connection createConnection (String jndiLookup, boolean updateCache) throws Exception {
		
		Connection conn = null;
		
		if ( updateCache ) {
			DataSource ds = updateDataSourceCache(jndiLookup);
			conn = ds.getConnection();
		} else {
			DataSource ds = getDataSource(jndiLookup);
			conn = ds.getConnection();
		}
        
        return ( conn );
	}

	/**
	 * @author David Rollins
	 *
	 */
	private class DataSourceRecord {
		private long timestamp = 0;
		private DataSource dataSource = null;
		private static final long timeout = (1000 * 60 * 30); // expire in 30 minutes
		
		/**
		 * @param ds
		 * @param timestamp
		 */
		protected DataSourceRecord (DataSource ds, long timestamp) {
			this.dataSource = ds;
			this.timestamp = timestamp;
			
			if ( timestamp <= 0 ) {
				this.timestamp = getCurrentTime();
			}
		}
		
		/**
		 * @param ds
		 */
		protected DataSourceRecord (DataSource ds) {
			this.dataSource = ds;
			this.timestamp = getCurrentTime();
		}

		/**
		 * @return the timestamp
		 */
		public long getTimestamp() {
			return timestamp;
		}

		/**
		 * @param timestamp the timestamp to set
		 */
		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		/**
		 * @return the dataSource
		 */
		public DataSource getDataSource() {
			return dataSource;
		}

		/**
		 * @param dataSource the dataSource to set
		 */
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		/**
		 * @return
		 */
		private long getCurrentTime () {
			return ( Calendar.getInstance().getTimeInMillis() );
		}
		
		/**
		 * @return
		 */
		public boolean isExpired () {
			long delta = getCurrentTime() - getTimestamp();
			
			if ( delta > timeout ) {
				return ( true );
			} else 
			{
				return ( false );
			}
		}
		
		/**
		 * 
		 */
		public void updateTimestamp() {
			setTimestamp ( Calendar.getInstance().getTimeInMillis() );
		}
	}
} 