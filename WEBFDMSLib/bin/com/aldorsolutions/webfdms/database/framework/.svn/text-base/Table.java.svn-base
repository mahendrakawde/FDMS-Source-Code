/*
 * Created on Dec 14, 2005
 */
package com.aldorsolutions.webfdms.database.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;


/**
 * @author Ranando
 *
 * This class covers the general concept of a table or view.
 * It is not meant to handle general SQL statements. For that,
 * use the execSQL() function of the Database object.
 */
public class Table {

	final private static Logger logger = Logger.getLogger(Table.class.getName());

	/* These constants are related to the logging process.
	 * There will be no separate DBT entries for these tables
	 * since they MUST be in the system and MUST be known by
	 * this implementation code. 
	 */
	private static final String LOG_TABLE = "LogTable";
	private static final String LOG_TABLE_EVENTDATE = "EventDate";
	private static final String LOG_TABLE_ACTIONCODE = "ActionCode";
	private static final String LOG_TABLE_ACTIONGROUP = "ActionGroup";
	private static final String LOG_TABLE_TABLENAME = "TableName";
	private static final String LOG_TABLE_OLDID = "OldID";
	private static final String LOG_TABLE_NEWID = "NewID";
	private static final String LOG_TABLE_USERID = "UserID";
	private static final String LOG_TABLE_ADMINNOTES = "AdminNotes";
	
	private static final String FAILSAFE_TABLE = "Failsafe";
	private static final String FAILSAFE_EVENTDATE = "EventDate";
	private static final String FAILSAFE_ACTIONGROUP = "ActionGroup";
	private static final String FAILSAFE_OLDLOGTABLEID = "OldLogTableID";
	private static final String FAILSAFE_NEWLOGTABLEID = "NewLogTableID";
	private static final String FAILSAFE_USERID = "UserID";
	private static final String FAILSAFE_ROLLBACKNOTES = "RollbackNotes";
	
	/* These constants are used by post to determine how to commit
	 * changes to the current record into the ResultSet. 
	 */
	private static final int TABLE_MODE_BROWSE		= 0;
	private static final int TABLE_MODE_INSERT		= 1;
	private static final int TABLE_MODE_UPDATE		= 2;

	/* These constants are used by logaction to determine what type
	 * of log entry to make and where.
	 */
	private static final int LOG_REASON_INSERT		= 0;
	private static final int LOG_REASON_UPDATE		= 1;
	private static final int LOG_REASON_DELETE		= 2;
	private static final int LOG_REASON_UNDELETE	= 3;
	private static final int LOG_REASON_REVERT		= 4;
	private static final int LOG_REASON_ERASE		= 5;
	
	/* These string constants represent the only guaranteed field names. */
	private static final String MFIELD_ID			= "ID";
	private static final String MFIELD_DELETED		= "Deleted";
	private static final String MFIELD_FAILSAFE		= "Failsafe";
	
	/* Private class variables. */
	private Connection dbConnection = null;		//The database's connection
	private String name = null;					//The table name
	private ResultSet table = null;				//The actual result set
	private ArrayList fieldNames = null;		//The set of all fields in this table
	private ArrayList dataFieldNames = null;	//The set of all fields in the result set
	private ArrayList useFieldNames = null;		//The set of all fields for the next SQL statement
	private String where = null;				/* SQL statement clauses... */
	private String orderBy = null;
	private String groupBy = null;
	private String having = null;
	private Hashtable currentRecord = null;		//In memory buffer of the current record
	private boolean modified = false;			//True if the current record has been edited or created
	private boolean readonly = false;			//True if the table is read only
	private boolean bof = false;				//True if the last prev call hit beforeFirst
	private boolean eof = false;				//True if the last next call hit afterLast
	private long lastCount = 0;					//The last valid record count;
	private int mode = TABLE_MODE_BROWSE;		//Current table access state.
	private int userID = -1;					//ID of the user generating the events.


	/* Copy data from the ResultSet(Database) to currentRecord(in-memory buffer) */
	private void RStoCR() throws TableException {
		try {
			if (getCount() > 0) {
				currentRecord = new Hashtable();
				for (int i=0; i<dataFieldNames.size(); i++) {
					String s = table.getString(dataFieldNames.get(i).toString());
	
					// logger.debug(dataFieldNames.get(i).toString() + ", " + s);
					if (table.wasNull())
						s = null;
					currentRecord.put(dataFieldNames.get(i).toString(), s);
				}
			}
		} catch (SQLException e) {
			throw new TableException("Could not read values from the current record.\n" + e.getMessage());
		} catch (Throwable e) {
			throw new TableException("Unspecified error while buffering the current record\n" + e.getMessage());
		}
	}
	
	/* Copy data from currentRecord to the ResultSet */
	private void CRtoRS() throws TableException {
		if (readonly) {
			try {
				currentRecord = new Hashtable();
				for (int i=0; i<dataFieldNames.size(); i++) {
					table.updateString(i+1, currentRecord.get(dataFieldNames.get(i).toString()).toString());
				}
			} catch (SQLException e) {
				throw new TableException("Could not write values to the current record.\n" + e.getMessage());
			} catch (Throwable e) {
				throw new TableException("Unspecified error while applying buffered data to the current record.\n" + e.getMessage());
			}
		}
	}
	
	/* This function pulls the list of fields that came in from the last select statement */
	private void getFieldNamesFromResultSet() throws TableException {
		try {
			fieldNames = new ArrayList();
			
			ResultSetMetaData rmd = table.getMetaData();
			for (int i=1; i<=rmd.getColumnCount(); i++) {
				fieldNames.add(rmd.getColumnName(i));
				// logger.debug("Field #" + Integer.toString(i) + ": " + rmd.getColumnName(i));
			}
			
			if (fieldNames.indexOf(MFIELD_ID) == 0)
				fieldNames.remove(0);
			else
				throw new TableException("Failed to find maintenance field \"" + MFIELD_ID + "\" as first field!");
			
			int last = fieldNames.size() - 1;
			
			if (fieldNames.indexOf(MFIELD_FAILSAFE) == last) {
				fieldNames.remove(last);
				last--;
				if (fieldNames.indexOf(MFIELD_DELETED) == last)
					fieldNames.remove(last);
				else
					throw new TableException("Failed to find maintenance field \"Deleted\" as next-to-last field!");
			}
			else
				throw new TableException("Failed to find maintenance field \"Failsafe\" as last field!");
				
			dataFieldNames = new ArrayList(fieldNames);
			useFieldNames = new ArrayList(fieldNames);
		} catch (SQLException e) {
			throw new TableException("Could not retrieve field names for table: " + name + "\n" + e.getMessage());
		} catch (Throwable e) {
			throw new TableException("Unspecified error while creating table: "+name + "\n" + e.getMessage());
		}
	}
	
	public Table (Connection con, String tableName) throws TableException {
		Statement sql = null;
		dbConnection = con;
		
		if ( tableName != null ) {
			tableName = tableName.toLowerCase();
		}
		
		name = tableName;
		
		try {
			/* Create an empty result set for the selected table. */
			sql = con.createStatement();
			table = sql.executeQuery("Select * From " + name + " Where NULL");
			getFieldNamesFromResultSet();
		} catch (TableException e) {
			throw e;
		} catch (SQLException e) {
			throw new TableException("Could not retrieve field names for table: " + name);
		} catch (Throwable e) {
			throw new TableException("Unspecified error while creating table: "+name,e);
		}
	}
	
	public Table (Connection con, ResultSet rs) throws TableException {
		dbConnection = con;
		table = rs;
		readonly = true;
		getFieldNamesFromResultSet();
		Next();
		getCount();
	}
	
	public Collection getAllFieldNames() {
		return fieldNames;
	}
	
	public Collection getDataFieldNames() {
		return dataFieldNames;
	}
	
	public Field getFieldByName(String fieldName) throws TableException {
		Field f = null;
		
		if (dataFieldNames.contains(fieldName))
			f = new Field(fieldName, currentRecord, readonly);
		else
			throw new TableException("Invalid field name requested");
		
		return f;
	}
	
	public Field getFieldByIndex(int index) throws TableException {
		Field f = null;
		
		if ((dataFieldNames.size() > index) && (index >= 0)) 
			f = new Field(dataFieldNames.get(index).toString(), table, readonly);
		else
			throw new TableException("Invalid field index requested");		
		return f;
	}
	
	public Field getOldFieldByName(String fieldName) throws TableException {
		Field f = null;
		
		if (dataFieldNames.contains(fieldName)) 
			f = new Field(fieldName, table, readonly);
		else
			throw new TableException("Invalid original field index requested");		
		
		return f;
	}
	
	public Field getOldFieldByIndex(int index) throws TableException {
		Field f = null;
		
		if (dataFieldNames.size() >= index) 
			f = new Field(dataFieldNames.get(index).toString(), table, readonly);
		else
			throw new TableException("Invalid original field index requested");		
		
		return f;
	}
	
	public void setFieldNames(Collection fieldList) throws TableException {
		if (fieldNames.containsAll(fieldList))
			useFieldNames = new ArrayList(fieldList);
		else
			throw new TableException("Invalid field names found in new field name list! Not setting.");
	}
	
	public void setConditions(String whereClause) {
		if ((whereClause != null) && (whereClause.trim().length() > 0))
			where = "WHERE " + whereClause;
	}
	
	public void setOrder(String orderByClause) {
		if ((orderByClause != null) && (orderByClause.trim().length() > 0))
			orderBy = "ORDER BY " + orderByClause;
	}
	
	public void setGroupBy(String groupByClause) {
		if ((groupByClause != null) && (groupByClause.trim().length() > 0))
			having = "GROUP BY " + groupByClause;		
	}
	
	public void setHaving(String havingClause) {
		if ((havingClause != null) && (havingClause.trim().length() > 0))
			having = "HAVING " + havingClause;
	}
	
	public void Select() throws TableException {
		if (!readonly) {
			String list = new String();
			
			Iterator i = useFieldNames.iterator();
			while (i.hasNext()) {
				if (list.length() > 0)
					list += ", ";
				list += i.next().toString();
			}
	
			String query = "Select " + list + " From " + name +
							where + groupBy + having + orderBy;
			
			try {
				if (mode != TABLE_MODE_BROWSE)
					throw new TableException("Cannot select new data while Inserting or Updating");
				Statement sql = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
															 ResultSet.CONCUR_UPDATABLE);
				table = sql.executeQuery(query);
				dataFieldNames = new ArrayList(useFieldNames);
				
				/* Always Jump to the first record. Use Next() because some data sets don't
				 * have bi-directional cursors.
				 */
				Next();
				getCount();
			} catch (TableException e) {
				throw e;
			} catch (SQLException e) {
				throw new TableException("Could not retrieve field names for table: " + name);
			} catch (Throwable e) {
				throw new TableException("Unspecified error while creating table: "+name, e);
			}
		}
	}
	
	public void Delete(String notes) throws TableException {
		if (!readonly) {
			try {
				if (mode == TABLE_MODE_BROWSE) {
					Field f = null;
					Update();
					f = new Field(MFIELD_DELETED, currentRecord, readonly);
					f.setBoolean(true);
					Post();
					//logTableAction(LOG_REASON_DELETE, id, id, notes);
				}
				else
					throw new TableException("Cannot delete data while Inserting or Updating");
			} catch (TableException e) {
				throw e;
			} catch (FieldException e) {
				throw new TableException("Could not delete the current record");
			} catch (Throwable e) {
				throw new TableException("Unspecified error while deleting the current record", e);
			}
		}
	}
	
	public void Erase() throws TableException {
		if (!readonly) {
			try {
				if (mode != TABLE_MODE_BROWSE)
					table.deleteRow();
				else
					throw new TableException("Cannot erase new data while Inserting or Updating");
			} catch (TableException e) {
				throw e;
			} catch (SQLException e) {
				throw new TableException("Could not delete the current record");
			} catch (Throwable e) {
				throw new TableException("Unspecified error while deleting the current record", e);
			}
		}
	}
	
	public void Insert() throws TableException {
		if (!readonly) {
			try {
				if (mode == TABLE_MODE_BROWSE) {
					table.moveToInsertRow();
					mode = TABLE_MODE_INSERT;
				} else 
					throw new TableException("Cannot insert new record while Inserting or Updating");
			} catch (TableException e) {
				throw e;
			} catch (SQLException e) {
				throw new TableException("Could not insert new record");
			} catch (Throwable e) {
				throw new TableException("Unspecified error while inserting new record", e);
			}
		}
	}
	
	public void Update() throws TableException {
		if (!readonly) {
			try {
				if (mode == TABLE_MODE_BROWSE) {
					mode = TABLE_MODE_UPDATE;
				} else
					throw new TableException("Cannot update current record while Inserting or Updating");
			} catch (TableException e) {
				throw e;
			} catch (Throwable e) {
				throw new TableException("Unspecified error while updating the current record", e);
			}
		}
	}
	
	public void Post() throws TableException {
		if (!readonly) {
			try {
				if (modified)	{
					switch (mode) {
						case TABLE_MODE_BROWSE:
							throw new TableException("Cannot Post if not Inserting or Updating");
						case TABLE_MODE_INSERT:
							CRtoRS();
							table.insertRow();
							break;
						case TABLE_MODE_UPDATE:
							CRtoRS();
							table.updateRow();
							break;
						default:
							throw new TableException("Serious corruption! Invalid Table Mode!!");
					}
					mode = TABLE_MODE_BROWSE;
					modified = false;				
				} else 
					throw new TableException("Cannot Post and unmodified record");
			} catch (TableException e) {
				throw e;
			} catch (SQLException e) {
				throw new TableException("Could not Posting the current record");
			} catch (Throwable e) {
				throw new TableException("Unspecified error while Posting the current record", e);
			}
		}
	}
	
	public void Abort () throws TableException {
		if (!readonly) {
			try {
				table.cancelRowUpdates();
				mode = TABLE_MODE_BROWSE;
				if (modified)
					RStoCR();
				modified = false;
			} catch (SQLException e) {
				throw new TableException("Could not delete the current record");
			} catch (Throwable e) {
				throw new TableException("Unspecified error while deleting the current record", e);
			}
		}
	}
	
	public void First() throws TableException {
		try {
			if (mode != TABLE_MODE_BROWSE)
				throw new TableException("Cannot change records while Inserting or Updating");
			table.first();
			RStoCR();
		} catch (TableException e) {
			throw e;
		} catch (SQLException e) {
			throw new TableException("Could not move to the first record");
		} catch (Throwable e) {
			throw new TableException("Unspecified error while moving to the first record", e);
		}
	}
	
	public void Last() throws TableException {
		try {
			if (mode != TABLE_MODE_BROWSE)
				throw new TableException("Cannot change records while Inserting or Updating");
			table.last();
			RStoCR();
		} catch (TableException e) {
			throw e;
		} catch (SQLException e) {
			throw new TableException("Could not move to the last record");
		} catch (Throwable e) {
			throw new TableException("Unspecified error while moving to the last record", e);
		}
	}
	
	public void Next() throws TableException {
		try {
			if (mode != TABLE_MODE_BROWSE)
				throw new TableException("Cannot change records while Inserting or Updating");
			if (!table.next()) {
				eof = true;
				table.last();
			} else {
				bof = false;
				eof = false;
			}
			RStoCR();
		} catch (TableException e) {
			throw e;
		} catch (SQLException e) {
			throw new TableException("Could not move to the next record");
		} catch (Throwable e) {
			throw new TableException("Unspecified error while moving to the next record", e);
		}
	}
	
	public void Prev() throws TableException {
		try {
			if (mode != TABLE_MODE_BROWSE)
				throw new TableException("Cannot change records while Inserting or Updating");
			if (table.previous()) {
				bof = true;
				table.first();
			} else {
				bof = false;
				eof = false;
			}
			RStoCR();
		} catch (TableException e) {
			throw e;
		} catch (SQLException e) {
			throw new TableException("Could not move to the previous record");
		} catch (Throwable e) {
			throw new TableException("Unspecified error while moving to the previous record", e);
		}
	}
	
	public boolean BOF() throws TableException {
		try {
			return bof;
		} catch (Throwable e) {
			throw new TableException("Unspecified error determining if on 1st record", e);
		}
	}
	
	public boolean EOF() throws TableException {
		try {
			return eof;
		} catch (Throwable e) {
			throw new TableException("Unspecified error determining if on last record", e);
		}
	}
	
	public long getCount() throws TableException {
		try {
			if (mode == TABLE_MODE_BROWSE) {
				int oldpos = table.getRow();
				if (table.last())
					lastCount = table.getRow();
				else lastCount = 0;
				table.absolute(oldpos);
			}
		} catch (SQLException e) {
			throw new TableException("Error determining row count");
		} catch (Throwable e) {
			throw new TableException("Unspecified error determining row count", e);
		}
		
		return lastCount;
	}
}
