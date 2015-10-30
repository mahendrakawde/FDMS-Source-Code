package com.aldorsolutions.webfdms.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class JMSClient {
	
	private Context context = null;
	private QueueConnection queueConnection = null;
	private QueueSession queueSession = null;
	private QueueSender queueSender = null;	
	private QueueConnectionFactory queueConnectionFactory = null;
	private HashMap requestPropertyMap = new HashMap();
	
    public static String CONNECTION_FACTORY = null;
    public static String CONNECTION_QUEUE = null;	
    
	static private Logger logger = Logger.getLogger(JMSClient.class
			.getName());    
	
	public JMSClient() {
	}
    
	public JMSClient(String jndiConnectionFactory, String jndiQueue) {
		init(jndiConnectionFactory, jndiQueue, true);
	}

	public void init(String jndiQueue, boolean autoAcknowledge) {
		init(jndiQueue, jndiQueue, autoAcknowledge);
	}
	
	public void init(String jndiConnectionFactory, String jndiQueue, boolean autoAcknowledge) {
		try {
			context = new InitialContext(); 
			ServiceLocator sl = ServiceLocator.getInstance();
			queueConnectionFactory = sl.getQueueConnectionFactory(jndiConnectionFactory);
			queueConnection = queueConnectionFactory.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			Queue queue = sl.getQueue(jndiQueue);
			queueSender = queueSession.createSender(queue);
		} catch (Exception ex) {
			logger.error("Naming Exception in JMSClient Constructor", ex);
		}
	}
		
	public void send(Serializable object) {
		try {
			ObjectMessage objectMessage = queueSession.createObjectMessage(object);
			sendMessage(objectMessage);
		} catch (JMSException jex) {
			System.err.println("JMS Exception in JMSClient send"+ jex);
		}
	}	

	private void sendMessage(Message message) {
		try {
			message = addProperties(message);
			queueSender.send(message);
		} catch (JMSException jex) {
			System.err.println("JMS Exception in JMSClient send"+ jex);
		}
	}	
	
	private Message addProperties(Message message) {
		try {
			Iterator propertyKeys = requestPropertyMap.keySet().iterator();
			while (propertyKeys.hasNext()) {
				String key = (String) propertyKeys.next();
				Object property = requestPropertyMap.get(key);
				if (property.getClass().getName().equals("java.lang.String")) {
					message.setStringProperty(key, ((String) property));
				} else {
					message.setObjectProperty(key, property);
				}
			}
		} catch (JMSException jex) {
			System.err.println("JMS Exception in JMSClient send"+ jex);
		}

		return message;
	}	
	
	private void close() {
		if (context != null) {
			try {
				context.close();
				context = null;
			} catch (NamingException nex) {
				System.err.println(
						"Exception in context.close() "+ nex);
			}
		}
		if (queueConnection != null) {
			try {
				queueConnection.close();
				queueConnection = null;
			} catch (JMSException jex) {
				System.err.println(
						"JMS Exception in queueConnection.close() "+ jex);
			}
		}
		if (queueSession != null) {
			try {
				queueSession.close();
				queueSession = null;
			} catch (JMSException jex) {
				System.err.println(
						"JMS Exception in queueSession.close() "+ jex);
			}
		}
		if (queueSender != null) {
			try {
				queueSender.close();
				queueSender = null;
			} catch (JMSException jex) {
				System.err.println(
						"JMS Exception in queueSemder.close() "+ jex);
			}
		}
	}

	public void finalize() throws Exception {
		close();
	}		
}
