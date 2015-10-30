package com.aldorsolutions.webfdms.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbArrangersPeer;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbExternalAppConfig;
import com.aldorsolutions.webfdms.beans.DbExternalAppConfigSet;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbGlAcctDefault;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbUserSet;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;


class Comp implements Comparator {
  public int compare (Object o1, Object o2) {
	  String s1 =  ( String ) o1; 
	  String s2 =  ( String ) o2; 
	  return s1.toUpperCase (  ) .compareTo ( s2.toUpperCase (  )  ) ; 
  }
}

/**
 * This class contains static methods to assist with common tasks
 * relating to the JSP user interface.
 * Creation date: (2/14/2002 4:24:12 PM)
 * @author:
 */
public class SessionHelpers {
    
    private static Logger logger = Logger.getLogger(SessionHelpers.class.getName());
    /**
     * SessionHelpers constructor comment.
     */
    public SessionHelpers() {
        super();
    }
    /**
     * Determine if sequence number is unique and non zero.
     * If zero, get the highest sequence number used in the collection of DbChargeItems
     * and increment by 5 to get a unique sequence number.
     * If not unique, increment until it is unique.
     * Creation date: (4/10/2002 12:33:50 PM)
     * @return int	  unique sequence number for this charge
     * @param charges java.util.Map collection of charges
     * @param item    DbChargeItem  a contract charge object with a sequence to validate
     */
    public static int calculateSequenceNumber(Map charges, DbChargeItem item ) {
        FinancialInformationLineItem aCharge=null;
        
        // Check if sequence number is zero
        int uniqueSeqNo = item.getSequenceNumber();
//        DatabaseTransaction t = null;
//        int orderByContLine  = 0;
//        try {
//			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
//			 DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
//			 orderByContLine  = 	 FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
//						DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
//			
//			
//        } catch (PersistenceException pe) {
//			logger.error("Persistence Exception in ShowFinancial.doPerform. " + pe);
//			
//		} catch (Exception pe) {
//			logger.error("Exception in  ShowFinancial .doPerform. ", pe);
//			
//		} finally {
//			if (t != null)
//				t.closeConnection();
//		}	

        
        
        if (uniqueSeqNo < 1){
            // If no sequence number, get highest in collection at this point
            uniqueSeqNo=10; // starting point
            Iterator myIterator = charges.values().iterator();
            while(myIterator.hasNext()){
                aCharge = (FinancialInformationLineItem)myIterator.next();
                if (uniqueSeqNo < aCharge.getDbChargeItem().getSequenceNumber()){
                    uniqueSeqNo = aCharge.getDbChargeItem().getSequenceNumber();
                }
            }
        }
        // Make sure this item's sequence number is unique
//        if (orderByContLine == 1) {
//	        while (charges.containsKey(new Integer(item.getType()+""+uniqueSeqNo))){
//	            uniqueSeqNo+=5;
//	        }
//        }else {
//        	 while (charges.containsKey(new Integer(uniqueSeqNo))){
// 	            uniqueSeqNo+=5;
// 	        }
//        }
        
     //create a map to get the uniqueno.
     Map sortedSeqMap = new TreeMap();
     Iterator myIterator = charges.values().iterator();
     while(myIterator.hasNext()){
         aCharge = (FinancialInformationLineItem)myIterator.next();
         sortedSeqMap.put(new Integer(aCharge.getItemSequenceNumber()), aCharge);
     }

        while (sortedSeqMap.containsKey(new Integer(uniqueSeqNo))){
            uniqueSeqNo+=5;
        }

        return uniqueSeqNo;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/22/2002 3:22:51 PM)
     * @return java.lang.String
     * @param checkbox boolean
     */
    public final static String convertBoolToYn(boolean checkbox) {
        if (checkbox) return "Y";
        return "N";
    }
    /**
     * Add dashes for a U.S. social security number.
     * Creation date: (4/22/2002 4:48:21 PM)
     * @return java.lang.String
     * @param astring java.lang.String
     */
    public final static String formatSocSecNo(String astring) {
        StringBuffer outssn = new StringBuffer();
        if (astring == null)
            return "";
        if (astring.length() < 9)
            return astring;
        outssn.append( astring.substring(0,3));
        outssn.append( "-");
        outssn.append( astring.substring(3,5));
        outssn.append( "-");
        outssn.append( astring.substring(5));
        return outssn.toString();
    }
    /**
     * Insert the method's description here.
     * Creation date: (10/24/2002 12:28:16 PM)
     * @return int
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param locationId int
     */
    public final static int getContractNumber(String dbLookup, int localeId) throws PersistenceException {
        
        int contractNumber = 0;
        
        if (localeId < 1){
            return contractNumber;
        }
        
        LocaleDTO dbLocale = FdmsDb.getInstance().getLocale(dbLookup, localeId);
        if (dbLocale == null){
            return contractNumber;
        }
        
        contractNumber = dbLocale.getNextContractNo();
        
        return contractNumber;
    }
    
    public final static int getCaseNumber(String dbLookup, int localeId) throws PersistenceException {
        
        int caseNumber = 0;
        
        if (localeId < 1){
            return caseNumber;
        }
        
        LocaleDTO dbLocale = FdmsDb.getInstance().getLocale(dbLookup, localeId);
        if (dbLocale == null){
            return caseNumber;
        }
        
        caseNumber = dbLocale.getNextPreNeedNumber();
        
        return caseNumber;
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (4/11/2002 3:46:16 PM)
     * @return java.lang.String
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param recid int
     * @exception PersistenceException The exception description.
     */
    public final static String getSpeedDataField(DatabaseTransaction t, int recid, int field) throws PersistenceException {
        if (recid < 1){
            return new String();
        }
        DbSpeedData row = FdmsDb.getInstance().getSpeedDataRow( t, recid);
        if (row == null){
            return new String();
        }
        String mydata = row.getData();
        
        return CsvTable.getField(mydata, field);
    }
    /**
     * Create collection of speed-data, putting each speed data element into
     * an "OptionList" object. Then in the JSP, you need code like:
     *  <html:select property="survivor" size="10">
     *      <html:options collection="survivorsList" property="listValue" labelProperty="listLabel" />
     *  </html:select>
     *
     * Creation date: (3/21/2002 1:02:39 PM)
     * @return java.util.ArrayList
     * @param user DbUserSession
     * @param category java.lang.String
     */
    public static ArrayList getSpeedDataOptionCollection(DbUserSession user, String category)
    throws PersistenceException{
        ArrayList <OptionsList> list=new ArrayList <OptionsList> ();
        DbSpeedData[] contlist=null;
        DatabaseTransaction t =null;
        
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            contlist = FdmsDb.getInstance().getSpeedData(user.getDbLookup(), user.getRegion(),category);
            
            if (contlist != null) {
                for (int i=0; i<contlist.length; i++){
                    list.add(new OptionsList( contlist[i].getData() ,contlist[i].getData()));
                }
            }
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
        return list;
    }
    /**
     * Create collection of speed-data, putting each speed data element into
     * an "OptionList" object. The "Value" of the option will be the
     * speed-data record-identifier. The information displayed to the user
     * is given by the displayField parameter.
     * Then in the JSP, you need code like:
     *  <html:select property="survivor" size="10">
     *      <html:options collection="survivorsList" property="listValue" labelProperty="listLabel" />
     *  </html:select>
     *
     * Creation date: (3/21/2002 1:02:39 PM)
     * @return ArrayList
     * @param user DbUserSession
     * @param category java.lang.String
     * @param displayField int  use comma delimiters to display a specific field from the speed data
     */
    public static ArrayList getSpeedDataOptionCollection(DbUserSession user, String category, int displayField)
    throws PersistenceException{
        ArrayList <OptionsList>  list=new ArrayList <OptionsList> ();
        DbSpeedData[] contlist=null;
        DatabaseTransaction t =null;
        
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            contlist = FdmsDb.getInstance().getSpeedData(user.getDbLookup(), user.getRegion(),category);
            
            if (contlist != null) {                        
                for (int i=0; i<contlist.length; i++){
                    String displayParam = CsvTable.getField(contlist[i].getData(), displayField);
                    list.add(new OptionsList( String.valueOf(contlist[i].getId()) , displayParam));
                }
            }
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
        return list;
    }
    /**
     * Create collection of speed-data, putting each speed data element into
     * an "OptionList" object. The information displayed to the user
     * is given by the displayField parameter. The value field of the option
     * object is specified the valueField paramter.
     * Then in the JSP, you need code like:
     *  <html:select property="survivor" size="10">
     *      <html:options collection="survivorsList" property="listValue" labelProperty="listLabel" />
     *  </html:select>
     *
     * Creation date: (3/21/2002 1:02:39 PM)
     * @return ArrayList
     * @param user DbUserSession
     * @param category java.lang.String
     * @param displayField int  use comma delimiters to display a specific field from the speed data
     * @param valueField int  use comma delimiters to specify the field from the speed data to be the "value="
     */
    public static ArrayList getSpeedDataOptionCollection(DbUserSession user, String category, int displayField, int valueField)
    throws PersistenceException{
        ArrayList <OptionsList> list=new ArrayList <OptionsList> ();
        DbSpeedData[] contlist=null;
        DatabaseTransaction t =null;
        
        try{

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            contlist = FdmsDb.getInstance().getSpeedData(user.getDbLookup(), user.getRegion(),category);
            
            if (contlist != null) {
                for (int i=0; i<contlist.length; i++){
                    String displayParam = CsvTable.getField(contlist[i].getData(), displayField);
                    String valueParam   = CsvTable.getField(contlist[i].getData(), valueField);
                    list.add(new OptionsList( valueParam , displayParam));
                }            
            }
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        return list;
    }
    /**
     * Get the USER object from the current session.
     * Creation date: (2/14/2002 4:32:13 PM)
     * @return DbUserSession
     */
    public static DbUserSession getUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        return sessionUser;
    }
    
    /**
     * Get the CompanyDTO object from the current session.
     * @return com.aldorsolutions.webfdms.company.model.CompanyDTO
     */
    public static CompanyDTO getCompanySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CompanyDTO dtoCompany = (CompanyDTO)session.getAttribute(SessionValueKeys.DB_COMPANY);
        return dtoCompany;
    }
    
    /**
     * Set the specified vitals ID into the request as an Integer object
     * Creation date: (10/7/2002 4:25:40 PM)
     */
    public static int getVitalsIdFromRequest(HttpServletRequest request) {
        if (request.getAttribute("vitalsId")==null){
            return -1;
        }
        Integer vitalsid = (Integer)request.getAttribute("vitalsId");
        return vitalsid.intValue();
    }
    /**
     * Get the vitals ID from request param, attribure, or user object.
     * To get "vitalsId" first check for a parameter.
     * If no parameter, check REQUEST for "vitalsId" attribute
     * If still not found, try the session user object
     * Creation date: (2/14/2002 4:25:40 PM)
     * @return int VitalsID for this session. Zero means error.
     */
    public static int getVitalsIdFromSession(HttpServletRequest request, DbUserSession user) {
        int vitalsid= -1;
        // check REQUEST parameter for "vitalsId"
       // String idparam = request.getParameter("vitalsId");
        String idparam = request.getParameter("vitalsId") != null ? request.getParameter("vitalsId") : request.getAttribute("myVitalsId") != null ? request.getAttribute("myVitalsId").toString() : null;
        if (idparam != null){
            vitalsid = FormatNumber.parseInteger(idparam);
        }
        // Check Request Attribute for "vitalsId"
        if (vitalsid < 1){
            vitalsid = getVitalsIdFromRequest(request);
        }
        if (vitalsid>0 && user != null) {
            // store in user object
            user.setCurrentCaseID(vitalsid);
        }
        // as a last resort, retrieve case-ID to process for user-session object
        if (vitalsid < 1 && user != null) {
            vitalsid = user.getCurrentCaseID();
        }
        return vitalsid;
    }
    /**
     * Return the next contract number for a given locale.
     * Increment and store the next contract number.
     * Creation date: (10/24/2002 12:28:16 PM)
     * @return int
     * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
     * @param locationId int
     */
    public final static synchronized int nextContractNumber(String dbLookup, int localeId) throws PersistenceException {
        
        int contractNumber = 0;
        
        if (localeId < 1){
            return contractNumber;
        }
        
        LocaleDTO dbLocale = FdmsDb.getInstance().getLocale(dbLookup, localeId);
        if (dbLocale == null){
            return contractNumber;
        }
        
        try {
        	contractNumber = dbLocale.getNextContractNo();
        	dbLocale.setNextContractNo(contractNumber + 1);
            
        	LocaleDAO localeDAO = new LocaleDAO();
        	localeDAO.updateLocale(dbLocale, dbLookup);
        } catch (Exception e) {
        	throw new PersistenceException (e);
        }
        
        return contractNumber;
    }
    
    public final static synchronized int nextCaseNumber(String dbLookup, int localeId) throws PersistenceException {
        
        int caseNumber = 0;
        
        if (localeId < 1){
            return caseNumber;
        }
        
        LocaleDTO dbLocale = FdmsDb.getInstance().getLocale(dbLookup, localeId);
        if (dbLocale == null){
            return caseNumber;
        }
        
        try {
        	caseNumber = dbLocale.getNextContractNo();
        	dbLocale.setNextContractNo(caseNumber + 1);
            
        	LocaleDAO localeDAO = new LocaleDAO();
        	localeDAO.updateLocale(dbLocale, dbLookup);
        } catch (Exception e) {
        	throw new PersistenceException (e);
        }
        
        return caseNumber;
    } 
    
    /**
     * Remove Arranger list session attribute.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public static void removeArrangerListFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("directorList");
    }
    /**
     * Remove chapel list session variable.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public static void removeChapelListInSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("chapelList");
    }
    
    
    
    /**
     * Make a list of AP Expense Accounts and add the list to the user's session.
     * Creation date: (5/06/2002 4:32:13 PM)
     */
    public static void setApAccountListInSession(HttpServletRequest request, int location) throws PersistenceException {
		ArrayList<DbApAccount> dbApAccountList = null;
		DbApAccount dpApAcct = null;
		ArrayList<OptionsList> coll = new ArrayList<OptionsList>();
		
		// Get a list of ap vendors so we can go thru them
		dbApAccountList = getApAccountList(request, location);

		if (dbApAccountList != null) {
			for (int i = 0; i < dbApAccountList.size(); i++) {
				dpApAcct = (DbApAccount) dbApAccountList.get(i);
				coll.add(new OptionsList(Integer.toString(dpApAcct.getId()),
						dpApAcct.toString()));
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("accountList", coll);
	}

    
    
    /**
	 * Make a list of AP Expense Accounts and return them
	 * Creation date: (12/21/2002 4:32:13 PM) - Chad
	 */
    public static ArrayList<DbApAccount> getApAccountList(HttpServletRequest request, int location)
    throws PersistenceException{
        DbApAccount[] alist	= null;
        ArrayList <DbApAccount> coll = new ArrayList <DbApAccount> ();
        DatabaseTransaction t =null;
        
        DbUserSession sessionUser = getUserSession(request);
        //AppLog.trace("SetApAccountInSession. User:"+sessionUser);
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of vendors
            alist = FdmsDb.getInstance().getApAccounts(t, sessionUser.getRegion(), location);
            for (int i=0; i<alist.length; i++){
               coll.add(alist[i]);
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		return coll;
     }

    public static void setApAccountListInSession(HttpServletRequest request, int location, boolean groupby) throws PersistenceException {
		ArrayList<DbApAccount> dbApAccountList = null;
		DbApAccount dpApAcct = null;
		ArrayList<OptionsList> coll = new ArrayList<OptionsList>();
		
		// Get a list of ap vendors so we can go thru them
		dbApAccountList = getApAccountList(request, location, groupby);

		if (dbApAccountList != null) {
			for (int i = 0; i < dbApAccountList.size(); i++) {
				dpApAcct = (DbApAccount) dbApAccountList.get(i);
				coll.add(new OptionsList(Integer.toString(dpApAcct.getId()),
						dpApAcct.toString()));
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("accountList", coll);
	}

    
    
    /**
	 * Make a list of AP Expense Accounts and return them
	 * Creation date: (12/21/2002 4:32:13 PM) - Chad
	 */
    public static ArrayList<DbApAccount> getApAccountList(HttpServletRequest request, int location, boolean groupby)
    throws PersistenceException{
        DbApAccount[] alist	= null;
        ArrayList <DbApAccount> coll = new ArrayList <DbApAccount> ();
        DatabaseTransaction t =null;
        
        DbUserSession sessionUser = getUserSession(request);
        //AppLog.trace("SetApAccountInSession. User:"+sessionUser);
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of vendors
            alist = FdmsDb.getInstance().getApAccounts(t, sessionUser.getRegion(), location,true);
            for (int i=0; i<alist.length; i++){
               coll.add(alist[i]);
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		return coll;
     }
  
    
    private static void setSessionList(HttpSession session, DbArrangers[] list, String listname) {
        ArrayList <OptionsList> directors = new ArrayList <OptionsList> ();

        //AppLog.trace("SetArrangersInSession. Region:"+sessionUser.getRegion()+" directors:"+dirlist);
		if (list != null) {
			String dirdata = null;
			for (int i=0; i<list.length; i++){
				dirdata = list[i].getName();
				directors.add( new OptionsList(Integer.toString(list[i].getId()) ,dirdata));
			}
		}
        
        session.setAttribute(listname,directors);
    }
    /**
     * Make a list of chapel locations and add the list to the current request.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public static void setArrangerListInSession(HttpServletRequest request)
        throws PersistenceException{
        
        DbArrangers[] dirlist = null;
        DatabaseTransaction t = null;        
        DbUserSession sessionUser = getUserSession(request);
        HttpSession session = request.getSession();
        Hashtable <String, Object> h = new Hashtable <String, Object> ();
        Integer locale = new Integer(sessionUser.getRegion()); 

        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

            // create select list of arrangers
            dirlist = FdmsDb.getInstance().getArrangers(t, locale.intValue());
            setSessionList(session, dirlist, "arrangerList");
            h.clear();
            
            // create select list of directors
    		h.put(DbArrangersPeer.LOCALE, locale);
    		h.put(DbArrangersPeer.LICENSENO, "**");
            dirlist = FdmsDb.getInstance().getArrangersByType(t, h);
            setSessionList(session, dirlist, "directorList");
            h.clear();
            
            // create select list of counselor
    		h.put(DbArrangersPeer.LOCALE, locale);
    		h.put(DbArrangersPeer.ISCOUNSELOR, "1");
            dirlist = FdmsDb.getInstance().getArrangersByType(t, h);
            setSessionList(session, dirlist, "counselorList");
            h.clear();
            
            // create select list of embalmer
    		h.put(DbArrangersPeer.LOCALE, locale);
    		h.put(DbArrangersPeer.EMBALMERLICENSENUMBER, "**");
            dirlist = FdmsDb.getInstance().getArrangersByType(t, h);
            setSessionList(session, dirlist, "embalmerList");
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
    }
    /**
     * Make a list of chapel locations and add the list to the current request.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public static void setChapelListInSession(HttpServletRequest request)
        throws PersistenceException{
            
        DbLocation[] chapels = null;
        ArrayList <OptionsList> locations = new ArrayList <OptionsList> ();
        DatabaseTransaction t = null;
        
        DbUserSession sessionUser = getUserSession(request);
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
        session.setAttribute("chapelList", locations);
    }
    /**
     * Make a list of Disposition types (from Speeddata) and add the list to the current session.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public static void setDispositionListInSession(HttpServletRequest request)
    throws PersistenceException{
        HttpSession session = request.getSession();
        
        DbUserSession sessionUser = getUserSession(request);
        // AppLog.trace("SetDispositionListInSession. User:"+sessionUser);
        session.setAttribute("dispositionList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "DISPCODE") );
    }
    /**
     * Make a list of GL Default Accounts and add the list to the user's session.
     * Creation date: (6/02/2002 4:32:13 PM)
     */
    public static void setGlAccountDefaultListInSession(HttpServletRequest request)
        throws PersistenceException{
        
        DbGlAcctDefault[] alist = null;
        DbLocation aloc = null;
        ArrayList <OptionsList> coll  = new ArrayList <OptionsList> ();
        String locname = null;
        DatabaseTransaction t = null;
        
        DbUserSession sessionUser = getUserSession(request);
        // AppLog.trace("SetGlAccountDefaultListInSession. User:"+sessionUser);
        
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of vendors
            alist = FdmsDb.getInstance().getGlAcctDefaultSet(t, sessionUser.getRegion(),0, null,null,null);
            
            if (alist != null) {
                for (int i=0; i<alist.length; i++){
                    aloc = FdmsDb.getInstance().getLocation(t, alist[i].getLocationID());
                    if (aloc==null){
                        if (alist[i].getLocationID()==0){
                            locname="*-Any Location";
                        } else {
                            locname = "Unknown Location";
                        }
                    } else{
                        locname = aloc.getName();
                    }
                    
                    coll.add( new OptionsList(Integer.toString(alist[i].getId()) ,locname+" "+alist[i].toString()));
                }
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
      
        HttpSession session = request.getSession();
        session.setAttribute("glAcctList", coll);
    }
    /**
     * Make a list of Arranger License Numbers and add the list to the current request.
     * Creation date: (1/1/2002 11:50:13 AM)
     */
    public static void setLicenseListInSession(HttpServletRequest request)
        throws PersistenceException{
            
        DbArrangers[] dirlist = null;
        ArrayList <OptionsList> licenses = new ArrayList <OptionsList> ();
        DatabaseTransaction t = null;
        
        DbUserSession sessionUser = getUserSession(request);
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of arrangers
            dirlist = FdmsDb.getInstance().getArrangers(t, sessionUser.getRegion());        
                
            if (dirlist != null) {
                // AppLog.trace("SetLicenseListInSession. Region:"+sessionUser.getRegion()+" directors:"+dirlist);
                for (int i=0; i<dirlist.length; i++){
                    //(6/9/03)licenses.add(new OptionsList(dirlist[i].getLicenseNumber(),dirlist[i].getLicenseNumber()));
                    licenses.add(new OptionsList(String.valueOf(dirlist[i].getId()),dirlist[i].getLicenseNumber()));
                }
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        HttpSession session = request.getSession();
        session.setAttribute("licenseList",licenses);
    }
    /**
     * Make a list of Pricing Categories and add the list to the current session.
     * Creation date: (6/2/2003 4:32:13 PM)
     */
    public static void setPriceCategoryListInSession(HttpServletRequest request)
        throws PersistenceException{
        
        DatabaseTransaction t = null;
        ArrayList <OptionsList> categoryList = new ArrayList <OptionsList> ();
        DbSpeedData[] dbSpeedData = null;
        String listValue = null;
        String listLabel = null;
        DbUserSession sessionUser = getUserSession(request);
        
        // AppLog.trace("SetPriceCategoryListInSession. User:"+sessionUser);
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of Services categories
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "PLCATGRY");
            
            if (dbSpeedData != null) {
                for (int i=0; i < dbSpeedData.length; i++) {
                    listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
                    listLabel = listValue+"-"+CsvTable.getField(dbSpeedData[i].getData(), 2);
                    categoryList.add(new OptionsList(listValue,listLabel));
                }
            }
            
            // Add Inventory price categories
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "INVCAT");
            // INVCAT table is set up like 1-Caskets instead of CSV.
            // We need just the first character for the "value"
            
            if (dbSpeedData != null) {
                for (int i=0; i < dbSpeedData.length; i++) {
                    listValue = dbSpeedData[i].getData().substring(0,1);
                    listLabel = dbSpeedData[i].getData();
                    categoryList.add(new OptionsList(listValue,listLabel));
                }
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        HttpSession session = request.getSession();
        session.setAttribute("categoryList", categoryList);
    }
    
    /**
     * Make a list of receipt types from formsavailable and add the list to the current session.
     * Creation date: (11/5/2002 10:54:13 PM)
     */
    public static void setReceiptTypesInSession(HttpServletRequest request)
        throws PersistenceException {
        
        DbFormsAvailable[] receipts = null;
        ArrayList <OptionsList> receiptTypes = new ArrayList <OptionsList> ();
        DatabaseTransaction t = null;
        
        DbUserSession sessionUser = getUserSession(request);
        // AppLog.trace("SetReceiptTypesInSession. Region:"+sessionUser.getRegion());
        
        // create select list of receipt types
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            receipts = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(), DbFormsAvailable.PMTRECEIPT_TYPE);
            
            if (receipts != null) {
                for (int i=0; i < receipts.length; i++){
                    receiptTypes.add(new OptionsList(Integer.toString(receipts[i].getFormId()),receipts[i].getDescription()));
                }
            }
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        HttpSession session = request.getSession();
        session.setAttribute("receiptTypes", receiptTypes);
    }
    /**
     * Make a list of Sale Types (from Speeddata) and add the list to the current session.
     * Creation date: (3/11/2002 4:32:13 PM)    
     */
    public static void setSaleTypeListInSession(HttpServletRequest request)
    throws PersistenceException{
        ArrayList <OptionsList> categoryList = new ArrayList <OptionsList> ();
        HttpSession session = request.getSession();
        DatabaseTransaction t = null;
        DbSpeedData[] 		dbSpeedData = null;
        String 				listValue = null;
        String 				listLabel = null;
        
        DbUserSession sessionUser = getUserSession(request);
        // AppLog.trace("SetSaleTypeListInSession. User:"+sessionUser);
        
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of Services categories
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(), sessionUser.getRegion(), "SaleType");
                    
            if (dbSpeedData != null) {
                for (int i=0; i < dbSpeedData.length; i++) {
                    listValue = CsvTable.getField(dbSpeedData[i].getData(), 1);
                    listLabel = listValue;
                    categoryList.add(new OptionsList(listValue,listLabel));
                }
            }
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        session.setAttribute("saleTypeList", categoryList );
    }
    
    /**
     * Make a list of WebFDMS authorized users and add the list to the current request.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public static void setUserListInSession(HttpServletRequest request)
        throws PersistenceException{
            
        DbUserSession auser = null;
        ArrayList <OptionsList> userlist = new ArrayList <OptionsList> ();
        DatabaseTransaction t =null;
        
        DbUserSession sessionUser = getUserSession(request);
        String userLookup = UtilSingleton.getInstance().getUserDBLookup();
        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser, userLookup);
            DbUserSet users = new DbUserSet();
            Hashtable <String, Object> h = new Hashtable <String, Object> ();
            // Get all users in the same region as our logged in user.
            // AppLog.trace("setUserList for region:"+sessionUser.getRegion()+" "+sessionUser.getDataUrl());
            h.put(DbUserPeer.COMPANYID, new Integer(sessionUser.getCompanyID()));
            h.put(DbUserPeer.DATAURL, sessionUser.getDataUrl() );
            users.restore(t,h);
            PersistentI[] obs = users.getPersistents();
            // Move objects to collection to be stored in session
            
            if (obs != null) {
                for (int i=0; i<obs.length; i++) {
                    auser = (DbUserSession)obs[i];
                    // AppLog.trace("setUserList: "+auser.getUserName());
                    
                    // only the regurlar user (customer user is show up, not the user of aldor)
                    if (auser.getSecurityFdmsAdmin()!=1){
                    	userlist.add( new OptionsList(Integer.toString(auser.getId()) ,auser.getUserName()));
                    }
                }
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
        HttpSession session = request.getSession();
        session.setAttribute("userList",userlist);
    }
    
    /**
     * Make a list of vendors and add the list to the user's session.
     * Creation date: (5/06/2002 4:32:13 PM)
     */
    public static void setVendorListInSession(HttpServletRequest request, int locationid)
        throws PersistenceException{
        ArrayList <Long> locationIDs = new ArrayList <Long> ();
        ArrayList <OptionsList> vendors = new ArrayList <OptionsList>();
//        HashMap <Long, OptionsList> map = new HashMap <Long, OptionsList> ();
        TreeMap <String, OptionsList> map = new TreeMap <String, OptionsList> (new Comp()) ;         
        
        DbUserSession sessionUser = getUserSession(request);
        ApVendorDAO vendorDao = new ApVendorDAO(sessionUser);
        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO(sessionUser);
	      
        // Don't change this.  We need this line of code to lookup but vendors
        // that were input thru the ap check write module.
        ArrayList <ApVendorDTO> vendorRecs = vendorDao.getApVendorByLocale(true, locationid);
//        ArrayList <ApVendorLocationDTO> vendorLocs1 = vendorLocDAO.getApVendorLocationByLocationID(sessionUser.getRegion(), locationid);
        
    		UserManagerBean uMgr = new UserManagerBean();
    		String[] stringLocationIDs = uMgr.getUserLocationIds(sessionUser.getId());
    		for (int i = 0; i < stringLocationIDs.length; i++) {
    			locationIDs.add(Long.parseLong(stringLocationIDs[i]));
    		}

        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByLocationIDs(locationIDs);
        		
				if ( vendorRecs != null ) {
					for ( ApVendorDTO vendor : vendorRecs ) {
						
						// logger.debug("Vendor Rec: " + vendor);
						
						if ( vendor.getLocaleID() == sessionUser.getRegion() && 
								((vendor.getLocationID() == locationid) || (vendor.getLocationID() == 0)) ) {
							OptionsList listItem = new OptionsList( Long.toString(vendor.getVendorID()), vendor.getName());
//							map.put(vendor.getVendorID(), listItem );
							map.put(vendor.getName(), listItem );
							continue;
						}
						
						
						if ( vendorLocs != null ) {
							for ( ApVendorLocationDTO vendorLoc : vendorLocs ) {
								// logger.debug("Vendor Loc Rec: " + vendorLoc);
								
								if ( (vendorLoc.getVendorID() == vendor.getVendorID()) || (vendorLoc.getVendorID() == 0) ) {
									OptionsList listItem = new OptionsList( Long.toString(vendor.getVendorID()), vendor.getName());
//									map.put(vendor.getVendorID(), listItem );
									map.put(vendor.getName(), listItem );
									break;
								}
							}
						}
						
					}
				}
		
				vendors.addAll(map.values());
        HttpSession session = request.getSession();
        session.setAttribute("vendorList", vendors);
    }
    
    /**
     * Set the specified vitals ID into the request as an Integer object
     * Creation date: (10/7/2002 4:25:40 PM)
     */
    public static void setVitalsIdInRequest(HttpServletRequest request, int vitalsid) {
        request.setAttribute("vitalsId",new Integer(vitalsid));
          return;
    }
    
    public static void setWebsiteInRequest(HttpServletRequest request, String url) {
        request.setAttribute("website",url);
          return;
    }
    
    /**
     * Sum the price amounts in a collection of DbChargeItems.
     * Omit entries flagged as "deleted" from the sum.
     * Creation date: (4/3/2002 2:33:50 PM)
     * @return int
     * @param charges Map
     */
    public static int sumCharges(Map charges) {
        int totalCharges=0;
        
        Iterator myIterator = charges.values().iterator();
        FinancialInformationLineItem aCharge;
        // AppLog.trace("sumCharges");
        while(myIterator.hasNext()){
            aCharge = (FinancialInformationLineItem)myIterator.next();
            // AppLog.trace("sumCharges aCharge:"+aCharge.getDbChargeItem().getDescription()+aCharge.getItemDeletion());
            if (aCharge.getItemDeletion()==0){
                // AppLog.trace("sumCharges active:"+totalCharges + aCharge.getDbChargeItem().getAmount());
                totalCharges += aCharge.getDbChargeItem().getAmount();
            }
        }
        
        return totalCharges;
    }
    

    /**
     * 
     * @param request
     * @return
     */
	public static void setExternalAppConfigMap(
			HttpServletRequest request) {
				
		Hashtable h = new Hashtable();
    DbUserSession sessionUser = getUserSession(request);
		DatabaseTransaction t = null; 
		
		try {
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
			DbExternalAppConfigSet set = new DbExternalAppConfigSet();
	        			
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			
			if ((obs != null) && (obs.length > 0)) {
				HashMap <String, Long> map = new HashMap <String, Long> ();
				for (int i = 0; i < obs.length; i++) {
					DbExternalAppConfig dbExternalAppConfig = (DbExternalAppConfig) obs[i];
					map.put(
							Long.toString(dbExternalAppConfig.getExternalAppId()), 
							new Long(dbExternalAppConfig.getExternalAppConfigId())
							);
				}
				
				sessionUser.setExternalAppConfigMap(map);
			}
			
		} catch (Exception e) {
			logger.error("Exception in getDbExternalAppConfigSet() ", e);
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
	}	  
}
