package fdms.ui.struts.action;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.LocaleEditForm;


public class ProcessLocaleEdit extends Action {

    private Logger logger = Logger.getLogger(ProcessLocaleEdit.class.getName());
    private ArrayList <String> formErrors;

    public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        formErrors = new ArrayList <String> ();
        LocaleEditForm form = (LocaleEditForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        LocaleDTO dbLocale = null;
        String submitType = form.getSubmitButton();
        

        if (!submitType.equals("save")) {
            return mapping.findForward("showLocaleAdminGlobal");
        }

        try {
        	
        	validateCompanyData(form, errors);
        	
            if (errors.isEmpty()) {
            	
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
            
            // Process the Locale add/change/or delete.
            if (form.getDirective().equals("add")) {
            	dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            	LocaleDTO dbLocaletmp = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
//            	Date expirationDate = dbLocale.getAccessExpirationDate();
//                Date lastFinanceChargeDate = dbLocale.getLastFinChgDate();
//                
//                LocaleDTO dbLocale2 = new LocaleDTO();
//                dbLocale2.setLastFinChgDate(lastFinanceChargeDate);
//                dbLocale2.setAccessExpirationDate(expirationDate);
//                dbLocale2.setInactiveCode("");
//                dbLocale2.setCompanyID( (int) sessionUser.getCompanyID());
//                dbLocale2.setCountry(dbLocale.getCountry());
                
            	setLocale(form, dbLocale, errors);
            	dbLocale.setCountry(dbLocaletmp.getCountry());
                LocaleManagerBean lmb = new LocaleManagerBean(sessionUser);
                lmb.addLocale(dbLocale);
                setLocaleConfig (t, form, dbLocale.getLocaleID());

            } 
            else {
            	//dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
            	dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), Integer.valueOf(form.getLocaleNumber()));
            	if (form.getDirective().equals("delete")) {
                    dbLocale.setInactiveCode("D");
                }  
            	
            	 setLocale(form, dbLocale, errors);
                 setLocaleConfig (t, form, dbLocale.getLocaleID());
                 
                 if (errors.isEmpty()) {
                     LocaleManagerBean lmb = new LocaleManagerBean(sessionUser);
                     lmb.updateLocale(dbLocale);

                 }
            }
            
            // Process any changes to the dbLocale
            t.save();

               
            }
            
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessLocaleEdit.doPerform. " + pe, pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessLocaleEdits.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }

        //Action Forward Logic

        ActionForward actionForward = mapping.findForward("showLocaleAdminGlobal");

        if (!errors.isEmpty()) {  	
            
            form.setDirective("change");
            form.setSubmitButton("change");	
            saveErrors(request, errors );
            request.setAttribute("formErrors", formErrors);
            actionForward = mapping.findForward("ShowLocaleEdit");
            
        }

        return actionForward;

    }
    /**
     * Called from ProcessLocaleEdit, this Method sets the Locale record
     * from the WebFDMSLocaleEdit.jsp values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/6/2002 10:13:59 AM)
     */

    public void setLocale(LocaleEditForm form, LocaleDTO dbLocale, ActionErrors errors) {
        String errorField = new String();

        try {

            errorField = "Name";
            dbLocale.setName(form.getName());

            errorField = "NextContractNo";
            dbLocale.setNextContractNo(Integer.parseInt(form.getNextContractNo()));

            errorField = "NextReceiptNo";
            dbLocale.setNextReceiptNo(Integer.parseInt(form.getNextReceiptNo()));

            errorField = "NextNonCashNo";
            dbLocale.setNextNonCashNo(Integer.parseInt(form.getNextNonCashNo()));

            errorField = "NumberOfUsers";
            dbLocale.setNumberOfUsers(Integer.parseInt(form.getNumberOfUsers()));

            dbLocale.setInterfaceType(  FormatNumber.parseInteger(form.getInterfaceType()));
            dbLocale.setPreneedLicense( form.getPreneedLicense());
            dbLocale.setAbbitID(      FormatNumber.parseInteger(form.getAbbitID()));
            dbLocale.setNextPreNeedNumber( FormatNumber.parseInteger(form.getNextPreNeedNo()));
            dbLocale.setDefaultBankID( FormatNumber.parseInteger(form.getBankID()));
            dbLocale.setDefaultInflationRate( FormatNumber.parseDouble( form.getInflationRate())/100.0);
            dbLocale.setDefaultCommission( FormatNumber.parseDouble( form.getCommissionRate())/100.0);
            dbLocale.setDefaultRefundRate( FormatNumber.parseDouble( form.getRefundRate())/100.0);
            dbLocale.setDefaultSaleDateCode( FormatNumber.parseInteger(form.getSaleDateCode()));
            dbLocale.setDaysBeforeDue(  FormatNumber.parseInteger(form.getDaysBeforeDue()));
            dbLocale.setAutoFillDateOfDeath(form.isAutoFillDateOfDeath());
            dbLocale.setAutoFillArrangeDate(form.isAutoFillArrangeDate());
            dbLocale.setFdmsLocaleType( form.getLocaleType() );
            dbLocale.setConfigAcctInterfaceDateRange( form.isFlagAcctInterfaceShowDates());
            dbLocale.setConfigShowFinancing(form.isFlagShowFinancing());
            dbLocale.setCountry(form.getCountry());
         
            
        } catch (Exception e) {
            logger.error("Exception in setLocale() : ", e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.set" +errorField));
        }

        return;

    }
    
    public void setLocaleConfig(DatabaseTransaction t, LocaleEditForm form, int localeID) {
    	FdmsDb fdmsdb = FdmsDb.getInstance();
        DbLocaleConfig[] configs = fdmsdb.getLocaleConfigForLocale(t, localeID);
        DbLocaleConfigType[] configTypes = fdmsdb.getLocaleConfigTypes(t);
        
        String [] selectedConfigs = form.getSelectedLocaleConfigs();
        
        for ( int i = 0; i < configs.length; i++ ) {
        	DbLocaleConfig config = configs[i];
        	
        	boolean found = false;
        	
        	if ( selectedConfigs != null ) {
            	for ( int x = 0; x < selectedConfigs.length; x++ ) {
            		int type = Integer.parseInt( selectedConfigs[x] );
            		
            		if ( config.getLocaleConfigTypeID() == type ) {
            			config.setValue(1);
            			found = true;
            			break;
            		}
            	}
        	}
        	
        	if ( found == false ) {
        		config.setValue(0);
        	}
        	
        	t.addPersistent(config);
        }
        
        for ( int i = 0; i < configTypes.length; i++ ) {
        	
        	DbLocaleConfigType configType = configTypes[i];
        	
        	boolean isNew = true;
        	for ( int x = 0; x < configs.length; x++ ) {
        		DbLocaleConfig config = (DbLocaleConfig) configs[x];
        		
        		if ( config.getLocaleConfigTypeID() == configType.getId() ) {
        			isNew = false;
        			break;
        		}
        	}
        	
        	if ( !isNew ) {
        		continue;
        	}
        	
        	
        	boolean isSelected = false;
        	
        	if ( selectedConfigs != null ) {
        		for ( int x = 0; x < selectedConfigs.length; x++ ) {
        			int type = Integer.parseInt( selectedConfigs[x] );
        		
        			if ( configType.getId() == type ) {
        				isSelected = true;
        				break;
        			}
        		}
        	}
        	
			DbLocaleConfig lCfg = new DbLocaleConfig ();
			lCfg.setNew();
			lCfg.setLocaleConfigTypeID(configType.getId());
			lCfg.setLocaleID(localeID);
			lCfg.setValue( isSelected ? 1 : 0 );
			t.addPersistent(lCfg);
			
        }
        
    }
    
    /**
     * Called from ProcessLocaleEdit, this Method validates the Company
     * (locale) data from WebFDMSLocaleEdit.jsp. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (9/6/2002 9:36:20 AM)
     */
    public void validateCompanyData(fdms.ui.struts.form.LocaleEditForm form, ActionErrors errors) {
        int checkInt = 0;

        try {

            // Name is Required
            if (form.getName() == null || form.getName().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.nullName"));
                formErrors.add("localeName");
            }

            // Next Contract Number is required, must be numeric, and must be > 0.
            try {
                if (form.getNextContractNo() == null || form.getNextContractNo().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.nullNextContractNo"));
                    formErrors.add("nextContractNo");
                } else {
                    checkInt = Integer.parseInt(form.getNextContractNo());
                    if (checkInt < 1) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNextContractNo"));
                        formErrors.add("nextContractNo");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNextContractNo"));
                formErrors.add("nextContractNo");
            }

            // Next Non Cash Number is required, must be numeric, and must be > 0.
            try {
                if (form.getNextNonCashNo() == null || form.getNextNonCashNo().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.nullNextNonCashNo"));
                    formErrors.add("nextNonCashNo");
                } else {
                    checkInt = Integer.parseInt(form.getNextNonCashNo());
                    if (checkInt < 1) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNextNonCashNo"));
                        formErrors.add("nextNonCashNo");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNextNonCashNo"));
                formErrors.add("nextNonCashNo");
            }

            // Next Receipt Number is required, must be numeric, and must be > 0.
            try {
                if (form.getNextReceiptNo() == null || form.getNextReceiptNo().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.nullNextReceiptNo"));
                    formErrors.add("nextReceiptNo");
                } else {
                    checkInt = Integer.parseInt(form.getNextReceiptNo());
                    if (checkInt < 1) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNextReceiptNo"));
                        formErrors.add("nextReceiptNo");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNextReceiptNo"));
                formErrors.add("nextReceiptNo");
            }

            // Number of Users is required & must be numeric
            try {
                if (form.getNumberOfUsers() == null || form.getNumberOfUsers().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.nullNumberOfUsers"));
                    formErrors.add("numberOfUsers");
                } else {
                    checkInt = Integer.parseInt(form.getNumberOfUsers());
                    if (checkInt < 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNumberOfUsers"));
                        formErrors.add("numberOfUsers");
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.locale.invalidNumberOfUsers"));
                formErrors.add("numberOfUsers");
            }

        } catch (Exception e) {
            logger.error("Catching errors in ProcessLocaleEdit.validateCompanyData. ", e);
        }
        
        // Check invalid inflation rate
        try {
            Double.parseDouble( form.getInflationRate());
        } catch (Exception e){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.inflation"));
            formErrors.add("inflationRate");
        }
        // verify commission / refund are within guidlines
        double commission = FormatNumber.parseDouble( form.getCommissionRate());
        double refund   = FormatNumber.parseDouble( form.getRefundRate());
        if (refund > 100 || refund < 90){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.refunderror"));
            formErrors.add("refund");
        }
        // Maximum commission is 10%
        if (commission > 10){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionover10"));
            formErrors.add("commission");
        }
        // if commission over 5% then refund must be 100%
        if (commission > 5 && refund < 100){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionover5"));
            formErrors.add("commission");
        }
        // if commission <= 5% then refund must be 100%
        if (commission > 0 & commission <= 5 && refund < 95){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionunder5"));
            formErrors.add("commission");
        }
        // if commission is zero then refund at least 90%
        if (commission ==0 && refund < 90){
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.pn.commissionzero"));
            formErrors.add("commission");
        }
    }
}
