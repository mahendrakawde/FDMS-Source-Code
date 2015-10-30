package fdms.ui.struts.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.OptionsList;


public class FormsAvailableManager {
  private Logger logger = Logger.getLogger(FormsAvailableManager.class.getName());

	
	public ArrayList <OptionsList> getFormsAvailable(DbUserSession sessionUser) 
					throws PersistenceException, Exception {

	  DatabaseTransaction t = null;
	  DbFormsAvailable[] dbForms = null;
	  ArrayList <OptionsList> formslist = new ArrayList <OptionsList> ();
	  
	  if (sessionUser == null)	{
	  	throw new Exception("error.login.invalid");
	  }
	  
	  // Database Access Logic
	  try {
	      t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	      dbForms = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion());
	      // Populate the Forms List
	      for (int i=0; i < dbForms.length; i++) {
	          String listValue = String.valueOf(dbForms[i].getId());
	          String listLabel = dbForms[i].getDescription();
	          formslist.add(new OptionsList(listValue, listLabel));
	      }
	      
	  }
	  catch(PersistenceException pe) {
	      logger.error("Persistence Exception in ShowFormsAdmin.doPerform. " + pe);
	      throw pe;
	  }
	  catch(Exception e) {
	      logger.error("Exception in  ShowFormsAdmin.doPerform. ", e);
	      throw e;
	  } finally {
	      if (t != null) t.closeConnection();
	  }
	
	  return formslist;
	}
}