package fdms.ui.struts.action;

import java.util.ArrayList;
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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialAddMerchandiseForm;
import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.QtyFinancialInformationForm;
import fdms.ui.struts.form.QtyFinancialMerchandiseInformationForm;

public class ProcessQtyFinancialAddMerchandise extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFinancialAddMerchandise.class.getName());

    /**
     * From FinancialAddMerchandise.JSP this action takes the user selections
     * and adds them to the charges collection.
     * Then FinancialInformation is redisplayed.
     */
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {                                            
        
        logger.debug("*** Entering ProcessFinancialAddMerchandise ***");
        
        FinancialAddMerchandiseForm form = (FinancialAddMerchandiseForm) actionForm;
        InventorySold soldData = null;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DatabaseTransaction t = null;
        DbChargeItem dbChargeItem = null;
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        
        if(form.getSubmitButton().equals("")){
        if (!form.getCategorySelect().equals("")){
        	String category = form.getCategorySelect();
            //System.out.println("pree"+category);
            session.setAttribute("category",category);
        	return mapping.findForward("financialAddMerchandise");
        }
        }
        
        
        CompanyOptionsManager coMgr = new CompanyOptionsManager ();
        int qtyOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTIION_QTY);
        if (qtyOption == 0) {
        	return mapping.findForward("processFinancialAddMerchandise");
        }      
        
        
        
        int vitalsid = 0;
        
        if(sessionUser==null)	{
            errors.add(
                ActionErrors.GLOBAL_ERROR,
                new ActionError("error.login.invalid"));
        } else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid < 1){
                errors.add(
                    ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.ui.nocase"));
            }
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
               
        // check if anything selected
        if (form.getSubmitButton().equals("save") && (null != form.getListValue()))  {
            //Database Access Logic
            try{
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);                
                //FinancialInformationForm  financialInfo =
                 //   (FinancialInformationForm)session.getAttribute("financialInformation");   
                
                QtyFinancialMerchandiseInformationForm qtyFinancialMerchandiseInfo = new QtyFinancialMerchandiseInformationForm();
                qtyFinancialMerchandiseInfo.setLineItems(null);
                qtyFinancialMerchandiseInfo.setLineItems(null);
                TreeMap chargeSet = new TreeMap();
                qtyFinancialMerchandiseInfo.setSaveNeeded(true);
                
                FinancialInformationForm financialInfo =
                    (FinancialInformationForm)session.getAttribute("financialInformation");      
                TreeMap chargeSetFin = (TreeMap)financialInfo.getLineItems();               
                
                DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
    			int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
                
                for (int i = 0; i < (form.getListValue()).length ; i++) {
                    
                    int priceId = FormatNumber.parseInteger((form.getListValue())[i]);
                    //Retreive A PriceList Record
                    DbInvMaster priceList = FdmsDb.getInstance().getInvMaster(t, priceId);
                    if (priceList == null){
                        logger.debug("Adding Merchandise: Received invalid Inv table ID : " + priceId);
                    } else {
                        logger.debug("Add Merchandise: adding : " + priceId
                                     + "\nDescription : " + priceList.getCDescription());
                        
                        String priceCategory = "!";
                        // Construct a new DbChargeItem object for this DbPriceList
                        // Price category code is from the 2nd column of the prodline table (not the first char of prodline.tab)
                        // Search speed-data for the prodline entry matching this item's prodline.
                        priceCategory = FdmsDb.getInstance().getSpeedDataIgnoreCase(
                            sessionUser.getDbLookup(), 
                            sessionUser.getRegion(),
                            "PRODLINE",
                            priceList.getCProductLine());
                         
                        dbChargeItem = new DbChargeItem(
                            vitalsid,
                            priceList.getSequenceNo(),
                            0,
                            (int)priceList.getContractLineNo(),
                            priceList.getCDescription(),
                            (int)priceList.getLPrice(),
                            priceList.getCGLsalesAcct(),
                            (int)((priceList.getFTaxExempt()*100)+.005),
                            priceList.getCTaxCode(),
                            'S',
                            priceList.getCItemName(),
                            priceCategory,
                            priceList.getId(),
                            priceList.getLPrice(),
                            0,
                            false,
                            0);
                        
                        // get valid sequence number
                        int uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSet, dbChargeItem);
                        logger.debug("Unique sequence # : " + uniqueSeqNo);
                        
                        dbChargeItem.setSequenceNumber(uniqueSeqNo);
                        FinancialInformationLineItem collectionItem =
                            new FinancialInformationLineItem(dbChargeItem);
                        collectionItem.setItemPicture(priceList.getImageUrl());
                        //collectionItem.setItemImageURL(t);
                        collectionItem.setNewItem(true); // added this session
                        
                        // process serial number information
                        logger.debug("Pricelist cStockType : " + priceList.getCStockType());
                        logger.debug("Pricelist stockTypeSerial : " + DbInvMaster.STOCK_TYPE_SERIAL);
                        
                        if (priceList.getCStockType().equalsIgnoreCase(DbInvMaster.STOCK_TYPE_SERIAL)){                            
                            collectionItem.setStockType(DbInvMaster.STOCK_TYPE_SERIAL);
                            collectionItem.setSerialNumber("- Select -");
                            collectionItem.setSerialNumberModifiable(1);// it is modifiable
                            
                        
                            DbInvOnHand[] onhand = FdmsDb.getInstance().getInvOnHandForItem(t, collectionItem.getDbChargeItem().getInvSeqNo());
                            if (onhand==null){
                                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Error retrieving on-hand for: "+collectionItem.getDbChargeItem().getInvItemName()));
                            } else {
	                            ArrayList serials = new ArrayList();
	                            // AppLog.trace("Number of serial items:"+onhand.length);
	//                            financialChangeChargeLine.setSerialNumber(Integer.toString(fiLineItem.getDbChargeItem().getInvOnHandID()));
	                            collectionItem.setSerialNumber(Integer.toString(collectionItem.getDbChargeItem().getInvOnHandID()));
	                            //serials.add(new OptionsList("0-"+Integer.toString(collectionItem.getDbChargeItem().getInvSeqNo()),"- Select -"));
	                            for (int j=0; j<onhand.length; j++){
//	                                serials.add(new OptionsList(Integer.toString(onhand[j].getId())+"-"+Integer.toString(collectionItem.getDbChargeItem().getInvSeqNo()) ,onhand[j].getCSerialNumber()));
	                                
	                            	Iterator myIterator = chargeSetFin.values().iterator();    
		                            FinancialInformationLineItem aCharge;
		                            boolean hasNotSet = true;
		                            while(myIterator.hasNext()) {
		                            	aCharge = (FinancialInformationLineItem) myIterator.next();
		                            	if (aCharge.getStockType().compareToIgnoreCase(DbInvMaster.STOCK_TYPE_SERIAL) == 0) {
		                            		if (aCharge.getDbChargeItem().getInvSeqNo() == collectionItem.getDbChargeItem().getInvSeqNo()) {
			                            		if (aCharge.getSerialNumber().compareToIgnoreCase(onhand[j].getCSerialNumber())==0){
			                            			hasNotSet = false;
			                            		}
		                            		}
		                            	}
		                            	
		                            }
		                            
		                            if (hasNotSet){
		                            	serials.add(new OptionsList(Integer.toString(onhand[j].getId())+"-"+Integer.toString(collectionItem.getDbChargeItem().getInvSeqNo()) ,onhand[j].getCSerialNumber()));
		                            }
	                                
	                            }
	                            collectionItem.setSerialNumbers(serials);
	                            collectionItem.setSerialQty(onhand.length);


                            }
       
                        }
                        else {
                        	collectionItem.setSerialQty(0);
                        }
                        
                        // Calculate default GL account for display purposes only at this point
                        // GL will be recaclulated when and if saving financial page.
                        // If DbCharge object already has a GL account then use it instead of the default.
                        if (dbChargeItem.getGlAcct() == null
                            || dbChargeItem.getGlAcct().compareTo("        ") <= 0) {
                                
                            soldData = new InventorySold();
                            FdmsDb.getInstance().getDefaultGlSalesAccts(
                                t,
                                soldData,
                                sessionUser.getRegion(), 
                                qtyFinancialMerchandiseInfo.getChapelLocation(),  
                                String.valueOf(dbChargeItem.getItemCategoryType()), 
                                qtyFinancialMerchandiseInfo.getSaleType(), 
                                qtyFinancialMerchandiseInfo.getDisposition());
                            collectionItem.setItemGLAccount(soldData.getAcctRevenue());
                        } else {
                            // set display GL account got DbCharge object's gl account
                            collectionItem.setItemGLAccount( dbChargeItem.getGlAcct());
                        }
                        
                        if (orderByContLine == 1){	
                        	chargeSet.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
                        }
                        else {
                        	chargeSet.put(new Integer(uniqueSeqNo), collectionItem);
                        }
                                                                  
                    }
                } // end FOR
                
                // Calculate sales tax amount
//                FdmsDb.getInstance().calculateSalesTax(
//                    (DbUser)sessionUser,
//                    chargeSet,
//                    qtyFinancialMerchandiseInfo.getPriceListVersion(),
//                    vitalsid);
                
                
                // Sum new total of all charges in collection
                int totalcharges = SessionHelpers.sumCharges(chargeSet);
                qtyFinancialMerchandiseInfo.setTotalCharge(FormatCurrency.toCurrency((long)totalcharges));
                
                qtyFinancialMerchandiseInfo.setLineItems(chargeSet);
                session.setAttribute("qtyFinancialMerchandiseInformation", qtyFinancialMerchandiseInfo);
                
                // clean up
                
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
                logger.error("PersistenceException in doPerform() : " + pe);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.PersistenceException",pe.getCause()));
            }
            catch(Exception pe) {
                logger.error("Error in doPerform() : ", pe);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.GeneralException",pe.getMessage()));
            } finally {
                if (t != null) {
                    try {
                        t.closeConnection();
                        t = null;
                    } catch (Exception e) {
                        logger.error("Error in closeConnection() : ", e);
                    }
                }
            }
        } // end IF                
        
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
        }
       
        logger.debug("Add Merchandise - completed.");
        return mapping.findForward("qtyFinancialMerchandiseInformation");
        
     
    }
}
