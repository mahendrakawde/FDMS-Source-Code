/*
 * Created on Dec 19, 2005
 */
package com.aldorsolutions.webfdms.database.framework;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

/**
 * @author Ranando
 *
 * A Field object represents a single database field value. The
 * field object contains all of the functions needed to convert a
 * database field value from one type to another, assuming the
 * conversion is valid.
 */
public class Field {
	private String fieldName = null;
	private Hashtable row = null;
	private ResultSet oldrow = null;
	private boolean readonly = false;

	public Field(String name, Hashtable r, boolean rdonly) {
		super();
		fieldName = name;
		row = r;
		readonly = rdonly;
	}
	
	public Field(String name, ResultSet or, boolean rdonly) {
		super();
		fieldName = name;
		oldrow = or;
		readonly = rdonly;
	}
	
	public String getString() throws FieldException {
		String value = null;
	
		try {
			if (row != null)
				value = row.get(fieldName).toString();
			else if (oldrow != null)
				value = oldrow.getString(fieldName);
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to String");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to String", e);
		}
		
		return value;
	}
	
	public int getInteger() throws FieldException {
		int value = 0;
		
		try {
			if (row != null)
				value = Integer.parseInt(row.get(fieldName).toString());
			else 
				value = oldrow.getInt(fieldName);
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to Integer");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to Integer", e);
		}
		
		return value;
	}
	
	public float getFloat() throws FieldException {
		float value = 0;
		
		try {
			if (row != null)
				value = Float.parseFloat(row.get(fieldName).toString());
			else 
				value = oldrow.getFloat(fieldName);
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to Float");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to Float", e);
		}
		
		return value;
	}
	
	public Date getDate() throws FieldException {
		Date value = null;
		
		try {
			if (row != null) {
				SimpleDateFormat format = new SimpleDateFormat();
				value = new Date(format.parse(row.get(fieldName).toString()).getTime());
			}
			else {
				value = oldrow.getDate(fieldName);
			}
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to Date");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to Date", e);
		}
		
		return value;
	}
	
	public Time getTime() throws FieldException {
		Time value = null;
		try {
			if (row != null) {
				SimpleDateFormat format = new SimpleDateFormat();
				value = new Time(format.parse(row.get(fieldName).toString()).getTime());
			}
			else
				value = oldrow.getTime(fieldName);
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to Time");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to Time", e);
		}
		
		return value;
	}

	public Timestamp getTimestamp() throws FieldException {
		Timestamp value = null;
		
		try {
			if (row != null)
				value = Timestamp.valueOf(row.get(fieldName).toString());
			else
				value = oldrow.getTimestamp(fieldName);
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to Timestamp");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to Timestamp", e);
		}
		
		return value;
	}
	
	public boolean getBoolean() throws FieldException {
		boolean value = false;
		
		try {
			if (row != null)
				value = Boolean.valueOf(row.get(fieldName).toString()).booleanValue();
			else
				value = oldrow.getBoolean(fieldName);
		} catch (SQLException e) {
			throw new FieldException("Failed converting field to Boolean");
		} catch (Throwable e) {
			throw new FieldException("Unspecified error converting field to Boolean", e);
		}
		
		return value;
	}
	
	public boolean isNull() throws FieldException {
		boolean b = false;
		if (((row == null) && (oldrow == null)) || 
			(getString() == null)) {
			b = true;
		}
		
		return b;
	}
	
	public void setString(String s) throws FieldException {
		if (!readonly) {
			try {
				if (row != null)
					row.put(fieldName, s);
				else if (oldrow != null)
					oldrow.updateString(fieldName, s);
			} catch (SQLException e) {
				throw new FieldException("Failed to update field \"" + fieldName +"\".");
			} catch (Throwable e) {
				throw new FieldException("Unspecified error while updating field \"" + fieldName +"\".", e);
			}
		}
	}
	
	public void setInteger(int i) throws FieldException {
		setString(Integer.toString(i));
	}
	
	public void setFloat(float f) throws FieldException {
		setString(Float.toString(f));
	}
	
	public void setDate(Date d) throws FieldException {
		setString(d.toString());
	}
	
	public void setTime(Time t) throws FieldException {
		setString(t.toString());
	}
	
	public void setTimeStamp(Timestamp ts) throws FieldException {
		setString(ts.toString());
	}
	
	public void setBoolean(boolean b) throws FieldException {
		setString(Boolean.toString(b));
	}
	
}
