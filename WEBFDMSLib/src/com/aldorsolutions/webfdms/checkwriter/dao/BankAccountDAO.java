package com.aldorsolutions.webfdms.checkwriter.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.checkwriter.model.BankAccountDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class BankAccountDAO extends DAO {

	public BankAccountDAO() {
		// TODO Auto-generated constructor stub
	}

	public BankAccountDAO(String dbLookup) {
		super(dbLookup);
		// TODO Auto-generated constructor stub
	}

	public BankAccountDAO(DbUserSession user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	public BankAccountDAO(long companyID, long userId) {
		super(companyID, userId);
		// TODO Auto-generated constructor stub
	}
	
	 private Logger logger = Logger.getLogger(ApMasterDAO.class.getName());
	 
	 public BankAccountDTO getBankAccount(int bankAccountID) {
	    	BankAccountDTO version = null;
	        
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append("select BankAccountID, LocaleID, LocationID, BankName, AccountName, RoutingNumber, AccountNumber, Street, City, State, Zip," +
	            		" PhoneNumber, OtherPhone, StartDate, Status, Balance, AccountingCode, InitialBalance from bankaccount where BankAccountID = ?");
	            
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setInt(1, bankAccountID);
	            
	            rs = statement.executeQuery();
	            
	            if (rs.next()) {
	            	int col = 1;
	                version = new BankAccountDTO();
	                version.setBankAccountId(rs.getInt(col++));
	                version.setLocaleId(rs.getInt(col++));
	                version.setLocationId(rs.getInt(col++));
	                version.setBankName(rs.getString(col++));
	                version.setAccountName(rs.getString(col++));
	                version.setRoutingNumber(rs.getString(col++));
	                version.setAccountNumber(rs.getString(col++));
	                version.setStreet(rs.getString(col++));
	                version.setCity(rs.getString(col++));
	                version.setState(rs.getString(col++));
	                version.setZip(rs.getString(col++));
	                version.setPhone(rs.getString(col++));
	                version.setOtherPhone(rs.getString(col++));
	                version.setStartDate(rs.getDate(col++));
	                version.setStatus(rs.getString(col++));
	                version.setBalance(rs.getInt(col++));
	                version.setAccountingCode(rs.getString(col++));
	                version.setInitialBalance(rs.getInt(col++));
	                return(version);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in getBankAccount() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in getBankAccount() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return null;
	    }
	 
	    public BankAccountDTO getBankAccountByLocationId(int locationId) {
	    	BankAccountDTO version = null;
	        
	        try {
	            StringBuffer sql = new StringBuffer();
	            sql.append("select BankAccountID, LocaleID, LocationID, BankName, AccountName, RoutingNumber, AccountNumber, Street, City, State, Zip," +
	            		" PhoneNumber, OtherPhone, StartDate, Status, Balance, AccountingCode, InitialBalance from bankaccount where LocationID = ?");
	            
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setInt(1, locationId);
	            
	            rs = statement.executeQuery();
	            
	            if (rs.next()) {
	            	int col = 1;
	                version = new BankAccountDTO();
	                version.setBankAccountId(rs.getInt(col++));
	                version.setLocaleId(rs.getInt(col++));
	                version.setLocationId(rs.getInt(col++));
	                version.setBankName(rs.getString(col++));
	                version.setAccountName(rs.getString(col++));
	                version.setRoutingNumber(rs.getString(col++));
	                version.setAccountNumber(rs.getString(col++));
	                version.setStreet(rs.getString(col++));
	                version.setCity(rs.getString(col++));
	                version.setState(rs.getString(col++));
	                version.setZip(rs.getString(col++));
	                version.setPhone(rs.getString(col++));
	                version.setOtherPhone(rs.getString(col++));
	                version.setStartDate(rs.getDate(col++));
	                version.setStatus(rs.getString(col++));
	                version.setBalance(rs.getInt(col++));
	                version.setAccountingCode(rs.getString(col++));
	                version.setInitialBalance(rs.getInt(col++));
	                return(version);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in getBankAccountByLocationId() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in getBankAccountByLocationId() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return null;
	    }
	    
	    public boolean addBankAccount(BankAccountDTO bankAccount) {
	        boolean added = false;
	        
	        try {
	        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
	            StringBuffer sql = new StringBuffer();
	            sql.append("INSERT INTO bankaccount ( LocaleID, LocationID, BankName, AccountName, RoutingNumber, AccountNumber, Street, City, State, Zip, PhoneNumber, OtherPhone, StartDate, Status, Balance, AccountingCode, InitialBalance ) ");
	            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	            
	            int col = 0;
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	            statement.setInt(++col, bankAccount.getLocaleId());
	            statement.setInt(++col, bankAccount.getLocationId());
	            statement.setString(++col, bankAccount.getBankName());
	            statement.setString(++col, bankAccount.getAccountName());
	            statement.setString(++col, bankAccount.getRoutingNumber());
	            statement.setString(++col, bankAccount.getAccountNumber());
	            statement.setString(++col, bankAccount.getStreet());
	            statement.setString(++col, bankAccount.getCity());
	            statement.setString(++col, bankAccount.getState());
	            statement.setString(++col, bankAccount.getZip());
	            statement.setString(++col, bankAccount.getPhone());
	            statement.setString(++col, bankAccount.getOtherPhone());
	            statement.setDate(++col, bankAccount.getStartDate());
	            statement.setString(++col, bankAccount.getStatus());
	            statement.setInt(++col, bankAccount.getBalance());
	            statement.setString(++col, bankAccount.getAccountingCode());
	            statement.setInt(++col, bankAccount.getInitialBalance());
	            statement.executeUpdate();
	            added = true;
	            
	            if (added) {
	            	rs = statement.getGeneratedKeys();
	                if (rs.next()) {
	                	bankAccount.setBankAccountId(rs.getInt(1));
	                }
	                
	                auditDTO.setCompanyId((int) this.companyID);
	                auditDTO.setUserId((int) this.userID);
	                
	                insertAudit(bankAccount);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in addBankAccount() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in addBankAccount() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return added;
	    }
	    
	    public boolean updateBankAccount(BankAccountDTO bankAccount) {
	        boolean added = false;
	        
	        try {
	        	BankAccountDTO oldVer = getBankAccount(bankAccount.getBankAccountId());
	        	
	            StringBuffer sql = new StringBuffer();
	            sql.append("UPDATE bankaccount set LocaleID = ?, LocationID = ?, BankName = ?, AccountName = ?, RoutingNumber = ?, AccountNumber = ?, Street = ?, City = ?, " +
	            		"State = ?, Zip = ?, PhoneNumber = ?, OtherPhone = ? , StartDate = ?, Status = ?, Balance = ?, AccountingCode = ?, InitialBalance = ? " +
	            		"WHERE BankAccountID = ? ");
	            
	            int col = 0;
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql.toString());
	            statement.setInt(++col, bankAccount.getLocaleId());
	            statement.setInt(++col, bankAccount.getLocationId());
	            statement.setString(++col, bankAccount.getBankName());
	            statement.setString(++col, bankAccount.getAccountName());
	            statement.setString(++col, bankAccount.getRoutingNumber());
	            statement.setString(++col, bankAccount.getAccountNumber());
	            statement.setString(++col, bankAccount.getStreet());
	            statement.setString(++col, bankAccount.getCity());
	            statement.setString(++col, bankAccount.getState());
	            statement.setString(++col, bankAccount.getZip());
	            statement.setString(++col, bankAccount.getPhone());
	            statement.setString(++col, bankAccount.getOtherPhone());
	            statement.setDate(++col, bankAccount.getStartDate());
	            statement.setString(++col, bankAccount.getStatus());
	            statement.setInt(++col, bankAccount.getBalance());
	            statement.setString(++col, bankAccount.getAccountingCode());
	            statement.setInt(++col, bankAccount.getInitialBalance());
	            statement.setInt(++col, bankAccount.getBankAccountId());
	            statement.executeUpdate();
	            added = true;
	            
	            if (added) {
		            auditDTO.setCompanyId((int) this.companyID);
		            auditDTO.setUserId((int) this.userID);
		            
		            updateAudit(oldVer, bankAccount);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in updateBankAccount() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in updateBankAccount() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return added;
	    }
	    
	    public boolean deleteBankAccount(int bankAccountId) {
	        
	    	boolean added = false;
	        try {
	        	BankAccountDTO oldVer = getBankAccount(bankAccountId);
	        	
	            String sql = "delete from bankaccount WHERE (BankAccountId = ?)";
	            makeConnection(this.dbLookup);
	            statement = conn.prepareStatement(sql);
	            statement.setInt(1, bankAccountId);
	            statement.executeUpdate();     
	            added = true;
	            if (added) {
		            auditDTO.setCompanyId((int) companyID);
		            auditDTO.setUserId((int) userID);
		            
		            deleteAudit(oldVer, null);
	            }
	            
	        } catch (SQLException e) {
	            logger.error("SQLException in deleteBankAccount() : ", e);
	        } catch (Exception e) {
	            logger.error("Exception in deleteBankAccount() : ", e);
	        } finally {
	            closeConnection();
	        }
	        
	        return added;
	    }
	    
	    
	    
}
