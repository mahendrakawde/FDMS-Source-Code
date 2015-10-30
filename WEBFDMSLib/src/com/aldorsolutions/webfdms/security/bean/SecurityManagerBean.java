package com.aldorsolutions.webfdms.security.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.beans.DbPasswordLog;
import com.aldorsolutions.webfdms.beans.DbSecurityConfig;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.security.dao.SecurityConfigDAOCon;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.security.util.Encryptor;
import com.aldorsolutions.webfdms.security.util.RegExPatternBuilder;
import com.aldorsolutions.webfdms.security.util.RegExValidator;

/**
 * @author drollins
 *
 */
public class SecurityManagerBean {
	
	private Logger logger = Logger.getLogger(SecurityManagerBean.class.getName());

	/**
	 * 
	 */
	public SecurityManagerBean () {
		super();
	}
	
	/**
	 * @param user
	 * @param password
	 */
	public void isUserValid ( UserDTO user, String password ) {
		
	}
	
	/**
	 * 
	 * @param user
	 * @param password
	 * @param security
	 */
	public boolean isPasswordComplexityValid ( String userName, String password, SecurityConfigDTO settings ) {
		
		boolean mixedCase = settings.isMixedCaseRequired();
		boolean numRqd  = settings.isNumberRequired();
		boolean symRqd  = settings.isSymbolRequired();
		int minLength  = settings.getMinPasswordLength();
		int maxLength = settings.getMaxPasswordLength();
		boolean adjNum  = settings.isAdjacentNumberAllowed();
		boolean userNameTest  = settings.isPasswordContainsUserNameAllowed();
		
		boolean isValid = isPasswordComplexityValid ( userName, password, mixedCase, numRqd, symRqd, 
				minLength, maxLength, adjNum, userNameTest );
				
		return ( isValid );
	}

	/**
	 * 
	 * @param user
	 * @param password
	 * @param security
	 */
	public boolean isPasswordComplexityValid ( String userName, String password, DbSecurityConfig settings ) {
		boolean mixedCase = settings.isMixedCaseRequired();
		boolean numRqd  = settings.isNumberRequired();
		boolean symRqd  = settings.isSymbolRequired();
		int minLength  = settings.getMinPasswordLength();
		int maxLength = settings.getMaxPasswordLength();
		boolean adjNum  = settings.isAdjacentNumberAllowed();
		boolean userNameTest  = settings.isPasswordContainsUserNameAllowed();
		
		boolean isValid = isPasswordComplexityValid ( userName, password, mixedCase, numRqd, symRqd, 
				minLength, maxLength, adjNum, userNameTest );
				
		return ( isValid );
	}
	
	/**
	 * 
	 * @param user
	 * @param password
	 * @param security
	 */
	public boolean isPasswordComplexityValid ( String userName, String password,  boolean mixedCase, 
			boolean numRqd, boolean symRqd, int minLength, int maxLength, boolean adjNum, boolean userNameTest ) {
		
		String complexPattern = RegExPatternBuilder.createComplexityPattern ( mixedCase, numRqd, symRqd, minLength, maxLength );
		
		RegExValidator requiredRegEx = new RegExValidator (complexPattern);
		
		if ( requiredRegEx.matchInput ( password ) == false ) {
			return ( false );
		}
		
		if ( adjNum == false ) {
			
			String adjacentPattern = RegExPatternBuilder.createAdjacentNumbersPattern();
			RegExValidator adjacentRegEx = new RegExValidator (adjacentPattern);
			
			if ( adjacentRegEx.matchInput ( password ) ) {
				return ( false );
			}
			
		}
		
		if ( userNameTest ) {
			
			if ( password.indexOf( userName ) != -1 ) {
				return ( false );
			}
			
		}
				
		return ( true );
	}
	
	
	public SecurityConfigDTO getSecurityConfig ( CompanyDTO company ) {
		return ( new SecurityConfigDAOCon (company).getSecurityConfig( company.getCompanyID() ) );
	}

	public SecurityConfigDTO getSecurityConfig ( int companyID ) {
		return ( getSecurityConfig( getCompany( companyID ) ) );
	}
	
	public static ArrayList getPasswordPatternDescription ( SecurityConfigDTO security) {
		return ( RegExPatternBuilder.createComplexityPatternDescription(security) );
	}
	
	public boolean addSecurityConfig(SecurityConfigDTO security, long userID)throws Exception {
		return (new SecurityConfigDAOCon(security.getCompanyID(),userID).addSecurityConfig(security));
	}
	
	public boolean addSecurityConfig(SecurityConfigDTO security, long userID, boolean isGlobal)throws Exception {
		return (new SecurityConfigDAOCon(security.getCompanyID(),userID).addSecurityConfig(security, isGlobal));
	}
	
	/*
	public boolean addSecurityConfig(SecurityConfigDTO security, String dbLookup, String dbName) {
		return (new SecurityConfigDAOCon(security.getCompanyID(),0).addSecurityConfig(security, dbLookup, dbName));
	}
	*/	
	
	public boolean updateSecurityConfig(SecurityConfigDTO security, long userID) {
		return (new SecurityConfigDAOCon(security.getCompanyID(),userID).updateSecurityConfig(security));
	}
	
	public boolean updateCompanyIdLocales(SecurityConfigDTO security, long userID) {
		return (new SecurityConfigDAOCon(security.getCompanyID(),userID).updateCompanyIdLocales(security.getCompanyID()));
	}
	
	public boolean updateCompanyNumberLocations(SecurityConfigDTO security, long userID) {
		return (new SecurityConfigDAOCon(security.getCompanyID(),userID).updateCompanyNumberLocations(security.getCompanyID()));
	}
	
	private CompanyDTO getCompany ( int companyID ) {
		return ( new CompanyManagerBean().getCompany(companyID) );
	}
	
	public Timestamp insertPasswordLog (DbUserSession sessionUser, String password) throws Exception {
		DatabaseTransaction t = null;
		
		try {
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
            t.setReadOnly(false);
            
            DbPasswordLog dbPasswordLog = new DbPasswordLog ();
    		t.addPersistent(dbPasswordLog);
    		dbPasswordLog.setNew();
    		dbPasswordLog.setPassword(password);
    		dbPasswordLog.setUserID(sessionUser.getId());
    		dbPasswordLog.setTmstmp(new Timestamp(System.currentTimeMillis()));
    		
    		t.save();
    		
    		return ( dbPasswordLog.getTmstmp() );
		}
		finally {
			if ( t != null ) {
        		t.closeConnection();
				t = null;
        	}
		}
	}

	public static long calculatePasswordExpirationInDays ( int dayCount, Timestamp tmstmp  ) {

		GregorianCalendar gc = new GregorianCalendar();

		Date currentDate = gc.getTime();
		
		gc.setTime(tmstmp);
		gc.add(GregorianCalendar.DATE, dayCount);
		Date expireDate = gc.getTime();

		long divider = 3600 * 24 * 1000;

		long nowInDays = currentDate.getTime() / divider;
		long expireInDays = expireDate.getTime() / divider;

		long daysToExpire = (expireInDays - nowInDays);
		
		return ( daysToExpire );

	}

	/**
	 * Check if parameter matches this user's password
	 * Creation date: (7/20/00 2:02:46 PM)
	 * @return boolean
	 * @param inPassword java.lang.String
	 */
	public static boolean isValidPassword(String inPassword, String userPassword) {
		if (userPassword == null) {
			return true;
		}

		if (inPassword == null || inPassword.length() == 0) {
			return false;
		}
		
		String pass = Encryptor.encrypt(inPassword);
		
		if (pass.equals(userPassword)) {
			return true;
		}
		
		return false;
	}

	
}