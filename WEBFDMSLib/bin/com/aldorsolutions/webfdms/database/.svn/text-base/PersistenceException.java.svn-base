package com.aldorsolutions.webfdms.database;

/**
 * An exception that gets thrown by failed persistence operations.
 * It allows you to chain exceptions so you can see what really
 * caused the persistence to fail.
 */
public class PersistenceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1426175707262087497L;
	
	// the exception that caused the persistence to fail
	private Exception cause;

	/**
	 * Constructs an exception without cause or reason.
	 */
	public PersistenceException() {
		this("An exception for no reason whatsoever.", null);
	}
	/**
	 * Constructs a new exception caused by the specified exception.
	 * @param e the exception causing the operation to fail
	 */
	public PersistenceException(Exception e) {
		this("A persisitence exception caused by: " + e.getMessage(), e);
	}
	/**
	 * Constructs a new exception for the specified reason.
	 * @param reason the reason the operation failed
	 */
	public PersistenceException(String reason) {
		this(reason, null);
	}
	/**
	 * Constructs a new exception with both a cause and reason.
	 * @param reason the reason for the exception
	 * @param e the cause
	 */
	public PersistenceException(String reason, Exception e) {
		super(reason);
		cause = e;
	}
	/**
	 * @return the exception that caused the persistence operation to fail
	 */
	public Throwable getCause() {
		return cause;
	}
}
