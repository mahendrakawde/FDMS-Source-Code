package fdms.ui.struts.action;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.bean.CheckWriterManager;
import com.aldorsolutions.webfdms.checkwriter.dao.ApMasterDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CheckEditDisplayForm;
import fdms.ui.struts.form.CheckEditForm;

public class ShowCheckEditDisplay extends Action {
    
    private Logger logger = Logger.getLogger(ShowCheckEditDisplay.class.getName());
    private ArrayList formErrors;
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        CheckEditForm form = (CheckEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        CheckEditDisplayForm checkForm = new CheckEditDisplayForm();
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        
        String masterIDStr = request.getParameter("masterID");
        
        if ( form == null && masterIDStr == null ) {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.number"));
        }
        else if ( masterIDStr != null) {
//        	form = new CheckEditForm();.
        	form.setDirective("");
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            return mapping.findForward("ShowCheckListJsp");
        }
        
        // check for exit request
        if ( form.getDirective() == null ) {
        	return mapping.findForward("ShowCheckListJsp");
        }
        
        if (form.getDirective().compareToIgnoreCase("exit")==0){
            return mapping.findForward("financial");
        }
        
        ApMasterDAO apDAO = new ApMasterDAO(sessionUser);
        long masterID = FormatNumber.parseLong(masterIDStr);
        
        
        
        // Make sure a check number was entered
        if (masterID <=0){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.number"));
//            formErrors.add("checknumber");
        }
        
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            return mapping.findForward("ShowCheckListJsp");
        }
        
        int locationID = 0;
        
        try{
        	ApMasterDTO masterDTO = apDAO.getApMaster(masterID);
        	// form.setChecknumber(masterIDStr);
        	
            if (masterDTO == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.number"));
                saveErrors(request, errors );
                return mapping.findForward("ShowCheckListJsp");
            }
            locationID = (int) masterDTO.getLocationID();
            
            // is this check already voided?
            if (masterDTO.getVoidedCode() != null) {
	            if ("V".compareToIgnoreCase(masterDTO.getVoidedCode()) ==0){
	            	//it should not give any warning any more.
	                // errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.voided"));
	            }
            }
            
            String checkAmountStr = FormatCurrency.toCurrency(masterDTO.getInvoiceTotal() - masterDTO.getDiscountAmount());
			double checkAmount = FormatCurrency.convertToCurrencyDbl(checkAmountStr);
			
			String check1099AmountStr = FormatCurrency.toCurrency( masterDTO.getCheck1099Amount() );
			double check1099Amount = FormatCurrency.convertToCurrencyDbl(check1099AmountStr);
			
			long dollars = (int) ((masterDTO.getInvoiceTotal()- masterDTO.getDiscountAmount())/100);
			double cents = (checkAmount - dollars)*100.0;
			
			DecimalFormat decFormat = new DecimalFormat("$#,##0.00");
			DecimalFormat dollarFormat = new DecimalFormat("#,##0");
			DecimalFormat centsFormat = new DecimalFormat(" and #0");
			
			String longFormat = dollarFormat.format(dollars) + centsFormat.format(cents) + "/100";
			boolean showPrinting = false; 
			
			// put data in check edit form
            checkForm.setLocation( String.valueOf(masterDTO.getLocationID()));
            checkForm.setVendor( String.valueOf(masterDTO.getVendorID()));
            checkForm.setMemo( masterDTO.getMemo());
            checkForm.setEditMemo( masterDTO.getVoidedComment());
            checkForm.setCheckAmount(decFormat.format(checkAmount));
			checkForm.setCheckAmountLong(longFormat);
			checkForm.setCheckDate(FormatDate.convertDateToMM_DD_YYYY(masterDTO.getCheckDate()));
			checkForm.setCheckNumber(String.valueOf(masterDTO.getCheckNumber()));
			checkForm.setApmasterID( Long.toString(masterDTO.getMasterID()) );
			checkForm.setApprovalStatus(masterDTO.getApprovalStatus());
			checkForm.setCheck1099(masterDTO.isCheck1099());
			checkForm.setCheck1099Amount(decFormat.format(check1099Amount));
			checkForm.setApprovalStatusDesc( CheckWriterManager.getCheckStatusDescription(masterDTO.getApprovalStatus()) );
			
			switch ( (int)checkForm.getApprovalStatus() ) {
				case ApMasterDTO.APPROVAL_STATUS_APPROVED :
					checkForm.setPriorApproval(true);
					SimpleDateFormat sdFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
					Timestamp appTimestamp = masterDTO.getApprovedTmstmp();
					
					checkForm.setApprovalTimestamp(sdFormat.format(appTimestamp));
					showPrinting = true;
					break;
				case ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY :
				case ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT :
					checkForm.setPriorApproval(false);
					showPrinting = true;
					break;
				case ApMasterDTO.APPROVAL_STATUS_PENDING :
				case ApMasterDTO.APPROVAL_STATUS_REJECTED :
					break;
			}
            
            DatabaseTransaction t = null;
            
            try {
    			t = new DatabaseTransaction(sessionUser);

    			//DbApVendor apVendor = FdmsDb.getInstance().getApVendor(t, (int) masterDTO.getVendorID());
    			UserDTO checkUser = FdmsDb.getInstance().getUser(masterDTO.getUserID());
    			DbLocation location = FdmsDb.getInstance().getLocation(t, (int) masterDTO.getLocationID());
    			
//    			if ( apVendor != null ) {
//    				checkForm.setPayee(apVendor.getName());
//    			}
    			checkForm.setPayee(masterDTO.getVendorName());
    			
    			if ( checkUser != null ) {
    				checkForm.setSignature(checkUser.getFirstName() + " " + checkUser.getLastName() );
    			}
    			
    			if ( location != null ) {
    				checkForm.setLocationAddr(location.getAddr1());
    				checkForm.setLocationCitySt( location.getCity() + ", " + 
    						location.getState() + " " + location.getZip() );
    				checkForm.setLocationName(location.getName() );
    			}
    			
    			DbCase theCase = FdmsDb.getInstance().getCase(t,masterDTO.getVitalsID());
                
                if ( theCase != null ) {
                	checkForm.setVitalsID( masterDTO.getVitalsID() );
                	checkForm.setVitalsName(theCase.getFirstName() + " " + theCase.getLastName());
        			checkForm.setVitalsCaseNumber(theCase.getContractCode());
                }
                
            } catch (Exception e) {
    			logger.error("Error : ", e);
    		} finally {
    			if (t != null) {
    				t.closeConnection();
    			}
    		}

            request.setAttribute( "CheckEditDisplayForm", checkForm );
            // Set collections in session
            SessionHelpers.setChapelListInSession(request) ;
            SessionHelpers.setVendorListInSession(request,locationID) ;
            //SessionHelpers.setApAccountListInSession(request,0) ;
            
    		CompanyOptionsManager coMgr = new CompanyOptionsManager ();
    		int checkApprovalRequired = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_CHECK_APPROVAL_REQUIRED);
    		//checkForm.setCheckApprovalRequired( checkApprovalRequired == 1 );
    		checkForm.setCheckPrintingAvailable(false);
    		
    		if ( checkApprovalRequired == 0 || showPrinting ) {
    			checkForm.setCheckPrintingAvailable(true);
    		}
    		   		

        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCheckEditDisplay.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowCheckEditDisplay .doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } 
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        
        return mapping.findForward("ShowCheckEditDisplayJsp");
    }
    
}
