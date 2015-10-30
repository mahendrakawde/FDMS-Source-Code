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
import org.apache.struts.util.LabelValueBean;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.LocaleEditForm;
import fdms.ui.struts.form.LocaleListForm;


public class ShowLocaleEdit extends Action {

    private Logger logger = Logger.getLogger(ShowLocaleEdit.class.getName());
    private ArrayList formErrors;

    /**
     * Called from WebFdmsLocaleAdmin.jsp, this action prepares to show
     * WebFdmsLocaleEdit.jsp.
     * The LocaleListForm is used to determine if adding or which
     * locale is being updated.
     * Then, the LocaleEdit form is created for the jsp to use..
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
    
        formErrors = new ArrayList();
        LocaleListForm form = (LocaleListForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        LocaleDTO dbLocale = null;
        LocaleEditForm localeEdit = new LocaleEditForm();
        ArrayList stateList = new ArrayList();
        ArrayList selectList = new ArrayList();
        ArrayList dbPriceList = new ArrayList();
        ArrayList priceList = new ArrayList();
        String submitType = form.getSubmitbutton();

        //AppLog.trace("ShowLocaleEdit action doPerform submit = " +submitType);

        // Exit
        if (submitType.equals("exit")) {
            ActionForward actionForward = mapping.findForward("webFDMSManagement");
            return actionForward;
        }

        // Change or Delete
        if (!submitType.equals("add") && FormatNumber.parseInteger(form.getLocaleID()) < 1) {
            //AppLog.trace("ShowLocaleEdit - Change/delete with no selection.");
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
            formErrors.add("localeID");            
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            return (new ActionForward(mapping.getInput()));
        }        

        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            int localeID = FormatNumber.parseInteger(form.getLocaleID());
            
            if (submitType.equals("delete")) {
                dbLocale = new LocaleDTO();
                
                LocaleManagerBean lmb = new LocaleManagerBean(sessionUser);
                lmb.deleteLocale(localeID);
                return mapping.findForward("showLocaleAdminGlobal");
            } else {
            
                //Populate the Company Information
                
                if (!submitType.equals("add")) {
                    //dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
                	dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), localeID);

                    localeEdit.setName(dbLocale.getName());
                    localeEdit.setNextContractNo(String.valueOf(dbLocale.getNextContractNo()));
                    localeEdit.setNextReceiptNo(String.valueOf(dbLocale.getNextReceiptNo()));
                    localeEdit.setNextNonCashNo(String.valueOf(dbLocale.getNextNonCashNo()));
                    localeEdit.setNumberOfUsers(String.valueOf(dbLocale.getNumberOfUsers()));
                    localeEdit.setNextPreNeedNo( String.valueOf(dbLocale.getNextPreNeedNumber()));
                    localeEdit.setInterfaceType(String.valueOf(dbLocale.getInterfaceType()));
                    localeEdit.setAbbitID(     String.valueOf(dbLocale.getAbbitID()));
                    localeEdit.setPreneedLicense( String.valueOf(dbLocale.getPreneedLicense()));
                    localeEdit.setLocaleNumber(  String.valueOf(dbLocale.getLocaleID()));
                    localeEdit.setBankID(       String.valueOf(dbLocale.getDefaultBankID()));
                    localeEdit.setInflationRate(    Double.toString(dbLocale.getDefaultInflationRate()*100));
                    localeEdit.setCommissionRate(     Double.toString(dbLocale.getDefaultCommission()*100));
                    localeEdit.setRefundRate(       Double.toString(dbLocale.getDefaultRefundRate()*100));
                    localeEdit.setSaleDateCode(    String.valueOf(dbLocale.getDefaultSaleDateCode()));
                    localeEdit.setDaysBeforeDue(    String.valueOf(dbLocale.getDaysBeforeDue()));
                    localeEdit.setAutoFillDateOfDeath(dbLocale.isAutoFillDateOfDeath());
                    localeEdit.setAutoFillArrangeDate(dbLocale.isAutoFillArrangeDate());
                    localeEdit.setCompanyID(sessionUser.getCompanyID());
                    localeEdit.setLocaleType(dbLocale.getFdmsLocaleType());
                    localeEdit.setFlagAcctInterfaceShowDates(dbLocale.isConfigAcctInterfaceDateRange());
                    localeEdit.setFlagShowFinancing(dbLocale.isConfigShowFinancing());
                    localeEdit.setCountry(dbLocale.getCountry());
                    
                } 
                else {
                	dbLocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), localeID);
                	
                	localeEdit.setLocaleNumber("0");
                	localeEdit.setCompanyID(sessionUser.getCompanyID());
                //	localeEdit.setNumberOfUsers(Integer.toString(dbLocale.getNumberOfUsers()));
                	localeEdit.setNumberOfUsers("5");
                }

                // Create the list collections
                //chai comment: not using webfdmsdata1 but use the dblookup of user.
                stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList();
//                stateList = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getStateList(sessionUser.getDbLookup());
                selectList.add(new OptionsList("--Select--","--Select--"));
                dbPriceList = new PriceListManager().getPriceListVersions(t, localeID );
                for (int i=0; i < dbPriceList.size(); i++) {
                    String listValue = (String)dbPriceList.get(i);
                    priceList.add(new OptionsList(listValue, listValue));
                }
                // Put bank list in session
                ProcessPnStatus.setBankListInSession(request, sessionUser);
                
                // Set Checkbox options for this page
                setLocaleConfigs(t, localeID, localeEdit);
                
                
                
                
                
                // Set collections in session
                localeEdit.setDirective(submitType);
                
                session.removeAttribute("localeEdit");
                session.removeAttribute("stateList");
                session.removeAttribute("selectList");
                session.removeAttribute("priceList");
                request.setAttribute("localeEdit",localeEdit);
                session.setAttribute("stateList",stateList);
                session.setAttribute("selectList",selectList);
                session.setAttribute("priceList", priceList);    
            }

        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowLocaleEdit.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in  ShowLocaleEdit.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        // Check for any errors
        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            return  new ActionForward(mapping.getInput());
        }    

        return mapping.findForward("localeEdit");
        
    }
    
    public void setLocaleConfigs (DatabaseTransaction t, long localeID, LocaleEditForm localeEdit ) {
    	FdmsDb fdmsdb = FdmsDb.getInstance();
        DbLocaleConfig[] configs = fdmsdb.getLocaleConfigForLocale(t, localeID);
        DbLocaleConfigType[] configTypes = fdmsdb.getLocaleConfigTypes(t);
        
        ArrayList configList = new ArrayList();
        ArrayList selectedConfigs = new ArrayList();
        
        for ( int i = 0; i < configTypes.length; i++ ) {
        	
        	DbLocaleConfigType configType = configTypes[i];
        	boolean isSelected = false;
        	
        	configList.add(configType);
        	
        	boolean found = false;
			for (int x = 0; x < configs.length; x++) {
				DbLocaleConfig config = (DbLocaleConfig) configs[x];

				if (config.getLocaleConfigTypeID() == configType.getId()) {
					found = true;
					if ( config.getValue() > 0 ) {
						isSelected = true;
					}
					break;
				}
			}

			if (found == false && configType.getDefaultValue() > 0 ) {
					isSelected = true;
			}
			
			if ( isSelected ) {
				selectedConfigs.add( String.valueOf(configType.getId()) );
			}
        	
        }
        
        LabelValueBean[] localeConfigA = new LabelValueBean [configList.size()];
        String [] selectedConfigA = new String [selectedConfigs.size()];
        
        for (int x = 0; x < configList.size(); x++) {
        	DbLocaleConfigType configType = (DbLocaleConfigType) configList.get(x);
        	localeConfigA[x] = new LabelValueBean(configType.getDescription(), String.valueOf(configType.getId()) );
		}
        
        for (int x = 0; x < selectedConfigs.size(); x++) {
        	selectedConfigA[x] = (String) selectedConfigs.get(x);
		}
        
        localeEdit.setLocaleConfigs(localeConfigA);
        localeEdit.setSelectedLocaleConfigs( selectedConfigA);
        
    }
    
}
