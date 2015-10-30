package com.aldorsolutions.webfdms.audit.bean;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.AuditDAO;
import com.aldorassist.webfdms.dao.AuditDAOFactory;
import com.aldorassist.webfdms.model.AuditDTO;

/**
 * XDoclet-based Message Driven entity bean.
 * 
 * To generate EJB related classes using XDoclet:
 *  - Add Standard EJB module to XDoclet project properties - Customize XDoclet
 * configuration - Run XDoclet
 * 
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="AuditMDB" display-name="Name for AuditMDB"
 *           description="Description for AuditMDB"
 *           destination-type="javax.jms.Queue"
 *           acknowledge-mode="Auto-acknowledge"
 *           transaction-type="Bean"
 */
public class AuditMDB implements MessageDrivenBean, MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3843159961721553493L;

	static final Logger logger = Logger.getLogger(AuditMDB.class.getName());

	/** The MessageDrivenContext */
	private MessageDrivenContext context;

	public AuditMDB() {
		super();
	}

	/**
	 * Set the associated context. The container calls this method after the
	 * instance creation. <br>
	 * 
	 * The enterprise bean instance should store the reference to the context
	 * object in an instance variable. <br>
	 * 
	 * This method is called with no transaction context.
	 * 
	 * @param newContext
	 *            A MessageDrivenContext interface for the instance.
	 * 
	 * @throws EJBException
	 *             Thrown by the method to indicate a failure caused by a
	 *             system-level error.
	 */
	public void setMessageDrivenContext(MessageDrivenContext newContext)
			throws EJBException {
		context = newContext;
	}
	
	public void ejbRemove() throws EJBException {
	}

	public void onMessage(Message msg) {
		try {
			ObjectMessage obj = (ObjectMessage) msg;
			AuditDTO auditDto = (AuditDTO) obj.getObject();
			AuditDAO auditDao = null;
			auditDao = AuditDAOFactory.getDAO();
			auditDao.record(auditDto);
		} catch (JMSException e) {
			logger.error("JMS Exception in onMessage() : ", e);
		} catch (Exception e) {
			logger.error("Exception in onMessage() : ", e);
		}
	}

	/**
	 * An ejbCreate method as required by the EJB specification.
	 * 
	 * The container calls the instance?s <code>ejbCreate</code> method
	 * immediately after instantiation.
	 * 
	 * @ejb.create-method
	 */
	public void ejbCreate() {
	}

}
