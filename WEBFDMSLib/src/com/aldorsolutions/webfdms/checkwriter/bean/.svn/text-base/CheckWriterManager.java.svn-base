/**
 * 030309
 */
package com.aldorsolutions.webfdms.checkwriter.bean;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApMasterDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.CheckListDisplayDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;

/**
 * @author David Rollins
 * Date: May 3, 2007 
 * File: CheckWriterManager.java	
 * 
 */
public class CheckWriterManager {
	
	Logger logger = Logger.getLogger(CheckWriterManager.class);

	public ArrayList <ApMasterDTO> getAllChecks (DbUserSession user) {
		ApMasterDAO apMasterDAO = new ApMasterDAO (user);
		return ( apMasterDAO.getApMaster () );
	}

	public ApMasterDTO getCheck (DbUserSession user, long checkID) {
		ApMasterDAO apMasterDAO = new ApMasterDAO (user);
		return ( apMasterDAO.getApMaster (checkID) );
	}
	
	public ArrayList <ApMasterDTO> getChecksForLocation (DbUserSession user) {
		ApMasterDAO apMasterDAO = new ApMasterDAO (user);
		return ( apMasterDAO.getApMaster (user.getLocationId(), user.getRegion()) );
	}

	public ArrayList <ApMasterDTO> getChecksForLocale (DbUserSession user) {
		ApMasterDAO apMasterDAO = new ApMasterDAO (user);
		return ( apMasterDAO.getApMasterByLocale(user.getRegion()) );
	}

	public ArrayList <ApMasterDTO> getChecksForLocaleLocation (DbUserSession user) {
		ApMasterDAO apMasterDAO = new ApMasterDAO (user);
		
			// If the locationID == 0 then that means they selected all locations so
			// get the checks for local.
		if (user.getLocationId() == -1) {
			return ( apMasterDAO.getApMasterByLocale(user.getRegion()) );
		} else {	
			return ( apMasterDAO.getApMaster(user.getLocationId(), user.getRegion()) );
		}
	}

    public ArrayList <CheckListDisplayDTO> getCheckDisplayForUser (DbUserSession user, 
    		ArrayList <ApMasterDTO> checks, long locationID, boolean showVoided) {

    	DatabaseTransaction t = null;
    	ArrayList <CheckListDisplayDTO> checkList = new ArrayList <CheckListDisplayDTO> ();
        
        try {
    		t = new DatabaseTransaction (user);
    		
    		UserDAO userDao = new UserDAO();
    		//ArrayList <DbApVendor> vendorList = FdmsDb.getInstance().getVendors (t);
//    		ArrayList <ApVendorDTO> vendorList = FdmsDb.getInstance().getVendorsByRegion (user,t,user.getRegion(),false);
    		ArrayList <UserDTO> userList = userDao.getUsers (UserDAO.ACTIVE, user.getCompanyID());
    		DbLocation[] locList = FdmsDb.getInstance().getLocationsForCompany(t, user.getCompanyID());
    		
    		for ( ApMasterDTO check : checks ) {
    			
//    			if ((showVoided == false && "V".equalsIgnoreCase(check.getVoidedCode())) ||
//    					check.getApprovalStatus() != ApMasterDTO.APPROVAL_STATUS_PENDING) {
//    				continue;   				
//    			}
    			
    			CheckListDisplayDTO checkDisp = new CheckListDisplayDTO();
    			checkDisp.setMasterID(check.getMasterID());
    			checkDisp.setCheckNumber(check.getCheckNumber());
    			
    			checkDisp.setApprovalStatus(check.getApprovalStatus());
    			checkDisp.setApprovedBy(check.getApprovedBy());
    			checkDisp.setApprovedTmstmp(check.getApprovedTmstmp());
    			checkDisp.setBalance(check.getBalance());
    			checkDisp.setCheckDate(check.getCheckDate());
    			checkDisp.setCheckNumber(check.getCheckNumber());
    			checkDisp.setCheckToBePrinted(check.isCheckToBePrinted());
//    			checkDisp.setContractName(check.getCheckNumber());
//    			checkDisp.setContractNum(check.getCheckNumber());
    			checkDisp.setDiscountAmount(check.getDiscountAmount());
    			checkDisp.setDueDate(check.getDueDate());
    			checkDisp.setHandwritten(check.isHandwritten());
//    			checkDisp.setInvoiceDate(check.getCheckNumber());
//    			checkDisp.setInvoiceNumber(check.getCheckNumber());
    			
    			checkDisp.setInvoiceTotal(check.getInvoiceTotal());
    			checkDisp.setLocaleID(check.getLocaleID());
    			checkDisp.setLocationID(check.getLocationID());
    			checkDisp.setMasterID(check.getMasterID());
    			checkDisp.setMemo(check.getMemo());
//    			checkDisp.setStatusDisplay(check.getCheckNumber());
    			checkDisp.setUserID(check.getUserID());
    			checkDisp.setVendorID(check.getVendorID());
    			checkDisp.setVoidedCode( String.valueOf("V".equalsIgnoreCase(check.getVoidedCode())) );
    			
    			
    			checkDisp.setPayee( "VendorID: " + check.getVendorID() );
    			
 			
//    			for ( ApVendorDTO vendor : vendorList ) {
//    				if ( vendor.getVendorID() == check.getVendorID() ) {
//    					checkDisp.setPayee( vendor.getName() );
//    					break;
//    				}
//    			}
    			checkDisp.setPayee( check.getVendorName() );
    			
    			
    			checkDisp.setUserName( "UserID: " + check.getUserID() );

    			for ( UserDTO aUser : userList ) {
    				if ( aUser.getUserId() == check.getUserID() ) {
    					checkDisp.setUserName( aUser.getLastName() + ", " + aUser.getFirstName() );
    					break;
    				}
    			}

    			checkDisp.setLocation( "LocationID: " + check.getLocationID() );

    			for ( DbLocation loc : locList ) {
    				long loctnID = loc.getId();
    				long localeID = loc.getLocaleNumber();
    				if ( (loctnID == check.getLocationID()) && 
    						(localeID == check.getLocaleID()) ) {
    					checkDisp.setLocation( loc.getName() );
    					break;
    				}
    			}
    			
    			checkDisp.setStatusDisplay( CheckWriterManager.getCheckStatusDescription(check.getApprovalStatus()) );
        		checkList.add(checkDisp);
    		}

		} catch (Exception e) {
			logger.error("Error : ", e);
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}
		
		return ( checkList );
			
    }
    
    public static String getCheckStatusDescription ( long status ) {
    	
    	switch ( (int) status ) {
    		case ApMasterDTO.APPROVAL_STATUS_APPROVED : 
    			return ( "Approved" );
    		case ApMasterDTO.APPROVAL_STATUS_REJECTED :
    			return ( "Voided" ); //Change from Rejected
    		case ApMasterDTO.APPROVAL_STATUS_PENDING :
    			return ( "Pending" );
    		case ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT :
    			return ( "Approval Not Needed" );
    		case ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY :
    			return ( "Not Required" );
    		default : 
    			return ( "Pending" );
    	}
    	
    }
    
	
}
