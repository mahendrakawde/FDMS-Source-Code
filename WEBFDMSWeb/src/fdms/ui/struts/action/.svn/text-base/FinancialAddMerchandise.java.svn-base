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

import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationAddMerchandiseLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialAddMerchandiseForm;
import fdms.ui.struts.form.FinancialInformationForm;

public class FinancialAddMerchandise extends Action {
    
    private Logger logger = Logger.getLogger(FinancialAddMerchandise.class.getName());

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("*** Entering FinancialAddMerchandise ***");
        
        FinancialAddMerchandiseForm form = (FinancialAddMerchandiseForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        java.util.ArrayList addMerchandiseList = new java.util.ArrayList();
        java.util.ArrayList addMerchandiseImageList = new java.util.ArrayList();
        String category = (String) session.getAttribute("category");
        if(category == null){
        	category = "";
        }
        if(category.equals("All")){
        	category = "";
        }

        
        //java.util.ArrayList addMerchandisePriceList = new java.util.ArrayList();
        int vitalsid=0;
        
        vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        
        if (vitalsid < 1){
            // AppLog.warning("AddServices. Invalid VitalsID to process:"+vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            logger.debug("AddMerchandise Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
        
        //Database Access Logic
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            FinancialInformationForm fiform =(FinancialInformationForm) session.getAttribute("financialInformation");
          
            if(category.equals("")){
            	  DbInvMaster[] dbInvMaster = FdmsDb.getInstance().getInvMasterForLocation(t, fiform.getChapelLocation());
            	for(int i=0 ; i < dbInvMaster.length ; i++) {
                    FinancialInformationAddMerchandiseLineItem famLineItem = new FinancialInformationAddMerchandiseLineItem(dbInvMaster[i]);
                    //addMerchandisePriceList.add(i,famLineItem);
                    addMerchandiseList.add(
                            new OptionsList(
                            famLineItem.getItemId(),
                            famLineItem.getItemName() + " " + famLineItem.getItemDescription() + " " + famLineItem.getItemPrice())
                            );
                    
                    form.setItemImage(dbInvMaster[i].getImageUrl());
                    
                    
                    addMerchandiseImageList.add(
                            i,
                            new OptionsList(
                            new Boolean(form.isImagePresent()).toString(),
                            dbInvMaster[i].getImageUrl().replace('\\','/'))
                            );
                    form.setItemImage(" ");
                }
              
            }
                      
            if(!category.equals("")){
            	int j = 0;
            	DbInvMaster[] dbInvMaster = FdmsDb.getInstance().getInvMasterForLocation(t, fiform.getChapelLocation());
            for(int i=0 ; i < dbInvMaster.length ; i++) {
            	if(dbInvMaster[i].getCProductLine().equals(category)){
                FinancialInformationAddMerchandiseLineItem famLineItem = new FinancialInformationAddMerchandiseLineItem(dbInvMaster[i]);
            
                //addMerchandisePriceList.add(i,famLineItem);
                addMerchandiseList.add(
                        new OptionsList(
                        famLineItem.getItemId(),
                        famLineItem.getItemName() + " " + famLineItem.getItemDescription() + " " + famLineItem.getItemPrice())
                        );
                
                form.setItemImage(dbInvMaster[i].getImageUrl());
                
                addMerchandiseImageList.add(j,
                        new OptionsList(
                        new Boolean(form.isImagePresent()).toString(),
                        dbInvMaster[i].getImageUrl().replace('\\','/')));
                j++;
                form.setItemImage(" ");
            	}
            }
            }
            
            
           
            
        } catch(PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }

        session.removeAttribute("category");
//added by haranesh patel
        
        DbSpeedData[] dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),  sessionUser.getRegion(), "PRODLINE");
        //AppLog.trace("dbSpeedData Length is "+ dbSpeedData.length );
        ArrayList dbCategoryList = new java.util.ArrayList();
        
        
        for(int i=0 ; i < dbSpeedData.length ; i++) {
            String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
            dbCategoryList.add(new OptionsList(listLabel,listLabel));
        }
 //finish       
        
        //request.setAttribute("addMerchandisePriceList",addMerchandiseList);
   
        session.setAttribute("inventory",form);
        session.setAttribute("categorylist", dbCategoryList);
        request.setAttribute("addMerchandiseList", addMerchandiseList);
        request.setAttribute("addMerchandiseImageList", addMerchandiseImageList);
        if( !errors.isEmpty() ){
            saveErrors(request, errors );
        }
        
        
        
        
        
        return mapping.findForward("financialAddMerchandise");
    }
}
