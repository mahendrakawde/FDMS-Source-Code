package com.aldorsolutions.webfdms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbPnCharge;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.SalesTaxes;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
/**
 * This class represent the set of pre-need charges being displayed and edited
 * in PnCharges.JSP.
 * Creation date: (2/10/2003 1:34:23 PM)
 * @author:
 */
public class TempPnCharges {
    
    private Logger logger = Logger.getLogger(TempPnCharges.class.getName());
    private com.aldorsolutions.webfdms.beans.DbUser user;
    private int contractID;
    private int totalCharges;
    public final static int ACTION_UNCHANGED = 0;
    public final static int ACTION_NEW 		= 1;
    public final static int ACTION_MODIFIED = 2;
    public final static int ACTION_DELETE 	= 3;
    public final static int ACTION_IGNORE 	= 4;
    private java.lang.String tempFileName;
    /**
     * TempPnCharges constructor comment.
     */
    public TempPnCharges(DbUser auser, int acontract) {
        super();
        this.user = auser;
        this.contractID = acontract;
        this.tempFileName = "tempPnCharges"+auser.getId()+acontract;
    }
    /**
     * Modify an existing temp-charge item specified by the first parameter.
     * Creation date: (2/11/2003 10:48:07 AM)
     * @return boolean true means successful
     * @param PnCharge the charge object to be added to the temp-table
     */
    public boolean addAcharge(int seqno, short typecode, String descr,
    int price, String spfcode, int priceID, int invID,
    String categoryCode, String taxcode, int taxexempt) {
        
        Connection connection = null;
        java.sql.PreparedStatement statement = null;
        DatabaseTransaction t = null;
        StringBuffer updateStmt = new StringBuffer();
        updateStmt.append("INSERT INTO "+getTempFileName()+" (");
        updateStmt.append("ChargeType, ");
        updateStmt.append("Description, ");
        updateStmt.append("Amount, ");
        updateStmt.append("Sequence, ");
        updateStmt.append("TaxCode, ");
        updateStmt.append("TaxExemptAmt, ");
        updateStmt.append("sessionAction, ");
        updateStmt.append("ContractID,");
        updateStmt.append("SPFcode,");
        updateStmt.append("PriceListId,");
        updateStmt.append("InvMasterKey,");
        updateStmt.append("CategoryCode ");
        
        updateStmt.append(") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
        boolean added = true;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.prepareStatement(updateStmt.toString());
            statement.setShort(1, typecode);
            statement.setString(2, descr);
            statement.setInt(	3, price);
            statement.setInt(	4, seqno);
            statement.setString(5, taxcode);
            statement.setInt(	6, taxexempt);
            statement.setInt(	7, ACTION_NEW);
            statement.setInt(	8, getContractID());
            statement.setString(9, spfcode);
            statement.setInt(	10, priceID);
            statement.setInt(	11, invID);
            statement.setString(12, categoryCode);
            
            statement.executeUpdate();
            //AppLog.trace("addAcharge result:"+rcount);
            
            setTotalCharges(	getTotalCharges()+price);
        } catch (PersistenceException e){
            if (e != null) added = false;
            logger.error("Persistence Exception in getAcharge.initializeSet. " + e );
        } catch( SQLException e ) {
            if (e != null) added = false;
            logger.error("SQL Exception in getAcharge.initializeSet:",e);
        } catch (Exception e) {
            if (e != null) added = false;
            logger.error("Error in addAcharge() : ", e);
        } finally {
        	
        	if (connection != null) {
        		try {
        			connection.close();
            		connection = null;
        		} 
        		catch ( Throwable r ) {
        			
        		}
            }
        	
        	if ( statement != null ) {
        		try {
            		statement.close();
            		statement = null;
        		} 
        		catch ( Throwable r ) {
        			
        		}
        	}
        	
            if (t != null) {
            	t.closeConnection();
				t = null;
            }
        }
        
        return added;
    }
    /**
     * Calculate state and local sales tax.
     * Creation date: (3/4/2003 3:42:00 PM)
     * @param user com.aldorsolutions.webfdms.beans.DbUser
     * @param version java.lang.String
     */
    public void calculateSalesTax(String version) throws Exception {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        DatabaseTransaction t = null;
        // SELECT statement retrieves active (not deleted) charges
        String selectStmt = "select * from "+getTempFileName()+" WHERE sessionAction < 3";
        int	stateTaxId = 0;
        int localTaxId = 0;
        int articleTaxId = 0;
        int	lineId = 0;
        DbPriceList plItem = null;
        PnCharge	acharge = null;
        try {
            // get initialized collection of tax codes and rates
            SalesTaxes taxlist = new SalesTaxes(user);
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            results = statement.executeQuery(selectStmt);
            // For each row in the result set
            while (results.next()){
                lineId = (int)results.getShort("ChargeType");
                // skip 97, 98 and 99 sales tax lines
                if (lineId==SalesTaxes.LOCAL_TAX_ID){
                    localTaxId = results.getInt("AutoIndex");
                    continue;
                }
                if (lineId==SalesTaxes.STATE_TAX_ID){
                    stateTaxId = results.getInt("AutoIndex");
                    continue;
                }
                if (lineId==SalesTaxes.ARTICLE_TAX_ID){
                    stateTaxId = results.getInt("AutoIndex");
                    continue;
                }
                // process this charge item through the sales tax object
                String  taxcode = results.getString("TaxCode");
                int		amount = results.getInt("Amount");
                int		exemptamount = results.getInt("TaxExemptAmt");
                taxlist.addSaleAmt(taxcode, amount, exemptamount);
            }
            // Get tax amounts
            int stateTax = taxlist.calculateStateSalesTax();
            int localTax = taxlist.calculateLocalSalesTax();
            int articleTax = taxlist.calculateArticleSalesTax();
            
            // if we have a state sales tax amount, make sure line 99 is in the charge collection
            if (stateTax != 0 && stateTaxId==0){
                // add charge 99
                plItem = taxlist.getStateTaxPriceListItem(getUser(), version);
                if (plItem != null){
                    addAcharge(plItem.getInvoiceSeqNo(), plItem.getContrLine(), plItem.getContrDescr(), stateTax, "S", plItem.getId(), 0, String.valueOf(plItem.getCategory()), "", plItem.getTaxExempt());
                }
            }
            else if (stateTaxId != 0){
                // update amount for existing sales tax line
                acharge = getAcharge(stateTaxId);
                int seqno 	= 	FormatNumber.parseInteger(acharge.getItemSequenceNumber());
                short typecode	= FormatNumber.parseShort(acharge.getItemTypeCode());
                int	  taxexempt = FormatNumber.parseInteger(acharge.getItemTaxExempt());
                changeAcharge(stateTaxId, seqno, typecode, acharge.getItemTypeDescription(), stateTax, acharge.getItemTaxCode(), taxexempt);
            }
            
            // if we have a local sales tax amount, make sure line 98 is in the charge collection
            if (localTax != 0 && localTaxId==0){
                // add charge 98
                plItem = taxlist.getLocalTaxPriceListItem(getUser(), version);
                if (plItem != null){
                    addAcharge(plItem.getInvoiceSeqNo(), plItem.getContrLine(), plItem.getContrDescr(), localTax, "S", plItem.getId(), 0, String.valueOf(plItem.getCategory()), "", plItem.getTaxExempt());
                }
            }
            else if (localTaxId != 0){
                // update amount for existing sales tax line
                acharge = getAcharge(localTaxId);
                int seqno 	= 	FormatNumber.parseInteger(acharge.getItemSequenceNumber());
                short typecode	= FormatNumber.parseShort(acharge.getItemTypeCode());
                int	  taxexempt = FormatNumber.parseInteger(acharge.getItemTaxExempt());
                changeAcharge(localTaxId, seqno, typecode, acharge.getItemTypeDescription(), localTax, acharge.getItemTaxCode(), taxexempt);
            }
            
            // if we have a local sales tax amount, make sure line 98 is in the charge collection
            if (articleTax != 0 && articleTaxId==0){
                // add charge 98
                plItem = taxlist.getArticleTaxPriceListItem(getUser(), version);
                if (plItem != null){
                    addAcharge(plItem.getInvoiceSeqNo(), plItem.getContrLine(), plItem.getContrDescr(), articleTax, "S", plItem.getId(), 0, String.valueOf(plItem.getCategory()), "", plItem.getTaxExempt());
                }
            }
            else if (articleTaxId != 0){
                // update amount for existing sales tax line
                acharge = getAcharge(articleTaxId);
                int seqno 	= 	FormatNumber.parseInteger(acharge.getItemSequenceNumber());
                short typecode	= FormatNumber.parseShort(acharge.getItemTypeCode());
                int	  taxexempt = FormatNumber.parseInteger(acharge.getItemTaxExempt());
                changeAcharge(articleTaxId, seqno, typecode, acharge.getItemTypeDescription(), articleTax, acharge.getItemTaxCode(), taxexempt);
            }
            
        } catch (PersistenceException e){
            logger.error("Persistence Exception in TempPnCharges.calculateSalesTax. " + e);            
        } catch( SQLException e ) {
            logger.error("SQL Exception in TempPnCharges.calculateSalesTax:",e);
        } catch (Exception e) {
            logger.error("Error in calculateSalesTax() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
    }
    /**
     * Modify an existing temp-charge item specified by the first parameter.
     * Creation date: (2/11/2003 10:48:07 AM)
     * @return boolean true means successful
     * @param chargeid int
     */
    public boolean changeAcharge(int chargeid, int seqno, short typecode, String descr,
    int price, String taxcode, int taxexempt) {
        Connection connection = null;
        java.sql.PreparedStatement statement = null;
        ResultSet results = null;
        DatabaseTransaction t = null;
        String selectStmt = "select * from "+getTempFileName()+" WHERE AutoIndex="+chargeid;
        StringBuffer updateStmt = new StringBuffer();
        updateStmt.append("UPDATE "+getTempFileName()+" SET ");
        updateStmt.append("ChargeType =?, ");
        updateStmt.append("Description =?, ");
        updateStmt.append("Amount =?, ");
        updateStmt.append("Sequence =?, ");
        updateStmt.append("TaxCode =?, ");
        updateStmt.append("TaxExemptAmt =?, ");
        updateStmt.append("sessionAction =? ");
        updateStmt.append(" WHERE AutoIndex=?");
        boolean changed = true;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            // get original record and its original price
            Statement selstmt = connection.createStatement();
            results = selstmt.executeQuery(selectStmt);
            if (!results.next()){
                changed = false;
            } else {
            
                int origPrice  = results.getInt("Amount");
                int newAction = results.getInt("sessionAction");
                if (newAction != ACTION_NEW){
                    newAction = ACTION_MODIFIED;
                }
                // update temp table
                statement = connection.prepareStatement(updateStmt.toString());
                statement.setShort(1, typecode);
                statement.setString(2, descr);
                statement.setInt(	3, price);
                statement.setInt(	4, seqno);
                statement.setString(5, taxcode);
                statement.setInt(	6, taxexempt);
                statement.setInt(	7, newAction);
                statement.setInt(	8, chargeid);
                statement.executeUpdate();
                //AppLog.trace("changeAcharge result:"+rcount);
                setTotalCharges(	getTotalCharges()+price-origPrice);
            }

        } catch (PersistenceException e){
            if (e != null) changed = false;
            logger.error("Persistence Exception in getAcharge.initializeSet. " + e);            
        } catch( SQLException e ) {
            if (e != null) changed = false;
            logger.error("SQL Exception in getAcharge.initializeSet:",e);
        } catch (Exception e) {
            if (e != null) changed = false;
            logger.error("Error in calculateSalesTax() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        return changed;
    }
    /**
     * Flag a charge for deletion when the temp table is saved.
     * Creation date: (2/11/2003 10:48:07 AM)
     * @return boolean false = unsuccessful delete
     * @param chargeid int ID of temp charge to be deleted
     */
    public boolean deleteAcharge(int chargeid) {
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        DatabaseTransaction t = null;
        String selectStmt = "select * from "+getTempFileName()+" WHERE AutoIndex="+chargeid;
        boolean deleted = true;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            // retrieve record to be flagged for deletion and check its status
            results = statement.executeQuery(selectStmt);
            if (!results.next()){
                deleted = false;
            } else {
                int origStatus = results.getInt("sessionAction");
                int origPrice  = results.getInt("Amount");
                int newStatus = ACTION_DELETE;
                if (origStatus == ACTION_NEW){
                    // flag to ignore if added and deleted in the same session
                    newStatus = ACTION_IGNORE;
                }
                // update temp table with new status
                String deleteStmt = "UPDATE "+getTempFileName()+" SET sessionAction="+newStatus+" WHERE AutoIndex="+chargeid;
                statement.executeUpdate(deleteStmt);
                //AppLog.trace("delete charge "+chargeid+" result:"+rcount);
                setTotalCharges(	getTotalCharges()-origPrice);
            }
        } catch (PersistenceException e){
            if (e != null) deleted = false;
            logger.error("Persistence Exception in getAcharge.initializeSet. " + e);            
        } catch( SQLException e ) {
            if (e != null) deleted = false;
            logger.error("SQL Exception in getAcharge.initializeSet:",e);            
        } catch (Exception e) {
            if (e != null) deleted = false;
            logger.error("Error in deleteAcharge() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }

        return deleted;
    }
    /**
     * delete list of temporary charges
     * Creation date: (2/10/2003 1:44:31 PM)
     */
    public void deleteTempTable() {
        Connection connection = null;
        Statement statement = null;
        DatabaseTransaction t = null;
        String dropStmt = "DROP TABLE IF EXISTS "+getTempFileName();
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(dropStmt);
            //AppLog.trace("Drop 1 result:"+rcount);
        } catch (PersistenceException e){
            logger.error("Persistence Exception in TempPnCharges.initializeSet. " + e);            
        } catch( SQLException e ) {
            logger.error("SQL Exception in TempPnCharges.initializeSet:"+e);
        } catch (Exception e) {
            logger.error("Error in deleteTempTable() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/11/2003 10:48:07 AM)
     * @return fdms.ui.struts.custom.PnCharge
     * @param chargeid int
     */
    public PnCharge getAcharge(int chargeid) {
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        DatabaseTransaction t = null;
        String selectStmt = "select * from "+getTempFileName()+" WHERE AutoIndex="+chargeid;
        PnCharge acharge = null;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            results = statement.executeQuery(selectStmt);
            int invID=0;
            String picture="";
            // For each column in the result set
            if (results.next()){
                invID = 	results.getInt("InvMasterKey");
                if (invID > 0){
                    DbInvMaster dbInvMaster = FdmsDb.getInstance().getInvMaster(t, invID);
                    picture = dbInvMaster.getImageUrl();
                }
                else {
                    picture = "";
                }
                acharge = new PnCharge();
                acharge.setItemID(				results.getObject("AutoIndex").toString());
                acharge.setItemPicture(picture);
                acharge.setItemSequenceNumber(	results.getObject("Sequence").toString());
                acharge.setItemTypeCode(		results.getObject("ChargeType").toString());
                acharge.setItemTypeDescription(	results.getObject("Description").toString());
                acharge.setItemPrice(			FormatCurrency.toCurrency((long)results.getInt("Amount")))	;
                acharge.setItemTaxCode(			results.getObject("TaxCode").toString());
                acharge.setItemTaxExempt(		FormatCurrency.toCurrency((long)results.getInt("TaxExemptAmt")));
                acharge.setSessionStatus(		results.getInt("sessionAction"));
                if (acharge.getSessionStatus()==ACTION_DELETE
                || acharge.getSessionStatus()==ACTION_IGNORE){
                    acharge.setItemDeletion("D");
                }
            }
            
        } catch (PersistenceException e){
            logger.error("Persistence Exception in getAcharge.initializeSet. " + e);            
        } catch( SQLException e ) {
            logger.error("SQL Exception in getAcharge.initializeSet:", e);
        } catch (Exception e) {
            logger.error("Error in getAcharge() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        return acharge;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/10/2003 1:46:03 PM)
     * @return int
     */
    public int getContractID() {
        return contractID;
    }
    /**
     * Create list of temporary charges for specified contract.
     * Creation date: (2/10/2003 1:44:31 PM)
     */
    public TreeMap getPnCharges() {
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        DatabaseTransaction t = null;
        String selectStmt = "select * from "+getTempFileName();
        PnCharge acharge = null;
        TreeMap chargemap = new TreeMap();
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            results = statement.executeQuery(selectStmt);
            Integer seqnumber;
            int invID=0;
            String picture="";
            setTotalCharges(0);
            // For each column in the result set
            while (results.next()){
                seqnumber = new Integer(results.getInt("Sequence"));
                invID = 	results.getInt("InvMasterKey");
                if (invID > 0){
                    DbInvMaster dbInvMaster = FdmsDb.getInstance().getInvMaster(t, invID);
                    picture = dbInvMaster.getImageUrl();
                }
                else {
                    picture = "";
                }
                seqnumber = uniqueSequenceNo(chargemap, seqnumber.intValue());
                acharge = new PnCharge();
                acharge.setItemID(				results.getObject("AutoIndex").toString());
                acharge.setItemPicture(picture);
                acharge.setItemSequenceNumber(	seqnumber.toString());
                acharge.setItemTypeCode(		results.getObject("ChargeType").toString());
                acharge.setItemTypeDescription(	results.getObject("Description").toString());
                acharge.setItemPrice(			FormatCurrency.toCurrency((long)results.getInt("Amount")))	;
                acharge.setItemTaxCode(			results.getObject("TaxCode").toString());
                acharge.setItemTaxExempt(		FormatCurrency.toCurrency((long)results.getInt("TaxExemptAmt")));
                acharge.setSessionStatus(		results.getInt("sessionAction"));
                if (acharge.getSessionStatus()==ACTION_DELETE
                || acharge.getSessionStatus()==ACTION_IGNORE){
                    acharge.setItemDeletion("D");
                }
                else {
                    // if active charge then accumulate total
                    setTotalCharges(getTotalCharges()+results.getInt("Amount"));
                }
                chargemap.put(seqnumber, acharge);
            }
            
        } catch (PersistenceException e){
            logger.error("Persistence Exception in TempPnCharges.initializeSet. " + e);            
        } catch( SQLException e ) {
            logger.error("SQL Exception in TempPnCharges.initializeSet:", e);
        } catch (Exception e) {
            logger.error("Error in getPnCharges() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        return chargemap;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/14/2003 2:03:19 PM)
     * @return java.lang.String
     */
    public java.lang.String getTempFileName() {
        return tempFileName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/10/2003 2:45:57 PM)
     * @return int
     */
    public int getTotalCharges() {
        return totalCharges;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/10/2003 1:41:54 PM)
     * @return com.aldorsolutions.webfdms.beans.DbUser
     */
    public com.aldorsolutions.webfdms.beans.DbUser getUser() {
        return user;
    }
    /**
     * Create list of temporary charges for specified contract.
     * Creation date: (2/10/2003 1:44:31 PM)
     */
    public void initializeSet() {
        Connection connection = null;
        Statement statement = null;
        DatabaseTransaction t = null;
        String tempfilename = getTempFileName();
        String dropStmt = "DROP TABLE IF EXISTS "+tempfilename;
        String createStmt = "CREATE TABLE "+tempfilename+" (PRIMARY KEY(AutoIndex)) SELECT * FROM pncharges WHERE ContractID="+getContractID();
        String alterStmt = "ALTER TABLE "+tempfilename+" MODIFY AutoIndex INT UNSIGNED NOT NULL AUTO_INCREMENT";
        String addActionCol="ALTER TABLE "+tempfilename+" ADD COLUMN sessionAction INT DEFAULT 0";
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(dropStmt);
            statement.executeUpdate(createStmt);
            statement.executeUpdate(alterStmt);
            statement.executeUpdate(addActionCol);

        } catch (PersistenceException e){
            logger.error("Persistence Exception in TempPnCharges.initializeSet. " + e);            
        } catch( SQLException e ) {
            logger.error("SQL Exception in TempPnCharges.initializeSet:", e);
        } catch (Exception e) {
            logger.error("Error in initializeSet() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
    }
    /**
     * Save changes, additions, deletions to DbPnCharges
     * Creation date: (2/11/2003 10:48:07 AM)
     * @return boolean false = unsuccessful delete
     * @param chargeid int ID of temp charge to be deleted
     */
    public boolean saveCharges() {
        DbPnCharge dbcharge = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        DatabaseTransaction t = null;
        String selectStmt = "select * from "+getTempFileName();
        boolean saved = true;
        
        try {
            // AppLog.trace("Begin PnCharges.saveCharges: "+selectStmt);
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getUser());
            connection = t.getConnection();
            statement = connection.createStatement();
            results = statement.executeQuery(selectStmt);
            int recordID = 0;
            int action = 0;
            // For each column in the result set
            while (results.next()){
                // AppLog.trace("Saving charge: "+results.getInt("AutoIndex")+" "+results.getString("Description"));
                recordID = results.getInt("AutoIndex");
                action = results.getInt("sessionAction");
                if (action == ACTION_UNCHANGED || action == ACTION_IGNORE){
                    continue;
                }
                if (action == ACTION_NEW){
                    dbcharge = new DbPnCharge();
                    dbcharge.setNew();
                    dbcharge.setContractID(getContractID());
                    t.addPersistent(dbcharge);
                }
                if (action == ACTION_MODIFIED){
                    dbcharge = FdmsDb.getInstance().getPnCharge(t, recordID);
                }
                if (action == ACTION_DELETE){
                    dbcharge = FdmsDb.getInstance().getPnCharge(t, recordID);
                    dbcharge.remove();
                }
                // common code for new or modified
                dbcharge.setAmount(		results.getInt("Amount"));
                dbcharge.setDescription(results.getString("Description"));
                dbcharge.setInvSeqNo(	results.getInt("InvMasterKey"));
                dbcharge.setItemCategoryType(FormatString.getFirstChar(results.getString("CategoryCode")));
                dbcharge.setPriceListId(results.getInt("PriceListId"));
                dbcharge.setSequenceNumber(	results.getInt("Sequence"));
                dbcharge.setSpfCode(		FormatString.getFirstChar(results.getString("SPFcode")));
                dbcharge.setType(			results.getInt("ChargeType"));
                dbcharge.setTaxCode(		results.getString("TaxCode"));
                dbcharge.setTaxExempt(		results.getInt("TaxExemptAmt"));
            }
            t.save();
        }
        catch (PersistenceException e){
            logger.error("Persistence Exception in getAcharge.initializeSet. " + e);
            saved = false;
        }
        catch( SQLException e ) {
            logger.error("SQL Exception in getAcharge.initializeSet:", e);
            saved = false;
        } catch (Exception e) {
            logger.error("Error in saveCharges() : ", e);
        } finally {
            if (t != null) {
            	t.closeConnection();
            	t = null;
            }
        }
        
        return saved;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/10/2003 1:46:03 PM)
     * @param newContractID int
     */
    public void setContractID(int newContractID) {
        contractID = newContractID;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/14/2003 2:03:19 PM)
     * @param newTempFileName java.lang.String
     */
    public void setTempFileName(java.lang.String newTempFileName) {
        tempFileName = newTempFileName;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/10/2003 2:45:57 PM)
     * @param newTotalCharges int
     */
    public void setTotalCharges(int newTotalCharges) {
        totalCharges = newTotalCharges;
    }
    /**
     * Insert the method's description here.
     * Creation date: (2/10/2003 1:41:54 PM)
     * @param newAuser com.aldorsolutions.webfdms.beans.DbUser
     */
    public void setUser(com.aldorsolutions.webfdms.beans.DbUser newAuser) {
        user = newAuser;
    }
    /**
     * Make sure that a sequence number does not exist already in the collection.
     * If so, increment it until it is unique
     * Creation date: (2/14/2003 10:11:10 AM)
     * @return java.lang.Integer next unique sequence number >= seqno param
     * @param chargemap java.util.TreeMap
     * @param seqno int
     */
    protected Integer uniqueSequenceNo(TreeMap chargemap, int seqno) {
        
        while (chargemap.containsKey(new Integer(seqno))){
            seqno++;
        }
        return new Integer(seqno);
    }
}
