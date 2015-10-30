package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbComponent;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.ComponentItem;
import com.aldorsolutions.webfdms.beans.custom.HistoricalPaymentLineItem;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.MailUtil;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import fdms.ui.struts.form.PaymentForm;


public class ShowPayment extends Action {
    
    private Logger logger = Logger.getLogger(ShowPayment.class.getName());
    
    /**
     * Called from status window or case list, this action prepares to show a summary of payment information.
     * One table will show the payment components with their balances.
     * A second table will show the payment history for this case.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DatabaseTransaction t =null;
        DbComponent[] dbComponent = null;
        DbHistory[] dbHistory = null;
        DbVitalsDeceased dbVitalsDeceased = null;
        Boolean GoodTrans = true;
        
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        int vitalsid=0;
        //Set object to null value
        ArrayList <ComponentItem> dbComponentsList = null;
        ArrayList <HistoricalPaymentLineItem> dbHistoryList = null;
        PaymentForm payment = new PaymentForm();
        
        
        
        
        if (sessionUser.getSecurityAdjustFinancial() == 1) {
        	 payment.setVerifyFinancial("true");
        }else {
        	payment.setVerifyFinancial("false");
        }
     
                
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        try{           
            // Set the list of receipt types in session.
            SessionHelpers.setReceiptTypesInSession(request);
            
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            if (!com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().isCasePosted(t, vitalsid)) {
            	payment.setVerifyFinancial("false");
			}
            
            
            dbComponentsList = new ArrayList <ComponentItem> ();
            dbHistoryList = new ArrayList <HistoricalPaymentLineItem> ();
            int totalCharges = 0;
            double transItemTotalPaid = 0;
            double financeTotalPaid = 0;
            
            
            logger.debug("Get Financial Info");
            DbFinancialSummary dbFinancial = FdmsDb.getInstance().getFinancial(t, vitalsid);
            
            if (dbFinancial != null)
                totalCharges = dbFinancial.getLFinTotalCharges();
            
            logger.debug("Get Vitals Info");
            
            try {
                dbComponent = FdmsDb.getInstance().getComponentsForID( t , vitalsid );
                dbHistory =   FdmsDb.getInstance().getHistoryForCase( t , vitalsid );
                dbVitalsDeceased = FdmsDb.getInstance().getVitalsDeceased( t, vitalsid );
            } catch (Exception e) {
                logger.error("Exception in get Vitals Info : ", e);
            }
            
            if (dbComponent != null && dbHistory != null && dbVitalsDeceased != null) {
                int totalSales = 0;
                int totalPaid = 0;
                
                logger.debug("Create collection of components");
                
                for(int i=0 ; i < dbComponent.length ; i++)	{
                    ComponentItem listItem = new ComponentItem(dbComponent[i]);
                   	dbComponentsList.add(listItem);
                    totalSales += dbComponent[i].getSaleAmt();
                    totalPaid  += dbComponent[i].getPaidAmt();
                }
                
                logger.debug("Create Form Object Passing in dbComponents List To Total Values");
                payment.setComponentList(dbComponentsList);
                payment.setTotalPaid( FormatCurrency.toCurrency( totalPaid ) );
                
                financeTotalPaid = totalPaid; 
                
                payment.setTotalDue(  FormatCurrency.toCurrency( totalCharges ) );
                payment.setTotalBalance( FormatCurrency.toCurrency(totalCharges - totalPaid) );
                
                logger.debug("Create collection of payments");
                
  
                
                for(int i=0 ; i < dbHistory.length ; i++) {
                    if (dbHistory[i].getCHistSPF()=='P'){
                        HistoricalPaymentLineItem paymentItem = new HistoricalPaymentLineItem(dbHistory[i]);
                        dbHistoryList.add(paymentItem);
                        transItemTotalPaid = transItemTotalPaid + dbHistory[i].getLHistAmount();
                    }
                }
                
                
            }
            payment.setPaymentList( dbHistoryList );
            payment.setDateOfVoid(FormatDate.getCurrentDateFormatedMMDDYYYY());
            
            if (transItemTotalPaid < 0) {
            	transItemTotalPaid = Math.abs(transItemTotalPaid);
            }
            if (financeTotalPaid < 0) {
            	financeTotalPaid = Math.abs(financeTotalPaid);
            }
            
            
            transItemTotalPaid = Math.abs(transItemTotalPaid);
            
            if ( transItemTotalPaid == financeTotalPaid) {
            	GoodTrans = true;
            	payment.setGoodTrans(GoodTrans);
            }
            else {
            	double balance = financeTotalPaid - transItemTotalPaid;
            	GoodTrans = false;
            	payment.setGoodTrans(GoodTrans);
            	
            	CompanyManagerBean cmBean = new CompanyManagerBean();
            	CompanyDTO company = cmBean.getCompany(sessionUser.getCompanyID());
            	

            	DbLocation dbLocation = FdmsDb.getInstance().getLocation(t, sessionUser.getLocationId());
            	
    			DbCase theCase = null;
    			theCase 	= FdmsDb.getInstance().getCase(t,vitalsid);
    			String caseStatus = "Active";
    			if (theCase.getVoidedContractCode().equals("V")) {
    				caseStatus = "Voided";
    			}
    			
    			if (caseStatus.compareToIgnoreCase("Active")==0) {
            	  	
	            	ArrayList <String> emailArray = new ArrayList <String> ();
					// emailArray.add("cjongs@aldorsolutions.com");
	            	emailArray.add("santosh@aldorsolutions.com");
	            	emailArray.add("bshah@aldorsolutions.com");
					emailArray.add("support@aldorsolutions.com");
					
					String subject ="Error Payment";	
					StringBuffer message = new StringBuffer();
					String endLine = "\r\n";
					message.append(endLine + endLine);
					message.append("Automatic email: Please fix the error payment " + endLine + endLine);
					message.append("vitalMasterKey : "+ vitalsid  + endLine);
					message.append("Case Status    : "+ caseStatus + endLine + endLine );
					message.append("     Companyid : "+ sessionUser.getCompanyID()+ endLine);
					message.append("  Company Name : "+ company.getName()+ endLine);
					message.append("       DataURL : "+ company.getDataURL()+ endLine);
					message.append("      DBLookup : "+ company.getDbLookup()+ endLine);
					message.append(endLine + endLine);
					message.append("     User Name : "+ sessionUser.getFirstName() + " "+ sessionUser.getLastName() + endLine);
					message.append(" Email address : "+ sessionUser.getEmailAddr() + endLine);
					message.append("   Locale Name : "+ sessionUser.getLocaleName()+ endLine);
					message.append(" Location Name : "+ sessionUser.getLocationName() + endLine);
					message.append(endLine + endLine);
					if (dbLocation != null) {
						message.append("Location Phone : "+ dbLocation.getPhone() + endLine);
						message.append("Location Phone : "+ dbLocation.getPhoneAlternate() + endLine);
					}
					
	            	MailUtil.sendEmail(sessionUser,sessionUser.getConfigID(), emailArray, null, null, subject, message.toString());
    			}
            	
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowPayment.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowPayment.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors so far
        if( !errors.isEmpty() )   {
            saveErrors(request, errors );
        }
        //Place Objects In Scope
        session.setAttribute("payment",payment);
//        if (GoodTrans)  {     
//        	return mapping.findForward("payment");
//        }
//        else {
//        	return mapping.findForward("errorPayment");
//        }
        return mapping.findForward("payment");
    }
}
