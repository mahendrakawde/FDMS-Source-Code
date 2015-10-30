package com.aldorsolutions.webfdms.checkwriter.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * 
 * @author cjongs
 * Date: 02-16-09
 * File: ApVendorDAO.java	
 *
 */
public class ApVendorDAO extends DAO {
    
    /** Creates a new instance of ApMasterDAO */
    public ApVendorDAO(DbUserSession user) {
    	super(user);
    }

    /** Creates a new instance of ApMasterDAO */
    public ApVendorDAO ( long companyID, long userId ) {
    	super(companyID, userId);
    }
    
    private Logger logger = Logger.getLogger(ApVendorDAO.class.getName());
    public static int ACTIVE = 1;
    public static int DELETED = 0;
    
    public ArrayList <ApVendorDTO> getApVendor(boolean activeOnly) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors ");
            
            if ( activeOnly ) {
            	sql.append(" where DeleteCode is null OR DeleteCode <> 'D' order by Name ");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++));  
                version.setVendorCountry(rs.getString(col++));           
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));               
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }
    
    public ArrayList <ApVendorDTO> getVendorsForUser(boolean activeOnly, DbUserSession user) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        UserManagerBean uMgr = new UserManagerBean();
        String [] locationIDs = uMgr.getUserLocationIds( user.getId());
        
        ApVendorLocationDAO vendorLoc = new ApVendorLocationDAO(user);
        ArrayList <String> vendorIDs = vendorLoc.getApVendorIDsByLocationIDs(locationIDs);
        String sVendorIDs = "";
    	for ( String sVendorID: vendorIDs){
    		sVendorIDs = sVendorIDs+sVendorID+", ";
    	}
    	sVendorIDs = sVendorIDs.substring(0, sVendorIDs.length()-2); 
        
        try {
            StringBuffer sql = new StringBuffer();
            
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors " +
            		"where VendorID in ("+ sVendorIDs +")");
            
            
            if ( activeOnly ) {
            	sql.append(" and DeleteCode is null OR DeleteCode <> 'D' order by Name ");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++));  
                version.setVendorCountry(rs.getString(col++));           
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));               
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }   
    
    public ArrayList <ApVendorDTO> getUnAssignedLocationApVendor(String vendorStatus, boolean unassigned) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors ");
            
            sql.append("where vendorid not in ( select distinct vendorid from apvendorlocations) ");
            
            if ( vendorStatus.compareToIgnoreCase("A") == 0 ) {
            	sql.append(" and (DeleteCode is null OR DeleteCode <> 'D') order by Name");
            }
            else if (vendorStatus.compareToIgnoreCase("D") == 0) {
            	sql.append(" and (DeleteCode = 'D') order by Name");
            }
            else {
          	  sql.append(" order by Name");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++));  
                version.setVendorCountry(rs.getString(col++));           
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));               
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }   
    
    
    
    public ArrayList <ApVendorDTO> getApVendorByName(boolean activeOnly, String Name) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors ");
            
            if ( activeOnly ) {
            	sql.append(" where (DeleteCode is null OR DeleteCode <> 'D') and (Name like '"+Name+"') order by Name ");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++));  
                version.setVendorCountry(rs.getString(col++));           
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));               
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }  
    
    public ArrayList <ApVendorDTO> getApVendorByCode(boolean activeOnly, String vendorCode) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors ");
            
            if ( activeOnly ) {
            	sql.append(" where (DeleteCode is null OR DeleteCode <> 'D') and (VendorCode like '"+vendorCode+"') order by VendorCode ");
            } else {
            	sql.append(" where (VendorCode like '"+vendorCode+"') order by VendorCode ");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++));  
                version.setVendorCountry(rs.getString(col++));           
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));               
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }        
    
    
    public ArrayList <ApVendorDTO> getApVendorByLocale(boolean activeOnly, long localeID) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors where " +
            		" VendorID in (select distinct vendorID from apvendorlocations where localeID = ?)");
            
            if ( activeOnly ) {
            	sql.append(" AND (DeleteCode is null OR DeleteCode <> 'D') ");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, localeID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++)); 
                version.setVendorCountry(rs.getString(col++));  
                
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));                        
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }
  
    // get vendorDetails of VendorID list.
    public ArrayList <ApVendorDTO> getApVendorByVendorIDs(boolean activeOnly, ArrayList <Long> vendorIDs) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
        	String sVendorIDs = "";
        	for ( Long vendorID: vendorIDs){
        		sVendorIDs = sVendorIDs+vendorID+", ";
        	}
        	sVendorIDs = sVendorIDs.substring(0, sVendorIDs.length()-2);       	
        	
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from " +
            		"apvendors where VendorID in ("+sVendorIDs+") ");
            
            if ( activeOnly ) {
            	sql.append(" AND (DeleteCode is null OR DeleteCode <> 'D') order by Name");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++)); 
                version.setVendorCountry(rs.getString(col++));                
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));                        
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }   
    
    // get vendorDetails of VendorID list.
    public ArrayList <ApVendorDTO> getApVendorByVendorIDsName(boolean activeOnly, ArrayList <Long> vendorIDs, String name) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
        	String sVendorIDs = "";
        	for ( Long vendorID: vendorIDs){
        		sVendorIDs = sVendorIDs+vendorID+", ";
        	}
        	sVendorIDs = sVendorIDs.substring(0, sVendorIDs.length()-2);       	
        	
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from " +
            		"apvendors where VendorID in ("+sVendorIDs+") and Name like '"+name+"'");
            
            if ( activeOnly ) {
            	sql.append(" AND (DeleteCode is null OR DeleteCode <> 'D') order by Name");
            }
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++)); 
                version.setVendorCountry(rs.getString(col++));                
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));                        
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }    
    
   public ArrayList <ApVendorDTO> getApVendorByVendorIDsName(String vendorStatus, ArrayList <Long> vendorIDs, String name, String code) {
    	
    	//vendorStatus (A = Active only, D = inactive only, B = both )
    	
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
        	String sVendorIDs = "";
        	for ( Long vendorID: vendorIDs){
        		sVendorIDs = sVendorIDs+vendorID+", ";
        	}
        	sVendorIDs = sVendorIDs.substring(0, sVendorIDs.length()-2);       	
        	
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from " +
            		"apvendors where VendorID in ("+sVendorIDs+") and Name like '"+name+"' and VendorCode like '"+code+"'");
            
            if ( vendorStatus.compareToIgnoreCase("A") == 0 ) {
            	sql.append(" AND (DeleteCode is null OR DeleteCode <> 'D') order by Name");
            }
            else if (vendorStatus.compareToIgnoreCase("D") == 0) {
            	sql.append(" AND (DeleteCode = 'D') order by Name");
            }
            else {
          	  sql.append(" order by Name");
            }
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++)); 
                version.setVendorCountry(rs.getString(col++));                
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));                        
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }              
    
    public ApVendorDTO getApVendor(long vendorID) {
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select 	VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, VendorCountry, Phone2, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors where VendorID = ?");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, vendorID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));   
                version.setVendorCountry(rs.getString(col++));                 
                version.setPhone2(rs.getString(col++)); 
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));                                       
                
                return(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return null;
    }
    
    public ArrayList <ApVendorDTO> getApVendor(long locationID, long localeID) {
        ArrayList <ApVendorDTO> versions = new ArrayList <ApVendorDTO> ();
        ApVendorDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select VendorID, VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, Phone2, VendorCountry, Fax, AccountNumber, "+
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, "+
            		"Vendor1099Type, Vendor1099Payment, TaxID, ApprovedVendor, CreditLimit from apvendors where (DeleteCode is null OR DeleteCode<>'D') " +
            		"AND Locale = ? and (LocationID = ? OR LocationID = 0)");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, localeID);
            statement.setLong(2, locationID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApVendorDTO();
                version.setVendorID(rs.getLong(col++));
                version.setVendorCode(rs.getString(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setName(rs.getString(col++));
                version.setAddr1(rs.getString(col++));
                version.setAddr2(rs.getString(col++));
                version.setCityState(rs.getString(col++));
                version.setPostalCode(rs.getString(col++));
                version.setContactName(rs.getString(col++));
                version.setPhone(rs.getString(col++));
                version.setEmailAddr(rs.getString(col++));
                version.setDefaultAcctID(rs.getInt(col++));
                version.setNotes(rs.getString(col++));
                version.setLocationID(rs.getLong(col++));
                version.setDeleteCode(rs.getString(col++));
                version.setInternalVendor(rs.getBoolean(col++));
                version.setVendorState(rs.getString(col++));              
                version.setPhone2(rs.getString(col++));   
                version.setVendorCountry(rs.getString(col++));                   
                version.setFax(rs.getString(col++));
                version.setAccountNumber(rs.getString(col++));
                version.setDiscountPercentage(rs.getFloat(col++));
                version.setDiscountIfInDays(rs.getInt(col++));
                version.setDiscountDueInDays(rs.getInt(col++));
                version.setVendor1099Type(rs.getString(col++));
                version.setVendor1099Payment(rs.getFloat(col++));
                version.setTaxID(rs.getString(col++));
                version.setApprovedVendor(rs.getString(col++));
                version.setCreditLimit(rs.getFloat(col++));                        
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }
    
    public boolean addApVendor(ApVendorDTO apVendor) {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO apvendors ( VendorCode, Locale, Name, Addr1, Addr2, CityState, PostalCode, " +
            		"ContactName, Phone, EmailAddr, DefaultAcctID, Notes, LocationID, DeleteCode, " +
            		"InternalVendor, VendorState, VendorCountry, Phone2, Fax, AccountNumber, " +
            		"DiscountPercentage, DiscountIfInDays, DiscountDueInDays, Vendor1099Type, Vendor1099Payment, " +
            		"TaxID, ApprovedVendor, CreditLimit ) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(++col, apVendor.getVendorCode());
            statement.setLong(++col, apVendor.getLocaleID());
            statement.setString(++col, apVendor.getName());
            statement.setString(++col, apVendor.getAddr1());
            statement.setString(++col, apVendor.getAddr2());
            statement.setString(++col, apVendor.getCityState());
            statement.setString(++col, apVendor.getPostalCode());
            statement.setString(++col, apVendor.getContactName());
            statement.setString(++col, apVendor.getPhone());
            statement.setString(++col, apVendor.getEmailAddr());
            statement.setInt(++col, apVendor.getDefaultAcctID());
            statement.setString(++col, apVendor.getNotes());
            statement.setLong(++col, apVendor.getLocationID());
            statement.setString(++col, apVendor.getDeleteCode());
            statement.setBoolean(++col, apVendor.isInternalVendor());
            statement.setString(++col, apVendor.getVendorState());
            statement.setString(++col, apVendor.getVendorCountry());            
            statement.setString(++col, apVendor.getPhone2());             
            statement.setString(++col, apVendor.getFax());
            statement.setString(++col, apVendor.getAccountNumber());
            statement.setFloat(++col, apVendor.getDiscountPercentage());
            statement.setInt(++col, apVendor.getDiscountIfInDays());
            statement.setInt(++col, apVendor.getDiscountDueInDays());
            statement.setString(++col, apVendor.getVendor1099Type());
            statement.setFloat(++col, apVendor.getVendor1099Payment());
            statement.setString(++col, apVendor.getTaxID());
            statement.setString(++col, apVendor.getApprovedVendor());
            statement.setFloat(++col, apVendor.getCreditLimit());           
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	apVendor.setVendorID(rs.getLong(1));
                }
                
                auditDTO.setCompanyId((int) this.companyID);
                auditDTO.setLocaleId((int) apVendor.getLocaleID());
                auditDTO.setUserId((int) this.userID);
                
                insertAudit(apVendor);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    /**
     * 
     * @param apVendor
     * @return
     */
    public boolean updateApVendor(ApVendorDTO apVendor) {
        boolean added = false;
        
        try {
        	ApVendorDTO oldVer = getApVendor(apVendor.getVendorID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE apvendors set VendorCode = ?, Locale = ?, Name = ?, Addr1 = ?, Addr2 = ?, CityState = ?, PostalCode = ?, " +
            		"ContactName = ?, Phone = ?, EmailAddr = ?, DefaultAcctID = ?, Notes = ?, LocationID = ?, DeleteCode = ?, " +
            		"InternalVendor = ?, VendorState = ?, VendorCountry = ?, Phone2 = ?,  "+
            		"Fax = ?, AccountNumber = ?, DiscountPercentage = ?, "+
            		"DiscountIfInDays = ?, DiscountDueInDays =?, Vendor1099Type = ?, Vendor1099Payment = ?, "+
            		"TaxID = ?, ApprovedVendor = ?, CreditLimit = ? WHERE VendorID = ? ");
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            
            statement.setString(++col, apVendor.getVendorCode());
            statement.setLong(++col, apVendor.getLocaleID());
            statement.setString(++col, apVendor.getName());
            statement.setString(++col, apVendor.getAddr1());
            statement.setString(++col, apVendor.getAddr2());
            statement.setString(++col, apVendor.getCityState());
            statement.setString(++col, apVendor.getPostalCode());
            statement.setString(++col, apVendor.getContactName());
            statement.setString(++col, apVendor.getPhone());
            statement.setString(++col, apVendor.getEmailAddr());
            statement.setInt(++col, apVendor.getDefaultAcctID());
            statement.setString(++col, apVendor.getNotes());
            statement.setLong(++col, apVendor.getLocationID());
            statement.setString(++col, apVendor.getDeleteCode());
            statement.setBoolean(++col, apVendor.isInternalVendor());
            statement.setString(++col, apVendor.getVendorState());
            statement.setString(++col, apVendor.getVendorCountry());            
            statement.setString(++col, apVendor.getPhone2());   
            statement.setString(++col, apVendor.getFax());
            statement.setString(++col, apVendor.getAccountNumber());
            statement.setFloat(++col,  apVendor.getDiscountPercentage());
            statement.setInt(++col, apVendor.getDiscountIfInDays());
            statement.setInt(++col, apVendor.getDiscountDueInDays());
            statement.setString(++col, apVendor.getVendor1099Type());
            statement.setFloat(++col, apVendor.getVendor1099Payment());
            statement.setString(++col, apVendor.getTaxID());
            statement.setString(++col, apVendor.getApprovedVendor());
            statement.setFloat(++col, apVendor.getCreditLimit());                            
            statement.setLong(++col, apVendor.getVendorID());          
            statement.executeUpdate();
            added = true;
            
            auditDTO.setCompanyId((int) this.companyID);
            auditDTO.setLocaleId((int) apVendor.getLocaleID());
            auditDTO.setUserId((int) this.userID);
            
            updateAudit(oldVer, apVendor);
            
        } catch (SQLException e) {
            logger.error("SQLException in updateApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    /**
     * @param versionId
     */
    public void deleteApVendor(long masterID) {
        
        try {
        	ApVendorDTO oldVer = getApVendor(masterID);
        	
            String sql = "delete from apvendors WHERE (VendorID = ?)";
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, masterID);
            statement.executeUpdate();     
            
            auditDTO.setCompanyId((int) companyID);
            auditDTO.setUserId((int) userID);
            auditDTO.setLocaleId((int) oldVer.getLocaleID());            
            
            deleteAudit(oldVer, null);
            
        } catch (SQLException e) {
            logger.error("SQLException in deleteApVendor() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteApVendor() : ", e);
        } finally {
            closeConnection();
        }
        
    }
    
    
}
