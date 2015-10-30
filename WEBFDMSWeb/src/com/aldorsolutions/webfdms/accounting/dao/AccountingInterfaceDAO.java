package com.aldorsolutions.webfdms.accounting.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.accounting.model.CustomerDTO;
import com.aldorsolutions.webfdms.accounting.model.DeceasedDTO;
import com.aldorsolutions.webfdms.accounting.model.GLAccountDefaultDTO;
import com.aldorsolutions.webfdms.accounting.model.ItemDTO;
import com.aldorsolutions.webfdms.accounting.model.SaleInfoDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * Workfile: AccountingInterfaceDAO.java
 * Date: Nov 29, 2005 3:32:12 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceDAO extends DAO {
	
	private static Logger logger = Logger.getLogger(AccountingInterfaceDAO.class.getName());

	public AccountingInterfaceDAO() { }
	

	/**
	 * 
	 * @param regionId
	 * @param locationId
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @return
	 */
	public ArrayList <Integer> getUnpostedVitalsIdByCompany(
			long companyId,
			String dbLookup) {
		
		ArrayList <Integer> list = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT transactionhistory.VitalsMasterKey ");
			sql.append("FROM transactionhistory RIGHT OUTER JOIN vitalstats ");
			sql.append("ON transactionhistory.VitalsMasterKey = vitalstats.VitalsMasterKey, locations ");
			sql.append("WHERE ( locations.LocationID = vitalstats.ChapelNumber ");
			sql.append("AND locations.Locale = vitalstats.LocaleNumber ) ");
			sql.append("AND ( transactionhistory.Postedyn <> 'Y' AND locations.CompanyNumber = ? ) ");
			sql.append("GROUP BY transactionhistory.VitalsMasterKey");

			
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(1, companyId);
			rs = statement.executeQuery();
			
			if (rs != null) {
				list = new ArrayList <Integer> ();
				while (rs.next()) {
					list.add(new Integer(rs.getInt(1)));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getUnpostedVitalsIdByCompany() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getUnpostedVitalsIdByCompany() : ", e);
		}	
		finally {
			closeConnection();
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param regionId
	 * @param locationId
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @return
	 */
	public ArrayList <Integer> getUnpostedVitalsIds(
			int regionId, 
			int locationId,
			String dbLookup) {
		
		ArrayList <Integer> list = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT transactionhistory.VitalsMasterKey FROM transactionhistory ");
			sql.append("LEFT OUTER JOIN vitalstats ");
			sql.append("ON vitalstats.VitalsMasterKey = transactionhistory.VitalsMasterKey ");
			sql.append("WHERE (transactionhistory.PostedYN != 'Y') ");
			sql.append("  AND (vitalstats.LocaleNumber = ?) ");
			if (locationId > 0) sql.append("AND (vitalstats.ChapelNumber = ?) ");
			sql.append("GROUP BY transactionhistory.VitalsMasterKey");
			
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, regionId);
			if (locationId > 0) statement.setInt(2, locationId);
			rs = statement.executeQuery();
			
			if (rs != null) {
				list = new ArrayList <Integer> ();
				while (rs.next()) {
					list.add(new Integer(rs.getInt(1)));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getUnpostedVitalsIds() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getUnpostedVitalsIds() : ", e);
		}	
		finally {
			closeConnection();
		}
		
		return list;
	}	
	
	/**
	 * 
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @param vitalsId
	 * @return
	 */
	public DeceasedDTO getDeceased( String dbLookup, long vitalsId) {
		
		DeceasedDTO deceasedDTO = null;
		
		try {			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT vitalstats.ContractCode, vitalstats.DeceasedFirstName, ");
			sql.append("	vitalstats.DeceasedMidName, vitalstats.DeceasedLastName, ");
			sql.append("	vitalstats.DeathDateKey, vitalstats.ArrangerId, ");
			sql.append("	vitalstats.ChapelNumber, locations.Division ");			
			sql.append("FROM vitalstats ");
			sql.append("LEFT OUTER JOIN locations ");
			sql.append("	ON vitalstats.ChapelNumber = locations.LocationID ");
			sql.append("WHERE (VitalsMasterKey = ?)");
			
			makeConnection(dbLookup);
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(1, vitalsId);
			rs = statement.executeQuery();
			
			if (rs.next()) {
				int col = 0;
				deceasedDTO = new DeceasedDTO();
				deceasedDTO.setVitalsMasterKey(vitalsId);
				deceasedDTO.setContractCode(rs.getString(++col));
				deceasedDTO.setDeceasedFirstName(rs.getString(++col));
				deceasedDTO.setDeceasedMidName(rs.getString(++col));
				deceasedDTO.setDeceasedLastName(rs.getString(++col));
				deceasedDTO.setDeathDateKey(rs.getString(++col));
				deceasedDTO.setArrangerId(rs.getInt(++col));
				deceasedDTO.setChapelNumber(rs.getInt(++col));
				deceasedDTO.setDivision(rs.getString(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getDeceased() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getDeceased() : ", e);
		}	
		finally {
			closeConnection();
		}	
		
		return deceasedDTO;
	}
	
	/**
	 * 
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @param vitalsId
	 * @return
	 */
	public SaleInfoDTO getSaleInfo(
			String dbLookup, 
			int vitalsId) {
		
		SaleInfoDTO saleInfoDTO = null;
		
		try {			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT vitalstats.ContractCode, financialdata.SaleDate, locations.Name, locations.locationNum ");		
			sql.append("FROM vitalstats ");
			sql.append("JOIN financialdata ");
			sql.append("ON vitalstats.VitalsMasterKey = financialdata.VitalsMasterKey ");
			sql.append("JOIN locations ");
			sql.append("ON vitalstats.ChapelNumber = locations.LocationID ");
			sql.append("WHERE vitalstats.VitalsMasterKey = ?");
//			logger.debug(sql);
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, vitalsId);
			rs = statement.executeQuery();

			if (rs.next()) {
				int col = 0;
				saleInfoDTO = new SaleInfoDTO();				
				saleInfoDTO.setVitalsMasterKey(vitalsId);
				saleInfoDTO.setContractCode(rs.getString(++col));
				saleInfoDTO.setSaleDate(rs.getString(++col));
				saleInfoDTO.setLocationName(rs.getString(++col));
				saleInfoDTO.setLocationCode(rs.getString(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getSaleInfo() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getSaleInfo() : ", e);
		}	
		finally {
			closeConnection();
		}	
		
		return saleInfoDTO;
	}
	
	/**
	 * 
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @param vitalsId
	 * @return
	 */
	public CustomerDTO getCustomer(
			String dbLookup, 
			int vitalsId) {
		
		CustomerDTO customerDTO = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT RecordNumber, Honorific, FirstName, LastName, ");
			sql.append("StreetAddr1, StreetAddr2, City, State, Zip, HomePhone, SocialSecNo, ");
			sql.append("Relation, ContractSignerYN, ModifiedFlag, Language, CashSale, Refused, ");
			sql.append("SendInvoice, SequenceNumber, DeleteCode, WorkPhone, FileVersion, County, ");
			sql.append("Street3, Street4, DateModified, TimeModified, EmailAddress, CellPhone ");
			sql.append("FROM billtoparties ");
			sql.append("WHERE (ContractSignerYN = 'Y') AND (VitalsID = ?)");
			
			makeConnection(dbLookup);			
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, vitalsId);
			rs = statement.executeQuery();
			
			if (rs.next()) {
				int col = 0;
				customerDTO = new CustomerDTO();
				customerDTO.setVitalsId(vitalsId);
				customerDTO.setRecordNumber(rs.getLong(++col));				
				customerDTO.setHonorific(rs.getString(++col));
				customerDTO.setFirstName(rs.getString(++col));
				customerDTO.setLastName(rs.getString(++col));
				customerDTO.setStreetAddr1(rs.getString(++col));
				customerDTO.setStreetAddr2(rs.getString(++col));
				customerDTO.setCity(rs.getString(++col));
				customerDTO.setState(rs.getString(++col));
				customerDTO.setZip(rs.getString(++col));
				customerDTO.setHomePhone(rs.getString(++col));
				customerDTO.setSocialSecNo(rs.getString(++col));
				customerDTO.setRelation(rs.getString(++col));
				customerDTO.setContractSignerYN(rs.getString(++col));
				customerDTO.setModifiedFlag(rs.getString(++col));
				customerDTO.setLanguage(rs.getString(++col));
				customerDTO.setCashSale(rs.getString(++col));
				customerDTO.setRefused(rs.getString(++col));
				customerDTO.setSendInvoice(rs.getString(++col));
				customerDTO.setSequenceNumber(rs.getString(++col));
				customerDTO.setDeleteCode(rs.getString(++col));
				customerDTO.setWorkPhone(rs.getString(++col));
				customerDTO.setFileVersion(rs.getString(++col));
				customerDTO.setCounty(rs.getString(++col));
				customerDTO.setStreet3(rs.getString(++col));
				customerDTO.setStreet4(rs.getString(++col));
				customerDTO.setDateModified(rs.getDate(++col));
				customerDTO.setTimeModified(rs.getString(++col));
				customerDTO.setEmailAddress(rs.getString(++col));
				customerDTO.setCellPhone(rs.getString(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCustomer() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCustomer() : ", e);
		}	
		finally {
			closeConnection();
		}	
		
		return customerDTO;
	}
	
	/**
	 * 
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @param vitalsId
	 * @return
	 */
	public CustomerDTO getCustomerACCPAC(
			String dbLookup, 
			int vitalsId) {
		
		CustomerDTO customerDTO = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT RecordNumber, Honorific, FirstName, LastName, ");
			sql.append("StreetAddr1, StreetAddr2, City, State, Zip, HomePhone, SocialSecNo, ");
			sql.append("Relation, ContractSignerYN, ModifiedFlag, Language, CashSale, Refused, ");
			sql.append("SendInvoice, SequenceNumber, DeleteCode, WorkPhone, FileVersion, County, ");
			sql.append("Street3, Street4, DateModified, TimeModified, EmailAddress, CellPhone ");
			sql.append("FROM billtoparties ");
			sql.append("WHERE ((ContractSignerYN = 'Y') OR (SendInvoice = 'Y')) AND (VitalsID = ?)");
			
			makeConnection(dbLookup);			
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, vitalsId);
			rs = statement.executeQuery();
			
			if (rs.next()) {
				int col = 0;
				customerDTO = new CustomerDTO();
				customerDTO.setVitalsId(vitalsId);
				customerDTO.setRecordNumber(rs.getLong(++col));				
				customerDTO.setHonorific(rs.getString(++col));
				customerDTO.setFirstName(rs.getString(++col));
				customerDTO.setLastName(rs.getString(++col));
				customerDTO.setStreetAddr1(rs.getString(++col));
				customerDTO.setStreetAddr2(rs.getString(++col));
				customerDTO.setCity(rs.getString(++col));
				customerDTO.setState(rs.getString(++col));
				customerDTO.setZip(rs.getString(++col));
				customerDTO.setHomePhone(rs.getString(++col));
				customerDTO.setSocialSecNo(rs.getString(++col));
				customerDTO.setRelation(rs.getString(++col));
				customerDTO.setContractSignerYN(rs.getString(++col));
				customerDTO.setModifiedFlag(rs.getString(++col));
				customerDTO.setLanguage(rs.getString(++col));
				customerDTO.setCashSale(rs.getString(++col));
				customerDTO.setRefused(rs.getString(++col));
				customerDTO.setSendInvoice(rs.getString(++col));
				customerDTO.setSequenceNumber(rs.getString(++col));
				customerDTO.setDeleteCode(rs.getString(++col));
				customerDTO.setWorkPhone(rs.getString(++col));
				customerDTO.setFileVersion(rs.getString(++col));
				customerDTO.setCounty(rs.getString(++col));
				customerDTO.setStreet3(rs.getString(++col));
				customerDTO.setStreet4(rs.getString(++col));
				customerDTO.setDateModified(rs.getDate(++col));
				customerDTO.setTimeModified(rs.getString(++col));
				customerDTO.setEmailAddress(rs.getString(++col));
				customerDTO.setCellPhone(rs.getString(++col));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCustomer() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCustomer() : ", e);
		}	
		finally {
			closeConnection();
		}	
		
		return customerDTO;
	}	
	
	
	/**
	 * 
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @param vitalsId
	 * @return
	 */
	public ArrayList getTransactions(
			String dbLookup, 
			int vitalsId) {
		
		ArrayList list = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT transactionhistory.TranHistID, transactionhistory.ChargeType, ");
			sql.append(" transactionhistory.Description, transactionhistory.AmountOfTran, ");
			sql.append(" transactionhistory.ARacct, transactionhistory.GLacct, " );
			sql.append(" transactionhistory.TaxExemptAmount, transactionhistory.TaxCode, ");
			sql.append(" concat(substring(financialdata.saledate,5,4),'-',substring(financialdata.saledate,1,2),");
			sql.append("'-',substring(financialdata.saledate,3,2)), transactionhistory.AccountingPeriod, ");
			sql.append(" transactionhistory.SPFcode, transactionhistory.ItemCategory, ");
			sql.append(" transactionhistory.ReceiptNumber, transactionhistory.Postedyn, ");
			sql.append(" transactionhistory.OriginalPostingYN, transactionhistory.InventoryItemName, ");
			sql.append(" transactionhistory.InvMasterKey, transactionhistory.InvCostOfSale, ");
			sql.append(" transactionhistory.PaymentMethod, transactionhistory.PaymentComponent, ");
			sql.append(" transactionhistory.UserInitials, transactionhistory.ManualReceiptNo, ");
			sql.append(" transactionhistory.RecordVersion, transactionhistory.InterfaceSequenceNo, ");
			sql.append(" transactionhistory.OrigChrgAmount, transactionhistory.OrigChrgDescr, ");
			sql.append(" transactionhistory.Exported, transactionhistory.DateModified, ");
			sql.append(" transactionhistory.TimeModified, transactionhistory.DatePaid, ");
			sql.append(" transactionhistory.DepositBatchNumber, transactionhistory.ClaimNumber, ");
			sql.append(" locations.LocationID, transactionhistory.InvOnHandID, ");
			sql.append(" transactionhistory.TaxAmount, locations.LocationNum ");
			sql.append("FROM financialdata, transactionhistory, vitalstats, locations ");
			sql.append("WHERE ( financialdata.VitalsMasterKey = transactionhistory.VitalsMasterKey AND ");
			sql.append(" transactionhistory.VitalsMasterKey = vitalstats.VitalsMasterKey AND ");
			sql.append(" locations.LocationID = vitalstats.ChapelNumber AND ");
			sql.append(" locations.Locale = vitalstats.LocaleNumber ) AND ");
			sql.append(" ( (transactionhistory.VitalsMasterKey = ?) AND ( transactionhistory.Postedyn != 'Y' ) )");
			
			makeConnection(dbLookup);			
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, vitalsId);
			rs = statement.executeQuery();
			
			if (rs != null) {
				list = new ArrayList();
				while (rs.next()) {
					int col = 0;
					ItemDTO itemDTO = new ItemDTO();
					itemDTO.setVitalsMasterKey(vitalsId);
					itemDTO.setTranHistId(rs.getInt(++col));
					itemDTO.setChargeType(rs.getInt(++col));
					itemDTO.setDescription(rs.getString(++col));
					itemDTO.setAmountOfTran(rs.getInt(++col));
					itemDTO.setArAcct(rs.getString(++col));
					itemDTO.setGlAcct(rs.getString(++col));
					itemDTO.setTaxExemptAmount(rs.getInt(++col));
					itemDTO.setTaxCode(rs.getString(++col));
					itemDTO.setDateOfTran(rs.getDate(++col));
					itemDTO.setAccountingPeriod(rs.getString(++col));
					itemDTO.setSpfCode(rs.getString(++col));
					itemDTO.setItemCategory(rs.getString(++col));
					itemDTO.setReceiptNumber(rs.getInt(++col));
					itemDTO.setPostedYN(rs.getString(++col));
					itemDTO.setOriginalPostingYN(rs.getString(++col));
					itemDTO.setInventoryItemName(rs.getString(++col));
					itemDTO.setInvMasterKey(rs.getInt(++col));
					itemDTO.setInvCostOfSale(rs.getInt(++col));
					itemDTO.setPaymentMethod(rs.getString(++col));
					itemDTO.setPaymentComponent(rs.getString(++col));
					itemDTO.setUserInitials(rs.getString(++col));
					itemDTO.setManualReceiptNo(rs.getString(++col));
					itemDTO.setRecordVersion(rs.getInt(++col));
					itemDTO.setInterfaceSequenceNo(rs.getInt(++col));
					itemDTO.setOrigChrgAmount(rs.getInt(++col));
					itemDTO.setOrigChrgDescr(rs.getString(++col));
					itemDTO.setExported(rs.getString(++col));
					itemDTO.setDateModified(rs.getDate(++col));
					itemDTO.setTimeModified(rs.getString(++col));
					itemDTO.setDatePaid(rs.getDate(++col));
					itemDTO.setDepositBatchNumber(rs.getString(++col));
					itemDTO.setClaimNumber(rs.getString(++col));
					itemDTO.setLocationId(rs.getInt(++col));
					itemDTO.setInvOnHandId(rs.getInt(++col));
					itemDTO.setTaxAmount(rs.getInt(++col));
					itemDTO.setLocationCode(rs.getString(++col));
					list.add(itemDTO);					
				}
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in getTransactions() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getTransactions() : ", e);
		}	
		finally {
			closeConnection();
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 * @param localeID
	 * @param locationID
	 * @return
	 */
	public ArrayList getGLAccountDefaults(
			String dbLookup, 
			long localeID ) {
		
		ArrayList list = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT glacctID, locale, deleteCode, location, category, disposition, ");
			sql.append("saleType, revenueAcct, invAssetAcct, InvCogsAcct ");
			sql.append("FROM glacctdefault ");
			sql.append("WHERE (locale = ?) AND (deleteCode != 'D')");
			
			makeConnection(dbLookup);			
			statement = conn.prepareStatement(sql.toString());
			statement.setLong(1, localeID);
			rs = statement.executeQuery();
			
			if (rs != null) {
				list = new ArrayList();
				while (rs.next()) {
					int col = 0;
					GLAccountDefaultDTO glacct = new GLAccountDefaultDTO();
					glacct.setGlAcctID(rs.getInt(++col));
					glacct.setLocale(rs.getInt(++col));
					glacct.setDeleteCode(rs.getString(++col));
					glacct.setLocationID(rs.getInt(++col));
					glacct.setCategory(rs.getString(++col));
					glacct.setDisposition(rs.getString(++col));
					glacct.setSaleType(rs.getString(++col));
					glacct.setRevenueAcct(rs.getString(++col));
					glacct.setInvAssetAcct(rs.getString(++col));
					glacct.setInvCogsAcct(rs.getString(++col));
					list.add(glacct);					
				}
			}
			
		} catch (SQLException e) {
			logger.error("SQLException in getTransactions() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getTransactions() : ", e);
		}	
		finally {
			closeConnection();
		}
		
		return list;
	}
	
	
	/**
	 * 
	 * @param transId
	 * @param dbUsername
	 * @param dbPassword
	 * @param dbUrl
	 */
	public void postTransaction(
			int tranHistId, 
			String dbLookup) {
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE transactionhistory SET Postedyn='Y' WHERE (TranHistID = ?)");
			
			makeConnection(dbLookup);			
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, tranHistId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("SQLException in postTransaction() : ", e);
		} catch (Exception e) {
			logger.error("Exception in postTransaction() : ", e);
		}	
		finally {
			closeConnection();
		}
		
	}
	
}
