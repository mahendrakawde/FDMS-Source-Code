package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbBookMarkLocation;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.ApAccountEdit;
import fdms.ui.struts.form.ApAccountListForm;


/**
 * ShowApAccountEdit
 * This class prepares the edit form bean with ApAccount data from DBMS.
 * Creation date: (5/13/2002 4:13:09 PM)
 * @author:
 */
public class ShowApAccountEdit extends Action {
    
    private Logger logger = Logger.getLogger(ShowApAccountEdit.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from ApAccountList.JSP, this action prepares to show
     * ApAccountEdit.JSP.
     * The ApAccountListForm is used to determine if adding or which
     * account is being updated.
     * Then the ApAccountEdit form is created for the JSP to use.
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.trace("ShowApAccountEdit action doPerfrom");
        
        formErrors = new ArrayList();
        ApAccountListForm form = (ApAccountListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        ApAccountEdit	editform = new ApAccountEdit();
        DbApAccount		anAcct = null;
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        editform.setLocaleName(sessionUser.getLocaleName());
        int  recid = 0;

//        ArrayList <LocaleDTO> locales = new ArrayList <LocaleDTO> ();
		
//		LocaleDAO localeDAO = new LocaleDAO();
//		locales = localeDAO.getLocales(true, (int) sessionUser.getCompanyID(), sessionUser.getDbLookup(), true);
//		ArrayList localeList = new ArrayList();
//        
//        // Populate the Locale List
//        localeList.add(new OptionsList("0", "-- All --"));
//        for ( LocaleDTO locale : locales ) {
//        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
//        }
//        
//        editform.setLocales(localeList);
//        session.setAttribute("localesSize", new Integer(localeList.size()) );
//        editform.setLocaleIds( getLocaleIds( 0 ) );
        
        try {
        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            if (form.getSubmitbutton().equals("add")){
                // prepare empty form for addition
                editform.setAccountID(0);
                editform.setActionID(ApAccountEdit.ACTION_ADD);
                editform.setAction("Add new account.");
            } else {
                // must be "change" or "delete"
                recid = FormatNumber.parseInteger( form.getAccount());
                if (recid < 1){
                    //AppLog.trace("ShowApAccountEdit- Change/delete with no selection.");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
                    formErrors.add("account");
                    saveErrors(request, errors);
                    request.setAttribute("formErrors",formErrors);
                    return (new ActionForward(mapping.getInput()));
                }
                // fetch account from DBMS
                //AppLog.trace("ShowApAccount - Update acct:"+recid);
                
                anAcct = FdmsDb.getInstance().getApAccount(t, recid);
                if (anAcct==null){
                    //AppLog.error("ShowApAccountEdit. Attempted update of recid:"+recid+" but record not found in DBMS");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Account record could not be accessed."));
                } else {
                    // Load form with DB data
                    editform.setAcctNumber(	anAcct.getAccountNumber());
                    editform.setDescription(anAcct.getDescription());
                    editform.setAccountID(	anAcct.getId());
                    editform.setLocation(	String.valueOf(anAcct.getLocationID()));
                    editform.setLocale(String.valueOf(anAcct.getLocaleID()));
                    if (form.getSubmitbutton().equals("delete")){
                        editform.setAction(	"Confirm Delete.");
                        editform.setActionID(ApAccountEdit.ACTION_DELETE);
                    } else {
                        editform.setAction( "Change: "+anAcct.getAccountNumber());
                        editform.setActionID(ApAccountEdit.ACTION_CHANGE);
                    }
                }

            }
            // Set collections in session
            session.setAttribute( "apAccountEditForm",editform);
//            SessionHelpers.setChapelListInSession(request) ;
            setChapelList(request, sessionUser) ;
            setLocaleList(request, sessionUser) ;
            
            setList(sessionUser,editform, form, t, request);
            
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowApAccountEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowApAccountEdit.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            return  new ActionForward(mapping.getInput());
        }
        
        return mapping.findForward("showApAccountEditJsp");
    }
    
    
    public static void setList(DbUserSession sessionUser, ApAccountEdit editform, ApAccountListForm form,DatabaseTransaction t,HttpServletRequest request){
    	// we are going to set the js for locale and locationlist.
		DbLocation dbLocations[] = FdmsDb.getInstance().getLocationsForCompany(t,
				(int) sessionUser.getCompanyID());

		ArrayList locationList = new ArrayList();
		// Populate the Location List
		for (int i = 0; i < dbLocations.length; i++) {
			DbLocation location = (DbLocation) dbLocations[i];
			locationList.add(location);
		}
		editform.setLocations(locationList);
		ArrayList <LocaleDTO> locales = FdmsDb.getInstance().getLocalesForCompany(sessionUser.getDbLookup(),
				(int) sessionUser.getCompanyID());
        ArrayList localeList = new ArrayList();
        
        // Populate the Locale List
        
        for ( LocaleDTO locale : locales ) {
        	localeList.add(new OptionsList(String.valueOf(locale.getLocaleID()), locale.getName()));
        }
        
        editform.setLocales(localeList);
        request.getSession().setAttribute("localesSize", new Integer(localeList.size()));
		request.getSession().setAttribute("locationsSize", new Integer(locationList.size()));
		List list = new ArrayList();
		DbApAccount[] alist	= null;
		ArrayList <DbApAccount> coll = new ArrayList <DbApAccount> ();
		if(!form.getSubmitbutton().equals("add")){
			alist = FdmsDb.getInstance().getApAccounts(t, editform.getAcctNumber(), editform.getDescription());
			for (int i=0; i<alist.length; i++){
	               coll.add(alist[i]);
	        }
		}
		editform.setLocationIds(getLocations(coll));
		editform.setLocaleIds(getLocale(coll));
		editform.setApLocationList(coll);
		editform.setType(getType(coll));
		
		String js = createLocaleJavascript(dbLocations, editform.getLocationIds());
		editform.setLocaleMapJavascript(js);
		
		
    }
    
    
    public static void setChapelList(HttpServletRequest request, DbUserSession sessionUser)
    throws PersistenceException{
        
    DbLocation[] chapels = null;
    ArrayList <OptionsList> locations = new ArrayList <OptionsList> ();
    DatabaseTransaction t = null;
    
    // AppLog.trace("SetChapelInSession. User:"+sessionUser);
    try {

        t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        // create select list of locations
        // AppLog.trace("SetChapelInSession. Region:"+sessionUser.getRegion());
        chapels = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
        
        // AppLog.trace("SetChapelInSession. Region:"+sessionUser.getRegion()+" chapels:"+chapels);
        if (chapels != null) {
        	  
            for (int i=0; i<chapels.length; i++){
                locations.add( new OptionsList(Integer.toString(chapels[i].getId()) ,chapels[i].getName()));
            }
        } 
        
	} finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
    HttpSession session = request.getSession();
    session.setAttribute("chapelListForAP", locations);
}
    
    public static void setLocaleList(HttpServletRequest request, DbUserSession sessionUser)
    throws PersistenceException{
           
    ArrayList <OptionsList> locales = new ArrayList <OptionsList> ();
    locales.add( new OptionsList(Integer.toString(0) ,"-- All Locales --"));
    locales.add( new OptionsList(Integer.toString(sessionUser.getRegion()) ,"All Locations of "+sessionUser.getLocaleName()));

    HttpSession session = request.getSession();
    session.setAttribute("localeListForAP", locales);
}   
    
    
	private static String createLocaleJavascript(DbLocation[] dbLocations, String[] locationIds) {

		String js = "\n";

		js += "\tlocList = new Array(" + dbLocations.length + ");\n";

		for (int j = 0; j < dbLocations.length; j++) {
			DbLocation dbLocation = (DbLocation) dbLocations[j];

			int localeID = dbLocation.getLocaleNumber();
			int locationID = dbLocation.getId();
			String locName = dbLocation.getName();
			boolean selected = false;

			if (locationIds != null) {
				for (int x = 0; x < locationIds.length; x++) {
					int aLocID = Integer.parseInt(locationIds[x]);

					if (aLocID == locationID) {
						selected = true;
					}
				}
			}
			
			js += "\tlocList[" + j + "] = new Array(" + localeID + ", " + locationID + ", \"" + locName
					+ "\", " + selected + ");\n";

		}

		return (js);

	}

	/**
	 * Gets Array of String locations
	 * @param list containing locations
	 * @return Array
	 */
	private static String[] getLocations(ArrayList <DbApAccount> lists) {

		Map locations = new HashMap();
		for (DbApAccount list: lists ) {
			locations.put(new Integer(list.getLocationID()).toString(),
					new Integer(list.getLocationID()).toString());
		}

		return (String[]) locations.keySet().toArray(new String[locations.keySet().size()]);

	}

	/**
	 * Gets Array of String locale
	 * @param list containing locale
	 * @return Array
	 */
	private static String[] getLocale(ArrayList <DbApAccount> lists) {

		Map locale = new HashMap();
		for (DbApAccount list: lists ) {
			locale.put(new Integer(list.getLocaleID()).toString(), new Integer(list
					.getLocaleID()).toString());
		}

		return (String[]) locale.keySet().toArray(new String[locale.keySet().size()]);
	}

	private static String getType(ArrayList <DbApAccount> lists){
		boolean isAll = false;
		boolean isLocaleSelection = false;
		for (DbApAccount list: lists ) {
			if(list.getLocaleID() == 0 ){
				isAll = true;
				break;
			}else if(list.getLocationID() == 0){
				isLocaleSelection = true;
				break;
			}
		}
		if(isAll) return Constants.GLOBAL_LOCALE_STRING;
		else if(isLocaleSelection) return Constants.GLOBAL_LOCATION_STRING;
		return Constants.LOCATION_SELECTION;
	}
}
