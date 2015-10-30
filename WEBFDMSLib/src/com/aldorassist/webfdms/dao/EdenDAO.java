package com.aldorassist.webfdms.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.EdenDTO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.InvoiceTransactionHistoryDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Sep 6, 2007
 * InvoiceDAO.java
 *
 */
public class EdenDAO extends DAO {
    
    private Logger logger = Logger.getLogger(InvoiceDAO.class.getName());
    
    public EdenDAO(DbUserSession user) {
    	super (user);
    }
    
    public EdenDAO(long companyID, long userID) {
    	super (companyID, userID);
    }
    
    private static String selectFields () {
    	return ("EdenId, State,	SequenceNumber,	LENGTH,	Description, ColumnDesc, UnknowDesc, "
    			+ "CommentDesc,	TableName,	FieldName,	FieldFormat");
    }
    
	public ArrayList <EdenDTO> getEdens ( String state ) {
		ArrayList <EdenDTO> list = new ArrayList <EdenDTO> ();
    	EdenDTO eden = null;
    	
        try {
            String sql = "select " + selectFields() + " FROM eden WHERE state = '"+state+"' ORDER BY SequenceNumber";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	eden = new EdenDTO();
            	eden.setEdenId(rs.getInt(++col));
            	eden.setState(rs.getString(++col));
            	eden.setSequenceNumber(rs.getInt(++col));
            	eden.setLength(rs.getInt(++col));
            	eden.setDescription(rs.getString(++col));
            	eden.setColumnDesc(rs.getString(++col));
            	eden.setUnknowDesc(rs.getString(++col));
            	eden.setCommentDesc(rs.getString(++col));
            	eden.setTableName(rs.getString(++col));
            	eden.setFieldName(rs.getString(++col));
            	eden.setFieldFormat(rs.getString(++col));
            	list.add(eden);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }

	public Object getData(EdenDTO eden, DbUserSession sessionUser, int vitalsId) {
		Object data = "";
		
		try {
            String sql = "select " + eden.getFieldName() + " from "+eden.getTableName()+ " where vitalsmasterkey = "+vitalsId ;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
           
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	data = rs.getObject(1);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }
		
		return data;
	}

    
}
