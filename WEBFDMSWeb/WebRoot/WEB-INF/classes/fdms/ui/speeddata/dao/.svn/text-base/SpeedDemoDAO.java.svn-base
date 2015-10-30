package fdms.ui.speeddata.dao;


/**
 * DbDemoData
 * Demographic Data - Get data from DB.
 * Creation date: (9/14/2005 6:27 PM)
 * @author: Kier Heyl
 * Adapted from CaseSetDAO.java
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;


public class SpeedDemoDAO {

    private static Logger logger = Logger.getLogger(SpeedDemoDAO.class.getName());
    private Connection conn = null;
    private PreparedStatement statement = null;
    private ResultSet rs = null;
    
    /** Creates a new instance of DbDemoData 
     * @param dbUrl
     * @param dbUsername
     * @param dbPassword
     * @throws Exception     
     */
    public SpeedDemoDAO( String dbLookup, int userID, int companyID, 
    		int localeID) throws Exception {
                
        DatabaseTransaction t = new DatabaseTransaction(dbLookup, userID, 
        		companyID, localeID);
        conn = t.getConnection();
        
        if (conn == null) throw new Exception("Unable to create db connection");
    }
    
    public ArrayList getDemoData(
            int locationId, 
			int localeId,
            String category) {
        
        logger.debug("** Entering getCaseList() **");
        
        ArrayList DemoData = new ArrayList();
        
        try {            
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT id,");
            sql.append("    last_name,");
            sql.append("    first_name,");
            //sql.append("    middle_name,");
            //sql.append("    salutation,");
            //sql.append("    suffix,");
            //sql.append("    alias,");
            //sql.append("    address,");
            //sql.append("    city,");
            //sql.append("    county,");
            //sql.append("    state,");
            //sql.append("    zip,");
            //sql.append("    country,");
            //sql.append("    license_number,");
            //sql.append("    phone1_desc,");
            //sql.append("    phone1,");
            //sql.append("    phone2_desc,");
            //sql.append("    phone2,");
            //sql.append("    phone3_desc,");
            //sql.append("    phone3,");
            //sql.append("    email ");
            sql.append("FROM tbl_speed_demo ");
            sql.append("WHERE Category=\"");
            sql.append(category);
            //sql.append("\" and last_name like \"");
            //sql.append(nameStart);
            sql.append("\" ORDER BY last_name");
            
            logger.debug("SQL : " + sql);
            
            statement = conn.prepareStatement(sql.toString());
            int col = 0;
            
            if (locationId > 0) statement.setInt(++col, locationId);
            else statement.setInt(++col, localeId);
            
            rs = statement.executeQuery();
            while (rs.next()) {
                col = 0;
//                DbSpeedDataDemo dbDemoData = new DbSpeedDataDemo();
                //dbDemoData.setLocationId(locationId);
//                dbDemoData.setId(rs.getInt(++col));
//                dbDemoData.setLastName(rs.getString(++col));
//                dbDemoData.setFirstName(rs.getString(++col));
                //dbDemoData.setMiddleName(rs.getString(++col));
                //dbDemoData.setSalutation(rs.getString(++col));
                //dbDemoData.setSuffix(rs.getString(++col));
                //dbDemoData.setAlias(rs.getString(++col));
                //dbDemoData.setAddress(rs.getString(++col));
                //dbDemoData.setCity(rs.getString(++col));
                //dbDemoData.setCounty(rs.getString(++col));
                //dbDemoData.setState(rs.getString(++col));
                //dbDemoData.setZipCode(rs.getString(++col));
                //dbDemoData.setCountry(rs.getString(++col));
                //dbDemoData.setLicenseNumber(rs.getString(++col));
                //dbDemoData.setPhone1Desc(rs.getString(++col));
                //dbDemoData.setPhone1(rs.getInt(++col));
                //dbDemoData.setPhone2Desc(rs.getString(++col));
                //dbDemoData.setPhone2(rs.getInt(++col));
                //dbDemoData.setPhone3Desc(rs.getString(++col));
                //dbDemoData.setPhone3(rs.getInt(++col));
                //dbDemoData.setEMail(rs.getString(++col));
                
//                DemoData.add(dbDemoData);
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in getCaseList() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCaseList() : ", e);
        } finally {
            closeConnection();
        }
        
        return DemoData;
    }
    
    /**
     * Closes all db resources
     */
    private void closeConnection() {
        
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            logger.error("SQL Exception in closing result set : ", e);
        }
        
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            logger.error("SQL Exception in closing prepared statement : ", e);
        }
        
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("SQL Exception in closing connection : ", e);
        }
    }
}