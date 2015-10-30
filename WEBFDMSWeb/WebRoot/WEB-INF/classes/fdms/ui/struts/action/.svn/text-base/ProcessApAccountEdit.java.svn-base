package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbBookMark;
import com.aldorsolutions.webfdms.beans.DbBookMarkLocation;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ApAccountEdit;
import fdms.ui.struts.form.BookMarkAdminForm;


/**
 * ProcessApAccountEdit
 * This class processes the form for adding or changing account data
 * and stores the information to the DBMS.
 * Creation date: (5/13/2002 4:13:09 PM)
 * @author:
 */
public class ProcessApAccountEdit extends Action {
    
    private Logger logger = Logger.getLogger(ProcessApAccountEdit.class.getName());

    /**
     * Called from ApAccountEdit.JSP, this action validates the form data,
     * stores it to the DBMS, and redisplays a new account list so the operator
     * can select another account to modify.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        // AppLog.trace("ProcessApAccountEdit action doPerfrom: "+form.getSubmitbutton()+"-"+form.getAction());
        
        ApAccountEdit form = (ApAccountEdit) actionForm;
        ActionErrors errors = new ActionErrors();
        DbApAccount		anAcct = null;
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        // Check if CANCEL button was clicked
        if (form.getSubmitbutton().equals("cancel")){
            return mapping.findForward("showApAccountListGlobal");
        }
        // Continue with add, change, or delete
        DatabaseTransaction t = null;
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            if (form.getActionID()==ApAccountEdit.ACTION_DELETE) {
            	ArrayList <DbApAccount> lists = form.getApLocationList();
				for( DbApAccount list: lists) {
					list.remove();
					t.addPersistent(list);
				}
			}
			if (form.getActionID() == ApAccountEdit.ACTION_ADD) {
				validate(form, errors,sessionUser);
				if (Constants.GLOBAL_LOCALE_STRING.equals(form.getType())) {
					anAcct = new DbApAccount();
	                anAcct.setNew();
	                anAcct.setAccountNumber(	form.getAcctNumber());
	                anAcct.setDescription(		form.getDescription());
	                anAcct.setLocaleID(0);
	                anAcct.setLocationID(0);
	                t.addPersistent(anAcct);
					
				} else if (Constants.GLOBAL_LOCATION_STRING.equals(form.getType())) {
					String[] locale = form.getLocaleIds();
					for (int i = 0; i < locale.length; i++) {
						
						anAcct = new DbApAccount();
		                anAcct.setNew();
		                anAcct.setAccountNumber(	form.getAcctNumber());
		                anAcct.setDescription(		form.getDescription());
		                anAcct.setLocaleID(Integer.parseInt(locale[i]));
		                anAcct.setLocationID(0);
						t.addPersistent(anAcct);
					}
				} 
				else {
					ArrayList <int[]> lists = getSaveLocations(form);
					for (int[] list: lists) {
						anAcct = new DbApAccount();
		                anAcct.setNew();
		                anAcct.setAccountNumber(	form.getAcctNumber());
		                anAcct.setDescription(		form.getDescription());
		                anAcct.setLocaleID(list[0]);
		                anAcct.setLocationID(list[1]);
						t.addPersistent(anAcct);
					}
				}
			}
			
			if (form.getActionID() == form.ACTION_CHANGE) {

				// remove the old selection.
				ArrayList <DbApAccount> lists = form.getApLocationList();
				for( DbApAccount list: lists) {
					list.remove();
					t.addPersistent(list);
				}
				// insert new selection.
				if (Constants.GLOBAL_LOCALE_STRING.equals(form.getType())) {
					anAcct = new DbApAccount();
	                anAcct.setNew();
	                anAcct.setAccountNumber(	form.getAcctNumber());
	                anAcct.setDescription(		form.getDescription());
	                anAcct.setLocaleID(0);
	                anAcct.setLocationID(0);
	                t.addPersistent(anAcct);
					
				} else if (Constants.GLOBAL_LOCATION_STRING.equals(form.getType())) {
					String[] locale = form.getLocaleIds();
					for (int i = 0; i < locale.length; i++) {
						
						anAcct = new DbApAccount();
		                anAcct.setNew();
		                anAcct.setAccountNumber(	form.getAcctNumber());
		                anAcct.setDescription(		form.getDescription());
		                anAcct.setLocaleID(Integer.parseInt(locale[i]));
		                anAcct.setLocationID(0);
						t.addPersistent(anAcct);
					}
				} 
				else {
					ArrayList <int[]> list = getSaveLocations(form);
					for (int[] data: list) {
						anAcct = new DbApAccount();
		                anAcct.setNew();
		                anAcct.setAccountNumber(	form.getAcctNumber());
		                anAcct.setDescription(		form.getDescription());
		                anAcct.setLocaleID(data[0]);
		                anAcct.setLocationID(data[1]);
						t.addPersistent(anAcct);
					}
				}
			
			}
			
			
            
//            if (form.getActionID()==ApAccountEdit.ACTION_ADD){
//                // prepare for addition
//                anAcct = new DbApAccount();
//                anAcct.setNew();
//                t.addPersistent(anAcct);
//            }
//            else {
//                // must be "change" or "delete"
//                int recid = form.getAccountID();
//                if (recid < 1){
//                    // AppLog.trace("ProcessApAccountEdit- Change/delete with no selection.");
//                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
//                    saveErrors(request, errors);
//                    return (new ActionForward(mapping.getInput()));
//                }
//                // fetch account from DBMS
//                // AppLog.trace("ProcessApAccount - Fetch acct:"+recid);
//                anAcct = FdmsDb.getInstance().getApAccount(t, recid);
//                if (anAcct==null){
//                    // AppLog.error("ProcessApAccountEdit. Attempted update of recid:"+recid+" but record not found in DBMS");
//                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Account record could not be accessed."));
//                    saveErrors(request, errors);
//                    return (new ActionForward(mapping.getInput()));
//                }
//            }
//            // Store to DBMS
//            if (form.getActionID()==ApAccountEdit.ACTION_DELETE){
//                anAcct.remove();
//            }
//            else {
//                anAcct.setAccountNumber(	form.getAcctNumber());
//                anAcct.setDescription(		form.getDescription());
////                anAcct.setLocationID(		FormatNumber.parseInteger(form.getLocation()));
//                if (FormatNumber.parseInteger(form.getLocale()) == 0) {
//                	anAcct.setLocaleID(0);
//                	anAcct.setLocationID(0);
//                }else if (FormatNumber.parseInteger(form.getLocale()) == -1) {
//	                LocationDAO locManager = new LocationDAO(sessionUser);
//	                LocationDTO location = locManager.getLocation(FormatNumber.parseInteger(form.getLocation()));
//	                anAcct.setLocaleID( (int) location.getLocaleID());
////                	anAcct.setLocaleID(FormatNumber.parseInteger(form.getLocale()));
//                	anAcct.setLocationID(FormatNumber.parseInteger(form.getLocation()));
//                } else {
//                	anAcct.setLocaleID(FormatNumber.parseInteger(form.getLocale()));
//                	anAcct.setLocationID(0);
//                }
//            }
            // clean up
            t.save();
            // Set collections in session
            SessionHelpers.setApAccountListInSession(request,0) ;
        }
        catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in ProcessApAccountEdit.doPerform. "+pe.getMessage());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            // AppLog.criticalError("Exception in  ProcessApAccountEdit.doPerform. "+pe);
            // pe.printStackTrace();
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
					t.closeConnection();
					t = null;
				}  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            return  new ActionForward(mapping.getInput());
        }
        
        return mapping.findForward("showApAccountListGlobal");
    }
    
	private void validate(ApAccountEdit form, ActionErrors errors, DbUserSession sessionUser) {
		DbApAccount[] alist	= null;
		DatabaseTransaction t = null;
	    try {
	            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
	            alist = FdmsDb.getInstance().getApAccounts(t, form.getAcctNumber(), form.getDescription());
	            if (alist != null && alist.length > 0){
	            	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.apaccount.exist"));
	            }
	    }catch(PersistenceException pe) {
	                // AppLog.criticalError("Persistence Exception in ProcessApAccountEdit.doPerform. "+pe.getMessage());
	                logger.error("PersistenceException in doPerform() : " + pe);
	                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	    }
	    catch(Exception pe) {
	                // AppLog.criticalError("Exception in  ProcessApAccountEdit.doPerform. "+pe);
	                // pe.printStackTrace();
	                logger.error("Error in doPerform() : ", pe);
	                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	    } finally {
	        if (t != null) {
	                    try {
	    					t.closeConnection();
	    					t = null;
	    				}  catch (Exception e) {
	                        logger.error("Error in closeConnection() : ", e);
	           }
	        }
	    }       
	}

	private static ArrayList <int[]> getSaveLocations(ApAccountEdit form) {

		ArrayList <int[]>saveLocations = new ArrayList <int[]>();
		ArrayList locationList = form.getLocations();
		String[] locationIds = form.getLocationIds();

		for (int i = 0; i < locationList.size(); i++) {
			DbLocation location = (DbLocation) locationList.get(i);

			int locationID = location.getId();

			if (locationIds != null) {
				for (int x = 0; x < locationIds.length; x++) {
					int aLocID = Integer.parseInt(locationIds[x]);

					if (aLocID == locationID) {

						int localeID = location.getLocaleNumber();

						int[] loc = new int[2];

						
						loc[0] = localeID;
						loc[1] = locationID;
						
						saveLocations.add(loc);
					}
				}
			}
		}

		return saveLocations;
	}
    
}
