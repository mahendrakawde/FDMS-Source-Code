package com.aldorsolutions.webservice;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.util.DAO;

/**
 * @author Manoj Dasyam
 * 
 * To store greetings received from HelloAldor service 
 *
 */
public class GreetinsDAO extends DAO {
	
	public static String INSERT_SQL = "insert into webservicegreeting (DateTime, Greeting) values (?, ?)";
	final private static Logger logger = Logger.getLogger(GreetinsDAO.class.getName());
	
	


	public GreetinsDAO() {
		super();
	}




	public GreetinsDAO(long companyID, long userId) {
		super(companyID, userId);
	}




	public GreetinsDAO(String dbLookup) {
		super(dbLookup);
	}




	public int insertGreetings(String greeting){
		int insertCount = 0;
		try{
			makeConnection(DAO.DB_FDMSCOMMON);
			statement = conn.prepareStatement(INSERT_SQL);
			statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			statement.setString(2, greeting);
			insertCount = statement.executeUpdate();
			
		}catch(SQLException sqlex){
			logger.error("SQLException in insertGreetings() : ", sqlex);
		}catch(Exception ex){
			logger.error("Exception in insertGreetings() : ", ex);
		}finally {
            closeConnection();
        }
		return insertCount;
	}
}
