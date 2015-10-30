package com.aldorsolutions.webfdms.beans;

/**
 * This class handles SQL database access for the User business object.
 * Specificially, it provides the SQL code needed by DbUser
 * Creation date: (11/1/2001 3:57:54 PM)
 * @author: 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.Persistent;
public class DbUserPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	// Column Headers for this table
	static public final String NAME       = "Name";
	static public final String PASSWORD   = "Password";
	static public final String USER_ID    = "UserID";
	static public final String REGION     = "Region";
	static public final String ADMIN      = "Administrator";
	static public final String ATNEED     = "Atneed";
	static public final String PRENEED    = "Preneed";
	static public final String FINANCIAL  = "Financial";
	static public final String PAYMENTS   = "Payments";
	static public final String ACCTSREC   = "AcctsRec";
	static public final String FORMS      = "Forms";
	static public final String REPORTS    = "Reports";
	static public final String DELETE     = "DeleteCases";
	static public final String INITIALS   = "Initials";
	static public final String INVENTORY  = "Inventory";
	static public final String VIEWONLY   = "ViewOnly";
	static public final String ACCOUNTINGINTERFACE = "accountingInterface";
	static public final String EASYPAYMENT= "easyPayment";
	//added by haranesh patel
	static public final String EREGISTERBOOK= "eregisterbook";
	static public final String PRICEDESCRIPTIONFINANCIAL= "priceDescriptionFinancial";
	static public final String SPEEDDATA  = "speedData";
	static public final String ARRANGERMANAGEMENT = "arrangerManagement";
	static public final String FORMSAVAILABLE = "formsAvailable";
	static public final String GLSALESACCOUNT = "glSalesAccount";
	static public final String ADJUSTFINANCIAL = "AdjustFinancial";
	static public final String BANK = "Bank";
	static public final String ENABLE_FINANCIAL_CHANGE = "EnableFinancialChange";
	static public final String FDMSNETWORK = "FdmsNetwork";
	static public final String FDMSDASHBOARD = "FdmsDashboard";
	static public final String FDMSWEBSERVICE = "FdmsWebservice";
	static public final String FDDEWEBSERVICE = "FddeWebservice";
	static public final String FDMSADMIN = "FdmsAdmin";
	static public final String DATAURL    = "DataUrl";
	static public final String DELETECODE = "DeleteCode";
	static public final String EMAILADDR  = "EmailAddr";
	static public final String FIRSTNAME  = "FirstName";
	static public final String LASTNAME   = "LastName";
	static public final String SQLUSER    = "sqlUser";
	static public final String SQLPW   	  = "sqlPassword";
	static public final String CASELISTSORTORDER = "caseListSortOrder";
	static public final String CASELISTPERSCREEN = "caseListPerScreen";
	static public final String STATE	  = "StateId";
	static public final String CASELISTDISPLAYPRENEED = "CaseListDisplayPreneed";
	static public final String CASELISTDISPLAYVOIDED = "CaseListDisplayVoided";
	static public final String TOS = "TOS";
	static public final String CHANGEPASSWORD = "chgPassword";
	static public final String USERLOCKED = "userLocked";
	static public final String LOCKOUTTMSTMP = "lockoutTmstmp";
	static public final String COMPANYID = "companyID";
	static public final String PASSWORDTMSTMP = "passwordTmstmp";
	static public final String DBLOOKUP = "dbLookup";
	static public final String CEMETERY = "enableCemetery";
	static public final String FUNERAL = "enableFuneral";
	static public final String ACCT_VACATION_FLAG = "acctVacationFlag";
	static public final String ACCT_VACATION_USERID = "acctVacationUserID";
	static public final String USERLIMITOVERRIDE = "userLimitOverride";
	static public final String LIMITINTERNALVENDOR = "limitInternalVendor";
	static public final String LIMITEXTERNALVENDOR = "limitExternalVendor";
	public static final String DASHBOARDREPORT = "DashboardReport";
	
	Logger logger = Logger.getLogger(DbUserPeer.class.getName());
	
	/**
	 * Provides the SQL used to insert a customer object into the database.
	 * @param p the Customer being inserted
	 * @return the SQL to insert the customer
	 */
	protected PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException{
		Connection connection = null;
		PreparedStatement pstmt=null;
		int col=0;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbUser user=(DbUser)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"INSERT INTO usersecurity (Name, Password, UserID, Region, Administrator, Atneed, "
					+ "Preneed, Financial, Payments, AcctsRec, Forms, Reports, DeleteCases, "
					+ "Initials, Inventory, ViewOnly, accountingInterface, speedData, arrangerManagement, " 
					+ "formsAvailable, glSalesAccount, AdjustFinancial, Bank, EnableFinancialChange, DashboardReport" 
					+ "FdmsNetwork, FdmsDashboard, FdmsWebservice, FddeWebservice, " 
					+ "DataUrl, FirstName, LastName, EmailAddr,"
					+ "sqlUser, sqlPassword, caseListSortOrder, caseListPerScreen, StateId, "
					+ "CaseListDisplayPreneed, CaseListDisplayVoided, chgPassword, userLocked, FdmsAdmin, " 
					+ "lockoutTmstmp, companyID, passwordTmstmp, dbLookup, " + CEMETERY + ", " 
					+ FUNERAL + ", " + ACCT_VACATION_FLAG + ", " + ACCT_VACATION_USERID + ", " 
					+ USERLIMITOVERRIDE + ", " + LIMITINTERNALVENDOR + ", " + LIMITEXTERNALVENDOR + ", easyPayment ," + EREGISTERBOOK + ", "+ PRICEDESCRIPTIONFINANCIAL +" ) "
					+ "VALUES (?, ?, 0, ?, ?, ?, ?, ?, ?, ?, " +
							  "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
							  "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
							  "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(++col, user.getUserName());	
			pstmt.setString(++col, user.getPassword());
			pstmt.setInt   (++col, user.getRegion());
			pstmt.setShort (++col, user.getSecurityAdmin());
			pstmt.setShort (++col, user.getSecurityAtneed());
			pstmt.setShort (++col, user.getSecurityPreneed());
			pstmt.setShort (++col, user.getSecurityFinancial());
			pstmt.setShort (++col, user.getSecurityPayments());
			pstmt.setShort (++col, user.getSecurityAcctsRec());
			pstmt.setShort (++col, user.getSecurityForms());
			pstmt.setShort (++col, user.getSecurityReports());
			pstmt.setShort (++col, user.getSecurityDelete());
			pstmt.setString(++col, user.getInitials());
			pstmt.setShort (++col, user.getSecurityInventory());
			pstmt.setShort (++col, user.getSecurityViewOnly());
			pstmt.setShort (++col, user.getSecurityAccountingInterface());
			pstmt.setShort (++col, user.getSecuritySpeedData());
			pstmt.setShort (++col, user.getSecurityArrangerManagement());
			pstmt.setShort (++col, user.getSecurityFormsAvaialble());
			pstmt.setShort (++col, user.getSecurityGLSalesAccount());
			pstmt.setShort (++col, user.getSecurityAdjustFinancial());
			pstmt.setShort (++col, user.getSecurityBank());
			pstmt.setShort (++col, user.getSecurityEnableFinancialChange());
			pstmt.setShort (++col, user.getSecurityDashboardReport());
			pstmt.setShort (++col, user.getSecurityFdmsNetwork());
			pstmt.setShort (++col, user.getSecurityFdmsDashboard());
			pstmt.setShort (++col, user.getSecurityFdmsWebservice());
			pstmt.setShort (++col, user.getSecurityFddeWebservice());
			pstmt.setString(++col, user.getDataUrl());
			pstmt.setString(++col, user.getFirstName());
			pstmt.setString(++col, user.getLastName());
			pstmt.setString(++col, user.getEmailAddr());
			pstmt.setString(++col, user.getSqlUser());
			pstmt.setString(++col, user.getSqlPassword());
			pstmt.setString(++col, user.getCaseListSortOrder());
			pstmt.setInt   (++col, user.getCaseListPerScreen());
			pstmt.setString(++col, user.getState());
			pstmt.setString(++col, user.getCaseListDisplayPreneed());
			pstmt.setString(++col, user.getCaseListDisplayVoided());
			pstmt.setBoolean(++col, user.isChangePassword());
			pstmt.setBoolean(++col, user.isUserLocked());
			pstmt.setShort (++col, user.getSecurityFdmsAdmin());
			pstmt.setTimestamp(++col, user.getLockedTimestamp());
			pstmt.setLong  (++col, user.getCompanyID());
			pstmt.setTimestamp(++col, user.getPasswordTimestamp());
			pstmt.setString(++col, user.getDbLookup());
			pstmt.setShort (++col, user.getSecurityCemetery());
			pstmt.setShort (++col, user.getSecurityFuneral());
			pstmt.setBoolean(++col, user.isAccountingVacationFlag());
			pstmt.setLong  (++col, user.getAccountingVacationUserID());
			pstmt.setBoolean(++col, user.isUserLimitOverride());
			pstmt.setDouble(++col, user.getLimitInternalVendor());
			pstmt.setDouble(++col, user.getLimitExternalVendor());
			pstmt.setShort(++col, user.getSecurityEasyPayment());
			pstmt.setShort(++col, user.getSecurityEregisterbook());
			pstmt.setShort(++col, user.getSecurityPriceDescriptionFinancial());
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbUserPeer.Insert",e);
		}
	}
	
	/**
	 * At this time, there is only one USER class to be restored.
	 * Later, we may have more than one USER class and this method
	 * would determine which to return.
	 */
	public String getPersistentClass(Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbUser";
	}
	
	/**
	 * Provides the SQL used to "logically" delete a user.
	 * We do not allow physical deletions.
	 * @param p the Customer being deleted
	 * @return the SQL used to delete the customer
	 */
	public PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t,Persistent p) 
		throws com.aldorsolutions.webfdms.database.PersistenceException{
		PreparedStatement pstmt=null;
		Connection connection = null;
		try {
			DbUser user=(DbUser)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"UPDATE usersecurity SET DeleteCode=? where UserID=?");
			pstmt.setString(1, "D");	
			pstmt.setInt(2,user.getId());
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbUserPeer.Remove",e);
		}
	}
	
	/**
	 * Provides the SQL used to restore a USER from the database by an ID#
	 * @param p the Customer being restored (only the ID field is filled in)
	 * @return the SQL used to restore that customer
	 */
	public String getRestoreSql(Persistent p) {
		// SELECT * from t_customer WHERE cust_id = 1
		return "SELECT * FROM usersecurity WHERE UserID= "
		+ p.getId();
	}
	
	/**
	 * Provides the SQL used to restore a USER from the database by user name.
	 * @param p the Customer being restored (only the ID field is filled in)
	 * @return the SQL used to restore that customer
	 */
	public String getRestoreSql(Persistent p, String name) {

		return "SELECT * FROM usersecurity WHERE name='" + name + "'";
	}
	
	/**
	 * Provides the SQL used to update a customer in the database
	 * @param p the Customer being updated
	 * @return the SQL used to UPDATE that customer
	 */
	public PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, Persistent p) 
		throws com.aldorsolutions.webfdms.database.PersistenceException{
		Connection connection = null;
		try {
			DbUser user=(DbUser)p;
			connection = t.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE usersecurity SET Name=?, Password=?, Region=?, Administrator=?, Atneed=?, "
					+ "Preneed=?, Financial=?, Payments=?, AcctsRec=?, Forms=?, Reports=?, "
					+ "DeleteCases=?, Initials=?, Inventory=?, ViewOnly=?, accountingInterface=?, "
					+ "speedData=?, arrangerManagement=?, formsAvailable=?, glSalesAccount=?, AdjustFinancial=?, Bank = ?, EnableFinancialChange = ?, " 
					+ "DashboardReport = ?, FdmsNetwork=?, FdmsDashboard=?, FdmsWebservice=?, FddeWebservice=?, DataUrl=?, "
					+ "FirstName=?,LastName=?,EmailAddr=?, sqlUser=?, sqlPassword=?, "
					+ "caseListSortOrder=?, caseListPerScreen=?, StateId=?, "
					+ "CaseListDisplayPreneed=?, CaseListDisplayVoided=?, chgPassword = ?, " 
					+ "userLocked = ?, FdmsAdmin = ?, lockoutTmstmp = ?, companyID = ?, passwordTmstmp = ?, " 
					+ "dbLookup = ?, " + CEMETERY + " = ?, " + FUNERAL + " = ?, " 
					+ ACCT_VACATION_FLAG + " = ?, " + ACCT_VACATION_USERID + " = ?, " 
					+ USERLIMITOVERRIDE + " = ?, " + LIMITINTERNALVENDOR + " = ?, " 
					+ LIMITEXTERNALVENDOR + " = ?, easyPayment=?, " + EREGISTERBOOK + "=?,"+ PRICEDESCRIPTIONFINANCIAL +"=?  where UserID=?");
			int col=0;
			pstmt.setString (++col, user.getUserName());	
			pstmt.setString (++col, user.getPassword());
			pstmt.setInt    (++col, user.getRegion());
			pstmt.setShort  (++col, user.getSecurityAdmin());
			pstmt.setShort  (++col, user.getSecurityAtneed());
			pstmt.setShort  (++col, user.getSecurityPreneed());
			pstmt.setShort  (++col, user.getSecurityFinancial());
			pstmt.setShort  (++col, user.getSecurityPayments());
			pstmt.setShort  (++col, user.getSecurityAcctsRec());
			pstmt.setShort  (++col, user.getSecurityForms());
			pstmt.setShort  (++col, user.getSecurityReports());
			pstmt.setShort  (++col, user.getSecurityDelete());
			pstmt.setString (++col, user.getInitials());
			pstmt.setShort  (++col, user.getSecurityInventory());
			pstmt.setShort  (++col, user.getSecurityViewOnly());
			pstmt.setShort  (++col, user.getSecurityAccountingInterface());
			pstmt.setShort  (++col, user.getSecuritySpeedData());
			pstmt.setShort  (++col, user.getSecurityArrangerManagement());
			pstmt.setShort  (++col, user.getSecurityFormsAvaialble());
			pstmt.setShort  (++col, user.getSecurityGLSalesAccount());
			pstmt.setShort  (++col, user.getSecurityAdjustFinancial());
			pstmt.setShort (++col, user.getSecurityBank());
			pstmt.setShort (++col, user.getSecurityEnableFinancialChange());
			pstmt.setShort (++col, user.getSecurityDashboardReport());
			pstmt.setShort (++col, user.getSecurityFdmsNetwork());
			pstmt.setShort (++col, user.getSecurityFdmsDashboard());
			pstmt.setShort (++col, user.getSecurityFdmsWebservice());
			pstmt.setShort (++col, user.getSecurityFddeWebservice());
			pstmt.setString (++col, user.getDataUrl());
			pstmt.setString (++col, user.getFirstName());
			pstmt.setString (++col, user.getLastName());
			pstmt.setString (++col, user.getEmailAddr());
			pstmt.setString (++col, user.getSqlUser());
			pstmt.setString (++col, user.getSqlPassword());
			pstmt.setString (++col, user.getCaseListSortOrder());
			pstmt.setInt    (++col, user.getCaseListPerScreen());
			pstmt.setString (++col, user.getState());
			pstmt.setString (++col, user.getCaseListDisplayPreneed());
			pstmt.setString (++col, user.getCaseListDisplayVoided());
			pstmt.setBoolean(++col, user.isChangePassword());
			pstmt.setBoolean(++col, user.isUserLocked());
			pstmt.setShort (++col, user.getSecurityFdmsAdmin());
			pstmt.setTimestamp(++col, user.getLockedTimestamp());
			pstmt.setLong   (++col, user.getCompanyID());
			pstmt.setTimestamp(++col, user.getPasswordTimestamp());
			pstmt.setString (++col, user.getDbLookup());
			pstmt.setShort  (++col, user.getSecurityCemetery());
			pstmt.setShort  (++col, user.getSecurityFuneral());
			pstmt.setBoolean(++col, user.isAccountingVacationFlag());
			pstmt.setLong   (++col, user.getAccountingVacationUserID());
			pstmt.setBoolean(++col, user.isUserLimitOverride());
			pstmt.setDouble (++col, user.getLimitInternalVendor());
			pstmt.setDouble (++col, user.getLimitExternalVendor());
			pstmt.setShort(++col,user.getSecurityEasyPayment());
			pstmt.setShort(++col,user.getSecurityEregisterbook());
			pstmt.setShort(++col,user.getSecurityPriceDescriptionFinancial());
			pstmt.setInt(++col, user.getId());
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbUserPeer.Update",e);
		}
	}
}
