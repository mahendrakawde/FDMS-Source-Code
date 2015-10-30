package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.beans.custom.QtyFinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialAddServicesForm;
import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.QtyFinancialInformationForm;


public class ProcessQtyFinancialAddServices extends Action {

    private Logger logger = Logger.getLogger(ProcessFinancialAddServices.class.getName());

    /**
     * Called from FinancialAddServices.JSP, this action Takes the selected
     * charge items and adds them to the charge collection.
     * Financial Information JSP is redisplayed with the added items.
     */
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request,
            HttpServletResponse response)        
        throws javax.servlet.ServletException, java.io.IOException {
                                            
        logger.debug("*** Entering ProcessFinancialAddServices ***");
       
        FinancialAddServicesForm form = (FinancialAddServicesForm) actionForm; 
       
        InventorySold soldData = null;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        
 
    
        if(form.getSubmitButton().equals("")){
            if (!form.getCategorySelect().equals("")){
            	String category = form.getCategorySelect();
                //System.out.println("pree"+category);
                session.setAttribute("category",category);
            	return mapping.findForward("financialAddServices");
            }
            }
        
        
        
        CompanyOptionsManager coMgr = new CompanyOptionsManager ();
        int qtyOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTIION_QTY);
        if (qtyOption == 0) {
        	return mapping.findForward("processFinancialAddServices");
        }
        
        
        
        int vitalsid = 0;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid<1){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput()));
        }
        //code added by haranesh patel
        String inventory = request.getParameter("listType");
        if (form.getSubmitButton().equals("filter")){
        	
        	return mapping.findForward("financialAddServices");	
        }
        
        
        // check if anything selected
        if (form.getSubmitButton().equals("save") && (null != form.getListValue()))  {
            logger.debug("ProcessAddServices. Number selected  :" + (form.getListValue()).length);
            
            int priceId=0;
            com.aldorsolutions.webfdms.beans.DbPriceList priceList = null;
            int uniqueSeqNo = 0;
            DatabaseTransaction t = null;
            DbChargeItem dbChargeItem = null;
            
            //Database Access Logic
            try {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

                //FinancialInformationForm financialInfo = (FinancialInformationForm) session.getAttribute("financialInformation");
                QtyFinancialInformationForm qtyfinancialInfo = new QtyFinancialInformationForm();
                
 
                
                
                qtyfinancialInfo.setLineItems(null);
                qtyfinancialInfo.setLineItems(null);
                TreeMap chargeSet = new TreeMap();
                qtyfinancialInfo.setSaveNeeded(true);
                
                DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
    			int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
                
                // Process Form: get charges selected
                for (int i = 0; i < (form.getListValue()).length; i++) {
                    
                    //logger.debug("Completing Process Add Services " + i);
                    priceId = FormatNumber.parseInteger((form.getListValue())[i]);
                    //Retreive A PriceList Record
                    PriceListManager plm = new PriceListManager();
                    priceList = plm.getPriceListItem(t,priceId);
                    
                    if (priceList==null){
                        logger.debug("Adding Services: Received invalid price list table ID : " + priceId);
                    } else {
                        //Construct a new DbChargeItem object for this DbPriceList
                        dbChargeItem = new DbChargeItem(
                            vitalsid,
                            priceList.getInvoiceSeqNo(),
                            0,
                            (int)priceList.getContrLine(),
                            priceList.getContrDescr(),
                            (int)priceList.getPrice(),
                            priceList.getGlAcctNo(),
                            (int)priceList.getTaxExempt(),
                            priceList.getTaxCode(),
                            'S',
                            "",
                            priceList.getCategory(),
                            (int)0,
                            (int)priceList.getPrice(),
                            priceList.getRecID(),
                            false,
                            0);
                        
                        // get valid sequence number
                        uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSet, dbChargeItem);
                        dbChargeItem.setSequenceNumber(uniqueSeqNo);
                        FinancialInformationLineItem collectionItem = new FinancialInformationLineItem(dbChargeItem);
                        // Calculate default GL account for display purposes only at this point
                        // GL will be recaclulated when and if saving financial page.
                        // If DbCharge object already has a GL account then use it instead of the default.
                        if (dbChargeItem.getGlAcct() == null || dbChargeItem.getGlAcct().compareTo("        ") <= 0){
                            soldData = new InventorySold();
                            FdmsDb.getInstance().getDefaultGlSalesAccts(
                                t,
                                soldData,
                                sessionUser.getRegion(),
                                qtyfinancialInfo.getChapelLocation(),
                                String.valueOf(dbChargeItem.getItemCategoryType()),
                                qtyfinancialInfo.getSaleType(),
                                qtyfinancialInfo.getDisposition());
                            collectionItem.setItemGLAccount( soldData.getAcctRevenue() );
                        } else {
                            // set display GL account got DbCharge object's gl account
                            collectionItem.setItemGLAccount( dbChargeItem.getGlAcct());
                        }
                
                        collectionItem.setModifiedItem(true); // modified this session
                        collectionItem.setNewItem(true); // added this session     
                        
                        if (orderByContLine == 1){	
                        	chargeSet.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
                        }else {
                        	chargeSet.put(new Integer(uniqueSeqNo), collectionItem);
                        }
                        
                        	
                    } 
                }
                // Calculate sales tax amount
//                FdmsDb.getInstance().calculateSalesTax(
//                    (DbUser)sessionUser,
//                    chargeSet,
//                    qtyfinancialInfo.getPriceListVersion(),
//                    vitalsid);
                
                // Sum new total of all charges in collection
                int totalcharges = SessionHelpers.sumCharges(chargeSet);                
                
                qtyfinancialInfo.setTotalCharge(FormatCurrency.toCurrency((long)totalcharges));
                qtyfinancialInfo.setLineItems(chargeSet);
                session.setAttribute("qtyFinancialInformation", qtyfinancialInfo);
                
                //set taxCode 

                DbSpeedData[]		contlist = null;
                ArrayList	<OptionsList> list = new ArrayList <OptionsList> ();
                contlist = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),sessionUser.getRegion(),"TaxCode");
            	String token = null;
            	list.add(new OptionsList("Default","Default"));
                if ( contlist != null )
                {
                    for (int i=0; i<contlist.length; i++){
                        String value = Integer.toString(contlist[i].getId());           
                        StringTokenizer st = new StringTokenizer(contlist[i].getData(), ",");
                    	if (st.hasMoreTokens()) {
                			token = st.nextToken();
                			value = token;
                		}
                        list.add(new OptionsList(value ,contlist[i].getData()));
                    }
                }    
                request.setAttribute("TaxCodes", list);


            } catch(PersistenceException pe) {
                //AppLog.criticalError("Persistence Exception in ProcessFinancialAddServices do.Perform. "+pe.getCause());
                logger.error("PersistenceException in doPerform() : " + pe);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.PersistenceException",pe.getCause()));
            } catch(Exception pe) {
                //AppLog.criticalError("Exception in  ProcessFinancialAddServices .doPerform. "+pe);
                //pe.printStackTrace();
                logger.error("Error in doPerform() : ", pe);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.GeneralException",pe.getMessage()));
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
        }
        
        if( !errors.isEmpty()) {
            saveErrors(request, errors );
        }
        
        //return mapping.findForward("financialInformation");
        return mapping.findForward("qtyFinancialInformation");
    }
}
