package com.aldorsolutions.webfdms.locale.bean;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.PersistenceException;

/**
 * @author drollins
 * 
 */
public class LocaleManagerBean {

	int companyID = 0;
	int userID = 0;
	String dbLookup = null;
	private Logger logger = Logger.getLogger(LocaleManagerBean.class.getName());

	/**
	 * 
	 */
	public LocaleManagerBean(long companyID) {
		super();
		this.companyID = (int) companyID;
	}
	
	public LocaleManagerBean(DbUserSession user) {
		super();
		this.companyID = (int) user.getCompanyID();
		this.userID = (int) user.getId();
		this.dbLookup = user.getDbLookup();
	}

	public ArrayList getLocales() {
		return (getLocales(true));
	}
	
	private String getDbLookup () {
		if ( dbLookup == null ) {
			CompanyDTO company = (new CompanyManagerBean()).getCompany(companyID);
			dbLookup = company.getDbLookup();
		}
		
		return ( dbLookup );
	}

	public ArrayList getLocales(boolean activeOnly) {
		return (new LocaleDAO(companyID, userID).getLocales(activeOnly, companyID, getDbLookup()));
	}
	
	public LocaleDTO getLocale(long localeID) throws Exception {
		return (new LocaleDAO(companyID, userID).getLocale(localeID, getDbLookup()));
	}
	
	public boolean addLocale(LocaleDTO locale)throws Exception {
		return (new LocaleDAO(companyID, userID).addLocale(locale, getDbLookup()));
	}
	
	public boolean updateLocale(LocaleDTO locale) throws PersistenceException {
		return (new LocaleDAO(companyID, userID).updateLocale(locale, getDbLookup()));
	}
	
	public boolean deleteLocale(long localeID) {
		return (new LocaleDAO(companyID, userID).deleteLocale(localeID, getDbLookup()));
	}
	
}
