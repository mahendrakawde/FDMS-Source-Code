package com.aldorsolutions.webfdms.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DbTable {
	private static Logger logger = Logger.getLogger(DbTable.class.getName());
	
	protected List columns;
	protected String name;
	protected String databaseName;
	
	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public DbTable()
	{
		columns = new ArrayList();
		name = "";
	}
	public DbTable ( String aName) {
		this();
		name = aName;
	}
	public void addColumn(DbColumn column)
	{
		columns.add(column);
	}
	/**
	 * @return the columns
	 */
	public List getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List columns) {
		this.columns = columns;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
