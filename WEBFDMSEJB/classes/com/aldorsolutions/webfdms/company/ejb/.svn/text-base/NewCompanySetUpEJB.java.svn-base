package com.aldorsolutions.webfdms.company.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.dao.DataBaseDAO;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;

/**
 * XDoclet-based session bean.  The class must be declared
 * public according to the EJB specification.
 *
 * To generate the EJB related files to this EJB:
 *		- Add Standard EJB module to XDoclet project properties
 *		- Customize XDoclet configuration for your appserver
 *		- Run XDoclet
 *
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="NewCompanySetUp"
 *           display-name="Name for NewCompanySetUp"
 *           description="Description for NewCompanySetUp"
 *           jndi-name="ejb/NewCompanySetUp"
 *           type="Stateless"
 *           view-type="both"
 *           transaction-type="Bean"
 */
public class NewCompanySetUpEJB implements SessionBean {

	/** The session context */
	private SessionContext context;
	private Logger logger = Logger.getLogger(NewCompanySetUpEJB.class.getName());

	public NewCompanySetUpEJB() {
		// TODO Auto-generated constructor stub
	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}
	 public void ejbCreate()throws javax.ejb.CreateException {
	   }

	/**
	 * Set the associated session context. The container calls this method 
	 * after the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context 
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext) throws EJBException {
		context = newContext;
	}

	/**
	 * AddsNew Company
	 *
	 * @ejb.interface-method view-type = "both"
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public boolean addCompany(CompanyDTO company) throws EJBException {
		boolean result = false;
		InitialContext ctx;
		CompanyManagerBean bean = new CompanyManagerBean();
		String databaseName =bean.getDataBaseName(company.getDataURL());
		UserTransaction userTransaction = null;
		
		try {
			ctx = new InitialContext();
			userTransaction = (UserTransaction) ctx.lookup("java:comp/UserTransaction");

			userTransaction.begin();
			result = (bean.addCompany(company));
			
			if (result) {
				userTransaction.commit();
			} else {
				userTransaction.rollback();
			}
		
		} catch (NamingException e) {
			logger.error(e);
		} catch (NotSupportedException e) {
			logger.error(e);
		} catch (SystemException e) {
			logger.error(e);
		} catch (Exception e){
			logger.error(e);
			try{
				userTransaction.rollback();
				DataBaseDAO dao = new DataBaseDAO();
				dao.dropDataBase(databaseName);
				bean.deleteDataSource(company);
			} catch (SystemException se) {
				logger.error(se);
			} catch (Exception se) {
				logger.error(se);
			}
		}finally{
			try {
				
			} catch (IllegalStateException e) {
				logger.error(e);
			} catch (SecurityException e) {
				logger.error(e);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		
		return result;
	}

}
