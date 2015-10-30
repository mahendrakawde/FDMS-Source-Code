package com.aldorsolutions.webfdms.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.UtilSingleton;

/**
 * Represents a user that has access to WebFDMS.
 * Login method provides verification of access and is a constructor.
 * Creation date: (7/20/00 2:29:19 PM)
 * @author:
 */
public class DbUser extends com.aldorsolutions.webfdms.database.Persistent implements DbUserSession {

    private static Logger logger = Logger.getLogger(DbUser.class.getName());

	static private final DbUserPeer peer = new DbUserPeer();

//	public static String URL = UtilSingleton.getInstance().getProperty("db.users.url");
	
	//  public static String URL = new String("jdbc:pervasive://127.0.0.1:1583/WebFdmsUsers");
	private String userName;

	private String password;

	private int region;

	private int locationId;

	private short securityAdmin;

	private short SecurityAtneed;

	private short SecurityPreneed;

	private short securityFinancial;

	private short securityPayments;

	private short securityAcctsRec;

	private short securityForms;

	private short securityReports;

	private short securityDelete;
	
	private short securityCemetery;
	
	private short securityFuneral;

	private String initials;

	private short securityInventory;

	private short securityViewOnly;
	
	private short securityAccountingInterface;
	
	private short securityEasyPayment;
	//added by haranesh patel
	private short securityEregisterbook;

	private short securityPriceDescriptionFinancial;
	
	

	private short securitySpeedData;
	
	private short securityArrangerManagement;
	
	private short securityFormsAvaialble;
	
	private short securityGLSalesAccount;
	
	private short securityAdjustFinancial;
	
	private short securityBank;
	private short securityDashboardReport;
	// Added by Parth
	private short securityEnableFinancialChange;
	
	private short securityFdmsNetwork;
	private short securityFdmsDashboard;
	private short securityFdmsWebservice;
	private short securityFddeWebservice;
	
	private short securityFdmsAdmin;

	private String dataUrl;

	private boolean loggedIn;

	private int currentCaseID;

	private int currentSpcID;
	
	private String locationName;
	
	private String localeName;

	private String greeting;

	private String deleteCode;

	private String firstName;

	private String lastName;

	private String emailAddr;

	private String sqlUser;

	private String sqlPassword;

	private String caseListSortOrder;

	private int caseListPerScreen;

	private boolean localizedSpeedData;

	private HashMap externalAppConfigMap;

	private String state;

	private String caseListDisplayPreneed;

	private String caseListDisplayVoided;

	private String tos;

	private int companyID;

	private boolean userLocked;

	private boolean changePassword;

	private Timestamp lockoutTimestamp;

	private Timestamp passwordTimestamp;
	
	private String dbLookup;
	
	private int currentCemPropID;
	
	private boolean accountingVacationFlag = false;
	
	private long accountingVacationUserID;
	
	private boolean userLimitOverride = false;
	
	private double limitInternalVendor = 0.0;
	
	private double limitExternalVendor = 0.0;
	
	/* 
	 * Not Persisted
	 */
	private ArrayList <RolesMembershipDTO> roles;
	
	/* 
	 * Not Persisted
	 */
	private ArrayList <DbLocation> userLocations;

	/* 
	 * Not Persisted
	 */
	private long configID;
	
	/* 
	 * Not Persisted
	 */
	private String localeCountry;
	
	
	/**
	 * DbUser constructor comment.
	 */
	public DbUser() {
		super();
		loggedIn = false;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:50:10 PM)
	 * @return int
	 */
	public int getCaseListPerScreen() {
		return caseListPerScreen;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:50:10 PM)
	 * @return int
	 */
	public String getCaseListSortOrder() {
		return caseListSortOrder;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:50:10 PM)
	 * @return int
	 */
	public int getCurrentSpcID() {
		return currentSpcID;
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:50:10 PM)
	 * @return int
	 */
	public int getCurrentCaseID() {
		return currentCaseID;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (11/9/2001 5:26:43 PM)
	 * @return String
	 */
	public String getDataUrl() {
		return dataUrl;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/16/2002 3:42:49 PM)
	 * @return String
	 */
	public String getDeleteCode() {
		return deleteCode;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/30/2002 8:07:24 AM)
	 * @return String
	 */
	public String getEmailAddr() {
		return emailAddr;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/30/2002 8:06:55 AM)
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:53:10 PM)
	 * @return String
	 */
	public String getGreeting() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.AM_PM) == Calendar.AM)
			greeting = "Good Morning";
		else
			greeting = "Good Afternoon";

		return greeting;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:01:08 PM)
	 * @return String
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/30/2002 8:07:09 AM)
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the name of the Locale for which this user is assigned.
	 * Creation date: (1/12/2002 5:50:54 PM)
	 * @return String Locale Name
	 */
	public String getLocationName() {
		
		DatabaseTransaction datatran = null;
		try {
			datatran = (DatabaseTransaction) DatabaseTransaction.getTransaction(this);
			locationName = (FdmsDb.getInstance()).getLocationName(datatran,locationId);
			return locationName;
		} catch (com.aldorsolutions.webfdms.database.PersistenceException e) {
			// AppLog.warning("DbUser.getLocationName Persistence Exception. "+e.getMessage());
			logger.error("Error in getLocationName() : ", e);
			return "Location Not Specified";
		}
		finally {
			if ( datatran != null ) {
				datatran.closeConnection();
				datatran = null;
			}
		}
	}

	/**
	 * Get the name of the Locale for which this user is assigned.
	 * Creation date: (1/12/2002 5:50:54 PM)
	 * @return String Locale Name
	 */
	public String getLocaleName() {
		localeName  = FdmsDb.getInstance().getLocaleName(getDbLookup(), getRegion());
		return localeName;
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:55:52 PM)
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:57:02 PM)
	 * @return int
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:52 PM)
	 * @return short
	 */
	public short getSecurityAcctsRec() {
		return securityAcctsRec;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:57:59 PM)
	 * @return short
	 */
	public short getSecurityAdmin() {
		return securityAdmin;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:58:28 PM)
	 * @return short
	 */
	public short getSecurityAtneed() {
		return SecurityAtneed;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:00:39 PM)
	 * @return short
	 */
	public short getSecurityDelete() {
		return securityDelete;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:03 PM)
	 * @return short
	 */
	public short getSecurityFinancial() {
		return securityFinancial;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:00:08 PM)
	 * @return short
	 */
	public short getSecurityForms() {
		return securityForms;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:01:21 PM)
	 * @return short
	 */
	public short getSecurityInventory() {
		return securityInventory;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:32 PM)
	 * @return int
	 */
	public short getSecurityPayments() {
		return securityPayments;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:58:45 PM)
	 * @return short
	 */
	public short getSecurityPreneed() {
		return SecurityPreneed;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:00:24 PM)
	 * @return short
	 */
	public short getSecurityReports() {
		return securityReports;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:01:40 PM)
	 * @return short
	 */
	public short getSecurityViewOnly() {
		return securityViewOnly;
	}

   /**
	 * Modified by JO - QPRIME - SOW: F030601A Cemetery Management System
	 * Return whether user has permission to view Cemetery management
	 * Creation date: (12/20/06)
	 * @return short
	 */
	public short getSecurityCemetery() {
		return securityCemetery;
	}
	
	/**
	 * Modified by JO - QPRIME - SOW: F030601A Cemetery Management System
	 * Return whether user has permission to view Funeral management
	 * Creation date: (01/05/07)
	 * @return short
	 */
	public short getSecurityFuneral() {
		return securityFuneral;
	}


	/**
	 * Insert the method's description here.
	 * Creation date: (9/26/2002 10:59:16 AM)
	 * @return String
	 */
	public String getSqlPassword() {
		return sqlPassword;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (9/26/2002 10:58:58 AM)
	 * @return String
	 */
	public String getSqlUser() {
		return sqlUser;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:56:27 PM)
	 * @return int
	 */
	public int getUserId() {
		return getId();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:53:58 PM)
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return false;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/18/2001 11:57:14 AM)
	 * @return boolean
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	
	/**
	 * This static method looks for a user name.
	 * If it is valid, the method returns a DbUser persistent.
	 * This funciton can be used instead of getPersistents(id#)
	 * DbUser is different from other Db* classes since it uses a different
	 * file set and ODBC connection.
	 * Creation date: (7/20/00 3:26:58 PM)
	 * @return boolean DbUser user object
	 * @param userName String
	 */
	static public DbUser findUserByUserName(String userName) {
		DatabaseTransaction trans = null;
		
		DbUser p = null;
		String userLookup = UtilSingleton.getInstance().getUserDBLookup();
		
		try {
			trans = new DatabaseTransaction (userLookup, 0, 0, 0);
			p = findUserByUserName ( trans, userName );
		} 
		catch (ClassNotFoundException e) {
			logger.error("Class not found exception in findUserByUserName() : ", e);
			return null;
		} catch (PersistenceException e) {
			logger.error("Error in findUserByUserName() : ", e);
			return null;
		} catch (SQLException e) {
			logger.error("SQLException in findUserByUserName() : ", e);
			return null;
		} catch (InstantiationException e) {
			logger.error("InstantiationException in findUserByUserName() : ", e);
			return null;
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException in findUserByUserName() : ", e);
			return null;
		} finally {
			if (trans != null) {
				trans.closeConnection();
				trans = null;
			}
			
		}

		return p;
	}
	
	/**
	 * This static method looks for a user name.
	 * If it is valid, the method returns a DbUser persistent.
	 * This funciton can be used instead of getPersistents(id#)
	 * DbUser is different from other Db* classes since it uses a different
	 * file set and ODBC connection.
	 * Creation date: (7/20/00 3:26:58 PM)
	 * @return boolean DbUser user object
	 * @param userName String
	 */
	static private DbUser findUserByUserName(DatabaseTransaction trans, String userName) throws PersistenceException, 
		ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		Statement statement = null;
		ResultSet results = null;
		Connection conn = null;
		ResultSetMetaData meta = null;
		DbUser p = null;
		String sql = "SELECT * FROM usersecurity WHERE (DeleteCode is null or DeleteCode <>'D') " + 
					 "AND Name='" + userName + "'";
		
		try {
			conn = trans.getConnection();
			if (conn == null) {
				return null;
			}
			
			// Create a JDBC statement from the connection
			statement = conn.createStatement();
			// Execute the SQL
			results = statement.executeQuery(sql);
			// Get the meta data
			meta = results.getMetaData();
			// If no rows, then not a valid user name
			if (!results.next()) {
				return null;
			}
			
			logger.debug ("Peer: " + peer);
						
			
			// NOW we have a valid username/password
			// create a DbUser object for this user and return it
			Hashtable <String, Object> h = new Hashtable <String, Object> (); // store the values here
			p = (DbUser) Class.forName(peer.getPersistentClass(h)).newInstance();
			
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				
				Object ob = null;
				
				try {
					ob = results.getObject(i);
				} catch ( SQLException sqe ) {
					logger.debug("Error retrieving object");
				}
				
				// Objects (strings?) without values were returned as "null"
				// So, I replaced with empty string objects (RD)
				if (ob == null) {
					ob = new String("");
				}
				// Put the value in the Hashtable using the column name as a key
				h.put(meta.getColumnLabel(i), ob);
			}
			
			// Call the restore from Hashtable method in the Persistent
			p.restore(trans, h);
						
		} 
		finally {
			
			try {
				if (results != null) {
					results.close();
					results = null;
				}
			} catch (SQLException e) {
				logger.error("SQLException in findUserByUserName() : ", e);
			}

			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				logger.error("SQLException in findUserByUserName() : ", e);
			}
			
//			try {
//				if (conn != null) {
//					conn.close();
//					conn = null;
//				}
//			} catch (SQLException e) {
//				logger.error("SQLException in findUserByUserName() : ", e);
//			}
			
		}

		return p;
	}
	
	/**
	 * This static method looks for a user name/password combination.
	 * If it is valid, the method returns a DbUser persistent.
	 * This funciton can be used instead of getPersistents(id#)
	 * DbUser is different from other Db* classes since it uses a different
	 * file set and ODBC connection.
	 * Creation date: (7/20/00 3:26:58 PM)
	 * @return boolean TRUE if logged in OK, FALSE if invalid name or password
	 * @param userName String
	 */
	static public DbUser login(String userName, String password) {
		
		DbUser p = findUserByUserName ( userName );
		
		if ( p != null ) {
			
			if (!SecurityManagerBean.isValidPassword(password, p.getPassword())) {
				return ( null );
			}
			
			p.setLoggedIn(true);
		}
		
		return p;
	}

	/**
	 * Override the remove() method since we are not allowing
	 * physical removal.
	 * Instead we code the check as "voided"
	 */
	public synchronized void remove() {
		super.remove();
		setDeleteCode("D");
	}

	/**
	 * Move data from hashtable and copy into class variables
	 * Peer object restores from database to hashtable.
	 */
	public void restore(com.aldorsolutions.webfdms.database.Transaction t, Hashtable data)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		userName = (String) data.get(DbUserPeer.NAME);
		password = (String) data.get(DbUserPeer.PASSWORD);
		setId(Integer.parseInt(data.get(DbUserPeer.USER_ID).toString()));
		region = FormatNumber.parseInteger(data.get(DbUserPeer.REGION).toString());
		securityAdmin = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ADMIN).toString());
		SecurityAtneed = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ATNEED).toString());
		SecurityPreneed = (short) FormatNumber.parseInteger(data.get(DbUserPeer.PRENEED).toString());
		securityFinancial = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FINANCIAL).toString());
		securityPayments = (short) FormatNumber.parseInteger(data.get(DbUserPeer.PAYMENTS).toString());
		securityAcctsRec = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ACCTSREC).toString());
		securityForms = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FORMS).toString());
		securityReports = (short) FormatNumber.parseInteger(data.get(DbUserPeer.REPORTS).toString());
		securityDelete = (short) FormatNumber.parseInteger(data.get(DbUserPeer.DELETE).toString());
		initials = (String) data.get(DbUserPeer.INITIALS);
		securityInventory = (short) FormatNumber.parseInteger(data.get(DbUserPeer.INVENTORY).toString());
		securityViewOnly = (short) FormatNumber.parseInteger(data.get(DbUserPeer.VIEWONLY).toString());
		securityAccountingInterface = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ACCOUNTINGINTERFACE).toString());
		securityEasyPayment= (short) FormatNumber.parseInteger(data.get(DbUserPeer.EASYPAYMENT).toString());
		//added by haranesh patel
		securityEregisterbook = (short) FormatNumber.parseInteger(data.get(DbUserPeer.EREGISTERBOOK).toString());
		securityPriceDescriptionFinancial= (short) FormatNumber.parseInteger(data.get(DbUserPeer.PRICEDESCRIPTIONFINANCIAL).toString());
		
		
		securitySpeedData = (short) FormatNumber.parseInteger(data.get(DbUserPeer.SPEEDDATA).toString());
		securityArrangerManagement = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ARRANGERMANAGEMENT).toString());
		securityFormsAvaialble = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FORMSAVAILABLE).toString());
		securityGLSalesAccount = (short) FormatNumber.parseInteger(data.get(DbUserPeer.GLSALESACCOUNT).toString());
		securityAdjustFinancial = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ADJUSTFINANCIAL).toString());
		securityBank = (short) FormatNumber.parseInteger(data.get(DbUserPeer.BANK).toString());
		securityEnableFinancialChange = (short) FormatNumber.parseInteger(data.get(DbUserPeer.ENABLE_FINANCIAL_CHANGE).toString());
		securityDashboardReport = (short) FormatNumber.parseInteger(data.get(DbUserPeer.DASHBOARDREPORT).toString());
		securityFdmsNetwork = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FDMSNETWORK).toString());
		securityFdmsDashboard = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FDMSDASHBOARD).toString());
		securityFdmsWebservice = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FDMSWEBSERVICE).toString());
		securityFddeWebservice = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FDDEWEBSERVICE).toString());
		securityFdmsAdmin = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FDMSADMIN).toString());
		securityCemetery = (short) FormatNumber.parseInteger(data.get(DbUserPeer.CEMETERY).toString());
		securityFuneral = (short) FormatNumber.parseInteger(data.get(DbUserPeer.FUNERAL).toString());
		dataUrl = (String) data.get(DbUserPeer.DATAURL);
		deleteCode = (String) data.get(DbUserPeer.DELETECODE);
		firstName = data.get(DbUserPeer.FIRSTNAME).toString();
		lastName = data.get(DbUserPeer.LASTNAME).toString();
		emailAddr = data.get(DbUserPeer.EMAILADDR).toString();
		sqlUser = data.get(DbUserPeer.SQLUSER).toString();
		sqlPassword = data.get(DbUserPeer.SQLPW).toString();
		caseListSortOrder = data.get(DbUserPeer.CASELISTSORTORDER).toString();
		caseListPerScreen = FormatNumber.parseInteger(data.get(DbUserPeer.CASELISTPERSCREEN).toString());
		state = data.get(DbUserPeer.STATE).toString();
		caseListDisplayPreneed = data.get(DbUserPeer.CASELISTDISPLAYPRENEED).toString();
		caseListDisplayVoided = data.get(DbUserPeer.CASELISTDISPLAYVOIDED).toString();
		tos = data.get(DbUserPeer.TOS).toString();
		changePassword = getChecked(data.get(DbUserPeer.CHANGEPASSWORD));
		userLocked = getChecked(data.get(DbUserPeer.USERLOCKED));
		lockoutTimestamp = getTimestamp(data.get(DbUserPeer.LOCKOUTTMSTMP));
		companyID = Integer.parseInt(data.get(DbUserPeer.COMPANYID).toString());
		passwordTimestamp = getTimestamp(data.get(DbUserPeer.PASSWORDTMSTMP));
		dbLookup = data.get(DbUserPeer.DBLOOKUP).toString(); 
		userLimitOverride = Boolean.parseBoolean( data.get(DbUserPeer.USERLIMITOVERRIDE).toString() );
		limitExternalVendor = Double.parseDouble( data.get(DbUserPeer.LIMITEXTERNALVENDOR).toString() );
		limitInternalVendor = Double.parseDouble( data.get(DbUserPeer.LIMITINTERNALVENDOR).toString() );
		accountingVacationFlag =  Boolean.parseBoolean(data.get(DbUserPeer.ACCT_VACATION_FLAG).toString());
		accountingVacationUserID = FormatNumber.parseLong(data.get(DbUserPeer.ACCT_VACATION_USERID).toString());
	}

	private boolean getChecked(Object value) {
		String strVal = value.toString();

		if (strVal != null && strVal.equals("1")) {
			return (true);
		} else {
			return (false);
		}

	}
	
	private Timestamp getTimestamp(Object value) {
		
		if ( value ==  null ) {
			return null;
		}
		
		String strVal = value.toString();

		if (strVal != null && strVal.equals("")) {
			return (null);
		} else {
			return ((Timestamp) value);
		}

	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:52 PM)
	 * @param newSecurityAcctsRec short
	 */
	public void setCaseListPerScreen(int newCaseListPerScreen) {
		caseListPerScreen = newCaseListPerScreen;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:52 PM)
	 * @param newSecurityAcctsRec short
	 */
	public void setCaseListSortOrder(String newCaseListSortOrder) {
		caseListSortOrder = newCaseListSortOrder;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:50:10 PM)
	 * @param newCurrentCaseID int
	 */
	public void setCurrentCaseID(int newCurrentCaseID) {
		currentCaseID = newCurrentCaseID;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:50:10 PM)
	 * @param newCurrentCaseID int
	 */
	public void setCurrentSpcID(int newCurrentSpcID) {
		currentSpcID = newCurrentSpcID;
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (11/9/2001 5:26:43 PM)
	 * @param newDataUrl String
	 */
	public void setDataUrl(String newDataUrl) {
		dataUrl = newDataUrl;
		modify();
	}

	/**
	 * Protected because this field is not updated in the peer class.
	 * The remove() method handles logical deletion.
	 * Creation date: (5/16/2002 3:42:49 PM)
	 * @param newDeleteCode String
	 */
	protected void setDeleteCode(String newDeleteCode) {
		deleteCode = newDeleteCode;
		// this field is not updated in the DBMS
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/30/2002 8:07:24 AM)
	 * @param newEmailAddr String
	 */
	public void setEmailAddr(String newEmailAddr) {
		emailAddr = newEmailAddr;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/30/2002 8:06:55 AM)
	 * @param newFirstName String
	 */
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (1/12/2002 5:53:10 PM)
	 * @param newGreeting String
	 */
	public void setGreeting(String newGreeting) {
		greeting = newGreeting;
	}

	/**
	 * Get the ID key for this object from the hashtable.
	 * DbUser objects can be accessed by "Name"
	 * So, first see if "Name" is being used for restoring
	 * or if ID# is being used.
	 */
	public void setId(Hashtable h) {
		//if (h.containsKey(DbUserPeer.NAME)){
		//  getPersistentPeer().restore(this, t);
		//}
		setId(((Integer) h.get(DbUserPeer.USER_ID)).intValue());
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:01:08 PM)
	 * @param newInitials String
	 */
	public void setInitials(String newInitials) {
		initials = newInitials;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/30/2002 8:07:09 AM)
	 * @param newLastName String
	 */
	public void setLastName(String newLastName) {
		lastName = newLastName;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/18/2001 11:57:14 AM)
	 * @param newLoggedIn boolean
	 */
	public void setLoggedIn(boolean newLoggedIn) {
		loggedIn = newLoggedIn;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:55:52 PM)
	 * @param newPassword String
	 */
	public void setPassword(String newPassword) {
		password = newPassword;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:57:02 PM)
	 * @param newRegion int
	 */
	public void setRegion(int newRegion) {
		region = newRegion;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:52 PM)
	 * @param newSecurityAcctsRec short
	 */
	public void setSecurityAcctsRec(short newSecurityAcctsRec) {
		securityAcctsRec = newSecurityAcctsRec;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:57:59 PM)
	 * @param newSecurityAdmin short
	 */
	public void setSecurityAdmin(short newSecurityAdmin) {
		securityAdmin = newSecurityAdmin;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:58:28 PM)
	 * @param newSecurityAtneed short
	 */
	public void setSecurityAtneed(short newSecurityAtneed) {
		SecurityAtneed = newSecurityAtneed;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:00:39 PM)
	 * @param newSecurityDelete short
	 */
	public void setSecurityDelete(short newSecurityDelete) {
		securityDelete = newSecurityDelete;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:03 PM)
	 * @param newSecurityFinancial short
	 */
	public void setSecurityFinancial(short newSecurityFinancial) {
		securityFinancial = newSecurityFinancial;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:00:08 PM)
	 * @param newSecurityForms short
	 */
	public void setSecurityForms(short newSecurityForms) {
		securityForms = newSecurityForms;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:01:21 PM)
	 * @param newSecurityInventory short
	 */
	public void setSecurityInventory(short newSecurityInventory) {
		securityInventory = newSecurityInventory;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:59:32 PM)
	 * @param newSecurityPayments int
	 */
	public void setSecurityPayments(short newSecurityPayments) {
		securityPayments = newSecurityPayments;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:58:45 PM)
	 * @param newSecuirtyPreneed short
	 */
	public void setSecurityPreneed(short newSecuirtyPreneed) {
		SecurityPreneed = newSecuirtyPreneed;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:00:24 PM)
	 * @param newSecurityReports short
	 */
	public void setSecurityReports(short newSecurityReports) {
		securityReports = newSecurityReports;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 2:01:40 PM)
	 * @param newSecurityViewOnly short
	 */
	public void setSecurityViewOnly(short newSecurityViewOnly) {
		securityViewOnly = newSecurityViewOnly;
		modify();
	}

	/**
	 * Modified by JO - QPRIME - SOW: F030601A Cemetery Management System
	 * Sets whether user has permission to view Cemetery Management
	 * Creation date: (12/20/06)
    */	
	public void setSecurityCemetery (short newSecurityCemetery) {
		securityCemetery = newSecurityCemetery;
		modify();
	}
	
	/**
	 * Modified by JO - QPRIME - SOW: F030601A Cemetery Management System
	 * Sets whether user has permission to view Funeral Management
	 * Creation date: (01/05/07)
    */	
	public void setSecurityFuneral (short newSecurityFuneral) {
		securityFuneral = newSecurityFuneral;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (9/26/2002 10:59:16 AM)
	 * @param newSqlPassword String
	 */
	public void setSqlPassword(String newSqlPassword) {
		sqlPassword = newSqlPassword;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (9/26/2002 10:58:58 AM)
	 * @param newSqlUser String
	 */
	public void setSqlUser(String newSqlUser) {
		sqlUser = newSqlUser;
		modify();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/20/00 1:53:58 PM)
	 * @param newUserName String
	 */
	public void setUserName(String newUserName) {
		userName = newUserName;
		modify();
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public boolean getLocalizedSpeedData() {
		return localizedSpeedData;
	}

	public void setLocalizedSpeedData(boolean localizedSpeedData) {
		this.localizedSpeedData = localizedSpeedData;
	}

	public HashMap getExternalAppConfigMap() {
		return externalAppConfigMap;
	}

	public void setExternalAppConfigMap(HashMap externalAppConfigMap) {
		this.externalAppConfigMap = externalAppConfigMap;
	}
//
//	public static String getURL() {
//		return URL;
//	}
//
//	public static void setURL(String url) {
//		URL = url;
//	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		modify();
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	public String getCaseListDisplayPreneed() {
		return caseListDisplayPreneed;
	}

	public void setCaseListDisplayPreneed(String caseListDisplayPreneed) {
		this.caseListDisplayPreneed = caseListDisplayPreneed;
	}

	public String getCaseListDisplayVoided() {
		return caseListDisplayVoided;
	}

	public void setCaseListDisplayVoided(String caseListDisplayVoided) {
		this.caseListDisplayVoided = caseListDisplayVoided;
	}

	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	/**
	 * @return Returns the companyID.
	 */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID The companyID to set.
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
		modify();
	}

	/**
	 * @return Returns the changePassword.
	 */
	public boolean isChangePassword() {
		return changePassword;
	}

	/**
	 * @param changePassword The changePassword to set.
	 */
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
		modify();
	}

	/**
	 * @return Returns the lockoutTimestamp.
	 */
	public Timestamp getLockedTimestamp() {
		return lockoutTimestamp;
	}

	/**
	 * @param lockoutTimestamp The lockoutTimestamp to set.
	 */
	public void setLockedTimestamp(Timestamp lockoutTimestamp) {
		this.lockoutTimestamp = lockoutTimestamp;
		modify();
	}

	/**
	 * @return Returns the userLocked.
	 */
	public boolean isUserLocked() {
		return userLocked;
	}

	/**
	 * @param userLocked The userLocked to set.
	 */
	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
		modify();
	}

	/**
	 * @return Returns the passwordTimestamp.
	 */
	public Timestamp getPasswordTimestamp() {
		return passwordTimestamp;
	}

	/**
	 * @param passwordTimestamp The passwordTimestamp to set.
	 */
	public void setPasswordTimestamp(Timestamp passwordTimestamp) {
		this.passwordTimestamp = passwordTimestamp;
		modify();
	}

	public String getDbLookup() {
		return dbLookup;
	}

	public void setDbLookup(String dbLookup) {
		this.dbLookup = dbLookup;
		modify();
	}

	/**
	 * @return the currentCemPropID
	 */
	public int getCurrentCemPropID() {
		return currentCemPropID;
	}

	/**
	 * @param currentCemPropID the currentCemPropID to set
	 */
	public void setCurrentCemPropID(int currentCemPropID) {
		this.currentCemPropID = currentCemPropID;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.beans.DbUserSession#getRoles()
	 */
	public ArrayList <RolesMembershipDTO> getRoles() {
		return roles;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.beans.DbUserSession#setRoles(ArrayList)
	 */
	public void setRoles(ArrayList <RolesMembershipDTO> roles) {
		this.roles = roles;		
	}

	/**
	 * @return the userLocations
	 */
	public ArrayList<DbLocation> getUserLocations() {
		return userLocations;
	}

	/**
	 * @param userLocations the userLocations to set
	 */
	public void setUserLocations(ArrayList<DbLocation> userLocations) {
		this.userLocations = userLocations;
	}
	
	public boolean isUserAssignedRole (long roleID) {

    	for ( RolesMembershipDTO userRole : roles ) {
    		
    		if ( userRole.getRoleID() == roleID ) {
   				return(true); 
    		}
		}
        
    	return ( false );
	}

	/**
	 * @return the accountingVacationFlag
	 */
	public boolean isAccountingVacationFlag() {
		return accountingVacationFlag;
	}

	/**
	 * @return the accountingVacationUserID
	 */
	public long getAccountingVacationUserID() {
		return accountingVacationUserID;
	}

	/**
	 * @return the lockoutTimestamp
	 */
	public Timestamp getLockoutTimestamp() {
		return lockoutTimestamp;
	}

	/**
	 * @param accountingVacationFlag the accountingVacationFlag to set
	 */
	public void setAccountingVacationFlag(boolean accountingVacationFlag) {
		this.accountingVacationFlag = accountingVacationFlag;
		modify();
	}

	/**
	 * @param accountingVacationUserID the accountingVacationUserID to set
	 */
	public void setAccountingVacationUserID(long accountingVacationUserID) {
		this.accountingVacationUserID = accountingVacationUserID;
	}

	/**
	 * @param lockoutTimestamp the lockoutTimestamp to set
	 */
	public void setLockoutTimestamp(Timestamp lockoutTimestamp) {
		this.lockoutTimestamp = lockoutTimestamp;
		modify();
	}

	/**
	 * @return the limitExternalVendor
	 */
	public double getLimitExternalVendor() {
		return limitExternalVendor;
	}

	/**
	 * @return the limitInternalVendor
	 */
	public double getLimitInternalVendor() {
		return limitInternalVendor;
	}

	/**
	 * @return the userLimitOverride
	 */
	public boolean isUserLimitOverride() {
		return userLimitOverride;
	}

	/**
	 * @param limitExternalVendor the limitExternalVendor to set
	 */
	public void setLimitExternalVendor(double limitExternalVendor) {
		this.limitExternalVendor = limitExternalVendor;
		modify();
	}

	/**
	 * @param limitInternalVendor the limitInternalVendor to set
	 */
	public void setLimitInternalVendor(double limitInternalVendor) {
		this.limitInternalVendor = limitInternalVendor;
		modify();
	}

	/**
	 * @param userLimitOverride the userLimitOverride to set
	 */
	public void setUserLimitOverride(boolean userLimitOverride) {
		this.userLimitOverride = userLimitOverride;
		modify();
	}

	/**
	 * @return the configID
	 */
	public long getConfigID() {
		return configID;
	}

	/**
	 * @param configID the configID to set
	 */
	public void setConfigID(long configID) {
		this.configID = configID;
	}

	/**
	 * @return the localeCountry
	 */
	public String getLocaleCountry() {
		return localeCountry;
	}

	/**
	 * @param localeCountry the localeCountry to set
	 */
	public void setLocaleCountry(String localeCountry) {
		this.localeCountry = localeCountry;
	}

	/**
	 * @return the securityAccountingInterface
	 */
	public short getSecurityAccountingInterface() {
		return securityAccountingInterface;
	}

	/**
	 * @param securityAccountingInterface the securityAccountingInterface to set
	 */
	public void setSecurityAccountingInterface(short securityAccountingInterface) {
		this.securityAccountingInterface = securityAccountingInterface;
		modify();
	}

	/**
	 * @return the securitySpeedData
	 */
	public short getSecuritySpeedData() {
		return securitySpeedData;
	}

	/**
	 * @param securitySpeedData the securitySpeedData to set
	 */
	public void setSecuritySpeedData(short securitySpeedData) {
		this.securitySpeedData = securitySpeedData;
		modify();
	}

	/**
	 * @return the securityArrangerManagement
	 */
	public short getSecurityArrangerManagement() {
		return securityArrangerManagement;
	}

	/**
	 * @param securityArrangerManagement the securityArrangerManagement to set
	 */
	public void setSecurityArrangerManagement(short securityArrangerManagement) {
		this.securityArrangerManagement = securityArrangerManagement;
		modify();
	}

	/**
	 * @return the securityFormsAvaialble
	 */
	public short getSecurityFormsAvaialble() {
		return securityFormsAvaialble;
	}

	/**
	 * @param securityFormsAvaialble the securityFormsAvaialble to set
	 */
	public void setSecurityFormsAvaialble(short securityFormsAvaialble) {
		this.securityFormsAvaialble = securityFormsAvaialble;
		modify();
	}

	/**
	 * @return the securityGLSalesAccount
	 */
	public short getSecurityGLSalesAccount() {
		return securityGLSalesAccount;
	}

	/**
	 * @param securityGLSalesAccount the securityGLSalesAccount to set
	 */
	public void setSecurityGLSalesAccount(short securityGLSalesAccount) {
		this.securityGLSalesAccount = securityGLSalesAccount;
		modify();
	}

	public short getSecurityAdjustFinancial() {
		return securityAdjustFinancial;
	}

	public void setSecurityAdjustFinancial(short securityAdjustFinancial) {
		this.securityAdjustFinancial = securityAdjustFinancial;
		modify();
	}

	public short getSecurityBank() {
		return securityBank;
	}

	public void setSecurityBank(short securityBank) {
		this.securityBank = securityBank;
		modify();
	}

	public short getSecurityFdmsAdmin() {
		return securityFdmsAdmin;
	}

	public void setSecurityFdmsAdmin(short securityFdmsAdmin) {
		this.securityFdmsAdmin = securityFdmsAdmin;
		modify();
	}

	public short getSecurityFdmsNetwork() {
		return securityFdmsNetwork;
	}

	public void setSecurityFdmsNetwork(short securityFdmsNetwork) {
		this.securityFdmsNetwork = securityFdmsNetwork;
		modify();
	}

	public short getSecurityFdmsDashboard() {
		return securityFdmsDashboard;
	}

	public void setSecurityFdmsDashboard(short securityFdmsDashboard) {
		this.securityFdmsDashboard = securityFdmsDashboard;
		modify();
	}

	/**
	 * @return the securityEregisterbook
	 */
	public short getSecurityEregisterbook() {
		return securityEregisterbook;
	}

	/**
	 * @param securityEregisterbook the securityEregisterbook to set
	 */
	public void setSecurityEregisterbook(short securityEregisterbook) {
		this.securityEregisterbook = securityEregisterbook;
	}

	
	/**
	 * @return the securityPriceDescriptionFinancial
	 */
	public short getSecurityPriceDescriptionFinancial() {
		return securityPriceDescriptionFinancial;
	}

	/**
	 * @param securityPriceDescriptionFinancial the securityPriceDescriptionFinancial to set
	 */
	public void setSecurityPriceDescriptionFinancial(short securityPriceDescriptionFinancial) {
		this.securityPriceDescriptionFinancial = securityPriceDescriptionFinancial;
	}

	public short getSecurityFdmsWebservice() {
		return securityFdmsWebservice;
	}

	public void setSecurityFdmsWebservice(short securityFdmsWebservice) {
		this.securityFdmsWebservice = securityFdmsWebservice;
		modify();
	}

	public short getSecurityFddeWebservice() {
		return securityFddeWebservice;
	}

	public void setSecurityFddeWebservice(short securityFddeWebservice) {
		this.securityFddeWebservice = securityFddeWebservice;
		modify();
	}

	public short getSecurityDashboardReport() {
		return securityDashboardReport;
	}

	public void setSecurityDashboardReport(short securityDashboardReport) {
		this.securityDashboardReport = securityDashboardReport;
		modify();
	}

	/**
	 * @return the securityEasyPayment
	 */
	public short getSecurityEasyPayment() {
		return securityEasyPayment;
	}

	/**
	 * @param securityEasyPayment the securityEasyPayment to set
	 */
	public void setSecurityEasyPayment(short securityEasyPayment) {
		this.securityEasyPayment = securityEasyPayment;
	}

	@Override
	public short getSecurityEnableFinancialChange() {
		return this.securityEnableFinancialChange;
	}
	
	@Override
	public void setSecurityEnableFinancialChange(short newSecurityEnableFinancialChange) {
		this.securityEnableFinancialChange = newSecurityEnableFinancialChange;
	}

}