package com.aldorsolutions.dashboard.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBHome;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

public class ServiceLocator {

	private Map cache = null;

	static private Logger logger = Logger.getLogger(ServiceLocator.class
			.getName());

	static private ServiceLocator serviceLocator = null;

	private InitialContext ctx = null;
	private DataSourceManager dsMgr = null;

	/**
	 * Create static ServiceLocator object upon initialization
	 */
	static {

		try {
			serviceLocator = new ServiceLocator();
		} catch (Exception e) {
			logger.error("Error in initializing ServiceLocator : ", e);
		}
	}

	/**
	 * Constructor to create ServiceLocator
	 * 
	 */
	private ServiceLocator() throws Exception {
		ctx = new InitialContext();
		cache = Collections.synchronizedMap(new HashMap ());
		dsMgr = new DataSourceManager();
	}

	/**
	 * 
	 * @return retrieve instance of static ServiceLocator
	 */
	static public ServiceLocator getInstance() {
		return serviceLocator;
	}

	/**
	 * Retrieve an EJBLocalHome object
	 * 
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	public EJBHome getEJBHome(String jndiName, Class className)
			throws Exception {

		EJBHome ejbHome = null;

		if (cache.containsKey(jndiName)) {
			ejbHome = (EJBHome) cache.get(jndiName);
		} else {
			logger.debug("Looking up service : " + jndiName);
			Object obj = ctx.lookup(jndiName);
			Object portObj = PortableRemoteObject.narrow(obj, className);
			ejbHome = (EJBHome) portObj;
			cache.put(jndiName, ejbHome);
		}
		return ejbHome;
	}

	/**
	 * Caches the QueueConnectionFactory in memory.
	 * 
	 * @param jndiName
	 *            The jndi name of the QueueConnectionFactory
	 * @return The newly looked up or cached QueueConnectionFactory
	 * @throws LayeredTechException
	 */
	public QueueConnectionFactory getQueueConnectionFactory(String jndiName)
			throws Exception {

		QueueConnectionFactory queueConnectionFactory = null;
		if (cache.containsKey(jndiName)) {
			queueConnectionFactory = (QueueConnectionFactory) cache
					.get(jndiName);
		} else {
			logger.info("Looking up service : " + jndiName);
			queueConnectionFactory = (QueueConnectionFactory) ctx
					.lookup(jndiName);
			cache.put(jndiName, queueConnectionFactory);
		}
		return queueConnectionFactory;
	}

	/**
	 * Caches Queue in memory.
	 * 
	 * @param jndiName
	 *            The jndi name of the Queue
	 * @return The newly looked up or cached Queue
	 * @throws LayeredTechException
	 */
	public Queue getQueue(String jndiName) throws Exception {

		Queue queue = null;
		if (cache.containsKey(jndiName)) {
			queue = (Queue) cache.get(jndiName);
		} else {
			logger.debug("Looking up service : " + jndiName);
			queue = (Queue) ctx.lookup(jndiName);
			cache.put(jndiName, queue);
		}
		return queue;
	}
	
	public Connection getConnectionFromCache(String jndiLookup) throws SQLException {
		return ( dsMgr.createConnection(jndiLookup) );
	}
	
}
