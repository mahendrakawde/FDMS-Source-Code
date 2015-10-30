package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.MessagingException;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorassist.webfdms.delegate.LocationManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbApMaster;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbBankAccount;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.AccountDistributionItem;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.BankAccountDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.checkwriter.model.BankAccountDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;
import com.aldorsolutions.webfdms.util.checks.CheckPrinter;

import fdms.ui.struts.form.CheckWriterForm;

public class ProcessApCheckWriter extends Action {

    private Logger logger = Logger.getLogger(ProcessApCheckWriter.class.getName());
    private ArrayList <String> formErrors;

    /**
     * Called from ApCheckWriter.JSP, this action is
     * the destination for the form. Multiple submit buttons are handled.
     * Depending on the action, the same JSP is redisplayed for entering
     * the next check to print.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList <String> ();
        CheckWriterForm form = (CheckWriterForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;

        String directive = form.getDirective();
        // AppLog.trace("ProcessCheckWriter. action:"+directive);
        // reset check preview window to empty, just in case.
        form.setPreviewFile("");
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            if( directive.equals("exit") )  {
                return mapping.findForward("financial");
            }
            else if( directive.equals("location") ){
                handleLocationChange(sessionUser, form, errors, request);
                setAcctList(sessionUser,form,errors,request);
                setUserLimit(form, sessionUser, t, errors);
            }
            else if( directive.equals("acctchange") ){
                handleAccountChange(sessionUser, form, errors, request);
            }
            else if( directive.equals("clear") ) {
                handleClear(sessionUser, form, errors,request);
            }
            else if( directive.equals("selectCase") ) {
            	request.setAttribute("checkWriterSearch", "true");
                return ( mapping.findForward("search") );
            }
            else if( directive.equals("selectVitalsCase") ) {
            	handleSelectCase(sessionUser, form, errors, request, response);
            }
            else if( directive.equals("clearVitalsCase") ) {
            	handleClearCase(sessionUser, form);
            }
            else if( directive.equals("save") || directive.equals("saveonly") || directive.equals("handcheck")) {
            	setUserLimit(form, sessionUser, t, errors);
                handleSave(sessionUser, form, errors, request, response);
                //handleClearVendor( sessionUser,  form,  errors,  request);
                
            }
            else if( directive.equals("AddVendor") ) {
                logger.debug("ProcessCheckWriter. Processing ADD VENDOR");
                form.setVendor("0"); // zero vendor ID means ADD MODE
                form.setVendorID(0);
                return forwardShowApVendorGlobal(mapping);
            }
            else if( directive.equals("EditVendor") ) {
                logger.debug("ProcessCheckWriter. Processing EDIT VENDOR");
                return forwardShowApVendorGlobal(mapping);
            }
            else if( directive.equals("vendorchange") ) {
                handleVendorChange(sessionUser, form, errors, request);
                setUserLimit(form, sessionUser, t, errors);
            }
            else if( directive.equals("AddDistribution") ) {
                handleAddDistribution(sessionUser, form, errors, request);
                setUserLimit(form, sessionUser, t, errors);
            }
            else if( directive.equals("RemoveDistribution") ) {
                handleRemoveDistribution(sessionUser, form, errors, request);
                setUserLimit(form, sessionUser, t, errors);
            }
            else {
                // AppLog.error("ProcessApCheckWriter unrecognized directive:"+directive);
            }
            
            // clean up
            t.save();
        }
        catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in ProcessCheckWriter.doPerform. "+pe.getMessage());
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
            logger.error("PersistenceException in doPerform() : " + pe);
        }
        catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
					t.closeConnection();
					t = null;
				}  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }

        if( !errors.isEmpty() )  {
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
        }

        return mapping.findForward("redisplayCheckWriterJsp");

        
        
    }


    protected ActionForward forwardShowApVendorGlobal(ActionMapping mapping) {
        return mapping.findForward("showApVendorGlobal");
    }

    
    protected long getApAcctID(DbUserSession sessionUser, HttpServletRequest request, ActionErrors errors, String defaultAcct){
		long apAcctID = 0;
		try {
			InvoiceManager imvMgr = new InvoiceManager();
			//DbApAccount account = imvMgr.getAccount(request, acctEditVendorsForm.getApAccountID());
			ArrayList <DbApAccount> accounts = imvMgr.getAccountList(request, sessionUser.getLocationId(),sessionUser.getRegion());
			//vendor.setDefaultAcct(FormatNumber.parseInteger(account.getAccountNumber()));
			//private long apAccountID;

			for ( DbApAccount account : accounts ) {
        		String Id = account.getAccountNumber();
        		String IdDefaultAcct = defaultAcct;
//        		String IdDefaultAcct = (recid);
        		if ( IdDefaultAcct.compareToIgnoreCase(Id) == 0  ) {
        			apAcctID = account.getId();
        			break;
        		}
        	}
			
		} catch ( Exception e ) {
			logger.debug("Exception: ", e);
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
			saveErrors(request, errors);
		}			
    	return apAcctID;
    }
    

    protected DbApAccount getAccountInfo(DbUserSession sessionUser,int recid){
    	DbApAccount arec = null;
    	DatabaseTransaction t = null;
    	try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

			//long apAcctID = getApAcctID( sessionUser,  request,  errors,  recid);         
            arec = FdmsDb.getInstance().getApAccount(t, recid);	
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }   
    	return arec;
    }
    
    
    /**
     * When account changes put balance to be distributed into distr-amount.
     * Creation date: (5/6/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleAccountChange(DbUserSession sessionUser, CheckWriterForm form, ActionErrors errors, HttpServletRequest request )
    throws PersistenceException {

        // retrieve collection of expense accounts
        ArrayList acctlist = form.getAccountDistributionList();
        
        // fetch AP account
        //int recid = FormatNumber.parseInteger(form.getAccount());
        int recid = (int) form.getApAccountID();
//        DatabaseTransaction t = null;
        DbApAccount arec = getAccountInfo(sessionUser,recid);
        
//        DbApAccount arec = null;
//
//        try {
//            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
//
//			//long apAcctID = getApAcctID( sessionUser,  request,  errors,  recid);         
//            arec = FdmsDb.getInstance().getApAccount(t, recid);	
//
//            
//            // end populate the list
//      
//        } catch (Exception e) {
//            logger.error("Error : ", e);
//        } finally {
//            if (t != null) {
//				t.closeConnection();
//				t = null;
//			}
//        }


//        if (arec == null){
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.noaccount",form.getAccount()));
//            return;
//        }

        int checkamount=0;
        try {
            checkamount = FormatCurrency.convertToCurrency( form.getCheckAmount());
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.checkamt"));
            formErrors.add("checkAmount");
        }
        
//        int check1099Amount=0;
//        try {
//        	check1099Amount = FormatCurrency.convertToCurrency( form.getCheck1099Amount());
//        } catch (Exception e){
//            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.checkamt"));
//            formErrors.add("check1099Amount");
//        }

        int distramount = sumDistributions( acctlist );
        form.setSumDistributions(String.valueOf(distramount));
        form.setDistAmount( FormatCurrency.toCurrency((long)checkamount - distramount));
        form.setDistMemo( arec.getDescription());
        form.setAccount(arec.getAccountNumber());
//        HttpSession session = request.getSession();
//        ArrayList vendors = (ArrayList)session.getAttribute("vendorList");
//        form.setVendor(((OptionsList)vendors.get(0)).getListValue());	     
		SessionHelpers.setApAccountListInSession(request,0) ;
        form.setFocus("distAmount");

    }
    /**
     * Add expense account to distribution list.
     * Creation date: (5/6/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleAddDistribution(DbUserSession sessionUser,
                                         CheckWriterForm form,
                                         ActionErrors errors,
                                         HttpServletRequest request)
                                         throws PersistenceException {

        

//    	int recid = FormatNumber.parseInteger(form.getAccount());
        int recid = (int) form.getApAccountID();
        DbApAccount arec = getAccountInfo(sessionUser,recid);
//    	String strRecid = arec.getAccountNumber();
        //String defaultAcct = "";
        //defaultAcct = arec.getAccountNumber();
//    	
    	//long apAcctID = getApAcctID( sessionUser,  request,  errors,  defaultAcct);   
    	
    	
        // AppLog.trace("handleAddDistribtuion. ID:"+recid);
        if (recid < 1){
            form.setAccount("0");
            return;
        }

        int amount = 0;
        try {
            amount = FormatCurrency.convertToCurrency( form.getDistAmount() );
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.distamt"));
            formErrors.add("distAmount");
            return;
        }

        // retrieve collection of expense accounts
        ArrayList <AccountDistributionItem> acctlist = form.getAccountDistributionList();
        // fetch AP account
//        DatabaseTransaction t = null;
//        DbApAccount arec = null;

//        try {
//            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
//            arec = FdmsDb.getInstance().getApAccount(t, (int) apAcctID);
//        } catch (Exception e) {
//            logger.error("Error : ", e);
//        } finally {
//            if (t != null){
//				t.closeConnection();
//				t = null;
//			}
//        }

        if (arec == null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.noaccount",form.getAccount()));
            return;
        }
//        ArrayList <AccountDistributionItem> acctlist = form.getAccountDistributionList();
//        int amount = 0;
//	      try {
//	          amount = FormatCurrency.convertToCurrency( form.getDistAmount() );
//	      } catch (Exception e){
//	          errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.distamt"));
//	          formErrors.add("distAmount");
//	          return;
//	      }       
        // construct new distribution object to add to list
        acctlist.add(
            new AccountDistributionItem(recid,
                arec.getAccountNumber(),
                arec.getDescription(),
                amount,
                form.getDistMemo()
                )
            );

        // update distribution amount balance
        handleAccountChange(sessionUser, form, errors, request);
        form.setFocus("account");
    }
    
    protected void handleClearVendor(DbUserSession sessionUser, CheckWriterForm form, ActionErrors errors, HttpServletRequest request)
    throws PersistenceException {
    	//AppLog.trace("ShowCheckWriter action doPerfrom");
       	
       	HttpSession session = request.getSession();
    	
    	

            DatabaseTransaction t = null;
    	try {
    		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
    		//form.setCheckDate( FormatDate.getCurrentDateFormatedMMDDYYYY() );
    		//form.setAccount("0");
    		//form.setLocation("0");
    		//form.setVendor("0");
    		//form.setCheckNumber("");
    		//form.setFocus("location");
    		// create empty account distribution list
    		form.setAccountDistributionList( new ArrayList <AccountDistributionItem>());
    		session.setAttribute( "checkWriterForm", form );
    		
    		
    		DbLocation[] userLocations = null;
    		userLocations = FdmsDb.getInstance().getLocationsForRegion(t, sessionUser.getRegion());
    		ArrayList<Long> locationAllIDs = new ArrayList<Long>();
    		UserManagerBean userManagerBean = new UserManagerBean();
    		String sLocationIDs[] = userManagerBean.getUserLocationIds( sessionUser.getId() );
    		
    		for(DbLocation location: userLocations ){
    			for(int i = 0; i < sLocationIDs.length; i++){
    				if (location.getId() == Integer.valueOf(sLocationIDs[i])) {
    					locationAllIDs.add( (long)location.getId());
    					break;
    				}
    			}
    		}	
    		LocationDAO locationDao = new LocationDAO(sessionUser);
    		ArrayList <LocationDTO> locations = locationDao.getLocationsByLocationIDs(locationAllIDs);
    		session.setAttribute("locationListAll", locations);		
    		
    		// get the vender for user
    		UserManagerBean uMgr = new UserManagerBean();
    		ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(sessionUser);

    		ArrayList <ApVendorLocationDTO> apVendorLocationListAll = apVendorLocationDao.getApVendorLocationByLocationIDs(locationAllIDs);
    		String [] jsVendorIDs = { String.valueOf(0) }; 
    		String js = uMgr.createLocationWithVendorJavascript ( apVendorLocationListAll, jsVendorIDs, sessionUser );
    		form.setLocationVendorMapJavascript(js);
    		
    		ArrayList<Long> vendorIDs = new ArrayList<Long>();
    		for ( ApVendorLocationDTO apVendorLocation : apVendorLocationListAll ) {
    			vendorIDs.add(apVendorLocation.getVendorID());
    		}
    		ApVendorDAO vendorDao = new ApVendorDAO(sessionUser);
    		ArrayList <ApVendorDTO> vendorList = vendorDao.getApVendorByVendorIDs(true,vendorIDs);
    		
    		if (vendorList.size() == 1) {
    			form.setVendorID((int)((ApVendorDTO)vendorList.get(0)).getVendorID());
    		}		
    		//ArrayList <ApVendorDTO> vendorList = new ArrayList <ApVendorDTO> ();
    		session.setAttribute("vendorListAll", vendorList);
    		
//    		
//    		// Set collections in session
//    		SessionHelpers.setChapelListInSession(request);
//    			// Now check and see how many chapels we have and if we have only one
//    			// then set it.
//    		ArrayList chapels = (ArrayList)session.getAttribute("chapelList");
//
//
//    		SessionHelpers.setVendorListInSession(request,sessionUser.getRegion());
//
//
//    		SessionHelpers.setApAccountListInSession(request,0) ;
//    			// Now check and see how many chapels we have and if we have only one
//    			// then set it.
//    		ArrayList accounts = (ArrayList)session.getAttribute("accountList");
//    		if (accounts.size() == 1) {
//    			form.setAccount(((OptionsList)accounts.get(0)).getListValue());			
//    		}
//    		
//    		CompanyOptionsManager coMgr = new CompanyOptionsManager ();
//    		int companyID = sessionUser.getCompanyID();
//    		int checkApprovalRqd = coMgr.getCompanyOptionValueForCompany(companyID, CompanyOptionsDTO.COMPANY_OPTION_CHECK_APPROVAL_REQUIRED);
//    		int checkUseLimits   = coMgr.getCompanyOptionValueForCompany(companyID, CompanyOptionsDTO.COMPANY_OPTION_CHECK_VENDOR_LIMITS);
//    		form.setCheckApprovalRequired( checkApprovalRqd == 1 );
//    		form.setCheckUseLimits( checkUseLimits == 1 ); 
    		
    	}
    	catch(PersistenceException pe) { 
    		logger.error("Persistence Exception in ShowCheckWriter.doPerform. " + pe);
    		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
    	}
    	catch(Exception pe) { 
    		logger.error("Exception in  ShowCheckWriter .doPerform. ", pe);
    		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
            
    	// Check for any errors so far
    	if( !errors.isEmpty() )   {
    		saveErrors(request, errors );
       	}

      }      
      
      
    
    
    /**
     * Clear form variables to start over again.
     * Creation date: (5/6/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleClear(DbUserSession sessionUser, CheckWriterForm form, ActionErrors errors, HttpServletRequest request)
    throws PersistenceException {
        form.setVendor(     "0");
        form.setVendorAddr1(  "" );
        form.setVendorAddr2(  "" );
        form.setVendorCityState("");
        form.setVendorState("");
        form.setVendorZip(    "");
        form.setVendorContact(  "");
        form.setVendorPhone(  "");
        form.setDistAmount(   "");
        form.setCheckAmount(  "");
        form.setDirective(    "none");
        form.setDistribution( "");
        form.setHandwritten(  false);
        form.setMemo(     "");
        form.setDistMemo(   "");
        form.setAccountDistributionList( new ArrayList <AccountDistributionItem> ());
        form.setFocus(      "vendor");
        //form.setLocationID(0);
        form.setVitalsID(0);
        form.setCheck1099(false);
        form.setCheck1099Amount("");
        form.setVitalsCaseNum("");
        form.setVitalsName("");
        form.setAccount(    "0");
        form.setApAccountID(0);
        form.setIsOneTimeVendor("flase");
        form.setOneTimeVendorName("");
        form.setSumDistributions("0");
        
        
//       	int recid = FormatNumber.parseInteger(form.getAccount());
//    	long apAcctID = getApAcctID( sessionUser,  request,  errors,  recid);   
//    	form.setApAccountID(apAcctID);
    }
    /**
     * Get new Next-Check-Number.
     * Creation date: (5/6/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleLocationChange(DbUserSession sessionUser, CheckWriterForm form, ActionErrors errors,HttpServletRequest request)
    throws PersistenceException {
    	HttpSession session = request.getSession();
        //int recid = FormatNumber.parseInteger( form.getLocation() ) ;
    	int recid = (int) form.getLocationID();
        // AppLog.trace("handleLocationChange. Location:"+recid);
        if (recid < 1){
            form.setCheckNumber("");
            return;
        }
        DatabaseTransaction t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        DbLocation arec = null;

        try {
            arec = FdmsDb.getInstance().getLocation(t, recid);
        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }

        if (arec == null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocationID()));
            return;
        }
        
        // populate fields in form
        form.setCheckNumber(  String.valueOf( arec.getNextCheckNumber()));
        form.setVendorID(form.getVendorID());
        form.setVendorAddr1("" );
        form.setVendorAddr2("" );
        form.setVendorCityState("");
        form.setVendorState("");
        form.setVendorZip("");
        form.setVendorContact("");
        form.setVendorPhone("");
        form.setAccount("");
        form.setMemo("");
        
        
        
        
        //SessionHelpers.setApAccountListInSession(request,recid);
//        SessionHelpers.setVendorListInSession(request,recid);
        //SessionHelpers.setVendorListInSession(request,sessionUser.getRegion());
        ArrayList <ApVendorDTO> vendorList = new ArrayList <ApVendorDTO> ();
        session.setAttribute("vendorListAll", vendorList);
        form.setFocus("vendor");
    }
    /**
     * Remove expense distribution from list.
     * Creation date: (5/6/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleRemoveDistribution(DbUserSession sessionUser, CheckWriterForm form, ActionErrors errors, HttpServletRequest request)
    throws PersistenceException {

        // retrieve collection of expense accounts
        ArrayList acctlist = form.getAccountDistributionList();
        int lineNum = FormatNumber.parseInteger( form.getRemoveline() ) ;
        
        if (lineNum < 0 || lineNum >= acctlist.size()){
            return;
        } else {
        	acctlist.remove(lineNum);
        }
        
        // update distribution amount balance
        handleAccountChange(sessionUser, form, errors, request);
        form.setFocus("account");
    }
    /**
     * Save this check to data files and optionally print a check.
     * Creation date: (5/8/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleSave(DbUserSession sessionUser,
                              CheckWriterForm form,
                              ActionErrors errors,
                              HttpServletRequest request,
                              HttpServletResponse response
                              ) throws PersistenceException {

        DatabaseTransaction t = null;
        DbApMaster [] checks = null;
        boolean approvalRequired = true;

        // retrieve collection of expense accounts
        ArrayList acctlist = form.getAccountDistributionList();
        int checkamount=0;

        try {
            checkamount = FormatCurrency.convertToCurrency( form.getCheckAmount());
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.checkamt"));
            formErrors.add("checkAmount");
        }
        
        int check1099Amount=0;
        if ( form.isCheck1099() ) {

            try {
            	check1099Amount = FormatCurrency.convertToCurrency( form.getCheck1099Amount());
            } catch (Exception e){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.checkamt"));
                formErrors.add("check1099Amount");
            }

        }
        
        
        logger.debug ( "sumDistributions(acctlist): " + sumDistributions(acctlist) );
        
        // Make sure expense distribution sum matches check total
        if (checkamount <=0 || checkamount != sumDistributions(acctlist)){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.unapplied"));
        }

        // Verify we have a valid vendor ID
        //int vendorid = FormatNumber.parseInteger(form.getVendor());
        int vendorid = (int) form.getVendorID();
        if (vendorid < 1) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.novendor",form.getVendorID()));
            formErrors.add("vendor");
        }
        
        // Verify we have a valid check date
        java.util.Date checkDate = null;
 
        try {
            checkDate = FormatDate.convertToDate( form.getCheckDate() );
        } catch (Exception e){
            logger.error("Error in handleSave() : ", e);
        }

        if (checkDate == null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.date"));
            formErrors.add("checkDate");
        }
        
        // Verify we have a valid location
        int locationID = 0;
        int localeID = 0;
        try {locationID = (int) form.getLocationID() ;}
        catch (Exception e){
            // unable to parse int from String
        }

        if (locationID < 1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocationID()));
            formErrors.add("location");
        }
        else { //get locale ID.
        	LocationDAO locationDAO = new LocationDAO(sessionUser);
        	try {
        		LocationDTO locationData = locationDAO.getLocation(form.getLocationID());
        		localeID = (int) locationData.getLocaleID();
        	}
        	catch (Exception e) {
        		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocationID()));
                formErrors.add("location");
        	}
        }    
        
        
        // Verify the check number is valid
        long checknumber = 0;

        try {checknumber = Long.parseLong( form.getCheckNumber());}
        catch(Exception e){
            // unable to parse int from String
        }

        if (checknumber < 1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.number"));
            formErrors.add("checkNumber");
        } else {
            try {
                // Make sure this check number not already used
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

                // getApCheckSet(DatabaseTransaction t, int locale, int location, long checknumber,	boolean includeVoids)
                //checks = FdmsDb.getInstance().getApCheckSet(t, sessionUser.getRegion(),0,checknumber,false);
                checks = FdmsDb.getInstance().getApCheckSet(t, localeID,locationID,checknumber,false,null);
                if (checks.length>0){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.numberalreadyexists"));
                }

            } catch (Exception e) {
                logger.error("Error : ", e);
            } finally {
                if (t != null) {
					t.closeConnection();
					t = null;
				}
            }
        }
        

        // determine if printing check or not
        String handcheck="N";
        if (form.getDirective().equals("saveonly") || form.getDirective().equals("handcheck")) {
            handcheck="Y";
        }
        // return if errors
        if (!errors.isEmpty()){
            return;
        }
        // Save AP information
        // AppLog.trace("ApCheckWriter:handleSave - beginning save");
        String locationName = "";
        String vendorName = "";
        
        // create new AP master
        DbApMaster newitem = null;
        int newitemid = 0;
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            newitem = new DbApMaster();
            newitem.setNew();
            newitem.setVendorID(  vendorid );
            newitem.setBalance(   0);
            newitem.setCheckDate( checkDate );
            newitem.setCheckNumber( checknumber);
            newitem.setDiscountAmount(0);
            newitem.setDueDate(   checkDate );
            newitem.setHandwritten( handcheck );
            newitem.setLocaleID(  localeID);
            newitem.setLocationID(  locationID);
            newitem.setMemo(    form.getMemo());
            newitem.setUserID(    sessionUser.getId());
            
            DbApVendor vendor = FdmsDb.getInstance().getApVendor(t, vendorid);
            vendorName = vendor.getName();
            t.removePersistent(vendor);
            newitem.setVendorName(vendorName);
            
            // set onetimevendorname to the db if it is the onetime vendor.
            DbLocation dbLocation = FdmsDb.getInstance().getLocation(t,(int) form.getLocationID());
            t.removePersistent(dbLocation);
            if (dbLocation.getOneTimeVendorCode().compareTo(vendor.getVendorCode()) == 0){
            	newitem.setVendorName(form.getOneTimeVendorName());
            	form.setIsOneTimeVendor("flase");
            }else {
            	newitem.setVendorName(vendorName);
            }
            
            
            
            if ( form.getVitalsID() > 0 ) {
            	newitem.setVitalsID(form.getVitalsID());
            }
            
            t.addPersistent(newitem);
            t.save();
            newitemid = newitem.getId();

            // if successful then ID is > 0
            if (newitemid < 1){
                throw new PersistenceException("Unable to SAVE AP Master check information to DBMS.");
            }
            // Fetch AP master just saved to DBMS
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            newitem = FdmsDb.getInstance().getApCheck(t,newitemid);
            if (newitem==null){
                throw new PersistenceException("Unable to ACCESS AP Master check information from DBMS.");
            }
            
//            DbApVendor vendor = FdmsDb.getInstance().getApVendor(t, vendorid);
//            vendorName = vendor.getName();


            // Loop through expense distributions and add each one
            Iterator myiter = acctlist.iterator();
            AccountDistributionItem aItem = null;
            while (myiter.hasNext()){
              aItem = (AccountDistributionItem)myiter.next();
              
              //DbApAccount arec = getAccountInfo(sessionUser,aItem.getAcctID());
              
              //long apAcctID = getApAcctID( sessionUser,  request,  errors,  aItem.getAcctID());   
              
              //newitem.addDistribution((int)apAcctID,aItem.getAmount(), aItem.getMemo(),  DbApDistributionHistory.CHECK_ITEM);
              newitem.addDistribution(aItem.getAcctID(),aItem.getAmount(), aItem.getMemo(),  DbApDistributionHistory.CHECK_ITEM);
            }
            
	      			// Record the DistributionHistory as a invoice transaction.  We are not going to 
	      			// record the discount because it will apply to the entire invoice.
	      		DbApDistributionHistory expenseHistory = new DbApDistributionHistory();
	      		expenseHistory.setNew();
	      		expenseHistory.setApMasterID(newitem.getId());
	      		
	           	DbApAccount		anAcct = getAnAccount( sessionUser,  vendor.getDefaultAcctID(),  errors);
	        	String acctNumber = "";
	        	String acctDesc = "";
	        		
				if (anAcct == null) {
					acctNumber = "";
					acctDesc = "";
				}
				else {
					acctNumber = anAcct.getAccountNumber();
					acctDesc = anAcct.getDescription();
				} 
	
	      		expenseHistory.setApAccountNumber(acctNumber);
	      		expenseHistory.setDiscountAmount(0);
	      		expenseHistory.setType(DbApDistributionHistory.CHECK);
	      		expenseHistory.setMemo(form.getMemo());
	      		expenseHistory.setAmount(newitem.getInvoiceTotal());
	      		t.addPersistent(expenseHistory);

            // Update next check number in location record
            DbLocation aloc = FdmsDb.getInstance().getLocation(t, locationID);
            if (aloc == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.nolocation",form.getLocationID()));
                return;
            }
            if ( (aloc.getNextCheckNumber() == checknumber) || (aloc.getNextCheckNumber() < checknumber)){
                aloc.setNextCheckNumber(  checknumber+1 );
            }
            

            //form.setCheckNumber(    String.valueOf(checknumber+1 ));
            form.setCheckNumber(    String.valueOf(aloc.getNextCheckNumber() ));
            aloc.setCashBalance(    aloc.getCashBalance() - checkamount);
            
            locationName = aloc.getName();

            newitem.setCheck1099(form.isCheck1099());
            
            if ( form.isCheck1099() ) {
            	newitem.setCheck1099Amount(check1099Amount);
            }
            
            // now we are going to set balance on the bankaccount
            BankAccountDAO bankDao = new BankAccountDAO(sessionUser);
			BankAccountDTO bankDto = new BankAccountDTO();
			bankDto = bankDao.getBankAccountByLocationId(locationID);
			if (bankDto != null) {
				DbBankAccount bankAccount = FdmsDb.getInstance().getBankAccount(t, bankDto.getBankAccountId());
	            int bankBalance = bankAccount.getBalance();
	            bankBalance = bankBalance - checkamount;
				bankAccount.setBalance(bankBalance);
				t.addPersistent(bankAccount);
			}
//            double externalLimit = aloc.getExternalVendorLimit();
//            double internalLimit = aloc.getInternalVendorLimit();
//            
//            if ( sessionUser.isUserLimitOverride() ) {
//            	externalLimit = sessionUser.getLimitExternalVendor();
//           		internalLimit = sessionUser.getLimitInternalVendor(); 
//            }
//            
//            double limit = 0.0;
//            
//            if ( vendor.isInternalVendor() ) {
//            	limit = internalLimit;
//            } else {
//            	limit = externalLimit;
//            }
//            
//            
//    		if ( form.isCheckApprovalRequired() == false ) {
//    			newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY);
//    		}
//    		else {
//    			// 
//    			double currLimit = limit * 100; 
//    			
//    			if ( checkamount <= currLimit ) {
//    				newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT);
//    				approvalRequired = false;
//    			}
//    		}
            
            // set the status of check.
            if (form.isCheckApprovalRequired() == false ){
            	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY);
            }
            else if (handcheck == "N") // this is save for approval. 
            {
            	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT);
            }
            else {
            	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_PENDING);
            }
           
            // Store to DBMS
            t.save();

        } catch (Exception e) {
            logger.error("Error : ", e);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }

        // Print check
        if (form.getDirective().equals("save")) {
        	CheckPrinter cp = new CheckPrinter ();
        	String pageName = cp.printCheckForCheckWriter(request, response, servlet.getServletContext(), sessionUser, errors, newitemid);
        	
            form.setPreviewFile(pageName);
        }

		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);

		if ( form.isCheckApprovalRequired() ) {
			
			try {
						StringBuffer appURL = request.getRequestURL();
						String viewDashboard = appURL.substring(0, appURL.indexOf("/webfdms")) + "/dashboard/";
						
						String subject = "FDMS Dashboard Check Created";
						String endLine = "\r\n";
						StringBuilder message = new StringBuilder();
						
						message.append("Check " + checknumber + " has been created." + endLine + endLine );
						message.append(viewDashboard + endLine + endLine);
						message.append("    Check Number: " +  checknumber + endLine);
						message.append("      Check Date: " + newitem.getCheckDate() + endLine);
						message.append("    Check Amount: " + FormatCurrency.toCurrency(checkamount) + endLine);
						message.append("   Location Name: " + locationName + endLine + endLine);
						message.append("           Payee: " + vendorName + endLine);
						message.append("  Vendor Address: " + form.getVendorAddr1() + endLine);
						message.append("                  " + form.getVendorAddr2() + endLine);
						message.append("                  " + form.getVendorCityState() + endLine);
						message.append("            Memo: " + newitem.getMemo() + endLine);
						message.append("            User: " +  user.getFirstName() + " " + user.getLastName() + endLine);
						
						
						if ( approvalRequired ) {
							message.append("        APPROVAL IS REQUIRED " + endLine );
						} else {
							message.append("        APPROVAL NOT REQUIRED; Check under limit" + endLine );
						}
						
						LocationManager locMgr = new LocationManager();
						//locMgr.sendLocationEmails(user, locationID, subject, message.toString());
						long tLocationID = form.getLocationID();
						//send email to accountant using S for submitted status
						locMgr.sendLocationEmailsByInvoiceStatus ( sessionUser, tLocationID , subject, message.toString(),"S");
						
			}
			catch (MessagingException me) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", me.getMessage()));
				logger.debug(me);
			}
			catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", e.getMessage()));
				logger.debug(e);
			}
	        
		} 

        handleClear(sessionUser, form, errors, request);
        handleClearVendor( sessionUser,  form,  errors,  request);
    }
    
    

    /**
     * Go to Case selection screen to tie case to check
     * Creation date: (6/14/2007)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleSelectCase(DbUserSession sessionUser,
                              CheckWriterForm form,
                              ActionErrors errors,
                              HttpServletRequest request,
                              HttpServletResponse response
                              ) throws PersistenceException {
    	
    	DbCase theCase = null;
    	
    	DatabaseTransaction t = null;

        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            theCase = FdmsDb.getInstance().getCase(t,form.getVitalsID());
            
            if ( theCase != null ) {
            	form.setVitalsName( theCase.getFirstName() + " " + theCase.getLastName() );
            	form.setVitalsCaseNum( theCase.getContractCode() );
            }
            
        } finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
    }
    
    /**
     * Go to Case selection screen to tie case to check
     * Creation date: (6/14/2007)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleClearCase(DbUserSession sessionUser,
                              CheckWriterForm form) throws PersistenceException {
    	
    	if ( form != null ) {
    		form.setVitalsID(0);
    		form.setVitalsName("");
    		form.setVitalsCaseNum("");
    	}
        
    }
    
    /**
     * Insert the method's description here.
     * Creation date: (5/6/2002 4:46:19 PM)
     * @param form fdms.ui.struts.form.CheckWriterForm
     * @param errors org.apache.struts.action.ActionErrors
     */
    protected void handleVendorChange(DbUserSession sessionUser, CheckWriterForm form, ActionErrors errors, HttpServletRequest request)
        throws PersistenceException {

        //int vendorid = FormatNumber.parseInteger( form.getVendor() )  ;
    	int vendorid = (int) (form.getVendorID());
        // AppLog.trace("handleVendorChange. Vendor:"+vendorid);
        DatabaseTransaction t = null;
        DbApVendor avendor = null;

        try {

            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            avendor = FdmsDb.getInstance().getApVendor(t, vendorid);

            if (avendor == null){
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.novendor",form.getVendor()));
                formErrors.add("vendor");
                return;
            }
            // populate vendor fields in form
            form.setVendorAddr1(  avendor.getAddr1() );
            form.setVendorAddr2(  avendor.getAddr2() );
            form.setVendorCityState(avendor.getCityState());
            form.setVendorState(avendor.getVendorState());
            form.setVendorZip(    avendor.getPostalCode());
            form.setVendorContact(  avendor.getContactName());
            form.setVendorPhone(FormatString.formatPhone(avendor.getPhone()));
            
            // This setup onetime vendor name
            DbLocation dbLocation = FdmsDb.getInstance().getLocation(t,(int) form.getLocationID());
            if (dbLocation.getOneTimeVendorCode().compareTo(avendor.getVendorCode()) == 0){
            	form.setOneTimeVendorName(avendor.getName());
            	form.setIsOneTimeVendor("true");
            }else {
            	form.setOneTimeVendorName("");
            	form.setIsOneTimeVendor("false");
            }
            
            
			int apAcctID = avendor.getDefaultAcctID();
			
			ApVendorLocationDTO vendorLocationDto = new  ApVendorLocationDTO();
			ApVendorLocationDAO vendorLocationDao = new ApVendorLocationDAO(sessionUser);
			vendorLocationDto = vendorLocationDao.getApVendorLocationByVendorIDLocaleIDLocationID(avendor.getId(), sessionUser.getRegion(), form.getLocationID());
			
			//now set the apacctid to the right account.
			DbApAccount		anAcct = null;
			DbApAccount[] alist	= null;
			
			int newAcctId = 0;
			
			if (vendorLocationDto != null) {
		        	anAcct = FdmsDb.getInstance().getApAccount(t,(int) vendorLocationDto.getDefaultAcctID());
			}
			else {
				anAcct = FdmsDb.getInstance().getApAccount(t,(int) avendor.getDefaultAcctID());
			}
		        	if (anAcct != null){
			        	alist = FdmsDb.getInstance().getApAccounts(t,anAcct.getAccountNumber(),"");
			        	for (int i=0; i < alist.length; i++){
			        		if (alist[i].getLocaleID()== sessionUser.getRegion()){
			        			newAcctId = alist[i].getId();
			        			break;
			        		}
			        	}
			        	if (newAcctId == 0){
			        		for (int i=0; i < alist.length; i++){
				        		if (alist[i].getLocaleID()== 0){
				        			newAcctId = alist[i].getId();
				        			break;
				        		}
				        	}
			        	}
		        	}
		     
            avendor.setDefaultAcctID(newAcctId);
           	anAcct = getAnAccount( sessionUser,  avendor.getDefaultAcctID(),  errors);
        	String acctNumber = "";
        	String acctDesc = "";
        		
			if (anAcct == null) {
				acctNumber = "";
				acctDesc = "";
			}
			else {
				acctNumber = anAcct.getAccountNumber();
				acctDesc = anAcct.getDescription();
			}           
            
            form.setAccount(    acctNumber);
            form.setMemo(     avendor.getNotes());
            form.setVendorID(vendorid);
            
            UserManagerBean uMgr = new UserManagerBean();
            ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(sessionUser);
            String [] stringLocationIDs = uMgr.getUserLocationIds(sessionUser.getId());
    		ArrayList<Long> locationAllIDs = new ArrayList<Long>();
    		for(int i=0;i<stringLocationIDs.length;i++){
    			locationAllIDs.add( Long.parseLong(stringLocationIDs[i]));
    		}
    		ArrayList <ApVendorLocationDTO> apVendorLocationListAll = apVendorLocationDao.getApVendorLocationByLocationIDs(locationAllIDs);
            
            
    		String [] jsVendorIDs = { String.valueOf(form.getVendorID()) }; 
    		String js = uMgr.createLocationWithVendorJavascript ( apVendorLocationListAll, jsVendorIDs, sessionUser );
    		form.setLocationVendorMapJavascript(js);	
            
            
            // handleAccountChange(sessionUser, form, errors, request);

            
            //int recid = FormatNumber.parseInteger(form.getAccount());
		        
			form.setApAccountID(avendor.getDefaultAcctID());
			DbApAccount arec = null;
	        arec = FdmsDb.getInstance().getApAccount(t, avendor.getDefaultAcctID());
	        
	        ArrayList acctlist = form.getAccountDistributionList();
	        int distramount = sumDistributions( acctlist );
	        int checkamount=0;
	        try {
	            checkamount = FormatCurrency.convertToCurrency( form.getCheckAmount());
	        } catch (Exception e){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.checks.checkamt"));
	            formErrors.add("checkAmount");
	        }
	        form.setDistAmount( FormatCurrency.toCurrency((long)checkamount - distramount));
	        if (arec == null) {
	        	form.setDistMemo("");
	        }else {
	        	form.setDistMemo( arec.getDescription());
	        }
	        
            form.setFocus(      "checkAmount");           
            
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
    }
    /**
     * Iterate collection and return sum of amounts.
     * Creation date: (5/7/2002 10:27:36 AM)
     * @return int
     * @param distlist java.util.ArrayList
     */
    protected int sumDistributions(ArrayList distlist) {
        java.util.Iterator myiter = distlist.iterator();
        int sum=0;
        AccountDistributionItem aItem = null;
        while (myiter.hasNext()){
            aItem = (AccountDistributionItem)myiter.next();
            sum += aItem.getAmount();
        }
        return sum;
    }
    
    private void setUserLimit (CheckWriterForm form, DbUserSession user, DatabaseTransaction t, ActionErrors errors) {
    	int vendorid = (int) form.getVendorID() ;
    	int locationid = (int) form.getLocationID();
    	double limit = 0.0;

        form.setUserLimit(0.0);
        DbApVendor avendor = null;
        DbLocation aloc = null;

        try {

            avendor = FdmsDb.getInstance().getApVendor(t, vendorid);
            aloc = FdmsDb.getInstance().getLocation(t, locationid);
            
            if (avendor == null || aloc == null){
                return;
            }
            
            double vendorlimit = 0;
            double userlimit = 0;
            if (avendor.isInternalVendor()){
            	vendorlimit = aloc.getInternalVendorLimit();
            	userlimit = user.getLimitInternalVendor();
            }
            else {
            	vendorlimit = aloc.getExternalVendorLimit();
            	userlimit = user.getLimitExternalVendor();        	
            }
            
            if (vendorlimit >0 && userlimit > 0) {
//	            double externalLimit = aloc.getExternalVendorLimit();
//	            double internalLimit = aloc.getInternalVendorLimit();
//	            
//	            if ( user.isUserLimitOverride() ) {
//	            	externalLimit = user.getLimitExternalVendor();
//	           		internalLimit = user.getLimitInternalVendor(); 
//	            }
//	            
//	            if ( avendor.isInternalVendor() ) {
//	            	limit = internalLimit;
//	            } else {
//	            	limit = externalLimit;
//	            }     
            	limit = userlimit; // limit for user no matter location is
            }
            else 
            if (vendorlimit == 0 && userlimit == 0){
            	limit = 0; //unlimited
            }
            else {
	            // since 0 is unlimited for location and user we need to get this case        
	            	if (vendorlimit == 0){
	            		limit = userlimit; // user overrides location limit
	            	}
	            	else {
	            		limit = vendorlimit; // location overrides user unlimited
	            	}     	   	
            }
            
            
            form.setUserLimit(limit);
            
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", e.getMessage()));
			logger.debug(e.getMessage(), e);
		} 
        
    }
    
    private void setAcctList ( DbUserSession user,CheckWriterForm form, ActionErrors errors, HttpServletRequest request) {
    	
    	try {
			SessionHelpers.setApAccountListInSession(request,(int) form.getLocationID()) ;
			HttpSession session = request.getSession();
			// Now check and see how many chapels we have and if we have only one
			// then set it.
			ArrayList accounts = (ArrayList)session.getAttribute("accountList");
			if (accounts.size() == 1) {
				form.setAccount(((OptionsList)accounts.get(0)).getListValue());			
			}
    	} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", e.getMessage()));
			logger.debug(e.getMessage(), e);
		} 
    	
    }
    
	private DbApAccount getAnAccount(DbUserSession user, int recid, ActionErrors errors) {
		 DbApAccount		anAcct = null;
		 DatabaseTransaction t = null;
	     
		 try {
	         t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
	         anAcct = FdmsDb.getInstance().getApAccount(t, recid);
		 } catch(Exception pe) {
	            logger.error("Exception in  ShowVendorEditDisplay.do ", pe);
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
	        } finally {
	            if (t != null) t.closeConnection();
	     }
		 return anAcct;
	}
    
}
