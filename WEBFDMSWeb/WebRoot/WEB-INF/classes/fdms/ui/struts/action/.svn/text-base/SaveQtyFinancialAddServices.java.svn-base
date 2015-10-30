package fdms.ui.struts.action;

import java.util.Iterator;
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

import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialAddServicesForm;
import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.QtyFinancialInformationForm;
import fdms.ui.struts.form.QtyFinancialInformationPageForm;
import fdms.ui.struts.form.TableRowForm;


public class SaveQtyFinancialAddServices extends Action {

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
       
        QtyFinancialInformationForm form = (QtyFinancialInformationForm) actionForm;        
        InventorySold soldData = null;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
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
        
        // check if anything selected
            
            int priceId=0;
            com.aldorsolutions.webfdms.beans.DbPriceList priceList = null;
            int uniqueSeqNo = 0;
            DatabaseTransaction t = null;
            DbChargeItem dbChargeItem = null;
            
            //Database Access Logic
            try {
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                
                FinancialInformationForm financialInfo =
                    (FinancialInformationForm)session.getAttribute("financialInformation");      
                TreeMap chargeSetFin = (TreeMap)financialInfo.getLineItems();
                financialInfo.setSaveNeeded(true);
                
                QtyFinancialInformationForm qtyFinancialInfo =
                    (QtyFinancialInformationForm)session.getAttribute("qtyFinancialInformation");                
                TreeMap chargeSet = (TreeMap)qtyFinancialInfo.getLineItems();
                qtyFinancialInfo.setSaveNeeded(true);
                
//                for (int i = 0; i < (form.getLineItems().size());i++) {

                Iterator myIterator = chargeSet.values().iterator();
                FinancialInformationLineItem aCharge;
                int a = 0;
                String[] itemQtys = form.getItemQty();
                String[] taxCodes = form.getTaxCodes();
                String[] descriptions = form.getDescriptions();
                String[] unitPrices = form.getUnitPrices();
                String[] exempts = form.getExempts();
                
                DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
    			int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
                
                
                while(myIterator.hasNext()) {
                	aCharge = (FinancialInformationLineItem) myIterator.next();
                	aCharge.setItemQty(Integer.valueOf(itemQtys[a]));
                	//aCharge.setItemTaxCode(taxCodes[a]);
                	
                	priceId = aCharge.getDbChargeItem().getPriceListId();
//                    //Retreive A PriceList Record
                    PriceListManager plm = new PriceListManager();
                    priceList = plm.getPriceListItem(t,priceId);
                	
                    for (int i=0; i< aCharge.getItemQty(); i++) {
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
	                        uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSetFin, dbChargeItem);
	                        dbChargeItem.setSequenceNumber(uniqueSeqNo);
	                        if (taxCodes[a].compareToIgnoreCase("Default")==0){
	                        	dbChargeItem.setTaxCode(priceList.getTaxCode());
	                        } else {
	                        	dbChargeItem.setTaxCode(taxCodes[a]);
	                        }
	                        
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
	                                financialInfo.getChapelLocation(),
	                                String.valueOf(dbChargeItem.getItemCategoryType()),
	                                financialInfo.getSaleType(),
	                                financialInfo.getDisposition());
	                            collectionItem.setItemGLAccount( soldData.getAcctRevenue() );
	                        } else {
	                            // set display GL account got DbCharge object's gl account
	                            collectionItem.setItemGLAccount( dbChargeItem.getGlAcct());
	                        }
	                        //collectionItem.setItemTaxCode(aCharge.getItemTaxCode());
	                        if (taxCodes[a].compareToIgnoreCase("Default")==0){
	                        	collectionItem.setItemTaxCode(priceList.getTaxCode());
	                        } else {
	                        	collectionItem.setItemTaxCode(taxCodes[a]);
	                        }
	                        collectionItem.setItemTypeDescription(descriptions[a]);
	                        collectionItem.setItemPrice(unitPrices[a]);
	                        collectionItem.setItemExemptDollars(exempts[a]);
	                        
	                        collectionItem.setModifiedItem(true); // modified this session
	                        collectionItem.setNewItem(true); // added this session

	                        
	                        if (orderByContLine == 1){	
	                        	chargeSetFin.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
	                        }else {
	                        	chargeSetFin.put(new Integer(uniqueSeqNo), collectionItem);
	                        }
	                        
	                        
	                    }
                    }//end qty for each item
                    a++;
                } //end treemap
                	
                // Calculate sales tax amount
                FdmsDb.getInstance().calculateSalesTax(
                    sessionUser,
                    chargeSetFin,
                    financialInfo.getPriceListVersion(),
                    vitalsid);
                
                // Sum new total of all charges in collection
                int totalcharges = SessionHelpers.sumCharges(chargeSetFin);                
                
                financialInfo.setTotalCharge(FormatCurrency.toCurrency((long)totalcharges));               	
                //session.setAttribute("financialInformation", financialInfo);
                
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

        
        if( !errors.isEmpty()) {
            saveErrors(request, errors );
        }
        
        //return mapping.findForward("financialInformation");
        return mapping.findForward("financialInformation");
    }
    
}
