/*
 * InvVersionDAO.java
 *
 * Created on February 1, 2005, 10:35 AM
 */

package com.aldorsolutions.webfdms.admin.user.dao;

/**
 *
 * @author Guadalupe Garcia
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserSessionDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;


public class UserDAO extends DAO {
    
    private Logger logger = Logger.getLogger(UserDAO.class.getName());
    public static int ACTIVE = 1;
    public static int DELETED = 0;
    
    public UserDAO( ) {
    	super();
    }
    
    private String getSelectFields () {
    	return " Name, Password, UserID, Region, Administrator, " +
		"   Atneed, Preneed, Financial, Payments, AcctsRec, " +
		"	Forms, Reports, DeleteCases, Initials, Inventory, " +
		"	ViewOnly, accountingInterface, speedData, arrangerManagement, " +
		" formsAvailable, glSalesAccount, AdjustFinancial, Bank, EnableFinancialChange, DashboardReport,FdmsNetwork, FdmsDashboard, " +
		" FdmsWebservice, FddeWebservice, DataUrl, DeleteCode, FirstName, LastName, " +
		"	EmailAddr, sqlUser, sqlPassword, caseListSortOrder, " +
		"	caseListPerScreen, dbLookup, chgPassword, userLocked, FdmsAdmin, " +
		"	lockoutTmstmp, companyID, acctVacationFlag, acctVacationUserID, " +
		"	userLimitOverride, limitInternalVendor, limitExternalVendor,easyPayment,eregisterbook,priceDescriptionFinancial ";
    }
    
    private UserDTO populateUserDTO (ResultSet rs) throws SQLException {
    	int col = 0;
    	UserDTO user = new UserDTO();
    	user.setName(rs.getString(++col));
        user.setPassword(rs.getString(++col));
        user.setUserId(rs.getLong(++col));
        user.setRegionId(rs.getInt(++col));
        user.setAdministrator(rs.getInt(++col));
        user.setAtneed(rs.getInt(++col));
        user.setPreneed(rs.getInt(++col));
        user.setFinancial(rs.getInt(++col));
        user.setPayments(rs.getInt(++col));
        user.setAcctsRec(rs.getInt(++col));
        user.setForms(rs.getInt(++col));
        user.setReports(rs.getInt(++col));
        user.setDeleteCases(rs.getInt(++col));
        user.setInitials(rs.getString(++col));
        user.setInventory(rs.getInt(++col));
        user.setViewOnly(rs.getInt(++col));
        user.setAccountingInterface(rs.getInt(++col));
        user.setSpeedData(rs.getInt(++col));
        user.setArrangerManager(rs.getInt(++col));
        user.setFormsAvailable(rs.getInt(++col));
        user.setGlSalesAccount(rs.getInt(++col));
        user.setAdjustFinancial(rs.getInt(++col));
        user.setBank(rs.getInt(++col));
        user.setEnableFinancialChange(rs.getInt(++col));
        user.setDashboardReport(rs.getInt(++col));
        user.setFdmsNetwork(rs.getInt(++col));
        user.setFdmsDashboard(rs.getInt(++col));
        user.setFdmsWebservice(rs.getInt(++col));
        user.setFddeWebservice(rs.getInt(++col));
        user.setDbUrl(rs.getString(++col));
        user.setDeleteCode(rs.getString(++col));
        user.setFirstName(rs.getString(++col));
        user.setLastName(rs.getString(++col));
        user.setEmail(rs.getString(++col));
        user.setDbUsername(rs.getString(++col));
        user.setDbPassword(rs.getString(++col));
        user.setCaseListOrder(rs.getString(++col));
        user.setCaseListLimit(rs.getInt(++col));
        user.setDbLookup(rs.getString(++col));
        user.setChangePassword(rs.getBoolean(++col));
        user.setUserLocked(rs.getBoolean(++col));
        user.setFdmsAdmin(rs.getInt(++col));
        user.setLockoutTmstmp(rs.getTimestamp(++col));
        user.setCompanyID(rs.getInt(++col));
        user.setAccountingVacationFlag(rs.getBoolean(++col));
        user.setAccountingVacationUserID(rs.getLong(++col));
        user.setUserLimitOverride(rs.getBoolean(++col));
        user.setLimitInternalVendor(rs.getDouble(++col));
        user.setLimitExternalVendor(rs.getDouble(++col));
        user.setEasyPayment(rs.getInt(++col));
		//added by haranesh patel
        user.setEregisterbook(rs.getInt(++col));
        user.setPriceDescriptionFinancial(rs.getInt(++col));
        return ( user );
    }
    
    public ArrayList <UserSessionDTO> getUsers() {
        ArrayList <UserSessionDTO> users = new ArrayList <UserSessionDTO> ();
        UserSessionDTO user = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT Name, ");
            sql.append("DataUrl ");
            sql.append("FROM usersecurity ");
            sql.append("ORDER BY Name");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
                user = new UserSessionDTO();
                user.setUsername(rs.getString(1));
                user.setDatabase(rs.getString(2));
                users.add(user);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getUsers() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUsers() : ", e);
        } finally {
            closeConnection();
        }
        
        return users;
    }

    
    public ArrayList <UserDTO> getUsers(int status) {
        
        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select " + getSelectFields() );
            sql.append("FROM usersecurity ");
            sql.append("WHERE ");
            if (status == ACTIVE)
                sql.append("(DeleteCode IS NULL) OR (DeleteCode = '') ");
            else
                sql.append("(DeleteCode = 'D') ");
            
            sql.append("ORDER BY Name");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {  
            	UserDTO user = populateUserDTO( rs );
                users.add(user);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getUsers() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUsers() : ", e);
        } finally {
            closeConnection();
        }
        
        return users;        
    }
    
    public ArrayList <UserDTO> getUsers(int status, long companyID) {
        
        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT " + getSelectFields() );
            sql.append("FROM usersecurity ");
            sql.append("WHERE ");
            if (status == ACTIVE) {
                sql.append("((DeleteCode IS NULL) OR (DeleteCode = '')) ");
            }
            else {
                sql.append("(DeleteCode = 'D') ");
            }
            sql.append("AND companyID = ? ");
            
            sql.append("ORDER BY Name");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            

            while (rs.next()) {  
            	UserDTO user = populateUserDTO( rs );
                users.add(user);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getUsers() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUsers() : ", e);
        } finally {
            closeConnection();
        }
        
        return users;        
    }

    public ArrayList <UserDTO> getUsersByRoleAssigned(int status, long companyID, long roleID) {
        
        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
        	
        	String fields = " usersecurity.Name, usersecurity.Password, usersecurity.UserID, usersecurity.Region, Administrator, " +
				    		"   Atneed, Preneed, Financial, Payments, AcctsRec, " +
				    		"	Forms, Reports, DeleteCases, Initials, Inventory, " +
				    		"	ViewOnly, accountingInterface,  speedData, arrangerManagement, " +
				    		" formsAvailable, glSalesAccount, AdjustFinancial, Bank, EnableFinancialChange, DashboardReport, FdmsNetwork, FdmsDashboard, " +
				    		" FdmsWebservice, FddeWebservice, DataUrl, DeleteCode, FirstName, LastName, " +
				    		"	EmailAddr, sqlUser, sqlPassword, caseListSortOrder, " +
				    		"	caseListPerScreen, dbLookup, chgPassword, userLocked, FdmsAdmin, " +
				    		"	lockoutTmstmp, companyID, acctVacationFlag, acctVacationUserID, " +
				    		"	userLimitOverride, limitInternalVendor, limitExternalVendor ";
        	
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT " + fields );
            sql.append("FROM usersecurity, ums_rolesmembership ");
            sql.append("WHERE ( usersecurity.UserID = ums_rolesmembership.userID ) AND ");
            
            if (status == ACTIVE) {
                sql.append("((usersecurity.DeleteCode IS NULL) OR (usersecurity.DeleteCode = '')) ");
            }
            else {
                sql.append("(usersecurity.DeleteCode = 'D') ");
            }
            
            sql.append("AND ( ums_rolesmembership.roleID = ? AND usersecurity.companyID = ? ) ");
            sql.append("ORDER BY Name");
            
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, roleID);
            statement.setLong(2, companyID);
            rs = statement.executeQuery();
            

            while (rs.next()) {  
            	UserDTO user = populateUserDTO( rs );
                users.add(user);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getUsersByRoleAssigned() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUsersByRoleAssigned() : ", e);
        } finally {
            closeConnection();
        }
        
        return users;        
    }
  
    public ArrayList <UserDTO> getUserByVacationAccountant(long userId) {
        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT " + getSelectFields() );
            sql.append("FROM usersecurity WHERE (acctVacationFlag = 1 ");
         	sql.append("and acctVacationUserID = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            
            rs = statement.executeQuery();

            while (rs.next()) {  
            	UserDTO user = populateUserDTO( rs );
                users.add(user);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getUserByVacationAccountant() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUserByVacationAccountant() : ", e);
        } finally {
            closeConnection();
        }
        
        return users;
    }
    
    public UserDTO getUser(long userId) {
        UserDTO user = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT " + getSelectFields() );
            sql.append("FROM usersecurity WHERE (UserID = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            
            rs = statement.executeQuery();

            if (rs.next()) {  
            	user = populateUserDTO( rs );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getUser() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUser() : ", e);
        } finally {
            closeConnection();
        }
        
        return user;
    }
    
    public boolean addUser(UserDTO user)throws Exception {
    	return addUser(user, false);
    }
    
    public boolean addUser(UserDTO user, boolean isGlobal)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO usersecurity (Name, Password, UserID, Region, Administrator, " +
            		"   Atneed, Preneed, Financial, Payments, AcctsRec, " +
            		"	Forms, Reports, DeleteCases, Initials, Inventory, " +
            		"	ViewOnly, accountingInterface,  speedData, arrangerManagement, " +
            		" formsAvailable, glSalesAccount, AdjustFinancial, Bank, EnableFinancialChange, DashboardReport, FdmsNetwork, FdmsDashboard, " +
            		" FdmsWebservice, FddeWebservice, DataUrl, DeleteCode, FirstName, LastName, " +
            		"	EmailAddr, sqlUser, sqlPassword, caseListSortOrder, " +
            		"	caseListPerScreen, dbLookup, chgPassword, userLocked, FdmsAdmin, " +
            		"	lockoutTmstmp, companyID, acctVacationFlag, acctVacationUserID, " +
            		"	userLimitOverride, limitInternalVendor, limitExternalVendor , easyPayment ,eregisterbook,priceDescriptionFinancial ) ");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            				   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            				   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            				   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? )");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS, isGlobal);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(++col, user.getName());
            statement.setString(++col, user.getPassword());
            statement.setLong(++col, user.getUserId());
            statement.setInt(++col, user.getRegionId());
            statement.setInt(++col, user.getAdministrator());
            statement.setInt(++col, user.getAtneed());
            statement.setInt(++col, user.getPreneed());
            statement.setInt(++col, user.getFinancial());
            statement.setInt(++col, user.getPayments());
            statement.setInt(++col, user.getAcctsRec());
            statement.setInt(++col, user.getForms());
            statement.setInt(++col, user.getReports());
            statement.setInt(++col, user.getDeleteCases());
            statement.setString(++col, user.getInitials());
            statement.setInt(++col, user.getInventory());
            statement.setInt(++col, user.getViewOnly());
            statement.setInt(++col, user.getAccountingInterface());
            statement.setInt(++col, user.getSpeedData());
            statement.setInt(++col, user.getArrangerManager());
            statement.setInt(++col, user.getFormsAvailable());
            statement.setInt(++col, user.getGlSalesAccount());
            statement.setInt(++col, user.getAdjustFinancial());
            statement.setInt(++col, user.getBank());
            statement.setInt(++col, user.getEnableFinancialChange());
            statement.setInt(++col, user.getDashboardReport());
            statement.setInt(++col, user.getFdmsNetwork());
            statement.setInt(++col, user.getFdmsDashboard());
            statement.setInt(++col, user.getFdmsWebservice());
            statement.setInt(++col, user.getFddeWebservice());
            statement.setString(++col, user.getDbUrl());
            statement.setString(++col, user.getDeleteCode());
            statement.setString(++col, user.getFirstName());
            statement.setString(++col, user.getLastName());
            statement.setString(++col, user.getEmail());
            statement.setString(++col, user.getDbUsername());
            statement.setString(++col, user.getDbPassword());
            statement.setString(++col, user.getCaseListOrder());
            statement.setInt(++col, user.getCaseListLimit());
            statement.setString(++col, user.getDbLookup());
            statement.setBoolean(++col, user.isChangePassword());
            statement.setBoolean(++col, user.isUserLocked());
            statement.setInt(++col, user.getFdmsAdmin());
            statement.setTimestamp(++col, user.getLockoutTmstmp());
            statement.setLong(++col, user.getCompanyID());
            statement.setBoolean(++col, user.isAccountingVacationFlag());
            statement.setLong(++col, user.getAccountingVacationUserID());
            statement.setBoolean(++col, user.isUserLimitOverride());
            statement.setDouble(++col, user.getLimitInternalVendor());
            statement.setDouble(++col, user.getLimitExternalVendor());
            statement.setInt(++col, user.getEasyPayment());
            //added by haranesh patel
            statement.setInt(++col, user.getEregisterbook());
            statement.setInt(++col, user.getPriceDescriptionFinancial());
           
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	user.setUserId(rs.getLong(1));
                }
                
                auditDTO.setCompanyId(user.getCompanyID());
                auditDTO.setLocaleId(user.getRegionId());
                auditDTO.setUserId((int)user.getUserId());
                
                insertAudit(user);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addUser() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addUser() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateUser(UserDTO user) {
        boolean updated = false;
        
        try {
        	
        	UserDTO oldUser = getUser(user.getUserId());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE usersecurity SET Name = ?, Password = ?, UserID = ?, Region = ?, Administrator = ?, " +
            		"   Atneed = ?, Preneed = ?, Financial = ?, Payments = ?, AcctsRec = ?, " +
            		"	Forms = ?, Reports = ?, DeleteCases = ?, Initials = ?, Inventory = ?, " +
            		"	ViewOnly = ?, accountingInterface = ?,  speedData = ?, arrangerManagement = ?, " +
            		" formsAvailable = ?, glSalesAccount = ?, AdjustFinancial = ?, Bank = ?, EnableFinancialChange = ?, " +
            		" DashboardReport = ?, FdmsNetwork = ?, FdmsDashboard = ?, FdmsWebservice = ?, FddeWebservice = ?," +
            		" DataUrl = ?, DeleteCode = ?, FirstName = ?, LastName = ?, " +
            		"	EmailAddr = ?, sqlUser = ?, sqlPassword = ?, caseListSortOrder = ?, " +
            		"	caseListPerScreen = ?, dbLookup = ?, chgPassword = ?, userLocked = ?, FdmsAdmin = ?, " +
            		"	lockoutTmstmp = ?, companyID = ?, acctVacationFlag = ?, " +
            		"   acctVacationUserID  = ?, userLimitOverride = ?, limitInternalVendor = ?, " +
            		"	limitExternalVendor = ?, easyPayment=? ,eregisterbook=?,priceDescriptionFinancial=?  ");
            sql.append(" WHERE (UserID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            statement.setString(++col, user.getName());
            statement.setString(++col, user.getPassword());
            statement.setLong(++col, user.getUserId());
            statement.setInt(++col, user.getRegionId());
            statement.setInt(++col, user.getAdministrator());
            statement.setInt(++col, user.getAtneed());
            statement.setInt(++col, user.getPreneed());
            statement.setInt(++col, user.getFinancial());
            statement.setInt(++col, user.getPayments());
            statement.setInt(++col, user.getAcctsRec());
            statement.setInt(++col, user.getForms());
            statement.setInt(++col, user.getReports());
            statement.setInt(++col, user.getDeleteCases());
            statement.setString(++col, user.getInitials());
            statement.setInt(++col, user.getInventory());
            statement.setInt(++col, user.getViewOnly());
            statement.setInt(++col, user.getAccountingInterface());
            statement.setInt(++col, user.getSpeedData());
            statement.setInt(++col, user.getArrangerManager());
            statement.setInt(++col, user.getFormsAvailable());
            statement.setInt(++col, user.getGlSalesAccount());
            statement.setInt(++col, user.getAdjustFinancial());
            statement.setInt(++col, user.getBank());
            statement.setInt(++col, user.getEnableFinancialChange());
            statement.setInt(++col, user.getDashboardReport());
            statement.setInt(++col, user.getFdmsNetwork());
            statement.setInt(++col, user.getFdmsDashboard());
            statement.setInt(++col, user.getFdmsWebservice());
            statement.setInt(++col, user.getFddeWebservice());
            statement.setString(++col, user.getDbUrl());
            statement.setString(++col, user.getDeleteCode());
            statement.setString(++col, user.getFirstName());
            statement.setString(++col, user.getLastName());
            statement.setString(++col, user.getEmail());
            statement.setString(++col, user.getDbUsername());
            statement.setString(++col, user.getDbPassword());
            statement.setString(++col, user.getCaseListOrder());
            statement.setInt(++col, user.getCaseListLimit());
            statement.setString(++col, user.getDbLookup());
            statement.setBoolean(++col, user.isChangePassword());
            statement.setBoolean(++col, user.isUserLocked());
            statement.setInt(++col, user.getFdmsAdmin());
            statement.setTimestamp(++col, user.getLockoutTmstmp());
            statement.setLong(++col, user.getCompanyID());
            statement.setBoolean(++col, user.isAccountingVacationFlag());
            statement.setLong(++col, user.getAccountingVacationUserID());
            statement.setBoolean(++col, user.isUserLimitOverride());
            statement.setDouble(++col, user.getLimitInternalVendor());
            statement.setDouble(++col, user.getLimitExternalVendor());
            statement.setInt(++col,user.getEasyPayment());
			//added by haranesh patel
            statement.setInt(++col,user.getEregisterbook());
            statement.setInt(++col,user.getPriceDescriptionFinancial());
            
            statement.setLong(++col, user.getUserId());

            
            statement.executeUpdate();

            auditDTO.setCompanyId(user.getCompanyID());
            auditDTO.setLocaleId(user.getRegionId());
            auditDTO.setUserId((int)user.getUserId());
            
            
            updateAudit(oldUser, user);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateUser() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateUser() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }
    
    public void deleteUser(long userId) {
        
        try {
        	UserDTO oldUser = getUser(userId);
        	UserDTO newUser = getUser(userId);
        	
            String sql = "UPDATE usersecurity SET DeleteCode = 'D' WHERE (UserID = ?)";
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.executeUpdate();            
            
            auditDTO.setCompanyId(oldUser.getCompanyID());
            auditDTO.setUserId((int)oldUser.getUserId());
            auditDTO.setLocaleId(oldUser.getRegionId());            
            
            newUser.setDeleteCode("D");
            
            deleteAudit(oldUser, newUser);
            
        } catch (SQLException e) {
            logger.error("SQLException in deleteUser() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteUser() : ", e);
        } finally {
            closeConnection();
        }
        
    }
    
    /**
     * Retrieves an ArrayList of all locations user is configured to
     * @param userId
     * @return userLocations
     */
    public ArrayList <UserLocaleDTO> getUserLocales(long userId) {
        
        logger.debug("Entering getUserLocales()");
        ArrayList <UserLocaleDTO> userLocales = new ArrayList <UserLocaleDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT locale_Id FROM userlocations ");
            sql.append("where user_id = ? ");
            sql.append("group by locale_Id");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            
            rs = statement.executeQuery();
            while (rs.next()) {
                UserLocaleDTO dtoLocale = new UserLocaleDTO();
                dtoLocale.setLocaleId(rs.getString(1));
                userLocales.add(dtoLocale);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getUserLocales() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUserLocales() : ", e);
        } finally {
            closeConnection();
        }
        
        return userLocales;
    }    
    
    /**
     * Retrieves an ArrayList of all locations user is configured to
     * @param userId
     * @return userLocations
     */
    public ArrayList <UserLocationDTO> getUserLocations(long userId) {
        
        logger.debug("Entering getUserLocations()");
        ArrayList <UserLocationDTO> userLocations = new ArrayList <UserLocationDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT location_id, default_location, locale_id ");
            sql.append("FROM userlocations ");
            sql.append("WHERE (user_id = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            
            rs = statement.executeQuery();
            while (rs.next()) {
                UserLocationDTO userLocationDto = new UserLocationDTO();
                userLocationDto.setLocationId(rs.getLong(1));   
                userLocationDto.setDefaultLocation(rs.getString(2));
                userLocationDto.setRegionId(rs.getLong(3));
                userLocations.add(userLocationDto);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getUserLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUserLocations() : ", e);
        } finally {
            closeConnection();
        }
        
        return userLocations;
    }    
    
   /**
    *  verify the localeId and locationId which are related and belong to the user.
	 * @param localeid
	 * @param locationid
	 * @param userId
	 * @return
	 */
public boolean  verifyUserLocaleAndLocation(int localeid, int locationid, long userId) {
        
        logger.debug("Entering verifyUserLocaleAndLocation()");
        boolean result = false;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT user_location_id ");
            sql.append("FROM userlocations ");
            sql.append("WHERE (user_id = ?) and (location_Id = ?) and (locale_Id = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            statement.setInt(2, locationid);
            statement.setInt(3, localeid);
            
            rs = statement.executeQuery();
           
            if (rs.next()) {
                result = true;
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in verifyUserLocaleAndLocation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in verifyUserLocaleAndLocation() : ", e);
        } finally {
            closeConnection();
        }
        
        return result;
    }       
   


public int  verifyVitalsId( int vitalsId, DbUserSession user) {
    
	String jndiLookup = user.getDbLookup();
    logger.debug("Entering verifyVitalsId()");
    int result = 0;
    try {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LocaleNumber ");
        sql.append("FROM vitalstats ");
        sql.append("WHERE  vitalsmasterkey = ?");
        
        makeConnection(jndiLookup);
        statement = conn.prepareStatement(sql.toString());
        statement.setInt(1, vitalsId);
        
        rs = statement.executeQuery();
       
        if (rs.next()) {
           result = rs.getInt(1);
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception in verifyVitalsId() : ", e);
    } catch (Exception e) {
        logger.error("Exception in verifyVitalsId() : ", e);
    } finally {
        closeConnection();
    }
    
    return result;
} 

public boolean  verifyContractNoUesed( String contractCode, int localeNumber, DbUserSession user) {
    
	String jndiLookup = user.getDbLookup();
    logger.debug("Entering verifyContractNo()");
    boolean result = false;
    try {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ContractCode ");
        sql.append("FROM vitalstats ");
        sql.append("WHERE  ContractCode = ? and LocaleNumber = ?");
        
        makeConnection(jndiLookup);
        statement = conn.prepareStatement(sql.toString());
        statement.setString(1, contractCode);
        statement.setInt(2, localeNumber);
        
        rs = statement.executeQuery();
       
        if (rs.next()) {
           result = true;
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception in verifyContractNo() : ", e);
    } catch (Exception e) {
        logger.error("Exception in verifyContractNo() : ", e);
    } finally {
        closeConnection();
    }
    
    return result;
}  



public boolean verifyLocale(long userId, int localeId) {
    
    logger.debug("Entering verifyLocale()");
    boolean result = false;
    try {

        
        makeConnection(DAO.DB_FDMSUSERS);
        statement = conn.prepareStatement("SELECT locale_Id FROM userlocations where user_id = ? and locale_Id = ?");
        statement.setLong(1, userId);
        statement.setLong(2, localeId);
        rs = statement.executeQuery();
        if (rs.next()) {
           result = true;
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception in verifyLocale() : ", e);
    } catch (Exception e) {
        logger.error("Exception in verifyLocale() : ", e);
    } finally {
        closeConnection();
    }
    
    return result;
}    

public boolean  verifyLocaleAndArranger( int localeId, int identity, DbUserSession user) {
    
	String jndiLookup = user.getDbLookup();
    logger.debug("Entering verifyLocaleAndDirector()");
    boolean result = false;
    try {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT Identity ");
        sql.append("FROM arrangers ");
        sql.append("WHERE  Locale = ? AND Identity = ?");
        
        
        makeConnection(jndiLookup);
        statement = conn.prepareStatement(sql.toString());
        statement.setInt(1, localeId);
        statement.setInt(2, identity);
        
        rs = statement.executeQuery();
       
        if (rs.next()) {
            result = true;
        }
        
    } catch (SQLException e) {
        logger.error("SQL Exception in verifyLocaleAndDirector() : ", e);
    } catch (Exception e) {
        logger.error("Exception in verifyLocaleAndDirector() : ", e);
    } finally {
        closeConnection();
    }
    
    return result;
}       



    /**
     * Retrieves an ArrayList of all locations user is configured to
     * @param userId
     * @return userLocations
     */
    public ArrayList <UserLocationDTO> getUserLocations(long userId, int regionId) {
        
        logger.debug("Entering getUserLocations()");
        ArrayList <UserLocationDTO> userLocations = new ArrayList <UserLocationDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT location_id, default_location, locale_id ");
            sql.append("FROM userlocations ");
            sql.append("WHERE (user_id = ?) and (locale_id = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            statement.setInt(2, regionId);
            
            rs = statement.executeQuery();
            while (rs.next()) {
                UserLocationDTO userLocationDto = new UserLocationDTO();
                userLocationDto.setLocationId(rs.getLong(1));   
                userLocationDto.setDefaultLocation(rs.getString(2));
                userLocationDto.setRegionId(rs.getLong(3));
                userLocations.add(userLocationDto);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getUserLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUserLocations() : ", e);
        } finally {
            closeConnection();
        }
        
        return userLocations;
    } 
    
    /**
     * Retrieves an ArrayList of all locations user is configured to
     * @param userId
     * @return userLocations
     */
    public ArrayList <UserLocationDTO> getUsersByLocation(long locationID) {
        
        logger.debug("Entering getUsersByLocation()");
        ArrayList <UserLocationDTO> usersByLocation = new ArrayList <UserLocationDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT user_id ");
            sql.append("FROM userlocations ");
            sql.append("WHERE (location_Id = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, locationID);
            
            rs = statement.executeQuery();
            while (rs.next()) {
                UserLocationDTO userLocationDto = new UserLocationDTO();
                userLocationDto.setUser_id(rs.getInt(1));   
                usersByLocation.add(userLocationDto);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getUserLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getUserLocations() : ", e);
        } finally {
            closeConnection();
        }
        
        return usersByLocation;
    }    
    
    
    /**
     * Delete all locations associated to user from userlocations table
     * in webfdmsusers db
     * @param userId
     */
    public void deleteUserLocations(long userId) {
        
        try {
        	ArrayList <UserLocationDTO> userLocs = getUserLocations(userId);
        	UserDTO user = getUser(userId);
        	        	
            String sql = "DELETE FROM userlocations WHERE (user_id = ?)";
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.executeUpdate();
            
            for ( int i = 0; i < userLocs.size(); i++ ) {
            	UserLocationDTO userLocationDto = (UserLocationDTO) userLocs.get(i);
            	
                auditDTO.setLocaleId( user.getRegionId() );
                auditDTO.setUserId( (int) userId);
                auditDTO.setCompanyId(user.getCompanyID());
                
                deleteAudit(userLocationDto);                
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in deleteUserLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteUserLocations() : ", e);
        } finally {
            closeConnection();
        }
    }
    
    
    /**
     * adds locations for user in userlocations table in webfdmsusers db
     * @param locations an ArrayList of UserLocationDTO's
     */
    public void addUserLocations(long userId, ArrayList <UserLocationDTO> locations)throws Exception {
    	 addUserLocations( userId,  locations, false);
    }
    /**
     * adds locations for user in userlocations table in webfdmsusers db
     * @param locations an ArrayList of UserLocationDTO's
     */
    public void addUserLocations(long userId, ArrayList <UserLocationDTO> locations, boolean isGlobal)throws Exception {
        
        try {
        	UserDTO userDTO = getUser(userId);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO userlocations (user_id, location_id, locale_id) ");
            sql.append("VALUES (?,?,?)");
            
            makeConnection(DAO.DB_FDMSUSERS, isGlobal);
            statement = conn.prepareStatement(sql.toString());
            
            int i = 0;
            while (i < locations.size()) {
            	UserLocationDTO location = (UserLocationDTO) locations.get(i++);
            	
                statement.setLong(1, userId);
                statement.setLong(2, location.getLocationId());
                statement.setLong(3, location.getRegionId());
                statement.executeUpdate();
                statement.clearParameters();
                
                auditDTO.setLocaleId( userDTO.getRegionId() );
                auditDTO.setUserId( (int) userId);
                auditDTO.setCompanyId(userDTO.getCompanyID());
                
                insertAudit(location);    
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in addUserLocations() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addUserLocations() : ", e);
            throw e;
        } finally {
            closeConnection();
        }        
    }
    
    /**
     * Updates user's default locationId
     * @param locationId
     */
    public void updateUserDefaultLocation(long userId, int locationId) {
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE userlocations SET default_location = NULL ");
            sql.append("WHERE (user_id = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            statement.executeUpdate();
            
            sql = new StringBuffer();
            sql.append("UPDATE userlocations SET default_location = 'Y' ");
            sql.append("WHERE (user_id = ?) AND (location_id = ?)");
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userId);
            statement.setInt(2, locationId);
            statement.executeUpdate();            
            
        } catch (SQLException e) {
            logger.error("SQL Exception in updateUserDefaultLocation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateUserDefaultLocation() : ", e);
        } finally {
            closeConnection();
        }    
    }
    
    public boolean hasAllLocations(long userId) {
    	boolean hasAll = false;
    	
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT COUNT(*) FROM userlocations ");
    		sql.append("WHERE (user_id = ?) and (location_id = 0)");
    		
    		makeConnection(DAO.DB_FDMSUSERS);
    		statement = conn.prepareStatement(sql.toString());
    		statement.setLong(1, userId);
    		
    		rs = statement.executeQuery();
    		if (rs.next()) {
    			if (rs.getInt(1) > 0) hasAll = true;
    		}
    		
        } catch (SQLException e) {
            logger.error("SQL Exception in hasAllLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in hasAllLocations() : ", e);
        } finally {
            closeConnection();
        } 
        
    	return hasAll;
    }

    public void addAllLocationId(long userId) {
    	
    	try {
    		StringBuffer sql = new StringBuffer();
    		sql.append("INSERT INTO userlocations (user_id, location_id) ");
    		sql.append("VALUES (?,0)");
    		
    		makeConnection(DAO.DB_FDMSUSERS);
    		statement = conn.prepareStatement(sql.toString());
    		statement.setLong(1, userId);
    		statement.executeUpdate();
			
        } catch (SQLException e) {
            logger.error("SQL Exception in addAllLocationId() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addAllLocationId() : ", e);
        } finally {
            closeConnection();
        } 
    }
    
    /**
     * Adds Default user location
     * @param userId
     * @param locatioId
     * @param localeId
     */
    public void addDefaultUserLocation(long userId,UserLocationDTO userDefaultLocation) {
        
        try {
        	
        	String defaultLocation = "Y";
        	
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO userlocations (user_id, location_id, locale_id, default_location) ");
            sql.append("VALUES (?,?,?,?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            statement.setLong(1, userId);
			statement.setLong(2, userDefaultLocation.getLocationId());
			statement.setLong(3, userDefaultLocation.getRegionId());
			statement.setString(4, defaultLocation);
			statement.executeUpdate();
			
			insertAudit(userDefaultLocation);    
            
        } catch (SQLException e) {
            logger.error("SQL Exception in addDefaultUserLocation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addDefaultUserLocation() : ", e);
        } finally {
            closeConnection();
        }   
    }
}
