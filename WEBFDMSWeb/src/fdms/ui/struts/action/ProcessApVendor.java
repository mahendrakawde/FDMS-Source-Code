package fdms.ui.struts.action;

import java.util.ArrayList;

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
import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CheckWriterForm;
import fdms.ui.struts.form.VendorChange;


/**
 * ProcessApVendor Adds or updates Vendor Database
 * Creation date: (5/06/2002 4:13:09 PM)
 * @author: CJongs
 */
public class ProcessApVendor extends Action {
    
    private Logger logger = Logger.getLogger(ProcessApVendor.class.getName());

    /**
     * Called from ApCheckWriter.JSP, this action updates or
     * adds vendor information.
     */
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
    		VendorChange form = (VendorChange) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbApVendor			avendor = null;
        CheckWriterForm	checkform = (CheckWriterForm)session.getAttribute( "checkWriterForm" );
        if (checkform == null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.session","AP Check Writer Form"));
            checkform = new CheckWriterForm();
        }
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        // bail out if severe errors
        if( !errors.isEmpty() )  {
            // AppLog.info("ProcessApVendor Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput()));
        }
        String directive = form.getDirective();
        // AppLog.trace("ProcessApVendor. action:"+directive);
        if (directive.equals("cancel")){
            return mapping.findForward("showApCheckWriterJsp");
        }
        
        boolean duplicateVendorCode = false;
        // check that not allow to have the duplicate vendorCode.
        duplicateVendorCode = checkDuplicateVendorCode( sessionUser,  form);
        if (!duplicateVendorCode) {
        	
        boolean containQuote = false;
        containQuote = (form.getName().contains("\'") || form.getName().contains("\""));
        
        
        if (!containQuote) {
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

            
            // check if adding a new vendor, denoted by vendor ID == 0
            if( form.getVendorID() < 1 )	{
                avendor = new DbApVendor();
                avendor.setNew();
                t.addPersistent(avendor);
                LocationDAO locationDAO = new LocationDAO(sessionUser);
                LocationDTO location = locationDAO.getLocation(checkform.getLocationID());
                avendor.setLocale( (int) location.getLocaleID() );
                
                
        		
                
            }
            else {
                avendor = FdmsDb.getInstance().getApVendor(t, form.getVendorID());
                if (avendor==null){
                    // AppLog.error("ProcessApVendor. Attempted update of vendorid:"+form.getVendorID()+" but vendor not found in DBMS");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Vendor could not be accessed."));
                    saveErrors(request, errors );
                    return (new ActionForward(mapping.getInput()));
                }
            }
         
            
            
            avendor.setDefaultAcctID(	(int)	form.getApAccountID());
            avendor.setAddr1(			form.getAddr1());
            avendor.setAddr2(			form.getAddr2());
            avendor.setCityState(		form.getCity());
            avendor.setNotes(			form.getComments());
            avendor.setEmailAddr(		form.getEmail());
            avendor.setName(			form.getName());
            avendor.setPostalCode(		form.getZip());
            avendor.setContactName(		form.getContact());
            avendor.setPhone(FormatString.formatPhone(form.getPhone()));
            avendor.setLocationID(	(int)	checkform.getLocationID());
            
            avendor.setVendorState(		form.getVendorState());
            avendor.setVendorCountry(	form.getVendorCountry());
            avendor.setPhone2(FormatString.formatPhone(form.getPhone2()));   
            avendor.setFax(FormatString.formatPhone(form.getFax()));             
            avendor.setAccountNumber(	form.getAccountNumber());             
            avendor.setDiscountPercentage(	form.getDiscountPercentage());             
            //avendor.setDiscountIfInDays(	form.getDiscountIfInDays());            
            //avendor.setDiscountDueInDays(	form.getDiscountDueInDays()); 
            avendor.setDiscountIfInDays(	0);            
            avendor.setDiscountDueInDays(	0); 
            avendor.setVendor1099Type(	form.getVendor1099Type()); 
            //avendor.setVendor1099Payment(	form.getVendor1099Payment()); 
            float initValue = 0;
            avendor.setVendor1099Payment(	initValue);
            avendor.setTaxID(			form.getTaxID()); 
            avendor.setApprovedVendor(	form.getApprovedVendor()); 
            avendor.setCreditLimit(		form.getCreditLimit());   

            avendor.setVendorCode(		form.getVendorCode());
            
            if( form.getVendorID() < 1 )	{
                
                avendor.setDeleteCode("A");
                avendor.setInternalVendor(true);
                avendor.setApprovedVendor("Y");
            }
            
            // Update check form
            if (form.getVendorID() > 0) {
            checkform.setVendorAddr1(	form.getAddr1());
            checkform.setVendorAddr2(	form.getAddr2());
            checkform.setVendorCityState(form.getCity());
            checkform.setVendorState(form.getVendorState());
            checkform.setVendorContact( form.getContact());
            checkform.setVendorPhone(FormatString.formatPhone(form.getPhone()));
            checkform.setVendorZip(		form.getZip());
            checkform.setAccount(		form.getAccount());
            checkform.setMemo(			form.getComments());
            checkform.setApAccountID(form.getApAccountID());
            DbApAccount arec = getAccountInfo(sessionUser,(int) form.getApAccountID());
            checkform.setDistMemo( arec.getDescription());
            }
            
            // Check if DELETING a vendor
            if (directive.equals("delete")){
                avendor.remove();
                checkform.setVendor("0");
            }
            
            // clean up
            t.save();
            SessionHelpers.setVendorListInSession(request, sessionUser.getRegion());
           
            // If added a vendor, set new vendor code in check writer form
            ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO (sessionUser);
            LocationDAO locationDAO = new LocationDAO(sessionUser);
	        LocationDTO location = locationDAO.getLocation(checkform.getLocationID());
            checkform.setVendor( String.valueOf(avendor.getId()));
			ApVendorLocationDTO vendorLoc = new ApVendorLocationDTO();
	        vendorLoc.setDefaultAcctID( (int)	form.getApAccountID() );
	        vendorLoc.setVendorID(avendor.getId());
   	        vendorLoc.setLocationID(checkform.getLocationID());
	        vendorLoc.setLocaleID(location.getLocaleID());   	  	        
            if (form.getVendorID() < 1){
 
    	        vendorLocDAO.addApVendorLocation(vendorLoc);
            }
            else {
            	
            	ArrayList <ApVendorLocationDTO> vendors = new ArrayList <ApVendorLocationDTO> ();
            	vendors = vendorLocDAO.getApVendorLocationByLocationID(location.getLocaleID(), checkform.getLocationID());
            	for (ApVendorLocationDTO vendor: vendors){
            		if (vendor.getVendorID() == vendorLoc.getVendorID()) {
            			vendorLoc.setVendorLocationID(vendor.getVendorLocationID());
            		}
            	}
            	vendorLocDAO.updateApVendorLocation(vendorLoc);
            }
            
        } catch(PersistenceException pe) {
            // AppLog.criticalError("Persistence Exception in ProcessApVendor.doPerform. "+pe.getMessage());
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            // AppLog.criticalError("Exception in ProcessApVendor .doPerform. "+pe);
            // pe.printStackTrace();
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
        
        
        UserManagerBean uMgr = new UserManagerBean();
        ApVendorLocationDAO apVendorLocationDao = new ApVendorLocationDAO(sessionUser);
        String [] stringLocationIDs = uMgr.getUserLocationIds(sessionUser.getId());
		ArrayList<Long> locationAllIDs = new ArrayList<Long>();
		for(int i=0;i<stringLocationIDs.length;i++){
			locationAllIDs.add( Long.parseLong(stringLocationIDs[i]));
		}
		ArrayList <ApVendorLocationDTO> apVendorLocationListAll = apVendorLocationDao.getApVendorLocationByLocationIDs(locationAllIDs);
        
        
		String [] jsVendorIDs = { String.valueOf(checkform.getVendorID()) }; 
		String js = uMgr.createLocationWithVendorJavascript ( apVendorLocationListAll, jsVendorIDs, sessionUser );
		checkform.setLocationVendorMapJavascript(js);	
		
        }
        else {
        	logger.error("Eror Vendor name with sigle or douple quote");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.acct.vendor.Name"));
        }
		
        }
        else {
        	logger.error("Quick vendor add error: duplicate vendorCode");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.acct.vendor.DuplicateCode"));
        }
        ActionForward actionForward = mapping.findForward("showApCheckWriterJsp");
        if( !errors.isEmpty() )  {
            // AppLog.info("ProcessFinancialBillToPartiesAddChange Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        return  actionForward;
        
    }
    
    private boolean checkDuplicateVendorCode(DbUserSession sessionUser, VendorChange form) {
    	boolean duplicateCode = false;
		ApVendorDAO vendorDaoC = new ApVendorDAO(sessionUser);
		ArrayList<ApVendorDTO> vendorlistC = new ArrayList <ApVendorDTO>();
		vendorlistC =	vendorDaoC.getApVendorByCode(true,form.getVendorCode());
		if (vendorlistC.size() > 0) {
			if (form.getVendorID() < 1) {
				duplicateCode = true;
			}else {
				for (ApVendorDTO vendor : vendorlistC) {
					if(vendor.getVendorID() == form.getVendorID() ) {
						continue;
					} else {
						if(vendor.getVendorCode().compareToIgnoreCase(form.getVendorCode())== 0 ){
							duplicateCode = true;
						}
					}
				}
			}	
		}
		return duplicateCode;
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
}
