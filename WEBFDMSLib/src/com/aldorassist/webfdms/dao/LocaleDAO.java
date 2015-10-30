/*
 * LocaleDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorassist.webfdms.dao;

/**
 *
 * @author drollins
 * modify cjongs 030309
 */

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.DAO;




public class LocaleDAO extends DAO {
    
    private Logger logger = Logger.getLogger(LocaleDAO.class.getName());
    
    public LocaleDAO() {
    	super ();
    }
    
    public LocaleDAO(DbUserSession user) {
    	super (user);
    }
    
    public LocaleDAO(long companyID, long userID) {
    	super (companyID, userID);
    }
    
    private String getSelectFields () {
    	return ("LocaleID, Name, NextContractNo, NextReceiptNo, " +
        		"NextNonCashNo, LastFinChgDate, AccessExpirationDate, " +
        		"NumberOfUsers, NextPreNeedNumber, InterfaceType, " +
        		"ManagerName, AbbitID, DefaultBankID, DefaultCommission, " +
        		"DefaultRefundRate, DefaultInflationRate, PreneedLicense, " +
        		"AtneedLicense, DefaultSaleDateCode, DaysBeforeDue, " +
        		"LocalizedSpeedData, AutoFillDateOfDeath, " +
        		"AutoFillArrangeDate, CompanyID, InactiveCode, " +
        		"ConfigAcctInterfaceDateRange, ConfigShowFinancing, " +
        		"FDMS_LocaleType, Country");
    }
    
    public LocaleDTO getLocale ( long localeID, String dbLookup ) throws SQLException, Exception {
    	LocaleDTO locale = null;
        
        try {
        	
            String sql = "SELECT " + getSelectFields() + " FROM locales where localeID = ?";
            
            makeConnection(dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, localeID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	locale = new LocaleDTO();
            	locale.setLocaleID(rs.getInt(++col));
            	locale.setName(rs.getString(++col));
            	locale.setNextContractNo( rs.getInt(++col) );
            	locale.setNextReceiptNo( rs.getInt(++col) );
            	locale.setNextNonCashNo( rs.getInt(++col) );
            	locale.setLastFinChgDate( rs.getDate(++col) );
            	locale.setAccessExpirationDate( rs.getDate(++col) );
            	locale.setNumberOfUsers( rs.getInt(++col) );
            	locale.setNextPreNeedNumber( rs.getInt(++col) );
            	locale.setInterfaceType( rs.getInt(++col) );
            	locale.setManagerName( rs.getString(++col) );
            	locale.setAbbitID( rs.getInt(++col) );
            	locale.setDefaultBankID( rs.getInt(++col) );
            	locale.setDefaultCommission(rs.getDouble(++col) );
            	locale.setDefaultRefundRate( rs.getDouble(++col) );
            	locale.setDefaultInflationRate( rs.getDouble(++col) );
            	locale.setPreneedLicense( rs.getString(++col) );
            	locale.setAtneedLicense( rs.getString(++col) );
            	locale.setDefaultSaleDateCode( rs.getInt(++col) );
            	locale.setDaysBeforeDue( rs.getInt(++col) );
            	locale.setLocalizedSpeedData( rs.getBoolean(++col) );
            	locale.setAutoFillDateOfDeath( rs.getBoolean(++col) );
            	locale.setAutoFillArrangeDate( rs.getBoolean(++col) );
            	locale.setCompanyID( rs.getInt(++col) );
            	locale.setInactiveCode( rs.getString(++col) );
            	locale.setConfigAcctInterfaceDateRange( rs.getBoolean(++col) );
            	locale.setConfigShowFinancing( rs.getBoolean(++col) );
            	locale.setFdmsLocaleType( rs.getInt(++col) );
            	locale.setCountry( rs.getString(++col) );
            	
            }
            
        } finally {
            closeConnection();
        }
        
        return locale;
    }
    
    public ArrayList <LocaleDTO> getLocales ( boolean active, long companyID) {
    	return ( getLocales(active, companyID, dbLookup) );
    }
    
    public ArrayList <LocaleDTO> getLocales ( boolean active, long companyID, String dbLookup ) {
    	ArrayList <LocaleDTO> elements = new ArrayList <LocaleDTO> ();
    	
        try {
            String sql = "SELECT " + getSelectFields() + " FROM locales where CompanyID = ? ";
            
            if ( active ) {
            	sql += "AND InactiveCode <> 'D' ";
            }
            
            sql += " ORDER BY Name ";
            
            makeConnection(dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	LocaleDTO locale = new LocaleDTO();
            	locale.setLocaleID(rs.getInt(++col));
            	locale.setName(rs.getString(++col));
            	locale.setNextContractNo( rs.getInt(++col) );
            	locale.setNextReceiptNo( rs.getInt(++col) );
            	locale.setNextNonCashNo( rs.getInt(++col) );
            	locale.setLastFinChgDate( rs.getDate(++col) );
            	locale.setAccessExpirationDate( rs.getDate(++col) );
            	locale.setNumberOfUsers( rs.getInt(++col) );
            	locale.setNextPreNeedNumber( rs.getInt(++col) );
            	locale.setInterfaceType( rs.getInt(++col) );
            	locale.setManagerName( rs.getString(++col) );
            	locale.setAbbitID( rs.getInt(++col) );
            	locale.setDefaultBankID( rs.getInt(++col) );
            	locale.setDefaultCommission(rs.getDouble(++col) );
            	locale.setDefaultRefundRate( rs.getDouble(++col) );
            	locale.setDefaultInflationRate( rs.getDouble(++col) );
            	locale.setPreneedLicense( rs.getString(++col) );
            	locale.setAtneedLicense( rs.getString(++col) );
            	locale.setDefaultSaleDateCode( rs.getInt(++col) );
            	locale.setDaysBeforeDue( rs.getInt(++col) );
            	locale.setLocalizedSpeedData( rs.getBoolean(++col) );
            	locale.setAutoFillDateOfDeath( rs.getBoolean(++col) );
            	locale.setAutoFillArrangeDate( rs.getBoolean(++col) );
            	locale.setCompanyID( rs.getInt(++col) );
            	locale.setInactiveCode( rs.getString(++col) );
            	locale.setConfigAcctInterfaceDateRange( rs.getBoolean(++col) );
            	locale.setConfigShowFinancing( rs.getBoolean(++col) );
            	locale.setFdmsLocaleType( rs.getInt(++col) );
            	locale.setCountry( rs.getString(++col) );
            	elements.add(locale);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getLocales() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocales() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    public ArrayList <LocaleDTO> getLocales ( boolean active, long companyID, String dbLookup, Boolean isOrderByName ) {
    	ArrayList <LocaleDTO> elements = new ArrayList <LocaleDTO> ();
    	
        try {
            String sql = "SELECT " + getSelectFields() + " FROM locales where CompanyID = ? ";
            
            if ( active ) {
            	sql += "AND InactiveCode <> 'D' ORDER BY Name";
            }
            
            makeConnection(dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	LocaleDTO locale = new LocaleDTO();
            	locale.setLocaleID(rs.getInt(++col));
            	locale.setName(rs.getString(++col));
            	locale.setNextContractNo( rs.getInt(++col) );
            	locale.setNextReceiptNo( rs.getInt(++col) );
            	locale.setNextNonCashNo( rs.getInt(++col) );
            	locale.setLastFinChgDate( rs.getDate(++col) );
            	locale.setAccessExpirationDate( rs.getDate(++col) );
            	locale.setNumberOfUsers( rs.getInt(++col) );
            	locale.setNextPreNeedNumber( rs.getInt(++col) );
            	locale.setInterfaceType( rs.getInt(++col) );
            	locale.setManagerName( rs.getString(++col) );
            	locale.setAbbitID( rs.getInt(++col) );
            	locale.setDefaultBankID( rs.getInt(++col) );
            	locale.setDefaultCommission(rs.getDouble(++col) );
            	locale.setDefaultRefundRate( rs.getDouble(++col) );
            	locale.setDefaultInflationRate( rs.getDouble(++col) );
            	locale.setPreneedLicense( rs.getString(++col) );
            	locale.setAtneedLicense( rs.getString(++col) );
            	locale.setDefaultSaleDateCode( rs.getInt(++col) );
            	locale.setDaysBeforeDue( rs.getInt(++col) );
            	locale.setLocalizedSpeedData( rs.getBoolean(++col) );
            	locale.setAutoFillDateOfDeath( rs.getBoolean(++col) );
            	locale.setAutoFillArrangeDate( rs.getBoolean(++col) );
            	locale.setCompanyID( rs.getInt(++col) );
            	locale.setInactiveCode( rs.getString(++col) );
            	locale.setConfigAcctInterfaceDateRange( rs.getBoolean(++col) );
            	locale.setConfigShowFinancing( rs.getBoolean(++col) );
            	locale.setFdmsLocaleType( rs.getInt(++col) );
            	locale.setCountry( rs.getString(++col) );
            	elements.add(locale);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getLocales() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocales() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    
    public boolean addLocale(LocaleDTO locale, String dbLookup) throws Exception{
    	return addLocale(locale, dbLookup, false);
    }
    
    public boolean addLocale(LocaleDTO locale, String dbLookup, boolean isGlobal)throws Exception {
        boolean added = false;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO locales ( Name, NextContractNo, NextReceiptNo, " +
            		"NextNonCashNo, LastFinChgDate, AccessExpirationDate, " +
            		"NumberOfUsers, NextPreNeedNumber, InterfaceType, " +
            		"ManagerName, AbbitID, DefaultBankID, DefaultCommission, " +
            		"DefaultRefundRate, DefaultInflationRate, PreneedLicense, " +
            		"AtneedLicense, DefaultSaleDateCode, DaysBeforeDue, " +
            		"LocalizedSpeedData, AutoFillDateOfDeath, " +
            		"AutoFillArrangeDate, CompanyID, InactiveCode, " +
            		"ConfigAcctInterfaceDateRange, ConfigShowFinancing, " +
            		"FDMS_LocaleType, Country )");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            		"?, ?, ?, ?, ? )");
            
            
            
            int col = 0;
            makeConnection(dbLookup, isGlobal);
            statement = conn.prepareStatement(sql.toString());   
            statement.setString(++col, locale.getName());
            statement.setInt(++col, locale.getNextContractNo());
            statement.setInt(++col, locale.getNextReceiptNo());
            statement.setInt(++col, locale.getNextNonCashNo());
            statement.setDate(++col, locale.getLastFinChgDate());
            statement.setDate(++col, locale.getAccessExpirationDate());
            statement.setInt(++col, locale.getNumberOfUsers());
            statement.setInt(++col, locale.getNextPreNeedNumber());
            statement.setInt(++col, locale.getInterfaceType());
            statement.setString(++col, locale.getManagerName());
            statement.setInt(++col, locale.getAbbitID());
            statement.setInt(++col, locale.getDefaultBankID());
            statement.setDouble(++col, locale.getDefaultCommission());
            statement.setDouble(++col, locale.getDefaultRefundRate());
            statement.setDouble(++col, locale.getDefaultInflationRate());
            statement.setString(++col, locale.getPreneedLicense());
            statement.setString(++col, locale.getAtneedLicense());
            statement.setInt(++col, locale.getDefaultSaleDateCode());
            statement.setInt(++col, locale.getDaysBeforeDue());
            statement.setBoolean(++col, locale.isLocalizedSpeedData());
            statement.setBoolean(++col, locale.isAutoFillDateOfDeath());
            statement.setBoolean(++col, locale.isAutoFillArrangeDate());
            statement.setInt(++col, locale.getCompanyID());
            statement.setString(++col, locale.getInactiveCode());
            statement.setBoolean(++col, locale.isConfigAcctInterfaceDateRange());
            statement.setBoolean(++col, locale.isConfigShowFinancing());
            statement.setInt(++col, locale.getFdmsLocaleType());
            statement.setString(++col, locale.getCountry());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
                statement.clearParameters();
                sql = new StringBuffer("SELECT LAST_INSERT_ID()");
                statement = conn.prepareStatement(sql.toString());
                rs = statement.executeQuery();
                if (rs.next()) {
                	locale.setLocaleID(rs.getInt(1));
                }
                
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addLocale() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addLocale() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateLocale(LocaleDTO locale, String dbLookup) throws PersistenceException {
        boolean updated = false;
        
        try {
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE locales SET Name = ?, NextContractNo = ?, NextReceiptNo = ?, " +
            		"NextNonCashNo = ?, LastFinChgDate = ?, AccessExpirationDate = ?, " +
            		"NumberOfUsers = ?, NextPreNeedNumber = ?, InterfaceType = ?, " +
            		"ManagerName = ?, AbbitID = ?, DefaultBankID = ?, DefaultCommission = ?, " +
            		"DefaultRefundRate = ?, DefaultInflationRate = ?, PreneedLicense = ?, " +
            		"AtneedLicense = ?, DefaultSaleDateCode = ?, DaysBeforeDue = ?, " +
            		"LocalizedSpeedData = ?, AutoFillDateOfDeath = ?, " +
            		"AutoFillArrangeDate = ?, CompanyID = ?, InactiveCode = ?," +
            		"ConfigAcctInterfaceDateRange =?, ConfigShowFinancing =?, " +
            		"FDMS_LocaleType =?, Country = ?");
            sql.append("WHERE (LocaleID = ?)");
            
            int col = 0;
            makeConnection(dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, locale.getName());
            statement.setInt(++col, locale.getNextContractNo());
            statement.setInt(++col, locale.getNextReceiptNo());
            statement.setInt(++col, locale.getNextNonCashNo());
            statement.setDate(++col, locale.getLastFinChgDate());
            statement.setDate(++col, locale.getAccessExpirationDate());
            statement.setInt(++col, locale.getNumberOfUsers());
            statement.setInt(++col, locale.getNextPreNeedNumber());
            statement.setInt(++col, locale.getInterfaceType());
            statement.setString(++col, locale.getManagerName());
            statement.setInt(++col, locale.getAbbitID());
            statement.setInt(++col, locale.getDefaultBankID());
            statement.setDouble(++col, locale.getDefaultCommission());
            statement.setDouble(++col, locale.getDefaultRefundRate());
            statement.setDouble(++col, locale.getDefaultInflationRate());
            statement.setString(++col, locale.getPreneedLicense());
            statement.setString(++col, locale.getAtneedLicense());
            statement.setInt(++col, locale.getDefaultSaleDateCode());
            statement.setInt(++col, locale.getDaysBeforeDue());
            statement.setBoolean(++col, locale.isLocalizedSpeedData());
            statement.setBoolean(++col, locale.isAutoFillDateOfDeath());
            statement.setBoolean(++col, locale.isAutoFillArrangeDate());
            statement.setInt(++col, locale.getCompanyID());
            statement.setString(++col, locale.getInactiveCode());
            statement.setBoolean(++col, locale.isConfigAcctInterfaceDateRange());
            statement.setBoolean(++col, locale.isConfigShowFinancing());
            statement.setInt(++col, locale.getFdmsLocaleType());
            statement.setString(++col, locale.getCountry());
            statement.setInt(++col, locale.getLocaleID());
            
            statement.executeUpdate();
            
            updated = true;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            logger.error("Exception in updateLocale() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteLocale(long localeID, String dbLookup) {
        boolean deleted = false;
        
        try {
            StringBuffer sql = new StringBuffer();
            
            sql.append("update locales set InactiveCode = 'D' WHERE (LocaleID=?)");
            
            int col = 0;
            makeConnection(dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, localeID);
            statement.executeUpdate();
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteLocale() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteLocale() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
