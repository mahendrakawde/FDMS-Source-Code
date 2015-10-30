package fdms.ui.struts.action;

import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbDefaultFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbDefaultPriceList;
import com.aldorsolutions.webfdms.beans.DbDefaultSpeedData;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.JulianDay;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.UserRegistration;

public class ProcessUserAcceptance extends Action {

    private Logger logger = Logger.getLogger(ProcessUserAcceptance.class.getName());

 /**
 * Called from the UserAcceptance.jsp, this action handles the
 * add submit button.  
 * @return ActionForward
 * Created 08/28/02 5:38PM
  */
 public ActionForward execute(ActionMapping mapping,
	ActionForm actionForm,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {

     UserRegistration form = (UserRegistration) actionForm;
	ActionErrors errors = new ActionErrors();
	ActionForward actionForward = new ActionForward(mapping.getInput());
//	Locale locale = getLocale(request);
//	MessageResources messages = getResources(request);
	String submitType = form.getSubmitButton();
	
	// First, if the user didn't chose to 'save', then exit back to login.jsp
	if (submitType == null || (!submitType.equals("save"))) {
	   return mapping.findForward("logon");
	}

	// Make sure the Acceptance User Initials are valid before proceeding.
	if (form.getInitials() == null || form.getInitials().trim().equals("")) {
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.acceptance.nullInitials"));
	   saveErrors(request, errors);
	   return actionForward;  
	} else {
	   form.setInitials(form.getInitials().toUpperCase());
	}
	  
	HttpSession session = request.getSession();  
	DbUserSession sessionUser = null;    
	DbUser dbUser = new DbUser();
	String userLookup = UtilSingleton.getInstance().getUserDBLookup();
	
	DbLocale dbLocale = null;
	DbLocation dbLocation = null;
	
	DatabaseTransaction t = null;
        
	try {
	    // Create a new usersecurity record for the User.
	    t = new DatabaseTransaction(userLookup, 0, 0, 0 );
	    
	    dbUser = new DbUser();
	    dbUser.setNew();
	    dbUser.setUserName(form.getUserLogin());
	    dbUser.setPassword(form.getUserPassword1());
	    dbUser.setDataUrl("jdbc:mysql:///WebFdmsData1");
	    dbUser.setSecurityAcctsRec(Short.parseShort("1"));
	    dbUser.setSecurityAdmin(Short.parseShort("1"));
	    dbUser.setSecurityAtneed(Short.parseShort("1"));
	    dbUser.setSecurityDelete(Short.parseShort("1"));
	    dbUser.setSecurityFinancial(Short.parseShort("1"));
	    dbUser.setSecurityForms(Short.parseShort("1"));
	    dbUser.setSecurityInventory(Short.parseShort("1"));
	    dbUser.setSecurityPayments(Short.parseShort("1"));
	    dbUser.setSecurityPreneed(Short.parseShort("1"));
	    dbUser.setSecurityReports(Short.parseShort("1"));
	    dbUser.setSecurityViewOnly(Short.parseShort("0"));
	    dbUser.setSecurityAccountingInterface(Short.parseShort("0"));
	    dbUser.setSecuritySpeedData(Short.parseShort("0"));
	    dbUser.setSecurityArrangerManagement(Short.parseShort("0"));
	    dbUser.setSecurityFormsAvaialble(Short.parseShort("0"));
	    dbUser.setSecurityGLSalesAccount(Short.parseShort("0"));
	    // Funeral Management for new users.
	    // These are the differences for an Abbit new user
	    if (form.getActionType().equals("abbit")){
			dbUser.setDataUrl("jdbc:mysql:///abbit");
		    dbUser.setSecurityAcctsRec(Short.parseShort("0"));
		    dbUser.setSecurityAdmin(Short.parseShort("1"));
	 	   	dbUser.setSecurityAtneed(Short.parseShort("0"));
	  	  	dbUser.setSecurityDelete(Short.parseShort("1"));
	    	dbUser.setSecurityFinancial(Short.parseShort("0"));
	    	dbUser.setSecurityForms(Short.parseShort("0"));
	    	dbUser.setSecurityInventory(Short.parseShort("0"));
	    	dbUser.setSecurityPayments(Short.parseShort("0"));
	    	dbUser.setSecurityPreneed(Short.parseShort("1"));
	    	dbUser.setSecurityReports(Short.parseShort("0"));
	    }
	    dbUser.setEmailAddr(form.getUserEmail());
	    dbUser.setFirstName(form.getFirstName());
	    dbUser.setLastName(form.getLastName());
	    dbUser.setInitials(form.getInitials());
	    dbUser.setGreeting(form.getFirstName());
	    dbUser.setSqlUser(setRandomUser(dbUser));
	    dbUser.setSqlPassword(setRandomPassword());
	    dbUser.setCaseListSortOrder("DeathDateKey");
	    dbUser.setCaseListPerScreen(10);
	    t.addPersistent(dbUser);
	    t.save();
	    //AppLog.info("New User Created, login successful: " +form.getUserName());
	    t.closeConnection();

	    // Grant database privleges for the new user
	    String databaseName = dbUser.getDataUrl().substring((dbUser.getDataUrl().lastIndexOf("/")+1), dbUser.getDataUrl().trim().length());
	    String grantStmt1 = "GRANT All PRIVILEGES ON " +databaseName +".* to " +dbUser.getSqlUser() +"@\"%\" identified by '" +dbUser.getSqlPassword() +"'";
	    String grantStmt2 = "GRANT All PRIVILEGES ON " +databaseName +".* to " +dbUser.getSqlUser() +"@localhost identified by '" +dbUser.getSqlPassword() +"'";
	    t = new DatabaseTransaction(userLookup, 0, 0, 0 );
		Connection connection = t.getConnection();
	    Statement sqlStmt = connection.createStatement();
		sqlStmt.executeUpdate(grantStmt1);
		sqlStmt.executeUpdate(grantStmt2);
		t.closeConnection();

	    // At this point, we have a new user logged in.
	    String username = form.getUserLogin();
	    String password = form.getUserPassword1();
	    dbUser = DbUser.login(username,password);
	    if (dbUser == null) {
		   logger.error("Something went foobar in login.");
		   return actionForward;
	    }
	    session = request.getSession();
		session.setAttribute(SessionValueKeys.DB_USER, dbUser);
		sessionUser = SessionHelpers.getUserSession(request);
	    
		// Create a new Locale record for Funeral Home.
		//AppLog.info("Creating locale record for " +form.getFuneralHomeName());
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		dbLocale = new DbLocale();
		dbLocale.setNew();
		dbLocale.setName(form.getFuneralHomeName());
		dbLocale.setNextContractNo(1000);
		dbLocale.setNextNonCashNo(1000);
		dbLocale.setNextReceiptNo(1000);
		dbLocale.setLastFinanceChargeDate(FormatDate.convertToDate(FormatDate.getCurrentDateFormatedMMDDYYYY()));
		dbLocale.setNumberOfUsers(1);
		dbLocale.setPreneedLicense("A");
		dbLocale.setInflationRate(0.032);
		// Calculate the ExpirationDate
		JulianDay julianDay = new JulianDay();
	    if (form.getActionType().equals("abbit")){
			julianDay.add(JulianDay.DATE,+365);
			// also for Abbit...
			dbLocale.setAtneedLicense("N");	// disable access to at-need stuff
	    }
	    else {
			julianDay.add(JulianDay.DATE,+31);
	    }
		julianDay.setDateFormat("MM/dd/yyyy");
		dbLocale.setExpirationDate(FormatDate.convertToDate(julianDay.getDateTimeStr()));
		t.addPersistent(dbLocale);
	    t.save();
		//AppLog.info("Created locale record for " +dbLocale.getId());
	    t.closeConnection();
	    
		// Update the usersecurity record with the new locale id.
	    t = new DatabaseTransaction ( userLookup, dbUser.getId(), dbUser.getCompanyID(), dbUser.getRegion() );
	    dbUser.setRegion(dbLocale.getId());
	    t.addPersistent(dbUser);
	    t.save();
	    t.closeConnection();
	    //AppLog.info("Updated userSecurity " +form.getUserName() +" record with locale id" +dbLocale.getId());

	    // Create a new location record for the new Home.
		//AppLog.info("Creating location record for new locale " +dbLocale.getId());
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		dbLocation = new DbLocation();
		dbLocation.setNew();
		dbLocation.setName(form.getFuneralHomeName());
		dbLocation.setLocaleNumber(dbLocale.getId());
		dbLocation.setCompanyNumber(String.valueOf(dbLocale.getId()));
		dbLocation.setAddr1(form.getAddr1());
		dbLocation.setAddr2(form.getAddr2());
		dbLocation.setAddr3(form.getAddr3());
		dbLocation.setCity(form.getCity());
		dbLocation.setState(form.getState());
		dbLocation.setZip(form.getZipCode());
		dbLocation.setLicenseNumber(form.getLicenseNumber());
		dbLocation.setPhone(FormatString.formatPhone(form.getPhoneNumber()));
		dbLocation.setPhoneAlternate(FormatString.formatPhone(form.getOtherPhone()));
		dbLocation.setApAcct("301");
		dbLocation.setArAcct("201");
		dbLocation.setCashAcct("101");
		dbLocation.setCashBalance(0);
		dbLocation.setDiscountAcct("501");
		dbLocation.setFinanceChargeAcct("502");   
		dbLocation.setStdServiceCharge(250000);          
		dbLocation.setNextCheckNumber(1000);
		dbLocation.setPriceListVersion(FdmsDb.getInstance().getDefaultPriceListVersion(t));
		dbLocation.setPackageVersion(" ");
		dbLocation.setInactiveCode(" ");
		dbLocation.setDiscountHandlingCode(" ");
		dbLocation.setPreferenceGenericVitals("Y");
		t.addPersistent(dbLocation);
		//AppLog.info("Added new dbLocation to persistent");
	    t.save();
	    t.closeConnection();
	    //AppLog.info("Created location record for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());

	    // Create the pricelist records from the defaultpricelist
	    //AppLog.info("Creating priceliist records for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		DbDefaultPriceList[] dbDefaultPriceList = FdmsDb.getInstance().getDefaultPriceListRecords(t);
		DbPriceList priceList = null;
	    for (int i= 0; i < dbDefaultPriceList.length; i++) {
		   priceList = new DbPriceList();
		   priceList.setNew();
		   priceList.setActive(dbDefaultPriceList[i].getActive());
		   priceList.setCategory(dbDefaultPriceList[i].getCategory());
		   priceList.setContrDescr(dbDefaultPriceList[i].getContrDescr());
		   priceList.setContrLine(dbDefaultPriceList[i].getContrLine());
		   priceList.setGlAcctNo(dbDefaultPriceList[i].getGlAcctNo());
		   priceList.setGPLdescr(dbDefaultPriceList[i].getGPLdescr());
		   priceList.setGPLkey(dbDefaultPriceList[i].getGPLkey());
		   priceList.setGPLprint(dbDefaultPriceList[i].getGPLprint());
		   priceList.setGPLregulated(dbDefaultPriceList[i].getGPLregulated());
		   priceList.setGridRow(dbDefaultPriceList[i].getGridRow());
		   priceList.setIncludedDescr(dbDefaultPriceList[i].getIncludedDescr());
		   priceList.setInvoiceSeqNo(dbDefaultPriceList[i].getInvoiceSeqNo());
		   priceList.setMasterDescr(dbDefaultPriceList[i].getMasterDescr());
		   priceList.setNotInclDescr(dbDefaultPriceList[i].getNotInclDescr());
		   priceList.setPackage(dbDefaultPriceList[i].getPackage());
		   priceList.setPrice(dbDefaultPriceList[i].getPrice());
		   priceList.setPriceUnit(dbDefaultPriceList[i].getPriceUnit());
		   priceList.setTaxCode(dbDefaultPriceList[i].getTaxCode());
		   priceList.setTaxExempt(dbDefaultPriceList[i].getTaxExempt());
		   priceList.setToPrice(dbDefaultPriceList[i].getToPrice());
		   priceList.setVersion(dbDefaultPriceList[i].getVersion());
		   priceList.setLocaleID(sessionUser.getRegion());
		   t.addPersistent(priceList);
	    }
		t.save();
	    t.closeConnection();
	    //AppLog.info("Created pricelist records for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());

	    // Create the speeddata records from the defaultspeedata table.
	    //AppLog.info("Creating speeddata records for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		DbDefaultSpeedData[] dbDefaultSpeedData = FdmsDb.getInstance().getDefaultSpeedData(t);
		DbSpeedData speedData = null;
		boolean foundFlag = false;
		for (int i= 0; i < dbDefaultSpeedData.length; i++) {
		   speedData = new DbSpeedData();
		   speedData.setNew();
		   speedData.setCategory(dbDefaultSpeedData[i].getCategory());
		   speedData.setData(dbDefaultSpeedData[i].getData());
		   if (CsvTable.getField(speedData.getData(), 1).toUpperCase().equals(form.getCity().toUpperCase()) &&
		   		CsvTable.getField(speedData.getData(), 5).toUpperCase().equals(form.getState().toUpperCase()) &&
		   		CsvTable.getField(speedData.getData(), 3).equals(form.getZipCode())) {
	              foundFlag = true;
		   }
		   speedData.setLocale(dbLocale.getId());
		   t.addPersistent(speedData);
	    }
	    // Add User's City, State, and Zip to speeddata
	    if (!foundFlag) {
		   speedData = new DbSpeedData();
		   speedData.setNew();
		   speedData.setCategory("CITY_STATE");
		   speedData.setData(form.getCity().toUpperCase() +", " +form.getState().toUpperCase() +", " +form.getZipCode().toUpperCase());
		   speedData.setLocale(dbLocale.getId());
		   t.addPersistent(speedData);
	    }
	    // Add Location to speeddata category "Srvplace"
	    String allAddress = new String();
	    allAddress = form.getAddr1();
	    if (form.getAddr2() != null && form.getAddr2().trim().length() > 0) {
		   allAddress = allAddress +" " +form.getAddr2();
	    }
	    if (form.getAddr3() != null && form.getAddr3().trim().length() > 0) {
		   allAddress = allAddress +" " +form.getAddr3();
	    }
	    speedData = new DbSpeedData();
	    speedData.setNew();
	    speedData.setCategory("Srvplace");
	    speedData.setData(form.getFuneralHomeName() +", " +allAddress +", " +form.getCity() +", " +form.getState() +", " +form.getZipCode());
		speedData.setLocale(dbLocale.getId());
		t.addPersistent(speedData);
		
		// Commit all speedata inserts
	    t.save();
	    t.closeConnection();
	    //AppLog.info("Created speedData records for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());

	    // Create the formsavailable records from the defaultformsavailable table.
	    //AppLog.info("Creating formsavailable records for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		DbDefaultFormsAvailable[] dbDefaultFormsAvailable = FdmsDb.getInstance().getDefaultFormsAvailable(t);
		DbFormsAvailable formsAvailable = null;
	    for (int i= 0; i < dbDefaultFormsAvailable.length; i++) {
		   formsAvailable = new DbFormsAvailable();
		   formsAvailable.setNew();
		   formsAvailable.setCategory(dbDefaultFormsAvailable[i].getCategory());
		   formsAvailable.setDescription(dbDefaultFormsAvailable[i].getDescription());
		   formsAvailable.setExportType(dbDefaultFormsAvailable[i].getExportType());
		   formsAvailable.setLocaleNumber(dbLocale.getId());
		   formsAvailable.setMarginBottom(dbDefaultFormsAvailable[i].getMarginBottom());
		   formsAvailable.setMarginTop(dbDefaultFormsAvailable[i].getMarginTop());
		   formsAvailable.setMarginRight(dbDefaultFormsAvailable[i].getMarginRight());
		   formsAvailable.setMarginLeft(dbDefaultFormsAvailable[i].getMarginLeft());
		   formsAvailable.setReportName(dbDefaultFormsAvailable[i].getReportName());
		   formsAvailable.setSelectionFormula(dbDefaultFormsAvailable[i].getSelectionFormula());
		   t.addPersistent(formsAvailable);
	    }
	    t.save();
	    t.closeConnection();
	    //AppLog.info("Created formsavailable records for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());

	    // Add new User as an arranger
	    //AppLog.info("Creating arranger record for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		DbArrangers dbArranger = new DbArrangers();
		dbArranger.setNew();
		dbArranger.setLocale(dbLocale.getId());
		dbArranger.setName(form.getFirstName() +" " +form.getLastName());
		t.addPersistent(dbArranger);
		t.save();
	    //AppLog.info("Created arranger record for new locale " +dbLocale.getId() +" new location id " +dbLocation.getId());
			
	    // Remove the obsolete form from scope
	    if (mapping.getAttribute() != null) {
	       if ("request".equals(mapping.getScope())) {
	          request.removeAttribute(mapping.getAttribute());
	       } else {
  	          session.removeAttribute(mapping.getAttribute());
	       }
	    }
	    form.setSubmitButton("");

	}
	catch (PersistenceException pe) {
		logger.error("Persistence Exception in ProcessUserAcceptance.doPerform. " + pe);
		return actionForward;
	} catch(Exception e) { 
		logger.error("Exception in ProcessUserAcceptance.doPerform. ", e);
		return actionForward;
    } finally {
        if (t != null) t.closeConnection();
    }
        
	if (form.getActionType().equals("abbit")){
		return mapping.findForward("showLocationAdminGlobal");
	}
	//Action Forward Logic
	return mapping.findForward("introduction");
  }                    
/**
 * This function creates a Random UserPassword
 * Creation date: (9/26/2002 12:37:29 PM)
 * @return java.lang.String
 */
public String setRandomPassword() {

   java.util.Random myRandom = new java.util.Random();
   int myInt = myRandom.nextInt()+myRandom.nextInt();
 
   while (myInt < 0) {
	  myInt = myRandom.nextInt();
   }

   return "p" +String.valueOf(myInt);

}
/**
 * This function creates a Random UserName
 * Creation date: (9/26/2002 12:37:29 PM)
 * @return java.lang.String
 */
public String setRandomUser(DbUser dbUser) {

   java.util.Random myRandom = new java.util.Random();
   int myInt = myRandom.nextInt();
 
   while (myInt < 0) {
	  myInt = myRandom.nextInt();
   }

   return "u" +myInt;

}
}
