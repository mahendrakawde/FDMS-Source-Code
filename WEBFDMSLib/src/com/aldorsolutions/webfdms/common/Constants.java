package com.aldorsolutions.webfdms.common;

/**
 * Workfile: Constants.java
 * Date: Nov 3, 2005 6:48:18 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class Constants {
	
	public static final String AUDIT_DAO_CLASS = "com.aldorassist.webfdms.dao.AuditDAOSQL";
	
	// accounting interfaces
    public final static int INTERFACE_PEACHTREE = 1;
    public final static int INTERFACE_BUSINESSWORKS = 2;
    public final static int INTERFACE_QUICKBOOKS = 3;
    public final static int INTERFACE_QUICKBOOKS_NEW = 4;
    public final static int INTERFACE_PEACHTREE_XML = 5;
    public final static int INTERFACE_QUICKBOOKS_XML = 6;
    public final static int INTERFACE_ACCPAC = 7;	
    public final static int INTERFACE_LAWSON = 8;
    public final static int INTERFACE_GREATPLAINS = 9;
    public final static int INTERFACE_PEOPLESOFT_KEYSTONE = 10;
    public final static int INTERFACE_BUSINESSWORKS_NEW = 11;
	
	// values below used to determine locale set for App
    public static String LOCALE_US = "us";
    public static String LOCALE_CANADA = "ca";
    
	// values used for Case List specific configurations
	public final static String SHOW_ACTIVE_CASES = "SHOW_ACTIVE_CASES";
	public final static String SHOW_PRENEED_ONLY = "SHOW_PRENEED_ONLY";
	public final static String USER_START_INDEX = "USER_START_INDEX";
	
	public final static String USER_LOCALE_IDS = "USER_LOCALE_IDS";
	public final static String USER_LOCATION_IDS = "USER_LOCATION_IDS";
	public final static String USER_LOCATIONS = "USER_LOCATIONS";
	public final static String USER_LOCALES = "USER_LOCALES";
	public final static String INTERNATIONAL_LOCALE = "INTERNATIONAL_LOCALE";
	
	public final static String SHOW_CEM_OWN_LIST = "SHOW_CEM_OWN_LIST";
	
	// external application ids
	public static final String ASIMAS_APPLICATION_ID = "1";
	
	// session constants
	public static final String PRENEED_DISBURSEMENT_MAP = "preNeedDisbursementMap";
	
	// Local Types
    public final static int LOCALE_TYPE_FUNERALHOME = 1;
    public final static int LOCALE_TYPE_CEMETERY = 2;
    
    public static final String ASCE_SORT_ORDER = "asce";
	public static final String DESC_SORT_ORDER = "dsce";
	
	public static final String REQUIRED_ERROR_LI = "error.required";
	public static final String REQUIRED_ERROR = "error.isRequired";
	public static final String DATASOURCE_PREFIX = "datasource.prefix";
	public static final String DATAURL_PREFIX = "dataurl.prefix";
	public static final String PREFIX_ERROR = "prefix.error";
	public static final String SUFFIX_ERROR = "suffix.error";
	public static final String EMPTY_ERROR = "error.empty";
	public final static String USERID = "userid";
	public final static String PASSWORD = "dbpassword";
	public final static String JNDINAME = "jndiname";
	public final static String CONNECTIONURL = "connectionurl";
	public final static String DATASOURCETEMPLATE = "newdatasourcesample";
	
	public static int GLOBAL_LOCALE = -1;
	public static int GLOBAL_LOCATION = -1;
	
	public static String GLOBAL_LOCALE_STRING = "All";
	public static String GLOBAL_LOCATION_STRING = "LocaleSpecific";
	public static String LOCATION_SELECTION = "LocationSpecific";
	
	public static String ASIMAS_WEBSERVICES_URL ="asimas.webservices.url"; 
	
//	Error: 1007 SQLSTATE: HY000 (ER_DB_CREATE_EXISTS) 
//	Message: Can't create database '%s'; database exists
	public static final int MYSQL_ERROR_DATABASE_EXISTS = 1007;
	
	public static int TYPE_1099_A    = 1;
	public static int TYPE_1099_B    = 2;
	public static int TYPE_1099_C    = 3;
	public static int TYPE_1099_CAP  = 4;
	public static int TYPE_1099_DIV  = 5;
	public static int TYPE_1099_G    = 6;
	public static int TYPE_1099_H    = 7;
	public static int TYPE_1099_INT  = 8;
	public static int TYPE_1099_LTC  = 9;
	public static int TYPE_1099_MISC = 10;
	public static int TYPE_1099_OID  = 11;
	public static int TYPE_1099_PART = 12;
	public static int TYPE_1099_Q    = 13;
	public static int TYPE_1099_R    = 14;
	public static int TYPE_1099_S    = 15;
	public static int TYPE_1099_SA   = 16;
	
	public static String CONFIG_CODE_APP_DIRECTORY = "ApplicationDirectory";
	public static String CONFIG_CODE_CR_AUDITDB = "CrystalServer.auditSchema";
	public static String CONFIG_CODE_CR_DATASOURCE = "CrystalServer.dsn";
	public static String CONFIG_CODE_CR_ERROR_PAGE = "CrystalServer.errorPg";
	public static String CONFIG_CODE_CR_SERVER = "CrystalServer.IP";
	
	public static String CONFIG_CODE_CR_REPORTGENERATOR = "CrystalServer.ReportGenerator.IP";
	public static String CONFIG_CODE_CR_REPORTSERVER = "CrystalServer.ReportServer.IP";
	public static String CONFIG_CODE_CR_WEBSERVICE_NAMESPACE = "CrystalServer.WebService.NameSpace";
	
	public static String CONFIG_CODE_CR_REPORT_DIR = "CrystalServer.reportsDir";
	public static String CONFIG_CODE_CR_REPORT_LIB = "CrystalServer.reportsLib";
	public static String CONFIG_CODE_CR_USE_RPT_SERVER = "CrystalServer.useReportingService";
	public static String CONFIG_CODE_CR_USERDB = "CrystalServer.userSchema";
	public static String CONFIG_CODE_DB_AUDIT_JNDI = "db.audit.jndi";
	public static String CONFIG_CODE_DB_COMMON_JNDI = "db.common.jndi";
	public static String CONFIG_CODE_DB_FUNERALSYNC_JNDI = "db.funeralsync.jndi";
	public static String CONFIG_CODE_DB_DRIVER = "db.driver";
	public static String CONFIG_CODE_DB_FDMS_JNDI = "db.fdmsdata1.jndi";
	public static String CONFIG_CODE_DB_JNDI_TEMPLATE = "db.jndiTemplate";
	public static String CONFIG_CODE_DB_PASSWORD = "db.password";
	public static String CONFIG_CODE_DB_URL = "db.url";
	public static String CONFIG_CODE_DB_USERNAME = "db.username";
	public static String CONFIG_CODE_DB_USER_JNDI = "db.users.jndi";
	public static String CONFIG_CODE_DB_USERS_URL = "db.users.url";
	public static String CONFIG_CODE_INTERFACE_LOC = "InterfaceLocation";
	public static String CONFIG_CODE_SMTP_FROM = "smtp.mail.from";
	public static String CONFIG_CODE_SMTP_HOST = "smtp.mail.host";
	public static String CONFIG_CODE_APP_LOCALE = "webapp.locale";
	public static String CONFIG_CODE_APP_MULTIPLE_LOGINS = "WebApp.warnMultipleLogins";
	public static String CONFIG_CODE_APP_WEBCONTEXT = "WebAppContext";
	
	// Image types required to store in "imagemapping" table
	public static final String IMAGE_TYPE_LOGO = "logo";
	public static final String IMAGE_TYPE_DECEDENT = "decedent";
	public static final String IMAGE_TYPE_PRESENTATION = "presentation";
	
}