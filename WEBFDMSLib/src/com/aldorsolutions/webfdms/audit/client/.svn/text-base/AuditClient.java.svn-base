package com.aldorsolutions.webfdms.audit.client;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.AuditDTO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.util.JMSClient;
import com.aldorsolutions.webfdms.util.JNDINames;
import com.aldorsolutions.webfdms.util.UtilSingleton;

public class AuditClient extends JMSClient {
	
	static private Logger logger = Logger.getLogger(AuditClient.class.getName());    
  long companyID;
  
  public AuditClient(long companyID) {
		super();
		this.companyID = companyID;
	}



	public void record(AuditDTO auditDto) {
		// Check to see if we have global Audit logging turned off.
	  String globalAuditLog = UtilSingleton.getInstance().getProperty(0,"Global.Audit.Log");

	  if (globalAuditLog == null) {
	  	globalAuditLog = "on";
	  }
	  
    if (("off".toUpperCase().equals(globalAuditLog.toUpperCase())) == false ) {
				CompanyOptionsManager coMgr = new CompanyOptionsManager ();
	  	// Now check to see if this options is turned on for this customer
	    int serviceDateOption = coMgr.getCompanyOptionValueForCompany(companyID, CompanyOptionsDTO.COMPANY_OPTION_AUDIT_LOG_OFF);
	    	// If this value is 0 then it is ok to log.  1 = turn log off.
	    if (serviceDateOption == 0) {
	      try {
	        init(JNDINames.JMS_CONNECTION_FACTORY, JNDINames.AUDIT_QUEUE, true);
		        
	      	send(auditDto);	   
	//        	logger.debug("Sent Object to Queue.");
	      } catch (Exception e) {
	       	logger.error("Exception in record() : ", e);
	      } finally {
	        try {
	          finalize();
	        } catch (Exception e) {
	       	  logger.error("Error while closing resources : ", e);
	        }
	      }      
	    } // if Company Option On
    } // If global Audit log
  }
}
