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
import org.apache.struts.util.LabelValueBean;

import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorLocationDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDisplayDTO;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CheckWriterForm;
import fdms.ui.struts.form.VendorChange;


/**
 * ShowApVendor Prepares form bean with DBMS data needed
 * to show the Vendor Change JSP page.
 * Creation date: (5/06/2002 4:13:09 PM)
 * @author:
 */
public class ShowApVendor extends Action {
    
    private Logger logger = Logger.getLogger(ShowApVendor.class.getName());
    
    /**
     * Called from ApCheckWriter.JSP, this action prepares to show
     * ApVendorChange.JSP.
     * The ApCheckWriter form is used to determine if adding or which
     * vendor is being updated.
     * Then the VendorChange form is created for the JSP to use.
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        //AppLog.trace("ShowVendorChange action doPerfrom");
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        CheckWriterForm	checkform = (CheckWriterForm)session.getAttribute( "checkWriterForm" );
        VendorChange	vendorform = new VendorChange();
        DbApVendor		avendor = null;
        if (checkform==null){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.session","AP Check Writer Form"));
            checkform = new CheckWriterForm();
        }
        
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);        
        DatabaseTransaction t = null;
        
        try {
            int vendorid = (int) checkform.getVendorID();
            vendorform.setType1099Values(get1099Types());
            if (vendorid < 1){
                //AppLog.trace("ShowApVendor - Add Vendor");
                vendorform.setVendorID(0);
                SecurityManagerBean smBean = new SecurityManagerBean();
    			SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
    			vendorform.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
            		
            } else {
                //AppLog.trace("ShowApVendor - Update vendor:"+vendorid);
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                avendor = FdmsDb.getInstance().getApVendor(t, vendorid);
                if (avendor==null){
                    //AppLog.error("ShowApVendor. Attempted update of vendorid:"+vendorid+" but vendor not found in DBMS");
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Vendor could not be accessed."));
                }
                else {
                    // Load form with DB data
                	
//                	DbApAccount		anAcct = getAnAccount( sessionUser,  avendor.getDefaultAcctID(),  errors);
                	String acctNumber = "";
                	String acctDesc = "";
//                		
//    				if (anAcct == null) {
//    					acctNumber = "";
//    					acctDesc = "";
//    				}
//    				else {
//    					acctNumber = anAcct.getAccountNumber();
//    					acctDesc = anAcct.getDescription();
//    				}
    				
    				//now set the apacctid to the right account.
    				DbApAccount		anAcct = null;
    				DbApAccount[] alist	= null;
    				int newAcctId = 0;
    				try {
    			        	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
    			        	anAcct = FdmsDb.getInstance().getApAccount(t,(int) avendor.getDefaultAcctID());
    			        	if (anAcct != null){
	    			        	alist = FdmsDb.getInstance().getApAccounts(t,anAcct.getAccountNumber(),"");
	    			        	for (int i=0; i < alist.length; i++){
	    			        		if (alist[i].getLocaleID()== sessionUser.getRegion()){
	    			        			newAcctId = alist[i].getId();
	    			        			avendor.setDefaultAcctID(alist[i].getId());
	    			        			acctNumber = alist[i].getAccountNumber();
	    			        			acctDesc = alist[i].getDescription();
	    			        			break;
	    			        		}
	    			        	}
	    			        	if (newAcctId == 0){
	    			        		for (int i=0; i < alist.length; i++){
	    				        		if (alist[i].getLocaleID()== 0){
	    				        			newAcctId = alist[i].getId();
	    				        			avendor.setDefaultAcctID(alist[i].getId());
	    				        			acctNumber = alist[i].getAccountNumber();
		    			        			acctDesc = alist[i].getDescription();
	    				        			break;
	    				        		}
	    				        	}
	    			        	}
    			        	}
    			        	
    			        	
    				} catch(PersistenceException pe) {
    		            logger.error("Persistence Exception in ShowVendorEditDisplay.doPerform. " + pe);
    		            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
    		        } catch(Exception pe) {
    		            logger.error("Exception in  ShowVendorEditDisplay.doPerform. ", pe);
    		            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
    		        } finally {
    		            if (t != null) t.closeConnection();
    		        }
    				
    		        if (anAcct == null) {
    					acctNumber = "";
    					acctDesc = "";
    				}
    				
                	
                    vendorform.setAccount(	acctNumber);
                    vendorform.setAddr1(	avendor.getAddr1());
                    vendorform.setAddr2(	avendor.getAddr2());
                    vendorform.setCity(		avendor.getCityState());
                    vendorform.setComments(	avendor.getNotes());
                    vendorform.setEmail(	avendor.getEmailAddr());
                    vendorform.setName(		avendor.getName());
                    vendorform.setVendorID(	vendorid );
                    vendorform.setZip(		avendor.getPostalCode());
                    vendorform.setContact(	avendor.getContactName());
                    vendorform.setPhone(FormatString.formatPhone(avendor.getPhone()));
                    vendorform.setLocation( String.valueOf(avendor.getLocationID()));
                    
                    vendorform.setVendorCode(avendor.getVendorCode());
                    vendorform.setVendorState(avendor.getVendorState());
                    vendorform.setVendorCountry(avendor.getVendorCountry());
                    vendorform.setPhone2(avendor.getPhone2());
                    vendorform.setFax(avendor.getFax());
                    vendorform.setAccountNumber(avendor.getAccountNumber());
                    vendorform.setDiscountPercentage(avendor.getDiscountPercentage());
                    vendorform.setDiscountIfInDays(avendor.getDiscountIfInDays());
                    vendorform.setDiscountDueInDays(avendor.getDiscountDueInDays());
                    vendorform.setVendor1099Type(avendor.getVendor1099Type());
                    vendorform.setVendor1099Payment(avendor.getVendor1099Payment());
                    vendorform.setTaxID(avendor.getTaxID());
                    vendorform.setCreditLimit(avendor.getCreditLimit());
                    
                    SecurityManagerBean smBean = new SecurityManagerBean();
        			SecurityConfigDTO security = smBean.getSecurityConfig(sessionUser.getCompanyID());
        			vendorform.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
                    
                    
         	    UserManagerBean uMgr = new UserManagerBean();
       	        ApVendorLocationDAO vendorLocDAO = new ApVendorLocationDAO(sessionUser);
       	        ArrayList <ApVendorLocationDTO> vendorLocs = vendorLocDAO.getApVendorLocationByVendorID(vendorid);
       	        ArrayList <ApVendorLocationDisplayDTO> vendorDisplayLocs = new ArrayList <ApVendorLocationDisplayDTO> ();
       	        ArrayList <UserLocationDTO> locations = uMgr.getLocations( sessionUser.getDbLookup(), (int) sessionUser.getId(), 
       					sessionUser.getCompanyID(), sessionUser.getRegion() ); 
       	        ArrayList <UserLocaleDTO> locales = uMgr.getLocales( sessionUser.getDbLookup(), (int) sessionUser.getId(), 
       					sessionUser.getCompanyID(), sessionUser.getRegion());
       		                   
    		        long localeID = 0;		        
    		        for ( ApVendorLocationDTO myVendorLoc : vendorLocs ) {
    		        	ApVendorLocationDisplayDTO display = new ApVendorLocationDisplayDTO();
    		        	
    		        	display.setVendorLocationID( myVendorLoc.getVendorLocationID() );
    		        	
    	               	DbApAccount		anAcctForLoc = getAnAccount( sessionUser,  myVendorLoc.getDefaultAcctID(),  errors);
                    	String acctNumberForLoc = "";
                    	String acctDescForLoc = "";
                    		
        				if (anAcct == null) {
        					acctNumberForLoc = "";
        					acctDescForLoc = "";
        				}
        				else {
        					acctNumberForLoc = anAcct.getAccountNumber();
        					acctDescForLoc = anAcct.getDescription();
        				}
    		        	
    		        	
    		        	
    		        	display.setDefaultAcct( acctNumberForLoc );
    		        	display.setLocaleName( String.valueOf(myVendorLoc.getLocaleID()) );
    		        	display.setLocationName( String.valueOf(myVendorLoc.getLocationID()) );
   	
    		        	for ( UserLocationDTO loc : locations ) {
    		        		long locID = loc.getLocationId();
    		        		if ( locID == myVendorLoc.getLocationID() ) {
    		        			display.setLocationName(loc.getName());
    		        			break;
    		        		}
    		        	}
    		        	
    		        	for ( UserLocaleDTO loc : locales ) {
    		        		long locID = FormatNumber.parseLong(loc.getLocaleId());
    		        		if ( locID == myVendorLoc.getLocaleID() ) {
    		        			localeID = locID;		        			
    		        			display.setLocaleName(loc.getName());
    		        			break;
    		        		}
    		        	}
    		        	
    		        	vendorDisplayLocs.add(display);
    		        	
    		        }
    		        try {
    		        	SessionHelpers.setApAccountListInSession(request, sessionUser.getLocationId());
    		        	
    				} catch ( Exception e ) {
    					logger.debug("Exception: ", e);
    					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
    					saveErrors(request, errors);
    				}                    
//    				long apAcctID = 0;
//    				try {
//    					InvoiceManager imvMgr = new InvoiceManager();
//    					//DbApAccount account = imvMgr.getAccount(request, acctEditVendorsForm.getApAccountID());
//    					ArrayList <DbApAccount> accounts = imvMgr.getAccountList(request, sessionUser.getLocationId(),localeID);
//    					//vendor.setDefaultAcct(FormatNumber.parseInteger(account.getAccountNumber()));
//    					//private long apAccountID;
//
//    					for ( DbApAccount account : accounts ) {
//    		        		String Id = account.getAccountNumber();
//    		        		String IdDefaultAcct = avendor.getDefaultAcct();
//    		        		if ( IdDefaultAcct.compareToIgnoreCase(Id) == 0  ) {
//    		        			apAcctID = account.getId();
//    		        			break;
//    		        		}
//    		        	}
//    					
//    				} catch ( Exception e ) {
//    					logger.debug("Exception: ", e);
//    					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
//    					saveErrors(request, errors);
//    				}			                    
                    vendorform.setApAccountID(avendor.getDefaultAcctID());
                    
                }

            }
            // Set collections in session
            session.setAttribute( "vendorChangeForm",vendorform);
            if (vendorid > 0 && avendor !=null) {
                //SessionHelpers.setApAccountListInSession(request,avendor.getLocationID()) ;
            	SessionHelpers.setApAccountListInSession(request,(int) checkform.getLocationID()) ;
            }
            else {
                SessionHelpers.setApAccountListInSession(request,(int) checkform.getLocationID()) ;
            }
            SessionHelpers.setChapelListInSession(request) ;
        }
        catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowVendorChange.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        }
        catch(Exception pe) {
            logger.error("Exception in  ShowVendorChange.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
            return mapping.findForward("showApCheckWriterJSP");
        }
        
        return mapping.findForward("showApVendorJsp");
    }
    
	private ArrayList <LabelValueBean> get1099Types( ) {
		
		ArrayList <LabelValueBean> array = new ArrayList <LabelValueBean> ();

		array.add ( new LabelValueBean("1099-A",Integer.toString(Constants.TYPE_1099_A) ) );
		array.add ( new LabelValueBean("1099-B",Integer.toString(Constants.TYPE_1099_B) ) );
		array.add ( new LabelValueBean("1099-C",Integer.toString(Constants.TYPE_1099_C) ) );
		array.add ( new LabelValueBean("1099-CAP",Integer.toString(Constants.TYPE_1099_CAP) ) );
		array.add ( new LabelValueBean("1099-DIV",Integer.toString(Constants.TYPE_1099_DIV) ) );
		array.add ( new LabelValueBean("1099-G",Integer.toString(Constants.TYPE_1099_G) ) );
		array.add ( new LabelValueBean("1099-H",Integer.toString(Constants.TYPE_1099_H) ) );
		array.add ( new LabelValueBean("1099-INT",Integer.toString(Constants.TYPE_1099_INT) ) );
		array.add ( new LabelValueBean("1099-LTC",Integer.toString(Constants.TYPE_1099_LTC) ) );
		array.add ( new LabelValueBean("1099-MISC",Integer.toString(Constants.TYPE_1099_MISC) ) );
		array.add ( new LabelValueBean("1099-OID", Integer.toString(Constants.TYPE_1099_OID) ) );
		array.add ( new LabelValueBean("1099-PART",Integer.toString(Constants.TYPE_1099_PART) ) );
		array.add ( new LabelValueBean("1099-Q",Integer.toString(Constants.TYPE_1099_Q) ) );
		array.add ( new LabelValueBean("1099-R",Integer.toString(Constants.TYPE_1099_R) ) );
		array.add ( new LabelValueBean("1099-S",Integer.toString(Constants.TYPE_1099_S) ) );
		array.add ( new LabelValueBean("1099-SA",Integer.toString(Constants.TYPE_1099_SA) ) );
		
		return ( array );		
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
